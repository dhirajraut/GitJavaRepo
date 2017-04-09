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

public class SlateUtil
{
  public static void checkSlates(List slatePriceList)
  {
    if(slatePriceList == null) return;

    for(int i=0; i<slatePriceList.size(); i++)
    {
      SlatePrice slatePrice = (SlatePrice)slatePriceList.get(i);

      if(slatePrice.getSlate() == null)
      {
        Slate slate = new Slate();

        System.out.println("========== slate id " + slatePrice.getSlatePriceId().getSlateId());

        slatePrice.setSlate(slate);
      }
    }
  }

  public static List getSlatePriceExtList(List slateIds, List slatePriceList, List slateList, String contractCode)
  {
    List results = new ArrayList();
    if((slateIds == null) || (slatePriceList == null) || (slateList == null) || (contractCode == null)) return results;

    Map extMap = new HashMap();

    for(int i=0; i<slatePriceList.size(); i++)
    {
      SlatePrice slatePrice = (SlatePrice)slatePriceList.get(i);

      String slateId = slatePrice.getSlatePriceId().getSlateId();
      SlatePriceExt slatePriceExt = (SlatePriceExt)extMap.get(slateId);
      if(slatePriceExt == null)
      {
        slatePriceExt = new SlatePriceExt();
        slatePriceExt.setSlateId(slateId);

        extMap.put(slateId, slatePriceExt);
      }

      if(contractCode.equals(slatePrice.getSlatePriceId().getContractId()))
      {
        slatePriceExt.getSlatePrices().add(slatePrice);
      }
      else
      {
        slatePriceExt.getPriceBookSlatePrices().add(slatePrice);
      }
    }

    for(int i=0; i<slateList.size(); i++)
    {
      Slate slate = (Slate)slateList.get(i);
      String slateId = slate.getSlateId();
      SlatePriceExt slatePriceExt = (SlatePriceExt)extMap.get(slateId);
      if(slatePriceExt != null)
      {
        slatePriceExt.setSlate(slate);
      }
    }


    for(int i=0; i<slateIds.size(); i++)
    {
      String slateId = (String)slateIds.get(i);

      SlatePriceExt slatePriceExt = (SlatePriceExt)extMap.get(slateId);
      if(slatePriceExt != null)
      {
        results.add(slatePriceExt);
      }
    }

    return results;
  }

  public static List getContractSlatePriceExtList(List slateIds, List slatePriceList, List contractSlateList)
  {
    List results = new ArrayList();
    if((slateIds == null) || (slatePriceList == null) || (contractSlateList == null)) return results;

    Map extMap = new HashMap();

    for(int i=0; i<slateIds.size(); i++)
    {
      String slateId = (String)slateIds.get(i);

      ContractSlatePriceExt slatePriceExt = (ContractSlatePriceExt)extMap.get(slateId);
      if(slatePriceExt == null)
      {
        slatePriceExt = new ContractSlatePriceExt();
        slatePriceExt.setSlateId(slateId);

        extMap.put(slateId, slatePriceExt);

        results.add(slatePriceExt);
      }
    }

    for(int i=0; i<slatePriceList.size(); i++)
    {
      SlatePrice slatePrice = (SlatePrice)slatePriceList.get(i);

      String slateId = slatePrice.getSlatePriceId().getSlateId();
      ContractSlatePriceExt slatePriceExt = (ContractSlatePriceExt)extMap.get(slateId);
      if(slatePriceExt != null)
      {
        slatePriceExt.getSlatePrices().add(slatePrice);
      }
    }

    for(int i=0; i<contractSlateList.size(); i++)
    {
      ContractSlate contractSlate = (ContractSlate)contractSlateList.get(i);
      String slateId = contractSlate.getContractSlateId().getSlateId();
      ContractSlatePriceExt slatePriceExt = (ContractSlatePriceExt)extMap.get(slateId);
      if(slatePriceExt != null)
      {
        slatePriceExt.setContractSlate(contractSlate);
      }
    }

    return results;
  }

