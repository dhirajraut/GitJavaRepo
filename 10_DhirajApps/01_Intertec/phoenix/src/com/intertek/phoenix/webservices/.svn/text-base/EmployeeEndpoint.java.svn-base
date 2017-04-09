package com.intertek.phoenix.webservices;

import java.sql.Timestamp;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.Employee;
import com.intertek.phoenix.externalEntity.EmployeeX;
import com.intertek.phoenix.externalEntity.response.AcknowledgementX;
/*
 * @author Eric.Nguyen
 */
public class EmployeeEndpoint extends BaseJDomPayloadEndpoint<EmployeeX, AcknowledgementX>{

    @Override
    public String getId(EmployeeX obj) {
        return obj.getEmployeeId();
    }

    @Override
    public AcknowledgementX process(EmployeeX obj) throws PhoenixException {
        System.out.println("Got Employee Endpoint");

        Employee em=obj.convert();
        em.setUpdateDate(new Timestamp(System.currentTimeMillis()));
        getEsbService().saveOrUpdate(Employee.class, em);

        AcknowledgementX result = new AcknowledgementX();
        result.setStatusCode("success");
        return result;
    }


}
