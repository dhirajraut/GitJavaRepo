package com.intertek.phoenix.web.controller.job;

import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class DateBetweenField.
 */

public class DateBetweenField  {
	
	/** The from. */
	private Date from;
	
	/** The to. */
	private Date to;

	/**
	 * Instantiates a new date between field.
	 */
	public DateBetweenField() {
	}

	/**
	 * Instantiates a new date between field.
	 * 
	 * @param from the from
	 * @param to the to
	 */
	public DateBetweenField(Date from, Date to) {
		this.from = from;
		this.to = to;
	}

	/**
	 * Gets the from.
	 * 
	 * @return the from
	 */
	public Date getFrom() {
		return from;
	}

	/**
	 * Sets the from.
	 * 
	 * @param from the new from
	 */
	public void setFrom(Date from) {
		this.from = from;
	}

	/**
	 * Gets the to.
	 * 
	 * @return the to
	 */
	public Date getTo() {
		return to;
	}

	/**
	 * Sets the to.
	 * 
	 * @param to the new to
	 */
	public void setTo(Date to) {
		this.to = to;
	}

	
}
