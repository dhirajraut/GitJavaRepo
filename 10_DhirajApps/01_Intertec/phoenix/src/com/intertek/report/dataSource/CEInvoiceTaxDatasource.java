package com.intertek.report.dataSource;

import static com.intertek.report.ReportConstants.REPORT_INVOICE_TAX_VATLABEL;

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

public class CEInvoiceTaxDatasource extends AbstractJasperDataSource {
    private static final String[] fieldNames = {
    	"LINE_ITEM_ID",
        "JOB_NUMBER",
        "INVOICE",
        "CREDIT_IND",
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
        "SHIP_TO_COUNTRY",
        "SHIP_TO_STATE",
        "INVOICE_LINE_ID",
        "LINE_DESCRIPTION",
        "SPLIT_PCT",
        "DISCOUNT_PCT",
        "UNIT_PRICE",
        "TAX_CODE",
        "TAX_PCT",
        "TAX_AMT",
        "VAT_CODE",
        "VAT_PCT",
        "VAT_AMT",
        "NET_PRICE",
        "TAX_ARTICLE_CODE",
        "ACCOUNT",
        "PRODUCT_GROUP",
        "DEPTID",
        "PRIMARY_BRANCH_CD",
        "INVOICE_DESCR",
        "JOB_DESCRIPTION",
        "VAT_LABEL",
        "SALES_TAX_LABEL",
        "VAT_REG_LABEL",
        "VAT_REGISTRATION_ID",
        "TRANSACT_CURRENCY_CD",
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
        "BANK_ACCT_DESC",
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
        "INV_AMT_POST_TAX",
        "DEPOSIT_SUBTOTAL"
        
    };
    
    private static final String[] fieldTypes = {
        "INVOICE_LINE_ID",  "BigDecimal", 
        "SPLIT_PCT",        "BigDecimal", 
        "DISCOUNT_PCT",     "BigDecimal", 
        "UNIT_PRICE",       "BigDecimal", 
        "TAX_PCT",          "BigDecimal", 
        "TAX_AMT",          "BigDecimal", 
        "VAT_PCT",          "BigDecimal", 
        "VAT_AMT",          "BigDecimal", 
        "NET_PRICE",        "BigDecimal", 
        "DECIMAL_DIGITS",   "BigDecimal", 
        "INV_AMT_PRE_TAX",  "BigDecimal", 
        "INV_AMT_POST_TAX", "BigDecimal",
        "DEPOSIT_SUBTOTAL", "BigDecimal"
    };
    
