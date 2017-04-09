package com.intertek.service;

import java.util.List;

public interface JobVesselService
{
  List getJobVesselsByJobNumberWithDetail(String jobNumber);
}

