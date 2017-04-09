package com.intertek.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.intertek.exception.ServiceException;
import com.intertek.domain.AddBranchInt;
import com.intertek.domain.AddBusinessStream;
import com.intertek.domain.AddTaxLabels;
import com.intertek.domain.BranchSearch;
import com.intertek.domain.BusinessUnitSearch;
import com.intertek.domain.JobCodeSearch;
import com.intertek.domain.SupervisorIdSearch;
import com.intertek.domain.UserSearch;
import com.intertek.entity.AssocBranch;
import com.intertek.entity.BatchReprint;
import com.intertek.entity.Branch;
import com.intertek.entity.BranchLanguage;
import com.intertek.entity.BranchCode;
import com.intertek.entity.BranchInt;
import com.intertek.entity.BusStream;
import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.BusinessUnitLanguage;
import com.intertek.entity.ConsolidatedInvoice;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.Country;
import com.intertek.entity.Customer;
import com.intertek.entity.Employee;
import com.intertek.entity.JobCode;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobContractNote;
import com.intertek.entity.JobOrder;
import com.intertek.entity.LogConfigList;
import com.intertek.entity.Notes;
import com.intertek.entity.OpenPeriod;
import com.intertek.entity.Organization;
import com.intertek.entity.PrebillSplit;
import com.intertek.entity.Role;
import com.intertek.entity.State;
import com.intertek.entity.TimeZone;
import com.intertek.entity.User;
import com.intertek.entity.UserLanguage;
import com.intertek.entity.UserSettings;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.util.BusinessUnitUtil;
import com.intertek.util.Constants;
import com.intertek.util.HashGenerator;
import com.intertek.util.SecurityUtil;
import com.intertek.util.StringUtil;

public class UserServiceImpl extends BaseService implements UserService
{
  public User getUserByName(String name)
  {
    List users = getDao().find(
      "from User u left join fetch u.roles roles left join fetch u.branch branch left join fetch branch.businessUnit bu left join fetch bu.organization  where upper(u.loginName) = ?",
      new Object[] { name.toUpperCase() }
    );

    if(users!=null &&users.size() > 0)   return (User)users.get(0);

    return null;
  }

  public User getUserByNameWithRoles(String name)
  {
      List users = getDao().find(
              "from User u left join fetch u.branch branch left join fetch branch.businessUnit bu left join fetch bu.organization left join fetch u.roles r left join fetch r.users where upper(u.loginName) = ?",
              new Object[] { name.toUpperCase() }
            );

            if(users.size() > 0) return (User)users.get(0);

            return null;
  }

 public User getUsersandRolesByName(String name)
  {
   User user=null;
    List users = getDao().find(
    "from User user left join fetch user.branch branch left join fetch branch.businessUnit bu left join fetch bu.organization  left join fetch user.roles left join fetch user.supervisor  where upper(user.loginName)=?",
    new Object[] { name.toUpperCase() } );

  if(users!= null && users.size() >0) {
      user = (User)users.get(0);
    } else{
          throw new ServiceException("user.not.exists.error", new Object[] {name}, null);
    }
  return user;
  }



public JobCode getJobCodeByName(String jodCode)
  {
  JobCode jobCode=null;
    List jobCodes=getDao().find("from JobCode j where j.jobCode=?", new Object[] {jodCode});
  if(jobCodes!= null && jobCodes.size() >0) {
      jobCode = (JobCode)jobCodes.get(0);
    } else{
          throw new ServiceException("jobcode.not.exists.error", new Object[] {jodCode}, null);
    }
  return jobCode;
  }


public  User getUserByFirstLastName(String name)
    {
      String lname="";
      String fname="";
        StringTokenizer sToken = new StringTokenizer(name, " ");
                  while (sToken.hasMoreElements())
            {
                 fname=(String)sToken.nextElement();
                 lname=(String)sToken.nextElement();
             }

  String queryStr = " from User u where upper(u.firstName) like '" + fname.toUpperCase() + "%' and upper(u.lastName)  like '"+ lname.toUpperCase()+"%'";
       List user = getDao().find(queryStr , null );
     if( user.size() > 0)
        { return (User)user.get(0); }
          return null;
  }

  public User getUserByNameWithPermissions(String name)
  {
    List users = getDao().find(
      "from User u left join fetch u.roles r left join fetch r.permissions left join fetch u.branch branch left join fetch branch.businessUnit bu left join fetch bu.organization where upper(u.status)=? and  upper(u.loginName) = ?",
      new Object[] {Constants.STATUS_A.toUpperCase(), name.trim().toUpperCase() }
    );

    if(users.size() > 0) return (User)users.get(0);

    return null;
  }

  public User getUserByNameWithOrgHierarchy(String loginName)
  {
  User user=null;
    List users = getDao().find(
      "from User u left join fetch u.branch branch left join fetch branch.businessUnit bu left join fetch bu.organization where upper(u.loginName) = ?",
      new Object[] {loginName.toUpperCase()}
    );

    if(users!= null && users.size() >0) {
      user = (User)users.get(0);
    } else{
          throw new ServiceException("users.org.heirarchy.error");
    }
  return user;
  }

  @Transactional(propagation=Propagation.REQUIRED)
  public User addUser(User user)
  {
    if(user == null) return null;
  User existedUser = getUserByName(user.getLoginName());

  if(existedUser != null)
    {throw new ServiceException("user.exists.error", new Object[] {user.getLoginName()}, null);}

    String branchName = user.getBranchName();
    if(branchName == null)
    {throw new ServiceException("user.branch.error", new Object[] {user.getLoginName()}, null);}

   Branch existedBranch = getBranchByName(user.getBranchName());
     if(existedBranch == null)
    {throw new ServiceException("branch.not.exists.error",new Object[] {user.getBranchName()}, null); }

  /* Employee existingEmployee = null;

  Employee employee=user.getEmployee();
     if(employee != null &&!"".equals(employee.getId().trim()))
    {
     existingEmployee=  getEmployeeById(employee.getId());
     if(existingEmployee==null)
     {throw new RuntimeException("User  with the employeeId does not exist in employee"+  employee.getId());}*/

    /* User existedempCode=getEmpIdById(employee.getId());
      if(existedempCode!=null)
     { throw new RuntimeException("User with the employee Id already Exists in users " +user.getLoginName());}
      user.setEmployee(existingEmployee);
    }*/

  BusinessUnit existedBu=getBusinessUnitByName(user.getBuName());
  if(existedBu==null)
  {throw new ServiceException("bu.not.exists.error",new Object[] {user.getBuName()}, null);}

  /*Employee supervisor=user.getSupervisor();
  Employee existingSupervisor = null;
  if(supervisor != null && supervisor.getId() >0)
    {
    existingSupervisor=getEmployeeById(supervisor.getId());
    if(existingSupervisor==null)
    {throw new RuntimeException("No Supervisor exists "+supervisor.getId());}
    user.setSupervisor(existingSupervisor);
    }*/
  /*  else
      user.setSupervisor(null);*/

   User supervisor=user.getSupervisor();
  String supervisorname=user.getSupervisorName();
  if(supervisorname != null &&!"".equals(supervisorname.trim()))
    {
    boolean existingSupervisor=getSupervisorNameByName(user.getSupervisorName());
    if(existingSupervisor==false)
    {throw new ServiceException("supervisor.exist.error", new Object[] {user.getSupervisorName()}, null);}

    }else
      user.setSupervisorName(null);



  JobCode existedJobCode = null;
    if(user.getJobCodeValue() == null)
  {throw new ServiceException("user.jobcode.error", new Object[] {user.getLoginName()}, null);}

else
    {
      existedJobCode=getJobCodeByName(user.getJobCodeValue());
      if(existedJobCode==null)
      {throw new ServiceException("jobcode.exist.error", new Object[] {user.getJobCodeValue()}, null);}
     /* {throw new RuntimeException("No Job Code Exist "+ user.getJobCodeValue());}*/
    }


  TimeZone existedTimeZone = null;
    if(user.getPreferredTz() != null && (!user.getPreferredTz().trim().equals("")))
    {
      AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
      existedTimeZone=adminService.getTimeZoneByName(user.getPreferredTz());
      if(existedTimeZone==null)
      {       throw new ServiceException("time.zone.error", new Object[] {user.getPreferredTz()}, null);}
        //throw new RuntimeException("No Such Time Zone Exists : "+ user.getPreferredTz());

   }




    String salt = user.getSalt();
    if(salt == null)
    {
      HashGenerator hashGenerator = (HashGenerator)ServiceLocator.getInstance().getBean("hashGenerator");
      user.setSalt(hashGenerator.generateSalt());
      String hashedPassword = hashGenerator.generateHash(user.getPassword(), user.getSalt());
      user.setPassword(hashedPassword);
    }


    Date date = new Date();
    user.setCreateTime(date);
    user.setUpdateTime(date);
  user.setBranch(existedBranch);
  user.setBusinessUnit(existedBu);
 // user.setEmployee(existingEmployee);
//  user.setSupervisor(existingSupervisor);
  user.setSupervisor(supervisor);
  user.setPreferredTimeZone(existedTimeZone);
    user.setJobCode(existedJobCode);
    return getDao().save(user);
    }


