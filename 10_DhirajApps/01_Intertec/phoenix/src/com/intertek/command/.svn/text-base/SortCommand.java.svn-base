package com.intertek.command;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractProductServices;
import com.intertek.domain.AddJobContractServices;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobContractVesselServices;
import com.intertek.sort.SortOrderNumComparator;

/**
 * Sort the charges of the AddJobContract child tree.
 **/

public class SortCommand implements Command {
    /**
     * Sort the charges of the AddJobContract child tree.
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
            sort((AddJobContractServices) obj);
        }
        else if (obj instanceof AddJobContractVesselServices) {
            sort((AddJobContractVesselServices) obj);
        }
        else if (obj instanceof AddJobContractProductServices) {
            sort((AddJobContractProductServices) obj);
        }
        else if (obj instanceof AddJobContract) {
            sort((AddJobContract) obj);
        }
        else if (obj instanceof AddJobContractVessel) {
            sort((AddJobContractVessel) obj);
        }
        else if (obj instanceof AddJobContractProd) {
            sort((AddJobContractProd) obj);
        }
    }

    private void sort(AddJobContractServices addJobContractServices) {
        if (addJobContractServices == null)
            return;

        List addedServiceExtList = addJobContractServices.getAddedJobContractServices();
        Collections.sort(addedServiceExtList, new SortOrderNumComparator());
    }

    private void sort(AddJobContractVesselServices addJobContractVesselServices) {
        if (addJobContractVesselServices == null)
            return;

        List addedServiceExtList = addJobContractVesselServices.getAddedJobContractVesselServices();
        Collections.sort(addedServiceExtList, new SortOrderNumComparator());
    }

    private void sort(AddJobContractProductServices addJobContractProductServices) {
        if (addJobContractProductServices == null)
            return;

        List addedServiceExtList = addJobContractProductServices.getAddedJobContractProductServices();
        Collections.sort(addedServiceExtList, new SortOrderNumComparator());
    }

    private void sort(AddJobContract addJobContract) {
        if (addJobContract == null)
            return;

        SortOrderNumComparator sortOrderNumComparator = new SortOrderNumComparator();

        AddJobContractVessel[] addJobContractVessels = addJobContract.getAddJobContractVessels();
        if (addJobContractVessels != null)
            Arrays.sort(addJobContractVessels, sortOrderNumComparator);
    }

    private void sort(AddJobContractVessel addJobContractVessel) {
        if (addJobContractVessel == null)
            return;

        SortOrderNumComparator sortOrderNumComparator = new SortOrderNumComparator();

        AddJobContractProd[] addJobContractProds = addJobContractVessel.getAddJobContractProds();
        if (addJobContractProds != null)
            Arrays.sort(addJobContractProds, sortOrderNumComparator);
    }

    private void sort(AddJobContractProd addJobContractProd) {
        if (addJobContractProd == null)
            return;

        SortOrderNumComparator sortOrderNumComparator = new SortOrderNumComparator();

        // List inspectionResultExts =
        // addJobContractProd.getInspectionResultExts();
        // if(inspectionResultExts != null)
        // Collections.sort(inspectionResultExts, sortOrderNumComparator);

        List jobContractTestExts = addJobContractProd.getJobContractTestExts();
        if (jobContractTestExts != null)
            Collections.sort(jobContractTestExts, sortOrderNumComparator);

        List jobContractSlateExts = addJobContractProd.getJobContractSlateExts();
        if (jobContractSlateExts != null)
            Collections.sort(jobContractSlateExts, sortOrderNumComparator);

        List jobContractManualTestExts = addJobContractProd.getJobContractManualTestExts();
        if (jobContractManualTestExts != null)
            Collections.sort(jobContractManualTestExts, sortOrderNumComparator);
    }
}
