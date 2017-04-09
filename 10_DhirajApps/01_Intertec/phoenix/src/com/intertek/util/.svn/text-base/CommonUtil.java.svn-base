package com.intertek.util;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import com.intertek.calculator.CalculatorUtil;
import com.intertek.domain.AddJobProd;
import com.intertek.domain.AddJobProdSample;
import com.intertek.domain.AddJobVessel;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobManualTest;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobSlate;
import com.intertek.entity.JobTest;
import com.intertek.entity.Operation;
import com.intertek.entity.ProductGroup;
import com.intertek.entity.Service;
import com.intertek.entity.VesselType;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.FieldMeta;
import com.intertek.meta.MetaDataManager;
import com.intertek.meta.ObjectMeta;
import com.intertek.meta.ViewMeta;
import com.intertek.meta.dropdown.DropDownManager;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;
import com.intertek.service.JobTypeService;
import com.intertek.servicetype.JobServiceType;
import com.intertek.servicetype.ServiceTypeExt;

public class CommonUtil
{

  private static final Pattern emailPattern = Pattern.compile("^(([A-Za-z0-9]+_+)|([A-Za-z0-9]+\\-+)|([A-Za-z0-9]+\\.+)|([A-Za-z0-9]+\\++))*[A-Za-z0-9]+@((\\w+\\-+)|(\\w+\\.))*\\w{1,63}\\.[a-zA-Z]{2,6}$");
 // private static final Pattern phonePattern= Pattern.compile("[0-9-+]+");
  private static final Pattern phonePattern= Pattern.compile("[0-9-+()/ ]+");
 //added for the issue-104407 
  private static final Pattern faxPattern= Pattern.compile("[0-9-+()/ ]+");

  private static final Comparator dropDownComparator=new Comparator(){
    public int compare(Object obj1, Object obj2){
    try{
      Field f1=(Field)obj1;
      Field f2=(Field)obj2;
      String n1=f1.getName();
      String n2=f2.getName();
      n1=n1==null?"":n1.toLowerCase();
      n2=n2==null?"":n2.toLowerCase();
      return n1.compareTo(n2);
    }
    catch(Exception e){
    }
    return 0;
  }
  };

  private static final Comparator serviceComparator=new Comparator(){
    public int compare(Object o1, Object o2){
    try{
      Service s1=(Service)o1;
      Service s2=(Service)o2;
      if(s1==null){
        return -1;
      }
      if(s2==null){
        return 1;
      }
      return s1.getVisibility().compareTo(s2.getVisibility());
    }
    catch(Exception e){
    }
    return 0;
  }
  };

  public static Integer parseInt(String s){
    try{
      return Integer.parseInt(s);
    }
    catch(Exception e){

    }
    return null;
  }

  public static Long parseLong(String s){
    try{
      return Long.parseLong(s);
    }
    catch(Exception e){

    }
    return null;
  }

    /**
   * Name :validateEmail
   * Date :Sep 24, 2008
   * purpose :This method purpose is to validate the given email and returns valid or not
   * @param strEmail
   * @return boolean
   */
  public boolean validateEmail(String strEmail) {
      return ((emailPattern.matcher(strEmail).matches()) ? true : false);
    }


  /**
   * Name :validatePhone`
   * Date :Oct 23, 2008
   * purpose :This method purpose is to validate the given phone number  and return valid or not
   * @param strPhone
   * @return boolean
   */
  public boolean validatePhone(String strPhone){
    System.out.println("phone number in common util is"+ strPhone);
    return ((phonePattern.matcher(strPhone).matches()) ? true : false);
  }
  
  //added for the issue-104407
  /**
   * Name : validateFax
   * Date : Apr 30, 2009
   * @param strFax
   * @return boolean
   */
  public boolean validateFax(String strFax){
	strFax = strFax.trim();
	return ((faxPattern.matcher(strFax).matches()) ? true : false);
  }

  public static ObjectMeta getObjectMeta(String divisionName, String jobType, Class clazz)
  {
    ObjectMeta objectMeta = null;

    MetaDataManager metaDataMgr = (MetaDataManager)ServiceLocator.getInstance().getBean("metaDataMgr");
    ViewMeta viewMeta = metaDataMgr.getViewMeta(new String[] {divisionName, jobType});
    System.out.println("========== jobType, viewMeta: " + jobType + ", " + viewMeta);
    if(viewMeta != null)
    {
      objectMeta = viewMeta.getObjectMeta(clazz.getSimpleName());
    }

    System.out.println("========== divisionName, name, objectMeta: " + divisionName + ", " + clazz.getSimpleName() + ", " + objectMeta);

    return objectMeta;
  }

