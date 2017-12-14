package edu.colorado.oit.crm.salesforce.client.partner

import com.sforce.soap.partner.*
import com.sforce.soap.partner.sobject.ISObject
import com.sforce.soap.partner.sobject.SObject
import edu.colorado.oit.crm.salesforce.client.partner.ManagedPartnerConnection
import edu.colorado.oit.crm.salesforce.client.partner.SalesforceOperationHandlerFactory
import spock.lang.Specification

class ManagedPartnerConnectionSpec extends Specification {

    def 'it should implement IPartnerConnectionWrapper'() {
        when:
        def (connection, mockConnection) = buildConnection()
        then:
        connection instanceof IPartnerConnectionWrapper
    }

    def 'it should proxy setPassword'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.setPassword('userId', 'password')
        then:
//        1 * mockOperationHandler.performOperation('setPassword', 'userId', 'password')
        1 * mockConnection.setPassword('userId', 'password')
    }

    def 'it should proxy resetPassword'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.login('userId', 'password')
        then:
//        1 * mockOperationHandler.performOperation('login', 'userId', 'password')
        1 * mockConnection.login('userId', 'password')
    }

    def 'it should proxy logout'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.logout()
        then:
//        1 * mockOperationHandler.performOperation('logout')
        1 * mockConnection.logout()
    }

    def 'it should proxy getUserInfo'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.getUserInfo()
        then:
//        1 * mockOperationHandler.performOperation('getUserInfo')
        1 * mockConnection.getUserInfo()
    }

    def 'it should proxy getServerTimestamp'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.getServerTimestamp()
        then:
//        1 * mockOperationHandler.performOperation('getServerTimestamp')
        1 * mockConnection.getServerTimestamp()
    }

    def 'it should proxy query'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.query('queryString')
        then:
//        1 * mockOperationHandler.performOperation('query', 'queryString')
        1 * mockConnection.query('queryString')
    }

    def 'it should proxy queryMore'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.queryMore('queryLocator')
        then:
//        1 * mockOperationHandler.performOperation('queryMore', 'queryLocator')
        1 * mockConnection.queryMore('queryLocator')
    }

    def 'it should proxy queryAll'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.queryAll('queryString')
        then:
//        1 * mockOperationHandler.performOperation('queryAll', 'queryString')
        1 * mockConnection.queryAll('queryString')
    }

    def 'it should proxy create'() {
        given:
        def (connection, mockConnection) = buildConnection()
        ISObject[] sobs = [new SObject()]

        when:
        connection.create(sobs)
        then:
//        1 * mockOperationHandler.performOperation('create', sobs)
        1 * mockConnection.create(sobs)
    }

    def 'it should proxy update'() {
        given:
        def (connection, mockConnection) = buildConnection()
        ISObject[] sobs = [new SObject()]

        when:
        connection.update(sobs)
        then:
//        1 * mockOperationHandler.performOperation('update', sobs)
        1 * mockConnection.update(sobs)
    }

    def 'it should proxy upsert'() {
        given:
        def (connection, mockConnection) = buildConnection()
        ISObject[] sobs = [new SObject()]

        when:
        connection.upsert('externalId', sobs)
        then:
//        1 * mockOperationHandler.performOperation('upsert', 'externalId', sobs)
        1 * mockConnection.upsert('externalId', sobs)
    }

    def 'it should proxy delete'() {
        given:
        def (connection, mockConnection) = buildConnection()
        String[] ids = ['ids']

        when:
        connection.delete(ids)
        then:
//        1 * mockOperationHandler.performOperation('delete', ids)
        1 * mockConnection.delete(ids)
    }

    def 'it should proxy undelete'() {
        given:
        def (connection, mockConnection) = buildConnection()
        String[] ids = ['ids']

        when:
        connection.undelete(ids)
        then:
//        1 * mockOperationHandler.performOperation('undelete', ids)
        1 * mockConnection.undelete(ids)
    }

    def 'it should proxy emptyRecycleBin'() {
        given:
        def (connection, mockConnection) = buildConnection()
        String[] ids = ['ids']

        when:
        connection.emptyRecycleBin(ids)
        then:
//        1 * mockOperationHandler.performOperation('emptyRecycleBin', ids)
        1 * mockConnection.emptyRecycleBin(ids)
    }

    def 'it should proxy search'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.search('searchString')
        then:
