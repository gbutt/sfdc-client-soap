package edu.colorado.oit.crm.salesforce

import com.sforce.soap.partner.sobject.SObject
import spock.lang.Specification

class SObjectBuilderSpec extends Specification {

    def "it should add type"() {
        when:
        def builder = new SObjectBuilder('Type')

        then:
        builder.sob.type == 'Type'

    }

    def "it should add field"() {
        when:
        def builder = new SObjectBuilder('Type').addField('Field', 'Value')

        then:
        builder.sob.getField('Field') == 'Value'
    }

    def "it should add another SObjectBuilder as a field"() {
        when:
        def builder = new SObjectBuilder('Type').addField('Field', new SObjectBuilder('ParentType').addField('ParentField','Value'))

        then:
        builder.sob.getField('Field').getField('ParentField') == 'Value'
        builder.sob.getField('Field').type == 'ParentType'
    }

    def "it should wrap a provided SObject"() {
        given:
        def sob = new SObject()

        when:
        def builder = new SObjectBuilder(sob)

        then:
        builder.sob == sob
    }

}