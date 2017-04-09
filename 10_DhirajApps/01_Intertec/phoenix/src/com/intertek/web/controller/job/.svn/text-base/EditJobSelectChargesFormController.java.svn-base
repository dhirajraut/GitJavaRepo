package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.command.CalculateTotalCommand;
import com.intertek.command.DeleteChargesCommand;
import com.intertek.command.PopulatePrebillInfoCommand;
import com.intertek.command.RecalculateInspectionCommand;
import com.intertek.command.RecheckServiceCommand;
import com.intertek.command.SortCommand;
import com.intertek.command.SplitCommand;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractServices;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.domain.JobSearch;
import com.intertek.entity.BranchCode;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contract;
import com.intertek.entity.Customer;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractService;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobVessel;
import com.intertek.entity.PriceBook;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.service.ContractService;
import com.intertek.service.JobContractResultService;
import com.intertek.service.JobContractSrc;
import com.intertek.service.JobService;
import com.intertek.service.JobVesselService;
import com.intertek.servicetype.JobServiceType;
import com.intertek.servicetype.ServiceTypeManager;
import com.intertek.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.InspectionServiceUtil;
import com.intertek.util.JobContractProductServiceUtil;
import com.intertek.util.JobContractServiceUtil;
import com.intertek.util.JobContractVesselServiceUtil;
import com.intertek.util.JobUtil;
import com.intertek.util.ServiceUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.calculator.CalculatorUtil;

public class EditJobSelectChargesFormController extends BaseSimpleFormController
{
	public EditJobSelectChargesFormController() {
		super();

		setCommandClass(AddJobOrder.class);
		setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(
			HttpServletRequest request,
			HttpServletResponse response,
			Object command,
			BindException errors
	) throws Exception
	{
		AddJobOrder addJobOrder = (AddJobOrder)command;

		request.setAttribute("warning_msg", request.getParameter("messageKey"));

		// start of scroll bar handling
		String scrollflag=request.getParameter("scrollFlag");   
		if(scrollflag!= null  && !scrollflag.trim().equals(""))    
		{addJobOrder.setScrollFlag(scrollflag);}
		// End  of scroll bar handling

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
		if(nextList != null && nextList.trim().equals("next"))
		{ 	
			JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	    	JobOrder jobOrder = addJobOrder.getJobOrder();
			jobNumber=addJobOrder.getJobOrder().getJobNumber();
			try
			{
				if(request.getSession() != null)
				{
					JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
					jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,nextList);
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
			String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
			return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

		}
		//String prevList=request.getParameter("prevListFlag");
		String prevList=request.getParameter("showPrevListFlag");
		if(prevList != null && prevList.trim().equals("prev"))
		{
			JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
			JobOrder jobOrder = addJobOrder.getJobOrder();
			jobNumber=jobOrder.getJobNumber();
			System.out.println("CURR JOB NUMBER: SC :--- " + jobNumber);
			try
			{
				if(request.getSession() != null)
				{
					JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
					jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,prevList);
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
			String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
			return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

		}    
	    // END : #119240
		
		
		String warnUserFlag = addJobOrder.getWarnUserFlag();

		if((warnUserFlag != null) && "warn".equals(warnUserFlag))
		{
			addJobOrder.setWarnUserFlag("warned");
			errors.reject("unsaved.changes.save.error", new Object[] {}, null);
			return showForm(request, response, errors);
		}

		if((warnUserFlag != null) && "navigate".equals(warnUserFlag))
		{
			String navigationUrl = addJobOrder.getNavigationUrl();
			if(navigationUrl != null)
			{
				return new ModelAndView(new RedirectView(navigationUrl));
			}
			else
				return showForm(request, response, errors);
		}

		String refreshing = request.getParameter("refreshing");

		try
		{
			int contractIndex = 0;
			try
			{
				contractIndex = new Integer(request.getParameter("contractIndex")).intValue();
			}
			catch(Exception e)
			{
			}
			addJobOrder.setContractIndex(contractIndex);

			String operationType = request.getParameter("operationType");
			boolean splitted = false;
			if("split".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);
				ServiceUtil.applyCommand(addJobContract, new SplitCommand(true));
				splitted = true;
			}
			else if("sort".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);
				ServiceUtil.applyCommand(addJobContract, new SortCommand());
			}
			else if("delete".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);

				DeleteChargesCommand deleteChargeCommand = new DeleteChargesCommand();
				ServiceUtil.applyCommand(addJobContract, deleteChargeCommand);
			}

			if((refreshing != null) && "true".equals(refreshing))
			{
				ServiceUtil.applyCommand(addJobOrder, new RecheckServiceCommand());

				if(!splitted)
				{
					AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
							addJobOrder,
							request.getParameter("contractIndex")
					);
					ServiceUtil.applyCommand(addJobContract, new SplitCommand(true));
				}

				ServiceUtil.applyCommand(addJobOrder, new CalculateTotalCommand());

				return showForm(request, response, errors);
			}

			JobContractResultService jobContractResultService = (JobContractResultService)ServiceLocator.getInstance().getBean("jobContractResultService");

			if("deleteCharge".equals(operationType))
			{
				Integer selectedChargeIndex = null;
				try
				{
					selectedChargeIndex = new Integer(request.getParameter("selectedChargeIndex"));
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

				if((selectedChargeIndex == null) || (selectedChargeIndex < 0))
				{
					errors.reject("edit.job.select.charge.error.delete", new Object[] {selectedChargeIndex}, null);
					return showForm(request, response, errors);
				}

				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);
				AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
						addJobContract,
						request.getParameter("selectedJobContractVesselIndex")
				);
				AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
						addJobContractVessel,
						request.getParameter("selectedJobContractProdIndex")
				);

