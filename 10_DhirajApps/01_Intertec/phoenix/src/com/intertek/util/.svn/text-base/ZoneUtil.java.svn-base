package com.intertek.util;

import java.io.*;
import java.util.*;

import com.intertek.locator.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.pagination.*;
import com.intertek.domain.*;
import com.intertek.entity.*;
import com.intertek.meta.*;
import com.intertek.meta.dropdown.*;
import com.intertek.servicetype.*;
import com.intertek.calculator.*;

public class ZoneUtil
{
  public static List getZoneExtsByContractCode(String contractCode)
  {
    List results = new ArrayList();
    if(contractCode == null) return results;

    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");
    List locationDiscounts = zoneService.getLocationDiscountsByContractCode(contractCode);
    List branchLocations = zoneService.getBranchLocationsByContractCode(contractCode);
    List portLocations = zoneService.getPortLocationsByContractCode(contractCode);

    Map zoneExtMap = new HashMap();

    for(int i=0; i<locationDiscounts.size(); i++)
    {
      LocationDiscount ld = (LocationDiscount)locationDiscounts.get(i);

      String zoneId = ld.getLocationDiscountId().getLocation();

      ZoneExt zoneExt = (ZoneExt)zoneExtMap.get(zoneId);
      if(zoneExt == null)
      {
        zoneExt = new ZoneExt();
        zoneExt.setZoneId(zoneId);

        zoneExtMap.put(zoneId, zoneExt);
      }

      zoneExt.getLocationDiscounts().add(ld);
    }

    for(int i=0; i<branchLocations.size(); i++)
    {
      BranchLocation ld = (BranchLocation)branchLocations.get(i);

      String zoneId = ld.getBranchLocationId().getLocation();

      ZoneExt zoneExt = (ZoneExt)zoneExtMap.get(zoneId);
      if(zoneExt == null)
      {
        zoneExt = new ZoneExt();
        zoneExt.setZoneId(zoneId);

        zoneExtMap.put(zoneId, zoneExt);
      }

      BranchLocationExt ldExt = new BranchLocationExt();
      ldExt.setBranchLocation(ld);

      zoneExt.getBranchLocationExts().add(ldExt);
    }

    for(int i=0; i<portLocations.size(); i++)
    {
      PortLocation ld = (PortLocation)portLocations.get(i);

      String zoneId = ld.getPortLocationId().getLocation();

      ZoneExt zoneExt = (ZoneExt)zoneExtMap.get(zoneId);
      if(zoneExt == null)
      {
        zoneExt = new ZoneExt();
        zoneExt.setZoneId(zoneId);

        zoneExtMap.put(zoneId, zoneExt);
      }

      zoneExt.getPortLocations().add(ld);
    }

    Map serviceRateMap = null;
    Map inspectionRateMap = null;
    Map testPriceMap = null;
    Map slatePriceMap = null;
    if(zoneExtMap.size() > 0)
    {
      serviceRateMap = zoneService.getZoneIdServiceRateCountMap(contractCode);
      inspectionRateMap = zoneService.getZoneIdInspectionRateCountMap(contractCode);
      testPriceMap = zoneService.getZoneIdTestPriceCountMap(contractCode);
      slatePriceMap = zoneService.getZoneIdSlatePriceCountMap(contractCode);
    }

    Iterator it = zoneExtMap.keySet().iterator();
    while(it.hasNext())
    {
      String zoneId = (String)it.next();
      ZoneExt zoneExt = (ZoneExt)zoneExtMap.get(zoneId);

      boolean editable = true;

      if(serviceRateMap != null)
      {
        Number number = (Number)serviceRateMap.get(zoneId);
        if((number != null) && (number.intValue() > 0)) editable = false;
      }

      if(inspectionRateMap != null)
      {
        Number number = (Number)inspectionRateMap.get(zoneId);
        if((number != null) && (number.intValue() > 0)) editable = false;
      }

      if(testPriceMap != null)
      {
        Number number = (Number)testPriceMap.get(zoneId);
        if((number != null) && (number.intValue() > 0)) editable = false;
      }

      if(slatePriceMap != null)
      {
        Number number = (Number)slatePriceMap.get(zoneId);
        if((number != null) && (number.intValue() > 0)) editable = false;
      }

      zoneExt.setEditable(editable);

      results.add(zoneExt);
    }

    return results;
  }

