/**
 * 
 */
package com.intertek.report.dataSource;

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

/**
 * 
 * 
 */
public class CEInvoiceNontaxFooterDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceNontaxFooterDataSource.class);

    private static final String[] fieldNames = {
        "JOB_NUMBER",
        "INVOICE",
        //"UID20",
        "CUSTOMERS_NAME",
        "BU_NAME",
        "BUSINESS_UNITS_DESCRIPTION",
        "BUSINESS_UNITS_COMPANY_DESC",
        "BUSINESS_UNITS_ADDRESS1",
        "BUSINESS_UNITS_CITY",
        "BUSINESS_UNITS_POSTAL",
        "BUSINESS_UNITS_ADDRESS2",
        "BUSINESS_UNITS_ADDRESS3",
        "BUSINESS_UNITS_ADDRESS4",
        "BUSINESS_UNITS_COUNTRY_CODE",
        "BUSINESS_UNITS_STATE_CODE",
        "NAME",
        "BRANCHES_COMPANY_DESC"
    };
    
    private static final String[] fieldTypes = {};
    
    public CEInvoiceNontaxFooterDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
    	
    	  String invoice =(params.get("InvType")!=null && params.get("InvType").equals("deposit"))?"DepositInvoice":"CEInvoice";
    	  String jobContractId=(params.get("InvType")!=null && params.get("InvType").equals("deposit"))?"inv.jobContractId":"inv.jobOrderContractId";
    	  
          String hql=  "select"
                     + "     jc.jobOrderNumber as JOB_NUMBER,"
                     + "     inv.invoiceNumber as INVOICE,"
                     + "     cu.name as CUSTOMERS_NAME,"
                     +"      bu.name as BU_NAME,"
                     + "     bu.description as BUSINESS_UNITS_DESCRIPTION,"
                     + "     bu.companyDesc as BUSINESS_UNITS_COMPANY_DESC,"
                     + "     bu.address1 as BUSINESS_UNITS_ADDRESS1,"
                     + "     bu.city as BUSINESS_UNITS_CITY,"
                     + "     bu.postal as BUSINESS_UNITS_POSTAL,"
                     + "     bu.address2 as BUSINESS_UNITS_ADDRESS2,"
                     + "     bu.address3 as BUSINESS_UNITS_ADDRESS3,"
                     + "     bu.address4 as BUSINESS_UNITS_ADDRESS4,"
                     + "     bu.countryCode as BUSINESS_UNITS_COUNTRY_CODE,"
                     + "     bu.stateCode as BUSINESS_UNITS_STATE_CODE,"
                     + "     c.name as NAME,"
                     + "     br.companyDesc as BRANCHES_COMPANY_DESC"
                     + "     FROM CEJobOrder jo,CEJobContract jc,"+invoice+" inv,BusinessUnit bu,Branch br,Country c,Customer cu"
                     + "     WHERE jo.jobNumber=jc.jobOrderNumber"
                     + "     AND jc.id="+jobContractId
                     + "     AND jo.buName = bu.name"
                     + "     AND bu.countryCode=c.countryCode"
                     + "     AND jo.branchName = br.name" 
                     + "     AND jc.customerCode=cu.custCode"
                     + "     AND inv.invoiceNumber = ?";
        log.debug(hql);
        //SearchService searchService = (SearchService) ServiceLocator.getInstance().getBean("searchService");
        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(params.get("Invoice"));    
        List<Object[]> result = ReportUtil.hibernateJasperDataSourceHelperByHql(hql.toString(), paramList,typeMap,fieldNames);
/*        Pair<?, ?>[] parameters = {
                                   new Pair<String, Object>("JOB_NUMBER", params.get("Job_Number")),
                                   new Pair<String, Object>("UID20", params.get("uid20")),
                                   new Pair<String, Object>("INVOICE", params.get("Invoice"))
                               };  */      
        if(result != null){
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), result, this, params);
        }
        else
        {
            return null;
        }
     /*   return ReportUtil.hibernateJasperDataSourceHelper(
                                                          reportName, hql, parameters, fieldNames, typeMap, this, params);*/
    }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
        return getDataSource(reportName,params);
    }

    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_1")){
            String buaddr1 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS1");
            String buaddr2 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS2");
            String buaddr3 = (String)source.getFieldValue("BUSINESS_UNITS_ADDRESS3");
            String bucity = (String)source.getFieldValue("BUSINESS_UNITS_CITY");
            String bustate = (String)source.getFieldValue("BUSINESS_UNITS_STATE_CODE");
            String bupostal = (String)source.getFieldValue("BUSINESS_UNITS_POSTAL");
            String buname = (String)source.getFieldValue("BU_NAME");
            String name = (String)source.getFieldValue("NAME");
            
            StringBuilder sb = new StringBuilder();
            if(buaddr1 != null && !buaddr1.trim().isEmpty()){
                sb.append(buaddr1).append(", ");
            }
            if(buaddr2 != null && !buaddr2.trim().isEmpty()){
                sb.append(buaddr2).append(", ");
            }
            if(buaddr3 != null && !buaddr3.trim().isEmpty()){
                sb.append(buaddr3).append(", ");
            }
            if(bucity != null && !bucity.trim().isEmpty()){
                sb.append(bucity).append(", ");
            }
            if(bustate != null && !bustate.trim().isEmpty() && !buname.equalsIgnoreCase("KOR01")){
                sb.append(bustate).append(", ");
            }
            if(bupostal != null && !bupostal.trim().isEmpty()){
                sb.append(bupostal).append(" ");
            }
            if(name != null && !name.trim().isEmpty() && !name.equals(bucity)){
                sb.append(name).append(".");
            }
            return sb.toString();
        }
        return source.getCalculatedField(fieldName);
    }
    @Override
    public void dataLoaded(ArrayMapDataSource datasource, Map<String, Object> params) {
        datasource.addCalculatedField("UID20", "");
    }
}
