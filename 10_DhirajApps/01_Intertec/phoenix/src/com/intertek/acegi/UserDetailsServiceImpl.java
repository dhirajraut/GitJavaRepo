package com.intertek.acegi;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

import com.intertek.entity.Menu;
import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.MenuService;
import com.intertek.service.UserService;

/**
 * Retrieves user details (username, password, enabled flag, and authorities).
 **/

public class UserDetailsServiceImpl implements MyUserDetailsService
{
  /**
   * Locates the user based on the username.
   *
   * @param username - the username
   * @throws UsernameNotFoundException - if the user could not be found or the user has no GrantedAuthority
   * @throws org.springframework.dao.DataAccessException - if user could not be found for a repository-specific reason
   **/
  public UserDetails loadUserByUsername(
    String username
  ) throws UsernameNotFoundException, DataAccessException
  {
    return loadUserByUsername(username, null);
  }

  /**
   * Locates the user based on the username.
   *
   * @param username - the username
   * @param password - the password
   * @throws UsernameNotFoundException - if the user could not be found or the user has no GrantedAuthority
   * @throws org.springframework.dao.DataAccessException - if user could not be found for a repository-specific reason
   **/
  public UserDetails loadUserByUsername(
    String username,
    String password
  ) throws UsernameNotFoundException, DataAccessException
  {
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    MenuService menuService = (MenuService)ServiceLocator.getInstance().getBean("menuService");

    User user = userService.getUserByNameWithPermissions(username);

    if(user != null)
    {
      List perms = new ArrayList();
      Set permNameSet = new HashSet();
      Iterator it = user.getRoles().iterator();
      while(it.hasNext())
      {
        Role role = (Role)it.next();
        Iterator it1 = role.getPermissions().iterator();
        while(it1.hasNext())
        {
          Permission perm = (Permission)it1.next();
          perms.add(new GrantedAuthorityImpl(perm.getName()));
          permNameSet.add(perm.getName());
        }
      }

      List menuTree = menuService.getMenuTree();
      if(menuTree != null)
      {
        List removedMenus = new ArrayList();
        for(int i=0; i<menuTree.size(); i++)
        {
          Menu menu = (Menu)menuTree.get(i);
          if( (menu.getPermName() != null) && !permNameSet.contains(menu.getPermName()))
          {
            removedMenus.add(menu);
          }
          else
          {
            removeNotAllowedChildMenus(menu, permNameSet);
          }
        }

        menuTree.removeAll(removedMenus);
      }

      UserDetails userDetails = new AcegiUser(
        user.getLoginName(),
        (password == null) ? user.getPassword() : password,
        true,  // enabled
        true,  // accountNonExpired
        true,  // credentialsNonExpired
        true,  // accountNonLocked
        (GrantedAuthorityImpl[])perms.toArray(new GrantedAuthorityImpl[] {}),
        //new GrantedAuthorityImpl[] { new GrantedAuthorityImpl("ROLE_SUPERVISOR") }
        user,
        menuTree,
        permNameSet
      );

      return userDetails;
    }

    throw new UsernameNotFoundException("Username is invalid: " + username);
  }

  private void removeNotAllowedChildMenus(Menu menu, Set permNameSet)
  {
    if((menu == null) || (permNameSet == null)) return;

    List removedMenus = new ArrayList();
    Iterator it = menu.getChildMenus().iterator();
    while(it.hasNext())
    {
      Menu childMenu = (Menu)it.next();
      if( (childMenu.getPermName() != null) && !permNameSet.contains(childMenu.getPermName()))
      {
        removedMenus.add(childMenu);
      }
      else
      {
        removeNotAllowedChildMenus(childMenu, permNameSet);
      }
    }

    menu.getChildMenus().removeAll(removedMenus);
  }
}
