package com.intertek.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import com.intertek.domain.EditVesselTypeSet;
import com.intertek.domain.VesselTypeExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.VesselType;
import com.intertek.entity.VesselTypeSet;
import com.intertek.locator.ServiceLocator;
import com.intertek.util.Constants;
import com.intertek.util.EntityUtil;

public class VesselTypeServiceImpl extends BaseService implements VesselTypeService
{
  public List getVesselTypeSetNameList(String vesselTypeSetName)
  {
    List results = new ArrayList();

    if((vesselTypeSetName != null) && !"".equals(vesselTypeSetName.trim()))
    {
      VesselTypeSet vts = getVesselTypeSetByName(vesselTypeSetName);
      if(vts == null)
      {
        results.add(vesselTypeSetName);

        return results;
      }
    }

    List vtsList = getVesselTypeSetList();
    for(int i=0; i<vtsList.size(); i++)
    {
      VesselTypeSet vts = (VesselTypeSet)vtsList.get(i);
      results.add(vts.getVesselTypeSetName());
    }

    return results;
  }

  public List getVesselTypeSetNameList()
  {
    List results = new ArrayList();

    List vtsList = getVesselTypeSetList();
    for(int i=0; i<vtsList.size(); i++)
    {
      VesselTypeSet vts = (VesselTypeSet)vtsList.get(i);
      results.add(vts.getVesselTypeSetName());
    }

    return results;
  }

  public List getVesselTypeSetList()
  {
    return getDao().find("from VesselTypeSet", null);
  }

  public VesselTypeSet getVesselTypeSetByName(String vesselTypeSetName)
  {
    List vtsList = getDao().find(
      "from VesselTypeSet vts where vts.vesselTypeSetName = ?",
      new Object[] {vesselTypeSetName}
    );

    if(vtsList.size() > 0)
    {
      return (VesselTypeSet)vtsList.get(0);
    }

    return null;
  }

  public VesselTypeSet getDefaultVesselTypeSet()
  {
    List vtsList = getDao().find(
      "from VesselTypeSet vts where vts.defaultFlag = ?",
      new Object[] { Boolean.TRUE }
    );

    if(vtsList.size() > 0)
    {
      return (VesselTypeSet)vtsList.get(0);
    }

    return null;
  }

  public boolean existVesselTypeSetByName(String vesselTypeSetName)
  {
    if(vesselTypeSetName == null) return false;

    List list = getDao().find(
      "select count(*) from VesselTypeSet pgs where pgs.vesselTypeSetName = ?",
      new Object[] { vesselTypeSetName }
    );

    if(list.size() > 0)
    {
      Number number = (Number)list.get(0);
      if(number.intValue() > 0) return true;
    }

    return false;
  }

  public List getVesselTypesByVesselTypeSetNameWithEqualBeginDate(
    String vesselTypeSetName,
    Date date
  )
  {
    List results = new ArrayList();

    if((vesselTypeSetName == null) || (date == null)) return results;

    List list = getDao().find(
      "from VesselType vt where vt.vesselTypeId.vesselSet = ? and vt.vesselTypeId.beginDate = ? order by vt.vesselTypeId.vesselType",
      new Object[] {vesselTypeSetName, date}
    );

    for(int i=0; i<list.size(); i++)
    {
      VesselType vt = (VesselType)list.get(i);

      VesselTypeExt vtExt = new VesselTypeExt();
      vtExt.setVesselType(vt);

      results.add(vtExt);
    }

    return results;
  }

  public List getVesselTypesByVesselTypeSetName(
    String vesselTypeSetName,
    Date date
  )
  {
    List results = new ArrayList();

    if((vesselTypeSetName == null) || (date == null)) return results;

    List list = getDao().find(
      "from VesselType vt where vt.vesselTypeId.vesselSet = ? and ? between vt.vesselTypeId.beginDate and vt.endDate order by vt.vesselTypeId.vesselType",
      new Object[] {vesselTypeSetName, date}
    );

    for(int i=0; i<list.size(); i++)
    {
      VesselType vt = (VesselType)list.get(i);

      VesselTypeExt vtExt = new VesselTypeExt();
      vtExt.setVesselType(vt);

      results.add(vtExt);
    }

    return results;
  }

  public List getVesselSetDateListByContractId(String contractId)
  {
    return getDao().find(
      "select o.cfgContractId.beginDate, o.endDate, o.vesselSet from CfgContract o where o.cfgContractId.contractId = ?",
      new Object[] { contractId }
    );
  }

  public List getVesselTypeAndRbKeyListByVesselSetAndDates(
    String vesselSet,
    Date beginDate,
    Date endDate
  )
  {
    return getDao().find(
      "select o.vesselTypeId.vesselType, o.rbKey from VesselType o where o.vesselTypeId.vesselSet = ? and o.vesselTypeId.beginDate = ? and o.endDate = ?",
      new Object[] { vesselSet, beginDate, endDate }
    );
  }

  public List getVesselTypeAndRbKeyListByVesselSet(
    String vesselSet
  )
  {
    return getDao().find(
      "select o.vesselTypeId.vesselType, o.rbKey from VesselType o where o.vesselTypeId.vesselSet = ?",
      new Object[] { vesselSet }
    );
  }

  public void saveEditVesselTypeSet(EditVesselTypeSet editVesselTypeSet)
  {
    if(editVesselTypeSet == null) return;
    CfgContract cfgContract = editVesselTypeSet.getCfgContract();
    CfgContract oldCfgContract = editVesselTypeSet.getOldCfgContract();

    if(cfgContract == null) return;

    List vtExts = editVesselTypeSet.getVesselTypeExts();
    if(vtExts != null)
    {
      RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");
      rbService.removeRbsByContractIdAndBeginDateForVessel(
        cfgContract.getCfgContractId().getContractId(),
        oldCfgContract == null ? cfgContract.getCfgContractId().getBeginDate() : oldCfgContract.getCfgContractId().getBeginDate()
      );
      for(int j=0; j<vtExts.size(); j++)
      {
        VesselTypeExt vtExt = (VesselTypeExt)vtExts.get(j);
        VesselType oldVesselType = vtExt.getOldVesselType();

        if(oldVesselType != null)
        {
          getDao().remove(oldVesselType);
        }

        if(vtExt.getSelected())
        {
          rbService.saveNotesRb(
            vtExt.getRbExt(),
            cfgContract.getCfgContractId().getBeginDate(),
            cfgContract.getEndDate()
          );
        }
      }

      String myVesselSet = cfgContract.getVesselSet();
      if(cfgContract.getCfgContractId().getContractId().equals(myVesselSet))
      {
        for(int j=0; j<vtExts.size(); j++)
        {
          VesselTypeExt vtExt = (VesselTypeExt)vtExts.get(j);

          if(vtExt.getSelected())
          {
            VesselType vesselType = vtExt.getVesselType();
            vesselType.getVesselTypeId().setVesselSet(cfgContract.getCfgContractId().getContractId());
            vesselType.getVesselTypeId().setBeginDate(cfgContract.getCfgContractId().getBeginDate());
            vesselType.setEndDate(cfgContract.getEndDate());

            getDao().save(vesselType);
          }
        }
      }
    }
  }
}

