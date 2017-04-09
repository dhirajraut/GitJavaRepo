package constructor_args;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

public class HelloWorld {

	public static void main(String[] args) {
	
		BeanFactory factory = new XmlBeanFactory(new FileSystemResource("C:\\Tanay\\SprigPractice\\HelloWorld\\constructor_args\\hellospringapp.xml"));
		GreetingService gs = (GreetingService) factory.getBean("greetingClass");
		gs.printGreeting();
		}

}
