package com.intertek.phoenix.web.controller.job;

import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.Estimation;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.UserType;

public class CEJobInstructionEstimationForm extends Form{
    
    private Long id;

    private UserType userType;
    private double estimatedHour;
    private String userTypeId;
    private Long jobTestId;
    private JobTest jobTest;
    private Estimation estimation;
    
    public CEJobInstructionEstimationForm(){
        
    }
    
    
    public Estimation getEstimation() {
        return estimation;
    }


    public void setEstimation(Estimation estimation) {
        this.estimation = estimation;
    }


    public Long getId() {
        
        return estimation.getId();
    }

    public void setId(Long id) {
        estimation.setId(id);
    }

    public UserType getUserType() {
        return estimation.getUserType();
    }

    public void setUserType(UserType userType) {
        estimation.setUserType(userType);
    }

    public double getEstimatedHour() {
        return estimation.getEstimatedHour();
    }

    public void setEstimatedHour(double estimatedHour) {
        estimation.setEstimatedHour(estimatedHour);
    }

    public Long getJobTestId() {
       return estimation.getJobTestId();
    }

    public void setJobTestId(Long jobTestId) {
        estimation.setJobTestId(jobTestId);
    }

    public String getUserTypeId() {
        
        if(estimation.getUserType()!=null){
            userTypeId=estimation.getUserType().getValue();
            return userTypeId;
        }else{
            return null;
        }
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;

        for(UserType uType : UserType.values()){
            if(uType.getValue().equals(userTypeId)){
                estimation.setUserType(uType);
                break;
            }
        }
    }


}
