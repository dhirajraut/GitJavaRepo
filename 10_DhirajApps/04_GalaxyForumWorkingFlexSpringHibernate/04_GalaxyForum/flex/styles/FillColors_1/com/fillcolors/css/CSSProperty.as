package com.fillcolors.css
{
	import mx.styles.CSSStyleDeclaration;
	import mx.styles.StyleManager;
	import mx.styles.IStyleClient;
	
	public class CSSProperty
	{
		[Bindable] public var value:Object;
	
		private var _propertyName:String;
		private var _selector:String;
		private var _defaultValue:Object;
		private var _styleName:Object;
		private var _styleClient:IStyleClient;
		
		public function set property(value:String):void
		{
			_propertyName = value;
			
			if(selector && defaultValue || defaultValue && styleName)
				setValue();
		}
		public function get property():String
		{
			return _propertyName;
		}
		public function set selector(value:String):void
		{
			_selector = value;
			if(property && defaultValue)
				setValue();
		}
		public function get selector():String
		{
			return _selector;
		}
		public function set defaultValue(value:Object):void
		{
			_defaultValue = value;
			if(property && selector || property && styleName) setValue();
				
			else this.value = value;
		}
		public function get defaultValue():Object
		{
			return _defaultValue;
		}
		public function set styleName(value:Object):void
		{
			_styleName = value;
			if(value is String)
			{
				selector = "."+ value;
			}
			else if(value is IStyleClient)
			{
				_styleClient = IStyleClient(value);
				if(property && defaultValue) setValue();
			}
		}
		public function get styleName():Object
		{
			return _styleName
		}
		private function setValue():void
		{	
			var styleValue:*;
			
			if(_styleClient)
			{
				_styleClient;
				styleValue = _styleClient.getStyle(property);
				if(StyleManager.isValidStyleValue(styleValue))
				{
					value = styleValue;
				}
				else
				{
					value = defaultValue;
				}
				
			}
			else
			{
				var style:CSSStyleDeclaration = StyleManager.getStyleDeclaration(selector);
				if(style)
				{
					styleValue = style.getStyle(property);
					if(StyleManager.isValidStyleValue(styleValue))
					{
						value = styleValue;
					}
					else
					{
						value = defaultValue;
					}
				}
			}
		}
	}
	
}