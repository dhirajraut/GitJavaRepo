package com.galaxy.generic.log;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogManager {
    public static Logger loggerInstance = null;
    
    /**
     * This method returns the Logger Instance.
     * @param classObject - Input Class object.
     * @return Logger Instance.
     */
    public static synchronized Logger getLogger (Class classObject) {
        if (null == loggerInstance){
            loggerInstance = Logger.getLogger(classObject);
            initializeLogger();
        }
        return loggerInstance;
    }
    
    private static void initializeLogger() {
        Properties log4jProperties =  new Properties();
        try {
            log4jProperties.load(new FileInputStream ("C:\\Dhiraj\\51_Workspace\\08_Eclipse\\01_Java\\Java\\WEB-INF\\log4j.properties"));
            PropertyConfigurator.configure(log4jProperties);
            System.out.println("Logger Init Success");
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Error Initializing Logger");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.err.println("Error Initializing Logger");
            e.printStackTrace();
        }
    }
}
