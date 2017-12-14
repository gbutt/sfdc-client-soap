package edu.colorado.oit.crm.salesforce.client.partner;

import com.sforce.soap.partner.*;
import com.sforce.soap.partner.sobject.ISObject;
import com.sforce.ws.ConnectionException;

import java.util.Calendar;

/*
* This class allows us to intercept operations to the Salesforce SOAP API and perform additional logic around each request.
* This is done by implementing the SalesforceOperationHandler interface and passing an instance to this class.
* The SalesforceOperationHandler will intercept each api call and perform the operation along with any additional logic
* */
public class ManagedPartnerConnection implements IPartnerConnectionWrapper {

    private final SalesforceOperationHandlerFactory operationHandlerFactory;
    private final IPartnerConnectionWrapper delegate;

    public ManagedPartnerConnection(IPartnerConnectionWrapper delegate, SalesforceOperationHandlerFactory operationHandlerFactory) {
        this.operationHandlerFactory = operationHandlerFactory;
        this.delegate = delegate;
    }

    // region USER OPERATIONS
    @Override
    public ISetPasswordResult setPassword(String userId, String newPassword) throws ConnectionException {
        return buildOperationFor("setPassword", ISetPasswordResult.class, userId, newPassword).execute();
    }

    @Override
    public IResetPasswordResult resetPassword(String userId) throws ConnectionException {
        return buildOperationFor("resetPassword", IResetPasswordResult.class, userId).execute();
    }

    @Override
    public ILoginResult login(String username, String password) throws ConnectionException {
        return buildOperationFor("login", ILoginResult.class, username, password).execute();
    }

    @Override
    public void logout() throws ConnectionException {
        buildOperationFor("logout", Void.class).execute();
    }

    @Override
    public IGetUserInfoResult getUserInfo() throws ConnectionException {
        return buildOperationFor("getUserInfo", IGetUserInfoResult.class).execute();
    }

    @Override
    public IGetServerTimestampResult getServerTimestamp() throws ConnectionException {
        return buildOperationFor("getServerTimestamp", IGetServerTimestampResult.class).execute();
    }
    // endregion

    //region CRUD OPERATIONS
    @Override
    public IQueryResult query(String queryString) throws ConnectionException {
        return buildOperationFor("query", IQueryResult.class,queryString).execute();
    }

    @Override
    public IQueryResult queryMore(String queryLocator) throws ConnectionException {
        return buildOperationFor("queryMore", IQueryResult.class, queryLocator).execute();
    }

    @Override
    public IQueryResult queryAll(String queryString) throws ConnectionException {
        return buildOperationFor("queryAll", IQueryResult.class, queryString).execute();
    }

    @Override
    public ISaveResult[] create(ISObject[] sObjects) throws ConnectionException {
        return buildOperationFor("create", ISaveResult[].class, (Object)sObjects).execute();
    }

    @Override
    public ISaveResult[] update(ISObject[] sObjects) throws ConnectionException {
        return buildOperationFor("update", ISaveResult[].class, (Object)sObjects).execute();
    }

    @Override
    public IUpsertResult[] upsert(String externalIDFieldName, ISObject[] sObjects) throws ConnectionException {
        return buildOperationFor("upsert", IUpsertResult[].class, externalIDFieldName, sObjects).execute();
    }

    @Override
    public IDeleteResult[] delete(String[] ids) throws ConnectionException {
        return buildOperationFor("delete", IDeleteResult[].class, (Object)ids).execute();
    }

    @Override
    public IUndeleteResult[] undelete(String[] ids) throws ConnectionException {
        return buildOperationFor("undelete", IUndeleteResult[].class, (Object)ids).execute();
    }
    // endregion

    // region CRUDish OPERATIONS
    @Override
    public IEmptyRecycleBinResult[] emptyRecycleBin(String[] ids) throws ConnectionException {
        return buildOperationFor("emptyRecycleBin", IEmptyRecycleBinResult[].class, (Object)ids).execute();
    }

    @Override
    public ISearchResult search(String searchString) throws ConnectionException {
        return buildOperationFor("search", ISearchResult.class, searchString).execute();
    }

    @Override
    public IMergeResult[] merge(IMergeRequest[] mergeRequests) throws ConnectionException {
        return buildOperationFor("merge", IMergeResult[].class, (Object)mergeRequests).execute();
    }
    @Override
    public ILeadConvertResult[] convertLead(ILeadConvert[] leadConverts) throws ConnectionException {
        return buildOperationFor("convertLead", ILeadConvertResult[].class, (Object)leadConverts).execute();
    }

    @Override
    public IFindDuplicatesResult[] findDuplicates(ISObject[] sObjects) throws ConnectionException {
        return buildOperationFor("findDuplicates", IFindDuplicatesResult[].class, (Object)sObjects).execute();
    }
    // endregion

    // region EMAIL OPERATIONS
    @Override
    public IRenderEmailTemplateResult[] renderEmailTemplate(IRenderEmailTemplateRequest[] renderEmailTemplateRequests) throws ConnectionException {
        return buildOperationFor("renderEmailTemplate", IRenderEmailTemplateResult[].class, (Object)renderEmailTemplateRequests).execute();
    }

