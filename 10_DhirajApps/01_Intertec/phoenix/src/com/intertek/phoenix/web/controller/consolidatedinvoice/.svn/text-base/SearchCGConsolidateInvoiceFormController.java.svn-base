package com.intertek.phoenix.web.controller.consolidatedinvoice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.exception.ServiceException;
import com.intertek.export.Exporter;
import com.intertek.pagination.Pagination;
import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.SortInfo;
import com.intertek.phoenix.entity.ConsolidatedInvoice;
import com.intertek.phoenix.export.template.ConsolInvoiceExcel;
import com.intertek.phoenix.export.template.ConsolInvoiceSheet;
import com.intertek.phoenix.search.SearchCriteria;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.web.controller.AbstractSearchFormController;

public class SearchCGConsolidateInvoiceFormController extends AbstractSearchFormController {
    private static Log log = LogFactory.getLog(SearchCGConsolidateInvoiceFormController.class);

    public SearchCGConsolidateInvoiceFormController() {
        super();
        setSessionForm(true);
        setCommandClass(SearchConsolidatedInvoiceForm.class);
    }

    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        SearchConsolidatedInvoiceForm searchForm = (SearchConsolidatedInvoiceForm) command;
        String pageNumberStr = request.getParameter("pageNo");
        Pagination pagination = doPagination(pageNumberStr);

        SearchService searchService = ServiceManager.getSearchService();
        List<SearchCriteria> criteria = new ArrayList<SearchCriteria>();
        SortInfo sortInfo = buildSortInfo(searchForm);
        String buName = searchForm.getBuName().getValue();
        String custCode = searchForm.getCustCode().getValue();
        String custName = searchForm.getCustomerName().getValue();
        String invoice = searchForm.getInvoice().getValue();

        if (buName != null && !buName.equals("")) {
            criteria.add(new SearchCriteria("id.buName", buName, ""));
        }

        if (custCode != null && !custCode.equals("")) {
            criteria.add(new SearchCriteria("custCode", custCode, ""));
        }
        if (custName != null && !custName.equals("")) {
            criteria.add(new SearchCriteria("customer.name", custName, "equals"));
        }

        if (invoice != null && !invoice.equals("")) {
            criteria.add(new SearchCriteria("id.invoice", invoice, "equals"));
        }

        List<ConsolidatedInvoice> poList = (List<ConsolidatedInvoice>) searchService.advancedSearch(ConsolidatedInvoice.class, criteria, pagination, sortInfo);

        if (poList.size() > 0) {
            searchForm.setResults(poList);
            if (searchForm.getCxcel().equalsIgnoreCase("true")) {
                if (exportToExcel(poList, response)) {
                    return null;
                }
            }
        }
        else {
            log.error("Results for search string not found");
            throw new ServiceException("search.string.not.exist.error", new Object[] {}, null);
        }

        return showForm(request, response, errors);
    }

    /**
     * This method will build the sort information
     * @param myForm
     * @return
     */
    private SortInfo buildSortInfo(SearchConsolidatedInvoiceForm myForm) {
        SortInfo sortInfo = null;
        String sortBy = myForm.getSortBy();
        if (sortBy != null && !"".equals(sortBy)) {
            sortInfo = new SortInfo();
            String sortFlag = myForm.getSortFlag();
            boolean ascending = true;
            if (sortFlag != null && !"".equals(sortFlag)) {
                ascending = "ASC".equals(sortFlag.toUpperCase());
            }
            if (sortBy.contains(",")) {
                String[] sortFields = sortBy.split(",");
                // This is a little ugly, but SortInfo has to have something to
                // sortBy before appending more sortby's
                for (int i = 0; i < sortFields.length; i++) {
                    if (i == 0) {
                        sortInfo = new SortInfo(sortFields[i], ascending);
                    }
                    else {
                        sortInfo.addSortInfo(new SortInfo(sortFields[i], ascending));
                    }
                }
            }
            else {
                sortInfo = new SortInfo(sortBy, ascending);
            }
        }
        return sortInfo;
    }

    /**
     * This method will take care of export to Excel.
     * 
     * @param ciList
     *            the list of Consolidate Invoice
     * @param response
     *            the HttpServletResponse
     * 
     * @return true, if successful
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private boolean exportToExcel(List<ConsolidatedInvoice> ciList, HttpServletResponse response) throws IOException {
        ConsolInvoiceExcel ciExcel = new ConsolInvoiceExcel(new ConsolInvoiceSheet(ciList));
        return Exporter.exportTOXCell(response, ciExcel);

    }

    protected Object formBackingObject(HttpServletRequest request) throws PhoenixException {
        return new SearchConsolidatedInvoiceForm();
    }
}
