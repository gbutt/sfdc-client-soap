package edu.colorado.oit.crm.salesforce

import com.sforce.soap.partner.Error
import com.sforce.soap.partner.IError
import com.sforce.soap.partner.StatusCode
import spock.lang.Specification


class SalesforceUtilSpec extends Specification {

    def 'it should format errors'() {
        given:
        IError[] errors = [
                new Error(statusCode: StatusCode.ALL_OR_NONE_OPERATION_ROLLED_BACK, message: 'message1', fields: (String[])['field1', 'field2']),
                new Error(statusCode: StatusCode.ALREADY_IN_PROCESS, message: 'message2')
        ]

        when:
        def result = SalesforceUtil.formatErrors(errors)

        then:
        result == 'ALL_OR_NONE_OPERATION_ROLLED_BACK: message1 [field1, field2]; ALREADY_IN_PROCESS: message2'
    }
}