  public static FieldMeta getFieldMeta(ObjectMeta objectMeta, String name)
  {
    if(objectMeta == null) return null;
    return objectMeta.getFieldMeta(name);
  }
  

  public static List getProductGroups(String name, JobOrder jo){
      DropDownManager dropDownMgr = (DropDownManager)ServiceLocator.getInstance().getBean("dropDownMgr");
      List params=new ArrayList();
      params.add(jo);
      List list=dropDownMgr.execute(name, params);
      return list;
  }

  public static List getDropDown(String name, List params)
  {
    DropDownManager dropDownMgr = (DropDownManager)ServiceLocator.getInstance().getBean("dropDownMgr");
    List list=dropDownMgr.execute(name, params);
    /*
    HashSet skip=new HashSet();
    skip.add("staticDropdown");
    skip.add("firstFreezeDropDown");
    skip.add("secondFreezeDropDown");
    skip.add("thirdFreezeDropDown");
    skip.add("fourthFreezeDropDown");
    skip.add("hideDropDown");
    skip.add("sortDropDown");
    skip.add("hideandSort");
    */

    HashSet doSort=new HashSet();
    //doSort.add("productGroup");
    doSort.add("events");

    if(doSort.contains(name)){
      Collections.sort(list, dropDownComparator);
    }
    else{
      //System.out.println("skip sorting "+name);
    }

    return com.intertek.phoenix.util.CommonUtil.fixIE7Bug(list);
  }

  public static InputStream getClassPathResource(String location)
  {
    if(location == null) return null;

    //ClassLoader classLoader = ServiceLocator.getInstance().getClass().getClassLoader();
    //if(classLoader == null) classLoader = ClassLoader.getSystemClassLoader();


    return Thread.currentThread().getContextClassLoader().getResourceAsStream(location);
  }

  public static String getServicesPopupStr(
    int contractIndex,
    int vesselIndex,
    int prodIndex,
    String contractCode,
    JobServiceType jobServiceType,
    Locale locale
  )
  {
    if((jobServiceType == null)) return null;

    String level = null;
    if(prodIndex >= 0) level = Constants.LOT;
    else if(vesselIndex >= 0) level = Constants.VESSEL;
    else level = Constants.JOB;

    List steList = (List)jobServiceType.getDataMap().get(level);

    if((steList == null) || (steList.size() == 0)) return null;

    StringBuffer sb = new StringBuffer();
    for(int i=0; i<steList.size(); i++)
    {
      ServiceTypeExt ste = (ServiceTypeExt)steList.get(i);
      sb.append("<a href=# onClick=popAddEditService(\\'");
      sb.append(contractIndex);
      sb.append("\\'");
      sb.append(",\\'");
      sb.append(contractCode);
      sb.append("\\'");
      sb.append(",\\'");
      sb.append(vesselIndex);
      sb.append("\\'");
      sb.append(",\\'");
      sb.append(prodIndex);
      sb.append("\\'");
      sb.append(",\\'");
      sb.append(ste.getServiceType().getServiceTypeId().getServiceName());
      sb.append("\\');>");
      //sb.append(I18nUtil.getString(locale, ste.getServiceType().getServiceTypeId().getServiceName()));
      sb.append(ste.getRbValue());
      sb.append("</a><br>");
    }

    return sb.toString();
  }

  public static boolean[] createBooleans(int size)
  {
    boolean[] selects = new boolean[size];

    return selects;
  }

  public static boolean[] createBooleans(List inputs)
  {
    if(inputs == null) return null;

    boolean[] selects = new boolean[inputs.size()];
    for(int i=0; i<inputs.size(); i++)
    {
      selects[i] = ((Boolean)inputs.get(i)).booleanValue();
    }

    return selects;
  }

  public static boolean[] removeFromBoolean(boolean[] selects, int index)
  {
    if(selects == null) return null;

    if((index < 0) || (index >= selects.length)) return selects;

    if(selects.length == 0) return null;

    boolean[] newSelects = new boolean[selects.length - 1];
    int count = 0;
    for(int i=0; i<selects.length; i++)
    {
      if(i != index)
      {
        newSelects[count] = selects[i];
        count ++;
      }
    }

    return newSelects;
  }

