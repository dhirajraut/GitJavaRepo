import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import com.galaxy.spring.IGreeting;

public class GreetingApplication {
	public static void main(final String args[]) throws Exception {
		
		BeanFactory beanFactory = 
			new XmlBeanFactory(new ClassPathResource("BeanConfig.xml"));
		
		IGreeting greeting = (IGreeting)beanFactory.getBean("greetingService");
		greeting.greet();
	}
}
