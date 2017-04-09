package com.intertek.command;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.JobContractManualTestExt;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.domain.JobContractSlateExt;
import com.intertek.domain.JobContractTestExt;
import com.intertek.domain.JobContractVesselServiceExt;
import com.intertek.entity.JobContractInspectionResult;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractProductServiceResult;
import com.intertek.entity.JobContractServiceResult;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobContractVesselServiceResult;
import com.intertek.entity.Prebill;

/**
 * A command implementation used to calculate the total price of the prebills of
 * the child object of AddJobContract
 * 
 */

public class CalculateTotalCommand implements Command {
    /**
     * calculate the total price of the prebills of the input object.
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

        double total = addJobContract.getInvoiceTotalAmt();

        if (obj instanceof AddJobContract) {
            total = calculateTotal((AddJobContract) obj);
        }
        else if (obj instanceof JobContractServiceExt) {
            total += calculateTotal((JobContractServiceExt) obj);
        }
        else if (obj instanceof JobContractVesselServiceExt) {
            total += calculateTotal((JobContractVesselServiceExt) obj);
        }
        else if (obj instanceof JobContractProductServiceExt) {
            total += calculateTotal((JobContractProductServiceExt) obj);
        }
        else if (obj instanceof AddJobContractProd) {
            total += calculateTotal((AddJobContractProd) obj);
        }
        else if (obj instanceof AddJobContractVessel) {
            total += calculateTotal((AddJobContractVessel) obj);
        }
        else if (obj instanceof AddJobContract) {
            total += calculateTotal((AddJobContract) obj);
        }

        addJobContract.setInvoiceTotalAmt(total);
    }

    private double calculateTotal(AddJobContract addJobContract) {
        double total = 0.0;

        if (addJobContract == null)
            return total;

        List results = addJobContract.getResults();

        if (results == null)
            return total;

        for (int i = 0; i < results.size(); i++) {
            JobContractInspectionResult jResult = (JobContractInspectionResult) results.get(i);

            total += doCalculateTotal(jResult.getPrebills());
        }

        return total;
    }

    private double calculateTotal(JobContractServiceExt serviceExt) {
        double total = 0.0;

        if (serviceExt == null)
            return total;

        List results = serviceExt.getResults();
        if (results == null)
            return total;

        for (int i = 0; i < results.size(); i++) {
            JobContractServiceResult jResult = (JobContractServiceResult) results.get(i);

            total += doCalculateTotal(jResult.getPrebills());
        }

        return total;
    }

    private double calculateTotal(JobContractVesselServiceExt serviceExt) {
        double total = 0.0;

        if (serviceExt == null)
            return total;

        List results = serviceExt.getResults();

        if (results == null)
            return total;

        for (int i = 0; i < results.size(); i++) {
            JobContractVesselServiceResult jResult = (JobContractVesselServiceResult) results.get(i);

            total += doCalculateTotal(jResult.getPrebills());
        }

        return total;
    }

    private double calculateTotal(JobContractProductServiceExt serviceExt) {
        double total = 0.0;

        if (serviceExt == null)
            return total;

        List results = serviceExt.getResults();

        if (results == null)
            return total;

        for (int i = 0; i < results.size(); i++) {
            JobContractProductServiceResult jResult = (JobContractProductServiceResult) results.get(i);

            total += doCalculateTotal(jResult.getPrebills());
        }

        return total;
    }

    private double calculateTotal(AddJobContractProd addJobContractProd) {
        double total = 0.0;

        if (addJobContractProd == null)
            return total;

        total += calculateTotalForTest(addJobContractProd.getJobContractTestExts());
        total += calculateTotalForSlate(addJobContractProd.getJobContractSlateExts());
        total += calculateTotalForManualTest(addJobContractProd.getJobContractManualTestExts());

        List results = addJobContractProd.getResults();

        if (results == null)
            return total;

        for (int i = 0; i < results.size(); i++) {
            JobContractProductInspectionResult jResult = (JobContractProductInspectionResult) results.get(i);

            total += doCalculateTotal(jResult.getPrebills());
        }

        return total;
    }

    private double calculateTotal(AddJobContractVessel addJobContractVessel) {
        double total = 0.0;

        if (addJobContractVessel == null)
            return total;

        List results = addJobContractVessel.getResults();

        if (results == null)
            return total;

        for (int i = 0; i < results.size(); i++) {
            JobContractVesselInspectionResult jResult = (JobContractVesselInspectionResult) results.get(i);

            total += doCalculateTotal(jResult.getPrebills());
        }

        return total;
    }

    private double calculateTotalForTest(List testExts) {
        double total = 0.0;

        if (testExts == null)
            return total;

        for (int i = 0; i < testExts.size(); i++) {
            JobContractTestExt testExt = (JobContractTestExt) testExts.get(i);

            total += doCalculateTotal(testExt.getTest().getPrebills());
        }

        return total;
    }

    private double calculateTotalForSlate(List slateExts) {
        double total = 0.0;

        if (slateExts == null)
            return total;

        for (int i = 0; i < slateExts.size(); i++) {
            JobContractSlateExt slateExt = (JobContractSlateExt) slateExts.get(i);

            total += doCalculateTotal(slateExt.getSlate().getPrebills());
        }

        return total;
    }

    private double calculateTotalForManualTest(List manualTestExts) {
        double total = 0.0;

        if (manualTestExts == null)
            return total;

        for (int i = 0; i < manualTestExts.size(); i++) {
            JobContractManualTestExt manualTestExt = (JobContractManualTestExt) manualTestExts.get(i);

            total += doCalculateTotal(manualTestExt.getManualTest().getPrebills());
        }

        return total;
    }

    private double doCalculateTotal(Set prebills) {
        double total = 0.0;
        if (prebills == null)
            return total;

        Iterator it = prebills.iterator();
        while (it.hasNext()) {
            Prebill prebill = (Prebill) it.next();

            Double netPriceObj = prebill.getNetPrice();
            if (netPriceObj != null)
                total += netPriceObj.doubleValue();
        }

        return total;
    }
}
