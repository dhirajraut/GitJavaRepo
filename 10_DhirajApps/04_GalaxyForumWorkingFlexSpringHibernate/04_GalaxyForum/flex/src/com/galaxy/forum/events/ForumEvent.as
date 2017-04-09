package com.galaxy.forum.events
{
	import flash.events.Event;

	public class ForumEvent extends Event
	{
		private var __data:*;
		
		public function ForumEvent(type:String,data:*=null)
		{
			super(type, true, true);
			this.__data = data;
		}
		
		public function get data():*
		{
			return __data;
		}		
	}
}