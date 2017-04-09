package com.fillcolors.events
{
	import flash.events.Event;
	import com.fillcolors.vos.Design;

	public class GalleryEvent extends Event
	{
		static public const DESIGN_SELECTED:String		= "designSelected";
		static public const DESIGN_UNSELECTED:String	= "designUnselected";
		
		static public const STATE_CHANGE:String		 	= "galleryStateChange";
		static public const VIEW_CHANGE:String 			= "galleryViewChange";
		
		public var selectedDesign:Design;
		public var state:String;
		public var view:int;
		
		public function GalleryEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}