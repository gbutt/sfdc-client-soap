package edu.colorado.oit.crm.salesforce.client.partner;

import com.sforce.soap.partner.IPartnerConnectionWrapper;
import lombok.Setter;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class SalesforceOperationHandlerFactory {
    private static final Map<String, Method> methodCache;

    static {
        Method[] methods = IPartnerConnectionWrapper.class.getMethods();
        methodCache = new HashMap<>(methods.length);
        for (Method m : methods) {
            methodCache.put(m.getName(), m);
        }
    }

    @Setter
    private Integer maxRetries = 2;
    @Setter
    private Long retryWaitTimeInMilliseconds = 1000l;

    public SalesforceOperationHandlerFactory() {
    }


    public <T> SalesforceOperationHandler<T> buildOperationFor(IPartnerConnectionWrapper delegate, String operationName, Class<T> returnType, Object... args) {
        Method method = methodCache.get(operationName);
        SalesforceOperationHandler<T> handler = new SalesforceOperationHandler(delegate, method, returnType, args);
        handler.setMaxRetries(maxRetries);
        handler.setWaitTimeInMilliseconds(retryWaitTimeInMilliseconds);
        return handler;
    }
}