  public static String getUrlSuffixByJobType(String jobType)
  {
    if(Constants.ANALYTICAL_SERVICE_JOBTYPE.equals(jobType)) return "analytical_service";
    if(Constants.MARINE_JOBTYPE.equals(jobType)) return "marine";
    if(Constants.OUTSOURCING_JOBTYPE.equals(jobType)) return "outsourcing";

    return "insp";
  }

  public static String getUrlPrefixByJobStatus(String jobStatus)
  {
    if(Constants.OPEN_STATUS.equals(jobStatus) || Constants.REOPENED_STATUS.equals(jobStatus)) return "edit";

    return "view";
  }


  public static String getDropdownLabel(String dropdown, List params, String value)
  {
    if(value == null) return null;

    List fields = getDropDown(dropdown, params);
    if(fields != null)
    {
      for(int i=0; i<fields.size(); i++)
      {
        Field field = (Field)fields.get(i);
        if(value.equals(field.getValue())) return field.getName();
      }
    }

    return value;
  }

  public static String getOperationDesc(String code)
  {
    if(code == null) return null;

    JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");
    Operation operation = jobTypeService.getOperationByOperatoinCode(code);

    if(operation == null)
      return null;
    else
      return operation.getDescription();

  }

  public static String getJobStatusByInvoiceType(String jobNumber)
  {
     if(jobNumber == null) return null;
     String invoiceType = "REG";

      JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
      JobOrder jobOrder = jobService.getJobOrderByJobNumberWithInvoiceInfo(jobNumber);

      if(jobOrder == null)
        return null;

      Set jobContracts = jobOrder.getJobContracts();
      if(jobContracts != null)
      {
        Iterator iter = jobContracts.iterator();
        while(iter.hasNext())
        {
          JobContract jobContract = (JobContract) iter.next();
          if(jobContract.getInvoiceType() != null && jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_CON))
          {
            //Check if invoice has already been generated for this consolidated invoice type
            Set invoices = jobContract.getJobContractInvoices();
            if(invoices != null && invoices.size() > 0)
              invoiceType = Constants.INV_TYPE_CON;
          }

        }
      }

