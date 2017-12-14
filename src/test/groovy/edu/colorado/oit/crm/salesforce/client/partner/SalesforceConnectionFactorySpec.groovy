package edu.colorado.oit.crm.salesforce.client.partner

import com.sforce.soap.partner.IPartnerConnectionWrapper
import com.sforce.ws.ConnectionException
import com.sforce.ws.ConnectorConfig
import spock.lang.Ignore
import spock.lang.Specification

class SalesforceConnectionFactorySpec extends Specification {

    def mockNewConnection = Mock(IPartnerConnectionWrapper)

    def 'it should build a new connection'() {
        given: 'a factory'
        ConnectorConfig config = new ConnectorConfig()
        SalesforceConnectionFactory factory = new SalesforceConnectionFactoryMock(config)

        when: 'we create the first connection'
        def connection = factory.buildPartnerConnection()

        then: 'it creates a new connection'
        factory.createdNewConnection == true
    }

    def 'it should reuse existing connection'() {
        given: 'a factory with an existing connection, assuming the session is valid'
        ConnectorConfig config = new ConnectorConfig()
        def mockExistingConnection = Mock(IPartnerConnectionWrapper)
        SalesforceConnectionFactory factory = new SalesforceConnectionFactoryMock(mockExistingConnection,config)

        when: 'we ask for a connection'
        def connection = factory.buildPartnerConnection()

        then: 'we get the existing connection'
        factory.createdNewConnection == false
    }

    @Ignore
    def 'it should renew existing connection'() {
        given:
        ConnectorConfig config = new ConnectorConfig(
                username: '',
                password: '',
                authEndpoint: 'https://test.salesforce.com/services/Soap/u/39.0')
        SalesforceConnectionFactory factory = new SalesforceConnectionFactory(config, new SalesforceOperationHandlerFactory())
        def connection = factory.buildPartnerConnection()
        assert connection.getServerTimestamp() != null

        when:
        config.setSessionId(null);
        connection.getServerTimestamp()

        then: noExceptionThrown()
    }

    class SalesforceConnectionFactoryMock extends SalesforceConnectionFactory {
        def createdNewConnection = false

        SalesforceConnectionFactoryMock(IPartnerConnectionWrapper connection, ConnectorConfig config) {
            this(config)
            this.cachedConnection = connection
        }

        SalesforceConnectionFactoryMock(ConnectorConfig config) {
            super(config, new SalesforceOperationHandlerFactory())
        }

        @Override
        protected IPartnerConnectionWrapper newPartnerConnection() throws ConnectionException {
            createdNewConnection = true
            return mockNewConnection
        }
    }
}