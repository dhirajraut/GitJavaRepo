package com.fillcolors.model
{
	import mx.collections.ArrayCollection;
	import com.fillcolors.vos.Design;

	public class DesignsList extends ArrayCollection
	{
		private var _filterCriteria:String = "";
		
		public function DesignsList(source:Array=null)
		{
			super(source);
			this.filterFunction = filter;
		}
		
		public function set filterCriteria(criteria:String):void {
			_filterCriteria = criteria.toLowerCase();
		}
		
		//filter function
		public function filter(item:Object):Boolean {
        	var design:Design = item as Design;
        	
        	//search in the name
        	if(design.name.toLowerCase().indexOf(_filterCriteria) != -1) 
        		return true;
        	
        	//search in the author name
        	if(design.author.toLowerCase().indexOf(_filterCriteria) != -1) 
        		return true;
        	
        	//search in the author country
        	if(design.country.toLowerCase().indexOf(_filterCriteria) != -1) 
        		return true;
        	
        	//search in the license
        	if(design.license.toLowerCase().indexOf(_filterCriteria) != -1) 
        		return true;
        		
        	//search in the website of the author
        	if(design.website.toLowerCase().indexOf(_filterCriteria) != -1) 
        		return true;
        		
        	//search in the rating
        	if(design.rating.toString().indexOf(_filterCriteria) != -1) 
        		return true;
        		
        	
        	return false;
        	
        }
		
	}
}