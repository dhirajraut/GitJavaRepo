package com.intertek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.classic.Session;

import com.intertek.domain.AddressSeqSearch;
import com.intertek.domain.CollectorSearch;
import com.intertek.domain.ContactSearch;
import com.intertek.domain.CreditAnalystSearch;
import com.intertek.domain.CustomerSearch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Collector;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.Country;
import com.intertek.entity.CreditAnalyst;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustAddressLanguage;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.Customer;
import com.intertek.entity.CustomerLanguage;
import com.intertek.entity.JobContractNote;
import com.intertek.entity.Role;
import com.intertek.entity.State;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;
import com.intertek.entity.JobContract;
public class CustomerServiceImpl extends BaseService implements CustomerService
{
  public Contact getContactById(Long id)
  {
    List contacts = getDao().find(
      "from Contact c left join  fetch c.contactCusts cc left join fetch cc.customer left join fetch cc.custAddrSeq where c.id = ?",
      new Object[] { id }
    );
    if(contacts.size() > 0) return (Contact)contacts.get(0);

    return null;
  }

  public Contact addContact(Contact contact)
  {
    if(contact == null) return null;

    Contact existedContact = getContactById(contact.getId());
    System.out.println("existedContact"+existedContact);
    if(existedContact != null)
    {
      //throw new RuntimeException("Contact exists for name:" + contact.getFirstName()+" "+contact.getLastName());
      throw new ServiceException("contact.exist.error",new Object[] {contact.getFirstName()+" "+contact.getLastName()});
    }
   Iterator it = contact.getContactCusts().iterator();
    while(it.hasNext())
    {
      ContactCust contactCust = (ContactCust)it.next();

      Customer customer = contactCust.getCustomer();
      CustAddrSeq custAddrSeq = contactCust.getCustAddrSeq();

      if(customer != null)
      {
        Customer customers = getCustomerByCustCode(customer.getCustCode());
          if(customers == null)
            throw new ServiceException("customer.error" ,new Object[] {customer.getCustCode()}, null);
            //throw new RuntimeException("customer does not exist: " + customer.getCustCode());
        contactCust.setCustomer(customer);
        contactCust.getContactCustId().setCustCode(customer.getCustCode());
      }
      if(custAddrSeq != null)
      {
          custAddrSeq = getCustAddrSeqByLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber(),customer.getCustCode());
         //Sreeram Comented
          /*contactCust.setCustAddrSeq(custAddrSeq);
          if(custAddrSeq != null)
               contactCust.getContactCustId().setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());
              */
        if(custAddrSeq != null)
        {
          contactCust.setCustAddrSeq(custAddrSeq);
            contactCust.getContactCustId().setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());
        }else
          contactCust.setCustAddrSeq(new CustAddrSeq());
      }
      contactCust.setContact(contact);
      contactCust.getContactCustId().setContactId(contact.getId());
  }


    contact.setNewFlag(Constants.NEW);
    contact.setUpdateFlag(Constants.NEW);
    contact.setUpdateLimsFlag(Constants.NEW);

    contact = getDao().save(contact);
    contact.setFinContactId(contact.getId());
    contact = getDao().save(contact);

  getDao().flush();
  return contact;

  }

 public void saveContact(Contact contact)
  {

    if(contact == null) return;

    Contact existedContact = getContactById(contact.getId());
    if(existedContact == null)
    {
     // throw new RuntimeException("contact does not exist for id: " + contact.getId());
      throw new ServiceException("contact.error" ,new Object[] {contact.getId()}, null);
    }


    Iterator it = contact.getContactCusts().iterator();
    while(it.hasNext())
    {

      ContactCust contactCust = (ContactCust)it.next();
      contactCust.setContact(contact);


      Customer customer = contactCust.getCustomer();

      CustAddrSeq custAddrSeq = contactCust.getCustAddrSeq();

      if(customer != null)
      {
       // customer = getCustomerByCustCode(customer.getCustCode());
        Customer customers = getCustomerByCustCode(customer.getCustCode());

        if(customers == null)
          throw new ServiceException("customer.error" ,new Object[] {customer.getCustCode()}, null);
          //throw new RuntimeException("customer does not exist: " + customer.getCustCode());
        contactCust.setCustomer(customers);
       // contactCust.setCustomer(customer);
      }

      if(custAddrSeq != null)
      {
        custAddrSeq = getCustAddrSeqByLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber(),customer.getCustCode());

        //if(custAddrSeq == null) throw new RuntimeException("custAddrSeq does not exist: " + custAddrSeq.getCustAddrSeqId().getLocationNumber());
        if(custAddrSeq == null)
        throw new ServiceException("customer.addressseq.error" ,new Object[] {custAddrSeq.getCustAddrSeqId().getLocationNumber()}, null);
        contactCust.setCustAddrSeq(custAddrSeq);
      }
    }

   contact.setUpdateFlag(Constants.NEW);
   contact.setUpdateLimsFlag(Constants.NEW);
  try{
    getDao().save(contact);

  }catch(Exception e){

  }

  }

  public void searchContact(ContactSearch search,String sortFlag)
  {

    if(search == null) return;
    StringBuffer sb = new StringBuffer(" where 1=1 ");
    List params = new ArrayList();
    Long contactId = search.getContactId().getValue();
  int contactValue=search.getContactId().getOperator();

        if((contactId!=null)&&(contactValue==Constants.CONTAINS))
         {
          String cId='%'+String.valueOf(contactId)+'%';
          sb.append(" and str(cc.contact.id) like ?");
          params.add(cId);
         }

                  else if((contactId!=null)&&(contactValue==Constants.BEGINS_WITH))
                  {
                    String cId=String.valueOf(contactId)+'%';
                     sb.append(" and str(cc.contact.id) like ?");
                    params.add(cId);
                  }

                      else if((contactId!=null)&&(contactValue==Constants.EQUALS))
                        {
                          sb.append(" and  cc.contact.id = ?");
                          params.add(contactId);
                        }

                              else if((contactId!=null)&&(contactValue==Constants.NOT_EQUALS))
                              {
                                   sb.append(" and cc.contact.id != ?");
                                  params.add(contactId);
                              }

    String firstName = search.getFirstName().getValue();
    int firstNameVal=search.getFirstName().getOperator();
    if((firstName != null) && !"".equals(firstName.trim()))
    {

      if((firstName!=null)&&(firstNameVal==Constants.CONTAINS))
        {
         String fName='%'+firstName.toUpperCase() +'%';
         sb.append(" and upper(cc.contact.firstName) like ? ");
           params.add(fName);

         }
           if((firstName!=null)&&(firstNameVal==Constants.BEGINS_WITH))
            {
             String fName=firstName.toUpperCase() +'%';
             sb.append(" and upper(cc.contact.firstName)  like ? ");
             params.add(fName);

             }
                  if((firstName!=null)&&(firstNameVal==Constants.EQUALS))
                   {
                     String fName=firstName.toUpperCase();
                   sb.append(" and upper(cc.contact.firstName) = ?");
                   params.add(fName);

                   }
                         if((firstName!=null)&&(firstNameVal==Constants.NOT_EQUALS))
                               {
                                String fName=firstName.toUpperCase();
                                sb.append(" and upper(cc.contact.firstName) != ?");
                                params.add(fName);

                                }
                        }

    String lastName = search.getLastName().getValue();
    int lastNameVal=search.getFirstName().getOperator();
    if((lastName != null) && !"".equals(lastName.trim()))
    {

      if((lastName!=null)&&(lastNameVal==Constants.CONTAINS))
        {
         String lName='%'+lastName.toUpperCase() +'%';
         sb.append(" and upper(cc.contact.lastName) like ? ");
           params.add(lName);
         }
           if((lastName!=null)&&(lastNameVal==Constants.BEGINS_WITH))
            {
             String lName=lastName.toUpperCase() +'%';
             sb.append(" and upper(cc.contact.lastName)  like ? ");
             params.add(lName);
             }
                  if((lastName!=null)&&(lastNameVal==Constants.EQUALS))
                   {
                     String lName=lastName.toUpperCase();
                   sb.append(" and upper(cc.contact.lastName) = ?");
                   params.add(lName);
                   }
                         if((lastName!=null)&&(lastNameVal==Constants.NOT_EQUALS))
                               {
                                String lName=lastName.toUpperCase();
                                sb.append(" and upper(cc.contact.lastName) != ?");
                                params.add(lName);
                                }
                        }

   // Long customerId = search.getCustomerId().getValue();
    String customerId= search.getCustomerId().getValue();
    int customerVal=search.getCustomerId().getOperator();

    if(customerId != null)
          {
                if((customerId!=null)&&(customerVal==Constants.CONTAINS))
                {
                 String custId='%'+String.valueOf(customerId)+'%';
    sb.append(" and  upper(cu.custCode) like ?");
      params.add(custId.toUpperCase());
                }
             else if((customerId!=null)&&(customerVal==Constants.BEGINS_WITH))
                    {
                     String custId=String.valueOf(customerId)+'%';
                      sb.append(" and str(cu.custCode) like ?");
                      params.add(custId);
                    }
                        if((customerId!=null)&&(customerVal==Constants.EQUALS))
                        {
                         sb.append(" and cu.custCode=?");
                          params.add(customerId);
                        }
                               else if((customerId!=null)&&(customerVal==Constants.NOT_EQUALS))
                              {
                                  sb.append(" and upper(cu.custCode) !=?");
                                  params.add(customerId.toUpperCase());
                              }
                 }


     String customerName = search.getCustomerName().getValue();
    int customerValue=search.getCustomerName().getOperator();
    if((customerName != null) && !"".equals(customerName.trim()))
      {

            if((customerName!=null)&&(customerValue==Constants.CONTAINS))
            {
             String custName='%'+customerName.toUpperCase()+'%';
             sb.append(" and upper(cu.name) like ? ");
             params.add(custName);
              }
                 if((customerName!=null)&&(customerValue==Constants.BEGINS_WITH))
                  {
                   String custName=customerName.toUpperCase()+'%';
                   sb.append(" and upper(cu.name)  like ?");
                   params.add(custName);
                   }
                        if((customerName!=null)&&(customerValue==Constants.EQUALS))
                         {
                          String custName=customerName.toUpperCase();
                          sb.append(" and upper(cu.name) = ? ");
                          params.add(custName);
                           }
                               if((customerName!=null)&&(customerValue==Constants.NOT_EQUALS))
                                 {
                                 String custName=customerName.toUpperCase();
                                 sb.append(" and upper(cu.name) != ? ");
                                 params.add(custName);
                                  }
                        }
    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {
    if(pagination != null)
    {
  if(pagination.getTotalRecord() > 0)
     {
   /* List counts = getDao().find("select count(cc.contact.id) from ContactCust cc " + sb.toString(),
           params.toArray()
              );*/
   /* List counts = getDao().find("select count(ccid.contactId) from ContactCust cc left join fetch cc.contactCustId ccid left join fetch cc.contact c left join fetch cc.customer cu " + sb.toString(),
     params.toArray()
         );*/
    results = getDao().find(
             "select distinct cc from ContactCust cc inner join fetch cc.contact c inner join fetch cc.customer cu "+sb.toString()+" order by c.id",
             params.toArray()
               );
    /* if(counts.size() > 0)
       {

           Number count = (Number)results.get(0);
            pagination.setTotalRecord(count.intValue());
       }*/
     pagination.setTotalRecord(results.size());
     }
     pagination.calculatePages();
      results = getDao().find(
        "select distinct cc from ContactCust cc inner join fetch cc.contact c inner join fetch cc.customer cu "+sb.toString()+" order by c.id",
        params.toArray(),
          pagination
          );
    /* if(pagination.getTotalRecord() > 0 && !results.isEmpty())
       pagination.setTotalRecord(results.size());*/
   search.setResults(results);
   //search.setTotalResults(results);
    //  pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
   //   "select distinct cc from ContactCust cc " + sb.toString(),
   "select distinct cc from ContactCust cc  inner join fetch cc.contact c inner join fetch cc.customer cu " + sb.toString(),
        params.toArray()
      );
  }
        for(int i=0;i<results.size();i++)
            {
                ContactCust con = (ContactCust) results.get(i);
              }
          search.setResults(results);
    // search.setTotalResults(results);
          //line added
        search.setPagination(pagination);
    }else
    {
    String orderByValue = "";
if(sortFlag != null)
{
      if(sortFlag.equals("id"))
        orderByValue=" order by c.id ";
      if(sortFlag.equals("name"))
        orderByValue=" order by c.firstName,c.lastName ";
      if(sortFlag.equals("customer.custCode"))
        orderByValue=" order by cu.custCode ";
      if(sortFlag.equals("customer.name"))
        orderByValue=" order by cu.name ";
}
     if(pagination != null)
     {
       if(pagination.getTotalRecord() > 0)
       {
     /* List counts = getDao().find("select count(cc.contact.id) from ContactCust cc " + sb.toString(),
             params.toArray()
                );*/
        /* List counts = getDao().find("select count(ccid.contactId) from ContactCust cc inner join fetch cc.contactCustId ccid inner join fetch cc.contact c inner join fetch cc.customer cu " + sb.toString(),
               params.toArray()
                   );*/
         results = getDao().find(
               "select distinct cc from ContactCust cc inner join fetch cc.contact c inner join fetch cc.customer cu "+sb.toString()+" order by c.id",
               params.toArray()
                 );
      /* if(counts.size() > 0)
         {

             Number count = (Number)results.get(0);
              pagination.setTotalRecord(count.intValue());
         }*/
         pagination.setTotalRecord(results.size());
       }
       pagination.calculatePages();
       search.setPagination(pagination);
     }
     results = getDao().find(
            "select cc from ContactCust cc inner join fetch cc.contact c inner join fetch cc.customer cu " + sb.toString() + orderByValue,
            params.toArray(),
              pagination
              );

 /*  if(pagination.getTotalRecord() > 0 && !results.isEmpty())
     pagination.setTotalRecord(results.size());
    pagination.calculatePages();*/
    search.setResults(results);
  // search.setTotalResults(results);
    }
  }

  public Customer loadCustomerByCustCode(String custCode)
  {
    List customers = getDao().find(
      //"from Customer c left join fetch c.parentCustomer left join fetch c.creditAnalyst left join fetch c.collector left join fetch c.accountOwner left join fetch c.interunitBusUnit left join fetch c.custAddrSeqs cas left join fetch cas.custAddresses left join fetch c.contactCusts  con left join fetch con.contact left join fetch con.custAddrSeq left join fetch c.contractCusts cont left join fetch cont.contract where c.custCode = ? ",
      "from Customer c left join fetch c.contactCusts  con left join fetch con.contact left join fetch c.parentCustomer left join fetch c.creditAnalyst left join fetch c.collector left join fetch c.accountOwner left join fetch c.interunitBusUnit  where c.custCode = ? ",
      new Object[] { custCode }
    );

   /* if(customers.size() > 0) return (Customer)customers.get(0);

    return null;*/

    if(customers != null && customers.size() > 0) return (Customer)customers.get(0);
    else
      throw new ServiceException("load.customer.error");
   // return null;
  }

  public List getContactCustsByCustCode(String custCode)
  {
    List contactCusts = getDao().find(
          " from ContactCust con inner join fetch con.contact ct inner join fetch con.customer left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? order by ct.id,con.custAddrSeq.custAddrSeqId.locationNumber",
          new Object[] { custCode }
        );

        if(contactCusts.size() > 0) return contactCusts;

        return null;
  }

  public List getContactCustsByContactIdAndCustCode(Long contactId, String custCode)
  {
    return getDao().find(
      " from ContactCust con where con.contactCustId.contactId = ? and con.contactCustId.custCode = ?",
      new Object[] {contactId, custCode }
    );
  }

  public List sortContactCustsByFirstNameWithCustCode(String custCode)
  {
    List contactCusts = getDao().find(
          " from ContactCust con inner join fetch con.contact ct left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? order by ct.firstName",
          new Object[] { custCode }
        );

       /* if(contactCusts.size() > 0) return contactCusts;

        return null;*/

        if(contactCusts != null && contactCusts.size() >0) {
          return contactCusts;
        } else {
            throw new ServiceException("contactcust.sort.error");
        }
  }

  public List sortContactCustsByLastNameWithCustCode(String custCode)
  {
    List contactCusts = getDao().find(
          " from ContactCust con inner join fetch con.contact ct left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? order by ct.lastName",
          new Object[] { custCode }
        );

      /*  if(contactCusts.size() > 0) return contactCusts;

        return null;*/
      if(contactCusts != null && contactCusts.size() >0) {
      return contactCusts;
    } else {
        throw new ServiceException("contactcust.sort.error");
    }
  }

  public List sortContactCustsByContactIdWithCustCode(String custCode)
  {
      List contactCusts = getDao().find(
            " from ContactCust con inner join fetch con.contact ct left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? order by ct.id",
            new Object[] { custCode }
          );

         /* if(contactCusts.size() > 0) return contactCusts;

          return null;*/

      if(contactCusts != null && contactCusts.size() >0) {
        return contactCusts;
      } else {
          throw new ServiceException("contactcust.sort.error");
      }
  }
  public List sortContactCustsByAddrSeqWithCustCode(String custCode)
  {

      List contactCusts = getDao().find(
            " from ContactCust con inner join fetch con.contact ct left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? order by con.custAddrSeq.custAddrSeqId.locationNumber,con.custAddrSeq.addressDescr",
            new Object[] { custCode }
          );

        /*  if(contactCusts.size() > 0) return contactCusts;

          return null;*/
      if(contactCusts != null && contactCusts.size() >0) {
      return contactCusts;
    } else {
        throw new ServiceException("contactcust.sort.error");
    }
  }
  public List sortContactCustsByAddrDescWithCustCode(String custCode)
  {

      List contactCusts = getDao().find(
            " from ContactCust con inner join fetch con.contact ct left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? order by con.custAddrSeq.addressDescr",
            new Object[] { custCode }
          );

         /* if(contactCusts.size() > 0) return contactCusts;

          return null;*/
      if(contactCusts != null && contactCusts.size() >0) {
      return contactCusts;
    } else {
        throw new ServiceException("contactcust.sort.error");
    }
  }

  public List sortContactCustsByRelStatusWithCustCode(String custCode)
  {

      List contactCusts = getDao().find(
            " from ContactCust con inner join fetch con.contact ct left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? order by con.status",
            new Object[] { custCode }
          );

       /*   if(contactCusts.size() > 0) return contactCusts;

          return null;*/
      if(contactCusts != null && contactCusts.size() >0) {
          return contactCusts;
        } else {
            throw new ServiceException("contactcust.sort.error");
        }
  }

  public List getContractCustsByCustCode(String custCode)
  {
    List contractCusts = getDao().find(
          " from ContractCust cont left join fetch cont.contract  where cont.contractCustId.custCode = ?  ",
          new Object[] { custCode }
        );

        if(contractCusts.size() > 0) return contractCusts;

        return null;
  }

