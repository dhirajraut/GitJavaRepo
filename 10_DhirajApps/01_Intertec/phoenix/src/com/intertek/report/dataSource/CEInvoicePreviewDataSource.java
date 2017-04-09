package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

public class CEInvoicePreviewDataSource extends AbstractJasperDataSource {
    private static final String[] fieldNames = {
    											"LINE_ITEM_ID",
                                                "INVOICE",
                                                "CUST_CODE",            
                                                "PYMNT_TERMS_CD",       
                                                "BILLING_CONTACT_NAME",
                                                "INVOICE_LABEL1",
                                                "INVOICE_VALUE1",
                                                "INVOICE_LABEL2",
                                                "INVOICE_VALUE2",
                                                "INVOICE_LABEL3",
                                                "INVOICE_VALUE3",
                                                "INVOICE_LABEL4",
                                                "INVOICE_VALUE4",
                                                "INVOICE_LABEL5",
                                                "INVOICE_VALUE5",
                                                "CUST_REF_NUM",
                                                "TRANSACT_CURRENCY_CD",
                                                "INVOICE_DESCR",
                                                "JOB_FINISH_DATE",
                                                "JOB_NUMBER",            
                                                "NOMINATION_DT",
                                                "JOB_DESCRIPTION",      
                                                "SERVICE_LOCATION_CODE",
                                                "BU_NAME",
                                                "LINE_DESCRIPTION",
                                                "SPLIT_PCT",
                                                "DISCOUNT_PCT",
                                                "CURRENCY_CD",      
                                                "NET_PRICE",        
                                                "UNIT_PRICE",       
                                                "ACCOUNT",          
                                                "PRODUCT_GROUP",
                                                "DEPTID",
                                                "PRIMARY_BRANCH_CD",
                                                "BRANCH_NAME",
                                                "ADDRESS1",
                                                "ADDRESS2",
                                                "ADDRESS3",
                                                "CITY",
                                                "COUNTY",
                                                "COUNTRY",
                                                "POSTAL",
                                                "STATE",
                                                "ADDRESS4",
                                                "CUSTOMERS_NAME",
                                                "LINE_NUMBER",
                                                "NAME",
                                                "BUSINESS_UNITS_COUNTRY_CODE",
                                                "QUOTE_ID",
                                                "PYMNT_TERMS_DESC",
                                                "BRANCH_DESCRIPTION",
                                                "SHIP_ADDRESS1",
                                                "SHIP_ADDRESS2",
                                                "SHIP_ADDRESS3",
                                                "SHIP_CITY",
                                                "SHIP_STATE",
                                                "SHIP_POSTAL",
                                                "SHIP_NAME",
                                                "SHIPING_CONTACT_NAME"
                                            };
    
    private static final String[] fieldTypes = {
                                                "JOB_FINISH_DATE", "Timestamp",
                                                "NOMINATION_DT", "Timestamp",
                                                "SPLIT_PCT", "BigDecimal",
                                                "DISCOUNT_PCT", "BigDecimal",
                                                "NET_PRICE", "BigDecimal",
                                                "UNIT_PRICE", "BigDecimal",
                                                "LINE_NUMBER", "BigDecimal",
                                                "ROUNDING_DIGITS", "BigDecimal"
                                            };