  @Transactional(propagation=Propagation.REQUIRES_NEW)
  public User changeUserLoginName(String oldUserName, String newUserName)
  {
     User oldUser = getUserByNameWithRoles(oldUserName);
     if(oldUser == null) return null;
     /* For iTrack issue #127767 -starts */
     String actualOldUserName = oldUser.getLoginName(); 
     /* For iTrack issue #127767 -ends */
     User existedUser = getUserByName(newUserName);

    if(existedUser != null)
          {throw new ServiceException("user.exists.error", new Object[] {newUserName}, null);}

      String branchName = oldUser.getBranchName();
      if(branchName == null)
          {throw new ServiceException("user.branch.error", new Object[] {newUserName}, null);}
     Branch existedBranch = getBranchByName(oldUser.getBranchName());
     if(existedBranch == null)
            {throw new ServiceException("branch.not.exists.error" , new Object[] {oldUser.getBranchName()}); }

     BusinessUnit existedBu=getBusinessUnitByName(oldUser.getBuName());
     if(existedBu==null)
     {throw new ServiceException("bu.not.exists.error" , new Object[] {oldUser.getBuName()}, null);}
            /*{throw new RuntimeException(" bu.not.exists.error "+oldUser.getBuName());}*/


     User supervisor=oldUser.getSupervisor();
     String supervisorname=oldUser.getSupervisorName();
     if(supervisorname != null &&!"".equals(supervisorname.trim()))
    {
       boolean existingSupervisor=getSupervisorNameByName(oldUser.getSupervisorName());
       if(existingSupervisor==false)
       {throw new ServiceException("supervisor.exist.error" , new Object[] {oldUser.getSupervisorName()}, null);}
        /*{throw new RuntimeException("No Supervisor exists "+oldUser.getSupervisorName());}*/
      }
      else
        oldUser.setSupervisorName(null);



     JobCode existedJobCode = null;
     if(oldUser.getJobCodeValue() == null)
    {throw new ServiceException("user.jobcode.error", new Object[] {newUserName}, null);}
     else
    {
       existedJobCode=getJobCodeByName(oldUser.getJobCodeValue());
       if(existedJobCode==null)
       {throw new ServiceException("jobcode.not.exists.error" , new Object[] {oldUser.getJobCodeValue()}, null);}
        //{throw new RuntimeException("No Job Code Exist "+ oldUser.getJobCodeValue());}
    }


    TimeZone existedTimeZone = null;
    if(oldUser.getPreferredTz() != null && (!oldUser.getPreferredTz().trim().equals("")))
    {
      AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
      existedTimeZone=adminService.getTimeZoneByName(oldUser.getPreferredTz());
      if(existedTimeZone==null)
      {
       throw new ServiceException("time.zone.error", new Object[] {oldUser.getPreferredTz()}, null);
       // throw new RuntimeException("No Such Time Zone Exists : "+ oldUser.getPreferredTz());
      }
    }


    String salt = oldUser.getSalt();
    if(salt == null)
    {
      HashGenerator hashGenerator = (HashGenerator)ServiceLocator.getInstance().getBean("hashGenerator");
      oldUser.setSalt(hashGenerator.generateSalt());
      String hashedPassword = hashGenerator.generateHash(oldUser.getPassword(), oldUser.getSalt());
      oldUser.setPassword(hashedPassword);
    }

    User newUser = new User();
    newUser.setLoginName(newUserName);
    newUser.setActivationCode(oldUser.getActivationCode());
    newUser.setBranchName(oldUser.getBranchName());
    newUser.setBuName(oldUser.getBuName());
    newUser.setCountry(oldUser.getCountry());
    newUser.setCountryCode(oldUser.getCountryCode());
    newUser.setCreateTime(new Date());
    newUser.setCurrencyCd(oldUser.getCurrencyCd());
    newUser.setDateFormat(oldUser.getDateFormat());
    newUser.setEmail(oldUser.getEmail());
    newUser.setEmployeeId(oldUser.getEmployeeId());
    newUser.setEmployeeStatus(oldUser.getEmployeeStatus());
    newUser.setEmployeeType(oldUser.getEmployeeType());
    newUser.setFirstName(oldUser.getFirstName());
    newUser.setFullPartTime(oldUser.getFullPartTime());
    newUser.setIsApprover(oldUser.getIsApprover());
    newUser.setJobCodeValue(oldUser.getJobCodeValue());
    newUser.setJobType(oldUser.getJobType());
    newUser.setLanguage(oldUser.getLanguage());
    newUser.setLastName(oldUser.getLastName());
    newUser.setMiddleName(oldUser.getMiddleName());
    newUser.setPassword(oldUser.getPassword());
    newUser.setPreferredTimeZone(oldUser.getPreferredTimeZone());
    newUser.setPreferredTz(oldUser.getPreferredTz());
    newUser.setProductType(oldUser.getProductType());
    newUser.setRegularTemp(oldUser.getRegularTemp());


    newUser.setSalt(oldUser.getSalt());
    newUser.setStatus(oldUser.getStatus());
    newUser.setSupervisorName(oldUser.getSupervisorName());
    newUser.setTimeFormat(oldUser.getTimeFormat());
    newUser.setUrl(oldUser.getUrl());
    newUser.setWorkFunction(oldUser.getWorkFunction());

    newUser = addUser(newUser);

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    ConsolidatedInvoiceService consolidatedInvoiceService = (ConsolidatedInvoiceService)ServiceLocator.getInstance().getBean("consolidatedInvoiceService");
    AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");

    if(oldUser.getRoles() != null && oldUser.getRoles().size() > 0)
    {
      Iterator iter = oldUser.getRoles().iterator();
      while(iter.hasNext())
      {
        Role role = (Role) iter.next();
        Role newRole = new Role();

        newRole.setName(role.getName());
        newRole.setPermissions(role.getPermissions());
        newRole.setRoleDesc(role.getRoleDesc());
        newRole.setSolutions(role.getSolutions());
        Set usersSet = new HashSet();
        for(User user : role.getUsers()){
          if(!user.getLoginName().equals(oldUser.getLoginName()))
            usersSet.add(user);
        }
        usersSet.add(newUser);
        newRole.setUsers(usersSet);

        newRole = getDao().save(newRole);
      }
    }

  //Get all users for which supervisor = oldUser & replace with new user
    List users = getUsersBySupervisorName(oldUserName);
    if(users != null && users.size() > 0)
    {
      for(int i=0;i<users.size();i++)
      {
        User updatedUser = (User) users.get(i);
        updatedUser.setSupervisorName(newUserName);
        updatedUser.setSupervisor(newUser);
        saveUser(updatedUser);
      }
    }

    //Get all contracts where originator name = oldUser & replace with new user

    List contracts = contractService.getContractsByOriginator(oldUserName);
    if(contracts != null && contracts.size() > 0)
    {
      for(int i=0;i<contracts.size();i++)
      {
        Contract updatedContract = (Contract) contracts.get(i);
        updatedContract.setOriginator(newUser);
        updatedContract.setOriginatorUserName(newUserName);
        contractService.saveContract(updatedContract);
      }
    }

    //Get all contracts where signatory name = oldUser & replace with new user
    contracts = contractService.getContractsBySignatory(oldUserName);
    if(contracts != null && contracts.size() > 0)
    {
      for(int i=0;i<contracts.size();i++)
      {
        Contract updatedContract = (Contract) contracts.get(i);
        updatedContract.setSignatory(newUser);
        updatedContract.setSignatoryUserName(newUserName);
        contractService.saveContract(updatedContract);
      }
    }
    /* For iTrack issue #127767 -Starts */  
    getDao().bulkUpdate(
            "update Contract set creByUserName = ? where creByUserName = ?",
            new Object[] { newUserName, actualOldUserName });
    
    getDao().bulkUpdate(
            "update Contract set modByUserName = ? where modByUserName = ?",
            new Object[] { newUserName, actualOldUserName }); 
    //For iTrack #103082-START	
    getDao().bulkUpdate(
            "update Contact set creByUserName = ? where creByUserName = ?",
            new Object[] { newUserName, actualOldUserName });
    
    getDao().bulkUpdate(
            "update Contact set modByUserName = ? where modByUserName = ?",
            new Object[] { newUserName, actualOldUserName }); 
    //For iTrack #103082-END	
    try {
    	 List<BatchReprint> batchReprints = getBatchReprintByUsername(actualOldUserName);
    	 if(batchReprints != null && batchReprints.size() > 0)
         {
    	   for(BatchReprint batchRep:batchReprints)
           {
        	   batchRep.setRunnedBy(newUser);
        	   batchRep.setRunnedByUserName(newUserName);
        	   jobService.saveBatchReprint(batchRep);
           }
         }
    	//update all consolidated invocies where generated by = old user
    	// List consolidatedInvs = consolidatedInvoiceService.getConsolidatedInvoicesByUserName(oldUserName);
        List consolidatedInvs = consolidatedInvoiceService.getConsolidatedInvoicesByUserName(actualOldUserName);
        if(consolidatedInvs != null && consolidatedInvs.size() > 0)
        {
          for(int i=0;i<consolidatedInvs.size();i++)
          {
            ConsolidatedInvoice consolidatedInvoice = (ConsolidatedInvoice) consolidatedInvs.get(i);
            consolidatedInvoice.setGeneratedBy(newUser);
            consolidatedInvoice.setGeneratedByUserName(newUserName);
            consolidatedInvoiceService.saveConsolidatedInvoice(consolidatedInvoice);
          }
        }
         //update all jobContractInvoices where credit reason user = old user
        
         // List jobContractInvoices = prebillService.getJobContractInvoicesByUserName(oldUserName);
         List jobContractInvoices = prebillService.getJobContractInvoicesByUserName(actualOldUserName);
         if(jobContractInvoices != null && jobContractInvoices.size() > 0)
         {
           for(int i=0;i<jobContractInvoices.size();i++)
           {
             JobContractInvoice jobContractInvoice = (JobContractInvoice) jobContractInvoices.get(i);
             jobContractInvoice.setCreditReasonUser(newUser);
             jobContractInvoice.setCreditReasonUserName(newUserName);
             prebillService.saveJobContractInvoice(jobContractInvoice);
           }
         }
        // List<JobContractInvoice> jobContractInvoiceList = prebillService.getJobContractInvoicesByReceivedBy(oldUserName);
         List<JobContractInvoice> jobContractInvoiceList = prebillService.getJobContractInvoicesByReceivedBy(actualOldUserName);
         if(jobContractInvoices != null && jobContractInvoices.size() > 0)
         {
           for(JobContractInvoice jobContractInvoice : jobContractInvoiceList)
           {
             jobContractInvoice.setReceivedByUserName(newUser.getLoginName());
             prebillService.saveJobContractInvoice(jobContractInvoice);
           }
         }
         getDao().bulkUpdate(
                 "update JobContractInvoice set creationUserName = ? where creationUserName = ?",
                 new Object[] { newUserName, actualOldUserName });
    } catch (Exception e){
    }
    /* For iTrack issue #127767 -Ends */ 
    
    //Update jobOrders where created by/updated by/ received by/admin contact user name = oldUser
    /* Commented For iTrack issue #127767 -Starts */ 
   // List jobOrders = jobService.getJobsByUserName(oldUserName);
   /* if(jobOrders != null && jobOrders.size() > 0)
    {
      for(int i=0;i<jobOrders.size();i++)
      {
        JobOrder jobOrder = (JobOrder) jobOrders.get(i);
        if(jobOrder.getReceivedByUserName() != null && jobOrder.getReceivedByUserName().trim().equalsIgnoreCase(oldUserName))
        {
          jobOrder.setReceivedBy(newUser);
          jobOrder.setReceivedByUserName(newUserName);
        }
        if(jobOrder.getCreatedByUserName() != null && jobOrder.getCreatedByUserName().trim().equalsIgnoreCase(oldUserName))
        {
          jobOrder.setCreatedBy(newUser);
          jobOrder.setCreatedByUserName(newUserName);
        }
        if(jobOrder.getUpdatedByUserName() != null && jobOrder.getUpdatedByUserName().trim().equalsIgnoreCase(oldUserName))
        {
          jobOrder.setUpdatedBy(newUser);
          jobOrder.setUpdatedByUserName(newUserName);
        }
        if(jobOrder.getAdminContactUserName() != null && jobOrder.getAdminContactUserName().trim().equalsIgnoreCase(oldUserName))
        {
          jobOrder.setAdminContact(newUser);
          jobOrder.setAdminContactUserName(newUserName);
        }
        jobService.updateJobOrder(jobOrder);
      }
    }*/
    /* Comment ends For iTrack issue #127767 */ 
    List<LogConfigList> logConfigList1 = jobService.getLogConfigListsByCreatedByUser(actualOldUserName);
    if(logConfigList1 != null && logConfigList1.size() > 0)
    {
      for(LogConfigList logCfgList : logConfigList1)
      {
    	  logCfgList.setCreatedByUserName(newUserName);
    	  logCfgList.setCreatedBy(newUser);
    	  jobService.saveConfig(logCfgList);
      }
    }
    List<LogConfigList> logConfigList2 = jobService.getLogConfigListsByUpdatedByUser(actualOldUserName);
    if(logConfigList2 != null && logConfigList2.size() > 0)
    {
      for(LogConfigList logCfgList : logConfigList2)
      {
    	  logCfgList.setUpdatedByUserName(newUserName);
    	  logCfgList.setUpdatedBy(newUser);
    	  jobService.saveConfig(logCfgList);
      }
    }
    //Update prebill_splits where updated by & created by user naem = oldUser
    // List prebillSplits = prebillService.getPrebillSplitsByUserName(oldUserName);
    List prebillSplits = prebillService.getPrebillSplitsByUserName(actualOldUserName);
    if(prebillSplits != null && prebillSplits.size() > 0)
    {
      for(int i=0;i<prebillSplits.size();i++)
      {
        PrebillSplit prebillSplit = (PrebillSplit) prebillSplits.get(i);
        prebillSplit.setUpdatedBy(newUser);
        prebillSplit.setUpdatedByUserName(newUserName);
        prebillService.savePrebillSplit(prebillSplit);
      }
    }

    //update all customers where accoun owner = oldUser
   // List customers = customerService.getCustomersByAccountOwnerName(oldUserName);
    List customers = customerService.getCustomersByAccountOwnerName(actualOldUserName);
    if(customers != null && customers.size() > 0)
    {
      for(int i=0;i<customers.size();i++)
      {
        Customer customer = (Customer) customers.get(i);
        customer.setAccountOwnerName(newUserName);
        customer.setAccountOwner(newUser);
        customerService.updateCustomer(customer);
      }
    }
    /* For iTrack issue #127767 -Starts */ 
    List<Customer> createdBycusts = customerService.getCreatedByCustomers(actualOldUserName);
    if(createdBycusts != null && createdBycusts.size() > 0)
    {
    	for(Customer customer : createdBycusts)
        {
    		customer.setCreByUserName(newUserName);
    		customer.setCreByUser(newUser);
    		customerService.updateCustomer(customer);
        }
    }
    List<Customer> updatedBycusts = customerService.getUpdatedByCustomers(actualOldUserName);
    if(updatedBycusts != null && updatedBycusts.size() > 0)
    {
    	for(Customer customer  : updatedBycusts)
        {
    		customer.setModByUserName(newUserName);
    		customer.setUpByUser(newUser);
    		customerService.updateCustomer(customer);
        }
    }
    /* For iTrack issue #127767 -Ends */ 
    //update all consolidated invocies where generated by = old user
   /* List consolidatedInvs = consolidatedInvoiceService.getConsolidatedInvoicesByUserName(oldUserName);
    if(consolidatedInvs != null && consolidatedInvs.size() > 0)
    {
      for(int i=0;i<consolidatedInvs.size();i++)
      {
        ConsolidatedInvoice consolidatedInvoice = (ConsolidatedInvoice) consolidatedInvs.get(i);
        consolidatedInvoice.setGeneratedBy(newUser);
        consolidatedInvoice.setGeneratedByUserName(newUserName);

        consolidatedInvoiceService.saveConsolidatedInvoice(consolidatedInvoice);
      }
    }

    //update all contract attachments where added by = old user
    List contractAttaches = contractService.getContractAttachmentsByUserName(oldUserName);
    if(contractAttaches != null && contractAttaches.size() > 0)
    {
      for(int i=0;i<contractAttaches.size();i++)
      {
        ContractAttach contractAttach = (ContractAttach) contractAttaches.get(i);
        contractAttach.setAddedBy(newUser);
        contractAttach.setAddedByUserName(newUserName);

        contractService.saveContractAttach(contractAttach);
      }
    }

    //update all jobContractInvoices where credit reason user = old user
   /* List jobContractInvoices = prebillService.getJobContractInvoicesByUserName(oldUserName);
    if(jobContractInvoices != null && jobContractInvoices.size() > 0)
    {
      for(int i=0;i<jobContractInvoices.size();i++)
      {
        JobContractInvoice jobContractInvoice = (JobContractInvoice) jobContractInvoices.get(i);
        jobContractInvoice.setCreditReasonUser(newUser);
        jobContractInvoice.setCreditReasonUserName(newUserName);
        prebillService.saveJobContractInvoice(jobContractInvoice);
      }
    }


    List<JobContractInvoice> jobContractInvoiceList = prebillService.getJobContractInvoicesByReceivedBy(oldUserName);
    if(jobContractInvoices != null && jobContractInvoices.size() > 0)
    {
      for(JobContractInvoice jobContractInvoice : jobContractInvoiceList)
      {
        jobContractInvoice.setReceivedByUserName(newUser.getLoginName());
        prebillService.saveJobContractInvoice(jobContractInvoice);
      }
    }

    //update all jobContractNotes where added by user = old user
  //  List jobContractNotes = jobService.getJobContractNotesByUserName(oldUserName);
    List jobContractNotes = jobService.getJobContractNotesByUserName(actualOldUserName);
    if(jobContractNotes != null && jobContractNotes.size() > 0)
    {
      for(int i=0;i<jobContractNotes.size();i++)
      {
        JobContractNote jobContractNote = (JobContractNote) jobContractNotes.get(i);
        jobContractNote.setAddedBy(newUser);
        jobContractNote.setAddedByUserName(newUserName);

        jobService.saveJobContractNote(jobContractNote);
      }
    }
    /* For iTrack issue #127767 -Starts */    
    List<UserLanguage> userlanguageLst = getUserLanguageByUserName(actualOldUserName);
    if(userlanguageLst != null && userlanguageLst.size() > 0)
    {
        for(UserLanguage userLanguage : userlanguageLst)
        {
        	userLanguage.getUserLanguageId().setLoginName(newUser.getLoginName());
        	userLanguage.setUser(newUser);
            saveUserLanguage(userLanguage);
        }
    }
    List<Notes> notesLst = getNotesByUserName(actualOldUserName);
    if(notesLst != null && notesLst.size() > 0)
    {
        for(Notes notes : notesLst)
        {
        	notes.setAddedByUserName(newUserName);
        	notes.setAddedBy(newUser);
        	saveNotes(notes);
        }
    }
    List<UserSettings> userSettingLst = getUserSettingsByUserName(actualOldUserName);
    if(userSettingLst != null && userSettingLst.size() > 0)
    {
        for(UserSettings userSettings : userSettingLst)
        {
        	userSettings.setLoginName(newUser.getLoginName());
        	userSettings.setUser(newUser);
        	saveUserSettings(userSettings);
        }
    }
    List<JobOrder> jobOrderLst1 = jobService.getReceivedJobsByUserName(actualOldUserName);
    if(jobOrderLst1 != null && jobOrderLst1.size() > 0)
    {
      for(JobOrder jobOrder : jobOrderLst1)
      {
    	  jobOrder.setReceivedBy(newUser);
          jobOrder.setReceivedByUserName(newUserName);
          jobService.updateOnlyJobOrder(jobOrder);
      }
    }
    List<JobOrder> jobOrderLst2 = jobService.getCreatedByJobsByUserName(actualOldUserName);
    if(jobOrderLst2 != null && jobOrderLst2.size() > 0)
    {
      for(JobOrder jobOrder : jobOrderLst2)
      {
    	  jobOrder.setCreatedBy(newUser);
          jobOrder.setCreatedByUserName(newUserName);
          jobService.updateOnlyJobOrder(jobOrder);
      }
    }
    List<JobOrder> jobOrderLst3 = jobService.getUpdatedByJobsByUserName(actualOldUserName);
    if(jobOrderLst3 != null && jobOrderLst3.size() > 0)
    {
      for(JobOrder jobOrder : jobOrderLst3)
      {
    	  jobOrder.setUpdatedBy(newUser);
          jobOrder.setUpdatedByUserName(newUserName);
          jobService.updateOnlyJobOrder(jobOrder);
      }
    }
    List<JobOrder> jobOrderLst4 = jobService.getAdminContactJobsByUserName(actualOldUserName);
    if(jobOrderLst4 != null && jobOrderLst4.size() > 0)
    {
      for(JobOrder jobOrder : jobOrderLst4)
      {
    	  jobOrder.setAdminContact(newUser);
          jobOrder.setAdminContactUserName(newUserName);
          jobService.updateOnlyJobOrder(jobOrder);
      }
    }
    /* For iTrack issue #127767 -Ends */
    /* Deleting oldUser after update with new user name */
    deleteUser(oldUser);
    return newUser;
    }

public boolean getSupervisorNameByName(String supervisorId)
{
boolean flag=false;
//List superVisorIds=getDao().find( "from User u left join fetch u.branch left join fetch u.branch.businessUnit where upper(u.loginName) like'" + supervisorId.toUpperCase() +"' and u.employeeType= '"+Constants.EMP_TYPE + "'",null);
List superVisorIds=getDao().find( "from User u left join fetch u.branch left join fetch u.branch.businessUnit where upper(u.loginName) like'" + supervisorId.toUpperCase()+"'",null);
if(superVisorIds.size()>0)
{flag=true;return flag;}
else
{throw new ServiceException("supervisor.not.exists.error", new Object[] {supervisorId}, null);}
//return flag;
}



