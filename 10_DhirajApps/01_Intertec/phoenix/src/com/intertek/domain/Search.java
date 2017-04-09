package com.intertek.domain;

import java.util.List;

import com.intertek.pagination.Pagination;

public class Search
{
  private Pagination pagination;
  private List results;
  private List totalResults;

  public Pagination getPagination()
  {
    return pagination;
  }

  public void setPagination(Pagination pagination)
  {
    this.pagination = pagination;
  }

  public List getResults()
  {
    return results;
  }

  public void setResults(List results)
  {
    this.results = results;
  }
public List getTotalResults() {
	return totalResults;
}

public void setTotalResults(List totalResults) {
	this.totalResults = totalResults;
}
}
