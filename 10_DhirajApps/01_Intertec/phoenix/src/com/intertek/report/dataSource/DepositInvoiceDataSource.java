package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

public class DepositInvoiceDataSource extends AbstractJasperDataSource{
    private static final Logger log = Logger.getLogger(InvoiceDataSource.class);
    private static final String[] fieldNames = { "JOB_NUMBER",
                                                 "INVOICE",
                                                 "CUSTOMERS_NAME",
                                                 "BUSINESS_UNITS_DESCRIPTION",
                                                 "BUSINESS_UNITS_COMPANY_DESC",
                                                 "BUSINESS_UNITS_ADDRESS1",
                                                 "BUSINESS_UNITS_CITY",
                                                 "BUSINESS_UNITS_POSTAL",
                                                 "BUSINESS_UNITS_PHONE_PREFIX",
                                                 "BUSINESS_UNITS_PHONE_NUMBER",
                                                 "BUSINESS_UNITS_PHONE_EXTENSION",
                                                 "BUSINESS_UNITS_ADDRESS2",
                                                 "BUSINESS_UNITS_ADDRESS3",
                                                 "BUSINESS_UNITS_ADDRESS4",
                                                 "BUSINESS_UNITS_COUNTRY_CODE",
                                                 "BUSINESS_UNITS_STATE_CODE",
                                                 "NAME",
                                                 "BUSINESS_UNITS_COUNTRY2_CODE",
                                                 "LINE_DESCRIPTION",
//                                                 "SPLIT_PCT",
//                                                 "DISCOUNT_PCT",
                                                 "NET_PRICE",
                                                 "UNIT_PRICE",
                                                 "ACCOUNT",
//                                                 "PRODUCT_GROUP",
                                                 "DEPTID",
//                                                 "PRIMARY_BRANCH_CD",
                                                 "INVOICE_DESCR",
                                                 "TRANSACT_CURRENCY_CD",
                                                 "JOB_DESCRIPTION",
                                                 "BANK_ACCT_DESC",
                                                 "DFI_ID_NUM",
                                                 "BANK_CODE",
                                                 "BANK_ACCT_CODE",
                                                 "BANKS_BANK_DESC",
                                                 "BANKS_ADDRESS1",
                                                 "BANKS_ADDRESS2",
                                                 "BANKS_ADDRESS3",
                                                 "BANKS_CITY",
                                                 "BANKS_STATE_CODE",
                                                 "BANKS_POSTAL",
                                                 "BANKS_COUNTRY_CODE",
                                                 "BANKS_BANK_ID_NBR",
                                                 "BANK_LANG_DESC",   
                                                 "BANK_ACCT_ADDRESS1",
                                                 "BANK_ACCT_ADDRESS2",
                                                 "BANK_ACCT_ADDRESS3",
                                                 "BANK_ACCT_ADDRESS4",
                                                 "BANK_ACCT_CITY",
                                                 "BANK_ACCT_STATE_CODE",
                                                 "BANK_ACCT_COUNTRY_CODE",
                                                 "BANK_ACCT_POSTAL",
                                                 "BANK_ACCONT_NUM",
                                                 "IBAN_CHECK_DIGIT",
                                                 "CHECK_DIGIT",
                                                 "DEPOSIT_TYPE",
                                                 "BANK_BRANCH_ID",
                                                 "BRANCHES_COMPANY_DESC",
                                                 "BRANCHES_PHONE_PREFIX",
                                                 "BRANCHES_PHONE_NUMBER",
                                                 "BRANCHES_PHONE_EXTENSION",
                                                 "TERMS_URL", 
                                                 "BU_NAME",
                                                 "COMPANY_LANG_DESC", 
                                                 "LOGO_NAME",
                                                 "sellers_country",
                                                 "DECIMAL_DIGITS",
                                                 "INV_AMT_PRE_TAX",
                                                 "INV_AMT_POST_TAX"
                                                 //"DEPOSIT_SUBTOTAL"
                                                 };

