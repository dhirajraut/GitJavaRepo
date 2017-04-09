package com.intertek.service;

import java.util.Iterator;
import java.util.List;

import com.intertek.entity.Link;
import com.intertek.entity.Menu;
import com.intertek.entity.Role;
import com.intertek.entity.Solution;
import com.intertek.locator.ServiceLocator;

public class MenuServiceImpl extends BaseService implements MenuService
{
  public Solution getSolutionByName(String solutionName)
  {
    List solutions = getDao().find("from Solution s where s.name = ?",
      new Object[] { solutionName }
    );

    if(solutions.size() > 0) return (Solution)solutions.get(0);

    return null;
  }

  public Menu getMenuByName(String menuName)
  {
    List menus = getDao().find("from Menu p where p.name = ?",
      new Object[] { menuName }
    );

    if(menus.size() > 0) return (Menu)menus.get(0);

    return null;
  }

  public Link getLinkByName(String linkName)
  {
    List links = getDao().find("from Link l where l.name = ?",
      new Object[] { linkName }
    );

    if(links.size() > 0) return (Link)links.get(0);

    return null;
  }

  public void addSolution(Solution solution)
  {
    Solution existedSolution = getSolutionByName(solution.getName());
    if(existedSolution != null)
    {
      throw new RuntimeException("Solution Name has been taken.");
    }

    getDao().save(solution);
  }

  public void saveMenu(Menu menu)
  {
    getDao().save(menu);
  }

  public void addMenu(Menu menu)
  {
    Menu existedMenu = getMenuByName(menu.getName());
    if(existedMenu != null)
    {
      throw new RuntimeException("Menu Name has been taken.");
    }

    getDao().save(menu);
  }

  public void addLink(Link link)
  {
    if(link == null) return;

    Link existedLink = getLinkByName(link.getName());
    if(existedLink != null)
    {
      throw new RuntimeException("Link Name has been taken.");
    }

    getDao().save(link);
  }

  public void addMenuToSolution(String menuName, String solutionName)
  {
    Solution solution = getSolutionByName(solutionName);
    if(solution == null)
    {
      throw new RuntimeException("Solution: " + solutionName + " does not exist in addMenuToSolution.");
    }

    Menu menu = getMenuByName(menuName);
    if(menu == null)
    {
      throw new RuntimeException("Menu: " + menuName + " does not exist in addMenuToSolution.");
    }

    solution.getMenus().add(menu);
  }

  public void addSolutionToRole(String solutionName, String roleName)
  {
    Solution solution = getSolutionByName(solutionName);
    if(solution == null)
    {
      throw new RuntimeException("Solution: " + solutionName + " does not exist in addMenuToSolution.");
    }

    AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
    Role role = (Role)adminService.getRoleByName(roleName);
    if(role == null)
    {
      throw new RuntimeException("Role doesn't exist: " + roleName);
    }

    role.getSolutions().add(solution);
  }

  public List getSolutionsByUserName(String userName)
  {
    List solutions = getDao().find(
      "select distinct s from Solution s left join fetch s.menus left join s.roles r left join r.users u where u.loginName = ?",
      new Object[] { userName }
    );

    Iterator it = solutions.iterator();
    while(it.hasNext())
    {
      Solution solution = (Solution)it.next();

      iterateSolution(solution);
    }

    return solutions;
  }

  private void iterateSolution(Solution solution)
  {
    Iterator it = solution.getMenus().iterator();
    while(it.hasNext())
    {
      Menu menu = (Menu)it.next();
      String name = menu.getName();

      iterateMenu(menu);
    }
  }

  private void iterateMenu(Menu menu)
  {
    Iterator it = menu.getChildMenus().iterator();
    while(it.hasNext())
    {
      Menu childMenu = (Menu)it.next();
      String name = childMenu.getName();

      iterateMenu(childMenu);
    }
  }

  public List getLinksWithPermissions()
  {
    return getDao().find(
      "select distinct l from Link l left join fetch l.permissions",
      null
    );
  }

  public List getMenuTree()
  {
    List menus = getDao().find(
      "select m from Menu m where m.parentMenuName is null order by m.seqNumber",
      null
    );

    for(int i=0; i<menus.size(); i++)
    {
      iterateMenu((Menu)menus.get(i));
    }

    return menus;
  }
}

