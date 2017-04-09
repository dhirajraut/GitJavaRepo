package com.intertek.service;

import java.util.*;

import com.intertek.domain.*;
import com.intertek.entity.*;
import com.intertek.locator.*;
import com.intertek.calculator.*;
import com.intertek.util.*;
import com.intertek.pagination.*;

public class ZoneServiceImpl extends BaseService implements ZoneService
{
  public List getLocationDiscountsByContractCode(String contractCode)
  {
    if(contractCode == null) return new ArrayList();

    return  getDao().find(
      " from LocationDiscount ld  where ld.locationDiscountId.contractId = ? order by ld.locationDiscountId.beginDate desc",
      new Object[] { contractCode }
    );
  }

  public List getBranchLocationsByContractCode(String contractCode)
  {
    if(contractCode == null) return new ArrayList();

    return  getDao().find(
      " from BranchLocation bl  where bl.branchLocationId.contractId = ? order by bl.branchLocationId.beginDate desc",
      new Object[] { contractCode }
    );
  }

  public List getPortLocationsByContractCode(String contractCode)
  {
    if(contractCode == null) return new ArrayList();

    return  getDao().find(
      " from PortLocation pl  where pl.portLocationId.contractId = ? order by pl.portLocationId.beginDate desc",
      new Object[] { contractCode }
    );
  }

  public List getPriceBookZoneIds()
  {
    return getDao().findByNamedSqlQuery("getPriceBookZoneIds", null);
  }

  public List getPriceBookZoneIdsForPriceBookId(String priceBookId)
  {
    if(priceBookId == null) return new ArrayList();

    return getDao().findByNamedSqlQuery(
      "getPriceBookZoneIdsForPriceBookId",
      new Object[] { priceBookId, priceBookId, priceBookId }
    );
  }

  public List getZoneIdsForContract(String contractCode)
  {
    if(contractCode == null) return new ArrayList();

    return getDao().findByNamedSqlQuery(
      "getPriceBookZoneIdsForPriceBookId",
      new Object[] { contractCode, contractCode, contractCode }
    );
  }

  public List getZoneIdsForContractWithDate(String contractCode, String priceBookId, Date date)
  {
    if((contractCode == null) || (priceBookId == null) ||(date == null)) return new ArrayList();

    return getDao().findByNamedSqlQuery(
      "getActiveZoneIdsForContractPriceBookIdWithDate",
      new Object[] { contractCode, priceBookId, date, contractCode, priceBookId, date, contractCode, priceBookId, date }
    );
  }

  public Map getZoneIdServiceRateCountMap(String contractCode)
  {
    Map resultMap = new HashMap();

    if(contractCode == null) return resultMap;

    List results = getDao().find(
      "select sr.serviceRateId.location, count(*) from ServiceRate sr where sr.serviceRateId.contractId = ? group by sr.serviceRateId.location",
      new Object[] { contractCode }
    );

    for(int i=0; i<results.size(); i++)
    {
      Object[] objects = (Object[])results.get(i);
      resultMap.put(objects[0], objects[1]);
    }

    return resultMap;
  }

  public Map getZoneIdInspectionRateCountMap(String contractCode)
  {
    Map resultMap = new HashMap();

    if(contractCode == null) return resultMap;

    List results = getDao().find(
      "select ir.inspectionRateId.location, count(*) from InspectionRate ir where ir.inspectionRateId.contractId = ? group by ir.inspectionRateId.location",
      new Object[] { contractCode }
    );

    for(int i=0; i<results.size(); i++)
    {
      Object[] objects = (Object[])results.get(i);
      resultMap.put(objects[0], objects[1]);
    }

    return resultMap;
  }

  public Map getZoneIdTestPriceCountMap(String contractCode)
  {
    Map resultMap = new HashMap();

    if(contractCode == null) return resultMap;

    List results = getDao().find(
      "select o.testPriceId.location, count(*) from TestPrice o where o.testPriceId.contractId = ? group by o.testPriceId.location",
      new Object[] { contractCode }
    );

    for(int i=0; i<results.size(); i++)
    {
      Object[] objects = (Object[])results.get(i);
      resultMap.put(objects[0], objects[1]);
    }

    return resultMap;
  }

  public Map getZoneIdSlatePriceCountMap(String contractCode)
  {
    Map resultMap = new HashMap();

    if(contractCode == null) return resultMap;

    List results = getDao().find(
      "select o.slatePriceId.location, count(*) from SlatePrice o where o.slatePriceId.contractId = ? group by o.slatePriceId.location",
      new Object[] { contractCode }
    );

    for(int i=0; i<results.size(); i++)
    {
      Object[] objects = (Object[])results.get(i);
      resultMap.put(objects[0], objects[1]);
    }

    return resultMap;
  }

