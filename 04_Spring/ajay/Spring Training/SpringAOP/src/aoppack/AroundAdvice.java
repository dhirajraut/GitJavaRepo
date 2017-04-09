package aoppack;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundAdvice implements MethodInterceptor {

	public Object invoke(MethodInvocation arg0) throws Throwable {
		Logger logObj=Logger.getLogger("aoppack");
		Handler hObj=new FileHandler("c:/ajay/AccountLog.xml");
		logObj.addHandler(hObj);
		logObj.log(Level.SEVERE,"Method Execution Started");
		
		System.out.println("Class Details" + this.getClass().getName());
		arg0.proceed();
		System.out.println("Class Details trapped" + this.getClass().getName());
		logObj.log(Level.SEVERE,"Method Execution Completed");
		return true;
	}

}
