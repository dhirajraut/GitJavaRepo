package com.intertek.export;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.intertek.export.template.ExportColumn;
import com.intertek.export.template.ITemplate;
import com.intertek.export.template.IXCellTemplate;
import com.intertek.phoenix.search.Search;
import com.intertek.phoenix.util.DataTransformer;
import com.intertek.util.BeanUtil;

public class Exporter {
    public static final String APPLICATION_CONTENT_TYPE="application/download";
    
    public static boolean exportTOXCell(HttpServletResponse response, IXCellTemplate template) throws IOException {
        if (template != null && template.getSheets() != null && !template.getSheets().isEmpty()) {
            writeHttpHeader(response, APPLICATION_CONTENT_TYPE, template.getFileName());
            OutputStream out = response.getOutputStream();
            exportToXCell(out, template);
            out.close();
            return true;
        }
        return false;
    }

    public static void exportToXCell(OutputStream out, IXCellTemplate template) throws IOException {
        HSSFWorkbook workBook = new HSSFWorkbook();

        List<ITemplate> sheets = template.getSheets();
        for (ITemplate sheet : sheets) {
            HSSFSheet xSheet= workBook.createSheet(sheet.getName());
            export(new ExportXCellWriter(xSheet), sheet);
        }

        workBook.write(out);
    }


    public static boolean exportTOCSV(HttpServletResponse response, ITemplate template) throws IOException {
        if (template != null && template.hasMoreRow()) {
            writeHttpHeader(response, APPLICATION_CONTENT_TYPE, template.getName());
            PrintWriter writer = response.getWriter();
            export(new ExportCSVWriter(writer), template);
            writer.close();
            return true;
        }
        return false;
    }

    public static void export(IExportWriter writer, ITemplate template) throws IOException {
        List<ExportColumn> header = template.getHeader();
        int numCols = header.size();
        for (int i = 0; i < numCols; i++) {
            ExportColumn h = header.get(i);
            writer.write(h.getText());
        }

        while (template.hasMoreRow()) {
            writer.writeNextRow();
            Object obj = template.getNextRow();
            for (int i = 0; i < numCols; i++) {
                ExportColumn col = header.get(i);
                Object value = BeanUtil.getProperty(obj, col.getName());
                writer.write(value);
            }
        }
    }

    /**
     * Take a search object and convert its result into a csv file and export
     * it.
     * 
     * @param response
     *            HttpServletResponse
     * @param search
     *            Search<?> a search object that contains the result to be
     *            exported
     * @return true if successful, otherwise false
     * @throws IOException
     */
    public static boolean exportTOCSV(HttpServletResponse response, Search<?> search) throws IOException {
        if (search != null && search.getResultSize() > 0) {
            writeHttpHeader(response, search.getExportContentType(), search.getExportFileName());
            PrintWriter writer = response.getWriter();
            exportToCSV(writer, search);
            writer.close();
        }
        return true;
    }

    /**
     * Export the search result to a CSV file. The result is an ArrayMapGrid
     * object, which is a tabular data construct. ArrayMapGrid is backed by an
     * ArrayMap object which support very fast lookup.
     * 
     * @param writer
     *            The output sink
     * @param search
     *            The search that contains the ArrayMapGrid and supporting info
     * @throws IOException
     */
    public static void exportToCSV(PrintWriter writer, Search<?> search) throws IOException {
        String headers = DataTransformer.toCsvString(search.getResultHeaders(), ",");
        writer.println(headers);

        for (int k = 0; k < search.getResultSize(); k++) {
            Object[] result = search.getResultAsStrings(k);
            String[] strings = DataTransformer.flattenStrings(result);
            String line = DataTransformer.toCsvString(strings, ",");
            writer.println(line);
        }
    }

    private static void writeHttpHeader(HttpServletResponse response, String contentType, String filename) {
        response.setContentType(contentType);
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        response.setHeader("Pragma", "public");
        response.setHeader("Cache-Control", "max-age=0");
    }
}
