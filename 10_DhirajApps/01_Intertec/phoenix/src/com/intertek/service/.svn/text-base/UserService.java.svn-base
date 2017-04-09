package com.intertek.service;

import java.util.List;

import com.intertek.domain.AddBranchInt;
import com.intertek.domain.AddBusinessStream;
import com.intertek.domain.BranchSearch;
import com.intertek.domain.BusinessUnitSearch;
import com.intertek.domain.JobCodeSearch;
import com.intertek.domain.SupervisorIdSearch;
import com.intertek.domain.UserSearch;
import com.intertek.entity.Branch;
import com.intertek.entity.BranchCode;
import com.intertek.entity.BranchInt;
import com.intertek.entity.BranchLanguage;
import com.intertek.entity.BusStream;
import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.BusinessUnitLanguage;
import com.intertek.entity.OpenPeriod;
import com.intertek.entity.Organization;
import com.intertek.entity.User;
import com.intertek.entity.UserSettings;

public interface UserService
{
  User getUserByName(String name);
  User getUsersandRolesByName(String name);
  User getUserByFirstLastName(String name);
  User getUserByNameWithPermissions(String name);
  User getUserByNameWithOrgHierarchy(String loginName);
  User addUser(User user);
  List getUsersBySupervisorName(String name);
  void deleteUser(User user);
  void saveUser(User user);
  void searchUser(UserSearch search,String sortFlag);
  User getUserByNameWithRoles(String name);

  Branch getBranchByName(String name);
  Branch addBranch(Branch branch);
  List getBranchesByBusinessUnitNameAndBranchName(String buName,String branchName);
  List getJobBranchesByBusinessUnitNameAndBranchName(String buName,String branchName);
  void saveBranch(Branch branch);
  void searchBranch(BranchSearch search,String sortFlag);
  List getUserBranchesByBusinessUnitNameAndBranchName(String buName,String branchName);

  BusinessUnit getBusinessUnitByName(String name);
  BusinessUnit addBusinessUnit(BusinessUnit bu);
  void updateBusinessUnitHeaderOnly(BusinessUnit bu);
  void saveBusinessUnit(BusinessUnit bu);
  void searchBusinessUnit(BusinessUnitSearch search,String sortFlag);
  List searchBusinessUnitsByName(String name);

  Organization getOrgByName(String name);
  Organization addOrg(Organization org);



  User login(String email, String password);

  List getBusinessUnitsByDivisionName(String divisionName);
  List getBranchesByBusinessUnitName(String buName);
  List getBranchesByDivisionAndBusinessUnitName(String divisionName, String buName);
  String getFirstBusinessUnitNameByDivisionName(String divisionName);
  List searchBranchByNameAndBranches(String name,List branch)throws Exception;
  List singleOrganizationByDivisionName(String divisionName);
  List getJobCodeByCode(String jobCode);


  //List getSupervisorById(String supervisorId);
  List getSupervisorByName(String supervisorId);
  List getBusinessUnitsByName(String name);
  List searchBranchByName(String name);
  List getUsersByName(String name);
  List searchBranchesByNameAndBu(String name,String bu);


  void searchJobCode(JobCodeSearch search,String sortFlag);
  void searchSupervisorId(SupervisorIdSearch search);

  void saveBusUnitVatLoc(BusUnitVatLoc busUnitVatLoc);
  void saveOpenPeriod(OpenPeriod openPeriod);
  void searchLogo(BranchSearch search,String sortFlag);

  List getUsersByFirstName(String firstName);
  User changeUserLoginName(String oldLoginName, String newLoginName);
  BranchCode getBranchCodeByBranchCode(String branchCode);
 /* For Itrack Issue # 117812 - START 17 Jun 2009*/
  //List getLabBranchesByBUAndBranchName(String buName,String branchName);
  List getLabBranchesByBranchName(String branchName);
 /* For Itrack Issue # 117812 - END 17 Jun 2009*/

  List getAllBranchIntegrations(AddBranchInt addBranchInt);
  void addBranchInt(BranchInt branchInt);
  boolean deleteBranchInt(BranchInt branchInt);
  BranchInt getBranchIntByCode(String branchCode);
  List getBusStreamsByBranchName(String branchName);
  boolean businessStreamExist(String branchName);

  void saveBranchLanguage(BranchLanguage branchLanguage);
  BranchLanguage getBranchLanguageByName(String name,String languagecode);
  void saveBusinessUnitLanguage(BusinessUnitLanguage businessUnitLanguage);
  BusinessUnitLanguage getBuseinessUnitLanguageByName(String buname,String languagecode);
  public BusinessUnit getBusinessUnitByNameandDefaultLocId(String name);

  List getAllBuStreams(AddBusinessStream search);
  boolean deleteBuStreams(BusStream busStream);
  BusStream addBuStreams(BusStream busStream);
  void searchBusinessStream(AddBusinessStream search);
  List getBuStreamBranchesByBuName(String name);

  UserSettings getUserSettings(String loginName);
  UserSettings saveUserSettings(UserSettings userSettings);
}

