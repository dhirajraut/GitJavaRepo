package com.intertek.command;

import java.util.*;

import com.intertek.command.*;
import com.intertek.domain.*;
import com.intertek.entity.*;
import com.intertek.util.*;

/**
 * A command implementation used to delete the selected charges of
 * AddJobContract tree objects.
 * 
 */
public class DeleteChargesCommand implements Command {
    private boolean hasInspectionChargeDeleted;

    public DeleteChargesCommand() {
    }

    /**
     * Get the hasInspectionChargeDeleted flag.
     * 
     * @return - if there are any inspection charges get deleted, it will return
     *         true.
     **/
    public boolean getHasInspectionChargeDeleted() {
        return hasInspectionChargeDeleted;
    }

    /**
     * delete the selected charges of AddJobContract tree objects.
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

        if (obj instanceof AddJobContractProd) {
            delete((AddJobContractProd) obj);
        }
        else if (obj instanceof AddJobContractVessel) {
            delete((AddJobContractVessel) obj);
        }
        else if (obj instanceof AddJobContract) {
            delete((AddJobContract) obj);
        }
    }

    private void delete(AddJobContractProd addJobContractProd) {
        if (addJobContractProd == null)
            return;

        deleteTest(addJobContractProd, addJobContractProd.getJobContractTestExts());
        deleteSlate(addJobContractProd, addJobContractProd.getJobContractSlateExts());
        deleteManualTest(addJobContractProd, addJobContractProd.getJobContractManualTestExts());

        deleteServices(addJobContractProd);
        /*
         * boolean[] selects = addJobContractProd.getSelects(); List results =
         * addJobContractProd.getResults();
         * 
         * if((selects == null) || (results == null)) return;
         * 
         * if(selects.length != results.size()) return;
         * 
         * List removed = new ArrayList();
         * 
         * for(int i=0; i<results.size(); i++) {
         * JobContractProductInspectionResult jResult =
         * (JobContractProductInspectionResult)results.get(i);
         * 
         * if(selects[i]) { addJobContractProd.getDeletedResults().add(jResult);
         * addJobContractProd.getJobContractProd().getResults().remove(jResult);
         * 
         * removed.add(jResult); hasInspectionChargeDeleted = true; } }
         * 
         * results.removeAll(removed);
         */
    }

    private void delete(AddJobContractVessel addJobContractVessel) {
        if (addJobContractVessel == null)
            return;

        deleteServices(addJobContractVessel);
        /*
         * boolean[] selects = addJobContractVessel.getSelects(); List results =
         * addJobContractVessel.getResults();
         * 
         * if((selects == null) || (results == null)) return;
         * 
         * if(selects.length != results.size()) return;
         * 
         * List removed = new ArrayList();
         * 
         * for(int i=0; i<results.size(); i++) {
         * JobContractVesselInspectionResult jResult =
         * (JobContractVesselInspectionResult)results.get(i);
         * 
         * if(selects[i]) {
         * addJobContractVessel.getDeletedResults().add(jResult);
         * addJobContractVessel
         * .getJobContractVessel().getResults().remove(jResult);
         * 
         * removed.add(jResult); } }
         * 
         * results.removeAll(removed);
         */
    }

    private void delete(AddJobContract addJobContract) {
        if (addJobContract == null)
            return;

        deleteServices(addJobContract);
        /*
         * boolean[] selects = addJobContract.getSelects(); List results =
         * addJobContract.getResults();
         * 
         * if((selects == null) || (results == null)) return;
         * 
         * if(selects.length != results.size()) return;
         * 
         * List removed = new ArrayList();
         * 
         * for(int i=0; i<results.size(); i++) { JobContractInspectionResult
         * jResult = (JobContractInspectionResult)results.get(i);
         * 
         * if(selects[i]) { addJobContract.getDeletedResults().add(jResult);
         * addJobContract.getJobContract().getResults().remove(jResult);
         * 
         * removed.add(jResult); } }
         * 
         * results.removeAll(removed);
         */
    }

    private void deleteTest(AddJobContractProd addJobContractProd, List testExts) {
        if (testExts == null)
            return;

        List removed = new ArrayList();

        for (int i = 0; i < testExts.size(); i++) {
            JobContractTestExt testExt = (JobContractTestExt) testExts.get(i);

            if (testExt.getSelected()) {
                addJobContractProd.getJobContractProd().getJobContractTests().remove(testExt.getTest());

                removed.add(testExt);
            }
        }

        testExts.removeAll(removed);
    }

    private void deleteSlate(AddJobContractProd addJobContractProd, List slateExts) {
        if (slateExts == null)
            return;

        List removed = new ArrayList();

        for (int i = 0; i < slateExts.size(); i++) {
            JobContractSlateExt slateExt = (JobContractSlateExt) slateExts.get(i);

            if (slateExt.getSelected()) {
                addJobContractProd.getJobContractProd().getJobContractSlates().remove(slateExt.getSlate());

                removed.add(slateExt);
            }
        }

        slateExts.removeAll(removed);
    }

    private void deleteManualTest(AddJobContractProd addJobContractProd, List manualTestExts) {
        if (manualTestExts == null)
            return;

        List removed = new ArrayList();

        for (int i = 0; i < manualTestExts.size(); i++) {
            JobContractManualTestExt manualTestExt = (JobContractManualTestExt) manualTestExts.get(i);

            if (manualTestExt.getSelected()) {
                addJobContractProd.getJobContractProd().getJobContractManualTests().remove(manualTestExt.getManualTest());

                removed.add(manualTestExt);
            }
        }

        manualTestExts.removeAll(removed);
    }

    private void deleteServices(AddJobContractProd addJobContractProd) {
        if (addJobContractProd == null)
            return;

        AddJobContractProductServices addJobContractProductServices = addJobContractProd.getAddJobContractProductServices();
        if (addJobContractProductServices != null) {
            List addedServiceExtList = addJobContractProductServices.getAddedJobContractProductServices();

            List removed = new ArrayList();
            for (int l = 0; l < addedServiceExtList.size(); l++) {
                JobContractProductServiceExt serviceExt = (JobContractProductServiceExt) addedServiceExtList.get(l);
                boolean selected = isSelected(serviceExt.getSelects());

                if (selected) {
                    JobContractProductService jobContractProductService = serviceExt.getService();
                    addJobContractProd.getJobContractProd().getJobContractProductServices().remove(jobContractProductService);

                    removed.add(serviceExt);
                }
            }

            addedServiceExtList.removeAll(removed);
        }
    }

    private void deleteServices(AddJobContractVessel addJobContractVessel) {
        if (addJobContractVessel == null)
            return;

        AddJobContractVesselServices addJobContractVesselServices = addJobContractVessel.getAddJobContractVesselServices();
        if (addJobContractVesselServices != null) {
            List addedServiceExtList = addJobContractVesselServices.getAddedJobContractVesselServices();

            List removed = new ArrayList();
            for (int l = 0; l < addedServiceExtList.size(); l++) {
                JobContractVesselServiceExt serviceExt = (JobContractVesselServiceExt) addedServiceExtList.get(l);
                boolean selected = isSelected(serviceExt.getSelects());

                if (selected) {
                    JobContractVesselService jobContractVesselService = serviceExt.getService();
                    addJobContractVessel.getJobContractVessel().getJobContractVesselServices().remove(jobContractVesselService);

                    removed.add(serviceExt);
                }
            }

            addedServiceExtList.removeAll(removed);
        }
    }

    private void deleteServices(AddJobContract addJobContract) {
        if (addJobContract == null)
            return;

        AddJobContractServices addJobContractServices = addJobContract.getAddJobContractServices();
        if (addJobContractServices != null) {
            List addedServiceExtList = addJobContractServices.getAddedJobContractServices();

            List removed = new ArrayList();
            for (int l = 0; l < addedServiceExtList.size(); l++) {
                JobContractServiceExt serviceExt = (JobContractServiceExt) addedServiceExtList.get(l);
                boolean selected = isSelected(serviceExt.getSelects());

                if (selected) {
                    JobContractService jobContractService = serviceExt.getService();
                    addJobContract.getJobContract().getJobContractServices().remove(jobContractService);

                    removed.add(serviceExt);
                }
            }

            addedServiceExtList.removeAll(removed);
        }
    }

    private boolean isSelected(boolean[] selects) {
        if (selects == null)
            return false;

        for (int i = 0; i < selects.length; i++) {
            if (selects[i])
                return true;
        }

        return false;
    }
}
