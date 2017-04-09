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

public class VesselTypeSetUtil
{
  public static void setEditDescription(EditVesselTypeSet editVesselTypeSet, String vesselTypeIndex)
  {
    if((editVesselTypeSet == null) || (vesselTypeIndex == null)) return;
    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return;
    CfgContract oldCfgContract = editVesselTypeSet.getOldCfgContract();

    Integer tmp = null;
    try
    {
      tmp = new Integer(vesselTypeIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return;
    int index = tmp.intValue();

    List list = editVesselTypeSet.getVesselTypeExts();
    if(list != null)
    {
      if((index >= 0) && (index < list.size()))
      {
        VesselTypeExt vtExt = (VesselTypeExt)list.get(index);

        EditRBExt editRBExt = new EditRBExt();
        editRBExt.setRbKey(vtExt.getVesselType().getRbKey());

        RBExt rbExt = vtExt.getRbExt();
        editRBExt.setRbExt(rbExt);

        List rbExtList = new ArrayList();
        RB customizedRb = rbService.getRB(
          cfgContract.getCfgContractId().getContractId(),
          vtExt.getVesselType().getRbKey(),
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

            newRb.getRbId().setRbKey(vtExt.getVesselType().getRbKey());
            newRb.getRbId().setContractId(cfgContract.getCfgContractId().getContractId());
            newRb.getRbId().setBeginDate(cfgContract.getCfgContractId().getBeginDate());
            newRb.getRbId().setEndDate(cfgContract.getEndDate());
            newRb.setRbValue(null);
            newRb.setRbType("PRD");
          }

          rbExtList.add(newRbExt);
        }

        editVesselTypeSet.setEditRBExt(editRBExt);
      }
    }
  }

  public static void saveDescription(EditVesselTypeSet editVesselTypeSet)
  {
    if(editVesselTypeSet == null) return;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return;

    EditRBExt editRBExt = editVesselTypeSet.getEditRBExt();
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

  public static void addNewDescription(EditVesselTypeSet editVesselTypeSet)
  {
    if(editVesselTypeSet == null) return;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return;

    EditRBExt editRBExt = editVesselTypeSet.getEditRBExt();
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

  public static void deleteDescription(EditVesselTypeSet editVesselTypeSet, String rbExtIndex)
  {
    if(editVesselTypeSet == null) return;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return;

    EditRBExt editRBExt = editVesselTypeSet.getEditRBExt();
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

  public static boolean checkVesselTypeChanged(EditVesselTypeSet editVesselTypeSet)
  {
    if(editVesselTypeSet == null) return false;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return false;

    List list = editVesselTypeSet.getVesselTypeExts();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)list.get(i);
        if(vtExt.getNewlyAdded()) return true;

        if(vtExt.getSelected() == false) return true;

        if(cfgContract.getCfgContractId().getContractId().equals(
          vtExt.getVesselType().getVesselTypeId().getVesselSet()
        ))
        {
          return true;
        }
      }
    }

    return false;
  }

  public static boolean checkVesselTypeSameAsBase(EditVesselTypeSet editVesselTypeSet)
  {
    if(editVesselTypeSet == null) return false;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return false;

    List baseList = editVesselTypeSet.getBaseVesselTypeExts();
    List list = editVesselTypeSet.getVesselTypeExts();
    if((list != null) && (baseList != null))
    {
      Set checkedSet = new HashSet();
      for(int i=0; i<list.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)list.get(i);

        if(vtExt.getSelected())
        {
          checkedSet.add(vtExt.getVesselType().getVesselTypeId().getVesselType());
        }
      }

      if(checkedSet.size() != baseList.size()) return false;

      for(int i=0; i<baseList.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)baseList.get(i);
        if(!checkedSet.contains(vtExt.getVesselType().getVesselTypeId().getVesselType()))
        {
          return false;
        }
      }

      return true;
    }