    @Override
    public ISendEmailResult[] sendEmailMessage(String[] ids) throws ConnectionException {
        return buildOperationFor("sendEmailMessage", ISendEmailResult[].class, (Object)ids).execute();
    }

    @Override
    public ISendEmailResult[] sendEmail(IEmail[] emails) throws ConnectionException {
        return buildOperationFor("sendEmail", ISendEmailResult[].class, (Object)emails).execute();
    }
    // endregion

    // region OTHER OPERATIONS
    @Override
    public IPerformQuickActionResult[] performQuickActions(IPerformQuickActionRequest[] performQuickActionRequests) throws ConnectionException {
        return buildOperationFor("performQuickActions", IPerformQuickActionResult[].class, (Object)performQuickActionRequests).execute();
    }

    @Override
    public IProcessResult[] process(IProcessRequest[] processRequests) throws ConnectionException {
        return buildOperationFor("process", IProcessResult[].class, (Object)processRequests).execute();
    }

    @Override
    public IQuickActionTemplateResult[] retrieveQuickActionTemplates(String[] quickActionNames, String contextId) throws ConnectionException {
        return buildOperationFor("retrieveQuickActionTemplates", IQuickActionTemplateResult[].class, quickActionNames, contextId).execute();
    }

    @Override
    public IExecuteListViewResult executeListView(IExecuteListViewRequest executeListViewRequest) throws ConnectionException {
        return buildOperationFor("executeListView", IExecuteListViewResult.class, (IExecuteListViewRequest)executeListViewRequest).execute();
    }

    @Override
    public IInvalidateSessionsResult[] invalidateSessions(String[] sessionIds) throws ConnectionException {
        return buildOperationFor("invalidateSessions", IInvalidateSessionsResult[].class, (Object)sessionIds).execute();
    }
    // endregion

    // region DESCRIBE OPERATIONS
    @Override
    public IDescribeTab[] describeAllTabs() throws ConnectionException {
        return buildOperationFor("describeAllTabs", IDescribeTab[].class).execute();
    }

    @Override
    public IDescribeDataCategoryGroupStructureResult[] describeDataCategoryGroupStructures(IDataCategoryGroupSobjectTypePair[] dataCategoryGroupSobjectTypePairs, boolean topCategoriesOnly) throws ConnectionException {
        return buildOperationFor("describeDataCategoryGroupStructures", IDescribeDataCategoryGroupStructureResult[].class,dataCategoryGroupSobjectTypePairs, topCategoriesOnly).execute();
    }

    @Override
    public IDescribeDataCategoryGroupResult[] describeDataCategoryGroups(String[] sObjectType) throws ConnectionException {
        return buildOperationFor("describeDataCategoryGroups", IDescribeDataCategoryGroupResult[].class, (Object)sObjectType).execute();
    }

    @Override
    public IDescribeGlobalResult describeGlobal() throws ConnectionException {
        return buildOperationFor("describeGlobal", IDescribeGlobalResult.class).execute();
    }

    @Override
    public IDescribeGlobalTheme describeGlobalTheme() throws ConnectionException {
        return buildOperationFor("describeGlobalTheme", IDescribeGlobalTheme.class).execute();
    }

    @Override
    public IDescribeApprovalLayoutResult describeApprovalLayout(String sObjectType, String[] approvalProcessNames) throws ConnectionException {
        return buildOperationFor("describeApprovalLayout", IDescribeApprovalLayoutResult.class, sObjectType, approvalProcessNames).execute();
    }

    @Override
    public IDescribeCompactLayout[] describePrimaryCompactLayouts(String[] sObjectTypes) throws ConnectionException {
        return buildOperationFor("describePrimaryCompactLayouts", IDescribeCompactLayout[].class, (Object)sObjectTypes).execute();
    }

    @Override
    public IDescribeSearchableEntityResult[] describeSearchableEntities(boolean includeOnlyEntitiesWithTabs) throws ConnectionException {
        return buildOperationFor("describeSearchableEntities", IDescribeSearchableEntityResult[].class, includeOnlyEntitiesWithTabs).execute();
    }

    @Override
    public IDescribeLayoutResult describeLayout(String sObjectType, String layoutName, String[] recordTypeIds) throws ConnectionException {
        return buildOperationFor("describeLayout", IDescribeLayoutResult.class, sObjectType, layoutName, recordTypeIds).execute();
    }

    @Override
    public IDescribeAppMenuResult describeAppMenu(AppMenuType menuType, String networkId) throws ConnectionException {
        return buildOperationFor("describeAppMenu", IDescribeAppMenuResult.class, menuType, networkId).execute();
    }

    @Override
    public IDescribeSoqlListViewResult describeSObjectListViews(String sObjectType, boolean recentsOnly, ListViewIsSoqlCompatible viewIsSoqlCompatible, int limit, int offset) throws ConnectionException {
        return buildOperationFor("describeSObjectListViews", IDescribeSoqlListViewResult.class, sObjectType, recentsOnly, viewIsSoqlCompatible, limit, offset).execute();
    }

