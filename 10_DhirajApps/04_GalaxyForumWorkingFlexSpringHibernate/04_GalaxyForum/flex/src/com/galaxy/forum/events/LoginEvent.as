package com.galaxy.forum.events
{
	import com.galaxy.forum.views.CurrentView;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	
	public class LoginEvent extends Event
	{
		public static const LOGIN_SUCCESS:String = "loginSuccess";
		public static const LOGIN_FAILURE:String = "loginFailure";
		public static const LOGOUT:String = "logout";

		private var _result:ArrayCollection;
		
		public function LoginEvent(type:String, argResult:ArrayCollection,
								bubbles:Boolean=false, cancelable:Boolean=false) {
			this._result = argResult;
			super(type, bubbles, cancelable);
		}
		
		public function get result():ArrayCollection {
			return this._result;
		}

		override public function clone():Event {
			return new LoginEvent (type, result, bubbles, cancelable);
		}
	}
}