/* commented for General Notes
 * public List getCustomerNotes(String custCode)
  {
    List custNotes=getDao().find("from CustomerNote custNote where custNote.custCode = ?"
        , new Object[] { custCode });
    return custNotes;
  }*/


  public List getSortedContractCustsByCustCode(String custCode, String sortFlag)
  {
    List contractCusts = getDao().find(
          " from ContractCust cont left join fetch cont.contract  con where cont.contractCustId.custCode = ?  order by "+sortFlag,
          new Object[] { custCode }
        );

       /* if(contractCusts.size() > 0) return contractCusts;

        return null;*/

    if(contractCusts != null && contractCusts.size() >0) {
      return contractCusts;
    } else {
        throw new ServiceException("contractcust.sort.error");
    }
  }

  public List getCustomerVatRegistrationsByCustCode(String custCode)
  {
    List vatRegs = getDao().find(
          " from CustVatRegistration cvr left join fetch cvr.country where cvr.custVatRegistrationId.custCode = ?  ",
          new Object[] { custCode }
        );

        if(vatRegs != null && vatRegs.size() > 0) return vatRegs;

        return null;
  }

  public List getCustAddrSeqsByCustCode(String custCode)
  {


       List results = getDao().find(
            "select distinct c from CustAddrSeq c left join fetch c.custAddresses where c.custAddrSeqId.custCode = ? order by c.custAddrSeqId.locationNumber ",
              new Object[] { custCode });


    return results;

 }

  public Customer loadContractCustsByCustCode(String custCode)
  {
    List customers = getDao().find(
          "from Customer c left join fetch c.contractCusts where c.custCode = ?",
          new Object[] { custCode }
        );

        if(customers.size() > 0) return (Customer)customers.get(0);

        return null;
  }

  public Customer getCustomerByName(String customerName)
  {
    List customers = getDao().find(
      "from Customer c where c.name = ?",
      new Object[] { customerName }
    );

    if(customers.size() > 0) return (Customer)customers.get(0);

    return null;
    }

  public Customer getCustomerByNameAndCode(String customerName)
  {
    List customers=null;

    int index_val = customerName.lastIndexOf(",");
    String cname= customerName.substring(0, index_val);
    String ccode=customerName.substring(index_val+1);

    customers = getDao().find(" from Customer c where c.name = ? and upper(c.custCode) = ? ",  new Object[] { cname, ccode.toUpperCase()}  );

    if(customers.size() > 0) return (Customer)customers.get(0);
    return null;
  }


  public List getCustomersByName(String  customerName)
  {
     if(customerName == null) return new ArrayList();
     List name=new ArrayList();
     try{
     name=getDao().find("from Customer c where upper(c.name) like  '"+customerName.toUpperCase()+"%'",
     null);
     }
     catch(Exception e){

}
return name;
}
  /*For iTrack#135193-START */
  /**
   * Name :getParentCustomers
   * Date :Aug 24, 2009
   * purpose :to get current ParentCustomers
   * @param locationType
   * @return List
   */
  public List getParentCustomers(String locationType)
  {
     List name = new ArrayList();
     locationType = Constants.HQRT;
     try {
     name = getDao().find("from Customer c where c.locationType ='"+locationType+"' order by c.name",null);
     } catch(Exception e){
     }
	return name;
  }
  /**
   * Name :getChildCustomers
   * Date :Aug 24, 2009
   * purpose :to get current ChildCustomers
   * @param locationType
   * @return List
   */
  public List getChildCustomers(String locationType)
  {
    if(locationType == null) locationType = Constants.HQRT;
    List name = new ArrayList();
    try {
    	name = getDao().find("from Customer c where c.locationType !='"+locationType+"' order by c.name",null);
    } catch(Exception e){
	}
	return name;
  }
  /**
   * Name :getChildCustomersByParentcutomer
   * Date :Aug 24, 2009
   * purpose :to get current childCustomers of PraentCustomer
   * @param customerName
   * @return List
   */
  public List getChildCustomersByParentcutomer(String customerName){
	  List childNames = new ArrayList();
	  try {
	    	List name = getDao().find("from Customer c where c.name ='"+customerName+"' and c.locationType='"+Constants.HQRT+"'",null);
	    	if(name != null && name.size()>0){
	    		Customer customer =(Customer)name.get(0);
	    		childNames = getDao().find("from Customer c where c.parentCustCode ='"+customer.getParentCustCode()+"' order by c.name",null);
	    	}
	  } catch(Exception e){
	  }
	  return childNames;
  }
  
  /*For iTrack#135193-END */
  
  public void saveContractCust(ContractCust contractCust)
  {
    getDao().save(contractCust);
  }

  public Customer getCustomerByCustCode(String custCode)
  {
    List customers = getDao().find(
      "from Customer c where c.custCode = ?",
      new Object[] { custCode }
    );

   if(customers != null && customers.size() > 0) return (Customer)customers.get(0);

    return null;
  }

  public void saveCustomer(Customer customer,String parentCustomerName)
  {
    if(customer == null) return;
    //Customer existedCustomer =null;
    //existedCustomer = getCustomerByCustCode(customer.getCustCode());
    if(getCustomerByCustCode(customer.getCustCode()) == null)
    {
      //throw new RuntimeException("customer does not exist for custCode: " + customer.getCustCode());
        throw new ServiceException("customer.error" ,new Object[] {customer.getCustCode()}, null);
    }
    if(customer.getAccountOwnerName() == null || (customer.getAccountOwnerName().trim().equals("")))
      customer.setAccountOwner(null);
    else
    {
      UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
      User accountOwner = userService.getUserByName(customer.getAccountOwnerName());
      if(accountOwner != null)
        customer.setAccountOwner(accountOwner);
    }
    CreditAnalyst exitedCa = null;

    if(customer.getCreditAnalystName() != null && (!customer.getCreditAnalystName().trim().equals("")))
    {
      exitedCa = getCreditAnalystByCACode(customer.getCreditAnalystName());
      if( exitedCa == null)
      {

       // throw new RuntimeException("Creditanalyst  does not exist: " + customer.getCreditAnalystName());
        throw new ServiceException("creditanalyst.error",new Object[] {customer.getCreditAnalystName()});
      }
      customer.setCreditAnalystName(exitedCa.getCreditAnalystCode());
   }else
      customer.setCreditAnalystName(Constants.DEFAULT_CREDITANALYST_CODE);

    Collector exitedCollector = null;
    if(customer.getCollectorName() != null && (!customer.getCollectorName().trim().equals("")))
    {
      exitedCollector = getCollectorByCode(customer.getCollectorName());

      if( exitedCollector == null)
      {
        //throw new RuntimeException("Collector does not exist: " + customer.getCollectorName());
        throw new ServiceException("collector.error",new Object[] {customer.getCollectorName()});
      }
      customer.setCollectorName(exitedCollector.getCollectorCode());
    }
    else
      customer.setCollectorName(Constants.DEFAULT_COLLECT_CODE);

   BusinessUnit exitedBu = null;
    if(customer.getInterunitBusUnitName() != null && (!customer.getInterunitBusUnitName().trim().equals("")))
    {
      UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
      exitedBu = userService.getBusinessUnitByName(customer.getInterunitBusUnitName());
       if( exitedBu == null)
      {
       // throw new RuntimeException("BusinessUnit does not  exist: " + customer.getInterunitBusUnitName());
         throw new ServiceException("businessUnit.error",new Object[] {customer.getInterunitBusUnitName()});
      }
    }

    String exitedParentCustomer = parentCustomerName;
    Customer exitedPc = null;
  if(exitedParentCustomer != null && (!exitedParentCustomer.trim().equals("")) && !customer.getLocationType().equals(Constants.HQRT))
    {
    exitedPc = getCustomerByName(exitedParentCustomer);

     if( exitedPc == null)
    {
     // throw new RuntimeException("Parent customer does not  exist: " + exitedParentCustomer);
       throw new ServiceException("parent.customer.error",new Object[] {exitedParentCustomer});
    }else
    {

      customer.setParentCustCode(exitedPc.getCustCode());
      customer.setParentCustomer(exitedPc);
    }

    }


    customer.setInterunitBusUnit(exitedBu);
    customer.setCreditAnalyst(exitedCa);
    customer.setCollector(exitedCollector);


  if(customer.getLocationType().equals(Constants.HQRT))
  {
  customer.setParentCustCode(customer.getCustCode());
    customer.setParentCustomer(customer);
  }
  else
  {
    if(exitedPc != null)
    {
      customer.setParentCustCode(exitedPc.getCustCode());
      customer.setParentCustomer(exitedPc);
       // customer.setName(existedCustomer.getName());
      if(customer.getCustCode().equalsIgnoreCase(customer.getParentCustCode()))
      {
        customer.getParentCustomer().setName(customer.getName());
      }
    }
    else
    {
      customer.setParentCustCode("");
      customer.setParentCustomer(null);
    }
  }

   if(customer.getCustAddrSeqs() != null)
   {
   Iterator it = customer.getCustAddrSeqs().iterator();
    while(it.hasNext())
    {
      CustAddrSeq custAddrSeq = (CustAddrSeq) it.next();
      custAddrSeq.getCustAddrSeqId().setCustCode(customer.getCustCode());

      Iterator iter = custAddrSeq.getCustAddresses().iterator();
      while(iter.hasNext())
      {
        CustAddress custAddress = (CustAddress) iter.next();
        custAddress.setCustCode(customer.getCustCode());
        custAddress.setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());
        /*if(custAddress.getTaxCode() != null && (!custAddress.getTaxCode().trim().equals("")))
        {
          TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
          TaxRate taxRate = taxService.getTaxRateByCode(custAddress.getTaxCode());
          if(taxRate == null)
            throw new RuntimeException("Tax Rate does not exist for tax code : "+custAddress.getTaxCode());
          else
          {
            custAddress.setTaxCode(taxRate.getTaxCode());
          }
        }
        else
          custAddress.setTaxRate(null);*/
        custAddress.setCustAddrSeq(custAddrSeq);

      }
      custAddrSeq.setCustomer(customer);
    }
   }


   Set<ContactCust> existedContactCusts =  new HashSet<ContactCust>();
   existedContactCusts = getCustomerByCustCode(customer.getCustCode()).getContactCusts();
   Set<ContactCust> updatedContactCusts = customer.getContactCusts();

   if(customer.getContactCusts() != null)
   {

  List updatedContactCustIds = new ArrayList();
  List existedContactCustIds = new ArrayList();
  for(ContactCust updatedContactCust : customer.getContactCusts()){
    updatedContactCustIds.add(updatedContactCust.getContactCustId());
  }
    Iterator existedContactCustIt = existedContactCusts.iterator();

    //removing deleted ContactCust which for example by changing the location number it needs to be removed Since the location number is part of the primary key
    while(existedContactCustIt.hasNext()){
      ContactCust existedContactCust = (ContactCust)existedContactCustIt.next();
      if(!updatedContactCustIds.contains(existedContactCust.getContactCustId())){
        existedContactCust.getContact().getContactCusts().remove(existedContactCust);
        existedContactCust.getCustAddrSeq().getContactCusts().remove(existedContactCust);
      }
    }

    for(ContactCust contactCust : updatedContactCusts)
    {
        contactCust.getContactCustId().setCustCode(customer.getCustCode());
        contactCust.setCustomer(customer);
        Contact contact = contactCust.getContact();
        CustAddrSeq custAddrSeq = contactCust.getCustAddrSeq();
        if(contact != null)
        {
          //contact = getContactById(contact.getId());
          if(getContactById(contact.getId()) == null)
             throw new ServiceException("contact.error" ,new Object[] {""}, null);
          contactCust.setContact(contact);
          contactCust.getContactCustId().setContactId(contact.getId());
        }

        if(custAddrSeq != null)
        {
          //custAddrSeq = getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(),customer.getCustCode());
          if(getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(),customer.getCustCode()) == null)
            throw new ServiceException("customer.addressseq.error" ,null, null);

          contactCust.setCustAddrSeq(custAddrSeq);
          //contactCust.getCustAddrSeq().getContactCusts().add(contactCust);
        }
       }
   }
   Session sess = (Session) getDao().openHibernateSession();
   sess.evict(customer);
    customer.setUpdateFlag(Constants.NEW);
    customer.setUpdateLimsFlag(Constants.NEW);
    getDao().save(customer);
  }

  public Customer addCustomer(Customer customer)
  {
    if(customer == null) return null;
    Customer existedCustomer = getCustomerByName(customer.getName());
    if(existedCustomer != null)
    {
       throw new ServiceException("customer.exist.error" ,new Object[] {customer.getName()}, null);
     // throw new RuntimeException("A Customer exists for name:" + customer.getName());
    }



  CreditAnalyst exitedCa = null;

    if(customer.getCreditAnalystName() != null && (!customer.getCreditAnalystName().trim().equals("")))
    {
    exitedCa = getCreditAnalystByCACode(customer.getCreditAnalystName());

    if( exitedCa == null)
    {

     // throw new RuntimeException("Creditanalyst  does not exist: " + customer.getCreditAnalystName());
      throw new ServiceException("creditanalyst.error",new Object[] {customer.getCreditAnalystName()});
    }
    customer.setCreditAnalystName(exitedCa.getCreditAnalystCode());

  }
    else
      customer.setCreditAnalystName(Constants.DEFAULT_CREDITANALYST_CODE);


   Collector exitedCollector = null;
    if(customer.getCollectorName() != null && (!customer.getCollectorName().trim().equals("")))
    {
    exitedCollector = getCollectorByCode(customer.getCollectorName());

    if( exitedCollector == null)
    {
     // throw new RuntimeException("Collector does not exist: " + customer.getCollectorName());
      throw new ServiceException("collector.error",new Object[] {customer.getCollectorName()});
    }
    customer.setCollectorName(exitedCollector.getCollectorCode());
  }
    else
      customer.setCollectorName(Constants.DEFAULT_COLLECT_CODE);


   BusinessUnit exitedBu = null;
    if(customer.getInterunitBusUnitName() != null && (!customer.getInterunitBusUnitName().trim().equals("")))
    {
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    exitedBu = userService.getBusinessUnitByName(customer.getInterunitBusUnitName());
     if( exitedBu == null)
    {
     // throw new RuntimeException("BusinessUnit does not  exist: " + customer.getInterunitBusUnitName());
      throw new ServiceException("businessUnit.error",new Object[] {customer.getInterunitBusUnitName()});
    }
    }

    String exitedParentCustomer = customer.getParentCustomer().getName();
  Customer exitedPc = null;

    if(exitedParentCustomer != null && (!exitedParentCustomer.trim().equals("")))
    {
      exitedPc = getCustomerByName(exitedParentCustomer);
     if( exitedPc == null)
    {
      //throw new RuntimeException("Parent customer does not  exist: " + exitedParentCustomer);
      throw new ServiceException("parent.customer.error",new Object[] {exitedParentCustomer});
      }else
      {
        customer.setParentCustCode(exitedPc.getCustCode());
    }

    }
  //else if(!customer.getLocationType().equals("Hqrt"))
  /*else if(!customer.getLocationType().equals(Constants.HQRT))
    {
      throw new RuntimeException("Parent customer is required for customer : "+customer.getName());
    }*/

   User exitedAccountOwner = null;
    if(customer.getAccountOwnerName() != null && (!customer.getAccountOwnerName().trim().equals("")))
    {
      try {
        exitedAccountOwner = getAccountOwnerByLoginName(customer.getAccountOwnerName());
      } catch(ServiceException e)
      {
        throw new ServiceException("account.owner.error",new Object[] {customer.getAccountOwnerName()});
      }
   /* if( exitedAccountOwner == null)
    {
      //throw new RuntimeException("Account Owner does not exist: " + customer.getAccountOwnerName());
      throw new ServiceException("account.owner.error",new Object[] {customer.getAccountOwnerName()});
    }*/
  }


  customer.setInterunitBusUnit(exitedBu);
  customer.setAccountOwner(exitedAccountOwner);
  customer.setCreditAnalyst(exitedCa);
  customer.setCollector(exitedCollector);
  customer.setParentCustomer(exitedPc);

  if(customer.getLocationType().equals(Constants.HQRT))
  {
    //customer.setParentCustomer(customer);
    customer.setParentCustCode(customer.getCustCode());
  }
  else
  {
    if(exitedParentCustomer!=null && !exitedParentCustomer.trim().equals(""))
    {
    customer.setParentCustomer(exitedPc);
    }
    else
    {
        customer.setParentCustCode(customer.getCustCode());
      customer.setParentCustomer(customer);
    }
  }

   Iterator it = customer.getCustAddrSeqs().iterator();

    while(it.hasNext())
    {
      CustAddrSeq custAddrSeq = (CustAddrSeq) it.next();
      custAddrSeq.getCustAddrSeqId().setCustCode(customer.getCustCode());
      Iterator iter = custAddrSeq.getCustAddresses().iterator();
      while(iter.hasNext())
      {
        CustAddress custAddress = (CustAddress) iter.next();
        custAddress.setCustCode(customer.getCustCode());
        custAddress.setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());

       /* if(custAddress.getTaxCode() != null && (!custAddress.getTaxCode().trim().equals("")))
        {
          TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
          TaxRate taxRate = taxService.getTaxRateByCode(custAddress.getTaxCode());
          if(taxRate == null)
            throw new RuntimeException("Tax Rate does not exist for tax code : "+custAddress.getTaxCode());
          else
          {
            custAddress.setTaxCode(taxRate.getTaxCode());
          }
        }
        else
          custAddress.setTaxRate(null);*/
      }

    }

    customer.setNewFlag(Constants.NEW);
    customer.setUpdateFlag(Constants.NEW);
    customer.setUpdateLimsFlag(Constants.NEW);

  customer = getDao().save(customer);

   return customer;
  }

  public Customer addQuickCustomer(Customer customer)
  {
    if(customer == null) return null;
    Customer existedCustomer = getCustomerByName(customer.getName());
   Set contractCustSet = customer.getContractCusts();
/*
    if(existedCustomer != null)
    {
    customer=loadCustomerByCustCode(existedCustomer.getCustCode());

    customer.getContractCusts().clear();
    customer.setContractCusts(contractCustSet);
  //  customer = existedCustomer;
    //customer.setContractCusts(contractCustSet);
    customer= getDao().save(customer);
      System.out.println("After saving the existing customer & contract" + customer.getName());

    return customer;
    }
*/

    String ca= customer.getCreditAnalystName();
  CreditAnalyst exitedCa = null;

    if(ca != null && (!ca.trim().equals("")))
    {
    exitedCa = getCreditAnalystByCACode(ca);

    if( exitedCa == null)
    {
      throw new ServiceException("creditanalyst.error",new Object[] {ca});
    //  throw new RuntimeException("Creditanalyst  does not exist: " + ca);
    }

  }
    else
      customer.setCreditAnalystName(Constants.DEFAULT_CREDITANALYST_CODE);

   String collector= customer.getCollectorName();
   Collector exitedCollector = null;
    if(collector != null && (!collector.trim().equals("")))
    {
    exitedCollector = getCollectorByCode(collector);

    if( exitedCollector == null)
    {
     // throw new RuntimeException("Collector does not exist: " + collector);
      throw new ServiceException("collector.error",new Object[] {collector});
    }
  }
    else
      customer.setCollectorName(Constants.DEFAULT_COLLECT_CODE);

   String  interBu= customer.getInterunitBusUnitName();
   BusinessUnit exitedBu = null;
    if(interBu != null && (!interBu.trim().equals("")))
    {
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    exitedBu = userService.getBusinessUnitByName(interBu);
     if( exitedBu == null)
    {
     // throw new RuntimeException("BusinessUnit does not  exist: " + interBu);
       throw new ServiceException("businessUnit.error",new Object[] {interBu});
    }
    }

 /* String exitedParentCustomer = customer.getParentCustCode();
  Customer exitedPc = null;

    if(exitedParentCustomer != null && (!exitedParentCustomer.trim().equals("")))
    {
    exitedPc = getCustomerByCustCode(exitedParentCustomer);
     if( exitedPc == null)
    {
      throw new RuntimeException("Parent customer does not  exist: " + exitedParentCustomer);
    }

    }
  else if(!customer.getLocationType().equals(Constants.HQRT))
    {
      throw new RuntimeException("Parent customer is required for customer : "+customer.getName());
    }*/

   String exitedParentCustomer = customer.getParentCustomer().getName();
  Customer exitedPc = null;
    if( exitedParentCustomer != null && (!exitedParentCustomer.trim().equals(""))  && !customer.getName().equalsIgnoreCase(exitedParentCustomer))
    {
      exitedPc = getCustomerByName(exitedParentCustomer);
     if( exitedPc == null)
      {
    //  throw new RuntimeException("Parent customer does not  exist: " + exitedParentCustomer);
      throw new ServiceException("parent.customer.error",new Object[] {exitedParentCustomer});
      }
        else
        {
        customer.setParentCustCode(exitedPc.getCustCode());
        }
     }
    /* else if(!customer.getLocationType().equals(Constants.HQRT))
    {
      throw new RuntimeException("Parent customer is required for customer : "+customer.getName());
    }*/


   String accountOwner= customer.getAccountOwnerName();
   User exitedAccountOwner = null;
    if(accountOwner != null && (!accountOwner.trim().equals("")))
    {
      try {
            exitedAccountOwner = getAccountOwnerByLoginName(accountOwner);
          } catch(ServiceException e)
          {
            throw new ServiceException("account.owner.error",new Object[] {customer.getAccountOwnerName()});
          }
   /* exitedAccountOwner = getAccountOwnerByLoginName(accountOwner);


    if( exitedAccountOwner == null)
    {
     // throw new RuntimeException("Account Owner does not exist: " + accountOwner);
      throw new ServiceException("account.owner.error",new Object[] {accountOwner});
    }*/
   }
  customer.setInterunitBusUnit(exitedBu);
  customer.setAccountOwner(exitedAccountOwner);
  customer.setCreditAnalyst(exitedCa);
  customer.setCollector(exitedCollector);

  if(customer.getLocationType().equals(Constants.HQRT))
  {
    //customer.setParentCustomer(customer);
    customer.setParentCustCode(customer.getCustCode());
  customer.setParentCustomer(customer);
  }
  else
  {
    if(exitedParentCustomer!=null && !exitedParentCustomer.trim().equals("")&& !customer.getName().equalsIgnoreCase(exitedParentCustomer))
    {
          customer.setParentCustomer(exitedPc);
    }
    else
    {
        customer.setParentCustCode(customer.getCustCode());
      customer.setParentCustomer(customer);


    }

  }

   Iterator it = customer.getCustAddrSeqs().iterator();

    while(it.hasNext())
    {

      CustAddrSeq custAddrSeq = (CustAddrSeq) it.next();

      Iterator iter = custAddrSeq.getCustAddresses().iterator();
      while(iter.hasNext())
      {
        CustAddress custAddress = (CustAddress) iter.next();
        custAddress.setCustCode(customer.getCustCode());
        custAddress.setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());

        /*if(custAddress.getTaxCode() != null && (!custAddress.getTaxCode().trim().equals("")))
        {
          TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
          TaxRate taxRate = taxService.getTaxRateByCode(custAddress.getTaxCode());
          if(taxRate == null)
            throw new RuntimeException("Tax Rate does not exist for tax code : "+custAddress.getTaxCode());
          else
          {
            custAddress.setTaxRate(taxRate);
          }
        }
        else
          custAddress.setTaxRate(null);*/
      }

    }

    if(existedCustomer == null)
    {
      customer.setNewFlag(Constants.NEW);
    }
    customer.setUpdateFlag(Constants.NEW);
    customer.setUpdateLimsFlag(Constants.NEW);
    // Setting customer created by and created time values for new customer.

    Customer existCustomer = getCustomerByCustCode(customer.getCustCode());

    if(existCustomer == null && (customer.getCreByUserName()== null || customer.getCreByUserName().trim().equals(""))){
      System.out.println("Quick create existCustomer="+existCustomer);
      customer.setCreByUserName(SecurityUtil.getUser().getLoginName());
    }
    if(existCustomer == null && customer.getCreatedTime()== null){
      System.out.println("Quick create existCustomer="+existCustomer);
    customer.setCreatedTime(new Date());
    }
    //end
    customer = getDao().save(customer);

   return customer;
  }


  private CreditAnalyst getCreditAnalystByName(String name)
  {
  String searchName = name.toUpperCase();
   List creditAnalyst = getDao().find(
      "from CreditAnalyst c where c.crAnalystName = ?", new Object[] { searchName } );
   if(creditAnalyst.size() > 0) {
     return (CreditAnalyst)creditAnalyst.get(0);
   }
    return null;
  }

  public List listCreditAnalystByCode(String code)
  {
  String searchName =  code.toUpperCase() + "%";
   List creditAnalyst = getDao().find(
      "from CreditAnalyst c where upper(c.creditAnalystCode) like ?", new Object[] { searchName } );
   if(creditAnalyst.size() > 0) {
     return creditAnalyst;
   }
    return null;
  }


  private Collector getCollectorByName(String name) {
  String searchName = name.toUpperCase();
   List collector = getDao().find(
      "from Collector c where upper(c.collectorName) = ? ", new Object[] { searchName } );
   if(collector.size() > 0)
    {
     return (Collector)collector.get(0);
    }

    return null;

 }

  public List listCollectorByCode(String code)
  {
  String searchName =  code.toUpperCase() + "%";
   List collector = getDao().find(
      "from Collector c where upper(c.collectorCode) like ? ", new Object[] { searchName } );
   if(collector.size() > 0) {
     return collector;
   }
    return null;
  }


  public void searchCustomer(CustomerSearch search,String sortFlag)
  {
    if(search == null) return;
  StringBuffer sb = new StringBuffer();
    List params = new ArrayList();
  boolean hasWhere = false;
     String customerId= search.getCustomerId().getValue();
  int customerVal=search.getCustomerId().getOperator();
  String billToFlag = search.getBillToFlag();
  String soldToFlag = search.getSoldToFlag();
  String shipToFlag = search.getShipToFlag();

    if((customerId!=null)&&(customerVal==Constants.CONTAINS))
     {
      String cId='%'+String.valueOf(customerId)+'%';
    //  sb.append(" where str(c.id) like ?");
// 96509 start
    sb.append(" where upper(c.custCode) like ?");
      params.add(cId.toUpperCase());
      hasWhere = true;
       }
             else if((customerId!=null)&&(customerVal==Constants.BEGINS_WITH))
                  {
                    String cId=String.valueOf(customerId)+'%';
                    sb.append(" where upper(c.custCode) like ?");
                    params.add(cId.toUpperCase());
                    hasWhere = true;
                  }
                      else if((customerId!=null)&&(customerVal==Constants.EQUALS))
                        {
                        sb.append(" where upper(c.custCode) = ?");
                          params.add(customerId.toUpperCase());
                          hasWhere = true;
                        }
                               else if((customerId!=null)&&(customerVal==Constants.NOT_EQUALS))
                              {
                                  sb.append(" where upper(c.custCode) !=?");
                                  params.add(customerId.toUpperCase());
// 96509 end                               
   hasWhere = true;
                              }

    String customerName = search.getCustomerName().getValue();
   int customerValue=search.getCustomerName().getOperator();
   if((customerName != null) && !"".equals(customerName.trim()))
    {
      if(hasWhere)
        sb.append(" and ");
      else
       {
        hasWhere = true;
        sb.append(" where ");
       }
      if((customerName!=null)&&(customerValue==Constants.CONTAINS))
        {
         String cName='%'+customerName.toUpperCase()+'%';
         sb.append(" upper(c.name) like ? ");
         params.add(cName);
        }
      if((customerName!=null)&&(customerValue==Constants.BEGINS_WITH))
          {
           String cName=customerName.toUpperCase()+'%';
           sb.append(" upper(c.name) like ? ");
           params.add(cName);
          }
        if((customerName!=null)&&(customerValue==Constants.EQUALS))
        {
          String cName=customerName.toUpperCase();
          sb.append(" upper(c.name)  = ? ");
          params.add(cName);
        }
       if((customerName!=null)&&(customerValue==Constants.NOT_EQUALS))
       {
         String cName=customerName.toUpperCase();
         sb.append(" upper(c.name) !=? ");
         params.add(cName);
       }
    }

   // Added alternateName search for Issue # 103441 on 24-04-2009 START
   String alternateName = search.getAlternateName().getValue();
   int alternateNameValue=search.getAlternateName().getOperator();
   if((alternateName != null) && !"".equals(alternateName.trim())){
      if(hasWhere)
    	  sb.append(" and ");
      else {
    	  hasWhere = true;
    	  sb.append(" where ");
      }
      if((alternateName!=null)&&(alternateNameValue==Constants.CONTAINS)){
    	  String aName='%'+alternateName.toUpperCase()+'%';
    	  sb.append(" upper(c.alternateName) like ? ");
    	  params.add(aName);
      }
      if((alternateName!=null)&&(alternateNameValue==Constants.BEGINS_WITH)){
          String aName=alternateName.toUpperCase()+'%';
          sb.append(" upper(c.alternateName) like ? ");
          params.add(aName);
      }
      if((alternateName!=null)&&(alternateNameValue==Constants.EQUALS)){
          String aName=alternateName.toUpperCase();
          sb.append(" upper(c.alternateName)  = ? ");
          params.add(aName);
      }
      if((alternateName!=null)&&(alternateNameValue==Constants.NOT_EQUALS)){
    	  String aName=alternateName.toUpperCase();
    	  sb.append(" upper(c.alternateName) !=? ");
    	  params.add(aName);
      }
   }
    // Added alternateName search for Issue # 103441 on 24-04-2009 END
   if((billToFlag != null) && !"".equals(billToFlag.trim()))
   {
      if(hasWhere)
        sb.append(" and ");
      else
       {
        hasWhere = true;
        sb.append(" where ");
       }
        sb.append(" c.billTo = ?");
        params.add(billToFlag);
   }
   if((soldToFlag != null) && !"".equals(soldToFlag.trim()))
   {
      if(hasWhere)
        sb.append(" and ");
      else
       {
        hasWhere = true;
        sb.append(" where ");
       }
        sb.append(" c.soldTo = ?");
        params.add(soldToFlag);
   }
   if((shipToFlag != null) && !"".equals(shipToFlag.trim()))
   {
      if(hasWhere)
        sb.append(" and ");
      else
       {
        hasWhere = true;
        sb.append(" where ");
       }
        sb.append(" c.shipTo = ?");
        params.add(shipToFlag);
   }
   
// START 96509 
   
   String address1 = search.getAddress1().getValue();
   if(null != address1 && !"".equals(address1.trim())){
	 String strAddress1 = '%' + address1.trim().toUpperCase() + '%';
	   	if(hasWhere) sb.append(" and ");
	    else
        {
          hasWhere = true;
          sb.append(" where ");
        }
        sb.append(" upper(ca.address1) like ? ");
        params.add(strAddress1);
   }
   /* commented by sreeram due to not require */
  /* CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
   String strCountryCode  = search.getCountry().getValue();
   String strStateCode = search.getState().getValue();
   String strCity = search.getCity().getValue();*/
   //End
   String strCountryCode  = search.getCountry().getValue();
   if(strCountryCode!= null && (!strCountryCode.trim().equals(""))){	    
   /* commented by sreeram due to not require */
   //Country country = countryService.getCountryByCode(strCountryCode);
   //if(country != null)
   //strCountryCode = country.getCountryCode();
   //END
     if(hasWhere) sb.append(" and ");
     else
     {
         hasWhere = true;
         sb.append(" where ");
     }
   sb.append(" ca.country = ?");
   params.add(strCountryCode);
   //   hasWhere = true;
 }
 String strStateCode = search.getState().getValue();
 if(strStateCode != null && (!strStateCode.trim().equals(""))){
	 /* commented by sreeram due to not require */
     /*State state = countryService.getStateByCode(strStateCode, strCountryCode);
     if(state!= null)
       strStateCode = state.getStateId().getStateCode();*/
	 //End
     if(hasWhere) sb.append(" and ");
     else {
       hasWhere = true;
       sb.append(" where ");
     }
     sb.append(" ca.state = ?");
     params.add(strStateCode);
    // hasWhere = true;
 }
 String strCity = search.getCity().getValue();
 if((strCity != null) && !"".equals(strCity.trim())){

     if(hasWhere)
       sb.append(" and ");
     else
     {
    	 hasWhere = true;
         sb.append(" where ");
     }
     String ct = strCity +'%';
     sb.append(" upper(ca.city) like ? ");
     params.add(ct.trim().toUpperCase());
 }
// END 96509 
  Pagination pagination = search.getPagination();
    List results = null;
 if(sortFlag != null && sortFlag.equals(""))
 {
   if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find(
// 96509
         /* Ketan - commented - START */		
         // "select count(ca.id) from Customer c, CustAddrSeq cs, CustAddress ca  " + sb.toString(),
         // params.toArray()
        //);
        /* Ketan - commented - END */
        /* Ketan - added - START */
        "select count(ca.id) from Customer c, CustAddrSeq cs, CustAddress ca " +  
        sb.toString() +
        " and c.custCode=cs.customer.custCode" +
        " and cs.customer.custCode=ca.custAddrSeq.customer.custCode " +
        " and cs.custAddrSeqId.locationNumber=ca.locationNumber",
        params.toArray()
        );
        /* Ketan - added - END */
        
        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
// 96509 start
/*      results = getDao().find(
        "select c from Customer c left join fetch c.custAddrSeqs  left join fetch c.custAddrSeqs.custAddresses " + sb.toString(),
        params.toArray(),
        pagination
      );
*/
/*      sb.append("and cs.custCode = ca.custCode");
      results = getDao().find(
    	        "select distinct c from Customer c inner join fetch c.custAddrSeqs  cs on c.custCode = cs.custCode inner join fetch c.custAddrSeqs.custAddresses ca on cs.locationNumber = ca.locationNumber " + sb.toString(),
    	        params.toArray(),
    	        pagination
    	      );
*/   
      
      
      
      
   /*   results = getDao().find(
    	        "select c from Customer c left join fetch c.custAddrSeqs cs left join fetch cu.custAddress ca where c.custCode=cs.customer.custCode and ca.locationNumber=cs.custAddresses.locationNumber and cs.customer.custCode=ca.custAddrSeq.customer.custCode" +  sb.toString(),
    	        params.toArray(),
    	        pagination
    	      );
      
      
      
      results = getDao().find(
  	        "select c from Customer c, CustAddrSeq cs, CustAddress ca where c.custCode=cs.customer.custCode and ca.locationNumber=cs.custAddresses.locationNumber and cs.customer.custCode=ca.custAddrSeq.customer.custCode and " +  sb.toString(),
  	        params.toArray(),
  	        pagination
  	      );
 */    
      
     results = getDao().find(
    	      "select ca from Customer c, CustAddrSeq cs, CustAddress ca " +  sb.toString()+" and c.custCode=cs.customer.custCode and ca.locationNumber=cs.custAddrSeqId.locationNumber and cs.customer.custCode=ca.custAddrSeq.customer.custCode",
    	        params.toArray(),
    	        pagination
    	      );
      pagination.calculatePages();
    }
    else
    {
    	  results = getDao().find(
    			  "select ca from Customer c, CustAddrSeq cs, CustAddress ca " +  sb.toString()+" and c.custCode=cs.customer.custCode and ca.locationNumber=cs.custAddrSeqId.locationNumber and cs.customer.custCode=ca.custAddrSeq.customer.custCode",
      	        params.toArray()
    	  );
    	
    	// 96509 end
    }
    search.setResults(results);
  }
  else
  {
   // Itrack issue 96509 code starts here
   // String orderByValue=" order by ca."+sortFlag;
    String orderByValue ="";
	if(sortFlag != null && sortFlag.equals("id")){
		orderByValue=" order by ca.custCode";
    } else if(sortFlag != null && sortFlag.equals("name")){
    	orderByValue=" order by ca.custAddrSeq.customer.name";
    } else if(sortFlag != null && sortFlag.equals("alternateName")){
    	orderByValue=" order by ca.custAddrSeq.customer.alternateName";
    } else if(sortFlag != null && sortFlag.equals("country")){
    	orderByValue=" order by ca.country";
    } else if(sortFlag != null && sortFlag.equals("state")){
    	orderByValue=" order by ca.state";
    } else if(sortFlag != null && sortFlag.equals("city")){
    	orderByValue=" order by ca.city";
    } else if(sortFlag != null && sortFlag.equals("status")){
    	orderByValue=" order by ca.custAddrSeq.customer.status";
    } else if(sortFlag != null && sortFlag.equals("address1")){
    	orderByValue=" order by ca.address1";
    }
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
      List counts = getDao().find(

        /* KETAN - commented - START */		
          //"select count(ca.id) from Customer c, CustAddrSeq cs, CustAddress ca " + sb.toString(),
          //params.toArray()
        //);
        /* KETAN - commented - END */
        /* Ketan - added - START */
	    "select count(ca.id) from Customer c, CustAddrSeq cs, CustAddress ca " +  
        sb.toString() +
        " and c.custCode=cs.customer.custCode" +
        " and cs.customer.custCode=ca.custAddrSeq.customer.custCode " +
        " and cs.custAddrSeqId.locationNumber=ca.locationNumber",
        params.toArray()
        );
        /* Ketan - added - END */	      
        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      pagination.calculatePages();
    }
    results = getDao().find(
    		"select ca from Customer c, CustAddrSeq cs, CustAddress ca " +  sb.toString()+" and c.custCode=cs.customer.custCode and ca.locationNumber=cs.custAddrSeqId.locationNumber and cs.customer.custCode=ca.custAddrSeq.customer.custCode"+orderByValue,
	        params.toArray(),pagination);
   // End
    search.setResults(results);
  }
  }
