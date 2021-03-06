/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.web.controller;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Id;

import org.apache.log4j.Logger;
import org.hibernate.LazyInitializationException;
import org.hibernate.MappingException;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PersistentClass;
import org.hibernate.mapping.Property;
import org.hibernate.metadata.ClassMetadata;

import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.util.CommonUtil;


/**
 * A utility class that help Controllers to do automatic entity merging
 * or loadAndUpdate operations.
 * <p>This class is designed to greatly reduce coding efforts required for
 * manipulating entity objects over a Hibernate persistence solution.
 * 
 * TODO fix log messages
 * 
 * @author richard.qin
 */
public class ControllerUtil {
    static private Logger log = Logger.getLogger(ControllerUtil.class);

    public static <T, ID extends Serializable> T findById(Class<T> cls, ID id) throws DaoException{
        Dao<T> dao = DaoManager.getDao(cls);
        return dao.find(id);
    }
    
    /**
     * This method ensures that an entity (detached or not) will be
     * converted (if necessary) to a managed entity, and with it state
     * updated automatically.
     * <p>
     * This method does the followings:
     * 1. Extract Id from the entity
     * 2. Load the managed entity from database
     * 3. if the loaded entity has the same identity as the input entity
     *    return the input entity.
     * 4. else, populate the input values from the input entity to the
     *    loaded entity.
     *    4.1 for each field, if the values are different and if it is 
     *    an id value of a related entity, load the entity and call this
     *    method recursively.
     *    4.2 if the field is a collection of entities, then repeat 4.1
     *    4.3 for all other fields, copy the value from input entity to
     *    the loaded entity.
     * 
     * @param jobContract
     * @return
     */
    public static <T> T loadAndUpdate(T inputEntity, Class<T> entityCls) throws DaoException {
        return loadAndUpdate(inputEntity, entityCls, false);
    }
    
    public static <T> T loadAndUpdate(T inputEntity, Class<T> entityCls, boolean followCollection) throws DaoException {
        log.warn("Load and update entity: " + entityCls.getSimpleName());
        
        Serializable id = getId(inputEntity); // use dao method, not reflection
        // load
        T loadedEntity = findById(entityCls, id);
        // update
        // the equality check is valid, because if the inputEntity is already
        // managed, the the newly loaded entity must be the same instance.
        // and if that is the case, no need to call update().
        Set<?> done = new HashSet<Object>(); // need to break infinit loop
        // 0 is indentation level
        update(loadedEntity, inputEntity, entityCls, done, 0, followCollection, false); 
        return loadedEntity;
    }

