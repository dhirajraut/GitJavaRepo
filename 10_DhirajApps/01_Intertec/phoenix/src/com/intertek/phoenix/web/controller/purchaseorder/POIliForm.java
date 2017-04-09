/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.purchaseorder;

import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.util.DateUtil;

/**
 * Form representing invoice information section on 'Edit PO'
 * @author rautsmit
 *
 */
public class POIliForm {

	private CEInvoiceLineItem ceInvoiceLineItem;
	
	/**
	 * Instantiates new POIliForm
	 * @param ceInvoiceLineItem
	 */
	public POIliForm(CEInvoiceLineItem ceInvoiceLineItem) {
		this.ceInvoiceLineItem = ceInvoiceLineItem; 
	}
	
	
	public String getDate() {
		return DateUtil.dateToString(ceInvoiceLineItem.getTaxDate());
	}
	
	public long getInvoiceId() {
		return ceInvoiceLineItem.getInvoiceId();
	}
	public double getTotalAmount() {
		return ceInvoiceLineItem.getTotalAmount();
	}
	
	
}