public List  getAccountOwner(String accountOwner)
  {
  if(accountOwner==null) return new ArrayList();
  List accountowners=new ArrayList();
    try{

     accountowners=getDao().find(
  "from User u where upper(u.loginName) like'"+accountOwner.toUpperCase()+"%'",
       null);

    }
    catch (Exception e)
    {

        }
      return accountowners;
  }
/*For ITrack#135159-START */
public List<User>  getAccountOwnerRoleUsers(String accountOwner)
{
if(accountOwner==null) return new ArrayList();
List accountOwnerRole = new ArrayList();
  try {
	  List accountowners  = getDao().find(
		   "from User u where upper(u.loginName) like'"+accountOwner.toUpperCase()+"%'",
     null);
     for(int i=0;i<accountowners.size();i++){
  	 User user = (User)accountowners.get(i);
  	 Set userRoles = user.getRoles();
  	 for(Iterator itr= userRoles.iterator();itr.hasNext();){
  		 Role role = (Role)itr.next();
  		 if(role.getName().trim().equals(Constants.AccountOwnerRole)){
  			accountOwnerRole.add(user);
  		 }
  	 }
   }
  }
  catch (Exception e)
  {}
  return accountOwnerRole;
}

/*For ITrack#135159-END */
public User  getAccountOwnerByLoginName(String accountOwner)
  {
  if(accountOwner==null) return null;
  List accountowners=new ArrayList();
   // try{

     accountowners=getDao().find(
  "from User u where upper(u.loginName) = '"+accountOwner.toUpperCase()+"'",
       null);

  /*  }
    catch (Exception e)
    {

        }*/
     if(accountowners != null && accountowners.size() > 0) return (User) accountowners.get(0);
     else
     throw new ServiceException("account.owner.error",new Object[] {accountOwner});

   /* if(accountowners.size() > 0) return (User) accountowners.get(0);

    return null;*/
  }