				if(addJobContract == null) return showForm(request, response, errors);

				String selectedChargeType = request.getParameter("selectedChargeType");
				if("JobContractService".equals(selectedChargeType))
				{
					JobContractServiceUtil.removeJobContractServiceByIndex(addJobContract, selectedChargeIndex);
				}
				else if("JobContractVesselService".equals(selectedChargeType))
				{
					if(addJobContractVessel != null)
					{
						JobContractVesselServiceUtil.removeJobContractVesselServiceByIndex(addJobContractVessel, selectedChargeIndex);
					}
				}
				else if("JobContractProductService".equals(selectedChargeType))
				{
					if((addJobContractVessel != null) && (addJobContractProd != null))
					{
						JobContractProductServiceUtil.removeJobContractProductServiceByIndex(addJobContractProd, selectedChargeIndex);
					}
				}
				else if("JobContractTest".equals(selectedChargeType))
				{
					if((addJobContractVessel != null) && (addJobContractProd != null))
					{
						JobUtil.removeJobContractTestByIndex(addJobContractProd, selectedChargeIndex);
					}
				}
				else if("JobContractManualTest".equals(selectedChargeType))
				{
					if((addJobContractVessel != null) && (addJobContractProd != null))
					{
						JobUtil.removeJobContractManualTestByIndex(addJobContractProd, selectedChargeIndex);
					}
				}
				else if("JobContractSlate".equals(selectedChargeType))
				{
					if((addJobContractVessel != null) && (addJobContractProd != null))
					{
						JobUtil.removeJobContractSlateByIndex(addJobContractProd, selectedChargeIndex);
					}
				}
				else if("JobContractProductInspection".equals(selectedChargeType))
				{
					if((addJobContractVessel != null) && (addJobContractProd != null))
					{
						InspectionServiceUtil.removeJobContractProductInspectionResultByIndex(addJobContractProd, selectedChargeIndex);

						ServiceUtil.applyCommand(addJobContractVessel, addJobContract, new RecalculateInspectionCommand());
						ServiceUtil.applyCommand(addJobContract, new RecheckServiceCommand());
					}
				}

				ServiceUtil.applyCommand(addJobContract, new CalculateTotalCommand());