        return invoiceType;

  }

  public static int getJobContractCount(String jobNumber)
  {
     if(jobNumber == null) return 0;

      JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
      JobOrder jobOrder = jobService.getJobOrderByJobNumberWithJobContracts(jobNumber);

      if(jobOrder == null)
        return 0;

      Set jobContracts = jobOrder.getJobContracts();
      if(jobContracts != null && jobContracts.size() > 0)
      {
        return jobContracts.size();
      }

        return 0;

  }

  public static boolean getInvoiceGeneratedFlag(String jobNumber)
  {
    boolean invoiceFlag = false;
    //boolean allContractsInvoiced = true;
    int cntContractsNotInvoiced = 0;

     if(jobNumber == null) return false;
     Set invoices = null;
      JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
      JobOrder jobOrder = jobService.getJobOrderByJobNumberWithInvoiceInfo(jobNumber);

      if(jobOrder == null)
      {
        return false;
      }

      Set jobContracts = jobOrder.getJobContracts();
      /* 03/26/09 KETAN - START - Code added to set invoiced flag to false in case of no contracts */
      if(jobContracts.size()==0)
      {
        return false;
      }

      /* 03/26/09 KETAN - END - Code added to set invoiced flag to false in case of no contracts */
      if(jobContracts != null)
      {
        Iterator iter = jobContracts.iterator();
        while(iter.hasNext())
        {
          JobContract jobContract = (JobContract) iter.next();
          /* 02/25/09 KETAN - START - Code modified to check if all the contracts are invoiced, then allow to CLOSE the job */
          if(jobContract != null)
          {
            invoices = jobContract.getJobContractInvoices();

            // Monthly Job - checked
            if(jobContract.getMonthlyJobNumber()!=null
              && jobContract.getMonthlyFlag()!=null)
            {
              //if(!(jobContract.getMonthlyJobNumber().toString().isEmpty())
             if(!(jobContract.getMonthlyJobNumber().toString().equals(""))
                 && jobContract.getMonthlyFlag() == true
                   && (!(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_INVOICED_STATUS))))
              {
                cntContractsNotInvoiced++;
              }
            }

            //Monthly Job - Unchecked
            if(jobContract.getMonthlyJobNumber()!=null
              && (jobContract.getMonthlyFlag()==null || jobContract.getMonthlyFlag() == false)
              && (jobOrder.getJobFinishDate()==null || jobOrder.getJobFinishDate().toString()==""))
            {
              cntContractsNotInvoiced++;
            }

            // Regular Job
            if((jobContract.getMonthlyJobNumber()==null)
                    && (!(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_INVOICED_STATUS))))
             {
            cntContractsNotInvoiced++;
             }
         }
        }
        invoiceFlag = (cntContractsNotInvoiced > 0) ? false : true;
         //if(invoices != null && invoices.size() > 0 && !invoiceFlag)
         //    invoiceFlag = true;
      /* 02/25/09 KETAN - END - Code modified to check if all the contracts are invoiced, then allow to CLOSE the job */
      }

      return invoiceFlag;
  }

  public static String displayVesselType(List vesselTypes, String vesselType)
  {
    if((vesselTypes == null) || (vesselType == null)) return null;

    VesselType matchedVesselType = CalculatorUtil.getSelectedVesselType(vesselTypes, vesselType);

    if(matchedVesselType != null)
    {
      return matchedVesselType.getRbValue();
    }
    else
    {
      return "<span style=\"color:red;\">" + vesselType + "</span>";
    }
  }

  public static String displayProductGroup(List productGroups, String productGroup)
  {
    if((productGroups == null) || (productGroup == null)) return null;

    ProductGroup matchedProductGroup = CalculatorUtil.getSelectedProductGroup(productGroups, productGroup);

    if(matchedProductGroup != null)
    {
      return matchedProductGroup.getRbValue();
    }
    else
    {
      return "<span style=\"color:red;\">" + productGroup + "</span>";
    }
  }

  public static String getQuestionMarks(int count){
    if(count<=0){
      return "";
    }

    StringBuffer buf=new StringBuffer("");
    for(int i=0; i<count-1; i++){
      buf.append("?, ");
    }
    buf.append("?");
    return buf.toString();
  }

  public static boolean contains(Collection collection, Object obj)
  {
    if(obj == null) return false;
    if(collection == null) return false;

    return collection.contains(obj);
  }

  public static String getJobRemainTime(Date aDate){
    long amount=Constants.JOB_LOCK_TIME_AMOUNT;
    long remainTime=amount;
    if(aDate!=null){
      remainTime= amount - (System.currentTimeMillis() - aDate.getTime());
    }
    String result="";
    remainTime=remainTime/1000;

    int hours = (int) remainTime / 3600,
    remainder = (int) remainTime % 3600,
    minutes = remainder / 60,
    seconds = remainder % 60;

    return ( hours
    + ":" + (minutes < 10 ? "0" : "") + minutes
    + ":" + (seconds< 10 ? "0" : "") + seconds );
  }

  public static List sortServices(List services){
    Collections.sort(services, serviceComparator);
    return services;
  }
  public static boolean validateSamplesData(AddJobVessel[] addJobVessels){
    boolean validate = false;
      if(addJobVessels != null)
      {
        for(int i=0;i<addJobVessels.length;i++)
        {
          AddJobVessel addJobVessel = addJobVessels[i];
          if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
          {
            for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
            {
              AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
              if(addJobProd.getAddJobProdSamples() != null && addJobProd.getAddJobProdSamples().length >0)
              {
                for(int k=0;k<addJobProd.getAddJobProdSamples().length;k++)
                {
                  AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[k];

                  if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length >0)
                  {
                  int testOtNoValCount = 0;
                  int testOtYesValCount = 0;
                    for(int l=0;l<addJobProdSample.getJobTests().length;l++)
                    {
                      JobTest jobTest = addJobProdSample.getJobTests()[l];
                      if(jobTest.getOt())
                      {
                        testOtYesValCount++;
                      } else {
                       testOtNoValCount++;
                      }
                    }
                    if(testOtNoValCount == addJobProdSample.getJobTests().length){
                    	 // START : #134001 
                        /*if(addJobProdSample.getJobProdSample() != null
                            && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                            || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){*/
                    	  if(addJobProdSample.getJobProdSample() != null
                             && addJobProdSample.getJobProdSample().getPriority()!=null && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                         	 || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH)))
                    	    {
                    		  		validate=true;
                            }
                         // END : #134001
                    }
                    if(testOtYesValCount != 0){
                      if(addJobProdSample.getJobProdSample() != null
                          && addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){
                        validate=true;
                    }
                    }
                  }
                  if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length >0)
                  {
                    int slateOtNoValCount = 0;
                  int slateOtYesValCount = 0;
                    for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
                    {
                      JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
                      if(jobSlate.getOt())
                      {
                        slateOtYesValCount++;
                      } else {
                       slateOtNoValCount++;
                      }
                    }
                    if(slateOtNoValCount == addJobProdSample.getJobSlates().length){
                      if(addJobProdSample.getJobProdSample() != null
                          && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                              || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                        validate=true;
                    }
                     }
                     if(slateOtYesValCount != 0){
                      if(addJobProdSample.getJobProdSample() != null
                          && addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){
                        validate=true;
                    }
                     }
                  }
                  // 101771 START
                  if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0)
                  {
                    int mtestOtNoValCount = 0;
                  int mtestOtYesValCount = 0;
                    for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
                    {
                      JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
                      if(jobManualTest.getOt())
                      {
                        mtestOtYesValCount++;
                      } else {
                       mtestOtNoValCount++;
                      }
                    }
                      if(mtestOtNoValCount == addJobProdSample.getJobManualTests().length){
                        if(addJobProdSample.getJobProdSample() != null
                            && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                                || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                          validate=true;
                      }
                      }
                      if(mtestOtYesValCount != 0){
                          if(addJobProdSample.getJobProdSample() != null
                              && addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){
                            validate=true;
                        }
                      }
                  }
                  // For Tests and Manual tests
                  if((addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0 )
                		  && (addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length > 0))
                  {
                    int mtestOtNoValCount = 0;
                    int mtestOtYesValCount = 0;
                    for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
                    {
                      JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
                      if(jobManualTest.getOt())
                      {
                        mtestOtYesValCount++;
                      } else {
                       mtestOtNoValCount++;
                      }
                    }
                    if(mtestOtNoValCount == addJobProdSample.getJobManualTests().length){
                        if(addJobProdSample.getJobProdSample() != null
                            && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                                || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                          validate=true;
                    }
                    }
                    if(mtestOtYesValCount != 0){
                          if(addJobProdSample.getJobProdSample() != null
                              && addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){
                            validate=true;
                        }
                    }
                    int testOtNoValCount = 0;
                    int testOtYesValCount = 0;
                    for(int l=0;l<addJobProdSample.getJobTests().length;l++)
                        {
                          JobTest jobTest = addJobProdSample.getJobTests()[l];
                          if(jobTest.getOt())
                          {
                            testOtYesValCount++;
                          } else {
                           testOtNoValCount++;
                          }
                        }
                        if(testOtNoValCount == addJobProdSample.getJobTests().length){
                        if(addJobProdSample.getJobProdSample() != null
                            && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                                || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                          validate=true;
                        }
                        }
                        if(testOtYesValCount != 0){
                          if(addJobProdSample.getJobProdSample() != null
                              && addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){
                            validate=true;
                        }
                        }
                  }
                  // 101771 END
                }
              }
            }
          }
        }
      }
    return validate;
  }

