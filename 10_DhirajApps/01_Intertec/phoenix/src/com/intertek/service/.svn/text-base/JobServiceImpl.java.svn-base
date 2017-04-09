package com.intertek.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinFragment;
import org.springframework.web.multipart.MultipartFile;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddWeeklyReport;
import com.intertek.domain.BankSearch;
import com.intertek.domain.BatchReprintHelper;
import com.intertek.domain.CancelledJobsSearch;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.DateSearchField;
import com.intertek.domain.JobSearch;
import com.intertek.domain.SlateSearch;
import com.intertek.domain.TemplateSearch;
import com.intertek.domain.TestSearch;
import com.intertek.domain.VesselSearch;
import com.intertek.entity.BatchReprint;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.Country;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.Customer;
import com.intertek.entity.Event;
import com.intertek.entity.InspectionEventTbl;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractAttach;
import com.intertek.entity.JobContractAttachType;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobContractNote;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.JobEvent;
import com.intertek.entity.JobLog;
import com.intertek.entity.JobManualTest;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdQty;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobSlate;
import com.intertek.entity.JobTest;
import com.intertek.entity.JobVessel;
import com.intertek.entity.LogConfigDetail;
import com.intertek.entity.LogConfigList;
import com.intertek.entity.OpenPeriod;
import com.intertek.entity.Operation;
import com.intertek.entity.Prebill;
import com.intertek.entity.PriceBook;
import com.intertek.entity.RB;
import com.intertek.entity.RevisionNotes;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.ShippingAgent;
import com.intertek.entity.Slate;
import com.intertek.entity.State;
import com.intertek.entity.Test;
import com.intertek.entity.TowingCompany;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.report.JasperFillReport;
import com.intertek.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.JobUtil;
import com.intertek.util.OrderBySqlFormula;
import com.intertek.util.SecurityUtil;
import com.intertek.util.SortUtil;

public class JobServiceImpl extends BaseService implements JobService {
  private static Log log = LogFactory.getLog(JobServiceImpl.class);

  public void removeObject(Object obj){
    getDao().remove(obj);
  }

  public synchronized String getJobNumber(String branchName){
    SequenceNumberService sns = (SequenceNumberService) ServiceLocator
        .getInstance().getBean("sequenceNumberService");

    Long sequenceNumber = sns.getSequenceNumber(Constants.SEQ_PREFIX
        + branchName);
    return branchName + "-" + sequenceNumber;
  }

  // Start Template
  public synchronized String getTemplateNumber(String branchName){
    SequenceNumberService sns = (SequenceNumberService) ServiceLocator
        .getInstance().getBean("sequenceNumberService");

    Long sequenceNumber = sns.getSequenceNumber(Constants.BRANCH_SEQ);

    String seqNumStr = sequenceNumber.toString();
    String updatedSeqNumber = seqNumStr;

    for(int i = 0; i < (7 - seqNumStr.length()); i++){
      updatedSeqNumber = "0" + updatedSeqNumber;
    }

    return branchName + Constants.TEMP + updatedSeqNumber;
  }

  public List getTemplateByNameAndBranch(String tmpName, String branchName){
    String sqlStmt = "";

    if(tmpName.equals("")){
      sqlStmt = "select job from JobOrder job "
          + " where job.branchName = ? and job.isTemplate =true";
    }
    else{
      sqlStmt = "select job from JobOrder job "
          + " where job.branchName = ? and job.isTemplate =true and upper(job.tempName) like  '"
          + tmpName.toUpperCase() + "%'";
    }

    List jobs;
    jobs = getDao().find(sqlStmt, new Object[] { branchName });

    if((jobs == null) || (jobs.size() <= 0))
      return new ArrayList();

    return jobs;
  }

  // End Template
  public synchronized String getJobNumberByBranch(Branch branch){
    double seqNumber = branch.getSeqNumber();

    if(seqNumber == 0)
      seqNumber = 1;
    else
      seqNumber = seqNumber + 1;

    Double jobSeqNum = new Double(seqNumber);
    Integer intJobSeqNum = new Integer(jobSeqNum.intValue());
    String seqNumStr = intJobSeqNum.toString();
    String updatedSeqNumber = seqNumStr;

    for(int i = 0; i < (7 - seqNumStr.length()); i++){
      updatedSeqNumber = "0" + updatedSeqNumber;
    }

    branch.setSeqNumber(seqNumber);
    try{
      getDao().save(branch);
    }
    catch (Exception e){
      System.out
          .println("exception while updating branch in getJobNumberByBranch() :"
              + e.toString());
    }
    return branch.getName() + "-" + updatedSeqNumber;
  }

  public JobOrder getJobOrderByJobNumber(String jobNumber){

    JobOrder joborder = null;
    List params = new ArrayList();
    params.add(jobNumber);
    List jobs = getDao()
        .find(
            "from JobOrder job left join fetch job.jobContracts left join fetch job.branch branch left join fetch branch.businessUnit left join fetch job.towingCompany left join fetch job.shippingAgent left join fetch job.serviceLocation where job.jobNumber = ?",
            new Object[] { jobNumber.toUpperCase() });
    /*
     * if (jobs.size() > 0) { return (JobOrder) jobs.get(0);} else { return
     * null;}
     */

    if(jobs != null && jobs.size() > 0){
      joborder = (JobOrder) jobs.get(0);
    }
    else{
      throw new ServiceException("record.not.exist.error",
          new Object[] { jobNumber }, null);
    }
    return joborder;
  }

  private JobOrder getExistJobOrderByJobNumber(String jobNumber){
    List jobs = getDao()
        .find(
            "from JobOrder job left join fetch job.branch branch left join fetch branch.businessUnit left join fetch job.towingCompany left join fetch job.shippingAgent left join fetch job.serviceLocation where job.jobNumber = ?",
            new Object[] { jobNumber.toUpperCase() });
    if(jobs.size() > 0){
      return (JobOrder) jobs.get(0);
    }
    else{
      return null;
    }
  }

  public JobOrder getJobOrderByJobNumberWithJobContracts(String jobNumber){
    List jobs = getDao()
        .find(
            "select job from JobOrder job "
                + " left join fetch job.branch branch left join fetch branch.businessUnit "
                + " left join fetch job.towingCompany "
                + " left join fetch job.shippingAgent "
                + " left join fetch job.serviceLocation "
                + " left join fetch job.jobContracts jc "
                + " left join fetch jc.customer "
                + " where job.jobNumber = ? ",
            new Object[] { jobNumber });

    if(jobs.size() > 0)
      return (JobOrder) jobs.get(0);

    return null;
  }

  public JobOrder getJobOrderByJobNumberWithDetail(String jobNumber){
    if(jobNumber == null)
      return null;

    List jobs = getDao()
        .find(
            "select job from JobOrder job "
                + " left join fetch job.branch branch left join fetch branch.businessUnit "
                + " left join fetch job.towingCompany "
                + " left join fetch job.shippingAgent "
                + " left join fetch job.serviceLocation "
                + " left join fetch job.jobContracts jc "
                + " left join fetch job.jobVessels jv "
                + " left join fetch jv.jobProds jp "
                + " left join fetch jp.jobProdContracts "
                + " left join fetch jp.jobProdQtys "
                + " left join fetch jp.jobEvents je "
                + " left join fetch je.event "
                + " left join fetch jp.jobProdSamples jps "
                + " left join fetch jps.jobTests jpst left join fetch jpst.test "
                + " left join fetch jps.jobManualTests "
                + " left join fetch jps.jobSlates jpss left join fetch jpss.slate "
                + " where job.jobNumber = ? ",
            new Object[] { jobNumber.toUpperCase() });

    if(jobs != null && jobs.size() > 0)
      return (JobOrder) jobs.get(0);

    return null;
  }

  public JobOrder getJobOrderByJobNumberWithInvoiceInfo(String jobNumber){
    if(jobNumber == null)
      return null;

    List jobs = getDao()
        .find(
            " from JobOrder job "
                + " left join fetch job.branch branch left join fetch branch.businessUnit "
                + " left join fetch job.towingCompany "
                + " left join fetch job.shippingAgent "
                + " left join fetch job.serviceLocation "
                + " left join fetch job.jobContracts jc "
                + " left join fetch jc.jobContractInvoices jci "
                + " left join fetch jci.invoiceFiles i left join fetch i.invoiceFileType "
                + " where job.jobNumber = ? order by jci.generatedDateTime",
            new Object[] { jobNumber.toUpperCase() });

    if(jobs != null && jobs.size() > 0)
      return (JobOrder) jobs.get(0);

    return null;
  }

  public List getJobContractsByJobNumber(String jobNumber){
    return getDao().find(
        "select distinct jobCon from JobContract jobCon   "
            + "where jobCon.jobNumber = ?  ",
        new Object[] { jobNumber });
  }

  public JobOrder addJobOrder(JobOrder jobOrder){

    String branchName = jobOrder.getBranchName();
    String servName = "";
    if(branchName == null){
      // throw new
      // RuntimeException("Branch name can not be null for job creation!"
      // );
      throw new ServiceException("job.branch.null.error",
          new Object[] {}, null);
    }
    String operations = jobOrder.getOperation();
    if(operations == null || operations.trim().equals("")){
      // throw new
      // RuntimeException("Operation can not be null for job creation!");
      throw new ServiceException("operation.job.null.error",
          new Object[] {}, null);
    }

    UserService userService = (UserService) ServiceLocator.getInstance()
        .getBean("userService");
    Branch existedBranch = userService.getBranchByName(branchName);
    if(existedBranch == null){
      // throw new RuntimeException("Branch can not be found: " +
      // branchName);
      throw new ServiceException("branch.not.exist.error",
          new Object[] { branchName }, null);
    }
    jobOrder.setBranchName(existedBranch.getName());
    String buName = jobOrder.getBuName();
    if(buName == null){
      // throw new
      // RuntimeException("bu name can not be null for job creation!");
      throw new ServiceException("bu.job.null.error", new Object[] {},
          null);
    }
    /*
     * if (jobOrder.getServiceLocation()!= null &&
     * jobOrder.getServiceLocation().getName()!= null &&
     * jobOrder.getServiceLocation().getName().trim().equals("")) {
     *
     * throw new RuntimeException(
     * "Servicelocation can not be null for job creation!"); }
     *
     * if (jobOrder.getServiceLocation()!= null &&
     * jobOrder.getServiceLocation().getCity()!= null &&
     * jobOrder.getServiceLocation().getCity().trim().equals("")) {
     *
     * throw new RuntimeException(
     * "Portlocation can not be null for job creation!"); }
     */
    BusinessUnit existedBu = userService.getBusinessUnitByName(buName);
    if(existedBu == null){
      // throw new RuntimeException("Business Unit does not exist: " +
      // buName);
      throw new ServiceException("businessUnit.error",
          new Object[] { buName }, null);
    }

    if(!buName.equals(existedBranch.getBuName())){
      // throw new
      // RuntimeException("Business unit name not match when inserting job"
      // + buName + ", " + existedBranch.getBuName());
      throw new ServiceException("bu.job.match.error",
          new Object[] { buName + ", " + existedBranch.getBuName() },
          null);

    }
    TowingCompany exitedTowingCompany = null;
    ShippingAgent existedShippingAgent = null;
    ServiceLocation exitedServiceLocation = null;

    if(jobOrder.getJobType().equals(Constants.INSPECTION_JOBTYPE)
        || jobOrder.getJobType().equals(Constants.MARINE_JOBTYPE)){
      ShippingAgentService shippingAgentService = (ShippingAgentService) ServiceLocator
          .getInstance().getBean("shippingAgentService");

      TowingCompanyService towingCompanyService = (TowingCompanyService) ServiceLocator
          .getInstance().getBean("towingCompanyService");

      ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
          .getInstance().getBean("serviceLocationService");
      CountryService countryService = (CountryService) ServiceLocator
          .getInstance().getBean("countryService");
      if(jobOrder.getShippingAgent().getId() == 0
          && jobOrder.getShippingAgent() != null){
        String shipName = "";
        String shipPhone = "";
        if(jobOrder.getShippingAgent().getName() != null){
          if(!jobOrder.getShippingAgent().getName().equals(""))
            shipName = jobOrder.getShippingAgent().getName();
        }

        if(!shipName.equals("")){
          existedShippingAgent = shippingAgentService
              .getShippingAgentByName(shipName);
          if(existedShippingAgent == null)
            throw new ServiceException("shippingAgent.error",
                new Object[] { jobOrder.getShippingAgent()
                    .getName() }, null);
        }
      }
      else{
        if(jobOrder.getShippingAgent().getId() != 0
            && jobOrder.getShippingAgent() != null){

          if(jobOrder.getShippingAgent().getName() != null){
            existedShippingAgent = shippingAgentService
                .getShippingAgentById(jobOrder
                    .getShippingAgent().getId());
            if(existedShippingAgent == null){
              // throw new
              // RuntimeException("Shipping Agent does not exist: "
              // + jobOrder.getShippingAgent().getName());
              throw new ServiceException("shippingAgent.error",
                  new Object[] { jobOrder.getShippingAgent()
                      .getName() }, null);
            }

          }
        }
      }

      if(jobOrder.getTowingCompany().getId() == 0
          && jobOrder.getTowingCompany() != null){
        String towCoName = "";
        String towCoPhone = "";
        if(jobOrder.getTowingCompany().getName() != null){
          if(!jobOrder.getTowingCompany().getName().equals(""))
            towCoName = jobOrder.getTowingCompany().getName();
        }
        if(jobOrder.getTowingCompany().getPhone() != null){
          if(!jobOrder.getTowingCompany().getPhone().equals(""))
            towCoPhone = jobOrder.getTowingCompany().getPhone();
        }

        if(!towCoName.equals("") || !towCoPhone.equals("")){
          exitedTowingCompany = towingCompanyService
              .getTowingCompanyByName(towCoName);

          if(exitedTowingCompany == null){
            // throw new
            // RuntimeException("Towing Company does not exist: " +
            // jobOrder.getTowingCompany().getName());
            throw new ServiceException("towingcompany.error",
                new Object[] { jobOrder.getTowingCompany()
                    .getName() }, null);
          }

        }
      }
      else{
        if(jobOrder.getTowingCompany().getId() != 0
            && jobOrder.getTowingCompany() != null){
          if(jobOrder.getTowingCompany().getName() != null){
            if(!jobOrder.getTowingCompany().getName().trim()
                .equals("")){
              exitedTowingCompany = towingCompanyService
                  .getTowingCompanyById(jobOrder
                      .getTowingCompany().getId());
              if(exitedTowingCompany == null){
                // throw new RuntimeException(
                // "Towing Company does not exist: " +
                // jobOrder.getTowingCompany().getName());
                throw new ServiceException(
                    "towingcompany.error",
                    new Object[] { jobOrder
                        .getTowingCompany().getName() },
                    null);
              }
            }

          }
        }
      }

      if(jobOrder.getServiceLocation() != null
          && jobOrder.getServiceLocation().getCity() != null
          && !jobOrder.getServiceLocation().getCity().trim().equals(
              "")){
        if(jobOrder.getServiceLocation().getName().equals(""))
          // throw new RuntimeException(
          // "Please Select Servicelocation for port/location: " +
          // jobOrder.getServiceLocation().getCity());
          throw new ServiceException(
              "select.servicelocation.port.error",
              new Object[] { jobOrder.getServiceLocation()
                  .getCity() }, null);
      }

      if(jobOrder.getServiceLocation() != null
          && jobOrder.getServiceLocation().getName() != null
          && !jobOrder.getServiceLocation().getName().trim().equals(
              "")){
        if(jobOrder.getServiceLocation().getCity().equals(""))
          // throw new RuntimeException(
          // "Please Select port/location for ServiceLocation: " +
          // jobOrder.getServiceLocation().getName());
          throw new ServiceException(
              "select.port.servicelocation.error",
              new Object[] { jobOrder.getServiceLocation()
                  .getCity() }, null);
      }
      if(jobOrder.getServiceLocation().getServiceLocationCode() == null
          && jobOrder.getServiceLocation().getName() != null
          && !jobOrder.getServiceLocation().getName().trim().equals(
              "")
          && jobOrder.getServiceLocation().getCity() != null
          && !jobOrder.getServiceLocation().getCity().trim().equals(
              "")){
        String servcLocationCity = "";
        String servcLocationName = "";
        String servCountryName = "";
        String servStateName = "";
        String serLocName = "";
        String serLocCity = "";
        String servLocName = "";
        String servLocPort = "";
        String servLocs = "";
        String servCity = "";
        if(jobOrder.getServiceLocation().getName() != null){
          if(!jobOrder.getServiceLocation().getName().trim().equals(
              ""))
            servLocName = jobOrder.getServiceLocation().getName();
        }
        if(jobOrder.getServiceLocation().getCity() != null){
          if(!jobOrder.getServiceLocation().getCity().trim().equals(
              "")){
            servLocPort = jobOrder.getServiceLocation().getCity();
          }
        }
        List serviceLocations = new ArrayList();
        if(!servLocName.equals("") || !servLocPort.equals("")){
          if(servLocName.indexOf(',') != -1){
            String servc[] = servLocName.split("\\,");
            String portLoc[] = servLocPort.split("\\,");
            if(servc.length == 3){
              servLocName = servc[0];
              servCity = servc[1];
              servCountryName = servc[2];
            }
            else{
              ServiceLocation serviceLocation = null;
              int servLocLength = servc.length;
              for(int i = 0; i < servLocLength;){

                if(i > 0)
                  serLocName = serLocName + "," + servc[i];
                else
                  serLocName = serLocName + servc[i];

                serviceLocation = serviceLocationService
                    .getServiceLocationByName(serLocName
                        .trim());

                if(serviceLocation != null){
                  servcLocationName = serLocName;
                  serLocName = serLocName;
                }
                else{
                  serLocName = serLocName;
                }
                i++;
              }
              int portLength = portLoc.length;
              for(int j = 0; j < portLength;){
                if(j > 0)
                  serLocCity = serLocCity + "," + portLoc[j];
                else
                  serLocCity = serLocCity + portLoc[j];
                serviceLocation = serviceLocationService
                    .getServiceLocationByCity(serLocCity
                        .trim());
                if(serviceLocation != null){
                  servcLocationCity = serLocCity;
                  serLocCity = serLocCity;
                }
                else{
                  serLocCity = serLocCity;
                }
                j++;
              }
              servLocName = servcLocationName;
              servCity = servcLocationCity;
              servStateName = servc[servLocLength - 2];
              State state = countryService
                  .getStateByName(servStateName);
              if(state == null)
                servStateName = "";
              servCountryName = servc[servLocLength - 1];
            }
          }
          else{
            servLocName = jobOrder.getServiceLocation().getName();
            servCity = jobOrder.getServiceLocation().getCity();
          }
        }
        if(!servLocName.equals("") && !servCity.equals("")
            && !servStateName.equals("")
            && !servCountryName.equals("")){
          serviceLocations = serviceLocationService
              .getServiceLocationByPortValues(servLocName,
                  servCity, servStateName, servCountryName);
        }
        else if(!servLocName.equals("") && !servCity.equals("")){
          serviceLocations = serviceLocationService
              .getServiceLocationByNameAndCity(servLocName,
                  servCity);
        }
        if(serviceLocations.size() > 0)
          exitedServiceLocation = (ServiceLocation) serviceLocations
              .get(0);
        if(exitedServiceLocation == null){
          // throw new RuntimeException(
          // "Service Location does not associated with port/location: "
          // + jobOrder.getServiceLocation().getName());
          throw new ServiceException("serviceLocation.port.error",
              new Object[] { jobOrder.getServiceLocation()
                  .getCity() }, null);

        }

        jobOrder.setServiceLocationCode(exitedServiceLocation
            .getServiceLocationCode());
        jobOrder.setShipToCustId(exitedServiceLocation.getCustCode());
        jobOrder.setShipToAddrNum(exitedServiceLocation
            .getLocationNumber().toString());

      }
      else{
        if(jobOrder.getServiceLocation().getName() != null){
          if(!jobOrder.getServiceLocation().getName().trim().equals(
              "")){
            exitedServiceLocation = serviceLocationService
                .getServiceLocationByServiceLocationCode(jobOrder
                    .getServiceLocation()
                    .getServiceLocationCode());
            if(exitedServiceLocation == null){
              // throw new RuntimeException(
              // "Service Location does not associated with port/location: "
              // + jobOrder.getServiceLocation().getName());
              throw new ServiceException(
                  "serviceLocation.port.error",
                  new Object[] { jobOrder
                      .getServiceLocation().getCity() },
                  null);

            }
            jobOrder.setServiceLocationCode(exitedServiceLocation
                .getServiceLocationCode());
            jobOrder.setShipToCustId(exitedServiceLocation
                .getCustCode());
            jobOrder.setShipToAddrNum(exitedServiceLocation
                .getLocationNumber().toString());
          }

        }

      }

    }
    if(jobOrder.getJobType().equals(Constants.ANALYTICAL_SERVICE_JOBTYPE)
        || jobOrder.getJobType().equals(Constants.OUTSOURCING_JOBTYPE)){
      String newJObDescp = "";
      int length = 0;
      boolean hasDate = false;
      ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
          .getInstance().getBean("serviceLocationService");

      exitedServiceLocation = serviceLocationService
          .getServiceLocationByBranchName(jobOrder.getBranchName());
      if(exitedServiceLocation == null){
        // throw new RuntimeException(
        // "Service Location does not exist for this Branch: " +
        // jobOrder.getBranchName());
        throw new ServiceException(
            "Service.Location.does.not.exist.error",
            new Object[] { jobOrder.getBranchName() }, null);
      }
      if(jobOrder.getJobDescription() != null
          && jobOrder.getJobDescription().trim().equals("")){

        Operation operation = getOperationByOperationCode(jobOrder
            .getOperation());
        if(operation != null)
          newJObDescp = operation.getDescription() + " " + "of";
        if(jobOrder.getProductNames() != null){
          if(!jobOrder.getProductNames().equals(""))
            newJObDescp = newJObDescp + " "
                + jobOrder.getProductNames();
        }
        servName = exitedServiceLocation.getName() + ","
            + exitedServiceLocation.getCity();
        String servState = "";
        String servCountry = "";
        CountryService countryService = (CountryService) ServiceLocator
            .getInstance().getBean("countryService");
        Country country = null;
        if(exitedServiceLocation.getCountryCode() != null){
          country = countryService
              .getCountryByCode(exitedServiceLocation
                  .getCountryCode());
        }

        if(exitedServiceLocation.getStateCode() != null
            && exitedServiceLocation.getCountryCode() != null){
          State state = countryService.getStateByCodeAndCountryCode(
              exitedServiceLocation.getStateCode(),
              exitedServiceLocation.getCountryCode());
          if(state != null
              && (country == null || country.getShowState())){
            servState = "," + state.getName();
          }
        }
        if(exitedServiceLocation.getCountryCode() != null){
          if(country != null)
            servCountry = "," + country.getName();
        }
        // end
        servName = servName + servState + servCountry;
        newJObDescp = newJObDescp + " " + "at" + " " + servName;
        // newJObDescp =
        // newJObDescp+" "+"at"+" "+exitedServiceLocation.getName();
        newJObDescp = newJObDescp + " " + "on";
        if(jobOrder.getJobFinishDate() != null){
          hasDate = true;
          String formatedDate = DateUtil
              .formateJobDescriptionDate(jobOrder
                  .getJobFinishDate());

          newJObDescp = newJObDescp + " " + formatedDate + " ";
        }
        if(!hasDate){
          if(jobOrder.getEtaDate() != null){
            String formatedDate = DateUtil
                .formateJobDescriptionDate(jobOrder
                    .getEtaDate());

            newJObDescp = newJObDescp + " " + formatedDate;
          }
        }

        // newJObDescp = newJObDescp+".";

        jobOrder.setJobDescription(newJObDescp);

      }
      else{
        if(jobOrder.getJobDescription().indexOf("of") != -1
            && jobOrder.getJobDescription().indexOf("on") != -1
            && jobOrder.getJobDescription().indexOf("at") != -1){
          String exitJObDescp = jobOrder.getJobDescription();

          String[] jobDesc = exitJObDescp.split("\\ ");

          for(int i = 0; i < jobDesc.length; i++)

          {
            if(jobDesc[i].equals("of")){

              for(int j = 0; j < i; j++)

              {
                newJObDescp = newJObDescp + jobDesc[j];

              }
            }
          }

          newJObDescp = newJObDescp + " " + "of";
          if(jobOrder.getProductNames() != null){
            if(!jobOrder.getProductNames().equals(""))
              newJObDescp = newJObDescp + " "
                  + jobOrder.getProductNames();
          }// setting portvalues
          servName = exitedServiceLocation.getName() + ","
              + exitedServiceLocation.getCity();

          String servState = "";
          String servCountry = "";
          CountryService countryService = (CountryService) ServiceLocator
              .getInstance().getBean("countryService");
          Country country2 = null;
          if(exitedServiceLocation.getCountryCode() != null){
            country2 = countryService
                .getCountryByCode(exitedServiceLocation
                    .getCountryCode());
          }
          if(exitedServiceLocation.getStateCode() != null
              && exitedServiceLocation.getCountryCode() != null){
            State state = countryService
                .getStateByCodeAndCountryCode(
                    exitedServiceLocation.getStateCode(),
                    exitedServiceLocation.getCountryCode());
            if(state != null
                && (country2 == null || country2.getShowState())){
              servState = "," + state.getName();
            }
          }
          if(exitedServiceLocation.getCountryCode() != null){
            if(country2 != null)
              servCountry = "," + country2.getName() + ",";
          }
          // end
          // servName = servName+servState+servCountry;
          // setting ony servname
          servName = exitedServiceLocation.getName();

          if(servName != null && servName.indexOf(",") != -1){
            String servLoc[] = servName.split(",");
            servCountry = servLoc[servLoc.length - 1];
            Country country = countryService
                .getCountryByCode(servCountry);
            if(country != null){
              servName = servName.replaceAll(servCountry, country
                  .getName());
              String newServLoc[] = servName.split(",");
              servName = "";
              for(int i = 0; i < newServLoc.length; i++){
                if(i == 0)
                  servName = newServLoc[i];
                else
                  servName = servName + "," + " "
                      + newServLoc[i];
              }
            }
            else{
              for(int i = 0; i < servLoc.length; i++){
                if(i == 0)
                  servName = servLoc[i].trim();
                else
                  servName = servName + "," + " "
                      + servLoc[i].trim();
              }
            }
          }
          // end
          newJObDescp = newJObDescp + " " + "at" + " " + servName;
          newJObDescp = newJObDescp + " " + "on";
          if(jobOrder.getJobFinishDate() != null){
            hasDate = true;
            String formatedDate = DateUtil
                .formateJobDescriptionDate(jobOrder
                    .getJobFinishDate());

            newJObDescp = newJObDescp + " " + formatedDate;
          }
          if(!hasDate){
            if(jobOrder.getEtaDate() != null){
              String formatedDate = DateUtil
                  .formateJobDescriptionDate(jobOrder
                      .getEtaDate());

              newJObDescp = newJObDescp + " " + formatedDate;
            }
          }

          // newJObDescp = newJObDescp+".";
          jobOrder.setJobDescription(newJObDescp);
        }
      }
      jobOrder.setServiceLocationCode(exitedServiceLocation
          .getServiceLocationCode());
      jobOrder.setShipToCustId(exitedServiceLocation.getCustCode());
      jobOrder.setShipToAddrNum(exitedServiceLocation.getLocationNumber()
          .toString());
    }
    String receivedBy = jobOrder.getReceivedByUserName();
    User user = null;
    if(receivedBy != null && (!receivedBy.trim().equals(""))){
      user = userService.getUserByName(receivedBy);
      if(user == null){
        // throw new
        // RuntimeException("Received By User does not exist: " +
        // receivedBy);
        throw new ServiceException("received.user.not.exist.error",
            new Object[] { receivedBy }, null);

      }
    }

    jobOrder.setReceivedBy(user);

    jobOrder.setBranch(existedBranch);
    jobOrder.setBusinessUnit(existedBu);
    jobOrder.setShippingAgent(existedShippingAgent);
    jobOrder.setTowingCompany(exitedTowingCompany);
    jobOrder.setServiceLocation(exitedServiceLocation);
    String JobNumber = getJobNumberByBranch(existedBranch);
    jobOrder.setCreatedByUserName(SecurityUtil.getUser().getLoginName());
    List users = userService.getUsersByName(SecurityUtil.getUser()
        .getLoginName());

    if(users.size() > 0)
      jobOrder.setCreatedBy((User) users.get(0));
    jobOrder.setJobNumber(JobNumber);
    if(jobOrder.getIsTemplate() == null)
      jobOrder.setIsTemplate(false);
    return getDao().save(jobOrder);
  }

  public BatchReprint saveBatchReprint(BatchReprint batchReprint){
    return getDao().save(batchReprint);
  }

  public JobOrder saveJobOrder(JobOrder jobOrder){

    if(jobOrder == null)
      return null;

    String branchName = jobOrder.getBranchName();
    if(branchName == null){
      // throw new
      // RuntimeException("Branch name can not be null for job creation!"
      // );
      throw new ServiceException("job.branch.null.error",
          new Object[] {}, null);
    }
    String operations = jobOrder.getOperation();
    if(operations == null || operations.trim().equals("")){
      // throw new
      // RuntimeException("Operation can not be null for job creation!");
      throw new ServiceException("operation.job.null.error",
          new Object[] {}, null);
    }

    UserService userService = (UserService) ServiceLocator.getInstance()
        .getBean("userService");
    Branch existedBranch = userService.getBranchByName(branchName);
    if(existedBranch == null){
      // throw new RuntimeException("Branch can not be found: " +
      // branchName);
      throw new ServiceException("branch.not.exist.error",
          new Object[] { branchName }, null);
    }
    jobOrder.setBranchName(existedBranch.getName());
    String buName = jobOrder.getBuName();
    if(buName == null){
      // throw new
      // RuntimeException("bu name can not be null for job creation!");
      throw new ServiceException("bu.job.null.error", new Object[] {},
          null);
    }

    BusinessUnit existedBu = userService.getBusinessUnitByName(buName);
    if(existedBu == null){
      // throw new RuntimeException("Business Unit does not exist: " +
      // buName);
      throw new ServiceException("businessUnit.error",
          new Object[] { buName }, null);
    }

    if(!buName.equals(existedBranch.getBuName())){
      // throw new RuntimeException(
      // "Business unit name not match when inserting job: " + buName +
      // ", " + existedBranch.getBuName());
      throw new ServiceException("bu.job.match.error",
          new Object[] { buName + ", " + existedBranch.getBuName() },
          null);
    }
    /*
     * if (jobOrder.getServiceLocation()!= null &&
     * jobOrder.getServiceLocation().getName()!= null &&
     * jobOrder.getServiceLocation().getName().trim().equals("")) {
     *
     * throw new RuntimeException(
     * "Servicelocation can not be null for job creation!"); } if
     * (jobOrder.getServiceLocation()!= null &&
     * jobOrder.getServiceLocation().getCity()!= null &&
     * jobOrder.getServiceLocation().getCity().trim().equals("")) {
     *
     * throw new RuntimeException(
     * "Portlocation can not be null for job creation!"); }
     */
    TowingCompany exitedTowingCompany = null;
    ShippingAgent existedShippingAgent = null;
    ServiceLocation exitedServiceLocation = null;

    if(jobOrder.getJobType().equals(Constants.INSPECTION_JOBTYPE)
        || jobOrder.getJobType().equals(Constants.MARINE_JOBTYPE)){
      ShippingAgentService shippingAgentService = (ShippingAgentService) ServiceLocator
          .getInstance().getBean("shippingAgentService");
      TowingCompanyService towingCompanyService = (TowingCompanyService) ServiceLocator
          .getInstance().getBean("towingCompanyService");

      ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
          .getInstance().getBean("serviceLocationService");
      CountryService countryService = (CountryService) ServiceLocator
          .getInstance().getBean("countryService");

      if(jobOrder.getShippingAgent() != null){
        String shipName = "";
        String shipPhone = "";
        if(jobOrder.getShippingAgent().getName() != null){
          if(!jobOrder.getShippingAgent().getName().equals(""))
            shipName = jobOrder.getShippingAgent().getName();
        }

        if(!shipName.equals("") && !shipPhone.equals("")){
          // jobOrder.getShippingAgent().setPhone("");
          existedShippingAgent = shippingAgentService
              .getShippingAgentByName(shipName);
          if(existedShippingAgent == null){
            throw new ServiceException("shippingAgent.error",
                new Object[] { jobOrder.getShippingAgent()
                    .getName() }, null);
          }

        }
        else if(!shipName.equals("") && shipPhone.equals("")){
          existedShippingAgent = shippingAgentService
              .getShippingAgentByName(shipName);
          if(existedShippingAgent == null)
            throw new ServiceException("shippingAgent.error",
                new Object[] { jobOrder.getShippingAgent()
                    .getName() }, null);
        }
        else{
          existedShippingAgent = null;
        }

      }
      if(jobOrder.getTowingCompany() != null){
        String towCoName = "";
        String towCoPhone = "";
        if(jobOrder.getTowingCompany().getName() != null){
          if(!jobOrder.getTowingCompany().getName().equals(""))
            towCoName = jobOrder.getTowingCompany().getName();
        }
        if(jobOrder.getTowingCompany().getPhone() != null){
          if(!jobOrder.getTowingCompany().getPhone().equals(""))
            towCoPhone = jobOrder.getTowingCompany().getPhone();
        }

        if(!towCoName.equals("")){
          exitedTowingCompany = towingCompanyService
              .getTowingCompanyByName(towCoName);
          if(exitedTowingCompany == null){
            throw new ServiceException("towingcompany.error",
                new Object[] { jobOrder.getTowingCompany()
                    .getName() }, null);
          }

        }
        else{
          exitedTowingCompany = null;
        }
      }
      if(jobOrder.getServiceLocation() != null
          && jobOrder.getServiceLocation().getCity() != null
          && !jobOrder.getServiceLocation().getCity().trim().equals(
              "")){
        if(jobOrder.getServiceLocation().getName().equals(""))
          // throw new RuntimeException(
          // "Please Select Servicelocation for port/location: " +
          // jobOrder.getServiceLocation().getCity());
          throw new ServiceException(
              "select.servicelocation.port.error",
              new Object[] { jobOrder.getServiceLocation()
                  .getCity() }, null);
      }

      if(jobOrder.getServiceLocation() != null
          && jobOrder.getServiceLocation().getName() != null
          && !jobOrder.getServiceLocation().getName().trim().equals(
              "")){
        if(jobOrder.getServiceLocation().getCity().equals(""))
          // throw new RuntimeException(
          // "Please Select port/location for ServiceLocation: " +
          // jobOrder.getServiceLocation().getName());
          throw new ServiceException(
              "select.port.servicelocation.error",
              new Object[] { jobOrder.getServiceLocation()
                  .getCity() }, null);
      }

      if(jobOrder.getServiceLocation() != null){
        String servcLocationCity = "";
        String servcLocationName = "";
        String servCountryName = "";
        String servStateName = "";
        String servLocName = "";
        String servLocPort = "";
        String serLocName = "";
        String serLocCity = "";
        String servLocs = "";
        String servCity = "";
        if(jobOrder.getServiceLocation().getName() != null){
          if(!jobOrder.getServiceLocation().getName().equals(""))
            servLocName = jobOrder.getServiceLocation().getName();
        }
        if(jobOrder.getServiceLocation().getCity() != null){
          if(!jobOrder.getServiceLocation().getCity().equals("")){
            servLocPort = jobOrder.getServiceLocation().getCity();
          }
        }
        if(!servLocName.equals("") || !servLocPort.equals("")){

          if(servLocName.indexOf(',') != -1){
            String servc[] = servLocName.split("\\,");
            String portLoc[] = servLocPort.split("\\,");
            ServiceLocation serviceLocation = null;
            if(servc.length == 3){
              servLocName = servc[0];
              servCity = servc[1];
              servCountryName = servc[2];
            }
            else{
              int servLocLength = servc.length;
              for(int i = 0; i < servLocLength;){
                if(i > 0)
                  serLocName = serLocName + "," + servc[i];
                else
                  serLocName = serLocName + servc[i];
                serviceLocation = serviceLocationService
                    .getServiceLocationByName(serLocName
                        .trim());

                if(serviceLocation != null){
                  servcLocationName = serLocName;
                  serLocName = serLocName;
                }
                else{
                  serLocName = serLocName;
                }
                i++;
              }
              int portLength = portLoc.length;
              for(int j = 0; j < portLength;){
                if(j > 0)
                  serLocCity = serLocCity + "," + portLoc[j];
                else
                  serLocCity = serLocCity + portLoc[j];
                serviceLocation = serviceLocationService
                    .getServiceLocationByCity(serLocCity
                        .trim());
                if(serviceLocation != null){
                  servcLocationCity = serLocCity;
                  serLocCity = serLocCity;
                }
                else{
                  serLocCity = serLocCity;
                }
                j++;
              }
              servLocName = servcLocationName;
              servCity = servcLocationCity;
              servStateName = servc[servLocLength - 2];
              State state = countryService
                  .getStateByName(servStateName);
              if(state == null)
                servStateName = "";
              servCountryName = servc[servLocLength - 1];
            }
          }
          else{
            servLocName = jobOrder.getServiceLocation().getName();
            servCity = jobOrder.getServiceLocation().getCity();
          }

          List serviceLocations = new ArrayList();
          if(!servLocName.equals("") && !servCity.equals("")
              && !servStateName.equals("")
              && !servCountryName.equals("")){
            serviceLocations = serviceLocationService
                .getServiceLocationByPortValues(servLocName,
                    servCity, servStateName,
                    servCountryName);
          }
          else if(!servLocName.equals("") && !servCity.equals("")){
            serviceLocations = serviceLocationService
                .getServiceLocationByNameAndCity(servLocName,
                    servCity);
          }
          if(serviceLocations.size() > 0)
            exitedServiceLocation = (ServiceLocation) serviceLocations
                .get(0);
          if(exitedServiceLocation == null){
            // throw new RuntimeException(
            // "Service Location does not associated with port/location: "
            // + jobOrder.getServiceLocation().getName());
            throw new ServiceException(
                "serviceLocation.port.error",
                new Object[] { jobOrder.getServiceLocation()
                    .getCity() }, null);

          }
        }
        else if(!servLocName.equals("")){
          exitedServiceLocation = serviceLocationService
              .getServiceLocationByServiceLocationNameAndCity(
                  servLocName, servLocPort);
          if(exitedServiceLocation == null){
            // throw new RuntimeException(
            // "Service Location does not associated with port/location: "
            // + jobOrder.getServiceLocation().getName());
            throw new ServiceException(
                "serviceLocation.port.error",
                new Object[] { jobOrder.getServiceLocation()
                    .getCity() }, null);
          }
        }
        else{
          exitedServiceLocation = null;
        }
        if(exitedServiceLocation != null){
          jobOrder.setServiceLocationCode(exitedServiceLocation
              .getServiceLocationCode());
          jobOrder.setShipToCustId(exitedServiceLocation
              .getCustCode());
          jobOrder.setShipToAddrNum(exitedServiceLocation
              .getLocationNumber().toString());

        }
        else{
          jobOrder.setServiceLocationCode(null);
          jobOrder.setShipToCustId(null);
          jobOrder.setShipToAddrNum(null);
        }

      }
    }
    String receivedBy = jobOrder.getReceivedByUserName();
    User user = null;
    if(receivedBy != null && (!receivedBy.trim().equals(""))){
      user = userService.getUserByName(receivedBy);
      if(user == null){
        // throw new
        // RuntimeException("Received By User does not exist: " +
        // receivedBy);
        throw new ServiceException("received.user.not.exist.error",
            new Object[] { receivedBy }, null);

      }

    }
    jobOrder.setReceivedBy(user);

    jobOrder.setBranch(existedBranch);
    jobOrder.setBusinessUnit(existedBu);
    jobOrder.setShippingAgent(existedShippingAgent);
    jobOrder.setTowingCompany(exitedTowingCompany);
    jobOrder.setServiceLocation(exitedServiceLocation);

    jobOrder.setUpdatedBy(SecurityUtil.getUser());

    jobOrder.setUpdatedByUserName(SecurityUtil.getUser().getLoginName());

    List users = userService.getUsersByName(SecurityUtil.getUser()
        .getLoginName());
    if(users.size() > 0)
      jobOrder.setUpdatedBy((User) users.get(0));

    if(jobOrder.getIsTemplate() == null)
      jobOrder.setIsTemplate(false);
    // Setting job reopen date
    JobOrder jobOrders = getJobOrderByJobNumber(jobOrder.getJobNumber());
    if(jobOrders != null
        && jobOrders.getJobStatus() != null
        && jobOrders.getJobStatus().trim().equals(
            Constants.REOPENED_STATUS)
        && jobOrders.getReOpenDate() != null){

    }
    else if(jobOrder != null
        && jobOrder.getJobStatus() != null
        && jobOrder.getJobStatus().trim().equals(
            Constants.REOPENED_STATUS)){

      jobOrder.setReOpenDate(new Date());
    }
    // end

    // Setting jobContract vatRegCountry
    try{
      Set jobContracts = jobOrder.getJobContracts();
      if(jobContracts != null && jobContracts.size() > 0){

        Set newJobContracts = new HashSet();
        for(Iterator itr = jobContracts.iterator(); itr.hasNext();){
          JobContract jobContract = (JobContract) itr.next();
          Country vatRegCountry = getCountryCodeByCustCodeAndVatRegId(
              jobContract.getCustCode(), jobContract
                  .getVatRegId());
          jobContract.setVatRegCountry(vatRegCountry);
          newJobContracts.add(jobContract);
        }
        jobOrder.setJobContracts(newJobContracts);
      }
    }
    catch (Exception e){
    }
    // End
    return getDao().save(jobOrder);
  }

