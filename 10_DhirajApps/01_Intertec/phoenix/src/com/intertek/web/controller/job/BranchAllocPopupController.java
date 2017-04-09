package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddPrebill;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.JobContract;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;
import com.intertek.service.PrebillService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.JobUtil;
import com.intertek.util.NumberUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.I18nUtil;

public class BranchAllocPopupController extends SimpleFormController {
  private static Log log = LogFactory.getLog(BranchAllocPopupController.class);
  
  public BranchAllocPopupController() {
    super();
    setCommandClass(AddPrebill.class);
    setSessionForm(true);
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
   *      javax.servlet.http.HttpServletResponse, java.lang.Object,
   *      org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(HttpServletRequest request,
      HttpServletResponse response, Object command, BindException errors)
      throws Exception {

    AddPrebill addPrebill = (AddPrebill) command;
    //Prebill prebill = addPrebill.getPrebill();

      HttpSession session = request.getSession();
      AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobInvoicePreviewFormController.FORM.command");

    String addOrDeletePrebillSplit = addPrebill.getAddOrDeletePrebillSplit();
    String updatePctAllocFlag = addPrebill.getUpdatePctAllocFlag();
    String updateBranchDescFlag = addPrebill.getUpdateBranchDescFlag();
    System.out.println("index from request :"+request.getParameter("prebillSplitIndex"));
    int prebillSplitIndex = Integer.parseInt(request.getParameter("prebillSplitIndex"));


    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    Prebill[] allocPrebills = addPrebill.getAllocPrebills();


    if((addOrDeletePrebillSplit != null) && ("add".equals(addOrDeletePrebillSplit) || "delete".equals(addOrDeletePrebillSplit)))
    {
     if("add".equals(addOrDeletePrebillSplit))
     {
       System.out.println("in add or delete prebill split ");
       //rearrange % allocation
       addPrebill.setPrebillSplits(rearrangePercentageAlloc(addPrebill.getPrebillSplits(),addPrebill.getPrebill()));
       addPrebill.setPrebillSplits(addPrebillSplit(addPrebill.getPrebillSplits(),addPrebill.getPrebill()));
       System.out.println("size of prebillsplit :"+addPrebill.getPrebillSplits().length);
     }
     else
     {
       addPrebill.setPrebillSplits(deletePrebillSplit(addPrebill.getPrebillSplits(),prebillSplitIndex));
       addPrebill.setPrebillSplits(rearrangePercentageAlloc(addPrebill.getPrebillSplits(),addPrebill.getPrebill()));
     }
     addPrebill.setAddOrDeletePrebillSplit("none");


     return showForm(request, response, errors);

    }

    Integer roundingDigits = prebillService.getDecimalDigitsByCurrency(addPrebill.getPrebill().getJobContract().getTransactionCurrencyCd());



    //Validate for duplicate branch
    String duplicateBranch = checkForDuplicateBranches(addPrebill.getPrebillSplits());
    if(duplicateBranch != null && (!duplicateBranch.trim().equals("")))
    {
            errors.reject("branch.alloc.error", new Object[] {"Duplicate branches are not allowed for allocation: "+duplicateBranch}, null);
             return showForm(request, response, errors);
    }

    if(allocPrebills != null && allocPrebills.length > 0)
    {
      for(int a=0;a<allocPrebills.length;a++)
      {
        Prebill allocPrebill = allocPrebills[a];
        long prebillId = allocPrebill.getId();

        if(addPrebill.getPrebillSplits() != null && addPrebill.getPrebillSplits().length > 0)
         {
           for(int b=0;b<addPrebill.getPrebillSplits().length;b++)
           {

             //Validate prebillSplit branch before saving
             Branch branch = userService.getBranchByName(addPrebill.getPrebillSplits()[b].getBranchName());
             if(branch == null)
             {
               errors.reject("invalid.branch.error", new Object[] {addPrebill.getPrebillSplits()[b].getBranchName()}, null);
                   return showForm(request, response, errors);
             }

             if(!branch.getName().equalsIgnoreCase(allocPrebill.getPrimaryBranchCd()))
             {
                 boolean branchValidateFlag = validateBranch(allocPrebill,addPrebill.getPrebillSplits()[b],branch);
                 if(!branchValidateFlag)
                 {
                   errors.reject("branch.alloc.service.mismatch.error", new Object[] {allocPrebill.getServiceType(),addPrebill.getPrebillSplits()[b].getBranchName(),branch.getType()}, null);
                       return showForm(request, response, errors);
                 }
             }
           }
         }
      }
    }


    //rearrange percentages
    if(updatePctAllocFlag != null && (updatePctAllocFlag.trim().equals("update")))
    {

      PrebillSplit[] prebillSplits = addPrebill.getPrebillSplits();

        if(prebillSplits != null && prebillSplits.length > 0)
        {
             for(int i=0;i<prebillSplits.length;i++)
             {

               String allocPct = new Double(prebillSplits[i].getAllocPct()).toString();

                   String[] allocItems = allocPct.split("\\.");
                System.out.println("allocPct :"+allocPct);
                 if(allocItems != null && allocItems.length > 1)
                 {
                   String decimalPart = allocItems[1];
                   System.out.println("decimal PArt :"+decimalPart);
                   if(decimalPart != null && decimalPart.length() > 3)
                   {
                     errors.reject("branch.alloc.error", new Object[] {"Upto 3 decimal places only are allowed for percentage allocation : "+allocPct}, null);
                           return showForm(request, response, errors);
                   }
                 }

             }

        }



      addPrebill.setPrebillSplits(rearrangePercentageAlloc(addPrebill.getPrebillSplits(),addPrebill.getPrebill()));

        if(prebillSplits != null && prebillSplits.length > 0)
        {
         double percentTotal = 0;
         for(int i=0;i<prebillSplits.length;i++)
         {
           percentTotal = percentTotal + prebillSplits[i].getAllocPct();

         }
         if(percentTotal > 100)
         {
                errors.reject("branch.alloc.error", new Object[] {"Sum of allocation percentages cannot be more than 100 %"}, null);
                return showForm(request, response, errors);
         }
        }


      addPrebill.setUpdatePctAllocFlag("none");
        return showForm(request, response, errors);
    }

    if(updateBranchDescFlag != null && (updateBranchDescFlag.trim().equals("update")))
    {

      PrebillSplit[] prebillSplits = addPrebill.getPrebillSplits();

        if(prebillSplits != null && prebillSplits.length > 0)
        {
             for(int i=0;i<prebillSplits.length;i++)
             {
               String branchName = prebillSplits[i].getBranchName();
               Branch branch = userService.getBranchByName(branchName);

               if(branch != null)
               {
                prebillSplits[i].setBranch(branch);
               }
               else
               {
                addPrebill.setUpdateBranchDescFlag("none");
                errors.reject("branch.alloc.error", new Object[] {"Invalid Branch Name: "+branchName}, null);
                    return showForm(request, response, errors);
               }
             }
        }
      addPrebill.setUpdateBranchDescFlag("none");
        return showForm(request, response, errors);
    }


     System.out.println("contract code in BranchAllocPopupController onSubmit :"+addPrebill.getContractCode());

     //rearrange % allocation
     addPrebill.setPrebillSplits(rearrangePercentageAlloc(addPrebill.getPrebillSplits(),addPrebill.getPrebill()));

     //Check for % alloc > 100
     PrebillSplit[] prebillSplits = addPrebill.getPrebillSplits();

     if(prebillSplits != null && prebillSplits.length > 0)
     {
       double percentTotal = 0;
       for(int i=0;i<prebillSplits.length;i++)
       {
         percentTotal = percentTotal + prebillSplits[i].getAllocPct();
         String allocPct = new Double(prebillSplits[i].getAllocPct()).toString();

         if(allocPct.indexOf(".") > -1)
         {
           String[] allocItems = allocPct.split(".");
           if(allocItems != null && allocItems.length > 1)
           {
             String decimalPart = allocItems[1];
             if(decimalPart != null && decimalPart.length() > 3)
             {
               errors.reject("branch.alloc.error", new Object[] {"Upto 3 decimal places only are allowed for percentage allocation : "+allocPct}, null);
                     return showForm(request, response, errors);
             }
           }
         }
       }
       if(percentTotal > 100)
       {
             errors.reject("branch.alloc.error", new Object[] {"Sum of allocation percentages cannot be more than 100 %"}, null);
             return showForm(request, response, errors);
       }
     }

     AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(addJobOrder, addPrebill.getContractIndex());
     JobContract jobContract = addJobContract.getJobContract();
     System.out.println("before getting prebill set ");

      //AddJobContractServices addJobContractServices = addJobContract.getAddJobContractServices();
      //List addedJobContractServices = addJobContractServices.getAddedJobContractServices();

     


      if(allocPrebills != null && allocPrebills.length > 0)
      {
        for(int a=0;a<allocPrebills.length;a++)
        {
          Prebill allocPrebill = allocPrebills[a];
          long prebillId = allocPrebill.getId();

          Set prebillSplitsSet = new HashSet();
          if(addPrebill.getPrebillSplits() != null && addPrebill.getPrebillSplits().length > 0)
           {
             for(int b=0;b<addPrebill.getPrebillSplits().length;b++)
             {

               PrebillSplit prebillSplit = prebillService.getPrebillSplitByPrebillIdAndBranchCode(allocPrebill.getId(), addPrebill.getPrebillSplits()[b].getBranchName());
               if(prebillSplit == null)
               {
                 prebillSplit = new PrebillSplit();
                 System.out.println("creating new prebillSplit object");
                 prebillSplit.setPrimaryInd(false);
               }
               prebillSplit.setPrebill(allocPrebill);
               prebillSplit.setBranchName(addPrebill.getPrebillSplits()[b].getBranchName());
               prebillSplit.setAllocPct(addPrebill.getPrebillSplits()[b].getAllocPct());
               //prebillSplit.setBranch(addPrebill.getPrebillSplits()[b].getBranch());
               prebillSplit.setUpdatedByUserName(addPrebill.getPrebillSplits()[b].getUpdatedByUserName());
               prebillSplit.setUpdateTime(addPrebill.getPrebillSplits()[b].getUpdateTime());

               System.out.println("after creating new prebillSplit object");
               prebillSplitsSet.add(prebillSplit);
             }
           }

          Set prebills = addJobContract.getJobContract().getPrebills();
          if(prebills != null)
          {
            Iterator prebillIterator = prebills.iterator();
            while(prebillIterator.hasNext())
            {
              Prebill prebill = (Prebill) prebillIterator.next();

              if(prebill.getId() == prebillId)
              {
                //System.out.println("match found for prebill id in jobContract index :"+i+prebillId);
                if(prebill.getPrebillSplits() != null)
                  prebill.getPrebillSplits().clear();

                prebill.setPrebillSplits(prebillSplitsSet);
                prebill = populateSplitAllocAmount(prebill, roundingDigits);
              }
            }
          }
          addJobContract.getJobContract().setPrebills(prebills);

        }
      }

    return new ModelAndView("invoice-preview-refresh-page");

  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
   *      org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(HttpServletRequest request,
      ServletRequestDataBinder binder) throws Exception {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null,
        new CustomDateEditor(dateFormat, true));
  }

  /*
   * (non-Javadoc)
   *
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(HttpServletRequest request)
      throws Exception {


    AddPrebill addPrebill = new AddPrebill();
       HttpSession session = request.getSession();
        AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobInvoicePreviewFormController.FORM.command");

        AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
          addJobOrder,
          request.getParameter("contractIndex")
        );





    System.out.println("inside formBackingObject of BranchAllocPopupController");
    String prebillId = request.getParameter("prebillId");
    String allocType = request.getParameter("allocType");
    String buName = request.getParameter("buName");
    String branchName = request.getParameter("branchName");
    String orgName = request.getParameter("orgName");
    String jobContractServiceIndex = request.getParameter("jobContractServiceIndex");
    String contractIndex = request.getParameter("contractIndex");
    String contractCode = request.getParameter("contractCode");

    addPrebill.setBuName(buName);
    addPrebill.setContractCode(contractCode);
    addPrebill.setContractIndex(contractIndex);
    System.out.println("prebillId=" + prebillId);
    System.out.println("allocType=" + allocType);
    System.out.println("buName=" + buName);
    System.out.println("branchName=" + branchName);
    System.out.println("orgName=" + orgName);
    System.out.println("contractCode=" + contractCode);
    System.out.println("jobContractServiceIndex=" + jobContractServiceIndex);

    addPrebill.setJobContractServiceIndex(jobContractServiceIndex);

    Set prebills = addJobContract.getJobContract().getPrebills();
    
    //START: To fix issue  109072
    if(null != addJobContract.getJobContract().getJobContractStatus()
   		 && (Constants.JOBCON_INVOICED_STATUS.equalsIgnoreCase(addJobContract.getJobContract().getJobContractStatus().trim())
   		 || Constants.JOBCON_CREDITED_STATUS.equalsIgnoreCase(addJobContract.getJobContract().getJobContractStatus().trim()))){
   	
    	addPrebill.setDisableEditBrachAlloc(true);
    }
    //END: To fix issue  109072


    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    Boolean disabledFlag = false;

    if(prebillId != null)
    {
      String[] prebillList=prebillId.split(";");
      Prebill[] allocPrebills = new Prebill[prebillList.length];
      //If more than 1 prebills for allocation, check the service type of each to make sure they are the same for all
      if(prebillList.length > 1)
      {
        disabledFlag = checkDiffPrebillServiceType(prebillList,jobService);
        if(disabledFlag)
          addPrebill.setWarnMsg(I18nUtil.getString(I18nUtil.getLocale(request), "branch.alloc.servicetype.error"));
      }
      for(int i=0;i<prebillList.length;i++)
      {

          if(prebills != null && prebills.size() > 0)
          {
            Iterator iter = prebills.iterator();
            while(iter.hasNext())
            {
              Prebill prebill = (Prebill) iter.next();
              if(prebill.getId() == new Long(prebillList[i]).longValue())
              {
                allocPrebills[i] = prebill;
                if(prebill.getPrebillSplits() != null && prebill.getPrebillSplits().size() >0)
                    {
                      Iterator iterSplit = prebill.getPrebillSplits().iterator();
                      int count = 0;
                      PrebillSplit[] prebillSplits = new PrebillSplit[prebill.getPrebillSplits().size()];
                      while(iterSplit.hasNext())
                      {
                        PrebillSplit prebillSplit = (PrebillSplit) iterSplit.next();
                        if(prebillSplit != null && prebillSplit.getBranchName() != null)
                          prebillSplit.setBranch(userService.getBranchByName(prebillSplit.getBranchName()));
                        prebillSplits[count] = prebillSplit;
                        count++;
                      }
                      addPrebill.setPrebillSplits(prebillSplits);

                    }
                      else
                      {
                        PrebillSplit[] prebillSplits = new PrebillSplit[1];
                        PrebillSplit prebillSplit = new PrebillSplit();
                        if(prebill.getPrimaryBranchCd() != null && (!prebill.getPrimaryBranchCd().trim().equals("")))
                          prebillSplit.setBranchName(prebill.getPrimaryBranchCd());
                        else
                        {
                          prebillSplit.setBranchName(branchName);
                          prebill.setPrimaryBranchCd(branchName);
                        }
                        prebillSplit.setPrebill(prebill);
                        prebillSplit.setAllocPct(100);
                        prebillSplit.setPrimaryInd(true);
                        prebillSplit.setUpdatedByUserName(SecurityUtil.getUser().getLoginName());
                        prebillSplit.setUpdatedBy(SecurityUtil.getUser());
                        prebillSplit.setUpdateTime(new Date());
                        prebillSplits[0] = prebillSplit;
                        addPrebill.setPrebillSplits(prebillSplits);
                      }
                addPrebill.setPrebill(prebill);

              }

            }
          }
        //Prebill prebill = jobService.getPrebillById(new Long(prebillList[i]));






        }


              addPrebill.setAllocPrebills(allocPrebills);
    System.out.println("size of prebillList :"+prebillList.length);
    }
    addPrebill.setDisabledFlag(disabledFlag);




    if(orgName == null || orgName.trim().equals(""))
    {
      BusinessUnit bu = userService.getBusinessUnitByName(buName);
      if(bu != null)
        orgName = bu.getOrgName();
      System.out.println("org name is :"+orgName);
    }
    addPrebill.setOrgName(orgName);
    //Load prebill & prebillSplit from DB

    System.out.println("YG:DisableEditBrachAlloc:"+addPrebill.isDisableEditBrachAlloc());
    
    //START: To fix issue  109072
    if(addPrebill.isDisableEditBrachAlloc()){
    	addPrebill.setDisabledFlag(true);
    }
    //END: To fix issue  109072
    
    return addPrebill;

  }

    private PrebillSplit[] addPrebillSplit(PrebillSplit[] prebillSplits,Prebill prebill)
    {
      PrebillSplit prebillSplit = new PrebillSplit();
      prebillSplit.setPrebill(prebill);
      prebillSplit.setUpdatedByUserName(SecurityUtil.getUser().getLoginName());
      prebillSplit.setUpdatedBy(SecurityUtil.getUser());
      prebillSplit.setUpdateTime(new Date());
      prebillSplit.setPrimaryInd(false);
      prebillSplit.setBranch(new Branch());

      PrebillSplit[] results = null;
      if(prebillSplits == null) results = new PrebillSplit[1];
      else results = new PrebillSplit[prebillSplits.length + 1];
      if(prebillSplits != null)
        {
        for(int i=0; i<prebillSplits.length; i++)
        {
          results[i] = prebillSplits[i];
        }
        }


      results[results.length - 1] = prebillSplit;

      return results;
    }

    private PrebillSplit[] deletePrebillSplit(PrebillSplit[] prebillSplits, int index)
    {
      if(prebillSplits == null) return null;
      if(prebillSplits.length <= 0) return prebillSplits;

      PrebillSplit[] results = new PrebillSplit[prebillSplits.length - 1];

      int count = 0;
      for(int i=0; i<prebillSplits.length; i++)
      {
        if(i == index) continue;
        results[count] = prebillSplits[i];

        count ++;
      }

      return results;
    }

    private PrebillSplit[] rearrangePercentageAlloc(PrebillSplit[] prebillSplits, Prebill prebill)
    {
      if(prebillSplits == null) return null;
      if(prebillSplits.length <= 0) return prebillSplits;
      double totalNonPrimaryBranchAlloc = 0;
      int primarySplitIndex = 0;
      for(int i=0;i<prebillSplits.length;i++ )
      {
        System.out.println("prebillSplits[i].getBranchName() :"+prebillSplits[i].getBranchName());
        System.out.println("prebill.getPrimaryBranchCd() :"+prebill.getPrimaryBranchCd());
        if(prebillSplits[i].getBranchName() != null &&
          !prebillSplits[i].getBranchName().trim().equals(prebill.getPrimaryBranchCd()))
        {
          totalNonPrimaryBranchAlloc =  totalNonPrimaryBranchAlloc + prebillSplits[i].getAllocPct();
        }
        else
          primarySplitIndex = i;

      }
      System.out.println("primarySplitIndex :"+primarySplitIndex);
      //Reset primary branch allocation percentage
      PrebillSplit primaryPrebillSplit = prebillSplits[primarySplitIndex];
      double primaryBranchAllocPct = 100 - totalNonPrimaryBranchAlloc;
      if(primaryBranchAllocPct < 0)
        primaryBranchAllocPct = 0;

      primaryPrebillSplit.setAllocPct(NumberUtil.roundHalfUp(primaryBranchAllocPct,3));
      prebillSplits[primarySplitIndex] = primaryPrebillSplit;
      return prebillSplits;
    }

 private String checkForDuplicateBranches(PrebillSplit[] prebillSplits)
 {
   String duplicateBranch = "";
   if(prebillSplits == null || prebillSplits.length <= 0)
     return null;
   String[] branches = new String[prebillSplits.length];
   for(int i=0;i<prebillSplits.length;i++)
   {
     branches[i] = prebillSplits[i].getBranchName();

   }
   duplicateBranch = findDuplicates(branches);
   return duplicateBranch;
 }

 private String findDuplicates(String[] arr){
      Set set = new HashSet();
      String duplicateStr = "";
      for(int i=0; i < arr.length; i++){
        if(set.contains(arr[i])){
          System.out.println("Duplicate string found at index " + i);
          duplicateStr = arr[i];
        } else {
          set.add(arr[i]);
        }
      }
      return duplicateStr;
    }
 private Prebill populateSplitAllocAmount(Prebill prebill, Integer roundingDigits)
 {
   double lineTotal = prebill.getNetPrice();

   double sumOfSplits = 0;
   double remainingAmount = lineTotal;
   double remainingPct = 100;
   
   
   if(prebill.getPrebillSplits() != null && prebill.getPrebillSplits().size() > 0)
   {
     Set prebillSplits = prebill.getPrebillSplits();
     Iterator iter = prebillSplits.iterator();
     while(iter.hasNext())
     {
       PrebillSplit prebillSplit = (PrebillSplit) iter.next();
       if(prebillSplit.getPrimaryInd() != null && !prebillSplit.getPrimaryInd())
       {
         System.out.println("non primary row being processed");
         double splitAmount = lineTotal * (prebillSplit.getAllocPct()/100);
         if(roundingDigits != null)
        	 splitAmount = NumberUtil.roundHalfUp(splitAmount, roundingDigits);
         else
        	 splitAmount = NumberUtil.roundHalfUp(splitAmount, 2);

         if(splitAmount > remainingAmount || (remainingPct == prebillSplit.getAllocPct()))
           splitAmount = remainingAmount;
         prebillSplit.setAllocAmt(splitAmount);

         sumOfSplits = sumOfSplits + prebillSplit.getAllocAmt();
         remainingAmount = remainingAmount - prebillSplit.getAllocAmt();
         remainingPct = remainingPct - prebillSplit.getAllocPct();
       }
     }
     Iterator iterPrimary = prebillSplits.iterator();
     while(iterPrimary.hasNext())
     {
       PrebillSplit prebillSplit = (PrebillSplit) iterPrimary.next();
       if(prebillSplit.getPrimaryInd() != null && prebillSplit.getPrimaryInd())
       {
    	 double primaryAmt = NumberUtil.roundHalfUp((lineTotal - sumOfSplits), 2);
    	 if(roundingDigits != null)
    		 primaryAmt = NumberUtil.roundHalfUp((lineTotal - sumOfSplits), roundingDigits);
         prebillSplit.setAllocAmt(primaryAmt);
         System.out.println("primary alloc amt :"+prebillSplit.getAllocAmt());
       }
       else
         System.out.println("non primary alloc amt :"+prebillSplit.getAllocAmt());
     }
   }
   return prebill;
 }

 public boolean checkDiffPrebillServiceType(String[] prebillList,JobService jobService)
 {
   boolean trueFalseFlag = false;
   String serviceType = "";
   for(int i=0;i<prebillList.length;i++)
   {
     Prebill prebill = jobService.getPrebillById(new Long(prebillList[i]));
     if(prebill != null)
     {
       if(i==0)
         serviceType =  prebill.getServiceType();
       if(!trueFalseFlag && serviceType.trim().equalsIgnoreCase(prebill.getServiceType()))
         continue;
       else
       {
         trueFalseFlag = true;
       }
     }
   }
   return trueFalseFlag;
 }

 public boolean validateBranch(Prebill prebill,PrebillSplit prebillSplit,Branch branch)
 {
   if(prebill.getServiceType() != null && prebill.getServiceType().trim().equalsIgnoreCase(Constants.OPS))
   {
    if((branch.getType().trim().equalsIgnoreCase(prebill.getServiceType())) || (branch.getType().trim().equalsIgnoreCase(Constants.OPSLAB))
        || (branch.getType().trim().equalsIgnoreCase(Constants.OPSC)))
      return true;
    else
      return false;
   }
   else
   {
     if((branch.getType().trim().equalsIgnoreCase(prebill.getServiceType())) || (branch.getType().trim().equalsIgnoreCase(Constants.OPSLAB)))
        return true;
      else
        return false;
   }
 }
}
