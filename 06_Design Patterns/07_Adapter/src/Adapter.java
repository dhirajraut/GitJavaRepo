import services.LoginService;
import services.LogoutService;
import services.LogoutServiceAdapter;

public class Adapter {
	public static void main (String args[]) {
		LoginService loginService = new LoginService();
		loginService.process();

		LogoutService logoutService = new LogoutService();
		logoutService.execute();

		LogoutServiceAdapter logoutServiceAdapter = 
				new LogoutServiceAdapter(logoutService);
		logoutServiceAdapter.process();
	}
}
