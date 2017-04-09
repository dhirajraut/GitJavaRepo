package com.intertek.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractProductServices;
import com.intertek.domain.AddJobContractServices;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobContractVesselServices;
import com.intertek.domain.JobContractManualTestExt;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.domain.JobContractSlateExt;
import com.intertek.domain.JobContractTestExt;
import com.intertek.domain.JobContractVesselServiceExt;
import com.intertek.entity.JobContractInspectionResult;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractProductService;
import com.intertek.entity.JobContractProductServiceResult;
import com.intertek.entity.JobContractService;
import com.intertek.entity.JobContractServiceResult;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobContractVesselService;
import com.intertek.entity.JobContractVesselServiceResult;
import com.intertek.entity.Prebill;
import com.intertek.util.Constants;

/**
 * Populate the information for the prebills of the AddJobContract object tree.
 * 
 * The information to be populated are vessel sort number, lot sort number, and
 * charge sort number.
 * 
 **/

public class PopulatePrebillInfoCommand implements Command {
    /**
     * populate the information for the prebills.
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

        if (obj instanceof AddJobContractServices) {
            populate((AddJobContractServices) obj);
        }
        else if (obj instanceof AddJobContract) {
            populate((AddJobContract) obj);
        }
    }

    private void populate(AddJobContractServices addJobContractServices) {
        if (addJobContractServices == null)
            return;

        int lastNum = 1;
        List addedServiceExtList = addJobContractServices.getAddedJobContractServices();
        for (int i = 0; i < addedServiceExtList.size(); i++) {
            JobContractServiceExt serviceExt = (JobContractServiceExt) addedServiceExtList.get(i);
            Integer sortOrderNum = serviceExt.getSortOrderNum();
            if (sortOrderNum != null) {
                lastNum = sortOrderNum.intValue();
            }

            populate(serviceExt, lastNum);
            lastNum++;
        }
    }

    private void populate(JobContractServiceExt serviceExt, int chargeSortNum) {
        if (serviceExt == null)
            return;
        JobContractService service = serviceExt.getService();
        if (service == null)
            return;

        List results = serviceExt.getResults();
        if ((results == null) || (results.size() == 0))
            return;

        Map dataMap = new HashMap();
        dataMap.put(Constants.VESSEL_SORT_NUM, Constants.NULL_SORT_NUM);
        dataMap.put(Constants.LOT_SORT_NUM, Constants.NULL_SORT_NUM);

        for (int i = 0; i < results.size(); i++) {
            JobContractServiceResult jResult = (JobContractServiceResult) results.get(i);
            dataMap.put(Constants.CHARGE_SORT_NUM, Constants.SERVICE_CHARGE_START_NO + chargeSortNum * Constants.BASE_SORT_NUM + i);

            doPopulate(jResult.getPrebills(), dataMap);
        }
    }

    private void populate(AddJobContract addJobContract) {
        if (addJobContract == null)
            return;

        AddJobContractVessel[] addJobContractVessels = addJobContract.getAddJobContractVessels();
        if (addJobContractVessels != null) {
            int lastVesselSortNum = 1;
            for (int i = 0; i < addJobContractVessels.length; i++) {
                Integer vesselSortNum = addJobContractVessels[i].getJobContractVessel().getSortOrderNum();
                if (vesselSortNum != null) {
                    lastVesselSortNum = vesselSortNum.intValue();
                }

                populate(addJobContractVessels[i].getAddJobContractVesselServices(), lastVesselSortNum);

                populate(addJobContractVessels[i], lastVesselSortNum);

                AddJobContractProd[] addJobContractProds = addJobContractVessels[i].getAddJobContractProds();
                if (addJobContractProds != null) {
                    int lastLotSortNum = 1;
                    for (int k = 0; k < addJobContractProds.length; k++) {
                        Integer lotSortNum = addJobContractProds[k].getJobContractProd().getSortOrderNum();
                        if (lotSortNum != null) {
                            lastLotSortNum = lotSortNum.intValue();
                        }

                        populate(addJobContractProds[k], lastVesselSortNum, lastLotSortNum);
                        lastLotSortNum++;
                    }
                }

                lastVesselSortNum++;
            }
        }

        List results = addJobContract.getResults();
        if ((results == null) || (results.size() == 0))
            return;

        Map dataMap = new HashMap();
        dataMap.put(Constants.VESSEL_SORT_NUM, Constants.NULL_SORT_NUM);
        dataMap.put(Constants.LOT_SORT_NUM, Constants.NULL_SORT_NUM);

        for (int i = 0; i < results.size(); i++) {
            JobContractInspectionResult jResult = (JobContractInspectionResult) results.get(i);
            dataMap.put(Constants.CHARGE_SORT_NUM, Constants.SERVICE_CHARGE_START_NO + 10000 + i);

            doPopulate(jResult.getPrebills(), dataMap);
        }
    }

    private void populate(AddJobContractVesselServices addJobContractVesselServices, int vesselSortNum) {
        if (addJobContractVesselServices == null)
            return;

        int lastNum = 1;
        List addedServiceExtList = addJobContractVesselServices.getAddedJobContractVesselServices();
        for (int i = 0; i < addedServiceExtList.size(); i++) {
            JobContractVesselServiceExt serviceExt = (JobContractVesselServiceExt) addedServiceExtList.get(i);
            Integer sortOrderNum = serviceExt.getSortOrderNum();
            if (sortOrderNum != null) {
                lastNum = sortOrderNum.intValue();
            }

            populate(serviceExt, vesselSortNum, lastNum);
            lastNum++;
        }
    }

    private void populate(JobContractVesselServiceExt serviceExt, int vesselSortNum, int chargeSortNum) {
        if (serviceExt == null)
            return;
        JobContractVesselService service = serviceExt.getService();
        if (service == null)
            return;
        JobContractVessel vessel = service.getJobContractVessel();
        if (vessel == null)
            return;

        List results = serviceExt.getResults();
        if ((results == null) || (results.size() == 0))
            return;

        Map dataMap = new HashMap();
        dataMap.put(Constants.VESSEL_SORT_NUM, vesselSortNum);
        dataMap.put(Constants.LOT_SORT_NUM, Constants.NULL_SORT_NUM);
        dataMap.put(Constants.VESSEL_NAME, vessel.getVesselName());

        for (int i = 0; i < results.size(); i++) {
            JobContractVesselServiceResult jResult = (JobContractVesselServiceResult) results.get(i);
            dataMap.put(Constants.CHARGE_SORT_NUM, Constants.SERVICE_CHARGE_START_NO + chargeSortNum * Constants.BASE_SORT_NUM + i);

            doPopulate(jResult.getPrebills(), dataMap);
        }
    }

    private void populate(AddJobContractVessel addJobContractVessel, int vesselSortNum) {
        if (addJobContractVessel == null)
            return;

        List results = addJobContractVessel.getResults();
        if ((results == null) || (results.size() == 0))
            return;

        JobContractVessel vessel = addJobContractVessel.getJobContractVessel();
        if (vessel == null)
            return;

        Map dataMap = new HashMap();
        dataMap.put(Constants.VESSEL_SORT_NUM, vesselSortNum);
        dataMap.put(Constants.LOT_SORT_NUM, Constants.NULL_SORT_NUM);
        dataMap.put(Constants.VESSEL_NAME, vessel.getVesselName());

        for (int i = 0; i < results.size(); i++) {
            JobContractVesselInspectionResult jResult = (JobContractVesselInspectionResult) results.get(i);
            dataMap.put(Constants.CHARGE_SORT_NUM, Constants.SERVICE_CHARGE_START_NO + 10000 + i);

            doPopulate(jResult.getPrebills(), dataMap);
        }
    }

    private void populate(AddJobContractProductServices addJobContractProductServices, int vesselSortNum, int lotSortNum) {
        if (addJobContractProductServices == null)
            return;

        int lastNum = 1;
        List addedServiceExtList = addJobContractProductServices.getAddedJobContractProductServices();
        for (int i = 0; i < addedServiceExtList.size(); i++) {
            JobContractProductServiceExt serviceExt = (JobContractProductServiceExt) addedServiceExtList.get(i);
            Integer sortOrderNum = serviceExt.getSortOrderNum();
            if (sortOrderNum != null) {
                lastNum = sortOrderNum.intValue();
            }

            populate(serviceExt, vesselSortNum, lotSortNum, lastNum);
            lastNum++;
        }
    }

    private void populate(JobContractProductServiceExt serviceExt, int vesselSortNum, int lotSortNum, int chargeSortNum) {
        if (serviceExt == null)
            return;
        JobContractProductService service = serviceExt.getService();
        if (service == null)
            return;
        JobContractProd prod = service.getJobContractProd();
        if (prod == null)
            return;
        JobContractVessel vessel = prod.getJobContractVessel();
        if (vessel == null)
            return;

        List results = serviceExt.getResults();
        if ((results == null) || (results.size() == 0))
            return;

        Map dataMap = new HashMap();
        dataMap.put(Constants.VESSEL_SORT_NUM, vesselSortNum);
        dataMap.put(Constants.LOT_SORT_NUM, lotSortNum);
        dataMap.put(Constants.VESSEL_NAME, vessel.getVesselName());
        dataMap.put(Constants.PRODUCT_NAME, prod.getJobProductName());

        for (int i = 0; i < results.size(); i++) {
            JobContractProductServiceResult jResult = (JobContractProductServiceResult) results.get(i);
            dataMap.put(Constants.CHARGE_SORT_NUM, Constants.SERVICE_CHARGE_START_NO + chargeSortNum * Constants.BASE_SORT_NUM + i);

            doPopulate(jResult.getPrebills(), dataMap);
        }
    }

    private void populate(AddJobContractProd addJobContractProd, int vesselSortNum, int lotSortNum) {
        if (addJobContractProd == null)
            return;
        JobContractProd prod = addJobContractProd.getJobContractProd();
        if (prod == null)
            return;
        JobContractVessel vessel = prod.getJobContractVessel();
        if (vessel == null)
            return;

        Map dataMap = new HashMap();
        dataMap.put(Constants.VESSEL_SORT_NUM, vesselSortNum);
        dataMap.put(Constants.LOT_SORT_NUM, lotSortNum);
        dataMap.put(Constants.VESSEL_NAME, vessel.getVesselName());
        dataMap.put(Constants.PRODUCT_NAME, prod.getJobProductName());

        populate(addJobContractProd.getAddJobContractProductServices(), vesselSortNum, lotSortNum);

        List results = addJobContractProd.getResults();
        if (results != null) {
            for (int i = 0; i < results.size(); i++) {
                JobContractProductInspectionResult jResult = (JobContractProductInspectionResult) results.get(i);
                dataMap.put(Constants.CHARGE_SORT_NUM, Constants.INSPECTION_CHARGE_START_NO + i);

                doPopulate(jResult.getPrebills(), dataMap);
            }
        }

        List jobContractTestExts = addJobContractProd.getJobContractTestExts();
        if (jobContractTestExts != null) {
            int lastNum = 1;
            for (int i = 0; i < jobContractTestExts.size(); i++) {
                JobContractTestExt testExt = (JobContractTestExt) jobContractTestExts.get(i);
                Integer sortOrderNum = testExt.getSortOrderNum();
                if (sortOrderNum != null) {
                    lastNum = sortOrderNum.intValue();
                }
                dataMap.put(Constants.CHARGE_SORT_NUM, Constants.TEST_CHARGE_START_NO + lastNum);

                doPopulate(testExt.getTest().getPrebills(), dataMap);

                lastNum++;
            }
        }

        List jobContractSlateExts = addJobContractProd.getJobContractSlateExts();
        if (jobContractSlateExts != null) {
            int lastNum = 1;
            for (int i = 0; i < jobContractSlateExts.size(); i++) {
                JobContractSlateExt slateExt = (JobContractSlateExt) jobContractSlateExts.get(i);
                Integer sortOrderNum = slateExt.getSortOrderNum();
                if (sortOrderNum != null) {
                    lastNum = sortOrderNum.intValue();
                }
                dataMap.put(Constants.CHARGE_SORT_NUM, Constants.SLATE_CHARGE_START_NO + lastNum);

                doPopulate(slateExt.getSlate().getPrebills(), dataMap);

                lastNum++;
            }
        }

        List jobContractManualTestExts = addJobContractProd.getJobContractManualTestExts();
        if (jobContractManualTestExts != null) {
            int lastNum = 1;
            for (int i = 0; i < jobContractManualTestExts.size(); i++) {
                JobContractManualTestExt manualTestExt = (JobContractManualTestExt) jobContractManualTestExts.get(i);
                Integer sortOrderNum = manualTestExt.getSortOrderNum();
                if (sortOrderNum != null) {
                    lastNum = sortOrderNum.intValue();
                }
                dataMap.put(Constants.CHARGE_SORT_NUM, Constants.MANUAL_TEST_CHARGE_START_NO + lastNum);

                doPopulate(manualTestExt.getManualTest().getPrebills(), dataMap);

                lastNum++;
            }
        }
    }

    private void doPopulate(Set prebills, Map dataMap) {
        if ((prebills == null) || (dataMap == null))
            return;

        Iterator it = prebills.iterator();
        while (it.hasNext()) {
            Prebill prebill = (Prebill) it.next();

            prebill.setVesselSortNum((Integer) dataMap.get(Constants.VESSEL_SORT_NUM));
            prebill.setLotSortNum((Integer) dataMap.get(Constants.LOT_SORT_NUM));
            prebill.setChargeSortNum((Integer) dataMap.get(Constants.CHARGE_SORT_NUM));

            prebill.setLevel0((String) dataMap.get(Constants.VESSEL_NAME));
            prebill.setLevel1((String) dataMap.get(Constants.PRODUCT_NAME));
        }
    }
}
