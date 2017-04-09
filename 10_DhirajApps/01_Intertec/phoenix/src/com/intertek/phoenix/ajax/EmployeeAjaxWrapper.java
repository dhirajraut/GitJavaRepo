package com.intertek.phoenix.ajax;

import com.intertek.phoenix.entity.Employee;

public class EmployeeAjaxWrapper implements AjaxWrapper {

    private Employee employee;

    public EmployeeAjaxWrapper() {
    }

    public EmployeeAjaxWrapper(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String getValue() {
        if (employee != null) {
            return employee.getFirstName()+ " " + employee.getLastName()+ " (BU="+employee.getBusinessUnit()+")|"+employee.getEmployeeId();
        }
        return null;
    }

    @Override
    public String getText() {
        if (employee != null) {
            return employee.getFirstName()+ " " + employee.getLastName()+ " (BU="+employee.getBusinessUnit()+")";
        }
        return null;
    }

    @Override
    public void setObject(Object obj) {
        employee = (Employee) obj;
    }

}
