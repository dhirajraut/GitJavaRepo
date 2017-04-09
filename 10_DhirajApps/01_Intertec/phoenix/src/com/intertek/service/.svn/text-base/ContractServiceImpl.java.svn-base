package com.intertek.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.calculator.ControlExt;
import com.intertek.domain.AddContract;
import com.intertek.domain.CfgContractExt;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.EditProductGroupSet;
import com.intertek.domain.EditVesselTypeSet;
import com.intertek.domain.ProductGroupExt;
import com.intertek.domain.ServiceLevel;
import com.intertek.domain.ServiceRateExt;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceExt;
import com.intertek.domain.ServiceVersionExt;
import com.intertek.domain.VesselTypeExt;
import com.intertek.domain.ZoneExt;
import com.intertek.domain.RBExt;
import com.intertek.entity.BranchLocation;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.Control;
import com.intertek.entity.HighLevelService;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.PortLocation;
import com.intertek.entity.ProductGroup;
import com.intertek.entity.ProductGroupSet;
import com.intertek.entity.RB;
import com.intertek.entity.ReferenceField;
import com.intertek.entity.Service;
import com.intertek.entity.ServiceRate;
import com.intertek.entity.ServiceVersion;
import com.intertek.entity.User;
import com.intertek.entity.VesselType;
import com.intertek.entity.VesselTypeSet;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.ContractServiceUtil;
import com.intertek.util.EntityUtil;
import com.intertek.util.SecurityUtil;

public class ContractServiceImpl extends BaseService implements ContractService
{
  public Contract loadContractByContractCode(String contractCode)
  {
    List contracts = getDao().find(
     "from Contract c left join fetch c.contractAttaches left join fetch c.originator left join fetch c.signatory  where c.contractCode=?",
      new Object[] { contractCode }
    );

    if(contracts.size() > 0) return (Contract)contracts.get(0);

    return null;
  }

  public List loadCfgContractsByContractCode(String contractCode)
  {
    return getDao().find(
     "from CfgContract cc where cc.cfgContractId.contractId = ?",
      new Object[] { contractCode }
    );
  }

  public Contract getContractByContractCode(String contractCode)
  {
    List contracts = getDao().find(
      "from Contract c where c.contractCode = ?",
      new Object[] { contractCode }
    );

    if(contracts.size() > 0) return (Contract)contracts.get(0);

    return null;
  }

  public Contract addContract(Contract contract)
  {
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    if(contract == null) return null;
    Date  d1 = contract.getMasterListDate();
    if(d1 == null)
    {
      throw new RuntimeException("No MasterList Date for Contract: " + contract.getContractCode());
    }

    Date d2=contract.getStatusDate();
    if(d2==null)
    {
      throw new RuntimeException("No Status Date for Contract: " + contract.getContractCode());
    }

    Date d3=contract.getExpireDate();
    if(d3==null)
    {
      throw new RuntimeException("No Expiration Date for Contract :" + contract.getContractCode());
    }

    String  originatorUser = contract.getOriginatorUserName();
    User exitsedOrig=null;
    if(originatorUser!= null &&(!originatorUser.trim().equals("")))
    {
      exitsedOrig = userService.getUserByName(originatorUser);
      if( exitsedOrig == null)
      {
        throw new RuntimeException("Originator  not exist for: " + originatorUser);
      }
    }


    String  signatoryUser = contract.getSignatoryUserName();
    User existedSig=null;
    if(signatoryUser!= null &&(!signatoryUser.trim().equals("")))
    {
      existedSig = userService.getUserByName(signatoryUser);
      if( existedSig == null)
      {
        throw new RuntimeException("signatory  not exist for: " + signatoryUser);
      }
    }

    contract.setOriginator(exitsedOrig);
    contract.setSignatory(existedSig);
    return getDao().save(contract);
  }

