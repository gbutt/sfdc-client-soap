package edu.colorado.oit.crm.salesforce.autoconfigure;

import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;
import edu.colorado.oit.crm.salesforce.client.partner.SalesforceConnectionFactory;
import edu.colorado.oit.crm.salesforce.client.partner.SalesforceOperationHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SalesforceProperties.class)
public class SalesforceAutoConfiguration {

    @Autowired
    private SalesforceProperties properties;

    @Bean
    public ConnectorConfig buildSalesforceConfig() throws NullPointerException {
        return properties.getConnectorConfig();
    }

    @Bean
    public SalesforceOperationHandlerFactory buildOperationHandlerFactory() {
        return properties.getOperationHandlerFactory();
    }

    @Bean
    public SalesforceConnectionFactory buildSalesforceClientFactory(ConnectorConfig config, SalesforceOperationHandlerFactory operationHandlerFactory) throws ConnectionException {
        SalesforceConnectionFactory factory = new SalesforceConnectionFactory(config, operationHandlerFactory);
        if (properties.getValidateConfig()) {
            factory.buildPartnerConnection();
        }
        return factory;
    }
}
