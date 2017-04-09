/*
 * CEJobOrderDataSource.java
 * 
 * @version
 * 
 * Jun 22, 2009
 * 
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 */

package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.entity.Contact;
import com.intertek.entity.CustAddress;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.entity.Estimation;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

/**
 * The Class CEJobOrderDataSource.
 * 
 * @author Patni
 */
public class CEJobOrderDataSource extends AbstractJasperDataSource {

	/** The Constant fieldNames. */
	public static final String[] fieldNames = { "phoenix_order_num",
			"quote_issue_date", "quote_number", "ecs_order_date",
			"ecs_order_num", "order_amount", "currency", "payment_terms",
			"deposit_amount", "deposit_type", "deposit_ref_num",
			"deposit_paid", "model_num", "follow_up_services", "project_num",
			"operation_status", "billing_status", "project_type",
			"business_stream", "warehouse", "sales_rep", "secondary_sales",
			"project_manger", "customer_ready_date", "actual_ready_date",
			"promise_completion", "product_evaluation", "service_location",
			"bill_to_customer", "bill_to_customer_company",
			"bill_to_customer_address1", "bill_to_customer_address2",
			"bill_to_customer_address3", "bill_to_customer_city_state_zip_co",
			"bill_to_customer_customer_num", "bill_to_customer_contact",
			"bill_to_customer_phone", "bill_to_customer_fax",
			"bill_to_customer_email", "ship_to_customer",
			"ship_to_customer_company", "ship_to_customer_address1",
			"ship_to_customer_address2", "ship_to_customer_address3",
			"ship_to_customer_city_state_zip_co",
			"ship_to_customer_customer_num", "ship_to_customer_contact",
			"ship_to_customer_phone", "ship_to_customer_fax",
			"ship_to_customer_email", "manufacture", "manufacture_company",
			"manufacture_address1", "manufacture_address2",
			"manufacture_address3", "manufacture_city_state_zip_co",
			"manufacture_customer_num", "manufacture_contact",
			"manufacture_phone", "manufacture_fax", "manufacture_email",
			"task", "standard_description", "task_description", "task_owner",
			"service_offering", "task_manager", "sample_description",
			"task_service_location", "task_operation_status", "task_notes",
			"revenue", "po_number", "task_billing_status", "start_date",
			"completion_date", "ready_date", "eng_data", "tech_data",
			"adm_data" };

	/** The field types. */
	static private String[] fieldTypes = { "revenue", "BigDecimal",
			"customer_ready_date", "Timestamp", "actual_ready_date",
			"Timestamp", "promise_completion", "Timestamp", "start_date",
			"Timestamp", "completion_date", "Timestamp", "ready_date",
			"Timestamp", "deposit_amount", "BigDecimal", "ecs_order_date",
			"Timestamp", "quote_issue_date", "Timestamp", "order_amount",
			"BigDecimal", "deposit_paid", "BigDecimal" };

