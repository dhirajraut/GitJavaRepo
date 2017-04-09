/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.util.CommonUtil;

/**
 * A simple DAO implementation that does not use JDBC or Hibernate.
 * <p>
 * All objects are created and managed in memory. This implementation maintains
 * a map for all the entities of one type. Whenever an entity object is
 * requested, it first tries to find an object in the map. If not found, a new
 * object of the given type will be created and put into the map, so it can be
 * used later. This mechanism gurantees that there is only one instance of every
 * business entity in the scope of the entire application.
 * 
 * @author richard.qin
 */
public class MemoryDao<T> extends AbstractDao<T> {

    protected Map<Serializable, T> objectPool;

    public MemoryDao(Class<T> cls) {
        super(cls);
        objectPool = new HashMap<Serializable, T>();
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#count(com.intertek.phoenix.dao.QueryInfo)
     */
    
    public int count(QueryInfo query) throws DaoException {
        List<T> result = this.searchInternal(query);
        return result.size();
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#count(org.hibernate.criterion.Example)
     */
    
    public int count(T example) throws DaoException {
        List<T> result = this.searchInternal(example);
        return result.size();
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#find(java.lang.Class, com.intertek.phoenix.dao.PrimaryKey)
     */
    
    public <ID extends Serializable> T find(ID id) throws DaoException {
        T obj = objectPool.get(id);
        if (obj != null) {
            populateEntityReferences(obj, obj.getClass());
        }
        return obj;
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#find(java.lang.Class,
     *      java.util.Set)
     */
     
    public <ID extends Serializable> List<T> find(List<ID> ids) throws DaoException {
        List<T> results = new ArrayList<T>();
        for (ID id : ids) {
            results.add(find(id));
        }
        return results;
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#find(java.lang.Class, long[])
     */
    
    public List<T> find(long... ids) throws DaoException {
        List<T> results = new ArrayList<T>();
        for (long id : ids) {
            results.add(find(id));
        }
        return results;
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#findAll(java.lang.Class)
     */
    
    public List<T> findAll() {
        List<T> results = new ArrayList<T>();
        results.addAll(objectPool.values());
        return results;
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#flush()
     */
    
    public void flush() throws DaoException{
        // in memory dao needs no implementation
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#refresh(java.lang.Class)
     */
    
    public T refresh(T entity) {
        // in memory dao needs no implementation
        return entity;
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#remove(java.lang.Object)
     */
    
    public boolean remove(T entity) throws DaoException {
        Serializable pk = getPrimaryKey(entity, entity.getClass());
        return removeById(pk);
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#remove(java.util.Set)
     */
    
    public void remove(List<T> entities) throws DaoException {
        for (T entity : entities) {
            remove(entity);
        }
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeById(java.lang.Class, com.intertek.phoenix.dao.PrimaryKey)
     */
    
    public <ID extends Serializable> boolean removeById(ID id) {
        T obj = objectPool.remove(id);
        
        if(obj != null){
            // If the entity has some child objects that are marked "cascade", they need to be 
            // removed accordingly.
            // TODO
            
            return true;
        }
        return false;
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeById(java.lang.Class, long)
     */
    
    public boolean removeById(long id) {
        return removeById(new Long(id));
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeByIds(java.lang.Class, java.util.List)
     */
    
    public <ID extends Serializable> void removeByIds(List<ID> ids) {
        for (ID id : ids) {
            removeById(id);
        }
    }

    /**
     * @see com.intertek.phoenix.dao.Dao#removeByIds(java.lang.Class, long[])
     */
    
    public void removeByIds(long... ids) {
        throw new UnsupportedOperationException();
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#saveOrUpdate(java.util.Collection)
     */
    
    public List<T> saveOrUpdate(List<T> entities) throws DaoException {
        List<T> results = new ArrayList<T>();
        for (T entity : entities) {
            results.add(saveOrUpdate(entity));
        }
        return results;
    }

   /**
     * @see com.intertek.phoenix.dao.Dao#saveOrUpdate(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    
    public T saveOrUpdate(T entity) throws DaoException{
        // since every "persisted" object is maintained in the object pool,
        // there is no need to support update, as everything is automatically
        // up to date. For a new object, just add it to the pool.
        Serializable pk = getPrimaryKey(entity, entity.getClass()); // automatic key generation may kick in
        if (!objectPool.containsKey(pk)) { 
            objectPool.put(pk, entity);
            
            // if an entity has some child objects that are also new, make sure
            // that those objects are "persisted" too
            try {
                // the following method does not go deep enough
                //Field[] fields = entity.getClass().getDeclaredFields();
                List<Field> fields = new ArrayList<Field>();
                fields = CommonUtil.getAllDeclaredFields(fields, entity.getClass());
                for (Field field : fields) {
                    Class<?> fieldCls = field.getType();
                    Object fieldValue = null;
                    field.setAccessible(true);
                    fieldValue = field.get(entity);
                    if (!DaoUtil.isValueNullEquivalent(fieldValue)) {
                        if (CommonUtil.isEntityClass(fieldCls)){ // && !isForeignSide(field)) {
                            Dao dao = DaoManager.getDao(fieldValue.getClass());
                            dao.saveOrUpdate(fieldValue);
                        }
                        else if (CommonUtil.isCollectionClass(fieldCls)) {
                            Collection col = (Collection)fieldValue;
                            Class<?> entityCls = getCollectionEntityClass(field);
                            if(CommonUtil.isEntityClass(entityCls)){
                                // entityCls may be abstract, so the next line is an error
                                // Dao dao = DaoManager.getDao(entityCls);
                                for(Object obj: col){
                                    Dao dao = DaoManager.getDao(obj.getClass());
                                    dao.saveOrUpdate(obj);
                                }
                            }
                        }
                        else if (isMapClass(fieldCls)) {
                            // not supported
                        }
                    }
                }
            }
            catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            catch (PhoenixException e) {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else{
            // merge the entity with the one already been managed
            // TODO
        }
        return entity;
    }

    /**
     * @param fieldCls
     * @return
     */
    private boolean isMapClass(Class<?> cls) {
        return Map.class.isAssignableFrom(cls);
    }

//    /**
//     * @param fieldCls
//     * @return
//     */
//    private boolean isCollectionClass(Class<?> cls) {
//        return Collection.class.isAssignableFrom(cls);
//    }
//
//    /**
//     * Check if cls represents an entity class.
//     * @param fieldCls
//     * @return
//     */
//    private boolean isEntityClass(Class<?> cls) {
//        Annotation anno = cls.getAnnotation(Entity.class);
//        if(anno != null){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
//
//    /**
//     * Check if the field in the entity is a reference to another entity 
//     * @param fieldCls
//     * @return
//     */
//    private boolean isMappedField(Field field) {
//        Annotation[] annos = field.getAnnotations();
//        for(Annotation anno: annos){
//            Class<? extends Annotation> annoCls = anno.annotationType();
//            if (annoCls.equals(ManyToOne.class)
//                || annoCls.equals(OneToOne.class)
//                || annoCls.equals(OneToMany.class)
//                || annoCls.equals(ManyToMany.class)){
//                return true;
//            }
//        }
//        return false;
//    }
//
    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#search(com.intertek.phoenix.dao.QueryInfo)
     */
    
    public List<T> search(QueryInfo query) throws DaoException {
        // round up some candidates
        List<T> result = searchInternal(query);
        Iterator<T> it = result.iterator();
        while(it.hasNext()) {
            T entity = it.next();
            // resolve all the referenced objects
            populateEntityReferences(entity, entity.getClass());
            
            // check the join conditions
            try{
                if(satisfy(entity, query, false, true) == false){
                    it.remove();
                }
            }
            catch(Exception ex){
                throw new DaoException("Search error when checking Join conditions.", ex);
            }
        }
        Pagination pagination = query.getPagination();
        if (pagination != null){
            int totalCount = result.size();
            pagination.setTotalRecord(totalCount);
            int currentPageNum = pagination.getCurrentPageNum();
            int numInPage = pagination.getNumInPage();
            int startPos = (currentPageNum -1 ) * numInPage;
            int endPos = startPos + numInPage > totalCount ? totalCount: startPos +numInPage;
            result = result.subList(startPos, endPos);
        }
        return result;
    }
    
    /**
     * Same as search(QueryInfo query), but without eager fetching
     * @param query
     * @return
     * @throws DaoException 
     */
    protected List<T> searchInternal(QueryInfo query) throws DaoException{
        List<T> result = new ArrayList<T>();
        for(T entity: objectPool.values()){
            // check the query condition
            try{
                if(satisfy(entity, query, true, false)){
                    // check the join to fetch referenced objects
                    result.add(entity);
                }
            }
            catch(Exception ex){
                throw new DaoException("Search error.", ex);
            }
        }
        return result;
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#search(org.hibernate.criterion.Example)
     */
    
    public List<T> search(T example) throws DaoException {
        QueryInfo query = DaoUtil.exampleToQuery(example);
        return search(query);
    }
    
    /**
     * Same as search(T example), but without the eager fetching
     * @param example
     * @return
     * @throws DaoException 
     */
    protected List<T> searchInternal(T example) throws DaoException{
        QueryInfo query = DaoUtil.exampleToQuery(example);
        return searchInternal(query);
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#searchUnique(com.intertek.phoenix.dao.QueryInfo)
     */
    
    public T searchUnique(QueryInfo query) throws DaoException {
        List<T> result = this.search(query);
        if(result.size() == 1){
            for(T one: result){
                return one;
            }
        }
        throw new DaoException("No or multiple objects have been found for this entity type.");
    }

    /**
     * @throws DaoException 
     * @see com.intertek.phoenix.dao.Dao#searchUnique(org.hibernate.criterion.Example)
     */
    
    public T searchUnique(T example) throws DaoException {
        List<T> result = this.search(example);
        if(result.size()==0){
            return null;
        }
        else if(result.size() == 1){
            for(T one: result){
                return one;
            }
        }
        throw new DaoException("No or multiple objects have been found for this entity type.");
    }

    /**
     * Get the primary key for the given entity object.
     * <p>If the entity class does not define its own primary key using @Id annotation,
     * and if the entity class extends another entity class, the the parent class's
     * primary key will be used. Same for @IdClass.
     * <p>Note, I could used getAllDeclaredFields() to avoid the recursive call, but it 
     * can be tricky here. 
     * <p>Note, I really want to use Class<? super T> for the second argument, but could
     * not make it work. Need to revisit it.
     * 
     * @param entity
     * @return
     * @throws DaoException
     */
    protected Serializable getPrimaryKey(T entity, Class<?> entityCls) throws DaoException {
        Serializable pk = null;
        // Inspect the entity object for fields annotated with @Id for single
        // field primary key or @IdClass for composite primary key.
        // @EmbeddedId and @Embeddable are not used in this implementation.
        // Check the wiki page for more technical details.
        Field[] fields = entityCls.getDeclaredFields();
        try {
            IdClass ann = entityCls.getAnnotation(IdClass.class);
            if (ann != null) {
                // the entity class is marked with IdClass, so look for a
                // composite key
                Class<?> keyCls = ann.value();
                Object compKey = keyCls.newInstance();
                for (Field entityField : fields) {
                    Id annId = entityField.getAnnotation(Id.class);
                    if (annId != null) {
                        entityField.setAccessible(true);
                        // find one @Id field in the entity,
                        // get its value and set it to the compKey object
                        Object value = entityField.get(entity);
                        Field keyField = keyCls.getDeclaredField(entityField.getName());
                        keyField.setAccessible(true);
                        keyField.set(compKey, value);
                    }
                }
                pk = (Serializable) compKey;
            }
            else { // look for a single @Id field
                Object key = null;
                for (Field entityField : fields) {
                    Id annId = entityField.getAnnotation(Id.class);
                    if (annId != null) {
                        // find one @Id field in the entity,
                        if (key == null) { // there should be only one @Id field
                            entityField.setAccessible(true);
                            key = entityField.get(entity);
                            // if key is null and @GeneratedValue is defined, then
                            // automatically generate a key value
//                            if(entityField.getType().equals(long.class) && ((Long)key).longValue() == 0){
//                                throw new DaoException("Long primary key is undefined for entity " + entity.getClass());
//                            }
                            // for a long key, 0 means an unassigned value
                            // for an object key, null means an unassigned value
                            if(key == null || (entityField.getType().equals(long.class) && ((Long)key).longValue() == 0)){
                                GeneratedValue gv = entityField.getAnnotation(GeneratedValue.class);
                                if(gv != null && entityField.getType().equals(long.class)){
                                    key = CommonUtil.generateId(); // autoboxing
                                    entityField.set(entity, key);
                                }
                                else{
                                    // this is bad, because the primary key value is not set
                                    // and there is no generator designated to it
                                    throw new DaoException("Object primary key is undefined for entity " + entity.getClass());
                                }
                            }
                        }
                        else {
                            // there must not be more than on @Id field,
                            // raise an error
                            throw new DaoException("Invalud Entity class definition: " + entity.getClass().getSimpleName());
                        }
                    }
                }
                if(key == null){
                    // if no primary key is found, check the super class
                    Class<?> parentCls = entityCls.getSuperclass();
                    if(!parentCls.equals(Object.class)){
                        key = getPrimaryKey(entity, parentCls);
                    }
                }
                // if still null, this is an error
                if(key == null){
                    throw new DaoException("Error occured when calculating primary key for entity class " + entity.getClass().getSimpleName());
                }
                pk = (Serializable) key;
            }
        }
        catch (InstantiationException e) {
            throw new DaoException("Error occured when calculating primary key for entity class " + entity.getClass().getSimpleName(), e);
        }
        catch (IllegalAccessException e) {
            throw new DaoException("Error occured when calculating primary key for entity class " + entity.getClass().getSimpleName(), e);
        }
        catch (NoSuchFieldException e) {
            throw new DaoException("Error occured when calculating primary key for entity class " + entity.getClass().getSimpleName(), e);
        }
        return pk;
    }

    protected T generateNewEntity(Class<T> type) throws DaoException {
        T newObj = null;
        try {
            newObj = type.newInstance();
            populateNewEntity(newObj);
        }
        catch (InstantiationException e) {
            throw new DaoException("Error occured when creating new entity: " + type.getSimpleName(), e);
        }
        catch (IllegalAccessException e) {
            throw new DaoException("Error occured when creating new entity: " + type.getSimpleName(), e);
        }

        return newObj;
    }

    private boolean satisfy(Object entity, QueryInfo query, boolean checkFilter, boolean checkJoin) 
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException{
        // flatten all the filters and sibling filters 
        boolean ok = true;
        if(checkFilter){
            // reset ok, otherwise there is a logic error
            ok = false;
            List<List<FilterInfo>> filterGroup = new ArrayList<List<FilterInfo>>();
            calculateFilterGroup(filterGroup, query);
//            boolean ok = false;
            if(filterGroup.size() == 0){
                // automatically match if there is no filter
                ok = true;
            }
            else{
                for(List<FilterInfo> filters: filterGroup){
                    if(satisfy(entity, filters)){
                        ok = true;
                    }
                }
            }
        }
        // check joins
        // TODO
        if(ok && checkJoin){
            for(QueryInfo joinQuery: query.getInnerJoinedQueryInfo()){
                // get the inner joined object
                String joinFieldName = joinQuery.getTargetFieldName();
                Field joinField = CommonUtil.getDeclaredField(entity.getClass(), joinFieldName);
                joinField.setAccessible(true);
                Object joinObject = joinField.get(entity);
                // check the inner join condition
                if(joinObject != null && !satisfy(joinObject, joinQuery, true, checkJoin)){
                    ok = false;
                    break;
                }
            }
        }
        if(ok && checkJoin){
            for(QueryInfo joinQuery: query.getLeftJoinedQueryInfo()){
                // get the left joined object
                String joinFieldName = joinQuery.getTargetFieldName();
                Field joinField = CommonUtil.getDeclaredField(entity.getClass(), joinFieldName);
                joinField.setAccessible(true);
                Object joinObject = joinField.get(entity);
                // check the Left join condition
                if(joinObject == null || !satisfy(joinObject, joinQuery, true, checkJoin)){
                    ok = false;
                    break;
                }
            }
        }
        return ok;
    }
    
    /**
     * Some filters may be set to the super classes fields.
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    private boolean satisfy(Object entity, List<FilterInfo> filters) 
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = null;
        if(filters.size() == 0){
            // when no filter's defined, no object will match
            return false;
        }
        for (FilterInfo filter : filters) {
            field = CommonUtil.getDeclaredField(entity.getClass(), filter.getName());
//                field = entity.getClass().getDeclaredField(filter.getName());
            field.setAccessible(true);
            Object value = field.get(entity);
            Object filterValue = filter.getValue();
            switch(filter.getOp()){
                case NOT_EQUAL:
                    if (filterValue.equals(value)) {
                        return false;
                    }
                    break;
                case GREATER_THAN:
                    if (((Number)filterValue).doubleValue() <= ((Number)value).doubleValue()) {
                        return false;
                    }
                    break;
                case GREATER_OR_EQUAL:
                    if (((Number)filterValue).doubleValue() < ((Number)value).doubleValue()) {
                        return false;
                    }
                    break;
                case LESS_THAN:
                    if (((Number)filterValue).doubleValue() >= ((Number)value).doubleValue()) {
                        return false;
                    }
                    break;
                case LESS_OR_EQUAL:
                    if (((Number)filterValue).doubleValue() > ((Number)value).doubleValue()) {
                        return false;
                    }
                    break;
                case LIKE:
                    String str = ((String)filterValue).replaceAll("%", ""); 
                    if(!((String)value).contains(str)){
                        return false;
                    }
                    break;
                case IN:
                    if(!CommonUtil.isOneOf(value, ((String)filterValue).split(","))){
                        return false;
                    }
                    break;
                case NOT_IN:
                    if(CommonUtil.isOneOf(value, ((String)filterValue).split(","))){
                        return false;
                    }
                    break;
                case IS_NULL:
                    if(value != null){
                        return false;
                    }
                    break;
                case IS_NOT_NULL:
                    if(value == null){
                        return false;
                    }
                    break;
                case EQUAL:
                default:
                    if (!filterValue.equals(value)) {
                        return false;
                    }
                    break;
            }

        }
        return true;
    }
    
    // this method needs to be updated if QueryInfo is enhanced to support 
    // more complicated constructs
    private void calculateFilterGroup(List<List<FilterInfo>> filterGroup, QueryInfo query){
        if(query.getFilters() != null){
            filterGroup.add(query.getFilters());
        }
        for(QueryInfo sibling: query.getSiblings()){
            calculateFilterGroup(filterGroup, sibling);
        }
    }
    
    protected void populateNewEntity(T entity) throws IllegalArgumentException, IllegalAccessException{
        // TODO populate the new entity with some dummy data, randomly generated
    }
    
    /**
     * After an entity is loaded, make sure that all its referenced objects are 
     * instantiated. this behavior is equivalent to eager loading. There is no
     * need to support lazy loading here.
     * <p>If the entity class extends from another entity class, then the super
     * class must also be checked. It is needed to support entity inherence.
     * <p>Instead of making it recursive, I think getAllDeclaredFields should work
     * too.
     * 
     * @param entity
     * @param entityCls Type of the entity object, which can be a super class of the
     * class of the entity object.
     * @throws DaoException
     */
    protected void populateEntityReferences(Object entity, Class<?> entityCls) throws DaoException {
        Field[] fields = entityCls.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                // if a field is an entity type, and it is not populated,
                // then search the matching entity to populate it
                Class<?> fieldCls = field.getType();
                if (field.get(entity) == null && CommonUtil.isMappedField(field)){
                    if(CommonUtil.isEntityClass(fieldCls)){ // this is one of the ToOne scenarios
                        Object referencedObj = null;
                        String[][] joinFieldNames = null;
                        if(CommonUtil.isForeignSide(field)){ // ManyToOne, OneToOne (foreign side)
                            // find that object by the foreign key, it can be a composit key
                            joinFieldNames = CommonUtil.getJoinColumnFieldNames(field);
                        }
                        else{ // OneToOne (reverse side)
                            // go to the foreign side to find the foreign keys
                            Field fieldMappedBy = CommonUtil.getFieldMappedBy(field);
                            joinFieldNames = CommonUtil.getReverseJoinColumnFieldNames(fieldMappedBy);
                        }
                        referencedObj = findUniqueReferencedEntity(fieldCls, entity, entityCls, joinFieldNames);
                        if(referencedObj != null){
                            // add reference to the referenced entity
                            field.set(entity, referencedObj);
                            // make sure that the referenced entity also has a reference back to this object
                            // in the case of many to one, this entity should be added to the matching 
                            // collection in the referenced entity on the one side.
                            setReverseReferencedEntity(entity, referencedObj, field);
                            
                            // do eager loading on the referenced object too
                            populateEntityReferences(referencedObj, fieldCls);
                        }
                    }
                    else if(CommonUtil.isCollectionClass(fieldCls)){ // this is one of the ToMany scenarios
                        Set<?> referencedObjs = null;
                        Class<?> referencedEntityCls = getCollectionEntityClass(field);
                        // for the time being, do not consider many-to-many case, 
                        // OneToMany
                        // go to the foreign side to find the foreign keys
                        Field fieldMappedBy = CommonUtil.getFieldMappedBy(field, referencedEntityCls);
                        String[][] joinFieldNames = CommonUtil.getReverseJoinColumnFieldNames(fieldMappedBy);
                        referencedObjs = findReferencedEntity(referencedEntityCls, entity, entityCls, joinFieldNames);
                        
                        if(referencedObjs != null){
                            field.set(entity, referencedObjs);
                            // every member of the referencedObjs collection may have to a pointer to this Entity too
                            setReverseReferencedEntity(entity, referencedObjs, field, referencedEntityCls);
                            
                            // do eager loading on every referenced object
                            for(Object obj: referencedObjs){
                                populateEntityReferences(obj, referencedEntityCls);
                            }
                        }
                    }
                    // else if(isMapClass(fieldCls))
                    // Note, map is not supported yet
                }
            }
            // now do the same thing for the super class
            Class<?> parentCls = entityCls.getSuperclass();
            if(!parentCls.equals(Object.class)){
                populateEntityReferences(entity, parentCls);
            }
        }
        catch (IllegalAccessException e) {
            throw new DaoException("Error occured when associating object relationship: " + entity.getClass().getSimpleName(), e);
        }
        catch (NoSuchFieldException e) {
            throw new DaoException("Error occured when associating object relationship: " + entity.getClass().getSimpleName(), e);
        }
        catch (InstantiationException e) {
            throw new DaoException("Error occured when associating object relationship: " + entity.getClass().getSimpleName(), e);
        }
        catch (ClassNotFoundException e) {
            throw new DaoException("Error occured when associating object relationship: " + entity.getClass().getSimpleName(), e);
        }
        catch (PhoenixException e) {
            throw new DaoException("Error occured when associating object relationship: " + entity.getClass().getSimpleName(), e);
        }
    }

    /**
     * @param entity the object that is on the one side of the one-to-many relationship
     * @param referencedObjs a set of entities that are on the many side of the relationship
     * @param field the Field object in entity that owns the relationship
     * @param referencedEntityCls the type of the element entity in the set
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     */
    private void setReverseReferencedEntity(Object entity, Set<?> referencedObjs, Field ownerField, Class<?> referencedEntityCls) 
            throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        OneToMany otm = ownerField.getAnnotation(OneToMany.class);
        if(otm != null){
            String fieldName = otm.mappedBy();
            //Field referenceField = referencedEntityCls.getDeclaredField(fieldName);
            Field referenceField = CommonUtil.getDeclaredField(referencedEntityCls, fieldName);
            referenceField.setAccessible(true);
            for(Object referenced: referencedObjs){
                referenceField.set(referenced, entity);
            }
        }
    }    
    /**
     * @param entity
     * @param referencedObj
     * @param referencedField the Field object in entity that points to referencedObj
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws ClassNotFoundException 
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked")
    private void setReverseReferencedEntity(Object entity, Object referencedObj, Field referencedField) 
            throws IllegalArgumentException, IllegalAccessException, DaoException, ClassNotFoundException {
        String referencedFieldName = referencedField.getName(); // this name is needed to resolve the linkage
        //Field[] fields = referencedObj.getClass().getDeclaredFields();
        List<Field> fields = new ArrayList<Field>();
        CommonUtil.getAllDeclaredFields(fields, referencedObj.getClass());
        // find the field that has OneToMany or OneToOne annotation with mappedBy equals referencedFieldName
        for (Field field: fields){
            OneToOne oto = field.getAnnotation(OneToOne.class);
            field.setAccessible(true);
            if(oto != null){
                String mappedBy = oto.mappedBy();
                if(referencedFieldName.equals(mappedBy) && field.getName().equals(entity.getClass().getSimpleName())){
                    // the matching field is found, set it value
                    field.set(referencedObj, entity);
                    break;
                }
            }
            else{
                OneToMany otm = field.getAnnotation(OneToMany.class);
                if(otm != null){
                    String mappedBy = otm.mappedBy();
                    if(referencedFieldName.equals(mappedBy) 
                            && getCollectionEntityClass(field).equals(referencedObj.getClass())){
                        Set entitySet = (Set)field.get(referencedObj);
                        if(field.get(referencedObj) == null){
                            // need to instantiate a collection
                            entitySet = new HashSet();
                            field.set(referencedObj, entitySet);
                        }
                        entitySet.add(entity);
                        break;
                    }
                }
            }
        }
    }

    /**
     * @param field
     * @return
     * @throws ClassNotFoundException 
     * @throws PhoenixException 
     */
    private Class<?> getCollectionEntityClass(Field field) throws ClassNotFoundException, DaoException {
        Class<?> entityCls = null;
        Class<?> cls = field.getType();
        if(Collection.class.isAssignableFrom(cls)){
            Type colType = field.getGenericType();
            if(colType instanceof ParameterizedType){
                ParameterizedType parameterizedType = (ParameterizedType)colType;
                Type[] typeArg = parameterizedType.getActualTypeArguments();
                if(typeArg.length == 1){
                    entityCls = (Class<?>)typeArg[0];
                }
                else{
                    // this must not happen
                    throw new DaoException("This must not happen.");
                }
            }
            else{
                // this is not a generic collection, must rely on annotation
                OneToMany otm = field.getAnnotation(OneToMany.class);
                if(otm != null){
                    entityCls = otm.targetEntity();
                }
                else{
                    ManyToMany mtm = field.getAnnotation(ManyToMany.class);
                    if(mtm != null){
                        entityCls = mtm.targetEntity();
                    }
                    else{
                        throw new DaoException("Invalid mapping: " + this.getEntityClass().getName());
                    }
                }
            }
        }
        return entityCls;
    }

    /**
     * Find the referenced entity from the foreign side.
     * <p>If the entity class extends another entity class, then the super class
     * will also be examined.
     * <p>If the entity class is polymorphic, all the objects of the child classes 
     * will be examined.
     * 
     * @param fieldCls The target class
     * @param entity
     * @param entityCls The type of the entity object. The class can be the super
     * class of the entity class.
     * @param joinColumns
     * @return
     * @throws NoSuchFieldException 
     * @throws IllegalArgumentException 
     * @throws InstantiationException 
     * @throws IllegalAccessException 
     * @throws DaoException 
     */
    @SuppressWarnings("unchecked") 
    // I have trouble to infer generics type in dao.search(), as the exact type parameter 
    // is not known at the compile time. RQ
    private Set<?> findReferencedEntity(Class<?> refCls, Object entity, Class<?> entityCls, String[][] joinColumns) 
            throws NoSuchFieldException, IllegalArgumentException, InstantiationException, 
                   IllegalAccessException, DaoException {
        Set result = new HashSet();
        // get the key values from the owner entity
        Object[] foreignKeyValues = new Object[joinColumns.length];
        for(int k=0; k<joinColumns.length; k++){
//            Field keyField = entityCls.getDeclaredField(joinColumns[k][0]);
            Field keyField = CommonUtil.getDeclaredField(entityCls, joinColumns[k][0]);
            keyField.setAccessible(true);
            foreignKeyValues[k] = keyField.get(entity);
        }
        // the target class may be a super class of some concrete entities, all the 
        // sub class entities must be examined to support polymorphism
        Class<?>[] targetClasses = getTargetClass(refCls);
        for(Class<?> targetCls: targetClasses){
            // note, it is safe to cast here, as MemoryDao only deals with MemoryDao
            MemoryDao dao = getMemoryDao(targetCls);
            
            // ther are two ways to define the query here, QBE or QueryInfo. I opt to use
            // QueryInfo for now, QBE has been tried and works, but I think it is not as 
            // efficient as QueryInfo.
            
            QueryInfo query = new QueryInfo(targetCls);
            for(int k=0; k<joinColumns.length; k++){
                if(foreignKeyValues[k] != null){
                    query.addFilter(joinColumns[k][1], foreignKeyValues[k]);
                }
            }
            result.addAll(dao.searchInternal(query));
            
            /*
            // create a new instance of the referenced entity, and set the 
            // foreign key values on it
            Object referenced = targetCls.newInstance();
            for(int k=0; k<joinColumns.length; k++){
                // Note that the field may be declared on the super class
                Class<?> cls = targetCls;
                while(!cls.equals(Object.class)){ // check, this loop may be redundant
                    try{
                        // Field foreignKeyField = cls.getDeclaredField(joinColumns[k][1]);
                        Field foreignKeyField = CommonUtil.getDeclaredField(cls, joinColumns[k][1]);
                        foreignKeyField.setAccessible(true);
                        foreignKeyField.set(referenced, foreignKeyValues[k]);
                        // exit the while loop
                        break;
                    }
                    catch(NoSuchFieldException e){
                        cls = cls.getSuperclass();
                    }
                }
            }
            // find the matching entity, query by example
            result.addAll(dao.searchInternal(targetCls.cast(referenced)));
            */
        }
        return result;
    }

    /**
     * Find all the entity subclasses of the given super class
     * @param refCls
     * @return
     */
    private Class<?>[] getTargetClass(Class<?> refCls) {
        Inheritance anno = refCls.getAnnotation(Inheritance.class);
        if(anno == null){
            return new Class<?>[]{refCls};
        }
        else{
            // for now, just hard code all the known polymophic entity classes
//            if(refCls.equals(Invoice.class)){
//                return new Class<?>[]{
//                    CEInvoice.class,
//                    DepositInvoice.class,
//                    CreditInvoice.class,
//                };
//            }
//            else if(refCls.equals(OrderLineItem.class)){
//                return new Class<?>[]{
//                    CEJobOrderLineItem.class,
//                    ExpenseLineItem.class,
//                    SplitLineItem.class,
//                };
//            }
        }
        return null;
    }

    /**
     * @param targetCls
     * @return
     */
    private MemoryDao<?> getMemoryDao(Class<?> cls) {
        GenericDao<?> dao = (GenericDao<?>)DaoManager.getDao(cls);
        return (MemoryDao<?>)dao.getDelegate();
    }

    private Object findUniqueReferencedEntity(Class<?> targetCls, Object entity, Class<?> entityCls, String[][] joinColumns) 
            throws NoSuchFieldException, IllegalArgumentException, 
                   InstantiationException, IllegalAccessException, DaoException {
        Set<?> result = findReferencedEntity(targetCls, entity, entityCls, joinColumns);
        if(result.size() == 1){
            for(Object one: result){
                return one;
            }
        }
        else if(result.size() > 1){
            throw new ReferentialIntegrityException();
        }
        return null;
    }

    /**
     * Find all realted object type.
     * @param exclude 
     * @return A set of classes that are not on the foreign side of an entity
     * @throws DaoException 
     */
    protected Set<Class<?>> findAllRelatedEntityType(Set<Class<?>> exclude) throws DaoException {
        Class<?> entityType = this.getEntityClass();
        Set<Class<?>> result = new HashSet<Class<?>>();
        
        List<Field> fields = new ArrayList<Field>();
        fields = CommonUtil.getAllDeclaredFields(fields, entityType);
        try {
            for (Field field : fields) {
                Class<?> relatedCls = field.getType();
                field.setAccessible(true);
                // get the element class out of the collection
                if (CommonUtil.isCollectionClass(relatedCls)) {
                    // note, a collection of entities is always on the non-foreign side
                    relatedCls = getCollectionEntityClass(field);
                }  
                
                if(!CommonUtil.isEntityClass(relatedCls)){
                    continue;
                }
                // must skip the abstract class
                else if((relatedCls.getModifiers() & Modifier.ABSTRACT) != 0){
//                    System.out.println(relatedCls.getSimpleName());
                }
                else{
                    if(exclude.contains(relatedCls)){
                        continue;
                    }
                    else if (isRelatedEntityClass(relatedCls)) {
                        result.add(relatedCls);
                    }
//                    else if (isCollectionClass(relatedCls)) {
//                        // note, a collection of entities is always on the non-foreign side
//                        relatedCls = getCollectionEntityClass(field);
//                        if(!exclude.contains(relatedCls)){
//                            result.add(relatedCls);
//                        }
//                    }
                }
                // it is possible that this entity class has sub classes
                Class<?>[] subTypes = getSubEntityType(relatedCls);
                for(Class<?> subType: subTypes){
                    if(!exclude.contains(subType)){
                        result.add(subType);
                    }
                }
            }
        }
        catch (IllegalArgumentException e) {
            throw new DaoException(e);
        }
        catch (ClassNotFoundException e) {
            throw new DaoException(e);
        }
        return result;
    }

    /**
     * List all entity classes that extend the given class
     * Note, this is a hack implementation, to be fixed later
     * Hopefully, Hibernate already has a better solution.
     * 
     * @param fieldCls
     * @return
     */
    private Class<?>[] getSubEntityType(Class<?> fieldCls) {
//        if(fieldCls.equals(Invoice.class)){
//            return new Class<?>[]{
//                CEInvoice.class,
//                CreditInvoice.class,
//                DepositInvoice.class,
//            };
//        }
//        else if(fieldCls.equals(OrderLineItem.class)){
//            return new Class<?>[]{
//                CEJobOrderLineItem.class,
//                ExpenseLineItem.class,
//                SplitLineItem.class,
//            };
//        }
        return new Class<?>[0];
    }

    /**
     * @param fieldCls
     * @return
     */
    private boolean isRelatedEntityClass(Class<?> fieldCls) {
        Annotation entityAnno = fieldCls.getAnnotation(Entity.class);
        if(entityAnno != null){
            // if this field is the foreign side, do not continue
            Annotation joinColumnAnno = fieldCls.getAnnotation(JoinColumn.class);
            if(joinColumnAnno == null){
                return true;
            }
        }
        return false;
    }

    
    public int execute(String statement, Object[] parameters) {
        // There is nothing I can do here, just keep it quiet
        return 1;
    }

    public List<Object[]> query(String name, Object statement, Pagination pagination, SortInfo sort) throws DaoException{
        throw new UnsupportedOperationException("Please use csvDao");
    }
    
    /**
     * This is not part of the DAO interface, just a convenient method to clean up the in
     * memory object pool
     */
    public void clear(){
        this.objectPool.clear();
    }

    /**
     * @see com.intertek.phoenix.dao.AbstractDao#search(java.lang.String, java.util.Map)
     */
    @Override
    public List<Object[]> searchByNamedQuery(String fnName, Object[] parameters, Pagination pagination) throws DaoException {
        throw new UnsupportedOperationException("No implementation");
    }
      @Override
    public List<Object[]> searchBySql(String sqlString, Object[] parameters, Pagination pagination) throws DaoException {
          throw new UnsupportedOperationException("No implementation");
    }

    @Override
    public String getIdentifierPropertyName() {
        throw new UnsupportedOperationException("No implementation");
    }

    @Override
    public T merge(T entity) throws DaoException {
        throw new UnsupportedOperationException("No implementation");
    }

   }