  public Contract saveContract(Contract contract)
  {
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    if(contract == null) return null;

    Date  d1 = contract.getMasterListDate();
    if(d1 == null)
    {
      throw new RuntimeException("No MasterList Date for Contract: " + contract.getContractCode());
    }

    Date d2=contract.getStatusDate();
    if(d2==null)
    {
      throw new RuntimeException("No Status Date for Contract: " + contract.getContractCode());
    }

    Date d3=contract.getExpireDate();
    if(d3==null)
    {
      throw new RuntimeException("No Expiration Date for Contract :" + contract.getContractCode());
    }

    String originatorUser = contract.getOriginatorUserName();
    User exitsedOrig=null;
    if(originatorUser!= null &&(!originatorUser.trim().equals("")))
    {
      exitsedOrig = userService.getUserByName(originatorUser);
      if( exitsedOrig == null)
      {
        throw new RuntimeException("Originator  not exist for: " + originatorUser);
      }
    }


    String  signatoryUser = contract.getSignatoryUserName();
    User existedSig=null;
    if(signatoryUser!= null &&(!signatoryUser.trim().equals("")))
    {
      existedSig = userService.getUserByName(signatoryUser);
      if( existedSig == null)
      {
        throw new RuntimeException("signatory  not exist for: " + signatoryUser);
      }
    }


    contract.setOriginator(exitsedOrig);
    contract.setSignatory(existedSig);

    contract.setUpdatedTime(new Date());

    User user = SecurityUtil.getUser();
    if(user != null)
    {
      contract.setModByUserName(user.getLoginName());
    }

    return getDao().save(contract);
  }


  public List getContractsByContractCode(String contractCode)
  {
    if(contractCode == null) return new ArrayList();

    return getDao().find(
      "from Contract c where upper(c.contractCode) like '" + contractCode.toUpperCase() + "%'",
      null
    );
  }

  private  User getSignatorIdByName(String name)
  {
    String lname="";
    String fname="";
    StringTokenizer sToken = new StringTokenizer(name, ",");
    while (sToken.hasMoreElements())
    {
      fname=(String)sToken.nextElement();
      lname=(String)sToken.nextElement();
    }

    String queryStr = " from User u where upper(u.firstName) = '" + fname.toUpperCase() + "' and upper(u.lastName) ='"+ lname.toUpperCase()+"'";
    List user = getDao().find(queryStr , null );
    if( user.size() > 0)
    {
      return (User)user.get(0);
    }

    return null;
  }

  private  User getOriginatorIdByName(String name)
  {
    String lname="";
    String fname="";
    StringTokenizer sToken = new StringTokenizer(name, ",");
    while (sToken.hasMoreElements())
    {
      fname=(String)sToken.nextElement();
      lname=(String)sToken.nextElement();
    }
    //List user = getDao().find("from User u where u.loginName=?", new Object[] {name});
    String queryStr = " from User u where upper(u.firstName) = '" + fname.toUpperCase() + "' and upper(u.lastName) ='"+ lname.toUpperCase()+"'";
    List user = getDao().find(queryStr , null );
    if( user.size() > 0)
    {
      return (User)user.get(0);
    }

    return null;
  }