    @Override
    public IDescribeCompactLayoutsResult describeCompactLayouts(String sObjectType, String[] recordTypeIds) throws ConnectionException {
        return buildOperationFor("describeCompactLayouts", IDescribeCompactLayoutsResult.class, sObjectType, recordTypeIds).execute();
    }

    @Override
    public IDescribeSoqlListViewResult describeSoqlListViews(IDescribeSoqlListViewsRequest describeSoqlListViewsRequest) throws ConnectionException {
        return buildOperationFor("describeSoqlListViews", IDescribeSoqlListViewResult.class, describeSoqlListViewsRequest).execute();
    }

    @Override
    public IDescribePathAssistantsResult describePathAssistants(String sObjectType, String picklistValue, String[] recordTypeIds) throws ConnectionException {
        return buildOperationFor("describePathAssistants", IDescribePathAssistantsResult.class, sObjectType, picklistValue, recordTypeIds).execute();
    }

    @Override
    public IDescribeAvailableQuickActionResult[] describeAvailableQuickActions(String contextType) throws ConnectionException {
        return buildOperationFor("describeAvailableQuickActions", IDescribeAvailableQuickActionResult[].class, contextType).execute();
    }

    @Override
    public IDescribeQuickActionResult[] describeQuickActions(String[] quickActions) throws ConnectionException {
        return buildOperationFor("describeQuickActions", IDescribeQuickActionResult[].class, (Object)quickActions).execute();
    }

    @Override
    public IDescribeSObjectResult[] describeSObjects(String[] sObjectType) throws ConnectionException {
        return buildOperationFor("describeSObjects", IDescribeSObjectResult[].class, (Object)sObjectType).execute();
    }

    @Override
    public IKnowledgeSettings describeKnowledgeSettings() throws ConnectionException {
        return buildOperationFor("describeKnowledgeSettings", IKnowledgeSettings.class).execute();
    }

    @Override
    public IDescribeThemeResult describeTheme(String[] sobjectType) throws ConnectionException {
        return buildOperationFor("describeTheme", IDescribeThemeResult.class, (Object)sobjectType).execute();
    }

    @Override
    public IDescribeNounResult[] describeNouns(String[] nouns, boolean onlyRenamed, boolean includeFields) throws ConnectionException {
        return buildOperationFor("describeNouns", IDescribeNounResult[].class, nouns, onlyRenamed, includeFields).execute();
    }

    @Override
    public IDescribeVisualForceResult describeVisualForce(boolean includeAllDetails, String namespacePrefix) throws ConnectionException {
        return buildOperationFor("describeVisualForce", IDescribeVisualForceResult.class, includeAllDetails, namespacePrefix).execute();
    }

    @Override
    public IDescribeSObjectResult describeSObject(String sObjectType) throws ConnectionException {
        return buildOperationFor("describeSObject", IDescribeSObjectResult.class, sObjectType).execute();
    }

    @Override
    public IDescribeSoftphoneLayoutResult describeSoftphoneLayout() throws ConnectionException {
        return buildOperationFor("describeSoftphoneLayout", IDescribeSoftphoneLayoutResult.class).execute();
    }

    @Override
    public IDescribeSearchLayoutResult[] describeSearchLayouts(String[] sObjectType) throws ConnectionException {
        return buildOperationFor("describeSearchLayouts", IDescribeSearchLayoutResult[].class, (Object)sObjectType).execute();
    }

    @Override
    public IDescribeSearchScopeOrderResult[] describeSearchScopeOrder() throws ConnectionException {
        return buildOperationFor("describeSearchScopeOrder", IDescribeSearchScopeOrderResult[].class).execute();
    }

    @Override
    public IDescribeTabSetResult[] describeTabs() throws ConnectionException {
        return buildOperationFor("describeTabs", IDescribeTabSetResult[].class).execute();
    }
    // endregion

    // region REPLICATION OPERATIONS
    @Override
    public IGetDeletedResult getDeleted(String sObjectType, Calendar startDate, Calendar endDate) throws ConnectionException {
        return buildOperationFor("getDeleted", IGetDeletedResult.class, sObjectType, startDate, endDate).execute();
    }

    @Override
    public IGetUpdatedResult getUpdated(String sObjectType, Calendar startDate, Calendar endDate) throws ConnectionException {
        return buildOperationFor("getUpdated", IGetUpdatedResult.class, sObjectType, startDate, endDate).execute();
    }

    @Override
    public ISObject[] retrieve(String fieldList, String sObjectType, String[] ids) throws ConnectionException {
        return buildOperationFor("retrieve", ISObject[].class, fieldList, sObjectType, ids).execute();
    }
    // endregion

    private <T> SalesforceOperationHandler<T> buildOperationFor(String operationName, Class<T> returnType, Object... args) {
        return operationHandlerFactory.buildOperationFor(delegate, operationName, returnType, args);
    }
}
