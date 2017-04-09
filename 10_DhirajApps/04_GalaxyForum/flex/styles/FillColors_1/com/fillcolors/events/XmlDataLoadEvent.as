package com.fillcolors.events
{

	public class XmlDataLoadEvent
	{
		public static const LOAD:String = "loadXml";
		
		public var xmlURL:String;
		
		public function XmlDataLoadEvent(type:String, bubbles:Boolean=true, cancelable:Boolean=true)
		{
			super(type, bubbles, cancelable);
		}

	}
}