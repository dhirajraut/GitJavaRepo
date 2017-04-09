package com.intertek.service;

import java.util.List;

import com.intertek.entity.Link;
import com.intertek.entity.Menu;
import com.intertek.entity.Solution;

public interface MenuService
{
  Solution getSolutionByName(String solutionName);
  Menu getMenuByName(String menuName);
  Link getLinkByName(String linkName);
  void addSolution(Solution solution);
  void saveMenu(Menu menu);
  void addMenu(Menu menu);
  void addLink(Link link);
  void addMenuToSolution(String menuName, String solutionName);

  void addSolutionToRole(String solutionName, String roleName);

  List getSolutionsByUserName(String userName);

  List getLinksWithPermissions();

  List getMenuTree();
}

