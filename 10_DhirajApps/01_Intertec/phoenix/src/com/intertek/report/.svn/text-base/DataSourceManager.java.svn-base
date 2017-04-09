/**
 * 
 */
package com.intertek.report;

import java.sql.Connection;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

/**
 * DataSourceManager provides a simplified interface for getting JRDataSource objects, 
 * without the need to get the ReportDataSourceManager singlton instance. 
 * This class simply wraps around various methods available in ReportDataSourceManager.
 * 
 * This class is introduced to benefit Jasper reports so that a report data source can be
 * obtained by one simple method call.
 * 
 * @author richard.qin
 */
public class DataSourceManager {
    public static JRDataSource getReportDataSource(String reportName, Map<String, Object> params) {
        return ReportDataSourceManager.getManager().getReportDataSource(reportName, params);
    }

    public static JRDataSource getReportDataSource(String reportName, Map<String, Object> params, Connection conn) {
        return ReportDataSourceManager.getManager().getReportDataSource(reportName, params, conn);
    }

    public static JRDataSource getReportDataSource(String reportName, String[] keys, Object[] values, Connection conn) {
        return ReportDataSourceManager.getManager().getReportDataSource(reportName, keys, values, conn);
    }
}
