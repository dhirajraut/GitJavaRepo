package com.intertek.command;

import java.util.List;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.InspectionServiceShellUtil;
import com.intertek.util.InspectionServiceUtil;
import com.intertek.calculator.*;

/**
 * Recalculate the price of the inspection charges.
 * 
 **/
public class RecalculateInspectionCommand implements Command {
    /**
     * recalculate the price of the inpsection charges.
     * 
     * @param params
     *            - a list of Objects. The first one is AddJobContract. The
     *            second one is the child in the AddJobContract object tree.
     * 
     **/
    public void execute(List params) {
        if ((params == null) || (params.size() < 2))
            return;

        AddJobContract addJobContract = (AddJobContract) params.get(0);
        Object obj = params.get(1);

        if (obj instanceof AddJobContract) {
            // recalculateInspection((AddJobContract)obj);
        }
        else if (obj instanceof AddJobContractVessel) {
            recalculateInspection((AddJobContractVessel) obj, addJobContract);
        }
        else if (obj == null) {
            recalculateInspection(null, addJobContract);
        }
    }

    private void recalculateInspection(AddJobContractVessel addJobContractVessel, AddJobContract addJobContract) {
        if (addJobContract == null)
            return;

        JobContract jobContract = addJobContract.getJobContract();
        if (jobContract == null)
            return;

        JobOrder jobOrder = jobContract.getJobOrder();
        if (jobOrder == null)
            return;

        CalculatorService calculatorService = (CalculatorService) ServiceLocator.getInstance().getBean("calculatorService");
        int modelType = calculatorService.getInspectionModelType(jobContract.getContractCode(), jobOrder.getJobFinishDate());

        try {
            CalculatorUtil.checkVesselTypeForAddJobContract(addJobContract);
        } catch (Throwable t) {
            return;
        }

        if (modelType == 2) {
            InspectionServiceShellUtil.calculateInspectionServiceForShell(addJobContract, addJobContract.getCfgContract(), null, null, null);
        }
        else {
            if (addJobContractVessel != null) {
                InspectionServiceUtil.recalculateInspectionForVesselLevel(addJobContractVessel, addJobContract.getCfgContract(), null, modelType);
            }

            InspectionServiceUtil.recalculateInspectionForContractLevel(addJobContract, addJobContract.getCfgContract(), addJobContractVessel, null, modelType);
        }
    }
    /*
     * private void recalculateInspection(AddJobContract addJobContract) {
     * if(addJobContract == null) return;
     * 
     * JobContract jobContract = addJobContract.getJobContract(); if(jobContract
     * == null) return;
     * 
     * JobOrder jobOrder = jobContract.getJobOrder(); if(jobOrder == null)
     * return;
     * 
     * CalculatorService calculatorService =
     * (CalculatorService)ServiceLocator.getInstance
     * ().getBean("calculatorService"); int modelType =
     * calculatorService.getInspectionModelType( jobContract.getContractCode(),
     * jobOrder.getJobFinishDate() );
     * 
     * if(modelType == 2) {
     * InspectionServiceShellUtil.calculateInspectionServiceForShell(
     * addJobContract, addJobContract.getCfgContract(), null, null, null ); }
     * else { InspectionServiceUtil.recalculateInspectionForContractLevel(
     * addJobContract, addJobContract.getCfgContract(), null, null, modelType );
     * } }
     */
}
