package com.intertek.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.intertek.domain.EditProductGroupSet;
import com.intertek.domain.ProductGroupExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ProductGroupSet;
import com.intertek.entity.ProductGroup;
import com.intertek.locator.ServiceLocator;
import com.intertek.util.Constants;
import com.intertek.util.EntityUtil;

public class ProductGroupServiceImpl extends BaseService implements ProductGroupService
{
  public List getProductGroupSetNameList(String productGroupSetName)
  {
    List results = new ArrayList();

    if((productGroupSetName != null) && !"".equals(productGroupSetName.trim()))
    {
      ProductGroupSet pgs = getProductGroupSetByName(productGroupSetName);
      if(pgs == null)
      {
        results.add(productGroupSetName);

        return results;
      }
    }

    List pgsList = getProductGroupSetList();
    for(int i=0; i<pgsList.size(); i++)
    {
      ProductGroupSet pgs = (ProductGroupSet)pgsList.get(i);
      results.add(pgs.getProductGroupSetName());
    }

    return results;
  }

  public List getProductGroupSetNameList()
  {
    List results = new ArrayList();

    List pgsList = getProductGroupSetList();
    for(int i=0; i<pgsList.size(); i++)
    {
      ProductGroupSet pgs = (ProductGroupSet)pgsList.get(i);
      results.add(pgs.getProductGroupSetName());
    }

    return results;
  }

  public List getProductGroupSetList()
  {
    return getDao().find("from ProductGroupSet", null);
  }

  public ProductGroupSet getProductGroupSetByName(String productGroupSetName)
  {
    List pgsList = getDao().find(
      "from ProductGroupSet pgs where pgs.productGroupSetName = ?",
      new Object[] {productGroupSetName}
    );

    if(pgsList.size() > 0)
    {
      return (ProductGroupSet)pgsList.get(0);
    }

    return null;
  }

  public ProductGroupSet getDefaultProductGroupSet()
  {
    List pgsList = getDao().find(
      "from ProductGroupSet pgs where pgs.defaultFlag = ?",
      new Object[] { Boolean.TRUE }
    );

    if(pgsList.size() > 0)
    {
      return (ProductGroupSet)pgsList.get(0);
    }

    return null;
  }

  public boolean existProductGroupSetByName(String productGroupSetName)
  {
    if(productGroupSetName == null) return false;

    List list = getDao().find(
      "select count(*) from ProductGroupSet pgs where pgs.productGroupSetName = ?",
      new Object[] { productGroupSetName }
    );

    if(list.size() > 0)
    {
      Number number = (Number)list.get(0);
      if(number.intValue() > 0) return true;
    }

    return false;
  }

  public List getProductGroupsByProductGroupSetNameWithEqualBeginDate(
    String productGroupSetName,
    Date date
  )
  {
    List results = new ArrayList();

    if((productGroupSetName == null) || (date == null)) return results;

    List list = getDao().find(
      "from ProductGroup pg where pg.productGroupId.productGroupSet = ? and pg.productGroupId.beginDate = ? order by pg.productGroupId.groupId",
      new Object[] {productGroupSetName, date}
    );

    for(int i=0; i<list.size(); i++)
    {
      ProductGroup pg = (ProductGroup)list.get(i);

      ProductGroupExt pgExt = new ProductGroupExt();
      pgExt.setProductGroup(pg);

      results.add(pgExt);
    }

    return results;
  }

  public List getProductGroupsByProductGroupSetName(
    String productGroupSetName,
    Date date
  )
  {
    List results = new ArrayList();

    if((productGroupSetName == null) || (date == null)) return results;

    List list = getDao().find(
      "from ProductGroup pg where pg.productGroupId.productGroupSet = ? and ? between pg.productGroupId.beginDate and pg.endDate order by pg.productGroupId.groupId",
      new Object[] {productGroupSetName, date}
    );

    for(int i=0; i<list.size(); i++)
    {
      ProductGroup pg = (ProductGroup)list.get(i);

      ProductGroupExt pgExt = new ProductGroupExt();
      pgExt.setProductGroup(pg);

      results.add(pgExt);
    }

    return results;
  }

  public List getProductGroupSetDateListByContractId(String contractId)
  {
    return getDao().find(
      "select o.cfgContractId.beginDate, o.endDate, o.productGroupSet from CfgContract o where o.cfgContractId.contractId = ?",
      new Object[] { contractId }
    );
  }

  public List getGroupIdAndRbKeyListByProductGroupSetAndDates(
    String productGroupSet,
    Date beginDate,
    Date endDate
  )
  {
    return getDao().find(
      "select o.productGroupId.groupId, o.rbKey from ProductGroup o where o.productGroupId.productGroupSet = ? and o.productGroupId.beginDate = ? and o.endDate = ?",
      new Object[] { productGroupSet, beginDate, endDate }
    );
  }

  public List getGroupIdAndRbKeyListByProductGroupSet(
    String productGroupSet
  )
  {
    return getDao().find(
      "select o.productGroupId.groupId, o.rbKey from ProductGroup o where o.productGroupId.productGroupSet = ?",
      new Object[] { productGroupSet }
    );
  }

  public void saveEditProductGroupSet(EditProductGroupSet editProductGroupSet)
  {
    if(editProductGroupSet == null) return;
    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    CfgContract oldCfgContract = editProductGroupSet.getOldCfgContract();

    if(cfgContract == null) return;

    List pgExts = editProductGroupSet.getProductGroupExts();
    if(pgExts != null)
    {
      RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");
      rbService.removeRbsByContractIdAndBeginDateForGroup(
        cfgContract.getCfgContractId().getContractId(),
        oldCfgContract == null ? cfgContract.getCfgContractId().getBeginDate() : oldCfgContract.getCfgContractId().getBeginDate()
      );
      for(int j=0; j<pgExts.size(); j++)
      {
        ProductGroupExt pgExt = (ProductGroupExt)pgExts.get(j);
        ProductGroup oldProductGroup = pgExt.getOldProductGroup();

        if(oldProductGroup != null)
        {
          getDao().remove(oldProductGroup);
        }

        if(pgExt.getSelected())
        {
          rbService.saveNotesRb(
            pgExt.getRbExt(),
            cfgContract.getCfgContractId().getBeginDate(),
            cfgContract.getEndDate()
          );
        }
      }

      String myProductGroupSet = cfgContract.getProductGroupSet();
      if(cfgContract.getCfgContractId().getContractId().equals(myProductGroupSet))
      {
        for(int j=0; j<pgExts.size(); j++)
        {
          ProductGroupExt pgExt = (ProductGroupExt)pgExts.get(j);

          if(pgExt.getSelected())
          {
            ProductGroup productGroup = pgExt.getProductGroup();
            productGroup.getProductGroupId().setProductGroupSet(cfgContract.getCfgContractId().getContractId());
            productGroup.getProductGroupId().setBeginDate(cfgContract.getCfgContractId().getBeginDate());
            productGroup.setEndDate(cfgContract.getEndDate());

            getDao().save(productGroup);
          }
        }
      }
    }
  }
}

