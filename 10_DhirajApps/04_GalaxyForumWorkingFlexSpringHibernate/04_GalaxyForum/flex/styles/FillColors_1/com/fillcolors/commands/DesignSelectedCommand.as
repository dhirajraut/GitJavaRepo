package com.fillcolors.commands
{
	import com.fillcolors.events.GalleryEvent;
	import com.fillcolors.model.ModelLocator;
	public class DesignSelectedCommand
	{
		public function execute(event:GalleryEvent):void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			model.selectedDesign = event.selectedDesign;
		}
	}
}