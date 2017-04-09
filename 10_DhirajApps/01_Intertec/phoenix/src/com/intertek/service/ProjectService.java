package com.intertek.service;

import java.util.List;

import com.intertek.domain.ProjectSearch;

public interface ProjectService {
	List getProjectsByProjectNumber(String number,String custCode);

	void searchProjects(ProjectSearch search,String sortFlag);

}