  public void searchContract(ContractSearch search,String sortFlag)
  {
    if(search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;
    String contractCode = search.getContractCode().getValue();
    int contractValue=search.getContractCode().getOperator();

    if((contractCode!=null)&&!"".equals(contractCode.trim())&&(contractValue==Constants.CONTAINS))
    {
      String cId = '%' + contractCode.toUpperCase() + '%';
      sb.append("where upper(c.contractCode) like ?");
      params.add(cId);
      hasWhere = true;
    }
    else if((contractCode!=null)&&!"".equals(contractCode.trim())&&(contractValue==Constants.BEGINS_WITH))
    {
     String cId = contractCode.toUpperCase() + '%';
     sb.append("where upper(c.contractCode) like ?");
     params.add(cId);
     hasWhere = true;
    }
    else if((contractCode!=null)&&!"".equals(contractCode.trim())&&(contractValue==Constants.EQUALS))
    {
      String cId = contractCode.toUpperCase();
      sb.append("where upper(c.contractCode) = ?");
      params.add( cId);
      hasWhere = true;
    }
    else if((contractCode!=null)&&!"".equals(contractCode.trim())&&(contractValue==Constants.NOT_EQUALS))
    {
      String cId = contractCode.toUpperCase();
      sb.append("where upper(c.contractCode) != ?");
      params.add(cId);
      hasWhere = true;
    }

    String contractStatus = search.getContractStatus().getValue();

    if(contractStatus !=null)
    {
      if(hasWhere)
      {
        sb.append(" and ");
      }
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }


      if(contractStatus.equals(Constants.APPROVED) ||contractStatus.equals(Constants.INPROCESS)||contractStatus.equals(Constants.INACTIVE)||contractStatus.equals(Constants.SUBMIT)||contractStatus.equals(Constants.REJECT))
      {
        sb.append(" c.status = ?");
        params.add(contractStatus);
        hasWhere = true;
      }
      /*if(contractStatus.equals(Constants.INPROCESS))
        {
        sb.append(" c.status = ?");
        params.add(contractStatus);
        hasWhere = true;
      }
      if(contractStatus.equals(Constants.INACTIVE))
      {
        sb.append(" c.status = ?");
        params.add(contractStatus);
        hasWhere = true;
      }*/
          if(contractStatus.equals(Constants.BLANK))
      {
        sb.append("c.status != ?");
        params.add(contractStatus);
        hasWhere=true;
        }
    }

    String contractDescription = search.getContractDescription().getValue();
    int contractVal=search.getContractDescription().getOperator();
    if((contractDescription != null) && !"".equals(contractDescription.trim()))
    {
      if(hasWhere)
      {
        sb.append(" and ");
      }
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }
      if((contractDescription!=null)&&(contractVal==Constants.CONTAINS))
      {
        String cName='%'+contractDescription.toUpperCase() +'%';
        sb.append("upper(c.description) like ?");
        params.add(cName);
        hasWhere = true;
      }
      if((contractDescription!=null)&&(contractVal==Constants.BEGINS_WITH))
      {
        String cName=contractDescription.toUpperCase() +'%';
        sb.append(" upper(c.description)  like ? ");
        params.add(cName);
        hasWhere = true;
      }
      if((contractDescription!=null)&&(contractVal==Constants.EQUALS))
      {
        String cName=contractDescription.toUpperCase();
        sb.append("upper(c.description) = ? ");
        params.add(cName);
        hasWhere = true;
      }
      if((contractDescription!=null)&&(contractVal==Constants.NOT_EQUALS))
      {
        String cName=contractDescription.toUpperCase();
        sb.append("upper(c.description) != ? ");
        params.add(cName);
        hasWhere = true;
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
        List counts = getDao().find(
        "select count(c.id) from Contract c " + sb.toString(),
        params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
      "select distinct c from Contract c " + sb.toString()+" order by c.id",
      params.toArray(),
      pagination
      );

      pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
        "select distinct c from Contract c " + sb.toString(),
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
            List counts = getDao().find(
            "select count(c.id) from Contract c " + sb.toString(),
            params.toArray()
            );

            if(counts.size() > 0)
            {
              Number count = (Number)counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
          }
          pagination.calculatePages();
      results = getDao().find(
          "select distinct c from Contract c " + sb.toString()+orderByValue,
          params.toArray(),pagination
          );
        }
      search.setResults(results);
      }
  }

  public ContractCustContact addContractCustContact(ContractCustContact contractCustContact)
  {
    if(contractCustContact == null) return null;

    //  ContractCust existingContractCust = getContractCustById(contractCustContact.getContractCust().getId());

    //if(existingContractCust == null)
    //    throw new RuntimeException("ContractCust does not exist");
    //contractCustContact.setContractCust(existingContractCust);

    return getDao().save(contractCustContact);

  }

  public ContractCust getContractCustByCustCodeAndContractCode(String custCode, String contractCode)
  {
    List contractCusts = getDao().find(
      "from ContractCust c where c.contractCustId.custCode = ? and c.contractCustId.contractCode = ?",
      new Object[] {custCode, contractCode}
    );

    if(contractCusts.size() > 0) return (ContractCust)contractCusts.get(0);

    return null;
  }
  
  //START: Issue # 99933
  /*public ContractCust loadContractCustByCustCodeContract(String custCode, String contractCode)
  {
    List contractCusts = getDao().find(
      "from ContractCust c left join fetch c.customer cs where c.contractCustId.custCode = ? and c.contractCustId.contractCode = ? order by  c.contractCustId.custCode",
      new Object[] {custCode, contractCode}
    );
    
    if(contractCusts.size() > 0) return (ContractCust)contractCusts.get(0);

    return null;
  }*/
  /**
   * Name :loadContractCustsByCustCodeAndContractCode 
   * Date :May 07, 2009 
   * Purpose : to get contractCusts data
   * @param addContract
   * @param contractCode
   * @return List
   */
  public List loadContractCustsByCustCodeAndContractCode(AddContract addContract,String contractCode)
  {
	  StringBuffer sb = new StringBuffer();
	  List params = new ArrayList();
	  boolean hasWhere = false;
	  String customerId = addContract.getSearchCustomerId();
	  int customerVal = addContract.getCustomerSearchOperator();
	  if((customerId != null && !customerId.trim().equals(""))&&(customerVal==Constants.CONTAINS))
	     {
		      String cId='%'+String.valueOf(customerId)+'%';
		      sb.append("where upper(c.contractCustId.custCode) like ?");
		      params.add(cId.toUpperCase());
		      hasWhere = true;
	     } else if((customerId != null && !customerId.trim().equals(""))&&(customerVal==Constants.BEGINS_WITH))
                  {
	                  String cId=String.valueOf(customerId)+'%';
	                  sb.append("where upper(c.contractCustId.custCode) like ?");
	                  params.add(cId.toUpperCase());
	                  hasWhere = true;
                  } else if((customerId != null && !customerId.trim().equals(""))&&(customerVal==Constants.EQUALS))
	                        {
		                        sb.append("where upper(c.contractCustId.custCode) = ?");
		                        params.add(customerId.toUpperCase());
		                        hasWhere = true;
	                        } else if((customerId != null && !customerId.trim().equals(""))&&(customerVal==Constants.NOT_EQUALS))
		                            {
			                            sb.append("where upper(c.contractCustId.custCode) !=?");
			                            params.add(customerId.toUpperCase());
			                            hasWhere = true;
		                            } 
	  String customerName = addContract.getSearchCustomerName();   
	  if(customerName != null && !customerName.trim().equals("")){
		  String cName='%'+customerName.toUpperCase()+'%';
		  if(hasWhere){
			  sb.append(" and upper(cs.name) like ? and c.contractCustId.contractCode = ?");
              params.add(cName);
              params.add(contractCode);
		  } else {
			  sb.append(" where upper(cs.name) like ? and c.contractCustId.contractCode = ?");
              params.add(cName);
              params.add(contractCode);
		  }
	  } else {
		  if(hasWhere){
			  sb.append(" and c.contractCustId.contractCode = ?");
	          params.add(contractCode);
		  } else {
			  sb.append(" where c.contractCustId.contractCode = ?");
	          params.add(contractCode);
		  }
	  }
	  List contractCusts = getDao().find(
      "from ContractCust c left join fetch c.customer cs "+ sb.toString()+" order by  c.contractCustId.custCode",
      params.toArray()
    );
    return contractCusts;
  }
  /**
   * Name :loadContactsByCustCodeAndContractCode 
   * Date :May 07, 2009 
   * Purpose : to get contractCusts data
   * @param addContract
   * @param custCode
   * @param contractCode
   * @return List
   */
  public List loadContactsByCustCodeAndContractCode(AddContract addContract,String custCode,String contractCode)
  {
      if((custCode == null) || (contractCode == null)) return new ArrayList();
      StringBuffer sb = new StringBuffer();
	  List params = new ArrayList();
	  boolean hasWhere = false;
	  String contactId = addContract.getSearchContactId();
	  int contactVal = addContract.getContactSearchOperator();
	  if((contactId != null && !contactId.trim().equals(""))&&(contactVal==Constants.CONTAINS))
	     {
		      String cId='%'+String.valueOf(contactId)+'%';
		      sb.append("where upper(str(ct.id)) like ?");
		      params.add(cId.toUpperCase());
		      hasWhere = true;
	     } else if((contactId != null && !contactId.trim().equals(""))&&(contactVal==Constants.BEGINS_WITH))
                {
	                  String cId=String.valueOf(contactId)+'%';
	                  sb.append("where upper(str(ct.id)) like ?");
	                  params.add(cId.toUpperCase());
	                  hasWhere = true;
                } else if((contactId != null && !contactId.trim().equals(""))&&(contactVal==Constants.EQUALS))
	                        {
		                        sb.append("where ct.id = ?");
		                        params.add(Long.valueOf(contactId));
		                        hasWhere = true;
	                        } else if((contactId != null && !contactId.trim().equals(""))&&(contactVal==Constants.NOT_EQUALS))
		                            {
			                            sb.append("where ct.id !=?");
			                            params.add(Long.valueOf(contactId));
			                            hasWhere = true;
		                            } 
	  String contactName = addContract.getSearchContactName();
	  if(contactName != null && !contactName.trim().equals("")){
		  contactName = "%"+contactName.trim().toUpperCase()+"%";
		  if(hasWhere){			
			  sb.append(" and ");
		  }
		  else{
			  sb.append(" where ");			  
		  }
		  sb.append(" (upper(ct.firstName) like ? or ");
		  sb.append(" upper(ct.lastName) like ? or ");
		  sb.append(" concat(upper(ct.firstName),' ',upper(ct.lastName)) like ? or ");
		  sb.append(" concat(upper(ct.firstName),upper(ct.lastName)) like ? )");
		  params.add(contactName);
		  params.add(contactName);
		  params.add(contactName);
		  params.add(contactName);
		  

	  } 
	params.add(custCode);
	params.add(contractCode);
	if(sb.toString()!= null && !sb.toString().trim().equals("")){
	    return getDao().find(
	      "from ContractCustContact c left join fetch c.contact ct "+ sb.toString() +" and c.contractCustContactId.custCode = ? and c.contractCustContactId.contractCode = ? order by c.contact.id",
	      params.toArray()
	    );
	} else {
		return getDao().find(
			      "from ContractCustContact c left join fetch c.contact ct "+ sb.toString() +"where c.contractCustContactId.custCode = ? and c.contractCustContactId.contractCode = ? order by c.contact.id",
			      params.toArray()
			    );
	}
  }
  //End
  public ContractCust loadContractCustByCustCodeContract(String custCode, String contractCode)
  {
    List contractCusts = getDao().find(
      "from ContractCust c left join fetch c.customer cs where c.contractCustId.custCode = ? and c.contractCustId.contractCode = ? order by  c.contractCustId.custCode",
      new Object[] {custCode, contractCode}
    );
    
    if(contractCusts.size() > 0) return (ContractCust)contractCusts.get(0);

    return null;
  }
  public List getContractCustsByCustCodeAndContractCode(String custCode,String contractCode){
	  
	  String cCode = custCode.toUpperCase() +'%';
	  
	  List contractCusts = getDao().find(
		      "from ContractCust c left join fetch c.customer cs where upper(c.contractCustId.custCode) like ? and c.contractCustId.contractCode = ? order by c.contractCustId.custCode",
		      new Object[] {cCode, contractCode}
		    );
		    
		    if(contractCusts.size() > 0) 
		    	return contractCusts;		    
		    return null;
  }  
  
  public List getContactsByContractCode(String contactId, String contractCode){
	  String cCode = contactId.toUpperCase() +'%';
	  
	  List contactCusts = getDao().find(
		      //"from ContractCustContact ccc left join fetch ccc.contractCust cr left join fetch ccc.contact c where upper(str(ccc.contractCustContactId.contactId)) like ? and cr.contractCustId.contractCode = ? order by c.contractCustId.custCode",
			  "select distinct ccc from ContractCustContact ccc left join fetch ccc.contact c left join fetch c.contactCusts cc where str(ccc.contractCustContactId.contactId) like ? and ccc.contractCustContactId.contractCode = ? order by ccc.contractCustContactId.custCode",
		      new Object[] {cCode, contractCode}
		    );
	    if(contactCusts.size() > 0) 
	    	return contactCusts;		    
	    return null;
  }
  
  //END: Issue # 99933
  
  /**
 * Name :getContractCustsByContractCode
 * Date :Jun 24, 2008
 * Purpose :
 * @param contractCode
 * @param pagination
 * @return
 */
