package com.fillcolors.commands
{
	import com.fillcolors.events.*;
	import com.fillcolors.model.*;
	import mx.collections.Sort;
	import mx.collections.SortField;
	
	public class DesignListChangeCommand
	{
		public function filter(event:DesignsListEvent):void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			
			(model.designs as DesignsList).filterCriteria = event.filterBy;
			model.designs.refresh();			
		}
		
		public function sort(event:DesignsListEvent):void
		{
			var model:ModelLocator = ModelLocator.getInstance();
			var sort:Sort = new Sort();
			var sortField:String = "id";
			var sortFieldDirection:Boolean = false;
			
			switch(event.sortBy){
				case "Date Added": sortField = 'dateAdded'; sortFieldDirection= true; break;
				case "Name": sortField = 'name'; break;
				case "Rating": sortField = 'rating'; sortFieldDirection = true; break;
			}
			
			sort.fields = [new SortField(sortField, true, sortFieldDirection)];
			
			model.designs.sort = sort;
			model.designs.refresh();
		}
		
	}
}