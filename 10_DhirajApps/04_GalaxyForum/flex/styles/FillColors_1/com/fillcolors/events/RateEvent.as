package com.fillcolors.events
{
	import flash.events.Event;
	import com.fillcolors.vos.Design;
	
	[Bindable]
	
	public class RateEvent extends Event
	{
		static public const APPLY:String = "applyRatingEvent";

		public var design:Design;
		public var rating:Number;
		
		public function RateEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=true)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}