/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;

import com.intertek.entity.User;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.BillingStatus;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.InvoiceStatus;
import com.intertek.phoenix.entity.InvoiceType;
import com.intertek.phoenix.entity.OperationalStatus;
import com.intertek.phoenix.entity.OrderOrigin;
import com.intertek.phoenix.entity.OrderStatus;
import com.intertek.phoenix.entity.PaymentStatus;
import com.intertek.phoenix.entity.PaymentType;
import com.intertek.phoenix.entity.ProjectType;
import com.intertek.phoenix.entity.SourceOrigin;
import com.intertek.util.SecurityUtil;

/**
 * 
 * @author richard.qin
 */
public class CommonUtil {

    private static PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");

    /** The label resource bundle. */
    private static PropertyResourceBundle lrb = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");

    private static Random rnd = new Random(System.currentTimeMillis());

    public static String getConfigValue(String name) {
        try {
            System.out.println("config name is" + pRB.getString(name));
            return pRB.getString(name);
        }
        catch (Exception e) {
            return "";
        }
    }
    
    public static List<com.intertek.meta.dropdown.Field> fixIE7Bug(List<com.intertek.meta.dropdown.Field> fields){
        if(fields==null){
            fields=new ArrayList<com.intertek.meta.dropdown.Field>();
        }
        
        //Making sure the list has at least two entries
        int size=fields.size();
        int toBeAdded=2-size;
        for(int i=0; i<toBeAdded; i++){
            com.intertek.meta.dropdown.Field field=new com.intertek.meta.dropdown.Field("", "");
            fields.add(fields.size(), field);
        }
        return fields;
    }   
    /**
     * Gets the labels from TrackerRes.properties
     * 
     * @param name
     *            the name
     * 
     * @return the label
     */
    public static String getTrackerResValue(String name) {
        try {
            return lrb.getString(name);
        }
        catch (Exception e) {
            return "";
        }
    }

