package com.intertek.phoenix.ajax;

import com.intertek.entity.ServiceLocation;
import com.intertek.phoenix.util.CommonUtil;

public class ServiceLocationAjaxWrapper implements AjaxWrapper {
    
    private ServiceLocation serviceLocation;
    public ServiceLocationAjaxWrapper()
    {}
    public ServiceLocationAjaxWrapper(ServiceLocation serviceLocation){
        this.serviceLocation = serviceLocation;
    }

    @Override
    public String  getValue(){
        if (serviceLocation != null){
           return serviceLocation.getValue()+ " | " + serviceLocation.getServiceLocationCode();
        }
        return null;
    }

    @Override
    public String getText() {
        if (serviceLocation != null){
            return getValue();
        }
        return null;
    }

    @Override
    public void setObject(Object obj) {
        serviceLocation = (ServiceLocation) obj;
    }

}