  public JobOrder updateJobOrder(JobOrder jobOrder){
    // Setting job reopen date

    JobOrder jobOrders = getExistJobOrderByJobNumber(jobOrder
        .getJobNumber());

    if(jobOrders != null
        && jobOrders.getJobStatus() != null
        && jobOrders.getJobStatus().trim().equals(
            Constants.REOPENED_STATUS)
        && jobOrders.getReOpenDate() != null){

    }
    else if(jobOrder != null
        && jobOrder.getJobStatus() != null
        && jobOrder.getJobStatus().trim().equals(
            Constants.REOPENED_STATUS)){

      jobOrder.setReOpenDate(new Date());
    }

    // end
    // Setting jobContract vatRegCountry
    try{
      Set jobContracts = jobOrder.getJobContracts();
      if(jobContracts != null && jobContracts.size() > 0){

        Set newJobContracts = new HashSet();
        for(Iterator itr = jobContracts.iterator(); itr.hasNext();){
          JobContract jobContract = (JobContract) itr.next();
          Country vatRegCountry = getCountryCodeByCustCodeAndVatRegId(
              jobContract.getCustCode(), jobContract
                  .getVatRegId());
          jobContract.setVatRegCountry(vatRegCountry);
          newJobContracts.add(jobContract);
        }
        jobOrder.setJobContracts(newJobContracts);
      }
    }
    catch (Exception e){
    }

    // End
    return getDao().save(jobOrder);
  }

