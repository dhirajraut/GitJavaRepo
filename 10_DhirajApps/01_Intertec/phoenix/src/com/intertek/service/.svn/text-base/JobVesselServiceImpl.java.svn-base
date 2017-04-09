package com.intertek.service;

import java.util.List;

public class JobVesselServiceImpl extends BaseService implements JobVesselService
{
  public List getJobVesselsByJobNumberWithDetail(String jobNumber)
  {
    return getDao().find(
      "select distinct jv from JobVessel jv"
      + " left join fetch jv.jobProds jp "
      + " left join fetch jp.jobProdSamples jps "
      + " left join fetch jps.jobTests jpst left join fetch jpst.test "
      + " left join fetch jps.jobManualTests "
      + " left join fetch jps.jobSlates jpss left join fetch jpss.slate "
      + " where jv.jobVesselId.jobNumber = ? order by jv.jobVesselId.linkedVslRow",
      new Object[] {jobNumber}
    );
  }
}
