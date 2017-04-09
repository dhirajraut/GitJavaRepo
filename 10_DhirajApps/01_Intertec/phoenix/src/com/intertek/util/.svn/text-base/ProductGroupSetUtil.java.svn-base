package com.intertek.util;

import java.io.*;
import java.util.*;

import org.apache.commons.beanutils.PropertyUtils;

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

public class ProductGroupSetUtil
{
  public static ProductGroupExt addProductGroupExt(EditProductGroupSet editProductGroupSet, String productGroupIndex)
  {
    if((editProductGroupSet == null) || (productGroupIndex == null)) return null;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return null;

    Integer tmp = null;
    try
    {
      tmp = new Integer(productGroupIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return null;
    int index = tmp.intValue();

    List list = editProductGroupSet.getProductGroupExts();
    if(list != null)
    {
      if((index >= 0) && (index < list.size()))
      {
        ProductGroupExt oldPgExt = (ProductGroupExt)list.get(index);

        ProductGroupExt pgExt = new ProductGroupExt();

        ProductGroup pg = new ProductGroup();
        ProductGroupId pgId = new ProductGroupId();
        pgId.setProductGroupSet(cfgContract.getCfgContractId().getContractId());

        int maxSplittedNumber = getMaxSplittedNumber(list, oldPgExt.getProductGroup().getProductGroupId().getGroupId());
        pgId.setGroupId(oldPgExt.getProductGroup().getProductGroupId().getGroupId() + (maxSplittedNumber + 1));
        pgId.setBeginDate(oldPgExt.getProductGroup().getProductGroupId().getBeginDate());

        pg.setProductGroupId(pgId);
        pg.setProductGroupMaster(oldPgExt.getProductGroup().getProductGroupId().getGroupId());
        pg.setRbKey("Group." + pgId.getGroupId());
        pg.setEndDate(oldPgExt.getProductGroup().getEndDate());

        pgExt.setProductGroup(pg);
        pgExt.setNewlyAdded(true);

        RBExt rbExt = new RBExt();
        pgExt.setRbExt(rbExt);

        list.add(index + 1, pgExt);

        return pgExt;
      }
    }

    return null;
  }

  public static int getMaxSplittedNumber(List productGroupExts, String groupId)
  {
    if((productGroupExts == null) || (groupId == null)) return 0;

    int max = 0;
    for(int i=0; i<productGroupExts.size(); i++)
    {
      ProductGroupExt pgExt = (ProductGroupExt)productGroupExts.get(i);

      String tmpGroupId = pgExt.getProductGroup().getProductGroupId().getGroupId();
      if(tmpGroupId.startsWith(groupId))
      {
        if(!tmpGroupId.equals(groupId))
        {
          int index = tmpGroupId.indexOf(groupId);
          String subStr = tmpGroupId.substring(index + groupId.length());

          int tmpValue = 0;
          try
          {
            tmpValue = new Integer(subStr).intValue();
          }
          catch(Exception e)
          {
          }

          if(tmpValue > max) max = tmpValue;
        }
      }
    }

    return max;
  }

  public static void setEditDescription(EditProductGroupSet editProductGroupSet, String productGroupIndex)
  {
    if((editProductGroupSet == null) || (productGroupIndex == null)) return;
    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return;
    CfgContract oldCfgContract = editProductGroupSet.getOldCfgContract();

    Integer tmp = null;
    try
    {
      tmp = new Integer(productGroupIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return;
    int index = tmp.intValue();

    List list = editProductGroupSet.getProductGroupExts();
    if(list != null)
    {
      if((index >= 0) && (index < list.size()))
      {
        ProductGroupExt pgExt = (ProductGroupExt)list.get(index);

        EditRBExt editRBExt = new EditRBExt();
        editRBExt.setRbKey(pgExt.getProductGroup().getRbKey());

        RBExt rbExt = pgExt.getRbExt();
        editRBExt.setRbExt(rbExt);

        List rbExtList = new ArrayList();
        RB customizedRb = rbService.getRB(
          cfgContract.getCfgContractId().getContractId(),
          pgExt.getProductGroup().getRbKey(),
          oldCfgContract == null ? cfgContract.getCfgContractId().getBeginDate() : oldCfgContract.getCfgContractId().getBeginDate()
        );
        if(customizedRb != null)
        {
          RBExt customizedRbExt = RbUtil.createRBExtByRB(customizedRb);
          rbExtList.add(customizedRbExt);
        }

        editRBExt.setRbExtList(rbExtList);

        if(rbExtList.size() == 0)
        {
          RB rb = rbExt.getRb();

          RBExt newRbExt = new RBExt();
          RB newRb = new RB();
          newRbExt.setNotesRb(newRb);

          if(rb != null)
          {
            EntityUtil.copyRb(newRb, rb);

            newRb.getRbId().setContractId(cfgContract.getCfgContractId().getContractId());
            newRb.getRbId().setBeginDate(cfgContract.getCfgContractId().getBeginDate());
            newRb.getRbId().setEndDate(cfgContract.getEndDate());
            newRb.setRbValue(null);
          }
          else
          {
            RBId rbId = new RBId();
            newRb.setRbId(rbId);

            newRb.getRbId().setRbKey(pgExt.getProductGroup().getRbKey());
            newRb.getRbId().setContractId(cfgContract.getCfgContractId().getContractId());
            newRb.getRbId().setBeginDate(cfgContract.getCfgContractId().getBeginDate());
            newRb.getRbId().setEndDate(cfgContract.getEndDate());
            newRb.setRbValue(null);
            newRb.setRbType("PRD");
          }

          rbExtList.add(newRbExt);
        }

        editProductGroupSet.setEditRBExt(editRBExt);
      }
    }
  }

  public static void saveDescription(EditProductGroupSet editProductGroupSet)
  {
    if(editProductGroupSet == null) return;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return;

    EditRBExt editRBExt = editProductGroupSet.getEditRBExt();
    if(editRBExt == null) return;

    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    RBExt rbExt = editRBExt.getRbExt();
    if(rbExt != null)
    {
      RB changedNotesRb = null;
      List rbExtList = editRBExt.getRbExtList();
      if((rbExtList != null) && (rbExtList.size() > 0))
      {
        RBExt notesRbExt = (RBExt)rbExtList.get(0);
        changedNotesRb = notesRbExt.getNotesRb();
      }

      rbExt.setNotesRb(changedNotesRb);
    }
  }

  public static void addNewDescription(EditProductGroupSet editProductGroupSet)
  {
    if(editProductGroupSet == null) return;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return;

    EditRBExt editRBExt = editProductGroupSet.getEditRBExt();
    if(editRBExt == null) return;

    RBExt rbExt = editRBExt.getRbExt();

    List rbExtList = editRBExt.getRbExtList();
    if(rbExtList == null)
    {
      rbExtList = new ArrayList();
      editRBExt.setRbExtList(rbExtList);
    }

    if(rbExtList.size() > 0)
    {
      rbExt = (RBExt)rbExtList.get(0);
    }

    RBExt newRbExt = new RBExt();
    RB newRb = new RB();
    newRbExt.setNotesRb(newRb);

    if(rbExt != null)
    {
      EntityUtil.copyRb(newRb, rbExt.getRb());

      newRb.getRbId().setContractId(cfgContract.getCfgContractId().getContractId());
      newRb.getRbId().setBeginDate(cfgContract.getCfgContractId().getBeginDate());
      newRb.getRbId().setEndDate(cfgContract.getEndDate());
      newRb.setRbValue(null);
    }
    else
    {
      RBId rbId = new RBId();
      newRb.setRbId(rbId);

      newRb.getRbId().setRbKey(editRBExt.getRbKey());
      newRb.getRbId().setContractId(cfgContract.getCfgContractId().getContractId());
      newRb.getRbId().setBeginDate(cfgContract.getCfgContractId().getBeginDate());
      newRb.getRbId().setEndDate(cfgContract.getEndDate());
      newRb.setRbValue(null);
      newRb.setRbType("PRD");
    }

    rbExtList.add(newRbExt);
  }

  public static void deleteDescription(EditProductGroupSet editProductGroupSet, String rbExtIndex)
  {
    if(editProductGroupSet == null) return;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return;

    EditRBExt editRBExt = editProductGroupSet.getEditRBExt();
    if(editRBExt == null) return;

    List rbExtList = editRBExt.getRbExtList();
    if(rbExtList == null) return;

    Integer tmp = null;
    try
    {
      tmp = new Integer(rbExtIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return;
    int index = tmp.intValue();

    if((index >= 0) && (index < rbExtList.size()))
    {
      editRBExt.getDeletedRbExtList().add(rbExtList.remove(index));
    }
  }

  public static boolean checkProductGroupChanged(EditProductGroupSet editProductGroupSet)
  {
    if(editProductGroupSet == null) return false;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return false;

    List list = editProductGroupSet.getProductGroupExts();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        ProductGroupExt pgExt = (ProductGroupExt)list.get(i);
        if(pgExt.getNewlyAdded()) return true;

        if(pgExt.getSelected() == false) return true;

        if(cfgContract.getCfgContractId().getContractId().equals(
          pgExt.getProductGroup().getProductGroupId().getProductGroupSet()
        ))
        {
          return true;
        }
      }
    }

    return false;
  }

  public static boolean checkProductGroupSameAsBase(EditProductGroupSet editProductGroupSet)
  {
    if(editProductGroupSet == null) return false;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return false;

    List baseList = editProductGroupSet.getBaseProductGroupExts();
    List list = editProductGroupSet.getProductGroupExts();
    if((list != null) && (baseList != null))
    {
      Set checkedSet = new HashSet();
      for(int i=0; i<list.size(); i++)
      {
        ProductGroupExt pgExt = (ProductGroupExt)list.get(i);

        if(pgExt.getSelected())
        {
          checkedSet.add(pgExt.getProductGroup().getProductGroupId().getGroupId());
        }
      }

      if(checkedSet.size() != baseList.size()) return false;

      for(int i=0; i<baseList.size(); i++)
      {
        ProductGroupExt pgExt = (ProductGroupExt)baseList.get(i);
        if(!checkedSet.contains(pgExt.getProductGroup().getProductGroupId().getGroupId()))
        {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  public static List getChangedProductGroupExts(EditProductGroupSet editProductGroupSet)
  {
    List results = new ArrayList();
    if(editProductGroupSet == null) return results;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return results;

    //List customizedProductGroupExts = editProductGroupSet.getCustomizedProductGroupExts();
    //if(customizedProductGroupExts == null) return results;

    //if(customizedProductGroupExts.size() <= 0) return results;

    List list = editProductGroupSet.getProductGroupExts();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        ProductGroupExt pgExt = (ProductGroupExt)list.get(i);
        if(pgExt.getSelected() ||
          cfgContract.getCfgContractId().getContractId().equals(
            pgExt.getProductGroup().getProductGroupId().getProductGroupSet()
          )
        )
        {
          results.add(pgExt);
        }
      }
    }

    return results;
  }

  public static void setChangedProductGroups(EditProductGroupSet editProductGroupSet)
  {
    if(editProductGroupSet == null) return;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return;

    List list = editProductGroupSet.getProductGroupExts();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        ProductGroupExt pgExt = (ProductGroupExt)list.get(i);

        if(pgExt.getSelected())
        {
          ProductGroup pg = pgExt.getProductGroup();
          if(pg == null) continue;

          pg.getProductGroupId().setProductGroupSet(cfgContract.getCfgContractId().getContractId());
        }
      }
    }

    editProductGroupSet.setChangedProductGroupSetName(cfgContract.getCfgContractId().getContractId());
  }

  public static void updateProductGroupSetName(AddContract addContract)
  {
    if(addContract == null) return;

    List list = addContract.getCfgContractExtList();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        CfgContractExt cfgContractExt = (CfgContractExt)list.get(i);
        CfgContract cfgContract = cfgContractExt.getCfgContract();

        EditProductGroupSet editProductGroupSet = cfgContractExt.getEditProductGroupSet();
        if(editProductGroupSet != null)
        {
          if(editProductGroupSet.getChangedProductGroupSetName() != null)
          {
            cfgContract.setProductGroupSet(editProductGroupSet.getChangedProductGroupSetName());
          }
          else
          {
           cfgContract.setProductGroupSet(editProductGroupSet.getBaseProductGroupSetName());
          }
        }
      }
    }
  }

  public static void setOldProductGroups(
    List customizedGroupExts
  )
  {
    if(customizedGroupExts == null) return;

    for(int i=0; i<customizedGroupExts.size(); i++)
    {
      ProductGroupExt customizedPde = (ProductGroupExt)customizedGroupExts.get(i);

      ProductGroup productGroup = customizedPde.getProductGroup();
      ProductGroup oldProductGroup = new ProductGroup();
      EntityUtil.copyProductGroup(oldProductGroup, productGroup);
      customizedPde.setOldProductGroup(oldProductGroup);
    }
  }

  public static List mergeProductGroupExts(
    List productGroupExts,
    List customizedGroupExts
  )
  {
    List results = new ArrayList();

    if((productGroupExts == null) || (customizedGroupExts == null)) return results;

    for(int i=0; i<productGroupExts.size(); i++)
    {
      ProductGroupExt pde = (ProductGroupExt)productGroupExts.get(i);

      boolean found = false;

      for(int j=0; j<customizedGroupExts.size(); j++)
      {
        ProductGroupExt customizedPde = (ProductGroupExt)customizedGroupExts.get(j);
        if(customizedPde.getProductGroup().getProductGroupId().getGroupId().equals(
          pde.getProductGroup().getProductGroupId().getGroupId())
        )
        {
          found = true;
          break;
        }
      }

      if(!found)
      {
        pde.setSelected(false);
        results.add(pde);
      }
    }

    results.addAll(customizedGroupExts);
    Collections.sort(results, new Comparator()
    {
      public int compare(Object o1, Object o2)
      {
        ProductGroupExt p1 = (ProductGroupExt)o1;
        ProductGroupExt p2 = (ProductGroupExt)o2;

        String group1 = p1.getProductGroup().getProductGroupId().getGroupId();
        String group2 = p2.getProductGroup().getProductGroupId().getGroupId();

        return group1.compareTo(group2);
      }
    });

    return results;
  }

  public static void loadProductGroupRBs(EditProductGroupSet editProductGroupSet, boolean customizedOnly)
  {
    if(editProductGroupSet == null) return;

    CfgContract cfgContract = editProductGroupSet.getCfgContract();
    if(cfgContract == null) return;

    List productGroupExts = editProductGroupSet.getProductGroupExts();

    loadProductGroupRBs(cfgContract, productGroupExts, customizedOnly, editProductGroupSet.getOldCfgContract());
  }

  public static void loadProductGroupRBs(
    CfgContract cfgContract,
    List productGroupExts,
    boolean customizedOnly,
    CfgContract oldCfgContract
  )
  {
    if(cfgContract == null) return;

    if((productGroupExts != null) && (productGroupExts.size() > 0))
    {
      List rbKeyList = new ArrayList();
      for(int i=0; i<productGroupExts.size(); i++)
      {
        ProductGroupExt pgExt = (ProductGroupExt)productGroupExts.get(i);
        RBExt rbExt = pgExt.getRbExt();
        if((rbExt == null) || (rbExt.getRb() == null))
        {
          rbKeyList.add(pgExt.getProductGroup().getRbKey());
        }
      }

      if(rbKeyList.size() > 0)
      {
        RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

        if(!customizedOnly)
        {
          List rbList = rbService.getRbList("NONE", new Date(), rbKeyList);
          Map rbMap = ContractUtil.mapRBList(rbList);

          for(int i=0; i<productGroupExts.size(); i++)
          {
            ProductGroupExt pgExt = (ProductGroupExt)productGroupExts.get(i);
            RBExt rbExt = pgExt.getRbExt();
            if(rbExt == null)
            {
              rbExt = new RBExt();
              pgExt.setRbExt(rbExt);
            }

            String rbKey = pgExt.getProductGroup().getRbKey();

            RB rb = (RB)rbMap.get(rbKey);
            if(rb != null)
            {
              rbExt.setRb(rb);
            }
          }
        }

        List customizedRbList = rbService.getRbListWithEqualBeginDate(
          cfgContract.getCfgContractId().getContractId(),
          oldCfgContract == null ? cfgContract.getCfgContractId().getBeginDate() : oldCfgContract.getCfgContractId().getBeginDate(),
          rbKeyList
        );
        Map customizedRbMap = ContractUtil.mapRBList(customizedRbList);

        for(int i=0; i<productGroupExts.size(); i++)
        {
          ProductGroupExt pgExt = (ProductGroupExt)productGroupExts.get(i);

          String rbKey = pgExt.getProductGroup().getRbKey();
          RB rb = (RB)customizedRbMap.get(rbKey);
          if(rb != null)
          {
            RBExt rbExt = pgExt.getRbExt();
            if(rbExt == null)
            {
              rbExt = new RBExt();
              pgExt.setRbExt(rbExt);
            }

            rbExt.setNotesRb(rb);
          }
        }
      }
    }
  }

  public static void createEditProductGroupSet(
    AddContract addContract,
    CfgContractExt cfgContractExt,
    boolean customizedRBOnly
  )
  {
    if((addContract == null) || (cfgContractExt == null)) return;
    CfgContract cfgContract = cfgContractExt.getCfgContract();
    CfgContract oldCfgContract = cfgContractExt.getOldCfgContract();

    ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");

    EditProductGroupSet editProductGroupSet = cfgContractExt.getEditProductGroupSet();
    if(editProductGroupSet == null)
    {
      editProductGroupSet = new EditProductGroupSet();
      cfgContractExt.setEditProductGroupSet(editProductGroupSet);
    }

    editProductGroupSet.setCfgContract(cfgContract);
    editProductGroupSet.setOldCfgContract(oldCfgContract);

    String baseProductGroupSetName = cfgContract.getProductGroupSet();
    boolean existProductGroupSet = productGroupService.existProductGroupSetByName(
      baseProductGroupSetName
    );
    if(!existProductGroupSet) baseProductGroupSetName = Constants.Pricebook;
    editProductGroupSet.setBaseProductGroupSetName(baseProductGroupSetName);

    List productGroupExts = editProductGroupSet.getProductGroupExts();
    if(productGroupExts == null)
    {
      editProductGroupSet.setOldProductGroupSetName(cfgContract.getProductGroupSet());
      productGroupExts = productGroupService.getProductGroupsByProductGroupSetName(
        baseProductGroupSetName,
        new Date()
      );
      List baseProductGroupExts = new ArrayList();
      baseProductGroupExts.addAll(productGroupExts);
      editProductGroupSet.setBaseProductGroupExts(baseProductGroupExts);
      editProductGroupSet.setChangedProductGroupSetName(cfgContract.getProductGroupSet());
      if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getProductGroupSet()))
      {
        List customizedProductGroupExts = editProductGroupSet.getCustomizedProductGroupExts();
        if(customizedProductGroupExts == null)
        {
          customizedProductGroupExts = productGroupService.getProductGroupsByProductGroupSetNameWithEqualBeginDate(
            cfgContract.getCfgContractId().getContractId(),
            oldCfgContract == null ? cfgContract.getCfgContractId().getBeginDate() : oldCfgContract.getCfgContractId().getBeginDate()
          );
          ProductGroupSetUtil.setOldProductGroups(customizedProductGroupExts);
          editProductGroupSet.setCustomizedProductGroupExts(customizedProductGroupExts);
        }

        List mergedProductGroupExts = ProductGroupSetUtil.mergeProductGroupExts(
          productGroupExts,
          customizedProductGroupExts
        );
        editProductGroupSet.setProductGroupExts(mergedProductGroupExts);
      }
      else
      {
        editProductGroupSet.setProductGroupExts(productGroupExts);
      }
    }

    loadProductGroupRBs(editProductGroupSet, customizedRBOnly);
  }

