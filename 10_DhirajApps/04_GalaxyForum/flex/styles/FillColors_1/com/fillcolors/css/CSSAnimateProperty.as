package com.fillcolors.css
{
	import mx.core.UIComponent;
	import mx.effects.AnimateProperty;
	import mx.styles.CSSStyleDeclaration;
	import mx.styles.StyleManager;
	import flash.utils.Dictionary;
	
	[DefaultProperty("targetsAvailable")]
	public class CSSAnimateProperty
	{
	
		private var _property:*;
		private var _fromValue:*;
		private var _toValue:*;
		private var _duration:*;
		private var _isStyle:*;
		private var _filter:*;
		private var _startDelay:*;
		private var _roundValue:*;
		private var _cssTarget:*;
		
		private var _target:UIComponent;
		private var _animateProperty:AnimateProperty;
		private var _style:CSSStyleDeclaration
		private var _selector:String;
		private var _targets:Dictionary;
		
		private var needsInvalidation:Boolean = false;
		
		public function CSSAnimateProperty()
		{
			_animateProperty = new AnimateProperty();
			_targets = new Dictionary();
		}
		public function set styleName(value:String):void
		{
			_selector = value;
			_style = null;
			getCSSValues();
		}
		public function get styleName():String
		{
			return "."+_selector;
		}
		public function set target(value:UIComponent):void
		{
			_target = value;
			_animateProperty.target = value;
		}
		public function get target():UIComponent
		{
			return _target;
		}
		private function get style():CSSStyleDeclaration
		{
			if(!_style && styleName)
				 _style = StyleManager.getStyleDeclaration(styleName);
			return _style
		}
		//[ArrayElementType("mx.core.UIComponent")]
		public function set targetsAvailable(value:Array):void
		{
			for each(var component:UIComponent in value)
			{
				_targets[component.id] = component;
			}
			if(value) updateTarget();
		}
		public function addPosibleTarget(component:UIComponent):void
		{
			_targets[component.id] = component;
		}
		public function play():void
		{
			updateTarget();
			if(_animateProperty.property && _animateProperty.target)
			{
				_animateProperty.play();
			}
		}
		protected function updateTarget():void
		{
			if(style) 
			{
				_cssTarget = style.getStyle('target');
				if(StyleManager.isValidStyleValue(_cssTarget))
				{
					if(_targets[_cssTarget])
					{
						target = _targets[_cssTarget]  as UIComponent
					}
				}
			}
		}
		protected function getCSSValues():void
		{
			if(style)
			{
				_property = style.getStyle('property');
				if(StyleManager.isValidStyleValue(_property))
				{
					_animateProperty.property = _property as String;
				}
				
				_fromValue = style.getStyle('fromValue');
				if(StyleManager.isValidStyleValue(_fromValue))
				{
					_animateProperty.fromValue = _fromValue as Number;
				}
				
				_toValue = style.getStyle('toValue');
				if(StyleManager.isValidStyleValue(_toValue))
				{
					_animateProperty.toValue = _toValue as Number;
				}
				
				_duration = style.getStyle('duration');
				if(StyleManager.isValidStyleValue(_duration))
				{
					_animateProperty.duration = _duration as Number;
				}
				
				_isStyle = style.getStyle('isStyle');
				if(StyleManager.isValidStyleValue(_isStyle))
				{
					_animateProperty.isStyle = _isStyle as Boolean;
				}
				
				_filter = style.getStyle('filter');
				if(StyleManager.isValidStyleValue(_filter))
				{
					_animateProperty.filter = _filter as String;
				}
				
				_startDelay = style.getStyle('startDelay');
				if(StyleManager.isValidStyleValue(_startDelay))
				{
					_animateProperty.startDelay = _startDelay as int;
				}
				
				_roundValue = style.getStyle('roundValue');
				if(StyleManager.isValidStyleValue(_roundValue))
				{
					_animateProperty.roundValue = _roundValue as Boolean;
				}
				
			}
		}
	}
}