  public void saveUser(User user)
  {
    if(user == null) return;

   User existedUser=getUserByName(user.getLoginName());
   if(existedUser== null)
    {
    throw new ServiceException("user.not.exists.error", new Object[] {user.getLoginName()}, null);
    }



   Branch existedBranch = getBranchByName(user.getBranchName());
     if(existedBranch == null)
  {throw new ServiceException("branch.not.exists.error", new Object[] {user.getBranchName()}, null); }


  /*Employee employee=user.getEmployee();
  Employee existingEmployee = null;
  //if(employee != null && employee.getId() >0)
   if(employee != null &&!"".equals(employee.getId().trim()))
    {
    existingEmployee=  getEmployeeById(employee.getId());
    if(existingEmployee==null)
    {throw new RuntimeException("No Employee id exists for  "+  employee.getId());}
    user.setEmployee(existingEmployee);

    }*/

    BusinessUnit existedBu=getBusinessUnitByName(user.getBuName());
  if(existedBu==null)
    {throw new ServiceException("bu.not.exists.error" ,new Object[] {user.getBuName()}, null);}

  /*  Employee supervisor=user.getSupervisor();
    Employee existingSupervisor = null;
  if(supervisor != null && supervisor.getId() >0)
    {
    existingSupervisor =getEmployeeById(supervisor.getId());
    if(existingSupervisor==null)
    {throw new RuntimeException("No Supervisor exists "+supervisor.getId());}
    user.setSupervisor(existingSupervisor);
    }*/
/*  else
    {
    user.setSupervisor(null);
    }*/
User supervisor=user.getSupervisor();
 String supervisorname=user.getSupervisorName();
  if(supervisorname != null &&!"".equals(supervisorname.trim()))
    {
    boolean existingSupervisor=getSupervisorNameByName(user.getSupervisorName());
    if(existingSupervisor==false)
    {throw new ServiceException("supervisor.exist.error" , new Object[] {user.getSupervisorName()}, null);}
   // {throw new RuntimeException("No Supervisor exists "+user.getSupervisorName());}
    }
        else
      user.setSupervisorName(null);





     JobCode existedJobCode=getJobCodeByName(user.getJobCodeValue());
     if(existedJobCode==null)
     {throw new ServiceException("jobcode.not.exists.error" , new Object[] {user.getJobCodeValue()}, null);}
    //{throw new RuntimeException("No JobCode Exist "+ user.getJobCodeValue());}

    TimeZone existedTimeZone = null;
      if(user.getPreferredTz() != null && (!user.getPreferredTz().trim().equals("")))
      {
        AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
        existedTimeZone=adminService.getTimeZoneByName(user.getPreferredTz());
        if(existedTimeZone==null)
        {
          throw new ServiceException("time.zone.error", new Object[] {user.getPreferredTz()}, null);

        //  throw new RuntimeException("No Such Time Zone Exists"+ user.getPreferredTz());
        }
     }

    String password = user.getPassword();

    if((password != null) && !"".equals(password.trim()))
    {
      if(!existedUser.getPassword().equals(password))
      {

        HashGenerator hashGenerator = (HashGenerator)ServiceLocator.getInstance().getBean("hashGenerator");
     user.setSalt(hashGenerator.generateSalt());
     String hashedPassword = hashGenerator.generateHash(password, user.getSalt());
        user.setPassword(hashedPassword);
      }
    }
    else user.setPassword(existedUser.getPassword());

    if((user.getPassword() == null) || "".equals(user.getPassword()))
    {
      user.setPassword(" ");
    }

    Date date = new Date();
    user.setUpdateTime(date);
  user.setBranch(existedBranch);
  user.setBusinessUnit(existedBu);
    user.setJobCode(existedJobCode);
   // user.setEmployee(existingEmployee);
 //  user.setSupervisor(existingSupervisor);
 user.setSupervisor(supervisor);
    user.setPreferredTimeZone(existedTimeZone);
    getDao().save(user);
  }

  public void searchUser(UserSearch search,String sortFlag)
  {
    if(search == null) return;
  System.out.println("Inside search user");
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;
  String strLoginNameSearch="";
  String strFirstNameSearch="";
  String strLastNameSearch="";
  String strCountrySearch="";

    String name = search.getName().getValue();

  if((name != null) && !"".equals(name.trim()))
    strLoginNameSearch = '%' + name.toUpperCase() + '%';

    if((name != null) && !"".equals(name.trim()))
    {
      sb.append(" where upper(u.loginName) like ?");
      params.add(strLoginNameSearch);
      hasWhere = true;
    }

    String firstName = search.getFirstName().getValue();

  if((firstName != null) && !"".equals(firstName.trim()))
    strFirstNameSearch = '%' + firstName.toUpperCase() + '%';

    if((firstName != null) && !"".equals(firstName.trim()))
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(u.firstName ) like ? ");
      params.add(strFirstNameSearch);
    }

    String lastName = search.getLastName().getValue();

  if((lastName != null) && !"".equals(lastName.trim()))
    strLastNameSearch = '%' + lastName.toUpperCase() + '%';


    if((lastName != null) && !"".equals(lastName.trim()))
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(u.lastName) like ? ");
      params.add(strLastNameSearch);
    }
String country=search.getCountryCode().getValue();

