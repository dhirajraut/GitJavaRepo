import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.galaxy.spring.IGreeting;

public class GreetingApplication {
	public static void main(final String args[]) throws Exception {
		
		ApplicationContext context = 
			new ClassPathXmlApplicationContext("BeanConfig.xml");
		
		IGreeting greeting = (IGreeting)context.getBean("greetingService");
		greeting.greet();
	}
}
