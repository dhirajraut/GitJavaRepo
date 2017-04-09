/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.search;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.CurrencyRate;
import com.intertek.entity.CustAddress;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.GLCode;
import com.intertek.phoenix.entity.PurchaseOrder;
import com.intertek.phoenix.util.ArrayMapGrid;

/**
 * This is the service to search entity objects and "unstructured" specific
 * information.
 * <p>
 * When searching entity objects, the caller must pass in the type (Class) of
 * entity to be searched and some search criteria to one of the search methods.
 * There are two ways to specify search criteria: using a Map of name/value
 * pair, or a List of SearchCriteria for advanced search. A Map of name/value
 * pair is a simpler way to specify search conditions, similar to properties
 * files. For example, if a user want to search for a JobOrder with quote number
 * equals "Q11" and warehouseName equals "Houston" and buName equals "CE Toys"
 * and customer's name equals to "Mattle", then the user specify these criteria
 * as:
 * 
 * <pre>
 *     buName=CE Toys
 *     warehouseName=Houston
 *     quote.number=Q11
 *     customerInfo.customer.name=Mattle
 * </pre>
 * 
 * To call a search function,
 * 
 * <code>
 * <pre>
 *      Map<String, String> criteria = new HashMap<String, String>();
 *      criteria.put("buName", "CE Toys");
 *      criteria.put("warehouseName", "Houston");
 *      criteria.put("quote.number", "Q11");
 *      criteria.put("customerInfo.customer.name", "Mattle");
 *      List<JobOrder> searchResult = searchService.search(JobOrder.class, criteria);
 * </pre>
 * <code>
 * 
 * Notice the naming scheme used in the search criteria. A criterion name may
 * contain more than one parts, separated by a single dot. Each part except the
 * last one can be one of two things: the simple name of an entity class, or the
 * name of a field of the entity denoted in the previous parts. For example,
 * giving that we are searching for <code>JobOrder</code> objects,
 * <code>quote.number</code> means that the object referenced by the field which
 * has the name "quote" must have a field named "number" and its value must be
 * "Q11". The same criterion can also be specified as:
 * <code>Quote.number=Q11</code>; in this case, the field within JobOrder which
 * is a type of Quote must have a field "number" and its value must equals to
 * "Q11".
 * <p>
 * Simple criteria expressed in Map only use the EQUAL (=) operator. To specify
 * other search criteria like <code>age > 65</code>, the
 * <code>advancedSearch()</code> methods should be used. Instead of taking a Map
 * of name/value pairs, these methods take a list of SearchCriteria object.
 * <code>SearchCriteria</code> is a simple class that has three fields: name,
 * value and operator. All valid SQL operators are supported, including LIKES,
 * IN, EXISTS etc. To recycle the previous example, the search conditions can be
 * specified as:
 * 
 * <pre>
 *     buName=CE Toys
 *     warehouseName LIKE Hou%
 *     quote.number LIKE Q%
 *     customerInfo.customer.name=Mattle
 * </pre>
 * 
 * To call advancedSearch() function,
 * 
 * <code>
 * <pre>
 *      List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();
 *      criteria.add(new SearchCriteria("buName", "CE Toys"));
 *      criteria.add(new SearchCriteria("warehouseName", "Houston", "LIKE"));
 *      criteria.add(new SearchCriteria("quote.number", "Q11", "LIKE"));
 *      criteria.add(new SearchCriteria("customerInfo.customer.name", "Mattle"));
 *      List<JobOrder> searchResult = searchService.advancedSearch(JobOrder.class, criteria));
 * </pre>
 * <code>
 * <p>
 * Criterion names are case sensitive.
 * <p>
 * Both <code>search()</code> and <code>advancedSearch()</code> functions are
 * overloaded to support pagination and sorting. Check the method signature for
 * more details.
 * <p>
 * Other methods defined in the search service are for special purposes, for
 * example, search for JobLogs. This type of search will typically return data
 * in an "unstructured" form, meaning not as entity objects, but in a two
 * dimensional array, expressed as <code>ArrayMapGrid</code>. These specialized method 
 * also take the simple Map constructs as well as the SearchCriteria for advanced search.
 * Specialized search method always have pagination and sorting support.
 * <p>
 * More specialized search methods will be added in the future.
 * <p>
 * Note, for simple searches such as searching for bank codes, it is
 * recommended to use simple search functions that returns entity objects, and
 * retrieve the required information (bank code) out of the returned entity objects.
 * Do not create/use specialized function for this simple searches.
 * <p>
 * Note, almost all searches can be performed by either specialized search
 * methods or advancedSearch() methods, it is up to the caller to choose the
 * most convenient method to call. In many cases, using DAO service's find()
 * methods or Query By Example feature are more convenient.
 * 
 * @author lily.sun
 * @author richard.qin
 */
