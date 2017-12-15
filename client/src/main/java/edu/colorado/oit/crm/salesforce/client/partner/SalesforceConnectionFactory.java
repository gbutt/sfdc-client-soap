package edu.colorado.oit.crm.salesforce.client.partner;

import com.sforce.soap.partner.Connector;
import com.sforce.soap.partner.IPartnerConnectionWrapper;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.soap.partner.PartnerConnectionWrapper;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import com.sforce.ws.SessionRenewer;
import lombok.extern.slf4j.Slf4j;

import javax.xml.namespace.QName;

@Slf4j
public class SalesforceConnectionFactory {

    private final ConnectorConfig config;
    private final SalesforceOperationHandlerFactory operationHandlerFactory;
    protected IPartnerConnectionWrapper cachedConnection;

    public SalesforceConnectionFactory(ConnectorConfig config, SalesforceOperationHandlerFactory operationHandlerFactory) {
        this.config = config;
        this.operationHandlerFactory = operationHandlerFactory;
        this.config.setSessionRenewer(expiredConfig -> {
            log.debug("Renewing expired session");
            expiredConfig.setSessionId( null );

            PartnerConnection connection = Connector.newConnection(expiredConfig);

            SessionRenewer.SessionRenewalHeader header = new SessionRenewer.SessionRenewalHeader();
            header.name = new QName( "urn:partner.soap.sforce.com", "SessionHeader" );
            header.headerElement = connection.getSessionHeader();

            return header;
        });
    }

    protected IPartnerConnectionWrapper newPartnerConnection() throws ConnectionException {
        return new PartnerConnectionWrapper(Connector.newConnection(this.config));
    }

    public IPartnerConnectionWrapper buildPartnerConnection() throws ConnectionException {
        if (cachedConnection == null) {
            log.debug(String.format("Building new Salesforce connection for username %s and endpoint %s", config.getUsername(), config.getAuthEndpoint()));
            cachedConnection = newPartnerConnection();
        }
        return new ManagedPartnerConnection(cachedConnection, operationHandlerFactory);
    }


}
