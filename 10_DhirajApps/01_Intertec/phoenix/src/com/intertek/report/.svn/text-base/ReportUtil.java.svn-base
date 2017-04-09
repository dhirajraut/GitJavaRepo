/**
 * 
 */
package com.intertek.report;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRDataSource;

import oracle.jdbc.OracleTypes;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import au.com.bytecode.opencsv.CSVReader;

import com.intertek.phoenix.util.ArrayMap;
import com.intertek.phoenix.util.ArrayMapGrid;
import com.intertek.report.jasper.StringShell;

/**
 * 
 * @author richard.qin
 */
public class ReportUtil {
    private static Logger log = Logger.getLogger(ReportUtil.class);
    private static BigDecimal ZERO = new BigDecimal(0);
    private static Map<String, ReferenceData> referenceCache = new HashMap<String, ReferenceData>();
       
    public static boolean isBlank(String str){
        return str == null || str.trim().isEmpty();
    }

    // this method is copied from FieldPackager
    // TODO refactor it
    public static void populatePrefix(String prefix, Properties formatProps, HashMap<String, String> keyVals,
                    ArrayList<StringShell> returnedFields) {
        int linesToCheck = 10;
        // run down entries in the properties file for lines 1-10 and evaluate
        // into string,
        // then add to returned fields list.
        for (int t = 1; t <= linesToCheck; t++) {
            String prefixCheck = prefix + "." + String.valueOf(t);
            if (formatProps.containsKey(prefixCheck)) { // if prefix doesn't
                                                        // exist for a certain
                                                        // line, skip it.
                if (t == 2 && keyVals != null && !keyVals.isEmpty() && keyVals.containsKey("address4")
                                && keyVals.get("address4") != null && !keyVals.get("address4").equals("null")
                                && !keyVals.get("address4").isEmpty() && keyVals.get("address4").trim().length() > 0) {
//                    System.out.println("here when t==2 address4:" + keyVals.get("address4"));
                    String address4 = "ATTN TO: ".concat(keyVals.get("address4"));
                    keyVals.put("address4", address4);
                }
                String textPattern = formatProps.getProperty(prefixCheck);
                String resolvedText = resolveTextLiteral(textPattern, keyVals);
                if (resolvedText.trim().length() > 0) { // only add if something
                                                        // useful came back from
                                                        // the resolved text.
                    returnedFields.add(new StringShell(resolvedText));
                }
            }
        }
    }

    // this method is copied from FieldPackager
    // TODO refactor it
    public static String resolveTextLiteral(String textPattern, HashMap<String, String> tokenValues) {
        String textLiteral = "";
        StringTokenizer st = new StringTokenizer(textPattern, "+");
        while (st.hasMoreTokens()) {
            String nextToken = st.nextToken();
            boolean foundHashMatch = false;
            // check all available tokens for replacement.
            Iterator<String> tokIt = tokenValues.keySet().iterator();
            while (tokIt.hasNext()) {
                String nextKey = tokIt.next().toString();
                if (nextToken.trim().equalsIgnoreCase(nextKey)) { // token match
                                                                  // found.
                    foundHashMatch = true;
                    String tokValue = tokenValues.get(nextKey);
                    if (tokValue != null && !tokValue.equalsIgnoreCase("null")) { // fixed
                        textLiteral += tokValue;
                    }
                }
            }
            if (foundHashMatch == false) {// nothing was found in the token
                                          // hash, use literal.
                textLiteral += nextToken;
            }
        }
        return textLiteral;
    }
    
    /**
     * load tabular data from a csv file into a list of String array
     * @param dataPath
     * @param limit
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String[]> loadFromCsv(String dataPath, int limit){
        try {
            CSVReader reader = new CSVReader(new FileReader(dataPath), ',');
            List<String[]> csvdata = null;
            if(limit > 0){
                csvdata = new ArrayList<String[]>();
                for(int k=0; k<limit; k++){
                    String[] line = reader.readNext();
                    if(line == null){
                        // there is less data than I expect to read
                        break;
                    }
                    csvdata.add(line);
                }
            }
            else{
                csvdata = reader.readAll();
            }
            reader.close();
            return csvdata;
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * load tabular data from a csv file into a list of object array. Exact type of the objects
     * is determined by the <code>types</code> parameter. The first row read from a csv file is 
     * the column headings, which may not be in the order expected. Adjustment must be made to 
     * make sure that the column headings are in line with expectation.
     * @param dataPath
     * @param limit
     * @param types
     * @return
     */
    public static List<Object[]> loadFromCsv(String dataPath, String[] fieldNames, Map<String, FieldType> types, int limit){
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(dataPath), ',');
            List<Object[]> csvdata = null;
            if(limit == 0) {
                limit = Integer.MAX_VALUE;
            }
            // read in the headings
            String[] headings = reader.readNext();
            
