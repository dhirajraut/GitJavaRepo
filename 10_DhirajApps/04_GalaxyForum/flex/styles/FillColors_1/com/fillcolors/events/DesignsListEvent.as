package com.fillcolors.events
{
	import flash.events.Event;

	public class DesignsListEvent extends Event
	{
		static public const FILTER:String 	= "filterEvent";
		static public const SORT:String 	= "sortEvent";
		
		public var filterBy:String;
		public var sortBy:String;
		
		public function DesignsListEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}