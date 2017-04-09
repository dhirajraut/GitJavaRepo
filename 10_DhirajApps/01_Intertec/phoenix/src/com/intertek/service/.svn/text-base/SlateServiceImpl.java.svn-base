package com.intertek.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.intertek.domain.SelectedSlate;
import com.intertek.domain.CopyPbSlatePrice;
import com.intertek.domain.SlatePriceExt;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractSlate;
import com.intertek.entity.Slate;
import com.intertek.entity.SlatePrice;
import com.intertek.entity.SlatePriceId;
import com.intertek.pagination.Pagination;
import com.intertek.util.EntityUtil;
import com.intertek.exception.ServiceException;

public class SlateServiceImpl extends BaseService implements SlateService
{
  public static String SQL_1 = "SELECT DISTINCT t.SLATE_ID, t.ITS_SLTE_DESC FROM PS_ITS_SLTE_PB tpb, PS_ITSC_SLATE t "
    + " WHERE tpb.PRICEBOOK_ID = ? "
    + " AND t.SLATE_ID = tpb.SLATE_ID "
    + " AND t.SLATE_ID NOT IN ( "
    + "   SELECT tpr.SLATE_ID FROM PS_ITSC_SLTE_PRICE tpr "
    + "   WHERE tpr.CFG_CONTRACT_ID = ?  "
    + " ) ";
  public static String SQL_2 = " ORDER BY t.SLATE_ID";

  public Slate getSlateById(String slateId)
  {
    List slates = getDao().find(
      "from Slate s where s.slateId = ?",
      new Object[] { slateId }
    );

    if(slates.size() > 0) return (Slate)slates.get(0);

    return null;
  }

  public SlatePrice getSlatePrice(
    String contractId,
    String priceBookId,
    String slateId,
    String location,
    Integer quantity,
    String nominationDateStr
  )
  {
    List results = getDao().findByNamedSqlQuery(
      "getSlatePrice_FN",
      new Object[] { contractId, priceBookId, slateId, location, quantity, nominationDateStr }
    );

    if(results.size() > 0) return (SlatePrice)results.get(0);

    return null;
  }

  public List getSlates(
    String contractId,
    String priceBookId,
    String value,
    String searchType,
    String location,
    String nominationDateStr,
    String languageCD
  )
  {
    List searchResults = getDao().findByNamedSqlQuery(
      "getSlate_FN",
      new Object[] {
        contractId,
        priceBookId,
        value,
        searchType,
        location,
        nominationDateStr,
        languageCD
      }
    );

    List results = new ArrayList();
    for(int i=0; i<searchResults.size(); i++)
    {
      Object[] objs = (Object[])searchResults.get(i);

      Slate slate = new Slate();
      slate.setSlateId((String)objs[0]);
      slate.setSlateDescription((String)objs[1]);

      results.add(slate);
    }

    return results;
  }

  public int getTotalRecordOfPbSlateIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String slateId,
    String description
  )
  {
    if((priceBookId == null) || (contractCode == null)) return 0;

    int count = 0;
    StringBuffer sb = new StringBuffer();
    sb.append("select count(*) from (");
    sb.append(SQL_1);
    if(slateId != null)
    {
      sb.append(" AND t.SLATE_ID LIKE ? ");
      count ++;
    }

    if(description != null)
    {
      sb.append(" AND t.ITS_SLTE_DESC LIKE ? ");
      count ++;
    }
    sb.append(")");

    Object[] objects = new Object[ 2 + count];
    objects[0] = priceBookId;
    objects[1] = contractCode;
    for(int i=0; i<count; i++)
    {
      if(i == 0) objects[2] = "%" + slateId + "%";
      if(i == 1) objects[3] = "%" + description + "%";
    }

    List results = getDao().findBySQL(
      sb.toString(),
      objects
    );

    if(results.size() > 0)
    {
      Number number = (Number)results.get(0);
      return number.intValue();
    }

    return 0;
  }

  public List getPbSlateIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String slateId,
    String description,
    Pagination pagination
  )
  {
    if((priceBookId == null) || (contractCode == null)) return new ArrayList();

    int count = 0;
    StringBuffer sb = new StringBuffer();
    sb.append(SQL_1);
    if(slateId != null)
    {
      sb.append(" AND t.SLATE_ID LIKE ? ");
      count ++;
    }

    if(description != null)
    {
      sb.append(" AND t.ITS_SLTE_DESC LIKE ? ");
      count ++;
    }
    sb.append(SQL_2);

    if(pagination.getTotalRecord() > 0)
    {
      int totalRecord = getTotalRecordOfPbSlateIdAndDescriptions(priceBookId, contractCode, slateId, description);
      pagination.setTotalRecord(totalRecord);
    }

    Object[] objects = new Object[ 2 + count];
    objects[0] = priceBookId;
    objects[1] = contractCode;
    for(int i=0; i<count; i++)
    {
      if(i == 0) objects[2] = "%" + slateId + "%";
      if(i == 1) objects[3] = "%" + description + "%";
    }

    return getDao().findBySQL(
      sb.toString(),
      objects,
      pagination
    );
  }

  public int getTotalRecordOfPriceBookSlateIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode
  )
  {
    if(contractCode == null) return 0;

    List results = getDao().findByNamedSqlQuery(
      "getTotalRecordOfPriceBookSlateIdsByPriceBookIdAndContractCode",
      new Object[] {priceBookId, contractCode}
    );

    if(results.size() > 0)
    {
      Number number = (Number)results.get(0);
      return number.intValue();
    }

    return 0;
  }

  public List loadPriceBookSlateIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode,
    Pagination pagination
  )
  {
    if((priceBookId == null) || (contractCode == null)) return new ArrayList();

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        int totalRecord = getTotalRecordOfPriceBookSlateIdsByPriceBookIdAndContractCode(priceBookId, contractCode);
        pagination.setTotalRecord(totalRecord);
      }

      return getDao().findByNamedSqlQuery(
        "getPriceBookSlateIdsByPriceBookIdAndContractCode",
        new Object[] {priceBookId, contractCode},
        pagination
      );
    }
    else
    {
      return getDao().findByNamedSqlQuery(
        "getPriceBookSlateIdsByPriceBookIdAndContractCode",
        new Object[] {priceBookId, contractCode}
      );
    }
  }

  public List loadSlatePricesBySlateIds(
    String priceBookId,
    String contractCode,
    List slateIds
  )
  {
    if((contractCode == null) || (priceBookId == null) || (slateIds == null) || (slateIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[slateIds.size() + 2];
    StringBuffer sb = new StringBuffer();
    sb.append("from SlatePrice tp where (tp.slatePriceId.contractId = ? or tp.slatePriceId.contractId = ?) and tp.slatePriceId.slateId in (");
    objects[0] = priceBookId;
    objects[1] = contractCode;
    for(int i=0; i<slateIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i + 2] = slateIds.get(i);
    }
    sb.append(") order by tp.slatePriceId.slateId, tp.slatePriceId.contractId, tp.slatePriceId.location, tp.slatePriceId.beginDate desc");

    return getDao().find(sb.toString(), objects);
  }

  public List loadSlatePricesBySlateIds(
    String contractCode,
    List slateIds
  )
  {
    if((contractCode == null) || (slateIds == null) || (slateIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[slateIds.size() + 1];
    StringBuffer sb = new StringBuffer();
    sb.append("from SlatePrice tp where (tp.slatePriceId.contractId = ?) and tp.slatePriceId.slateId in (");
    objects[0] = contractCode;
    for(int i=0; i<slateIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i + 1] = slateIds.get(i);
    }
    sb.append(") order by tp.slatePriceId.slateId, tp.slatePriceId.contractId, tp.slatePriceId.location, tp.slatePriceId.beginDate desc");

    return getDao().find(sb.toString(), objects);
  }

  public List loadSlatesBySlateIds(List slateIds)
  {
    if((slateIds == null) || (slateIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[slateIds.size()];
    StringBuffer sb = new StringBuffer();
    sb.append("from Slate t where t.slateId in (");
    for(int i=0; i<slateIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i] = slateIds.get(i);
    }
    sb.append(")");

    return getDao().find(sb.toString(), objects);
  }

  public int getTotalRecordOfContractSlateIdsByPriceBookIdAndContractCode(String contractCode)
  {
    if(contractCode == null) return 0;

    List results = getDao().findByNamedSqlQuery(
      "getTotalRecordOfContractSlateIdsByContractCode",
      new Object[] { contractCode}
    );

    if(results.size() > 0)
    {
      Number number = (Number)results.get(0);
      return number.intValue();
    }

    return 0;
  }


  public List loadContractSlateIdsByPriceBookIdAndContractCode(
    String contractCode,
    Pagination pagination
  )
  {
    if(contractCode == null) return new ArrayList();

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        int totalRecord = getTotalRecordOfContractSlateIdsByPriceBookIdAndContractCode(contractCode);
        pagination.setTotalRecord(totalRecord);
      }

      return getDao().findByNamedSqlQuery(
        "getContractSlateIdsByContractCode",
        new Object[] { contractCode},
        pagination
      );
    }
    else
    {
      return getDao().findByNamedSqlQuery(
        "getContractSlateIdsByContractCode",
        new Object[] { contractCode}
      );
    }
  }

  public List loadContractSlatesBySlateIds(List slateIds)
  {
    if((slateIds == null) || (slateIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[slateIds.size()];
    StringBuffer sb = new StringBuffer();
    sb.append("from ContractSlate cs where cs.contractSlateId.slateId in (");
    for(int i=0; i<slateIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i] = slateIds.get(i);
    }
    sb.append(")");

    List csList = getDao().find(sb.toString(), objects);

    List sList = loadSlatesBySlateIds(slateIds);
    Map slateMap = new HashMap();

    for(int i=0; i<sList.size(); i++)
    {
      Slate slate = (Slate)sList.get(i);
      slateMap.put(slate.getSlateId(), slate);
    }

    for(int i=0; i<csList.size(); i++)
    {
      ContractSlate ct = (ContractSlate)csList.get(i);
      Slate slate = (Slate)slateMap.get(ct.getContractSlateId().getSlateId());
      ct.setSlate(slate);
    }

    return csList;
  }

  public void copySelectedSlatePricesFromPb(CopyPbSlatePrice copyPbSlatePrice)
  {
    if(copyPbSlatePrice == null) return;

    String contractCode = copyPbSlatePrice.getContractCode();
    if(contractCode == null) return;

    String priceBookId = copyPbSlatePrice.getPriceBookId();
    if(priceBookId == null) return;

    List selectedSlates = copyPbSlatePrice.getSelectedSlates();
    if(selectedSlates == null) return;

    for(int i=0; i<selectedSlates.size(); i++)
    {
      SelectedSlate selectedSlate = (SelectedSlate)selectedSlates.get(i);

      if(selectedSlate.isSelected())
      {
        Slate slate = selectedSlate.getSlate();
        if(slate == null) continue;

        copySelectedSlatePriceFromPb(slate.getSlateId(), priceBookId, contractCode);
      }
    }
  }

  public void copySelectedSlatePriceFromPb(String slateId, String priceBookId, String contractCode)
  {
    if((slateId == null) || (priceBookId == null) || (contractCode == null)) return;

    List pbSlatePrices = getPbSlatePricesBySlateIdAndPriceBookId(slateId, priceBookId);
    for(int i=0; i<pbSlatePrices.size(); i++)
    {
      SlatePrice pbSlatePrice = (SlatePrice)pbSlatePrices.get(i);

      SlatePrice contractSlatePrice = new SlatePrice();
      EntityUtil.copySlatePrice(contractSlatePrice, pbSlatePrice);

      contractSlatePrice.getSlatePriceId().setContractId(contractCode);
      contractSlatePrice.getSlatePriceId().setSlateType("C");
      contractSlatePrice.setContractRef("CONT");

      getDao().save(contractSlatePrice);
    }
  }

  public List getPbSlatePricesBySlateIdAndPriceBookId(String slateId, String priceBookId)
  {
    if((slateId == null) || (priceBookId == null)) return new ArrayList();

    return getDao().find(
      "from SlatePrice tp where tp.slatePriceId.slateId = ? and tp.slatePriceId.contractId = ?",
      new Object[] { slateId, priceBookId }
    );
  }

  public void deleteSlatePricesByContractCodeAndSlateId(String contractCode, String slateId)
  {
    getDao().bulkUpdate(
      "delete from SlatePrice o where o.slatePriceId.contractId = ? and o.slatePriceId.slateId = ?",
      new Object[] { contractCode, slateId }
    );
  }

  public void saveSlatePrices(List slatePrices)
  {
    if(slatePrices == null) return;

    for(int i=0; i<slatePrices.size(); i++)
    {
      SlatePrice slatePrice = (SlatePrice)slatePrices.get(i);
      getDao().save(slatePrice);
    }
  }

  public void saveSlatePrices(SlatePriceExt slatePriceExt)
  {
    if(slatePriceExt == null) return;

    String contractCode = slatePriceExt.getContractCode();
    String slateId = slatePriceExt.getSlateId();
    if((contractCode == null) || (slateId == null)) return;

    deleteSlatePricesByContractCodeAndSlateId(contractCode, slateId);

    saveSlatePrices(slatePriceExt.getSlatePrices());

    Slate slate = slatePriceExt.getSlate();
    if(slate != null)
    {
      getDao().save(slate);
    }
  }

  public ContractSlate getContractSlateById(String contractCode, String slateId, String slateType)
  {
    if((contractCode == null) || (slateId == null) || (slateType == null)) return null;

    List slates = getDao().find(
      "from ContractSlate o where o.contractSlateId.contractId = ? and o.contractSlateId.slateId = ? and o.contractSlateId.slateType = ?",
      new Object[] { contractCode, slateId, slateType }
    );

    if(slates.size() > 0) return (ContractSlate)slates.get(0);

    return null;
  }

  public void addContractSlate(ContractSlate contractSlate)
  {
    if(contractSlate == null) return;

    ContractSlate existedContractSlate = getContractSlateById(
      contractSlate.getContractSlateId().getContractId(),
      contractSlate.getContractSlateId().getSlateId(),
      contractSlate.getContractSlateId().getSlateType()
    );

    if(existedContractSlate != null)
    {
      throw new ServiceException(
        "contract_slate_exists",
        new Object[] {
          contractSlate.getContractSlateId().getContractId(),
          contractSlate.getContractSlateId().getSlateId(),
          contractSlate.getContractSlateId().getSlateType()
        }
      );
    }

    Slate slate = contractSlate.getSlate();
    if(slate != null)
    {
      Slate existedSlate = getSlateById(slate.getSlateId());
      if(existedSlate != null)
      {
        throw new ServiceException("slate_exists", new Object[] { slate.getSlateId() });
      }

      getDao().save(slate);
    }

    getDao().save(contractSlate);
  }
}