            csvdata = new ArrayList<Object[]>();
            for(int k=1; k<limit; k++){
                String[] fields = reader.readNext();
                if(fields == null){
                    // there is less data than I expect to read
                    break;
                }
                // for each element in line, convert it to its designated type
                Object[] objects = new Object[fields.length];
                for(int j=0; j<fields.length; j++){
                    FieldType t = types.get(headings[j]);
                    int idx = getFieldIndex(headings[j], fieldNames);
                    if(idx < 0){
                     // this field is not needed
                        continue; 
                    }
                    // there is always an issue with "null" versus blank
                    else if(fields[j].length() == 0 || fields[j].equals("(null)")){
                        objects[idx] = null;
                    }
                    else if(t != null){ // a type other than String
                        switch(t){
                            case BigDecimal:
                                objects[idx] = new BigDecimal(fields[j]);
                                break;
                            case Timestamp:
                                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
                                try {
                                    Date d = df.parse(fields[j]);
                                    objects[idx] = new Timestamp(d.getTime());
                                }
                                catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                break;
                            default:
                                objects[idx] = fields[j];
                                break;
                        }
                    }
                    else{ // all others are treated as Strings
                        objects[idx] = fields[j];
                    }
                }
                csvdata.add(objects);
            }
            return csvdata;
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
        finally{
            if(reader != null){
                try{
                reader.close();
                }
                catch(Exception ignore){
                }
            }
        }
    }
    
    private static int getFieldIndex(String field, String[] fields){
        for(int k=0; k<fields.length; k++){
            if(field.equalsIgnoreCase(fields[k])){
                return k;
            }
        }
        return -1;
    }
    
    private static void concatFieldName(StringBuilder sb, String str, boolean first){
        if(first){
            if(str.contains(" ")){
                sb.append("\"").append(str).append("\"");
            }
            else {
                sb.append(str);
            }
        }
        else{
            if(str.contains(" ")){
                sb.append(", \"").append(str).append("\"");
            }
            else {
                sb.append(", ").append(str);
            }
        }
    }

    public static String concatFieldNames(String[] strings, String... extra) {
        if (strings != null && strings.length > 0) {
            StringBuilder sb = new StringBuilder();

            concatFieldName(sb, strings[0], true);

            for (int k = 1; k < strings.length; k++) {
                concatFieldName(sb, strings[k], false);
            }

            for (String s : extra) {
                concatFieldName(sb, s, false);
            }

            return sb.toString();
        }
        return null;
    }
    
    /**
     * Convert an array of field name / type into a map
     * @param fieldTypes an array of strings. Elements at odd positions are field names; elements at 
     * even positions are names of classes
     * @return a Map of String to FieldType
     */
    public static Map<String, FieldType> mapFieldTypes(String[] fieldTypes ) {
        Map<String, FieldType> map = new HashMap<String, FieldType>();
        for (int k = 0; k < fieldTypes.length; k += 2) {
            map.put(fieldTypes[k], Enum.valueOf(FieldType.class, fieldTypes[k+1]));
        }
        return map;
    }
    
    /**
     * A helper method that create a <code>JRDataSource</code> over a hibernate session
     * @param reportName The name of the report
     * @param sql The sql statement
     * @param parameters sql parameter values
     * @param fieldNames an array of field names
     * @param typeMap a map of field types
     * @return a JRDataSource
     */
    @SuppressWarnings("unchecked")
    public static JRDataSource hibernateJasperDataSourceHelper(
                    String reportName, String sql, Pair<?, ?>[] parameters, 
                    String[] fieldNames, Map<String, FieldType> typeMap,
                    JasperDataSource callback, Map<String, Object> params){
        
        // get a hibernate session
        Session session = HibernateUtil.getSessionFactory().openSession();
        // construct the query
        Query q = session.createSQLQuery(sql);
        for(Pair<?, ?> p: parameters){
            q.setParameter((String)p.key(), p.value());
        }
        // execute the query to get the result
        // no need to convert the result into any beans, keep'em as values
        List<Object[]> result = q.list();
        session.close();
        
        if(result != null){
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), result, callback, params);
        }
        return null;
    }
    
    /**
     * A helper method that creates a <code>JRDataSource</code> over a local csv file
     * @param reortName
     * @param dataPath
     * @param fieldNames
     * @param typeMap
     * @param limit
     * @return
     */
    public static JRDataSource localJasperDataSourceHelper(
                    String reortName, String dataPath, 
                    String[] fieldNames, Map<String, FieldType> typeMap, int limit,
                    JasperDataSource callback, Map<String, Object> params){
        List<Object[]> csvdata = loadFromCsv(dataPath, fieldNames, typeMap, limit);
        if(csvdata != null){
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), csvdata, callback, params);
        }
        return null;
    }

    public static JRDataSource groupJasperDataSourceHelper(String keyField, String[] sourceFieldNames, String[] destFieldNames, 
                   ArrayMapGrid grid, JasperDataSource callback, Map<String, Object> params){
        // transform
        Map<Object, BigDecimal[]> map = new HashMap<Object, BigDecimal[]>();
        
        while(grid.moveNext()){
            Object key = grid.getFieldValue(keyField);
            BigDecimal[] entry = map.get(key);
            if(entry == null){
                entry = new BigDecimal[sourceFieldNames.length];
                map.put(key, entry);
            }
            for(int k=0; k<sourceFieldNames.length; k++){
                Object value = grid.getFieldValue(sourceFieldNames[k]);
                BigDecimal num = (BigDecimal)value;
                BigDecimal sum = entry[k];
                if(sum == null){
                    entry[k] = num;
                }
                else{
                    entry[k] = sum.add(num);
                }
            }
        }
        // reset the grid
        grid.reset();
        
        // prepare the list
        List<Object[]> list = new ArrayList<Object[]>();
        for(Object key: map.keySet()){
            BigDecimal[] values = map.get(key);
            Object[] sumValues = new Object[sourceFieldNames.length + 1];
            sumValues[0] = key;
            for(int k=0; k<values.length; k++){
                sumValues[k+1] = values[k];
            }
            list.add(sumValues);
        }
        
        // obtain a data source
        return new ArrayMapDataSource(new ArrayMap<String, Object>(destFieldNames), list, callback, params);
    }
    
    public static JRDataSource jdbcCallableJasperDataSourceHelper(
              String reportName, Connection conn, String sql, 
              Pair<?, ?>[] parameters, String[] fieldNames, Map<String, FieldType> typeMap,
              JasperDataSource callback, Map<String, Object> params){
        CallableStatement stmt = null;
        ResultSet rs = null;
        try {
             stmt = conn.prepareCall(sql);
             // register the type of the out param - an Oracle specific type
            stmt.registerOutParameter(1, OracleTypes.CURSOR);
            // set the in param
            stmt.setString(2, (String)params.get("business_unit"));
            stmt.setString(3, (String)params.get("operating_unit"));
            stmt.setString(4, (String)params.get("currency"));
            stmt.setTimestamp(5, (Timestamp)params.get("start_dt"));
            stmt.setTimestamp(6, (Timestamp)params.get("as_of_dt"));
            // execute and get the result back
            stmt.execute();
            rs = (ResultSet)stmt.getObject(1);
            // prepare result
            List<Object[]> list = new ArrayList<Object[]>();
            while (rs.next()) {
                Object[] record = new Object[fieldNames.length];
                for (int k = 0; k < record.length; k++) {
                    FieldType type = typeMap.get(fieldNames[k]);
                    if (type != null) {
                        switch (type) {
                            case BigDecimal:
                                record[k] = rs.getBigDecimal(fieldNames[k]);
                                break;
                            case Timestamp:
                                record[k] = rs.getTimestamp(fieldNames[k]);
                                break;
                            default:
                                record[k] = rs.getString(fieldNames[k]);
                                break;
                        }
                    }
                    else {
                        record[k] = rs.getString(fieldNames[k]);
                    }
                }
                list.add(record);
            }
            // obtain a data source
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), list, callback, params);
        }
        catch(Exception ex){
            log.error("Failed to load data for report " + reportName);
            log.error(ex.getMessage());
        }
        finally{
            if(rs != null){
                try {
                    rs.close();
                }
                catch (SQLException ignore) {
                }
            }
            if(stmt != null){
                try {
                    stmt.close();
                }
                catch (SQLException ignore) {
                }
            }
        }
        return null;
    }
    
    /** 
     * A helper method that creates a <code>JRDataSource</code> over a JDBC connection.
     * @param reportName The name of the report
     * @param conn A JDBC connection
     * @param sql The sql statement
     * @param parameters The sql parameter values
     * @param fieldNames An array field names
     * @param typeMap A Map of types of fields
     * @return a JRDataSource
     */
    public static JRDataSource jdbcJasperDataSourceHelper(
                    String reportName, Connection conn, String sql, 
                    Pair<?, ?>[] parameters, String[] fieldNames, Map<String, FieldType> typeMap,
                    JasperDataSource callback, Map<String, Object> params){
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            if(log.isDebugEnabled()){
                log.debug(sql);
                for(Pair<?, ?> pair: parameters){
                    log.debug(pair);
                }
            }
            // set parameters
            for(int k=0; k<parameters.length; k++){
                Pair<?, ?> pair = parameters[k];
                FieldType type = typeMap.get(pair.key());
                if (type != null) {
                    switch (type) {
                        case BigDecimal:
                            stmt.setBigDecimal(k+1, (BigDecimal)pair.value());
                            break;
                        case Timestamp:
                            stmt.setTimestamp(k+1, (Timestamp)pair.value());
                            break;
                        default:
                            stmt.setString(k+1, (String)pair.value());
                            break;
                    }
                }
                else {
                    stmt.setString(k+1, (String)pair.value());
                }
            }
            // execute query
            ResultSet rs = stmt.executeQuery();
            // prepare result
            List<Object[]> list = new ArrayList<Object[]>();
            while (rs.next()) {
                Object[] record = new Object[fieldNames.length];
                for (int k = 0; k < record.length; k++) {
                    FieldType type = typeMap.get(fieldNames[k]);
                    if (type != null) {
                        switch (type) {
                            case BigDecimal:
                                record[k] = rs.getBigDecimal(fieldNames[k]);
                                break;
                            case Timestamp:
                                record[k] = rs.getTimestamp(fieldNames[k]);
                                break;
                            default:
                                record[k] = rs.getString(fieldNames[k]);
                                break;
                        }
                    }
                    else {
                        record[k] = rs.getString(fieldNames[k]);
                    }
                }
                list.add(record);
            }
            rs.close();
            stmt.close();
            // obtain a data source
            return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), list, callback, params);
        }
        catch(SQLException ex){
            log.error("Failed to load data for report " + reportName);
            log.error(ex.getMessage());
        }
        return null;
    }
    
    public static double calcDouble(String reportName, Connection conn, String sql, 
                    Pair<?, ?>[] parameters, String[] fieldNames, Map<String, FieldType> typeMap){
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            // set parameters
            for(int k=0; k<parameters.length; k++){
                Pair<?, ?> pair = parameters[k];
                FieldType type = typeMap.get(pair.key());
                if (type != null) {
                    switch (type) {
                        case BigDecimal:
                            stmt.setBigDecimal(k+1, (BigDecimal)pair.value());
                            break;
                        case Timestamp:
                            stmt.setTimestamp(k+1, (Timestamp)pair.value());
                            break;
                        default:
                            stmt.setString(k+1, (String)pair.value());
                            break;
                    }
                }
                else {
                    stmt.setString(k+1, (String)pair.value());
                }
            }
            // execute query
            ResultSet rs = stmt.executeQuery();
            // prepare result
            Double v = null;
            if(rs.next()){
                v = rs.getDouble(1);
            }
            rs.close();
            return v;
        }
        catch(SQLException ex){
            log.error("Failed to calculate result for report " + reportName);
            log.error(ex.getMessage());
        }
         return 0;
    }

    public static double[] summariseFields(String[] fields, ArrayMapDataSource amds) {
        double[] results = new double[fields.length];
        int[] indice = new int[fields.length];
        
        for(int k=0; k<fields.length; k++){
            indice[k] = amds.getFieldIndex(fields[k]);
        }
        for(Object[] value: amds.getDataset()){
            for(int k=0; k<fields.length; k++){
                BigDecimal v = (BigDecimal)value[indice[k]];
                if(v != null){
                    results[k] += v.doubleValue();
                }
                else{
                    log.warn("Field returned unexpected null value: " + fields[k]);
                }
            }
        }
        
        return results;
    }

    public static double perentSummariseFields(String amtField, String pctField, ArrayMapDataSource amds) {
        double result = 0;
        int amtIdx = amds.getFieldIndex(amtField);
        int pctIdx = amds.getFieldIndex(pctField);

        for(Object[] value: amds.getDataset()){
            double amt = 0;
            if(value[amtIdx] != null){
                amt = ((BigDecimal)value[amtIdx]).doubleValue();
            }
            double pct = 0;
            if(value[amtIdx] != null){
                pct = ((BigDecimal)value[pctIdx]).doubleValue() / 100;
            }
            result += amt * pct;
        }
        
        return result;
    }
    
    public static Object getReferenceFieldValue(String referenceName, String[] keyValues, String fieldname){
        Object result = null;
        ReferenceData reference = referenceCache.get(referenceName);
        result = reference.getReferenceValue(keyValues, fieldname);
        return result;
    }
    
    public static void loadReferenceDataSource(String referenceName, String[] keyFieldNames, Connection con){
        ReferenceData reference = referenceCache.get(referenceName);
        if (reference == null) {
            JRDataSource ds = ReportDataSourceManager.getManager().getReportDataSource(referenceName, null, con);
            referenceCache.put(referenceName, new ReferenceData((ArrayMapDataSource) ds, keyFieldNames));
        }
    }
    
    public static BigDecimal toBigDecimal(Object number){
        if(number == null){
            return ZERO;
        }
        return (BigDecimal)number;
    }

    //TODO Confirm: Common method for CEInvoice reports
    @SuppressWarnings("unchecked")
    public static String getPoAuth(String invoice) {
        String strResult = "";
        String hql = "select distinct po.poNumber,po.maxAmount from com.intertek.phoenix.entity.JobTest jt join jt.purchaseOrder po "
                     + "join jt.jobServiceLevel sl " + "join sl.jobOrder jo left join jo.jobContracts jc,CEInvoice inv "
                     + "where inv.jobOrderContractId = jc.id and inv.invoiceNumber =?";
        ArrayList<Object> paramList = new ArrayList<Object>();
        paramList.add(invoice);
        List<Object[]> result = hibernateJasperDataSourceHelperByHql(hql, paramList, new HashMap(), null);
        if (result != null && result.size() > 0) {
            for (Object[] obj : result) {
                if (obj[0] != null && !obj[0].toString().isEmpty()) {
                    strResult += obj[0] + " – " + obj[1] + ";\n";
                }
               // strResult += strResult;
            }
        }
        return strResult;
    }
    
    /*
     * TODO confirm:From jasper search service methods are failing due to unavailability of active transaction.
     * this helper method will help to solve the issue
     * */
    @SuppressWarnings("unchecked")
    public static List<Object[]> hibernateJasperDataSourceHelperByHql(String hql, List<Object> paramList, Map<String, FieldType> typeMap, String[] fieldNames) {
        // get a hibernate session
        Session session = com.intertek.phoenix.util.HibernateUtil.getSessionFactory().openSession();
        // construct the query
        Query query = session.createQuery(hql);
        if (paramList != null) {
            for (int i = 0; i < paramList.size(); i++) {
                query.setParameter(i, paramList.get(i));
            }
        }
        // execute the query to get the result
        // no need to convert the result into any beans, keep'em as values
        List<Object[]> result = query.list();
        

        if (typeMap.size() > 0 && result != null) {
            for (int k = 0; k < result.size(); k++) {
                boolean tempCreditInd=false;
                Object[] objArr = result.get(k);
                for (int i = 0; i < objArr.length; i++) {
                    FieldType type = typeMap.get(fieldNames[i]);
                    if(fieldNames[i].equalsIgnoreCase("CREDIT_IND")&&objArr[i]!=null&&objArr[i].toString().equalsIgnoreCase("CREDITED"))
                    {
                        tempCreditInd =true;
                    }
                    if(tempCreditInd){
                        if(fieldNames[i].equalsIgnoreCase("UNIT_PRICE")
                            ||fieldNames[i].equalsIgnoreCase("NET_PRICE")
                            ||fieldNames[i].equalsIgnoreCase("INV_AMT_PRE_TAX")
                            ||fieldNames[i].equalsIgnoreCase("INV_AMT_POST_TAX"))
                        {
                            objArr[i]="-"+objArr[i];
                        }
                    }
                    if(fieldNames[i].equalsIgnoreCase("DEPOSIT_SUBTOTAL")&&objArr[i]!=null)
                    {
                    	  objArr[i]="-"+objArr[i];
                    }
                    if (type != null) {
                        switch (type) {
                            case BigDecimal:
                                if(objArr[i]!=null){
                                objArr[i] = new BigDecimal(objArr[i].toString());
                                }
                                break;
                            case Timestamp:
                                if (objArr[i] != null && !objArr[i].toString().isEmpty()) {
                                    objArr[i] = Timestamp.valueOf(objArr[i].toString());

                                }
                                break;
                            default:
                                objArr[i] = fieldNames[i];
                                break;
                        }

                    }
                }
                result.set(k, objArr);

            }
        }

        session.close();
        return result;
    }

}