public interface SearchService {

    /**
     * Search for any entity by specifying the entity class and search criteria
     * in name value pair.
     * 
     * @param entityType
     *            : the class type of entity to be searched.
     * @param criteria
     *            : a Map<String, String> of name, value pair. It will apply
     *            EQUALS to the name value pair.
     * @return a list of objects that will be displayed on the page
     * @throws PhoenixException
     *            : Any exceptions that happen during the search
     */    
    public <T> List<T> search(Class<T> entityType, Map<String, String> criteria) throws DaoException;
    
    public <T> T uniqueSearch(Class<T> entityType, Map<String, String> criteria)throws DaoException;
    
    /**
     * Search for any entity based on user specified criteria.
     * 
     * @param entityType
     *            : the class type of entity to be searched
     * @param criteria
     *            : A list of SearchCriteria which has (field ops value)
     *            information
     * @return a list of objects that will be displayed on the page
     * @throws PhoenixException
     *            : Any exceptions that happen during the search
     */
    public <T> List<T> advancedSearch(Class<T> entityType, List<SearchCriteria> criteria) throws DaoException;

    /**
     * Search for any entity with pagination and sorting order.
     * 
     * @param entityType
     *            : the class type of entity to be searched.
     * @param criteria
     *            : a Map<String, String> of name, value pair with implied
     *            EQUALS for the pair.
     * @param paganiation
     *            : pagination information. If not specified, default to 20
     *            records per page.
     * @param sortBy
     *            : argument list of sorting column names. This can be zero, one
     *            or more sorting columns. If zero argument is specified, return
     *            an unsorted list.
     * @return a list of objects that will be displayed on the page
     * @throws PhoenixException
     *            : Any exceptions that happen during the search
     */
    public <T> List<T> search(Class<T> entityType, Map<String, String> criteria, Pagination pagaination, SortInfo sortBy) throws DaoException;
    
    /**
     * Search for any entity based on user specified criteria with pagination
     * info.
     * 
     * @param entityType
     *            : the class type of entity to be searched
     * @param criteria
     *            : A list of SearchCriteria which has (field ops value)
     *            information
     * @param paganiation
     *            : pagination information. If not specified, default to 20
     *            records per page.
     * @param sortBy
     *            : argument list of sorting column names. This can be zero, one
     *            or more sorting columns. If zero argument is specified, return
     *            an unsorted list.
     * @return a list of objects that will be displayed on the page
     * @throws PhoenixException
     *            : Any exceptions that happen during the search
     */
    public <T> List<T> advancedSearch(Class<T> entityType, List<SearchCriteria> criteria, Pagination pagaination, SortInfo sortBy) throws DaoException;


    /**
     * Given the search criteria, this method will retrieve the information
     * needed for Job Log page. Data may come from different entities,
     * therefore, no class type is specified. The class to be searched against
     * is resolved by parsing the name in the criteria.
     * 
     * @param criteria
     *            : search criteria that
     * @param pagaination
     * @param sortBy
     *            : argument list of sorting column names. This can be zero, one
     *            or more sorting columns. If zero argument is specified, return
     *            an unsorted list.
     * @return ArrayMapGrid
     *            : The result is stored in <code>ArrayMapGrid. grid.getFieldNames()</code> gives
     *            the header information. Using <code>moveNext()</code> will iterate 
     *            through data row by row. Then for each row, <code>grid.getFieldValue(key)</code> 
     *            will return the data matching the key. Code example:
     *            <code> 
     *            <pre>
     *             Set<String> keys = grid.getFieldNames();
     *             while(grid.moveNext()){
     *                  for(String key: keys){
     *                       String value = grid.getFieldValue(key);
     *                   }
     *               }
     *            </pre>
     *            </code>
     * @throws PhoenixException 
     *            : Any exceptions that happen during the search
     */
    public ArrayMapGrid searchJobLog(Map<String, String> criteria, Pagination pagaination, SortInfo sortBy) throws DaoException;
    
