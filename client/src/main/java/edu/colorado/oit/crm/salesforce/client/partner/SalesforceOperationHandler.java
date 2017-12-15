package edu.colorado.oit.crm.salesforce.client.partner;

import com.sforce.soap.partner.IPartnerConnectionWrapper;
import com.sforce.soap.partner.fault.UnexpectedErrorFault;
import com.sforce.ws.ConnectionException;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
* This class uses reflection to invoke the operation on the delegate instance.
* Why? It allows us to handle cross-cutting concerns in a concise manner.
* Some concerns handled by this class:
*  - retry operation on errors with backoff
*  - renew expired sessions and retry operation
*  - we can add other concerns such as metrics and counters if needed
*
*  It would be nice if we could turn this into a decorated operation, so we can use the decorator pattern to layer concerns.
* */

@Slf4j
public class SalesforceOperationHandler<T> {
    @Setter
    private int maxRetries = 2;
    @Setter
    private long waitTimeInMilliseconds = 1000;

    private final IPartnerConnectionWrapper delegate;
    private final Method method;
    private final Object[] args;


    public SalesforceOperationHandler(IPartnerConnectionWrapper delegate, Method method, Class<T> returnType, Object... args) {
        this.delegate = delegate;
        this.method = method;
        this.args = args;
    }

    public T execute() throws ConnectionException {
        SalesforceOperation<T> operation = buildOperation();
        return doOperation(operation);
    }

    private SalesforceOperation<T> buildOperation() {
        SalesforceOperation<T> operation = () -> {
            try {
                log.debug("Executing Salesforce operation " + method.getName());
                return (T) method.invoke(delegate, args);
            } catch (InvocationTargetException | IllegalAccessException ex) {
                if (ex.getCause() instanceof ConnectionException) {
                    throw (ConnectionException) ex.getCause();
                }
                throw new ConnectionException("Unknown Error", ex);
            }
        };

        return operation;
    }

    private T doOperation(SalesforceOperation<T> operation) throws ConnectionException {
        int currentAttempts = 0;
        while(currentAttempts < maxRetries) {
            try {
                currentAttempts++;
                return operation.apply();
            } catch (UnexpectedErrorFault ex) {
                log.error("Fault thrown by Salesforce", ex);
                log.debug(ex.toString());
                // rethrow Salesforce errors
                throw ex;
            } catch (ConnectionException ex) {
                log.error("Unknown Exception", ex);
                if (currentAttempts >= maxRetries) {
                    // rethrow exception once we exceed max attempts
                    throw ex;
                }
                try {
                    // sleep until next attempt
                    Thread.sleep(waitTimeInMilliseconds);
                } catch (InterruptedException iex) {
                    throw ex;
                }
            }
        }
        throw new ConnectionException("Illegal fallback in SalesforceOperationHandler. currentAttempts: " + currentAttempts + ", maxRetries: " + maxRetries);
    }
}
