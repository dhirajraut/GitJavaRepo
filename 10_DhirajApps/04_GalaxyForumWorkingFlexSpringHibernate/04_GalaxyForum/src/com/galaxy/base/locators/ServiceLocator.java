package com.galaxy.base.locators;

import org.springframework.context.ApplicationContext;

public class ServiceLocator {

	// Singleton Implementation.
	private static ServiceLocator serviceLocatorInstance = null;
	
	private ServiceLocator () {
		// Inaccessible.
	}
	
	public static ServiceLocator getInstance() {

		synchronized (ServiceLocator.class) {
			if (null == serviceLocatorInstance) {
				serviceLocatorInstance = new ServiceLocator();
			}
		}
		return serviceLocatorInstance;
	}
	
	// Application Context implementation.
	ApplicationContext applicationContext = null;
	
	public void setApplicationContext (ApplicationContext context) {
		this.applicationContext = context;
	}
}