if((country != null) && !"".equals(country.trim()))
    strCountrySearch = country.toUpperCase();


    if((country != null) && !"".equals(country.trim()))
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(u.countryCode) like ? ");
      params.add(strCountrySearch);
    }

 /* String searchForm=search.getSearchForm();
  if((searchForm!=null)&&(searchForm.equals("userCreateForm")||searchForm.equals("userEditForm")))
    {
     if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append("u.employeeType=? ");
      params.add(Constants.EMP_TYPE);
    }*/


    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find("select  count(u.loginName) from User u "+sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
        "select  u from User u left join fetch u.branch left join fetch u.branch.businessUnit " + sb.toString()+" order by u.loginName",
        params.toArray(),
        pagination
      );

      pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
        "select  u from User u left join fetch u.branch left join fetch u.branch.businessUnit " + sb.toString()+" order by u.loginName",
        params.toArray()
      );
    }
    /*For Itrack#135193-START */
    String searchForm=search.getSearchForm();
    if((searchForm!=null)&&(searchForm.equals("editCustomer")
    		||searchForm.equals("createCustomer")||searchForm.equals("quickCreate")))
    { 
    	List accountOwnerRole = new ArrayList();
	    for(int i=0;i<results.size();i++){
	     	 User user = (User)results.get(i);
	     	 Set userRoles = user.getRoles();
	     	 for(Iterator itr= userRoles.iterator();itr.hasNext();){
	     		 Role role = (Role)itr.next();
	     		 if(role.getName().trim().equals(Constants.AccountOwnerRole)){
	     			accountOwnerRole.add(user);
	     		 }
	     	 }
	    }
 	    if(pagination != null){
		    pagination.setTotalRecord(accountOwnerRole.size());
		    pagination.calculatePages();
 	    }
	    search.setResults(accountOwnerRole);
        search.setPagination(pagination);
    } else {
    	search.setResults(results);
        search.setPagination(pagination);
    }
    /*For Itrack#135193-END */
    
  //Commented for Itrack#135193
  //  search.setResults(results);
  //  search.setPagination(pagination);
    }else
    {
      String orderByValue="";
      if(sortFlag.equals("name"))
        orderByValue=" order by u.firstName||u.lastName";
      else
        orderByValue=" order by u."+sortFlag;

      if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find("select  count(u.loginName) from User u "+sb.toString(),
            params.toArray()
          );

          if(counts.size() > 0)
          {
            Number count = (Number)counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
      }
     // START : #119240 : 22/06/09 
   /* results = getDao().find(
            "select  u from User u left join fetch u.branch left join fetch u.branch.businessUnit " + sb.toString()+orderByValue,
            params.toArray(),pagination

          );*/
     if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
	      results = getDao().find(
	              "select  u from User u left join fetch u.branch left join fetch u.branch.businessUnit " + sb.toString()+orderByValue + " " + search.getSortOrderFlag(),
	              params.toArray(),pagination
	
	            );
     }else{ 
	      results = getDao().find(
	              "select  u from User u left join fetch u.branch left join fetch u.branch.businessUnit " + sb.toString()+orderByValue,
	              params.toArray(),pagination
	
	            );
      }
     // END : #119240 : 22/06/09 
   // search.setTotalResults(results);
     
     
     /*For Itrack#135193-START */
     String searchForm=search.getSearchForm();
     if((searchForm!=null)&&(searchForm.equals("editCustomer")
     		||searchForm.equals("createCustomer")||searchForm.equals("quickCreate")))
     {
     	List accountOwnerRole = null;
 	    for(int i=0;i<results.size();i++){
 	     	 User user = (User)results.get(i);
 	     	 Set userRoles = user.getRoles();
 	     	 for(Iterator itr= userRoles.iterator();itr.hasNext();){
 	     		 Role role = (Role)itr.next();
 	     		 if(role.getName().trim().equals(Constants.AccountOwnerRole)){
 	     			accountOwnerRole.add(user);
 	     		 }
 	     	 }
 	    }
 	    if(pagination != null){
 	    pagination.setTotalRecord(new Integer(accountOwnerRole.size()).intValue());
 	    pagination.calculatePages();
 	    }
 	    search.setResults(accountOwnerRole);
        search.setPagination(pagination);
     } else {
    	 if(pagination != null){
    	 pagination.calculatePages();}
    	 search.setResults(results);
    	 search.setPagination(pagination);
     }
     /*For Itrack#135193-END */
    /*pagination.calculatePages();
    search.setResults(results);
    search.setPagination(pagination);*/
    }
  }


  public Branch getBranchByName(String name)
  {
    String brachName = "";
    if(name != null)
      brachName = name.trim().toUpperCase();
    List branches = getDao().find(
      "from Branch b left join fetch b.opsBranch left join fetch b.labBranch left join fetch b.assocBranch where upper(b.name) = ?",
      new Object[] { brachName }
    );

    if(branches.size() > 0) return (Branch)branches.get(0);

    return null;
  }

  public Branch addBranch(Branch branch)
  {
    if(branch == null) return null;

    Branch existedBranch = getBranchByName(branch.getName());
    if(existedBranch != null)
    {
      throw new ServiceException("branch.exists.error", new Object[] {branch.getName()}, null);
    }

    String buName = branch.getBuName();
    if(buName == null)
    {
     // throw new RuntimeException("No business unit for branch: " + branch.getName());
    throw new ServiceException("bu.branch.error", new Object[] {branch.getName()}, null);
    }

    BusinessUnit existedBu = getBusinessUnitByName(buName);
    if(existedBu == null)
    {
    throw new ServiceException("businessUnit.not.exist.error", new Object[] {buName}, null);
    }
    branch.setBusinessUnit(existedBu);

    Set<AssocBranch> assocBranchSet = new HashSet<AssocBranch>();
    Iterator it = branch.getAssocBranch().iterator();
    while(it.hasNext())
    {
      AssocBranch assocBranch = (AssocBranch)it.next();
      assocBranch.getAssocBranchId().setBranchName(branch.getName());

      Branch existingAssocBranch = this.getBranchByName(assocBranch.getAssocBranchId().getAssocBranchName());
      if(existingAssocBranch == null)
       // throw new RuntimeException("associate.branch.not.exist.error: " + assocBranch.getAssocBranchId().getAssocBranchName());
    throw new ServiceException("associate.branch.not.exist.error", new Object[] {assocBranch.getAssocBranchId().getAssocBranchName()}, null);
      assocBranchSet.add(assocBranch);

    }
     try{
     branch.getAssocBranch().clear();
     branch.setAssocBranch(assocBranchSet);
     }
     catch(Exception e)
     {
       System.out.println("before exception");
       e.printStackTrace();
     }
    String countryCode = branch.getCountryCode();
    if(countryCode != null && (!countryCode.trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(countryCode);
      if(existedCountry == null)throw new ServiceException("country.error", new Object[] {countryCode}, null);
      branch.setCountry(existedCountry);

      String stateCode = branch.getStateCode();
      if(stateCode != null && (!stateCode.trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          stateCode,
          countryCode
        );
        if(existedState == null)
        {
     throw new ServiceException("state.not.exist.error",new Object[] {stateCode}, null);
        }

        if(!countryCode.equals(existedState.getStateId().getCountryCode()))
        {
         // throw new ServiceException("State's country code not match: " + stateCode + ", " + existedState.getStateId().getCountryCode() + ", " + countryCode);
    throw new ServiceException("state.country.match.error", new Object[] { stateCode + ", " + existedState.getStateId().getCountryCode() + ", " + countryCode}, null);
        }

        branch.setState(existedState);
      }
      else
        branch.setStateCode(null);
    }
    else
    {
      throw new ServiceException("country.branch.error",new Object[] { branch.getName()}, null);
    }

/*  Branch opsBranch = branch.getOpsBranch();
  if(opsBranch == null)
    {
    System.out.println("inside addBranch() opsBranch is null");

    }*/

    String opsBranchName = branch.getOpsBranchName();
    if(opsBranchName != null && (!opsBranchName.trim().equals("")))
    {
      Branch opsBranch = getBranchByName(opsBranchName);
      if(opsBranch == null)
      {
        //throw new RuntimeException("Invalid ops branch name: " + opsBranchName);
     throw new ServiceException("invalid.opsbranch.error",new Object[] { opsBranchName}, null);
      }
      branch.setOpsBranch(opsBranch);
    }
    else
    {
      branch.setOpsBranchName(null);
      branch.setOpsBranch(null);
    }

    String labBranchName = branch.getLabBranchName();
    if(labBranchName != null && (!labBranchName.trim().equals("")))
    {
      Branch labBranch = getBranchByName(labBranchName);
      if(labBranch == null)
      {
      //  throw new RuntimeException("Invalid lab branch name: " + labBranchName);
     throw new ServiceException("invalid.labbranch.error",new Object[] {labBranchName}, null);
      }

      branch.setLabBranch(labBranch);
    }
    else
    {
      branch.setLabBranchName(null);
      branch.setLabBranch(null);
    }

    branch.setNewFlag(Constants.NEW);
    branch.setUpdateFlag(Constants.NEW);

       try
        {
    existedBu.getBranches().add(branch);
    getDao().flush();

      getDao().executeUpdateSql(
        "create sequence " + Constants.SEQ_PREFIX + StringUtil.mingleStr(branch.getName()),
        null
      );

    //If ops branch and lab branch exist, update BranchCode table to make the branch enabled for select charges
      if(branch!=null && branch.getName()!=null && opsBranchName != null && (!opsBranchName.trim().equals("")) && labBranchName != null && (!labBranchName.trim().equals(""))){
        BranchCode branchCode = new BranchCode();
        branchCode.setBranchCode(branch.getName());
      branchCode.setLabCode(labBranchName);
      branchCode.setOpsCode(opsBranchName);
      saveBranchCode(branchCode);
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
/*      try
        {
        Branch addedBranch = this.getBranchByName(branch.getName());
        System.out.println("existing assoc branch :"+addedBranch.getAssocBranch()+" size: "+addedBranch.getAssocBranch().size());
        Iterator iter1 = assocBranchSet.iterator();
        while(iter1.hasNext())
        {
          Branch assocBr = (Branch) iter1.next();
          addedBranch.getAssocBranch().add(assocBr);
        }

        //branch.getAssocBranch().clear();

        //branch.setAssocBranch(assocBranchSet);
        getDao().save(addedBranch);
        getDao().flush();
        }
    catch(Exception e)
    {
      e.printStackTrace();
    } */
    return branch;
  }

  public void saveBranch(Branch branch)
  {
    if(branch == null) return;

    Branch existedBranch = getBranchByName(branch.getName());
    if(existedBranch == null)
    {
    //  throw new RuntimeException("Branch not exist for name: " + branch.getName());
  throw new ServiceException("branch.not.exists.error", new Object[] {branch.getName()}, null);
    }

    String buName = branch.getBuName();
    if(buName == null)
    {
     // throw new RuntimeException("No business unit for branch: " + branch.getName());
    }

    BusinessUnit existedBu = getBusinessUnitByName(buName);
    if(existedBu == null)
    {
     // throw new RuntimeException("Business Unit not exist: " + buName);
     throw new ServiceException("businessUnit.not.exist.error", new Object[] {buName}, null);
    }

    branch.setBusinessUnit(existedBu);

    Set<AssocBranch> assocBranchSet = new HashSet<AssocBranch>();
    Iterator it = branch.getAssocBranch().iterator();
    while(it.hasNext())
    {
        AssocBranch assocBranch = (AssocBranch)it.next();
        assocBranch.getAssocBranchId().setBranchName(branch.getName());

        Branch existingAssocBranch = this.getBranchByName(assocBranch.getAssocBranchId().getAssocBranchName());
        if(existingAssocBranch == null)
          //throw new RuntimeException("Associate branch does not exist: " + assocBranch.getAssocBranchId().getAssocBranchName());
    throw new ServiceException("associate.branch.not.exist.error", new Object[] {assocBranch.getAssocBranchId().getAssocBranchName()}, null);
        System.out.println("inside iteration: assoc br name --  "+assocBranch.getAssocBranchId().getAssocBranchName());
        System.out.println("inside iteration: br name --  "+assocBranch.getAssocBranchId().getBranchName());
        assocBranchSet.add(assocBranch);

    }
   System.out.println("assoc branch srt size :"+assocBranchSet.size());
   try{
   branch.getAssocBranch().clear();
   branch.setAssocBranch(assocBranchSet);
   }
   catch(Exception e)
   {
     System.out.println("before exception");
     e.printStackTrace();
   }

    String countryCode = branch.getCountryCode();
    if(countryCode != null && (!countryCode.trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(countryCode);
      if(existedCountry == null)
    throw new ServiceException("country.error", new Object[] {countryCode}, null);
      branch.setCountry(existedCountry);

      String stateCode = branch.getStateCode();
      if(stateCode != null && (!stateCode.trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          stateCode,
          countryCode
        );
        if(existedState == null)
        {
         // throw new RuntimeException("State does not exist: " + stateCode + ", " + countryCode);
     throw new ServiceException("state.not.exist.error",new Object[] {stateCode + ", " + countryCode}, null);
        }

        if(!countryCode.equals(existedState.getStateId().getCountryCode()))
        {
    throw new ServiceException("state.country.match.error", new Object[] {stateCode + ", " + existedState.getStateId().getCountryCode() + ", " + countryCode}, null);
        }
        branch.setState(existedState);
      }
      else
        branch.setStateCode(null);
    }
    else
    {
      throw new ServiceException("country.branch.error",new Object[] {branch.getName()}, null);
    }

    String opsBranchName = branch.getOpsBranchName();
    if(opsBranchName != null && (!opsBranchName.trim().equals("")))
    {
      Branch opsBranch = getBranchByName(opsBranchName);
      if(opsBranch == null)
      {
       // throw new RuntimeException("Invalid ops branch name: " + opsBranchName);
         throw new ServiceException("invalid.opsbranch.error",new Object[] {opsBranchName}, null);
      }
      branch.setOpsBranch(opsBranch);
    }
    else
    {
      branch.setOpsBranchName(null);
      branch.setOpsBranch(null);
    }

    String labBranchName = branch.getLabBranchName();
    if(labBranchName != null && (!labBranchName.trim().equals("")))
    {
      Branch labBranch = getBranchByName(labBranchName);
      if(labBranch == null)
      {
       // throw new RuntimeException("Invalid lab branch name: " + labBranchName);
         throw new ServiceException("invalid.labbranch.error",new Object[] {labBranchName}, null);
      }

      branch.setLabBranch(labBranch);
    }
    else
    {
      branch.setLabBranchName(null);
      branch.setLabBranch(null);
    }

    branch.setUpdateFlag(Constants.NEW);

    branch = getDao().save(branch);

    //If ops branch and lab branch exist, update BranchCode table to make the branch enabled for select charges
    if(branch!=null && branch.getName()!=null && opsBranchName != null && (!opsBranchName.trim().equals("")) && labBranchName != null && (!labBranchName.trim().equals(""))){
      BranchCode branchCode = new BranchCode();
      branchCode.setBranchCode(branch.getName());
    branchCode.setLabCode(labBranchName);
    branchCode.setOpsCode(opsBranchName);
    saveBranchCode(branchCode);
    }
  }

  public void searchBranch(BranchSearch search,String sortFlag)
  {
    if(search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strNameSearch = "";
    String strDescSearch = "";
    String strBUSearch = "";
    String branchStatus="";
    String strBranchSearch="";
    String strCity    = "";
  String strStateCode = "";
  String strCountryCode ="";
// 96509
  String strAddress1 = "";

    String searchForm=search.getSearchForm();
    if(searchForm == null)
    {
      searchForm="";
    }

    String name = search.getName().getValue();
    if((name != null) && !"".equals(name.trim()))
    strNameSearch = '%' + name.toUpperCase() + '%';

    if((name != null) && !"".equals(name.trim()))
    {
      sb.append(" where upper(b.name) like ?");
      params.add(strNameSearch);
      hasWhere = true;
    }

    String desc = search.getDesc().getValue();
    if((desc != null) && !"".equals(desc.trim()))
    strDescSearch = '%' + desc.toUpperCase() + '%';

    if((desc != null) && !"".equals(desc.trim()))
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(b.description) like ? ");
      params.add(strDescSearch);
    }

    String bu = search.getBusinessUnit().getValue();
   
	  if((bu != null) && !"".equals(bu.trim()))
	    strBUSearch = '%' + bu.toUpperCase() + '%';

	    if((bu != null) && !"".equals(bu.trim()))
	    {
	      if(hasWhere) sb.append(" and ");
	      else
	      {
	        hasWhere = true;
	        sb.append(" where ");
	      }

	      sb.append(" upper(b.businessUnit.name) like ? ");
	      params.add(strBUSearch);
	  }
     String status = search.getStatus().getValue();

    if((status != null) && !"".equals(status.trim()))
    {
      branchStatus =  status.toUpperCase();
      if(hasWhere) sb.append(" and ");
      else
        {
          hasWhere = true;
          sb.append(" where ");
        }
      sb.append(" b.status = ? ");
//96509
        params.add(branchStatus);

    }
//  START 96509
    String address1 = search.getAddress1().getValue();
    if(null != address1 && !"".equals(address1)){
    	strAddress1 = '%' + address1.toUpperCase() + '%';
    	 if(hasWhere) sb.append(" and ");
         else
         {
           hasWhere = true;
           sb.append(" where ");
         }

         sb.append(" upper(b.address1) like ? ");
         params.add(strAddress1);
    }
//  END 96509

    boolean excludeEmptyType = search.getExcludeEmptyType();
    if(excludeEmptyType)
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }
      sb.append(" b.type != ' ' and b.type is not null ");
    }

    boolean excludeAdminType = search.getExcludeAdminType();
    if(excludeAdminType)
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }
      sb.append(" b.type != 'ADMN' ");
    }

 // newly added
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    strCountryCode = search.getCountry().getValue();
    strStateCode = search.getState().getValue();
    strCity = search.getCity().getValue();
    if(strCountryCode!= null && (!strCountryCode.trim().equals(""))){

    Country country = countryService.getCountryByCode(strCountryCode);
    if(country != null)
    strCountryCode = country.getCountryCode();
      if(hasWhere) sb.append(" and ");
      else
      {
          hasWhere = true;
          sb.append(" where ");
      }
    sb.append(" b.countryCode = ?");
    params.add(strCountryCode);
    hasWhere = true;
  }
  if(strStateCode != null && (!strStateCode.trim().equals("")) && strStateCode != null && (!strStateCode.trim().equals(""))){

      State state = countryService.getStateByCode(strStateCode, strCountryCode);
      if(state!= null)
        strStateCode = state.getStateId().getStateCode();
      if(hasWhere) sb.append(" and ");
      else {
        hasWhere = true;
        sb.append(" and ");
      }

      sb.append(" b.stateCode = ?");
      params.add(strStateCode);
      hasWhere = true;
  }
  if((strCity != null) && !"".equals(strCity.trim())){

      if(hasWhere)
        sb.append(" and ");
      else
      {
      sb.append(" and ");
      }
      String ct = strCity +'%';
      sb.append(" upper(b.city) like ? ");
      params.add(ct.toUpperCase());
  }
    //end

    //Check for roles having limited branch access - iTrak 50040
    User user = SecurityUtil.getUser();
    if(user.getRoles() != null && user.getRoles().size() > 0)
    {
      Set roles = user.getRoles();
      Iterator iter = roles.iterator();
      boolean limitedBranchFlag = false;
      boolean limitedBuBranchFlag = false;
      String[] limitedBranchRoles = Constants.limitedBranchRoles;
      String[] limitedBuBranchRoles = Constants.buBranchRoles;

      while(iter.hasNext())
      {
        Role role = (Role)iter.next();
        if(!limitedBranchFlag && limitedBranchRoles != null && limitedBranchRoles.length > 0)
        {
          for(int i=0;i<limitedBranchRoles.length;i++)
          {
            String roleName = limitedBranchRoles[i];
            if(role.getName().trim().equals(roleName))
            {
              limitedBranchFlag = true;
              break;
            }
          }
        }

        if(!limitedBuBranchFlag && limitedBuBranchRoles != null && limitedBuBranchRoles.length > 0)
        {
          for(int i=0;i<limitedBuBranchRoles.length;i++)
          {
            String roleName = limitedBuBranchRoles[i];
            if(role.getName().trim().equals(roleName))
            {
              limitedBuBranchFlag = true;
              break;
            }
          }
        }

      }

      if(limitedBuBranchFlag)
      {
        if(SecurityUtil.getUser().getBuName() != null && !SecurityUtil.getUser().getBuName().trim().equals(""))
        {
            if(hasWhere) sb.append(" and ");
              else
              {
                hasWhere = true;
                sb.append(" where ");
              }

              sb.append(" upper(b.businessUnit.name) = ? ");
              params.add(SecurityUtil.getUser().getBuName().toUpperCase());
        }

      }

      else if(limitedBranchFlag)
      {
        String userBranchName = user.getBranchName();
        String branchList = "( '"+userBranchName+"'";

        List assocBranches = getAssocBranchesByBranchName(userBranchName);
        if(assocBranches != null && assocBranches.size() > 0)
        {
          for(int i=0;i<assocBranches.size();i++)
          {
            Branch assocBranch = (Branch) assocBranches.get(i);
            if(assocBranch != null)
            {
              branchList = branchList + ",'"+assocBranch.getName()+"'";
            }
          }
        }
        branchList = branchList + ")";
        System.out.println("branchList: "+branchList);
        if(hasWhere) sb.append(" and ");
            else
              {
                hasWhere = true;
                sb.append(" where ");
              }
            sb.append(" b.name in  "+branchList+" ");

      }
    }
    if((searchForm != null) && !"".equals(searchForm.trim()) && (searchForm.trim().equals("opInstrForm")))
    {
      if(hasWhere) sb.append(" and ");
      else
        {
          hasWhere = true;
          sb.append(" where ");
        }

      sb.append(" b.type in (?,?) ");
        params.add("LAB");
        params.add("OPSL");

      if(search.getLimsFlag()!=null && search.getLimsFlag().equals("true")){
        sb.append(" and bi.branch=b and bi.limsInd='Y'");
      }
    }

    Pagination pagination = search.getPagination();
   // List finalresults = new ArrayList();
    List results = null;

    if(pagination != null || searchForm != null)
    {
     if(pagination != null && pagination.getTotalRecord() > 0)
      {

      /* user create form or edit form count
       if(searchForm.equals("userCreateForm")||(searchForm.equals("userEditForm"))){

         List counts = getDao().find(" select b from Branch b  " + sb.toString(),params.toArray());
          if(counts.size() > 0)
             pagination.setTotalRecord(counts.size());
             pagination.calculatePages();
        }  */



        if(searchForm.equals("serviceLocationCreateForm")){

         List counts = getDao().find(
            "select b from Branch b left join fetch b.businessUnit " + sb.toString() + "and upper(b.status)<> '"+Constants.STATUS_I +"' and b.name not in (select distinct s.branch.name from ServiceLocation s where s.branch.name <> null)",
              params.toArray()
                  );
            if(counts.size() > 0)
               pagination.setTotalRecord(counts.size());
            pagination.calculatePages();
          }
          // JobEntry branch pages calculation
          if(searchForm != null && searchForm.equals("jobsForm")||searchForm.equals("createJobsInspForm")||
                searchForm.equals("createJobsMarineForm")||searchForm.equals("createJobsOutSourcingForm")||
                searchForm.equals("createJobsAnalyticalServicesForm") || searchForm.equals("opInstrForm") || searchForm.equals("createSamLimsIntForm")){
               ArrayList newResults = new ArrayList();
               String q="";
               if(search.getLimsFlag()!=null && search.getLimsFlag().equals("true")){
                q = "select b  from Branch b left join fetch b.businessUnit, BranchInt bi" + sb.toString() + " and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.id";
               }else{
                q = "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.id";
               }
               results = getDao().find(
                  q,
                    params.toArray()
                );
             /*  List result = getDao().find(
                      "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and upper(b.type) is null and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.id",
                        params.toArray()
                    );*
               for(int i=0;i<results.size();i++)
               {
                 Branch branch=(Branch)results.get(i);
                 finalresults.add(branch);
               }

               for(int j=0;j<result.size();j++)
                 {
                   Branch branch=(Branch)result.get(j);
                   finalresults.add(branch);
               }*/
              System.out.println("Job Braches Total recorsds"+results.size());
              if(pagination != null)
              {
                /*if(finalresults.size() > 0){
                 pagination.setTotalRecord(finalresults.size());
                }*/
               pagination.setTotalRecord(results.size());
               pagination.calculatePages();
              }
             search.setPagination(pagination);
          }
          //main branch pages calculation
      if(searchForm.equals("branchCreateForm") || searchForm.equals("branchForm"))
            {
        List counts = getDao().find(
          "select count(b.id) from Branch b " + sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
                pagination.calculatePages();
      }
      
      // START : #119240 : 01/07/09 : Fix for pagination calculation in Edit branch : Branch Code popup
      if(searchForm != null && searchForm.equals("branchEditForm")){
    	  
    	  if(search.getLimsFlag()!=null && search.getLimsFlag().equals("true")){
              results = getDao().find(
                  "select b from Branch b left join fetch b.businessUnit , BranchInt bi" + sb.toString() + "  order by b.id",
                  params.toArray()  );
          }else{
              results = getDao().find(
                  "select b from Branch b left join fetch b.businessUnit " + sb.toString() + " order by b.id",
                  params.toArray() );
          }
    	  
          if(pagination != null)
          {
        	  pagination.setTotalRecord(results.size());
        	  pagination.calculatePages();
          }
         search.setPagination(pagination);          
      }
      // END : #119240 : 01/07/09 : Fix for pagination calculation in Edit branch : Branch Code popup
      
        }
        if(searchForm != null && searchForm.equals("jobsForm")||searchForm.equals("createJobsInspForm")||
           searchForm.equals("createJobsMarineForm")||searchForm.equals("createJobsOutSourcingForm")||
           searchForm.equals("createJobsAnalyticalServicesForm")||searchForm.equals("opInstrForm") || searchForm.equals("createSamLimsIntForm")){
          if(sortFlag != null && sortFlag.equals(""))
          {
           ArrayList newResults = new ArrayList();

           if(search.getLimsFlag()!=null && search.getLimsFlag().equals("true")){
            results = getDao().find(
                    "select b from Branch b left join fetch b.businessUnit, BranchInt bi" + sb.toString() + " and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.id",
                      params.toArray(),pagination
                );
           }else{
             results = getDao().find(
                    "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.id",
                      params.toArray(),pagination
                 );
           }
          /* List result = getDao().find(
                  "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and upper(b.type) is null and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.id",
                    params.toArray(),pagination
                );
           for(int i=0;i<results.size();i++)
           {
             Branch branch=(Branch)results.get(i);
             finalresults.add(branch);
           }

        for(int j=0;j<result.size();j++)
             {
               Branch branch=(Branch)result.get(j);
               finalresults.add(branch);
           }
               // new pagination code

            String pageNo=search.getPageNo();

            Branch branch = new Branch();

            int startRecord=Integer.valueOf(pageNo).intValue();
            int sortedResultsSize = finalresults.size();

            if(startRecord == 1 )
            {
              if(sortedResultsSize >= Constants.RECORDS_PAGE)
              {
                for(int i=0;i<Constants.RECORDS_PAGE;i++)
                {
                  branch = (Branch) finalresults.get(i);
                  newResults.add(branch);
                }
              }else
              {
                for(int i=0;i<sortedResultsSize;i++)
                {
                  branch = (Branch) finalresults.get(i);
                  newResults.add(branch);
                }
              }
            }else
            {
              int end=startRecord * Constants.RECORDS_PAGE;
              int start=end - Constants.RECORDS_PAGE;
              if(end<=sortedResultsSize)
              {
                for(int i=start;i<end;i++)
                {
                  branch = (Branch) finalresults.get(i);
                  newResults.add(branch);
                }
              }else
              {
                start=(startRecord -1)*Constants.RECORDS_PAGE;
                end=sortedResultsSize;
                for(int i=start;i<end;i++)
                {
                  branch = (Branch) finalresults.get(i);
                  newResults.add(branch);
                }
              }
            }
            search.setResults(newResults);*/
            search.setResults(results);
         }else
         {//job branch sorting header
           String orderByValue=" order by b."+sortFlag;
          /* results = getDao().find(
                  "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and upper(b.type) <>'"+Constants.BRANCH_TYPE +"' and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue,
                  params.toArray()
                );*/

           if(search.getLimsFlag()!=null && search.getLimsFlag().equals("true")){
        	// START : #119240
        	   /*results = getDao().find(
                          "select b from Branch b left join fetch b.businessUnit, BranchInt bi" + sb.toString() + " and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue,
                            params.toArray(),pagination
                        );*/
        	   if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
        		   results = getDao().find(
                           "select b from Branch b left join fetch b.businessUnit, BranchInt bi" + sb.toString() + " and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue +" "+ search.getSortOrderFlag(),
                             params.toArray(),pagination
                       );
        	   }else{
        		   results = getDao().find(
                           "select b from Branch b left join fetch b.businessUnit, BranchInt bi" + sb.toString() + " and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue,
                             params.toArray(),pagination
                       );
        	   }
        	// END : #119240
           }else{
        	 // START : #119240  
             /*results = getDao().find(
                          "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue,
                            params.toArray(),pagination
                        );*/
        	   if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
        		   results = getDao().find(
                       "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue+" " + search.getSortOrderFlag(),
                         params.toArray(),pagination
                     );
        	   }else{
        		   results = getDao().find(
                           "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue,
                             params.toArray(),pagination
                         ); 
        	   }
        	   // END : #119240   
           }

              /* List result = getDao().find(
                      "select b from Branch b left join fetch b.businessUnit" + sb.toString() + "and upper(b.type) is null and upper(b.status)<> '"+Constants.STATUS_I +"' "+orderByValue,
                      params.toArray(),pagination
                    );
               for(int i=0;i<results.size();i++)
               {
                 Branch branch=(Branch)results.get(i);
                 finalresults.add(branch);
               }

               for(int j=0;j<result.size();j++)
               {
                 Branch branch=(Branch)result.get(j);
                 finalresults.add(branch);
               }

             search.setTotalResults(finalresults);*/
           search.setResults(results);
         }
        }


      /*else if(searchForm.equals("userCreateForm")||(searchForm.equals("userEditForm"))){
          if(sortFlag != null && sortFlag.equals(""))
           {
            results= getDao().find(
             "select b from Branch b " + sb.toString()+ "order by b.name" , params.toArray(), pagination );


         System.out.println("inside the loop");
           search.setResults(results);
           search.setPagination(pagination);
          }else
          {
            String orderByValue=" order by b."+sortFlag;
            results= getDao().find(
                "select b from Branch b " + sb.toString()+ orderByValue, params.toArray(), pagination );
                     search.setResults(results);
                    search.setPagination(pagination);
           }
      }*/


else if(searchForm.equals("serviceLocationCreateForm")){
          // getting Service location branches
            if(!status.equals(Constants.STATUS_I))
            {
              if(sortFlag != null && sortFlag.equals(""))
                {
                results= getDao().find(
                 "select b from Branch b left join fetch b.businessUnit " + sb.toString() + "and upper(b.status)<> '"+Constants.STATUS_I +"' and b.name not in (select distinct s.branch.name from ServiceLocation s where s.branch.name <> null)",
                 params.toArray(),
                    pagination
                 );
                 search.setResults(results);
                 search.setPagination(pagination);
                }else
                {
                  String orderByValue=" order by b."+sortFlag;
                  results= getDao().find(
                        "select b from Branch b left join fetch b.businessUnit " + sb.toString() + "and upper(b.status)<> '"+Constants.STATUS_I +"' and b.name not in (select distinct s.branch.name from ServiceLocation s where s.branch.name <> null)"+orderByValue,
                        params.toArray(),pagination

                       );
                 // search.setTotalResults(finalresults);
                  search.setResults(results);
                  search.setPagination(pagination);
                }
           }else
           {
             List emptyresults = new ArrayList();
             search.setResults(emptyresults);
               search.setPagination(pagination);
           }

        }
        else{
          if(sortFlag != null && sortFlag.equals(""))
        {
            if(search.getLimsFlag()!=null && search.getLimsFlag().equals("true")){
            results = getDao().find(
                "select b from Branch b left join fetch b.businessUnit , BranchInt bi" + sb.toString() + "  order by b.id",
                params.toArray(),
                pagination
       );
            }else{
              results = getDao().find(
                          "select b from Branch b left join fetch b.businessUnit " + sb.toString() + " order by b.id",
                          params.toArray(),
                          pagination
                 );
            }
            search.setResults(results);
            search.setPagination(pagination);

          }else
          {
            String orderByValue=" order by b."+sortFlag;
            if(search.getLimsFlag()!=null && search.getLimsFlag().equals("true")){
              // START : #119240
            	/*results = getDao().find(
                        "select b from Branch b left join fetch b.businessUnit , BranchInt bi" + sb.toString()+orderByValue,
                        params.toArray(),pagination
                  );*/
            	if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
	            	results = getDao().find(
	                        "select b from Branch b left join fetch b.businessUnit , BranchInt bi" + sb.toString()+orderByValue+" " + search.getSortOrderFlag(),
	                        params.toArray(),pagination
	                  );
            	}else{
            		results = getDao().find(
                            "select b from Branch b left join fetch b.businessUnit , BranchInt bi" + sb.toString()+orderByValue,
                            params.toArray(),pagination
                      );
            	}
              // END : #119240
            }else{
            	// START : #119240 : 19/06/09
            	/*results = getDao().find(
                        "select b from Branch b left join fetch b.businessUnit " + sb.toString() +orderByValue,
                        params.toArray(),pagination
                  );*/
            	if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
            		results = getDao().find(
                            "select b from Branch b left join fetch b.businessUnit " + sb.toString() +orderByValue + " " + search.getSortOrderFlag(),
                            params.toArray(),pagination
                      );	
            	}else{
            		results = getDao().find(
                            "select b from Branch b left join fetch b.businessUnit " + sb.toString() +orderByValue,
                            params.toArray(),pagination
                      );
            	}
            	
            	// END : #119240 : 19/06/09	
            }

           // search.setTotalResults(finalresults);
            search.setResults(results);
            search.setPagination(pagination);
          }
     }
     // pagination.calculatePages();
    }
   /* else
    {
      finalresults = getDao().find(
        "select b from Branch b left join fetch b.businessUnit " + sb.toString(),
        params.toArray()
      );

      search.setResults(finalresults);
      search.setPagination(pagination);
    }*/
  }

  public List searchBranchByName(String name)
  {
    System.out.println("======= in searchBranchByName name = " + name);
    if(name == null) return new ArrayList();

    return getDao().find(
      "from Branch b where upper(b.name) like '" + name.toUpperCase() + "%'",
      null
    );
  }
  public List searchBranchesByNameAndBu(String name,String bu)
  {
    System.out.println("BU="+bu);
    System.out.println("BranchName="+name);

    if(name == null) return new ArrayList();

    List branches = null;
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();
    String strNameSearch="";
    boolean hasWhere = false;

    if((name != null) && !"".equals(name.trim()))
    {
        strNameSearch =  name.toUpperCase() + '%';
        sb.append(" where upper(b.name) like ?");
        params.add(strNameSearch);
        hasWhere = true;
    }

    if((bu != null) && !"".equals(bu.trim()))
    {
        if(hasWhere) sb.append(" and ");
        else
        {
          hasWhere = true;
          sb.append(" where ");
        }

        sb.append(" upper(b.businessUnit.name) like ? ");
        params.add(bu);
    }

    branches = getDao().find(
              "select b from Branch b left join fetch b.businessUnit " + sb.toString() + "and upper(b.status)<> '"+Constants.STATUS_I +"' and b.name not in (select distinct s.branch.name from ServiceLocation s where s.branch.name <> null)",
                params.toArray()
                    );
      return branches;
  }

  public BusinessUnit getBusinessUnitByName(String name)
  {
    List bues = getDao().find(
      "from BusinessUnit bu left join fetch bu.country left join fetch bu.organization left join fetch bu.busUnitVatLocs vatLocs left join fetch vatLocs.country ctry left join fetch ctry.countryVats where bu.name = ?",
      new Object[] { name }
    );

    if(bues.size() > 0) return (BusinessUnit)bues.get(0);

    return null;
  }

  public BusinessUnit addBusinessUnit(BusinessUnit bu)
  {
    if(bu == null) return null;

    BusinessUnit existedBu = getBusinessUnitByName(bu.getName());
    if(existedBu != null)
    {
    //  throw new RuntimeException("Business Unit exists for name" + bu.getName());
      throw new ServiceException("bu.exist.error", new Object[] {bu.getName()}, null);
    }

    Organization existedOrg = null;
    String orgName = bu.getOrgName();
    if(orgName != null)
    {
      existedOrg = getOrgByName(orgName);
    }

    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

    Iterator it = bu.getBusUnitVatLocs().iterator();
    while(it.hasNext())
    {
      BusUnitVatLoc vatLoc = (BusUnitVatLoc)it.next();

      vatLoc.setBusinessUnit(bu);

      String ctryCode = vatLoc.getBusUnitVatLocId().getCountryCode();
      if(ctryCode != null && (!ctryCode.trim().equals("")))
      {
        Country ctry = countryService.getCountryByCode(ctryCode);
       //if(ctry == null) throw new RuntimeException("Country does not exist: " + ctryCode);
     if(ctry == null) throw new ServiceException("country.error", new Object[] {ctryCode}, null);
        vatLoc.setCountry(ctry);



      }
      else
      {
       //throw new RuntimeException("Country can not be null for BusUnitVatLoc: " + bu.getName());
     throw new ServiceException("country.bu.error", new Object[] {bu.getName()}, null);
      }

    }


    //if the default BU indicator is checked , update the existing default BU object before saving new one
    //as there can be only one BU with default indicator

    if(bu.getDefaultBuInd() != null)
    {
      if(bu.getDefaultBuInd())
      {
        System.out.println(" default bu indicator :"+bu.getDefaultBuInd());
        BusinessUnit existingDefaultBu = getDefaultBusinessUnit();
        if(existingDefaultBu != null && (!existingDefaultBu.getName().trim().toUpperCase().equals(bu.getName().trim().toUpperCase())))
        {
          System.out.println("existing default bu indicator :"+existingDefaultBu.getDefaultBuInd());
          existingDefaultBu.setDefaultBuInd(false);
          getDao().save(existingDefaultBu);
        }
      }
    }

    //if handles Vat checkbox is off then clear the vat loc set
    System.out.println("handles vat :"+bu.getVatEnabledInd());
    if(bu.getVatEnabledInd() != null && (!bu.getVatEnabledInd()))
      bu.getBusUnitVatLocs().clear();

    bu.setOrganization(existedOrg);
    getDao().save(bu);
    return bu;
  }

  public void updateBusinessUnitHeaderOnly(BusinessUnit bu)
  {
    if(bu == null) return;

    Organization existedOrg = null;
    String orgName = bu.getOrgName();
    if(orgName != null)
    {
      existedOrg = getOrgByName(orgName);
    }

    BusinessUnit tmpBu = getBusinessUnitByName(bu.getName());
    if(tmpBu == null)
    {
      tmpBu = bu;
    }
    else
    {
      BusinessUnitUtil.copyBusinessUnitAttributes(bu, tmpBu);
    }

    //if the default BU indicator is checked , update the existing default BU object before saving new one
    //as there can be only one BU with default indicator
    if( (tmpBu.getDefaultBuInd() != null) && (!tmpBu.getDefaultBuInd()))
    {
      BusinessUnit existingDefaultBu = getDefaultBusinessUnit();
      if(existingDefaultBu != null
        && (!existingDefaultBu.getName().trim().toUpperCase().equals(tmpBu.getName().trim().toUpperCase())))
      {
        existingDefaultBu.setDefaultBuInd(false);
        getDao().save(existingDefaultBu);
      }
    }

    //if handles Vat checkbox is off then clear the vat loc set
    if(tmpBu.getVatEnabledInd() != null && (!tmpBu.getVatEnabledInd()))
    {
      tmpBu.getBusUnitVatLocs().clear();
    }

    tmpBu.setOrganization(existedOrg);
    getDao().save(tmpBu);
  }

  public void saveBusinessUnit(BusinessUnit bu)
  {
    if(bu == null) return;

    BusinessUnit existedBu = getBusinessUnitByName(bu.getName());
    if(existedBu == null)
       throw new ServiceException("bu.not.exist.error", new Object[] {bu.getName()}, null);

    Organization existedOrg = null;
    String orgName = bu.getOrgName();
    if(orgName != null)
    {
      existedOrg = getOrgByName(orgName);
    }

    bu.setOrganization(existedOrg);

    Iterator it = bu.getBusUnitVatLocs().iterator();
    while(it.hasNext())
    {
      BusUnitVatLoc vatLoc = (BusUnitVatLoc)it.next();

      if(vatLoc.getBusUnitVatLocId().getVatRegistrationId() == null || (vatLoc.getBusUnitVatLocId().getVatRegistrationId().trim().equals("")))
        throw new ServiceException("vat.registrationid.bu.error", new Object[] {bu.getName()}, null);

    }
    System.out.println("===11= bu.getBusUnitVatLocs().size(): " + bu.getBusUnitVatLocs().size());

    //if the default BU indicator is checked , update the existing default BU object before saving new one
    //as there can be only one BU with default indicator

    if(bu.getDefaultBuInd())
    {
      System.out.println(" default bu indicator :"+bu.getDefaultBuInd());
      BusinessUnit existingDefaultBu = getDefaultBusinessUnit();
      if(existingDefaultBu != null && (!existingDefaultBu.getName().trim().toUpperCase().equals(bu.getName().trim().toUpperCase())))
      {
        System.out.println("existing default bu indicator :"+existingDefaultBu.getDefaultBuInd());
        existingDefaultBu.setDefaultBuInd(false);
        getDao().save(existingDefaultBu);
      }
    }
    //if handles Vat checkbox is off then clear the vat loc set
    System.out.println("handles vat :"+bu.getVatEnabledInd());
    if(bu.getVatEnabledInd() != null && (!bu.getVatEnabledInd()))
        bu.getBusUnitVatLocs().clear();

    getDao().save(bu);
  }

  public List searchBusinessUnitsByName(String name)
  {
    System.out.println("======= in searchBusinessUnitsByName name = " + name);
    if(name == null) return new ArrayList();

    return getDao().find(
      "from BusinessUnit b where upper(b.name) like '" + name.toUpperCase() + "%'",
      null
    );
  }

  public void searchBusinessUnit(BusinessUnitSearch search,String sortFlag)
  {
    if(search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();
    String strNameSearch = "";
    String strDescSearch = "";
    String strCity    = "";
    String strStateCode = "";
    String strCountryCode ="";
    String strAddress1 = "";//96509

    boolean hasWhere = false;
    String name = search.getName().getValue();
    if((name != null) && !"".equals(name.trim()))
    strNameSearch = '%' + name.toUpperCase() + '%';


    if((name != null) && !"".equals(name.trim()))
    {

      sb.append(" where upper(b.name) like ?");
      params.add(strNameSearch);
      hasWhere = true;
    }

    String desc = search.getDesc().getValue();

    if((desc != null) && !"".equals(desc.trim()))
    strDescSearch = '%' + desc.toUpperCase() + '%';

    if((desc != null) && !"".equals(desc.trim()))
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" upper(b.description) like ? ");
      params.add(strDescSearch);
    }

