package com.intertek.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.intertek.domain.AddContract;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.CfgContractExt;
import com.intertek.domain.EditProductGroupSet;
import com.intertek.domain.EditVesselTypeSet;
import com.intertek.entity.CfgContract;
import com.intertek.entity.CfgContractId;
import com.intertek.entity.Contract;
import com.intertek.entity.ProductGroupSet;
import com.intertek.entity.RB;
import com.intertek.entity.ReferenceField;
import com.intertek.entity.ReferenceFieldId;
import com.intertek.entity.User;
import com.intertek.entity.VesselTypeSet;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.service.ProductGroupService;
import com.intertek.service.VesselTypeService;

public class ContractUtil
{
  public static CfgContractExt getCfgContractExtByIndex(AddContract addContract, String selectedIndex)
  {
    if((addContract == null) || (selectedIndex == null)) return null;

    Integer tmp = null;
    try
    {
      tmp = new Integer(selectedIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return null;
    int index = tmp.intValue();

    List list = addContract.getCfgContractExtList();
    if(list != null)
    {
      if((index >= 0) && (index < list.size())) return (CfgContractExt)list.get(index);
    }

    return null;
  }

  public static String getActiveCurrencyCD(AddContract addContract)
  {
    if(addContract == null) return null;

    CfgContractExt cfgContractExt = getActiveCfgContractExt(addContract);
    if(cfgContractExt == null) return null;

    return cfgContractExt.getCfgContract().getCurrencyCD();
  }

  public static CfgContractExt getActiveCfgContractExt(AddContract addContract)
  {
    if(addContract == null) return null;

    List list = addContract.getCfgContractExtList();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        CfgContractExt cfgContractExt = (CfgContractExt)list.get(i);
        boolean active = DateUtil.betweenDates(
          new Date(),
          cfgContractExt.getCfgContract().getCfgContractId().getBeginDate(),
          cfgContractExt.getCfgContract().getEndDate()
        );
        if(active) return cfgContractExt;
      }

      if(list.size() > 0) return (CfgContractExt)list.get(0);
    }

    return null;
  }

