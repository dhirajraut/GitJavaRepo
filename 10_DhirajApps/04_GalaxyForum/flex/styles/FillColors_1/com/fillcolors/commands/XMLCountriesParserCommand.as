package com.fillcolors.commands
{
	import com.fillcolors.model.*;
	import mx.collections.ArrayCollection;
	
	public class XMLCountriesParserCommand
	{
		
		public function parseXmlDocument(xmlDocument:XML):void {
			var model:ModelLocator = ModelLocator.getInstance();
			var countries:Array = new Array();
				
			for each( var thisCountry:XML in xmlDocument.Countries..country ){
				var obj:Object = new Object();
				obj.name = thisCountry.name;
				countries.push(obj);
			}
			
			model.countries = new ArrayCollection(countries);
			
		}
	}
}