  public static void setCustomFlagForZoneExtList(
    List zoneExtList,
    List priceBookZoneIdList
  )
  {
    if((zoneExtList == null) || (priceBookZoneIdList == null)) return;

    for(int i=0; i<zoneExtList.size(); i++)
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);

      setCustomFlagForZoneExt(zoneExt, priceBookZoneIdList);
    }
  }

  public static void setCustomFlagForZoneExt(
    ZoneExt zoneExt,
    List priceBookZoneIdList
  )
  {
    if((zoneExt == null) || (priceBookZoneIdList == null)) return;

    if(priceBookZoneIdList.contains(zoneExt.getZoneId()))
    {
      zoneExt.setCustomFlag(Constants.Pricebook);
      zoneExt.setPriceBookZoneId(zoneExt.getZoneId());
    }
    else
    {
      zoneExt.setCustomFlag(Constants.Custom);
      zoneExt.setCustomZoneId(zoneExt.getZoneId());
    }
  }

  public static void addZone(EditZone editZone)
  {
    if(editZone == null) return;
    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");

    List priceBookZoneIdList = editZone.getPriceBookZoneIdList();
    if(priceBookZoneIdList == null)
    {
      priceBookZoneIdList = zoneService.getPriceBookZoneIds();
      editZone.setPriceBookZoneIdList(priceBookZoneIdList);
    }

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null)
    {
      zoneExtList = new ArrayList();
      editZone.setZoneExtList(zoneExtList);
    }

    int numZonesToAdd = editZone.getNumZonesToAdd();
    if(numZonesToAdd <= 0) numZonesToAdd = 1;

    for(int i=0; i<numZonesToAdd; i++)
    {
      ZoneExt zoneExt = new ZoneExt();
      zoneExt.setCustomFlag(Constants.Pricebook);
      zoneExt.setDisplayStatus("SHOW");
      zoneExtList.add(0, zoneExt);
      zoneExt.setPriceBookZoneId("");

      zoneExt.getLocationDiscounts().add(createLocationDiscount());
      //zoneExt.getBranchLocations().add(createBranchLocation());
      //zoneExt.getPortLocations().add(createPortLocation());
    }
  }


  public static ZoneExt getZoneExtByIndexStr(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return null;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return null;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return null;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);
      return zoneExt;
    }

    return null;
  }

  public static void deleteZone(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.remove(index);

      if(zoneExt.getZoneId() != null)
      {
        editZone.getDeletedZoneIdSet().add(zoneExt.getZoneId());
      }
    }
  }

  public static void clearCustomOrPriceBookZoneId(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      if(zoneExt == null) return;

      if(Constants.Custom.equals(zoneExt.getCustomFlag()))
      {
        zoneExt.setPriceBookZoneId("");
      }
      else
      {
        zoneExt.setCustomZoneId("");
      }
    }
  }

  public static LocationDiscount createLocationDiscount()
  {
    LocationDiscount locationDiscount = new LocationDiscount();

    LocationDiscountId locationDiscountId = new LocationDiscountId();
    locationDiscount.setLocationDiscountId(locationDiscountId);

    locationDiscountId.setBeginDate(new Date());
    locationDiscount.setEndDate(DateUtil.parseDate("12/31/2099", "MM/dd/yyyy"));
    locationDiscount.setCurrencyCD("USD");

    return locationDiscount;
  }

  public static BranchLocation createBranchLocation()
  {
    BranchLocation branchLocation = new BranchLocation();

    BranchLocationId branchLocationId = new BranchLocationId();
    branchLocation.setBranchLocationId(branchLocationId);

    branchLocationId.setBeginDate(new Date());
    branchLocation.setEndDate(DateUtil.parseDate("12/31/2099", "MM/dd/yyyy"));

    return branchLocation;
  }

  public static PortLocation createPortLocation()
  {
    PortLocation portLocation = new PortLocation();

    PortLocationId portLocationId = new PortLocationId();
    portLocation.setPortLocationId(portLocationId);

    portLocationId.setBeginDate(new Date());
    portLocation.setEndDate(DateUtil.parseDate("12/31/2099", "MM/dd/yyyy"));

    return portLocation;
  }

  public static void addLocationDiscount(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      LocationDiscount locationDiscount = createLocationDiscount();
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      if(zoneExt.getLocationDiscounts().size() > 0)
      {
        LocationDiscount lastLocationDiscount = (LocationDiscount)zoneExt.getLocationDiscounts().get(0);

        Date[] newDatePair = new Date[] {
          locationDiscount.getLocationDiscountId().getBeginDate(),
          locationDiscount.getEndDate()
        };

        Date[] oldDatePair = new Date[] {
          lastLocationDiscount.getLocationDiscountId().getBeginDate(),
          lastLocationDiscount.getEndDate()
        };

        DateUtil.calculateBeginDateAndEndDate(newDatePair, oldDatePair);

        locationDiscount.getLocationDiscountId().setBeginDate(newDatePair[0]);
        locationDiscount.setEndDate(newDatePair[1]);

        lastLocationDiscount.getLocationDiscountId().setBeginDate(oldDatePair[0]);
        lastLocationDiscount.setEndDate(oldDatePair[1]);
      }

      zoneExt.getLocationDiscounts().add(0, locationDiscount);
    }
  }

  public static void addBranchLocation(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      BranchLocation branchLocation = createBranchLocation();
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      if(zoneExt.getBranchLocationExts().size() > 0)
      {
        BranchLocationExt lastBranchLocationExt = (BranchLocationExt)zoneExt.getBranchLocationExts().get(0);
        BranchLocation lastBranchLocation = lastBranchLocationExt.getBranchLocation();

        Date[] newDatePair = new Date[] {
          branchLocation.getBranchLocationId().getBeginDate(),
          branchLocation.getEndDate()
        };

        Date[] oldDatePair = new Date[] {
          lastBranchLocation.getBranchLocationId().getBeginDate(),
          lastBranchLocation.getEndDate()
        };

        DateUtil.calculateBeginDateAndEndDate(newDatePair, oldDatePair);

        branchLocation.getBranchLocationId().setBeginDate(newDatePair[0]);
        branchLocation.setEndDate(newDatePair[1]);

        lastBranchLocation.getBranchLocationId().setBeginDate(oldDatePair[0]);
        lastBranchLocation.setEndDate(oldDatePair[1]);
      }

      BranchLocationExt branchLocationExt = new BranchLocationExt();
      branchLocationExt.setBranchLocation(branchLocation);

      zoneExt.getBranchLocationExts().add(0, branchLocationExt);
    }
  }

  public static void addPortLocation(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      PortLocation portLocation = createPortLocation();
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      if(zoneExt.getPortLocations().size() > 0)
      {
        PortLocation lastPortLocation = (PortLocation)zoneExt.getPortLocations().get(0);

        Date[] newDatePair = new Date[] {
          portLocation.getPortLocationId().getBeginDate(),
          portLocation.getEndDate()
        };

        Date[] oldDatePair = new Date[] {
          lastPortLocation.getPortLocationId().getBeginDate(),
          lastPortLocation.getEndDate()
        };

        DateUtil.calculateBeginDateAndEndDate(newDatePair, oldDatePair);

        portLocation.getPortLocationId().setBeginDate(newDatePair[0]);
        portLocation.setEndDate(newDatePair[1]);

        lastPortLocation.getPortLocationId().setBeginDate(oldDatePair[0]);
        lastPortLocation.setEndDate(oldDatePair[1]);
      }

      zoneExt.getPortLocations().add(0, portLocation);
    }
  }

  public static void deleteLocationDiscount(EditZone editZone, String zoneIndexStr, String zoneSubIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null) || (zoneSubIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    tmp = null;
    try { tmp = new Integer(zoneSubIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int subIndex = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      if((subIndex >= 0) && (subIndex < zoneExt.getLocationDiscounts().size()))
      {
        zoneExt.getLocationDiscounts().remove(subIndex);
      }
    }
  }

  public static void deleteBranchLocation(EditZone editZone, String zoneIndexStr, String zoneSubIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null) || (zoneSubIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    tmp = null;
    try { tmp = new Integer(zoneSubIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int subIndex = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      if((subIndex >= 0) && (subIndex < zoneExt.getBranchLocationExts().size()))
      {
        zoneExt.getBranchLocationExts().remove(subIndex);
      }
    }
  }

  public static void deleteSelectedForBranchLocations(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      List branchLocationExts = zoneExt.getBranchLocationExts();
      if(branchLocationExts == null) return;

      List deletedList = new ArrayList();

      for(int i=0; i<branchLocationExts.size(); i++)
      {
        BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExts.get(i);
        if(branchLocationExt.getChecked())
        {
          deletedList.add(branchLocationExt);
        }
      }

      branchLocationExts.removeAll(deletedList);
    }
  }

  public static void applyDatesForAllBranches(EditZone editZone, String zoneIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      List branchLocationExts = zoneExt.getBranchLocationExts();
      if(branchLocationExts == null) return;

      Date newBeginDate = zoneExt.getBeginDate();
      Date newEndDate = zoneExt.getEndDate();

      for(int i=0; i<branchLocationExts.size(); i++)
      {
        BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExts.get(i);
        if(branchLocationExt.getChecked())
        {
          if(newBeginDate != null)
          {
            branchLocationExt.getBranchLocation().getBranchLocationId().setBeginDate(newBeginDate);
          }

          if(newEndDate != null)
          {
            branchLocationExt.getBranchLocation().setEndDate(newEndDate);
          }
        }
      }
    }
  }

  public static void deletePortLocation(EditZone editZone, String zoneIndexStr, String zoneSubIndexStr)
  {
    if((editZone == null) || (zoneIndexStr == null) || (zoneSubIndexStr == null)) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    Integer tmp = null;
    try { tmp = new Integer(zoneIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int index = tmp.intValue();

    tmp = null;
    try { tmp = new Integer(zoneSubIndexStr); } catch(Exception e) { }

    if(tmp == null) return;
    int subIndex = tmp.intValue();

    if((index >= 0) && (index < zoneExtList.size()))
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(index);

      if((subIndex >= 0) && (subIndex < zoneExt.getPortLocations().size()))
      {
        zoneExt.getPortLocations().remove(subIndex);
      }
    }
  }

  public static void packZoneData(EditZone editZone)
  {
    if(editZone == null) return;

    Contract contract = editZone.getContract();
    if(contract == null) return;

    List zoneExtList = editZone.getZoneExtList();
    if(zoneExtList == null) return;

    for(int i=0; i<zoneExtList.size(); i++)
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);

      String newZoneId = null;
      if(Constants.Custom.equals(zoneExt.getCustomFlag()))
      {
        newZoneId = zoneExt.getCustomZoneId();
      }
      else
      {
        newZoneId = zoneExt.getPriceBookZoneId();
      }

      List locationDiscounts = zoneExt.getLocationDiscounts();
      for(int j=0; j<locationDiscounts.size(); j++)
      {
        LocationDiscount locationDiscount = (LocationDiscount)locationDiscounts.get(j);
        locationDiscount.getLocationDiscountId().setContractId(contract.getContractCode());
        locationDiscount.getLocationDiscountId().setLocation(newZoneId);
      }

      List branchLocationExts = zoneExt.getBranchLocationExts();
      for(int j=0; j<branchLocationExts.size(); j++)
      {
        BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExts.get(j);
        BranchLocation branchLocation = branchLocationExt.getBranchLocation();
        branchLocation.getBranchLocationId().setContractId(contract.getContractCode());
        branchLocation.getBranchLocationId().setLocation(newZoneId);
      }

      List portLocations = zoneExt.getPortLocations();
      for(int j=0; j<portLocations.size(); j++)
      {
        PortLocation portLocation = (PortLocation)portLocations.get(j);
        portLocation.getPortLocationId().setContractId(contract.getContractCode());
        portLocation.getPortLocationId().setLocation(newZoneId);
      }
    }
  }

  public static BranchLocationExt getNextBranchLocationExt(List branchLocationExts, String buName, String branchCode, int startIndex)
  {
    if((branchLocationExts == null) || (buName == null) || (branchCode == null)) return null;

    for(int i=startIndex; i<branchLocationExts.size(); i++)
    {
      BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExts.get(i);
      if(buName.equals(branchLocationExt.getBranchLocation().getBranchLocationId().getBranchSetId())
        && branchCode.equals(branchLocationExt.getBranchLocation().getBranchLocationId().getBranchCode()))
      {
        return branchLocationExt;
      }
    }

    return null;
  }

  public static PortLocation getNextPortLocation(List portLocations, String portCode, int startIndex)
  {
    if((portLocations == null) || (portCode == null)) return null;

    for(int i=startIndex; i<portLocations.size(); i++)
    {
      PortLocation portLocation = (PortLocation)portLocations.get(i);
      if(portCode.equals(portLocation.getPortLocationId().getPortCode()))
      {
        return portLocation;
      }
    }

    return null;
  }

  public static boolean existZoneExtByZoneId(List zoneExtList, String zoneId, int startIndex)
  {
    if((zoneExtList == null) || (zoneId == null)) return false;

    for(int i=startIndex; i<zoneExtList.size(); i++)
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);

      String nextZoneId = null;
      if(Constants.Custom.equals(zoneExt.getCustomFlag()))
      {
        nextZoneId = zoneExt.getCustomZoneId();
        if(nextZoneId != null) nextZoneId = nextZoneId.trim();
      }
      else
      {
        nextZoneId = zoneExt.getPriceBookZoneId();
      }

      if((nextZoneId != null) && zoneId.equals(nextZoneId)) return true;
    }

    return false;
  }

  public static List constructBranchExtListFromBranchList(List branchList)
  {
    List results = new ArrayList();

    if(branchList == null) return results;

    for(int i=0; i<branchList.size(); i++)
    {
      Branch branch = (Branch)branchList.get(i);

      BranchExt branchExt = new BranchExt();
      branchExt.setBranch(branch);

      results.add(branchExt);
    }

    return results;
  }

  public static List constructBranchExtListFromBranchLocationExtList(List branchLocationExtList)
  {
    List results = new ArrayList();

    if(branchLocationExtList == null) return results;

    for(int i=0; i<branchLocationExtList.size(); i++)
    {
      BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExtList.get(i);

      BranchExt branchExt = createBranchExt(
        branchLocationExt.getBranchLocation().getBranchLocationId().getBranchSetId(),
        branchLocationExt.getBranchLocation().getBranchLocationId().getBranchCode(),
        branchLocationExt.getBranchDescription()
      );

      branchExt.setViewOnly(true);

      results.add(branchExt);
    }

    return results;
  }

  public static void addSelectedBranchList(SelectBranches selectBranches)
  {
    if(selectBranches == null) return;

    List selectedBranchExtList = selectBranches.getSelectedBranchExtList();
    Set selectedSet = new HashSet();

    for(int i=0; i<selectedBranchExtList.size(); i++)
    {
      BranchExt branchExt = (BranchExt)selectedBranchExtList.get(i);
      selectedSet.add(branchExt.getBranch().getName());
    }


    List allBranchExtList = selectBranches.getAllBranchExtList();
    for(int i=0; i<allBranchExtList.size(); i++)
    {
      BranchExt branchExt = (BranchExt)allBranchExtList.get(i);

      if(branchExt.getChecked())
      {
        if(!selectedSet.contains(branchExt.getBranch().getName()))
        {
          BranchExt newBranchExt = createBranchExt(
            branchExt.getBranch().getBusinessUnit().getName(),
            branchExt.getBranch().getName(),
            branchExt.getBranch().getDescription()
          );

          selectedBranchExtList.add(newBranchExt);
        }
      }
    }
  }

  public static void removeSelectedBranchList(SelectBranches selectBranches)
  {
    if(selectBranches == null) return;

    List selectedBranchExtList = selectBranches.getSelectedBranchExtList();
    List deleteList = new ArrayList();

    for(int i=0; i<selectedBranchExtList.size(); i++)
    {
      BranchExt branchExt = (BranchExt)selectedBranchExtList.get(i);
      if(branchExt.getChecked())
      {
        deleteList.add(branchExt);
      }
    }

    selectedBranchExtList.removeAll(deleteList);
  }

  public static void createBranchLocationListFromSelectedBranchList(SelectBranches selectBranches)
  {
    if(selectBranches == null) return;

    ZoneExt zoneExt = selectBranches.getZoneExt();
    if(zoneExt == null) return;

    List branchLocationExts = zoneExt.getBranchLocationExts();
    if(branchLocationExts == null)
    {
      branchLocationExts = new ArrayList();
      zoneExt.setBranchLocationExts(branchLocationExts);
    }

    Set existedSet = new HashSet();
    for(int i=0; i<branchLocationExts.size(); i++)
    {
      BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExts.get(i);
      existedSet.add(branchLocationExt.getBranchLocation().getBranchLocationId().getBranchCode());
    }

    List selectedBranchExtList = selectBranches.getSelectedBranchExtList();
    for(int i=0; i<selectedBranchExtList.size(); i++)
    {
      BranchExt branchExt = (BranchExt)selectedBranchExtList.get(i);
      if(!existedSet.contains(branchExt.getBranch().getName()))
      {
        BranchLocation branchLocation = createBranchLocation();
        branchLocation.getBranchLocationId().setBranchSetId(branchExt.getBranch().getBusinessUnit().getName());
        branchLocation.getBranchLocationId().setBranchCode(branchExt.getBranch().getName());

        BranchLocationExt branchLocationExt = new BranchLocationExt();
        branchLocationExt.setBranchLocation(branchLocation);
        branchLocationExt.setBranchDescription(branchExt.getBranch().getDescription());

        branchLocationExts.add(branchLocationExt);
      }
    }
  }

  public static BranchExt createBranchExt(String buName, String branchName, String description)
  {
    BranchExt branchExt = new BranchExt();
    Branch branch = new Branch();
    branch.setName(branchName);
    branch.setDescription(description);
    BusinessUnit bu = new BusinessUnit();
    bu.setName(buName);
    branch.setBusinessUnit(bu);
    branchExt.setBranch(branch);

    return branchExt;
  }

  public static void loadBranchDescriptions(
    List zoneExtList
  )
  {
    if(zoneExtList == null) return;

    Set branchNameSet = new HashSet();
    for(int i=0; i<zoneExtList.size(); i++)
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);

      List branchLocationExtList = zoneExt.getBranchLocationExts();
      if(branchLocationExtList == null) continue;

      for(int j=0; j<branchLocationExtList.size(); j++)
      {
        BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExtList.get(j);

        branchNameSet.add(branchLocationExt.getBranchLocation().getBranchLocationId().getBranchCode());
      }
    }

    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");
    Map branchNameDescriptionMap = zoneService.getBranchDescriptionMap(branchNameSet);

    for(int i=0; i<zoneExtList.size(); i++)
    {
      ZoneExt zoneExt = (ZoneExt)zoneExtList.get(i);

      List branchLocationExtList = zoneExt.getBranchLocationExts();
      if(branchLocationExtList == null) continue;

      for(int j=0; j<branchLocationExtList.size(); j++)
      {
        BranchLocationExt branchLocationExt = (BranchLocationExt)branchLocationExtList.get(j);

        String description = (String)branchNameDescriptionMap.get(
          branchLocationExt.getBranchLocation().getBranchLocationId().getBranchCode()
        );

        branchLocationExt.setBranchDescription(description);
      }
    }
  }
}