public static boolean checkOtApproved(AddJobVessel[] addJobVessels){

    boolean validate = false;
      if(addJobVessels != null)
      {
        for(int i=0;i<addJobVessels.length;i++)
        {
          AddJobVessel addJobVessel = addJobVessels[i];
          if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
          {
            for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
            {
              AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
              if(addJobProd.getAddJobProdSamples() != null && addJobProd.getAddJobProdSamples().length >0)
              {
                for(int k=0;k<addJobProd.getAddJobProdSamples().length;k++)
                {
                  AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[k];

        if( addJobProdSample.getJobSlates() == null && addJobProdSample.getJobManualTests() == null ) {
            if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length >0 )
            {
            int testOtYesValCount = 0;
            for(int l=0;l<addJobProdSample.getJobTests().length;l++)
            {
              JobTest jobTest = addJobProdSample.getJobTests()[l];
              if(jobTest.getOt())
              {
              testOtYesValCount++;
              }
            }
            if(testOtYesValCount != 0){
              if(addJobProdSample.getJobProdSample() != null
                  && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                      || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                validate = true;
              }
            }
            } else {
             if(addJobProdSample.getJobProdSample() != null
                && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                    || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
            validate = true;
             }
            }
                }

        if( addJobProdSample.getJobTests() == null && addJobProdSample.getJobManualTests() == null ) {

            if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length >0)
            {
            int slateOtYesValCount = 0;
            for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
            {
              JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
              if(jobSlate.getOt())
              {
              slateOtYesValCount++;
              }
            }
            if(slateOtYesValCount != 0){
               if(addJobProdSample.getJobProdSample() != null
                  && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                      || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                validate = true;
              }
             }
            } else {
               if(addJobProdSample.getJobProdSample() != null
                  && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                      || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
              validate = true;
               }
            }
          }

        if( addJobProdSample.getJobTests() == null && addJobProdSample.getJobSlates() == null ) {

            if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0)
            {
            int mtestOtYesValCount = 0;
            for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
            {
              JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
              if(jobManualTest.getOt())
              {
              mtestOtYesValCount++;
              }
              if(mtestOtYesValCount != 0){
                if(addJobProdSample.getJobProdSample() != null
                  && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                      || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                  validate = true;
                }
              }
            }
            } else {
               if(addJobProdSample.getJobProdSample() != null
                && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                    || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
              validate = true;
               }
            }
          }
        //iTrack Issue 101771
		        if( addJobProdSample.getJobTests() != null && addJobProdSample.getJobManualTests() != null ) {
		            if(addJobProdSample.getJobManualTests().length >0 && addJobProdSample.getJobTests().length >0)
		            {
			            int mtestOtYesValCount = 0;
			            for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
			            {
			              JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
			              if(jobManualTest.getOt())
			              {
			              mtestOtYesValCount++;
			              }
			            }
			            if(mtestOtYesValCount != 0){
			                if(addJobProdSample.getJobProdSample() != null
			                  && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
			                      || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
			                  validate = true;
			                }
			              }
			            int testOtYesValCount = 0;
			            for(int l=0;l<addJobProdSample.getJobTests().length;l++)
			            {
			              JobTest jobTest = addJobProdSample.getJobTests()[l];
			              if(jobTest.getOt())
			              {
			              testOtYesValCount++;
			              }
			            }
			            if(testOtYesValCount != 0){
			              if(addJobProdSample.getJobProdSample() != null
			                  && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
			                      || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
			                validate = true;
			              }
			            }
		            } else {
		               if(addJobProdSample.getJobProdSample() != null
		                && (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
		                    || addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
		                   validate = true;
		               }
		            }
		          }
		        //End
                }
              }
            }
          }
        }
      }
    return validate;
  }