    return false;
  }

  public static List getChangedVesselTypeExts(EditVesselTypeSet editVesselTypeSet)
  {
    List results = new ArrayList();
    if(editVesselTypeSet == null) return results;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return results;

    //List customizedVesselTypeExts = editVesselTypeSet.getCustomizedVesselTypeExts();
    //if(customizedVesselTypeExts == null) return results;

    //if(customizedVesselTypeExts.size() <= 0) return results;

    List list = editVesselTypeSet.getVesselTypeExts();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)list.get(i);
        if(vtExt.getSelected() ||
          cfgContract.getCfgContractId().getContractId().equals(
            vtExt.getVesselType().getVesselTypeId().getVesselSet()
          )
        )
        {
          results.add(vtExt);
        }
      }
    }

    return results;
  }

  public static void setChangedVesselTypes(EditVesselTypeSet editVesselTypeSet)
  {
    if(editVesselTypeSet == null) return;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return;

    List list = editVesselTypeSet.getVesselTypeExts();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)list.get(i);

        if(vtExt.getSelected())
        {
          VesselType pg = vtExt.getVesselType();
          if(pg == null) continue;

          pg.getVesselTypeId().setVesselSet(cfgContract.getCfgContractId().getContractId());
        }
      }
    }

    editVesselTypeSet.setChangedVesselTypeSetName(cfgContract.getCfgContractId().getContractId());
  }

  public static void updateVesselTypeSetName(AddContract addContract)
  {
    if(addContract == null) return;

    List list = addContract.getCfgContractExtList();
    if(list != null)
    {
      for(int i=0; i<list.size(); i++)
      {
        CfgContractExt cfgContractExt = (CfgContractExt)list.get(i);
        CfgContract cfgContract = cfgContractExt.getCfgContract();

        EditVesselTypeSet editVesselTypeSet = cfgContractExt.getEditVesselTypeSet();
        if(editVesselTypeSet != null)
        {
          if(editVesselTypeSet.getChangedVesselTypeSetName() != null)
          {
            cfgContract.setVesselSet(editVesselTypeSet.getChangedVesselTypeSetName());
          }
          else
          {
            cfgContract.setVesselSet(editVesselTypeSet.getBaseVesselTypeSetName());
          }
        }
      }
    }
  }

  public static void setOldVesselTypes(
    List customizedGroupExts
  )
  {
    if(customizedGroupExts == null) return;

    for(int i=0; i<customizedGroupExts.size(); i++)
    {
      VesselTypeExt customizedPde = (VesselTypeExt)customizedGroupExts.get(i);

      VesselType vesselType = customizedPde.getVesselType();
      VesselType oldVesselType = new VesselType();
      EntityUtil.copyVesselType(oldVesselType, vesselType);
      customizedPde.setOldVesselType(oldVesselType);
    }
  }

  public static List mergeVesselTypeExts(
    List vesselTypeExts,
    List customizedGroupExts
  )
  {
    List results = new ArrayList();

    if((vesselTypeExts == null) || (customizedGroupExts == null)) return results;

    for(int i=0; i<vesselTypeExts.size(); i++)
    {
      VesselTypeExt pde = (VesselTypeExt)vesselTypeExts.get(i);

      boolean found = false;

      for(int j=0; j<customizedGroupExts.size(); j++)
      {
        VesselTypeExt customizedPde = (VesselTypeExt)customizedGroupExts.get(j);
        if(customizedPde.getVesselType().getVesselTypeId().getVesselType().equals(
          pde.getVesselType().getVesselTypeId().getVesselType())
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
        VesselTypeExt p1 = (VesselTypeExt)o1;
        VesselTypeExt p2 = (VesselTypeExt)o2;

        String group1 = p1.getVesselType().getVesselTypeId().getVesselType();
        String group2 = p2.getVesselType().getVesselTypeId().getVesselType();

        return group1.compareTo(group2);
      }
    });

    return results;
  }

  public static void loadVesselTypeRBs(EditVesselTypeSet editVesselTypeSet, boolean customizedOnly)
  {
    if(editVesselTypeSet == null) return;

    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    if(cfgContract == null) return;

    List vesselTypeExts = editVesselTypeSet.getVesselTypeExts();

    loadVesselTypeRBs(cfgContract, vesselTypeExts, customizedOnly, editVesselTypeSet.getOldCfgContract());
  }

  public static void loadVesselTypeRBs(
    CfgContract cfgContract,
    List vesselTypeExts,
    boolean customizedOnly,
    CfgContract oldCfgContract
  )
  {
    if(cfgContract == null) return;

    if((vesselTypeExts != null) && (vesselTypeExts.size() > 0))
    {
      List rbKeyList = new ArrayList();
      for(int i=0; i<vesselTypeExts.size(); i++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExts.get(i);
        RBExt rbExt = vtExt.getRbExt();
        if((rbExt == null) || (rbExt.getRb() == null))
        {
          rbKeyList.add(vtExt.getVesselType().getRbKey());
        }
      }

      if(rbKeyList.size() > 0)
      {
        RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

        if(!customizedOnly)
        {
          List rbList = rbService.getRbList("NONE", new Date(), rbKeyList);
          Map rbMap = ContractUtil.mapRBList(rbList);

          for(int i=0; i<vesselTypeExts.size(); i++)
          {
            VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExts.get(i);
            RBExt rbExt = vtExt.getRbExt();
            if(rbExt == null)
            {
              rbExt = new RBExt();
              vtExt.setRbExt(rbExt);
            }

            String rbKey = vtExt.getVesselType().getRbKey();

            RB rb = (RB)rbMap.get(rbKey);
            if(rb != null)
            {
              rbExt.setRb(rb);
            }
          }
        }

        List customizedRbList = rbService.getRbList(
          cfgContract.getCfgContractId().getContractId(),
          oldCfgContract == null ? cfgContract.getCfgContractId().getBeginDate() : oldCfgContract.getCfgContractId().getBeginDate(),
          rbKeyList
        );

        Map customizedRbMap = ContractUtil.mapRBList(customizedRbList);

        for(int i=0; i<vesselTypeExts.size(); i++)
        {
          VesselTypeExt vtExt = (VesselTypeExt)vesselTypeExts.get(i);

          String rbKey = vtExt.getVesselType().getRbKey();
          RB rb = (RB)customizedRbMap.get(rbKey);
          if(rb != null)
          {
            RBExt rbExt = vtExt.getRbExt();
            if(rbExt == null)
            {
              rbExt = new RBExt();
              vtExt.setRbExt(rbExt);
            }

            rbExt.setNotesRb(rb);
          }
        }
      }
    }
  }

  public static void createEditVesselTypeSet(
    AddContract addContract,
    CfgContractExt cfgContractExt,
    boolean customizedRBOnly
  )
  {
    if((addContract == null) || (cfgContractExt == null)) return;
    CfgContract cfgContract = cfgContractExt.getCfgContract();
    CfgContract oldCfgContract = cfgContractExt.getOldCfgContract();

    VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");

    EditVesselTypeSet editVesselTypeSet = cfgContractExt.getEditVesselTypeSet();
    if(editVesselTypeSet == null)
    {
      editVesselTypeSet = new EditVesselTypeSet();
      cfgContractExt.setEditVesselTypeSet(editVesselTypeSet);
    }

    editVesselTypeSet.setCfgContract(cfgContract);
    editVesselTypeSet.setOldCfgContract(oldCfgContract);

    String baseVesselTypeSetName = cfgContract.getVesselSet();
    boolean existVesselTypeSet = vesselTypeService.existVesselTypeSetByName(
      baseVesselTypeSetName
    );
    if(!existVesselTypeSet) baseVesselTypeSetName = Constants.Pricebook;
    editVesselTypeSet.setBaseVesselTypeSetName(baseVesselTypeSetName);

    List vesselTypeExts = editVesselTypeSet.getVesselTypeExts();
    if(vesselTypeExts == null)
    {
      editVesselTypeSet.setOldVesselTypeSetName(cfgContract.getVesselSet());
      vesselTypeExts = vesselTypeService.getVesselTypesByVesselTypeSetName(
        baseVesselTypeSetName,
        new Date()
      );
      editVesselTypeSet.setBaseVesselTypeExts(vesselTypeExts);
      editVesselTypeSet.setChangedVesselTypeSetName(cfgContract.getVesselSet());
      if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getVesselSet()))
      {
        List customizedVesselTypeExts = editVesselTypeSet.getCustomizedVesselTypeExts();
        if(customizedVesselTypeExts == null)
        {
          customizedVesselTypeExts = vesselTypeService.getVesselTypesByVesselTypeSetNameWithEqualBeginDate(
            cfgContract.getCfgContractId().getContractId(),
            oldCfgContract == null ? cfgContract.getCfgContractId().getBeginDate() : oldCfgContract.getCfgContractId().getBeginDate()
          );
          VesselTypeSetUtil.setOldVesselTypes(customizedVesselTypeExts);
          editVesselTypeSet.setCustomizedVesselTypeExts(customizedVesselTypeExts);
        }

        List mergedVesselTypeExts = VesselTypeSetUtil.mergeVesselTypeExts(
          vesselTypeExts,
          customizedVesselTypeExts
        );
        editVesselTypeSet.setVesselTypeExts(mergedVesselTypeExts);
      }
      else
      {
        editVesselTypeSet.setVesselTypeExts(vesselTypeExts);
      }
    }

    VesselTypeSetUtil.loadVesselTypeRBs(editVesselTypeSet, customizedRBOnly);
  }

  public static EditVesselTypeSet copyEditVesselTypeSet(EditVesselTypeSet editVesselTypeSet)
  {
    if(editVesselTypeSet == null) return null;

    EditVesselTypeSet newEditVesselTypeSet = new EditVesselTypeSet();

    newEditVesselTypeSet.setBaseVesselTypeSetName(editVesselTypeSet.getBaseVesselTypeSetName());
    newEditVesselTypeSet.setChangedVesselTypeSetName(editVesselTypeSet.getChangedVesselTypeSetName());
    newEditVesselTypeSet.setOldVesselTypeSetName(editVesselTypeSet.getOldVesselTypeSetName());

    newEditVesselTypeSet.setVesselTypeExts(copyVesselTypeExtList(editVesselTypeSet.getVesselTypeExts()));
    newEditVesselTypeSet.setBaseVesselTypeExts(copyVesselTypeExtList(editVesselTypeSet.getBaseVesselTypeExts()));

    return newEditVesselTypeSet;
  }

  public static List copyVesselTypeExtList(List vesselTypeExtList)
  {
    if(vesselTypeExtList == null) return null;

    List results = new ArrayList();
    for(int i=0; i<vesselTypeExtList.size(); i++)
    {
      VesselTypeExt newVesselTypeExt = copyVesselTypeExt((VesselTypeExt)vesselTypeExtList.get(i));
      if(newVesselTypeExt != null) results.add(newVesselTypeExt);
    }

    return results;
  }

  public static VesselTypeExt copyVesselTypeExt(VesselTypeExt vesselTypeExt)
  {
    if(vesselTypeExt == null) return null;
    VesselType vesselType = vesselTypeExt.getVesselType();
    if(vesselType == null) return null;

    VesselTypeExt newVesselTypeExt = new VesselTypeExt();
    BeanUtil.copySimpleProperties(newVesselTypeExt, vesselTypeExt, true);

    VesselType newVesselType = new VesselType();
    EntityUtil.copyVesselType(newVesselType, vesselType);

    newVesselTypeExt.setVesselType(newVesselType);

    return newVesselTypeExt;
  }
}