//        1 * mockOperationHandler.performOperation('search', ['searchString'])
        1 * mockConnection.search('searchString')
    }

    def 'it should proxy merge'() {
        given:
        def (connection, mockConnection) = buildConnection()
        IMergeRequest[] mergeRequests = [new MergeRequest()]

        when:
        connection.merge(mergeRequests)
        then:
//        1 * mockOperationHandler.performOperation('merge', mergeRequests)
        1 * mockConnection.merge(mergeRequests)
    }

    def 'it should proxy convertLead'() {
        given:
        def (connection, mockConnection) = buildConnection()
        ILeadConvert[] leadConverts = [new LeadConvert()]

        when:
        connection.convertLead(leadConverts)
        then:
//        1 * mockOperationHandler.performOperation('convertLead', leadConverts)
        1 * mockConnection.convertLead(leadConverts)
    }

    def 'it should proxy findDuplicates'() {
        given:
        def (connection, mockConnection) = buildConnection()
        ISObject[] sobs = [new SObject()]

        when:
        connection.findDuplicates(sobs)
        then:
//        1 * mockOperationHandler.performOperation('findDuplicates', sobs)
        1 * mockConnection.findDuplicates(sobs)
    }

    def 'it should proxy renderEmailTemplate'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.renderEmailTemplate(new IRenderEmailTemplateRequest[0])
        then:
//        1 * mockOperationHandler.performOperation('renderEmailTemplate', _ as IRenderEmailTemplateRequest[])
        1 * mockConnection.renderEmailTemplate(new IRenderEmailTemplateRequest[0])
    }

    def 'it should proxy sendEmailMessages'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.sendEmailMessage(new String[0])
        then:
//        1 * mockOperationHandler.performOperation('sendEmailMessage', _ as String[])
        1 * mockConnection.sendEmailMessage(new String[0])
    }

    def 'it should proxy sendEmail'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.sendEmail(new IEmail[0])
        then:
//        1 * mockOperationHandler.performOperation('sendEmail', _ as IEmail[])
        1 * mockConnection.sendEmail(new IEmail[0])
    }

    def 'it should proxy performQuickActions'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.performQuickActions(new IPerformQuickActionRequest[0])
        then:
//        1 * mockOperationHandler.performOperation('performQuickActions', _ as IPerformQuickActionRequest[])
        1 * mockConnection.performQuickActions(new IPerformQuickActionRequest[0])
    }

    def 'it should proxy process'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.process(new IProcessRequest[0])
        then:
//        1 * mockOperationHandler.performOperation('process', _ as IProcessRequest[])
        1 * mockConnection.process(new IProcessRequest[0])
    }

    def 'it should proxy retrieveQuickActionTemplates'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.retrieveQuickActionTemplates(new String[0], 'contextId')
        then:
//        1 * mockOperationHandler.performOperation('retrieveQuickActionTemplates', _ as String[], 'contextId')
        1 * mockConnection.retrieveQuickActionTemplates(new String[0], 'contextId')
    }

    def 'it should proxy executeListView'() {
        given:
        def (connection, mockConnection) = buildConnection()
        IExecuteListViewRequest req = new ExecuteListViewRequest()

        when:
        connection.executeListView(req)
        then:
//        1 * mockOperationHandler.performOperation('executeListView', _ as IExecuteListViewRequest)
        1 * mockConnection.executeListView(_ as IExecuteListViewRequest)
    }

    def 'it should proxy invalidateSessions'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.invalidateSessions(new String[0])
        then:
//        1 * mockOperationHandler.performOperation('invalidateSessions', _ as String[])
        1 * mockConnection.invalidateSessions(new String[0])
    }

    def 'it should proxy describeAllTabs'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeAllTabs()
        then:
