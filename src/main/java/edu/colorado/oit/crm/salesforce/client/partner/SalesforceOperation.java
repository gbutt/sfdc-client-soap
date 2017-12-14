package edu.colorado.oit.crm.salesforce.client.partner;

import com.sforce.ws.ConnectionException;

@FunctionalInterface
public interface SalesforceOperation<R> {
    R apply() throws ConnectionException;
}