	/**
	 * Instantiates a new cE job order data source.
	 */
	public CEJobOrderDataSource() {
		super.fieldNames = fieldNames;
		super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String,
	 *      java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public JRDataSource getDataSource(String reportName,
			Map<String, Object> params) {
		SearchService searchService = (SearchService) ServiceLocator
				.getInstance().getBean("searchService");
		Map<String, String> criteria = new HashMap<String, String>();
		criteria.put("jobNumber", params.get("job_Number").toString());
		List<Object[]> resultSetList = new ArrayList<Object[]>();
		try {

            List<CEJobOrder> jobList = (List<CEJobOrder>) searchService.search(CEJobOrder.class, criteria);
            for (CEJobOrder jo : jobList) {
                CEJobContract jc = jo.getJobContract();
                Set dpInv = jc.getDepositInvoices();
                DepositInvoice di = getDepositInvoice(dpInv);
                for (com.intertek.phoenix.entity.JobServiceLevel level : jo.getRootServiceLevel().getChildServiceLevels()) {
                    Set<JobTest> JobTestSet = level.getJobTests();
                    for (JobTest jt : JobTestSet) {
                        // TODO
                        // A much better solution is to call a search method
                        // that
                        // returns the desired List<Object[]> directly. No post-
                        // processing is needed. If such a search method does
                        // not
                        // exist in the search service, add one.
                        // In the end, it is just data retrieving. --RQ
                        Object[] obArray = new Object[] {
                                                         jo.getJobNumber(),
                                                         jo.getQuote() == null ? null : jo.getQuote().getQuoteDate(),
                                                         jo.getQuoteNumber(),
                                                         jo.getQuote() == null ? null : jo.getQuote().getOrderDate(),
                                                         jo.getQuote() == null ? null : jo.getQuote().getOrderNumber(),
                                                         BigDecimal.valueOf(jt.getQuotedAmount()),
                                                         jc.getContractCurrency(),
                                                         jc.getPaymentTerms(),
                                                         getDepositInvoiceAmount(dpInv, false),
                                                         di == null ? "" : di.getDepositType().getDescription(),
                                                         di == null ? "" : di.getDepositReference(),
                                                         getDepositInvoiceAmount(dpInv, true),
                                                         jo.getModelNumber(),
                                                         getFollowUpService(jo.isFollowUp()),
                                                         jo.getProjectNumber(),
                                                         jo.getOperationalStatus() == null ? "" : jo.getOperationalStatus().getDescription(),
                                                         jo.getBillingStatus() == null ? "" : jo.getBillingStatus().getDescription(),
                                                         jo.getProjectType().getDescription(),
                                                   //      jo.getBusinessStream() == null ? null : jo.getBusinessStream().getDescription(),
                                                         jo.getBuName(),
                                                         jo.getBranchName(),
                                                         jo.getSalesPersonName(),
                                                         jo.getSecondarySalesPersonName(),
                                                         jo.getProjectManagerName(),
                                                         jo.getCustomerReadyDate(),
                                                         jo.getActualReadyDate(),
                                                         jo.getPromiseCompletionDate(),
                                                         jo.getDescription(),
                                                         jo.getServiceLocation().getValue(),
                                                         jc.getBillingCustomer() == null ? null : jc.getBillingCustomer().getName(),
                                                         jc.getBillingCustomer() == null ? null : jc.getBillingCustomer().getName(),
                                                         jc.getBillingAddress() == null ? null : jc.getBillingAddress().getAddress1(),
                                                         jc.getBillingAddress() == null ? null : jc.getBillingAddress().getAddress2(),
                                                         jc.getBillingAddress() == null ? null : jc.getBillingAddress().getAddress3(),
                                                         getAddressDetail(jc.getBillingAddress()),
                                                         jc.getBillingCustCode(),
                                                         getCustomerName(jc.getBillingContact()),
                                                         getCustomerPhone(jc.getBillingContact()),
                                                         jc.getBillingContact() == null ? null : jc.getBillingContact().getFax(),
                                                         getCustomerEmail(jc.getBillingContact()),
                                                         jc.getReportReceivingCustomer() == null ? null : jc.getReportReceivingCustomer().getName(),
                                                         jc.getReportReceivingCustomer() == null ? null : jc.getReportReceivingCustomer().getName(),
                                                         jc.getReportReceivingAddress() == null ? null : jc.getReportReceivingAddress().getAddress1(),
                                                         jc.getReportReceivingAddress() == null ? null : jc.getReportReceivingAddress().getAddress2(),
                                                         jc.getReportReceivingAddress() == null ? null : jc.getReportReceivingAddress().getAddress3(),
                                                         getAddressDetail(jc.getReportReceivingAddress()),
                                                         jc.getReportReceivingCustCode(),
                                                         getCustomerName(jc.getReportReceivingContact()),
                                                         getCustomerPhone(jc.getReportReceivingContact()),
                                                         jc.getReportReceivingContact() == null ? null : jc.getReportReceivingContact().getFax(),
                                                         getCustomerEmail(jc.getReportReceivingContact()),
                                                         jc.getManufacturerCustomer() == null ? null : jc.getManufacturerCustomer().getName(),
                                                         jc.getManufacturerCustomer() == null ? null : jc.getManufacturerCustomer().getName(),
                                                         jc.getManufacturerAddress() == null ? null : jc.getManufacturerAddress().getAddress1(),
                                                         jc.getManufacturerAddress() == null ? null : jc.getManufacturerAddress().getAddress2(),
                                                         jc.getManufacturerAddress() == null ? null : jc.getManufacturerAddress().getAddress3(),
                                                         getAddressDetail(jc.getManufacturerAddress()),
                                                         jc.getManfCustCode(),
                                                         getCustomerName(jc.getManufacturerContact()),
                                                         getCustomerPhone(jc.getManufacturerContact()),
                                                         jc.getManufacturerContact() == null ? null : jc.getManufacturerContact().getFax(),
                                                         getCustomerEmail(jc.getManufacturerContact()),
                                                         jt.getTestId(),
                                                         jt.getTest().getTestId(),
                                                         jt.getLineDescription(),
                                                         jo.getBranchName(), 
                                                         jt.getServiceOffering() == null ? "" : jt.getServiceOffering().getDescription(),
                                                         jt.getTaskManager() == null ? "" : getTaskmanager(jt.getTaskManager()),
                                                         jt.getSampleDescription(),
                                                         jo.getServiceLocation().getName(),
                                                         jo.getOperationalStatus() == null ? "" : jo.getOperationalStatus().getDescription(),
                                                         "",// getTaskNotes(joli.getNotes()),TODO
                                                         BigDecimal.valueOf(jt.getForcastedRevenue()), jc.getPoNumber(),
                                                         jt.getBillingStatus() == null ? "" : jt.getBillingStatus().getDescription(), jt.getStartDate(),
                                                         jt.getEndDate(), jo.getActualReadyDate(), getUserData(jt.getEstimations()),// engineer
                                                         getUserData(jt.getEstimations()),// technician
                                                         // data..
                                                         getUserData(jt.getEstimations()) // admin data.....
                        };
                        resultSetList.add(obArray);
                    }
                }
            }
        }
        catch (PhoenixException e) {
            e.printStackTrace();
        }
        return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), resultSetList, this, params);
    }

	/**
	 * Gets the estimate.
	 * 
	 * @param estimations
	 *            the estimations
	 * @param type
	 *            the type
	 * 
	 * @return the estimate
	 */
	private String getUserData(Set<Estimation> estimations) {
		String estimateHour = "";
		try {
			for (Estimation estimation : estimations) {
				if (!estimations.isEmpty() && null != estimation.getUserType()
						&& null != estimation.getUserType().getDescription()) {
					if (estimation.getUserType().getDescription().equals(
							"Engineer")
							|| estimation.getUserType().getDescription()
									.equalsIgnoreCase("Technician")
							|| estimation.getUserType().getDescription()
									.equals("Admin")) {
						estimateHour = String.valueOf(estimation
								.getEstimatedHour());
					}
				}
			}
		} catch (Exception e) {
		}

		return estimateHour;
	}

	/**
	 * Gets the task notes.
	 * 
	 * @param notes
	 *            the notes
	 * 
	 * @return the task notes
	 */
	// private Object getTaskNotes(Set<JobOrderLineItemNote> notes) {
	// StringBuffer noteBuffer=new StringBuffer();
	// for(JobOrderLineItemNote note:notes)
	// {
	// if(note.getNoteType().description().equalsIgnoreCase("Billing")){
	// noteBuffer.append(note.getNote());
	// noteBuffer.append(" ");
	// }
	//            
	// }
	// return noteBuffer.toString().trim();
	// }
	/**
	 * Gets the taskmanager.
	 * 
	 * @param taskManager
	 *            the task manager
	 * 
	 * @return the taskmanager
	 */
	private Object getTaskmanager(Employee taskManager) {
		StringBuffer userName = new StringBuffer();
		userName.append(taskManager.getFirstName());
		userName.append(" ");
		userName.append(taskManager.getLastName());

		return userName.toString();
	}

	/**
	 * Gets the follow up service.
	 * 
	 * @param followUp
	 *            the follow up
	 * 
	 * @return the follow up service
	 */
	private Object getFollowUpService(boolean followUp) {
		String value = "";
		if (followUp) {
			value = "Yes";
		} else {
			value = "No";
		}
		return value;
	}

	/**
	 * Gets the customer email.
	 * 
	 * @param contact
	 *            the contact
	 * 
	 * @return the customer email
	 */
	private Object getCustomerEmail(Contact contact) {
		String email = "";
		try {
			if (contact.getWorkEmail() != null
					&& !contact.getWorkEmail().isEmpty()) {
				email = contact.getWorkEmail();
			} else if (contact.getPersonalEmail() != null
					&& !contact.getPersonalEmail().isEmpty()) {
				email = contact.getPersonalEmail();
			}
		} catch (Exception e) {
		}

		return email;

	}

	/**
	 * Gets the customer phone.
	 * 
	 * @param contact
	 *            the contact
	 * 
	 * @return the customer phone
	 */
	private Object getCustomerPhone(Contact contact) {
		String phone = "";
		try {
			if (contact.getWorkPhone() != null
					&& !contact.getWorkPhone().isEmpty()) {
				phone = contact.getWorkPhone();
			} else if (contact.getPersonalPhone() != null
					&& !contact.getPersonalPhone().isEmpty()) {
				phone = contact.getPersonalPhone();
			}
		} catch (Exception e) {
		}

		return phone;
	}

	/**
	 * Gets the customer name.
	 * 
	 * @param contact
	 *            the contact
	 * 
	 * @return the customer name
	 */
	private Object getCustomerName(Contact contact) {
		StringBuffer nameBuffer = new StringBuffer();
		try {
			nameBuffer.append(contact.getFirstName());
			nameBuffer.append(" ");
			nameBuffer.append(contact.getLastName());
		} catch (Exception e) {
		}
		return nameBuffer.toString();
	}

	/**
	 * Gets the address detail.
	 * 
	 * @param custAddress
	 *            the addr
	 * 
	 * @return the address detail
	 */
	private Object getAddressDetail(CustAddress custAddress) {

		StringBuffer addressBuffer = new StringBuffer();
		try {
			addressBuffer.append(custAddress.getCity());
			addressBuffer.append(" ");
			addressBuffer.append(custAddress.getState());
			addressBuffer.append(" ");
			addressBuffer.append(custAddress.getPostal());
			addressBuffer.append(" ");
			addressBuffer.append(custAddress.getCountry());
		} catch (Exception e) {
		}
		return addressBuffer.toString();
	}

	@Override
	public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params) {
		String[] fields = { "order_amount" };
		double[] results = ReportUtil.summariseFields(fields, amds);
		amds.addCalculatedField("order_amount", new BigDecimal(String
				.valueOf(results[0])));

	}

	@Override
	public Object calculateFieldValue(String fieldName,
			Map<String, Object> params, ArrayMapDataSource source) {
		return source.getCalculatedField(fieldName);
	}

    /**
     * Gets the deposit invoice.
     * 
     * @param depositInvoices
     *            the deposit invoices
     * 
     * @return the deposit invoice
     */
    private DepositInvoice getDepositInvoice(Set<DepositInvoice> depositInvoices) {
        if(depositInvoices!=null&& !depositInvoices.isEmpty()){
        return depositInvoices.iterator().next();
        }
        return null;
        
    }
     private BigDecimal getDepositInvoiceAmount(Set<DepositInvoice> depositInvoices,boolean diapplied) {
            double ditotal=0.00;
            double avtotal=0.00;
         if(depositInvoices!=null){
            for(DepositInvoice di:depositInvoices){
                    ditotal+=di.getDepositAmount();
                    avtotal+=di.getAvailableAmount();
            }
            //TODO:want to verify this logic of Deposit Applied
            if(diapplied){
                return new BigDecimal(-+(avtotal-ditotal));
            }
            }
            return new BigDecimal(ditotal);
            
     }

	@Override
	public JRDataSource getDataSource(String reportName,Map<String, Object> params, Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

}
