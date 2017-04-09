package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;

public class EditZone extends BaseEditContract
{
  private List zoneExtList;
  private Set deletedZoneIdSet = new HashSet();
  private List priceBookZoneIdList;
  private int numZonesToAdd = 1;
  private SelectZones selectZones;

  private String marker;

  public List getZoneExtList()
  {
    return zoneExtList;
  }

  public void setZoneExtList(List zoneExtList)
  {
    this.zoneExtList = zoneExtList;
  }

  public List getPriceBookZoneIdList()
  {
    return priceBookZoneIdList;
  }

  public void setPriceBookZoneIdList(List priceBookZoneIdList)
  {
    this.priceBookZoneIdList = priceBookZoneIdList;
  }

  public int getNumZonesToAdd()
  {
    return numZonesToAdd;
  }

  public void setNumZonesToAdd(int numZonesToAdd)
  {
    this.numZonesToAdd = numZonesToAdd;
  }

  public SelectZones getSelectZones()
  {
    return selectZones;
  }

  public void setSelectZones(SelectZones selectZones)
  {
    this.selectZones = selectZones;
  }

  public Set getDeletedZoneIdSet()
  {
    return deletedZoneIdSet;
  }

  public void setMarker(String marker)
  {
    this.marker = marker;
  }

  public String getMarker()
  {
    return marker;
  }
}
