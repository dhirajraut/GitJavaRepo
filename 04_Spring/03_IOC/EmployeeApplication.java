import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.galaxy.spring.EmployeeProcessor;
public class EmployeeApplication {
	public static void main(final String args[]) {
		ApplicationContext context = new ClassPathXmlApplicationContext("ApplicationContext.xml");
		EmployeeProcessor empProcessor = (EmployeeProcessor)context.getBean("employeeProcessor");
		empProcessor.calculateSalary();
		empProcessor.printEmployee();
	}
}