public List getCustomersByCustCode(String custCode)
{
    if(custCode == null) return new ArrayList();
  List customer = new ArrayList();
try{
    customer = getDao().find(
      "from Customer c where upper(c.custCode) like '" + custCode.toUpperCase() + "%'",
      null
    );
}
catch(Exception e ){

}
return customer;
}
public List getParentCustomersByName(String name)
{
    if(name == null) return new ArrayList();
  List customer = new ArrayList();
try{
    customer = getDao().find(
      "from Customer c where upper(c.name) like '" + name.toUpperCase() + "%' and c.locationType = '04'",
      null
    );
}

catch(Exception e ){

}
return customer;
}

public List getParentCustomersByCustCode(String custCode)
{
    if(custCode == null) return new ArrayList();
  List customer = new ArrayList();
try{
    customer = getDao().find(
      "from Customer c where upper(c.custCode) like '" + custCode.toUpperCase() + "%' and c.locationType = '04'",
      null
    );
}
catch(Exception e ){

}
return customer;
}



public List getCustomerAddressesByAddrSeqNumber(String custCode,String addrSeqNumber)
{
    if(custCode == null) return new ArrayList();
  List customerAddresses = new ArrayList();

 // String queryStr = "from CustAddrSeq cas where cas.custAddrSeqId.custCode = '" + custCode + "'";

  //change query regd the contacts should not be allowed to be associated to an inactive address
  String queryStr = "from CustAddrSeq cas left join fetch cas.custAddresses cd where cas.custAddrSeqId.custCode = '" + custCode + "' and upper(cd.effStatus) <> '"+Constants.STATUS_I +"'";
  if(addrSeqNumber != null)
    queryStr = queryStr + "and cas.custAddrSeqId.locationNumber = "+addrSeqNumber;
  try{
    customerAddresses = getDao().find(
      queryStr ,
      null
    );
}
catch(Exception e ){

}
return customerAddresses;
}

