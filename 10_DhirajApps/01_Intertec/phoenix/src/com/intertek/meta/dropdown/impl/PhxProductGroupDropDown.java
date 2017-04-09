package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.intertek.entity.ProductGroup;
import com.intertek.entity.RB;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.service.JobService;

/*
 * PhxProductGroupDropDown is the temporary class to 
 * fetch the ProductGroup - parameter CEJobOrder.   
 */
public class PhxProductGroupDropDown implements DropDown {
    @SuppressWarnings("unchecked")
    public List execute(List params) {
        if (params == null || params.isEmpty()) {
            return null;
        }

        List results = new ArrayList();
        List<String> productTypes = new ArrayList<String>();

        CEJobOrder jo = (CEJobOrder) params.get(0);
        try {
            jo = DaoManager.getDao(CEJobOrder.class).merge(jo);
        }
        catch(DaoException e) {
            throw new ServiceException("Problem while loading product dropdown values");
        }
        Set<CEJobContract> jcs = jo.getJobContracts();
        for (CEJobContract jc : jcs) {
            productTypes.add(jc.getProductType());
        }

        JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");

        List productGrps = jobService.getProductGroups(productTypes);
        Field field1 = new Field();
        field1.setName("");
        field1.setValue("");
        results.add(field1);

        for (int i = 0; i < productGrps.size(); i++) {
            ProductGroup productGrp = (ProductGroup) productGrps.get(i);

            // Get the RB object for each product group
            RB rb = jobService.getRBByRBKeyAndContractCode(productGrp.getRbKey(), "NONE");

            if (rb != null) {
                Field field = new Field();
                field.setName(rb.getRbValue());
                field.setValue(productGrp.getProductGroupId().getGroupId());
                results.add(field);
            }
        }

        Comparator myComp = new Comparator() {
            public int compare(Object o1, Object o2) {
                try {
                    Field f1 = (Field) o1;
                    Field f2 = (Field) o2;
                    String n1 = f1.getName();
                    String n2 = f2.getName();

                    String g1 = n1.substring(n1.lastIndexOf("-"));
                    String g2 = n2.substring(n2.lastIndexOf("-"));

                    int g = g1.compareToIgnoreCase(g2);
                    if (g != 0) {
                        return g;
                    }
                    return n1.compareToIgnoreCase(n2);
                }
                catch (Exception e) {
                    // e.printStackTrace();
                }
                return -1;
            }
        };

        Collections.sort(results, myComp);

        return results;
    }
}
