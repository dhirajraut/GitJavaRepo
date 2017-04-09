package com.intertek.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.domain.JobContractVesselServiceExt;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionGLCode;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInspectionResult;
import com.intertek.entity.JobContractManualTest;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractProductService;
import com.intertek.entity.JobContractProductServiceResult;
import com.intertek.entity.JobContractServiceResult;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobContractVesselServiceResult;
import com.intertek.entity.JobOrder;
import com.intertek.entity.Prebill;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.CommonUtil;
import com.intertek.util.Constants;

/**
 * Pack the service related objects of the AddJobContract child tree.
 **/

public class RecheckServiceCommand implements Command {
    /**
     * Pack the service related objects of the AddJobContract child tree.
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

        if (obj instanceof JobContractServiceExt) {
            recheckServiceResults((JobContractServiceExt) obj);
        }
        else if (obj instanceof JobContractVesselServiceExt) {
            recheckServiceResults((JobContractVesselServiceExt) obj);
        }
        else if (obj instanceof JobContractProductServiceExt) {
            recheckServiceResults((JobContractProductServiceExt) obj);
        }
        else if (obj instanceof AddJobContract) {
            recheckServiceResults((AddJobContract) obj);
        }
        else if (obj instanceof AddJobContractVessel) {
            recheckServiceResults((AddJobContractVessel) obj);
        }
        else if (obj instanceof AddJobContractProd) {
            recheckServiceResults((AddJobContractProd) obj);
        }
    }

    private void recheckServiceResults(JobContractServiceExt serviceExt) {
        if (serviceExt != null) {
            List newResults = new ArrayList();
            List newSelectList = new ArrayList();

            List deletedResults = serviceExt.getDeletedResults();
            List addedResults = serviceExt.getAddedResults();
            List oldResults = serviceExt.getResults();

            for (int i = 0; i < oldResults.size(); i++) {
                JobContractServiceResult jResult = (JobContractServiceResult) oldResults.get(i);
                if (deletedResults.contains(jResult))
                    continue;

                newResults.add(jResult);
                newSelectList.add(serviceExt.getSelects()[i]);
            }

            for (int i = 0; i < addedResults.size(); i++) {
                newResults.add(addedResults.get(i));
                newSelectList.add(false);
            }

            serviceExt.getResults().clear();
            serviceExt.getResults().addAll(newResults);
            serviceExt.setSelects(CommonUtil.createBooleans(newSelectList));

            addedResults.clear();
            serviceExt.getService().getResults().clear();
            serviceExt.getService().getResults().addAll(newResults);
        }
    }

    private void recheckServiceResults(JobContractVesselServiceExt serviceExt) {
        if (serviceExt != null) {
            List newResults = new ArrayList();
            List newSelectList = new ArrayList();

            List deletedResults = serviceExt.getDeletedResults();
            List addedResults = serviceExt.getAddedResults();
            List oldResults = serviceExt.getResults();

            for (int i = 0; i < oldResults.size(); i++) {
                JobContractVesselServiceResult jResult = (JobContractVesselServiceResult) oldResults.get(i);
                if (deletedResults.contains(jResult))
                    continue;

                newResults.add(jResult);
                newSelectList.add(serviceExt.getSelects()[i]);
            }

            for (int i = 0; i < addedResults.size(); i++) {
                newResults.add(addedResults.get(i));
                newSelectList.add(false);
            }

            serviceExt.getResults().clear();
            serviceExt.getResults().addAll(newResults);
            serviceExt.setSelects(CommonUtil.createBooleans(newSelectList));

            addedResults.clear();
            serviceExt.getService().getResults().clear();
            serviceExt.getService().getResults().addAll(newResults);
        }
    }

    private void recheckServiceResults(JobContractProductServiceExt serviceExt) {
        if (serviceExt != null) {
            List newResults = new ArrayList();
            List newSelectList = new ArrayList();

            List deletedResults = serviceExt.getDeletedResults();
            List addedResults = serviceExt.getAddedResults();
            List oldResults = serviceExt.getResults();

            for (int i = 0; i < oldResults.size(); i++) {
                JobContractProductServiceResult jResult = (JobContractProductServiceResult) oldResults.get(i);
                if (deletedResults.contains(jResult))
                    continue;

                newResults.add(jResult);
                newSelectList.add(serviceExt.getSelects()[i]);
            }

            for (int i = 0; i < addedResults.size(); i++) {
                newResults.add(addedResults.get(i));
                newSelectList.add(false);
            }

            serviceExt.getResults().clear();
            serviceExt.getResults().addAll(newResults);
            serviceExt.setSelects(CommonUtil.createBooleans(newSelectList));

            addedResults.clear();
            serviceExt.getService().getResults().clear();
            serviceExt.getService().getResults().addAll(newResults);
        }
    }

    private void recheckServiceResults(AddJobContractProd addJobContractProd) {
        if (addJobContractProd != null) {
            List newResults = new ArrayList();
            List newSelectList = new ArrayList();

            List deletedResults = addJobContractProd.getDeletedResults();
            List addedResults = addJobContractProd.getAddedResults();
            List oldResults = addJobContractProd.getResults();

            for (int i = 0; i < oldResults.size(); i++) {
                JobContractProductInspectionResult jResult = (JobContractProductInspectionResult) oldResults.get(i);
                if (deletedResults.contains(jResult))
                    continue;

                newResults.add(jResult);
                newSelectList.add(addJobContractProd.getSelects()[i]);
            }

            for (int i = 0; i < addedResults.size(); i++) {
                newResults.add(addedResults.get(i));
                newSelectList.add(false);
            }

            addJobContractProd.getResults().clear();
            addJobContractProd.getResults().addAll(newResults);
            addJobContractProd.setSelects(CommonUtil.createBooleans(newSelectList));

            addedResults.clear();
            addJobContractProd.getJobContractProd().getResults().clear();
            addJobContractProd.getJobContractProd().getResults().addAll(newResults);

            Boolean notChargeInd = addJobContractProd.getJobContractProd().getNotChargeInd();
            if ((notChargeInd != null) && notChargeInd.booleanValue()) {
                addJobContractProd.getDeletedResults().addAll(addJobContractProd.getResults());
                addJobContractProd.getResults().clear();
                addJobContractProd.getJobContractProd().getResults().clear();
            }
        }
    }

    private void recheckServiceResults(AddJobContractVessel addJobContractVessel) {
        if (addJobContractVessel != null) {
            List newResults = new ArrayList();
            List newSelectList = new ArrayList();

            List deletedResults = addJobContractVessel.getDeletedResults();
            List addedResults = addJobContractVessel.getAddedResults();
            List oldResults = addJobContractVessel.getResults();

            for (int i = 0; i < oldResults.size(); i++) {
                JobContractVesselInspectionResult jResult = (JobContractVesselInspectionResult) oldResults.get(i);
                if (deletedResults.contains(jResult))
                    continue;

                newResults.add(jResult);
                newSelectList.add(addJobContractVessel.getSelects()[i]);
            }

            for (int i = 0; i < addedResults.size(); i++) {
                newResults.add(addedResults.get(i));
                newSelectList.add(false);
            }

            addJobContractVessel.getResults().clear();
            addJobContractVessel.getResults().addAll(newResults);
            addJobContractVessel.setSelects(CommonUtil.createBooleans(newSelectList));

            addedResults.clear();
            addJobContractVessel.getJobContractVessel().getResults().clear();
            addJobContractVessel.getJobContractVessel().getResults().addAll(newResults);
        }
    }

    private void recheckServiceResults(AddJobContract addJobContract) {
        if (addJobContract != null) {
            List newResults = new ArrayList();
            List newSelectList = new ArrayList();

            List deletedResults = addJobContract.getDeletedResults();
            List addedResults = addJobContract.getAddedResults();
            List oldResults = addJobContract.getResults();

            for (int i = 0; i < oldResults.size(); i++) {
                JobContractInspectionResult jResult = (JobContractInspectionResult) oldResults.get(i);
                if (deletedResults.contains(jResult))
                    continue;

                newResults.add(jResult);
                newSelectList.add(addJobContract.getSelects()[i]);
            }

            for (int i = 0; i < addedResults.size(); i++) {
                newResults.add(addedResults.get(i));
                newSelectList.add(false);
            }

            addJobContract.getResults().clear();
            addJobContract.getResults().addAll(newResults);
            addJobContract.setSelects(CommonUtil.createBooleans(newSelectList));

            addedResults.clear();
            addJobContract.getJobContract().getResults().clear();
            addJobContract.getJobContract().getResults().addAll(newResults);

            // iTreck 95017
            // after a user changes the product group for a JobContractProd, the
            // group name
            // of Prebill of all JobContractTest, JobContractManualTest,
            // JobContractSlate, etc.
            // need to be updated accordingly.
            // Note, this may not be the best place to add this change.
//            propagateGroupNameToPrebill(addJobContract);
        }
    }

    private void propagateGroupNameToPrebill(AddJobContract addJobContract) {
        JobContract jc = addJobContract.getJobContract();
        if (jc == null) {
            return;
        }
        Set<JobContractVessel> vessels = (Set<JobContractVessel>) jc.getJobContractVessels();
        if (vessels == null || vessels.size() == 0) {
            return;
        }
        for (JobContractVessel v : vessels) {
            Set<JobContractProd> prods = (Set<JobContractProd>) v.getJobContractProds();
            if (prods == null || prods.size() == 0) {
                return;
            }
            for (JobContractProd prod : prods) {
                // for all manual tests
                Set<JobContractManualTest> mts = (Set<JobContractManualTest>) prod.getJobContractManualTests();
                if (mts != null) {
                    for (JobContractManualTest mt : mts) {
                        Set<Prebill> prebills = (Set<Prebill>) mt.getPrebills();
                        updatePrebillGroupName(prebills, prod, Constants.TEST);
                    }
                }
                // for all tests
                Set<JobContractTest> ts = (Set<JobContractTest>) prod.getJobContractTests();
                if (ts != null) {
                    for (JobContractTest t : ts) {
                        Set<Prebill> prebills = (Set<Prebill>) t.getPrebills();
                        updatePrebillGroupName(prebills, prod, Constants.TEST);
                    }
                }
                // for all slates
                Set<JobContractSlate> slates = (Set<JobContractSlate>) prod.getJobContractSlates();
                if (slates != null) {
                    for (JobContractSlate slate : slates) {
                        Set<Prebill> prebills = (Set<Prebill>) slate.getPrebills();
                        updatePrebillGroupName(prebills, prod, Constants.SLATE);
                    }
                }
                // for all other product services
                Set<JobContractProductService> svcs = (Set<JobContractProductService>) prod.getJobContractProductServices();
                if (svcs != null) {
                    for (JobContractProductService svc : svcs) {
                        Set<JobContractProductServiceResult> results = (Set<JobContractProductServiceResult>) svc.getResults();
                        if (results == null) {
                            return;
                        }
                        for (JobContractProductServiceResult result : results) {
                            Set<Prebill> prebills = (Set<Prebill>) result.getPrebills();
                            updatePrebillGroupName(prebills, prod, result.getExpressionId());
                        }
                    }
                }
            }
        }
    }

    private String findProductGroupName(JobContractProd prod, String type) {
        CalculatorService calculatorService = (CalculatorService) ServiceLocator.getInstance().getBean("calculatorService");
        Expression expression = calculatorService.getExpressionByExpressionId(type);
        JobContract jobContract = prod.getJobContractVessel().getJobContract();
        JobOrder jobOrder = jobContract.getJobOrder();
        String jobType = jobOrder.getJobType();
        String jobCode = jobContract.getProductType();
        String masterGroupId = prod.getProductGroupMaster();

        ExpressionGLCode eGLCode = calculatorService.getExpressionGLCode(expression.getExpressionId().getExpressionId(), jobType);

        String productGroup = calculatorService.getProductGroup(null, eGLCode, jobCode, masterGroupId);

        return productGroup;
    }

    private void updatePrebillGroupName(Set<Prebill> prebills, JobContractProd prod, String type) {
        String productGroupName = findProductGroupName(prod, type);
        if (prebills == null) {
            return;
        }
        for (Prebill prebill : prebills) {
            prebill.setProductGroup(productGroupName);
        }
    }
}
