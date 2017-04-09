package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.JobSearch;
import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.ContactCust;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobVessel;
import com.intertek.entity.Operation;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;
import com.intertek.entity.State;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TaxCode;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.JobContractResultService;
import com.intertek.service.JobService;
import com.intertek.service.JobTypeService;
import com.intertek.service.PrebillService;
import com.intertek.service.TaxService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.InvoiceUtil;
import com.intertek.util.JobUtil;
import com.intertek.util.NumberUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.TaxUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.controller.user.BranchController;

public class EditJobInvoicePreviewFormController extends BaseSimpleFormController {
    private static Log log = LogFactory.getLog(EditJobInvoicePreviewFormController.class);

    public EditJobInvoicePreviewFormController() {
        super();
        setCommandClass(AddJobOrder.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        AddJobOrder addJobOrder = (AddJobOrder) command;
        PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
        
        String refreshing = request.getParameter("refreshing");
        String chosenPrebillIds = request.getParameter("chosenPrebillIds");
        String contractCode = request.getParameter("contractCd");
        String contractIndex = request.getParameter("contractIndex");

        String tabName = request.getParameter("tabName");
        String tabSource = request.getParameter("tabSource");
        System.out.println("chosenPrebillIds: " + chosenPrebillIds);
        log.info("tabname,tabSource,contractIndex :" + tabName + " , " + tabSource + " , " + contractIndex);

        AddJobContract addJobContractObj = JobUtil.getAddJobContractByContractIndex(addJobOrder, contractIndex);
        if (addJobContractObj != null)
            addJobContractObj.setInvoicePreviewTabSrc(tabSource);
        addJobOrder.setContractIndex(Integer.parseInt(contractIndex));

        if (addJobOrder.getAddJobContracts() != null && addJobOrder.getAddJobContracts().length > 0) {
            int contractIndx = Integer.parseInt(contractIndex);
            for (int i = 0; i < addJobOrder.getAddJobContracts().length; i++) {
                if (i == contractIndx)
                    continue;
                addJobOrder.getAddJobContracts()[i].setInvoicePreviewTabSrc(Constants.INV_PREVIEW_TAB1_SRC);
            }
        }

     // START : #119240
        String noPrev = request.getParameter("noPrevJob");
		if(noPrev !=null && noPrev.equals("true")){
			request.setAttribute("noJobMessage", "No Previous Job!");
			return showForm(request, response, errors);
		}
		String noNext = request.getParameter("noNextJob");
		if(noNext !=null && noNext.equals("true")){
			request.setAttribute("noJobMessage", "No Next Job!");
			return showForm(request, response, errors);
		}
        // String nextList=request.getParameter("nextListFlag");
        String nextList=request.getParameter("showNextListFlag");
        String jobNumber = "";
        JobOrder jobOrd = addJobOrder.getJobOrder();
    	if(nextList != null && nextList.trim().equals("next"))
    	{
    		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
    		jobNumber=jobOrd.getJobNumber();
    		
    		try
    		{
    			if(request.getSession() != null)
    			{
    				JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
    				jobOrd=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,nextList);
    			}
    		}		
    		catch(ServiceException e)
    		{
    			e.printStackTrace();
    			errors.reject(e.getMessage(), e.getParams(), null);
    			return showForm(request, response, errors);
    		}
    		catch(Throwable t)
    		{
    			t.printStackTrace();
    			errors.reject("generic.error", new Object[] {t.getMessage()}, null);
    			return showForm(request, response, errors);
    		}
    		
    		//String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
    		String url = jobService.getUrlByCurrPageIdentifier(jobOrd, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
    		
    		return new ModelAndView(new RedirectView(url+jobOrd.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

    	}
    	
    	//String prevList=request.getParameter("prevListFlag");
		String prevList=request.getParameter("showPrevListFlag");
		if(prevList != null && prevList.trim().equals("prev"))
    	{
    		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
    		jobNumber=jobOrd.getJobNumber();
    		
    		try
    		{
    			if(request.getSession() != null)
    			{
    				JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
    				jobOrd=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,prevList);
    			}
    		}
    		catch(ServiceException e)
    		{
    			e.printStackTrace();
    			errors.reject(e.getMessage(), e.getParams(), null);
    			return showForm(request, response, errors);
    		}
    		catch(Throwable t)
    		{
    			t.printStackTrace();
    			errors.reject("generic.error", new Object[] {t.getMessage()}, null);
    			return showForm(request, response, errors);
    		}
    		
    		//String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
    		String url = jobService.getUrlByCurrPageIdentifier(jobOrd, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
    		return new ModelAndView(new RedirectView(url+jobOrd.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

    	}    
        // END : #119240
        
        
        String warnUserFlag = addJobOrder.getWarnUserFlag();

        if ((warnUserFlag != null) && Constants.WARN.equals(warnUserFlag)) {
            addJobOrder.setWarnUserFlag(Constants.WARNED);
            errors.reject("unsaved.changes.save.error", new Object[] {}, null);
            return showForm(request, response, errors);
        }

        if ((warnUserFlag != null) && Constants.NAVIGATE.equals(warnUserFlag)) {
            String navigationUrl = addJobOrder.getNavigationUrl();
            if (navigationUrl != null) {
                return new ModelAndView(new RedirectView(navigationUrl));
            }
            else{
                return showForm(request, response, errors);
            }
        }

        if ((refreshing != null) && Constants.TRUE_VALUE.equals(refreshing)) {
            //START : 53791
        	if(null != addJobOrder.getSignIdntfr() &&
        			null != addJobOrder.getSignIndex() &&
        				addJobOrder.getSignIdntfr().trim().length() > 0 &&
        					addJobOrder.getSignIndex().length() > 0){
        		rearrangePrebillValues(addJobOrder,prebillService);
        	}
        	addJobOrder.setSignIdntfr("");
        	addJobOrder.setSignIndex("");
            //END : 53791
        	
        	// START : #119240
        	//   addJobOrder.setCurrPageIdentifier(Constants.JOB_PREVIEW_INVOICE);
            // Setting next list and prev list values
    		if(request.getSession() != null)
    		{
    			JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
    			
    			String orginFromJobSearch =  (String)request.getSession().getAttribute("orginFromJobSearch");
    			
    			JobUtil.setPrevNextJobFlags(addJobOrder, jobSearch, addJobOrder.getJobOrder(), orginFromJobSearch);
    		}
    		//end
    				
          // END : #119240  
    		
            return showForm(request, response, errors);
        }

        String addOrDeletePrebill = addJobOrder.getAddOrDeletePrebill();

        if ((addOrDeletePrebill != null) && (Constants.ADD.equals(addOrDeletePrebill) || Constants.DELETE.equals(addOrDeletePrebill))) {
            if (Constants.DELETE.equals(addOrDeletePrebill)) {
                // AddJobContract[] addJobContracts =
                // addJobOrder.getAddJobContracts();

                AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(addJobOrder, contractIndex);

                addJobContract = deletePrebill(addJobContract, addJobOrder.getPrebillId());

                addJobOrder.setPrebillId(0);
                addJobOrder.setAddOrDeletePrebill(Constants.NONE_VALUE);
            }
            else {
                AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(addJobOrder, contractIndex);

                addJobContract = addPrebill(addJobContract);

                addJobOrder.setAddOrDeletePrebill(Constants.NONE_VALUE);
            }
            return showForm(request, response, errors);
            // return showPage(request, errors, 1);
        }

        JobContractResultService jobContractResultService = (JobContractResultService) ServiceLocator.getInstance().getBean("jobContractResultService");
        JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        CountryService countryService = (CountryService) ServiceLocator.getInstance().getBean("countryService");

        JobOrder jobOrder = null;
        try {
            AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();
            if (addJobContracts != null && addJobContracts.length > 0) {
                for (int i = 0; i < addJobContracts.length; i++) {
                    AddJobContract addJobContract = addJobContracts[i];
                    JobContract jobContract = addJobContract.getJobContract();
                    // Validate customer/contact location
                    boolean locValidateFlag = InvoiceUtil.validateCustomerLocation(jobContract);
                    if (!locValidateFlag) {
                        errors.reject("cusomer.contact.combo.error", null, null);
                        return showForm(request, response, errors);
                    }

                    // newly added code
                    Set prebills = addJobContract.getJobContract().getPrebills();
                    Iterator prebillIterator = prebills.iterator();
                    while (prebillIterator.hasNext()) {
                        Prebill prebill = (Prebill) prebillIterator.next();
                        boolean splitpctflag = InvoiceUtil.validateSplitPct(prebill.getSplitPct());
                        if (!splitpctflag) {
                            errors.reject("Invalid.splitPct.error", new Object[] { prebill.getSplitPct() }, null);
                            return showForm(request, response, errors);
                        }
                    }
                    // upto here

                    // new code added for the vatprovince checking on 10112008

                    boolean splitVatCode = InvoiceUtil.validateLineVatCodes(addJobOrder.getJobOrder(), addJobContract.getJobContract().getPrebills());
                    if (splitVatCode == false) {
                        errors.reject("vatcode.line.items.error", null, null);
                        return showForm(request, response, errors);
                    }

                    if (addJobOrder.getJobOrder().getBuName() != null && (!addJobOrder.getJobOrder().getBuName().trim().equals(""))) {
                        BusinessUnit bu = userService.getBusinessUnitByName(addJobOrder.getJobOrder().getBuName());
                        Country country = bu.getCountry();
                        if (country.getSameProvinceValidation() != null && country.getSameProvinceValidation() != false) {
                            Set prebillss = addJobContract.getJobContract().getPrebills();
                            if (prebillss != null) {
                                Iterator prebillIterator1 = prebillss.iterator();
                                CountryVAT countryVAT = null;
                                while (prebillIterator1.hasNext()) {
                                    Prebill prebill = (Prebill) prebillIterator1.next();
                                    countryVAT = countryService.getDefaultStateByCountryCodeandVatCode(country.getCountryCode(), prebill.getVatCode());
                                }
                                if (countryVAT != null) {
                                    addJobContract.setContractLvlProvince(countryVAT.getCountryVATId().getStateCode());
                                }
                            }
                            System.out.println("default state code is" + addJobContract.getContractLvlProvince());
                        }
                        else {
                            addJobContract.setContractLvlProvince("");
                        }
                    }
                    String vattreament = TaxUtil.determineVATTreatementByCountry(addJobOrder.getJobOrder().getBuName(), addJobOrder.getJobOrder()
                            .getServiceLocationCode(), addJobContract.getJobContract().getCustCode(), addJobContract.getJobContract().getBillingContact()
                            .getId(), null, "single");
                    if (vattreament != null && !vattreament.trim().equals("")) {
                        jobContract.setVatTreament(vattreament);
                    }
                    // up to here

                    addJobContract.setJobContract(jobContract);
                    addJobContract = populateVatRegCountry(addJobContract);
                    Integer roundingDigits = prebillService.getDecimalDigitsByCurrency(addJobContract.getJobContract().getTransactionCurrencyCd());
                    addJobContract = calculateInvoiceLineItems(addJobContract, chosenPrebillIds, addJobOrder.getJobOrder().getJobFinishDate(), roundingDigits);
                    addJobContract.getJobContract().setInvoiceAmt(addJobContract.getInvoiceTotalAmt());
                    if ((refreshing != null) && Constants.GENERATE_INVOICE.equals(refreshing)) {
                        addJobContract.getJobContract().setInvoiceDate(new Date());
                    }
                    addJobContracts[i] = addJobContract;
                }
                addJobOrder.setAddJobContracts(addJobContracts);
                if ((refreshing != null) && Constants.CALC.equals(refreshing)) {
                    return showForm(request, response, errors);
                }

            }

            jobContractResultService.saveJobContractResults(addJobOrder.getAddJobContracts());

            // For saving job status
            jobOrder = jobService.getJobOrderByJobNumber(addJobOrder.getJobOrder().getJobNumber());

            if (!jobOrder.getJobStatus().trim().equals(addJobOrder.getJobOrder().getJobStatus())) {
                jobOrder.setJobStatus(addJobOrder.getJobOrder().getJobStatus());
                jobService.updateJobOrder(jobOrder);
            }
            // For breadcrumbs
            if (addJobOrder.getJobOrder().getPageNumber().intValue() < 4) {
                jobOrder.setPageNumber(Integer.valueOf(4));
                jobService.updateJobOrder(jobOrder);
            }
        }
        catch (ServiceException e) {
            e.printStackTrace();
            errors.reject("edit.job.invoice.preview.error.save", new Object[] { e.getMessage() }, null);
            return showForm(request, response, errors);
        }
        catch (Exception e) {
            e.printStackTrace();
            errors.reject("edit.job.invoice.preview.error.save", new Object[] { e.getMessage() }, null);
            return showForm(request, response, errors);
        }

        if (Constants.GENERATE_INVOICE.equals(refreshing)) {
            // For breadcrumbs
            if (addJobOrder.getJobOrder().getPageNumber().intValue() < 5) {
                jobOrder.setPageNumber(Integer.valueOf(5));
                jobService.updateJobOrder(jobOrder);
            }

            try {
                AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(addJobOrder, contractIndex);

                if ((addJobContract != null) && (addJobContract.getJobContract() != null)) {
                    if (userService.businessStreamExist(addJobContract.getJobOrder().getBranchName())) {
                        Set prebills = addJobContract.getJobContract().getPrebills();
                        Iterator prebillIterator = prebills.iterator();
                        while (prebillIterator.hasNext()) {
                            Prebill prebill = (Prebill) prebillIterator.next();
                            if (prebill.getBusStreamCode() == null || prebill.getBusStreamCode().trim().length() == 0) {
                                errors.reject("busstream.required.error", null, null);
                                return showForm(request, response, errors);
                            }
                        }
                    }
                    System.out.println("inside the generate invoice flag " + addJobContract.getContractLvlProvince());
                    /*
                     * InvoiceUtil.generateInvoice(
                     * addJobContract.getJobContract().getId(),
                     * InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(),
                     * null, null );
                     */
                    // new code added for vatprovince saving 10112008
                    InvoiceUtil.generateInvoice(addJobContract.getJobContract().getId(), InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(), null, null,
                                                addJobContract.getContractLvlProvince());
                    // up to here on 10112008
                }
            }
            catch (ServiceException e) {
                e.printStackTrace();
                errors.reject(e.getMessage(), e.getParams(), null);
                return showForm(request, response, errors);
            }
            catch (Exception e) {
                e.printStackTrace();
                errors.reject("edit.job.invoice.preview.error.save", new Object[] { e.getMessage() }, null);
                return showForm(request, response, errors);
            }

            ModelAndView modelAndView = new ModelAndView("edit-job-view-invoice-r");
            modelAndView.addObject("jobNumber", addJobOrder.getJobOrder().getJobNumber());
            modelAndView.addObject("contractIndex", addJobOrder.getContractIndex());

            return modelAndView;
        }/*
          * else if("previewInvoice".equals(refreshing)) {
          * addJobOrder.setPreviewFlag(true); return showForm(request, response,
          * errors); }
          */
        else if (Constants.NEXT.equals(refreshing)) {
            boolean nextFlag = false;
            if (tabName != null && (!tabName.trim().equals(""))) {
                AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();
                if (addJobContracts != null && addJobContracts.length > 0) {
                    JobContract jobContract = addJobContracts[Integer.parseInt(tabName)].getJobContract();
                    if (jobContract != null) {
                        if (jobContract.getInvoice() != null && (!jobContract.getInvoice().trim().equals(""))) {
                            // Invoice generated show the next page
                            nextFlag = true;
                        }
                        else {
                            nextFlag = false;
                        }
                    }
                }
            }
            if (nextFlag) {

                ModelAndView modelAndView = new ModelAndView("edit-job-view-invoice-r");
                modelAndView.addObject("jobNumber", addJobOrder.getJobOrder().getJobNumber());
                modelAndView.addObject("contractIndex", addJobOrder.getContractIndex());

                return modelAndView;
            }
            else {
                errors.reject("invoice.has.not.been.genererated.for.this.contract", new Object[] {}, null);
                return showForm(request, response, errors);
            }
        }
        else {
            AddJobContract addJobContract = addJobOrder.getAddJobContracts()[Integer.parseInt(tabName)];
            String tabSrc = addJobContract.getInvoicePreviewTabSrc();
            return new ModelAndView(new RedirectView("edit_job_invoice_preview.htm?jobNumber=" + addJobOrder.getJobOrder().getJobNumber() + "&tabName="
                                                     + tabName + "&tabSource=" + tabSrc + "&jobContractId=" + addJobContract.getJobContract().getId()
                                                     + "&warnUserFlag=" + addJobOrder.getWarnUserFlag() + "&contractIndex=" + contractIndex));
            /*
             * return new ModelAndView( "edit-job-invoice-preview-r",
             * "jobNumber", addJobOrder.getJobOrder().getJobNumber() );
             */
        }
    }

    protected boolean suppressValidation(HttpServletRequest request) {
        String refreshing = request.getParameter("refreshing");

        if ((refreshing != null) && Constants.TRUE_VALUE.equals(refreshing)) {

            return true;
        }
        // START : #119240
    	String showNextListFlag = request.getParameter("showNextListFlag");
    	String showPrevListFlag = request.getParameter("showPrevListFlag");
    	if(showNextListFlag != null && showNextListFlag.equals("next")){
    		return true;
    	}
    	if(showPrevListFlag != null && showPrevListFlag.equals("prev")){
    		return true;
    	}
    	// END : #119240
        
        return super.suppressValidation(request);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
     * javax.servlet.http.HttpServletRequest,
     * org.springframework.web.bind.ServletRequestDataBinder)
     */
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {

        String refreshing = request.getParameter("refreshing");
        SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.MM_DD_YYYY_DATE_FORMAT);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     * (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        AddJobOrder addJobOrder = new AddJobOrder();
        JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
        CalculatorService calculatorService = (CalculatorService) ServiceLocator.getInstance().getBean("calculatorService");
        JobContractResultService jobContractResultService = (JobContractResultService) ServiceLocator.getInstance().getBean("jobContractResultService");
        CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
        CountryService countryService = (CountryService) ServiceLocator.getInstance().getBean("countryService");
        PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
        // newly added 07112008
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        // up to here 07112008
        String jobNumber = request.getParameter("jobNumber");
        String tabName = request.getParameter("tabName");
        String tabSrc = request.getParameter("tabSource");
        String jobContractId = request.getParameter("jobContractId");
        String warnUserFlag = request.getParameter("warnUserFlag");
        String contractIndex = request.getParameter("contractIndex");
        // System.out.println("jobNumber in formBacking: "+jobNumber);
        if (contractIndex != null && !contractIndex.trim().equals("")) {
            addJobOrder.setContractIndex(Integer.parseInt(contractIndex));
        }
        else
            addJobOrder.setContractIndex(0);

        if (warnUserFlag == null)
            warnUserFlag = "";

        addJobOrder.setWarnUserFlag(warnUserFlag);

        long jobContractID = 0;

        if (tabName == null || tabName.trim().equals(""))
            tabName = "0";

        if (tabSrc == null || tabSrc.trim().equals(""))
            tabSrc = "edit_job_invoice_preview_tab1.htm";

        if (jobContractId != null && (!jobContractId.trim().equals("")))
            jobContractID = Long.parseLong(jobContractId);

        // JobOrder jobOrder =
        // jobService.getJobOrderByJobNumberWithDetail(jobNumber);
        try {
            JobOrder jobOrder = jobService.getJobOrderByJobNumberWithPrebills(jobNumber);
            if (jobOrder == null) {
                jobOrder = new JobOrder();
            }

            /*
             * String jobType = request.getParameter("jobType"); if((jobType !=
             * null) && !"".equals(jobType.trim())) {
             * jobOrder.setJobType(jobType); }
             */
            
          // START : #119240
            addJobOrder.setJobNumber(jobNumber);
            addJobOrder.setCurrPageIdentifier(Constants.JOB_PREVIEW_INVOICE);
            // Setting next list and prev list values
    		if(request.getSession() != null)
    		{
    			JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
    			
    			String orginFromJobSearch =  (String)request.getSession().getAttribute("orginFromJobSearch");
    			
    			JobUtil.setPrevNextJobFlags(addJobOrder, jobSearch, jobOrder, orginFromJobSearch);
    		}
    		//end
    				
          // END : #119240  
    		
            addJobOrder.setJobOrder(jobOrder);

            List jobContractList = new ArrayList(jobOrder.getJobContracts());
            JobUtil.sortJobContractsById(jobContractList);
            AddJobContract[] addJobContracts = new AddJobContract[jobContractList.size()];
            for (int i = 0; i < jobContractList.size(); i++) {
                JobContract jobContract = (JobContract) jobContractList.get(i);

                AddJobContract addJobContract = new AddJobContract();
                addJobContract.setJobContract(jobContract);
                addJobContract.setJobOrder(jobOrder);
                addJobContracts[i] = addJobContract;

                if (jobContractID > 0) {
                    if (jobContract.getId() == jobContractID) {
                        addJobOrder.setTabName(Integer.valueOf(i).toString());
                        addJobContract.setInvoicePreviewTabSrc(tabSrc);
                    }
                    else
                        addJobContract.setInvoicePreviewTabSrc("edit_job_invoice_preview_tab1.htm");
                }
                else
                    addJobContract.setInvoicePreviewTabSrc("edit_job_invoice_preview_tab1.htm");
                /*
                 * AddJobContractServices addJobContractServices = new
                 * AddJobContractServices();
                 * addJobContractServices.getAddedJobContractServices
                 * ().addAll(jobContract.getJobContractServices());
                 * 
                 * 
                 * addJobContract.setAddJobContractServices(addJobContractServices
                 * );
                 */

                /*
                 * addJobContract.setAddJobContractServices(addJobContractServices
                 * );
                 * 
                 * Iterator vesselItr =
                 * jobContract.getJobContractVessels().iterator();
                 * AddJobContractVessel[] addJobContractVessels = new
                 * AddJobContractVessel
                 * [jobContract.getJobContractVessels().size()]; int count = 0;
                 * while(vesselItr.hasNext()) { JobContractVessel
                 * jobContractVessel = (JobContractVessel)vesselItr.next();
                 * 
                 * AddJobContractVessel addJobContractVessel =
                 * JobUtil.createAddJobContractVessel(jobContractVessel);
                 * addJobContractVessels[count] = addJobContractVessel;
                 * 
                 * count ++; }
                 * addJobContract.setAddJobContractVessels(addJobContractVessels
                 * );
                 */

                // Set the default tax & vat codes & percentages for tax
                // calculation on load of page
                List vatCodeList = TaxUtil.determineVATCodesByCountry(jobOrder.getBuName(), jobOrder.getServiceLocationCode(), addJobContract.getJobContract()
                        .getCustCode(), addJobContract.getJobContract().getBillingContact().getId(), null, "single");
                List taxCodeList = TaxUtil.determineTaxCodesByServiceLocation(jobOrder.getServiceLocationCode());

                if (vatCodeList == null) // No vat processing
                {
                    addJobContract.setContractLvlVatCode(null);
                    addJobContract.setContractLvlVatPct(0);
                    addJobContract.getJobContract().setVatRegCountry(null);
                }
                else {
                    if (vatCodeList.size() > 0) {
                        TaxCode vatCode = (TaxCode) vatCodeList.get(0);
                        addJobContract.setContractLvlVatCode(vatCode.getTaxCodeHeader());
                        addJobContract.setContractLvlVatPct(TaxUtil.getTaxPctByTaxCode(vatCode.getTaxCodeHeader(), jobOrder.getJobFinishDate()));
                    }
                }
                if (taxCodeList == null) {
                    addJobContract.setContractLvlTaxCode(null);
                    addJobContract.setContractLvlTaxPct(0);
                }
                else {
                    TaxCode salesTaxCode = (TaxCode) taxCodeList.get(0);
                    addJobContract.setContractLvlTaxCode(salesTaxCode.getTaxCodeHeader());
                    // changed to get the tax_pct from tax_rates based on
                    // sales_tax_code
                    // addJobContract.setContractLvlTaxPct(taxRate.getTaxPct());
                    addJobContract.setContractLvlTaxPct(TaxUtil.getTaxPctByTaxCode(salesTaxCode.getTaxCodeHeader(), jobOrder.getJobFinishDate()));
                }

                // Prepare prebillIds list
                String chosenPrebillIds = "";
                Set prebillSet = jobContract.getPrebills();

                // setting Line Number to show in preview pdf
                int lineNumber = 1;
                for (Prebill prebill : (Set<Prebill>) prebillSet) {
                    prebill.setLineNumber(lineNumber);
                    lineNumber++;
                    prebillService.savePrebill(prebill);
                }
                prebillSet = jobContract.getPrebills();

                if (prebillSet != null && prebillSet.size() > 0) {
                    Iterator iter = prebillSet.iterator();
                    while (iter.hasNext()) {
                        Prebill prebill = (Prebill) iter.next();
                        /*
                         * if(prebill.getLineDescription() != null &&
                         * (!prebill.getLineDescription().trim().equals(""))) {
                         * if(prebill.getLineDescription().indexOf("°") > -1)
                         * prebill
                         * .setLineDescription(prebill.getLineDescription(
                         * ).replaceAll("°", "&deg;"));
                         * if(prebill.getLineDescription().indexOf("º") > -1)
                         * prebill
                         * .setLineDescription(prebill.getLineDescription(
                         * ).replaceAll("º", "&deg;"));
                         * 
                         * 
                         * //prebill.setLineDescription(URLEncoder.encode(prebill
                         * .getLineDescription())); }
                         */
                        if (prebill.getTaxArticle() != null)
                            addJobContract.setTaxArticleCode(prebill.getTaxArticle().getTaxArticleCode());

                        if (prebill.getTaxCode() == null || prebill.getTaxCode().trim().equals("")) {
                            prebill.setTaxCode(addJobContract.getContractLvlTaxCode());
                            prebill.setTaxPct(addJobContract.getContractLvlTaxPct());
                        }
                        if (prebill.getVatCode() == null || prebill.getVatCode().trim().equals("")) {
                            prebill.setVatCode(addJobContract.getContractLvlVatCode());
                            prebill.setVatPct(addJobContract.getContractLvlVatPct());
                        }

                        if (chosenPrebillIds == null || chosenPrebillIds.trim().equals(""))
                            chosenPrebillIds = (Long.valueOf(prebill.getId())).toString();
                        else
                            chosenPrebillIds = chosenPrebillIds + ";" + (Long.valueOf(prebill.getId())).toString();
                    }
                }
                Integer decimalDigits = prebillService.getDecimalDigitsByCurrency(jobContract.getTransactionCurrencyCd());
                addJobContract = calculateInvoiceLineItemsOnLoad(addJobContract, chosenPrebillIds, jobOrder.getJobFinishDate(), decimalDigits);

                addJobContract.setContractLvlTaxCode(null);
                addJobContract.setContractLvlVatCode(null);

                addJobContract.setContractLvlTaxPct(0);
                addJobContract.setContractLvlVatPct(0);

                // set billing address to display as label
                if (jobContract.getBillingContact() != null) {
                    ContactCust contactCust = jobService.getBillingContactByContactId(jobContract.getBillingContact().getId(), jobContract.getCustCode());
                    if (contactCust != null) {
                        if (contactCust.getCustAddrSeq() != null && contactCust.getCustAddrSeq().getCustAddresses() != null) {
                            Set st = contactCust.getCustAddrSeq().getCustAddresses();
                            if (st != null && st.size() > 0) {
                                Iterator itr = st.iterator();
                                if (itr != null && itr.hasNext()) {
                                    CustAddress custAddress = (CustAddress) itr.next();
                                    String address = "";

                                    if (custAddress.getAddress1() != null && (!custAddress.getAddress1().trim().equals(""))) {
                                        if (address.trim().equals(""))
                                            address = custAddress.getAddress1();
                                        else
                                            address = address + " , " + custAddress.getAddress1();
                                    }
                                    if (custAddress.getAddress2() != null && (!custAddress.getAddress2().trim().equals(""))) {
                                        if (address.trim().equals(""))
                                            address = custAddress.getAddress2();
                                        else
                                            address = address + " , " + custAddress.getAddress2();
                                    }
                                    if (custAddress.getAddress3() != null && (!custAddress.getAddress3().trim().equals(""))) {
                                        if (address.trim().equals(""))
                                            address = custAddress.getAddress3();
                                        else
                                            address = address + " , " + custAddress.getAddress3();
                                    }
                                    if (custAddress.getAddress4() != null && (!custAddress.getAddress4().trim().equals(""))) {
                                        if (address.trim().equals(""))
                                            address = custAddress.getAddress4();
                                        else
                                            address = address + " , " + custAddress.getAddress4();
                                    }
                                    if (custAddress.getCity() != null && (!custAddress.getCity().trim().equals(""))) {
                                        if (address.trim().equals(""))
                                            address = custAddress.getCity();
                                        else
                                            address = address + " , " + custAddress.getCity();
                                    }
                                    Country country = null;
                                    if (custAddress.getCountry() != null && !custAddress.getCountry().trim().equals("")) {
                                        country = countryService.getCountryByCode(custAddress.getCountry());
                                    }
                                    if (custAddress.getState() != null && (!custAddress.getState().trim().equals(""))
                                        && (country == null || country.getShowState())) {
                                        if (address.trim().equals(""))
                                            address = custAddress.getState();
                                        else
                                            address = address + " , " + custAddress.getState();
                                    }
                                    if (custAddress.getCountry() != null && (!custAddress.getCountry().trim().equals(""))) {
                                        // Country country =
                                        // countryService.getCountryByCode(custAddress.getCountry());
                                        if (country != null) {
                                            if (address.trim().equals(""))
                                                address = country.getName();
                                            else
                                                address = address + " , " + country.getName();
                                        }
                                        else {
                                            if (address.trim().equals(""))
                                                address = custAddress.getCountry();
                                            else
                                                address = address + " , " + custAddress.getCountry();
                                        }
                                    }

                                    addJobContract.setBillingAddress(address);
                                    if (vatCodeList != null && vatCodeList.size() > 0) // set vat reg country to billing addr country
                                    {
                                        if (addJobContract.getJobContract().getVatRegCountry() == null) {
                                            addJobContract.getJobContract().setVatRegCountry(countryService.getCountryByCode(custAddress.getCountry()));
                                            CustVatRegistration custVatRegistration = customerService
                                                    .getCustomerVatRegistrationsByCustCodeAndCountryCode(jobContract.getCustCode(), custAddress.getCountry());
                                            if (custVatRegistration != null) {
                                                addJobContract.getJobContract().setVatRegId(custVatRegistration.getCustVatRegistrationId().getRegistrationId());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Set default vat registration id for the given customer if its
                // null
                if (jobContract.getVatRegId() == null || jobContract.getVatRegId().trim().equals("")) {
                    List custVatRegList = customerService.getCustomerVatRegistrationsByCustCode(jobContract.getCustCode());
                    if (custVatRegList != null && custVatRegList.size() > 0) {
                        addJobContract.setVatRegistrationId(((CustVatRegistration) custVatRegList.get(0)).getCustVatRegistrationId().getRegistrationId());
                        jobContract.setVatRegId(addJobContract.getVatRegistrationId());
                    }
                }
            }
            String contractDesc = "";

            contractDesc = buildDefaultJobDescription(jobOrder);

            addJobOrder.setAddJobContracts(addJobContracts);
            addJobOrder.setDefaultJobDesc(contractDesc);

            // Check for open periods & set flag accordingly
            int periodCount = jobService.getPeriods(addJobOrder.getJobOrder().getBuName(), addJobOrder.getJobOrder().getJobFinishDate());
            if (periodCount <= 0)
                addJobOrder.setOpenPeriodsFlag(Constants.FALSE_VALUE);
            else
                addJobOrder.setOpenPeriodsFlag(Constants.TRUE_VALUE);
        }
        catch (ServiceException e) {
            throw new ServiceException(e.getMessage(), e.getParams(), e);
        }
        catch (Throwable t) {
            t.printStackTrace();
            throw new ServiceException("generic.error", new Object[] { t.getMessage() }, t);
        }
        return addJobOrder;
    }

    AddJobContract calculateInvoiceLineItems(AddJobContract addJobContract, String chosenPrebillIds, Date jobFinishDate, Integer roundingDigits) {
        PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        // List addedJobContractServices =
        // addJobContract.getAddJobContractServices().getAddedJobContractServices();
        double invoiceTotalAmt = 0;
        double vatTotalAmt = 0;
        double taxTotalAmt = 0;
        // Check if group tax calculation is needed

        String contractLvlTaxCode = addJobContract.getContractLvlTaxCode();
        String contractLvlVatCode = addJobContract.getContractLvlVatCode();
        double contractLvlTaxPct = addJobContract.getContractLvlTaxPct();
        double contractLvlVatPct = addJobContract.getContractLvlVatPct();
        String taxArticleCode = addJobContract.getTaxArticleCode();
        TaxArticle taxArticle = null;

        System.out.println("chosenPrebillIds :" + chosenPrebillIds + " contractLvlTaxCode :" + contractLvlTaxCode + " contractLvlVatCode :"
                           + contractLvlVatCode + " contractLvlTaxPct :" + contractLvlTaxPct + " contractLvlVatPct :" + contractLvlVatPct);

        TaxService taxService = (TaxService) ServiceLocator.getInstance().getBean("taxService");

        if (contractLvlTaxCode != null && (!contractLvlTaxCode.trim().equals(""))) {

            TaxCode taxCode = taxService.getTaxCodeByCode(contractLvlTaxCode);
            if (taxCode != null) {
                contractLvlTaxPct = TaxUtil.getTaxPctByTaxCode(taxCode.getTaxCodeHeader(), jobFinishDate);
                addJobContract.setContractLvlTaxPct(contractLvlTaxPct);
            }
        }
        if (contractLvlVatCode != null && (!contractLvlVatCode.trim().equals(""))) {

            TaxCode taxCode = taxService.getTaxCodeByCode(contractLvlVatCode);
            if (taxCode != null) {
                contractLvlVatPct = TaxUtil.getTaxPctByTaxCode(taxCode.getTaxCodeHeader(), jobFinishDate);
                addJobContract.setContractLvlVatPct(contractLvlVatPct);
            }
        }
        if (taxArticleCode != null && (!taxArticleCode.trim().equals(""))) {
            taxArticle = taxService.getTaxArticleByCode(taxArticleCode);
        }
        else
            addJobContract.setTaxArticleCode(null);

        String[] prebillList = null;
        boolean taxVatFlag = false;

        if (chosenPrebillIds != null && (!chosenPrebillIds.trim().equals(""))) {
            prebillList = chosenPrebillIds.split(";");
        }

        Set prebills = addJobContract.getJobContract().getPrebills();
        if (prebills != null) {
            Iterator prebillIterator = prebills.iterator();
            while (prebillIterator.hasNext()) {
                Prebill prebill = (Prebill) prebillIterator.next();
                // prebill.setLineDescription(URLEncoder.encode(prebill.getLineDescription()));
                prebill.setTaxArticle(taxArticle);

                if (prebillList != null && prebillList.length > 0) {
                    for (int j = 0; j < prebillList.length; j++) {
                        if (prebill.getId() == new Long(prebillList[j]).longValue()) {
                            if (contractLvlTaxCode != null && (!contractLvlTaxCode.trim().equals(""))) {
                                prebill.setTaxCode(contractLvlTaxCode);
                                prebill.setTaxPct(contractLvlTaxPct);
                            }
                            if (contractLvlVatCode != null && (!contractLvlVatCode.trim().equals(""))) {
                                prebill.setVatCode(contractLvlVatCode);
                                prebill.setVatPct(contractLvlVatPct);
                            }

                        }
                    }
                }
                prebill = calculatePrebill(prebill, jobFinishDate, roundingDigits, addJobContract.getJobOrder().getBuName());
                if ((!taxVatFlag)
                    && ((prebill.getTaxCode() != null && (!prebill.getTaxCode().trim().equals(""))) || (prebill.getVatCode() != null && (!prebill.getVatCode()
                            .trim().equals("")))))
                    taxVatFlag = true;

                invoiceTotalAmt = invoiceTotalAmt + prebill.getNetPrice();
                vatTotalAmt = vatTotalAmt + prebill.getVatAmt();
                taxTotalAmt = taxTotalAmt + prebill.getTaxAmt();
            }

            addJobContract.getJobContract().setTaxVatFlag(taxVatFlag);

            if (roundingDigits == null)
                roundingDigits = 2;

            addJobContract.setInvoiceTotalAmt(NumberUtil.roundHalfUp(invoiceTotalAmt, roundingDigits));
            
            if (addJobContract.getJobOrder().getBuName().equals("KOR01") && addJobContract.getJobContract().getTransactionCurrencyCd().equals("KRW")){
                addJobContract.setVatTotalAmt(NumberUtil.roundDown(vatTotalAmt, 0));
                addJobContract.setTaxTotalAmt(NumberUtil.roundDown(taxTotalAmt, 0));
            }else{
                addJobContract.setVatTotalAmt(NumberUtil.roundHalfUp(vatTotalAmt, Constants.TAX_VAT_SCALE));
                addJobContract.setTaxTotalAmt(NumberUtil.roundHalfUp(taxTotalAmt, Constants.TAX_VAT_SCALE));
            }
        }
        addJobContract.getJobContract().setPrebills(prebills);
        return addJobContract;
    }

    Prebill calculatePrebill(Prebill prebill, Date jobFinishDate, Integer roundingDigits, String buName) {
        PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
        System.out.println("prebill id :" + prebill.getId());
        System.out.println("unit price :" + prebill.getUnitPrice());
        System.out.println("split pct :" + prebill.getSplitPct());
        System.out.println("discount pct :" + prebill.getDiscountPct());
        System.out.println("tax pct :" + prebill.getTaxPct());
        System.out.println("vat pct :" + prebill.getTaxPct());
        System.out.println("discount pct :" + prebill.getDiscountPct());
        TaxService taxService = (TaxService) ServiceLocator.getInstance().getBean("taxService");

        if (prebill.getTaxCode() != null && (!prebill.getTaxCode().trim().equals(""))) {

            TaxCode taxCode = taxService.getTaxCodeByCode(prebill.getTaxCode());

            if (taxCode != null) {
                System.out.println("taxCodein calculatePrebill: " + taxCode.getTaxCodeHeader());
                double taxPct = TaxUtil.getTaxPctByTaxCode(taxCode.getTaxCodeHeader(), jobFinishDate);
                System.out.println("taxPct in calculatePrebill: " + taxPct);
                prebill.setTaxPct(taxPct);
            }
        }
        if (prebill.getVatCode() != null && (!prebill.getVatCode().trim().equals(""))) {

            TaxCode taxCode = taxService.getTaxCodeByCode(prebill.getVatCode());
            if (taxCode != null) {
                System.out.println("vatCode in calculatePrebill: " + taxCode.getTaxCodeHeader());
                double taxPct = TaxUtil.getTaxPctByTaxCode(taxCode.getTaxCodeHeader(), jobFinishDate);
                System.out.println("vatPct in calculatePrebill: " + taxPct);
                prebill.setVatPct(taxPct);
            }
        }
        if (roundingDigits == null)
            roundingDigits = 2;

        double unitPrice = prebill.getUnitPrice();
        double discountPct = prebill.getDiscountPct();
        double taxPct = prebill.getTaxPct() != null ? prebill.getTaxPct().doubleValue() : 0;
        double vatPct = prebill.getVatPct() != null ? prebill.getVatPct().doubleValue() : 0;

        double netPrice = prebill.getUnitPrice() * (prebill.getSplitPct() * 0.01) * (1 - (prebill.getDiscountPct() * 0.01));
        log.info("net price :" + netPrice);
        String taxCode = prebill.getTaxCode();
        String vatCode = prebill.getVatCode();

        // First apply the discount on unit price
        // netPrice = unitPrice - (unitPrice*discountPct)/100;
        // Changes for iTrak 53802, if TAX_CODE has INCLUDE_VAT flag set to 'Y',
        // then apply sales tax after adding vat amount
        // to net price, otherwise retain the old logic

        TaxCode salesTaxCode = taxService.getTaxCodeByCode(taxCode);
        if (salesTaxCode != null) {
            if (salesTaxCode.getIncludeVat()) {
                // First Apply vat % on net price

                double vatAmt = (netPrice * vatPct) / 100;

                double salesTaxNetPrice = netPrice + vatAmt;

                // Calculate sales tax amount on net price + vat amount
                double taxAmt = (salesTaxNetPrice * taxPct) / 100;

                prebill.setNetPrice(NumberUtil.roundHalfUp(netPrice, roundingDigits));
                prebill.setTaxAmt(NumberUtil.roundHalfUp(taxAmt, Constants.TAX_VAT_SCALE));
                prebill.setVatAmt(NumberUtil.roundHalfUp(vatAmt, Constants.TAX_VAT_SCALE));
                if (prebill.getRateMult() != null && prebill.getRateDiv() != null) {
                	setBasePrices(prebill, roundingDigits);
                }
            }
            else {
                // Apply tax & vat % on net price
                double taxAmt = (netPrice * taxPct) / 100;
                double vatAmt = (netPrice * vatPct) / 100;
                prebill.setNetPrice(NumberUtil.roundHalfUp(netPrice, roundingDigits));
                prebill.setTaxAmt(NumberUtil.roundHalfUp(taxAmt, Constants.TAX_VAT_SCALE));
                prebill.setVatAmt(NumberUtil.roundHalfUp(vatAmt, Constants.TAX_VAT_SCALE));
                if (prebill.getRateMult() != null && prebill.getRateDiv() != null) {
                	setBasePrices(prebill, roundingDigits);
                }
            }
        }
        else {
            // Apply tax & vat % on net price
            double taxAmt = (netPrice * taxPct) / 100;
            double vatAmt = (netPrice * vatPct) / 100;

            prebill.setNetPrice(NumberUtil.roundHalfUp(netPrice, roundingDigits));
            prebill.setTaxAmt(NumberUtil.roundHalfUp(taxAmt, Constants.TAX_VAT_SCALE));
            prebill.setVatAmt(NumberUtil.roundHalfUp(vatAmt, Constants.TAX_VAT_SCALE));
            if (prebill.getRateMult() != null && prebill.getRateDiv() != null) {
            	setBasePrices(prebill, roundingDigits);
            }
        }

        prebill.setTaxPct(taxPct);
        prebill.setVatPct(vatPct);
        prebill.setTaxCode(taxCode);
        prebill.setVatCode(vatCode);
        prebill = populateSplitAllocAmount(prebill, roundingDigits);
        return prebill;

    }
    
    Prebill setBasePrices(Prebill prebill, Integer roundingDigits){
    	prebill.setBaseUnitPrice(NumberUtil.roundHalfUp(prebill.getUnitPrice() * prebill.getRateMult() / prebill.getRateDiv(), roundingDigits));
        prebill.setBaseNetPrice(NumberUtil.roundHalfUp(prebill.getNetPrice() * prebill.getRateMult() / prebill.getRateDiv(), roundingDigits));
        prebill.setBaseTaxAmt(NumberUtil.roundHalfUp(prebill.getTaxAmt() * prebill.getRateMult() / prebill.getRateDiv(), Constants.TAX_VAT_SCALE));
        prebill.setBaseVatAmt(NumberUtil.roundHalfUp(prebill.getVatAmt() * prebill.getRateMult() / prebill.getRateDiv(), Constants.TAX_VAT_SCALE));
    	return prebill;
    }

    AddJobContract deletePrebill(AddJobContract addJobContract, long prebillId) {

        Set prebills = addJobContract.getJobContract().getPrebills();
        Set newPrebills = new HashSet();
        if (prebills != null) {
            Iterator prebillIterator = prebills.iterator();
            while (prebillIterator.hasNext()) {
                Prebill prebill = (Prebill) prebillIterator.next();
                if (prebill.getId() != prebillId) {
                    newPrebills.add(prebill);
                }
            }
            addJobContract.getJobContract().getPrebills().clear();
            addJobContract.getJobContract().setPrebills(newPrebills);
        }

        return addJobContract;
    }

    AddJobContract addPrebill(AddJobContract addJobContract) {
        Prebill prebill = new Prebill();
        prebill.setServiceType("OPS");
        prebill.setBusStreamCode("4112999");
        prebill.setAccount("4112999");
        prebill.setDeptid("INSPECT");
        prebill.setProductGroup("NOPROD");
        prebill.setLevel0(" ");
        prebill.setLevel1(" ");

        int lineNumber = 0;
        int manualLineNumber = 0;

        if (addJobContract.getJobContract().getPrebills() != null) {
            Iterator iter = addJobContract.getJobContract().getPrebills().iterator();
            lineNumber = addJobContract.getJobContract().getPrebills().size() + 1;
            while (iter.hasNext()) {
                Prebill prebillItem = (Prebill) iter.next();
                if (prebillItem.getLevel0() != null && prebillItem.getLevel0().equals(" ")) {
                    manualLineNumber = prebillItem.getVesselSortNum().intValue();
                }
            }
        }

        if (manualLineNumber > 0)
            lineNumber = manualLineNumber;

        prebill.setPrimaryBranchCd(addJobContract.getJobContract().getJobOrder().getBranchName());
        prebill.setJobContract(addJobContract.getJobContract());

        PrebillSplit prebillSplit = new PrebillSplit();
        prebillSplit.setPrebill(prebill);
        prebillSplit.setBranchName(addJobContract.getJobContract().getJobOrder().getBranchName());
        prebillSplit.setAllocPct(100.0);
        prebillSplit.setPrimaryInd(true);

        User user = SecurityUtil.getUser();
        if (user != null) {
            prebillSplit.setUpdatedByUserName(user.getLoginName());
            prebillSplit.setUpdateTime(new Date());
        }

        prebill.getPrebillSplits().add(prebillSplit);

        prebill.setVesselSortNum(lineNumber);
        prebill.setLotSortNum(lineNumber);

        addJobContract.getJobContract().getPrebills().add(prebill);

        /*
         * List addedJobContractServices =
         * addJobContract.getAddJobContractServices
         * ().getAddedJobContractServices();
         * 
         * boolean setPrebill = false;
         * 
         * if(addedJobContractServices != null &&
         * addedJobContractServices.size() > 0) { for(int
         * i=0;i<addedJobContractServices.size();i++) { if(!setPrebill) {
         * JobContractService jobContractService = (JobContractService)
         * addedJobContractServices.get(i); Set jobContractServiceResults =
         * jobContractService.getResults(); if(jobContractServiceResults !=
         * null) { Iterator jobContractIterator =
         * jobContractServiceResults.iterator();
         * while(jobContractIterator.hasNext() ) { JobContractServiceResult
         * jobContractServiceResult = (JobContractServiceResult)
         * jobContractIterator.next(); if(!setPrebill) {
         * prebill.setJobContract(addJobContract.getJobContract());
         * prebill.setJobContractServiceResult(jobContractServiceResult);
         * 
         * jobContractServiceResult.getPrebills().add(prebill); setPrebill =
         * true; }
         * 
         * } jobContractService.setResults(jobContractServiceResults); } } }
         * addJobContract
         * .getAddJobContractServices().setAddedJobContractServices
         * (addedJobContractServices);
         * 
         * 
         * }
         */
        return addJobContract;
    }

    String buildDefaultJobDescription(JobOrder jobOrder) {
        String jobDesc = "";

        jobDesc = "General Service Charge in connection with the ";
        JobTypeService jobTypeService = (JobTypeService) ServiceLocator.getInstance().getBean("jobTypeService");
        JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
        CountryService countryService = (CountryService) ServiceLocator.getInstance().getBean("countryService");

        Operation operation = jobTypeService.getOperationByOperatoinCode(jobOrder.getOperation());

        if (operation != null) {
            jobDesc = jobDesc + operation.getDescription();
        }
        if (jobOrder.getProductNames() != null && (!jobOrder.getProductNames().trim().equals(""))) {

            Set jobVesselSet = jobOrder.getJobVessels();
            String vessels = "";
            String products = "";

            if (jobVesselSet != null && jobVesselSet.size() > 0) {
                Iterator vesselIter = jobVesselSet.iterator();
                while (vesselIter.hasNext()) {
                    JobVessel jobVessel = (JobVessel) vesselIter.next();
                    if (jobVessel.getVesselName() != null && (!jobVessel.getVesselName().trim().equals(""))) {
                        if (vessels.trim().equals(""))
                            vessels = jobVessel.getVesselName();
                        else
                            vessels = vessels + "," + " " + jobVessel.getVesselName();
                    }

                    Set jobProdSet = jobVessel.getJobProds();
                    Iterator prodIter = jobProdSet.iterator();
                    while (prodIter.hasNext()) {
                        JobProd jobProd = (JobProd) prodIter.next();
                        if (jobProd.getJobProductName() != null && (!jobProd.getJobProductName().trim().equals(""))) {
                            if (products.trim().equals(""))
                                products = jobProd.getJobProductName();
                            else
                                products = products + "," + " " + jobProd.getJobProductName();
                        }
                    }
                }
                if (!jobOrder.getJobType().trim().equals(Constants.MARINE_JOBTYPE))
                    jobDesc = jobDesc + " of " + products;
                if (jobOrder.getJobType().trim().equals(Constants.INSPECTION_JOBTYPE) || jobOrder.getJobType().trim().equals(Constants.MARINE_JOBTYPE)) {
                    if (jobOrder.getVesselNames() != null && (!jobOrder.getVesselNames().trim().equals(""))) {
                        jobDesc = jobDesc + " on " + vessels;
                    }
                }
            }

        }
        // comma space
        if (jobOrder.getServiceLocation() != null && jobOrder.getServiceLocation().getName() != null
            && !jobOrder.getServiceLocation().getName().trim().equals("")) {
            if (jobOrder.getServiceLocation().getName().trim().indexOf(",") != -1) {
                String servLocName = jobOrder.getServiceLocation().getName();
                String servc[] = jobOrder.getServiceLocation().getName().split("\\,");

                String servCountry = servc[servc.length - 1];
                Country country = countryService.getCountryByCode(servCountry);
                if (country != null) {
                    servLocName = servLocName.replaceAll(servCountry, country.getName());
                    String newServLoc[] = servLocName.split(",");
                    servLocName = "";
                    for (int i = 0; i < newServLoc.length; i++) {
                        if (i == 0)
                            servLocName = newServLoc[i];
                        else
                            servLocName = servLocName + "," + " " + newServLoc[i];
                    }
                    jobDesc = jobDesc + " at " + servLocName;
                }
                else {
                    for (int i = 0; i < servc.length; i++) {
                        if (i == 0)
                            servLocName = servc[i].trim();
                        else
                            servLocName = servLocName + "," + " " + servc[i].trim();
                    }
                    jobDesc = jobDesc + " at " + servLocName;

                    if (jobOrder.getJobType().trim().equals(Constants.INSPECTION_JOBTYPE) || jobOrder.getJobType().trim().equals(Constants.MARINE_JOBTYPE)) {
                        if (jobOrder.getServiceLocation().getCity() != null && !jobOrder.getServiceLocation().getCity().trim().equals("")) {
                            if (jobOrder.getServiceLocation().getCity().trim().indexOf(",") != -1) {
                                String servLocCity = "";
                                String servcity[] = jobOrder.getServiceLocation().getCity().split("\\,");
                                for (int i = 0; i < servcity.length; i++) {
                                    if (i == 0)
                                        servLocCity = servcity[i].trim();
                                    else
                                        servLocCity = servLocCity + "," + " " + servcity[i].trim();
                                }
                                jobDesc = jobDesc + " , " + servLocCity;
                            }
                            else
                                jobDesc = jobDesc + " , " + jobOrder.getServiceLocation().getCity();
                        }
                        // end
                        Country country2 = null;
                        if (jobOrder.getServiceLocation().getCountryCode() != null) {
                            country2 = countryService.getCountryByCode(jobOrder.getServiceLocation().getCountryCode());
                        }
                        if (jobOrder.getServiceLocation().getStateCode() != null) {
                            State state = countryService.getStateByCodeAndCountryCode(jobOrder.getServiceLocation().getStateCode(), jobOrder
                                    .getServiceLocation().getCountryCode());
                            if (state != null && (country2 == null || country2.getShowState())) {
                                jobDesc = jobDesc + " , " + state.getName();
                            }
                        }
                        if (jobOrder.getServiceLocation().getCountryCode() != null) {
                            if (country2 != null)
                                jobDesc = jobDesc + " , " + country2.getName();
                        }
                    }
                }
            }
            else {
                jobDesc = jobDesc + " at " + jobOrder.getServiceLocation().getName();

                if (jobOrder.getJobType().trim().equals(Constants.INSPECTION_JOBTYPE) || jobOrder.getJobType().trim().equals(Constants.MARINE_JOBTYPE)) {
                    if (jobOrder.getServiceLocation().getCity() != null && !jobOrder.getServiceLocation().getCity().trim().equals("")) {
                        if (jobOrder.getServiceLocation().getCity().trim().indexOf(",") != -1) {
                            String servLocCity = "";
                            String servc[] = jobOrder.getServiceLocation().getCity().split("\\,");
                            for (int i = 0; i < servc.length; i++) {
                                if (i == 0)
                                    servLocCity = servc[i].trim();
                                else
                                    servLocCity = servLocCity + "," + " " + servc[i].trim();
                            }
                            jobDesc = jobDesc + " , " + servLocCity;
                        }
                        else
                            jobDesc = jobDesc + " , " + jobOrder.getServiceLocation().getCity();
                    }
                    // end
                    Country country = null;
                    if (jobOrder.getServiceLocation().getCountryCode() != null) {
                        country = countryService.getCountryByCode(jobOrder.getServiceLocation().getCountryCode());
                    }
                    if (jobOrder.getServiceLocation().getStateCode() != null) {
                        State state = countryService.getStateByCodeAndCountryCode(jobOrder.getServiceLocation().getStateCode(), jobOrder.getServiceLocation()
                                .getCountryCode());
                        if (state != null && (country == null || country.getShowState())) {
                            jobDesc = jobDesc + " , " + state.getName();
                        }
                    }
                    if (jobOrder.getServiceLocation().getCountryCode() != null) {
                        if (country != null)
                            jobDesc = jobDesc + " , " + country.getName();
                    }
                }
            }
        }
        if (jobOrder.getJobFinishDate() != null && (!jobOrder.getJobFinishDate().toString().trim().equals(""))) {
            String formattedDate = DateUtil.formateJobDescriptionDate(jobOrder.getJobFinishDate());
            jobDesc = jobDesc + " on " + formattedDate;
        }
        else if (jobOrder.getEtaDate() != null && (!jobOrder.getEtaDate().toString().trim().equals(""))) {
            String formattedDate = DateUtil.formateJobDescriptionDate(jobOrder.getEtaDate());
            jobDesc = jobDesc + " on " + formattedDate;
        }

        jobDesc = jobDesc + ".";
        return jobDesc;
    }

    private AddJobContract populateVatRegCountry(AddJobContract addJobContract) {
        CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
        CountryService countryService = (CountryService) ServiceLocator.getInstance().getBean("countryService");
        CustVatRegistration custVatRegistration = customerService.getCustomerVatRegistrationsByCustCodeAndRegId(addJobContract.getJobContract().getCustCode(),
                                                                                                                addJobContract.getJobContract().getVatRegId());
        if (custVatRegistration != null)
            addJobContract.getJobContract().setVatRegCountry(countryService.getCountryByCode(custVatRegistration.getCustVatRegistrationId().getCountryCode()));
        return addJobContract;
    }

    AddJobContract calculateInvoiceLineItemsOnLoad(AddJobContract addJobContract, String chosenPrebillIds, Date jobFinishDate, Integer roundingDigits) {
        // List addedJobContractServices =
        // addJobContract.getAddJobContractServices().getAddedJobContractServices();
        double invoiceTotalAmt = 0;
        double vatTotalAmt = 0;
        double taxTotalAmt = 0;
        // Check if group tax calculation is needed

        String contractLvlTaxCode = addJobContract.getContractLvlTaxCode();
        String contractLvlVatCode = addJobContract.getContractLvlVatCode();
        double contractLvlTaxPct = addJobContract.getContractLvlTaxPct();
        double contractLvlVatPct = addJobContract.getContractLvlVatPct();
        String taxArticleCode = addJobContract.getTaxArticleCode();
        TaxArticle taxArticle = null;

        TaxService taxService = (TaxService) ServiceLocator.getInstance().getBean("taxService");

        if (contractLvlTaxCode != null && (!contractLvlTaxCode.trim().equals(""))) {

            TaxCode salesTaxCode = taxService.getTaxCodeByCode(contractLvlTaxCode);
            if (salesTaxCode != null) {
                contractLvlTaxPct = TaxUtil.getTaxPctByTaxCode(salesTaxCode.getTaxCodeHeader(), jobFinishDate);
                addJobContract.setContractLvlTaxPct(contractLvlTaxPct);
            }
        }
        if (contractLvlVatCode != null && (!contractLvlVatCode.trim().equals(""))) {

            TaxCode vatCode = taxService.getTaxCodeByCode(contractLvlVatCode);
            if (vatCode != null) {
                contractLvlVatPct = TaxUtil.getTaxPctByTaxCode(vatCode.getTaxCodeHeader(), jobFinishDate);
                addJobContract.setContractLvlVatPct(contractLvlVatPct);
            }
        }
        if (taxArticleCode != null && (!taxArticleCode.trim().equals(""))) {
            taxArticle = taxService.getTaxArticleByCode(taxArticleCode);
        }

        String[] prebillList = null;

        if (chosenPrebillIds != null && (!chosenPrebillIds.trim().equals(""))) {
            prebillList = chosenPrebillIds.split(";");
        }

        Set prebills = addJobContract.getJobContract().getPrebills();
        if (prebills != null) {
            Iterator prebillIterator = prebills.iterator();
            while (prebillIterator.hasNext()) {
                Prebill prebill = (Prebill) prebillIterator.next();
                if (taxArticle != null) {
                    prebill.setTaxArticle(taxArticle);
                }
                if (prebillList != null && prebillList.length > 0) {
                    for (int j = 0; j < prebillList.length; j++) {
                        if (prebill.getId() == new Long(prebillList[j]).longValue()) {
                            if (prebill.getTaxCode() == null || (prebill.getTaxCode().trim().equals(""))) {
                                prebill.setTaxCode(contractLvlTaxCode);
                                prebill.setTaxPct(contractLvlTaxPct);
                            }
                            if (prebill.getVatCode() == null || (prebill.getVatCode().trim().equals(""))) {
                                prebill.setVatCode(contractLvlVatCode);
                                prebill.setVatPct(contractLvlVatPct);
                            }
                        }
                    }
                }

                prebill = calculatePrebill(prebill, jobFinishDate, roundingDigits, addJobContract.getJobOrder().getBuName());
                invoiceTotalAmt = invoiceTotalAmt + prebill.getNetPrice();
                vatTotalAmt = vatTotalAmt + prebill.getVatAmt();
                taxTotalAmt = taxTotalAmt + prebill.getTaxAmt();
            }
        }
        addJobContract.getJobContract().setPrebills(prebills);
        if (roundingDigits != null)
            addJobContract.setInvoiceTotalAmt(NumberUtil.roundHalfUp(invoiceTotalAmt, roundingDigits));
        else
            addJobContract.setInvoiceTotalAmt(NumberUtil.roundHalfUp(invoiceTotalAmt, 2));
        if (addJobContract.getJobOrder().getBuName().equals("KOR01") && addJobContract.getJobContract().getTransactionCurrencyCd().equals("KRW")){
            addJobContract.setVatTotalAmt(NumberUtil.roundDown(vatTotalAmt, 0));
            addJobContract.setTaxTotalAmt(NumberUtil.roundDown(taxTotalAmt, 0));
        }else{
            addJobContract.setVatTotalAmt(NumberUtil.roundHalfUp(vatTotalAmt,  Constants.TAX_VAT_SCALE));
            addJobContract.setTaxTotalAmt(NumberUtil.roundHalfUp(taxTotalAmt,  Constants.TAX_VAT_SCALE));
        }
        return addJobContract;
    }

    private Prebill populateSplitAllocAmount(Prebill prebill, Integer roundingDigits) {
        double lineTotal = prebill.getNetPrice();
        double sumOfSplits = 0;
        double remainingAmount = lineTotal;
        double remainingPct = 100;

        if (prebill.getPrebillSplits() != null && prebill.getPrebillSplits().size() > 0) {
            Set prebillSplits = prebill.getPrebillSplits();
            Iterator iter = prebillSplits.iterator();
            while (iter.hasNext()) {
                PrebillSplit prebillSplit = (PrebillSplit) iter.next();
                if (prebillSplit.getPrimaryInd() != null && !prebillSplit.getPrimaryInd()) {

                    double splitAmount = lineTotal * (prebillSplit.getAllocPct() / 100);
                    splitAmount = NumberUtil.roundHalfUp(splitAmount, 3);
                    if (splitAmount > remainingAmount || (remainingPct == prebillSplit.getAllocPct()))
                        splitAmount = remainingAmount;
                    prebillSplit.setAllocAmt(splitAmount);

                    sumOfSplits = sumOfSplits + prebillSplit.getAllocAmt();
                    remainingAmount = remainingAmount - prebillSplit.getAllocAmt();
                    remainingPct = remainingPct - prebillSplit.getAllocPct();
                }
            }
            Iterator iterPrimary = prebillSplits.iterator();
            while (iterPrimary.hasNext()) {
                PrebillSplit prebillSplit = (PrebillSplit) iterPrimary.next();
                if (prebillSplit.getPrimaryInd() != null && prebillSplit.getPrimaryInd()) {
                    double primaryAmt = NumberUtil.roundHalfUp((lineTotal - sumOfSplits), roundingDigits);
                    prebillSplit.setAllocAmt(primaryAmt);
                }
                else
                    System.out.println("non primary alloc amt :" + prebillSplit.getAllocAmt());
            }
        }
        return prebill;
    }
    //START : 53791
    private void rearrangePrebillValues(AddJobOrder pAddJobOrder,PrebillService prebillService){
        int intMultplr = -1;
        int intSignIndex = Integer.parseInt(pAddJobOrder.getSignIndex());
        double invoiceTotalAmt = 0.0;
        
    	if(null != pAddJobOrder){
    		int intContractIndex = pAddJobOrder.getContractIndex();
    		AddJobContract addJobContract = null;    		
    		Set prebills = null;
    		Iterator prebillIterator = null;
    		Prebill prebill = null;
			 AddJobContract[] addJobContracts = pAddJobOrder.getAddJobContracts();
	         if (addJobContracts != null && addJobContracts.length > 0) {
	              addJobContract = addJobContracts[intContractIndex]; 
	              Integer roundingDigits = prebillService.getDecimalDigitsByCurrency(addJobContract.getJobContract().getTransactionCurrencyCd());
	              prebills = addJobContract.getJobContract().getPrebills();
	              prebillIterator = prebills.iterator();
	              int intIndx = 0;
	              while (prebillIterator.hasNext()) {
	                  prebill = (Prebill) prebillIterator.next();
	                  if(intIndx == intSignIndex){
	                	  prebill.setUnitPrice(prebill.getUnitPrice()*intMultplr);
	                	  prebill.setNetPrice(prebill.getNetPrice()*intMultplr);
	                  }
	                  invoiceTotalAmt = invoiceTotalAmt + prebill.getNetPrice();
	                  intIndx++;
	              }
	              
	              addJobContract.getJobContract().setPrebills(prebills);
	              addJobContract.setInvoiceTotalAmt(NumberUtil.roundHalfUp(invoiceTotalAmt, roundingDigits));	              
	              pAddJobOrder.setAddJobContracts(addJobContracts);
	         }	        
	    	prebillIterator = null;
	    	prebill = null;
    	}
    }
    //END: 53791    
}