  public static int getActiveCfgContractIndex(AddContract addContract)
  {
    if(addContract == null) return 0;

    List list = addContract.getCfgContractExtList();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        CfgContractExt cfgContractExt = (CfgContractExt)list.get(i);
        boolean active = DateUtil.betweenDates(
          new Date(),
          cfgContractExt.getCfgContract().getCfgContractId().getBeginDate(),
          cfgContractExt.getCfgContract().getEndDate()
        );
        if(active) return i;
      }
    }

    return 0;
  }

  public static List sortCfgContractExtList(List cfgContractExtList)
  {
    if(cfgContractExtList == null) return null;

    Collections.sort(
      cfgContractExtList,
      new Comparator()
      {
        public int compare(Object o1, Object o2)
        {
          CfgContractExt p1 = (CfgContractExt)o1;
          CfgContractExt p2 = (CfgContractExt)o2;

          Date beginDate1 = p1.getCfgContract().getCfgContractId().getBeginDate();
          Date beginDate2 = p2.getCfgContract().getCfgContractId().getBeginDate();

          if(beginDate1 == null)
          {
            if(beginDate2 == null) return 0;
            else return 1;
          }
          else if(beginDate2 == null)
          {
            return -1;
          }

          return 0 - beginDate1.compareTo(beginDate2);
        }
      }
    );

    return cfgContractExtList;
  }

  public static void setWorkingPriceBookId(AddContract addContract)
  {
    if(addContract == null) return;

    CfgContractExt cfgContractExt = getActiveCfgContractExt(addContract);

    setWorkingPriceBookIdByCfgContract(addContract, cfgContractExt.getCfgContract());
  }

  public static void setWorkingPriceBookIdByCfgContract(
    AddContract addContract,
    CfgContract cfgContract
  )
  {
    if((addContract == null) || (cfgContract == null)) return;

    Contract contract = addContract.getContract();
    if(contract == null) return;

    setWorkingPriceBookIdByCfgContract(contract, cfgContract);
  }

  public static void setWorkingPriceBookIdByCfgContract(
    Contract contract,
    CfgContract cfgContract
  )
  {
    if((contract == null) || (cfgContract == null)) return;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    if(cfgContract.getPriceBookId() != null)
    {
      if(Constants.CURRENT.equals(cfgContract.getPriceBookId()))
      {
        String priceBookId = contractService.getActivePriceBookIdByCurrencyCDAndPriceBookSeries(
          cfgContract.getCurrencyCD(),
          cfgContract.getPbSeries()
        );
        contract.setWorkingPB(priceBookId);
      }
      else
      {
        //String priceBookId = contract.getWorkingPB();
        //if(priceBookId == null)
        //{
          contract.setWorkingPB(cfgContract.getPriceBookId());
        //}
      }
    }
  }

  public static void setCfgContractDataFromContract(CfgContract cfgContract, Contract contract)
  {
    if((cfgContract == null) || (contract == null)) return;

    cfgContract.getCfgContractId().setContractId(contract.getContractCode());

    String contractStatus = cfgContract.getContractStatus();
    cfgContract.setContractStatus(contract.getStatus());

    Date statusDate = cfgContract.getStatusDate();
    if(statusDate == null) cfgContract.setStatusDate(contract.getStatusDate());

    String statusUser = cfgContract.getStatusUser();
    if(statusUser == null) cfgContract.setStatusUser("U");

    String zoneAssoc = cfgContract.getZoneAssoc();
    if(zoneAssoc == null) cfgContract.setZoneAssoc("B");

    Double qualityDiscount = cfgContract.getQualityDiscount();
    if(qualityDiscount == null) cfgContract.setQualityDiscount(-1.0);

    Double quantityDiscount = cfgContract.getQuantityDiscount();
    if(quantityDiscount == null) cfgContract.setQuantityDiscount(-1.0);
  }

  public static void setCfgContractDataFromContract(AddContract addContract)
  {
    if(addContract == null) return;

    Contract contract = addContract.getContract();
    if(contract == null) return;

    List cfgContractExtList = addContract.getCfgContractExtList();
    if(cfgContractExtList != null)
    {
      for(int i=0; i<cfgContractExtList.size(); i++)
      {
        CfgContractExt cfgContractExt = (CfgContractExt)cfgContractExtList.get(i);
        setCfgContractDataFromContract(cfgContractExt.getCfgContract(), contract);
      }
    }

    List referenceFieldList = addContract.getReferenceFieldList();
    if(referenceFieldList != null)
    {
      List removedList = new ArrayList();
      int count = 1;
      for(int i=0; i<referenceFieldList.size(); i++)
      {
        ReferenceField referenceField = (ReferenceField)referenceFieldList.get(i);

        String rfIdStr = referenceField.getReferenceFieldId().getReferenceFieldId();
        if((rfIdStr == null) || "".equals(rfIdStr.trim()))
        {
          removedList.add(referenceField);
        }
        else
        {
          referenceField.getReferenceFieldId().setContractId(contract.getContractCode());
          referenceField.setSortOrderNum(count);

          count ++;
        }
      }

      referenceFieldList.removeAll(removedList);
    }
  }

  public static void addNewCfgContract(AddContract addContract)
  {
    if(addContract == null) return;

    Contract contract = addContract.getContract();
    if(contract == null) return;

    List cfgContractExtList = addContract.getCfgContractExtList();
    if(cfgContractExtList == null)
    {
      cfgContractExtList = new ArrayList();
      addContract.setCfgContractExtList(cfgContractExtList);
    }

    Date today = new Date();

    CfgContractExt cfgContractExt = new CfgContractExt();
    CfgContract cfgContract = new CfgContract();
    CfgContractId cfgContractId = new CfgContractId();
    cfgContractId.setBeginDate(today);
    cfgContractId.setContractId(contract.getContractCode());
    cfgContract.setCfgContractId(cfgContractId);
    cfgContractExt.setCfgContract(cfgContract);

    CfgContractExt activeCfgContractExt = getActiveCfgContractExt(addContract);
    if(activeCfgContractExt != null)
    {
      CfgContract activeCfgContract = activeCfgContractExt.getCfgContract();
      BeanUtil.copySimpleProperties(cfgContract, activeCfgContract, true);

      if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getProductGroupSet()))
      {
        EditProductGroupSet editProductGroupSet = ProductGroupSetUtil.copyEditProductGroupSet(
          activeCfgContractExt.getEditProductGroupSet()
        );
        editProductGroupSet.setCfgContract(cfgContract);
        editProductGroupSet.setAddContract(addContract);
        cfgContractExt.setEditProductGroupSet(editProductGroupSet);
      }

      if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getVesselSet()))
      {
        EditVesselTypeSet editVesselTypeSet = VesselTypeSetUtil.copyEditVesselTypeSet(
          activeCfgContractExt.getEditVesselTypeSet()
        );
        editVesselTypeSet.setCfgContract(cfgContract);
        editVesselTypeSet.setAddContract(addContract);
        cfgContractExt.setEditVesselTypeSet(editVesselTypeSet);
      }

      int beforeToday = DateUtil.compareToInDate(
        today,
        activeCfgContract.getCfgContractId().getBeginDate()
      );

      if(beforeToday > 0)
      {
        Date yesterday = DateUtil.addInDate(today, -1);
        activeCfgContract.setEndDate(yesterday);
      }
      else
      {
        Date dateAfterLastBeginDate = DateUtil.addInDate(
          activeCfgContract.getCfgContractId().getBeginDate(),
          1
        );
        activeCfgContract.setEndDate(activeCfgContract.getCfgContractId().getBeginDate());
        cfgContract.getCfgContractId().setBeginDate(dateAfterLastBeginDate);
      }
    }
    else
    {
      User currentUser = SecurityUtil.getUser();

      cfgContract.setCurrencyCD(currentUser.getCurrencyCd());
      cfgContract.setPriceBookId(Constants.CURRENT);
    }

    cfgContract.setEndDate(DateUtil.parseDate("12/31/2999", "MM/dd/yyyy"));
    cfgContractExtList.add(0, cfgContractExt);
  }

  public static CfgContract createInitCfgContract()
  {
    User currentUser = SecurityUtil.getUser();
    String currencyCD = null;
    if(currentUser != null) currencyCD = currentUser.getCurrencyCd();

    CfgContract cfgContract = new CfgContract();
    CfgContractId cfgContractId = new CfgContractId();
    cfgContractId.setBeginDate(new Date());
    cfgContract.setCfgContractId(cfgContractId);
    cfgContract.setEndDate(DateUtil.parseDate("12/31/2999", "MM/dd/yyyy"));
    cfgContract.setCurrencyCD(currencyCD);
    cfgContract.setPriceBookId(Constants.CURRENT);
    cfgContract.setUom(Constants.UOM_METRIC_TON_VALUE);


    VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");
    ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");

    ProductGroupSet pgs = productGroupService.getDefaultProductGroupSet();
    if(pgs != null)
    {
      cfgContract.setProductGroupSet(pgs.getProductGroupSetName());
    }
    else
    {
      cfgContract.setProductGroupSet(Constants.Pricebook);
    }

    VesselTypeSet vts = vesselTypeService.getDefaultVesselTypeSet();
    if(vts != null)
    {
      cfgContract.setVesselSet(vts.getVesselTypeSetName());
    }
    else
    {
      cfgContract.setVesselSet(Constants.Pricebook);
    }

    return cfgContract;
  }

  public static String getViewNameByTabName(String tabName)
  {
    if(tabName == null) return null;

    if("main".equals(tabName)) return "edit-contract-main-r";
    else if("customer".equals(tabName)) return "edit-contract-customer-r";
    else if("attachment".equals(tabName)) return "edit-contract-attachment-r";
    else if("zone".equals(tabName)) return "edit-contract-zone-r";
    else if("inspection".equals(tabName)) return "edit-contract-inspection-r";
    else if("service".equals(tabName)) return "edit-contract-service-r";
    else if("slate".equals(tabName)) return "edit-contract-slate-r";
    else if("test".equals(tabName)) return "edit-contract-test-r";
    else if("crtnotes".equals(tabName)) return "edit-contract-notes-r";//For iTrack #103082	
    return null;
  }

  public static ReferenceField createReferenceField(int number)
  {
    ReferenceField rf = new ReferenceField();

    ReferenceFieldId rfId = new ReferenceFieldId();
    //rfId.setReferenceFieldId("Reference #" + number);

    rf.setReferenceFieldId(rfId);
    rf.setSortOrderNum(number);

    return rf;
  }

  public static List copyReferenceFieldList(List referenceFieldList)
  {
    List results = new ArrayList();
    if(referenceFieldList == null) return results;

    for(int i=0; i<referenceFieldList.size(); i++)
    {
      ReferenceField rf = (ReferenceField)referenceFieldList.get(i);

      ReferenceField newRf = new ReferenceField();
      EntityUtil.copyReferenceField(newRf, rf);

      results.add(newRf);
    }

    return results;
  }

  public static boolean isReferenceFieldListChanged(List referenceFieldList, List pbReferenceFieldList)
  {
    if(referenceFieldList == null) return false;

    if(pbReferenceFieldList == null) return true;

    if(pbReferenceFieldList.size() != referenceFieldList.size()) return true;

    for(int i=0; i<referenceFieldList.size(); i++)
    {
      ReferenceField rf = (ReferenceField)referenceFieldList.get(i);
      ReferenceField pbRf = (ReferenceField)pbReferenceFieldList.get(i);
      boolean result = isReferenceFieldIdentical(rf, pbRf);
      if(result == false) return true;
    }

    return false;
  }

  public static boolean isReferenceFieldIdentical(ReferenceField rf, ReferenceField pbRf)
  {
    if((rf == null) && (pbRf == null)) return true;

    if((rf == null) || (pbRf == null)) return false;

    ReferenceFieldId rfId = rf.getReferenceFieldId();
    ReferenceFieldId pbRfId = pbRf.getReferenceFieldId();

    if((rfId == null) && (pbRfId == null)) return true;
    if((rfId == null) || (pbRfId == null)) return false;

    String fieldId = rfId.getReferenceFieldId();
    String pbFieldId = pbRfId.getReferenceFieldId();

    if((fieldId == null) && (pbFieldId == null)) return true;
    if((fieldId == null) || (pbFieldId == null)) return false;

    return pbFieldId.equals(fieldId);
  }

  public static Map mapRBList(List rbList)
  {
    Map results = new HashMap();

    if(rbList == null) return results;

    for(int j=0; j<rbList.size(); j++)
    {
      RB rb = (RB)rbList.get(j);
      results.put(rb.getRbId().getRbKey(), rb);
    }

    return results;
  }

  public static boolean isContractCodeValid(String contractCode)
  {
    if(contractCode == null) return false;

    for(int i=0; i<contractCode.length(); i++)
    {
      char c = contractCode.charAt(i);
      if(!Character.isLetter(c) && !Character.isDigit(c) && (c != '_')) return false;
    }

    return true;
  }

  public static String getNextContractCode(String currentContractCode, ContractSearch contractSearch, boolean prev)
  {
    if((currentContractCode == null) || (contractSearch == null)) return null;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    //contractService.searchContract(contractSearch, contractSearch.getSortFlag());

    List results = contractSearch.getResults();
    if(results == null) return null;

    for(int i=0; i<results.size(); i++)
    {
      Contract contract = (Contract)results.get(i);

      if(contract.getContractCode().equals(currentContractCode))
      {
        if(prev)
        {
          if(i == 0)
          {
            int currentPageNum = contractSearch.getPagination().getCurrentPageNum();
            if(currentPageNum > 1)
            {
              contractSearch.getPagination().setCurrentPageNum(currentPageNum - 1);
              contractService.searchContract(contractSearch, contractSearch.getSortFlag());

              List newResults = contractSearch.getResults();
              if(newResults == null) return null;

              if(newResults.size() == 0) return null;

              return ((Contract)newResults.get(newResults.size() - 1)).getContractCode();
            }
            else
            {
              return null;
            }
          }

          return ((Contract)results.get(i - 1)).getContractCode();
        }
        else
        {
          if(i == results.size() - 1)
          {
            int totalPages = contractSearch.getPagination().getTotalPages();
            int currentPageNum = contractSearch.getPagination().getCurrentPageNum();

            if(currentPageNum < totalPages)
            {
              contractSearch.getPagination().setCurrentPageNum(currentPageNum + 1);
              contractService.searchContract(contractSearch, contractSearch.getSortFlag());

              List newResults = contractSearch.getResults();
              if(newResults == null) return null;

              if(newResults.size() == 0) return null;

              return ((Contract)newResults.get(0)).getContractCode();
            }
            else
            {
              return null;
            }
          }

          return ((Contract)results.get(i + 1)).getContractCode();
        }
      }
    }

    return null;
  }

  public static CfgContract getOldCfgContractFromMap(
    CfgContract cfgContract,
    Map oldCfgContractMap
  )
  {
    if((cfgContract == null) || (oldCfgContractMap == null)) return null;

    Iterator it = oldCfgContractMap.keySet().iterator();
    while(it.hasNext())
    {
      CfgContract oldCfgContract = (CfgContract)it.next();
      CfgContract newCfgContract = (CfgContract)oldCfgContractMap.get(oldCfgContract);

      if(cfgContract == newCfgContract)
      {
        return oldCfgContract;
      }
    }

    return null;
  }
}