    private static final String[] fieldTypes = { "SPLIT_PCT", "BigDecimal", "DISCOUNT_PCT", "BigDecimal", "NET_PRICE", "BigDecimal", "UNIT_PRICE",
                                                "BigDecimal", "VESSEL_SORT_NUM", "BigDecimal", "LOT_SORT_NUM", "BigDecimal", "CHARGE_SORT_NUM", "BigDecimal",
                                                "DECIMAL_DIGITS", "BigDecimal", "INV_AMT_PRE_TAX", "BigDecimal", "INV_AMT_POST_TAX", "BigDecimal"
                                                //"DEPOSIT_SUBTOTAL","BigDecimal"
                                                };

    
   public DepositInvoiceDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String,
     *      java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
        String hql = "select"
                     + "     jc.jobOrderNumber as JOB_NUMBER,"
                     + "     di.invoiceNumber as INVOICE,"
                     + "     cu.name as CUSTOMERS_NAME,"
                     + "     bu.description as BUSINESS_UNITS_DESCRIPTION,"
                     + "     bu.companyDesc as BUSINESS_UNITS_COMPANY_DESC,"
                     + "     bu.address1 as BUSINESS_UNITS_ADDRESS1,"
                     + "     bu.city as BUSINESS_UNITS_CITY,"
                     + "     bu.postal as BUSINESS_UNITS_POSTAL,"
                     + "     bu.phonePrefix as BUSINESS_UNITS_PHONE_PREFIX,"
                     + "     bu.phoneNumber as BUSINESS_UNITS_PHONE_NUMBER,"
                     + "     bu.phoneExtension as BUSINESS_UNITS_PHONE_EXTENSION,"
                     + "     bu.address2 as BUSINESS_UNITS_ADDRESS2,"
                     + "     bu.address3 as BUSINESS_UNITS_ADDRESS3,"
                     + "     bu.address4 as BUSINESS_UNITS_ADDRESS4,"
                     + "     bu.countryCode as BUSINESS_UNITS_COUNTRY_CODE,"
                     + "     bu.stateCode as BUSINESS_UNITS_STATE_CODE,"
                     + "     c.name as NAME ,"
                     + "     c.country2 AS BUSINESS_UNITS_COUNTRY2_CODE,"
                     + "     di.description as LINE_DESCRIPTION,"
                     + "     di.depositAmount as NET_PRICE,"
                     + "     di.depositAmount as UNIT_PRICE,"
                     + "     di.account as ACCOUNT,"
                     + "     di.deptId as DEPTID,"
                     + "     di.description as INVOICE_DESCR,"
                     + "     jc.transactionCurrency as TRANSACT_CURRENCY_CD,"
                     + "     jo.description as JOB_DESCRIPTION,"
                     + "     ba.bankAcctDesc as BANK_ACCT_DESC,"
                     + "     ba.dfIdNum as DFI_ID_NUM,"
                     + "     b.bankCode as BANK_CODE,"
                     + "     ba.bankAccountId as BANK_ACCT_CODE,"
                     + "     b.bankDesc as  BANKS_BANK_DESC,"
                     + "     b.address1 as BANKS_ADDRESS1,"
                     + "     b.address2 as BANKS_ADDRESS2,"
                     + "     b.address3 as BANKS_ADDRESS3,"
                     + "     b.city as BANKS_CITY,"
                     + "     b.stateCode as BANKS_STATE_CODE,"
                     + "     b.postal as BANKS_POSTAL,"
                     + "     b.countryCode as BANKS_COUNTRY_CODE,"
                     + "     b.bankIdNumber as BANKS_BANK_ID_NBR,"
                     + "     (select bal.bankAcctDesc from CEJobContract jc,"
                     + "       CEJobOrder jo,DepositInvoice di,BankAccountLanguage bal where"
                     + "            jc.jobOrderNumber = jo.jobNumber"
                     + "           AND jo.buName = bal.bankAccountLangId.businessUnitName "
                     + "           AND jc.remitToBankCode = bal.bankAccount.bankAccountId.bankCode "
                     + "           AND bal.bankAccountLangId.languageCD = 'KOR'"
                     + "           AND jc.id=di.jobContractId"
                     + "           AND di.invoiceNumber = ?) as BANK_LANG_DESC,"
                     + "     ba.address1 as BANK_ACCT_ADDRESS1,"
                     + "     ba.address2 as BANK_ACCT_ADDRESS2,"
                     + "     ba.address3 as BANK_ACCT_ADDRESS3,"
                     + "     ba.address4 as BANK_ACCT_ADDRESS4,"
                     + "     ba.city as BANK_ACCT_CITY,"
                     + "     ba.stateCode as BANK_ACCT_STATE_CODE,"
                     + "     ba.countryCode as BANK_ACCT_COUNTRY_CODE,"
                     + "     ba.postal as BANK_ACCT_POSTAL,"
                     + "     ba.bankAccountNum as BANK_ACCONT_NUM,"
                     + "     ba.ibanCheckDigit as IBAN_CHECK_DIGIT,"
                     + "     ba.checkDigit as CHECK_DIGIT,"
                     + "     ba.depositType as DEPOSIT_TYPE,"
                     + "     ba.bankBranchId as BANK_BRANCH_ID,"
                     + "     br.companyDesc as BRANCHES_COMPANY_DESC,"
                     + "     br.phonePrefix as BRANCHES_PHONE_PREFIX,"
                     + "     br.phoneNumber as BRANCHES_PHONE_NUMBER,"
                     + "     br.phoneExtension as BRANCHES_PHONE_EXTENSION,"
                     + "     (select ctu.termUrl from CountryTermUrl ctu, " +
                             "CEJobOrder jo ,BusinessUnit bu where jo.buName=bu.name and ctu.countryCode=bu.countryCode and jo.jobNumber=?) as TERMS_URL,"                    
                     + "     bu.name as BU_NAME,"
                     + "   (select bul.companyDesc from CEJobContract jc,DepositInvoice di, "
                             + "        CEJobOrder jo,BusinessUnitLanguage bul"
                             + "        where jc.id = di.jobContractId"
                             + "        AND jc.jobOrderNumber = jo.jobNumber"
                             + "        AND jo.bu = bul.businessUnit"
                             + "        AND bul.businessUnitLanguageId.languageCD = 'KOR'"
                             + "        AND di.invoiceNumber = ?) as COMPANY_LANG_DESC,"
                     + "     br.logoName as LOGO_NAME,"
                     + "      (select co.countryCode from Country co, CEJobContract jc ,CEJobOrder jo, DepositInvoice di, BusinessUnit bu"
                     + "       where jc.jobOrderNumber=jo.jobNumber and jo.buName=bu.name and bu.countryCode=co.countryCode"
                     + "       and jc.id = di.jobContractId " 
                     + "       and  di.invoiceNumber = ?) as sellers_country, "
                     + "     (select distinct cur.decimalDigits from Currency cur,CEJobContract cejc ,DepositInvoice di" 
                     + "     where cejc.transactionCurrency=cur.currencyCd and jc.id = di.jobContractId and di.invoiceNumber = ?) as DECIMAL_DIGITS, "
                     + "     di.depositAmount as INV_AMT_PRE_TAX," 
                     + "     di.depositAmount as INV_AMT_POST_TAX "
//                     + "     (select sum(di.depositAmount) as sumdp from DepositInvoice di,CEJobContract jc,CEJobOrder jo where di.jobContractId= jc.id and jo.jobNumber=jc.jobOrderNumber and jo.jobNumber=? ) as DEPOSIT_SUBTOTAL  "
                     + "     from DepositInvoice di left join di.jobContract jc"
                     + "     left join jc.customer cu left join jc.jobOrder jo "
                     + "     left join jo.bu bu left join bu.country c left join jc.remitToBankAccount ba"
                     + "     left join jc.remitTo b  left join jo.branch br"
                     + "     where di.invoiceNumber = ?"   ;     
        
