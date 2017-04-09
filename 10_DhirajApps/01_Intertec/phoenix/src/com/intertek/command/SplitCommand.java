package com.intertek.command;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
import com.intertek.locator.ServiceLocator;
import com.intertek.service.PrebillService;
import com.intertek.util.NumberUtil;

/**
 * Do the splitting for the prebills of the AddJobContract child tree.
 * 
 **/

public class SplitCommand implements Command {
    private static Log log = LogFactory.getLog(SplitCommand.class);

    private boolean useGlobalSplitPct;

    /**
     * .ctr
     * 
     * Create a SplitCommand object with the useGlobalSplitPct flag.
     * 
     * @param useGlobalSplitPct
     *            - set to true if use the global split percentage value.
     * 
     **/
    public SplitCommand(boolean useGlobalSplitPct) {
        this.useGlobalSplitPct = useGlobalSplitPct;
    }

    /**
     * Do the splitting for the prebills of the AddJobContract child tree.
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
        Double splitPct = addJobContract.getSplitPct();

        if (obj instanceof JobContractServiceExt) {
            split((JobContractServiceExt) obj, splitPct);
        }
        else if (obj instanceof JobContractVesselServiceExt) {
            split((JobContractVesselServiceExt) obj, splitPct);
        }
        else if (obj instanceof JobContractProductServiceExt) {
            split((JobContractProductServiceExt) obj, splitPct);
        }
        else if (obj instanceof AddJobContractProd) {
            split((AddJobContractProd) obj, splitPct);
        }
        else if (obj instanceof AddJobContractVessel) {
            split((AddJobContractVessel) obj, splitPct);
        }
        else if (obj instanceof AddJobContract) {
            split((AddJobContract) obj, splitPct);
        }
    }

    private void split(JobContractServiceExt serviceExt, Double splitPct) {
        if (serviceExt == null)
            return;

        boolean[] selects = serviceExt.getSelects();
        List results = serviceExt.getResults();

        if ((selects == null) || (results == null))
            return;

        if (selects.length != results.size())
            return;

        for (int i = 0; i < results.size(); i++) {
            JobContractServiceResult jResult = (JobContractServiceResult) results.get(i);
            Double mySplitPct = null;

            if (selects[i]) {
                if (splitPct != null) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(jResult.getPrebills(), mySplitPct);
        }
    }

    private void split(JobContractVesselServiceExt serviceExt, Double splitPct) {
        if (serviceExt == null)
            return;

        boolean[] selects = serviceExt.getSelects();
        List results = serviceExt.getResults();

        if ((selects == null) || (results == null))
            return;

        if (selects.length != results.size())
            return;

        for (int i = 0; i < results.size(); i++) {
            JobContractVesselServiceResult jResult = (JobContractVesselServiceResult) results.get(i);
            Double mySplitPct = null;

            if (selects[i]) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(jResult.getPrebills(), mySplitPct);
        }
    }

    private void split(JobContractProductServiceExt serviceExt, Double splitPct) {
        if (serviceExt == null)
            return;

        boolean[] selects = serviceExt.getSelects();
        List results = serviceExt.getResults();

        if ((selects == null) || (results == null))
            return;

        if (selects.length != results.size())
            return;

        for (int i = 0; i < results.size(); i++) {
            JobContractProductServiceResult jResult = (JobContractProductServiceResult) results.get(i);
            Double mySplitPct = null;

            if (selects[i]) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(jResult.getPrebills(), mySplitPct);
        }
    }

    private void split(AddJobContractProd addJobContractProd, Double splitPct) {
        if (addJobContractProd == null)
            return;

        splitTest(addJobContractProd.getJobContractTestExts(), splitPct);
        splitSlate(addJobContractProd.getJobContractSlateExts(), splitPct);
        splitManualTest(addJobContractProd.getJobContractManualTestExts(), splitPct);

        boolean[] selects = addJobContractProd.getSelects();
        List results = addJobContractProd.getResults();

        if ((selects == null) || (results == null))
            return;

        if (selects.length != results.size())
            return;

        for (int i = 0; i < results.size(); i++) {
            JobContractProductInspectionResult jResult = (JobContractProductInspectionResult) results.get(i);
            Double mySplitPct = null;

            if (selects[i]) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(jResult.getPrebills(), mySplitPct);
        }
    }

    private void split(AddJobContractVessel addJobContractVessel, Double splitPct) {
        if (addJobContractVessel == null)
            return;

        boolean[] selects = addJobContractVessel.getSelects();
        List results = addJobContractVessel.getResults();

        if ((selects == null) || (results == null))
            return;

        if (selects.length != results.size())
            return;

        for (int i = 0; i < results.size(); i++) {
            JobContractVesselInspectionResult jResult = (JobContractVesselInspectionResult) results.get(i);
            Double mySplitPct = null;

            if (selects[i]) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(jResult.getPrebills(), mySplitPct);
        }
    }

    private void split(AddJobContract addJobContract, Double splitPct) {
        if (addJobContract == null)
            return;

        boolean[] selects = addJobContract.getSelects();
        List results = addJobContract.getResults();

        if ((selects == null) || (results == null))
            return;

        if (selects.length != results.size())
            return;

        for (int i = 0; i < results.size(); i++) {
            JobContractInspectionResult jResult = (JobContractInspectionResult) results.get(i);
            Double mySplitPct = null;

            if (selects[i]) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(jResult.getPrebills(), mySplitPct);
        }
    }

    private void splitTest(List testExts, Double splitPct) {
        if (testExts == null)
            return;

        for (int i = 0; i < testExts.size(); i++) {
            Double mySplitPct = null;
            JobContractTestExt testExt = (JobContractTestExt) testExts.get(i);

            if (testExt.getSelected()) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(testExt.getTest().getPrebills(), mySplitPct);
        }
    }

    private void splitSlate(List slateExts, Double splitPct) {
        if (slateExts == null)
            return;

        for (int i = 0; i < slateExts.size(); i++) {
            Double mySplitPct = null;
            JobContractSlateExt slateExt = (JobContractSlateExt) slateExts.get(i);

            if (slateExt.getSelected()) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(slateExt.getSlate().getPrebills(), mySplitPct);
        }
    }

    private void splitManualTest(List manualTestExts, Double splitPct) {
        if (manualTestExts == null)
            return;

        for (int i = 0; i < manualTestExts.size(); i++) {
            Double mySplitPct = null;
            JobContractManualTestExt manualTestExt = (JobContractManualTestExt) manualTestExts.get(i);

            if (manualTestExt.getSelected()) {
                if ((splitPct != null) && (splitPct.doubleValue() >= 0)) {
                    mySplitPct = splitPct;
                }
            }
            doSplit(manualTestExt.getManualTest().getPrebills(), mySplitPct);
        }
    }

    private void doSplit(Set prebills, Double splitPct) {
        if (prebills == null)
            return;

        Iterator it = prebills.iterator();
        while (it.hasNext()) {
            Prebill prebill = (Prebill) it.next();

            double mySplitPct = prebill.getSplitPct();
            if ((splitPct != null) && useGlobalSplitPct) {
                mySplitPct = splitPct.doubleValue();
            }

            if (mySplitPct >= 0.0) {
                prebill.setSplitPct(mySplitPct);
                double discountPct = prebill.getDiscountPct();
                double unitPrice = prebill.getUnitPrice();

                double netPrice = (100 - discountPct) * mySplitPct * unitPrice / 10000.0;
                PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
                Integer roundingDigits = prebillService.getDecimalDigitsByCurrency(prebill.getJobContract().getTransactionCurrencyCd());
                if (roundingDigits != null) {
                    netPrice = NumberUtil.roundHalfUp(netPrice, roundingDigits);
                    if (prebill.getRateMult() != null && prebill.getRateDiv() != null)
                        prebill.setBaseNetPrice(NumberUtil.roundHalfUp(netPrice * prebill.getRateMult() / prebill.getRateDiv(), roundingDigits));

                }
                else {
                    netPrice = NumberUtil.roundHalfUp(netPrice, 2);
                    if (prebill.getRateMult() != null && prebill.getRateDiv() != null)
                        prebill.setBaseNetPrice(NumberUtil.roundHalfUp(netPrice * prebill.getRateMult() / prebill.getRateDiv(), 2));

                }
                prebill.setNetPrice(netPrice);
            }
        }
    }
}
