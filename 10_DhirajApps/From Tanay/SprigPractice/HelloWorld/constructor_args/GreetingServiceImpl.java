package constructor_args;

public class GreetingServiceImpl implements GreetingService {
	String greeting;
	
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public void printGreeting() {
		System.out.println(greeting);
	}
	public GreetingServiceImpl(String greeting) {
		this.greeting = greeting;
	}
	public GreetingServiceImpl(){
		this.greeting = "Hi World";
	}

}