        log.debug(hql);
        
        
        ArrayList<Object> paramList = new ArrayList<Object>();
      // param list from master data source Job_Number", "Invoice"
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Job_Number"));
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Invoice"));
       // paramList.add(params.get("Job_Number"));
        paramList.add(params.get("Invoice"));
       
       
        //List<Object[]> result =searchService.searchByHql(hql,CEInvoice.class,paramList);
        List<Object[]> result =ReportUtil.hibernateJasperDataSourceHelperByHql(hql,paramList,typeMap,fieldNames );
        if(result != null && !result.isEmpty()){
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), result, this, params);
        }
        else
        {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String,
     *      java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection conn) {
        return getDataSource(reportName,params);
    }

    @Override
    public Object calculateFieldValue(String fieldName, Map<String, Object> params, ArrayMapDataSource source) {
        if (fieldName.equals("TEXT_22")) {
            return DataSourceHelper.calculateInvoiceReportTitle(fieldName, params, source);
        }
        else if (fieldName.equals("TEXT_28")) {
            return DataSourceHelper.calculateInvoiceBankDetail(fieldName, params, source);
        }
        else if (fieldName.equals("TEXT_24")) {
            return DataSourceHelper.calculateInvoiceBankDetail2(fieldName, params, source);
        }
        else if (fieldName.equals("TEXT_26")) {
            return DataSourceHelper.calculateInvoiceBankDetail3(fieldName, params, source);
        }
        return source.getCalculatedField(fieldName);
    }
    

    @Override
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params) {
        // subTotal: sum of NET_PRICE
        // total: sum of NET_PRICE, but two for the same?
        String[] fields = { "NET_PRICE", "NET_PRICE" };
        double[] results = ReportUtil.summariseFields(fields, amds);

        amds.addCalculatedField("subTotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("total", new BigDecimal(String.valueOf(results[1])));
        amds.addCalculatedField("UID20","");
        amds.addCalculatedField("CREDIT_IND","");
        amds.addCalculatedField("VESSEL_SORT_NUM",new BigDecimal(-9999));
        amds.addCalculatedField("LOT_SORT_NUM",new BigDecimal(-9999));
        amds.addCalculatedField("COMMENTS","");
        amds.addCalculatedField("CHARGE_SORT_NUM",new BigDecimal(0));
        amds.addCalculatedField("FED_ID_NUM","13-0668365");
        amds.addCalculatedField("HAS_INV_LINE_SPACE", new Boolean(true));
        //Deposit Invoice
        amds.addCalculatedField("DEPOSIT_INVOICE",params.get("InvType")==null?"":"DEPOSITINVOICE");
        
    }
}
