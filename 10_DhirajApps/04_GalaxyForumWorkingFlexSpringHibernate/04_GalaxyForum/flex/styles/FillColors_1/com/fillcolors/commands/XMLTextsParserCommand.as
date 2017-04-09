package com.fillcolors.commands
{
	import com.fillcolors.model.*;
	
	public class XMLTextsParserCommand
	{
		
		public function parseXmlDocument(xmlDocument:XML):void {
			var model:ModelLocator = ModelLocator.getInstance();
			model.textualContent.aboutFillColors = xmlDocument..aboutFillColors;
		}
		
	}
}