public CustAddrSeq getCustAddrSeqByLocationNumber(Integer locationNumber, String custCode)
{
    List custAddrSeqs = getDao().find(
      "from CustAddrSeq cas where cas.custAddrSeqId.locationNumber = ? and cas.custAddrSeqId.custCode = ?",
      new Object[] { locationNumber, custCode}
    );

    if(custAddrSeqs.size() > 0) return (CustAddrSeq)custAddrSeqs.get(0);

    return null;

}

public List getCreditAnalystByCode(String creditAnalystCode)
{
    if(creditAnalystCode == null) return new ArrayList();
  List creditAnalyst = new ArrayList();
try{
    creditAnalyst = getDao().find(
      "from CreditAnalyst c where upper(c.creditAnalystCode) like '" + creditAnalystCode.toUpperCase() + "%'",
      null
    );
}
catch(Exception e ){

}
return creditAnalyst;
}

public CreditAnalyst getCreditAnalystByCACode(String creditAnalystCode)
{
    if(creditAnalystCode == null) return null;
  List creditAnalyst = new ArrayList();
//try{
    creditAnalyst = getDao().find(
      "from CreditAnalyst c where upper(c.creditAnalystCode) = '" + creditAnalystCode.toUpperCase() + "'",
      null
    );
 /* }
catch(Exception e ){

}*/
if(creditAnalyst.size() > 0)
  return (CreditAnalyst) creditAnalyst.get(0);
else
  return null;
}

public List getCollectorNameByCode(String collectorCode)
{
    if(collectorCode == null) return new ArrayList();
  List collector = new ArrayList();
try{
    collector = getDao().find(
      "from Collector c where upper(c.collectorCode) like '" + collectorCode.toUpperCase() + "%'",
      null
    );
}
catch(Exception e ){

}
return collector;
}

public Collector getCollectorByCode(String collectorCode)
{
    if(collectorCode == null) return null;
  List collector = new ArrayList();
 // try{
    collector = getDao().find(
      "from Collector c where upper(c.collectorCode) = '" + collectorCode.toUpperCase() + "'",
      null
    );
/*}
catch(Exception e ){

}*/
if(collector.size() > 0)
  return (Collector) collector.get(0);
else
  return null;
}

public List getContactsById(String contactId)
{
    if(contactId == null) return new ArrayList();
  List contacts = new ArrayList();
try{
    contacts = getDao().find(
      " from Contact c where str(c.id) like '" + contactId.toUpperCase() + "%'",
      null
    );
}
catch(Exception e ){

}
return contacts;
}
public List getCustAddressDetailsBySeqNumber(Integer locationNumber, String custCode)
{
  List custAddresses = new ArrayList();
try{
    custAddresses = getDao().find(
      " from CustAddress c  where c.custAddrSeq.custAddrSeqId.locationNumber = ? and c.custAddrSeq.custAddrSeqId.custCode = ? ",
      new Object[] {locationNumber, custCode}
    );
}
catch(Exception e ){

}
return custAddresses;
}

public void deleteContactCustAssociations(String custCode)
{
  if(custCode == null) return ;
  List contactCustList = new ArrayList();
 // try{
    contactCustList = getDao().find(
      "from ContactCust c  left join fetch c.contact left join fetch c.customer where upper(c.contactCustId.custCode) = '" + custCode + "'  ",
      null
    );
 /*}
 catch(Exception e ){

 }*/
  if(contactCustList != null && contactCustList.size() > 0)
  {
  for(int i=0;i<contactCustList.size();i++)
  {
    ContactCust contactCust = (ContactCust) contactCustList.get(i);
      contactCust.getContact().getContactCusts().remove(contactCust);
      contactCust.getCustomer().getContactCusts().remove(contactCust);
  }
  }/*else
    throw new ServiceException("delete.contactcust.associations");*/

  return;
}



public void searchAddressSequence(AddressSeqSearch search,String sortFlag)
{
if(search == null) return;

StringBuffer sb = new StringBuffer();
List params = new ArrayList();

boolean hasWhere = false;

String strlocationNumberSearch = "";
String straddressDescSearch = "";
String strcustcodeSearch="";


String ln = search.getLocationNumber().getValue();
String cc=   search.getCustCode();
strlocationNumberSearch = '%'+ String.valueOf(ln) + '%';
if((ln != null)&&(cc!=null))
{
sb.append(" where str(c.custAddrSeqId.locationNumber) like ? and c.custAddrSeqId.custCode = ?");

params.add(strlocationNumberSearch);
params.add(cc);
hasWhere = true;
}

String addressDesc = search.getAddressDescr().getValue();

if((addressDesc != null) && !"".equals(addressDesc.trim()) &&(cc!=null))
{
if(hasWhere) sb.append(" and ");
else
{
hasWhere = true;
sb.append(" where ");
}
if(addressDesc!=null)
{
straddressDescSearch ='%'+ addressDesc.toUpperCase() + '%';

sb.append(" upper(c.addressDescr) like  ?  and c.custAddrSeqId.custCode = ? ");
params.add(straddressDescSearch);
params.add(cc);
}
}

else  {
  if(hasWhere) sb.append(" and ");
    else
  {
  hasWhere = true;
  sb.append(" where ");
  }

    sb.append("c.custAddrSeqId.custCode = ?");
   params.add(cc);
  hasWhere = true;
  }


Pagination pagination = search.getPagination();
List results = null;
List custAddrResults = new ArrayList();
if(sortFlag != null && sortFlag.equals(""))
{
if(pagination != null)
{
if(pagination.getTotalRecord() > 0)
{
List counts = getDao().find("select count(*) from  CustAddrSeq c  " + sb.toString(),
params.toArray()
);

if(counts.size() > 0)
{
Number count = (Number)counts.get(0);
pagination.setTotalRecord(count.intValue());
}
}
/*results = getDao().find(
"select c from  CustAddrSeq c "+sb.toString()+" order by c.custAddrSeqId.locationNumber",
params.toArray(),
pagination
);*/

/*results = getDao().find(
      "select c from  CustAddrSeq c left join fetch c.custAddresses "+sb.toString()+" order by c.custAddrSeqId.locationNumber",
      params.toArray(),
      pagination
      );*/

//change query regd the contacts should not be allowed to be associated to an inactive address
results = getDao().find(
      "select c from  CustAddrSeq c left join fetch c.custAddresses cd "+sb.toString()+" and upper(cd.effStatus) <> '"+Constants.STATUS_I +"' order by c.custAddrSeqId.locationNumber",
      params.toArray(),
      pagination
      );

if(results != null && results.size()>0)
  custAddrResults = getConcatinatedCustAddressSeqWithComas(results);

pagination.calculatePages();
}
else
{
/*results = getDao().find(
"select c from custAddrSeq c left join fetch c.custAddrSeqId "+ sb.toString() ,
params.toArray()
);*/

//  change query regd the contacts should not be allowed to be associated to an inactive address
  results = getDao().find(
      "select c from custAddrSeq c left join fetch c.custAddrSeqId left join fetch c.custAddresses cd "+ sb.toString()+" and upper(cd.effStatus) <> '"+Constants.STATUS_I +"'" ,
      params.toArray()
      );
}
search.setResults(custAddrResults);
//search.setResults(results);
search.setPagination(pagination);
}else
{
  String orderByValue=" order by c."+sortFlag;
  if(pagination != null)
  {
  if(pagination.getTotalRecord() > 0)
  {
  List counts = getDao().find("select count(*) from  CustAddrSeq c  " + sb.toString(),
  params.toArray()
  );

  if(counts.size() > 0)
  {
  Number count = (Number)counts.get(0);
  pagination.setTotalRecord(count.intValue());
  }
  }
  pagination.calculatePages();
  }
 /* results = getDao().find(
      "select c from  CustAddrSeq c left join fetch c.custAddresses "+sb.toString()+orderByValue,
      params.toArray(),pagination
      );*/

//  change query regd the contacts should not be allowed to be associated to an inactive address
  results = getDao().find(
        "select c from  CustAddrSeq c left join fetch c.custAddresses cd "+sb.toString()+" and upper(cd.effStatus) <> '"+Constants.STATUS_I +"'"+orderByValue,
        params.toArray(),pagination
        );
  if(results != null && results.size()>0)
  custAddrResults = getConcatinatedCustAddressSeqWithComas(results);

 // search.setTotalResults(results);
 // search.setResults(results);
  search.setResults(custAddrResults);
}
}


