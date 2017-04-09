package com.fillcolors.commands
{
	import com.fillcolors.events.GalleryEvent;
	import com.fillcolors.model.ModelLocator;
	import com.fillcolors.views.states.*;
	import com.asfusion.mate.core.Bus;
	import com.fillcolors.events.NavigationEvent;
	
	public class StateSwitcherCommand
	{
		private var model:ModelLocator = ModelLocator.getInstance(); 
		
		public function onDesignSelected(event:GalleryEvent):void
		{
			model.selectedDesign = event.selectedDesign;
			
			var stateEvent:GalleryEvent = new GalleryEvent(GalleryEvent.STATE_CHANGE);
			
			stateEvent.state = (GalleryState.GRID_VIEW == model.currentGalleryView) ? GalleryState.GRID_SELECTED : GalleryState.THUMBNAIL_SELECTED;
			
			Bus.instance.dispatchEvent(stateEvent);
			
		}
		
		public function setCurrentView(currentView:int):void
		{
			model.currentGalleryView = currentView;
		}
		
		public function onViewChange(event:GalleryEvent):void
		{
			model.currentGalleryView = event.view;
			dispatchGalleryEvent();
		}
		
		public function onNavigationChange(event:NavigationEvent):void
		{
			if(event.navigateTo == ContentState.GALLERY)
			{
				event.stopImmediatePropagation();
				dispatchGalleryEvent();
			}
		}
		public function dispatchGalleryEvent():void
		{
			var stateEvent:GalleryEvent = new GalleryEvent(GalleryEvent.STATE_CHANGE);
			if(model.selectedDesign)
			{
				stateEvent.state = (GalleryState.GRID_VIEW == model.currentGalleryView) ? GalleryState.GRID_SELECTED : GalleryState.THUMBNAIL_SELECTED;
			}
			else
			{
				stateEvent.state = (GalleryState.GRID_VIEW == model.currentGalleryView) ? GalleryState.GRID_UNSELECTED : GalleryState.THUMBNAIL_UNSELECTED;

			}
			Bus.instance.dispatchEvent(stateEvent);
		}
	}
}