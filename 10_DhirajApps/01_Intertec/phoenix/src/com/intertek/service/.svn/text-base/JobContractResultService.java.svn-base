package com.intertek.service;

import java.util.List;

import com.intertek.domain.AddJobContract;

public interface JobContractResultService
{
  void saveJobContractResults(AddJobContract[] addJobContracts);

  List getJobContractServices(Long jobContractId);
  List getJobContractVesselServices(Long jobContractId, Integer vesselId);
  List getJobContractProductServices(Long jobContractId, Integer vesselId, Integer prodSeqNum);
  List getJobContractProductInspectionResults(Long jobContractId, Integer vesselId, Integer prodSeqNum);

}

