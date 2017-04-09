package com.intertek.service;

import java.util.List;

import com.intertek.domain.AddressSeqSearch;
import com.intertek.domain.CollectorSearch;
import com.intertek.domain.ContactSearch;
import com.intertek.domain.CreditAnalystSearch;
import com.intertek.domain.CustomerSearch;
import com.intertek.entity.Collector;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.CreditAnalyst;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustAddressLanguage;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.Customer;
import com.intertek.entity.CustomerLanguage;
import com.intertek.entity.JobContract;
import com.intertek.entity.Notes;
import com.intertek.entity.User;

public interface CustomerService
{
  Contact getContactById(Long id);

  Contact addContact(Contact contact);
  void saveContact(Contact contact);
  void searchContact(ContactSearch search,String sortFlag);
  void getContractCustContacts(ContactSearch search, String sortFlag);
  void getContractCustCustomers(CustomerSearch search, String sortFlag);
  void contactIDSearch(ContactSearch search,String sortFlag)throws Exception;
  Contact getContactByIdandCustCode(Long id,String custCode);

  Customer getCustomerByName(String customerName);
  Customer getCustomerByNameAndCode(String customerName);

  Customer loadCustomerByCustCode(String custCode);

  Customer addCustomer(Customer customer);
  Customer addQuickCustomer(Customer customer);
  void saveCustomer(Customer customer,String parentCustomerName);
  void searchCustomer(CustomerSearch search,String sortFlag);
  void searchCreditAnalyst(CreditAnalystSearch search,String sortFlag);
  void searchCollector(CollectorSearch search,String sortFlag);
  void searchCustomerByName(CustomerSearch search,String parentCustomerSearchFlag,String sortFlag);
  //void UpdateDeleteNote(List<Notes> noteList,List<Notes> delList);
  List getCustomersByCustCode(String custCode);
  List getParentCustomersByCustCode(String custCode);
  List getParentCustomersByName(String Name);
  List getCustomersByName(String customerName);

  void deleteContactCustAssociations(String custCode);

  List getCustomerAddressesByAddrSeqNumber(String custCode,String addrSeqNumber);
  CustAddrSeq getCustAddrSeqByLocationNumber(Integer locationNumber, String custCode);
  Customer getCustomerByCustCode(String custCode);

  List getCreditAnalystByCode(String creditAnalystCode);
  CreditAnalyst getCreditAnalystByCACode(String creditAnalystCode);

  List listCreditAnalystByCode(String creditAnalystName);

  List getCollectorNameByCode(String collectorCode);
  List listCollectorByCode(String collectorName);
  Collector getCollectorByCode(String collectorCode);

  List getAccountOwner(String accountOwner);
  User getAccountOwnerByLoginName(String accountOwner);

  List getContactsById(String contactId);
  List getContactsByContactIdandCustCode(String contactId,String customerId);


  List getCustAddressDetailsBySeqNumber(Integer locationNumber, String custCode);
  List getCustAddrSeqsByCustCode(String custCode);
  List getContactCustsByCustCode(String custCode);
  List getContactCustsByContactIdAndCustCode(Long contactId, String custCode);
  List getContractCustsByCustCode(String custCode);
  List getSortedContractCustsByCustCode(String custCode, String sortFlag);
  List getCustomerVatRegistrationsByCustCode(String custCode);
  //List getCustomerNotes(String custCode);
  Customer loadContractCustsByCustCode(String custCode);
    void searchAddressSequence(AddressSeqSearch search,String sortFlag );
    void searchCustomerId(CustomerSearch search,String sortFlag);
    void saveContractCust(ContractCust contractCust);
  //  ContactCust saveContactCust(ContactCust contactCust);
    List sortContactCustsByFirstNameWithCustCode(String code);
    List sortContactCustsByLastNameWithCustCode(String code);
    List sortContactCustsByContactIdWithCustCode(String code);
    List sortContactCustsByRelStatusWithCustCode(String code);
    List sortContactCustsByAddrSeqWithCustCode(String code);
    List sortContactCustsByAddrDescWithCustCode(String code);
    CustVatRegistration getCustomerVatRegistrationsByCustCodeAndCountryCode(String custCode,String countryCode);
    CustVatRegistration getCustomerVatRegistrationsByCustCodeAndRegId(String custCode,String regId);
	//newly added
	List getContactSeqByCustCode(String custCode);
	boolean getContactCustomersByIdandCode(Long id,String customerId,Integer locationNumber,Integer seqnumber);
	boolean getExistingseqNumber( Long id,String customerId,Integer locationNumber);
	//up to here
	List getCustomersByAccountOwnerName(String name);
	void updateCustomer(Customer customer);

  List getBillingContactsByContactIdandCustCode(String contactId,String customerId,String contractcode);
  boolean existCustContact(Long id, String custCode);
  ContactCust getContactCustByPK(long contactId, String custCode, int location);
  void deleteContactCustByPK(long contactId, String custCode, int location);
  CustomerLanguage addCustomerLanguage(CustomerLanguage customerLanguage);
  CustAddressLanguage addCustomerAddrLanguage(CustAddressLanguage custAddressLanguage);
  CustAddress getCustAddresIdByCustCodeAndAddrSeqNumber(String custCode,Integer seqNumber);
  CustomerLanguage getCustomerLanguageByCode(String custCode,String language);
  CustAddressLanguage getCustomerAddrLanguageById(long custAddrid,String language);
  
  boolean getContactCustsByCustCodeandLocationNumber(String custCode,Integer locationNumber);
  List getContactCustListByContactid(long contactId);
 ContractCustContact getContactCustByContactidcustCodeandcontractCode(String custCode,long contactId,String contractCode);
 public List<Customer> getCreatedByCustomers(String userName);
 public List<Customer> getUpdatedByCustomers(String userName);
//START: 135193 
 public List getParentCustomers(String customerLocation);
 public List getChildCustomers(String customerLocation);
 public List getChildCustomersByParentcutomer(String customerLocation);
 public List<User> getAccountOwnerRoleUsers(String accountOwner);

 //To get Service Location and Date
 public List<JobContract> getServiceLocationInfo(String strCustCode);
 //To save Service Location and Date 
 public void updateServiceLocationInfo(Customer customer);
//START: 135193 
 }