				// should add a message here.
				return showForm(request, response, errors);
			}
			else if("refreshFromJob".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);
				if(addJobContract != null)
				{
					// remove all the job contract vessels
					addJobContract.getJobContract().getJobContractVessels().clear();
					addJobContract.getJobContract().getResults().clear();

					copyJobVessels(addJobOrder, addJobContract);
				}

				ServiceUtil.applyCommand(addJobContract, new SortCommand());
				ServiceUtil.applyCommand(addJobContract, new CalculateTotalCommand());

				// should add a message here.
				return showForm(request, response, errors);
			}
			else if("addVessel".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);

				if(addJobContract == null) return showForm(request, response, errors);

				JobContractVessel jobContractVessel = new JobContractVessel();
				jobContractVessel.setJobContract(addJobContract.getJobContract());
				addJobContract.getJobContract().getJobContractVessels().add(jobContractVessel);

				AddJobContractVessel addJobContractVessel = JobUtil.createAddJobContractVessel(jobContractVessel);

				JobUtil.addAddJobContractVesselToAddJobContract(addJobContract, addJobContractVessel);

				return showForm(request, response, errors);
			}
			else if("deleteVessel".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);

				if(addJobContract == null) return showForm(request, response, errors);

				AddJobContractVessel removedAddJobContractVessel = JobUtil.removeAddJobContractVesselFromAddJobContractByIndex(
						addJobContract,
						request.getParameter("selectedJobContractVesselIndex")
				);
				if(removedAddJobContractVessel != null)
				{
					addJobContract.getJobContract().getJobContractVessels().remove(removedAddJobContractVessel.getJobContractVessel());

					ServiceUtil.applyCommand(null, addJobContract, new RecalculateInspectionCommand());
					ServiceUtil.applyCommand(addJobContract, new RecheckServiceCommand());
				}

				ServiceUtil.applyCommand(addJobContract, new CalculateTotalCommand());
				return showForm(request, response, errors);
			}
			else if("addProduct".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);
				AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
						addJobContract,
						request.getParameter("selectedJobContractVesselIndex")
				);

				if((addJobContract == null) || (addJobContractVessel == null)) return showForm(request, response, errors);

				JobContractProd jobContractProd = new JobContractProd();
				jobContractProd.setJobContractVessel(addJobContractVessel.getJobContractVessel());
				jobContractProd.setJobNumber(addJobContractVessel.getJobContractVessel().getJobNumber());
				jobContractProd.setLinkedVslRow(addJobContractVessel.getJobContractVessel().getLinkedVslRow());
				addJobContractVessel.getJobContractVessel().getJobContractProds().add(jobContractProd);


				AddJobContractProd addJobContractProd = JobUtil.createAddJobContractProd(jobContractProd);

				JobUtil.addAddJobContractProdToAddJobContractVessel(addJobContractVessel, addJobContractProd);

				return showForm(request, response, errors);
			}
			else if("deleteProduct".equals(operationType))
			{
				AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
						addJobOrder,
						request.getParameter("contractIndex")
				);
				AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
						addJobContract,
						request.getParameter("selectedJobContractVesselIndex")
				);

				if((addJobContract == null) || (addJobContractVessel == null)) return showForm(request, response, errors);

				AddJobContractProd removedAddJobContractProd = JobUtil.removeAddJobContractProdFromAddJobContractVesselByIndex(
						addJobContractVessel,
						request.getParameter("selectedJobContractProdIndex")
				);
				if(removedAddJobContractProd != null)
				{
					addJobContractVessel.getJobContractVessel().getJobContractProds().remove(removedAddJobContractProd.getJobContractProd());

					ServiceUtil.applyCommand(addJobContractVessel, addJobContract, new RecalculateInspectionCommand());
					ServiceUtil.applyCommand(addJobContract, new RecheckServiceCommand());
				}

				ServiceUtil.applyCommand(addJobContract, new CalculateTotalCommand());
				return showForm(request, response, errors);
			}
			else
			{
				ServiceUtil.applyCommand(addJobOrder, new SplitCommand(false));
				ServiceUtil.applyCommand(addJobOrder, new SortCommand());
				ServiceUtil.applyCommand(addJobOrder, new PopulatePrebillInfoCommand());
				try
				{
					jobContractResultService.saveJobContractResults(
							addJobOrder.getAddJobContracts()
					);

					//For breadcrumbs
					if(addJobOrder.getJobOrder().getPageNumber() != null && addJobOrder.getJobOrder().getPageNumber().intValue() < 3)
					{
						addJobOrder.getJobOrder().setPageNumber(Integer.valueOf(3));
						JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
						jobService.updateJobOrder(addJobOrder.getJobOrder());
					}
				}
				catch(ServiceException e)
				{
					e.printStackTrace();
					errors.reject(e.getMessage(), e.getParams(), null);
					return showForm(request, response, errors);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					errors.reject("edit.job.select.charge.error.save", new Object[] {e.getMessage()}, null);
					return showForm(request, response, errors);
				}
			}
		} catch(ServiceException e)
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

		if("next".equals(refreshing))
		{
			// Added on 10/02/09
			JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
			JobContractSrc jobContractSrc = (JobContractSrc)ServiceLocator.getInstance().getBean("jobContractSrc");
			CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
			JobContractResultService jobContractResultService = (JobContractResultService)ServiceLocator.getInstance().getBean("jobContractResultService");

			JobOrder jobOrder = jobService.getJobOrderByJobNumberWithJobContracts(addJobOrder.getJobOrder().getJobNumber());
			List jobContractList = new ArrayList(jobOrder.getJobContracts());
			JobUtil.sortJobContractsById(jobContractList);
			List addJobContractList = new ArrayList();
			for(int i=0; i<jobContractList.size(); i++)
			{
				JobContract oldJobContract = (JobContract)jobContractList.get(i);
				JobContract jobContract = jobContractSrc.getJobContractByJobContractIdWithDetail(oldJobContract.getId());
				if(jobContract == null) continue;
				jobContract.setJobOrder(jobOrder);

				CfgContract contract = calculatorService.getContractByContractId(
						jobContract.getContractCode(),
						jobOrder.getJobFinishDate()
				);
				if(contract != null)
				{
					String priceBookId = contract.getPriceBookId();
					if(Constants.CURRENT.equals(priceBookId))
					{
						PriceBook pb = calculatorService.getCurrentPriceBook(
								contract.getPbSeries(),
								contract.getCurrencyCD(),
								jobOrder.getJobFinishDate()
						);
						if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
					}

					AddJobContract addJobContract = new AddJobContract();
					addJobContract.setJobContract(jobContract);
					addJobContract.setCfgContract(contract);
					if(jobContract.getJobOrder() != null && jobContract.getJobOrder().getBranch()!=null && jobContract.getJobOrder().getBranch().getBusinessUnit()!=null)
						jobContract.setBaseCurrencyCd(jobContract.getJobOrder().getBranch().getBusinessUnit().getCurrencyBase());
					addJobContractList.add(addJobContract);

					List vesselTypes = calculatorService.getVesselTypes(
							addJobContract.getCfgContract().getVesselSet(),
							addJobContract.getJobContract().getContractCode(),
							addJobContract.getCfgContract().getPriceBookId(),
							addJobContract.getJobContract().getZone(),
							DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
							addJobContract.getJobContract().getLanguage()
					);
					addJobContract.setVesselTypes(vesselTypes);

					addJobContract.getResults().addAll(jobContract.getResults());
					addJobContract.setSelects(
							CommonUtil.createBooleans(addJobContract.getResults().size())
					);

					if(jobContract.getJobContractStatus() == null || jobContract.getJobContractStatus().trim().equals(""))
						jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);

					if(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_INVOICED_STATUS)
							|| jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_CREDITED_STATUS)
							|| !"APP".equals(contract.getContractStatus())
					)
					{
						addJobContract.setContractViewOnly(true);
					}
					else
					{
						addJobContract.setContractViewOnly(false);
					}

					double currencyMultiplier = CalculatorUtil.getCurrencyMultiplier(
							contract.getCurrencyCD(),
							jobContract.getTransactionCurrencyCd(),
							jobOrder.getJobFinishDate(),
							contract.getOverridable(),
							jobContract.getOverrideCurrRate()
					);
					if(currencyMultiplier != 0) currencyMultiplier = 1.0 / currencyMultiplier;

					//setting baseCurrency Mult and Div in jobContract
					HashMap<String, Double> baseCurrencyRateMap = calculatorService.getCurrencyRateMap(jobContract.getTransactionCurrencyCd(), jobContract.getBaseCurrencyCd(), jobOrder.getJobFinishDate());
					Double rateMult = baseCurrencyRateMap.get("RateMult");
					Double rateDiv = baseCurrencyRateMap.get("RateDiv");
					if(rateMult != null && rateDiv != null){
						jobContract.setRateMult(rateMult);
						jobContract.setRateDiv(rateDiv);
					}

					addJobContract.setCurrencyMultiplier(currencyMultiplier);


					// load job service info
					List services = calculatorService.getServices(
							jobContract.getContractCode(), //contract id
							contract.getPriceBookId(),
							DateUtil.formatDate(jobOrder.getJobFinishDate(), "yyyyMMdd"), //"20071121", // nomination date
							addJobContract.getJobContract().getLanguage() // language
					);

					AddJobContractServices addJobContractServices = new AddJobContractServices();

					//List tmpServiceExtList = new ArrayList();
					Iterator it = jobContract.getJobContractServices().iterator();
					while(it.hasNext())
					{
						JobContractService jobContractService = (JobContractService)it.next();
						JobContractServiceExt jobContractServiceExt = new JobContractServiceExt();
						jobContractServiceExt.setService(jobContractService);
						jobContractServiceExt.getResults().addAll(jobContractService.getResults());
						jobContractServiceExt.setSelects(
								CommonUtil.createBooleans(jobContractServiceExt.getResults().size())
						);

						addJobContractServices.getAddedJobContractServices().add(jobContractServiceExt);
						//tmpServiceExtList.add(jobContractServiceExt);
					}
					//JobContractServiceExt[] serviceExts = (JobContractServiceExt[])tmpServiceExtList.toArray(new JobContractServiceExt[0]);
					//addJobContractServices.setServiceExts(

					JobServiceType jobServiceType = ServiceTypeManager.getJobServiceType(
							jobOrder.getJobType(),
							services
					);
					addJobContractServices.setJobServiceType(jobServiceType);

					addJobContract.setAddJobContractServices(addJobContractServices);

					Iterator vesselItr = jobContract.getJobContractVessels().iterator();
					AddJobContractVessel[] addJobContractVessels = new AddJobContractVessel[jobContract.getJobContractVessels().size()];
					int count = 0;
					while(vesselItr.hasNext())
					{
						JobContractVessel jobContractVessel = (JobContractVessel)vesselItr.next();

						AddJobContractVessel addJobContractVessel = JobUtil.createAddJobContractVessel(jobContractVessel);
						addJobContractVessels[count] = addJobContractVessel;

						List productGroups = calculatorService.getProductGroups(
								addJobContract.getCfgContract().getProductGroupSet(),
								addJobContract.getJobContract().getContractCode(),
								addJobContract.getCfgContract().getPriceBookId(),
								addJobContractVessel.getJobContractVessel().getType(),
								new Integer(addJobContract.getCfgContract().getUom()),
								addJobContract.getJobContract().getZone(),
								DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
								addJobContract.getJobContract().getLanguage() // language
						);
						addJobContractVessel.setProductGroups(productGroups);

						count ++;
					}
					addJobContract.setAddJobContractVessels(addJobContractVessels);

					// first time, copy vessel
					if(addJobContractVessels.length == 0)
					{
						copyJobVessels(addJobOrder, addJobContract);
					}
					ServiceUtil.applyCommand(addJobContract, new SortCommand());
				}
			}
			AddJobContract[] addJobContracts = (AddJobContract[])addJobContractList.toArray(new AddJobContract[0]);
			addJobOrder.setAddJobContracts(null);
			addJobOrder.setAddJobContracts(addJobContracts);
			ServiceUtil.applyCommand(addJobOrder, new SplitCommand(false));
			ServiceUtil.applyCommand(addJobOrder, new SortCommand());
			ServiceUtil.applyCommand(addJobOrder, new PopulatePrebillInfoCommand());
			try
			{
				jobContractResultService.saveJobContractResults(
						addJobOrder.getAddJobContracts()
				);
			}
			catch(ServiceException e)
			{
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				errors.reject("edit.job.select.charge.error.save", new Object[] {e.getMessage()}, null);
				return showForm(request, response, errors);
			}
			//End
			ModelAndView modelAndView = new ModelAndView("edit-job-invoice-preview-r");
			modelAndView.addObject("jobNumber", addJobOrder.getJobOrder().getJobNumber());
			modelAndView.addObject("contractIndex", addJobOrder.getContractIndex());

			return modelAndView;
		}
		else
		{
			ModelAndView modelAndView = new ModelAndView("edit-job-select-charges-r");
			modelAndView.addObject("jobNumber", addJobOrder.getJobOrder().getJobNumber());
			modelAndView.addObject("contractIndex", addJobOrder.getContractIndex());
			modelAndView.addObject("warnUserFlag", addJobOrder.getWarnUserFlag());

			return modelAndView;
		}
	}

	private boolean setContractInProgress(String contractCode,
			AddJobContract addJobContract) {
	    boolean isInProgress = false;
		ContractService contractService = (ContractService) ServiceLocator.getInstance().getBean("contractService");
		Contract contract = contractService.getContractByContractCode(contractCode);
		if (contract != null){
			isInProgress = Constants.INPROCESS.equalsIgnoreCase(contract.getStatus());
            addJobContract.setContractInProgress(isInProgress);
		}
		return isInProgress;
	}

	protected boolean suppressValidation(HttpServletRequest request, Object command)
	{
		String refreshing = request.getParameter("refreshing");
		if((refreshing != null) && "true".equals(refreshing))
		{
			String operationType = request.getParameter("operationType");
			if("split".equals(operationType))
			{
				return super.suppressValidation(request, command);
			}

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

		return super.suppressValidation(request, command);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(
			HttpServletRequest request,
			ServletRequestDataBinder binder
	) throws Exception
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
	}

	protected ModelAndView showForm(
			HttpServletRequest request,
			HttpServletResponse response,
			BindException errors
	) throws Exception
	{
		Object errorMsg = request.getAttribute("error_msg");
		if(errorMsg != null)
		{
			request.getRequestDispatcher("/edit_job_operational_info_insp.htm").forward(request, response);
			return null;
		}

		return super.showForm(request, response, errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(
			HttpServletRequest request
	) throws Exception
	{
		AddJobOrder addJobOrder = new AddJobOrder();

		JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
		JobContractSrc jobContractSrc = (JobContractSrc)ServiceLocator.getInstance().getBean("jobContractSrc");
		CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
		JobContractResultService jobContractResultService = (JobContractResultService)ServiceLocator.getInstance().getBean("jobContractResultService");
		try {
			String jobNumber = request.getParameter("jobNumber");
			JobOrder jobOrder = jobService.getJobOrderByJobNumberWithJobContracts(jobNumber);
			if(jobOrder == null)
			{
				jobOrder = new JobOrder();
			}

			addJobOrder.setJobOrder(jobOrder);

			String warnUserFlag = request.getParameter("warnUserFlag");

			if(warnUserFlag == null)
				warnUserFlag = "";

			addJobOrder.setWarnUserFlag(warnUserFlag);

			BranchCode branchCode = calculatorService.getBranchCodeByBranchCode(jobOrder.getBranchName());
			if(branchCode == null) {
				request.setAttribute("error_msg", "The_branch_code_selected_is_not_enabled_for_Select_Charges");
				return addJobOrder;
			}

			Date jobFinishDate = jobOrder.getJobFinishDate();
			if(jobFinishDate == null) {
				request.setAttribute("error_msg", "select_change_page_need_job_finish_date");
				return addJobOrder;
			}

			List jobContractList = new ArrayList(jobOrder.getJobContracts());
			JobUtil.sortJobContractsById(jobContractList);
			for(int i=0; i<jobContractList.size(); i++)
			{
				JobContract oldJobContract = (JobContract)jobContractList.get(i);

				if(oldJobContract.getJobContractStatus().trim().equals(Constants.JOBCON_INVOICED_STATUS) || oldJobContract.getJobContractStatus().trim().equals(Constants.JOBCON_CREDITED_STATUS)) continue;

				if(Constants.None.equalsIgnoreCase(oldJobContract.getZone()))
				{
					/*For iTrack Issue#131341- START if the JFD date is prior to the zone created date then ignoring validation*/
					String dt = DateUtil.formatDate(oldJobContract.getJobOrder().getJobFinishDate(),"dd-MMM-yyyy");
					List branchLocs = jobService.getZoneByContractCode(oldJobContract.getContractCode(),null,dt);
					List portLocs = jobService.getPortCodeByContractCode(oldJobContract.getContractCode(),null,dt);
					if((branchLocs != null && branchLocs.size() > 0)&&(portLocs != null && portLocs.size() > 0)){
						request.setAttribute("error_msg", "You_must_enter_a_Zone_Description_to_continue");
						return addJobOrder;
					}
					/*For iTrack Issue#131341- END */
					//Commented due to iTrack Issue#131341
					/*request.setAttribute("error_msg", "You_must_enter_a_Zone_Description_to_continue");
					return addJobOrder;*/
				}

				Customer customer = oldJobContract.getCustomer();
				Boolean creditApproved = customer != null ? customer.getCreditApproved() : null;
				if((creditApproved == null) || (creditApproved.booleanValue() == false))
				{
					request.setAttribute("error_msg", "Customer_credit_has_not_been_approved");
					return addJobOrder;
				}
			}

			List addJobContractList = new ArrayList();
			for(int i=0; i<jobContractList.size(); i++)
			{
				JobContract oldJobContract = (JobContract)jobContractList.get(i);
				JobContract jobContract = jobContractSrc.getJobContractByJobContractIdWithDetail(oldJobContract.getId());
				if(jobContract == null){
					continue;
				}
				jobContract.setJobOrder(jobOrder);

				CfgContract contract = calculatorService.getContractByContractId(
						jobContract.getContractCode(),
						jobOrder.getJobFinishDate()
				);
				if(contract != null)
				{
					String priceBookId = contract.getPriceBookId();
					if(Constants.CURRENT.equals(priceBookId))
					{
						PriceBook pb = calculatorService.getCurrentPriceBook(
								contract.getPbSeries(),
								contract.getCurrencyCD(),
								jobOrder.getJobFinishDate()
						);
						if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
					}

					AddJobContract addJobContract = new AddJobContract();
					addJobContract.setJobContract(jobContract);
					boolean inProgress = setContractInProgress(jobContract.getContractCode(), addJobContract);
					if (inProgress){
					    String existingWarning = (String) request.getAttribute("warning_msg");
					    request.setAttribute("warning_msg",
					                         (existingWarning != null && existingWarning.length() > 0) ?
					                         existingWarning.concat("<br/> Contract " + jobContract.getContractCode() + " is In Progress!")
					                         : "Contract " + jobContract.getContractCode() + " is In Progress!");
					}
					addJobContract.setCfgContract(contract);
					if(addJobContract.getJobOrder() != null && jobContract.getJobOrder().getBranch()!=null && addJobContract.getJobOrder().getBranch().getBusinessUnit()!=null)
						jobContract.setBaseCurrencyCd(jobContract.getJobOrder().getBranch().getBusinessUnit().getCurrencyBase());
					addJobContractList.add(addJobContract);

					List vesselTypes = calculatorService.getVesselTypes(
							addJobContract.getCfgContract().getVesselSet(),
							addJobContract.getJobContract().getContractCode(),
							addJobContract.getCfgContract().getPriceBookId(),
							addJobContract.getJobContract().getZone(),
							DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
							addJobContract.getJobContract().getLanguage()
					);
					addJobContract.setVesselTypes(vesselTypes);

					addJobContract.getResults().addAll(jobContract.getResults());
					addJobContract.setSelects(
							CommonUtil.createBooleans(addJobContract.getResults().size())
					);

					if(jobContract.getJobContractStatus() == null || jobContract.getJobContractStatus().trim().equals(""))
						jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);

					if(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_INVOICED_STATUS)
							|| jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_CREDITED_STATUS)
							|| !"APP".equals(contract.getContractStatus())
					)
					{
						addJobContract.setContractViewOnly(true);
					}
					else
					{
						addJobContract.setContractViewOnly(false);
					}

					double currencyMultiplier = CalculatorUtil.getCurrencyMultiplier(
							contract.getCurrencyCD(),
							jobContract.getTransactionCurrencyCd(),
							jobOrder.getJobFinishDate(),
							contract.getOverridable(),
							jobContract.getOverrideCurrRate()
					);
					if(currencyMultiplier != 0) currencyMultiplier = 1.0 / currencyMultiplier;
					addJobContract.setCurrencyMultiplier(currencyMultiplier);

					//setting baseCurrency Mult and Div in jobContract
					HashMap<String, Double> baseCurrencyRateMap = calculatorService.getCurrencyRateMap(jobContract.getTransactionCurrencyCd(), jobContract.getBaseCurrencyCd(), jobOrder.getJobFinishDate());
					Double rateMult = baseCurrencyRateMap.get("RateMult");
					Double rateDiv = baseCurrencyRateMap.get("RateDiv");
					if(rateMult != null && rateDiv != null){
						jobContract.setRateMult(rateMult);
						jobContract.setRateDiv(rateDiv);
					}

					// load job service info
					List services = calculatorService.getServices(
							jobContract.getContractCode(), //contract id
							contract.getPriceBookId(),
							DateUtil.formatDate(jobOrder.getJobFinishDate(), "yyyyMMdd"), //"20071121", // nomination date
							addJobContract.getJobContract().getLanguage() // language
					);

					AddJobContractServices addJobContractServices = new AddJobContractServices();

					//List tmpServiceExtList = new ArrayList();
					Iterator it = jobContract.getJobContractServices().iterator();
					while(it.hasNext())
					{
						JobContractService jobContractService = (JobContractService)it.next();
						JobContractServiceExt jobContractServiceExt = new JobContractServiceExt();
						jobContractServiceExt.setService(jobContractService);
						jobContractServiceExt.getResults().addAll(jobContractService.getResults());
						jobContractServiceExt.setSelects(
								CommonUtil.createBooleans(jobContractServiceExt.getResults().size())
						);

						addJobContractServices.getAddedJobContractServices().add(jobContractServiceExt);
						//tmpServiceExtList.add(jobContractServiceExt);
					}
					//JobContractServiceExt[] serviceExts = (JobContractServiceExt[])tmpServiceExtList.toArray(new JobContractServiceExt[0]);
					//addJobContractServices.setServiceExts(

					JobServiceType jobServiceType = ServiceTypeManager.getJobServiceType(
							jobOrder.getJobType(),
							services
					);
					addJobContractServices.setJobServiceType(jobServiceType);

					addJobContract.setAddJobContractServices(addJobContractServices);

					Iterator vesselItr = jobContract.getJobContractVessels().iterator();
					AddJobContractVessel[] addJobContractVessels = new AddJobContractVessel[jobContract.getJobContractVessels().size()];
					int count = 0;
					while(vesselItr.hasNext())
					{
						JobContractVessel jobContractVessel = (JobContractVessel)vesselItr.next();

						AddJobContractVessel addJobContractVessel = JobUtil.createAddJobContractVessel(jobContractVessel);
						addJobContractVessels[count] = addJobContractVessel;

						List productGroups = calculatorService.getProductGroups(
								addJobContract.getCfgContract().getProductGroupSet(),
								addJobContract.getJobContract().getContractCode(),
								addJobContract.getCfgContract().getPriceBookId(),
								addJobContractVessel.getJobContractVessel().getType(),
								new Integer(addJobContract.getCfgContract().getUom()),
								addJobContract.getJobContract().getZone(),
								DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
								addJobContract.getJobContract().getLanguage() // language
						);
						addJobContractVessel.setProductGroups(productGroups);

						count ++;
					}
					addJobContract.setAddJobContractVessels(addJobContractVessels);

					// first time, copy vessel
					if(addJobContractVessels.length == 0)
					{
						copyJobVessels(addJobOrder, addJobContract);
					}
					ServiceUtil.applyCommand(addJobContract, new SortCommand());
				}
			}
			AddJobContract[] addJobContracts = (AddJobContract[])addJobContractList.toArray(new AddJobContract[0]);
			addJobOrder.setAddJobContracts(addJobContracts);

			if((addJobContracts == null) || (addJobContracts.length == 0))
			{
				request.setAttribute("warning_msg", "No_valid_contract_for_the_Job_Finish_Date");
			}

			ServiceUtil.applyCommand(addJobOrder, new CalculateTotalCommand());

			int contractIndex = 0;
			try
			{
				contractIndex = new Integer(request.getParameter("contractIndex")).intValue();
			}
			catch(Exception e)
			{
			}
			addJobOrder.setContractIndex(contractIndex);
		
			
			
		 // START : #119240
		   addJobOrder.setJobNumber(jobNumber);
	       addJobOrder.setCurrPageIdentifier(Constants.JOB_SELECT_CHARGES);
	      
	       // Setting next list and prev list values
	   		if(request.getSession() != null)
	   		{
	   			JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
	   			
	   			String orginFromJobSearch =  (String)request.getSession().getAttribute("orginFromJobSearch");
	   			JobUtil.setPrevNextJobFlags(addJobOrder, jobSearch, jobOrder, orginFromJobSearch);
	   		}
	   		//end
			
	     // END : #119240  
			
		} catch(ServiceException e)
		{
			throw new ServiceException(e.getMessage(), e.getParams(), e);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		}
		return addJobOrder;
	}

	private void copyJobVessels(AddJobOrder addJobOrder, AddJobContract addJobContract)
	{
		CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
		List jobVessels = addJobOrder.getJobVessels();
		if(jobVessels == null)
		{
			JobVesselService jobVesselService = (JobVesselService)ServiceLocator.getInstance().getBean("jobVesselService");
			jobVessels = jobVesselService.getJobVesselsByJobNumberWithDetail(
					addJobOrder.getJobOrder().getJobNumber()
			);
			addJobOrder.setJobVessels(jobVessels);
			for(int i=0; i<jobVessels.size(); i++)
			{
				JobVessel jobVessel = (JobVessel)jobVessels.get(i);
				jobVessel.setJobOrder(addJobOrder.getJobOrder());
			}
		}

		JobUtil.copyJobVesselsToJobContract(
				jobVessels,
				addJobContract.getJobContract()
		);

		Iterator vesselItr = addJobContract.getJobContract().getJobContractVessels().iterator();
		AddJobContractVessel[] addJobContractVessels =
			new AddJobContractVessel[addJobContract.getJobContract().getJobContractVessels().size()];

		int count = 0;
		while(vesselItr.hasNext())
		{
			JobContractVessel jobContractVessel = (JobContractVessel)vesselItr.next();

			AddJobContractVessel addJobContractVessel = JobUtil.createAddJobContractVessel(jobContractVessel);
			addJobContractVessels[count] = addJobContractVessel;

			List productGroups = calculatorService.getProductGroups(
					addJobContract.getCfgContract().getProductGroupSet(),
					addJobContract.getJobContract().getContractCode(),
					addJobContract.getCfgContract().getPriceBookId(),
					addJobContractVessel.getJobContractVessel().getType(),
					new Integer(addJobContract.getCfgContract().getUom()),
					addJobContract.getJobContract().getZone(),
					DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
					addJobContract.getJobContract().getLanguage() // language
			);
			addJobContractVessel.setProductGroups(productGroups);

			count ++;
		}
		addJobContract.setAddJobContractVessels(addJobContractVessels);
	}
}
