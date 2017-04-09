/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix;

import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.common.PhxUserService;
import com.intertek.phoenix.common.PhxUserServiceImpl;
import com.intertek.phoenix.common.ReferenceDataService;
import com.intertek.phoenix.common.ReferenceDataServiceImpl;
import com.intertek.phoenix.esb.ESBService;
import com.intertek.phoenix.esb.ESBServiceImpl;
import com.intertek.phoenix.invoice.InvoiceService;
import com.intertek.phoenix.invoice.InvoiceServiceImpl;
import com.intertek.phoenix.job.CEJobOrderServiceImpl;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.job.JobOrderServiceImpl;
import com.intertek.phoenix.pricing.PricingSrvc;
import com.intertek.phoenix.pricing.PricingSrvcImpl;
import com.intertek.phoenix.report.ReportService;
import com.intertek.phoenix.report.ReportServiceImpl;
import com.intertek.phoenix.search.SearchService;
import com.intertek.phoenix.search.SearchServiceImpl;
import com.intertek.phoenix.service.PurchaseOrderService;
import com.intertek.phoenix.service.PurchaseOrderServiceImpl;
import com.intertek.phoenix.tax.TaxSrvc;
import com.intertek.phoenix.tax.TaxSrvcImpl;

/**
 * This is a temporary service locator implementation for phoenix services. The
 * actual implementation may use Spring's ApplicationContext and be integrated
 * with old phoenix's implementation.
 * 
 * @author richard.qin
 */
public class ServiceManager {
    private static JobOrderService jobOrderService = new JobOrderServiceImpl();
    private static InvoiceService invoiceService = new InvoiceServiceImpl();
    private static SearchService searchService = new SearchServiceImpl();
    private static ReportService reportService = new ReportServiceImpl();
    private static PurchaseOrderService poService = new PurchaseOrderServiceImpl();
    private static PricingSrvc pricingSrvc = new PricingSrvcImpl();
    private static PhxUserService userService = new PhxUserServiceImpl();
    private static ReferenceDataService referenceDataService = new ReferenceDataServiceImpl();
    private static TaxSrvc taxSrvc = new TaxSrvcImpl();

    private static boolean useStatic = true;

    public static JobOrderService getJobOrderService() {
        if (!useStatic) {
            return (JobOrderService) ServiceLocator.getInstance().getBean("jobOrderService");
        }
        return jobOrderService;
    }

    public static InvoiceService getInvoiceService() {
        if (!useStatic) {
            return (InvoiceService) ServiceLocator.getInstance().getBean("ceInvoiceService");
        }
        return invoiceService;
    }

    public static SearchService getSearchService() {
        if (!useStatic) {
            return (SearchService) ServiceLocator.getInstance().getBean("searchService");
        }
        return searchService;
    }

    public static ReportService getReportService() {
        if (!useStatic) {
            return (ReportService) ServiceLocator.getInstance().getBean("reportService");
        }
        return reportService;
    }

    public static PurchaseOrderService getPOService() {
        if (!useStatic) {
            return (PurchaseOrderService) ServiceLocator.getInstance().getBean("purchaseOrderService");
        }
        return poService;
    }

//    public static JobOrderService getCEJobService() {
//        if (!useStatic) {
//            return (JobOrderService) ServiceLocator.getInstance().getBean("ceJobOrderService");
//        }
//        return ceJobSrvc;
//    }

    public static PricingSrvc getPricingSrvc() {
        if (!useStatic) {
            return (PricingSrvc) ServiceLocator.getInstance().getBean("pricingSrvc");
        }
        return pricingSrvc;
    }

    public static PhxUserService getUserService() {
        if (!useStatic) {
            return (PhxUserService) ServiceLocator.getInstance().getBean("phxUserService");
        }
        return userService;
    }

    public static ReferenceDataService getReferenceDataService() {
        if (!useStatic) {
            return (ReferenceDataService) ServiceLocator.getInstance().getBean("referenceDataService");
        }
        return referenceDataService;
    }

    public static ESBService getEsbService() {
        return (ESBService) ServiceLocator.getInstance().getBean("esbService"); //will not work with static
    }

    public static TaxSrvc getTaxSrvc() {
        if (!useStatic) {
            return (TaxSrvc) ServiceLocator.getInstance().getBean("taxSrvc");
        }
        return taxSrvc;
    }

}
