package com.intertek.phoenix.webservices;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.externalEntity.DCAppBillableItemX;
import com.intertek.phoenix.externalEntity.response.BillableItemsResultX;
import com.intertek.phoenix.search.SearchService;

/*
 * @author Eric.Nguyen
 */
public class DCAppBillableItemsEndpoint extends BaseJDomPayloadEndpoint<DCAppBillableItemX, BillableItemsResultX> {
    private SearchService searchService;

    @Override
    public BillableItemsResultX process(DCAppBillableItemX obj) throws PhoenixException {
        System.out.println("Got DCAppBillableItemsEndpoint");
        BillableItemsResultX result = new BillableItemsResultX();
        result.setStatusCode("success");
        return result;
    }

    @Override
    public String getId(DCAppBillableItemX obj) {
        return obj.getOrderNumber();
    }

    public SearchService getSearchService() {
        return searchService;
    }

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }
}
