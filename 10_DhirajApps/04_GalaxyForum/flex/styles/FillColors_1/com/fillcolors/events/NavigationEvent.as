package com.fillcolors.events
{
	import flash.events.Event;

	public class NavigationEvent extends Event
	{
		static public const CHANGE:String = "navigationChange";
		
		public var navigateTo:String;
		
		public function NavigationEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}