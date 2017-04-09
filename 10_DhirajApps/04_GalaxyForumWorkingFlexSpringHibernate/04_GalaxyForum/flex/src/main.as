// ActionScript file
	import com.galaxy.forum.events.LoginEvent;
	import com.galaxy.forum.events.Dispatcher;
	import mx.controls.Alert;
	import com.galaxy.forum.utils.Constants;
	import com.galaxy.forum.views.LoginView;
	import com.galaxy.forum.views.CurrentView;
	
	private function createApplication():void {
		/* Initialization Tasks */
		initializeEvents();
	}
	 
	private function initializeEvents():void {
		Dispatcher.dispatcher.addEventListener(LoginEvent.LOGIN_SUCCESS, loginSuccess);
	 	Dispatcher.dispatcher.addEventListener(LoginEvent.LOGIN_FAILURE, loginFailure);
	 	Dispatcher.dispatcher.addEventListener(LoginEvent.LOGOUT, logout);
	}
	 
	private function loginSuccess(event:Event):void {
		currentView.currentViewStack.selectedIndex = 1;
		menubar.visible = true;
	}
	private function loginFailure(event:Event):void {
		Alert.show("Invalid Username or Password");
	}
	private function logout(event:Event):void {
		Alert.show("In Logout");
		currentView.currentViewStack.selectedIndex = 0;
		menubar.visible = false;
	}