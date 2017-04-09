package com.intertek.phoenix.search;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.CurrencyRate;
import com.intertek.entity.CustAddress;
import com.intertek.entity.JobOrder;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.FilterInfo;
import com.intertek.phoenix.dao.FilterOp;
import com.intertek.phoenix.dao.GenericDao;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.GLCode;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.search.JobSearchInfo.Division;
import com.intertek.phoenix.util.ArrayMap;
import com.intertek.phoenix.util.ArrayMapGrid;
import com.intertek.phoenix.util.CommonUtil;

/**
 * 
 * @author lily.sun
 */

public class SearchServiceImpl implements SearchService {

    private List<GLCode> glCodeList;

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.search.SearchService#searchJobOrder(java.util.Map)
     */
    public <T> List<T> search(Class<T> entityType, Map<String, String> criteria) throws DaoException {
        List<T> entities = null;
        if (criteria != null) {
            List<SearchCriteria> criteria2 = changeToSearchCritiria(criteria);
            entities = advancedSearch(entityType, criteria2);
        }
        return entities;
    }

    private List<SearchCriteria> changeToSearchCritiria(Map<String, String> criteria) {
        List<SearchCriteria> criteria2 = new ArrayList<SearchCriteria>();
        for (String key : criteria.keySet()) {
            if (criteria.get(key).contains("%")) {
                criteria2.add(new SearchCriteria(key, criteria.get(key).replaceAll("%", ""), "contains"));
            }
            else {
                criteria2.add(new SearchCriteria(key, criteria.get(key), "="));
            }
        }
        return criteria2;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.search.SearchService#advancedSearch(java.util.List)
     */
    public <T> List<T> advancedSearch(Class<T> entityType, List<SearchCriteria> criteria) throws DaoException {
        return advancedSearch(entityType, criteria, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.search.SearchService#search(java.lang.Class,
     *      java.util.Map, com.intertek.pagination.Pagination,
     *      java.lang.String[])
     */
    public <T> List<T> search(Class<T> entityType, Map<String, String> criteria, Pagination pagination, SortInfo sortBy) throws DaoException {
        List<T> entities = null;
        if (criteria != null) {
            List<SearchCriteria> criteria2 = changeToSearchCritiria(criteria);
            entities = advancedSearch(entityType, criteria2, pagination, sortBy);
        }
        return entities;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.search.SearchService#advancedSearch(java.lang.Class,
     *      java.util.List, com.intertek.pagination.Pagination,
     *      java.lang.String[])
     */
    public <T> List<T> advancedSearch(Class<T> entityType, List<SearchCriteria> criteria, Pagination pagination, SortInfo sortBy) throws DaoException {
        List<T> entities = null;
        if (criteria != null) {
            QueryInfo queryInfo = buildQuery(entityType, criteria);
            queryInfo.setSortBy(sortBy);
            queryInfo.setPagination(pagination);
            //System.out.println(queryInfo.toString());
            Dao<T> dao = DaoManager.getDao(entityType);
            entities = dao.search(queryInfo);
        }
        return entities;
    }

    private QueryInfo buildQuery(Class<?> entityType, List<SearchCriteria> criteria) throws DaoException {
        int size = criteria.size();
        String[] names = new String[size];
        String[] values = new String[size];
        FilterOp[] ops = new FilterOp[size];
        int pos = 0;
        for (SearchCriteria cri : criteria) {
            names[pos] = cri.getName();
            values[pos] = cri.getValue().replaceAll("%", "");
            // if ("contains".equals(cri.getOp().trim().toLowerCase())){
            // values[pos] = "%"+ values[pos] + "%";
            // }
            ops[pos] = cri.getFilterOp();
            pos++;
        }
        QueryInfo queryInfo = QueryInfo.newQueryInfo(entityType);
        queryInfo = queryInfo.buildQuery(names, values, ops);
        return queryInfo;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.search.SearchService#searchJobLog(java.util.Map,
     *      com.intertek.pagination.Pagination, java.lang.String[])
     */
    public ArrayMapGrid searchJobLog(Map<String, String> criteria, Pagination pagination, SortInfo sortBy) throws DaoException {
        String crit = "";
        for (String key : criteria.keySet()) {
            crit += (crit.length() > 0 ? " AND " : "") + key + " = " + criteria.get(key);
        }

        return buildDataGrid(crit, pagination, sortBy);
    }

    public ArrayMapGrid searchJobLog(List<SearchCriteria> criteria, Pagination pagination, SortInfo sortBy) throws DaoException {
        String crit = "";
        for (SearchCriteria criterion : criteria) {
            crit += (crit.length() > 0 ? " AND " : "") + criterion.toString();
        }
        return buildDataGrid(crit, pagination, sortBy);
    }

    private ArrayMapGrid buildDataGrid(String crit, Pagination pagination, SortInfo sort) throws DaoException {
        String selectFieldsInString = "";
        Map<String, String> searchFields = getSearchFields();
        Set<String> keys = searchFields.keySet();
        String[] columnAlias = new String[keys.size()];
        int i = 0;
        for (String key : keys) {
            selectFieldsInString += (selectFieldsInString.length() > 0 ? "," : "") + key + " as " + searchFields.get(key);
            columnAlias[i++] = searchFields.get(key);
        }
        String sql = "select  " + selectFieldsInString + "\n FROM CEJobOrderOrderLine joli " + "\n LEFT JOIN CEJobOrder jo " + "\n LEFT JOIN Quote q "
                     + "\n LEFT JOIN CEInvoice inv " + (crit.length() > 0 ? "\n WHERE " : "") + crit;

        GenericDao<CEJobOrder> gdao = ((GenericDao<CEJobOrder>) DaoManager.getDao(CEJobOrder.class));
        List<? extends Object[]> result = gdao.query("joblog", (Object) sql, pagination, sort);

        ArrayMap<String, Object> am = new ArrayMap<String, Object>(columnAlias);
        List<Object[]> list = new ArrayList<Object[]>();
        for (Object[] objects : result) {
            list.add(objects);
        }
        return new ArrayMapGrid(am, list);
    }

    protected Map<String, String> getSearchFields() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("jo.projectNumber", "projectNumber");
        map.put("joli.lineNumber", "taskNumber");
        map.put("jo.operationalStatus", "projectOperationalStatus");
        map.put("jo.orderStatus", "orderStatus");
        map.put("jo.customerName", "customerName");
        map.put("jo.modelNumber", "modelNumber");
        map.put("joli.taskSampleDescription", "taskSampleDescription");
        map.put("joli.taskName", "taskName");
        map.put("joli.description", "taskDescription");
        map.put("joli.taskComments", "taskComments");
        map.put("joli.taskOperationalStatus", "taskOperationalStatus");
        map.put("joli.branchName", "taskOwningOrg");
        map.put("currentMonthBilled", "currentMonthBilled");
        map.put("pastDue", "pastDue");
        map.put("currentMonth", "currentMonth");
        map.put("currentMonthPlus1", "currentMonthPlus1");
        map.put("currentMonthPlus2", "currentMonthPlus2");
        map.put("futureMonths", "futureMonths");
        map.put("rowTotalOfRev", "rowTotalOfRev");
        map.put("jo.businessStreamCode", "businessStreamCode");
        map.put("joli.serviceType", "serviceType");
        map.put("joli.taskReadyDate", "taskReadyDate");
        map.put("jo.promiseCompletionDate", "promisedCompletionDate");
        map.put("jo.projectManagerName", "projectManagerName");
        map.put("jo.salesPersonName", "salesPersonName");
        map.put("joli.taskManager", "taskManager");
        map.put("jo.branchName", "projectOwningOrg");
        map.put("jo.actualReadyDate", "actualReadyDate");
        map.put("joli.customerReadyDate", "customerReadyDate");
        map.put("jo.serviceLocation", "serviceLocation");
        map.put("jo.billStatus", "billStatus");
        map.put("joli.serviceOfferingParentName", "serviceOfferingParentName");
        map.put("jo.description", "jobDescription");
        map.put("jo.contractNumber", "contractNumber");
        map.put("jo.customerNumber", "customerNumber");
        map.put("jo.q.quoteNumber", "quoteNumber");
        map.put("jo.orderNumber", "orderNumber");
        map.put("jo.q.ecsOrderNumber", "ecsOrder#");
        map.put("orderAmt", "orderAmt");
        map.put("q.quoteDate", "quoteDate");
        map.put("joli.taskStartDate", "taskStartDate");
        map.put("joli.taskCompletionDate", "taskCompletionDate");
        map.put("orderDate", "orderDate");
        map.put("deliverableShipDate", "deliverableShipDate");
        map.put("joli.actualStart", "actualStart");
        map.put("jo.poNumber", "poNumber");
        map.put("joli.costBudgetHours", "costBudgetHours");
        map.put("joli.creditOverride", "creditOverride");
        // billing tab
        map.put("isfDate", "isfDate");
        map.put("inv.invoiceDate", "dateInvoiced");
        map.put("inv.totalAmount", "amountInvoiced");
        map.put("inv.invoicedNo", "invoicedNo");
        map.put("inv.invoicedCredited", "invoicedCredited");
        map.put("reOpenDate", "reOpenDate");
        // process log tab
        map.put("reportReviewerDate", "reportReviewerDate");
        map.put("reviewerName", "reviewerName");
        map.put("reportSentDate", "reportSentDate");

        return map;
    }

    public GLCode searchGLCode(String serviceDescription) {
        if (glCodeList == null) {
            loadGLCodes();
        }

        GLCode retVal = null;
        for (GLCode glCode : glCodeList) {
            if ((glCode.getServiceDescription().trim().toUpperCase()).contains(serviceDescription.trim().toUpperCase())) {
                retVal = glCode;
                break;
            }
        }
        return retVal;
    }

    // TODO: replace the hard coded value with real database values
    private void loadGLCodes() {
        this.glCodeList = new ArrayList<GLCode>();
        GLCode glCode = new GLCode("111", "Travel Charges", "Travel Charges");
        glCodeList.add(glCode);
        glCode = new GLCode("222", "Freight Charges", "Feight Charges");
        glCodeList.add(glCode);
        glCode = new GLCode("333", "Materials/Samples", "materials/samples related expenses");
        glCodeList.add(glCode);
        glCode = new GLCode("444", "Sub-contracting", "Sub-contracting expenses");
        glCodeList.add(glCode);
        glCode = new GLCode("555", "Deposit Invocie", "Deposit Invoice");
        glCodeList.add(glCode);

    }

    @SuppressWarnings("unchecked")
    public List<ContractCustContact> searchContractCustomerContact(String value, Pagination pagination, SortInfo sort) throws DaoException {
        String upperCaseValue = "%" + value.toUpperCase() + "%";

        StringBuffer buf = new StringBuffer("");
        String selectPart = "select c from ContractCustContact c left join c.contractCust " + " left join c.contractCust.contract "
                            + " left join c.contractCust.customer inner join c.contact ";
        String wherePart = "where (" + "( " + "  upper(c.contractCustContactId.contractCode) like ? " + "  or upper(c.contact.firstName) like ? "
                           + "  or upper(c.contact.lastName) like ? " + "  or concat(upper(c.contact.firstName),' ',upper(c.contact.lastName)) like ? "
                           + "  or concat(upper(c.contact.firstName),upper(c.contact.lastName)) like ? "
                           + "  or upper(c.contractCust.customer.custCode) like ? " + "  or upper(c.contractCust.customer.name) like ? " + ")"
                           + "and ( c.schedulerContactFlag= 'Y'" + "  and c.status='A'" + ")" + " ) ";
        buf.append(selectPart);
        buf.append(wherePart);
        if (sort != null) {
            buf.append(sort.toString().trim().length() > 0 ? " order by " + sort.toString() : "");
        }
        List<ContractCustContact> entities = getDao(ContractCustContact.class).searchBySql(
                                                                                           buf.toString(),
                                                                                           new Object[] { upperCaseValue, upperCaseValue, upperCaseValue,
                                                                                                         upperCaseValue, upperCaseValue, upperCaseValue,
                                                                                                         upperCaseValue }, pagination);

        return entities;
    }

    @SuppressWarnings("unchecked")
    public List<ContactCust> searchContactsByCustCode(ContactCustSearchInfo searchInfo, Pagination pagination, SortInfo sortInfo) throws DaoException {
        String searchQuery = " select c from ContactCust c " + "left join fetch c.custAddrSeq  " + "left join fetch c.custAddrSeq.custAddresses ads"
                             + " left join fetch c.contact left join fetch c.customer cust ";
        List params = new ArrayList();
        StringBuffer extraCriteria = new StringBuffer();
        if (!CommonUtil.isNullOrEmpty(searchInfo.getCustCode())) {
            String val = searchInfo.getCustCode();
            extraCriteria.append(" where c.custAddrSeq.custAddrSeqId.custCode = ? " + " and ads.effDate = "
                                 + "(select max(ads2.effDate) from ContactCust c2 left join c2.custAddrSeq  " + "left join c2.custAddrSeq.custAddresses ads2 "
                                 + "where ads2.effStatus='A' and ads2.effDate <= sysdate and ads2.custCode = ?)");
            params.add(val);
            params.add(val);
        }
        if (!CommonUtil.isNullOrEmpty(searchInfo.getCustomerName())) {

            extraCriteria.append((extraCriteria.length() > 0 ? " and " : " where ") + " upper(c.customer.name) like ? ");
            params.add("%" + searchInfo.getCustomerName().toUpperCase() + "%");
        }
        if (searchInfo.getContactId() != 0) {
            extraCriteria.append((extraCriteria.length() > 0 ? " and " : " where ") + " c.contact.id  = ? ");
            params.add(searchInfo.getContactId());
        }
        if (!CommonUtil.isNullOrEmpty(searchInfo.getContactName())) {
            String upperCaseName = "%" + searchInfo.getContactName() + "%";
            extraCriteria.append((extraCriteria.length() > 0 ? " and " : " where ") + "(upper(c.contact.firstName) like ? "
                                 + "  or upper(c.contact.lastName) like  ? " + "  or concat(upper(c.contact.firstName),' ',upper(c.contact.lastName)) like  ? "
                                 + "  or concat(upper(c.contact.firstName),upper(c.contact.lastName)) like  ? " + ")");
            for (int j = 0; j < 4; j++) {
                params.add(upperCaseName);
            }
        }

        String sortString = "";
        if (sortInfo != null) {
            sortString = sortInfo.toString().trim().length() > 0 ? " order by " + sortInfo.toString() : "";
        }

        List<ContactCust> contactCusts = getDao(ContactCust.class).searchBySql(
                                                                               new StringBuffer().append(searchQuery).append(extraCriteria).append(sortString)
                                                                                       .toString(), params.toArray(), pagination);
        return contactCusts;
    }

    @SuppressWarnings("unchecked")
    private Dao getDao(Class clazz) {
        return DaoManager.getDao(clazz);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CurrencyRate> searchCurrencyRate(String fromCurrency, String toCurrency, Date date) throws DaoException {
        Dao<CurrencyRate> dao = DaoManager.getGenericDao(CurrencyRate.class);

        String dateStr = new SimpleDateFormat("dd-MMM-yy").format(date);

        // the following sql is copied from
        // com.intertek.service.CalculatorServiceImpl
        String sql = "select cr from CurrencyRate cr where cr.currencyRateId.rateIndex = 'MODEL' " + " and cr.currencyRateId.term = 0 "
                     + " and cr.currencyRateId.fromCurrency = '" + fromCurrency + "'" + " and cr.currencyRateId.toCurrency = '" + toCurrency + "'"
                     + " and cr.currencyRateId.type = 'CRRNT' " + " and cr.currencyRateId.effectiveDate = ("
                     + "   select max(cr1.currencyRateId.effectiveDate) from CurrencyRate cr1 "
                     + "   where cr.currencyRateId.rateIndex = cr1.currencyRateId.rateIndex" + "     and cr.currencyRateId.term = cr1.currencyRateId.term"
                     + "     and cr.currencyRateId.fromCurrency = cr1.currencyRateId.fromCurrency"
                     + "     and cr.currencyRateId.toCurrency = cr1.currencyRateId.toCurrency" + "     and cr.currencyRateId.type = cr1.currencyRateId.type"
                     + "     and cr1.currencyRateId.effectiveDate <= '" + dateStr + "')";

        return dao.searchBySql(sql, null);
    }

    /*
     * @SuppressWarnings("unchecked") public List<User>
     * searchUsersByRole(Pagination pagination, SortInfo sortInfo, String...
     * value) throws DaoException {
     * 
     * String wherePartRole = ""; String wherePart = ""; String valueObj = "";
     * String cond1 = "where"; String selectPart = "select u from User u ";
     * String selectCountPart = "select count(*) from User u ";
     * 
     * int i = 0; for (String v : value) { if (i == 0 && v != null) {
     * wherePartRole = " left join u.roles r where r.name = ?"; valueObj += v;
     * cond1 = "and"; }
     * 
     * if (i == 1 && v != null && !"".equals(v)) { wherePart = cond1 + "
     * upper(u.loginName) like '%" + v.toUpperCase() + "%'"; cond1 = "and"; }
     * 
     * if (i == 2 && v != null && !"".equals(v)) { wherePart = wherePart + cond1 + "
     * upper(u.firstName) like '%" + v.toUpperCase() + "%'"; cond1 = "and"; }
     * 
     * if (i == 3 && v != null && !"".equals(v)) { wherePart = wherePart + cond1 + "
     * upper(u.lastName) like '%" + v.toUpperCase() + "%'"; } i++; }
     * 
     * List<User> users = new ArrayList<User>();
     * 
     * StringBuffer userBuf = new StringBuffer("");
     * userBuf.append(selectPart.toString());
     * userBuf.append(wherePartRole.toString());
     * userBuf.append(wherePart.toString());
     * 
     * String countSql = new
     * StringBuffer().append(selectCountPart).append(wherePartRole).append(wherePart).toString();
     * 
     * List countResult = new ArrayList(); Long count; if (value[0] != null) {
     * users = getDao(User.class).searchBySql(userBuf.toString(), new Object[] {
     * valueObj }, pagination); countResult =
     * getDao(User.class).searchBySql(countSql, new Object[] { valueObj });
     * count = (Long)countResult.get(0); } else { users =
     * getDao(User.class).searchBySql(userBuf.toString(), null, pagination);
     * countResult = getDao(User.class).searchBySql(countSql, null); count =
     * (Long)countResult.get(0); } pagination.setTotalRecord(count.intValue());
     * 
     * return users; }
     */

    @SuppressWarnings("unchecked")
    public CustAddress searchAddress(String custCode, long schedulerContactId) throws DaoException {
        List contactCustList = getDao(ContactCust.class).searchBySql(
                                                                     " select c from ContactCust c " + "left join fetch c.custAddrSeq  "
                                                                             + "left join fetch c.custAddrSeq.custAddresses ads"
                                                                             + " left join fetch c.contact left join fetch c.customer cust "
                                                                             + " where c.custAddrSeq.custAddrSeqId.custCode = ? " + " and c.contact.id = ? "
                                                                             + " and ads.effDate = "
                                                                             + "(select max(ads2.effDate) from ContactCust c2 left join c2.custAddrSeq  "
                                                                             + "left join c2.custAddrSeq.custAddresses ads2 "
                                                                             + "where ads2.effStatus='A' and ads2.effDate <= sysdate "
                                                                             + "and ads2.custCode = ?)",
                                                                     new Object[] { custCode, schedulerContactId, custCode });
        CustAddress address = new CustAddress();
        if (contactCustList.size() > 0) {
            ContactCust cc = (ContactCust) contactCustList.get(0);
            Set<CustAddress> custAddresses = cc.getCustAddrSeq().getCustAddresses();
            for (CustAddress custAddress : custAddresses) {
                address = custAddress;
            }
        }
        return address;
    }

    @SuppressWarnings("unchecked")
    public List<Bank> getBankCode(BankSearchInfo searchInfo, Pagination pagination, SortInfo sortInfo) throws DaoException {
        // TODO sort not taken care of yet
        String buName = searchInfo.getBuName();
        String currency = searchInfo.getCurrency();
        String bankCode = searchInfo.getBankCode();
        String bankDesc = searchInfo.getBankDesc();
        String bDSearch = '%' + "Do Not Use" + '%';
        StringBuffer sql = new StringBuffer();
        String firstPart = "select distinct b from Bank b,BankAccountCurr ba,BankAccount bac" + " where b.bankCode=ba.bankAccountCurrId.bankCode "
                           + " and ba.bankAccountCurrId.bankAcctCode=bac.bankAccountId.bankAcctCode "
                           + " and bac.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode " + " and bac.bankAccountId.businessUnitName = '" + buName
                           + "' and ba.bankAccountCurrId.currencyCode='" + currency.trim() + "' and bac.bi='Y' and upper(b.bankDesc) not like '"
                           + bDSearch.toUpperCase() + "'";
        sql.append(firstPart);
        if (bankCode != null && bankCode.length() > 0) {
            sql.append(" and b.bankCode = '" + bankCode + "'");
        }
        if (bankCode != null && bankDesc.length() > 0) {
            sql.append(" and upper(b.bankDesc) like '" + bankDesc.toUpperCase() + "'");
        }
        List<Bank> bankCodes = getDao(Bank.class).searchBySql(sql.toString(), null, pagination);
        return bankCodes;
    }

    @SuppressWarnings("unchecked")
    public List<BankAccount> getBankAccount(BankSearchInfo searchInfo, Pagination pagination, SortInfo sortInfo) throws DaoException {
        // TODO sort not taken care of yet
        String buName = searchInfo.getBuName();
        String currency = searchInfo.getCurrency();
        String bankCode = searchInfo.getBankCode();
        String bankAcctCode = searchInfo.getBankAcctCode();
        String bankDesc = searchInfo.getBankDesc();

        String bDSearch = '%' + "Do Not Use" + '%';

        StringBuffer sql = new StringBuffer();

        String firstPart = "select distinct b from BankAccount b, Bank bk, BankAccountCurr ba "
                           + "where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and b.bankAccountId.businessUnitName = '"
                           + buName + "' and ba.bankAccountCurrId.currencyCode='" + currency.trim() + "' and ba.bankAccountCurrId.bankCode ='" + bankCode
                           + "' and b.bi='Y' and upper(b.bankAcctDesc) not like '" + bDSearch.toUpperCase() + "'";
        sql.append(firstPart);
        if (bankAcctCode != null && bankAcctCode.length() > 0) {
            sql.append(" and b.bankAccountId.bankAcctCode = '" + bankAcctCode + "'");
        }
        if (bankDesc != null && bankDesc.length() > 0) {
            sql.append(" and upper(b.bankAcctDesc) like '" + bankDesc.toUpperCase() + "'");
        }
        List<BankAccount> bankAccounts = getDao(BankAccount.class).searchBySql(sql.toString(), null, pagination);
        return bankAccounts;
    }

    @Override
    public <T> List<T> search(Class<T> entityType, QueryInfo queryInfo) throws DaoException {
        return DaoManager.getDao(entityType).search(queryInfo);
    }

    // TODO the query is hard coded and not including everything yet. Need to
    // test it further.
    // also need to add pagination and sortBy info
    public ArrayMapGrid searchJob(JobSearchInfo jobSearchInfo, Pagination pagination, SortInfo sortInfo) throws PhoenixException {

        String ceJobQuery = "select " + configureSelectQuery(jobSearchInfo, Division.CE) + " from CEJobOrder jobOrder"
                            + " left outer join jobOrder.jobContracts jobContract " + " left outer join jobContract.ceInvoices invoice";
        String ocaJobQuery = "select " + configureSelectQuery(jobSearchInfo, Division.OCA) + " from JobOrder jobOrder"
                             + " left outer join jobOrder.jobContracts jobContract " + " left outer join jobContract.jobContractInvoices invoice ";

        List<FilterInfo> ceCriteria = jobSearchInfo.getCeCriteria();
        StringBuffer wherePart = new StringBuffer();
        List<Object> ceParamList = new ArrayList<Object>();
        for (FilterInfo filterInfo : ceCriteria) {
            wherePart.append((wherePart.length() == 0 ? " WHERE " : " and ") + filterInfo.getName() + " " + filterInfo.getOp().symbol() + " ? ");
            ceParamList.add(filterInfo.getValue());
        }
        if (wherePart.length() > 0) {
            ceJobQuery += wherePart.toString();
        }

        List<FilterInfo> ocaCriteria = jobSearchInfo.getOcaCriteria();
        StringBuffer ocaWherePart = new StringBuffer();
        List<Object> ocaParamList = new ArrayList<Object>();
        for (FilterInfo filterInfo : ocaCriteria) {
            ocaWherePart.append((ocaWherePart.length() == 0 ? " WHERE " : " and ") + filterInfo.getName() + " " + filterInfo.getOp().symbol() + " ? ");
            ocaParamList.add(filterInfo.getValue());
        }
        if (ocaWherePart.length() > 0) {
            ocaJobQuery += ocaWherePart.toString();
        }

        String ceSortBy = "";
        if (sortInfo != null) {
            ceSortBy += sortInfo.toString();
        }
        if (ceSortBy.length() > 0) {
            ceJobQuery += " ORDER BY " + ceSortBy;
            ocaJobQuery += " ORDER BY " + ceSortBy;
        }

        List ceResult = DaoManager.getDao(CEJobOrder.class).searchBySql(ceJobQuery, ceParamList.toArray(), pagination);
        List ocaResult = DaoManager.getDao(CEJobOrder.class).searchBySql(ocaJobQuery, ceParamList.toArray(), pagination);

        ArrayMap<String, Object> am = new ArrayMap<String, Object>(JobSearchInfo.headers);
        List<Object[]> list = new ArrayList<Object[]>();
        for (int i = 0; i < ceResult.size(); i++) {
            Object[] object = (Object[]) ceResult.get(i);
            list.add(object);
        }
        for (int i = 0; i < ocaResult.size(); i++) {
            Object[] object = (Object[]) ocaResult.get(i);
            list.add(object);
        }
        return new ArrayMapGrid(am, list);
    }

    private String configureSelectQuery(JobSearchInfo jobSearchInfo, Division jobType) {
        String selectPart = JobSearchInfo.jobIdField[0] + "," + JobSearchInfo.buNameField[0] + ","
                            + JobSearchInfo.operatingUnitField[0]
                            + ","
                            + JobSearchInfo.jobTypeField[0]
                            + ","
                            + JobSearchInfo.svcLocationNameField[0]
                            + ","
                            // need product info
                            + JobSearchInfo.companyIdField[0] + "," + JobSearchInfo.companyNameField[0]
                            + ","
                            + JobSearchInfo.contactFullName[0]
                            + ","
                            + JobSearchInfo.custRefNumField[0]
                            + ","
                            // need ICBRef#
                            + (Division.CE == jobType ? JobSearchInfo.promiseCompletionDateField[0] : JobSearchInfo.jobFinishedDateField[0])
                            + " as jobFinishedDate" + "," + (Division.CE == jobType ? JobSearchInfo.billingStatusField[0] : JobSearchInfo.ocaJobStatusField[0])
                            + "," + (Division.CE == jobType ? JobSearchInfo.invoiceNameField[0] : JobSearchInfo.ocaInvoiceNameField[0]) + ","
                            + JobSearchInfo.contractDescField[0] + "," + JobSearchInfo.contractStatusField[0] + "," + JobSearchInfo.createdByField[0] + ","
                            + JobSearchInfo.modifiedByField[0] + ","
                            + (Division.CE == jobType ? JobSearchInfo.modifiedDateField[0] : JobSearchInfo.ocaRevisionDttmField[0]);
        return selectPart;
    }

    @Override
    public <T> T uniqueSearch(Class<T> entityType, Map<String, String> criteria) throws DaoException {
        List<T> list = search(entityType, criteria);
        if (list == null || list.isEmpty()) {
            return null;
        }

        if (list.size() > 1) {
            throw new DaoException("UniqueSearch return more than one records");
        }
        else {
            return list.get(0);
        }
    }

    public <T> T quickJobSearch(SearchKey key, String searchValue) throws PhoenixException {
        T retObject = null;
        String ceSearchFrom = " from CEJobOrder jobOrder ";
        String ocaSearchFrom = " from JobOrder jobOrder ";
        String ceSql = "";
        String ocaSql = "";
        switch (key) {
            case JobId:
                ceSql = ceSearchFrom + " where upper(jobOrder.jobNumber) = ? ";
                ocaSql = ocaSearchFrom + " where upper(jobOrder.jobNumber) = ? ";
                break;
            case Invoice:
                ceSql = " select invoice.jobContract.jobOrder from CEInvoice invoice where upper(invoice.invoiceNumber)= ? ";
                ocaSql = " select invoice.jobContract.jobOrder  from JobContractInvoice invoice where upper(invoice.invoice) = ? ";
                break;
            case Quote:
                ceSql = ceSearchFrom + " where upper(jobOrder.quote.quoteNumber) = ? ";
                break;
            case ECSOrder:
                ceSql = ceSearchFrom + " where upper(jobOrder.quote.orderNumber) = ? ";
                break;
        }
        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(searchValue.toUpperCase());

        CEJobOrder ceJobOrder = null;
        JobOrder ocaJobOrder = null;
        try {
            if (ceSql.length() > 0) {
                List<CEJobOrder> result = DaoManager.getDao(CEJobOrder.class).searchBySql(ceSql, paramList.toArray(), null);
                if (result != null && result.size() > 0) {
                    ceJobOrder = result.get(0);
                }
            }
            if (ocaSql.length() > 0) {
                List<JobOrder> result = DaoManager.getDao(JobOrder.class).searchBySql(ocaSql, paramList.toArray(), null);
                if (result != null && result.size() > 0) {
                    ocaJobOrder = result.get(0);
                }
            }
        }
        catch (DaoException e) {
        }
        if (ocaJobOrder != null && ocaJobOrder.getJobNumber() != null) {
            retObject = (T) ocaJobOrder;
        }
        else if (ceJobOrder != null && ceJobOrder.getJobNumber() != null) {
            retObject = (T) ceJobOrder;
        }
        return retObject;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> searchByHql(String hql, Class<?> entitytype, ArrayList<Object> paramList) {

        try {
            List<Object[]> result = DaoManager.getDao(entitytype).searchBySql(hql, paramList.toArray(), null);
            return result;
        }
        catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }
    //TODO:confirm search DepositInvoice : Normal advance search/search methods throwing exceptions due to the native query problem ,
    //depositInvoiceSearch method help to solve the issue.
    @SuppressWarnings("unchecked")
    @Override
    public List<DepositInvoice> depositInvoiceSearch(List<SearchCriteria> criteria, Pagination pagination, Object object) {

        List<DepositInvoice> dilist=new ArrayList<DepositInvoice>();
        try {
            Dao<DepositInvoice> dao = DaoManager.getDao(DepositInvoice.class);
            String sqlString="Select di.id from DepositInvoice di,CEJobContract jc,CEJobOrder jo where di.jobContractId=jc.id" +
            		" and jc.jobOrderNumber=jo.jobNumber ";
            Object []parameters=new Object[criteria.size()];
            for(int i=0;i<criteria.size();i++){
                SearchCriteria sc=criteria.get(i);
                sqlString+= "and "+sc.getName()+" = ? ";
                parameters[i]=sc.getValue();
            }
            List<Long> list = dao.searchBySql(sqlString, parameters,pagination);
            for(int i=0;i<list.size();i++){
                DepositInvoice di=dao.find(list.get(i));
                dilist.add(di);
            }
            
            
        }
        catch (DaoException e) {

        }
        return dilist;
    }

    @SuppressWarnings("unchecked")
    public List<Double> getSumOfFundedAmount(PurchaseOrder po) {

        try {
            
            Dao<JobTest> dao = DaoManager.getDao(JobTest.class);
            String sqlString=" select sum(jt.fundedAmount) from com.intertek.phoenix.entity.JobTest jt where jt.poId =?";
            Object []parameters=new Object[]{po.getId()};   
            List<Double> list = dao.searchBySql(sqlString, parameters,null);
            return list;
        }
        catch (DaoException e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