//        1 * mockOperatinHandler.performOperation('describeAllTabs')
        1 * mockConnection.describeAllTabs()
    }

    def 'it should proxy describeDataCategoryGroupStructures'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeDataCategoryGroupStructures(new IDataCategoryGroupSobjectTypePair[0], true)
        then:
//        1 * mockOperatinHandler.performOperation('describeDataCategoryGroupStructures', _ as IDataCategoryGroupSobjectTypePair[], true)
        1 * mockConnection.describeDataCategoryGroupStructures(_ as IDataCategoryGroupSobjectTypePair[], true)
    }

    def 'it should proxy describeDataCategoryGroups'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeDataCategoryGroups(new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeDataCategoryGroups', _ as String[])
        1 * mockConnection.describeDataCategoryGroups(new String[0])
    }

    def 'it should proxy describeGlobal'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeGlobal()
        then:
//        1 * mockOperatinHandler.performOperation('describeGlobal')
        1 * mockConnection.describeGlobal()
    }

    def 'it should proxy describeGlobalTheme'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeGlobalTheme()
        then:
//        1 * mockOperatinHandler.performOperation('describeGlobalTheme')
        1 * mockConnection.describeGlobalTheme()
    }

    def 'it should proxy describeApprovalLayout'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeApprovalLayout('sobjectType', new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeApprovalLayout', 'sobjectType', _ as String[])
        1 * mockConnection.describeApprovalLayout('sobjectType', new String[0])
    }

    def 'it should proxy describePrimaryCompactLayouts'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describePrimaryCompactLayouts(new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describePrimaryCompactLayouts', _ as String[])
        1 * mockConnection.describePrimaryCompactLayouts(new String[0])
    }

    def 'it should proxy describeSearchableEntities'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSearchableEntities(true)
        then:
//        1 * mockOperatinHandler.performOperation('describeSearchableEntities', true)
        1 * mockConnection.describeSearchableEntities(true)
    }

    def 'it should proxy describeLayout'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeLayout('sobjectType', 'layoutName', new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeLayout', 'sobjectType', 'layoutName', _ as String[])
        1 * mockConnection.describeLayout('sobjectType', 'layoutName', new String[0])
    }

    def 'it should proxy describeAppMenu'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeAppMenu(AppMenuType.AppSwitcher, 'networkId')
        then:
//        1 * mockOperatinHandler.performOperation('describeAppMenu', AppMenuType.AppSwitcher, 'networkId')
        1 * mockConnection.describeAppMenu(AppMenuType.AppSwitcher, 'networkId')
    }

    def 'it should proxy describeSObjectListViews'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSObjectListViews('sobjectType', true, ListViewIsSoqlCompatible.ALL, 1, 2)
        then:
//        1 * mockOperatinHandler.performOperation('describeSObjectListViews', 'sobjectType', true, ListViewIsSoqlCompatible.ALL, 1, 2)
        1 * mockConnection.describeSObjectListViews('sobjectType', true, ListViewIsSoqlCompatible.ALL, 1, 2)
    }

    def 'it should proxy describeCompactLayouts'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeCompactLayouts('sobjectType', new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeCompactLayouts', 'sobjectType', _ as String[])
        1 * mockConnection.describeCompactLayouts('sobjectType', new String[0])
    }

    def 'it should proxy describeSoqlListViews'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSoqlListViews(new DescribeSoqlListViewsRequest())
        then:
//        1 * mockOperatinHandler.performOperation('describeSoqlListViews', _ as DescribeSoqlListViewsRequest)
        1 * mockConnection.describeSoqlListViews(_ as IDescribeSoqlListViewsRequest)
    }

    def 'it should proxy describePathAssistants'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describePathAssistants('sObjectType', 'picklistValue', new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describePathAssistants', 'sObjectType', 'picklistValue', _ as String[])
        1 * mockConnection.describePathAssistants('sObjectType', 'picklistValue', new String[0])
    }

    def 'it should proxy describeAvailableQuickActions'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeAvailableQuickActions('contextType')
        then:
