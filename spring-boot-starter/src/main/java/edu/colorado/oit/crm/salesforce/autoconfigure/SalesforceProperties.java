package edu.colorado.oit.crm.salesforce.autoconfigure;

import com.sforce.ws.ConnectorConfig;
import edu.colorado.oit.crm.salesforce.client.partner.SalesforceOperationHandlerFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("salesforce")
@Data
public class SalesforceProperties {

    private ConnectorConfig connectorConfig = new ConnectorConfig();
    private SalesforceOperationHandlerFactory operationHandlerFactory = new SalesforceOperationHandlerFactory();

    private Boolean validateConfig = true;
}