  public static void loadPriceBookSlatePrices(EditContractSlate editContractSlate, int pageNumber)
  {
    if(editContractSlate == null) return;
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    Pagination priceBookSlatePricePagination = null;
    if(pageNumber >= 1) priceBookSlatePricePagination = new Pagination(1,10, pageNumber, 10);
    List priceBookSlateIds = slateService.loadPriceBookSlateIdsByPriceBookIdAndContractCode(
      editContractSlate.getContract().getWorkingPB(),
      editContractSlate.getContract().getContractCode(),
      priceBookSlatePricePagination
    );
    if(pageNumber >= 1) priceBookSlatePricePagination.calculatePages();
    priceBookSlateIds = cleanList(priceBookSlateIds);
    editContractSlate.setPriceBookSlatePricePagination(priceBookSlatePricePagination);

    List priceBookSlatePriceList = slateService.loadSlatePricesBySlateIds(
      editContractSlate.getContract().getWorkingPB(),
      editContractSlate.getContract().getContractCode(),
      priceBookSlateIds
    );
    List priceBookSlateList = slateService.loadSlatesBySlateIds(priceBookSlateIds);

    List priceBookSlatePriceExtList = SlateUtil.getSlatePriceExtList(
      priceBookSlateIds,
      priceBookSlatePriceList,
      priceBookSlateList,
      editContractSlate.getContract().getContractCode()
    );
    editContractSlate.setSlatePriceExtList(priceBookSlatePriceExtList);
  }

  public static void loadContractSlatePrices(EditContractSlate editContractSlate, int pageNumber)
  {
    if(editContractSlate == null) return;
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    Pagination contractSlatePricePagination = null;
    if(pageNumber >= 1) contractSlatePricePagination = new Pagination(1, 10, pageNumber, 10);
    List contractSlateIds = slateService.loadContractSlateIdsByPriceBookIdAndContractCode(
      editContractSlate.getContract().getContractCode(),
      contractSlatePricePagination
    );
    if(pageNumber >= 1) contractSlatePricePagination.calculatePages();
    contractSlateIds = cleanList(contractSlateIds);
    editContractSlate.setContractSlatePricePagination(contractSlatePricePagination);

    List contractSlatePriceList = slateService.loadSlatePricesBySlateIds(
      editContractSlate.getContract().getContractCode(),
      contractSlateIds
    );
    List contractSlateList = slateService.loadContractSlatesBySlateIds(contractSlateIds);

    List contractSlatePriceExtList = SlateUtil.getContractSlatePriceExtList(
      contractSlateIds,
      contractSlatePriceList,
      contractSlateList
    );
    editContractSlate.setContractSlatePriceExtList(contractSlatePriceExtList);
  }

  public static List cleanList(List list)
  {
    List results = new ArrayList();
    if(list == null) return results;

    for(int i=0; i<list.size(); i++)
    {
      Object object = list.get(i);
      if(object instanceof Object[])
      {
        Object[] objects = (Object[])object;
        results.add(objects[0]);
      }
      else results.add(object);
    }

    return results;
  }

  public static void loadSelectedSlatesForPb(
    CopyPbSlatePrice copyPbSlatePrice,
    int pageNumber
  )
  {
    List results = new ArrayList();

    if((copyPbSlatePrice == null) || (pageNumber < 1)) return;
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    Pagination pagination = new Pagination(1, 10, pageNumber, 10);
    List slateIdAndDescs = slateService.getPbSlateIdAndDescriptions(
      copyPbSlatePrice.getPriceBookId(),
      copyPbSlatePrice.getContractCode(),
      copyPbSlatePrice.getSlateId(),
      copyPbSlatePrice.getDescription(),
      pagination
    );
    pagination.calculatePages();
    copyPbSlatePrice.setPagination(pagination);

    for(int i=0; i<slateIdAndDescs.size(); i++)
    {
      Object[] objects = (Object[])slateIdAndDescs.get(i);
      SelectedSlate selectedSlate = new SelectedSlate();
      Slate slate = new Slate();
      for(int j=0; j<objects.length; j++)
      {
        if(j == 0) slate.setSlateId((String)objects[0]);
        if(j == 1) slate.setSlateDescription((String)objects[1]);
      }
      selectedSlate.setSlate(slate);
      results.add(selectedSlate);
    }

    copyPbSlatePrice.setSelectedSlates(results);
  }

  public static void copySelectedSlatePricesFromPb(CopyPbSlatePrice copyPbSlatePrice)
  {
    if(copyPbSlatePrice == null) return;
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    slateService.copySelectedSlatePricesFromPb(copyPbSlatePrice);
  }

