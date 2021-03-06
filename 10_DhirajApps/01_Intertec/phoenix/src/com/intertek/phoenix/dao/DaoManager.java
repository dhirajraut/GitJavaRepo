/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.dao;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.util.HibernateUtil;

/**
 * 
 * @author richard.qin
 */
@SuppressWarnings("unchecked")
public class DaoManager {
    
    enum DaoFlavor{
        DAO_HIBERNATE(0, "Hibernate Dao"),
        DAO_MEMORY(1, "Memory Dao"),
        DAO_CSV(2, "CSV Dao"),  
        DAO_HIBERNATE_TEST(3, "Hibernate Test Dao");       
        
        private int value;
        private String description;
        
        private DaoFlavor(int value, String description){
            this.value = value;
            this.description = description;
        }
        
        public int value(){
            return value;
        }
        
        public String description(){
            return description;
        }
    }
    
    private static Map<Class<?>, Dao<?>> daoMap = new HashMap<Class<?>, Dao<?>>();
    // which flavor of DAO to use: hibernate? in-memory? csv?
    private static DaoFlavor flavor = DaoFlavor.DAO_HIBERNATE_TEST;
    
    private static SessionFactory sessionFactory;
    private static Configuration configuration;

    static {
        // TODO config data flavor

        // initialize the dao map
        // usually only "specialized" dao objects are initialized this way,
        // generic dao objects can be created at runtime when it is first time
        // requested.
        switch (flavor) {
            case DAO_MEMORY:
                break;
            case DAO_CSV:
//                daoMap.put(CEJobOrder.class, new GenericDao(new CsvDao<CEJobOrder>(CEJobOrder.class)));
                 // daoMap.put(CEJobOrderLineItem.class, new GenericDao(new CsvDao<CEJobOrderLineItem>(CEJobOrderLineItem.class)));
                //daoMap.put(ContactCust.class, new GenericDao(new CsvDao<ContactCust>(ContactCust.class)));
                break;
            case DAO_HIBERNATE_TEST:
                // make the session factory for testing
                sessionFactory = HibernateUtil.getSessionFactory();
                configuration = HibernateUtil.getConfiguration();
                break;
            case DAO_HIBERNATE:
//                sessionFactory = (SessionFactory)ServiceLocator.getInstance().getBean("entitySessionFactory");
//                configuration = ((AnnotationSessionFactoryBean)sessionFactory).getConfiguration();
            default:
                break;

        }
    }

    public static <T> Dao<T> getDao(Class<T> entityType) {
        Dao<T> dao = (Dao<T>) daoMap.get(entityType);
        if (dao == null) {
            // create a generic dao for this entity type, and make it
            // available for later use
            switch (flavor) {
            case DAO_MEMORY:
                dao = new GenericDao(new MemoryDao<T>(entityType));
                break;
            case DAO_CSV:
                dao = new GenericDao(new CsvDao<T>(entityType));
                break;
            case DAO_HIBERNATE_TEST:
                HibernateDao hdao = new HibernateDao<T>(entityType);
                hdao.setSesseionFactory(sessionFactory);
                dao = new GenericDao(hdao);
                break;
            case DAO_HIBERNATE:
                
            default:
                HibernateDao<T> hibernateDao = (HibernateDao<T>)ServiceLocator.getInstance().getBean("hibernateDao");
                hibernateDao.setEntityClass(entityType);
                dao = new GenericDao<T>(hibernateDao);
                break;
            }
            daoMap.put(entityType, dao);
        }
        return dao;
    }
    
    public static <T> GenericDao<T> getGenericDao(Class<T> entityClass){
        // TODO: make sure that the dao instance is indeed a GenericDao
        // In most of the cases this is true, as GenericDao is the single
        // point of inheritance for specialized dao implementation.
        return (GenericDao<T>)getDao(entityClass);
    }
    
    /**
     * Get the flavor of the DAO impl in use, for internal use only
     * @return
     */
    static DaoFlavor getFlavor(){
        return DaoManager.flavor;
    }
    
    /**
     * Set the flavor of the DAO impl in use, for internal use only
     * @return
     */
    static void setFlavor(DaoFlavor flavor){
        DaoManager.flavor = flavor;
    }
    
    /**
     * Reset all the DAO instances. After this call, all cached DAO objects are discarded,
     * a new DAO object will be instantiated next time when getDao() is called.
     */
    static void reset(){
        daoMap.clear();
    }

    /**
     * Get the test Hibernate session
     * @param sessionFactory
     */
    public static Session getCurrentSession() {
        if(flavor == DaoFlavor.DAO_HIBERNATE_TEST && sessionFactory != null){
            return sessionFactory.getCurrentSession();
        }
        return null;
    }
    
    public static Configuration getConfiguration(){
        return configuration;
    }
    
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
