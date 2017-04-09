package com.fillcolors.commands
{
	import com.fillcolors.model.*;
	import com.fillcolors.vos.*;
	
	import mx.collections.ArrayCollection;
	import flash.utils.Dictionary;

	public class XMLParserCommand
	{
		
		public function parseXmlDocument(xmlDocument:XML):void 
		{
			var index:int = 0;
			var designs:Array = new Array();
			var model:ModelLocator = ModelLocator.getInstance();
			var dictionary:Dictionary = model.designsDictionary;
				
			for each( var design:XML in xmlDocument..design ) 
			{
				designs[index] = new Design();
				designs[index].id = design.@id;
				designs[index].name = design.name;
				designs[index].author = design.author;
				designs[index].website = design.website;
				designs[index].country = design.country;
				designs[index].rating = design.rating;
				designs[index].thumbnail = model.imagesPath + design.thumbnail;
				designs[index].thumbnailLarge = model.imagesPath + design.thumbnaillarge;
				designs[index].votes = design.votes;
				designs[index].cssFile = design.cssfile;
				designs[index].sourceFile = design.sourcefile;
				designs[index].license = design.license;
				dictionary[(designs[index] as Design).id] = designs[index];
				
				index++;				
			}
			
			ModelLocator.getInstance().designs = new DesignsList(designs);

		}
	}
}
