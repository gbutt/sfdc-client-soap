package integration

import com.sforce.ws.ConnectorConfig
import edu.colorado.oit.crm.salesforce.autoconfigure.SalesforceAutoConfiguration
import edu.colorado.oit.crm.salesforce.client.partner.SalesforceConnectionFactory
import edu.colorado.oit.crm.salesforce.client.partner.SalesforceOperationHandlerFactory
import org.springframework.boot.test.util.EnvironmentTestUtils
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import spock.lang.Specification


class SalesforceAutoConfigurationSpec extends Specification {

    private AnnotationConfigApplicationContext context

    def cleanup() {
        if (this.context != null) {
            this.context.close()
        }
    }

    def "it should build connector config"() {
        given:
        def username = 'testuser'
        def password = 'secret'
        def authEndpoint = 'https://test.salesforce.com'
        load(
                "salesforce.validate-config=false"
                ,"salesforce.connector-config.username="+username
                ,"salesforce.connector-config.password="+password
                ,"salesforce.connector-config.auth-endpoint="+authEndpoint
        )
        when:
        ConnectorConfig config = this.context.getBean(ConnectorConfig)
        then:
        config != null
        config.username == username
        config.password == password
        config.authEndpoint == authEndpoint
    }

    def "it should build operation handler factory"() {
        given:
        def maxRetries = 10
        def retryWaitTimeInMilliseconds = 100l
        load(
                "salesforce.validate-config=false"
                ,"salesforce.operation-handler-factory.maxRetries="+maxRetries
                ,"salesforce.operation-handler-factory.retryWaitTimeInMilliseconds="+retryWaitTimeInMilliseconds
        )
        when:
        SalesforceOperationHandlerFactory factory = this.context.getBean(SalesforceOperationHandlerFactory)

        then:
        factory.maxRetries == maxRetries
        factory.retryWaitTimeInMilliseconds == retryWaitTimeInMilliseconds
    }

    def "it should build connection factory"() {
        given:
        load(
                "salesforce.validate-config=false"
        )

        when:
        SalesforceConnectionFactory factory = this.context.getBean(SalesforceConnectionFactory)

        then:
        factory.config != null
        factory.operationHandlerFactory != null
    }

    private void load(String... environment) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext()
        EnvironmentTestUtils.addEnvironment(applicationContext, environment)
        applicationContext.register(SalesforceAutoConfiguration)
        applicationContext.refresh()
        this.context = applicationContext
    }

}