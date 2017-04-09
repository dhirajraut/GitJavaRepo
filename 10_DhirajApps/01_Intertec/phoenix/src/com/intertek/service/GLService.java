package com.intertek.service;

import com.intertek.entity.GLAccount;
import com.intertek.entity.GLDepartment;
import com.intertek.entity.GLProductGroup;

public interface GLService
{
  void saveGLAccount(GLAccount glAccount);

  void saveGLDepartment(GLDepartment glDepartment);

  void saveGLProductGroup(GLProductGroup glProductGroup);
}