public List getContractCustsByContractCode(String contractCode, Pagination pagination)
  {
    if(contractCode == null) return new ArrayList();

    return getDao().find(
      "from ContractCust c left join fetch c.customer where c.contractCustId.contractCode = ? order by  c.contractCustId.custCode",
      new Object[] { contractCode },pagination
    );
  }

  public int getContractCustsCountByContractCode(String contractCode)
  {
    Number count = 0;
      if(contractCode == null)
        return count.intValue();
        List counts = getDao().find(
            "select count(c.contractCustId.custCode) from ContractCust c where c.contractCustId.contractCode = ? order by  c.contractCustId.custCode",
            new Object[] { contractCode }
        );
        if(counts.size() > 0)
            count = (Number)counts.get(0);
      return count.intValue();
  }


  public List getContractCustContactsByCustCodeAndContractCode(String custCode, String contractCode)
  {
    if((custCode == null) || (contractCode == null)) return new ArrayList();

    return getDao().find(
      "from ContractCustContact c left join fetch c.contact where c.contractCustContactId.custCode = ? and c.contractCustContactId.contractCode = ? order by c.contact.id",
      new Object[] {custCode, contractCode}
    );
  }

  public void deleteContractCustAssociations(ContractCust contractCust)
  {

      if(contractCust == null) return ;
    List contractCustList = new ArrayList();
  try{
      contractCustList = getDao().find(
        "from ContractCust c  left join fetch c.contract left join fetch c.customer where upper(c.contractCustId.contractCode) = '" + contractCust.getContract().getContractCode().toUpperCase() + "'  and  upper(c.contractCustId.custCode) =  '" +  contractCust.getCustomer().getCustCode().toUpperCase() + "' ",
        null
      );
  }
  catch(Exception e ){

  }
  if(contractCustList.size() > 0)
    {

       contractCust = (ContractCust) contractCustList.get(0);
      List contractCustContactList = new ArrayList();

      try{
        contractCustContactList = getDao().find(
          "from ContractCustContact cc   where upper(cc.contractCustContactId.contractCode) = '" + contractCust.getContractCustId().getContractCode().toUpperCase() + "'  and  upper(cc.contractCustContactId.custCode) =  '" +  contractCust.getContractCustId().getCustCode().toUpperCase() + "' ",
          null
        );
      }
      catch(Exception e ){

      }
      if(contractCustContactList.size() > 0)
      {

        for(int i=0;i< contractCustContactList.size(); i++)
        {
         ContractCustContact contractCustContact = (ContractCustContact) contractCustContactList.get(0);
         getDao().remove(contractCustContact);
        }
      }

      //contractCust.getContract().getContractCusts().remove(contactCust);
      contractCust.getCustomer().getContractCusts().remove(contractCust);

    }

    return;
  }

  public void deleteContractCustContactAssociations(ContractCustContact contractCustContact)
  {
      if(contractCustContact == null) return ;
    List contractCustContactList = new ArrayList();
  try{
      contractCustContactList = getDao().find(
        "from ContractCustContact c  where upper(c.contractCustContactId.contractCode) = '" + contractCustContact.getContractCustContactId().getContractCode().toUpperCase() + "'  and  upper(c.contractCustContactId.custCode) =  '" +  contractCustContact.getContractCustContactId().getCustCode().toUpperCase() + "'  and upper(c.contractCustContactId.contactId) =  "+  contractCustContact.getContractCustContactId().getContactId(),
        null
      );
  }
  catch(Exception e ){
  }
  if(contractCustContactList.size() > 0)
    {
       contractCustContact = (ContractCustContact) contractCustContactList.get(0);
       getDao().remove(contractCustContact);

    }

    return;
  }

  public void searchContractCode(ContractSearch search,String sortFlag)
  {

   if(search == null) return;

      StringBuffer sb = new StringBuffer();
      List params = new ArrayList();

      boolean hasWhere = false;
      String contractCode = search.getContractCode().getValue();

      if(contractCode!=null)
      {
        String cId = '%' + contractCode + '%';
        sb.append("where str(c.contractCode) like ?");
        params.add(cId);
        hasWhere = true;
      }

      String contractDescription = search.getContractDescription().getValue();
      if((contractDescription != null) && !"".equals(contractDescription.trim()))
      {
        if(hasWhere)
        {
          sb.append(" and ");
        }
        else
        {
          hasWhere = true;
          sb.append(" where ");
        }
        if(contractDescription!=null)
        {
          String cName='%'+contractDescription.toUpperCase() +'%';
          sb.append("upper(c.description) like ?");
          params.add(cName);
          hasWhere = true;
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
          List counts = getDao().find(
          "select count(c.id) from Contract c " + sb.toString(),
          params.toArray()
          );

          if(counts.size() > 0)
          {
            Number count = (Number)counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        results = getDao().find(
        "select distinct c from Contract c " + sb.toString(),
        params.toArray(),
        pagination
        );

        pagination.calculatePages();
      }
      else
      {
        results = getDao().find(
          "select distinct c from Contract c " + sb.toString(),
          params.toArray()
        );
      }
      search.setResults(results);
      search.setPagination(pagination);
    }
    else
    {
      String orderByValue=" order by c."+sortFlag;
      if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find(
          "select count(c.id) from Contract c " + sb.toString(),
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
      "select distinct c from Contract c " + sb.toString()+orderByValue,
      params.toArray(),pagination
      );

     // search.setTotalResults(results);
      search.setResults(results);

    }
  }

  public void submit(Contract contract, String toStateName)
  {
    contract.setStatus(toStateName);

    //saveContract(contract);
  }

  public void deactivate(Contract contract, String toStateName)
  {
    contract.setStatus(toStateName);

    //saveContract(contract);
  }

  public void approve(Contract contract, String toStateName)
  {
    contract.setStatus(toStateName);

    //saveContract(contract);
  }

  public void reject(Contract contract, String toStateName)
  {
    contract.setStatus(toStateName);

    //saveContract(contract);
  }

  public void setContractStatus(Contract contract, String toStateName)
  {
    contract.setStatus(toStateName);

    //saveContract(contract);
  }

 public List getDescriptionByContractDescription(String description)
{

  if(description==null) return new ArrayList();
  List descriptions=new ArrayList();
    String desc='%'+description.toUpperCase() +'%';
  try{
  descriptions=getDao().find("from Contract c where upper(c.description) like '" + desc +"'", null);
  }
  catch (Exception e)
  {
  }
  return descriptions;
 }

public List getContractsByOriginator(String originatorName)
{
  if(originatorName==null) return new ArrayList();
  List contracts=new ArrayList();

  try{
    contracts=getDao().find("from Contract c where upper(c.originatorUserName) = '" + originatorName.toUpperCase() +"'", null);
  }
  catch (Exception e)
  {
  }
  return contracts;
}
 public List getContractsBySignatory(String signatoryName)
 {
    if(signatoryName==null) return new ArrayList();
    List contracts=new ArrayList();

    try{
      contracts=getDao().find("from Contract c where upper(c.signatoryUserName) = '" + signatoryName.toUpperCase() +"'", null);
    }
    catch (Exception e)
    {
    }
    return contracts;
 }
 public List getContractAttachmentsByUserName(String userName)
 {
   return getDao().find("from ContractAttach c where upper(c.addedByUserName) = '" + userName.toUpperCase() +"'", null);
 }
 public void saveContractAttach(ContractAttach contractAttach)
 {
   getDao().save(contractAttach);
 }

  public List getPriceBookSeriesByCurrencyCD(String currencyCD)
  {
    return getDao().find(
      "select distinct pb.priceBookId.pbSeries from PriceBook pb where pb.priceBookId.currencyCD = ? and pb.priceBookId.priceBookId not in ('SHELLPB2001','SHELLPB2001-50') order by pb.priceBookId.pbSeries",
      new Object[] { currencyCD }
    );
  }

  public String getPriceBookSeriesNameByPriceBookId(String priceBookId)
  {
    List list = getDao().find(
      "select pb.priceBookId.pbSeries from PriceBook pb where pb.priceBookId.priceBookId = ?",
      new Object[] { priceBookId }
    );

    if(list.size() > 0)
    {
      return (String)list.get(0);
    }

    return null;
  }

  public List getPriceBookIdsByCurrencyCD(String currencyCD)
  {
    return getDao().find(
      "select distinct pb.priceBookId.priceBookId from PriceBook pb where pb.priceBookId.currencyCD = ? order by pb.priceBookId.priceBookId desc",
      new Object[] { currencyCD }
    );
  }

  public List getPriceBookIdsByCurrencyCDAndPriceBookSeries(
    String currencyCD,
    String priceBookSeries
  )
  {
    return getDao().find(
      "select distinct pb.priceBookId.priceBookId from PriceBook pb where pb.priceBookId.currencyCD = ? and pb.priceBookId.pbSeries = ? order by pb.priceBookId.priceBookId",
      new Object[] { currencyCD, priceBookSeries }
    );
  }

  public String getActivePriceBookIdByCurrencyCDAndPriceBookSeries(
    String currencyCD,
    String priceBookSeries
  )
  {
    List list = getDao().find(
      "select pb.priceBookId.priceBookId from PriceBook pb where pb.priceBookId.currencyCD = ? and pb.priceBookId.pbSeries = ? and ? between pb.priceBookId.beginDate and pb.endDate",
      new Object[] { currencyCD, priceBookSeries, new Date() }
    );

    if(list.size() > 0)
    {
      return (String)list.get(0);
    }

    return null;
  }

  public boolean existPriceBookByCurrencyCDAndPriceBookSeriesAndPriceBookId(
    String currencyCD,
    String priceBookSeries,
    String priceBookId
  )
  {
    List list = getDao().find(
      "select count(*) from PriceBook pb where pb.priceBookId.currencyCD = ? and pb.priceBookId.pbSeries = ? and pb.priceBookId.priceBookId = ?",
      new Object[] { currencyCD, priceBookSeries, priceBookId }
    );

    if(list.size() > 0)
    {
      Number number = (Number)list.get(0);
      if(number.intValue() > 0) return true;
    }

    return false;
  }

  public void saveAddContract(AddContract addContract)
  {
    if(addContract == null) return;

    Contract contract = addContract.getContract();
    if(contract == null) return;

    try{
      contract = saveContract(contract);
    }catch(Exception e){
      throw new RuntimeException(e.getMessage());
    }
    addContract.setContract(contract);

    List list = addContract.getCfgContractExtList();

    if(list != null)
    {
      clearOldCfgContracts(list);
      saveCfgContractExtList(list);

      ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");
      VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");
      for(int i=0; i<list.size(); i++)
      {
        CfgContractExt cfgContractExt = (CfgContractExt)list.get(i);
        CfgContract cfgContract = cfgContractExt.getCfgContract();

        productGroupService.saveEditProductGroupSet(cfgContractExt.getEditProductGroupSet());
        vesselTypeService.saveEditVesselTypeSet(cfgContractExt.getEditVesselTypeSet());
      }
    }

    List referenceFieldList = addContract.getReferenceFieldList();
    if(referenceFieldList != null)
    {
      List pbReferenceFieldList = addContract.getPbReferenceFieldList();
      boolean rfChanged = ContractUtil.isReferenceFieldListChanged(referenceFieldList, pbReferenceFieldList);
      if(rfChanged)
      {
        removeReferenceFieldsByContractId(contract.getContractCode());
        for(int i=0; i<referenceFieldList.size(); i++)
        {
          ReferenceField referenceField = (ReferenceField)referenceFieldList.get(i);
          getDao().save(referenceField);
        }
      }
    }
  }

  private void clearOldCfgContracts(List cfgContractExtList)
  {
    if(cfgContractExtList == null) return;

    for(int i=0; i<cfgContractExtList.size(); i++)
    {
      CfgContractExt cfgContractExt = (CfgContractExt)cfgContractExtList.get(i);

      CfgContract newCfgContract = cfgContractExt.getCfgContract();
      CfgContract oldCfgContract = cfgContractExt.getOldCfgContract();

      if(oldCfgContract != null)
      {
        if(!newCfgContract.getCfgContractId().equals(oldCfgContract.getCfgContractId()))
        {
          getDao().remove(oldCfgContract);
        }
      }
    }
  }

  public void saveCfgContractExtList(List cfgContractExtList)
  {
    if(cfgContractExtList == null) return;

    for(int i=0; i<cfgContractExtList.size(); i++)
    {
      CfgContractExt cfgContractExt = (CfgContractExt)cfgContractExtList.get(i);
      saveCfgContract(cfgContractExt.getCfgContract());
    }
  }

  public void saveCfgContract(CfgContract cfgContract)
  {
    if(cfgContract == null) return;

    getDao().save(cfgContract);
  }

  public List getReferenceFieldsByContractId(String contractId)
  {
    return getDao().find(
      "from ReferenceField o where o.referenceFieldId.contractId = ? order by o.sortOrderNum",
      new Object[] { contractId }
    );
  }

  public void removeReferenceFieldsByContractId(String contractId)
  {
    if(contractId == null) return;

    getDao().bulkUpdate(
      "delete from ReferenceField o where o.referenceFieldId.contractId = ?",
      new Object[] { contractId }
    );
  }

  public Date getEarliestBeginDateOfContract(String contractCode)
  {
    if(contractCode == null) return null;

    List dates = getDao().find(
     "select min(o.cfgContractId.beginDate) from CfgContract o where o.cfgContractId.contractId = ?",
      new Object[] { contractCode }
    );

    if(dates.size() > 0)
    {
      return (Date)dates.get(0);
    }

    return null;
  }
}