    @SuppressWarnings("unchecked")
    public static <T> void update(T entity, T sourceEntity, Class<T> entityCls) throws DaoException {
        Set done = new HashSet();
        update(entity, sourceEntity, entityCls, done, 0, false, false);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> void update(T entity, Class<T> entityCls) throws DaoException {
        Set done = new HashSet();
        update(entity, entity, entityCls, done, 0, false, true);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> void updateCollection(T entity, T sourceEntity, Class<T> entityCls, String collectionFieldName) throws DaoException {
        try{
            Set done = new HashSet();
            done.add(sourceEntity); // do not update the entity itself
            Field field = entityCls.getDeclaredField(collectionFieldName);
            updateCollectionField(entity, sourceEntity, entityCls, field, done, 0, false);
        }
        catch(Exception ex){
            throw new DaoException("Failed to update collection field " + collectionFieldName);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> void updateCollection(Collection<T> target, Collection<T> source, Class<T> entityCls) throws DaoException {
        Set<T> toRemove = new HashSet<T>();
        try{
            Set done = new HashSet();
            for(T obj: target){
                Serializable id = getId(obj);
                T objSource = findMatchedObjectInCollection(source, id);
                if(objSource == null){
                    // the source collection no longer have this object
                    toRemove.add(obj);
                }
                update(obj, objSource, entityCls, done, 0, false, false);
            }
            if(toRemove.size() > 0){
                target.remove(toRemove);
            }
            for (T obj: source){
                Serializable id = getId(obj);
                T objTarget = findMatchedObjectInCollection(target, id);
                if(objTarget == null){
                    // a new object needs to be added to the target collection
                    objTarget = entityCls.newInstance();
                    update(obj, objTarget, entityCls, done, 0, false, false);
                    target.add(objTarget);
                }
            }
        }
        catch(Exception ex){
            throw new DaoException("Failed to update collection " + entityCls.getSimpleName());
        }
    }
    
    /************************************************************************* 
     * helper methods
     *************************************************************************/
    
    public static void toFieldMap(Object entity, Class<?> entityCls){
        Map<String, String> map = new HashMap<String, String>();
        Set<Object> mapped = new HashSet<Object>();
        toStringValueMap(map, entity, entityCls, "", mapped);
    }
    
    public static void printEntity(Object entity, Class<?> entityCls){
        StringBuilder sb = new StringBuilder();
        sb.append(entityCls.getSimpleName());
        Set<Object> mapped = new HashSet<Object>();
        toString(sb, entity, entityCls, "", mapped);
        log.warn(sb.toString()); // TODO
    }
    
    /************************************************************************* 
     * implementation
     *************************************************************************/
    
    @SuppressWarnings("unchecked")
    private static boolean shouldStop(Object entity, Set processedEntity){
        try{
            if(processedEntity.contains(entity)){
                return true;
            }
            processedEntity.add(entity);
        }
        catch(LazyInitializationException ex){
            // no need to continue, if lazy loading fails
            log.debug("Ignore updating when lazy loading fails.");
            // because this entity is not loaded, there can not be any changes
            // made to it, so it must be ok to stop updating for this
            // referenced entity.
            return true; 
        }
        catch(NullPointerException ex){
            // I found that in some cases, a null pointer exception is thrown
            // when a hibernate session does not exist (null) in lazy loading
            // is initializing. However, this NullPointerException is thrown
            // unwrapped, so the expected LazyInitializationException is not
            // seen.
            return true;
        }
        return false;
    }
    
    @SuppressWarnings("unchecked")
    protected static <T> void update(T entity, T sourceEntity, Class<?> entityCls, Set done, int indent, 
                                     boolean followCollection, boolean selfUpdate) throws DaoException {
        if(!selfUpdate && entity == sourceEntity){
            return;
        }
        indent++;
        System.out.println("CU: " + indentation(indent) + ">>>>Enter " + entityCls.getSimpleName() + "\n");
        if(log.isDebugEnabled()){
            log.debug(indentation(indent) + ">>>>Enter " + entityCls.getSimpleName() + "\n");
        }
        
        try {
            if(shouldStop(sourceEntity, done)){
                return; // bail
            }
            Field[] fields = entityCls.getDeclaredFields();
            Set<String> fieldsChanged = new HashSet<String>();
            
            for (Field field : fields) {
                System.out.println("CU: " + indentation(indent + 1) + entityCls.getSimpleName() + "." + field.getName() + "\n");
                String name = entityCls.getSimpleName() + "." + field.getName();
                if(name.equals("Instruction.id")) {
                    System.out.println("Hi there");                
               }
            
                if(log.isDebugEnabled()){
                    log.debug(indentation(indent + 1) + entityCls.getSimpleName() + "." + field.getName() + "\n");
                }
                
                // note, obtaining the setter must be deferred, as not every
                // field, such as collection, do not have setters.
                Class<?> fieldCls = field.getType();
                if(entity != sourceEntity && CommonUtil.isSimpleType(fieldCls)){
                    try{
                        if(updateSimpleField(entity, sourceEntity, entityCls, field)){
                            fieldsChanged.add(field.getName());
                        }
                    }
                    catch(Exception ex){
                        ex.printStackTrace();
                        if(ex.getClass().equals(org.hibernate.LazyInitializationException.class)
                          || ex.getCause().getClass().equals(org.hibernate.LazyInitializationException.class)){
                            // ignore lazy loading exception, it only happens when the
                            // referencedEntity in the source entity is never loaded,
                            // therefore, no update is needed.
                            log.debug("Ignore updating when lazy loading fails.");
                            break;
                        }
                    }
                }
                // if a field is an entity type, we need to check if the 
                // foreign key field(s) is changed, and update the object
                // reference accordingly.
                else if(isEntityClass(fieldCls)){ // _ToOne side
                    boolean updatedWithLazyLoadingError = updateEntityField(entity, sourceEntity, entityCls, field, fieldsChanged, done, indent, followCollection);
                    if(!updatedWithLazyLoadingError){
                        log.debug("Ignore updating when lazy loading fails.");
                        break;
                    }
                }
                else if(CommonUtil.isCollectionClass(fieldCls)){ // _ToMany
                    // although automatically population can be done here, 
                    // I choose not to do it for now until the real need
                    // arises. Until then, the caller (Controllers) must
                    // iterate through the mapped entity collection and 
                    // loadAndUpdate each member individually.
                    // 2009-8-6 RQ: make updating collection optional
                    if(followCollection){
                        updateCollectionField(entity, sourceEntity, entityCls, field, done, indent, followCollection);
                    }
                }
            }
            // now do the same thing for the super class
            Class<?> parentCls = entityCls.getSuperclass();
            if(!parentCls.equals(Object.class)){
                Set done2 = new HashSet();
                update(entity, sourceEntity, parentCls, done2, indent, followCollection, false);
            }
        }
        catch (Exception e) {
            if(DaoException.class.isAssignableFrom(e.getClass())){
                throw (DaoException)e;
            }
            else{
                throw new DaoException("Error occured update an entity", e);
            }
        }
        finally{
            if(log.isDebugEnabled()){
                log.debug(indentation(indent) + "<<<<Leaving " + entityCls.getSimpleName() + "\n");
            }
        }
    }
    
    /**
     * @param indent
     * @return
     */
    private static Object indentation(int indent) {
        StringBuilder sb = new StringBuilder();
        while(--indent > 0){
            sb.append("    ");
        }
        return sb.toString();
    }

    /**
     * Copy the value of <code>field</code> from <code>sourceEntity</code>
     * to <code>entity</code>.
     * @return true if the field is updated; otherwise false.
     */
    private static boolean updateSimpleField(Object entity, Object sourceEntity, Class<?> entityCls, Field field) throws Exception {
        Method setter = getSetter(entityCls, field);
        Method getter = getGetter(entityCls, field);
        // copy the value over
        Object valueSource = null;
        try{
            valueSource = getter.invoke(sourceEntity);
        }
        catch(Exception ex){
//            if(ex.getClass().equals(org.hibernate.LazyInitializationException.class)
//              || ex.getCause().getClass().equals(org.hibernate.LazyInitializationException.class)){
//            }
            throw ex;
        }
        Object value = getter.invoke(entity);
        if(CommonUtil.compare(valueSource, value) != 0){
            setter.invoke(entity, valueSource);
            log.warn("Updating field: " + field.getName()); // TODO
            return true;
        }
        else{
            return false;
        }
    }
    
    /**
     * load and update members of a collection
     * @return true if the field is updated; otherwise false.
     */
    // this method is not used so far, consider removal
    @SuppressWarnings("unchecked")
    protected static boolean updateCollectionField(Object entity, Object sourceEntity, Class<?> entityCls, 
                                                 Field field, Set done, int indent, boolean followCollection) throws Exception{
        Method getter = getGetter(entityCls, field);
        Collection colSource = null;
        try{
            colSource = (Collection)getter.invoke(sourceEntity);
        }
        catch(Exception ex){
            if(ex.getClass().equals(org.hibernate.LazyInitializationException.class)
              || ex.getCause().getClass().equals(org.hibernate.LazyInitializationException.class)){
                log.debug("Ignore updating when lazy loading fails.");
                return false;
            }
            else{
                throw ex;
            }
        }
        Collection col = (Collection)getter.invoke(entity);
        if(col.size() == 0){
            // empty collection, nothing to update
            return true;
        }
        Class<?> objClass = getMemberClass(col);
        for(Object obj: col){
            Serializable id = getId(obj);
            Object objSource = findMatchedObjectInCollection(colSource, id);
            
            update(obj, objSource, objClass, done, indent, followCollection, false);
        }
        
        return true;
    }
    
    /**
     * @param col
     * @return
     */
    @SuppressWarnings("unchecked")
    private static Class<?> getMemberClass(Collection col) {
        Iterator it = col.iterator();
        Object obj = it.next();
        Class<?> objClass = obj.getClass();
        // TODO verify
        return objClass;
    }

    /**
     * @param col
     * @param id
     * @return
     * @throws DaoException 
     */
    private static <T> T findMatchedObjectInCollection(Collection<T> col, Serializable id) throws DaoException {
        for(T obj: col){
            Serializable objId = getId(obj);
            if(CommonUtil.compare(objId, id) == 0){
                return obj;
            }
        }
        // cannot find a matching one, this should be an error condition
        return null;
    }

    @SuppressWarnings("unchecked")
    private static boolean updateEntityField(Object entity, Object sourceEntity, Class<?> entityCls, Field field, 
                                             Set<String> fieldsChanged, Set done, int indent, boolean followCollection) throws Exception{
        Method getter = getGetter(entityCls, field);
        Method setter = getSetter(entityCls, field);
        Class<?> fieldCls = field.getType();
        Class<?> keyCls = getKeyClass(fieldCls);
        String[] idFieldNames = null;
        try{
            idFieldNames = getForeignFieldNames(entityCls, field);
        }
        catch(MappingException ex){
            // there is a mapping for this field, log an error,
            // but continue
            log.error("Mapping error detected for field " + field.getName()
                      + " for entity type " + entityCls.getSimpleName());
        }
        if(idFieldNames != null && idFieldNames.length > 0){
            // only when id values are different
            // note, the foreign fields are not the id field in the
            // referenced entity, but separate foreign key fields
            Object[] foreignKeyValueSource = getForeignFieldValues(sourceEntity, idFieldNames);
            Object[] foreignKeyValue = getForeignFieldValues(entity, idFieldNames);
            // need to check two conditions, because some "simple"
            // field values may have been updated already.
            if(CommonUtil.compare(foreignKeyValueSource, foreignKeyValue) != 0
              || isAnyFieldValueChanged(fieldsChanged, idFieldNames)){
                // object identity changed, need to reload the object back
                if(isKeyValueValid(foreignKeyValueSource)){ // none null value
                    Serializable key = (Serializable)makeKeyInstance(keyCls, idFieldNames, foreignKeyValueSource);
                    // the referenced entity's foreign key value is changed,
                    // need to reload teh reference entity.
                    // for example, if a BranchId is modified in a JobOrder,
                    // then the related Branch object needs to be reload and
                    // set to JobOrder.
                    // On the other hand, if the input is null or not changed,
                    // then there is no need to reload and update, lazy loading
                    // should be good enough.
                    log.warn("Reloading referenced entity: " + field.getName()); // TODO
                    Object loaded = findById(fieldCls, key);
                    setter.invoke(entity, loaded);
                }
                else{
                    log.warn("Replacing referenced entity: " + field.getName() + " with null"); // TODO
                    setter.invoke(entity, (Object)null);
                }
            }
            // key values are not changed between the source and
            // target, if both null, nothing to do; otherwise we 
            // need to do call update() recursively
            else if(isKeyValueValid(foreignKeyValueSource)) { // none null
                Object referencedEntitySource = null;
                try{
                    referencedEntitySource = getter.invoke(sourceEntity);
                }
                // ignore lazy loading exception, it only happens
                // when the referencedEntity in the source entity
                // is never loaded, therefore, no update is needed.
                catch(Exception ex){
                    if(ex.getClass().equals(org.hibernate.LazyInitializationException.class)
                      || ex.getCause().getClass().equals(org.hibernate.LazyInitializationException.class)){
                        log.debug("Ignore updating when lazy loading fails.");
                        return false;
                    }
                    else{
                        throw ex;
                    }
                }
                if(referencedEntitySource != null){
                    Object referencedEntity = getter.invoke(entity);
                    if(referencedEntity != null){ 
                        log.warn("Updating referenced entity: " + field.getName()); // TODO
                        update(referencedEntity, referencedEntitySource, fieldCls, done, indent, followCollection, false);
                    }
                    else{
                        throw new DaoException("Failed to load entity. Data error.");
                    }
                }
                else {
                    // in the case where entity is a transient object with some
                    // foreign key values set, these foreign keys needs to be
                    // converted into entity objects.
                    Serializable key = (Serializable)makeKeyInstance(keyCls, idFieldNames, foreignKeyValueSource);
                    Object loaded = findById(fieldCls, key);
                    setter.invoke(entity, loaded);
                }
            }
            // else, nothing to do
        }
        return true;
    }

    /**
     * @param fieldsChanged
     * @param idFieldNames
     * @return
     */
    private static boolean isAnyFieldValueChanged(Set<String> fieldsChanged, String[] idFieldNames) {
        for(String idFieldName: idFieldNames){
            if(fieldsChanged.contains(idFieldName)){
                return true;
            }
        }
        return false;
    }

    /**
     * @param foreignKeyValueSource
     * @return
     */
    private static boolean isKeyValueValid(Object[] keyValues) {
        for(Object value: keyValues){
            if(value == null){
                return false;
            }
            if(value instanceof String){
                return !CommonUtil.isNullOrEmpty((String)value);
            }
        }
        return true;
    }

    /**
     * To find the foreign key fields in the given cls for the related entity
     * type that is reference by field <code>field</code>
     * @param cls The entity type within which we need to find those foreign
     * key fields.
     * @param field Is the reference field that points to a related entity type
     * @return
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked")
    protected static String[] getForeignFieldNames(Class<?> cls, Field field) throws DaoException {
        //// get hibernate mapping for the related entity class
        // Class<?> referencedEntityCls = field.getType();
        // PersistentClass classInfoForeign = DaoManager.getConfiguration().getClassMapping(referencedEntityCls.getName());
        // Property idProperty = classInfoForeign.getIdentifierProperty();

        PersistentClass classInfo = DaoManager.getConfiguration().getClassMapping(cls.getName());
        Property property = classInfo.getProperty(field.getName());
        // obtain the column names for the field
        Iterator it = property.getColumnIterator();
        List<String> columnNames = new ArrayList<String>();
        while(it.hasNext()){
            Column columnInfo = (Column)it.next();
            columnNames.add(columnInfo.getName());
        }
        
        // find the corresponding field names in cls
        Field[] fields = cls.getDeclaredFields();
        List<String> fieldNames = new ArrayList<String>();
        for(Field fld: fields){
            Property prop = classInfo.getProperty(fld.getName());
            if(prop.getColumnSpan() == 1 && CommonUtil.isSimpleType(fld.getType())){
                Column columnInfo = (Column)prop.getColumnIterator().next();
                if(columnNames.contains(columnInfo.getName())){
                    fieldNames.add(fld.getName());
                }
            }
        }
        if(fieldNames.size() != columnNames.size()){
            return null;
        }
        return fieldNames.toArray(new String[]{});
    }

    /**
     * @param keyCls
     * @param names not used
     * @param values
     * @return
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked")
    protected static <T> T makeKeyInstance(Class<T> keyCls, String[] names, Object[] values) throws DaoException {
        try {
            if(values.length == 1){
                return (T)values[0];
            }
            else{ // a composite key
                Field[] fields = keyCls.getDeclaredFields();
                String[] fieldNames = new String[fields.length];
                for(int k=0; k<fields.length; k++){
                    fieldNames[k] = fields[k].getName();
                }
                
                T key = keyCls.newInstance();
                for(int k=0; k<fieldNames.length; k++){
                    Field field = CommonUtil.getDeclaredField(keyCls, fieldNames[k]);
                    Method setter = getSetter(keyCls, field);
                    setter.invoke(key, values[k]);
                }
                return key;
            }
        }
        catch (Exception e) {
            throw new DaoException("Invalid field name", e);
        }
    }

    /**
     * Get the id class of the entity type represented by this field.
     * @param field
     * @return
     * @throws DaoException 
     */
    protected static Class<?> getKeyClass(Class<?> cls) throws DaoException {
        try {
            ClassMetadata meta = getMetadata(cls);
            String idName = meta.getIdentifierPropertyName();
            Field idField = CommonUtil.getDeclaredField(cls, idName);
            return idField.getType();
        }
        catch(Exception e){
            throw new DaoException("Cannot find Id class for entity " + cls.getSimpleName(), e);
        }
        /* alternative implementation
         * the following relies on annotation, which we do not always have
        Field[] fields = type.getDeclaredFields();
        // look for a single @Id field
        for (Field f : fields) {
            Id annId = f.getAnnotation(Id.class);
            if (annId != null) {
                // this is the one,
                return f.getType();
            }
        }
        throw new DaoException("Cannot find Id class for related entity " + type);
        */
    }

    protected static <T> Serializable getId(T entity) throws DaoException{
        Serializable pk = null;
        Class<?> cls = entity.getClass();
        String pkName = getMetadata(cls).getIdentifierPropertyName();
        try {
            Method getter = getGetter(cls, pkName);
            pk = (Serializable) getter.invoke(entity);
        }
        catch (Exception e) {
            throw new DaoException("Error occured when calculating primary key for entity class " + entity.getClass().getSimpleName(), e);
        }
        return pk;
    }
    
    /**
     * Extract the primary key from this entity, the primary key may
     * by a compsite key. 
     * This method relies on annotations.
     * 
     * @param <T>
     * @param <ID>
     * @param entity
     * @return
     */
    protected static <T> Serializable getId(T entity, Class<?> entityCls) throws DaoException {
        Serializable pk = null;
        // Inspect the entity object for fields annotated with @Id for single
        // field primary key or @IdClass for composite primary key.
        Field[] fields = entityCls.getDeclaredFields();
        try {
            // look for a single @Id field
            Object key = null;
            for (Field entityField : fields) {
                Id annId = entityField.getAnnotation(Id.class);
                if (annId != null) {
                    // find one @Id field in the entity,
                    if (key == null) { // there should be only one @Id field
                        Method getter = getGetter(entityCls, entityField);
                        key = getter.invoke(entity);
                        // no break here, because I want to raise an exception
                        // it there are more than one @id fields
                    }
                    else {
                        // there must not be more than one @Id field,
                        // raise an error
                        throw new DaoException("Invalud Entity class definition: " + entity.getClass().getSimpleName());
                    }
                }
            }
            if(key == null){
                // if no primary key is found, check the super class
                Class<?> parentCls = entityCls.getSuperclass();
                if(!parentCls.equals(Object.class)){
                    key = getId(entity, parentCls);
                }
            }
            // if still null, this is an error
            if(key == null){
                throw new DaoException("Error occured when calculating primary key for entity class " + entity.getClass().getSimpleName());
            }
            pk = (Serializable) key;
        }
        catch (Exception e) {
            throw new DaoException("Error occured when calculating primary key for entity class " + entity.getClass().getSimpleName(), e);
        }
        return pk;
    }

    /**
     * @param entityField
     * @return
     * @throws NoSuchMethodException 
     * @throws  
     */
    protected static Method getGetter(Class<?> cls, Field entityField){
        String fieldName = entityField.getName();
        String suffix = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        String methodName = null;
        Class<?> fieldType = entityField.getType();
        if(fieldType.isAssignableFrom(boolean.class) /*|| fieldType.isAssignableFrom(Boolean.class)*/){
            methodName = "is" + suffix;
            try{
                return cls.getMethod(methodName);
            }
            catch(NoSuchMethodException ex){
                // just give it a second chance to try
            }
        }

        methodName = "get" + suffix;
        try{
            return cls.getMethod(methodName);
        }
        catch(NoSuchMethodException ex){
            log.error("Cannot find Getter for entity field " + entityField.getName(), ex);
            return null;
        }
    }
    
    protected static Method getGetter(Class<?> cls, String fieldName) throws NoSuchFieldException {
        Field field = CommonUtil.getDeclaredField(cls, fieldName);
        return getGetter(cls, field);
    }
    
    /**
     * @param entityField
     * @return
     * @throws NoSuchMethodException 
     * @throws  
     */
    protected static Method getSetter(Class<?> cls, Field entityField){
        String name = entityField.getName();
        Class<?> fieldType = entityField.getType();
        name = "set" + Character.toUpperCase(name.charAt(0)) + name.substring(1);
        try{
            return cls.getMethod(name, fieldType);
        }
        catch(NoSuchMethodException ex){
            log.error("Cannot find Setter for entity field " + entityField.getName(), ex);
            return null;
        }
    }
    
    protected static Method getSetter(Class<?> cls, String fieldName) throws NoSuchMethodException, NoSuchFieldException {
        Field field = CommonUtil.getDeclaredField(cls, fieldName);
        return getSetter(cls, field);
    }

    protected static Object[] getForeignFieldValues(Object entity, String[] fieldNames) 
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, 
            NoSuchMethodException, InvocationTargetException{
        Object[] idValues = new Object[fieldNames.length];
        for(int k=0; k<fieldNames.length; k++){
            Field keyField = CommonUtil.getDeclaredField(entity.getClass(), fieldNames[k]);
            Method getter = getGetter(entity.getClass(), keyField);
            idValues[k] = getter.invoke(entity);
        }
        // what about super class?
        return idValues;
    }
    
    /**
     * @param fieldCls
     * @return
     */
    protected static boolean isEntityClass(Class<?> cls) {
        ClassMetadata meta = getMetadata(cls);
        return meta != null;
    }

    private static ClassMetadata getMetadata(Class<?> cls){
        String clsName = cls.getName();
        int pos = clsName.indexOf("$$");
        if(pos > 0){
            clsName = clsName.substring(0, pos);
        }
        return DaoManager.getSessionFactory().getClassMetadata(clsName);
    }
    
    protected static void toString(StringBuilder sb, Object entity, Class<?> entityCls, String prefix, Set<Object> mapped) {
        if(mapped.contains(entity)){
            return;
        }
        mapped.add(entity);
        Field[] fields = entityCls.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldCls = field.getType();
            String nextPrefix = prefix.length() == 0 ? fieldName : prefix + "." + fieldName;
            sb.append(nextPrefix).append(": ");
            Method getter = getGetter(entityCls, field);
            if (getter != null) {
                try {
                    Object fieldValue = getter.invoke(entity);
                    if (CommonUtil.isSimpleType(fieldCls)) {
                        sb.append(fieldValue).append("\n");
                    }
                    else if (CommonUtil.isEntityClass(fieldCls)) {
                        if(fieldValue == null){
                            sb.append("[null]\n");
                        }
                        else if(mapped.contains(fieldValue)){
                            sb.append("[[")
                              .append(fieldCls.getSimpleName())
                              .append("]]\n");
                        }
                        else{
                            toString(sb, fieldValue, fieldCls, nextPrefix, mapped);
                        }
                    }
                    else if(CommonUtil.isCollectionClass(fieldCls)){
                        sb.append("[Set]\n");
                    }
                    else{
                        // unspecified
                        sb.append("\n");
                    }
                }
                catch(LazyInitializationException ex){
                    sb.append("[Lazy not loaded]\n");
                }
                catch (Exception ex) {
                    sb.append(ex.getClass().getSimpleName()).append("\n");
                }
            }
            else {
                sb.append("[No getter]\n");
            }
        }
    }

    protected static void toStringValueMap(Map<String, String> map, Object entity, Class<?> entityCls, String prefix, Set<Object> mapped){
        if(mapped.contains(entity)){
            return;
        }
        mapped.add(entity);
        Field[] fields = entityCls.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            Class<?> fieldCls = field.getType();
            String key = prefix.length() == 0 ? fieldName : prefix + "." + fieldName;
            Method getter = getGetter(entityCls, field);
            if (getter != null) {
                try {
                    Object fieldValue = getter.invoke(entity);
                    if (CommonUtil.isSimpleType(fieldCls)) {
                        map.put(key, fieldValue.toString());
                    }
                    else if (CommonUtil.isEntityClass(fieldCls)) {
                        if(fieldValue == null){
                            map.put(key, "null");
                        }
                        else if(mapped.contains(fieldValue)){
                            map.put(key, "[[" + fieldCls.getName() + "]]");
                        }
                        else{
                            toStringValueMap(map, fieldValue, fieldCls, key, mapped);
                        }
                    }
                }
                catch(LazyInitializationException ex){
                    map.put(key, "[Lazy not loaded]");
                }
                catch (Exception ex) {
                    map.put(key, ex.getClass().getSimpleName());
                }
            }
            else {
                map.put(key, "[No getter]");
            }
        }
    }
}