    public CEInvoicePreviewDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
     /*   SearchService searchService = ServiceManager.getSearchService();*/
        String hql = "SELECT ceil.id as  LINE_ITEM_ID,ce.invoiceNumber AS INVOICE, cejc.customerCode as CUST_CODE, cejc.paymentReferenceNum as PYMNT_TERMS_CD, " +
                "(ce.billingContact.firstName||' '||ce.billingContact.lastName) AS BILLING_CONTACT_NAME,"+
        		"cejc.invoiceLabel1 AS INVOICE_LABEL1, cejc.invoiceValue1 AS INVOICE_VALUE1, " +
        		"cejc.invoiceLabel2 AS INVOICE_LABEL2, cejc.invoiceValue2 AS INVOICE_VALUE2, " +
        		"cejc.invoiceLabel3 AS INVOICE_LABEL3, cejc.invoiceValue3 AS INVOICE_VALUE3, " +
        		"cejc.invoiceLabel4 AS INVOICE_LABEL4, cejc.invoiceValue4 AS INVOICE_VALUE4, " +
        		"cejc.invoiceLabel5 AS INVOICE_LABEL5, cejc.invoiceValue5 AS INVOICE_VALUE5, " +
        		"cejc.custRefNum AS CUST_REF_NUM,cejc.transactionCurrency AS TRANSACT_CURRENCY_CD," +
        		"cejc.invoiceDescription as INVOICE_DESCR,cejob.actualReadyDate AS JOB_FINISH_DATE," +
        		"cejob.jobNumber AS JOB_NUMBER, cejob.actualReadyDate AS NOMINATION_DT," +    
        		"ce.description as JOB_DESCRIPTION," +
        		"cejob.serviceLocationCode AS SERVICE_LOCATION_CODE,cejob.buName as BU_NAME, " +
        		"cejl.description AS LINE_DESCRIPTION,cejl.splitPct AS SPLIT_PCT," +
        		"cejl.discountPct AS DISCOUNT_PCT,cejl.currencyCd AS CURRENCY_CD,ceil.netPrice AS NET_PRICE," +
        		"ceil.unitPrice AS UNIT_PRICE,ceil.account AS ACCOUNT,cejl.productGroup AS PRODUCT_GROUP," +
        		"cejl.deptid AS DEPTID,cejl.primaryBranchCd AS PRIMARY_BRANCH_CD," +
        		"bran.name AS BRANCH_NAME,custAddr.address1 AS ADDRESS1,custAddr.address2 AS ADDRESS2," +
        		"custAddr.address3 AS ADDRESS3,custAddr.city AS CITY,custAddr.county AS COUNTY,custAddr.country AS COUNTRY," +
        		"custAddr.postal AS POSTAL,custAddr.state AS STATE,custAddr.address4 AS ADDRESS4," +
        		"cust.name AS CUSTOMERS_NAME,cejl.lineNumber AS LINE_NUMBER,co.name as NAME," +
        		"bu.countryCode AS BUSINESS_UNITS_COUNTRY_CODE," +
        		"cejob.quoteId AS QUOTE_ID,cejc.paymentTerms AS PYMNT_TERMS_DESC,bran.description AS BRANCH_DESCRIPTION," +
        		" supplAddr.address1 AS SHIP_ADDRESS1, " +
        		" supplAddr.address2 AS SHIP_ADDRESS2," +
        		" supplAddr.address3 AS SHIP_ADDRESS3," +
        		" supplAddr.city AS SHIP_CITY," +
        		" supplAddr.state AS SHIP_STATE," +
        		" supplAddr.postal AS SHIP_POSTAL," +
        		" supplAddr.county AS SHIP_NAME," +
        		"(suppCont.firstName||' '||suppCont.lastName) as SHIPING_CONTACT_NAME "+
        		" FROM CEInvoiceLineItem ceil left join ceil.invoice ce " +
        		"left join ce.jobContract cejc " +
        		"left join cejc.jobOrder cejob " +
        		"left join ceil.cEJobOrderLineItem cejl " +
        		"left join cejc.billingAddress custAddr " +
        		"left join cejc.supplierAddress supplAddr "+
        		"left join cejc.supplierContact suppCont "+
        		"left join cejc.customer cust " +
        		"left join cejob.bu bu " +
        		"left join bu.country co "+
        		"left join cejob.branch bran " +
        		"WHERE " +
        		"ce.invoiceNumber = ?";  
        
        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(params.get("Invoice"));
        List<Object[]> result = ReportUtil.hibernateJasperDataSourceHelperByHql(hql, paramList, typeMap, fieldNames);
        if (result != null && !result.isEmpty()) {
        	 List <Object[]> finalResult=  DataSourceHelper.getAppliedDeposit(params,result,fieldNames);
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), finalResult, this, params);
           }
        else {
             return null;
        }
    }

   
    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_10")){
                    String buAddr1 = (String) source.getFieldValue("ADDRESS1");
                    String buAddr2 = (String) source.getFieldValue("ADDRESS2");
                    String buAddr3 = (String) source.getFieldValue("ADDRESS3");
                    String buCity = (String) source.getFieldValue("CITY");
                    String buState = (String) source.getFieldValue("STATE");
                    String conslInvBuName = (String) source.getFieldValue("BU_NAME");
                    String buPostal = (String) source.getFieldValue("POSTAL");
                    String name = (String) source.getFieldValue("NAME");

                    StringBuilder sb = new StringBuilder();
                    if (buAddr1 != null && buAddr1.trim().length() > 0) {
                        sb.append(buAddr1.trim()).append(", ");
                    }
                    if (buAddr2 != null && buAddr2.trim().length() > 0) {
                        sb.append(buAddr2.trim()).append(", ");
                    }
                    if (buAddr3 != null && buAddr3.trim().length() > 0) {
                        sb.append(buAddr3.trim()).append(", ");
                    }
                    if (buCity != null && buCity.trim().length() > 0) {
                        sb.append(buCity.trim()).append(", ");
                    }
                    if (buState != null && buState.trim().length() > 0 && !"KOR01".equalsIgnoreCase(conslInvBuName)) {
                        sb.append(buState.trim()).append(", ");
                    }
                    if (buPostal != null && buPostal.trim().length() > 0) {
                        sb.append(buPostal.trim()).append(" ");
                    }
                    if (name != null && name.trim().length() > 0 && !name.equals(buCity)) {
                        sb.append(name.trim()).append(".");
                    }
                    return sb.toString();
        }
        if(fieldName.equals("SHIPPING_ADDRESS")){
            String buAddr1 = (String) source.getFieldValue("SHIP_ADDRESS1");
            String buAddr2 = (String) source.getFieldValue("SHIP_ADDRESS2");
            String buAddr3 = (String) source.getFieldValue("SHIP_ADDRESS3");
            String buCity = (String) source.getFieldValue("SHIP_CITY");
            String buState = (String) source.getFieldValue("SHIP_STATE");
            String conslInvBuName = (String) source.getFieldValue("BU_NAME");
            String buPostal = (String) source.getFieldValue("SHIP_POSTAL");
            String name = (String) source.getFieldValue("SHIP_NAME");

            StringBuilder sb = new StringBuilder();
            if (buAddr1 != null && buAddr1.trim().length() > 0) {
                sb.append(buAddr1.trim()).append(", ");
            }
            if (buAddr2 != null && buAddr2.trim().length() > 0) {
                sb.append(buAddr2.trim()).append(", ");
            }
            if (buAddr3 != null && buAddr3.trim().length() > 0) {
                sb.append(buAddr3.trim()).append(", ");
            }
            if (buCity != null && buCity.trim().length() > 0) {
                sb.append(buCity.trim()).append(", ");
            }
            if (buState != null && buState.trim().length() > 0 && !"KOR01".equalsIgnoreCase(conslInvBuName)) {
                sb.append(buState.trim()).append(", ");
            }
            if (buPostal != null && buPostal.trim().length() > 0) {
                sb.append(buPostal.trim()).append(" ");
            }
            if (name != null && name.trim().length() > 0 && !name.equals(buCity)) {
                sb.append(name.trim()).append(".");
            }
            return sb.toString();
        }
        if(fieldName.equals("BRANCH_NAME_AND_DESCRIPTION")){
            String br_desc = (String) source.getFieldValue("BRANCH_DESCRIPTION");
            String br_name = (String) source.getFieldValue("BRANCH_NAME");
            StringBuilder sbran = new StringBuilder();
            if (br_name != null && br_name.trim().length() > 0) {
                sbran.append(br_name.trim()).append(", ");
            }
            if (br_desc != null && br_desc.trim().length() > 0) {
                sbran.append(br_desc.trim());
            }
            return sbran.toString();
        }
        return source.getCalculatedField(fieldName);
    }
    
    @Override
    // TODO need to review
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        String[] fields = {"NET_PRICE"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("tally2", new BigDecimal(0));
        amds.addCalculatedField("groupSumVar", new BigDecimal(0));

        double[] result = ReportUtil.summariseFields(new String[]{"NET_PRICE"}, amds);
        amds.addCalculatedField("total", new BigDecimal(String.valueOf(result[0])));
        amds.addCalculatedField("UID20","");
        amds.addCalculatedField("CREDIT_IND","");
        amds.addCalculatedField("VESSEL_SORT_NUM",new BigDecimal(-9999));
        amds.addCalculatedField("LOT_SORT_NUM",new BigDecimal(-9999));
        amds.addCalculatedField("COMMENTS","");
        amds.addCalculatedField("CHARGE_SORT_NUM",new BigDecimal(0));
        amds.addCalculatedField("ROUNDING_DIGITS",new BigDecimal(0));
        amds.addCalculatedField("ALLOC_PCT",new BigDecimal(0));
        amds.addCalculatedField("PO_AUTH_TEXT",ReportUtil.getPoAuth(params.get("Invoice").toString()));
     }

    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        return getDataSource(reportName,params);
    }    
}