    public CEInvoiceTaxDatasource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {

        String hql = "select il.id as  LINE_ITEM_ID,"
                     + "     jc.jobOrderNumber as JOB_NUMBER,"
                     + "     ce.invoiceNumber as INVOICE,"
                     + "     ce.status as CREDIT_IND,"
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
                     + "     c.name as NAME,"
                     + "     c.country2 as BUSINESS_UNITS_COUNTRY2_CODE," 
                     + "     supAdd.country as SHIP_TO_COUNTRY," 
                     + "     supAdd.state as SHIP_TO_STATE," 
                     + "     joli.lineNumber as INVOICE_LINE_ID,"                                         
                     + "     joli.description as LINE_DESCRIPTION,"
                     + "     joli.splitPct as SPLIT_PCT,"
                     + "     joli.discountPct as DISCOUNT_PCT,"
                     + "     il.unitPrice as UNIT_PRICE,"
                     + "     il.taxCode as TAX_CODE,"
                     + "     il.taxPct as TAX_PCT,"
                     + "     il.taxAmt as TAX_AMT,"
                     + "     il.vatCode as VAT_CODE,"
                     + "     il.vatPct as VAT_PCT,"
                     + "     il.vatAmt as VAT_AMT,"
                     + "     il.netPrice as NET_PRICE,"
                     + "     il.taxArticleCode as TAX_ARTICLE_CODE,"
                     + "     joli.account as ACCOUNT,"
                     + "     joli.productGroup as PRODUCT_GROUP,"
                     + "     joli.deptid as DEPTID,"
                     + "     joli.primaryBranchCd as PRIMARY_BRANCH_CD,"
                     + "     jc.invoiceDescription as INVOICE_DESCR,"
                     + "     jo.description as JOB_DESCRIPTION,"
                     + "     ce.vatLabel as VAT_LABEL,"
                     + "     ce.salesTaxLabel as SALES_TAX_LABEL," 
                     + "     ce.vatRegLabel as VAT_REG_LABEL,"
                     + "     ce.vatRegId as VAT_REGISTRATION_ID,"
                     + "     ce.transactionCurrencyCd as TRANSACT_CURRENCY_CD,"
                     + "     ba.bankAcctDesc as BANK_ACCT_DESC,"
                     + "     ba.dfIdNum as DFI_ID_NUM,"
                     + "     b.bankCode as BANK_CODE,"
                     + "     ba.bankAccountId.bankAcctCode as BANK_ACCT_CODE,"
                     + "     b.bankDesc as  BANKS_BANK_DESC,"
                     + "     b.address1 as BANKS_ADDRESS1,"
                     + "     b.address2 as BANKS_ADDRESS2,"
                     + "     b.address3 as BANKS_ADDRESS3,"
                     + "     b.city   as BANKS_CITY,"
                     + "     b.stateCode as BANKS_STATE_CODE,"
                     + "     b.postal as BANKS_POSTAL,"
                     + "     b.countryCode as BANKS_COUNTRY_CODE,"
                     + "     b.bankIdNumber as BANKS_BANK_ID_NBR,"
                     + "    (select bal.bankAcctDesc from CEJobContract jc,"
                     + "       CEJobOrder jo,CEInvoice ce,BankAccountLanguage bal where"
                     + "            jc.jobOrderNumber = jo.jobNumber"
                     + "           and jo.buName = bal.bankAccountLangId.businessUnitName "
                     + "           and jc.remitToBankCode = bal.bankAccount.bankAccountId.bankCode "
                     + "           and bal.bankAccountLangId.languageCD = 'KOR'"
                     + "           and jc.id=ce.jobOrderContractId"
                     + "           and ce.invoiceNumber = ?) as BANK_LANG_DESC,"
                     + "     ba.bankAcctDesc as BANK_ACCT_DESC,"
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
                     + "    (select bul.companyDesc from CEJobContract jc,CEInvoice ce, "
                     + "        CEJobOrder jo,BusinessUnitLanguage bul"
                     + "        where jc.id = ce.jobOrderContractId"
                     + "        AND jc.jobOrderNumber = jo.jobNumber"
                     + "        AND jo.bu = bul.businessUnit"
                     + "        AND bul.businessUnitLanguageId.languageCD = 'KOR'"
                     + "        AND ce.invoiceNumber = ?) as COMPANY_LANG_DESC,"                   
                     + "       br.logoName as LOGO_NAME,"
                     + "      (select co.countryCode from Country co, CEJobContract jc ,CEJobOrder jo, CEInvoice ce, BusinessUnit bu"
                     + "       where jc.jobOrderNumber=jo.jobNumber and jo.buName=bu.name and bu.countryCode=co.countryCode"
                     + "       and jc.id = ce.jobOrderContractId " 
                     + "       and  ce.invoiceNumber = ?) as sellers_country,"
                     + "     (select distinct cur.decimalDigits from Currency cur,CEJobContract cejc ,CEInvoice inv" 
                     + "     where cejc.transactionCurrency=cur.currencyCd and jc.id = inv.jobOrderContractId and inv.invoiceNumber = ?) as DECIMAL_DIGITS, "
                     + "     ce.invAmtPreTax as INV_AMT_PRE_TAX,"
                     + "     ce.invAmtPostTax as INV_AMT_POST_TAX," 
                     + "     (select sum(da.amount) from DepositInvoiceAmount da,CEInvoiceLineItem il,CEInvoice inv"
                     + "	where da.invoiceLineItemId=il.id and il.invoiceId=inv.id and  inv.invoiceNumber=?) as DEPOSIT_SUBTOTAL " 
                     + "     from CEInvoiceLineItem il left join il.invoice ce left join ce.jobContract jc"
                     + "     left join jc.customer cu  left join jc.supplierAddress supAdd left join jc.jobOrder jo "
                     + "     left join jo.bu bu left join bu.country c left join jc.remitToBankAccount ba"
                     + "     left join jc.remitTo b  left join jo.branch br"
                     + "     left join  il.cEJobOrderLineItem joli where ce.invoiceNumber = ?"   ;     

        //SearchService searchService = (SearchService) ServiceLocator.getInstance().getBean("searchService");
        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Job_Number"));
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Invoice"));
        paramList.add(params.get("Invoice"));
       
        //List<Object[]> result =searchService.searchByHql(hql,CEInvoice.class,paramList);
        List<Object[]> result =ReportUtil.hibernateJasperDataSourceHelperByHql(hql,paramList,typeMap,fieldNames );
        if(result != null && !result.isEmpty()){
        	 List <Object[]> finalResult=  DataSourceHelper.getAppliedDeposit(params,result,fieldNames);
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), finalResult, this, params);
        }
        else
        {
            return null;
        }
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
      
        return getDataSource(reportName,params);
    }

    /**
     * The following calculation appeared in two different reports, so the logic is moved to
     * a common class
     */
    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_22")){
            return DataSourceHelper.calculateInvoiceReportTitle(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_39")){
            return DataSourceHelper.calculateInvoiceBankDetail(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_35")){
            return DataSourceHelper.calculateInvoiceBankDetail2(fieldName, params, source);
        }
        else if(fieldName.equals("TEXT_37")){
            return DataSourceHelper.calculateInvoiceBankDetail3(fieldName, params, source);
        }
        else if(fieldName.equals("VAT_LABEL") 
            || fieldName.equals("SALES_TAX_LABEL") 
            || fieldName.equals("VAT_REG_LABEL")){
            /*
             * The TAX_country_code is defined as the BU’s Country Code.
             * The TAX_state_code is defined as: If the BU’s COUNTRY_CODE 
             * is equal to “CAN” and the Ship To Country Code is equal to the BU’s COUNTRY_CODE then
             * If true: TAX_state_code will equal Ship To State Code
             * Else if false: TAX_state_code will be left blank.
             * The TAX_country_code & the TAX_state_code are used 
             * in the invoice_tax_vatlabelcalc to query the PHOENIX.TAX_LABELS table 
             * where COUNTRY_CODE = TAX_country_code and STATE = TAX_state_code
             */
            String buCountryCode = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
            String stateCode = null;
            if( buCountryCode.equals( "CAN" ) && buCountryCode.equals( source.getFieldValue("SHIP_TO_COUNTRY"))){
                stateCode = (String)source.getFieldValue("SHIP_TO_STATE");
            }
            String[] keyValues = new String[]{
                buCountryCode,
                stateCode,
            };
            return ReportUtil.getReferenceFieldValue(REPORT_INVOICE_TAX_VATLABEL, keyValues, fieldName);
        }
        return source.getCalculatedField(fieldName);
    }
    @Override
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params){
        // subTotal: sum of NET_PRICE
        // VAT_subtotal: sum of VAT_AMT
        // sales_tax_subtotal: sum of TAX_AMT
        // Sum_Tax_Pct: sum of TAX_PCT
        String[] fields = {"NET_PRICE", "VAT_AMT", "TAX_AMT", "TAX_PCT"};
        double[] results = ReportUtil.summariseFields(fields, amds);
        amds.addCalculatedField("subtotal", new BigDecimal(String.valueOf(results[0])));
        amds.addCalculatedField("VAT_subtotal", new BigDecimal(String.valueOf(results[1])));
        amds.addCalculatedField("sales_tax_subtotal", new BigDecimal(String.valueOf(results[2])));
        amds.addCalculatedField("sum_tax_pct", new BigDecimal(String.valueOf(results[3])));
        amds.addCalculatedField("UID20","");
        amds.addCalculatedField("VESSEL_SORT_NUM",new BigDecimal(-9999));
        amds.addCalculatedField("LOT_SORT_NUM",new BigDecimal(-9999));
        amds.addCalculatedField("COMMENTS","");
        amds.addCalculatedField("CHARGE_SORT_NUM",new BigDecimal(0));
        amds.addCalculatedField("FED_ID_NUM","13-0668365");
        amds.addCalculatedField("HAS_INV_LINE_SPACE", new Boolean(true));
    }
}
