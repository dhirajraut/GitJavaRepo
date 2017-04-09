/**
 * 
 */
package com.intertek.report.dataSource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

/**
 * 
 * @author richard.qin
 */
public class DataSourceHelper {

    static public Object calculateInvoiceReportTitle(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        
        String key = null;
        String country = (String)source.getFieldValue("sellers_country") + "";
        String credit =source.getFieldValue("CREDIT_IND") ==null?"":source.getFieldValue("CREDIT_IND").toString(); 
        BigDecimal vat = (BigDecimal)source.getCalculatedField("VAT_subtotal");
        String di=source.getFieldValue("DEPOSIT_INVOICE") ==null?"":source.getFieldValue("DEPOSIT_INVOICE").toString();
        
        if( country.equals("ZAF") ||
            country.equals("SGP") ||
            country.equals("TZA") ||
            country.equals("MOZ") ||
            country.equals("KEN") )
        {
            if(credit.equals("C") || credit.equals("Y")||credit.equalsIgnoreCase("CREDITED")){
                key = "credit_tax_invoice";
            }
            else{
                key = "tax_invoice";
            }
        }
        else {
            if(credit.equals("C") || credit.equals("Y")||credit.equalsIgnoreCase("CREDITED")){
        
                key = "credit_invoice";
            }
            else {
                if(country.equals("AUS") || country.equals("NZL")){
                    if(vat != null && vat.doubleValue() > 0){
                        key = "tax_invoice";
                    }
                    else{
                        key = "gst_free_invoice";
                    }
                }
                else{
                    key = "invoice";
                }
            }
        }
        if(di.equalsIgnoreCase("DEPOSITINVOICE"))
        {
            key="depositInvoice";
        }
        // return the resource string of the key value
        ResourceBundle res = ResourceBundle.getBundle("jasperRes");
        if(res != null){
            return res.getString(key);
        }

        return null;
    }
    
    static public Object calculateInvoiceBankDetail(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        String bucc = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
        if(bucc != null && bucc.equals("BEL")){
            return source.getFieldValue("BUSINESS_UNITS_COUNTRY2_CODE") + " " 
                 + source.getFieldValue("IBAN_CHECK_DIGIT") + " " 
                 + source.getFieldValue("BANKS_BANK_ID_NBR") + " "
                 + source.getFieldValue("BANK_ACCONT_NUM") + " " 
                 + source.getFieldValue("CHECK_DIGIT");
        }
        else if(bucc != null && bucc.equals("NLD")){
            return source.getFieldValue("BUSINESS_UNITS_COUNTRY2_CODE") + " " 
                  + source.getFieldValue("IBAN_CHECK_DIGIT") + " " 
                  + ((String)source.getFieldValue("DFI_ID_NUM")).substring( 0, 4 ) + " "
                  + source.getFieldValue("BANK_ACCONT_NUM") + " " 
                  + source.getFieldValue("CHECK_DIGIT");
        }
        else{
            return "";
        }
    }
    
    static public Object calculateInvoiceBankDetail2(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        String bucc = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
        if(bucc != null){
            if(bucc.equals("AUS") || bucc.equals("NZL")){
                return source.getFieldValue("BANKS_BANK_ID_NBR") + "-" + source.getFieldValue("BANK_BRANCH_ID");
            }
            else if(bucc.equals("MOZ")){
                String cur = (String)source.getFieldValue("TRANSACT_CURRENCY_CD");
                if(cur.equals("USD")){
                    return source.getFieldValue("BANK_ACCONT_NUM");
                }
                else{
                    return (String)source.getFieldValue("BANKS_BANK_ID_NBR") + source.getFieldValue("BANK_ACCONT_NUM");
                }
            }
            else if("KOR01".equals(source.getFieldValue("BU_NAME")) && (source.getFieldValue("BANK_ACCT_DESC")+"").indexOf("USD")>=0){
                return (String)source.getFieldValue("BUSINESS_UNITS_DESCRIPTION");
            }
            else{
                return (String)source.getFieldValue("BANKS_BANK_ID_NBR");
            }
        }
        else{
            return "";
        }
    }
    
    static public Object calculateInvoiceBankDetail3(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        String bucc = (String)source.getFieldValue("BUSINESS_UNITS_COUNTRY_CODE");
        if(bucc != null && bucc.equals("BEL")){
            return source.getFieldValue("BANKS_BANK_ID_NBR") + "-"
                 + source.getFieldValue("BANK_ACCONT_NUM") + "-" 
                 + source.getFieldValue("CHECK_DIGIT");
        }
        else if(bucc == null || !bucc.equals("MOZ")){
            return source.getFieldValue("BANK_ACCONT_NUM");
        }
        else{
            return "";
        }
    }
    //START: 130869
    static String nullCheckReturnEmptyStr(Object objParam){
    	String strRtn = "";
    	if(null != objParam){
    		strRtn = String.valueOf(objParam).trim();
    	}
    	return strRtn;
    }
   //END: 130869
    
//This method is commonly used for three reports:CEInvoice,CEinvoice Tax,CeInvoice preview
	public static   List <Object[]> getAppliedDeposit(Map<String, Object> params,List<Object[]> result, String[] fieldNames) {
	    
	    String hql = "select il.id ,da.amount,di.description from DepositInvoiceAmount da,CEInvoiceLineItem il,CEInvoice inv,DepositInvoice di "
				+ "where da.invoiceLineItemId=il.id and il.invoiceId=inv.id and di.id=da.depositInvoiceId and inv.invoiceNumber=?";
		ArrayList<Object> paramList = new ArrayList<Object>();
		// param list from master data source Job_Number", "Invoice"
		paramList.add(params.get("Invoice"));
		List<Object[]> resultTemp = ReportUtil.hibernateJasperDataSourceHelperByHql(hql, paramList,new HashMap(), null);
		List<Object[]> finalResult = new ArrayList<Object[]>();
		if (resultTemp != null && !resultTemp.isEmpty()) {

			for (int k = 0; k < result.size(); k++) {
				Object[] objArr = result.get(k);
				int index = -1;
				for (int i = 0; i < objArr.length; i++) {
					if (fieldNames[i].equals("LINE_ITEM_ID")) {
						index = i;
						break;
					}
				}
				finalResult.add(objArr);
				for (Object[] ObjArrTempResult : resultTemp) {

					if (ObjArrTempResult[0].equals(objArr[index])) {
						Object[] tempArr = new Object[objArr.length];
						for (int i = 0; i < objArr.length; i++) {
							if (fieldNames[i].equals("UNIT_PRICE")) {
								tempArr[i] = new BigDecimal("-"
										+ ObjArrTempResult[1]);
							} else if (fieldNames[i].equals("LINE_DESCRIPTION")) {
								tempArr[i] = ObjArrTempResult[2];
							} else if (fieldNames[i].equals("NET_PRICE")) {
								tempArr[i] = new BigDecimal("-"
										+ ObjArrTempResult[1]);
							} else {
								tempArr[i] = objArr[i];
							}
						}
						finalResult.add(tempArr);
						break;
					}
				}
				
			}

		} else {
			finalResult.addAll(result);
		}
		return finalResult;
	}
    
    
}