//        1 * mockOperatinHandler.performOperation('describeAvailableQuickActions', 'contextType')
        1 * mockConnection.describeAvailableQuickActions('contextType')
    }

    def 'it should proxy describeQuickActions'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeQuickActions(new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeQuickActions', _ as String[])
        1 * mockConnection.describeQuickActions(new String[0])
    }

    def 'it should proxy describeSObjects'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSObjects(new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeSObjects', _ as String[])
        1 * mockConnection.describeSObjects(new String[0])
    }

    def 'it should proxy describeKnowledgeSettings'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeKnowledgeSettings()
        then:
//        1 * mockOperatinHandler.performOperation('describeKnowledgeSettings')
        1 * mockConnection.describeKnowledgeSettings()
    }

    def 'it should proxy describeTheme'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeTheme(new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeTheme', _ as String[])
        1 * mockConnection.describeTheme(new String[0])
    }

    def 'it should proxy describeNouns'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeNouns(new String[0], true, true)
        then:
//        1 * mockOperatinHandler.performOperation('describeNouns', _ as String[], true, true)
        1 * mockConnection.describeNouns(_ as  String[], true, true)
    }

    def 'it should proxy describeVisualForce'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeVisualForce(true, 'namespacePrefix')
        then:
//        1 * mockOperatinHandler.performOperation('describeVisualForce', true, 'namespacePrefix')
        1 * mockConnection.describeVisualForce(true, 'namespacePrefix')
    }

    def 'it should proxy describeSObject'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSObject('sobjectType')
        then:
//        1 * mockOperatinHandler.performOperation('describeSObject', 'sobjectType')
        1 * mockConnection.describeSObject('sobjectType')
    }

    def 'it should proxy describeSoftphoneLayout'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSoftphoneLayout()
        then:
//        1 * mockOperatinHandler.performOperation('describeSoftphoneLayout')
        1 * mockConnection.describeSoftphoneLayout()
    }

    def 'it should proxy describeSearchLayouts'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSearchLayouts(new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('describeSearchLayouts', _ as String[])
        1 * mockConnection.describeSearchLayouts(new String[0])
    }

    def 'it should proxy describeSearchScopeOrder'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.describeSearchScopeOrder()
        then:
//        1 * mockOperatinHandler.performOperation('describeSearchScopeOrder')
        1 * mockConnection.describeSearchScopeOrder()
    }

    def 'it should proxy getDeleted'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.getDeleted('sobjectType', new GregorianCalendar(), new GregorianCalendar())
        then:
//        1 * mockOperatinHandler.performOperation('getDeleted', 'sobjectType', _ as Calendar, _ as Calendar)
        1 * mockConnection.getDeleted('sobjectType', _ as Calendar, _ as Calendar)
    }

    def 'it should proxy getUpdated'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.getUpdated('sobjectType', new GregorianCalendar(), new GregorianCalendar())
        then:
//        1 * mockOperatinHandler.performOperation('getUpdated', 'sobjectType', _ as Calendar, _ as Calendar)
        1 * mockConnection.getUpdated('sobjectType', _ as Calendar, _ as Calendar)
    }

    def 'it should proxy retrieve'() {
        given:
        def (connection, mockConnection) = buildConnection()

        when:
        connection.retrieve('fieldList', 'sObjectType', new String[0])
        then:
//        1 * mockOperatinHandler.performOperation('retrieve', 'fieldList', 'sObjectType', _ as String[])
        1 * mockConnection.retrieve('fieldList', 'sObjectType', new String[0])
    }

    def buildConnection() {
        def operationHandlerFactory = new SalesforceOperationHandlerFactory()
        def mockConnection = Mock(IPartnerConnectionWrapper)
        def connection = new ManagedPartnerConnection(mockConnection, operationHandlerFactory)
        return [connection, mockConnection]
    }
}