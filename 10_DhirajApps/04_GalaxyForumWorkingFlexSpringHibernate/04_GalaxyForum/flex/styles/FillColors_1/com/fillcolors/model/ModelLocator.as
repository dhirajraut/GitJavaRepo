package com.fillcolors.model
{
	import com.fillcolors.vos.Design;
	import mx.collections.ArrayCollection;
	import flash.utils.Dictionary;
	
	[Bindable]
	public class ModelLocator
	{
		public var selectedDesign:Design;
		public var designs:ArrayCollection;
		public var currentGalleryView:int;
		
		public var textualContent:TextualContent;
		
		public var countries:ArrayCollection;
		
		public var currentSkin:Design;
		public var designsDictionary:Dictionary;
		public var stylePath:String = "assets/sampleData/compiledStyles/";
		public var imagesPath:String = "assets/sampleData/thumbnails/";
		
		private static var model : ModelLocator;
		
		// singleton: constructor only allows one model locator
		public function ModelLocator() : void 
		{
			if ( model )
				throw new Error( "Only one ModelLocator instance should be instantiated" );
			
			textualContent = new TextualContent();
			designsDictionary = new Dictionary();
			
		}

		// singleton: always returns the one existing static instance to itself
		public static function getInstance() : ModelLocator 
		{
			if ( !model )
				model = new ModelLocator();
			return model;
		}
	}
}

