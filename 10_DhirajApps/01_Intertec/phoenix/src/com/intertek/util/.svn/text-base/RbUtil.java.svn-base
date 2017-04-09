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
import com.intertek.calculator.*;

public class RbUtil
{
  public static List createRbExtList(List rbList)
  {
    List rbExtList = new ArrayList();

    if(rbList == null) return rbExtList;

    for(int i=0; i<rbList.size(); i++)
    {
      RB rb = (RB)rbList.get(i);

      RBExt rbExt = createRBExtByRB(rb);

      rbExtList.add(rbExt);
    }

    return rbExtList;
  }

  public static RB getRb(String contractId, String rbKey)
  {
    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    RB rb = rbService.getRb(contractId, rbKey);
    if(rb == null) rb = rbService.getRb(rbKey);

    return rb;
  }

  public static RBExt createRBExtByRB(RB rb)
  {
    if(rb == null) return null;

    RBExt rbExt = new RBExt();
    rbExt.setRb(rb);

    RB notesRb = new RB();
    EntityUtil.copyRb(notesRb, rb);
    rbExt.setNotesRb(notesRb);

    return rbExt;
  }

  public static RB getRBByRbKey(
    String rbKey,
    List rbList
  )
  {
    if((rbKey == null) || (rbList == null)) return null;

    for(int i=0; i<rbList.size(); i++)
    {
      RB rb = (RB)rbList.get(i);

      if(rbKey.equals(rb.getRbId().getRbKey())) return rb;
    }

    return null;
  }

}

