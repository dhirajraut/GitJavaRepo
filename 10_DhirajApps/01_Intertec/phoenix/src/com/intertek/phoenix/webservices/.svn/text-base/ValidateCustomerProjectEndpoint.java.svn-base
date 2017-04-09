package com.intertek.phoenix.webservices;

import java.util.Set;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.externalEntity.ProjectValidationX;
import com.intertek.phoenix.externalEntity.response.CustomerProjectValidationResultX;
import com.intertek.phoenix.search.SearchService;

/*
 * @author Eric.Nguyen
 */
public class ValidateCustomerProjectEndpoint extends BaseJDomPayloadEndpoint<ProjectValidationX, CustomerProjectValidationResultX> {
    private SearchService searchService;

    @Override
    public CustomerProjectValidationResultX process(ProjectValidationX obj) throws PhoenixException {
        System.out.println("Got ValidateCustomerProjectEndpoint");
        CEJobOrder jo=new CEJobOrder();
        Dao<CEJobOrder> dao=DaoManager.getDao(CEJobOrder.class);
        jo.setProjectNumber(obj.getProjectNumber());
        jo = dao.searchUnique(jo);

        boolean exists = false;
        if (jo != null) {
            Set<CEJobContract> contracts = jo.getJobContracts();
            // should only have at most one contract
            for (CEJobContract ct : contracts) {
                if ((ct.getCustomerCode() + "").equals(obj.getCustomerId())) {
                    exists = true;
                    break;
                }
            }
        }

        if (!exists) {
            throw new PhoenixException("validation failed");
        }

        CustomerProjectValidationResultX result = new CustomerProjectValidationResultX(jo);

        return result;
    }

    @Override
    public String getId(ProjectValidationX obj) {
        return obj.getCustomerId();
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
