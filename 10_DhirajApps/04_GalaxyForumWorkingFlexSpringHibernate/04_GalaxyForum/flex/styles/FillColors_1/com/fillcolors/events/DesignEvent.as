package com.fillcolors.events
{
	import flash.events.Event;
	import com.fillcolors.vos.Design;
	
	[Bindable]
	
	public class DesignEvent extends Event
	{
		static public const SAVE:String 	= "saveDesignEvent";
		static public const SELECT:String 	= "selectDesignEvent";
		static public const GET:String 	= "getDesignEvent";
		static public const APPLY:String 	= "applyDesignEvent";
		static public const GET_AND_LOAD:String 	= "getAndLoadDesignEvent";
		static public const DOWNLOAD:String 	= "downloadDesignEvent";

		public var design:Design;
		
		public function DesignEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=true)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}