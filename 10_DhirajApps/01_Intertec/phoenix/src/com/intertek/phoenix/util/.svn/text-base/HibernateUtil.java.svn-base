package com.intertek.phoenix.util;

import org.apache.log4j.Logger;
import org.hibernate.*;
import org.hibernate.cfg.*;
import org.hibernate.classic.Session;
import org.hibernate.context.ManagedSessionContext;

public class HibernateUtil {
    private static Logger log = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;
    private static Configuration configuration;
    
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            initialize();
        }
        return sessionFactory;
    }

    public static Configuration getConfiguration() {
        if(configuration == null){
            initialize();
        }
        return configuration;
    }
    
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    private static void initialize(){
        try {
            configuration = new AnnotationConfiguration().configure("phase2.hibernate.cfg.xml");
            sessionFactory = configuration.buildSessionFactory();
        }
        catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static void beginTransaction(){
        log.debug("Starting a database transaction");
        Session session = getSessionFactory().openSession();
        session.setFlushMode(FlushMode.MANUAL);
        log.debug("Binding the current Session");
        ManagedSessionContext.bind(session);
        // from this point, the session can be used for the current thread
        
        // start a transaction
        session.beginTransaction();
    }

    // call this method to terminate the current transaction and start a new
    // transaction, the new transaction will stay open until the session
    // closes or this method is called again.
    public static void newTransaction(){
        Session currentSession = getSessionFactory().getCurrentSession();
        log.debug("Flushing Session");
        currentSession.flush();
        if (currentSession.getTransaction().isActive()) {
            log.debug("Committing the database transaction");
            currentSession.getTransaction().commit();
        }
        
        // start a new transaction
        currentSession.beginTransaction();
    }
    
    public static void endTransaction(boolean rollback) {
        Session currentSession = null;
        try {
            log.debug("Unbinding Session after processing"); 
            currentSession = ManagedSessionContext.unbind(sessionFactory); 
            if (rollback) {
                if (currentSession.getTransaction().isActive()) {
                    log.debug("Trying to rollback database transaction after exception");
                    currentSession.getTransaction().rollback();
                }
            }
            else {
                //log.debug("Unbinding Session after processing"); 
                //currentSession = ManagedSessionContext.unbind(sessionFactory); 
                log.debug("Flushing Session");
                currentSession.flush();
                if (currentSession.getTransaction().isActive()) {
                    log.debug("Committing the database transaction");
                    currentSession.getTransaction().commit();
                }
            }                    
        }
        catch (Throwable rbEx) {
            log.error("Could not commit/rollback transaction!", rbEx);
        }
        finally{
            if(currentSession != null) {
                // the session must not be reused, close the session 
                currentSession.close(); 
            }
        }
    }
}