    /**
     * This method will retrieve Job Log needed information based on the list of
     * search criteria. Data may come from different entities, therefore, no
     * class type is specified. The class to be searched against is resolved by
     * parsing the name in the criteria.
     * 
     * @param criteria
     * @param pagaination
     * @param sortBy
     *            : argument list of sorting column names. This can be zero, one
     *            or more sorting columns. If zero argument is specified, return
     *            an unsorted list.
     * @throws PhoenixException
     *            : Any exceptions that happen during the search
     */
    public ArrayMapGrid searchJobLog(List<SearchCriteria> criteria, Pagination pagaination, SortInfo sortBy) throws DaoException;

    /**
     * This method will return GLCode which contains the GLCode and DeptId based on
     * service description.
     * 
     * @param serviceDescription
     * @return GLCode
     */
    public GLCode searchGLCode(String serviceDescription);


    /**
     * This method retrieves a list of Contacts by customer or contact.
     *
     * @param searchInfo
     * @param pagination 
     * @param sortInfo 
     * @return A list of ContactCust entities
     */
    public List<ContactCust> searchContactsByCustCode(ContactCustSearchInfo searchInfo, Pagination pagination, SortInfo sortInfo) throws DaoException;
    
    /**
     * This methods specially searches ContractCustContact for the Customer search popup
     * where the only search input could be for contract code, customer code or primary contact name
     *  
     * @param value
     * @param pagination
     * @param sortInfo 
     * @return
     */
    public List<ContractCustContact> searchContractCustomerContact(String value, Pagination pagination, SortInfo sortInfo) throws DaoException;


    /**
     * Search for the currency conversion rates between two currencies at the given date.
     * @param fromCurrency
     * @param toCurrency
     * @param date
     * @return
     * @throws PhoenixException 
     */
    public List<CurrencyRate> searchCurrencyRate(String fromCurrency, String toCurrency, Date date) throws DaoException;
    
    /**
     * search for the users, and users based on a particular role  
     * @param pagination
     * @param sortInfo
     * @param value
     * @return
     */
//    public List<User> searchUsersByRole(Pagination pagination, SortInfo sortInfo, String... value) throws DaoException;
    
    /**
     * Search for CustAddress for a given customer and contact id
     * @param custCode
     * @param schedulerContactId
     * @return
     * @throws PhoenixException
     */
    public CustAddress searchAddress(String custCode, long schedulerContactId) throws DaoException ;
    
    /**
     * Retrieve the Bank code for Customer
     * 
     * @param pagination
     * @param sortInfo
     * @param value The search values would be buName, currency, bank_code, bankDescription in that order
     * @return
     * @throws PhoenixException
     */
    List<Bank> getBankCode(BankSearchInfo searchInfo, Pagination pagination, SortInfo sortInfo) throws DaoException ;
    
    /**
     * Retrieve bank account for 
     * 
     * @param pagination
     * @param sortInfo
     * @param value The search values would be buName, currency, bank_code, bankAcctCode, bankDescription in that order
     * @return
     * @throws PhoenixException
     */
    List<BankAccount> getBankAccount(BankSearchInfo searchInfo, Pagination pagination, SortInfo sortInfo) throws DaoException ;
    
    /**
     * A wrapper method added to do generic search so that entities are managed in hiberante.
     * 
     * @param <T>
     * @param entityType
     * @param queryInfo
     * @return
     * @throws DaoException
     */
    <T> List<T> search(Class<T> entityType, QueryInfo queryInfo) throws DaoException;
    
    /**
     * This method will return a list of job orders in both CE and OCA divisions. 
     * The default equals is applied to the search field and search value.
     * Data may come from different entities, therefore, no class type is specified. 
     * 
     * @param criteria
     * @param pagination
     * @param sortBy
     * @return
     * @throws PhoenixException
     */
    public ArrayMapGrid searchJob(JobSearchInfo criteria, Pagination pagination, SortInfo sortBy) throws PhoenixException ;

    
    public <T> T quickJobSearch(SearchKey key, String searchValue) throws PhoenixException;

    //TODO confirm:search method for reports
    public List<Object[]> searchByHql(String hql, Class<?> entitytype, ArrayList<Object> paramList);

  //TODO confirm:search method for DepositInvoice Search
    public List<DepositInvoice> depositInvoiceSearch(List<SearchCriteria> criteria, Pagination pagination, Object object);    

    /**
     *  For calculating the sum of funded Amounts for the given Purchase Order.
     *
     */
    public List<Double> getSumOfFundedAmount(PurchaseOrder po);

    
}