    /**
     * Loads tabular data from a csv file into a list of String arrays
     * 
     * @param dataPath
     * @param limit
     * @return
     */
    @SuppressWarnings("unchecked")
    public static List<String[]> loadFromCsv(String dataPath, int limit) {
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(dataPath), ',');
            List<String[]> csvdata = null;
            if (limit > 0) {
                csvdata = new ArrayList<String[]>();
                for (int k = 0; k < limit; k++) {
                    String[] line = reader.readNext();
                    if (line == null) {
                        // there is less data than I expect to read
                        break;
                    }
                    csvdata.add(line);
                }
            }
            else {
                csvdata = reader.readAll();
            }
            return csvdata;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        finally {
            if (reader != null) {
                try {
                    reader.close();
                }
                catch (IOException ignore) {
                }
            }
        }
    }

    /**
     * Saves a list of flattened objects to a csv file. If it succeeds, the
     * orinial contents (if any) will be replaced by new ones. If it fails, the
     * original contents is not touched.
     * 
     * @param dataPath
     * @param list
     * @return true if succeeds; otherwise, false
     */
    public static boolean saveToCsv(String dataPath, String[] fieldNames, List<String[]> list) {
        CSVWriter writer = null;
        try {
            // File tempCsv =
            // File.createTempFile(CommonUtil.getConfigValue("CSV_TEMP_FILE"),
            // ".csv");
            File tempCsv = File.createTempFile("phxcsv", ".csv");
            writer = new CSVWriter(new FileWriter(tempCsv));
            // write headers
            writer.writeNext(fieldNames);
            // write values
            writer.writeAll(list);
            writer.close();
            writer = null;
            // remove the old one if exists
            File targetCsv = new File(dataPath);
            if (targetCsv.exists()) {
                targetCsv.delete();
            }
            // rename the temp file to the target name
            boolean ok = tempCsv.renameTo(targetCsv);
            if (!ok) {
                System.out.println("Failed to create file: " + targetCsv);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        finally {
            if (writer != null) {
                try {
                    writer.close();
                }
                catch (IOException ignore) {
                }
            }
        }
        return true;
    }

    /**
     * This is a temporary measure to generate a "unique" numeric id. For the
     * real hibernate implementation, this method will become a no-op and always
     * return 0, so the hibernate id generator can function accordingly.
     * <p>
     * TODO make it responsive to the choice of dao implementation. TODO
     * consider the strategy pattern here to make it more elegant.
     * 
     * @return
     */
    public static Long generateId() {
        if(DaoManager.getSessionFactory() == null){
            return Long.valueOf(Math.abs(rnd.nextInt()));
        }
        return null;
    }

    /**
     * This is a temporary measure to generate a "unique" string id. For the
     * real hibernate implementation, this method will become a no-op and always
     * return null, so the hibernate id generator can function accordingly.
     * <p>
     * TODO make it responsive to the choice of dao implementation. TODO
     * consider the strategy pattern here to make it more elegant.
     * 
     * @return
     */
    public static String generateIdCode() {
        return "PHX-" + Integer.toString(Math.abs(rnd.nextInt()));
    }

    /**
     * A null-safe version of toString() which always convert a null string into
     * an empty string.
     * 
     * @param value
     * @return
     */
    public static String toString(Object value) {
        if (value == null) {
            return "";
        }
        else {
            return value.toString();
        }
    }

    /**
     * A null-safe version of toString() which always returns a null object
     * or an empty String as a null value.
     * 
     * @param value
     * @return
     */
    public static String toStringOrNull(Object value) {
        if (value == null) {
            return null;
        }
        else if(isNullOrEmpty(value.toString())){
            return null;
        }
        else {
            return value.toString();
        }
    }

    /**
     * Convert a string value to its corresponding primitive value or object
     * value.
     * 
     * @param cls
     * @param content
     * @param pos
     * @return
     */
    public static Object stringToObject(Class<?> cls, String value) {
        Object result = null;
        if (value.length() > 0) {
            if (cls.equals(long.class) || cls.equals(Long.class)) {
                result = Long.valueOf(value);
            }
            else if (cls.equals(int.class) || cls.equals(Integer.class)) {
                result = Integer.valueOf(value);
            }
            else if (cls.equals(double.class) || cls.equals(Double.class)) {
                result = Double.valueOf(value);
            }
            else if (cls.equals(boolean.class) || cls.equals(Boolean.class)) {
                result = Boolean.valueOf(value);
            }
            else if (cls.equals(BigDecimal.class)) {
                result = new BigDecimal(value);
            }
            else if (cls.equals(Timestamp.class)) {
                result = Timestamp.valueOf(value);
            }
            else if (cls.isEnum() && value.length() > 0) { // I wish there is a
                                                            // better way to
                                                            // handle enum
                if (cls.equals(BillingStatus.class)) {
                    result = BillingStatus.valueOf(value);
                }
                else if (cls.equals(InvoiceStatus.class)) {
                    result = InvoiceStatus.valueOf(value);
                }
                else if (cls.equals(InvoiceType.class)) {
                    result = InvoiceType.valueOf(value);
                }
                else if (cls.equals(OperationalStatus.class)) {
                    result = OperationalStatus.valueOf(value);
                }
                else if (cls.equals(OrderOrigin.class)) {
                    result = OrderOrigin.valueOf(value);
                }
                else if (cls.equals(OrderStatus.class)) {
                    result = OrderStatus.valueOf(value);
                }
                else if (cls.equals(PaymentStatus.class)) {
                    result = PaymentStatus.valueOf(value);
                }
                else if (cls.equals(PaymentType.class)) {
                    result = PaymentType.valueOf(value);
                }
                else if (cls.equals(ProjectType.class)) {
                    result = ProjectType.valueOf(value);
                }
                else if (cls.equals(SourceOrigin.class)) {
                    result = SourceOrigin.valueOf(value);
                }
                else {

                }
            }
            else if (cls.equals(String.class)) {
                result = value;
            }
        }
        return result;
    }

    /**
     * A simple method that checks if <code>one</code> is part of
     * <code>many</code>
     * 
     * @param one
     * @param many
     * @return
     */
    public static boolean isOneOf(Object one, Object[] many) {
        if (one == null) {
            return false;
        }
        else {
            for (Object obj : many) {
                if (one.equals(obj)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Check if a given type is a "simple" type.
     * <p>
     * A simple is one of String, Long, Double, Integer, BigDecimal, Timestamp
     * and Boolean. Simple types can be mapped to SQL column types.
     * 
     * @param fieldCls
     * @return
     */
    public static boolean isSimpleType(Class<?> fieldCls) {
        if (fieldCls.isPrimitive() || fieldCls.equals(String.class) 
            || fieldCls.equals(Long.class) || fieldCls.equals(Double.class)
            || fieldCls.equals(Integer.class) || fieldCls.equals(BigDecimal.class) 
            || fieldCls.equals(Timestamp.class) || fieldCls.equals(Boolean.class)
            || Enum.class.isAssignableFrom(fieldCls)) {

            return true;
        }
        return false;
    }

    /**
     * Check if the field in the entity is a reference to another entity 
     * @param fieldCls
     * @return
     */
    public static boolean isMappedField(Field field) {
        Annotation[] annos = field.getAnnotations();
        for(Annotation anno: annos){
            Class<? extends Annotation> annoCls = anno.annotationType();
            if (annoCls.equals(ManyToOne.class)
                || annoCls.equals(OneToOne.class)
                || annoCls.equals(OneToMany.class)
                || annoCls.equals(ManyToMany.class)){
                return true;
            }
        }
        return false;
    }

    /**
     * @param fieldCls
     * @return
     */
    public static boolean isCollectionClass(Class<?> cls) {
        return Collection.class.isAssignableFrom(cls);
    }

    /**
     * Check if cls represents an entity class.
     * @param fieldCls
     * @return
     */
    public static boolean isEntityClass(Class<?> cls) {
        Annotation anno = cls.getAnnotation(Entity.class);
        if(anno != null){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * Returns all declared fields of the given class and its ancestor classes
     * into a provided <code>list</code>
     * 
     * @return
     */
    public static List<Field> getAllDeclaredFields(List<Field> list, Class<?> cls) {
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if ((mod & Modifier.STATIC) != Modifier.STATIC) {
                list.add(field);
            }
        }
        Class<?> parentCls = cls.getSuperclass();
        if (!parentCls.equals(Object.class)) {
            getAllDeclaredFields(list, parentCls);
        }
        return list;
    }

    /**
     * Returns all declared fields of the given class and its ancestor classes
     * 
     * @return
     */
    public static List<Field> getAllDeclaredFields(Class<?> cls) {
        List<Field> result = new ArrayList<Field>();
        return getAllDeclaredFields(result, cls);
    }

    /**
     * Search and return the named filed in the given class. If the field is not
     * found, search its immediate super class until the root of the inheritence
     * tree is reached.
     * 
     * @param cls
     * @param name
     * @return
     * @throws NoSuchFieldException
     */
    public static Field getDeclaredField(Class<? extends Object> cls, String name) throws NoSuchFieldException {
        Field field = null;
        while (!cls.equals(Object.class)) {
            try {
                field = cls.getDeclaredField(name);
                break;
            }
            catch (NoSuchFieldException e) {
                // try the super class;
                cls = cls.getSuperclass();
            }
        }
        if (field == null) {
            throw new NoSuchFieldException(name);
        }
        return field;
    }

    /**
     * The foreign side of a relationship is marked by @JoinColumn or @JoinColumns
     * @param field
     * @return
     */
    public static boolean isForeignSide(Field field) {
        Annotation anno = field.getAnnotation(JoinColumn.class);
        if(anno != null){
            return true;
        }
        else{
            anno = field.getAnnotation(JoinColumns.class);
            if(anno != null){
                return true;
            }
        }
        return false;
    }

    /**
     * Convert FIELD_NAME to fieldName, based on such a naming convention
     * @param referencedColumnName
     * @return
     */
    public static String columnToFieldName(Field field, String columnName) {
        StringBuilder sb = new StringBuilder();
        boolean toHump = false;
        for(int k=0; k<columnName.length(); k++){
            char ch = columnName.charAt(k);
            if(ch == '_'){ // skip it
                toHump = true;
                continue;
            }
            else if(Character.isLetter(ch)){
                if(toHump){
                    sb.append(Character.toUpperCase(ch));
                    toHump = false;
                }
                else{
                    sb.append(Character.toLowerCase(ch));
                }
            }
            else if(Character.isDigit(ch)){
                sb.append(ch);
            }
            else { // igore all other characters, including white spaces
                continue;
            }
        }
        String temp = sb.toString();
        if(field == null){
            return temp;
        }
        else{
            Class<?> cls = field.getType();
            try {
                if(cls.getField(temp) != null){
                    return temp;
                }
            }
            catch (NoSuchFieldException e) {
                return null;
            }
            return null;
        }
    }

    /**
     * 
     * @param field
     * @return
     */
    public static String[][] getJoinColumnFieldNames(Field field) {
        String[][] result = null;
        JoinColumn fieldJc = field.getAnnotation(JoinColumn.class);
        if(fieldJc != null){
            result = new String[1][2]; // single key
            result[0][0] = columnToFieldName(null, fieldJc.name()); // name
            String referencedColumnName = fieldJc.referencedColumnName();
            if(referencedColumnName != null && referencedColumnName.length() > 0){
                result[0][1] = columnToFieldName(field, referencedColumnName);
            }
            if(result[0][1] == null) {
                // referenced column name is not specified in @JoinColumn, so use 
                // the @Id field of the referenced type
                Class<?> referencedCls = field.getType();
                // here is a bit of complication, the @Id may actually declared on the key 
                // field of a super class, hence the loop
                while(!referencedCls.equals(Object.class)){
//                    Field[] referencedFields = referencedCls.getDeclaredFields();
                    List<Field> referencedFields = new ArrayList<Field>();
                    CommonUtil.getAllDeclaredFields(referencedFields, referencedCls);
                    for(Field f: referencedFields){
                        Id annoId = f.getAnnotation(Id.class);
                        if(annoId != null){
                            result[0][1] = f.getName();
                            break;
                        }
                    }
                    // move up to the super class
                    referencedCls = referencedCls.getSuperclass();
                }
            }
            if(result[0][1] == null){
                throw new RuntimeException("Invalid field mapping: " + field.getName());
            }
        }
        else {
            JoinColumns joinCols = field.getAnnotation(JoinColumns.class);
            if(joinCols != null){
                JoinColumn[] jcs = joinCols.value();
                result = new String[jcs.length][2]; // composite key
                for(int k=0; k<jcs.length; k++){
                    result[k][0] = columnToFieldName(null, jcs[k].name());
                    result[k][1] = columnToFieldName(field, jcs[k].referencedColumnName());
                    if(result[k][1] == null){
                        throw new RuntimeException("Invalid @JoinColumns definition: " + field.getName());
                    }
                }
            }
            else{
                throw new RuntimeException("Invalid field mapping: " + field.getName());
            }
        }
        return result;
    }

    /**
     * @param fieldCls
     * @param fieldMappedBy
     * @return
     */
    public static String[][] getReverseJoinColumnFieldNames(Field field) {
        String[][] joinFieldNames = getJoinColumnFieldNames(field);
        // need to swap the order of String[][0] and String[][1]
        for(String[] fieldNamePair: joinFieldNames ){
            String temp = fieldNamePair[0];
            fieldNamePair[0] = fieldNamePair[1];
            fieldNamePair[1] = temp;
        }
        return joinFieldNames;
    }

    /**
     * @param ownerField
     * @return
     * @throws NoSuchFieldException 
     */
    public static Field getFieldMappedBy(Field ownerField) throws NoSuchFieldException {
        Class<?> fieldCls = ownerField.getType();
        return getFieldMappedBy(ownerField, fieldCls);
    }

    /**
     * @param ownerField
     * @return
     * @throws NoSuchFieldException 
     */
    public static Field getFieldMappedBy(Field ownerField, Class<?> fieldCls) throws NoSuchFieldException {
        Field mappedField = null;
        OneToMany otm = ownerField.getAnnotation(OneToMany.class);
        if(otm != null){
            String mappedBy = otm.mappedBy();
            //mappedField = fieldCls.getDeclaredField(mappedBy);
            mappedField = CommonUtil.getDeclaredField(fieldCls, mappedBy);
        }
        else {
            OneToOne oto = ownerField.getAnnotation(OneToOne.class);
            if(oto != null){
                String mappedBy = oto.mappedBy();
                mappedField = CommonUtil.getDeclaredField(fieldCls, mappedBy);
            }
            else{
                ManyToMany mtm = ownerField.getAnnotation(ManyToMany.class);
                if(mtm != null){
                    String mappedBy = mtm.mappedBy();
                    mappedField = CommonUtil.getDeclaredField(fieldCls, mappedBy);
                }
            }
        }
        return mappedField;
    }
    
    /**
     * Return the log in user. The user should be available from a thread local
     * storage, which is set by a http filter.
     * 
     * @return
     */
    public static String getCurrentUser() {
        // TODO Auto-generated method stub
        User user = SecurityUtil.getUser();
       // return null;
        return user.getLoginName();
    }

    /**
     * @param one
     * @param fieldName
     * @return
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public static Object getValue(CEJobOrderLineItem one, String fieldName) {
        if (one != null) {
            try {
                Field field = one.getClass().getField(fieldName);
                return field.get(one);
            }
            catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Compare two objects for equality
     * 
     * @param value1
     * @param value2
     * @return
     */
    @SuppressWarnings("unchecked")
    public static int compare(Object value1, Object value2) {
        int result = 0;
        if (value1 == null && value2 == null) {
            result = 0;
        }
        else if (value1 == null) {
            result = -1;
        }
        else if (value2 == null) {
            result = 1;
        }
        else if (value1 instanceof Comparable) {
            result = ((Comparable) value1).compareTo(value2);
        }
        else if(value1.equals(value2)){
            result = 0;
        }
        else{
            result = value1.hashCode() - value2.hashCode();
        }
        return result;
    }
    
    public static int compare(Object[] one, Object[] two){
        if(one == null && two == null){
            return 0;
        }
        else if (one == null){
            return -1;
        }
        else if (two == null){
            return 1;
        }
        else if(one.length != two.length){
            return -1;
        }
        else{
            int r = 0;
            for(int k=0; k<one.length && r==0; k++){
                r = compare(one[k], two[k]);
            }
            return r;
        }
    }

    public static double doubleValue(Double value) {
        if(value != null){
            return value.doubleValue();
        }
        return 0;
    }

    public static boolean booleanValue(Boolean value) {
        if(value != null){
            return value.booleanValue();
        }
        return false;
    }

    public static int intValue(Integer value) {
        if(value != null){
            return value.intValue();
        }
        return 0;
    }
    
    public static long toLong(String value){
        if (!isNullOrEmpty(value)){
            try {
                return Long.parseLong(value);
            }
            catch (NumberFormatException e) {
                return 0L;
            }
        }
        return 0L;
    }
    
    public static Long toLongOrNull(String value){
        if (!isNullOrEmpty(value)){
            try {
                return Long.parseLong(value);
            }
            catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
    
    public static boolean isNullOrEmpty(String val){
        return val == null || val.trim().length() == 0;
    }
    
    public static String toDateString(Date date){
        return new SimpleDateFormat("dd-MMM-yy").format(date);
    }
    
    
    public static String toDateString(Date date, String dateFormat){
        return new SimpleDateFormat(dateFormat).format(date);
    }
    
  //Valid Date
    public static boolean isValidDate(String strDate) {
        if (strDate != null && !strDate.equals("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
            sdf.setLenient(false);
            try {
                sdf.parse(strDate);
            }
            catch (ParseException pe) {
                return false;

            }
            try {
                int day =   Integer.parseInt((strDate.substring(0, 2)));
                int month = Integer.parseInt(strDate.substring(3, 5));
                int year = Integer.parseInt(strDate.substring(6));

                if (year > 3000 || year < 1800)
                    return false;

                if (((month == 2) && (day > 29) && ((year % 4) == 0)) || ((month == 2) && (day > 28) && (year % 4 != 0))
                    || (((month == 4) || (month == 6) || (month == 9) || (month == 11)) && (day > 30)))
                    return false;
            }
            catch (Exception pe) {
                return false;
            }
        }
        return true;
    }
    private static final Pattern alphaNumeric = Pattern.compile("^([A-Za-z0-9])[A-Za-z0-9 , -]+");

    public static boolean validateAlphaNum(String str) {
        return ((alphaNumeric.matcher(str).matches()) ? true : false);
    }

    // this pattern has problem
    //private static final Pattern numeric = Pattern.compile("[0-9]+([.][0-9])*");

    public static boolean validateNum(String str) {
        if(CommonUtil.isNullOrEmpty(str)){
            return false;
        }
        try{
            Double.valueOf(str);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

}
