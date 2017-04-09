package com.fillcolors.vos
{

	[Bindable]
	public class Design
	{

		public var id:String = "";
		public var name:String = "";
		public var author:String = "";
		public var website:String = "";
		public var country:String = "";
		public var cssFile:String = "";
		public var sourceFile:String = "";
		public var thumbnail:String = "";
		public var thumbnailLarge:String = "";
		public var license:String = "";
		public var rating:Number = 0;
		public var votes:Number = 0;
		public var dateAdded:Date;
		public var description:String = "";


		public function Design()
		{
		}

	}
}