//  START 96509
    String add1 = search.getAddress1().getValue();
    if(null !=add1 && !add1.equals("")){
    	strAddress1 = '%' + add1.toUpperCase() + '%';
    	if(hasWhere) sb.append(" and ");
        else
        {
          hasWhere = true;
          sb.append(" where ");
        }
    	 sb.append(" upper(b.address1) like ? ");
         params.add(strAddress1);
    }

//END 96509



    // newly added
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    strCountryCode = search.getCountry().getValue();
    strStateCode = search.getState().getValue();
    strCity = search.getCity().getValue();

    if(strCountryCode!= null && (!strCountryCode.trim().equals(""))){

    Country country = countryService.getCountryByCode(strCountryCode);
    if(country != null)
    strCountryCode = country.getCountryCode();
      if(hasWhere) sb.append(" and ");
      else
      {
          hasWhere = true;
          sb.append(" where ");
      }
    sb.append(" b.countryCode = ?");
    params.add(strCountryCode);
    hasWhere = true;
  }
  if(strStateCode != null && (!strStateCode.trim().equals("")) && strStateCode != null && (!strStateCode.trim().equals(""))){

      State state = countryService.getStateByCode(strStateCode, strCountryCode);
      if(state!= null)
        strStateCode = state.getStateId().getStateCode();
      if(hasWhere) sb.append(" and ");
      else {
        hasWhere = true;
        sb.append(" where ");//96509
      }

      sb.append(" b.stateCode = ?");
      params.add(strStateCode);
//      hasWhere = true; //96509
  }
  if((strCity != null) && !"".equals(strCity.trim())){

      if(hasWhere)
        sb.append(" and ");
      else
      {
    	  hasWhere = true;//96509
      sb.append(" where ");//96509
      }
      String ct = strCity +'%';
      sb.append(" upper(b.city) like ? ");
      params.add(ct.toUpperCase());
  }
    //end

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find(
          "select count(b.name) from BusinessUnit b " + sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      results = getDao().find(
        "select distinct b from BusinessUnit b " + sb.toString() + " order by b.name",
        params.toArray(),
        pagination
      );

      pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
        "select distinct b from BusinessUnit b " + sb.toString(),
        params.toArray()
      );
    }

    search.setResults(results);
    }else
    {
      String orderByValue=" order by b."+sortFlag;
      if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find(
            "select count(b.name) from BusinessUnit b " + sb.toString(),
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
      
      // START : #119240 : 19/06/09
      /*results = getDao().find(
              "select distinct b from BusinessUnit b " + sb.toString() + orderByValue,
              params.toArray(),pagination
            );*/
      	if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
      		results = getDao().find(
                    "select distinct b from BusinessUnit b " + sb.toString() + orderByValue + " " +search.getSortOrderFlag(),
                    params.toArray(),pagination
                  );	
      	}else{
		      results = getDao().find(
		              "select distinct b from BusinessUnit b " + sb.toString() + orderByValue,
		              params.toArray(),pagination
		            );
      	}
      // END : #119240 : 19/06/09
   // search.setTotalResults(results);
      search.setResults(results);
    }

  }


  public Organization getOrgByName(String name)
  {
    Organization organization=null;
  List orgs = getDao().find(
      "from Organization o where o.name = ?",
      new Object[] { name }
    );

  /*  if(orgs.size() > 0) return (Organization)orgs.get(0);
    return null;*/
  if(orgs!= null && orgs.size() >0) {
      organization = (Organization)orgs.get(0);
    } else{
          throw new ServiceException("organization.not.exist.error", new Object[] {name}, null);
    }
  return organization;


  }

  public Organization addOrg(Organization org)
  {
    if(org == null) return null;

    Organization existedOrg = getOrgByName(org.getName());
    if(existedOrg != null)
    {
   throw new ServiceException("organization.exists.error", new Object[] {org.getName()}, null);
    }

    return getDao().save(org);
  }


  public User login(String loginName, String password)
  {
    User user = null;
    List users = getDao().find(
      "from User u left join fetch u.branch branch left join fetch branch.businessUnit bu left join fetch bu.organization where upper(u.loginName) = ?",
      new Object[] {loginName.toUpperCase()}
    );

    if(users.size() > 0) user = (User)users.get(0);

    if(user != null)
    {
      HashGenerator hashGenerator = (HashGenerator)ServiceLocator.getInstance().getBean("hashGenerator");

      String hashedPassword = hashGenerator.generateHash(password, user.getSalt());
      if(hashedPassword.equals(user.getPassword()))
      {
        return user;
      }
    }

   throw new ServiceException("invalid.user.error");
  }

  public List getBusinessUnitsByDivisionName(String divisionName)
  {
    return getDao().find(
      "from BusinessUnit bu where bu.organization.name = ? order by bu.name",
      new Object[] { divisionName }
    );
  }

  public List getBranchesByDivisionAndBusinessUnitName(String divisionName, String buName)
  {
    return getDao().find(
      "from Branch b where b.businessUnit.organization.name = ? and b.businessUnit.name = ? and b.status = ? order by b.name ",
      new Object[] { divisionName, buName, Constants.STATUS_A}
    );
  }

  public List getBranchesByBusinessUnitName(String buName)
  {
    return getDao().find(
     // "from Branch b where b.businessUnit.name = ? order by b.name ",
  "from Branch b where b.businessUnit.name = ? and b.status = ? order by b.name ",
      new Object[] { buName, Constants.STATUS_A}
    );
  }

  public List getUserBranchesByBusinessUnitNameAndBranchName(String buName,String branchName)
  {
    return getDao().find(
      "from Branch b where b.businessUnit.name = ? and b.status = ? and upper(b.name) like '" + branchName.toUpperCase() + "%' order by b.name ",
      new Object[] {buName, Constants.STATUS_A}
    );
  }



  public List getBranchesByBusinessUnitNameAndBranchName(String buName,String branchName)
  {
    return getDao().find(
      "from Branch b where b.buName = ? and upper(b.name) like '" + branchName.toUpperCase() + "%' and b.status = '" + Constants.STATUS_A + "' order by b.name ",
      new Object[] { buName}
    );
  }
  public List getJobBranchesByBusinessUnitNameAndBranchName(String buName,String branchName)
  {
    List finalresults = new ArrayList();
    List results = getDao().find(
          "select b from Branch b left join fetch b.businessUnit where b.buName = ? and upper(b.name) like '" + branchName.toUpperCase() + "%' and upper(b.type) <>'"+Constants.BRANCH_TYPE +"' and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.name",
          new Object[] { buName}
        );
       List result = getDao().find(
              "select b from Branch b left join fetch b.businessUnit where b.buName = ? and upper(b.name) like '" + branchName.toUpperCase() + "%' and upper(b.type) is null and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.name",
              new Object[] { buName}
            );
       for(int i=0;i<results.size();i++)
       {
         Branch branch=(Branch)results.get(i);
         finalresults.add(branch);
       }

       for(int j=0;j<result.size();j++)
       {
         Branch branch=(Branch)result.get(j);
         finalresults.add(branch);
       }
       System.out.println("Ajax search results size="+finalresults.size());
    return finalresults;
  }
  public String getFirstBusinessUnitNameByDivisionName(String divisionName)
  {
    List buNames = getDao().find(
      "select bu.name from BusinessUnit bu where bu.organization.name = ?",
      new Object[] { divisionName }
    );
  if(buNames.size() >0) {
      return (String)buNames.get(0);
    } else{
          throw new ServiceException("businessUnit.not.exist.error",new Object[] {divisionName}, null);
    }



  }

  public List getBusinessUnitsByName(String name)
  {
  if(name == null) return new ArrayList();
  List businessUnit = new ArrayList();
  try{
    businessUnit = getDao().find(
      "from BusinessUnit c where upper(c.name) like '" + name.toUpperCase() + "%' order by c.name",
      null
    );
 } catch(Exception e ){
  System.out.println("Exception i n getBusinessUnitByName :"+ e.toString());
 }
 return businessUnit;
 }


  public List searchBranchByNameAndBranches(String name,List branch) throws Exception
  {

    System.out.println("======= in searchBranchByName name = " + name);
    if(name == null) return new ArrayList();
    System.out.println("inside search branch");


      List result;
      List finalResult = new ArrayList();


     result= getDao().find(
          "select distinct b from Branch b left join fetch b.businessUnit where upper(b.businessUnit.name) like '" + name.toUpperCase() + "%' and b.name not in (select distinct s.branch.name from ServiceLocation s where s.branch.name <> null)",
      null
    );


  return result;

  }

