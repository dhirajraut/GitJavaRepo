package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.AddTaxArticles;
import com.intertek.entity.TaxArticle;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.TaxService;

public class TaxArticlesDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
    AddTaxArticles addTaxArticle = new AddTaxArticles();
    List taxArticles = taxService.getAllTaxArticles(addTaxArticle);
    
    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    
    for(int i=0; i<taxArticles.size(); i++)
    {
      TaxArticle taxArticle = (TaxArticle) taxArticles.get(i);

      Field field = new Field();
      String name = taxArticle.getTaxArticleDescription();
      field.setName(name);
      field.setValue(taxArticle.getTaxArticleCode());
      results.add(field);
    }

    return results;
  }
}
