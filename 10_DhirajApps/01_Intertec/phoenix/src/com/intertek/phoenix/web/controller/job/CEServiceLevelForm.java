/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Comparator;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.common.ReferenceDataService.DropdownName;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.ContractServiceLevel;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractServiceExpression;
import com.intertek.phoenix.entity.JobContractTest;

/**
 * Form to represent product service level on SC page
 * 
 * @author rautsmit
 * 
 */
public class CEServiceLevelForm {

    private CEScJobOrderLineItemForm[] joliForms;
    private CEJobContract jobContract;
    private ContractServiceLevel product;
    private String productGroup;
    private String productName;

    /**
     * Instantiates new CEServiceLevelForm
     * 
     * @param srvLvl
     */
    public CEServiceLevelForm(ContractServiceLevel srvLvl) {
        updateForm(srvLvl);
    }

    /**
     * Reconstructs the CEServiceLevelForm
     * 
     * @param srvLvl
     */
    public void updateForm(ContractServiceLevel srvLvl) {
        this.product = srvLvl;
        this.jobContract = product.getJobContract();
        populateProductGroupAndName();

        Set<CEJobOrderLineItem> jolis = getMasterJolis(product);
        joliForms = new CEScJobOrderLineItemForm[jolis.size()];

        int joliCounter = 0;
        for (CEJobOrderLineItem joli : jolis) {
        	CEScJobOrderLineItemForm joliForm = new CEScJobOrderLineItemForm(joli, product);
        	//line number assigned to sort numbers
        	joliForm.setSortNumber(joliForm.getLineNumber());
            joliForms[joliCounter++] = joliForm;
        }
        Arrays.sort(joliForms, new MasterJoliComparator());
    }
    
    /**
     * Retrieve all the JOLIs associated with given contract service level
     * 
     * @param product
     * @return
     */
    private Set<CEJobOrderLineItem> getMasterJolis(ContractServiceLevel product) {
        Set<CEJobOrderLineItem> jolis = new HashSet<CEJobOrderLineItem>();
        for (JobContractTest jcTest : product.getJobContractTests()) {

            if (jcTest.getJobOrderLineItem().getMaster() == null) {
                jolis.add(jcTest.getJobOrderLineItem());
            }
        }

        for (JobContractService jcService : product.getJobContractServices()) {
            for (JobContractServiceExpression expr : jcService.getJobContractServiceExpresions()) {
                if (expr.getJobOrderLineItem().getMaster() == null) {
                    jolis.add(expr.getJobOrderLineItem());
                }
            }
        }

        return jolis;
    }

    /**
     * Updates service level name using product name and group entered by the
     * user
     */
    public void updateServiceLevelName() {
        if (productGroup != null && productName != null) {
            product.setServiceLevelName(productGroup + "/" + productName);
        }
    }

    /**
     * Populates form fields - product group and name, using entity service
     * level name
     */
    private void populateProductGroupAndName() {
        String prodSLName = product.getServiceLevelName();
        if (prodSLName != null && prodSLName.indexOf("/") > 0) {
            String[] prodNameGroup = prodSLName.split("/");
            if (prodNameGroup.length == 2) {
                this.productGroup = prodNameGroup[0];
                this.productName = prodNameGroup[1];
            }
        }
    }

    public String getId() {
        return String.valueOf(product.getId());
    }

    public CEScJobOrderLineItemForm[] getJoliForms() {
        return joliForms;
    }

    public void setJoliForms(CEScJobOrderLineItemForm[] joliForms) {
        this.joliForms = joliForms;
    }

    public ContractServiceLevel getProduct() {
        return product;
    }

    public void setProduct(ContractServiceLevel product) {
        this.product = product;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }

    public String getProductGroup() {
        return this.productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
        updateServiceLevelName();
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
        updateServiceLevelName();
    }

    public List<Field> getProductList() {
        ReferenceDataService refenceDataSerivce = ServiceManager.getReferenceDataService();
        return refenceDataSerivce.getProducts(productGroup);
    }
    
    public String getDescription() {
        return product.getDescription();
    }

    public void setDescription(String description) {
        product.setDescription(description);
    }

    public double getExtendedPriceTotal() {
        double total = 0.0;
        for (CEScJobOrderLineItemForm joliForm : this.joliForms) {
            total += joliForm.getExtendedPrice();
            for (CEScJobOrderLineItemForm splitForm : joliForm.getSplitItems()) {
                total += splitForm.getExtendedPrice();
            }
        }
        
        return total;
    }
    
    public Set<CEJobOrderLineItem> getSelectedJolis() {
        Set<CEJobOrderLineItem> selectedJolis = new HashSet<CEJobOrderLineItem> ();
        for(CEScJobOrderLineItemForm joliForm : joliForms) {
            if(joliForm.isSelectedFlag()) {
                selectedJolis.add(joliForm.getCeJobOrderLineItem());
            }
            for(CEScJobOrderLineItemForm splitForm : joliForm.getSplitItems()) {
                if(splitForm.isSelectedFlag()) {
                    selectedJolis.add(splitForm.getCeJobOrderLineItem());
                }
            }
        }
        
        return selectedJolis;
    }
    
    /**
     * To order/ sort JOLIs using line number
     * 
     * @author rautsmit
     * 
     */
    private class MasterJoliComparator implements Comparator<CEScJobOrderLineItemForm> {
        @Override
        public int compare(CEScJobOrderLineItemForm form1, CEScJobOrderLineItemForm form2) {
            Long lineNum1 = form1.getCeJobOrderLineItem().getLineNumber();
            Long lineNum2 = form2.getCeJobOrderLineItem().getLineNumber();

            return lineNum1.compareTo(lineNum2);
        }
    }

}