public static String doESB(String msgText){
	try{
	// Setting the ConnectionFactory such that it will use scout

	System.setProperty("javax.xml.registry.ConnectionFactoryClass", "org.apache.ws.scout.registry.ConnectionFactoryImpl");

	/*
	 * Properties p=System.getProperties(); Enumeration em=p.keys();
	 * while(em.hasMoreElements()){ String key=(String)em.nextElement();
	 * String value=p.getProperty(key); System.out.println(key+"="+value); }
	 */
	/*
	 * Map<String, String> mp=System.getenv(); Iterator<String>
	 * itr=mp.keySet().iterator(); while(itr.hasNext()){ String
	 * key=itr.next(); String value=mp.get(key);
	 * System.out.println(key+"="+value); }
	 */
	//String msgText = "TEST";
	System.out.println("Inbound Data: " + msgText);

	//ServiceInvoker invoker;
	//Message requestMessage;
	//Message replyMessage;
	//invoker = new ServiceInvoker("ActionServices", "CustomActionService");
	//requestMessage = MessageFactory.getInstance().getMessage(MessageType.JBOSS_XML);
	//requestMessage.getBody().add(msgText);

	//invoker.deliverAsync(requestMessage); // no waiting for a response

	// now delivery it and wait for a response
	//replyMessage = invoker.deliverSync(requestMessage, 20000);

	//System.out.println("Reply Message: " + replyMessage.getBody().get());
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return "";
}


    public static String encodeUrl(String text){
        try {
            return URLEncoder.encode(text, "UTF8");
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        return text;
    }
}  
