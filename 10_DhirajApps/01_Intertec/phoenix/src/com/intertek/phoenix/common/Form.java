/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import com.intertek.entity.TimeZone;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.ReferenceDataService.DropdownName;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.util.DateUtil;
import com.intertek.service.TimeZoneService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

/**
 * 
 * @author Eric.Nguyen
 */
public class Form {
    protected ReferenceDataService refenceDataSerivce = ServiceManager.getReferenceDataService();

    private String jobNumber;
    private String userDateFormat;
    private DateFormat dateFormat;
    
    protected static TimeZone userTimeZone;
    protected static TimeZone phoenixTimeZone;
    
    public Form() {
        setUserDateFormat(getCurrentUserFormat());
        setUserTimeZone(getUserTimeZone());
        setPhoenixTimeZone( Constants.TIME_ZONE );
        
    }

    public void setUserDateFormat(String format) {
        userDateFormat = format;
        dateFormat = new SimpleDateFormat(userDateFormat);
    }

    public String getUserDateFormat() {
        return userDateFormat;
    }

    public String convertToLocalTime(Timestamp aDate) {
        if (aDate == null) {
            return "";
        }
        else {
            return dateFormat.format(DateUtil.getConvertedDateTime(aDate, phoenixTimeZone, userTimeZone));
        }
    }
    
    public Timestamp convertToPhoenixTime(Timestamp aDate){
        if (aDate == null) {
            return null;
        }
        else {
            return DateUtil.getConvertedDateTime(aDate, userTimeZone, phoenixTimeZone);
        }
    }

    public static String getCurrentUserFormat() {
        PhxUserService userService = ServiceManager.getUserService();
        User loginUser = null;
		try {
			loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
		} catch (DaoException e) {
			return Constants.DD_MM_YYYY_DATE_FORMAT;
		}
        String dateFormat = loginUser.getDateFormat();

        if (dateFormat != null && !dateFormat.trim().equals("")) {
            String[] pattern = dateFormat.split("/");
            String month = pattern[1];
            if (pattern[0].trim().equalsIgnoreCase("mm"))
                dateFormat = Constants.MM_DD_YYYY_DATE_FORMAT;
            if (month.trim().equalsIgnoreCase("mmm"))
                dateFormat = Constants.DD_MMM_YYYY_DATE_FORMAT;
            if (month.trim().equalsIgnoreCase("mm"))
                dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT;
        }
        else {
            dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT;
        }

        return dateFormat;
    }

    public static String getUserTimeZone() {
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
        return loginUser.getPreferredTz();
    }
    
    public static String getUserCurrency(){
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");  
        User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
        return loginUser.getCurrencyCd();
    }
    
    public List<Field> getBillingStatuses() {
        return refenceDataSerivce.getBillingStatusFields();
    }

    public List<Field> getOperationalStatuses() {
        return refenceDataSerivce.getOperationalStatusFields();
    }

    public List<Field> getProjectTypes() {
        return refenceDataSerivce.getProjectTypeFields(null);

    }

    public String getLoginName() {
        UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
        User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
        return user.getLoginName();
    }
    
    public static void setUserTimeZone(String userTz) {
//        try {
//            userTimeZone = DaoManager.getDao(TimeZone.class).find(userTz);
            TimeZoneService timeZoneService = (TimeZoneService) ServiceLocator.getInstance().getBean("timeZoneService");
            userTimeZone = timeZoneService.getTimeZoneByName(userTz);
//        }
//        catch (DaoException e) {
            //TODO default to what?
//        }
    }

    public TimeZone getPhoenixTimeZone() {
        return phoenixTimeZone;
    }

    public static void setPhoenixTimeZone(String phoenixTz) {
//        try {
            TimeZoneService timeZoneService = (TimeZoneService) ServiceLocator.getInstance().getBean("timeZoneService");
//            phoenixTimeZone = DaoManager.getDao(TimeZone.class).find(phoenixTz);
            phoenixTimeZone = timeZoneService.getTimeZoneByName(phoenixTz);
//        }
//        catch (DaoException e) {
            //TODO default to what?
//        }
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }
    

    @SuppressWarnings("unchecked")
    public List<Field> getOrigins() {
        return refenceDataSerivce.getDropdown(DropdownName.origin);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getInvoiceTypes() {
        return refenceDataSerivce.getDropdown(DropdownName.invoiceType);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getPaymentTypes() {
        return refenceDataSerivce.getDropdown(DropdownName.paymentType);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getPaymentTermsList() {
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("paymentTerms", null);
    }

    public List<Field> getDepositTypeList(){
        return refenceDataSerivce.getDropdown(DropdownName.depositType);
    }
    
    public List<Field> getUomList(){
        return refenceDataSerivce.getDropdown(DropdownName.UOM);
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> getStatusList() {
        return refenceDataSerivce.getOrderStatusFields();
        // TODO: filter the dropdown list based on job status
        //return refenceDataSerivce.getDropdown(DropdownName.jobStatus);

        /*
         * List<String> params = new ArrayList<String>();
         * params.add("jobStatus"); params.add("new"); params.add("0");
         * params.add("false"); params.add("0");
         * params.add(getActualReadyDate()); return (List<Field>)
         * CommonUtil.getDropDown("staticDropdown", params);
         */
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> getInvoiceLanguages() {
        return refenceDataSerivce.getDropdown(DropdownName.selectedLanguage);
    }

    @SuppressWarnings("unchecked")
    public List<Field> getBuNames() {
        return (List<Field>) com.intertek.util.CommonUtil.getDropDown("businessUnit", null);
    }
    
    @SuppressWarnings("unchecked")
    public List<Field> getProductTypes() {
        return refenceDataSerivce.getDropdown(DropdownName.productType);
    }
    public List<Field> getYesNos() {
        return refenceDataSerivce.getDropdown(DropdownName.yesNo);
    }

    public List<Field> getActiveStatuses(){
        return refenceDataSerivce.getDropdown(DropdownName.activeStatus);
    }
}
