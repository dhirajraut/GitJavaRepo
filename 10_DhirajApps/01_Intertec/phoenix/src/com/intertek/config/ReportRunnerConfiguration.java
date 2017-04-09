/**
 * 
 */
package com.intertek.config;

/**
 * 
 * @author richard.qin
 */
public class ReportRunnerConfiguration {
    private static ReportRunnerConfiguration instance;
    
    
    
    private ReportRunnerConfiguration(){
    }
    
    protected static ReportRunnerConfiguration getConfiguration(){
        if(instance == null){
            instance = new ReportRunnerConfiguration();
        }
        return instance;
    }
}