public List singleOrganizationByDivisionName(String divisionName)
  {

  List organization = new ArrayList();
    try{
    organization = getDao().find(
      "from Organization o left join fetch o.businessUnits bu left join fetch bu.branches where o.name = ?",
    new Object[] { divisionName }
    );
    } catch(Exception e ){
  System.out.println("Exception i n getBusinessUnitByName :"+ e.toString());
   }
 return organization;

  }


/* public List getSupervisorById(String supervisorId)
{
    if(supervisorId == null) return new ArrayList();
   List superVisorIds = new ArrayList();
try{
    superVisorIds = getDao().find("from Employee e where upper(e.supervisorId) like'"+ supervisorId.toUpperCase() + "%'", null);
    }
catch(Exception e ){
System.out.println("Exception i n getSupervisorById :"+ e.toString());
}
return superVisorIds;
}*/

 public List getSupervisorByName(String supervisorId)
{
    if(supervisorId == null) return new ArrayList();
   List superVisorIds = new ArrayList();
   String supervisor=supervisorId.toUpperCase()+"%";
try{
  //  superVisorIds =  getDao().find( "from User u left join fetch u.branch left join fetch u.branch.businessUnit where upper(u.loginName) like'" + supervisorId.toUpperCase() + "%' and u.employeeType= '1'",null);
    superVisorIds =  getDao().find( "from User u left join fetch u.branch left join fetch u.branch.businessUnit where upper(u.loginName) like'" + supervisorId.toUpperCase() + "%'",null);
   System.out.println("size of the supervisor ids"+superVisorIds.size());
  }

catch(Exception e ){
System.out.println("Exception i n getSupervisorByName :"+ e.toString());
}
return superVisorIds;
}



 public List getJobCodeByCode(String jobCode)
    {
      if(jobCode == null) return new ArrayList();
      List jobCodes = new ArrayList();
        try{
        jobCodes = getDao().find("from JobCode j  where upper(j.jobCode) like'"+ jobCode.toUpperCase() + "%'", null);
        }
                              catch(Exception e )
                {
                System.out.println("Exception i n getJobCodeByCode :"+ e.toString());
                }
           return jobCodes;
       }

   public Employee  getEmployeeById(String id)
  {
    List employees = getDao().find(
      "from Employee e where e.id = ?",
        new Object[] { id }
    );
    if(employees.size() > 0)  return (Employee)employees.get(0);

    return null;
  }

     public User  getEmpIdById(String id)
    {
    List employeees = getDao().find(
      "from User u  where u.employee.id = ?",
       new Object[] { id });

      if(employeees.size() > 0) return (User)employeees.get(0);

      return null;
    }


  public Employee  getSupervisorByIds(String supervisorId)
  {
     List employees = getDao().find(" from Employee e where e.supervisorId= ? " ,
     new Object[] {supervisorId});
      if(employees.size() > 0) return (Employee)employees.get(0);
      return null;
  }



  public BusinessUnit getDefaultBusinessUnit()
  {
    BusinessUnit bu=null;
       List defaultBu = getDao().find(" from BusinessUnit bu where bu.defaultBuInd = true " ,
      null);
      if(defaultBu.size() > 0) return (BusinessUnit)defaultBu.get(0);
      return null;

  }