public void searchCustomerId(CustomerSearch search,String sortFlag)
{
if(search == null) return;
StringBuffer sb = new StringBuffer();
List params = new ArrayList();
boolean hasWhere = false;
String customerId= search.getCustomerId().getValue();
int customerVal=search.getCustomerId().getOperator();
if(customerId!=null)
{
String cId='%'+String.valueOf(customerId)+'%';
sb.append("where c.custCode like ?");
params.add(cId);
hasWhere = true;
}
String customerName = search.getCustomerName().getValue();
if((customerName != null) && !"".equals(customerName.trim()))
{
if(hasWhere) sb.append(" and ");
else
{
hasWhere = true;
sb.append(" where ");
}
if(customerName!=null)
{
String cName='%'+customerName.toUpperCase()+'%';
sb.append(" upper(c.name) like ? ");
params.add(cName);
}
}
Pagination pagination = search.getPagination();
List results = null;
  if(sortFlag != null && sortFlag.equals(""))
  {
if(pagination != null)
{
if(pagination.getTotalRecord() > 0)
{

List counts = getDao().find("select count(c.custCode) from  Customer c  " + sb.toString(),
params.toArray()
);

if(counts.size() > 0)
{
Number count = (Number)counts.get(0);
pagination.setTotalRecord(count.intValue());
}
}
results = getDao().find(
"select c from  Customer c "+sb.toString()+" order by c.custCode",
params.toArray(),
pagination
);


pagination.calculatePages();
}
else
{
results = getDao().find(
"select c from Customer c  "+ sb.toString() ,
params.toArray()
);
}
search.setResults(results);
search.setPagination(pagination);
  }else
  {
    String orderByValue=" order by c."+sortFlag;
    if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find("select count(c.custCode) from  Customer c  " + sb.toString(),
      params.toArray()
      );

      if(counts.size() > 0)
      {
      Number count = (Number)counts.get(0);
      pagination.setTotalRecord(count.intValue());
      }
    }
    pagination.calculatePages();
  }
    results = getDao().find(
        "select c from  Customer c "+sb.toString()+orderByValue,
        params.toArray(),pagination
        );
   // search.setTotalResults(results);
    search.setResults(results);
  }
}

  public void searchCreditAnalyst(CreditAnalystSearch search,String sortFlag) {
    if (search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strNameSearch = "";
    String strCodeSearch = "";

    String name = search.getName().getValue();
    if ((name != null) && !"".equals(name.trim()))
      strNameSearch = '%' + name.toUpperCase() + '%';

    if ((name != null) && !"".equals(name.trim())) {
      sb.append(" where upper(cr.crAnalystName) like ?");
      params.add(strNameSearch);
      hasWhere = true;
    }

    String code = search.getCreditAnalyst().getValue();
    if ((code != null) && !"".equals(code.trim()))
      strCodeSearch = '%' + code.toUpperCase() + '%';

    if ((code != null) && !"".equals(code.trim())) {
      if (hasWhere)
        sb.append(" and ");
      else {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(cr.creditAnalystCode) like ? ");
      params.add(strCodeSearch);
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {
    if (pagination != null) {
      if (pagination.getTotalRecord() > 0) {
        List counts = getDao().find(
            "select count(cr.creditAnalystCode) from CreditAnalyst cr "
                + sb.toString(), params.toArray());

        if (counts.size() > 0) {
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      results = getDao().find(
          "select distinct cr from CreditAnalyst cr " + sb.toString()
              + " order by cr.creditAnalystCode",
          params.toArray(), pagination);

      pagination.calculatePages();
    } else {
      results = getDao()
          .find(
              "select distinct cr from CreditAnalyst cr "
                  + sb.toString(), params.toArray());
    }

    search.setResults(results);
    search.setPagination(pagination);
    }else
    {
    String orderByValue=" order by cr."+sortFlag;
    if (pagination != null) {
        if (pagination.getTotalRecord() > 0) {
          List counts = getDao().find(
              "select count(cr.creditAnalystCode) from CreditAnalyst cr "
                  + sb.toString(), params.toArray());

          if (counts.size() > 0) {
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        pagination.calculatePages();
      }

    results = getDao().find(
            "select distinct cr from CreditAnalyst cr " + sb.toString()
                + orderByValue,
            params.toArray(),pagination);
   // search.setTotalResults(results);
    search.setResults(results);
    }

  }
  public void searchCollector(CollectorSearch search,String sortFlag) {
    if (search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strNameSearch = "";
    String strCodeSearch = "";

    String name = search.getName().getValue();
    if ((name != null) && !"".equals(name.trim()))
      strNameSearch = '%' + name.toUpperCase() + '%';

    if ((name != null) && !"".equals(name.trim())) {
      sb.append(" where upper(cl.collectorName) like ?");
      params.add(strNameSearch);
      hasWhere = true;
    }

    String code = search.getCollector().getValue();
    if ((code != null) && !"".equals(code.trim()))
      strCodeSearch = '%' + code.toUpperCase() + '%';

    if ((code != null) && !"".equals(code.trim())) {
      if (hasWhere)
        sb.append(" and ");
      else {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(cl.collectorCode) like ? ");
      params.add(strCodeSearch);
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {

    if (pagination != null) {
      if (pagination.getTotalRecord() > 0) {
        List counts = getDao().find(
            "select count(cl.collectorCode) from Collector cl "
                + sb.toString(), params.toArray());

        if (counts.size() > 0) {
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      results = getDao().find(
          "select distinct cl from Collector cl " + sb.toString()
              + " order by cl.collectorCode", params.toArray(),
          pagination);

      pagination.calculatePages();
    } else {
      results = getDao().find(
          "select distinct cl from Collector cl " + sb.toString(),
          params.toArray());
    }

    search.setResults(results);
    search.setPagination(pagination);
    }else
    {
      String orderByValue=" order by cl."+sortFlag;
      if (pagination != null) {
          if (pagination.getTotalRecord() > 0) {
            List counts = getDao().find(
                "select count(cl.collectorCode) from Collector cl "
                    + sb.toString(), params.toArray());

            if (counts.size() > 0) {
              Number count = (Number) counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
          }
          pagination.calculatePages();
        }

      results = getDao().find(
                "select distinct cl from Collector cl " + sb.toString()
                    + orderByValue, params.toArray(),pagination
                );

   // search.setTotalResults(results);
      search.setResults(results);

    }
  }
  public void searchCustomerByName(CustomerSearch search,
      String parentCustomerSearchFlag,String sortFlag) {
    if (search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strNameSearch = "";
    String strCodeSearch = "";

    Pagination pagination = search.getPagination();
    List results = null;

    if (parentCustomerSearchFlag != null) {

      String name = search.getCustomerName().getValue();
      if ((name != null) && !"".equals(name.trim()))
        strNameSearch = name.toUpperCase() + '%';

      if ((name != null) && !"".equals(name.trim())) {
        sb.append(" and  upper(c.name) like ? ");
        params.add(strNameSearch);
        hasWhere = true;
      }

      String code = search.getCustomer().getValue();
      if ((code != null) && !"".equals(code.trim()))
        strCodeSearch = code.toUpperCase() + '%';

      if ((code != null) && !"".equals(code.trim())) {
        if (hasWhere)
          sb.append(" and ");
        else {
          hasWhere = true;
          sb.append(" and ");
        }

        sb.append(" upper(c.custCode) like ? ");
        params.add(strCodeSearch);
      }
      if(sortFlag != null && sortFlag.equals(""))
      {
      if (pagination != null) {
        if (pagination.getTotalRecord() > 0) {

          List counts = getDao().find(
              "select count(c.custCode) from Customer c where c.locationType = '04' "
                  + sb.toString(), params.toArray());

          if (counts.size() > 0) {
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        results = getDao().find(
            "select distinct c from Customer c where c.locationType = '04'"
                + sb.toString() + " order by c.custCode",
            params.toArray(), pagination);

        pagination.calculatePages();
      } else {
        results = getDao().find(
            "select distinct c from Customer c where c.locationType = '04'"
                + sb.toString(), params.toArray());
        }
        search.setResults(results);
        search.setPagination(pagination);
      }
      else
      {
          //lookup header sort code begin

          String orderByValue=" order by c."+sortFlag;
          if (pagination != null) {
              if (pagination.getTotalRecord() > 0) {

                List counts = getDao().find(
                    "select count(c.custCode) from Customer c where c.locationType = '04' "
                        + sb.toString(), params.toArray());

                if (counts.size() > 0) {
                  Number count = (Number) counts.get(0);
                  pagination.setTotalRecord(count.intValue());
                }
              }
              results = getDao().find(
                      "select distinct c from Customer c where c.locationType = '04'"
                          + sb.toString() + orderByValue,
                      params.toArray(),pagination);
              search.setResults(results);

              pagination.calculatePages();
            }
        /*  results = getDao().find(
                  "select distinct c from Customer c where c.locationType = '04'"
                      + sb.toString() + orderByValue,
                  params.toArray());
          search.setTotalResults(results);*/
        //lookup header sort code end
      }

    } else {

      String name = search.getCustomerName().getValue();

      String billToFlag = search.getBillToFlag();
      String soldToFlag = search.getSoldToFlag();
      String shipToFlag = search.getShipToFlag();

      if ((name != null) && !"".equals(name.trim()))
        strNameSearch = name.toUpperCase() + '%';

      if ((name != null) && !"".equals(name.trim())) {
        sb.append("where upper(c.name) like ? ");
        params.add(strNameSearch);
        hasWhere = true;
      }

      String code = search.getCustomer().getValue();

      if ((code != null) && !"".equals(code.trim()))
        strCodeSearch = code.toUpperCase() + '%';


      if ((code != null) && !"".equals(code.trim())) {
        if (hasWhere)
          sb.append(" and ");
        else {
          hasWhere = true;
          sb.append(" where ");
        }

        sb.append(" upper(c.custCode) like ? ");
        params.add(strCodeSearch);
      }
      if((billToFlag != null) && !"".equals(billToFlag.trim()))
      {
        if(hasWhere)
          sb.append(" and ");
        else
          {
          hasWhere = true;
          sb.append(" where ");
          }
           sb.append(" c.billTo = ?");
           params.add(billToFlag);
      }
      if((soldToFlag != null) && !"".equals(soldToFlag.trim()))
      {
        if(hasWhere)
          sb.append(" and ");
        else
          {
          hasWhere = true;
          sb.append(" where ");
          }
           sb.append(" c.soldTo = ?");
           params.add(soldToFlag);
      }
      if((shipToFlag != null) && !"".equals(shipToFlag.trim()))
      {
        if(hasWhere)
          sb.append(" and ");
        else
          {
          hasWhere = true;
          sb.append(" where ");
          }
           sb.append(" c.shipTo = ?");
           params.add(shipToFlag);
      }
      if(sortFlag != null && sortFlag.trim().equals(""))
      {
      if (pagination != null) {
        if (pagination.getTotalRecord() > 0) {

          List counts = getDao().find(
              "select count(c.custCode) from Customer c "
                  + sb.toString(), params.toArray());

          if (counts.size() > 0) {
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        results = getDao().find(
            "select distinct c from Customer c " + sb.toString()
                + " order by c.custCode", params.toArray(),
            pagination);

        pagination.calculatePages();
      } else {
        results = getDao().find(
            "select distinct c from Customer c " + sb.toString(),
            params.toArray());
      }

    search.setResults(results);
   // search.setPagination(pagination);
    }else
    {
       //lookup header sort code begin

      String orderByValue=" order by c."+sortFlag;
      if (pagination != null) {
          if (pagination.getTotalRecord() > 0) {

            List counts = getDao().find(
                "select count(c.custCode) from Customer c "
                    + sb.toString(), params.toArray());

            if (counts.size() > 0) {
              Number count = (Number) counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
          }
          pagination.calculatePages();

          results = getDao().find(
                  "select distinct c from Customer c " + sb.toString()
                      + orderByValue, params.toArray(),pagination
                  );
        search.setResults(results);

        }
      //lookup header sort code end
    }
    }
  }
  public void contactIDSearch(ContactSearch search,String sortFlag) throws Exception {

      if (search == null)
        return;

      StringBuffer sb = new StringBuffer();
      List params     = new ArrayList();

      boolean hasWhere = false;
      long id = 0;

      String strFirstNameSearch = "";
      String strLastNameSearch = "";
      String strIDSearch = "";
      String custCode = search.getCustCode();
      String contractCode = search.getContractCode();
      // checking existing customer
      Customer customer = null;
      if(custCode != null && !custCode.trim().equalsIgnoreCase("New"))
        customer = getCustomerByCustCode(custCode);
      if(customer == null )
        custCode = null;
      if(search.getShowall().getValue()!=null)
      {custCode=null;}
      //end
      String searchForm=search.getSearchForm();
      if ((custCode != null) && !"".equals(custCode.trim())) {
    	  //Contract->Customer->Contact enhancement issue 96937 changes starts
    	  if(("createJobsInspForm".equals(searchForm))||("editJobsInspForm".equals(searchForm))
    			  ||("createJobsMarineForm".equals(searchForm))||("editJobsMarineForm".equals(searchForm))
    			  ||("editJobsOutSourcingForm".equals(searchForm))||("createJobsOutSourcingForm".equals(searchForm))
    			  ||("createJobsAnalyticalServicesForm".equals(searchForm))||("editJobsAnalyticalServicesForm".equals(searchForm))){
    		 
		    	  if((contractCode != null) &&(!"".equals(contractCode.trim()))) {
			    	  sb.append(" left join c.contactCusts cc left join cc.custAddrSeq.custAddresses ads  where ccc.contractCustContactId.custCode = ? and ccc.contractCustContactId.contractCode = ? and cc.contactCustId.custCode = ? ");
			          params.add(custCode);
			          params.add(contractCode);
			          params.add(custCode);
			          hasWhere = true;
		    	  }
    	  } else {//Contract->Customer->Contact enhancement issue 96937 changes end
    		      sb.append(" left join c.contactCusts cc left join cc.custAddrSeq.custAddresses ads  where cc.contactCustId.custCode = ? ");
    	          params.add(custCode);
    	          hasWhere = true;
    	  }
      }
      
      String fname = search.getFirstName().getValue();
      if ((fname != null) && !"".equals(fname.trim()))
        strFirstNameSearch = fname.toUpperCase() + '%';

      if ((fname != null) && !"".equals(fname.trim())) {
        if(hasWhere)
        {
          sb.append(" and ");
        }else
        {
          hasWhere = true;
            sb.append(" where ");

        }
          sb.append("   upper(c.firstName) like ? ");
          params.add(strFirstNameSearch.toUpperCase());

      }

      String lname = search.getLastName().getValue();
      if ((lname != null) && !"".equals(lname.trim()))
        strLastNameSearch = lname.toUpperCase() + '%';

      if ((lname != null) && !"".equals(lname.trim())) {
        if(hasWhere)
        {
          sb.append(" and ");
        }else
        {
          hasWhere = true;
            sb.append(" where ");

        }
         sb.append("  upper(c.lastName) like ? ");
         params.add(strLastNameSearch.toUpperCase());
      }

      strIDSearch = search.getContactIds().getValue();
      if (strIDSearch != null && !strIDSearch.equals("")) {

        id = (Long.valueOf(strIDSearch)).longValue();

        if (id == 0 || id > 0) {
          if (hasWhere)
            sb.append(" and ");
          else {

            sb.append(" where ");
          }
          if(("createJobsInspForm".equals(searchForm))||("editJobsInspForm".equals(searchForm))
    			  ||("createJobsMarineForm".equals(searchForm))||("editJobsMarineForm".equals(searchForm))
    			  ||("editJobsOutSourcingForm".equals(searchForm))||("createJobsOutSourcingForm".equals(searchForm))
    			  ||("createJobsAnalyticalServicesForm".equals(searchForm))||("editJobsAnalyticalServicesForm".equals(searchForm))){
        	  sb.append(" upper(ccc.contractCustContactId.contactId) = ? ");
          } else {
        	  sb.append("c.id = ? ");
          }
          params.add(id);
        }
    }

  //Contract->Customer->Contact enhancement issue 96937 changes starts
  if(("createJobsInspForm".equals(searchForm))||("editJobsInspForm".equals(searchForm))
		  ||("createJobsMarineForm".equals(searchForm))||("editJobsMarineForm".equals(searchForm))
		  ||("editJobsOutSourcingForm".equals(searchForm))||("createJobsOutSourcingForm".equals(searchForm))
		  ||("createJobsAnalyticalServicesForm".equals(searchForm))||("editJobsAnalyticalServicesForm".equals(searchForm)))
  {      
	  if (hasWhere)
        sb.append(" and ");
      else {
        hasWhere = true;
        sb.append("where");
      }
      sb.append(" ccc.billContactFlag='Y' " +
      " and ads.effDate = (select max(ads2.effDate) from c.contactCusts c2 left join c2.custAddrSeq  left join c2.custAddrSeq.custAddresses ads2 where ads2.effStatus='A' and ads2.effDate <= sysdate and ads2.custCode = ?)");
      params.add(custCode);
      
      Pagination pagination = search.getPagination();
      List results = null;
      if(sortFlag != null && sortFlag.equals(""))
      {
	      if (pagination != null) {
		  if (pagination.getTotalRecord() > 0) {
			  List counts = getDao().find(
	                    "select distinct count(c.id) from ContractCustContact ccc left join ccc.contact c " + sb.toString(),
	                    params.toArray());
		      if (counts.size() > 0) {
		            Number count = (Number) counts.get(0);
		            pagination.setTotalRecord(count.intValue());
		      }
	      }
          pagination.calculatePages();
	      results = getDao().find(
	                "select distinct ccc from ContractCustContact ccc left join fetch ccc.contact c " + sb.toString()
	                    + " order by ccc.contractCustContactId.contactId", params.toArray(), pagination);
	     
	      } else {
	    	  results = getDao().find(
	                  "select distinct ccc from ContractCustContact ccc left join fetch ccc.contact c " + sb.toString()
	                      + " order by ccc.contractCustContactId.contactId", params.toArray());
	      }
	      search.setResults(results);
	      search.setPagination(pagination);
      } else {
        //lookup header sort code begin
        String orderByValue="";
        if(sortFlag != null && sortFlag.equals("name"))
            orderByValue=" order by c.firstName||c.lastName";
        else
        	orderByValue=" order by ccc.contractCustContactId.contactId";
       
        if (pagination != null) {
            if (pagination.getTotalRecord() > 0) {

            	List counts = getDao().find(
                        "select distinct count(c.id) from ContractCustContact ccc left join ccc.contractCust crc left join ccc.contact c " + sb.toString(),
                        params.toArray());
            	
              if (counts.size() > 0) {
                Number count = (Number) counts.get(0);
                pagination.setTotalRecord(count.intValue());
              }
            }
            pagination.calculatePages();
          }
        // START : #119240
        /*results = getDao().find(
                "select distinct ccc from ContractCustContact ccc left join fetch ccc.contact c " + sb.toString()
                    + orderByValue, params.toArray(), pagination);*/
        if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
        	results = getDao().find(
                    "select distinct ccc from ContractCustContact ccc left join fetch ccc.contact c " + sb.toString()
                        + orderByValue + " " + search.getSortOrderFlag(), params.toArray(), pagination);	
        }else{
        	results = getDao().find(
                    "select distinct ccc from ContractCustContact ccc left join fetch ccc.contact c " + sb.toString()
                        + orderByValue, params.toArray(), pagination);
        }
        // END : #119240
      
        // search.setTotalResults(results);
        search.setResults(results);
        search.setPagination(pagination);
        //lookup header sort code end
   }
      
  } else {//Contract->Customer->Contact enhancement issue 96937 changes end
	 
      Pagination pagination = search.getPagination();
      List results = null;
      if(sortFlag != null && sortFlag.equals(""))
    {
      if (pagination != null) {
        if (pagination.getTotalRecord() > 0) {

          List counts = getDao().find(
              "select count(c.id) from Contact c  " + sb.toString(),
              params.toArray());

          if (counts.size() > 0) {
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        results = getDao().find(
            "select distinct c from Contact c " + sb.toString()
                + " order by c.id", params.toArray(), pagination);

        pagination.calculatePages();
      } else {
        results = getDao().find(
            "select distinct c from Contact c" + sb.toString(),
            params.toArray());
      }

      search.setResults(results);
      search.setPagination(pagination);
   }else
  {

      //lookup header sort code begin
     String orderByValue="";
        if(sortFlag != null && sortFlag.equals("name"))
          orderByValue=" order by c.firstName||c.lastName";
        else
          orderByValue=" order by c."+sortFlag;
        if (pagination != null) {
            if (pagination.getTotalRecord() > 0) {

              List counts = getDao().find(
                  "select count(c.id) from Contact c  " + sb.toString(),
                  params.toArray());

              if (counts.size() > 0) {
                Number count = (Number) counts.get(0);
                pagination.setTotalRecord(count.intValue());
              }
            }
            pagination.calculatePages();
          }
        results = getDao().find(
                "select distinct c from Contact c " + sb.toString()
                    + orderByValue, params.toArray(),pagination);
       // search.setTotalResults(results);
        search.setResults(results);
        //lookup header sort code end
   }
  }
    }

public List getContactsByContactIdandCustCode(String contactId,String customerId)
{

    if(contactId == null) return new ArrayList();
  List contacts = new ArrayList();
  String cId=contactId+'%';
  String custid=customerId.toUpperCase();
try{
   contacts = getDao().find(" select c from Contact c left join fetch c.contactCusts cc where str(c.id) like ? and upper(cc.contactCustId.custCode) = ?",
   new Object[] { cId, custid } );
  }
catch(Exception e ){

}
return contacts;
}


public List getBillingContactsByContactIdandCustCode(String contactId,String customerId,String contractcode)
{
  if(contactId == null) return new ArrayList();
  List contacts = new ArrayList();
  String cId=contactId+'%';
  String custid=customerId.toUpperCase();
  String ccode=contractcode.toUpperCase();
try{
 /* contacts = getDao().find(" select c from Contact c left join fetch c.contactCusts cc left join cc.custAddrSeq.custAddresses ads   where str(c.id) like ? and " +
   " upper(cc.contactCustId.custCode)=? and  c.billContactFlag='Y' and ads.effDate=(select max(ads2.effDate) from c.contactCusts c2 left join c2.custAddrSeq left join c2.custAddrSeq.custAddresses ads2 " +
   " where ads2.effStatus='A'   and ads2.effDate<=sysdate and upper(ads2.custCode)=?) ",
   new Object[] { cId, custid ,custid} );
  }*/	
	contacts = getDao().find(" select ccc from ContractCustContact ccc left join fetch ccc.contact c left join fetch c.contactCusts cc left join cc.custAddrSeq.custAddresses ads where str(ccc.contractCustContactId.contactId) like ? and " +
        " upper(ccc.contractCustContactId.custCode)=? and ccc.billContactFlag='Y' and upper(ccc.contractCustContactId.contractCode)=? and upper(cc.contactCustId.custCode)=? and ads.effDate=(select max(ads2.effDate) from c.contactCusts c2 left join c2.custAddrSeq left join c2.custAddrSeq.custAddresses ads2 " +
	" where ads2.effStatus='A' and ads2.effDate<=sysdate and upper(ads2.custCode)=?) ",      
	new Object[] { cId, custid,ccode,custid ,custid} );
	}	
catch(Exception e ){

}
return contacts;
}


public Contact getContactByIdandCustCode(Long id,String custCode)
{
  List contacts = new ArrayList();
  String cId=id.toString();
  String custid=custCode.toUpperCase();

try{
    contacts = getDao().find(
" select c from Contact c left join fetch c.contactCusts cc where str(c.id) = ? and upper(cc.contactCustId.custCode) = ?",
   new Object[] { cId, custid } );
}
catch(Exception e ){

}
if(contacts.size() > 0) return (Contact) contacts.get(0);
return null;
}


public CustVatRegistration getCustomerVatRegistrationsByCustCodeAndCountryCode(String custCode,String countryCode)
{
    List vatRegs = new ArrayList();


    vatRegs = getDao().find(
        "  from CustVatRegistration c  where c.custVatRegistrationId.custCode = ? and c.custVatRegistrationId.countryCode = ?",
           new Object[] { custCode, countryCode } );

        if(vatRegs.size() > 0) return (CustVatRegistration) vatRegs.get(0);
        return null;
}

public CustVatRegistration getCustomerVatRegistrationsByCustCodeAndRegId(String custCode,String regId)
{
    List vatRegs = new ArrayList();

    vatRegs = getDao().find(
        "  from CustVatRegistration c  where c.custVatRegistrationId.custCode = ? and c.custVatRegistrationId.registrationId = ?",
           new Object[] { custCode, regId } );

        if(vatRegs.size() > 0) return (CustVatRegistration) vatRegs.get(0);
        return null;
}

//newly added on tuesday
public List getContactSeqByCustCode(String custCode)
{
  List contacts = new ArrayList();
  String custid=custCode.toUpperCase();
try{
contacts = getDao().find("select distinct c from ContactCust c where upper(c.contactCustId.custCode) = ?", new Object[] {custid });
}
catch(Exception e ){

}
  if(contacts != null && contacts.size() > 0) return contacts;
return null;
}

public boolean getContactCustomersByIdandCode(Long id, String custCode,Integer locationNumber,Integer seqNumber)
{
boolean flag=false;
String cId=id.toString();
/*if(seqNumber==null)
{seqNumber=0;}*/
List codes=null;
if(seqNumber!=null)
{
codes=getDao().find("select c from ContactCust c left join fetch c.contact cc where str(cc.id) = ? and upper(c.contactCustId.custCode) = ? and c.contactCustId.locationNumber = ? and c.contactSeqNum = ? ", new Object[] {cId,custCode,locationNumber,seqNumber});
}
else
{
codes=getDao().find("select c from ContactCust c left join fetch c.contact cc where str(cc.id) = ? and upper(c.contactCustId.custCode) = ? and c.contactCustId.locationNumber = ? ", new Object[] {cId,custCode,locationNumber});
}

if(codes.size()>0)
{flag=true;return flag;}
else
return flag;
}
public boolean getExistingseqNumber(Long id,String custCode,Integer locationNumber)
{
  boolean flag=false;
  String cId=id.toString();

  List codes=getDao().find("select c from ContactCust c left join fetch c.contact cc where str(cc.id) = ? and upper(c.contactCustId.custCode) = ? and c.contactCustId.locationNumber = ? ", new Object[] {cId,custCode,locationNumber});

  if(codes.size()>0)
  {ContactCust contactCusts=(ContactCust)codes.get(0);
  if(contactCusts.getContactSeqNum()!=null)
    {flag=true;return flag;}
    else
    {return flag;}
  }
  else
  return flag;

}



//up to here
private List getConcatinatedCustAddressSeqWithComas(List results)
{
  CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

  List custAddrList = new ArrayList();
  for(int i=0;i<results.size();i++)
  {
    AddressSeqSearch search = new AddressSeqSearch();
    CustAddrSeq custAddrSeq =(CustAddrSeq)results.get(i);
    Set custAddr=custAddrSeq.getCustAddresses();
    if(custAddr != null && custAddr.size()>0)
    {
      String conAddress="";

      Iterator itr = custAddr.iterator();
      int loopCount=0;
      while(itr.hasNext())
      {
        CustAddress custAddress =(CustAddress)itr.next();
        if(loopCount>0)
          conAddress=conAddress+" "+"|"+" ";
        if(custAddress.getAddress1()!= null && !custAddress.getAddress1().trim().equals(""))
          conAddress=conAddress+custAddress.getAddress1()+","+" ";
        if(custAddress.getAddress2()!= null && !custAddress.getAddress2().trim().equals(""))
          conAddress=conAddress+custAddress.getAddress2()+","+" ";
        if(custAddress.getAddress3()!= null && !custAddress.getAddress3().trim().equals(""))
          conAddress=conAddress+custAddress.getAddress3()+","+" ";
        if(custAddress.getAddress4()!= null && !custAddress.getAddress4().trim().equals(""))
          conAddress=conAddress+custAddress.getAddress4()+","+" ";
        if(custAddress.getCity()!= null && !custAddress.getCity().trim().equals(""))
          conAddress=conAddress+custAddress.getCity()+","+" ";

        Country country =null;
        if(custAddress.getCountry()!= null &&!custAddress.getCountry().trim().equals("")){
          country=countryService.getCountryByCode(custAddress.getCountry());
        }

        if(custAddress.getState()!= null && !custAddress.getState().trim().equals("") && custAddress.getCountry()!= null && !custAddress.getCountry().trim().equals(""))
        {
          State state = countryService.getStateByCode(custAddress.getState(), custAddress.getCountry());
          if(state != null && (country==null || country.getShowState())){
            conAddress=conAddress+state.getName()+","+" ";
          }
        }
        if(custAddress.getCountry()!= null &&!custAddress.getCountry().trim().equals(""))
        {
          if(country != null)
            conAddress=conAddress+country.getName();
        }
        loopCount++;
      }
      search.setAddressDetails(conAddress);
    }
    search.setCustAddrSeq(custAddrSeq);
    custAddrList.add(search);

 }
  return custAddrList;
}
public List getCustomersByAccountOwnerName(String name)
{
     List customers = getDao().find(
              " from Customer c where c.accountOwnerName = ?  ",
              new Object[] { name }
            );

            if(customers.size() > 0) return customers;

            return null;
}

public void updateCustomer(Customer customer)
{
  getDao().save(customer);
}

public boolean existCustContact(Long id, String custCode)
{
  String cId=id.toString();
  List codes=getDao().find("select c from ContactCust c left join fetch c.contact cc where str(cc.id) = ? and upper(c.contactCustId.custCode) = ? ", new Object[] {cId, custCode});
  if(codes.size()>0)
    return true;
  return false;
}



public ContactCust getContactCustByPK(long contactId, String custCode, int location)
{
  List list = getDao().find(
    "from ContactCust c where  c.contactCustId.contactId = ? and c.contactCustId.custCode=? and c.contactCustId.locationNumber=?",
    new Object[] { contactId, custCode, location }
  );
  if(list.size() > 0) return (ContactCust)list.get(0);

  return null;
}

public void deleteContactCustByPK(long contactId, String custCode, int location)
{
  if( contactId==0 || custCode==null || location==0)
    return;
  getDao().bulkUpdate(
          "delete from ContactCust c where c.contactCustId.contactId = ? and c.contactCustId.custCode=? and c.contactCustId.locationNumber=?",
          new Object[] { contactId, custCode, location }
        );
}
// newly added code


public CustomerLanguage addCustomerLanguage(CustomerLanguage customerLanguage){

    return getDao().save(customerLanguage);
}
public CustAddressLanguage addCustomerAddrLanguage(CustAddressLanguage custAddressLanguage){

    return getDao().save(custAddressLanguage);
}
public CustAddress getCustAddresIdByCustCodeAndAddrSeqNumber(String custCode,Integer addrSeqNumber)
{
   if(custCode == null) return null;
   String queryStr="from CustAddress cd where cd.custCode='" + custCode + "'"+" and cd.locationNumber="+addrSeqNumber;

   List custAddr = getDao().find(queryStr ,
          null
        );

   if(custAddr.size()>0)
     return (CustAddress)custAddr.get(0);
   else
     return null;
}
public CustomerLanguage getCustomerLanguageByCode(String custCode,String language){

  if(custCode == null)
    return null;

  List customerLang = getDao().find(
        "from CustomerLanguage cl where upper(cl.customerLanguageId.custCode) = ? and upper(cl.customerLanguageId.languageCD)=?",
        new Object[] { custCode.toUpperCase(), language.toUpperCase()}
      );
  if(customerLang != null && customerLang.size()>0)
    return (CustomerLanguage)customerLang.get(0);
  else
    return null;
}

public CustAddressLanguage getCustomerAddrLanguageById(long custAddrid,String language){

  if(custAddrid == 0)
    return null;
  List customerAddrLang = getDao().find(
        "from CustAddressLanguage cal where cal.custAddressLanguageId.CustAddressId = ? and upper(cal.custAddressLanguageId.languageCD)=?",
        new Object[] { custAddrid, language.toUpperCase()}
      );
  if(customerAddrLang != null && customerAddrLang.size()>0)
    return (CustAddressLanguage)customerAddrLang.get(0);
  else
    return null;
}
//end

public boolean getContactCustsByCustCodeandLocationNumber(String custCode,Integer locationNumber)
  {
  boolean flag=false;
    List contactCusts = getDao().find(
          " from ContactCust con inner join fetch con.contact ct inner join fetch con.customer left join fetch con.custAddrSeq  where con.contactCustId.custCode = ? and con.custAddrSeq.custAddrSeqId.locationNumber = ?",
          new Object[] { custCode,locationNumber }
        );

        if(contactCusts.size() > 0)
        { flag=true;
        return flag;
        }
        else
        return flag;
  }
public List getContactCustListByContactid(long contactId)
{
  List list = getDao().find(
    "from ContactCust c where  c.contactCustId.contactId = ? ",
    new Object[] { contactId }
  );
  if(list.size() > 0) return list;

  return null;
}

/**
 * Name :getContactCustByContactidcustCodeandcontractCode
 * Date :Apr 1, 2009
 * purpose :to get the contractcustcontact data by passing customer, contact and the contract
 * @param custCode
 * @param contactId
 * @param contractCode
 * @return ContractCustContact
 */
public ContractCustContact getContactCustByContactidcustCodeandcontractCode(String custCode,long contactId,String contractCode)
{	
	 List contacts = getDao().find(
	          " from ContractCustContact c where c.contractCustContactId.custCode = ? and c.contractCustContactId.contactId = ? and c.contractCustContactId.contractCode=?",
	          new Object[] { custCode,contactId,contractCode}
	        );
	 
	 if(contacts.size()>0)
		 return (ContractCustContact)contacts.get(0);
	 else
		 return null;	
}
//START : To fix issue # 99933
public void getContractCustCustomers(CustomerSearch search,			 
		 String sortFlag) {	
	if (search == null)
		return;
	StringBuffer sb = new StringBuffer();
	List params = new ArrayList();
	boolean hasWhere = false;
	String customerId = search.getCustomerId().getValue();
	int customerVal = search.getCustomerId().getOperator();
	String billToFlag = search.getBillToFlag();
	String soldToFlag = search.getSoldToFlag();
	String shipToFlag = search.getShipToFlag();
	sb.append(" where cc.contractCustId.contractCode = ? ");
	params.add(search.getContractCode());
	hasWhere = true;
	
	if ((customerId != null) && (customerVal == Constants.CONTAINS)) {
		String cId = '%' + String.valueOf(customerId) + '%';
		// sb.append(" where str(c.id) like ?");
		sb.append("and upper(cc.contractCustId.custCode) like ?");
		params.add(cId.toUpperCase());
		hasWhere = true;
	} else if ((customerId != null)
			&& (customerVal == Constants.BEGINS_WITH)) {
		String cId = String.valueOf(customerId) + '%';
		sb.append("and upper(cc.contractCustId.custCode) like ?");
		params.add(cId.toUpperCase());
		hasWhere = true;
	} else if ((customerId != null) && (customerVal == Constants.EQUALS)) {
		sb.append("and upper(cc.contractCustId.custCode) = ?");
		params.add(customerId.toUpperCase());
		hasWhere = true;
	} else if ((customerId != null)
			&& (customerVal == Constants.NOT_EQUALS)) {
		sb.append("and upper(cc.contractCustId.custCode) !=?");
		params.add(customerId.toUpperCase());
		hasWhere = true;
	}

	String customerName = search.getCustomerName().getValue();
	int customerValue = search.getCustomerName().getOperator();
	if ((customerName != null) && !"".equals(customerName.trim())) {
		if (hasWhere)
			sb.append(" and ");
		else {
			hasWhere = true;
			sb.append(" where ");
		}
		if ((customerName != null) && (customerValue == Constants.CONTAINS)) {
			String cName = '%' + customerName.toUpperCase() + '%';
			sb.append(" upper(cc.customer.name) like ? ");
			params.add(cName);
		}
		if ((customerName != null)
				&& (customerValue == Constants.BEGINS_WITH)) {
			String cName = customerName.toUpperCase() + '%';
			sb.append(" upper(cc.customer.name) like ? ");
			params.add(cName);
		}
		if ((customerName != null) && (customerValue == Constants.EQUALS)) {
			String cName = customerName.toUpperCase();
			sb.append(" upper(cc.customer.name)  = ? ");
			params.add(cName);
		}
		if ((customerName != null)
				&& (customerValue == Constants.NOT_EQUALS)) {
			String cName = customerName.toUpperCase();
			sb.append(" upper(cc.customer.name) !=? ");
			params.add(cName);
		}
	}
	
	Pagination pagination = search.getPagination();
	List results = null;
	if (sortFlag != null && sortFlag.equals("")) {
		if (pagination != null) {
			if (pagination.getTotalRecord() > 0) {
				List counts =getDao().find(
						"select distinct cc from ContractCust cc left join fetch cc.customer c " + sb.toString(),
						params.toArray());

				if (null != counts) {
					pagination.setTotalRecord(counts.size());
				}
				System.out.println("counts.size():if:"+counts.size());
			}
			pagination.calculatePages();
			
			results = getDao().find(
					"select distinct cc from ContractCust cc left join fetch cc.customer c " + sb.toString(),
					params.toArray(), pagination);
			
			System.out.println("results.size():else:"+results.size());
		} else {
			results = getDao().find(
					"select distinct cc from ContractCust cc left join fetch cc.customer c " + sb.toString(),
					params.toArray());				
		}

		search.setResults(results);
	} else {
		String orderByValue = " order by cc.customer." + sortFlag;
		if (pagination != null) {
			if (pagination.getTotalRecord() > 0) {
				List counts = getDao().find(
						"select distinct cc from ContractCust cc left join fetch cc.customer c " + sb.toString(),
						params.toArray());

				if (null != counts) {
					pagination.setTotalRecord(counts.size());
				}
			}
			pagination.calculatePages();
		}
		results = getDao().find(
				"select distinct cc from ContractCust cc left join fetch cc.customer c " + sb.toString()
						+ orderByValue, params.toArray(), pagination);
		// search.setTotalResults(results);

		search.setResults(results);
	}	
}
public void getContractCustContacts(ContactSearch search,
		 String sortFlag) {

	if (search == null)
		return;
	StringBuffer sb = new StringBuffer(" where 1=1 ");
	List params = new ArrayList();
	sb.append("and cr.contractCustId.contractCode = ?");
	params.add(search.getContractCode());		
	
	Long contactId = search.getContactId().getValue();
	int contactValue = search.getContactId().getOperator();

	if ((contactId != null) && (contactValue == Constants.CONTAINS)) {
		String cId = '%' + String.valueOf(contactId) + '%';
		sb.append(" and str(c.id) like ?");
		params.add(cId);
	}

	else if ((contactId != null) && (contactValue == Constants.BEGINS_WITH)) {
		String cId = String.valueOf(contactId) + '%';
		sb.append(" and str(c.id) like ?");
		params.add(cId);
	}

	else if ((contactId != null) && (contactValue == Constants.EQUALS)) {
		sb.append(" and  c.id = ?");
		params.add(contactId);
	}

	else if ((contactId != null) && (contactValue == Constants.NOT_EQUALS)) {
		sb.append(" and c.id != ?");
		params.add(contactId);
	}

	String firstName = search.getFirstName().getValue();
	int firstNameVal = search.getFirstName().getOperator();
	if ((firstName != null) && !"".equals(firstName.trim())) {

		if ((firstName != null) && (firstNameVal == Constants.CONTAINS)) {
			String fName = '%' + firstName.toUpperCase() + '%';
			sb.append(" and upper(c.firstName) like ? ");
			params.add(fName);

		}
		if ((firstName != null) && (firstNameVal == Constants.BEGINS_WITH)) {
			String fName = firstName.toUpperCase() + '%';
			sb.append(" and upper(c.firstName)  like ? ");
			params.add(fName);

		}
		if ((firstName != null) && (firstNameVal == Constants.EQUALS)) {
			String fName = firstName.toUpperCase();
			sb.append(" and upper(c.firstName) = ?");
			params.add(fName);

		}
		if ((firstName != null) && (firstNameVal == Constants.NOT_EQUALS)) {
			String fName = firstName.toUpperCase();
			sb.append(" and upper(c.firstName) != ?");
			params.add(fName);

		}
	}

	String lastName = search.getLastName().getValue();
	int lastNameVal = search.getFirstName().getOperator();
	if ((lastName != null) && !"".equals(lastName.trim())) {

		if ((lastName != null) && (lastNameVal == Constants.CONTAINS)) {
			String lName = '%' + lastName.toUpperCase() + '%';
			sb.append(" and upper(c.lastName) like ? ");
			params.add(lName);
		}
		if ((lastName != null) && (lastNameVal == Constants.BEGINS_WITH)) {
			String lName = lastName.toUpperCase() + '%';
			sb.append(" and upper(c.lastName)  like ? ");
			params.add(lName);
		}
		if ((lastName != null) && (lastNameVal == Constants.EQUALS)) {
			String lName = lastName.toUpperCase();
			sb.append(" and upper(c.lastName) = ?");
			params.add(lName);
		}
		if ((lastName != null) && (lastNameVal == Constants.NOT_EQUALS)) {
			String lName = lastName.toUpperCase();
			sb.append(" and upper(c.lastName) != ?");
			params.add(lName);
		}
	}

	Pagination pagination = search.getPagination();
	List results = null;
	if (sortFlag != null && sortFlag.equals("")) {
		if (pagination != null) {
			if (pagination.getTotalRecord() > 0) {					
				results = getDao()
						.find(
								"select distinct ccc from ContractCustContact ccc left join fetch ccc.contractCust cr left join fetch cr.customer left join fetch ccc.contact c "
										+ sb.toString() + " order by c.id",
								params.toArray());
				if(null != results){
					pagination.setTotalRecord(results.size());
				}
				System.out.println("count:"+results.size());
			}
			pagination.calculatePages();
			results = getDao()
					.find(
							"select distinct ccc from ContractCustContact ccc left join fetch ccc.contractCust cr left join fetch cr.customer left join fetch ccc.contact c "
									+ sb.toString() + " order by c.id",
							params.toArray(), pagination);
			System.out.println("results:"+results.size());				
			search.setResults(results);
		} else {
			results = getDao()
					.find(
							"select distinct ccc from ContractCustContact ccc left join fetch ccc.contractCust cr left join fetch cr.customer left join fetch ccc.contact c "
									+ sb.toString(), params.toArray());
		}			
		search.setResults(results);			
		search.setPagination(pagination);
	} else {
		String orderByValue = "";
		if (sortFlag != null) {
			if (sortFlag.equals("id"))
				orderByValue = " order by c.id ";
			if (sortFlag.equals("name"))
				orderByValue = " order by c.firstName,c.lastName ";				
		}
		if (pagination != null) {
			if (pagination.getTotalRecord() > 0) {					
				results = getDao()
						.find(
								"select distinct ccc from ContractCustContact ccc left join fetch ccc.contractCust cr left join fetch cr.customer left join fetch ccc.contact c "
										+ sb.toString() + " order by c.id",
								params.toArray());
				if(null != results){
					pagination.setTotalRecord(results.size());
				}
			}
			pagination.calculatePages();
			search.setPagination(pagination);
		} 
		results = getDao()
				.find(
						"select distinct ccc from ContractCustContact ccc left join fetch ccc.contractCust cr left join fetch cr.customer left join fetch ccc.contact c "
								+ sb.toString() + orderByValue,
				params.toArray(), pagination);			

		search.setResults(results);
	}		
	
}
//END: To fix issue # 99933	
/* For iTrack issue #127767 -Starts*/ 
/**
 * Name :getCreatedByCustomers
 * Date :Jun 29, 2009
 * Purpose : to get CreatedByCustomersByUserName
 * @return List
 */
public List<Customer> getCreatedByCustomers(String userName){
     List customers = getDao().find(
          " from Customer c where c.creByUserName = ? ",
          new Object[] { userName }
        );
        if(customers.size() > 0) return customers;
        return null;
}
/**
 * Name :getUpdatedByCustomers
 * Date :Jun 29, 2009
 * Purpose : to get UpdatedByCustomersByUserName
 * @return List
 */
public List<Customer> getUpdatedByCustomers(String userName){
	
	   List customers = getDao().find(
          " from Customer c where c.modByUserName = ? ",
          new Object[] { userName }
        );
        if(customers.size() > 0) return customers;
        return null;
}
/* For iTrack issue #127767 -Ends  */
//START: 135193 
//To get Service Location and Date
public List<JobContract> getServiceLocationInfo(String strCustCode){
	List jobContrcts = getDao().find(
			//"select jc from JobContract jc left join fetch jc.customer c inner join fetch jc.jobOrder jo where jo.jobFinishDate is not null and c.custCode = ? order by jo.jobFinishDate desc ",
			"select jc from JobContract jc left join fetch jc.customer c inner join fetch jc.jobOrder jo inner join fetch jo.businessUnit where jo.jobFinishDate is not null and c.custCode = ? order by jo.jobFinishDate desc ",			
			new Object[] {strCustCode});
	return jobContrcts;
}
//To Save Service Location and Date
public void updateServiceLocationInfo(Customer customer){
	getDao().save(customer);	
}
//END: 135193

}
