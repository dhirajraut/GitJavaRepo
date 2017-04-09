package com.intertek.util;

import java.util.List;
import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

import com.intertek.acegi.AcegiUser;
import com.intertek.entity.User;

public class SecurityUtil
{
    
    public static String getLoginToken(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth == null) return null;

        Object principal = auth.getPrincipal();
        if(principal instanceof AcegiUser)
        {
          return ((AcegiUser)principal).getLoginToken();
        }

        return null;
    }
    
  public static User getUser()
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if(auth == null) return null;

    Object principal = auth.getPrincipal();
    if(principal instanceof AcegiUser)
    {
      return ((AcegiUser)principal).getUser();
    }

    return null;
  }

  public static List getMenuTree()
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if(auth == null) return null;

    Object principal = auth.getPrincipal();
    if(principal instanceof AcegiUser)
    {
      return ((AcegiUser)principal).getMenuTree();
    }

    return null;
  }

  public static Set getPermNameSet()
  {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();

    if(auth == null) return null;

    Object principal = auth.getPrincipal();
    if(principal instanceof AcegiUser)
    {
      return ((AcegiUser)principal).getPermNameSet();
    }

    return null;
  }

  public static boolean isAnyGranted(String[] perms)
  {
    boolean granted = true;
    if(perms == null) return granted;

    Set permNameSet = SecurityUtil.getPermNameSet();
    if(permNameSet != null)
    {
      for(int i=0; i<perms.length; i++)
      {
        granted = permNameSet.contains(perms[i]);
        if(granted) break;
      }
    }

    return granted;
  }
}
