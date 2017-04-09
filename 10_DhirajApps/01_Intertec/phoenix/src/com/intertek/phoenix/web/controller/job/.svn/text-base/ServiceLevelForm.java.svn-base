package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;
import org.springmodules.validation.bean.conf.loader.annotation.handler.NotBlank;

import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.JobServiceLevel;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.job.ServiceLevel.ServiceLevelType;
import com.intertek.phoenix.util.CommonUtil;

// RQ: the name of this class is not appropriate, as it only deals
// with one special case of service level - product. It cannot be
// used by other ServiceLevels, such as vessel or lot. 
// Should refactor it. TODO
public class ServiceLevelForm extends Form {

    @CascadeValidation
    private JobServiceLevel product;
    private String productGroup;
    private String productName;
    private JobTestForm[] jobTests;
    
    public long getId() {
        if (product != null) {
            return product.getId();
        }
        else {
            return -1;
        }
    }

    public void setId(long id) {
        this.product.setId(id);
    }

    public ServiceLevelType getType() {
        return product.getServiceLevelType();
    }

    public String getJobOrderNumber() {
        return this.product.getJobOrderNumber();
    }

    public String getName() {
        return this.product.getServiceLevelName();
    }

    public void updateServiceLevelName() {
        
        String tmpServiceLevelName = null;
        if(!CommonUtil.isNullOrEmpty(productGroup))
        {
            tmpServiceLevelName=productGroup;
        }
        if(!CommonUtil.isNullOrEmpty(productName))
        {
            tmpServiceLevelName=tmpServiceLevelName+"/"+productName;            
        }        
        product.setServiceLevelName(tmpServiceLevelName);
    }

    public void splitServiceLevelName() {
        
        String tmpServiceLevelName = product.getServiceLevelName();
        if(!CommonUtil.isNullOrEmpty(tmpServiceLevelName))
        {
            int position =tmpServiceLevelName.indexOf("/");
            if(position>0){
                productGroup=tmpServiceLevelName.substring(0,position);
                productName = tmpServiceLevelName.substring(position+1);
                System.out.println("inside Service Level Form , value of Product name is !"+productName+"!");
                // Useless setter called
                // this.setProductName(productName.trim());
            }
        }
        //This block of code is useless
//        if(product!=null && !"".equals(product))
//        {
//            tmpServiceLevelName=tmpServiceLevelName+"/"+product;
//        }
    }
    
    @NotBlank(errorCode = "not.blank")
    public void setName(String serviceLevelName) {
        this.product.setServiceLevelName(serviceLevelName);
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public JobServiceLevel getProduct() {
        return product;
    }

    public JobTestForm[] getJobTest() {
        return jobTests;
        // RQ: the old code is refactored, because it was so not good.
    }

    
    public void setProduct(JobServiceLevel product) {
        this.product = product;
        this.updateForm();
        
//        if (product != null && product.getJobTests() != null && product.getJobTests().size() > 0) {
//            List<JobTestForm> result = new ArrayList<JobTestForm>();
//            for(JobTest jobTest: product.getJobTests()){
//                result.add(new JobTestForm(jobTest));
//            }
            
//            JobTest tmpJobTest = null;
//            for (int i = result.length - 1; i >= 0; i--) {
//                for (int k = i - 1; k >= 0; k--) {
//                    if (result[k].getLinenumber() > result[i].getLinenumber()) {
//                        tmpJobTest = result[i];
//                        result[i] = result[k];
//                        result[k] = tmpJobTest;
//                    }
//                }
//
//            }
        
//            this.jobTests = result.toArray(new JobTestForm[0]);
//        }
    }

    public String getDescription() {
        return product.getDescription();
    }

    @NotBlank(errorCode = "not.blank")
    public void setDescription(String description) {
        product.setDescription(description);
    }
    
    public void updateForm(){
        if(product != null){
            this.jobTests = JobTestForm.buildJobTestForms(product.getJobTests());
        }
    }
}
