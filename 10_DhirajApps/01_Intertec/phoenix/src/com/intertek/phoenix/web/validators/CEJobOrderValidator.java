package com.intertek.phoenix.web.validators;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.entity.Bank;
import com.intertek.entity.Branch;
import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.web.controller.job.CEJobContractForm;
import com.intertek.phoenix.web.controller.job.CEJobOrderForm;

public class CEJobOrderValidator implements Validator {
    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#supports(java.lang.Class)
     */
    public boolean supports(Class clazz) {
        return CEJobOrderForm.class.isAssignableFrom(clazz);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.validation.Validator#validate(java.lang.Object,
     *      org.springframework.validation.Errors)
     */
    public void validate(Object obj, Errors errors) {
        CEJobOrderForm ceJobOrderForm = (CEJobOrderForm) obj;
        if (ceJobOrderForm == null)
            return;

        JobOrderService jobSrvc = ServiceManager.getJobOrderService();
        SearchService searchSrvc = ServiceManager.getSearchService();

        if (CommonUtil.isNullOrEmpty(ceJobOrderForm.getOperation())){
            errors.reject("invalid.operation");
        }
        if (CommonUtil.isNullOrEmpty(ceJobOrderForm.getServiceLocationCode())){
            errors.reject("invalid.service.location");
        }
        
        // validating sales rep & roles
        if (CommonUtil.isNullOrEmpty(ceJobOrderForm.getSalesPersonName())) {
            errors.reject("invalid.salesRep");
        }
        else{
            SearchCriteria searchcritria = new SearchCriteria("loginName", ceJobOrderForm.getSalesPersonName());
            List<SearchCriteria> listSeach = new ArrayList<SearchCriteria>();

            listSeach.add(searchcritria);
            List<User> usrList;
            try {
                usrList = searchSrvc.advancedSearch(User.class, listSeach);
                if (usrList == null || usrList.isEmpty()) {
                    errors.reject("invalid.salesRep");
                }
                else {
                    for (User user : usrList) {
                        Set<Role> roles = user.getRoles();
                        if (!user.getLoginName().equals(ceJobOrderForm.getSalesPersonName())) {
                            errors.reject("invalid.salesRep");
                        }
                        if (roles == null || roles.isEmpty()) {
                            for (Role role : roles) {
                                if (!role.getName().equals("Sales Rep")) {
                                    errors.reject("invalid.perm.salesRep");
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception e) {
                errors.reject("invalid.salesRep");
            }
        }

        // validating operating unit
        if (ceJobOrderForm.getWarehouseName() != null && !ceJobOrderForm.getWarehouseName().equals("")) {
            try {
                Branch branch = jobSrvc.findById(Branch.class, ceJobOrderForm.getWarehouseName());
                if (branch == null || branch.getBuName() == null || !branch.getBuName().equals(ceJobOrderForm.getBuName())) {
                    errors.reject("invalid.warehouse");
                }

            }
            catch (Exception e) {
                errors.reject("invalid.warehouse");
            }
        }

        // validating secondary sales rep & roles
        if (ceJobOrderForm.getSecondarySalesPersonName() != null && !ceJobOrderForm.getSecondarySalesPersonName().equals("")) {

            SearchCriteria searchcritria = new SearchCriteria("loginName", ceJobOrderForm.getSecondarySalesPersonName());
            List<SearchCriteria> listSeach = new ArrayList<SearchCriteria>();

            listSeach.add(searchcritria);

            List<User> usrList;
            try {
                usrList = searchSrvc.advancedSearch(User.class, listSeach);
                if (usrList == null || usrList.isEmpty()) {
                    errors.reject("invalid.sec.salesRep");
                }
                else {
                    for (User user : usrList) {
                        Set<Role> roles = user.getRoles();
                        if (!user.getLoginName().equals(ceJobOrderForm.getSecondarySalesPersonName())) {
                            errors.reject("invalid.sec.salesRep");
                            if (roles == null || roles.isEmpty()) {
                                for (Role role : roles) {
                                    if (!role.getName().equals("Sales Rep")) {
                                        errors.reject("invalid.perm.sec.salesRep");
                                    }
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception e) {
                errors.reject("invalid.sec.salesRep");
            }

        }
        // validating PromiseComplitiondate
        if (ceJobOrderForm.getPromiseCompDate() != null && !ceJobOrderForm.getPromiseCompDate().equals("")) {
            if (!CommonUtil.isValidDate(ceJobOrderForm.getPromiseCompDate())) {
                errors.reject("Promise.Date.Invalid");
            }
        }
        // validating customerreadydate
        if (ceJobOrderForm.getCustReadyDate() != null && !ceJobOrderForm.getCustReadyDate().equals("")) {
            if (!CommonUtil.isValidDate(ceJobOrderForm.getCustReadyDate())) {
                ceJobOrderForm.setCustomerReadyDate(ceJobOrderForm.getCustReadyDate());
                errors.reject("Cust.Ready.Date.Invalid");
            }
        }

        if (ceJobOrderForm.getActReadyDt() != null && !ceJobOrderForm.getActReadyDt().equals("")) {
            if (!CommonUtil.isValidDate(ceJobOrderForm.getActReadyDt())) {
                errors.reject("Actual.Ready.Date.Invalid");
            }
        }

        if (ceJobOrderForm.getQtDate() != null && !ceJobOrderForm.getQtDate().equals("")) {
            if (!CommonUtil.isValidDate(ceJobOrderForm.getQtDate())) {
                errors.reject("quote.date.invalid");
            }
        }

        if (ceJobOrderForm.getProjectManagerName() != null && !ceJobOrderForm.getProjectManagerName().equals("")) {

            SearchCriteria searchcritria = new SearchCriteria("employeeId", ceJobOrderForm.getProjectManagerName());
            List<SearchCriteria> listSeach = new ArrayList<SearchCriteria>();

            listSeach.add(searchcritria);
            List<Employee> empList;
            try {
                empList = searchSrvc.advancedSearch(Employee.class, listSeach);
                if (empList == null || empList.isEmpty()) {
                    errors.reject("invalid.project.manager");
                }
                else {
                    for (Employee emp: empList) {
                        if (!emp.getEmployeeId().equals(ceJobOrderForm.getProjectManagerName())) {
                            errors.reject("invalid.project.manager");
                        }
                    }
                }
            }

            catch (Exception e) {
                errors.reject("invalid.project.manager");
            }
        }

        // validating add customer

        // validation for customer tab if trying to submit customer tab form
        // with entry form having errors.
        if (errors != null && errors.getFieldError() != null && ceJobOrderForm.getTabName().equals("1")) {
            String field = errors.getFieldError().getField();
            if (field != null
                && (field.equals("salesPersonName") || field.equals("warehouseName") || field.equals("operation") || field.equals("buName") || field
                        .equals("serviceLocationCode"))) {
                errors.reject("invalid.fillEntryForm");

            }
        }
        CEJobContractForm[] ceJobContracts = ceJobOrderForm.getJobContracts();

        if (ceJobContracts == null)
            return;
        for (CEJobContractForm ceJobContractForm : ceJobContracts) {
            if (ceJobContractForm != null) {
                if (ceJobContractForm.getJobContract().getCustRefNum() == null || ceJobContractForm.getJobContract().getCustRefNum().equals("")) {
                    errors.reject("custRefNum.null");
                }
                else if (ceJobContractForm.getJobContract().getCustRefNum() != null && !ceJobContractForm.getJobContract().getCustRefNum().equals("")) {
                    boolean custRefNumValid = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getCustRefNum());

                    if (!custRefNumValid)
                        errors.reject("custRefNum.invalid");
                }

                if (ceJobContractForm.getSourceOrigin() == null || ceJobContractForm.getSourceOrigin().equals("")) {
                    errors.reject("Source.Origin.null");
                }

                if (ceJobContractForm.getJobContract().getInvoiceType() == null || ceJobContractForm.getJobContract().getInvoiceType().equals("")) {
                    errors.reject("invoiceType.null");
                }
                else if (ceJobContractForm.getJobContract().getInvoiceType() != null && !ceJobContractForm.getJobContract().getInvoiceType().equals("")) {
                    if (!CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceType())) {
                        errors.reject("invoice.type.invalid");
                    }
                }

                if (ceJobContractForm.getJobContract().getInvoiceLanguage() == null || ceJobContractForm.getJobContract().getInvoiceLanguage().equals("")) {
                    errors.reject("invoiceLanguage.null");
                }
                else if (ceJobContractForm.getJobContract().getInvoiceLanguage() != null && !ceJobContractForm.getJobContract().getInvoiceLanguage().equals("")) {
                    boolean validInvoiceLang = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceLanguage());
                    if (!validInvoiceLang)
                        errors.reject("invoice.lang.invalid");
                }

                if (ceJobContractForm.getJobContract().getInvoiceValue1() != null && !ceJobContractForm.getJobContract().getInvoiceValue1().equals("")) {
                    boolean validRefVal = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceValue1());
                    if (!validRefVal)
                        errors.reject("invalid.Ref.Field1");
                }

                if (ceJobContractForm.getJobContract().getInvoiceValue2() != null && !ceJobContractForm.getJobContract().getInvoiceValue2().equals("")) {
                    boolean validRefVal = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceValue2());
                    if (!validRefVal)
                        errors.reject("invalid.Ref.Field2");
                }
                if (ceJobContractForm.getJobContract().getInvoiceValue3() != null && !ceJobContractForm.getJobContract().getInvoiceValue3().equals("")) {
                    boolean validRefVal = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceValue3());
                    if (!validRefVal)
                        errors.reject("invalid.Ref.Field3");
                }
                if (ceJobContractForm.getJobContract().getInvoiceValue4() != null && !ceJobContractForm.getJobContract().getInvoiceValue4().equals("")) {
                    boolean validRefVal = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceValue4());
                    if (!validRefVal)
                        errors.reject("invalid.Ref.Field4");
                }
                if (ceJobContractForm.getJobContract().getInvoiceValue5() != null && !ceJobContractForm.getJobContract().getInvoiceValue5().equals("")) {
                    boolean validRefVal = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceValue5());
                    if (!validRefVal)
                        errors.reject("invalid.Ref.Field5");
                }

                if (ceJobContractForm.getJobContract().getParentJobNumber() != null && !ceJobContractForm.getJobContract().getParentJobNumber().equals("")) {
                    boolean validJob = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getParentJobNumber());
                    if (!validJob)
                        errors.reject("parent.job.invalid");

                    else {

                        Map<String, String> criteria = new HashMap<String, String>();
                        criteria.put("buName", ceJobOrderForm.getBuName());
                        criteria.put("jobNumber", ceJobContractForm.getJobContract().getParentJobNumber());
                        try {
                            CEJobOrder order = searchSrvc.uniqueSearch(CEJobOrder.class, criteria);
                            if (order == null || order.getJobNumber() == null)
                                errors.reject("parent.job.invalid");

                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            errors.reject("parent.job.invalid");
                        }
                    }
                }

                if (ceJobContractForm.getJobContract().getTransactionCurrency() == null
                    || ceJobContractForm.getJobContract().getTransactionCurrency().equals(""))
                    errors.reject("transCurrency.null");

                if (ceJobContractForm.getJobContract() != null && !ceJobContractForm.getJobContract().getTransactionCurrency().equals("")) {
                    boolean transCurrValid = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getTransactionCurrency());
                    if (!transCurrValid)
                        errors.reject("invalid.trans.curr");
                }

                if (ceJobContractForm.getJobContract().getProductType() == null || ceJobContractForm.getJobContract().getProductType().equals("")) {
                    errors.reject("proType.null");
                }

                if (ceJobContractForm.getJobContract() != null && !ceJobContractForm.getJobContract().getProductType().equals("")) {
                    boolean prodTypeValid = CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getProductType());
                    if (!prodTypeValid)
                        errors.reject("invalid.prod.type");
                }

                if (ceJobContractForm.getBillingContactId() == null || ceJobContractForm.getBillingContactId().equals("")) {
                    errors.reject("billingContactId.null");
                }

                else if (!CommonUtil.validateAlphaNum(ceJobContractForm.getBillingContactId())) {
                    errors.reject("invalid.billing.contact");
                }

                if (ceJobContractForm.getJobContract().getReportReceivingContactId() == null
                    || ceJobContractForm.getJobContract().getReportReceivingContactId().equals("")) {

                    errors.reject("report.recieving.contact");
                }

                if (ceJobContractForm.getRemitToCode() == null || ceJobContractForm.getRemitToCode().equals("")) {

                    errors.reject("remitTo.null");
                }

                if (ceJobContractForm.getRemitToCode() != null && !ceJobContractForm.getRemitToCode().equals("")) {
                    if (!CommonUtil.validateAlphaNum(ceJobContractForm.getRemitToCode())) {
                        errors.reject("invalid.remitTo.code");
                    }
                    else {
                        try {
                            Bank bankCode = jobSrvc.findById(Bank.class, ceJobContractForm.getRemitToCode());
                            if (bankCode == null || bankCode.getBankDesc() == null)
                                errors.reject("invalid.remitTo.code");
                        }
                        catch (Exception e) {
                            errors.reject("invalid.remitTo.code");
                        }
                    }
                }

                // TO DO:remittobankacc num is not saving in JOBContract table
                // if
                // (ceJobContractForm.getJobContract().getRemitToBankAccountNum()
                // == null
                // ||ceJobContractForm.getJobContract().getRemitToBankAccountNum().equals(""))
                // {
                // errors.reject("remitToAcc.null");
                // }
                // TO DO:
                // if (ceJobContractForm.getJobContract() != null &&
                // !ceJobContractForm.getJobContract().getRemitToBankAccountNum().equals(""))
                // {
                // if
                // (!CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getRemitToBankAccountNum()))
                // {
                // errors.reject("invalid.Remit.acc.number");
                // }
                //
                // }

            }// end of for loop
            // TODO : after confirm from business
            // if (ceJobContractForm.getJobContract().getInvoiceDescription() !=
            // null &&
            // !ceJobContractForm.getJobContract().getInvoiceDescription().equals(""))
            // {
            // boolean validInvDesc =
            // CommonUtil.validateAlphaNum(ceJobContractForm.getJobContract().getInvoiceDescription());
            //                if (!validInvDesc)
            //                    errors.reject("invalid.Invoice.Description");
            //            }
        }

    }
}
