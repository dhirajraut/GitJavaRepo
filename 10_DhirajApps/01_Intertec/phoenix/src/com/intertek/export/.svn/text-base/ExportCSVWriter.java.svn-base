package com.intertek.export;

import java.io.PrintWriter;

public class ExportCSVWriter implements IExportWriter {
    private static final String COLUMN_DELIM = ",";
    private static final String ROW_DELIM = "\n";
    private static final String[][] ESCAPES = { { "\"", "\"\"" } };

    private PrintWriter writer;
    private boolean written=false;
    
    public ExportCSVWriter(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void write(Object value) {
        if(written){
            writer.print(COLUMN_DELIM);
        }
        written=true;
        if (value == null) {
        }
        else {
            writer.print(escapeCSV(value));
        }
    }

    @Override
    public void writeNextRow() {
        writer.print(ROW_DELIM);
        written=false;
    }

    private static String escapeCSV(Object obj) {
        if (obj == null) {
            return "";
        }
        else {
            String s = obj.toString();
            for (int i = 0; i < ESCAPES.length; i++) {
                s = s.replaceAll(ESCAPES[i][0], ESCAPES[i][1]);
            }
            return "\"" + s + "\"";
        }
    }
}