  public static List getProductGroupExtList(CfgContract cfgContract)
  {
    if(cfgContract == null) return new ArrayList();

    ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");

    List productGroupExtList = null;
    if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getProductGroupSet()))
    {
      productGroupExtList = productGroupService.getProductGroupsByProductGroupSetNameWithEqualBeginDate(
        cfgContract.getProductGroupSet(),
        cfgContract.getCfgContractId().getBeginDate()
      );
    }
    else
    {
      productGroupExtList = productGroupService.getProductGroupsByProductGroupSetName(
        cfgContract.getProductGroupSet(),
        new Date()
      );
    }

    ProductGroupSetUtil.loadProductGroupRBs(cfgContract, productGroupExtList, false, null);

    return productGroupExtList;
  }

  public static List makeProductGroupDropDownList(List productGroupExtList)
  {
    List results = new ArrayList();

    if(productGroupExtList == null) return results;

    for(int i=0; i<productGroupExtList.size(); i++)
    {
      ProductGroupExt pgExt = (ProductGroupExt)productGroupExtList.get(i);

      Field field = new Field();
      field.setValue(pgExt.getProductGroup().getProductGroupId().getGroupId());

      String name = null;
      RBExt rbExt = pgExt.getRbExt();
      if(rbExt != null)
      {
        RB customizedRb = rbExt.getNotesRb();
        if(customizedRb != null)
        {
          name = customizedRb.getRbValue();
        }
        else
        {
          RB rb = rbExt.getRb();
          if(rb != null)
          {
            name = rb.getRbValue();
          }
        }
      }

      if(name == null) name = pgExt.getProductGroup().getProductGroupId().getGroupId();
      field.setName(name);

      results.add(field);
    }

    return results;
  }

  public static EditProductGroupSet copyEditProductGroupSet(EditProductGroupSet editProductGroupSet)
  {
    if(editProductGroupSet == null) return null;

    EditProductGroupSet newEditProductGroupSet = new EditProductGroupSet();

    newEditProductGroupSet.setBaseProductGroupSetName(editProductGroupSet.getBaseProductGroupSetName());
    newEditProductGroupSet.setChangedProductGroupSetName(editProductGroupSet.getChangedProductGroupSetName());
    newEditProductGroupSet.setOldProductGroupSetName(editProductGroupSet.getOldProductGroupSetName());

    newEditProductGroupSet.setProductGroupExts(copyProductGroupExtList(editProductGroupSet.getProductGroupExts()));
    newEditProductGroupSet.setBaseProductGroupExts(copyProductGroupExtList(editProductGroupSet.getBaseProductGroupExts()));

    return newEditProductGroupSet;
  }

  public static List copyProductGroupExtList(List productGroupExtList)
  {
    if(productGroupExtList == null) return null;

    List results = new ArrayList();
    for(int i=0; i<productGroupExtList.size(); i++)
    {
      ProductGroupExt newProductGroupExt = copyProductGroupExt((ProductGroupExt)productGroupExtList.get(i));
      if(newProductGroupExt != null) results.add(newProductGroupExt);
    }

    return results;
  }

  public static ProductGroupExt copyProductGroupExt(ProductGroupExt productGroupExt)
  {
    if(productGroupExt == null) return null;
    ProductGroup productGroup = productGroupExt.getProductGroup();
    if(productGroup == null) return null;

    ProductGroupExt newProductGroupExt = new ProductGroupExt();
    BeanUtil.copySimpleProperties(newProductGroupExt, productGroupExt, true);

    ProductGroup newProductGroup = new ProductGroup();
    EntityUtil.copyProductGroup(newProductGroup, productGroup);

    newProductGroupExt.setProductGroup(newProductGroup);

    return newProductGroupExt;
  }
}
