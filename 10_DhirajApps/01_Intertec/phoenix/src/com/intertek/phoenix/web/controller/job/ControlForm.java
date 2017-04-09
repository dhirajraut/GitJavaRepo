/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.intertek.entity.Control;

/**
 * Form to represent Control on 'Add Service' popup in 'Select Charges'
 * 
 */

public class ControlForm {
    private Control control;
    private Map<String, String> dataMap = new HashMap<String, String>();
    private String dataInput;

    public ControlForm(Control control) {
        this.control = control;
    }

    public Control getControl() {
        return control;
    }

    public String getDataInput() {
        return dataInput;
    }

    public void setDataInput(String dataInput) {
        this.dataInput = dataInput;
    }

    public Map<String, String> getDataMap() {
        return dataMap;
    }


    public Map<String, String> getGroupItems() {
        //TODO: implement this
        Map<String, String> groupMap = new TreeMap<String, String>();
        
        return groupMap;
    }
}
