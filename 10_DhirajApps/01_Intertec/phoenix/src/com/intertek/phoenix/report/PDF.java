/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.report;

/**
 * PDF is not an entity class. It is a simple value object that encapsulate
 * a single pdf file generated by the report service.
 * <p>
 * TODO details to be filled
 * @author richard.qin
 */
public class PDF {

    private String fileName;
    
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
