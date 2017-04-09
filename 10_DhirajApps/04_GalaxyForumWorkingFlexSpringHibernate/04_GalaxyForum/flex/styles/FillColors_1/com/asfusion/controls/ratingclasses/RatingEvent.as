package com.asfusion.controls.ratingclasses
{
	import flash.events.Event;

	public class RatingEvent extends Event
	{
		public static const SELECTION_CHANGE:String = "selectionChange";
		
		public var selectedValue:uint;
		
		public function RatingEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
		
	}
}