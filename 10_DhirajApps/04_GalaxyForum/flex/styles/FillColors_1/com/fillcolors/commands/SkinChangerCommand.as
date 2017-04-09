package com.fillcolors.commands
{
	import com.asfusion.mate.core.Bus;
	import com.fillcolors.events.DesignEvent;
	import com.fillcolors.model.ModelLocator;
	import com.fillcolors.views.LoadingStyleWindow;
	import com.fillcolors.vos.Design;
	
	import flash.display.DisplayObject;
	import flash.events.*;
	
	import mx.controls.Alert;
	import mx.core.Application;
	import mx.events.StyleEvent;
	import mx.managers.PopUpManager;
	import mx.styles.StyleManager;
	
	public class SkinChangerCommand
	{
		private var popup:LoadingStyleWindow;
		private var design:Design;
		private var styleEventDispatcher:IEventDispatcher;
		
		public function execute(event:DesignEvent):void
		{ 
			var storedDesign:Design = ModelLocator.getInstance().designsDictionary[event.design.id] as Design;
			//first check whether we need to get this design first
			if (!storedDesign || storedDesign.cssFile.length == 0){
				//call get and load and stop this event
				var getevent:DesignEvent = new DesignEvent(DesignEvent.GET_AND_LOAD);
					getevent.design = storedDesign;
				Bus.instance.dispatchEvent(getevent);
				event.stopImmediatePropagation();
				return;
			}
			
			var previousSkin:Design = ModelLocator.getInstance().currentSkin;
			design = storedDesign;
						
			popup = PopUpManager.createPopUp(Application.application as DisplayObject, LoadingStyleWindow, true) as LoadingStyleWindow;
			PopUpManager.centerPopUp(popup);
			
			//unload old skin
			if (previousSkin){
				StyleManager.unloadStyleDeclarations(ModelLocator.getInstance().stylePath + previousSkin.cssFile, false);
				//StyleManager.clearStyleDeclaration('Panel', false);
			}
			
			
			styleEventDispatcher = StyleManager.loadStyleDeclarations(ModelLocator.getInstance().stylePath + design.cssFile, true);
			
			//show popup with loading bar         
            styleEventDispatcher.addEventListener(StyleEvent.COMPLETE, removeLoading);
			popup.progressBarSource = styleEventDispatcher;
		}
		
		public function removeLoading(e:StyleEvent):void {
			//remove the popup
			PopUpManager.removePopUp(popup);
			//check for error and show alert
			if (e.type == StyleEvent.ERROR){
				Alert.show("Style could not be loaded:  ");
			}
			else if( e.type == StyleEvent.COMPLETE){
				var event:DesignEvent = new DesignEvent(DesignEvent.APPLY);
					event.design = design;
				Bus.instance.dispatchEvent(event);
			}
			styleEventDispatcher.removeEventListener(StyleEvent.COMPLETE, removeLoading);
		}
		
		public function designApplied(e:DesignEvent):void {
			ModelLocator.getInstance().currentSkin = e.design;
		}
		
		public function loadDefaultStyle():void {
			var newDesign:Design = new Design();
			newDesign.id = "naked";
			newDesign.cssFile = "Naked.swf";
			newDesign.name = "Naked";
			ModelLocator.getInstance().designsDictionary[newDesign.id] = newDesign;
			
			var event:DesignEvent = new DesignEvent(DesignEvent.SELECT);
			event.design = newDesign;
			Bus.instance.dispatchEvent(event);
			
		}
	}
}