  public void saveZonesFromEditZone(EditZone editZone)
  {
    if(editZone == null) return;

    Contract contract = editZone.getContract();
    if(contract == null) return;

    Iterator it = editZone.getDeletedZoneIdSet().iterator();
    while(it.hasNext())
    {
      String zoneId = (String)it.next();
      deleteLocationDiscountsByContractIdAndZoneId(contract.getContractCode(), zoneId);
      deleteBranchLocationsByContractIdAndZoneId(contract.getContractCode(), zoneId);
      deletePortLocationsByContractIdAndZoneId(contract.getContractCode(), zoneId);
    }

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList != null)
    {
      for(int i=0; i<zoneExtList.size(); i++)
      {
        ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);

        String zoneId = zoneExt.getZoneId();
        if(zoneId != null)
        {
          deleteLocationDiscountsByContractIdAndZoneId(contract.getContractCode(), zoneId);
          deleteBranchLocationsByContractIdAndZoneId(contract.getContractCode(), zoneId);
          deletePortLocationsByContractIdAndZoneId(contract.getContractCode(), zoneId);
        }
      }

      for(int i=0; i<zoneExtList.size(); i++)
      {
        ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);

        saveLocationDiscounts(zoneExt.getLocationDiscounts());
        saveBranchLocationExts(zoneExt.getBranchLocationExts());
        savePortLocations(zoneExt.getPortLocations());
      }
    }
  }


  public void deleteLocationDiscountsByContractIdAndZoneId(String contractCode, String zoneId)
  {
    getDao().bulkUpdate(
      "delete from LocationDiscount o where o.locationDiscountId.contractId = ? and o.locationDiscountId.location = ?",
      new Object[] { contractCode, zoneId }
    );
  }

  public void deleteBranchLocationsByContractIdAndZoneId(String contractCode, String zoneId)
  {
    getDao().bulkUpdate(
      "delete from BranchLocation o where o.branchLocationId.contractId = ? and o.branchLocationId.location = ?",
      new Object[] { contractCode, zoneId }
    );
  }

  public void deletePortLocationsByContractIdAndZoneId(String contractCode, String zoneId)
  {
    getDao().bulkUpdate(
      "delete from PortLocation o where o.portLocationId.contractId = ? and o.portLocationId.location = ?",
      new Object[] { contractCode, zoneId }
    );
  }

  public void saveLocationDiscounts(List locationDiscounts)
  {
    if(locationDiscounts == null) return;

    for(int i=0; i<locationDiscounts.size(); i++)
    {
      LocationDiscount locationDiscount = (LocationDiscount)locationDiscounts.get(i);
      getDao().save(locationDiscount);
    }
  }

  public void saveBranchLocationExts(List branchLocationExts)
  {
    if(branchLocationExts == null) return;

    for(int i=0; i<branchLocationExts.size(); i++)
    {
      BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExts.get(i);
      BranchLocation branchLocation = branchLocationExt.getBranchLocation();
      getDao().save(branchLocation);
    }
  }

  public void savePortLocations(List portLocations)
  {
    if(portLocations == null) return;

    for(int i=0; i<portLocations.size(); i++)
    {
      PortLocation portLocation = (PortLocation)portLocations.get(i);
      getDao().save(portLocation);
    }
  }

  public Map getBranchDescriptionMap(Collection branchNameCol)
  {
    Map results = new HashMap();

    if(branchNameCol == null) return results;

    if(branchNameCol.size() == 0) return results;

    Object[] params = new Object[branchNameCol.size()];

    StringBuffer sb = new StringBuffer();
    sb.append("select o.name, o.description from Branch o where o.name in (");

    int count = 0;
    Iterator it = branchNameCol.iterator();
    while(it.hasNext())
    {
      String branchName = (String)it.next();
      params[count] = branchName;

      if(count > 0) sb.append(", ");
      sb.append("?");
      count ++;
    }
    sb.append(")");

    List list = getDao().find(sb.toString(), params);
    for(int i=0; i<list.size(); i++)
    {
      Object[] objects = (Object[])list.get(i);

      if((objects != null) && (objects.length == 2))
      {
        String branchName = (String)objects[0];
        String branchDescription = (String)objects[1];

        results.put(branchName, branchDescription);
      }
    }

    return results;
  }
}