public void searchJobCode(JobCodeSearch search,String sortFlag)
{
if(search == null) return;

StringBuffer sb = new StringBuffer();
List params = new ArrayList();

boolean hasWhere = false;

String strjobCodeSearch = "";
String strjobDescSearch = "";

String jobCode = search.getJobCode().getValue();
//if((jobCode != null) && !"".equals(jobCode.trim()))
strjobCodeSearch = '%'+jobCode.toUpperCase() + '%';

if((jobCode != null) && !"".equals(jobCode.trim()))
{
sb.append(" where upper(j.jobCode) like ?");
params.add(strjobCodeSearch);
hasWhere = true;
}

String description = search.getJobCodeDesc().getValue();
//if((description != null) && !"".equals(description.trim()))
strjobDescSearch ='%' + description.toUpperCase() + '%';

if((description != null) && !"".equals(description.trim()))
{
if(hasWhere) sb.append(" and ");
else
{
hasWhere = true;
sb.append(" where ");
}

sb.append(" j.jobCodeDesc like  ? ");
params.add(strjobDescSearch);
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
"select count(j.jobCode) from JobCode j " + sb.toString(),
params.toArray()
);

if(counts.size() > 0)
{
Number count = (Number)counts.get(0);
pagination.setTotalRecord(count.intValue());
}
}
System.out.println("string buffer :"+sb.toString());
results = getDao().find(
"select distinct j from JobCode j " + sb.toString() +" order by j.jobCode",
params.toArray(),
pagination
);

pagination.calculatePages();
}
else
{
results = getDao().find(
"select distinct j from JobCode j "+ sb.toString() ,
params.toArray()
);
}
search.setResults(results);
search.setPagination(pagination);
}else
{
  String orderByValue=" order by j."+sortFlag;
  if(pagination != null)
  {
  if(pagination.getTotalRecord() > 0)
  {
  List counts = getDao().find(
  "select count(j.jobCode) from JobCode j " + sb.toString(),
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
  // START : #119240 : 22/06/09
  	/*results = getDao().find(
      "select distinct j from JobCode j " + sb.toString()+orderByValue,
      params.toArray(),pagination
      );*/
	  if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
		  results = getDao().find(
			      "select distinct j from JobCode j " + sb.toString()+orderByValue + " " + search.getSortOrderFlag(),
			      params.toArray(),pagination
			      );
	  }else{
		  results = getDao().find(
			      "select distinct j from JobCode j " + sb.toString()+orderByValue,
			      params.toArray(),pagination
			      );
	  }
  // END : #119240 : 22/06/09
  
 // search.setTotalResults(results);
  search.setResults(results);
  search.setPagination(pagination);
}
}


public void searchSupervisorId(SupervisorIdSearch search)
{
if(search == null) return;

StringBuffer sb = new StringBuffer();
List params = new ArrayList();

boolean hasWhere = false;
String strsupervisorIdSearch = "";
String strbranchNameSearch = "";
String supervisor = search.getId().getValue();
if(supervisor != null)
{
// String strsupervisorIdSearch=String.valueOf(tc)+'%';
strsupervisorIdSearch ='%'+ (supervisor) + '%';
sb.append(" where str(e.id) like ?");
params.add(strsupervisorIdSearch);
hasWhere = true;
}

String branchName = search.getBranchName().getValue();
//if((branchName != null) && !"".equals(branchName.trim()))
strbranchNameSearch = '%'+ branchName.toUpperCase() + '%';

if((branchName != null) && !"".equals(branchName.trim()))
{
if(hasWhere) sb.append(" and ");
else
{
hasWhere = true;
sb.append(" where ");
}

sb.append(" e.branchName like  ? ");
params.add(strbranchNameSearch);
}

Pagination pagination = search.getPagination();
List results = null;

if(pagination != null)
{
if(pagination.getTotalRecord() > 0)
{
List counts = getDao().find(
"select count(e.id) from Employee e " + sb.toString(),
params.toArray()
);

if(counts.size() > 0)
{
Number count = (Number)counts.get(0);
pagination.setTotalRecord(count.intValue());
}
}
results = getDao().find(
"select distinct e from Employee e " + sb.toString() +" order by e.id",
params.toArray(),
pagination
);

pagination.calculatePages();
}
else
{
results = getDao().find(
"select distinct e from Employee e"  +sb.toString(),
params.toArray()
);
}
search.setResults(results);
search.setPagination(pagination);
}

public List  getUsersByName(String name)
{
if(name==null) return new ArrayList();
List userNames=new ArrayList();


    userNames=getDao().find(
"from User u where upper(u.loginName) like'"+name.toUpperCase()+"%'",
     null);
    if(userNames==null || userNames.size()==0)
      return null;
    return userNames;
}

public List getUsersByFirstName(String firstName)
{
  if(firstName==null) return new ArrayList();
  List userNames=new ArrayList();
    try{

      userNames=getDao().find(
  "from User u where upper(u.firstName) like'"+firstName.toUpperCase()+"%'",
       null);

    }
    catch (Exception e)
    {
      System.out.println("user does not exists"+e.toString());
      }
      return userNames;
}

  public void saveBusUnitVatLoc(BusUnitVatLoc busUnitVatLoc)
  {
    getDao().save(busUnitVatLoc);
  }

  public void saveOpenPeriod(OpenPeriod openPeriod)
  {
    getDao().save(openPeriod);
  }

 public void searchLogo(BranchSearch search,String sortFlag)
  {
    if(search == null) return;
  System.out.println("Inside search logo");
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;
  String strLogoNameSearch="";

    String name = search.getLogoName().getValue();
  System.out.println("logo name in search logo method is "+ name);

  if((name != null) && !"".equals(name.trim()))
    strLogoNameSearch = '%' + name.toUpperCase() + '%';

    if((name != null) && !"".equals(name.trim()))
    {
      sb.append(" where upper(l.logoName) like ?");
      params.add(strLogoNameSearch);
      hasWhere = true;
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {
   if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find("select  count(l.logoName) from Logo l "+sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
        "select  l from Logo l " + sb.toString()+" order by l.logoName",
        params.toArray(),
        pagination
      );

    pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
        "select  l from Logo l  " + sb.toString()+" order by l.logoName",
        params.toArray()
      );
   }

    search.setResults(results);
   search.setPagination(pagination);
    }else
    {
      String orderByValue="";
      if(sortFlag.equals("logoName"))
        orderByValue=" order by l.logoName";
      else
        orderByValue=" order by l."+sortFlag;
      if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find("select  count(l.logoName) from Logo l "+sb.toString(),
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
      
      // START : #119240 : 22/06/09
   /* results = getDao().find(
            "select  l from Logo l " + sb.toString() + orderByValue,
            params.toArray(),pagination

          );*/
      if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	  results = getDao().find(
                  "select  l from Logo l " + sb.toString() + orderByValue + " " + search.getSortOrderFlag(),
                  params.toArray(),pagination );
      }else{
	      results = getDao().find(
	              "select  l from Logo l " + sb.toString() + orderByValue,
	              params.toArray(),pagination
	            );
      }
      // END : #119240 : 22/06/09
   // search.setTotalResults(results);
    search.setResults(results);
    search.setPagination(pagination);
    }
  }

 public List getUsersBySupervisorName(String name)
 {
    if(name==null) return new ArrayList();
    List userNames=new ArrayList();

        userNames=getDao().find( " from User u where upper(u.supervisorName) = '"+name.toUpperCase()+"'", null);
 //if(userNames!= null && userNames.size() >0) {
    return userNames;
    //} else
      //{
      //System.out.println("getUsersBySupervisorName");
       // //  throw new ServiceException("control.not.exist.error", new Object[] {name}, null);
       // }
 }
 public void deleteUser(User user)
 {
   getDao().remove(user);
 }

   private Role saveRole(Role role){
     return getDao().save(role);
   }
public BranchCode getBranchCodeByBranchCode(String branchCode)
{
    if(branchCode==null) return null;
    List branchCodes=new ArrayList();
      try{

        branchCodes=getDao().find(
    "from BranchCode bc where upper(bc.branchCode) = '"+branchCode.toUpperCase()+"'",
         null);

      }
      catch (Exception e)
      {
        System.out.println("BranchCode does not exist"+e.toString());
       }
       if(branchCodes.size() > 0)
         return (BranchCode) branchCodes.get(0);
       return null;
}