  /*
   * public void searchJobOrder(JobSearch jobSearch) { if(jobSearch == null)
   * return;
   *
   * StringBuffer sb = new StringBuffer(); List params = new ArrayList();
   *
   * boolean hasWhere = false; String buName =
   * jobSearch.getBusinessUnit().getValue(); if((buName != null) &&
   * !"".equals(buName.trim())) {
   * sb.append(" where job.branch.businessUnit.name = ?"); params.add(buName);
   * hasWhere = true; }
   *
   * String branchName = jobSearch.getBranch().getValue(); if((branchName !=
   * null) && !"".equals(branchName.trim())) { if(hasWhere)
   * sb.append(" and "); else sb.append(" where ");
   * sb.append(" job.branch.name = ?"); params.add(branchName); }
   *
   * Pagination pagination = jobSearch.getPagination(); List results = null;
   *
   * if(pagination != null) { if(pagination.getTotalRecord() > 0) { List
   * counts = getDao().find( "select count(job.id) from JobOrder job " +
   * sb.toString(), params.toArray() );
   *
   * if(counts.size() > 0) { Number count = (Number)counts.get(0);
   * pagination.setTotalRecord(count.intValue()); } }
   *
   * results = getDao().find(
   * "select distinct job from JobOrder job left join fetch job.branch branch left join fetch job.branch.businessUnit "
   * + sb.toString(), params.toArray(), pagination );
   *
   * pagination.calculatePages(); } else { results = getDao().find(
   * "select distinct job from JobOrder job left join fetch job.branch branch left join fetch job.branch.businessUnit "
   * + sb.toString(), params.toArray() ); }
   *
   * jobSearch.setResults(results); }
   */
  public void searchVessel(VesselSearch search){
    if(search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;
    String vesselType = search.getVesselType().getValue();

    if((vesselType != null) && !"".equals(vesselType.trim())){
      sb.append(" where v.vesselTypeId.vesselType like ?");
      String vesselTypeSearch = '%' + vesselType.trim() + '%';
      params.add(vesselTypeSearch);
      hasWhere = true;
    }

    String vesselSet = search.getVesselSet().getValue();
    if((vesselSet != null) && !"".equals(vesselSet.trim())){
      if(hasWhere)
        sb.append(" and ");
      else
        sb.append(" where ");
      sb.append(" v.vesselTypeId.vesselSet = ?");
      params.add(vesselSet);
    }
    Pagination pagination = search.getPagination();
    List results = null;

    if(pagination != null){
      if(pagination.getTotalRecord() > 0){
        List counts = getDao().find(
            "select count(*) from VesselType v " + sb.toString(),
            params.toArray());
        if(counts.size() > 0){
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
          "select distinct v from VesselType v  " + sb.toString(),
          params.toArray(), pagination);

      pagination.calculatePages();
    }
    else{
      results = getDao().find(
          "select distinct v from VesselType v " + sb.toString(),
          params.toArray());
    }

    search.setResults(results);
  }

  public void searchTest(TestSearch search){
    if(search == null)
      return;

    StringBuffer sb = new StringBuffer();
    StringBuffer sbSearch = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String criteria1 = search.getCriteria1().getValue();
    String criteria2 = search.getCriteria2().getValue();
    String testSearchStr = search.getTestSearch().getValue();
    String productGroup = search.getProductGroup().getValue();
    String chosenContracts = search.getChosenContracts();

    PriceBook priceBook = null;

    String pbSeries = "";
    String jobContractCode = "";
    String contractCode = "";
    CfgContract cfgContract = null;
    if(chosenContracts != null){
      CalculatorService calculatorService = (CalculatorService) ServiceLocator
          .getInstance().getBean("calculatorService");
      StringTokenizer st = new StringTokenizer(chosenContracts, ";");

      while (st.hasMoreTokens()){
        jobContractCode = st.nextToken();
        contractCode = getJobContractById(
            new Long(jobContractCode).longValue())
            .getContractCode();
        cfgContract = calculatorService.getContractByContractId(
            contractCode, search.getNominationDate());
        if(cfgContract != null){
          String priceBookId = cfgContract.getPriceBookId();
          if(Constants.CURRENT.equals(priceBookId)){
            PriceBook pb = calculatorService.getCurrentPriceBook(
                cfgContract.getPbSeries(), cfgContract
                    .getCurrencyCD(), search
                    .getNominationDate());
            if(pb != null)
              cfgContract.setPriceBookId(pb.getPriceBookId()
                  .getPriceBookId());
          }
        }

      }
    }

    TestService testService = (TestService) ServiceLocator.getInstance()
        .getBean("testService");

    List searchTests = testService.getTests(contractCode, cfgContract
        .getPriceBookId(), search.getProductGroup().getValue(), search
        .getCriteria1().getValue(), search.getTestSearch().getValue(),
        search.getCriteria2().getValue(), "*", DateUtil.formatDate(
            search.getNominationDate(), "yyyyMMdd"), "ENG");

    /*
     * if(criteria1 != null && (!criteria1.trim().equals(""))) {
     * if(criteria1.equalsIgnoreCase("Pricebook")) { if(criteria2 != null &&
     * (!criteria2.trim().equals(""))) {
     * if(criteria2.equalsIgnoreCase("Methodology")) { if(productGroup !=
     * null) {
     * sb.append("from Test t, TestPriceBook tpb, TestProductGroup tpg " +
     * "where t.testId = tpb.testPriceBookId.testId and tpg.testProductGroupId.testId = t.testId"
     * +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.methodTitle) like ?"
     * ); params.add(productGroup); String methodSearch = '%' +
     * testSearchStr + '%'; params.add(methodSearch); } }
     * if(criteria2.equalsIgnoreCase("Description")) { if(productGroup !=
     * null) {
     * sb.append("from Test t, TestPriceBook tpb, TestProductGroup tpg " +
     * "where t.testId = tpb.testPriceBookId.testId and tpg.testProductGroupId.testId = t.testId"
     * +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.testDescription) like ?"
     * ); params.add(productGroup); String descSearch = '%' + testSearchStr
     * + '%'; params.add(descSearch); } }
     * if(criteria2.equalsIgnoreCase("Both")) { if(productGroup != null) {
     * sb.append("from Test t, TestPriceBook tpb, TestProductGroup tpg " +
     * "where t.testId = tpb.testPriceBookId.testId and tpg.testProductGroupId.testId = t.testId"
     * +
     * " and tpg.testProductGroupId.testProductId =  ? and (upper(t.testDescription) like ?"
     * + " or upper(t.methodTitle) like ?)"); params.add(productGroup);
     * String descSearch = '%' + testSearchStr + '%';
     * params.add(descSearch); params.add(descSearch); } } } else //If
     * criteria 2 == null { if(productGroup != null) {
     * sb.append("from Test t, TestPriceBook tpb, TestProductGroup tpg " +
     * "where t.testId = tpb.testPriceBookId.testId and tpg.testProductGroupId.testId = t.testId "
     * + " and tpg.testProductGroupId.testProductId =  ? ");
     * params.add(productGroup);
     *
     * } } } if(criteria1.equalsIgnoreCase("Contract")) { StringTokenizer st
     * = new StringTokenizer(chosenContracts,";"); String contractSearchStr
     * =""; while(st.hasMoreTokens()) { String contractCode =
     * st.nextToken(); if(contractSearchStr.trim().equals(""))
     * contractSearchStr = "'"+ contractCode + "',"; else contractSearchStr
     * = contractSearchStr + "'"+ contractCode + "',"; } contractSearchStr =
     * contractSearchStr.substring(0, contractSearchStr.length()-1);
     *
     * if(criteria2 != null && (!criteria2.trim().equals(""))) {
     * if(criteria2.equalsIgnoreCase("Methodology")) { if(productGroup !=
     * null) {
     * sb.append("from Test t, ContractTest ct, TestProductGroup tpg " +
     * "where t.testId = ct.contractTestId.testId and tpg.testProductGroupId.testId = t.testId"
     * +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.methodTitle) like ? and ct.contractTestId.contractId in (?)"
     * ); params.add(productGroup); String methodSearch = '%' +
     * testSearchStr + '%'; params.add(methodSearch);
     * params.add(contractSearchStr); } }
     * if(criteria2.equalsIgnoreCase("Description")) { if(productGroup !=
     * null) {
     * sb.append("from Test t, ContractTest ct, TestProductGroup tpg " +
     * "where t.testId = ct.contractTestId.testId and tpg.testProductGroupId.testId = t.testId"
     * +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.testDescription) like ? and ct.contractTestId.contractId in (?)"
     * ); params.add(productGroup); String descSearch = '%' + testSearchStr
     * + '%'; params.add(descSearch); params.add(contractSearchStr); } }
     * if(criteria2.equalsIgnoreCase("Both")) { if(productGroup != null) {
     * sb.append("from Test t, ContractTest ct, TestProductGroup tpg " +
     * "where t.testId = ct.contractTestId.testId and tpg.testProductGroupId.testId = t.testId"
     * +
     * " and tpg.testProductGroupId.testProductId =  ? and (upper(t.testDescription) like ?"
     * +
     * " or upper(t.methodTitle) like ?) and ct.contractTestId.contractId in (?)"
     * ); params.add(productGroup); String descSearch = '%' + testSearchStr
     * + '%'; params.add(descSearch); params.add(descSearch);
     * params.add(contractSearchStr); } } } else //If criteria 2 == null {
     * if(productGroup != null) {
     * sb.append("from Test t, ContractTest ct, TestProductGroup tpg " +
     * "where t.testId = ct.contractTestId.testId and tpg.testProductGroupId.testId = t.testId "
     * +
     * " and tpg.testProductGroupId.testProductId =  ? and ct.contractTestId.contractId in (?) "
     * ); params.add(productGroup); params.add(contractSearchStr);
     *
     * } } } if(criteria1.equalsIgnoreCase("Both")) { StringTokenizer st =
     * new StringTokenizer(chosenContracts,";"); String contractSearchStr
     * =""; while(st.hasMoreTokens()) { String contractCode =
     * st.nextToken(); if(contractSearchStr.trim().equals(""))
     * contractSearchStr = "'"+ contractCode + "',"; else contractSearchStr
     * = contractSearchStr + "'"+ contractCode + "',"; } contractSearchStr =
     * contractSearchStr.substring(0, contractSearchStr.length()-1);
     * System.out.println("contractSearchStr :"+contractSearchStr);
     *
     * if(criteria2 != null && (!criteria2.trim().equals(""))) {
     * if(criteria2.equalsIgnoreCase("Methodology")) { if(productGroup !=
     * null) {sb.append(
     * "from Test t, ContractTest ct, TestPriceBook tpb, TestProductGroup tpg "
     * +
     * "where t.testId = ct.contractTestId.testId and t.testId = tpb.testPriceBookId.testId "
     * + "and ct.contractTestId.testId = tpb.testPriceBookId.testId and " +
     * "tpg.testProductGroupId.testId = t.testId" +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.methodTitle) like ? "
     * + "and ct.contractTestId.contractId in (?)");
     * params.add(productGroup); String methodSearch = '%' + testSearchStr +
     * '%'; params.add(methodSearch); params.add(contractSearchStr); } }
     * if(criteria2.equalsIgnoreCase("Description")) { if(productGroup !=
     * null) {sb.append(
     * "from Test t, ContractTest ct, TestPriceBook tpb, TestProductGroup tpg "
     * +
     * "where t.testId = ct.contractTestId.testId and t.testId = tpb.testPriceBookId.testId "
     * + "and ct.contractTestId.testId = tpb.testPriceBookId.testId and " +
     * "tpg.testProductGroupId.testId = t.testId" +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.testDescription) like ? "
     * + "and ct.contractTestId.contractId in (?)");
     * params.add(productGroup); String descSearch = '%' + testSearchStr +
     * '%'; params.add(descSearch); params.add(contractSearchStr); } }
     * if(criteria2.equalsIgnoreCase("Both")) { if(productGroup != null) {
     * sb.append(
     * "from Test t, ContractTest ct, TestPriceBook tpb,TestProductGroup tpg "
     * +
     * "where t.testId = ct.contractTestId.testId and t.testId = tpb.testPriceBookId.testId "
     * + "and ct.contractTestId.testId = tpb.testPriceBookId.testId and " +
     * "tpg.testProductGroupId.testId = t.testId" +
     * " and tpg.testProductGroupId.testProductId =  ? and (upper(t.testDescription) like ?"
     * +
     * " or upper(t.methodTitle) like ?) and ct.contractTestId.contractId in (?)"
     * ); params.add(productGroup); String descSearch = '%' + testSearchStr
     * + '%'; params.add(descSearch); params.add(descSearch);
     * params.add(contractSearchStr); } } } else //If criteria 2 == null {
     * if(productGroup != null) {sb.append(
     * "from Test t, ContractTest ct, TestPriceBook tpb,TestProductGroup tpg "
     * +
     * "where t.testId = ct.contractTestId.testId and t.testId = tpb.testPriceBookId.testId "
     * + " and ct.contractTestId.testId = tpb.testPriceBookId.testId " +
     * "and tpg.testProductGroupId.testId = t.testId " +
     * " and tpg.testProductGroupId.testProductId =  ? and ct.contractTestId.contractId in (?) "
     * ); params.add(productGroup); params.add(contractSearchStr);
     *
     * } } } } else //Criteria1 == null { if(criteria2 != null &&
     * (!criteria2.trim().equals(""))) {
     * if(criteria2.equalsIgnoreCase("Methodology")) { if(productGroup !=
     * null) { sb.append("from Test t, TestProductGroup tpg " +
     * "where tpg.testProductGroupId.testId = t.testId" +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.methodTitle) like ? "
     * ); params.add(productGroup); String methodSearch = '%' +
     * testSearchStr + '%'; params.add(methodSearch);
     *
     * } } if(criteria2.equalsIgnoreCase("Description")) { if(productGroup
     * != null) { sb.append("from Test t, TestProductGroup tpg " +
     * "where tpg.testProductGroupId.testId = t.testId" +
     * " and tpg.testProductGroupId.testProductId =  ? and upper(t.testDescription) like ? "
     * ); params.add(productGroup); String descSearch = '%' + testSearchStr
     * + '%'; params.add(descSearch);
     *
     * } } if(criteria2.equalsIgnoreCase("Both")) { if(productGroup != null)
     * { sb.append("from Test t, TestProductGroup tpg " +
     * "where tpg.testProductGroupId.testId = t.testId" +
     * " and tpg.testProductGroupId.testProductId =  ? and (upper(t.testDescription) like ?"
     * + " or upper(t.methodTitle) like ?) "); params.add(productGroup);
     * String descSearch = '%' + testSearchStr + '%';
     * params.add(descSearch); params.add(descSearch);
     *
     * } } } else //If criteria 2 == null { if(productGroup != null) {
     * sb.append("from Test as t, TestProductGroup as tpg " +
     * "where t.testId = tpg.testProductGroupId.testId and tpg.testProductGroupId.testProductId = ? "
     * ); params.add(productGroup);
     *
     *
     * } } }
     *
     *
     * System.out.println("params size: "+params.size()); Pagination
     * pagination = search.getPagination(); List results = null;
     * System.out.println("final query :"+sb.toString()); if(pagination !=
     * null) { if(pagination.getTotalRecord() > 0) { List counts =
     * getDao().find( //"select count(*) from Test t " + sb.toString(),
     * "select count(*)  " + sb.toString(), params.toArray() );
     * System.out.println
     * ("after executing pagination query: "+counts.size());
     * if(counts.size() > 0) { Number count = (Number)counts.get(0);
     * pagination.setTotalRecord(count.intValue()); } }
     *
     * results = getDao().find( sb.toString(), params.toArray(), pagination
     * );
     *
     * pagination.calculatePages(); } else { results = getDao().find(
     * sb.toString(), params.toArray() ); }
     * System.out.println("result size : "+results.size()); //
     * System.out.println("result object :"+ (Test) results.get(0));
     */
    search.setResults(searchTests);
  }

  public List getJObIdsById(String jobId){
    if(jobId == null)
      return new ArrayList();
    List jobIds = new ArrayList();
    try{
      jobIds = getDao().find(
          "from JobOrder j where j.jobNumber like '"
              + jobId.toUpperCase() + "%'", null);
    }
    catch (Exception e){
      System.out.println("Exception in getJobIdsByJobid" + e.toString());
    }
    return jobIds;

  }

  public void searchSlate(SlateSearch search){
    if(search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String criteria = search.getCriteria().getValue();

    String slateSearchStr = search.getSearchStr().getValue();

    String chosenContracts = search.getChosenContracts();

    PriceBook priceBook = null;

    String pbSeries = "";
    String jobContractCode = "";
    String contractCode = "";
    CfgContract cfgContract = null;
    if(chosenContracts != null){
      CalculatorService calculatorService = (CalculatorService) ServiceLocator
          .getInstance().getBean("calculatorService");
      StringTokenizer st = new StringTokenizer(chosenContracts, ";");

      while (st.hasMoreTokens()){

        jobContractCode = st.nextToken();
        contractCode = getJobContractById(
            new Long(jobContractCode).longValue())
            .getContractCode();
        cfgContract = calculatorService.getContractByContractId(
            contractCode, search.getNominationDate());
        if(cfgContract != null){
          String priceBookId = cfgContract.getPriceBookId();
          if(Constants.CURRENT.equals(priceBookId)){
            PriceBook pb = calculatorService.getCurrentPriceBook(
                cfgContract.getPbSeries(), cfgContract
                    .getCurrencyCD(), search
                    .getNominationDate());
            if(pb != null)
              cfgContract.setPriceBookId(pb.getPriceBookId()
                  .getPriceBookId());
          }
        }

      }
    }

    SlateService slateService = (SlateService) ServiceLocator.getInstance()
        .getBean("slateService");

    List searchSlates = slateService.getSlates(contractCode, cfgContract
        .getPriceBookId(),
        slateSearchStr, // value
        criteria, // searchType
        "*", DateUtil
            .formatDate(search.getNominationDate(), "yyyyMMdd"),
        "ENG");

    /*
     * if(criteria != null && (!criteria.trim().equals("")) && testSearchStr
     * != null && (!testSearchStr.trim().equals(""))) {
     * if(criteria.equalsIgnoreCase("Slate Id")) {
     * sb.append(" where s.slateId like ? "); if(testSearchStr != null) {
     * String idSearch = '%' + testSearchStr + '%'; params.add(idSearch);
     * hasWhere = true; } } if(criteria.equalsIgnoreCase("Description")) {
     * sb.append(" where s.slateDescription like ? "); if(testSearchStr !=
     * null) { String descSearch = '%' + testSearchStr + '%';
     * params.add(descSearch); hasWhere = true; } }
     * if(criteria.equalsIgnoreCase("Both")) {
     * sb.append(" where s.slateId like ? or s.slateDescription like ? ");
     * if(testSearchStr != null) { String searchStr = '%' + testSearchStr +
     * '%'; params.add(searchStr); params.add(searchStr); hasWhere = true; }
     * } } String vesselType = search.getVesselType().getValue();
     * System.out.println("vesselType: "+vesselType);
     *
     * if((vesselType != null) && !"".equals(vesselType.trim())) {
     * sb.append(" where v.vesselTypeId.vesselType like ?"); String
     * vesselTypeSearch = '%' + vesselType.trim() + '%';
     * params.add(vesselTypeSearch); hasWhere = true; }
     *
     * String vesselSet = search.getVesselSet().getValue();
     * System.out.println("vesselSet: "+vesselSet); if((vesselSet != null)
     * && !"".equals(vesselSet.trim())) { if(hasWhere) sb.append(" and ");
     * else sb.append(" where ");
     * sb.append(" v.vesselTypeId.vesselSet = ?"); params.add(vesselSet); }
     * System.out.println("params size: "+params.size()); Pagination
     * pagination = search.getPagination(); List results = null;
     *
     * if(pagination != null) { if(pagination.getTotalRecord() > 0) { List
     * counts = getDao().find( "select count(*) from Slate s " +
     * sb.toString(), params.toArray() );
     * System.out.println("after executing pagination query: "
     * +counts.size()); if(counts.size() > 0) { Number count =
     * (Number)counts.get(0);
     * System.out.println("total number of records : "+count.intValue());
     * pagination.setTotalRecord(count.intValue()); } }
     *
     * results = getDao().find( "select distinct s from Slate s  " +
     * sb.toString(), params.toArray(), pagination );
     *
     * pagination.calculatePages(); } else { results = getDao().find(
     * "select distinct s from Slate s " + sb.toString(), params.toArray()
     * ); }
     */
    search.setResults(searchSlates);
  }

  public JobManualTest addManualTest(JobManualTest jobManualTest){
    return getDao().save(jobManualTest);

  }

  public ContractCustContact getContractDetailsByCode(String contractCode){
    String ccode = "";
    String custcode = "";
    String s = "";
    long id = 0;
    StringTokenizer sToken = new StringTokenizer(contractCode, ",");
    while (sToken.hasMoreElements()){
      ccode = (String) sToken.nextElement();
      custcode = (String) sToken.nextElement();
      s = (String) sToken.nextElement();
      id = Long.valueOf(s).longValue();
    }

    List contracts = getDao()
        .find(
            // "select c from ContractCustContact c left join fetch c.contractCust left join fetch c.contractCust.contract left join fetch c.contractCust.customer left join fetch c.contact where c.contractCustContactId.contractCode = ?"
            // ,
            "select c from ContractCustContact c left join fetch c.contractCust left join fetch c.contractCust.contract left join fetch c.contractCust.customer left join fetch c.contact where c.contractCustContactId.contractCode = ? and c.contractCustContactId.custCode=? and c.contractCustContactId.contactId=?",
            new Object[] { ccode, custcode, id });
    /*
     * if(contracts.size() > 0) return
     * (ContractCustContact)contracts.get(0); return null;
     */
    ContractCustContact contractcustcontact = null;

    if(contracts != null && contracts.size() > 0){
      contractcustcontact = (ContractCustContact) contracts.get(0);
    }
    else{
      throw new ServiceException("contractcustcontact.not.exists.error",
          new Object[] { ccode }, null);
    }
    return contractcustcontact;
  }

  public ContractCustContact getContractDetailsByCode(String contractCode,
      String custCode, long contactId){

    List contracts = getDao()
        .find(
            "select c from ContractCustContact c left join fetch c.contractCust left join fetch c.contractCust.contract left join fetch c.contractCust.customer left join fetch c.contact where c.contractCustContactId.contractCode = ? and c.contractCustContactId.custCode=? and c.contractCustContactId.contactId=?",
            new Object[] { contractCode, custCode, contactId });
    if(contracts.size() > 0)
      return (ContractCustContact) contracts.get(0);
    return null;
  }

  public List getContractDetailsBycontractCode(String contractCode){

    List contrcts = new ArrayList();
    List contracts = new ArrayList();
    String[] code = contractCode.split("\\;");
    for(int i = 0; i < code.length; i++){
      contracts = getDao()
          .find(
              "select c from ContractCustContact c left join fetch c.contractCust left join fetch c.contractCust.contract left join fetch c.contractCust.customer left join fetch c.contact where c.contractCustContactId.contractCode = ?",
              new Object[] { code[i] });

      if(contracts.size() == 0){

        contrcts.add("0");
        contrcts.add(code[i]);
      }
      for(int j = 0; j < contracts.size(); j++){
        contrcts.add(contracts.get(j));
      }
    }
    return contrcts;
  }

  public JobContractNote addJobContractNote(JobContractNote jobContractNote){
    return getDao().save(jobContractNote);
  }

  /**
   * Name :addJobContractAttach Date :May 22, 2008 Purpose : Inserting a
   * JobContractAttach and saving the file filename transfered =
   * jobContractAttachid + "_" + file original name
   *
   * @param jobContractAttach
   * @param path
   * @param file
   * @return
   */
  public JobContractAttach addJobContractAttach(
      JobContractAttach jobContractAttach, String path, MultipartFile file){
    try{
      jobContractAttach = getDao().save(jobContractAttach);
      if(jobContractAttach != null && jobContractAttach.getId() > 0){
        String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
        File f = new File(path.concat(dateFolder));
        if(!f.exists())
          f.mkdir();
        jobContractAttach.setSystemFileName(dateFolder.concat("/")
            .concat(
                String.valueOf(jobContractAttach.getId())
                    .concat(
                        jobContractAttach
                            .getSystemFileName())));
        jobContractAttach = getDao().merge(jobContractAttach);
        File xferFile = new File(path
            + jobContractAttach.getSystemFileName());
        file.transferTo(xferFile);
      }
    }
    catch (Exception e){
      e.printStackTrace();
    }

    return jobContractAttach;
  }

  /*
   * public List getBankByBankCode(String bankCode,String buName) {
   * if(bankCode == null) return new ArrayList(); List bankCodes = new
   * ArrayList(); String bankCodeSearch ='%'+ bankCode.toUpperCase() + '%';
   * System
   * .out.println("bankc cod eis in getBankByBankCode is"+bankCodeSearch);
   * System
   * .out.println("bunsiness unit name is in getBankByBankCode is "+buName);
   *
   * try {bankCodes=getDao().find(
   * "from Bank b  where upper(b.bankId.bankCode) like ? and b.bankId.businessUnitName = ?"
   * , new Object[]{bankCodeSearch,buName});
   * System.out.println("size of the bankcodes in get bank by bankcode is "
   * +bankCodes.size()); } catch(Exception e ) { e.printStackTrace();
   * System.out.println("Exception in getBankByBankCode :"+ e.toString()); }
   * return bankCodes; }
   */
  public List getBankByBankCode(String bankDesc, String buName,
      String currency){
    if(bankDesc == null)
      return new ArrayList();
    List bankCodes = new ArrayList();
    String bankDescSearch = '%' + bankDesc.toUpperCase() + '%';
    String bDSearch = '%' + "Do Not Use" + '%';
    // bankCodes=getDao().find(
    // "select b from Bank b,BankAccountCurr ba where b.bankCode=ba.bankAccountCurrId.bankCode and upper(b.bankDesc) like '"
    // +bankDescSearch +
    // "' and ba.bankAccountCurrId.businessUnitName = '"+buName
    // +"' and ba.bankAccountCurrId.currencyCode='"+currency.trim()
    // +"'",null);
    // bankCodes=getDao().find(
    // "select distinct b from Bank b,BankAccountCurr ba where b.bankCode=ba.bankAccountCurrId.bankCode and upper(b.bankDesc) like '"
    // +bankDescSearch +
    // "' and ba.bankAccountCurrId.businessUnitName = '"+buName
    // +"' and ba.bankAccountCurrId.currencyCode='"+currency.trim()
    // +"' and upper(b.bankDesc) not like '" +bDSearch.toUpperCase()
    // +"'",null);
    bankCodes = getDao()
        .find(
            "select distinct b from Bank b,BankAccountCurr ba,BankAccount bac where b.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankAcctCode=bac.bankAccountId.bankAcctCode and bac.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and upper(b.bankDesc) like '"
                + bankDescSearch
                + "' and bac.bankAccountId.businessUnitName = '"
                + buName
                + "' and ba.bankAccountCurrId.currencyCode='"
                + currency.trim()
                + "' and upper(b.bankDesc) not like '"
                + bDSearch.toUpperCase() + "'", null);
    return bankCodes;
  }

  public List getBankCodebyBunameandCurrency(String buName, String currency){
    List bankCodes = new ArrayList();
    String bDSearch = '%' + "Do Not Use" + '%';
    // bankCodes=getDao().find(
    // "select distinct b from Bank b,BankAccountCurr ba where b.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.businessUnitName = '"
    // +buName +"' and ba.bankAccountCurrId.currencyCode='"+currency.trim()+
    // "' and upper(b.bankDesc) not like '" +bDSearch.toUpperCase()
    // +"'",null);
    bankCodes = getDao()
        .find(
            "select distinct b from Bank b,BankAccountCurr ba,BankAccount bac where b.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankAcctCode=bac.bankAccountId.bankAcctCode and bac.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bac.bankAccountId.businessUnitName = '"
                + buName
                + "' and ba.bankAccountCurrId.currencyCode='"
                + currency.trim()
                + "' and bac.bi='Y' and upper(b.bankDesc) not like '"
                + bDSearch.toUpperCase() + "'", null);
    return bankCodes;
  }

  public List getBankAccountByBankCodeAndCurrency(String buName,
      String currency, String bankCode){
    List bankAccounts = new ArrayList();
    String bDSearch = '%' + "Do Not Use" + '%';
    // bankAccounts=getDao().find(
    // "select distinct b from BankAccount b, BankAccountCurr ba where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode  and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and ba.bankAccountCurrId.businessUnitName = '"
    // +buName +"' and ba.bankAccountCurrId.currencyCode='"+currency.trim()
    // +"' and ba.bankAccountCurrId.bankCode ='"+bankCode+
    // "' and upper(b.bankAcctDesc) not like '" +bDSearch.toUpperCase()
    // +"'",null);
    bankAccounts = getDao()
        .find(
            "select distinct b from BankAccount b, Bank bk, BankAccountCurr ba where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and b.bankAccountId.businessUnitName = '"
                + buName
                + "' and ba.bankAccountCurrId.currencyCode='"
                + currency.trim()
                + "' and ba.bankAccountCurrId.bankCode ='"
                + bankCode
                + "' and b.bi='Y' and upper(b.bankAcctDesc) not like '"
                + bDSearch.toUpperCase() + "'", null);
    return bankAccounts;
  }

  public List getPrimBankCodebyBunameandCurrency(String buName,
      String currency){
    List bankCodes = new ArrayList();
    String bDSearch = '%' + "Do Not Use" + '%';
    bankCodes = getDao()
        .find(
            "select distinct b from Bank b,BankAccountCurr ba,BankAccount bac where b.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankAcctCode=bac.bankAccountId.bankAcctCode and bac.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bac.bankAccountId.businessUnitName = '"
                + buName
                + "' and ba.bankAccountCurrId.currencyCode='"
                + currency.trim()
                + "' and ba.primary='Y' and bac.bi='Y' and upper(b.bankDesc) not like '"
                + bDSearch.toUpperCase() + "'", null);
    if(bankCodes != null && bankCodes.size() == 1){
      return bankCodes;
    }
    return getBankCodebyBunameandCurrency(buName, currency);
  }

  public List getPrimBankAccountByBankCodeAndCurrency(String buName,
      String currency, String bankCode){
    List bankAccounts = new ArrayList();
    String bDSearch = '%' + "Do Not Use" + '%';
    bankAccounts = getDao()
        .find(
            "select distinct b from BankAccount b, Bank bk, BankAccountCurr ba where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and b.bankAccountId.businessUnitName = '"
                + buName
                + "' and ba.bankAccountCurrId.currencyCode='"
                + currency.trim()
                + "' and ba.bankAccountCurrId.bankCode ='"
                + bankCode
                + "' and ba.primary='Y' and b.bi='Y' and upper(b.bankAcctDesc) not like '"
                + bDSearch.toUpperCase() + "'", null);
    if(bankAccounts != null && bankAccounts.size() == 1){
      return bankAccounts;
    }
    return getBankAccountByBankCodeAndCurrency(buName, currency, bankCode);
  }

  // newly added
  public List getBankAccountByBankCode(String bankAccountDesc, String buName,
      String currency, String bankCode){
    if(bankAccountDesc == null)
      return new ArrayList();
    List bankAccounts = new ArrayList();
    String bankAcctDescSearch = '%' + bankAccountDesc.toUpperCase() + '%';
    String bDSearch = '%' + "Do Not Use" + '%';
    // bankAccounts=getDao().find(
    // "select distinct b from BankAccount b, BankAccountCurr ba where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode  and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and upper(b.bankAcctDesc) like '"
    // +bankAcctDescSearch +
    // "' and ba.bankAccountCurrId.businessUnitName = '"+buName
    // +"' and ba.bankAccountCurrId.currencyCode='"+currency.trim()
    // +"' and ba.bankAccountCurrId.bankCode ='"
    // +bankCode+"' and upper(b.bankAcctDesc) not like '"
    // +bDSearch.toUpperCase() +"'",null);
    bankAccounts = getDao()
        .find(
            "select distinct b from BankAccount b, BankAccountCurr ba, Bank bk where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and upper(b.bankAcctDesc) like '"
                + bankAcctDescSearch
                + "' and b.bankAccountId.businessUnitName = '"
                + buName
                + "' and ba.bankAccountCurrId.currencyCode='"
                + currency.trim()
                + "' and ba.bankAccountCurrId.bankCode ='"
                + bankCode
                + "' and upper(b.bankAcctDesc) not like '"
                + bDSearch.toUpperCase() + "'", null);
    return bankAccounts;
  }

  // up to here

  public List getBankAccountByAccount(String bankAccountDesc, String buName,
      String currency){
    if(bankAccountDesc == null)
      return new ArrayList();
    List bankAccounts = new ArrayList();
    String bankAcctDescSearch = '%' + bankAccountDesc.toUpperCase() + '%';
    String bDSearch = '%' + "Do Not Use" + '%';

    bankAccounts = getDao()
        .find(
            "select distinct b from BankAccount b, BankAccountCurr ba, Bank bk where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and  bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and upper(b.bankAcctDesc) like '"
                + bankAcctDescSearch
                + "' and b.bankAccountId.businessUnitName = '"
                + buName
                + "' and ba.bankAccountCurrId.currencyCode='"
                + currency.trim()
                + "' and upper(b.bankAcctDesc) not like '"
                + bDSearch.toUpperCase() + "'", null);
    return bankAccounts;
  }

  public void searchBankCode(BankSearch search, String sortFlag){
    if(search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strbankCodeSearch = "";
    String strbankDescSearch = "";
    String buName = search.getBuName().getValue();
    String currency = search.getCurrency().getValue();
    String bankCode = search.getBankCode().getValue();
    String bDSearch = '%' + "Do Not Use" + '%';

    if((bankCode != null) && !"".equals(bankCode.trim())){
      strbankCodeSearch = '%' + bankCode.toUpperCase() + '%';
      sb
          .append("where b.bankCode=ba.bankAccountCurrId.bankCode and bac.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankAcctCode=bac.bankAccountId.bankAcctCode and upper(b.bankCode) like ? and bac.bankAccountId.businessUnitName = ? and ba.bankAccountCurrId.currencyCode = ? and upper(b.bankDesc) not like ? ");
      params.add(strbankCodeSearch);
      params.add(buName);
      params.add(currency);
      params.add(bDSearch.toUpperCase());
      hasWhere = true;
    }

    String description = search.getDescription().getValue();

    if((description != null) && !"".equals(description.trim())){
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      strbankDescSearch = '%' + description.toUpperCase() + '%';
      sb
          .append("b.bankCode=ba.bankAccountCurrId.bankCode and bac.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankAcctCode=bac.bankAccountId.bankAcctCode and upper(b.bankDesc) like ? and bac.bankAccountId.businessUnitName = ? and ba.bankAccountCurrId.currencyCode = ? and upper(b.bankDesc) not like ? ");
      params.add(strbankDescSearch);
      params.add(buName);
      params.add(currency);
      params.add(bDSearch.toUpperCase());

    }

    else{
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      sb
          .append(" b.bankCode=ba.bankAccountCurrId.bankCode and bac.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankAcctCode=bac.bankAccountId.bankAcctCode and  bac.bankAccountId.businessUnitName = ? and ba.bankAccountCurrId.currencyCode=? and upper(b.bankDesc) not like ? ");
      params.add(buName);
      params.add(currency);
      params.add(bDSearch.toUpperCase());

      hasWhere = true;
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals("")){

      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(*) from Bank b,BankAccountCurr ba, BankAccount bac "
                  + sb.toString(), params.toArray());
          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        results = getDao().find(
            "select distinct b from Bank b,BankAccountCurr ba, BankAccount bac "
                + sb.toString(), params.toArray(), pagination);
        pagination.calculatePages();
      }
      else{
        results = getDao().find(
            "select distinct b from Bank b,BankAccountCurr ba,BankAccount bac "
                + sb.toString(), params.toArray());

      }
      search.setResults(results);
      search.setPagination(pagination);
    }
    else{
      String orderByValue = " order by b." + sortFlag;
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(*) from Bank b,BankAccountCurr ba, BankAccount bac "
                  + sb.toString(), params.toArray());
          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        pagination.calculatePages();
      }
      // START : #119240
      /*results = getDao().find(
          "select distinct b from Bank b,BankAccountCurr ba, BankAccount bac "
              + sb.toString() + orderByValue, params.toArray(),
          pagination);*/
      if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	  results = getDao().find(
    	          "select distinct b from Bank b,BankAccountCurr ba, BankAccount bac "
    	              + sb.toString() + orderByValue +" " + search.getSortOrderFlag(), params.toArray(),
    	          pagination);
      }else{
    	  results = getDao().find(
    	          "select distinct b from Bank b,BankAccountCurr ba, BankAccount bac "
    	              + sb.toString() + orderByValue, params.toArray(),
    	          pagination);
      }
     // END : #119240 
      // search.setTotalResults(results);
      search.setResults(results);
      search.setPagination(pagination);
    }
  }

  public void searchBankAccount(BankSearch search, String sortFlag){
    if(search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String strbankCodeSearch = "";
    String strbankDescSearch = "";
    String strbankAccountSearch = "";
    String buName = search.getBuName().getValue();
    String currency = search.getCurrency().getValue();

    String bankCode = search.getBankCode().getValue();
    String bDSearch = '%' + "Do Not Use" + '%';

    if((bankCode != null) && !"".equals(bankCode.trim())){
      strbankCodeSearch = '%' + bankCode.toUpperCase() + '%';
      sb
          .append("where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and  b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and  upper(b.bankAccountId.bankCode) like ? and b.bankAccountId.businessUnitName = ? and ba.bankAccountCurrId.currencyCode=? and upper(b.bankAcctDesc) not like ?");
      params.add(strbankCodeSearch);
      params.add(buName);
      params.add(currency);
      params.add(bDSearch.toUpperCase());

      hasWhere = true;
    }

	if(hasWhere){
		sb.append(" and ");
	}
	else{
		hasWhere = true;
		sb.append(" where ");
	}
	sb.append(" b.bi=? ");
	params.add(true);
	
    String description = search.getDescription().getValue();

    if((description != null) && !"".equals(description.trim())){
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      strbankDescSearch = '%' + description.toUpperCase() + '%';
      sb
          .append("b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and  b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and  upper(b.bankAcctDesc) like ? and b.bankAccountId.businessUnitName = ? and ba.bankAccountCurrId.currencyCode=? and upper(b.bankAcctDesc) not like ?");
      params.add(strbankDescSearch);
      params.add(buName);
      params.add(currency);
      params.add(bDSearch.toUpperCase());

      hasWhere = true;
    }
    String bankaccount = search.getBankAcctCode().getValue();
    if((bankaccount != null) && !"".equals(bankaccount.trim())){
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      strbankAccountSearch = '%' + bankaccount.toUpperCase() + '%';
      sb
          .append("b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and  b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and upper(b.bankAccountId.bankAcctCode) like ? and b.bankAccountId.businessUnitName = ? and ba.bankAccountCurrId.currencyCode=? and upper(b.bankAcctDesc) not like ?");
      params.add(strbankAccountSearch);
      params.add(buName);
      params.add(currency);
      params.add(bDSearch.toUpperCase());

    }
    else{
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      sb
          .append(" b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and  bk.bankCode= b.bankAccountId.bankCode and bk.bankCode=ba.bankAccountCurrId.bankCode and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and b.bankAccountId.businessUnitName = ? and  ba.bankAccountCurrId.currencyCode = ? and upper(b.bankAcctDesc) not like ?");
      params.add(buName);
      params.add(currency);
      params.add(bDSearch.toUpperCase());
      hasWhere = true;
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals("")){
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select count(*) from BankAccount b, BankAccountCurr ba, Bank bk "
                  + sb.toString(), params.toArray());
          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        results = getDao().find(
            "select  distinct b from BankAccount b, BankAccountCurr ba, Bank bk "
                + sb.toString(), params.toArray(), pagination);
        pagination.calculatePages();
      }
      else{
        results = getDao().find(
            "select distinct b from BankAccount b, BankAccountCurr ba, Bank bk "
                + sb.toString(), params.toArray());

      }
      search.setResults(results);
      search.setPagination(pagination);
    }
    else{
      String orderByValue = " order by " + sortFlag;
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select count(*) from BankAccount b, BankAccountCurr ba, Bank bk "
                  + sb.toString(), params.toArray());
          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        pagination.calculatePages();
      }
      // START : #119240
      /*results = getDao().find(
          "select  distinct b from BankAccount b, BankAccountCurr ba, Bank bk "
              + sb.toString() + orderByValue, params.toArray(),
          pagination);*/
      if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	  results = getDao().find(
    	          "select  distinct b from BankAccount b, BankAccountCurr ba, Bank bk "
    	              + sb.toString() + orderByValue +" " + search.getSortOrderFlag(), params.toArray(),
    	          pagination);
      }else{
    	  results = getDao().find(
    	          "select  distinct b from BankAccount b, BankAccountCurr ba, Bank bk "
    	              + sb.toString() + orderByValue, params.toArray(),
    	          pagination);
      }      
      // END : #119240
      
      // search.setTotalResults(results);
      search.setResults(results);
      search.setPagination(pagination);
    }
  }

  public JobContract addJobContractInsp(JobContract jobContract){
    String jobNumber = jobContract.getJobNumber();

    if(jobNumber == null){
      // throw new RuntimeException("JobNumber can not be null !");
      throw new ServiceException("jobnumber.error", new Object[] {}, null);
    }
    JobOrder jobOrder = new JobOrder();

    JobOrder existedJob = getJobOrderByJobNumber(jobNumber);

    /*
     * if(existedJob == null) { throw new
     * RuntimeException("JobNumber doe's not exist : " + jobNumber); }
     */

    Contact billingContact = jobContract.getBillingContact();
    String custCode = jobContract.getCustCode();

    if(billingContact != null && billingContact.getId() > 0){
      boolean existingbillingId = getBillingContactIdById(billingContact
          .getId(), custCode);
      /*
       * if(existingbillingId==false) { throw new
       * RuntimeException("No Billing ContactId exists "
       * +billingContact.getId()); }
       */
    }
    else
      jobContract.setBillingContact(null);

    long contactid = jobContract.getContactId();
    String contractCode = jobContract.getContractCode();

    /*
     * boolean
     * existingRecord=getExistingRecordByCode(jobNumber,custCode,contactid
     * ,contractCode); if(existingRecord==false) { throw new
     * RuntimeException("The Combination is already exists"+contractCode +
     * custCode + contactid); }
     */

    String bankCode = jobContract.getBankCd();
    String buName = jobContract.getJobOrder().getBuName();

    /*
     * if((bankCode!=null) && !"".equals(bankCode.trim())) { boolean
     * existingBankCodes=getBankCodesByCode(bankCode,buName);
     * if(existingBankCodes==false) { throw new
     * RuntimeException(" BankCode doe's not Exists "+bankCode); } }
     *
     * String bankAccount=jobContract.getBankAcctKey();
     * if((bankAccount!=null) && !"".equals(bankAccount.trim())) { boolean
     * existingBankAccounts=getBankAccountsByAccountkey(bankAccount,buName);
     * if(existingBankAccounts==false) { throw new
     * RuntimeException(" BankAccount doe's not Exists "+bankAccount); } }
     */

    // Setting jobContract vatRegCountry
    Country vatRegCountry = getCountryCodeByCustCodeAndVatRegId(jobContract
        .getCustCode(), jobContract.getVatRegId());
    jobContract.setVatRegCountry(vatRegCountry);
    // End

    return getDao().save(jobContract);
  }

  public void searchContract(ContractSearch search){
    if(search == null){
      return;
    }

    List results = new ArrayList();
    List result = new ArrayList();
    String[] codes = null;
    boolean hasWhere = false;
    String ccode = search.getContractCode().getValue();
    if((ccode != null) && (ccode.lastIndexOf(';') != -1)){
      codes = ccode.split("\\;");
    }
    else if(ccode != null && !ccode.trim().isEmpty()){

      codes = new String[1];
      codes[0] = ccode;
    }

    if(codes == null || codes.length == 0){
      return;
    }

    Session sess = getDao().openHibernateSession();
    for(int i = 0; i < codes.length; i++){
      StringBuffer sb = new StringBuffer();
      List params = new ArrayList();
      String codeI = codes[i];
      if(codeI == null || codeI.trim().isEmpty()){

        continue;
      }
      // codeI=codeI.replaceAll("'", "''");
      String ccodesearch = "%" + codeI.toUpperCase() + "%";

      StringBuffer buf = new StringBuffer("");
      buf.append("select c from ContractCustContact c left join fetch c.contractCust left join fetch c.contractCust.contract left join fetch c.contractCust.contract.cfgContracts left join fetch c.contractCust.customer inner join fetch c.contact ");
      buf.append("where ");
      buf.append("( ");
      buf.append("  upper(c.contractCustContactId.contractCode) like :contractCode ");
      buf.append("  or upper(c.contact.firstName) like :firstName ");
      buf.append("  or upper(c.contact.lastName) like :lastName ");
      buf.append("  or concat(upper(c.contact.firstName),' ',upper(c.contact.lastName)) like :firstLast1 ");
      buf.append("  or concat(upper(c.contact.firstName),upper(c.contact.lastName)) like :firstLast2 ");
      buf.append("  or upper(c.contractCust.customer.custCode) like :custCode ");
      buf.append("  or upper(c.contractCust.customer.name) like :custName ");
      buf.append("  or upper(c.contractCust.customer.alternateName) like :alternateCustName ");
      buf.append("  or upper(c.contractCust.contract.description) like :description ");
      buf.append(")");
      buf.append("and (");
    //buf.append("  c.contact.schedulerContactFlag=:schedulerFlag");
      buf.append("  c.schedulerContactFlag=:schedulerFlag");      
      buf.append("  and c.status=:status");
      buf.append(")");

      Date aDate = search.getAsOfDate();
      if(aDate == null){
        aDate = new Date();
      }
      sess.enableFilter("asOfDateCurrencyFilter").setParameter(
          "asOfDate", aDate);

      Query query = sess.createQuery(buf.toString());
      query.setString("contractCode", ccodesearch);
      query.setString("firstName", ccodesearch);
      query.setString("lastName", ccodesearch);
      query.setString("firstLast1", ccodesearch);
      query.setString("firstLast2", ccodesearch);
      query.setString("custCode", ccodesearch);
      query.setString("custName", ccodesearch);
      query.setString("alternateCustName", ccodesearch);
      query.setString("description", ccodesearch);
      query.setBoolean("schedulerFlag", new Boolean(true));
      query.setString("status", "A");

      result = query.list();
      for(int j = 0; j < result.size(); j++){
        results.add(result.get(j));
      }
    }

    if(sess != null){
      getDao().closeHibernateSession(sess);
    }

    Set set = new HashSet();
    set.addAll(results);
    results.clear();
    results.addAll(set);
    search.setResults(results);
  }

  public boolean getBillingContactIdById(long contactId, String custCode){
    boolean flag = false;
    List contacts = getDao()
        .find(
            "select c from ContactCust c  where c.contactCustId.contactId=? and c.contactCustId.custCode=?",
            new Object[] { contactId, custCode });
    if(contacts.size() > 0){
      flag = true;
      return flag;
    }
    else{
      throw new ServiceException("billing.contactid.not.exists.error",
          new Object[] { contactId }, null);
    }
    // return flag;
  }

  public boolean getExistingRecordByCode(String jobNumber, String custCode,
      long contactid, String contractCode){
    boolean flag = false;
    List records = getDao()
        .find(
            "select j from JobContract j where j.jobNumber=?  and j.custCode=? and j.contactId =? and j.contractCode=?",
            new Object[] { jobNumber, custCode, contactid,
                contractCode });
    if(records.size() > 0){
      flag = true;
      return flag;
    }
    else
      return flag;
  }

  public boolean getBankCodesByCode(String bankCode, String buName){
    boolean flag = false;
    // List codes=getDao().find(
    // "from Bank b,BankAccountCurr ba  where  b.bankCode=ba.bankAccountCurrId.bankCode and upper(b.bankDesc)='"
    // +bankCode .toUpperCase()
    // +"' and ba.bankAccountCurrId.businessUnitName = '" + buName
    // +"' ",null);
    // List codes=getDao().find(
    // "from Bank b,BankAccountCurr ba  where  b.bankCode=ba.bankAccountCurrId.bankCode and upper(b.bankCode)='"
    // +bankCode .toUpperCase()
    // +"' and ba.bankAccountCurrId.businessUnitName = '" + buName
    // +"' ",null);
    List codes = getDao()
        .find(
            "from Bank b,BankAccountCurr ba, BankAccount bac  where  b.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankCode=bac.bankAccountId.bankCode and upper(b.bankCode)='"
                + bankCode.toUpperCase()
                + "' and bac.bankAccountId.businessUnitName = '"
                + buName + "' ", null);
    if(codes.size() > 0){
      flag = true;
      return flag;
    }
    else
      return flag;
  }

  public boolean getBankAccountsByAccountkey(String bankAccount, String buName){
    boolean flag = false;
    // List accounts=getDao().find(
    // "from BankAccount b,BankAccountCurr ba  where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode  and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and upper(b.bankAcctDesc) = '"
    // +bankAccount .toUpperCase()
    // +"'and ba.bankAccountCurrId.businessUnitName =  '" + buName
    // +"' ",null);
    // List accounts=getDao().find(
    // "from BankAccount b,BankAccountCurr ba  where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode  and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and b.bankAccountId.bankAcctCode = '"
    // +bankAccount +"'and ba.bankAccountCurrId.businessUnitName =  '" +
    // buName +"' ",null);
    List accounts = getDao()
        .find(
            "from BankAccount b,BankAccountCurr ba, Bank bk  where b.bankAccountId.bankCode=ba.bankAccountCurrId.bankCode and ba.bankAccountCurrId.bankCode=bk.bankCode and b.bankAccountId.bankAcctCode=ba.bankAccountCurrId.bankAcctCode and b.bankAccountId.bankAcctCode = '"
                + bankAccount
                + "'and b.bankAccountId.businessUnitName =  '"
                + buName + "' ", null);
    if(accounts.size() > 0){
      flag = true;
      return flag;
    }
    else
      return flag;
  }

  /**
   * Name :getBillingContactByContactId Date :Apr 11, 2008 Purpose : It
   * retrieves ContactCust along with the active and effective address for the
   * particular contactId and CustCode
   *
   * @param contactId
   * @param custCode
   * @return
   */
  public ContactCust getBillingContactByContactId(long contactId,
      String custCode){
    List contacts = getDao()
        .find(
            " select c from ContactCust c left join fetch c.custAddrSeq  left join fetch c.custAddrSeq.custAddresses ads"
                + " left join fetch c.contact  where c.contactCustId.contactId=? and c.custAddrSeq.custAddrSeqId.custCode = ?"
                + " and ads.effDate = " +
                    "(select max(ads2.effDate) from ContactCust c2 left join c2.custAddrSeq  " +
                    "left join c2.custAddrSeq.custAddresses ads2 " +
                    "where ads2.effStatus='A' and ads2.effDate <= sysdate " +
                    "and c2.contactCustId.contactId=? and ads2.custCode = ?)",

            new Object[] { contactId, custCode, contactId, custCode });

    if(contacts.size() > 0)
      return (ContactCust) contacts.get(0);
    return null;
  }

	/**
	 * Retrieves the billing contacts for a particular customer
	 */
	public List getBillingContactByCustCode(String custCode){
		List billingContacts = new ArrayList();
		billingContacts = getDao()
				.find("select distinct c from Contact c " +
						" left join c.contactCusts cc left join cc.custAddrSeq.custAddresses ads  where cc.contactCustId.custCode = ? " +
						" and c.billContactFlag='Y' and ads.effDate = " +
						"(select max(ads2.effDate) from c.contactCusts c2 left join c2.custAddrSeq  " +
						" left join c2.custAddrSeq.custAddresses ads2 where ads2.effStatus='A' and ads2.effDate <= sysdate and ads2.custCode = ? )",
						new Object[] { custCode, custCode });			
			
		return billingContacts;
	}
	
	/**
	 * Name :getBillingContactByCustCodeAndContractCode
	 * Date :Apr 2, 2009
	 * purpose :To get the list of billing contacts for a particular contract and the customer
	 * @param custCode
	 * @param contractCode
	 * @return List
	 */
	public List getBillingContactByCustCodeAndContractCode(String custCode,String contractCode){
		List billingContacts = new ArrayList();		
		billingContacts = getDao()
        .find(" select ccc from ContractCustContact ccc left join fetch ccc.contact c left join fetch c.contactCusts cc " +
              " left join cc.custAddrSeq.custAddresses ads where upper(ccc.contractCustContactId.custCode)= ? and " +
              " ccc.billContactFlag='Y' and upper(ccc.contractCustContactId.contractCode)= ? and upper(cc.contactCustId.custCode)=? "+
              " and ads.effDate=(select max(ads2.effDate) from c.contactCusts c2 left join c2.custAddrSeq " +
              " left join c2.custAddrSeq.custAddresses ads2 where ads2.effStatus='A' " +
              " and ads2.effDate <= sysdate and upper(ads2.custCode) = ?) ", 
              new Object[] { custCode.toUpperCase(),contractCode.toUpperCase(), custCode.toUpperCase(),custCode.toUpperCase()});
     return billingContacts;
     }
	

	public List getVesselTypes(){
	 Date currentDt = new Date();
	 List vesselTypes = getDao()
		 .find(

            "  from VesselType vt   where  vt.vesselTypeId.vesselSet = ? and "
                + " vt.vesselTypeId.beginDate <= ? and vt.endDate >= ?  "
                + "  order by vt.vesselTypeId.vesselType ",
            new Object[] { "Pricebook", currentDt, currentDt });

    if(vesselTypes.size() > 0)
      return vesselTypes;
    return null;
  }

  public RB getRBByRBKeyAndContractCode(String rbKey, String contractCode){
    Date currentDt = new Date();
    List RBs = getDao()
        .find(
            " from RB rb where rb.rbId.rbKey = ? and rb.rbId.beginDate <= ? and rb.rbId.endDate >= ? "
                + " and rb.rbId.contractId = ? ",
            new Object[] { rbKey, currentDt, currentDt,
                contractCode });

    if(RBs.size() > 0)
      return (RB) RBs.get(0);
    return null;
  }

  public List getProductGroups(List<String> productTypes){
		Hashtable<String, Object> values=new Hashtable<String, Object>();
		values.put("productTypes", productTypes);
		List pgs=getDao().findBySQLNamedParams("select product_group from product_type_group_map where product_type in (:productTypes)", values);
		
		Date currentDt = new Date();
		values=new Hashtable<String, Object>();
		values.put("productGroupSet", "Pricebook");
		values.put("beginDate", currentDt);
		values.put("endDate", currentDt);
		values.put("rbKeys", pgs);
		List prdGrps = getDao().findNamedParams(
						" from ProductGroup pg where pg.productGroupId.productGroupSet = :productGroupSet and "
								+ " pg.productGroupId.beginDate <= :beginDate  and pg.endDate >= :endDate "
								+ " and pg.rbKey in (:rbKeys) "
								+ " order by pg.productGroupId.groupId ",
						values);
		if(prdGrps.size() > 0)
			return prdGrps;
		return null;
  }

  /**
   * Name :getTestProductGroups Date :Dec 3, 2008 Purpose : Returns all Test
   * Products
   *
   * @return
   */
  public List getTestProductGroups(){
    List testPrdGrps = getDao()
        .find(
            "select tpg from TestProduct tpg  order by tpg.testProductDescription ",
            null);
    if(testPrdGrps.size() > 0)
      return testPrdGrps;
    return null;
  }

  public JobVessel saveJobVessel(JobVessel jobVessel){
    return getDao().save(jobVessel);

  }

  public JobProd saveJobProduct(JobProd jobProd){
    return getDao().save(jobProd);
  }

  public JobProdQty saveJobProdQty(JobProdQty jobProdQty){
    return getDao().save(jobProdQty);
  }

  public JobEvent saveJobEvent(JobEvent jobEvent){
    return getDao().save(jobEvent);
  }

  public InspectionEventTbl saveInspectionEventTbl(
      InspectionEventTbl inspectionEventTbl){
    return getDao().save(inspectionEventTbl);
  }

  public JobProdSample saveJobProdSample(JobProdSample jobProdSample){
    return getDao().save(jobProdSample);
  }

  public Test getTestByTestId(String testId){
    List tests = getDao().find("select t from Test t where t.testId = ?",
        new Object[] { testId });
    if(tests != null && tests.size() > 0)
      return (Test) tests.get(0);
    return null;
  }

  public Slate getSlateBySlateId(String slateId){
    List slates = getDao().find(
        "select s from Slate s where s.slateId = ?",
        new Object[] { slateId });
    if(slates != null && slates.size() > 0)
      return (Slate) slates.get(0);
    return null;
  }

  public JobContract getJobContractByJobNumberAndContractCode(
      String jobNumber, String contractCode){
    List jobContracts = getDao()
        .find(
            "select jc from JobContract jc where jc.jobNumber = ? and jc.contractCode = ? ",
            new Object[] { jobNumber, contractCode });
    if(jobContracts.size() > 0)
      return (JobContract) jobContracts.get(0);
    return null;
  }

  public JobContractTest saveJobContractTest(JobContractTest jobContractTest){
    return getDao().save(jobContractTest);
  }

  public JobTest saveJobTest(JobTest jobTest){
    return getDao().save(jobTest);
  }

  public JobSlate saveJobSlate(JobSlate jobSlate){
    return getDao().save(jobSlate);
  }

  public JobContractSlate saveJobContractSlate(
      JobContractSlate jobContractSlate){
    return getDao().save(jobContractSlate);
  }

  public List getJobVesselsByJobNumber(String jobNumber){
    List jobVessels = getDao()
        .find(
            "select distinct jv from JobVessel jv left join fetch jv.jobProds where jv.jobVesselId.jobNumber = ? order by jv.jobVesselId.linkedVslRow ",
            new Object[] { jobNumber });
    if(jobVessels.size() > 0)
      return jobVessels;
    return null;
  }

  public List getJobProductsByJobNumberAndVesselRow(String jobNumber,
      Integer vesselRow){
    List jobProducts = getDao()
        .find(
            "select distinct jp from JobProd jp left join fetch jp.jobProdSamples left join fetch jp.jobProdContracts where jp.jobProdId.jobNumber = ? and jp.jobProdId.linkedVslRow = ? order by jp.jobProdId.prodSeqNum ",
            new Object[] { jobNumber, vesselRow });
    if(jobProducts.size() > 0)
      return jobProducts;
    return null;
  }

  public List getJobProdQtysByJobNumberVesselRowAndProductName(
      String jobNumber, Integer vesselRow, Integer prodSeqNum){
    List jobProdQtys = getDao()
        .find(
            "select distinct jpq from JobProdQty jpq where jpq.jobNumber = ? and jpq.linkedVslRow = ? and jpq.prodSeqNum = ? order by jpq.id ",
            new Object[] { jobNumber, vesselRow, prodSeqNum });
    if(jobProdQtys.size() > 0)
      return jobProdQtys;
    return null;
  }

  public List getJobEventsByJobNumberVesselRowAndProductName(
      String jobNumber, Integer vesselRow, Integer prodSeqNum){
    List jobEvents = getDao()
        .find(
            "select distinct je from JobEvent je left join fetch je.event where je.jobEventId.jobNumber = ? and je.jobEventId.linkedVslRow = ? and je.jobEventId.prodSeqNum = ? order by je.sortOrder ",
            new Object[] { jobNumber, vesselRow, prodSeqNum });
    if(jobEvents.size() > 0)
      return jobEvents;
    return null;
  }

  public List getJobProdSamplesByJobNumberVesselRowAndProductName(
      String jobNumber, Integer vesselRow, Integer prodSeqNum){
    List jobProdSamples = getDao()
        .find(
            "select distinct jps from JobProdSample jps left join fetch jps.jobTests left join fetch jps.jobSlates left join fetch jps.jobManualTests where jps.jobProdSampleId.jobNumber = ? and jps.jobProdSampleId.linkedVslRow = ? and jps.jobProdSampleId.prodSeqNum = ? order by jps.jobProdSampleId.sampSeqId ",
            new Object[] { jobNumber, vesselRow, prodSeqNum });
    if(jobProdSamples.size() > 0)
      return jobProdSamples;
    return null;
  }

  public List getJobTestsByJobNumberVesselRowProductAndSampleLoc(
      String jobNumber, Integer vesselRow, Integer prodSeqNum,
      Integer sampSeqId){
    List jobTests = getDao()
        .find(
            "select distinct jct from JobTest jct left join fetch jct.jobProdSample jps left join fetch jct.test where jps.jobProdSampleId.jobNumber = ? and jps.jobProdSampleId.linkedVslRow = ? and jps.jobProdSampleId.prodSeqNum = ? and jps.jobProdSampleId.sampSeqId = ? order by jct.id ",
            new Object[] { jobNumber, vesselRow, prodSeqNum,
                sampSeqId });
    if(jobTests.size() > 0)
      return jobTests;
    return null;
  }

  public List getJobManualTestsByJobNumberVesselRowProductAndSampleLoc(
      String jobNumber, Integer vesselRow, Integer prodSeqNum,
      Integer sampSeqId){
    List jobManualTests = getDao()
        .find(
            "select distinct jct from JobManualTest jct left join fetch jct.jobProdSample jps where jps.jobProdSampleId.jobNumber = ? and jps.jobProdSampleId.linkedVslRow = ? and jps.jobProdSampleId.prodSeqNum = ? and jps.jobProdSampleId.sampSeqId = ? order by jct.id ",
            new Object[] { jobNumber, vesselRow, prodSeqNum,
                sampSeqId });
    if(jobManualTests.size() > 0)
      return jobManualTests;
    return null;
  }

  public List getJobSlatesByJobNumberVesselRowProductAndSampleLoc(
      String jobNumber, Integer vesselRow, Integer prodSeqNum,
      Integer sampSeqId){
    List jobSlates = getDao()
        .find(
            "select distinct jcs from JobSlate jcs left join fetch jcs.jobProdSample jps left join fetch jcs.slate where jps.jobProdSampleId.jobNumber = ? and jps.jobProdSampleId.linkedVslRow = ? and jps.jobProdSampleId.prodSeqNum = ? and jps.jobProdSampleId.sampSeqId = ? order by jcs.id ",
            new Object[] { jobNumber, vesselRow, prodSeqNum,
                sampSeqId });
    if(jobSlates.size() > 0)
      return jobSlates;
    return null;
  }

  public List getReferenceFieldsByContractCode(String contractCode){
    if(contractCode == null)
      return new ArrayList();
    List referenceFields = new ArrayList();

    try{
      referenceFields = getDao()
          .find(
              "select r from ReferenceField r  where upper(r.referenceFieldId.contractId) like '"
                  + contractCode.toUpperCase()
                  + "' order by r.sortOrderNum", null);
    }
    catch (Exception e){
      e.printStackTrace();
      System.out.println("Exception in getReferenceFieldsByCode :"
          + e.toString());
    }
    return referenceFields;
  }

  public String getLocationByContractCode(String contractCode){
    List portcodes = getDao()
        .find(
            "select p.portLocationId.portCode from PortLocation p  where upper(p.portLocationId.contractId) ='"
                + contractCode.toUpperCase() + "'", null);
    if(portcodes.size() > 0)
      return (String) portcodes.get(0);
    return null;
  }

  /*
   * public List getPortCodeByContractCode(String contractCode, String
   * priceBookId, String asOfDate) { if(priceBookId!=null&&
   * !priceBookId.trim().equals("")){ return getDao().find(
   * " from PortLocation p  where  upper(p.portLocationId.contractId)in ('"
   * +contractCode.toUpperCase()+"','"+priceBookId
   * +"' ) and p.portLocationId.beginDate <= '" +asOfDate +
   * "' and p.endDate >='"+asOfDate+ "'" +
   * " order by p.portLocationId.portCode ",null); } else { return
   * getDao().find(
   * " from PortLocation p  where  upper(p.portLocationId.contractId)='"
   * +contractCode.toUpperCase()+"' and p.portLocationId.beginDate <= '"
   * +asOfDate + "' and p.endDate >='"+asOfDate+ "'" +
   * " order by p.portLocationId.portCode ",null); }
   *
   * }
   */

  public List getPortCodeByContractCode(String contractCode,
      String priceBookId, String asOfDate){
    List locations = null;
    List priceLocations = null;
    locations = getDao().find(
        "select p from PortLocation p  where  upper(p.portLocationId.contractId)= '"
            + contractCode.toUpperCase()
            + "'and p.portLocationId.beginDate <= '" + asOfDate
            + "' and p.endDate >='" + asOfDate + "'"
            + " order by p.portLocationId.portCode ", null);
    if(priceBookId != null && !priceBookId.trim().equals("")){
      priceLocations = getDao().find(
          "select p from PortLocation p  where  upper(p.portLocationId.contractId)= '"
              + priceBookId
              + "'  and p.portLocationId.beginDate <= '"
              + asOfDate + "' and p.endDate >='" + asOfDate + "'"
              + " order by p.portLocationId.portCode ", null);
    }
    if(priceLocations != null && priceLocations.size() != 0){
      for(int j = 0; j < priceLocations.size(); j++){
        locations.add(priceLocations.get(j));
      }
    }
    return locations;
  }

  public String getZoneByBranchCodeandContractCode(String branchCode,
      String contractCode, String priceBook, Date k){

    String asOfDate = DateUtil.formatDate(k, "dd-MMM-yyyy");
    List branchLocations = null;
    if(priceBook != null && !priceBook.trim().equals("")){
      branchLocations = getDao()
          .find(
              "select b.branchLocationId.location from BranchLocation b  where upper(b.branchLocationId.branchCode) = '"
                  + branchCode.toUpperCase()
                  + "'  and upper(b.branchLocationId.contractId) in ('"
                  + contractCode.toUpperCase()
                  + "','"
                  + priceBook
                  + "' ) and b.branchLocationId.beginDate <='"
                  + asOfDate
                  + "' and b.endDate >='"
                  + asOfDate + "'", null);
    }
    else{
      branchLocations = getDao()
          .find(
              "select b.branchLocationId.location from BranchLocation b  where upper(b.branchLocationId.branchCode) = '"
                  + branchCode.toUpperCase()
                  + "'  and upper(b.branchLocationId.contractId) ='"
                  + contractCode.toUpperCase()
                  + "' and b.branchLocationId.beginDate <='"
                  + asOfDate
                  + "' and b.endDate >='"
                  + asOfDate + "'", null);
    }
    if(branchLocations != null && branchLocations.size() > 0)
      return (String) branchLocations.get(0);
    return null;
  }

  /*
   * public List getZoneByPortCodeandContractCode(String portCode,String
   * contractCode) { return getDao().find(
   * " from PortLocation p  where upper(p.portLocationId.portCode) = '"
   * +portCode.toUpperCase()
   * +"'  and upper(p.portLocationId.contractId) ='"+contractCode
   * .toUpperCase() +"'",null); }
   */

  public List getZoneByPortCodeandContractCode(String portCode,
      String contractCode, String priceBookId, String asOfDate){
    if(priceBookId != null && !priceBookId.trim().equals("")){
      return getDao()
          .find(
              " from PortLocation p  where upper(p.portLocationId.portCode) = '"
                  + portCode.toUpperCase()
                  + "'  and upper(p.portLocationId.contractId) in ('"
                  + contractCode.toUpperCase() + "','"
                  + priceBookId
                  + "' ) and p.portLocationId.beginDate <= '"
                  + asOfDate + "' and p.endDate >='"
                  + asOfDate + "'", null);
    }
    else{
      return getDao()
          .find(
              " from PortLocation p  where upper(p.portLocationId.portCode) = '"
                  + portCode.toUpperCase()
                  + "'  and upper(p.portLocationId.contractId) ='"
                  + contractCode.toUpperCase()
                  + "' and p.portLocationId.beginDate <= '"
                  + asOfDate + "' and p.endDate >='"
                  + asOfDate + "'", null);
    }
  }

  public List getZoneByContractCode(String contractCode, String priceBookId,
      String asOfDate){
    if(priceBookId != null && !priceBookId.trim().equals("")){
      return getDao()
          .find(
              " from BranchLocation b  where upper(b.branchLocationId.contractId) in ('"
                  + contractCode.toUpperCase()
                  + "','"
                  + priceBookId
                  + "' ) and b.branchLocationId.beginDate <='"
                  + asOfDate + "' and b.endDate >='"
                  + asOfDate + "'", null);
    }
    else{
      return getDao()
          .find(
              " from BranchLocation b  where upper(b.branchLocationId.contractId) ='"
                  + contractCode.toUpperCase()
                  + "' and b.branchLocationId.beginDate <='"
                  + asOfDate + "' and b.endDate >='"
                  + asOfDate + "'", null);
    }
  }

  public CfgContract getPriceBookByContractCode(String contractCode, Date k){
    String ccode = contractCode.toUpperCase();
    String asOfDate = DateUtil.formatDate(k, "dd-MMM-yyyy");
    // List cfgContract = getDao().find(
    // " from CfgContract c  where upper(c.cfgContractId.contractId) = ? and c.cfgContractId.beginDate <=? and c.endDate >= ?"
    // , new Object[] { ccode, asOfDate, asOfDate});
    List cfgContract = getDao().find(
        "from CfgContract c  where upper(c.cfgContractId.contractId) = '"
            + ccode.toUpperCase()
            + "' and c.cfgContractId.beginDate <= '" + asOfDate
            + "' and c.endDate >='" + asOfDate + "'", null);
    if(cfgContract != null && cfgContract.size() > 0)
      return (CfgContract) cfgContract.get(0);
    return null;
  }

  public PriceBook getPriceBookIdByCurrencyandSeries(String currency,
      String pbSeries, Date k){
    String asOfDate = DateUtil.formatDate(k, "dd-MMM-yyyy");
    List priceBook = getDao().find(
        "from PriceBook p where p.priceBookId.currencyCD ='" + currency
            + "'and p.priceBookId.pbSeries ='" + pbSeries
            + "'and p.priceBookId.beginDate <='" + asOfDate
            + "' and p.endDate >='" + asOfDate + "'", null);
    if(priceBook.size() > 0)
      return (PriceBook) priceBook.get(0);
    return null;
  }

  /*
   * public CfgContract getCfgContractByContractCode(String contractCode) {
   * String ccode=contractCode.toUpperCase(); List cfgContract =
   * getDao().find(
   * " from CfgContract c  where upper(c.cfgContractId.contractId) = ? ", new
   * Object[] { ccode}); if(cfgContract.size() > 0) return
   * (CfgContract)cfgContract.get(0); return null; }
   */

  public List getCurrencyTransByCurrency(String currency, Date effectiveDate){
    /*
     * List finalList = new ArrayList(); List currList =getDao().find(
     * "select distinct c.currencyRateId.toCurrency  from CurrencyRate c  where c.currencyRateId.effectiveDate <= ? and c.currencyRateId.fromCurrency = ? order by c.currencyRateId.toCurrency "
     * , new Object[] {new Date(), currency}); if(currList != null &&
     * currList.size() >0) { for(int i=0;i<currList.size();i++) { String
     * toCurr = (String) currList.get(i);
     *
     * List maxDateList =getDao().find(
     * "  select max(cr.currencyRateId.effectiveDate)  from CurrencyRate cr where cr.currencyRateId.toCurrency = ? "
     * , new Object[] {toCurr});
     *
     * if(maxDateList != null && maxDateList.size() >0) { Date maxDate =
     * (Date) maxDateList.get(0);
     *
     *
     *
     *
     * List tempList =getDao().find(
     * " from CurrencyRate c where c.currencyRateId.toCurrency = ? " +
     * " and c.currencyRateId.effectiveDate <= ? and c.currencyRateId.effectiveDate = ? ) "
     * , new Object[] {toCurr,new Date(), maxDate}); if(tempList != null &&
     * tempList.size() >0) { CurrencyRate c = (CurrencyRate)
     * tempList.get(0); finalList.add(c); } } } } return finalList;
     */
    // return getDao().find(
    // " from CurrencyRate c  where c.currencyRateId.effectiveDate in (select max(c.currencyRateId.effectiveDate) from CurrencyRate c  where  upper(c.currencyRateId.fromCurrency) ='"
    // +currency.toUpperCase()
    // +"' and c.currencyRateId.effectiveDate<sysdate)",null);
    return getDao().findByNamedSqlQuery(
        "getCurrencyTransByCurrency",
        new Object[] {
            effectiveDate == null ? new Date() : effectiveDate,
            currency });

  }

  /*
   * public List getZoneIdByBranchCodeandContractCode(String branchCode,String
   * contractCode) { List zoneIds= getDao().find(
   * " from BranchLocation b  where upper(b.branchLocationId.branchCode) = '"
   * +branchCode.toUpperCase()
   * +"'  and upper(b.branchLocationId.contractId) ='"
   * +contractCode.toUpperCase() +"'",null); return zoneIds; }
   */

  public List getZoneIdByBranchCodeandContractCode(String branchCode,
      String contractCode, String priceBookId, String asOfDate){
    List zoneIds = null;

    if(priceBookId != null && !priceBookId.trim().equals("")){
      zoneIds = getDao()
          .find(
              " from BranchLocation b where upper(b.branchLocationId.branchCode) = '"
                  + branchCode.toUpperCase()
                  + "'  and upper(b.branchLocationId.contractId) in ('"
                  + contractCode.toUpperCase()
                  + "','"
                  + priceBookId
                  + "' ) and b.branchLocationId.beginDate <='"
                  + asOfDate + "' and b.endDate >='"
                  + asOfDate + "'", null);
    }
    else{
      zoneIds = getDao()
          .find(
              " from BranchLocation b where upper(b.branchLocationId.branchCode) = '"
                  + branchCode.toUpperCase()
                  + "'  and upper(b.branchLocationId.contractId) ='"
                  + contractCode.toUpperCase()
                  + "' and b.branchLocationId.beginDate <='"
                  + asOfDate + "' and b.endDate >='"
                  + asOfDate + "'", null);
    }
    return zoneIds;
  }

  public List getAllJobContractByJobNumber(String jobNumber){
    if(jobNumber == null)
      return new ArrayList();

    return getDao().find(
        "from JobContract j where j.jobNumber= ? order by j.id",
        new Object[] { jobNumber.toUpperCase() });
  }

  public List<JobContractNote> getJobContractNotesByContractIdandJobNumber(
      String jobNumber, String contractCode, long jobId){
    String jId = String.valueOf(jobId);
    return getDao().find(
        "select j from JobContractNote j  where j.jobNumber = '"
            + jobNumber.toUpperCase()
            + "' and upper(j.contractCode)='"
            + contractCode.toUpperCase()
            + "' and j.jobContractId= '" + jId + "'", null);
  }

  public User getUserByName(String name){
    List userNames = getDao().find(
        "from User u where upper(u.loginName) like'"
            + name.toUpperCase() + "%'", null);
    if(userNames.size() > 0)
      return (User) userNames.get(0);
    return null;
  }

  public boolean checkJobContract(String jobNumber, String custCode,
      String contractCode, long contactid){
    boolean flag = false;
    String cid = String.valueOf(contactid);
    List records = getDao().find(
        "from JobContract j where j.jobNumber = '"
            + jobNumber.toUpperCase() + "' and upper(j.custCode)='"
            + custCode.toUpperCase()
            + "'  and upper(j.contractCode)='"
            + contractCode.toUpperCase()
            + "' and str(j.contactId) ='" + cid + "'", null);
    if(records.size() > 0){
      flag = true;
      return flag;
    }
    else
      return flag;
  }

  public void deleteJobNoteInsp(JobContractNote jobContractNote){
    getDao().remove(jobContractNote);
  }

  /**
   * Name :deleteJobContractNotes Date :May 23, 2008 Purpose : Delete all
   * JobContractNotes related to one JobContract
   *
   * @param jobContractId
   */
  public void deleteJobContractNotesByJobContract(long jobContractId){
    List<JobContractNote> list = getDao().find(
        "from JobContractNote n where n.jobContract.id = ? ",
        new Object[] { jobContractId });
    if(list != null && !list.isEmpty()){
      for(JobContractNote n : list){
        getDao().remove(com.intertek.entity.JobContractNote.class,
            n.getId());
      }
    }/*
     * else throw new ServiceException("delete.jobcontractnotes.error");
     */
  }

  public JobContractNote getJobContractNoteByJobId(long id, long jobId){

    String iD = String.valueOf(id);
    String jid = String.valueOf(jobId);
    List jobnotes = getDao().find(
        " from JobContractNote j  where str(j.id) = '" + iD
            + "' and str(j.jobContractId) = '" + jid + "'", null);
    if(jobnotes.size() > 0)
      return (JobContractNote) jobnotes.get(0);
    return null;
  }

  public void deleteJobInsp(JobContract jobContract){
    getDao().remove(jobContract);
  }

  public JobContractNote saveJobContractNote(JobContractNote jobContractNote){
    return getDao().save(jobContractNote);
  }

  public JobContract saveJobContract(JobContract jobContract){
    return getDao().save(jobContract);
  }

  public JobContractAttach saveJobContractAttach(
      JobContractAttach jobContractAttach){
    return getDao().save(jobContractAttach);
  }

  public JobContract saveJobContractInsp(JobContract jobContract){
    String jobNumber = jobContract.getJobNumber();

    if(jobNumber == null){
      // throw new RuntimeException("JobNumber can not be null !");
      throw new ServiceException("jobnumber.error", new Object[] {}, null);
    }
    JobOrder jobOrder = new JobOrder();
    JobOrder existedJob = getJobOrderByJobNumber(jobNumber);

    /*
     * if(existedJob == null) { throw new
     * RuntimeException("JobNumber doe's not exist : " + jobNumber); }
     */

    Contact billingContact = jobContract.getBillingContact();
    String custCode = jobContract.getCustCode();

    if(billingContact != null && billingContact.getId() > 0){
      boolean existingbillingId = getBillingContactIdById(billingContact
          .getId(), custCode);
      /*
       * if(existingbillingId==false) { throw new
       * RuntimeException("No Billing ContactId exists "
       * +billingContact.getId()); }
       */
    }
    else
      jobContract.setBillingContact(null);

    long contactid = jobContract.getContactId();
    String contractCode = jobContract.getContractCode();

    /*
     * boolean
     * existingRecord=getExistingRecordByCode(jobNumber,custCode,contactid
     * ,contractCode); if(existingRecord==false) { throw new
     * RuntimeException("The Combination is already exists"+contractCode +
     * custCode + contactid); }
     */

    String bankCode = jobContract.getBankCd();
    String buName = jobContract.getJobOrder().getBuName();
    if((bankCode != null) && !"".equals(bankCode.trim())){
      boolean existingBankCodes = getBankCodesByCode(bankCode, buName);
      if(existingBankCodes == false){
        // throw new
        // RuntimeException("BankCode doe's not Exists "+bankCode);
        throw new ServiceException("bankcode.search.error",
            new Object[] { bankCode }, null);
      }
    }

    String bankAccount = jobContract.getBankAcctKey();
    if((bankAccount != null) && !"".equals(bankAccount.trim())){
      boolean existingBankAccounts = getBankAccountsByAccountkey(
          bankAccount, buName);
      if(existingBankAccounts == false){
        // throw new
        // RuntimeException(" BankAccount doe's not Exists "+bankAccount
        // );
        throw new ServiceException("bankaccount.search.error",
            new Object[] { bankAccount }, null);
      }
    }
    // Setting jobContract vatRegCountry
    Country vatRegCountry = getCountryCodeByCustCodeAndVatRegId(jobContract
        .getCustCode(), jobContract.getVatRegId());
    jobContract.setVatRegCountry(vatRegCountry);
    // End
    return getDao().save(jobContract);
  }

  public Prebill getPrebillById(Long prebillId){
    List prebills = getDao()
        .find(
            "from Prebill pb left join fetch pb.jobContract jc left join fetch jc.jobOrder left join  fetch pb.prebillSplits pbs left join fetch pbs.branch where pb.id = ?",
            new Object[] { prebillId });
    if(prebills.size() > 0)
      return (Prebill) prebills.get(0);

    return null;
  }

  public void searchJobOrder(JobSearch jobSearch, int pageNumber,
      String reqForm, String sortFlag){
    if(jobSearch == null)
      return;
    Criteria criteria = null;
    List results = null;

    // jobsearch criteria code begins
    String status = jobSearch.getStatus().getValue();
    String branchName = jobSearch.getBranch().getValue();
    if(Constants.ALL_STATUS.equals(status)
        || (Constants.CLOSED_STATUS.equals(status)
            && (branchName != null) && !""
            .equals(branchName.trim()))){
      String fromJobid = jobSearch.getFromJobId().getValue();
      String toJobid = jobSearch.getToJobId().getValue();
      Date fromDate = jobSearch.getFromJobFinshDate().getValue();
      Date toDate = jobSearch.getToJobFinshDate().getValue();
      Date etaFromDate = jobSearch.getEtaFrom().getValue();
      Date etaToDate = jobSearch.getEtaTo().getValue();
      Date nomFromDate = jobSearch.getNomDateFrom().getValue();
      Date nomToDate = jobSearch.getNomDateTo().getValue();
      if(((fromJobid == null || fromJobid.equals("")) && (toJobid == null || toJobid
          .equals("")))
          && (fromDate == null || toDate == null)
          && (etaFromDate == null || etaToDate == null)
          && (nomFromDate == null || nomToDate == null)){
        String invoice = jobSearch.getInvoice().getValue();
        // String schedulerVal=jobSearch.getScheduler().getValue();
        if(invoice == null || invoice.trim().isEmpty()){

          /*
           * throw new RuntimeException(
           * "Select either From/To Date fields or From/To Job Id fields: "
           * );
           */
          jobSearch.setTabFlag("criteria");
          throw new ServiceException(
              "select.either.from.to.date.or.job.fields");
        }
      }
    }
    if(!reqForm.equals("jobSearch")
        && (Constants.OPEN_STATUS.equals(status)
            || Constants.REOPENED_STATUS.equals(status)
            || Constants.CANCELLED_STATUS.equals(status) || Constants.OPENREOPEN_STATUS
            .equals(status))){
      String searchRes = jobSearch.getSearchResults().getValue();
      if(searchRes == null || (searchRes != null && searchRes.equals(""))){
        // throw new RuntimeException("Select Number Of Jobs :");
        jobSearch.setTabFlag("criteria");
        throw new ServiceException("select.number.of.jobs");
      }
    }
    // CreateCriteria Code start
    Session sess = null;
    try{
      sess = getDao().openHibernateSession();

      /*
       * if(sortFlag == null || (sortFlag.trim().equals("")) ||
       * sortFlag.trim().equals("null")) { sortFlag = "job.jobNumber"; }
       */
      criteria = sess.createCriteria(JobOrder.class, "job");
      boolean jci = false;
      boolean sc = false;

      if(sortFlag == null || (sortFlag.trim().equals(""))
          || sortFlag.trim().equals("null")){
        // Results sort order
        LogConfigDetail logConfigDetail = new LogConfigDetail();
        List sortOrder = null;
        if(pageNumber == 1
            && (sortFlag == null || sortFlag.trim().equals("") || sortFlag
                .trim().equals("null"))){
          List sortList = new ArrayList();
          sortList = jobSearch.getResultSortOrder();
          if(sortList != null){
            if(jobSearch.getSessionResultSortOrder() != null
                && jobSearch.getSessionResultSortOrder().size() > 0)
              jobSearch.getSessionResultSortOrder().clear();
            jobSearch.setSessionResultSortOrder(sortList);
            sortOrder = jobSearch.getSessionResultSortOrder();
          }
          else{
            if(jobSearch.getSessionResultSortOrder() != null
                && jobSearch.getSessionResultSortOrder().size() > 0)
              jobSearch.getSessionResultSortOrder().clear();
          }

        }
        else{
          List uiSortOrder = jobSearch.getResultSortOrder();
          if(uiSortOrder == null)
            sortOrder = jobSearch.getSessionResultSortOrder();
          else
            sortOrder = uiSortOrder;
        }

        if(sortOrder != null){
          for(int i = 0; i < sortOrder.size(); i++){
            String sortValue = (String) sortOrder.get(i);
            if(sortValue != null){
              logConfigDetail = getDbColumnValue(sortValue);
              if(logConfigDetail != null){
                if(logConfigDetail.getDbColName().trim()
                    .equals("job.jobNumber"))
                  criteria
                      .addOrder(OrderBySqlFormula
                          .sqlFormula(" to_number(substr(this_.JOB_NUMBER, INSTR(this_.JOB_NUMBER, '-')+1 )) asc "));
                else
                  criteria.addOrder(Order.asc(logConfigDetail
                      .getDbColName()));
              }
            }
            if(sortFlag == null || (sortFlag.trim().equals(""))
                || sortFlag.trim().equals("null"))
              sortFlag = logConfigDetail.getDbColName();
            else
              sortFlag = sortFlag + ","
                  + logConfigDetail.getDbColName();
          }
        }
        else{
          // criteria.addOrder(Order.asc("job.jobNumber"));
          criteria
              .addOrder(OrderBySqlFormula
                  .sqlFormula(" to_number(substr(this_.JOB_NUMBER, INSTR(this_.JOB_NUMBER, '-')+1 )) asc "));
        }

      }
      else{
        // header sort order
        if(sortFlag != null && !sortFlag.equals("")
            && !sortFlag.equalsIgnoreCase("mh_col13")
            && !sortFlag.equalsIgnoreCase("mh_col14")
            && !sortFlag.equalsIgnoreCase("mh_col19")
            && !sortFlag.equalsIgnoreCase("mh_col32")
            ){
          /*
           * if(sortFlag.trim().equals("job.jobNumber"))
           * criteria.addOrder(OrderBySqlFormula.sqlFormula(
           * " to_number(substr(this_.JOB_NUMBER, INSTR(this_.JOB_NUMBER, '-')+1)) asc "
           * )); else criteria.addOrder(Order.asc(sortFlag));
           */
          // Setting Sort Order on 03/02/09
          // START : #119240	
          //if(reqForm != null && !reqForm.equals("jobSearch")){
        	if(reqForm != null && (reqForm.equals("jobSearch") || reqForm.equals("jobLog"))){
          // END : #119240 		
            if(sortFlag.trim().equals("job.jobNumber")){
              if(jobSearch.getSortOrderFlag() != null
                  && jobSearch.getSortOrderFlag().trim()
                      .equals("asc")){
                criteria
                    .addOrder(OrderBySqlFormula
                        .sqlFormula(" to_number(substr(this_.JOB_NUMBER, INSTR(this_.JOB_NUMBER, '-')+1)) asc "));
              }
              else{
                criteria
                    .addOrder(OrderBySqlFormula
                        .sqlFormula(" to_number(substr(this_.JOB_NUMBER, INSTR(this_.JOB_NUMBER, '-')+1)) desc "));
              }
            }
            else if(jobSearch.getSortOrderFlag() != null
                && jobSearch.getSortOrderFlag().trim().equals(
                    "asc")){
              criteria.addOrder(Order.asc(sortFlag));
            }
            else{
              criteria.addOrder(Order.desc(sortFlag));
            }
          }
          else{
            if(sortFlag.trim().equals("job.jobNumber")){
              criteria
                  .addOrder(OrderBySqlFormula
                      .sqlFormula(" to_number(substr(this_.JOB_NUMBER, INSTR(this_.JOB_NUMBER, '-')+1)) asc "));
            }
            else{
              criteria.addOrder(Order.asc(sortFlag));
            }
          }// End
        }
      }
      String buName = jobSearch.getBusinessUnit().getValue();
      if((buName != null) && !"".equals(buName.trim())){
        criteria.add(Expression.eq("buName", buName.trim()
            .toUpperCase()));
      }

      /*
       * String invoice = jobSearch.getInvoice().getValue(); if(invoice ==
       * null || invoice.trim().equals("")) { if(branchName == null)
       * branchName="";
       * criteria.add(Expression.like("branchName",branchName
       * .trim().toUpperCase(),MatchMode.ANYWHERE)); }
       */
      
      //START: To fix Issue # 108032
      boolean blnForInvoiceonlysearch = false;
      
      if((null == branchName || "".equals(branchName.trim()))
    	  && (null == jobSearch.getJobType().getValue() || "".equals(jobSearch.getJobType().getValue().trim()))
    	  && (null != jobSearch.getInvoice().getValue() && !"".equals(jobSearch.getInvoice().getValue().trim()))	  
    	  ){
    	  blnForInvoiceonlysearch = true;
      }
      //END: To fix Issue # 108032
      
      if(branchName == null)
        branchName = "";
      //START: To fix Issue # 108032
//      criteria.add(Expression.like("branchName", branchName.trim()
//	          .toUpperCase(), MatchMode.ANYWHERE));
      if(!blnForInvoiceonlysearch){
	  /* criteria.add(Expression.like("branchName", branchName.trim()
	          .toUpperCase(), MatchMode.ANYWHERE));*/
	    /*For Itrack Issue #96935 Starts 16-06-09*/
    	if(branchName != null && !branchName.isEmpty()){
	      criteria.add(Expression.like("branchName", branchName.trim()
		          .toUpperCase(), MatchMode.START));
    	}
    	/*For Itrack Issue #96935 Ends 16-06-09*/
      }
      //END: To fix Issue # 108032

      String contractDesc = jobSearch.getContractDescription().getValue();

      if((contractDesc != null) && !"".equals(contractDesc.trim())){
        criteria.add(Expression.like("contract.description",
            contractDesc.trim(), MatchMode.ANYWHERE).ignoreCase());
      }
      String invoiceStatus = jobSearch.getInvoiceStatus().getValue();

      if((invoiceStatus != null) && !"".equals(invoiceStatus.trim())
          && !"0".equals(invoiceStatus.trim())){
        criteria.add(Expression.eq("jobc.jobContractStatus",
            invoiceStatus));
      }
      String contractStatus = jobSearch.getContractStatus().getValue();

      if((contractStatus != null) && !"".equals(contractStatus.trim())){
        criteria.add(Expression.eq("contract.status", contractStatus));
      }

      String createdBy = jobSearch.getCreatedBy().getValue();

      if((createdBy != null) && !"".equals(createdBy.trim())){
        criteria.add(Expression.like("createdByUserName",
            createdBy.trim(), MatchMode.ANYWHERE).ignoreCase());
      }

      String modifiedBy = jobSearch.getModifiedBy().getValue();

      if((modifiedBy != null) && !"".equals(modifiedBy.trim())){
        criteria.add(Expression.like("updatedByUserName",
            modifiedBy.trim(), MatchMode.ANYWHERE).ignoreCase());
      }
      if((status != null) && !"".equals(status.trim())){
        status = status.trim();
        if(status.equals(Constants.ALL_STATUS)){
          criteria.add(Expression.in("jobStatus", new String[] {
              Constants.OPEN_STATUS, Constants.CLOSED_STATUS,
              Constants.REOPENED_STATUS,
              Constants.CANCELLED_STATUS }));
        }
        else if(status.equals(Constants.OPENREOPEN_STATUS)){
          criteria
              .add(Expression
                  .or(
                      Expression
                          .in(
                              "jobStatus",
                              new String[] { Constants.OPEN_STATUS }),
                      Expression
                          .in(
                              "jobStatus",
                              new String[] { Constants.REOPENED_STATUS })));
        }
        else if(status.equals(Constants.OPEN_STATUS)){
          criteria.add(Expression.eq("jobStatus",
              Constants.OPEN_STATUS));
        }
        else if(status.equals(Constants.CLOSED_STATUS)){
          criteria.add(Expression.eq("jobStatus",
              Constants.CLOSED_STATUS));
        }
        else if(status.equals(Constants.CANCELLED_STATUS)){
          criteria.add(Expression.eq("jobStatus",
              Constants.CANCELLED_STATUS));
        }
        else
          criteria.add(Expression.eq("jobStatus",
              Constants.REOPENED_STATUS));
      }

      String jobType = jobSearch.getJobType().getValue();
      if(jobType == null)
        jobType = "";
      
      //START: To fix Issue # 108032
//      criteria.add(Expression.like("jobType", jobType.trim(),
//	          MatchMode.ANYWHERE).ignoreCase());
      if(!blnForInvoiceonlysearch){
    	 /* criteria.add(Expression.like("jobType", jobType.trim(),
	          MatchMode.ANYWHERE).ignoreCase());*/
    	  /*For Itrack Issue #96935 Starts 16-06-09*/
    	  if(jobType != null && !jobType.isEmpty()){
    		  criteria.add(Expression.eq("jobType", jobType).ignoreCase());
    	  }
    	  /*For Itrack Issue #96935 Ends 16-06-09*/
      }
      //END: To fix Issue # 108032

      String fromJobid = jobSearch.getFromJobId().getValue();
      String toJobid = jobSearch.getToJobId().getValue();
      if((fromJobid != null) && !"".equals(fromJobid)
          && ((toJobid != null) && !"".equals(toJobid))){
        String fromJobId = "'" + fromJobid.trim() + "'";
        String toJobId = "'" + toJobid.trim() + "'";
        criteria
            .add(Expression
                .sql("substr(this_.JOB_NUMBER, INSTR(this_.JOB_NUMBER, '-')+1 ) between to_number(lower(to_char(substr("
                    + fromJobId
                    + ", INSTR("
                    + fromJobId
                    + ", '-')+1)))) and to_number(lower(to_char(substr("
                    + toJobId
                    + ", INSTR("
                    + toJobId
                    + ", '-')+1))))"));
      }
      else{
        /* if((fromJobid != null) && !"".equals(fromJobid.trim())){
          fromJobid = fromJobid.trim();
          criteria.add(Expression.like("jobNumber", fromJobid
              .toUpperCase(), MatchMode.ANYWHERE));
        }
        if((toJobid != null) && !"".equals(toJobid.trim())){
          toJobid = toJobid.trim();
          criteria.add(Expression.like("jobNumber", toJobid
              .toUpperCase(), MatchMode.ANYWHERE));
        }*/
        /*For Itrack Issue #96935 Starts 16-06-09*/
        if((fromJobid != null) && !"".equals(fromJobid.trim())){
            fromJobid = fromJobid.trim();
            criteria.add(Expression.like("jobNumber", fromJobid
                .toUpperCase(), MatchMode.START));
        }
        if((toJobid != null) && !"".equals(toJobid.trim())){
            toJobid = toJobid.trim();
            criteria.add(Expression.like("jobNumber", toJobid
                .toUpperCase(), MatchMode.START));
        }
        /*For Itrack Issue #96935 Ends 16-06-09*/
      }

      Date fromJobFinshDate = jobSearch.getFromJobFinshDate().getValue();
      Date toJobFinshDate = jobSearch.getToJobFinshDate().getValue();
      if(fromJobFinshDate != null && toJobFinshDate != null){
        criteria.add(Expression.between("jobFinishDate",
            fromJobFinshDate, toJobFinshDate));
      }

      Date etaFromDate = jobSearch.getEtaFrom().getValue();
      Date etaToDate = jobSearch.getEtaTo().getValue();
      if(etaFromDate != null && etaToDate != null){
        criteria.add(Expression.between("etaDate", etaFromDate,
            etaToDate));
      }
      Date nomFromDate = jobSearch.getNomDateFrom().getValue();
      Date nomToDate = jobSearch.getNomDateTo().getValue();
      if(nomFromDate != null && nomToDate != null){
        criteria.add(Expression.between("nominationDate", nomFromDate,
            nomToDate));
      }

      int vesselOperator = jobSearch.getVessel().getOperator();
      String vesselVal = jobSearch.getVessel().getValue();
      if((vesselVal != null) && !"".equals(vesselVal.trim())){
        vesselVal = vesselVal.trim();
        if(vesselOperator == Constants.CONTAINS){
          criteria.add(Expression.like("vesselNames", vesselVal,
              MatchMode.ANYWHERE).ignoreCase());
        }
        else if(vesselOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.like("vesselNames", vesselVal,
              MatchMode.START).ignoreCase());
        }
        else if(vesselOperator == Constants.EQUALS){
          criteria.add(Expression.eq("vesselNames", vesselVal)
              .ignoreCase());
        }
        else if(vesselOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("vesselNames", vesselVal)
              .ignoreCase());
        }
      }

      int productOperator = jobSearch.getProduct().getOperator();
      String productVal = jobSearch.getProduct().getValue();
      if((productVal != null) && !"".equals(productVal.trim())){
        productVal = productVal.trim();
        if(productOperator == Constants.CONTAINS){
          criteria.add(Expression.like("productNames", productVal,
              MatchMode.ANYWHERE).ignoreCase());
        }
        else if(productOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.like("productNames", productVal,
              MatchMode.START).ignoreCase());
        }
        else if(productOperator == Constants.EQUALS){
          criteria.add(Expression.eq("productNames", productVal)
              .ignoreCase());
        }
        else if(productOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("productNames", productVal)
              .ignoreCase());
        }
      }
      int portOperator = jobSearch.getPort().getOperator();
      String portVal = jobSearch.getPort().getValue();
      if((portVal != null) && !"".equals(portVal.trim())){
        portVal = portVal.trim();
        if(portOperator == Constants.CONTAINS){
          criteria.add(Expression.like("serv.city", portVal,
              MatchMode.ANYWHERE).ignoreCase());
        }
        else if(portOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.like("serv.city", portVal,
              MatchMode.START).ignoreCase());
        }
        else if(portOperator == Constants.EQUALS){
          criteria.add(Expression.eq("serv.city", portVal)
              .ignoreCase());
        }
        else if(portOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("serv.city", portVal)
              .ignoreCase());
        }
      }

      int svcLocationOperator = jobSearch.getSvcLocation().getOperator();
      String svcLocationVal = jobSearch.getSvcLocation().getValue();
      if((svcLocationVal != null) && !"".equals(svcLocationVal.trim())){
        svcLocationVal = svcLocationVal.trim();
        if(svcLocationOperator == Constants.CONTAINS){
          criteria.add(Expression.like("serv.name", svcLocationVal,
              MatchMode.ANYWHERE).ignoreCase());
        }
        else if(svcLocationOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.like("serv.name", svcLocationVal,
              MatchMode.START).ignoreCase());
        }
        else if(svcLocationOperator == Constants.EQUALS){
          criteria.add(Expression.eq("serv.name", svcLocationVal)
              .ignoreCase());
        }
        else if(svcLocationOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("serv.name", svcLocationVal)
              .ignoreCase());
        }
      }
      String custRefNum = jobSearch.getCustRefNum().getValue();
      if((custRefNum != null) && !"".equals(custRefNum.trim())){
        custRefNum = custRefNum.trim();
        criteria.add(Expression.like("jobc.custRefNum", custRefNum,
            MatchMode.ANYWHERE).ignoreCase());
      }
      String icbRefNum = jobSearch.getIcbRefNum().getValue();
      if((icbRefNum != null) && !"".equals(icbRefNum.trim())){
        icbRefNum = icbRefNum.trim();
        criteria.add(Expression.like("jobc.invoiceValue5", icbRefNum,
            MatchMode.ANYWHERE).ignoreCase());
      }
      String invoice = jobSearch.getInvoice().getValue();
      if(invoice != null && !invoice.equals("")){
        jci = true;

      //START: To fix Issue # 108032
       /* criteria.add(Expression.like("jobcinvc.invoice",invoice,MatchMode.
         ANYWHERE).ignoreCase());   */   
       /*For Itrack Issue #96935 Starts 16-06-09*/
       criteria.add(Expression.like("jobcinvc.invoice",invoice,MatchMode.
                START).ignoreCase());
       /*For Itrack Issue #96935 Ends 16-06-09*/
//        criteria.add(Expression.sql(
//            "contains(jobcinvc5_.INVOICE, ?)>0", "%"
//                + invoice.trim() + "%", Hibernate.STRING));
        
      //END: To fix Issue # 108032
        
        // criteria.add(
        // Expression.sql("jobcinvc5_.INVOICE) like (?)",invoiceId,
        // Hibernate.STRING));
      }
      int companyIdOperator = jobSearch.getCompanyId().getOperator();
      String companyIdVal = jobSearch.getCompanyId().getValue();

      if(companyIdVal != null && !"".equals(companyIdVal.trim())){
        companyIdVal = companyIdVal.trim();
        if(companyIdOperator == Constants.CONTAINS){
          criteria.add(Expression.like("jobc.custCode", companyIdVal,
              MatchMode.ANYWHERE).ignoreCase());
        }
        else if(companyIdOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.like("jobc.custCode", companyIdVal,
              MatchMode.START).ignoreCase());
        }
        else if(companyIdOperator == Constants.EQUALS){
          criteria.add(Expression.eq("jobc.custCode", companyIdVal)
              .ignoreCase());
        }
        else if(companyIdOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("jobc.custCode", companyIdVal)
              .ignoreCase());
        }
      }

      int companyOperator = jobSearch.getCompany().getOperator();
      String companyVal = jobSearch.getCompany().getValue();
      if((companyVal != null) && !"".equals(companyVal.trim())){
        companyVal = companyVal.trim();
        if(companyOperator == Constants.CONTAINS){
          criteria.add(Expression.like("cust.name", companyVal,
              MatchMode.ANYWHERE).ignoreCase());
        }
        else if(companyOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.like("cust.name", companyVal,
              MatchMode.START).ignoreCase());
        }
        else if(companyOperator == Constants.EQUALS){
          criteria.add(Expression.eq("cust.name", companyVal)
              .ignoreCase());
        }
        else if(companyOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("cust.name", companyVal)
              .ignoreCase());
        }
      }

      int contactIdOperator = jobSearch.getBillingContactId()
          .getOperator();
      String contactIdVal = jobSearch.getBillingContactId().getValue();
      if(contactIdVal != null && !"".equals(contactIdVal.trim())){
        String strBegin = contactIdVal + '%';
        String strContains = '%' + contactIdVal + '%';

        long contactId = (Long.valueOf(contactIdVal)).longValue();

        if(contactIdOperator == Constants.CONTAINS){
          // criteria.add(Expression.like("jobc.billingContact.id",
          // contactIdVal,MatchMode.ANYWHERE).ignoreCase());
          criteria.add(Expression.sql(
              "to_char(jobc4_.BILLING_CONTACT_ID) like (?)",
              strContains, Hibernate.STRING));
        }
        else if(contactIdOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.sql(
              "to_char(jobc4_.BILLING_CONTACT_ID) like(?)",
              strBegin, Hibernate.STRING));
          // criteria.add(Expression.like("jobc.billingContact.id",
          // contactIdVal,MatchMode.START).ignoreCase());
        }
        else if(contactIdOperator == Constants.EQUALS){
          criteria.add(Expression.eq("jobc.billingContact.id",
              contactId));
        }
        else if(contactIdOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("jobc.billingContact.id",
              contactId));
        }
      }
      int contractIdOperator = jobSearch.getContractId().getOperator();
      String contractIdVal = jobSearch.getContractId().getValue();
      if(contractIdVal != null && !"".equals(contractIdVal.trim())){
        contractIdVal = contractIdVal.trim();
        if(contractIdOperator == Constants.CONTAINS){
          criteria.add(Expression.like("jobc.contractCode",
              contractIdVal, MatchMode.ANYWHERE).ignoreCase());
        }
        else if(contractIdOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.like("jobc.contractCode",
              contractIdVal, MatchMode.START).ignoreCase());
        }
        else if(contractIdOperator == Constants.EQUALS){
          criteria.add(Expression.eq("jobc.contractCode",
              contractIdVal).ignoreCase());
        }
        else if(contractIdOperator == Constants.NOT_EQUALS){
          criteria.add(Expression.ne("jobc.contractCode",
              contractIdVal).ignoreCase());
        }
      }

      int billingContactOperator = jobSearch.getBillingContact()
          .getOperator();
      String billingContactVal = jobSearch.getBillingContact().getValue();
      if((billingContactVal != null)
          && !"".equals(billingContactVal.trim())){
        billingContactVal = billingContactVal.trim();
        /*
         * Ketan START 02/09/09 - code modified to take care of first
         * name, last name with space as criteria
         */
        String billingContactName[] = billingContactVal.split(" ");
        if(billingContactOperator == Constants.CONTAINS){
          if(billingContactName.length > 1){
            criteria.add(Expression.and(Expression.like(
                "blconct.firstName", billingContactName[0],
                MatchMode.ANYWHERE).ignoreCase(), Expression
                .like("blconct.lastName",
                    billingContactName[1],
                    MatchMode.ANYWHERE).ignoreCase()));
          }
          else{
            criteria.add(Expression.or(Expression.like(
                "blconct.firstName", billingContactVal,
                MatchMode.ANYWHERE).ignoreCase(), Expression
                .like("blconct.lastName", billingContactVal,
                    MatchMode.ANYWHERE).ignoreCase()));
          }

        }
        else if(billingContactOperator == Constants.BEGINS_WITH){
          if(billingContactName.length > 1){
            criteria.add(Expression.and(Expression.like(
                "blconct.firstName", billingContactName[0],
                MatchMode.START).ignoreCase(), Expression.like(
                "blconct.lastName", billingContactName[1],
                MatchMode.START).ignoreCase()));
          }
          else{
            criteria.add(Expression.or(Expression.like(
                "blconct.firstName", billingContactVal,
                MatchMode.START).ignoreCase(), Expression.like(
                "blconct.lastName", billingContactVal,
                MatchMode.START).ignoreCase()));
          }
        }
        else if(billingContactOperator == Constants.EQUALS){
          if(billingContactName.length > 1){
            criteria.add(Expression.and(Expression.eq(
                "blconct.firstName", billingContactName[0])
                .ignoreCase(), Expression.eq(
                "blconct.lastName", billingContactName[1])
                .ignoreCase()));
          }
          else{
            criteria.add(Expression.or(Expression.eq(
                "blconct.firstName", billingContactVal)
                .ignoreCase(), Expression.eq(
                "blconct.lastName", billingContactVal)
                .ignoreCase()));
          }
        }
        else if(billingContactOperator == Constants.NOT_EQUALS){
          if(billingContactName.length > 1){
            criteria.add(Expression.and(Expression.ne(
                "blconct.firstName", billingContactName[0])
                .ignoreCase(), Expression.ne(
                "blconct.lastName", billingContactName[1])
                .ignoreCase()));
          }
          else{
            criteria.add(Expression.or(Expression.ne(
                "blconct.firstName", billingContactVal)
                .ignoreCase(), Expression.ne(
                "blconct.lastName", billingContactVal)
                .ignoreCase()));
          }
        }
      }

      int schedulerIdOperator = jobSearch.getSchedulerId().getOperator();
      Long schedulerIdVal = jobSearch.getSchedulerId().getValue();
      if(schedulerIdVal != null
          && !"".equals(schedulerIdVal.toString().trim())){
        String strBegin = String.valueOf(schedulerIdVal) + '%';
        String strContains = '%' + String.valueOf(schedulerIdVal) + '%';

        if(schedulerIdOperator == Constants.CONTAINS){
          criteria.add(Expression.sql(
              "to_char(jobc4_.CONTACT_ID) like (?)", strContains,
              Hibernate.STRING));

        }
        else if(schedulerIdOperator == Constants.BEGINS_WITH){
          criteria.add(Expression.sql(
              "to_char(jobc4_.CONTACT_ID) like(?)", strBegin,
              Hibernate.STRING));
          // criteria.add(
          // Expression.sql("to_char(jobc.contactId) like(?)"
          // ,strBegin, Hibernate.STRING));

        }
        else if(schedulerIdOperator == Constants.EQUALS){
          criteria.add(Expression
              .eq("jobc.contactId", schedulerIdVal));
        }
        else if(schedulerIdOperator == Constants.NOT_EQUALS){
          criteria.add(Expression
              .ne("jobc.contactId", schedulerIdVal));
        }
      }

      int schedulerOperator = jobSearch.getScheduler().getOperator();
      String schedulerVal = jobSearch.getScheduler().getValue();
      if((schedulerVal != null) && !"".equals(schedulerVal.trim())){
        sc = true;
        schedulerVal = schedulerVal.trim();
        String schedulerName[] = schedulerVal.split(" ");
        if(schedulerOperator == Constants.CONTAINS){
          System.out.println("SCHEDULER NAME LENGTH= "
              + schedulerName.length);
          System.out.println("SCHEDULAR NAME= " + schedulerVal);
          if(schedulerName.length > 1){
            /*
             * criteria.add( Expression.and(
             * Expression.like("contact.firstName"
             * ,schedulerName[0],MatchMode.ANYWHERE).ignoreCase(),
             * Expression
             * .like("contact.lastName",schedulerName[1],MatchMode
             * .ANYWHERE).ignoreCase()) );
             */
            criteria.add(Expression.and(Expression.sql(
                "contains(contact7_.FIRST_NAME, ?)>0", "%"
                    + schedulerName[0] + "%",
                Hibernate.STRING), Expression.sql(
                "contains(contact7_.LAST_NAME, ?)>0", "%"
                    + schedulerName[1] + "%",
                Hibernate.STRING)));
          }
          else{
            /*
             * criteria.add( Expression.or(
             * Expression.like("contact.firstName"
             * ,schedulerVal,MatchMode.ANYWHERE).ignoreCase(),
             * Expression
             * .like("contact.lastName",schedulerVal,MatchMode
             * .ANYWHERE).ignoreCase() ) );
             */
            criteria.add(Expression.or(
                Expression.sql(
                    "contains(contact7_.FIRST_NAME, ?)>0",
                    "%" + schedulerVal + "%",
                    Hibernate.STRING), Expression.sql(
                    "contains(contact7_.LAST_NAME, ?)>0",
                    "%" + schedulerVal + "%",
                    Hibernate.STRING)));

          }

        }
        else if(schedulerOperator == Constants.BEGINS_WITH){
          if(schedulerName.length > 1){
            criteria.add(Expression.and(Expression.like(
                "contact.firstName", schedulerName[0],
                MatchMode.START).ignoreCase(), Expression.like(
                "contact.lastName", schedulerName[1],
                MatchMode.START).ignoreCase()));
          }
          else{
            criteria.add(Expression.or(Expression.like(
                "contact.firstName", schedulerVal,
                MatchMode.START).ignoreCase(), Expression.like(
                "contact.lastName", schedulerVal,
                MatchMode.START).ignoreCase()));
          }
        }
        else if(schedulerOperator == Constants.EQUALS){
          if(schedulerName.length > 1){
            criteria.add(Expression.and(Expression.eq(
                "contact.firstName", schedulerName[0])
                .ignoreCase(), Expression.eq(
                "contact.lastName", schedulerName[1])
                .ignoreCase()));
          }
          else{
            criteria.add(Expression.or(
                Expression
                    .eq("contact.firstName", schedulerVal)
                    .ignoreCase(), Expression.eq(
                    "contact.lastName", schedulerVal)
                    .ignoreCase()));
          }

        }
        else if(schedulerOperator == Constants.NOT_EQUALS){
          if(schedulerName.length > 1){
            criteria.add(Expression.and(Expression.ne(
                "contact.firstName", schedulerName[0])
                .ignoreCase(), Expression.ne(
                "contact.lastName", schedulerName[1])
                .ignoreCase()));
          }
          else{
            criteria.add(Expression.or(
                Expression
                    .ne("contact.firstName", schedulerVal)
                    .ignoreCase(), Expression.ne(
                    "contact.lastName", schedulerVal)
                    .ignoreCase()));
          }
        }
      }
      /*
       * Ketan END 02/09/09 - code modified to take care of first name,
       * last name with space as criteria
       */
      String dispatchStatus = jobSearch.getDispatchStatus().getValue();
      if((dispatchStatus != null) && !"".equals(dispatchStatus.trim())){
        criteria.add(Expression.eq("joblog.dispatchStatus",
            dispatchStatus));
      }
      // Removing null checking
      criteria.add(Restrictions.eq("isTemplate", Boolean.FALSE));
      // criteria.add( Restrictions.or(Restrictions.eq("isTemplate",
      // Boolean.FALSE),Restrictions.isNull("isTemplate")));
      // if(sortFlag == null || (sortFlag.trim().equals("")) ||
      // sortFlag.trim().equals("null"))
      /*For Itrack Issue #96935 Starts 16-06-09*/
   if(!reqForm.equals("jobSearch")){
      criteria.createAlias("shippingAgent", "ship",
              JoinFragment.LEFT_OUTER_JOIN).createAlias("towingCompany",
              "tco", JoinFragment.LEFT_OUTER_JOIN).createAlias(
              "serviceLocation", "serv", JoinFragment.LEFT_OUTER_JOIN)
              .createAlias("jobContracts", "jobc",
                  JoinFragment.LEFT_OUTER_JOIN).createAlias(
                  "jobc.jobContractInvoices",
                  "jobcinvc",
                  jci ? JoinFragment.INNER_JOIN
                      : JoinFragment.LEFT_OUTER_JOIN)
              .createAlias("jobc.customer", "cust",
                  JoinFragment.LEFT_OUTER_JOIN).createAlias(
                  "jobc.contact",
                  "contact",
                  sc ? JoinFragment.INNER_JOIN
                      : JoinFragment.LEFT_OUTER_JOIN)
              .createAlias("jobc.contract", "contract",
                  JoinFragment.LEFT_OUTER_JOIN).createAlias(
                  "jobc.billingContact", "blconct",
                  JoinFragment.LEFT_OUTER_JOIN).createAlias(
                  "jobc.jobLog", "joblog",
                  JoinFragment.LEFT_OUTER_JOIN);
      } else {
    	  criteria.createAlias(
                  "serviceLocation", "serv", JoinFragment.LEFT_OUTER_JOIN)
                  .createAlias("jobContracts", "jobc",
                      JoinFragment.LEFT_OUTER_JOIN).createAlias(
                      "jobc.jobContractInvoices",
                      "jobcinvc",
                      jci ? JoinFragment.INNER_JOIN
                          : JoinFragment.LEFT_OUTER_JOIN)
                  .createAlias("jobc.customer", "cust",
                      JoinFragment.LEFT_OUTER_JOIN).createAlias(
                      "jobc.contact",
                      "contact",
                      sc ? JoinFragment.INNER_JOIN
                          : JoinFragment.LEFT_OUTER_JOIN)
                  .createAlias("jobc.contract", "contract",
                      JoinFragment.LEFT_OUTER_JOIN).createAlias(
                      "jobc.billingContact", "blconct",
                      JoinFragment.LEFT_OUTER_JOIN);
      }
      /*For Itrack Issue #96935 Ends 16-06-09*/
      // Commented due to itrack issue #96935 on 16-06-09
   /* criteria.createAlias("shippingAgent", "ship",
          JoinFragment.LEFT_OUTER_JOIN).createAlias("towingCompany",
          "tco", JoinFragment.LEFT_OUTER_JOIN).createAlias(
          "serviceLocation", "serv", JoinFragment.LEFT_OUTER_JOIN)
          .createAlias("jobContracts", "jobc",
              JoinFragment.LEFT_OUTER_JOIN).createAlias(
              "jobc.jobContractInvoices",
              "jobcinvc",
              jci ? JoinFragment.INNER_JOIN
                  : JoinFragment.LEFT_OUTER_JOIN)
          .createAlias("jobc.customer", "cust",
              JoinFragment.LEFT_OUTER_JOIN).createAlias(
              "jobc.contact",
              "contact",
              sc ? JoinFragment.INNER_JOIN
                  : JoinFragment.LEFT_OUTER_JOIN)
          .createAlias("jobc.contract", "contract",
              JoinFragment.LEFT_OUTER_JOIN).createAlias(
              "jobc.billingContact", "blconct",
              JoinFragment.LEFT_OUTER_JOIN).createAlias(
              "jobc.jobLog", "joblog",
              JoinFragment.LEFT_OUTER_JOIN);
      System.out.println("CRITERIA= " + criteria.toString());*/
      // Commented due to itrack issue #96935 on 16-06-09-END
      if(jobSearch.getSearchFlag() != null
          && jobSearch.getSearchFlag().trim().equals("true")){
        List jobCount = criteria.setResultTransformer(
            Criteria.DISTINCT_ROOT_ENTITY).list();
        if(jobCount != null)
          jobSearch.setNoOfJobs(jobCount.size());
        criteria.setResultTransformer(Criteria.ROOT_ENTITY);
        jobSearch.setSearchFlag("false");
      }
      Pagination pagination = jobSearch.getPagination();

      if(pagination != null){
        criteria.setFirstResult((pagination.getCurrentPageNum() - 1)
            * pagination.getNumInPage());
        criteria.setMaxResults(pagination.getNumInPage());

      }

      log.info("time before executing job search query :" + new Date());
      results = criteria.list();
      log.info("time after executing job search query :" + new Date());
      if(results != null)
        if(pagination != null){
          if(pagination.getTotalRecord() > 0){
            criteria.setProjection(Projections.alias(Projections
                .rowCount(), "numResults"));
            criteria.setFirstResult(0);
            List totalRecordsList = criteria.list();
            if(totalRecordsList.size() > 0){
              Integer totalRecordsInt = (Integer) totalRecordsList
                  .get(0);
              if(totalRecordsInt != null)
                pagination.setTotalRecord(totalRecordsInt
                    .intValue());

            }
          }

          pagination.calculatePages();
        }

    }
    finally{
      // sess.clear();
      if(sess != null){
        getDao().closeHibernateSession(sess);
      }
    }
    List updatedResult = new ArrayList();

    int jobOrderSize = results.size();
    String dispatchStatus = jobSearch.getDispatchStatus().getValue();
    log.info("time before getting jobcontracts :" + new Date());
    if(!reqForm.equals("jobSearch")){
      String currJobNumber[] = new String[jobOrderSize];
      ArrayList al = new ArrayList();
      int j = 0;
      int k = 0;
      for(int i = 0; i < jobOrderSize; i++){
        AddJobContract addJobContract = new AddJobContract();
        JobOrder jobOrder = (JobOrder) results.get(i);
        ServiceLocation serviceLocation = jobOrder.getServiceLocation();
        boolean invoiceJobLog = false;
        if(i > 0){
          currJobNumber[k] = jobOrder.getJobNumber();

          al.add(currJobNumber[k]);
          int jobNumCount = Collections.frequency(al,
              currJobNumber[k]);
          if(jobNumCount == 1){
            Set st = jobOrder.getJobContracts();
            if(st != null && st.size() != 0){
              Iterator itr = st.iterator();
              while (itr.hasNext()){
                AddJobContract addJobContracts = new AddJobContract();
                JobContract jobContract = (JobContract) itr
                    .next();

                addJobContracts.setJobContract(jobContract);
                // List<JobContractNote> jobContractNoteList =
                // getJobContractNotesByJobContract
                // (jobContract);
                Set jobContractNotes = jobContract
                    .getJobContractNotes();

                String notes = "";
                String updateNotes = "";
                //For ITrack#58291- START
                String customerNotes = "";
                String customerNoteDetails ="";
                //For ITrack#58291- END
                if(jobContractNotes != null
                    && jobContractNotes.size() > 0){

                  Iterator jcn = jobContractNotes.iterator();

                  while (jcn.hasNext()){
                    JobContractNote jobContractNote = (JobContractNote) jcn
                        .next();

                    if(jobContractNote.getNote() != null
                        && !jobContractNote.getNote()
                            .trim().equals("")){
                      // Check if JobContractNote's
                      // noteCategory = "log"
                      if(jobContractNote
                          .getNoteCategory() != null
                          && !jobContractNote
                              .getNoteCategory()
                              .trim().equals("")
                          && jobContractNote
                              .getNoteCategory()
                              .trim()
                              .equals(
                                  Constants.LOG_STR)){
                        String dt = DateUtil
                            .formateJobDescriptionDate(jobContractNote
                                .getDateTimeAdded());
                        String date = DateUtil
                            .getTimeFromDate(jobContractNote
                                .getDateTimeAdded());
                        if(notes != null
                            && !notes.trim()
                                .equals("")){
                          updateNotes = updateNotes
                              + ","
                              + jobContractNote
                                  .getNote();
                          notes = notes
                              + "<br>"
                              + jobContractNote
                                  .getAddedByUserName()
                              + ", "
                              + dt
                              + ", "
                              + date
                              + ":"
                              + jobContractNote
                                  .getNote();
                        } else {
                          updateNotes = jobContractNote
                              .getNote();
                          notes = jobContractNote
                              .getAddedByUserName()
                              + ", "
                              + dt
                              + ", "
                              + date
                              + ":"
                              + jobContractNote
                                  .getNote();
                        }
                      } 
                      //For ITrack#58291- START
                      if(jobContractNote
                              .getNoteCategory() != null
                              && !jobContractNote
                                  .getNoteCategory()
                                  .trim().equals("")
                              && jobContractNote
                                  .getNoteCategory()
                                  .trim()
                                  .equals(Constants.CONTRACT_STR)){
                    	 
                    	  String dt = DateUtil
                          .formateJobDescriptionDate(jobContractNote
                              .getDateTimeAdded());
                    	  String date = DateUtil
                          .getTimeFromDate(jobContractNote
                              .getDateTimeAdded());
                    	  customerNotes = jobContractNote
                          .getNote();
                    	  if(customerNoteDetails != null && !customerNoteDetails.isEmpty()){
                    	  customerNoteDetails = customerNoteDetails
                          + "<br>"
                          +jobContractNote
                          .getAddedByUserName()
                          + ", "
                          + dt
                          + ", "
                          + date
                          + ":"
                          + jobContractNote
                              .getNote();
                    	  } else {
                    		  customerNoteDetails = jobContractNote
                              .getAddedByUserName()
                              + ", "
                              + dt
                              + ", "
                              + date
                              + ":"
                              + jobContractNote
                                  .getNote();
                    	  }
                      }//For ITrack#58291- END
                    } // End if
                  } // End While
                } // End if
                addJobContracts.setAddNote(notes);
                addJobContracts.setAddUpdatedNote(updateNotes);
                //For ITrack#58291- START
                addJobContracts.setCustomerNote(customerNotes);
                addJobContracts.setCustomerNoteDetails(customerNoteDetails);
                //For ITrack#58291- END
                
                // START : #127441
	            String cancelNote = "";
	            String cancelNoteDetails ="";
	            if(jobOrder.getCancelReasonNote()!=null && !jobOrder.getCancelReasonNote().trim().equals("")){
	  	        	  String dt = DateUtil.formateJobDescriptionDate(jobOrder.getUpdateTime());
	  	        	  String date = DateUtil.getTimeFromDate(jobOrder.getUpdateTime());
	  	              cancelNote = jobOrder.getCancelReasonNote();
	  	        	  cancelNoteDetails = jobOrder.getCancelReasonUserName()
		              + ", "
		              + dt
		              + ", "
		              + date
		              + ":"
		              + jobOrder.getCancelReasonNote(); 	 	        	  
  	          	}
                addJobContracts.setCancelNote(cancelNote);
                addJobContracts.setCancelNoteDetails(cancelNoteDetails);
                // END : #127441
                                
                // Getting jobContractNotes
                /*
                 * List<JobContractNote> jobContractNoteList =
                 * getJobContractNotesByJobContract
                 * (jobContract);
                 *
                 * String notes=""; String updateNotes="";
                 * if(jobContractNoteList!=null &&
                 * jobContractNoteList.size()!=0) { for(int
                 * n=0;n<jobContractNoteList.size();n++) {
                 * JobContractNote
                 * jobContractNote=jobContractNoteList.get(n);
                 * if(jobContractNote.getNote()!=null&&
                 * !jobContractNote.getNote().trim().equals(""))
                 * { String
                 * dt=DateUtil.formateJobDescriptionDate
                 * (jobContractNote.getDateTimeAdded()); String
                 * date
                 * =DateUtil.getTimeFromDate(jobContractNote.
                 * getDateTimeAdded()); if(notes!=null &&
                 * !notes.trim().equals("")) {
                 * updateNotes=updateNotes
                 * +","+jobContractNote.getNote();
                 * notes=notes+"<br>"
                 * +jobContractNote.getAddedByUserName
                 * ()+", "+dt+
                 * ", "+date+":"+jobContractNote.getNote(); }
                 * else { updateNotes=jobContractNote.getNote();
                 * notes
                 * =jobContractNote.getAddedByUserName()+", "
                 * +dt+", "+date+":"+jobContractNote.getNote();}
                 * } } } addJobContracts.setAddNote(notes);
                 * addJobContracts
                 * .setAddUpdatedNote(updateNotes);
                 */
                if(jobOrder != null){
                  if(jobOrder.getEtaTime() != null){
                    Calendar cal = new GregorianCalendar();
                    cal.setTime(jobOrder.getEtaTime());
                    int hour24 = cal
                        .get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    String etaTime = String.valueOf(hour24)
                        + ":" + String.valueOf(min);
                    Date jobEtaTime = new Date(jobOrder
                        .getEtaTime().getTime());

                    // jobEtaTime = jobOrder.getEtaTime();
                    // addJobContracts.setEtaTime(DateUtil.
                    // getTimeFromDate
                    // (DateUtil.getConvertedDateTime
                    // (jobEtaTime
                    // ,etaTime,Constants.TIME_ZONE
                    // ,jobOrder.getEtaTimeTz())));
                    addJobContracts
                        .setEtaTime(DateUtil
                            .getTimewithsecondsFromDate(DateUtil
                                .getConvertedDateTime(
                                    jobEtaTime,
                                    etaTime,
                                    Constants.TIME_ZONE,
                                    jobOrder
                                        .getEtaTimeTz())));

                  }
                }
                // addJobContract.setJobContract(jobContract);
                JobLog jobLog = jobContract.getJobLog();
                /*
                 * JobLog jobLog = null; if((dispatchStatus !=
                 * null) && !"".equals(dispatchStatus.trim())) {
                 * System.out.println(
                 * "3 time before executing job log search query :"
                 * +new Date()); jobLog =
                 * getJobLogByJobContract(
                 * jobContract,dispatchStatus);
                 * System.out.println
                 * ("4 time after executing job log search query :"
                 * +new Date()); }else {System.out.println(
                 * "5 time before executing job log search query :"
                 * +new Date()); jobLog =
                 * getJobLogByJobContracts(jobContract);
                 * System.out.println(
                 * "6 time after executing job contract note search query :"
                 * +new Date()); }
                 */

                // setting invoice values
                if(addJobContracts.getJobContract() != null
                    && addJobContracts.getJobContract()
                        .getJobContractInvoices() != null
                    && addJobContracts.getJobContract()
                        .getJobContractInvoices()
                        .size() != 0){
                  Set jobcInvoiceSet = addJobContracts
                      .getJobContract()
                      .getJobContractInvoices();
                  if(jobcInvoiceSet != null
                      && jobcInvoiceSet.size() != 0){
                    Iterator itrInvoice = jobcInvoiceSet
                        .iterator();
                    JobContractInvoice jobContractInvoice;
                    boolean hasJoblog = true;
                    JobLog invoiceRecJobLog = null;
                    Iterator itrInvc = jobcInvoiceSet
                        .iterator();
                    String latestInvoice = "";

                    List jobContractInv = null;
                    // getting latest invoice of jobcontract
                    jobContractInv = getLatestInvoiceByJobContractId(jobContract
                        .getId());
                    if(jobContractInv != null
                        && jobContractInv.size() > 0)
                      latestInvoice = ((JobContractInvoice) jobContractInv
                          .get(0)).getInvoice();
                    while (itrInvoice.hasNext()){

                      AddJobContract addJobCon = new AddJobContract();

                      // addJobCon.setAddJobContract(
                      // addJobContracts);
                      addJobCon
                          .setJobContract(addJobContracts
                              .getJobContract());
                      jobContractInvoice = (JobContractInvoice) itrInvoice
                          .next();
                      addJobCon
                          .setLatestJobContractInvoice(jobContractInvoice);
                      addJobCon.setAddNote(notes);
                      addJobCon
                          .setAddUpdatedNote(updateNotes);
                      //For ITrack#58291- START
                      addJobCon.setCustomerNote(customerNotes);
                      addJobCon.setCustomerNoteDetails(customerNoteDetails);
                      //For ITrack#58291- END
                      // START : #127441
                      addJobCon.setCancelNote(cancelNote);
                      addJobCon.setCancelNoteDetails(cancelNoteDetails);
                      // END : #127441
                      
                      addJobCon
                          .setEtaTime(addJobContracts
                              .getEtaTime());
                      invoiceJobLog = true;

                      addJobCon = setUiJobSearchAndJobLogFields(
                          jobOrder, addJobCon,
                          reqForm, invoiceJobLog);

                      if(jobLog != null){
                        addJobCon = getJobLogDateandTime(
                            addJobCon, jobLog);
                        addJobCon.setJobLog(jobLog);
                      }
                      else{
                        if(hasJoblog){
                          invoiceRecJobLog = new JobLog();
                          addJobCon
                              .setJobLog(invoiceRecJobLog);
                          hasJoblog = false;
                        }
                        else
                          addJobCon
                              .setJobLog(invoiceRecJobLog);
                      }
                      // setting latest invoice row enable
                      if(latestInvoice != null
                          && !latestInvoice.trim()
                              .equals("")
                          && latestInvoice
                              .trim()
                              .equals(
                                  addJobCon
                                      .getLatestJobContractInvoice()
                                      .getInvoice())){
                        addJobCon
                            .setEtaDateTimeFlag(Constants.FALSE_VALUE);
                        addJobCon
                            .setInvoiceFlag(Constants.FALSE_VALUE);
                      }
                      else{
                        addJobCon
                            .setEtaDateTimeFlag(Constants.TRUE_VALUE);
                        addJobCon
                            .setInvoiceFlag(Constants.TRUE_VALUE);
                      }
                      updatedResult.add(addJobCon);
                    }

                    invoiceJobLog = false;
                  }
                }
                else{
                  JobContractInvoice jobContractInvoice = new JobContractInvoice();
                  jobContractInvoice.setContactId(0);
                  addJobContracts
                      .setLatestJobContractInvoice(jobContractInvoice);
                  // addJobContracts =
                  // setUiJobSearchAndJobLogFields
                  // (jobOrder,addJobContracts,reqForm);
                  if(jobLog != null){
                    addJobContracts = getJobLogDateandTime(
                        addJobContracts, jobLog);
                    addJobContracts.setJobLog(jobLog);
                  }
                  else{
                    addJobContracts.setJobLog(new JobLog());
                  }
                  addJobContracts = setUiJobSearchAndJobLogFields(
                      jobOrder, addJobContracts, reqForm,
                      invoiceJobLog);
                  updatedResult.add(addJobContracts);
                }

              }

            }

            else{
              // ContractCustContact contractCustContact = new
              // ContractCustContact();
              if(jobOrder != null){
                if(jobOrder.getEtaTime() != null){

                  Calendar cal = new GregorianCalendar();
                  cal.setTime(jobOrder.getEtaTime());
                  int hour24 = cal.get(Calendar.HOUR_OF_DAY);
                  int min = cal.get(Calendar.MINUTE);
                  Date jobEtaTime = new Date(jobOrder
                      .getEtaTime().getTime());
                  String etaTime = String.valueOf(hour24)
                      + ":" + String.valueOf(min);
                  // addJobContract.setEtaTime(DateUtil.
                  // getTimeFromDate
                  // (DateUtil.getConvertedDateTime
                  // (jobEtaTime,
                  // etaTime,Constants.TIME_ZONE,jobOrder
                  // .getEtaTimeTz())));
                  addJobContract
                      .setEtaTime(DateUtil
                          .getTimewithsecondsFromDate(DateUtil
                              .getConvertedDateTime(
                                  jobEtaTime,
                                  etaTime,
                                  Constants.TIME_ZONE,
                                  jobOrder
                                      .getEtaTimeTz())));
                }
              }
              // START : #127441
              String cancelNote = "";
              String cancelNoteDetails ="";
              if(jobOrder != null && jobOrder.getCancelReasonNote()!=null && !jobOrder.getCancelReasonNote().trim().equals("")){ 
  	        	  String dt = DateUtil.formateJobDescriptionDate(jobOrder.getUpdateTime());
  	        	  String date = DateUtil.getTimeFromDate(jobOrder.getUpdateTime());
  	              cancelNote = jobOrder.getCancelReasonNote();
  	        	  cancelNoteDetails = jobOrder.getCancelReasonUserName()
  	              + ", "
  	              + dt
  	              + ", "
  	              + date
  	              + ":"
  	              + jobOrder.getCancelReasonNote();	        	  
  	         }
             addJobContract.setCancelNote(cancelNote);
             addJobContract.setCancelNoteDetails(cancelNoteDetails);
             // END : #127441
             
              JobContractInvoice jobContractInvoice = new JobContractInvoice();
              JobContract jobContract = new JobContract();
              Customer customer = new Customer();
              Contact contact = new Contact();

              jobContract.setCustomer(customer);
              jobContract.setContact(contact);
              addJobContract.setJobContract(jobContract);
              jobContractInvoice.setContactId(0);
              addJobContract
                  .setLatestJobContractInvoice(jobContractInvoice);
              addJobContract = setUiJobSearchAndJobLogFields(
                  jobOrder, addJobContract, reqForm,
                  invoiceJobLog);
              addJobContract
                  .setEtaDateTimeFlag(Constants.FALSE_VALUE);
              addJobContract.setInvoiceFlag(Constants.TRUE_VALUE);

              updatedResult.add(addJobContract);
            }
          }
          k++;
        }
        else{
          // process job log first record
          Set st = jobOrder.getJobContracts();
          al.add(jobOrder.getJobNumber());
          if(st != null && st.size() != 0){
            Iterator itr = st.iterator();
            while (itr.hasNext()){
              AddJobContract addJobContracts = new AddJobContract();

              JobContract jobContract = (JobContract) itr.next();

              addJobContracts.setJobContract(jobContract);
              // List<JobContractNote> jobContractNoteList=
              // getJobContractNotesByJobContract(jobContract);
              Set jobContractNotes = jobContract
                  .getJobContractNotes();

              String notes = "";
              String updateNotes = "";
              //For ITrack#58291- START
              String customerNotes = "";
              String customerNoteDetails ="";
              //For ITrack#58291- END
              if(jobContractNotes != null
                  && jobContractNotes.size() > 0){

                Iterator jcn = jobContractNotes.iterator();
                while (jcn.hasNext()){
                  JobContractNote jobContractNote = (JobContractNote) jcn
                      .next();

                  if(jobContractNote.getNote() != null
                      && !jobContractNote.getNote()
                          .trim().equals("")){
                    // Check if JobContractNote's
                    // noteCategory = "log"
                    if(jobContractNote.getNoteCategory() != null
                        && !jobContractNote
                            .getNoteCategory()
                            .trim().equals("")
                        && jobContractNote
                            .getNoteCategory()
                            .trim()
                            .equals(
                                Constants.LOG_STR)){
                      String dt = DateUtil
                          .formateJobDescriptionDate(jobContractNote
                              .getDateTimeAdded());
                      String date = DateUtil
                          .getTimeFromDate(jobContractNote
                              .getDateTimeAdded());
                      if(notes != null
                          && !notes.trim().equals("")){
                        updateNotes = updateNotes
                            + ","
                            + jobContractNote
                                .getNote();
                        notes = notes
                            + "<br>"
                            + jobContractNote
                                .getAddedByUserName()
                            + ", "
                            + dt
                            + ", "
                            + date
                            + ":"
                            + jobContractNote
                                .getNote();
                      }
                      else{
                        updateNotes = jobContractNote
                            .getNote();
                        notes = jobContractNote
                            .getAddedByUserName()
                            + ", "
                            + dt
                            + ", "
                            + date
                            + ":"
                            + jobContractNote
                                .getNote();
                      }// End if-else
                    }
                    //For ITrack#58291- START
                    if(jobContractNote
                            .getNoteCategory() != null
                            && !jobContractNote
                                .getNoteCategory()
                                .trim().equals("")
                            && jobContractNote
                                .getNoteCategory()
                                .trim()
                                .equals(Constants.CONTRACT_STR)){
                  	 
                  	  String dt = DateUtil
                        .formateJobDescriptionDate(jobContractNote
                            .getDateTimeAdded());
                  	  String date = DateUtil
                        .getTimeFromDate(jobContractNote
                            .getDateTimeAdded());
                  	  customerNotes = jobContractNote
                        .getNote();
                  	  if(customerNoteDetails != null && !customerNoteDetails.isEmpty()){
                  	  customerNoteDetails = customerNoteDetails
                        + "<br>"
                        +jobContractNote
                        .getAddedByUserName()
                        + ", "
                        + dt
                        + ", "
                        + date
                        + ":"
                        + jobContractNote
                            .getNote();
                  	  } else {
                  		  customerNoteDetails = jobContractNote
                            .getAddedByUserName()
                            + ", "
                            + dt
                            + ", "
                            + date
                            + ":"
                            + jobContractNote
                                .getNote();
                  	  }
                    }//For ITrack#58291- END
                  } // End if
                } // End While
              } // End if
              addJobContracts.setAddNote(notes);
              addJobContracts.setAddUpdatedNote(updateNotes);
              //For ITrack#58291- START
              addJobContracts.setCustomerNote(customerNotes);
              addJobContracts.setCustomerNoteDetails(customerNoteDetails);
              //For ITrack#58291- END
              
           // START : #127441
              String cancelNote = "";
              String cancelNoteDetails ="";
              if(jobOrder.getCancelReasonNote()!=null && !jobOrder.getCancelReasonNote().trim().equals("")){ 
	        	  String dt = DateUtil.formateJobDescriptionDate(jobOrder.getUpdateTime());
	        	  String date = DateUtil.getTimeFromDate(jobOrder.getUpdateTime());
	              cancelNote = jobOrder.getCancelReasonNote();
	        	  cancelNoteDetails = jobOrder.getCancelReasonUserName()
	              + ", "
	              + dt
	              + ", "
	              + date
	              + ":"
	              + jobOrder.getCancelReasonNote();	        	  
	          }
              addJobContracts.setCancelNote(cancelNote);
              addJobContracts.setCancelNoteDetails(cancelNoteDetails);
              // END : #127441
              
              if(jobOrder != null){
                if(jobOrder.getEtaTime() != null){

                  Calendar cal = new GregorianCalendar();
                  cal.setTime(jobOrder.getEtaTime());
                  int hour24 = cal.get(Calendar.HOUR_OF_DAY);
                  int min = cal.get(Calendar.MINUTE);
                  String etaTime = String.valueOf(hour24)
                      + ":" + String.valueOf(min);
                  Date jobEtaTime = new Date(jobOrder
                      .getEtaTime().getTime());
                  // addJobContracts.setEtaTime(DateUtil.
                  // getTimeFromDate
                  // (DateUtil.getConvertedDateTime
                  // (jobEtaTime,
                  // etaTime,Constants.TIME_ZONE,jobOrder
                  // .getEtaTimeTz())));
                  addJobContracts
                      .setEtaTime(DateUtil
                          .getTimewithsecondsFromDate(DateUtil
                              .getConvertedDateTime(
                                  jobEtaTime,
                                  etaTime,
                                  Constants.TIME_ZONE,
                                  jobOrder
                                      .getEtaTimeTz())));
                }
              }
              // Getting joblog records
              JobLog jobLog = jobContract.getJobLog();
              /*
               * JobLog jobLog = null; if((dispatchStatus != null)
               * && !"".equals(dispatchStatus.trim())) {
               * System.out.println(
               * "9 time before executing job log search query :"
               * +new Date()); jobLog =
               * getJobLogByJobContract(jobContract
               * ,dispatchStatus);System.out.println(
               * "10 time after executing job log search query :"
               * +new Date()); } else {System.out.println(
               * "11 time before executing job log search query :"
               * +new Date()); jobLog =
               * getJobLogByJobContracts(jobContract);
               * System.out.println
               * ("12 time after executing job log search query :"
               * +new Date()); }
               */
              // setting invoice values
              if(addJobContracts.getJobContract() != null
                  && addJobContracts.getJobContract()
                      .getJobContractInvoices() != null
                  && addJobContracts.getJobContract()
                      .getJobContractInvoices().size() != 0){
                Set jobcInvoiceSet = addJobContracts
                    .getJobContract()
                    .getJobContractInvoices();
                if(jobcInvoiceSet != null
                    && jobcInvoiceSet.size() != 0){
                  Iterator itrInvoice = jobcInvoiceSet
                      .iterator();
                  Iterator itrInvc = jobcInvoiceSet
                      .iterator();
                  JobContractInvoice jobContractInvoice;
                  boolean hasJoblog = true;
                  String latestInvoice = "";
                  JobLog invoiceRecJobLog = null;

                  List jobContractInv = null;
                  // getting latest invoice of jobcontract
                  jobContractInv = getLatestInvoiceByJobContractId(jobContract
                      .getId());
                  if(jobContractInv != null
                      && jobContractInv.size() > 0)
                    latestInvoice = ((JobContractInvoice) jobContractInv
                        .get(0)).getInvoice();
                  while (itrInvoice.hasNext()){
                    AddJobContract addJobCon = new AddJobContract();

                    // addJobCon.setAddJobContract(
                    // addJobContracts);
                    addJobCon
                        .setJobContract(addJobContracts
                            .getJobContract());
                    jobContractInvoice = (JobContractInvoice) itrInvoice
                        .next();
                    addJobCon
                        .setLatestJobContractInvoice(jobContractInvoice);
                    addJobCon.setAddNote(notes);
                    addJobCon
                        .setAddUpdatedNote(updateNotes);
                    //For ITrack#58291- START
                    addJobCon.setCustomerNote(customerNotes);
                    addJobCon.setCustomerNoteDetails(customerNoteDetails);
                    //For ITrack#58291- END
                    // START : #127441
                    addJobCon.setCancelNote(cancelNote);
                    addJobCon.setCancelNoteDetails(cancelNoteDetails);
                    // END : #127441
                    
                    addJobCon.setEtaTime(addJobContracts
                        .getEtaTime());

                    invoiceJobLog = true;
                    addJobCon = setUiJobSearchAndJobLogFields(
                        jobOrder, addJobCon, reqForm,
                        invoiceJobLog);
                    if(jobLog != null){
                      addJobCon = getJobLogDateandTime(
                          addJobCon, jobLog);
                      addJobCon.setJobLog(jobLog);
                    }
                    else{
                      if(hasJoblog){
                        invoiceRecJobLog = new JobLog();
                        addJobCon
                            .setJobLog(invoiceRecJobLog);
                        hasJoblog = false;

                      }
                      else{
                        addJobCon
                            .setJobLog(invoiceRecJobLog);
                      }
                    }
                    // setting latest invoice row enable
                    if(latestInvoice != null
                        && !latestInvoice.trim()
                            .equals("")
                        && latestInvoice
                            .trim()
                            .equals(
                                addJobCon
                                    .getLatestJobContractInvoice()
                                    .getInvoice())){
                      addJobCon
                          .setEtaDateTimeFlag(Constants.FALSE_VALUE);
                      addJobCon
                          .setInvoiceFlag(Constants.FALSE_VALUE);
                    }
                    else{
                      addJobCon
                          .setEtaDateTimeFlag(Constants.TRUE_VALUE);
                      addJobCon
                          .setInvoiceFlag(Constants.TRUE_VALUE);
                    }
                    updatedResult.add(addJobCon);
                  }
                  // invoiceMap.clear();
                  invoiceJobLog = false;
                }

              }
              else{
                JobContractInvoice jobContractInvoice = new JobContractInvoice();
                jobContractInvoice.setContactId(0);
                addJobContracts
                    .setLatestJobContractInvoice(jobContractInvoice);

                if(jobLog != null){
                  addJobContracts = getJobLogDateandTime(
                      addJobContracts, jobLog);
                  addJobContracts.setJobLog(jobLog);
                }
                else
                  addJobContracts.setJobLog(new JobLog());
                addJobContracts = setUiJobSearchAndJobLogFields(
                    jobOrder, addJobContracts, reqForm,
                    invoiceJobLog);
                updatedResult.add(addJobContracts);
              }
            }
          }
          else{
            if(jobOrder != null){
              if(jobOrder.getEtaTime() != null){

                Calendar cal = new GregorianCalendar();
                cal.setTime(jobOrder.getEtaTime());
                int hour24 = cal.get(Calendar.HOUR_OF_DAY);
                int min = cal.get(Calendar.MINUTE);
                String etaTime = String.valueOf(hour24) + ":"
                    + String.valueOf(min);
                Date jobEtaTime = new Date(jobOrder
                    .getEtaTime().getTime());
                // addJobContract.setEtaTime(DateUtil.
                // getTimeFromDate
                // (DateUtil.getConvertedDateTime(
                // jobEtaTime,etaTime
                // ,Constants.TIME_ZONE,jobOrder
                // .getEtaTimeTz())));
                addJobContract
                    .setEtaTime(DateUtil
                        .getTimewithsecondsFromDate(DateUtil
                            .getConvertedDateTime(
                                jobEtaTime,
                                etaTime,
                                Constants.TIME_ZONE,
                                jobOrder
                                    .getEtaTimeTz())));
              }
            }
            // START : #127441
            String cancelNote = "";
            String cancelNoteDetails ="";
            if(jobOrder != null && jobOrder.getCancelReasonNote()!=null && !jobOrder.getCancelReasonNote().trim().equals("")){ 
	        	  String dt = DateUtil.formateJobDescriptionDate(jobOrder.getUpdateTime());
	        	  String date = DateUtil.getTimeFromDate(jobOrder.getUpdateTime());
	              cancelNote = jobOrder.getCancelReasonNote();
	        	  cancelNoteDetails = jobOrder.getCancelReasonUserName()
	              + ", "
	              + dt
	              + ", "
	              + date
	              + ":"
	              + jobOrder.getCancelReasonNote();	        	  
	         }
            addJobContract.setCancelNote(cancelNote);
            addJobContract.setCancelNoteDetails(cancelNoteDetails);
            // END : #127441
            JobContractInvoice jobContractInvoice = new JobContractInvoice();
            JobContract jobContract = new JobContract();
            Customer customer = new Customer();
            Contact contact = new Contact();
            jobContract.setCustomer(customer);
            jobContract.setContact(contact);
            addJobContract.setJobContract(jobContract);
            jobContractInvoice.setContactId(0);
            addJobContract
                .setLatestJobContractInvoice(jobContractInvoice);
            addJobContract = setUiJobSearchAndJobLogFields(
                jobOrder, addJobContract, reqForm,
                invoiceJobLog);
            addJobContract
                .setEtaDateTimeFlag(Constants.FALSE_VALUE);
            addJobContract.setInvoiceFlag(Constants.TRUE_VALUE);
            updatedResult.add(addJobContract);

          }
        }
      }
    }
    else{
      // jobsearch process
      String currJobNumber[] = new String[jobOrderSize];
      boolean invoiceJobLog = false;
      int j = 0;
      int k = 0;
      ArrayList al = new ArrayList();
      for(int i = 0; i < jobOrderSize; i++){
        AddJobContract addJobContract = new AddJobContract();
        JobOrder jobOrder = (JobOrder) results.get(i);
        ServiceLocation serviceLocation = jobOrder.getServiceLocation();

        if(i > 0){
          currJobNumber[k] = jobOrder.getJobNumber();

          // finding jobnumber in arraylist
          al.add(currJobNumber[k]);
          int jobNumCount = Collections.frequency(al,
              currJobNumber[k]);

          if(jobNumCount == 1){
            Set st = jobOrder.getJobContracts();
            if(st != null && st.size() != 0){
              Iterator itr = st.iterator();
              while (itr.hasNext()){
                AddJobContract addJobContracts = new AddJobContract();
                addJobContracts
                    .setJobContract((JobContract) itr
                        .next());
                // setting invoice values
                if(addJobContracts.getJobContract() != null
                    && addJobContracts.getJobContract()
                        .getJobContractInvoices() != null
                    && addJobContracts.getJobContract()
                        .getJobContractInvoices()
                        .size() != 0){
                  Set jobcInvoiceSet = addJobContracts
                      .getJobContract()
                      .getJobContractInvoices();
                  if(jobcInvoiceSet != null
                      && jobcInvoiceSet.size() != 0){
                    Iterator itrInvoice = jobcInvoiceSet
                        .iterator();
                    JobContractInvoice jobContractInvoice;
                    while (itrInvoice.hasNext()){
                      AddJobContract addJobCon = new AddJobContract();

                      addJobCon
                          .setJobContract(addJobContracts
                              .getJobContract());
                      jobContractInvoice = (JobContractInvoice) itrInvoice
                          .next();
                      addJobCon
                          .setLatestJobContractInvoice(jobContractInvoice);
                      addJobCon = setUiJobSearchAndJobLogFields(
                          jobOrder, addJobCon,
                          reqForm, invoiceJobLog);
                      updatedResult.add(addJobCon);
                    }
                  }

                }
                else{
                  JobContractInvoice jobContractInvoice = new JobContractInvoice();
                  jobContractInvoice.setContactId(0);
                  addJobContract
                      .setLatestJobContractInvoice(jobContractInvoice);
                  addJobContracts = setUiJobSearchAndJobLogFields(
                      jobOrder, addJobContracts, reqForm,
                      invoiceJobLog);
                  updatedResult.add(addJobContracts);
                }
                // end
                // updatedResult.add(addJobContracts);
              }
            }
            else{
              JobContractInvoice jobContractInvoice = new JobContractInvoice();
              JobContract jobContract = new JobContract();
              Customer customer = new Customer();
              Contact contact = new Contact();

              jobContract.setCustomer(customer);
              jobContract.setContact(contact);
              addJobContract.setJobContract(jobContract);
              jobContractInvoice.setContactId(0);
              addJobContract
                  .setLatestJobContractInvoice(jobContractInvoice);
              addJobContract = setUiJobSearchAndJobLogFields(
                  jobOrder, addJobContract, reqForm,
                  invoiceJobLog);

              updatedResult.add(addJobContract);
            }
            k++;
          }

        }
        else{
          Set st = jobOrder.getJobContracts();
          // prevJobNumber[j] = jobOrder.getJobNumber();
          al.add(jobOrder.getJobNumber());
          if(st != null && st.size() != 0){
            Iterator itr = st.iterator();
            while (itr.hasNext()){
              AddJobContract addJobContracts = new AddJobContract();
              addJobContracts.setJobContract((JobContract) itr
                  .next());
              // setting invoice values
              if(addJobContracts.getJobContract() != null
                  && addJobContracts.getJobContract()
                      .getJobContractInvoices() != null
                  && addJobContracts.getJobContract()
                      .getJobContractInvoices().size() != 0){
                Set jobcInvoiceSet = addJobContracts
                    .getJobContract()
                    .getJobContractInvoices();
                if(jobcInvoiceSet != null
                    && jobcInvoiceSet.size() != 0){
                  Iterator itrInvoice = jobcInvoiceSet
                      .iterator();
                  JobContractInvoice jobContractInvoice;
                  while (itrInvoice.hasNext()){
                    AddJobContract addJobCon = new AddJobContract();
                    addJobCon
                        .setJobContract(addJobContracts
                            .getJobContract());
                    jobContractInvoice = (JobContractInvoice) itrInvoice
                        .next();
                    addJobCon
                        .setLatestJobContractInvoice(jobContractInvoice);
                    addJobCon = setUiJobSearchAndJobLogFields(
                        jobOrder, addJobCon, reqForm,
                        invoiceJobLog);
                    updatedResult.add(addJobCon);
                  }
                }

              }
              else{
                JobContractInvoice jobContractInvoice = new JobContractInvoice();
                jobContractInvoice.setContactId(0);
                addJobContract
                    .setLatestJobContractInvoice(jobContractInvoice);
                addJobContracts = setUiJobSearchAndJobLogFields(
                    jobOrder, addJobContracts, reqForm,
                    invoiceJobLog);
                updatedResult.add(addJobContracts);
              }
              // end
            }
          }
          else{
            // ContractCustContact contractCustContact=new
            // ContractCustContact();
            // ContractCust contractCust=new ContractCust();
            JobContractInvoice jobContractInvoice = new JobContractInvoice();
            JobContract jobContract = new JobContract();
            Customer customer = new Customer();
            Contact contact = new Contact();
            // contractCust.setCustomer(customer);
            // contractCustContact.setContractCust(contractCust);
            // contractCustContact.setContact(contact);
            //jobContract.setContractCustContact(contractCustContact
            // );
            jobContract.setCustomer(customer);
            jobContract.setContact(contact);
            jobContractInvoice.setContactId(0);
            addJobContract
                .setLatestJobContractInvoice(jobContractInvoice);
            addJobContract.setJobContract(jobContract);
            addJobContract = setUiJobSearchAndJobLogFields(
                jobOrder, addJobContract, reqForm,
                invoiceJobLog);
            updatedResult.add(addJobContract);
          }
        }
      }
    }
    List finalResults = new ArrayList();
    if(sortFlag != null
        && (!sortFlag.equals("") && !sortFlag.equals("job.jobNumber")
            && !sortFlag.equals("job.etaTimeTz")
            && !sortFlag.equals("job.operation")
            && !sortFlag.equals("job.jobDescription")
            && !sortFlag.equals("job.jobType")
            && !sortFlag.equals("serv.name")
            && !sortFlag.equals("serv.city")
            && !sortFlag.equals("job.vesselNames")
            && !sortFlag.equals("job.productNames")
            && !sortFlag.equals("ship.name")
            && !sortFlag.equals("ship.phone")
            && !sortFlag.equals("tco.name")
            && !sortFlag.equals("tco.phone")
            && !sortFlag.equals("job.createdByUserName")
            && !sortFlag.equals("job.updatedByUserName")
            && !sortFlag.equalsIgnoreCase("mh_col13")
            && !sortFlag.equalsIgnoreCase("mh_col19")
            && !sortFlag.equals("job.buName")
            && !sortFlag.equals("job.jobStatus")
            && !sortFlag.equals("job.nominationTimeTz") && !sortFlag
            .equals("job.updateTime")
            && !sortFlag.equals("job.cancelReasonNote")

        )){
      finalResults = SortUtil.sortByJobLogColumnHeader(updatedResult,
          sortFlag, jobSearch.getSortOrderFlag());
      jobSearch.setResults(finalResults);

    }
    else
      jobSearch.setResults(updatedResult);

    log.info("time at the end of Search job order processing :"
        + new Date());
  }

  private AddJobContract setUiJobSearchAndJobLogFields(JobOrder jobOrder,
      AddJobContract addJobContract, String reqForm, Boolean invoiceJobLog){

    // Setting UI Status Values
   /* String jobStatus = jobOrder.getJobStatus();
    if(Constants.OPEN_STATUS.equalsIgnoreCase(jobStatus.trim()))
      addJobContract.setJobStatus(Constants.OPEN);
    if(Constants.CLOSED_STATUS.equalsIgnoreCase(jobStatus.trim()))
      addJobContract.setJobStatus(Constants.CLOSED);
    if(Constants.REOPENED_STATUS.equalsIgnoreCase(jobStatus.trim()))
      addJobContract.setJobStatus(Constants.REOPENED);
    if(Constants.CANCELLED_STATUS.equalsIgnoreCase(jobStatus.trim()))
      addJobContract.setJobStatus(Constants.CANCELLED);

    // Setting UI Contract Status Values
    if(addJobContract.getJobContract().getContract() != null){
      if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_INPROCESS))
        addJobContract.setContractStatus(Constants.CONTRACT_INPROCESS);
      if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_APPROVED))
        addJobContract.setContractStatus(Constants.CONTRACT_APPROVED);
      if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_INACTIVE))
        addJobContract.setContractStatus(Constants.CONTRACT_INACTIVE);
      if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_NULL))
        addJobContract.setContractStatus(Constants.CONTRACT_NULL);
      if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_SUBMITTED))
        addJobContract.setContractStatus(Constants.CONTRACT_SUBMITTED);
      if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_REJECTED))
        addJobContract.setContractStatus(Constants.CONTRACT_REJECTED);

    }
    String jobType = jobOrder.getJobType();

    // Setting UI JobType Values
    if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE))
      addJobContract.setJobType(Constants.INSPJOBTYPE);
    if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE))
      addJobContract.setJobType(Constants.MARINEJOBTYPE);
    if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE))
      addJobContract.setJobType(Constants.ANLSJOBTYPE);
    if(jobType.equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE))
      addJobContract.setJobType(Constants.OUTSOURCINGJOBTYPE);*/
    
    /* For iTrack Issue#96935-START*/
    String jobStatus = jobOrder.getJobStatus();
    if(Constants.OPEN_STATUS.equalsIgnoreCase(jobStatus.trim())){
      addJobContract.setJobStatus(Constants.OPEN);
    } else if(Constants.CLOSED_STATUS.equalsIgnoreCase(jobStatus.trim())){
      addJobContract.setJobStatus(Constants.CLOSED);
    } else if(Constants.REOPENED_STATUS.equalsIgnoreCase(jobStatus.trim())){
      addJobContract.setJobStatus(Constants.REOPENED);
    } else {
      addJobContract.setJobStatus(Constants.CANCELLED);
    }
    // Setting UI Contract Status Values
    if(addJobContract.getJobContract().getContract() != null){
      if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_INPROCESS)){
        addJobContract.setContractStatus(Constants.CONTRACT_INPROCESS);
      } else if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_APPROVED)){
        addJobContract.setContractStatus(Constants.CONTRACT_APPROVED);
      } else if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_INACTIVE)){
        addJobContract.setContractStatus(Constants.CONTRACT_INACTIVE);
      } else if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_NULL)){
        addJobContract.setContractStatus(Constants.CONTRACT_NULL);
      } else if(addJobContract.getJobContract().getContract().getStatus()
          .equals(Constants.STATUS_SUBMITTED)){
        addJobContract.setContractStatus(Constants.CONTRACT_SUBMITTED);
      } else {
        addJobContract.setContractStatus(Constants.CONTRACT_REJECTED);
      }
    }
    String jobType = jobOrder.getJobType();

    // Setting UI JobType Values
    if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE)){
      addJobContract.setJobType(Constants.INSPJOBTYPE);
    } else if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE)){
      addJobContract.setJobType(Constants.MARINEJOBTYPE);
    } else if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE)){
      addJobContract.setJobType(Constants.ANLSJOBTYPE);
    } else {
      addJobContract.setJobType(Constants.OUTSOURCINGJOBTYPE);
    }
    /* For iTrack Issue#96935-START*/
    // Setting UI Operation Values

    // String
    // opDesc=getJobOperationDescByOperationCode(jobOrder.getOperation());
    String desc = "";
    Operation operation = null;
    operation = getOperationByOperationCode(jobOrder.getOperation());
    if(operation != null)
      addJobContract.setOperation(operation.getDescription());
    else
      addJobContract.setOperation(desc);

    // Setting URL Href values
    if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE)){
      addJobContract.setReqFrom(reqForm);
      if(Constants.CLOSED_STATUS.equalsIgnoreCase(jobStatus.trim())
          || Constants.CANCELLED_STATUS.equalsIgnoreCase(jobStatus
              .trim()))
        addJobContract.setHerfJobType(Constants.VIEW_INSP);
      else
        addJobContract.setHerfJobType(Constants.INSP);
    }
    if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE)){
      addJobContract.setHerfJobType(Constants.MAR);
      addJobContract.setReqFrom(reqForm);
      if(Constants.CLOSED_STATUS.equalsIgnoreCase(jobStatus.trim())
          || Constants.CANCELLED_STATUS.equalsIgnoreCase(jobStatus
              .trim()))
        addJobContract.setHerfJobType(Constants.VIEW_MAR);
      else
        addJobContract.setHerfJobType(Constants.MAR);
    }
    if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE)){
      addJobContract.setReqFrom(reqForm);
      if(Constants.CLOSED_STATUS.equalsIgnoreCase(jobStatus.trim())
          || Constants.CANCELLED_STATUS.equalsIgnoreCase(jobStatus
              .trim()))
        addJobContract.setHerfJobType(Constants.VIEW_FST);
      else
        addJobContract.setHerfJobType(Constants.FST);
    }
    if(jobType.equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE)){
      addJobContract.setReqFrom(reqForm);
      if(Constants.CLOSED_STATUS.equalsIgnoreCase(jobStatus.trim())
          || Constants.CANCELLED_STATUS.equalsIgnoreCase(jobStatus
              .trim()))
        addJobContract.setHerfJobType(Constants.VIEW_OUT);
      else
        addJobContract.setHerfJobType(Constants.OUT);
    }

    // addJobContract.setJobContract(jobContract);
    addJobContract.setJobOrder(jobOrder);
    if(invoiceJobLog == false){
      if(addJobContract.getJobLog() == null){
        addJobContract.setJobLog(new JobLog());
      }
    }
    /*
     * if(addJobContract.getJobContractNoteList() == null) {
     * addJobContract.setJobContractNoteList(new
     * ArrayList<JobContractNote>());
     * //addJobContract.setJobContractNote(new JobContractNote());
     *
     * }
     */
    return addJobContract;
  }

  private String getJobOperationDescByOperationCode(String OpearionCode){
    List operations = getDao().find(
        " from Operation o where o.operationCode ='" + OpearionCode
            + "'", null);
    String desc = "";
    Operation operation = null;
    if(operations.size() > 0){
      operation = (Operation) operations.get(0);
      desc = operation.getDescription();
    }
    return desc;
  }

  public List<JobContractNote> getJobContractNotesByJobContract(
      JobContract jobcontract){
    return getDao()
        .find(
            "from JobContractNote jn where jn.jobContract.id=? order by jn.id desc",
            new Object[] { jobcontract.getId() });
  }

  private JobLog getJobLogByJobContract(JobContract jobcontract,
      String dispatchStatus){
    List jobLog = new ArrayList();
    if(jobcontract.getJobLog() != null){
      jobLog = getDao().find(
          "from JobLog jl where jl.id=? and jl.dispatchStatus=?",
          new Object[] { jobcontract.getJobLog().getId(),
              dispatchStatus.toUpperCase() });
    }
    if(jobLog.size() > 0)
      return (JobLog) jobLog.get(0);
    else
      return null;
  }

  public JobLog getJobLogByJobContracts(JobContract jobcontract){
    List jobLog = new ArrayList();
    if(jobcontract.getJobLog() != null){
      jobLog = getDao().find("from JobLog jl where jl.id=?",
          new Object[] { jobcontract.getJobLog().getId() });
    }
    if(jobLog.size() > 0)
      return (JobLog) jobLog.get(0);
    else
      return null;
  }

  public void searchJobId(JobSearch jobSearch, String sortFlag){
    if(jobSearch == null)
      return;
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;
    String buName = jobSearch.getBusinessUnit().getValue();
    String strbuNameSearch = '%' + (buName) + '%';

    if((buName != null) && !"".equals(buName.trim())){
      sb.append(" where upper(job.businessUnit.name) like ?");
      params.add(strbuNameSearch.toUpperCase());
      hasWhere = true;
    }

    String branchName = jobSearch.getBranch().getValue();
    String strbrNameSeaerch = '%' + branchName + '%';

    if((branchName != null) && !"".equals(branchName.trim())){
      if(hasWhere)
        sb.append(" and ");
      else
        sb.append(" where ");
      sb.append(" job.branchName like ?");
      params.add(strbrNameSeaerch.toUpperCase());
    }
    String jobId = jobSearch.getFromJobId().getValue();
    String strJobIdSearch = '%' + (jobId) + '%';

    if((jobId != null) && !"".equals(jobId.trim())){
      if(hasWhere)
        sb.append(" and ");
      else
        sb.append(" where ");
      sb.append(" job.jobNumber like ?");
      params.add(strJobIdSearch.toUpperCase());
    }

    Pagination pagination = jobSearch.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals("")){
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select count(job.jobNumber) from JobOrder job "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        results = getDao()
            .find(
                "select distinct job from JobOrder job left join fetch job.branch branch left join fetch job.branch.businessUnit "
                    + sb.toString()
                    + "order by job.jobNumber",
                params.toArray(), pagination);

        pagination.calculatePages();
      }
      else{
        results = getDao()
            .find(
                "select distinct job from JobOrder job left join fetch job.branch branch left join fetch job.branch.businessUnit "
                    + sb.toString()
                    + "order by job.jobNumber",
                params.toArray());
      }

      jobSearch.setResults(results);
      jobSearch.setPagination(pagination);
    }
    else{
      String orderByValue = "order by job." + sortFlag;
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select count(job.jobNumber) from JobOrder job "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        pagination.calculatePages();
      }
      // START : #119240
      /*results = getDao()
          .find(
              "select distinct job from JobOrder job left join fetch job.branch branch left join fetch job.branch.businessUnit "
                  + sb.toString() + orderByValue,
              params.toArray(), pagination);*/
      if(jobSearch.getSortOrderFlag()!=null && !jobSearch.getSortOrderFlag().equals("")){
    	  results = getDao()
          .find("select distinct job from JobOrder job left join fetch job.branch branch left join fetch job.branch.businessUnit "
                  + sb.toString() + orderByValue + " " + jobSearch.getSortOrderFlag(),
              params.toArray(), pagination);
      }else{
    	  results = getDao()
          .find("select distinct job from JobOrder job left join fetch job.branch branch left join fetch job.branch.businessUnit "
                  + sb.toString() + orderByValue,
              params.toArray(), pagination);
      }
      // END : #119240
      jobSearch.setResults(results);
      jobSearch.setPagination(pagination);
      // jobSearch.setTotalResults(results);
    }
  }

  public JobOrder getNextOrPreviousJobNumber(JobSearch jobSearch,
      String jobNumber, String nextOrPrevFlag){

    String nextNum = "";
    String prevNum = "";
    if(jobSearch.getJobNumbers() != null
        && jobSearch.getJobNumbers().size() != 0){
      int currentJobNumIndex = 0;

      List jobSearchResults = jobSearch.getJobNumbers();
      String[] jobNum = new String[jobSearchResults.size()];

      for(int i = 0; i < jobSearchResults.size(); i++){
        jobNum[i] = (String) jobSearchResults.get(i);
      }
      for(int j = 0; j < jobNum.length; j++){
        if(jobNum[j].equals(jobNumber)){
          currentJobNumIndex = j;
          j = jobNum.length;
        }
      }
      if(nextOrPrevFlag != null && nextOrPrevFlag.trim().equals("next")){
        if(jobNum.length > 0){
          if(currentJobNumIndex != jobNum.length){
            for(int j = currentJobNumIndex; j < jobNum.length; j++){
              nextNum = jobNum[j + 1];
              if(jobNumber.equals(jobNum[j + 1])){
                nextNum = jobNum[j + 1];
              }
              else
                break;
            }
          }
        }
      }
      else{
        if(jobNum.length > 0){
          if(currentJobNumIndex != jobNum.length){
            for(int j = currentJobNumIndex; j >= 0; j--){
              prevNum = jobNum[j - 1];
              if(jobNumber.equals(jobNum[j - 1]))
                prevNum = jobNum[j - 1];
              else
                break;
            }
          }
        }
      }
    }
    JobOrder jobOrder = null;
    if(!nextNum.equals(""))
      jobOrder = getJobOrderByJobNumber(nextNum.trim());
    if(!prevNum.equals(""))
      jobOrder = getJobOrderByJobNumber(prevNum.trim());

    /*
     * if(jobOrder == null) { throw new
     * RuntimeException(" Next or Previous JobNumber does not exist  : "); }
     */
    return jobOrder;

  }

  public String getUrlHrefJobTypeByJobOrder(JobOrder jobOrder){
    String url = "";
    String jobType = jobOrder.getJobType();
    if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)
        || jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS)){
      if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE)){
        url = Constants.INSP;
      }
      if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE)){
        url = Constants.MAR;
      }
      if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE)){
        url = Constants.FST;
      }
      if(jobType.equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE)){
        url = Constants.OUT;
      }
    }
    else{
      if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE)){
        url = Constants.VIEW_INSP;
      }
      if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE)){
        url = Constants.VIEW_MAR;
      }
      if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE)){
        url = Constants.VIEW_FST;
      }
      if(jobType.equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE)){
        url = Constants.VIEW_OUT;
      }
    }

    return url;
  }

  // START : #119240
  public String getUrlByCurrPageIdentifier(JobOrder jobOrder, String currPageIdentifier)
  {
		String url = "";
	    String jobType = jobOrder.getJobType();
	   
	    if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)
	        || jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS)){
	    	
	    	if(new Integer(currPageIdentifier).intValue() <= jobOrder.getPageNumber().intValue())
	    	{
		    	  // get url same as that for curr job
		    	  url = JobUtil.getUrl(currPageIdentifier, jobType, jobOrder.getJobStatus());
	    		  
	    	}else if(new Integer(currPageIdentifier).intValue() > jobOrder.getPageNumber().intValue())
	    	{
		    	  // get the url of last active page ie. jobOrder.getPageNumber()
		    	  url = JobUtil.getUrl(jobOrder.getPageNumber().toString(), jobType, jobOrder.getJobStatus());
	    	 }	 
	      
	    }
	    else{
	      
	    	if(new Integer(currPageIdentifier).intValue() <= jobOrder.getPageNumber().intValue())
	    	{
	    		url = JobUtil.getUrl(currPageIdentifier, jobType, jobOrder.getJobStatus());
	    	  
	    	}else if(new Integer(currPageIdentifier).intValue() > jobOrder.getPageNumber().intValue())
	    	{
	    		url = JobUtil.getUrl(jobOrder.getPageNumber().toString(), jobType, jobOrder.getJobStatus());	    		
	    	}	    
	    }	
	    return url;
  }
  // END : #119240
 
  
  public List getJobIdById(String jobid){
    List params = new ArrayList();
    params.add(jobid);
    String strJobidSearch = jobid + '%';
    List jobs = getDao()
        .find(
            "from JobOrder job where job.jobNumber like ? order by job.jobNumber",
            new Object[] { strJobidSearch.toUpperCase() });
    if(jobs.size() > 0){
      return jobs;
    }
    else{
      return null;
    }
  }

  public void updateJobContractNote(JobContractNote jobContractNote){
    getDao().update(jobContractNote);
  }

  public JobLog addJobLog(JobLog jobLog){
    return getDao().save(jobLog);
  }

  public String getUiJobOperations(String operation){
    // Setting UI Operation Values
    String opern = "";
    if(Constants.ADDT.equals(operation.trim()))
      opern = Constants.Additivation;
    if(Constants.AONY.equals(operation.trim()))
      opern = Constants.AnalysisOnly;
    if(Constants.ATMO.equals(operation.trim()))
      opern = Constants.AtmosphericMontr;
    if(Constants.BUNK.equals(operation.trim()))
      opern = Constants.Bunkering;
    if(Constants.CALB.equals(operation.trim()))
      opern = Constants.Calibration;
    if(Constants.CONS.equals(operation.trim()))
      opern = Constants.Consulting;
    if(Constants.MMRR.equals(operation.trim()))
      opern = Constants.ControlOnMARPOLregulations;
    if(Constants.CUCL.equals(operation.trim()))
      opern = Constants.Customsclearance;
    if(Constants.DEOP.equals(operation.trim()))
      opern = Constants.Depotoperations;
    if(Constants.DOCMC.equals(operation.trim()))
      opern = Constants.Documentcontrol;
    if(Constants.DKSP.equals(operation.trim()))
      opern = Constants.DockSupervision;
    if(Constants.DRFT.equals(operation.trim()))
      opern = Constants.DraftSurvey;
    if(Constants.EXPD.equals(operation.trim()))
      opern = Constants.Expediting;
    if(Constants.GONL.equals(operation.trim()))
      opern = Constants.GaugingOnly;
    if(Constants.HBAN.equals(operation.trim()))
      opern = Constants.HandblendAnalysis;
    if(Constants.HLDC.equals(operation.trim()))
      opern = Constants.HoldConditionSurvey;
    if(Constants.HLDI.equals(operation.trim()))
      opern = Constants.HoldInspection;
    if(Constants.INV.equals(operation.trim()))
      opern = Constants.Inventory;
    if(Constants.JESE.equals(operation.trim()))
      opern = Constants.Jettyservices;
    if(Constants.LIGH.equals(operation.trim()))
      opern = Constants.Lightering;
    if(Constants.LNDP.equals(operation.trim()))
      opern = Constants.LineDisplacement;
    if(Constants.LOAD.equals(operation.trim()))
      opern = Constants.Loading;
    if(Constants.PIPE.equals(operation.trim()))
      opern = Constants.Pipeline;
    if(Constants.SAFE.equals(operation.trim()))
      opern = Constants.Safety;

    if(Constants.SFCH.equals(operation.trim()))
      opern = Constants.Safetychecklist;
    if(Constants.SAMP.equals(operation.trim()))
      opern = Constants.SampleAnalysis;
    if(Constants.SS.equals(operation.trim()))
      opern = Constants.SampleShipping;
    if(Constants.SPUD.equals(operation.trim()))
      opern = Constants.SamplePickUpDelivery;
    if(Constants.SASH.equals(operation.trim()))
      opern = Constants.Samplestoragehandling;
    if(Constants.SONY.equals(operation.trim()))
      opern = Constants.SamplingOnly;
    if(Constants.SUBM.equals(operation.trim()))
      opern = Constants.SubmittedSample;
    if(Constants.TTSL.equals(operation.trim()))
      opern = Constants.TankTruckSealing;
    if(Constants.TEAS.equals(operation.trim()))
      opern = Constants.Technicalassistance;
    if(Constants.TSTK.equals(operation.trim()))
      opern = Constants.TestKit;
    if(Constants.TRAN.equals(operation.trim()))
      opern = Constants.Transfer;
    if(Constants.VIS.equals(operation.trim()))
      opern = Constants.VisualInspection;
    return opern;
  }

  public String getUiJobTypes(String jobType){
    String jbType = "";
    if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE))
      jbType = Constants.INSPJOBTYPE;
    if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE))
      jbType = Constants.MARINEJOBTYPE;
    if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE))
      jbType = Constants.ANLSJOBTYPE;
    if(jobType.equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE))
      jbType = Constants.OUTSOURCINGJOBTYPE;
    return jbType;
  }

  public String getUiJobStatus(String jobStatus){
    String jbStatus = "";
    if(Constants.OPEN_STATUS.equalsIgnoreCase(jobStatus.trim()))
      jbStatus = Constants.OPEN;
    if(Constants.CLOSED_STATUS.equalsIgnoreCase(jobStatus.trim()))
      jbStatus = Constants.CLOSED;
    if(Constants.REOPENED_STATUS.equalsIgnoreCase(jobStatus.trim()))
      jbStatus = Constants.REOPENED;
    if(Constants.CANCELLED_STATUS.equalsIgnoreCase(jobStatus.trim()))
      jbStatus = Constants.CANCELLED;
    return jbStatus;
  }

  public String getUiContractStatus(String contractStatus){
    if(Constants.STATUS_APPROVED.equalsIgnoreCase(contractStatus.trim()))
      contractStatus = Constants.CONTRACT_APPROVED;
    if(Constants.STATUS_INACTIVE.equalsIgnoreCase(contractStatus.trim()))
      contractStatus = Constants.CONTRACT_INACTIVE;
    if(Constants.STATUS_INPROCESS.equalsIgnoreCase(contractStatus.trim()))
      contractStatus = Constants.CONTRACT_INPROCESS;
    if(Constants.STATUS_NULL.equalsIgnoreCase(contractStatus.trim()))
      contractStatus = Constants.CONTRACT_NULL;
    if(Constants.STATUS_SUBMITTED.equalsIgnoreCase(contractStatus.trim()))
      contractStatus = Constants.CONTRACT_SUBMITTED;

    if(Constants.STATUS_REJECTED.equalsIgnoreCase(contractStatus.trim()))
      contractStatus = Constants.CONTRACT_REJECTED;
    return contractStatus;
  }

  /*
   * public JobType getJobOperationByJobType(String jType) {
   *
   * List operations=getDao().find(
   * " from JobType j left join fetch j.operations op where j.jobTypeCode ='"
   * +jType+"' order by  op.description asc ",null);
   * System.out.println("size of the operations in jobservice impl is"
   * +operations.size()); if(operations.size()>0) return
   * (JobType)operations.get(0); return null; }
   */

  public Operation getOperationByOperationCode(String operationCode){
    List operations = getDao().find(
        " from Operation op left join fetch op.events where op.operationCode = '"
            + operationCode + "' ", null);
    if(operations.size() > 0)
      return (Operation) operations.get(0);
    return null;
  }

  public Event getEventByEventCode(String eventCode){
    List events = getDao()
        .find(
            " from Event e  where e.eventCode = '" + eventCode
                + "' ", null);
    if(events != null && events.size() > 0)
      return (Event) events.get(0);
    return null;
  }

  public JobOrder getJobOrderByJobNumberWithPrebills(String jobNumber){
    List jobOrder = getDao()
        .find(
            " from JobOrder job left join fetch job.branch branch left join fetch branch.businessUnit "
                + "left join fetch job.towingCompany left join fetch job.shippingAgent left join fetch "
                + "job.serviceLocation left join fetch job.jobVessels jv left join fetch jv.jobProds left join fetch job.jobContracts jc left join fetch jc.prebills jcp "
                + "left join fetch jcp.prebillSplits left join fetch jcp.taxArticle where job.jobNumber = ? ",
            new Object[] { jobNumber });
    if(jobOrder.size() > 0)
      return (JobOrder) jobOrder.get(0);
    return null;
  }

  public JobContract getJobContractWithInvoiceDetails(long jobContractId){
    List jobContract = getDao().find(
        " from JobContract jc left join fetch  jc.jobContractInvoices jc1 "
            + " where jc.id = ?  ", new Object[] { jobContractId });
    if(jobContract.size() > 0)
      return (JobContract) jobContract.get(0);
    return null;
  }

  public void saveJobContractInvoice(JobContractInvoice jobContractInvoice){
    getDao().save(jobContractInvoice);
  }

  public List getJobOrdersByJobNumberWithJobContracts(String jobNumber){
    return getDao()
        .find(
            "select job from JobOrder job "
                + " left join fetch job.branch branch left join fetch branch.businessUnit "
                + " left join fetch job.towingCompany "
                + " left join fetch job.shippingAgent "
                + " left join fetch job.serviceLocation "
                + " left join fetch job.jobContracts jc "
                + " where job.jobNumber = ? ",
            new Object[] { jobNumber.toUpperCase() });

    /*
     * if (jobs.size() > 0) return (JobOrder) jobs.get(0);
     *
     * return null;
     */
  }

  public byte[] getJobOrderPdfData(String jobNumber, String dirName,
      String jasperDirName){
    byte[] result = null;
    JasperPrint jasperPrint = null;

    Map paramMap = new HashMap();
    paramMap.put("jobNumber", jobNumber);
    paramMap.put("dirName", jasperDirName);

    javax.sql.DataSource dataSource = (javax.sql.DataSource) ServiceLocator
        .getInstance().getBean("entityDataSource");
    java.sql.Connection connection = null;

    try{
      java.io.InputStream is = new FileInputStream(jasperDirName
          + "job_order.jasper");
      connection = dataSource.getConnection();

      jasperPrint = JasperFillReport.fillReport(is, paramMap, connection);
      if(jasperPrint == null){
        if(connection != null){
          try{
            connection.close();
          }
          catch (Exception e){
          }
        }
        if(is != null){
          is.close();
        }
        throw new ServiceException("INVOICE_GENERATION_ERROR",
            new Object[] { "Fail to Fill Report" }, null);
      }
      /*
       * jasperPrint = JasperFillManager.fillReport( is, paramMap,
       * connection );
       */

      result = JasperExportManager.exportReportToPdf(jasperPrint);
    }
    catch (Throwable e){
      e.printStackTrace();
      throw new ServiceException(e.getMessage(), new Object[] { e
          .getMessage() }, e);
    }
    finally{
      if(connection != null){
        try{
          connection.close();
        }
        catch (Exception e){
        }
      }
    }

    return result;
  }

  public void createJobOrder(JobOrder jobOrder, String toStateName){
    jobOrder.setJobStatus(toStateName);
    updateJobOrder(jobOrder);
  }

  public void cancelJobOrder(JobOrder jobOrder, String toStateName){
    jobOrder.setJobStatus(toStateName);
    updateJobOrder(jobOrder);
  }

  public void closeJobOrder(JobOrder jobOrder, String toStateName){
    jobOrder.setJobStatus(toStateName);
    updateJobOrder(jobOrder);
  }

  public void reopenJobOrder(JobOrder jobOrder, String toStateName){
    jobOrder.setJobStatus(toStateName);
    updateJobOrder(jobOrder);
  }

  public void updateJobOrderStatus(String jobNumber, String toStateName){
    if((jobNumber == null) || (toStateName == null))
      return;

    // Setting job reopen date

    JobOrder jobOrders = getJobOrderByJobNumber(jobNumber);
    if(jobOrders != null
        && jobOrders.getJobStatus() != null
        && jobOrders.getJobStatus().trim().equals(
            Constants.REOPENED_STATUS)
        && jobOrders.getReOpenDate() != null){
      getDao().bulkUpdate(
          "update JobOrder set jobStatus = ? where jobNumber = ?",
          new Object[] { toStateName, jobNumber });
    }
    else if(toStateName != null
        && toStateName.trim().equals(Constants.REOPENED_STATUS)){
      Date reOpenDate = new Date();
      getDao()
          .bulkUpdate(
              "update JobOrder set jobStatus = ?,reOpenDate = ? where jobNumber = ?",
              new Object[] { toStateName, reOpenDate, jobNumber });
    }
    else{
      getDao().bulkUpdate(
          "update JobOrder set jobStatus = ? where jobNumber = ?",
          new Object[] { toStateName, jobNumber });
    }

    // end

    /*
     * getDao().bulkUpdate(
     * "update JobOrder set jobStatus = ? where jobNumber = ?", new Object[]
     * { toStateName, jobNumber } );
     */
  }

  public List<JobContractAttach> getJobContractAttachByJobContractId(
      long jobContractId){
    return getDao()
        .find(
            "from JobContractAttach j left join fetch j.jobContractAttachType where j.jobContract.id = ? ",
            new Object[] { Long.valueOf(jobContractId) });
  }

  public void deleteJobContractAttach(long id, String path){
    List<JobContractAttach> list = getDao().find(
        "from JobContractAttach j where j.id = ? ",
        new Object[] { Long.valueOf(id) });
    if(list != null && !list.isEmpty()){
      JobContractAttach jobContractAttach = list.get(0);
      String systemFileName = jobContractAttach.getSystemFileName();
      if(path != null && path.trim().length() > 0)
        systemFileName = path.concat(systemFileName);
      File file = new File(systemFileName);
      file.delete();
      getDao().remove(com.intertek.entity.JobContractAttach.class, id);
    }
  }

  public void deleteJobLogId(long id){
    getDao().remove(com.intertek.entity.JobLog.class, id);
  }

  public void updateJobContract(JobContract jobContract){
    getDao().update(jobContract);
  }

  public List<JobContractNote> getJobContractNoteByJobContractId(
      long jobContractId){
    return getDao()
        .find(
            "from JobContractNote n where n.jobContract.id = ? and n.noteCategory = ?",
            new Object[] { Long.valueOf(jobContractId),
                Constants.CONTRACT_STR });
  }

  public void deleteJobContractNote(long id){
    getDao().remove(com.intertek.entity.JobContractNote.class, id);
  }

  public JobContract getJobContractById(long id){
    List<JobContract> list = getDao().find(
        "from JobContract j where j.id=? ",
        new Object[] { Long.valueOf(id) });
    if(list != null && !list.isEmpty())
      return list.get(0);
    return null;
  }

  public JobContract getJobContractByIdWithDetails(long id){
    List<JobContract> list = getDao()
        .find(
            "from JobContract j left join fetch j.jobOrder job left join fetch j.jobLog left join fetch j.contact left join fetch j.billingContact left join fetch j.customer left join fetch job.branch left join fetch job.serviceLocation where j.id=? ",
            new Object[] { Long.valueOf(id) });
    if(list != null && !list.isEmpty())
      return list.get(0);
    else
      throw new ServiceException("search.job.contract.error",
          new Object[] { id });
    // return null;
  }

  public JobContractNote mergeJobContractNote(JobContractNote jobContractNote){
    return getDao().merge(jobContractNote);
  }

  public List getSchedulersByJobContractIds(String[] jobContractList){
    if(jobContractList == null || jobContractList.length == 0)
      throw new ServiceException("job.mail.jobcontracts.error",
          new Object[] { jobContractList });
    // return null;

    StringBuffer queryStr = new StringBuffer();
    queryStr.append("(");
    for(int i = 0; i < jobContractList.length; i++){
      String jobContractId = jobContractList[i];
      queryStr.append(jobContractId);
      if(i < jobContractList.length - 1)
        queryStr.append(",");
    }
    queryStr.append(")");

    List schedulers = getDao().find(
        "select distinct contact from JobContract j where j.id in "
            + queryStr.toString(), null);
    if(schedulers != null)
      return schedulers;
    else
      throw new ServiceException("job.mail.schedulers.error",
          new Object[] { queryStr.toString() });
  }

  public List getJobOrdersByJobContractsAndScheduler(
      String[] jobContractList, long id){
    if(jobContractList == null || jobContractList.length == 0)
      return null;

    StringBuffer queryStr = new StringBuffer();
    queryStr.append("(");
    for(int i = 0; i < jobContractList.length; i++){
      String jobContractId = jobContractList[i];
      queryStr.append(jobContractId);
      if(i < jobContractList.length - 1)
        queryStr.append(",");
    }
    queryStr.append(")");

    List jobOrders = getDao()
        .find(
            "select distinct job from JobOrder job left join fetch job.jobContracts jc left join fetch jc.jobLog left join fetch jc.contact left join fetch jc.billingContact left join fetch jc.customer left join fetch job.branch left join fetch job.serviceLocation where jc.id in "
                + queryStr.toString()
                + " and jc.contactId = "
                + id, null);
    return jobOrders;
  }

  public void searchTemplate(TemplateSearch search, String sortFlag){
    if(search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();
    boolean hasWhere = false;

    String tempName = search.getTemplateName().getValue();
    if((tempName != null) && !"".equals(tempName.trim())){
      tempName = tempName.toUpperCase();
      tempName = '%' + tempName + '%';
      sb.append(" where upper(j.tempName) like ?");
      params.add(tempName);
      hasWhere = true;
    }

    String branchName = search.getBranchName().getValue();
    if((branchName != null) && !"".equals(branchName.trim())){
      branchName = '%' + branchName + '%';
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      sb.append("  j.branchName like ?");
      params.add(branchName);
      hasWhere = true;
    }

    String firstName = search.getFirstName().getValue();
    if((firstName != null) && !"".equals(firstName.trim())){
      firstName = '%' + firstName + '%';
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      sb.append("  j.createdBy.firstName like ?");
      params.add(firstName);
      hasWhere = true;
    }

    String lastName = search.getLastName().getValue();
    if((lastName != null) && !"".equals(lastName.trim())){
      lastName = '%' + lastName + '%';
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      sb.append("  j.createdBy.lastName like ?");
      params.add(lastName);
      hasWhere = true;
    }

    if(hasWhere)
      sb.append(" and ");
    else{
      hasWhere = true;
      sb.append(" where ");
    }
    sb.append("  j.isTemplate = true");

    Pagination pagination = search.getPagination();
    List results = null;

    if(params != null){
      for(int i = 0; i < params.size(); i++){
        System.out.println("param :" + i + " : "
            + params.get(i).toString());
      }
    }

    if(sortFlag != null && sortFlag.equals("")){
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  j from JobOrder j left join fetch j.createdBy  "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            pagination.setTotalRecord(counts.size());
          }
        }

        results = getDao().find(
            "select  j from JobOrder j left join fetch j.createdBy  "
                + sb.toString() + " order by j.tempName",
            params.toArray(), pagination);

        pagination.calculatePages();

      }
      else{
        results = getDao().find(
            "select  j from JobOrder j left join fetch j.createdBy  "
                + sb.toString() + " order by j.tempName",
            params.toArray());
      }

      search.setResults(results);
      search.setPagination(pagination);
    }
    else{
      String orderByValue = " order by j." + sortFlag;

      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  j from JobOrder j left join fetch j.createdBy  "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            pagination.setTotalRecord(counts.size());
          }
        }
        pagination.calculatePages();

      }
      // START : #119240      
      /*results = getDao().find(
          "select  j from JobOrder j left join fetch j.createdBy  "
              + sb.toString() + orderByValue, params.toArray(),
          pagination);*/
      if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	  results = getDao().find(
              "select  j from JobOrder j left join fetch j.createdBy  "
                  + sb.toString() + orderByValue +" " + search.getSortOrderFlag(), params.toArray(),
              pagination);
      }else{
 		  results = getDao().find(
              "select  j from JobOrder j left join fetch j.createdBy  "
	                  + sb.toString() + orderByValue, params.toArray(),
	              pagination);
   	  }
      // END : #119240
      // search.setTotalResults(results);
      search.setResults(results);
      search.setPagination(pagination);
    }

  }

  public List getMonthlyJobIdById(String jobid, String branchName,
      String contractCode, String parentCustCode){
    String strJobidSearch = jobid + '%';
    boolean montlyflagval = true;
    List jobs = getDao()
        .find(
            "from JobContract j left join fetch j.jobOrder  where j.jobNumber like ? and j.monthlyFlag=? and j.jobOrder.branchName=? and j.contractCode=?  and j.customer.parentCustCode = ? order by j.jobNumber ",
            new Object[] { strJobidSearch.toUpperCase(),
                montlyflagval, branchName, contractCode,
                parentCustCode });
    if(jobs.size() > 0){
      return jobs;
    }
    else{
      return null;
    }
  }

  public void searchMonthlyJob(JobSearch search, String sortFlag){

    if(search == null)
      return;
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    String buName = search.getBusinessUnit().getValue();
    String branchName = search.getBranch().getValue();
    String jobId = search.getMonthlyJobId().getValue();
    String contractCode = search.getContractId().getValue();
    String pCode = search.getPCustCode();
    boolean montlyflagval = true;

    if((jobId != null) && !"".equals(jobId.trim())){
      String strJobIdSearch = jobId.toUpperCase() + '%';
      sb
          .append(" where upper(j.jobNumber) like ? and j.contractCode=?  and j.monthlyFlag = ? and j.jobOrder.buName=? and j.jobOrder.branchName=? and j.customer.parentCustCode = ? ");
      params.add(strJobIdSearch);
      params.add(contractCode);
      params.add(montlyflagval);
      params.add(buName);
      params.add(branchName);
      params.add(pCode);
      hasWhere = true;
    }

    else{
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      sb
          .append("j.contractCode=? and j.monthlyFlag = ? and j.jobOrder.buName=? and j.jobOrder.branchName=? and j.customer.parentCustCode = ? ");
      params.add(contractCode);
      params.add(montlyflagval);
      params.add(buName);
      params.add(branchName);
      params.add(pCode);
      hasWhere = true;
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals("")){
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(j.jobNumber) from JobContract j "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        results = getDao()
            .find(
                "select j from JobContract j left join fetch j.jobOrder left join fetch j.customer left join fetch j.contact "
                    + sb.toString()
                    + " order by j.jobNumber",
                params.toArray(), pagination);

        pagination.calculatePages();
      }
      else{
        results = getDao()
            .find(
                "select j from JobContract j left join fetch j.jobOrder left join fetch j.customer left join fetch j.contact "
                    + sb.toString()
                    + " order by j.jobNumber",
                params.toArray());
      }

      search.setResults(results);
      search.setPagination(pagination);
    }
    else{
      String orderByValue = "";
      if(sortFlag != null && sortFlag.equals("sname")){
        orderByValue = " order by j.contact.firstName||j.contact.lastName";
      }
      else{
        orderByValue = " order by j." + sortFlag;

      }

      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(j.jobNumber) from JobContract j "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        // START : #119240
        /*results = getDao()
            .find(
                "select j from JobContract j left join fetch j.jobOrder left join fetch j.customer left join fetch j.contact "
                    + sb.toString() + orderByValue,
                params.toArray(), pagination);*/
        if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
        	results = getDao()
            .find("select j from JobContract j left join fetch j.jobOrder left join fetch j.customer left join fetch j.contact "
                    + sb.toString() + orderByValue + " " + search.getSortOrderFlag(),
                params.toArray(), pagination);
        }else{
        	results = getDao()
            .find("select j from JobContract j left join fetch j.jobOrder left join fetch j.customer left join fetch j.contact "
                    + sb.toString() + orderByValue,
                params.toArray(), pagination);
        }
        // END : #119240 
        pagination.calculatePages();
      }
      search.setResults(results);
    }

    /*
     * String desc=""; for(int i=0;i<results.size();i++) { JobContract
     * jobContract=(JobContract)results.get(i); Operation
     * operation=getOperationByOperationCode
     * (jobContract.getJobOrder().getOperation()); if(operation!= null)
     * {jobContract.getJobOrder().setOperation(operation.getDescription());}
     * else {jobContract.getJobOrder().setOperation(desc);}
     * results.add(jobContract); } search.setResults(results);
     */
  }

  public List checkMonthlyJob(String jobNumber){
    List contacts = getDao().find(
        "from JobContract j where j.monthlyJobNumber=?",
        new Object[] { jobNumber });
    if(contacts.size() > 0){
      return contacts;
    }
    else{
      return null;
    }
  }

  public List getJobsByUserName(String userName){
    List jobs = getDao()
        .find(
            "from JobOrder job where job.receivedByUserName=? or job.adminContactUserName = ? or job.createdByUserName=? or job.updatedByUserName=?",
            new Object[] { userName, userName, userName, userName });
    if(jobs.size() > 0){
      return jobs;
    }
    else{
      return null;
    }
  }

  public List getJobContractNotesByUserName(String userName){
    List notes = getDao().find(
        "from JobContractNote note where note.addedByUserName=? ",
        new Object[] { userName });
    if(notes.size() > 0){
      return notes;
    }
    else{
      return null;
    }
  }

  public int getPeriods(String buName, Date jobFinishDate){

    List counts = getDao()
        .find(
            "select count(*) from OpenPeriod op where op.buName = ? and op.openFromDate <=? and op.openToDate >=?",
            new Object[] { buName, jobFinishDate, jobFinishDate });
    int periodCount = 0;
    if(counts.size() > 0){
      Number count = (Number) counts.get(0);
      periodCount = count.intValue();
    }
    return periodCount;
  }

  /**
   * Name :findOpenPeriodByBuName Date :Aug 15, 2008 Purpose :It return the
   * open period for that bu. Each bu needs to have only 1 record in that
   * table all the time
   *
   * @param buName
   * @return
   */
  public OpenPeriod findOpenPeriodByBuName(String buName){
    List list = getDao().find("from OpenPeriod op where op.buName = ? ",
        new Object[] { buName });
    if(!list.isEmpty())
      return (OpenPeriod) list.get(0);
    return null;
  }

  public AddJobContract getJobLogDateandTime(AddJobContract addJobContracts,
      JobLog jobLog){
    addJobContracts.setInspectorNotifyTime(DateUtil.getTimeFromDate(jobLog
        .getInspectorNotifyTime()));
    addJobContracts.setInspectorArriveTime(DateUtil.getTimeFromDate(jobLog
        .getInspectorArriveTime()));
    addJobContracts.setArriveTime(DateUtil.getTimeFromDate(jobLog
        .getArriveTime()));
    addJobContracts.setDockTime(DateUtil.getTimeFromDate(jobLog
        .getDockTime()));
    addJobContracts.setHoseOnTime(DateUtil.getTimeFromDate(jobLog
        .getHoseOnTime()));
    addJobContracts.setEstStartTime(DateUtil.getTimeFromDate(jobLog
        .getEstStartTime()));
    addJobContracts.setCommenceTime(DateUtil.getTimeFromDate(jobLog
        .getCommenceTime()));
    addJobContracts.setDelayTime(DateUtil.getTimeFromDate(jobLog
        .getDelayTime()));
    addJobContracts.setDelayEndTime(DateUtil.getTimeFromDate(jobLog
        .getDelayEndTime()));
    addJobContracts.setEstCompleteTime(DateUtil.getTimeFromDate(jobLog
        .getEstCompleteTime()));
    addJobContracts.setCompleteTime(DateUtil.getTimeFromDate(jobLog
        .getCompleteTime()));
    // addJobContracts.setEstCompleteTime(DateUtil.getTimeFromDate(jobLog.
    // getHoseOffTime()));
    addJobContracts.setHoseOffTime(DateUtil.getTimeFromDate(jobLog
        .getHoseOffTime()));
    addJobContracts.setReleaseTime(DateUtil.getTimeFromDate(jobLog
        .getReleaseTime()));
    addJobContracts.setSampleReceiveTime(DateUtil.getTimeFromDate(jobLog
        .getSampleReceiveTime()));
    addJobContracts.setPrelimReportTime(DateUtil.getTimeFromDate(jobLog
        .getPrelimReportTime()));
    addJobContracts.setSampleShipTime(DateUtil.getTimeFromDate(jobLog
        .getSampleShipTime()));
    addJobContracts.setFinalReportTime(DateUtil.getTimeFromDate(jobLog
        .getFinalReportTime()));
    addJobContracts.setDistributeTime(DateUtil.getTimeFromDate(jobLog
        .getDistributeTime()));

    return addJobContracts;
  }

  public List getTabNames(){
    List params = new ArrayList();
    List Tabs = getDao()
        .find(
            "select distinct l.logConfigDetailId.tabName from LogConfigDetail  l ",
            params.toArray());
    if(Tabs.size() > 0)
      return Tabs;
    else
      return null;
  }

  public List getCloumnsByTabName(String tabName, long confListId){
    List params = new ArrayList();
    List columns = getDao()
        .find(
            "select l from LogConfigDetail  l where  upper(l.logConfigDetailId.tabName)= ? and l.logConfigDetailId.confListId=? order by l.displayOrder ",
            new Object[] { tabName.toUpperCase(),
                Long.valueOf(confListId) });
    if(columns.size() > 0)
      return columns;
    else
      return null;
  }

  public List getColsandTabsByListId(long confListId){
    List list = null;
    list = getDao()
        .find(
            "from LogConfigDetail  l where l.logConfigDetailId.confListId=? ",
            new Object[] { Long.valueOf(confListId) });
    if(list == null || list.size() <= 0){
      System.out.println("getColsandTabsByListId");
      throw new ServiceException("jobs.Log.configurecolumns.list.error");
    }
    return list;
  }

  public LogConfigList saveConfig(LogConfigList logConfigList){
    return getDao().save(logConfigList);
  }

  public LogConfigList addConfig(LogConfigList logConfigList){
    return getDao().save(logConfigList);
  }

  public void addlogConfigDetail(LogConfigDetail logConfigDetail){
    getDao().save(logConfigDetail);
  }

  public void searchConfiguration(JobSearch search, String sortFlag){

    if(search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    Long conflistid = search.getConfListId().getValue();
    String strconfiListname = search.getConfListName().getValue();
    String uname = search.getUserName();
    // boolean publicScope=N;

    if((conflistid != null) && (conflistid != 0)){
      String cId = '%' + String.valueOf(conflistid) + '%';
      sb.append("where str(l.confListId) like ?");
      params.add(cId);
      hasWhere = true;

    }

    if((strconfiListname != null) && !"".equals(strconfiListname.trim())){
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      String cname = '%' + strconfiListname.toUpperCase() + '%';
      sb.append("upper(l.confListName) like ?  ");
      params.add(cname);

    }

    if(uname != null && !uname.trim().equals("")){
      if(hasWhere)
        sb.append(" and ");
      else{
        hasWhere = true;
        sb.append(" where ");
      }
      sb
          .append("l.createdByUserName = ? or (l.createdByUserName != ? and l.accessScope = 'N')");
      params.add(uname);
      params.add(uname);
      // params.add(publicScope);
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals("")){

      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(*) from LogConfigList l "
                  + sb.toString(), params.toArray());
          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        results = getDao().find(
            "select distinct l from LogConfigList l "
                + sb.toString(), params.toArray(), pagination);
        pagination.calculatePages();
      }
      else{
        results = getDao().find(
            "select distinct l from LogConfigList l "
                + sb.toString(), params.toArray());

      }
      search.setResults(results);
      search.setPagination(pagination);
    }
    else{
      String orderByValue = " order by l." + sortFlag;
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(*) from LogConfigList l "
                  + sb.toString(), params.toArray());
          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        pagination.calculatePages();
      }
      // START : #119240
      /*results = getDao().find(
          "select distinct l from LogConfigList l " + sb.toString()
              + orderByValue, params.toArray(), pagination);*/
      if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	  results = getDao().find(
    	          "select distinct l from LogConfigList l " + sb.toString()
    	              + orderByValue+" " + search.getSortOrderFlag(), params.toArray(), pagination);
      }else{
    	  results = getDao().find(
    	          "select distinct l from LogConfigList l " + sb.toString()
    	              + orderByValue, params.toArray(), pagination);
      }
      // END : #119240
      search.setResults(results);
      search.setPagination(pagination);

    }
  }

  public List getConfName(String confName, String uname){
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();
    String cname = confName.toUpperCase() + '%';
    sb
        .append("where upper(l.confListName) like ? and l.createdByUserName = ? or (l.createdByUserName != ? and l.accessScope = 'N')");
    params.add(cname);
    params.add(uname);
    params.add(uname);

    return getDao().find(
        "select distinct l from LogConfigList l " + sb.toString(),
        params.toArray());
  }

  public List getColsandTabsByConfListId(long confListId){
    List list = getDao()
        .find(
            "from LogConfigList l left join fetch l.logConfigDetails where l.confListId=? ",
            new Object[] { Long.valueOf(confListId) });
    if(list.size() > 0)
      return list;
    else
      return null;
  }

  public LogConfigDetail getDbColumnValue(String colHeader){
    LogConfigDetail logCnfgDet = null;
    List results = getDao()
        .find(
            "select distinct ld from LogConfigDetail ld where upper(ld.logConfigDetailId.colHeader)= ? ",
            new Object[] { colHeader.toUpperCase() });
    if(results != null && results.size() > 0){
      logCnfgDet = (LogConfigDetail) results.get(0);
    }
    else{
      System.out.println("getDbColumnValue");
      throw new ServiceException("jobs.Log.configurecolumns.list.error");
    }
    return logCnfgDet;
  }

  public boolean getConfigListByName(String confListName){
    boolean flag = false;
    List records = getDao().find(
        "from LogConfigList l where l.confListName = '" + confListName
            + "'", null);
    if(records.size() > 0){
      flag = true;
      return flag;
    }
    else
      return flag;
  }

  public LogConfigList getDefaultConfigListByUserName(String userName){
    List<LogConfigList> records = getDao().find(
        "from LogConfigList l where upper(l.updatedByUserName) = '"
            + userName.toUpperCase()
            + "' and upper(l.defaultList) ='Y'", null);
    /*
     * if(!records.isEmpty()) return records.get(0); return null;
     */
    if(records != null && !records.isEmpty())
      return records.get(0);
    else{
      return null;
      // throw new
      // ServiceException("jobs.Log.configurecolumns.list.error");
    }
  }

  public int updateDefaultConfigListByUserName(String userName){
    int i = 0;
    i = getDao()
        .bulkUpdate(
            "update LogConfigList  set defaultList = 'N'  where upper(updatedByUserName) = ?",
            new Object[] { userName.toUpperCase() });
    return i;
  }

  public LogConfigList getConfListNameExists(String confListName){
    boolean flag = false;
    List<LogConfigList> records = getDao().find(
        "from LogConfigList l where upper(l.confListName) = '"
            + confListName.toUpperCase() + "'", null);
    if(!records.isEmpty())
      return records.get(0);
    return null;
  }

  public LogConfigList getConfigNameById(String confListName){
    List<LogConfigList> confListNames = getDao().find(
        "from LogConfigList l where upper(l.confListName)=? ",
        new Object[] { confListName.toUpperCase() });
    if(!confListNames.isEmpty())
      return confListNames.get(0);
    return null;
  }

  public boolean getJobContractNotesByNoteandId(long id, String note){
    boolean flag = false;
    List records = getDao()
        .find(
            "from JobContractNote j  where j.jobContractId=? and upper(j.note) =? ",
            new Object[] { Long.valueOf(id), note.toUpperCase() });
    if(records.size() > 0){
      flag = true;
      return flag;
    }
    else
      return flag;
  }

  /**
   * Name :searchInvoice Date :Jul 17, 2008 Purpose : search for
   * jobContractInvoice depending on the criteria sent
   *
   * @param batchReprint
   * @param pageNumber
   * @param sortFlag
   * @return
   */
  public List<JobContractInvoice> searchInvoice(
      BatchReprintHelper batchReprint, int pageNumber, String sortFlag){
    List<JobContractInvoice> invoiceList = new ArrayList<JobContractInvoice>();
    if(batchReprint == null)
      return null;
    String status = batchReprint.getStatus().getValue();
    String branchName = batchReprint.getBranch().getValue();
    if(Constants.CLOSED_STATUS.equals(status) && (branchName != null)
        && !"".equals(branchName.trim())){
      String fromJobid = batchReprint.getFromJobId().getValue();
      String toJobid = batchReprint.getToJobId().getValue();
      Date fromDate = batchReprint.getFromJobFinshDate().getValue();
      Date toDate = batchReprint.getToJobFinshDate().getValue();
    }
    Session sess = getDao().openHibernateSession();
    try{
      Criteria criteria = sess.createCriteria(JobContractInvoice.class,
          "jci").createCriteria("jobContract", "jc").createCriteria(
          "jobOrder", "j");

      if(sortFlag == null || (sortFlag.trim().equals(""))
          || sortFlag.trim().equals("null"))
        criteria.addOrder(Order.asc("jci.invoice"));
      else{
        int myIndex = sortFlag.indexOf(",");
        if(myIndex > 0){
          String[] sortFlags = sortFlag.split(",");
          for(int i = 0; i < sortFlags.length; i++){
            criteria.addOrder(Order.asc(sortFlags[i].trim()));
          }
        }
        else{
          criteria.addOrder(Order.asc(sortFlag));
        }
      }
      //They want to show all old financial invoices in the batch reprint
      //criteria.add(Expression.isNotNull("jci.sentToFinFlag"));

      // Because invoices are getting attached from the original generated
      // pdf, the invoice file name better not to be null.
      criteria.add(Expression.isNotNull("jci.invoiceFileName"));

      String buName = batchReprint.getBusinessUnit().getValue();
      if((buName != null) && !"".equals(buName.trim())){
        System.out.println("buName" + buName);
        criteria.add(Expression.eq("j.buName", buName.trim()
            .toUpperCase()));
      }

      if((branchName != null) && !"".equals(branchName.trim())){
        System.out.println("branchName" + branchName);
        criteria.add(Expression.like("j.branchName", branchName.trim()
            .toUpperCase(), MatchMode.ANYWHERE));
      }

      String invoiceStatus = batchReprint.getInvoiceStatus().getValue();
      if((invoiceStatus != null) && !"".equals(invoiceStatus.trim())
          && !"0".equals(invoiceStatus.trim())){
        System.out.println("invoiceStatus" + invoiceStatus);
        criteria.add(Expression.eq("jc.jobContractStatus",
            invoiceStatus));
      }

      /*
       * sheidaString contractStatus =
       * jobSearch.getContractStatus().getValue();
       *
       * if((contractStatus != null) && !"".equals(contractStatus.trim()))
       * { criteria.add(Expression.eq("contract.status",contractStatus));
       * }
       */
      String createdBy = batchReprint.getCreatedBy().getValue();
      if((createdBy != null) && !"".equals(createdBy.trim())){
        System.out.println("createdBy" + createdBy);
        criteria.add(Expression.like("j.createdByUserName",
            createdBy.trim(), MatchMode.ANYWHERE).ignoreCase());
      }

      String modifiedBy = batchReprint.getModifiedBy().getValue();
      if((modifiedBy != null) && !"".equals(modifiedBy.trim())){
        System.out.println("modifiedBy" + modifiedBy);
        criteria.add(Expression.like("j.updatedByUserName",
            modifiedBy.trim(), MatchMode.ANYWHERE).ignoreCase());
      }

      if((status != null) && !"".equals(status.trim())){
        System.out.println("status" + status);
        status = status.trim();
        if(status.equals(Constants.ALL_STATUS)){
          criteria.add(Expression.in("j.jobStatus", new String[] {
              Constants.OPEN_STATUS, Constants.CLOSED_STATUS,
              Constants.REOPENED_STATUS,
              Constants.CANCELLED_STATUS }));
        }
        else if(status.equals(Constants.OPENREOPEN_STATUS)){
          criteria
              .add(Expression
                  .or(
                      Expression
                          .in(
                              "j.jobStatus",
                              new String[] { Constants.OPEN_STATUS }),
                      Expression
                          .in(
                              "jobStatus",
                              new String[] { Constants.REOPENED_STATUS })));
        }
        else if(status.equals(Constants.OPEN_STATUS)){
          criteria.add(Expression.eq("j.jobStatus",
              Constants.OPEN_STATUS));
        }
        else if(status.equals(Constants.CLOSED_STATUS)){
          criteria.add(Expression.eq("j.jobStatus",
              Constants.CLOSED_STATUS));
        }
        else if(status.equals(Constants.CANCELLED_STATUS)){
          criteria.add(Expression.eq("j.jobStatus",
              Constants.CANCELLED_STATUS));
        }
        else
          criteria.add(Expression.eq("j.jobStatus",
              Constants.REOPENED_STATUS));
      }

      String jobType = batchReprint.getJobType().getValue();
      if((jobType != null) && !"".equals(jobType.trim())){
        System.out.println("jobType" + jobType);
        criteria.add(Expression.eq("j.jobType", jobType.trim()));
      }

      String fromJobid = batchReprint.getFromJobId().getValue();
      String toJobid = batchReprint.getToJobId().getValue();
      if((fromJobid != null) && !"".equals(fromJobid)
          && ((toJobid != null) && !"".equals(toJobid))){
        String fromJobId = "'" + fromJobid.trim() + "'";
        String toJobId = "'" + toJobid.trim() + "'";
        System.out.println("fromJobId" + fromJobid);
        System.out.println("toJobid" + toJobid);

        criteria
            .add(Expression
                .sql("substr(j2_.JOB_NUMBER, INSTR(j2_.JOB_NUMBER, '-')+1 ) between to_number(lower(to_char(substr("
                    + fromJobId
                    + ", INSTR("
                    + fromJobId
                    + ", '-')+1 )))) and to_number(lower(to_char(substr("
                    + toJobId
                    + ", INSTR("
                    + toJobId
                    + ", '-')+1 ))))"));
      }
      else{
        if((fromJobid != null) && !"".equals(fromJobid.trim())){
          fromJobid = fromJobid.trim();
          criteria.add(Expression.like("j.jobNumber", fromJobid
              .toUpperCase(), MatchMode.ANYWHERE));
        }
        if((toJobid != null) && !"".equals(toJobid.trim())){
          toJobid = toJobid.trim();
          criteria.add(Expression.like("j.jobNumber", toJobid
              .toUpperCase(), MatchMode.ANYWHERE));
        }
      }

      Date fromJobFinshDate = batchReprint.getFromJobFinshDate()
          .getValue();
      Date toJobFinshDate = batchReprint.getToJobFinshDate().getValue();
      if(fromJobFinshDate != null && toJobFinshDate != null){
        Calendar tocal = Calendar.getInstance();
        tocal.setTime(toJobFinshDate);
        tocal.add(Calendar.DATE, 1);
        toJobFinshDate = tocal.getTime();
        System.out.println("fromJobFinshDate" + fromJobFinshDate);
        criteria.add(Expression.between("j.jobFinishDate",
            fromJobFinshDate, toJobFinshDate));
      }

      Date fromInvoiceDate = batchReprint.getFromInvoiceDate().getValue();
      Date toInvoiceDate = batchReprint.getToInvoiceDate().getValue();
      if(fromInvoiceDate != null && toInvoiceDate != null){
        System.out.println("fromInvoiceDate" + fromInvoiceDate);
        System.out.println("toInvoiceDate" + toInvoiceDate);
        criteria.add(Expression.between("jci.invoiceDate",
            fromInvoiceDate, toInvoiceDate));
      }

      Date fromInvoiceGenDate = batchReprint.getFromInvoiceGenerateDate()
          .getValue();
      Date toInvoiceGenDate = batchReprint.getToInvoiceGenerateDate()
          .getValue();
      if(fromInvoiceGenDate != null && toInvoiceGenDate != null){
        System.out.println("fromInvoiceGenDate in serach"
            + fromInvoiceGenDate);
        System.out.println("toInvoiceGenDate in serach"
            + toInvoiceGenDate);
        criteria.add(Expression.between("jci.generatedDateTime",
            fromInvoiceGenDate, toInvoiceGenDate));
      }

      Date nomFromDate = batchReprint.getNomDateFrom().getValue();
      Date nomToDate = batchReprint.getNomDateTo().getValue();
      if(nomFromDate != null && nomToDate != null){
        System.out.println("nomFromDate" + nomFromDate);
        criteria.add(Expression.between("j.nominationDate",
            nomFromDate, nomToDate));
      }

      String custRefNum = batchReprint.getCustRefNum().getValue();
      if((custRefNum != null) && !"".equals(custRefNum.trim())){
        System.out.println("custRefNum" + custRefNum);
        custRefNum = custRefNum.trim();
        criteria.add(Expression.like("jc.custRefNum", custRefNum,
            MatchMode.ANYWHERE).ignoreCase());
      }

      String icbRefNum = batchReprint.getIcbRefNum().getValue();
      if((icbRefNum != null) && !"".equals(icbRefNum.trim())){
        System.out.println("icbRefNum" + icbRefNum);
        icbRefNum = icbRefNum.trim();
        criteria.add(Expression.like("j.invoiceValue5", icbRefNum,
            MatchMode.ANYWHERE).ignoreCase());
      }
      String invoice = batchReprint.getInvoice().getValue();
      if(invoice != null && !invoice.equals("")){
        System.out.println("invoice" + invoice);
        criteria.add(Expression.like("jci.invoice", invoice.trim(),
            MatchMode.ANYWHERE).ignoreCase());
      }

      int companyIdOperator = batchReprint.getCompanyId().getOperator();
      String companyIdVal = batchReprint.getCompanyId().getValue();

      if(companyIdVal != null && !"".equals(companyIdVal.trim())){
        System.out.println("companyIdVal" + companyIdVal);
        companyIdVal = companyIdVal.trim();
        switch (companyIdOperator) {
        case Constants.CONTAINS:
          criteria.add(Expression.like("jc.custCode", companyIdVal,
              MatchMode.ANYWHERE).ignoreCase());
          break;
        case Constants.BEGINS_WITH:
          criteria.add(Expression.like("jc.custCode", companyIdVal,
              MatchMode.START).ignoreCase());
          break;
        case Constants.EQUALS:
          criteria.add(Expression.eq("jc.custCode", companyIdVal)
              .ignoreCase());
          break;
        case Constants.NOT_EQUALS:
          criteria.add(Expression.ne("jc.custCode", companyIdVal)
              .ignoreCase());
          break;
        }
      }

      int companyOperator = batchReprint.getCompany().getOperator();
      String companyVal = batchReprint.getCompany().getValue();
      if((companyVal != null) && !"".equals(companyVal.trim())){
        System.out.println("companyVal" + companyVal);
        companyVal = companyVal.trim();
        switch (companyOperator) {
        case Constants.CONTAINS:
          criteria.add(Expression.like("jci.customerName",
              companyVal, MatchMode.ANYWHERE).ignoreCase());
          break;
        case Constants.BEGINS_WITH:
          criteria.add(Expression.like("jci.customerName",
              companyVal, MatchMode.START).ignoreCase());
          break;
        case Constants.EQUALS:
          criteria.add(Expression.eq("jci.customerName", companyVal)
              .ignoreCase());
          break;
        case Constants.NOT_EQUALS:
          criteria.add(Expression.ne("jci.customerName", companyVal)
              .ignoreCase());
          break;
        }
      }

      // int contactIdOperator =
      // batchReprint.getBillingContactId().getOperator();
      String contactIdVal = batchReprint.getBillingContactId().getValue();
      if(contactIdVal != null && !"".equals(contactIdVal.trim())){
        System.out.println("contactIdVal" + contactIdVal);
        contactIdVal = contactIdVal.trim();
        long contactId = Long.parseLong(contactIdVal);
        criteria.add(Expression.eq("jc.billingContact.id", contactId));
        /*
         * switch(contactIdOperator){ case Constants.CONTAINS:
         * criteria.add
         * (Expression.like("jci.jobContract.billingContact.id"
         * ,contactIdVal,MatchMode.ANYWHERE).ignoreCase());break; case
         * Constants.BEGINS_WITH:
         * criteria.add(Expression.like("jci.jobContract.billingContact.id"
         * ,contactIdVal,MatchMode.START).ignoreCase());break; case
         * Constants.EQUALS:
         * criteria.add(Expression.eq("jci.jobContract.billingContact.id"
         * ,contactIdVal).ignoreCase());break; case
         * Constants.NOT_EQUALS:
         * criteria.add(Expression.ne("jci.jobContract.billingContact.id"
         * ,contactIdVal).ignoreCase());break; }
         */
      }
      int contractIdOperator = batchReprint.getContractId().getOperator();
      String contractIdVal = batchReprint.getContractId().getValue();
      if(contractIdVal != null && !"".equals(contractIdVal.trim())){
        System.out.println("contractIdVal" + contractIdVal);
        contractIdVal = contractIdVal.trim();
        switch (contractIdOperator) {
        case Constants.CONTAINS:
          criteria.add(Expression.like("jc.contractCode",
              contractIdVal, MatchMode.ANYWHERE).ignoreCase());
          break;
        case Constants.BEGINS_WITH:
          criteria.add(Expression.like("jc.contractCode",
              contractIdVal, MatchMode.START).ignoreCase());
          break;
        case Constants.EQUALS:
          criteria.add(Expression
              .eq("jc.contractCode", contractIdVal).ignoreCase());
          break;
        case Constants.NOT_EQUALS:
          criteria.add(Expression
              .ne("jc.contractCode", contractIdVal).ignoreCase());
          break;
        }

      }

      int billingContactOperator = batchReprint.getBillingContact()
          .getOperator();
      String billingContactVal = batchReprint.getBillingContact()
          .getValue();
      if((billingContactVal != null)
          && !"".equals(billingContactVal.trim())){
        System.out.println("billingContactVal" + billingContactVal);
        billingContactVal = billingContactVal.trim();
        switch (billingContactOperator) {
        case Constants.CONTAINS:
          criteria
              .add(Expression.like("jc.billingContactName",
                  billingContactVal, MatchMode.ANYWHERE)
                  .ignoreCase());
          break;
        case Constants.BEGINS_WITH:
          criteria.add(Expression.like("jc.billingContactName",
              billingContactVal, MatchMode.START).ignoreCase());
          break;
        case Constants.EQUALS:
          criteria.add(Expression.eq("jc.billingContactName",
              billingContactVal).ignoreCase());
          break;
        case Constants.NOT_EQUALS:
          criteria.add(Expression.ne("jc.billingContactName",
              billingContactVal).ignoreCase());
          break;
        }
      }

      // int schedulerIdOperator =
      // batchReprint.getSchedulerId().getOperator();
      Long schedulerIdVal = batchReprint.getSchedulerId().getValue();
      if(schedulerIdVal != null
          && !"".equals(schedulerIdVal.toString().trim())){
        System.out.println("schedulerIdVal" + schedulerIdVal);
        criteria.add(Expression.eq("jc.contactId", schedulerIdVal));

        // shString strBegin=String.valueOf(schedulerIdVal)+'%';
        // sh String strContains='%'+String.valueOf(schedulerIdVal)+'%';

        /*
         * sheida if((schedulerIdVal!=
         * null)&&(schedulerIdOperator==Constants.CONTAINS)) {
         * criteria.add(
         * Expression.sql("to_char(jobc4_.CONTACT_ID) like (?)"
         * ,strContains, Hibernate.STRING)); } if((schedulerIdVal!=
         * null)&&(schedulerIdOperator==Constants.BEGINS_WITH)) {
         * criteria.add(
         * Expression.sql("to_char(jobc4_.CONTACT_ID) like(?)",strBegin,
         * Hibernate.STRING)); } if((schedulerIdVal!=
         * null)&&(schedulerIdOperator==Constants.EQUALS)) {
         * criteria.add(Expression.eq("jobc.contactId",schedulerIdVal));
         * } if((schedulerIdVal!=
         * null)&&(schedulerIdOperator==Constants.NOT_EQUALS)) {
         * criteria.add(Expression.ne("jobc.contactId",schedulerIdVal));
         * }
         */
      }

      List invoiceCount = criteria.setResultTransformer(
          Criteria.DISTINCT_ROOT_ENTITY).list();
      if(invoiceCount != null){
        batchReprint.setNoOfInvoices(invoiceCount.size());
        System.out.println("invoiceCount.size()" + invoiceCount.size());
      }
      criteria.setResultTransformer(Criteria.ROOT_ENTITY);

      Pagination pagination = batchReprint.getPagination();

      if(pagination != null){
        System.out.println("paginationis not null ");
        System.out.println("first result "
            + (pagination.getCurrentPageNum() - 1)
            * pagination.getNumInPage());
        System.out.println("maxresult " + pagination.getNumInPage());
        criteria.setFirstResult((pagination.getCurrentPageNum() - 1)
            * pagination.getNumInPage());
        criteria.setMaxResults(pagination.getNumInPage());
      }
      log.info("time before executing invoice search query :"
          + new Date());
      invoiceList = criteria.list();
      System.out.println(criteria.toString());

      log
          .info("time after executing invoice search query :"
              + new Date());
      if(invoiceList != null)
        System.out.println("result size: " + invoiceList.size());
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          criteria.setProjection(Projections.alias(Projections
              .rowCount(), "numResults"));
          criteria.setFirstResult(0);
          List totalRecordsList = criteria.list();

          if(totalRecordsList.size() > 0){
            Integer totalRecordsInt = (Integer) totalRecordsList
                .get(0);
            if(totalRecordsInt != null)
              pagination.setTotalRecord(totalRecordsInt
                  .intValue());

          }
        }

        pagination.calculatePages();
      }
    }
    finally{
      if(sess != null){
        getDao().closeHibernateSession(sess);
      }
    }
    return invoiceList;
  }

  /**
   * Name :getBatchReprint Date :Jul 23, 2008 Purpose : 1- get new batch
   * reprint : toDate=currentDate 2- get the already run batch reprint at the
   * given Date
   *
   * It checks if the previous batch reprint date is null or is before five
   * days before the date given, it looks for invoices with generateDate
   * greater than five days ago before given date. If not it looks for
   * invoices with generate date greater than last
   *
   * It sends the array of ids as array of string to generateBatchReprintPDF
   * method to create jasper report batch reprint run date
   *
   * @param toDate
   * @param dirName
   * @param batchReprintHelper
   * @param newFlag
   * @param userName
   * @return
   */
  public List<JobContractInvoice> getBatchReprint(Date toDate,
      BatchReprintHelper batchReprintHelper){
    if(batchReprintHelper == null
        || batchReprintHelper.getBatchReprintBranch() == null
        || toDate == null){
      return null;
    }

    String batchReprintBranch = batchReprintHelper.getBatchReprintBranch()
        .getValue();
    byte[] result = null;
    Date fromDate = null;
    Date lastBatchReprintDate = getPrevBatchReprintDate(batchReprintBranch,
        toDate);

    Calendar fcal = Calendar.getInstance();
    fcal.setTime(toDate);
    fcal.add(Calendar.DATE, -10);//changed to -10 from -5 for the issue 116077
    Date tenDaysAgo = fcal.getTime();

    if(lastBatchReprintDate == null
        || tenDaysAgo.after(lastBatchReprintDate))
      fromDate = tenDaysAgo;
    else
      fromDate = lastBatchReprintDate;

    System.out.println("fromdate " + fromDate);
    System.out.println("todate " + toDate);
    DateSearchField dateSearchFieldFrom = new DateSearchField();
    dateSearchFieldFrom.setValue(fromDate);
    batchReprintHelper.setFromInvoiceGenerateDate(dateSearchFieldFrom);
    DateSearchField dateSearchFieldTo = new DateSearchField();
    dateSearchFieldTo.setValue(toDate);
    batchReprintHelper.setToInvoiceGenerateDate(dateSearchFieldTo);

    List<JobContractInvoice> jobContractInvoiceList = new ArrayList<JobContractInvoice>();
    batchReprintHelper.setBusinessUnit(batchReprintHelper
        .getBatchReprintBusinessUnit());
    batchReprintHelper
        .setBranch(batchReprintHelper.getBatchReprintBranch());

    jobContractInvoiceList = searchInvoice(batchReprintHelper, 0,
        "jc.jobNumber, jci.invoice");
    return jobContractInvoiceList;
    /*
     * if(jobContractInvoiceList==null || jobContractInvoiceList.isEmpty())
     * return null;
     *
     * String[] invoiceIdArray = new String[jobContractInvoiceList.size()];
     * String[] invoiceFileNames = new
     * String[jobContractInvoiceList.size()]; int i=0;
     * for(JobContractInvoice jci : jobContractInvoiceList){
     * invoiceIdArray[i]=String.valueOf(jci.getId());
     * invoiceFileNames[i]=jci.getInvoiceFileName(); i++; }
     *
     *
     * / if((missingFiles==null || missingFiles.isEmpty())&& newFlag){
     * BatchReprint batchReprint = new BatchReprint();
     * batchReprint.setBranchName(batchReprintBranch);
     * batchReprint.setRunDate(toDate);
     * batchReprint.setRunnedByUserName(userName);
     * getDao().save(batchReprint); }
     */

    // return invoiceFileNames;
    // return generateBatchReprintPDF(dirName, jasperDirName,
    // invoiceIdArray);
  }

  /**
   * Name :generateBatchReprintPDF Date :Jul 22, 2008 Purpose : Generating pdf
   * for batchreprint
   *
   * @param dirName
   * @param invoiceIdArray
   * @return
   */
  public byte[] generateBatchReprintPDF(String dirName, String jasperDirName,
      String[] invoiceIdArray){
    if(invoiceIdArray == null || invoiceIdArray.length == 0)
      return null;

    StringBuffer commaSeparatedSB = new StringBuffer();
    commaSeparatedSB.append("'");
    commaSeparatedSB.append(invoiceIdArray[0]);
    commaSeparatedSB.append("'");
    for(int i = 1; i < invoiceIdArray.length; i++){
      commaSeparatedSB.append(",");
      commaSeparatedSB.append("'");
      commaSeparatedSB.append(invoiceIdArray[i]);
      commaSeparatedSB.append("'");
    }
    String invoiceIds = commaSeparatedSB.toString();
    byte[] result = null;
    JasperPrint jasperPrint = null;
    Map paramMap = new HashMap();
    paramMap.put("invoice_id_list", invoiceIds);

    paramMap.put("dirName", jasperDirName);
    javax.sql.DataSource dataSource = (javax.sql.DataSource) ServiceLocator
        .getInstance().getBean("entityDataSource");
    java.sql.Connection connection = null;

    try{
      java.io.InputStream is = new FileInputStream(jasperDirName
          + "invoice_batch.jasper");
      connection = dataSource.getConnection();

      jasperPrint = JasperFillReport.fillReport(is, paramMap, connection);
      if(jasperPrint == null){
        if(connection != null){
          try{
            connection.close();
          }
          catch (Exception e){
          }
        }
        if(is != null){
          is.close();
        }
        throw new ServiceException("INVOICE_GENERATION_ERROR",
            new Object[] { "Fail to Fill Report" }, null);
      }
      /*
       * jasperPrint = JasperFillManager.fillReport( is, paramMap,
       * connection );
       */
      result = JasperExportManager.exportReportToPdf(jasperPrint);
    }
    catch (Throwable e){
      e.printStackTrace();
      throw new ServiceException(e.getMessage(), e);
    }
    finally{
      if(connection != null){
        try{
          connection.close();
        }
        catch (Exception e){
        }
      }
    }

    return result;
  }

  /**
   * Name :getLastBatchReprintDate Date :Jul 22, 2008 Purpose :get the
   * previous batch reprint date before the date sent
   *
   * @param branchName
   * @param toDate
   * @return
   */
  private Date getPrevBatchReprintDate(String branchName, Date toDate){
    Date date = null;
    List list = getDao()
        .find(
            "select max(runDate) from BatchReprint b where upper(b.branchName) = ? and b.runDate < ?",
            new Object[] { branchName.toUpperCase(), toDate });
    if(list != null && !list.isEmpty())
      date = (Date) list.get(0);
    return date;
  }

  /**
   * Name :getLastFiveBatchReprint Date :Jul 22, 2008 Purpose : Return last
   * five batchreprint for that branch
   *
   * @param branchName
   * @return
   */
  public List<BatchReprint> getLastFiveBatchReprint(String branchName){
    if(branchName == null)
      return null;
    Session sess = getDao().openHibernateSession();
    Criteria criteria = sess.createCriteria(BatchReprint.class, "b");
    criteria.addOrder(Order.desc("b.id"));
    criteria.add(Expression.eq("b.branchName", branchName.trim()
        .toUpperCase()));
    criteria.setMaxResults(5);
    return criteria.list();
  }

  /**
   * Name :getBatchReprintById Date :Jul 22, 2008 Purpose :get BatchReprint of
   * the given Id
   *
   * @param id
   * @return
   */
  public BatchReprint getBatchReprintById(long id){
    BatchReprint batchReprint = null;
    List list = getDao().find("from BatchReprint b where id = ?",
        new Object[] { id });
    if(list != null && !list.isEmpty())
      batchReprint = (BatchReprint) list.get(0);
    return batchReprint;
  }

  public JobLog getJobLogById(long id){
    JobLog jobLog = null;
    List list = getDao().find("from JobLog jl where jl.id = ?",
        new Object[] { id });
    if(list != null && !list.isEmpty())
      jobLog = (JobLog) list.get(0);
    return jobLog;
  }

  public List getLatestInvoiceByJobContractId(long id){
    return getDao()
        .find(
            "from JobContractInvoice jinv left join fetch jinv.jobContract jc where jc.id = ? order by jinv.invoice desc",
            new Object[] { id });

  }

  public JobContract saveJobContracts(JobContract jobContract){
    return getDao().save(jobContract);
  }

  /**
   * Name :deleteTemplate Date :Nov 3, 2008 purpose :to delete the template
   *
   * @param jobOrder
   */
  public void deleteTemplate(JobOrder jobOrder){
    getDao().remove(jobOrder);
  }

  public List<JobContractInvoice> getJobContractInvoices(
      Long[] jobContractInvoiceIDs){
    if(jobContractInvoiceIDs == null || jobContractInvoiceIDs.length <= 0){
      return null;
    }
    String questionMarks = CommonUtil
        .getQuestionMarks(jobContractInvoiceIDs.length);
    return getDao()
        .find(
            "from JobContractInvoice jci left join fetch jci.jobContract jc where jci.id in("
                + questionMarks
                + ") order by jc.jobNumber, jci.invoice",
            jobContractInvoiceIDs);
  }

  public JobContract getJobContractHeaderById(Long jobContractId)

  {
    List jcs = getDao()
        .find(
            "from JobContract jc left join fetch jc.jobOrder j left join fetch j.businessUnit left join fetch j.branch left join fetch j.serviceLocation  where jc.id = ?",
            new Object[] { jobContractId });

    if(jcs.size() > 0)
      return (JobContract) jcs.get(0);

    return null;
  }

  public void searchMonthlyJobDetails(JobSearch search, String sortFlag,
      Date fromDate, Date toDate){
    if(search == null)
      return;
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();
    boolean hasWhere = false;
    boolean monthlycheck = true;
    if((fromDate != null) && (toDate != null)){
      sb
          .append(" where j.monthlyFlag = ? and j.monthlyJobNumber is not null and j.jobOrder.jobStatus = ? and j.jobOrder.createTime between ? and ? and j.invoice is null ");
      params.add(monthlycheck);
      params.add(Constants.CLOSED_STATUS);
      params.add(fromDate);
      params.add(toDate);
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals("")){
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(j.jobNumber) from JobContract j "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }

        results = getDao().find(
            "select j from JobContract j left join fetch j.jobOrder  "
                + sb.toString() + " order by j.jobNumber",
            params.toArray(), pagination);

        pagination.calculatePages();
      }
      else{
        results = getDao().find(
            "select j from JobContract j left join fetch j.jobOrder "
                + sb.toString() + " order by j.jobNumber",
            params.toArray());
      }

      search.setResults(results);
      search.setPagination(pagination);
    }
    else{

      String orderByValue = "order by j.jobOrder." + sortFlag;
      if(pagination != null){
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
              "select  count(j.jobNumber) from JobContract j "
                  + sb.toString(), params.toArray());

          if(counts.size() > 0){
            Number count = (Number) counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        results = getDao().find(
            "select j from JobContract j left join fetch j.jobOrder "
                + sb.toString() + orderByValue,
            params.toArray(), pagination);
        pagination.calculatePages();
      }

      search.setResults(results);
    }
  }

  public JobContractInvoice getLastJobContractInvoice(Long jobContractId){
    List jcis = getDao()
        .find(
            "from JobContractInvoice jci where jci.jobContract.id = ? order by jci.generatedDateTime desc",
            new Object[] { jobContractId });
    if(jcis.size() > 0)
      return (JobContractInvoice) jcis.get(0);
    return null;
  }

  /**
   * Name :generateWeeklyReport Date :Jan 23, 2009 Purpose : Calls the jasper
   * engine to create the weekly report
   *
   * @param addWeeklyReport
   * @param dirName
   * @param jasperDirName
   * @return
   */
  public byte[] generateWeeklyReport(AddWeeklyReport addWeeklyReport,
      String dirName, String jasperDirName){
    byte[] result = null;
    JasperPrint jasperPrint = null;
    String buName = addWeeklyReport.getBusinessUnit().getValue();
    String branchName = addWeeklyReport.getBranch().getValue();
    String currency = addWeeklyReport.getCurrency().getValue();
    Date date = addWeeklyReport.getAsOfDate().getValue();
    Map paramMap = new HashMap();
    // params: business_unit, operating_unit, as_of_dt, start_dt, dirName,
    // currency
    paramMap.put("dirName", jasperDirName);
    if(buName == null || buName.trim().length() == 0)
      buName = "%";
    paramMap.put("business_unit", buName);
    if(branchName == null || branchName.trim().length() == 0)
      branchName = "%";
    paramMap.put("operating_unit", branchName);
    Date lastThursday = DateUtil.getLastThursday(date);
    paramMap.put("as_of_dt", lastThursday);
    if(currency == null || currency.trim().length() == 0)
      currency = "%";
    paramMap.put("currency", currency);
    Calendar cal = Calendar.getInstance();
    cal.add(Calendar.DATE, -84);
    Date startDate = cal.getTime();
    paramMap.put("start_dt", startDate);
    javax.sql.DataSource dataSource = (javax.sql.DataSource) ServiceLocator
        .getInstance().getBean("entityDataSource");
    java.sql.Connection connection = null;

    try{
      java.io.InputStream is = new FileInputStream(jasperDirName
          + "weekly_sales_report.jasper");
      connection = dataSource.getConnection();

      jasperPrint = JasperFillReport.fillReport(is, paramMap, connection);
      if(jasperPrint == null){
        if(connection != null){
          try{
            connection.close();
          }
          catch (Exception e){
          }
        }
        if(is != null){
          is.close();
        }
        throw new ServiceException("WEEKLY_REPORT_GENERATION_ERROR",
            new Object[] { "Fail to Fill Report" }, null);
      }
      result = JasperExportManager.exportReportToPdf(jasperPrint);
    }
    catch (Throwable e){
      e.printStackTrace();
      throw new ServiceException(e.getMessage(), new Object[] { e
          .getMessage() }, e);
    }
    finally{
      if(connection != null){
        try{
          connection.close();
        }
        catch (Exception e){
        }
      }
    }

    return result;
  }

  public List getJobProdSampleSeqId(int prodSeqNum, String jobNumber,
      int linkedVesselRow, String sampleLoc){

    List jps = getDao()
        .find(
            "from JobProdSample jps where jps.jobProdSampleId.prodSeqNum = ? and jps.jobProdSampleId.jobNumber = ? and jps.jobProdSampleId.linkedVslRow = ? and jps.jobSampleLocation= ?",
            new Object[] { prodSeqNum, jobNumber, linkedVesselRow,
                sampleLoc });
    return jps;
  }

  public void updateJobProdSampleSeqId(JobProdSample jobProdSample){
    getDao().save(jobProdSample);

  }

  /**
   * Name :getJobProductsSeqId Date :Feb 13, 2009 Purpose : To get jobProds
   *
   * @param linkedVesselRow
   * @param jobNumber
   * @param productName
   * @return list
   */
  public List getJobProductsSeqId(int linkedVesselRow, String jobNumber,
      String productName){

    List jpds = getDao()
        .find(
            "from JobProd jpds where jpds.jobProdId.linkedVslRow = ? and jpds.jobProdId.jobNumber = ? and jpds.jobProductName = ?",
            new Object[] { linkedVesselRow, jobNumber, productName });
    return jpds;
  }

  /**
   * Name :updateJobProdsSeqId Date :Feb 13, 2009 Purpose : to update jobProd
   * prodSeqNums
   *
   * @param jobProd
   * @return
   */
  public void updateJobProdsSeqId(JobProd jobProd){
    getDao().save(jobProd);

  }

  public List<JobContractAttachType> getJobContractAttachTypes(){
    return getDao().find(
        "from JobContractAttachType t order by t.type desc ", null);
  }

  public Country getCountryCodeByCustCodeAndVatRegId(String custCode,
      String vatRegId){
    Country country = null;
    List custVatRegs = getDao()
        .find(
            " from CustVatRegistration cv where cv.custVatRegistrationId.custCode= ? and cv.custVatRegistrationId.registrationId= ?",
            new Object[] { custCode, vatRegId });
    if(custVatRegs.size() > 0)
      return ((CustVatRegistration) custVatRegs.get(0)).getCountry();
    else
      return country;
  }
  /**
   * Name :updateJobVesselsSeqId 
   * Date :April 23, 2009 
   * Purpose : to update jobVessel linkedVslRow
   * @param jobVessel
   * @return
   */
  public void updateJobVesselsSeqId(JobVessel jobVessel){
    getDao().save(jobVessel);

  }
  /* For iTrack issue #127767 -Starts*/ 
  /**
   * Name :updateOnlyJobOrder
   * Date :Jun 30, 2009
   * Purpose : to save jobOrder
   */
  public void updateOnlyJobOrder(JobOrder jobOrder){
  	getDao().save(jobOrder);
  }
  /**
   * Name :getReceivedJobsByUserName
   * Date :Jun 30, 2009
   * Purpose : to get ReceivedByJobs by username
   * @return List
   */
  public List<JobOrder> getReceivedJobsByUserName(String userName){
      List jobs = getDao()
          .find(
              "from JobOrder job where job.receivedByUserName=? ",
              new Object[] { userName });
      if(jobs.size() > 0){
        return jobs;
      } else {
        return null;
      }
    }
  /**
   * Name :getAdminContactJobsByUserName
   * Date :Jun 30, 2009
   * Purpose : to get AdminContactJobs by username
   * @return List
   */
  public List<JobOrder> getAdminContactJobsByUserName(String userName){
      List jobs = getDao()
          .find(
              "from JobOrder job where job.adminContactUserName = ? ",
              new Object[] { userName });
      if(jobs.size() > 0){
        return jobs;
      } else {
        return null;
      }
    }
  /**
   * Name :getCreatedByJobsByUserName
   * Date :Jun 30, 2009
   * Purpose : to get CreatedByJobs by username
   * @return List
   */
  public List<JobOrder> getCreatedByJobsByUserName(String userName){
      List jobs = getDao()
          .find(
              "from JobOrder job where job.createdByUserName=? ",
              new Object[] { userName });
      if(jobs.size() > 0){
        return jobs;
      } else {
        return null;
      }
    }
  /**
   * Name :getUpdatedByJobsByUserName
   * Date :Jun 30, 2009
   * Purpose : to get UpdatedByJobs by username
   * @return List
   */
  public List<JobOrder> getUpdatedByJobsByUserName(String userName){
      List jobs = getDao()
          .find(
              "from JobOrder job where job.updatedByUserName=?",
              new Object[] { userName});
      if(jobs.size() > 0){
        return jobs;
      } else {
        return null;
      }
    }
  /**
   * Name :getLogConfigListsByCreatedByUser
   * Date :Jun 29, 2009
   * Purpose : to get LogConfigListsByUserName
   * @return List
   */
  public List<LogConfigList> getLogConfigListsByCreatedByUser(String userName){
  	List<LogConfigList> list =  getDao().find(
            "select lcf from LogConfigList lcf where lcf.createdByUserName=?",
            new Object[] { userName }
          );
          if(!list.isEmpty() && list.size() > 0){
            return list;
          }
          return null;
  }
  /**
   * Name :getLogConfigListsByUpdatedByUser
   * Date :Jun 29, 2009
   * Purpose : to get LogConfigListsByUserName
   * @return List
   */
  public List<LogConfigList> getLogConfigListsByUpdatedByUser(String userName){
  	
  	List<LogConfigList> list =  getDao().find(
            "select lcf from LogConfigList lcf where lcf.updatedByUserName=?",
            new Object[] { userName}
          );
          if(!list.isEmpty() && list.size() > 0){
            return list;
          }
          return null;
  }
  /* For iTrack issue #127767 -Ends  */ 
