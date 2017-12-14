package edu.colorado.oit.crm.salesforce.client.partner

import com.sforce.soap.partner.IPartnerConnectionWrapper
import com.sforce.soap.partner.IUpsertResult
import com.sforce.soap.partner.UpsertResult
import com.sforce.soap.partner.fault.ExceptionCode
import com.sforce.soap.partner.fault.UnexpectedErrorFault
import com.sforce.soap.partner.sobject.ISObject
import com.sforce.soap.partner.sobject.SObject
import com.sforce.ws.ConnectionException
import edu.colorado.oit.crm.salesforce.client.partner.SalesforceOperationHandler
import spock.lang.Specification

class SalesforceOperationHandlerSpec extends Specification {

    def 'it should execute operation on delegate'() {
        given:
        def mockDelegate = Mock(IPartnerConnectionWrapper)
        def method = mockDelegate.class.getDeclaredMethod("upsert", String.class, ISObject[].class)
        ISObject[] sobs = [new SObject()]
        def operationHandler = new SalesforceOperationHandler(mockDelegate, method, IUpsertResult.class, "externalId", sobs)

        when:
        def result = operationHandler.execute()

        then:
        1 * mockDelegate.upsert("externalId", sobs) >> [new UpsertResult()]
        result instanceof IUpsertResult[]
    }

    def 'it should retry operation on exception'() {
        given:
        def mockDelegate = Mock(IPartnerConnectionWrapper)
        def method = mockDelegate.class.getDeclaredMethod("upsert", String.class, ISObject[].class)
        ISObject[] sobs = [new SObject()]
        def operationHandler = new SalesforceOperationHandler(mockDelegate, method, IUpsertResult.class, "externalId", sobs)
        operationHandler.waitTimeInMilliseconds = 1


        when:
        def result = operationHandler.execute()

        then:
        1 * mockDelegate.upsert("externalId", sobs) >> {throw new ConnectionException('Unknown Error', new SocketException('connection reset'))}
        1 * mockDelegate.upsert("externalId", sobs) >> [new UpsertResult()]
        result instanceof IUpsertResult[]
        noExceptionThrown()
    }

    def 'it should not retry operation on salesforce exception'() {
        given:
        def mockDelegate = Mock(IPartnerConnectionWrapper)
        def method = mockDelegate.class.getDeclaredMethod("upsert", String.class, ISObject[].class)
        ISObject[] sobs = [new SObject()]
        def operationHandler = new SalesforceOperationHandler(mockDelegate, method, IUpsertResult.class, "externalId", sobs)


        when:
        def result = operationHandler.execute()

        then:
        1 * mockDelegate.upsert("externalId", sobs) >> {throw new UnexpectedErrorFault(exceptionCode: ExceptionCode.API_CURRENTLY_DISABLED)}
        0 * mockDelegate.upsert("externalId", sobs)
        thrown(UnexpectedErrorFault)
    }

    def 'it should throw exception after max retries exceeded'() {
        given:
        def mockDelegate = Mock(IPartnerConnectionWrapper)
        def method = mockDelegate.class.getDeclaredMethod("upsert", String.class, ISObject[].class)
        ISObject[] sobs = [new SObject()]
        def operationHandler = new SalesforceOperationHandler(mockDelegate, method, IUpsertResult.class, "externalId", sobs)
        operationHandler.waitTimeInMilliseconds = 1


        when:
        operationHandler.execute()

        then:
        2 * mockDelegate.upsert("externalId", sobs) >> {throw new ConnectionException('Unknown Error', new SocketException('connection reset'))}
        0 * mockDelegate.upsert("externalId", sobs)
        thrown(ConnectionException)

    }
}