  public static void loadSlatePrices(SlatePriceExt slatePriceExt)
  {
    if(slatePriceExt == null) return;
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    List slateIds = new ArrayList();
    slateIds.add(slatePriceExt.getSlateId());

    List slatePriceList = slateService.loadSlatePricesBySlateIds(
      slatePriceExt.getContractCode(),
      slateIds
    );
    slatePriceExt.setSlatePrices(slatePriceList);

    Slate slate = slateService.getSlateById(slatePriceExt.getSlateId());

    slatePriceExt.setSlate(slate);
  }

  public static void addSlatePrice(SlatePriceExt slatePriceExt)
  {
    if(slatePriceExt == null) return;

    List slatePriceList = slatePriceExt.getSlatePrices();
    if(slatePriceList == null) return;

    int rowsToAdd = slatePriceExt.getRowsToAdd();
    if(rowsToAdd <= 0) rowsToAdd = 1;
    slatePriceExt.setRowsToAdd(rowsToAdd);

    for(int i=0; i<rowsToAdd; i++)
    {
      SlatePrice slatePrice = createSlatePrice();

      if(slatePriceList.size() > 0)
      {
        SlatePrice lastSlatePrice = (SlatePrice)slatePriceList.get(0);

        Date[] newDatePair = new Date[] {
          slatePrice.getSlatePriceId().getBeginDate(),
          slatePrice.getEndDate()
        };

        Date[] oldDatePair = new Date[] {
          lastSlatePrice.getSlatePriceId().getBeginDate(),
          lastSlatePrice.getEndDate()
        };

        DateUtil.calculateBeginDateAndEndDate(newDatePair, oldDatePair);

        slatePrice.getSlatePriceId().setBeginDate(newDatePair[0]);
        slatePrice.setEndDate(newDatePair[1]);

        lastSlatePrice.getSlatePriceId().setBeginDate(oldDatePair[0]);
        lastSlatePrice.setEndDate(oldDatePair[1]);

        slatePrice.getSlatePriceId().setLocation(lastSlatePrice.getSlatePriceId().getLocation());
      }
      else
      {
        slatePrice.getSlatePriceId().setLocation("*");
      }

      slatePrice.getSlatePriceId().setSlateId(slatePriceExt.getSlateId());
      slatePrice.getSlatePriceId().setContractId(slatePriceExt.getContractCode());

      slatePriceList.add(0, slatePrice);
    }
  }

  public static void removeSlatePrice(SlatePriceExt slatePriceExt, int index)
  {
    if((slatePriceExt == null) || (index < 0)) return;

    List slatePriceList = slatePriceExt.getSlatePrices();
    if(slatePriceList == null) return;

    if(index < slatePriceList.size()) slatePriceList.remove(index);
  }

  public static SlatePrice createSlatePrice()
  {
    SlatePrice slatePrice = new SlatePrice();

    SlatePriceId slatePriceId = new SlatePriceId();
    slatePriceId.setSlateType("C");
    slatePriceId.setBeginDate(new Date());
    slatePriceId.setMinQty(0);
    slatePriceId.setMaxQty(9999);
    slatePrice.setSlatePriceId(slatePriceId);

    slatePrice.setContractRef("CONT");
    slatePrice.setEndDate(DateUtil.parseDate("12/31/2099", "MM/dd/yyyy"));
    slatePrice.setSeqNum(0);
    slatePrice.setCurrencyCD("USD");

    return slatePrice;
  }

  public static void saveSlatePrices(SlatePriceExt slatePriceExt)
  {
    if(slatePriceExt == null) return;
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    slateService.saveSlatePrices(slatePriceExt);
  }

  public static List getNextSlatePriceList(List slatePrices, String zone, int startIndex)
  {
    List results = new ArrayList();

    if((slatePrices == null) || (zone == null)) return results;

    for(int i=startIndex; i<slatePrices.size(); i++)
    {
      SlatePrice slatePrice = (SlatePrice)slatePrices.get(i);
      if(zone.equals(slatePrice.getSlatePriceId().getLocation()))
      {
        results.add(slatePrice);
      }
    }

    return results;
  }

  public static void addContractSlate(ContractSlateExt contractSlateExt)
  {
    if(contractSlateExt == null) return;

    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    ContractSlate contractSlate = contractSlateExt.getContractSlate();
    if(contractSlate == null) return;

    Slate slate = contractSlate.getSlate();
    if(slate == null) return;

    slate.setSlateId(contractSlate.getContractSlateId().getSlateId());
    populateSlate(slate);

    slateService.addContractSlate(contractSlate);
  }

  public static void populateSlate(Slate slate)
  {
    if(slate == null) return;

    slate.setRbKey("D");
  }
}