/*public JobManualTest getJobManualTestByTestId(String jobManualTest) {
	List jobManualTests = getDao().find("select t from JobManualTest t where t.testId = ?",
	        new Object[] { jobManualTest });
	    
	    if(jobManualTests != null && jobManualTests.size() > 0)
	      return (JobManualTest) jobManualTests.get(0);
	    return null;
}*/

	//START: Issue # 75052	
	public void saveRevisionNotes(RevisionNotes revisionNotes){	
		
		//Set incremented revision number
		revisionNotes.getRevisionNoteId().setRevisionNumber(getNextRevisionNumber(revisionNotes.getRevisionNoteId().getJobNumber()));
		//Set Current Date & time
		revisionNotes.setRevisionDateTime(GregorianCalendar.getInstance().getTime());
	    try{
	        getDao().save(revisionNotes);
	      }
	      catch (Exception e){
	        System.out
	            .println("exception while RevisionNotes() :"
	                + e.toString());
	      }
	}
	
 	public List<RevisionNotes> getRevisionNotes(String jobNumber){
 		List revisionNotes = getDao()
		 .find(
           "  from RevisionNotes rn  where rn.revisionNoteId.jobNumber = ? order by rn.revisionDateTime DESC",
				 new Object[] { jobNumber.toUpperCase() }); 
 		return revisionNotes;
 	}
 	
 	private long getNextRevisionNumber(String jobNumber){
 		long lngNotes = 0;
 		List revisionNotes = getDao()
		 .find(
          " select max(revisionNoteId.revisionNumber) from RevisionNotes rn  where rn.revisionNoteId.jobNumber = ? ",
				 new Object[] { jobNumber.toUpperCase() });
 		if(null != revisionNotes && revisionNotes.size() > 0){
 			if(null != revisionNotes.get(0)){
 				lngNotes = Long.valueOf(revisionNotes.get(0).toString());
 			}
 		}
 		lngNotes += 1;
		return lngNotes;
	}
 	//END: Issue # 75052
 	
 	//START 125185
 	public boolean notifyAboutPrebillEntry(Long jobContractID){
 		List lst = getDao().find("select id from Prebill where jobContract.id = ?",new Object[] {jobContractID});
 		if(null != lst && lst.size() > 0)
 			return true;
 		else
 			return false;
 	}
 	//END 125185
 	
 	// START : #127441
 /**
   * Name :searchCancelledJobDetails
   * Date :Aug 14, 2009
   * Purpose : to set the resultList for cancelled jobs
   */
	public void searchCancelledJobDetails(CancelledJobsSearch search,
			String sortFlag) {
		if (search == null)
			return;
		Criteria criteria = null;
		List results = null;
		String status = search.getStatus().getValue();
		String branchName = search.getBranch().getValue();

		Session sess = null;
		try {
			sess = getDao().openHibernateSession();
			criteria = sess.createCriteria(JobOrder.class, "job");
			if (sortFlag == null || (sortFlag.trim().equals(""))
					|| sortFlag.trim().equals("null")) {
				criteria.addOrder(Order.asc("job.jobNumber"));
			} else {
				if (search.getSortOrderFlag() != null
						&& search.getSortOrderFlag().trim().equals("asc")) {
					criteria.addOrder(Order.asc(sortFlag));
				} else {
					criteria.addOrder(Order.desc(sortFlag));
				}
			}

			String buName = search.getBusinessUnit().getValue();
			if ((buName != null) && !"".equals(buName.trim())) {
				criteria.add(Expression.eq("buName", buName.trim()
						.toUpperCase()));
			}
			
			if ((branchName != null) && !"".equals(branchName.trim())) {
				criteria.add(Expression.like("branchName", branchName.trim()
						.toUpperCase(), MatchMode.ANYWHERE));
			}
			
			criteria.add(Expression.eq("jobStatus", Constants.CANCELLED_STATUS));
			
			Date nomFromDate = search.getNomDateFrom().getValue();
			Date nomToDate = search.getNomDateTo().getValue();
			if (nomFromDate != null && nomToDate != null) {
				criteria.add(Expression.between("nominationDate", nomFromDate,
						nomToDate));
			}

			String jobId = search.getJobId().getValue();
			if (jobId != null && !jobId.trim().equals("")) {
				criteria.add(Expression.like("jobNumber", jobId.toUpperCase(),
						MatchMode.ANYWHERE));
			}

			Pagination pagination = search.getPagination();
			if (pagination != null) {
				criteria.setFirstResult((pagination.getCurrentPageNum() - 1)
						* pagination.getNumInPage());
				criteria.setMaxResults(pagination.getNumInPage());
			}

			log.info("time before executing CANCEL job search query :"
					+ new Date());
			results = criteria.list();
			log.info("time after executing CANCEL job search query :"
					+ new Date());
			if (results != null)
				if (pagination != null) {
					if (pagination.getTotalRecord() > 0) {
						criteria.setProjection(Projections.alias(Projections
								.rowCount(), "numResults"));
						criteria.setFirstResult(0);
						List totalRecordsList = criteria.list();
						if (totalRecordsList.size() > 0) {
							Integer totalRecordsInt = (Integer) totalRecordsList
									.get(0);
							if (totalRecordsInt != null)
								pagination.setTotalRecord(totalRecordsInt
										.intValue());
						}
					}
					pagination.calculatePages();
				}
		} finally {
			if (sess != null) {
				getDao().closeHibernateSession(sess);
			}
		}
		
		
		if(results!=null && results.size() > 0){
			int resultSize = results.size();
			String[] cancelNoteDetails = new String[resultSize];
			for(int i = 0; i < resultSize; i++){
				JobOrder jobOrder =	(JobOrder)results.get(i);
				String cancelNote = "";
		        String cancelNoteDetls ="";
		        if(jobOrder.getCancelReasonNote()!=null && !jobOrder.getCancelReasonNote().trim().equals("")){
		        	  String dt = DateUtil.formateJobDescriptionDate(jobOrder.getUpdateTime());
		        	  String date = DateUtil.getTimeFromDate(jobOrder.getUpdateTime());
		        	  cancelNoteDetls = jobOrder.getCancelReasonUserName()
		              + ", "
		              + dt
		              + ", "
		              + date
		              + ":"
		              + jobOrder.getCancelReasonNote(); 	  	        	  
		        	}
		         cancelNoteDetails[i] = cancelNoteDetls;
		        //addJobContracts.setCancelNoteDetails(cancelNoteDetails);		        
			}			
			search.setCancelNoteDetails(cancelNoteDetails);			
		}	
	
		search.setResults(results);
	}
 	// END : #127441
}