/* For Itrack Issue # 117812 - START 17 Jun 2009*/
//public List getLabBranchesByBUAndBranchName(String buName,String branchName)
public List getLabBranchesByBranchName(String branchName)
{
   // if(branchName==null || buName == null) return null;
    List branches=new ArrayList();

      try {
        /* Commented due to Itrack issue 117812, this query returns Ops branches */
        /*branches=getDao().find(
    	" select b from Branch b left join fetch b.businessUnit, BranchInt bi where b.buName = '"+buName+"'  and bi.branch=b and bi.limsInd='Y' and upper(b.name) like '"+branchName.toUpperCase()+"%' and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.name",
         null);*/
       /* For Itrack issue 117812 START*/
    	 /* branches=getDao().find(
    			    " select b from Branch b left join fetch b.businessUnit, BranchInt bi where b.buName = '"+buName+"'  and b.type in ('LAB','OPSL') and bi.branch=b and bi.limsInd='Y' and upper(b.name) like '"+branchName.toUpperCase()+"%' and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.name",
    			         null);*/
       //End
    	  /*For Itrack issue 117812 START as comments on 15-06-09 */
    	  branches=getDao().find(
  			    " select b from Branch b left join fetch b.businessUnit, BranchInt bi where b.type in ('LAB','OPSL') and bi.branch=b and bi.limsInd='Y' and upper(b.name) like '"+branchName.toUpperCase()+"%' and (upper(b.type) <>'"+Constants.BRANCH_TYPE +"' or upper(b.type) is null) and upper(b.status)<> '"+Constants.STATUS_I +"' order by b.name",
  			         null);
    	  //End
      }
      catch (Exception e)
      {
        System.out.println("Branch does not exist"+e.toString());
      }
       if(branches.size() > 0)
         return branches;
       else
         System.out.println("LIMS Branch does not exist");
       return null;
}
/* For Itrack Issue # 117812 - END 17 Jun 2009*/

public List getAllBranchIntegrations(AddBranchInt search)
{
  Pagination pagination=search.getPagination();
    List results = null;
    String sortFlag = search.getSortFlag();
    if(sortFlag == null || sortFlag.trim().equals(""))
      sortFlag = "bi.branchName";

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find( "select count(bi.branchName) from BranchInt bi ",null);

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
      }
      }
       results = getDao().find( "select distinct bi from BranchInt bi left join fetch bi.branch br left join fetch br.businessUnit order by "+sortFlag,null, pagination);
       pagination.calculatePages();
    }
    else
    {
      results = getDao().find(  "select distinct bi from BranchInt bi left join fetch bi.branch br left join fetch br.businessUnit order by "+sortFlag, null);
    }
  search.setResults(results);
  search.setPagination(pagination);
  return results;
}
public void addBranchInt(BranchInt branchInt)
{
  branchInt=getDao().save(branchInt);
     return;
}
public boolean deleteBranchInt(BranchInt branchInt)
{
  boolean deletedFlag;
    try{
       getDao().remove(branchInt);
       deletedFlag=false;
    }
    catch(Exception e)
    {
      deletedFlag = true;
    }

      return deletedFlag;
}
public BranchInt getBranchIntByCode(String branchCode)
{
  List branchInts=getDao().find(
      "from BranchInt bi left join fetch bi.branch where bi.branchName = ?", new Object[]{branchCode});
      if(branchInts != null && branchInts.size()>0) return (BranchInt)branchInts.get(0);
      return null;
}

private List getAssocBranchesByBranchName(String branchName)
{
  List assocBranches = new ArrayList();
  Branch branch = getBranchByName(branchName);
  if(branch != null && branch.getAssocBranch() != null && branch.getAssocBranch().size() > 0)
  {
    Set assocBranchSet = branch.getAssocBranch();
    Iterator iter = assocBranchSet.iterator();
    while(iter.hasNext())
    {
      AssocBranch assocBranchItem = (AssocBranch) iter.next();
      Branch assocBranchRef = getBranchByName(assocBranchItem.getAssocBranchId().getAssocBranchName());
      if(assocBranchRef != null)
        assocBranches.add(assocBranchRef);
    }
  }


  return assocBranches;
}

/**
 * Name :getBusStreamsByBranchName
 * Date :Aug 23, 2008
 * Purpose :Finding all business streams related to the given branch
 * @param branchName
 * @return
 */
public List<String> getBusStreamsByBranchName(String branchName)
{
  return getDao().find("select busStreamId.busStreamCode from BusStream b where b.busStreamId.branchCode = ? order by b.sortOrderNum",
     new Object[] {branchName}
  );
}

/**
 * Name :businessStreamExist
 * Date :Nov 19, 2008
 * Purpose :It determines if the branch has any business stream
 * @param branchName
 * @return
 */
public boolean businessStreamExist(String branchName)
{
  long count = (Long)getDao().uniqueResult("select count(*) from BusStream b where b.busStreamId.branchCode = ? ",
     new Object[] {branchName}
  );
  if(count>0)
    return true;
  return false;
}

/**
 * Name :saveBranchLanguage
 * Date :Oct 16, 2008
 * purpose :To save the BranchLanguage data
 * @param branchLanguage
 */
public void saveBranchLanguage(BranchLanguage branchLanguage)
{
  getDao().save(branchLanguage);
}

/**
 * Name :getBranchLanguageByName
 * Date :Oct 16, 2008
 * purpose :To get the branch language details related to the given branch name and the languageCode
 * @param name
 * @param languagecode
 * @return BranchLanguage
 */
public BranchLanguage getBranchLanguageByName(String name,String languagecode)
{
  String brachName = "";
  if(name != null)
    brachName = name.trim().toUpperCase();
  List branches = getDao().find(
    "from BranchLanguage b where upper(b.branchLanguageId.name) = ? and upper(b.branchLanguageId.languageCD) =? ",
    new Object[] { brachName,languagecode.toUpperCase() }
  );
  if(branches.size() > 0) return (BranchLanguage)branches.get(0);
  return null;
}

/**
 * Name :saveBusinessUnitLanguage
 * Date :Oct 16, 2008
 * purpose :To save the business unit language
 * @param businessUnitLanguage
 */
public void saveBusinessUnitLanguage(BusinessUnitLanguage businessUnitLanguage)
{
  getDao().save(businessUnitLanguage);
}

/**
 * Name :getBuseinessUnitLanguageByName
 * Date :Oct 16, 2008
 * purpose :To get the businessUnit language details related to the given buName and the languageCode
 * @param name
 * @param languagecode
 * @return BusinessUnitLanguage
 */
public BusinessUnitLanguage getBuseinessUnitLanguageByName(String buname,String languagecode)
{
  String buName = "";
  if(buname != null)
    buName = buname.trim().toUpperCase();

  List bunames = getDao().find(
    "from BusinessUnitLanguage b where upper(b.businessUnitLanguageId.name) = ? and upper(b.businessUnitLanguageId.languageCD) =? ",
    new Object[] { buName,languagecode.toUpperCase()}
  );
  if(bunames.size() > 0) return (BusinessUnitLanguage)bunames.get(0);
   return null;
}


//up to here


public BusinessUnit getBusinessUnitByNameandDefaultLocId(String name)
{
  List bues = getDao().find(
    "from BusinessUnit bu left join fetch bu.organization left join fetch bu.busUnitVatLocs vatLocs left join fetch vatLocs.country ctry left join fetch ctry.countryVats where bu.name = ?  and vatLocs.defaultLocInd = 1 ",
    new Object[] { name}
  );

  if(bues.size() > 0) return (BusinessUnit)bues.get(0);

  return null;
}
// newly added methods on 20-01-09 regd BusinessStreams functionality
public List getAllBuStreams(AddBusinessStream search)
{
  Pagination pagination = search.getPagination();

  List results = null;

  if(pagination != null)
  {
    if(pagination.getTotalRecord() > 0)
    {
      List counts = getDao().find(" select count(*) from BusStream bs ", null);

      if(counts.size() > 0)
      {
        Number count = (Number)counts.get(0);
        pagination.setTotalRecord(count.intValue());
    }
    }
    results = getDao().find(" select distinct bs from BusStream bs left join fetch bs.branch order by bs.busStreamId.branchCode",null , pagination);
    pagination.calculatePages();
  }
  else
  {
    results = getDao().find(" select distinct bs from BusStream bs left join fetch bs.branch order by bs.busStreamId.branchCode" , null);
  }
search.setResults(results);
search.setPagination(pagination);
return results;
}
public boolean deleteBuStreams(BusStream busStream){
   boolean deletedFlag;
    boolean existedBuStreams = checkBuStreams(busStream.getBusStreamId().getBusStreamCode());
     if(existedBuStreams==true)
      {
         deletedFlag = true;
      throw new ServiceException("buStream.delete.error", new Object[]{busStream.getBusStreamId().getBusStreamCode()}, null);
      } else
      {
        System.out.println("Dleting bustream process=");

        getDao().remove(busStream);
         deletedFlag=false;
      }
     return deletedFlag;
}

public boolean checkBuStreams(String busStreamCode)
{
   boolean flag = false;
  return flag;
}

public BusStream addBuStreams(BusStream busStream)
{
  busStream = getDao().save(busStream);
    return busStream;
}
public void searchBusinessStream(AddBusinessStream search)
{
  if(search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();
    String strBranchSearch = "";
    String strBuSearch = "";
    List results = null;
    boolean hasWhere = false;
    String buName = search.getBusinessUnit().getValue();
    if((buName != null) && !"".equals(buName.trim()))
      strBuSearch =  buName.toUpperCase() ;

    if((buName != null) && !"".equals(buName.trim()))
    {
      hasWhere =true;
      sb.append(" where upper(bs.branch.buName) = ?");
      params.add(strBuSearch);
    }

    String name = search.getBranch().getValue();
    if((name != null) && !"".equals(name.trim())&& !"1".equals(name.trim()))
      strBranchSearch =  name.toUpperCase() ;

    if((name != null) && !"".equals(name.trim())&& !"1".equals(name.trim()))
    {
      if(hasWhere) sb.append(" and ");
        else
        {
          hasWhere = true;
          sb.append(" where ");
        }
        sb.append(" upper(bs.busStreamId.branchCode) = ?");
        params.add(strBranchSearch);
    }
    Pagination pagination = search.getPagination();
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find(
          "select count(*) from BusStream bs " + sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      results = getDao().find(
          "select distinct bs from BusStream bs left join fetch bs.branch " + sb.toString()+" order by bs.busStreamId.branchCode",
        params.toArray(),
        pagination
      );

      pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
        "select distinct bs from BusStream bs left join fetch bs.branch " + sb.toString()+" order by bs.busStreamId.branchCode",
        params.toArray()
      );
    }
    int i = 0;

    BusStream[] busStreams = new BusStream[results.size()];
  for(Iterator itr = results.iterator();itr.hasNext();)
  {
    busStreams[i] =(BusStream)itr.next();
    i++;
    }
    search.setBusStreams(busStreams);
}
public List getBuStreamBranchesByBuName(String buName)
{
  return getDao().find (
      "from Branch b where b.businessUnit.name = ? ",
    new Object[] { buName }
  );
}

public UserSettings getUserSettings(String loginName) {
  if(loginName==null){
    return null;
  }

  List<UserSettings> list=getDao().find("from UserSettings u where lower(u.loginName)=?", new Object[]{loginName.trim().toLowerCase()});
  if(list!=null && list.size()>0){
    return list.get(0);
  }
  return null;
}

public UserSettings saveUserSettings(UserSettings userSettings) {
  if(userSettings!=null){
    return this.getDao().save(userSettings);
  }

  return null;
}

/**
 * Name :saveBranchCode
 * Date :Feb 24, 2009
 * Purpose : Saving Branch Code for enabling select charges for the branch
 * @param branchCode
 * @return
 */
private BranchCode saveBranchCode(BranchCode branchCode){
  branchCode = getDao().save(branchCode);
  return branchCode;
 }
/* For iTrack issue 127767 -Starts */ 
/**
 * Name :getBatchReprintByUsername
 * Date :Jun 29, 2009
 * Purpose : to get BatchReprintsByUsername
 * @return List
 */
private List<BatchReprint> getBatchReprintByUsername(String oldUserName){
      List list =  getDao().find(
        "select bp from BatchReprint bp where bp.runnedByUserName=?",
        new Object[] { oldUserName }
      );
      if(!list.isEmpty() && list.size() > 0){
        return list;
      }
      return null;
}

/**
 * Name :getUserLanguageByUserName
 * Date :Jun 29, 2009
 * Purpose : to get UserLanguageByUserName
 * @return List
 */
public List<UserLanguage> getUserLanguageByUserName(String userName){
	
	List<UserLanguage> list =  getDao().find(
          "select ul from UserLanguage ul where ul.userLanguageId.loginName=?",
          new Object[] { userName}
        );
        if(!list.isEmpty() && list.size() > 0){
          return list;
        }
        return null;
}
/**
 * Name :getNotesByUserName
 * Date :Jun 29, 2009
 * Purpose : to get NotesByUserName
 * @return List
 */
public List<Notes> getNotesByUserName(String userName){
	List<Notes> list =  getDao().find(
          "select nt from Notes nt where nt.addedByUserName=?",
          new Object[] { userName}
        );
        if(!list.isEmpty() && list.size() > 0){
          return list;
        }
        return null;
}
/**
 * Name :getUserSettingsByUserName
 * Date :Jun 29, 2009
 * Purpose : to get UserSettingsByUserName
 * @return List
 */
public List<UserSettings> getUserSettingsByUserName(String userName){
	List<UserSettings> list =  getDao().find(
          "select us from UserSettings us where us.loginName=?",
          new Object[] { userName}
        );
        if(!list.isEmpty() && list.size() > 0){
          return list;
        }
        return null;
}
/**
 * Name :saveUserLanguage
 * Date :Jun 29, 2009
 * Purpose : to save userLanguage
 * @return UserLanguage
 */
public UserLanguage saveUserLanguage(UserLanguage userLanguage){
    return getDao().save(userLanguage);
}
/**
 * Name :saveNotes
 * Date :Jun 29, 2009
 * Purpose : to save notes
 */
public void saveNotes(Notes notes){
 getDao().save(notes);
}

/* For iTrack issue 127767 -Ends */ 
}
