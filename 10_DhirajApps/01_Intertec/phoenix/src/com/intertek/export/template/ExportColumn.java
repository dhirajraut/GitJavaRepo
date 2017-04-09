package com.intertek.export.template;

public class ExportColumn{
	String text;
	String name;
	
	public ExportColumn(String text, String name){
		this.text=text;
		this.name=name;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
