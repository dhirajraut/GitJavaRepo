package com.intertek.service;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.ProjectSearch;
import com.intertek.pagination.Pagination;

public class ProjectServiceImpl extends BaseService implements ProjectService {

  public List getProjectsByProjectNumber(String number,String custCode)
  {
    System.out.println("======= in searchNominationTime = " + number);
    System.out.println("======= in searchNominationTime = " +custCode);
    if (number == null)
      return new ArrayList();
    String projectNumberSearch = number.toUpperCase() + '%';
    String custCodeSearch=custCode.toUpperCase();
List  projectNumbers=getDao().find("from Project p where upper(p.projectNumber) like ?  and upper(p.custCode) = ?",
  new Object[]{projectNumberSearch,custCodeSearch});
System.out.println("size of projectNumbers in controller :" + projectNumbers.size());

return projectNumbers;

    }


  public void searchProjects(ProjectSearch search,String sortFlag) {
    if (search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;


   String strProjectNumberSearch = search.getProjectNumber().getValue();

    System.out.println("ProjectNumber" + strProjectNumberSearch);

    String cc = search.getCustCode().getValue();
    String custCode=cc.toUpperCase();

  if ((strProjectNumberSearch != null) && !"".equals(strProjectNumberSearch.trim()))
    {
      strProjectNumberSearch = '%' + strProjectNumberSearch.toUpperCase() + '%';
      sb.append(" where upper(p.projectNumber) like ? and upper(p.custCode) = ? ");
      params.add(strProjectNumberSearch);
      params.add( custCode);
      hasWhere = true;
    }



    if ((custCode != null)&& !"".equals(custCode.trim()))
    {
      if (hasWhere)
        sb.append(" and ");
          else
            {
            hasWhere = true;
            sb.append(" where ");
            }
                    String strCustomerNumberSearch = '%' + custCode.toUpperCase() + '%';
          sb.append(" upper(p.custCode) like ?");
          params.add(strCustomerNumberSearch);
          hasWhere = true;
    }


    String strProjectTypeSearch = search.getProjectType().getValue();

    if ((strProjectTypeSearch != null) && !"".equals(strProjectTypeSearch.trim()))
      {
      if (hasWhere)
        sb.append(" and ");
          else
          {
          hasWhere = true;
          sb.append(" where ");
          }
            strProjectTypeSearch = '%' + strProjectTypeSearch.toUpperCase() + '%';
      sb.append(" upper(p.projectType) like ? and upper(p.custCode) =?");
      params.add(strProjectTypeSearch);
      params.add(custCode);
      }

    else
  {
    if(hasWhere)
      sb.append(" and ");
      else
      {
      hasWhere = true;
      sb.append(" where ");
      }
      sb.append("upper(p.custCode) = ?");
       params.add(custCode);
    }


    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
        {
    if (pagination != null)
        {
      System.out.println("In side pagination");
          if (pagination.getTotalRecord() > 0)
          {
            List counts = getDao().find("select count(p.projectNumber) from Project p "+ sb.toString(), params.toArray());

                if (counts.size() > 0)
                  {
                  System.out.println("No Of records" + counts.size());
                  Number count = (Number) counts.get(0);
                  pagination.setTotalRecord(count.intValue());
                   }
          }
      System.out.println("string buffer :" + sb.toString());
      results = getDao().find("select distinct p from Project p " + sb.toString() + " order by p.projectNumber", params.toArray(), pagination);
      System.out.println("Results= :" + results.size());
      pagination.calculatePages();
    }
       else
        {
        results = getDao().find("select distinct p from Project p " + sb.toString(),params.toArray());
        }

    search.setResults(results);
    search.setPagination(pagination);
        }else
        {
          String orderByValue="order by p."+sortFlag;
          if (pagination != null)
            {
          System.out.println("In side pagination");
              if (pagination.getTotalRecord() > 0)
              {
                List counts = getDao().find("select count(p.projectNumber) from Project p "+ sb.toString(), params.toArray());

                    if (counts.size() > 0)
                      {
                      System.out.println("No Of records" + counts.size());
                      Number count = (Number) counts.get(0);
                      pagination.setTotalRecord(count.intValue());
                       }
              }
          pagination.calculatePages();
        }

          // START : #119240
          //results = getDao().find("select distinct p from Project p " + sb.toString() + orderByValue, params.toArray(),pagination);
          if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
        	  results = getDao().find("select distinct p from Project p " + sb.toString() + orderByValue + " " + search.getSortOrderFlag(), params.toArray(),pagination); 
          }else{
        	  results = getDao().find("select distinct p from Project p " + sb.toString() + orderByValue, params.toArray(),pagination);
          }
        // END : #119240
          
         // search.setTotalResults(results);
          search.setResults(results);
          search.setPagination(pagination);
        }

  }

}
