package com.intertek.export;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class ExportXCellWriter implements IExportWriter {
    private HSSFSheet sheet;
    private HSSFRow row=null;
    private short rowIndex=0;
    private short cellIndex=0;
    
    public ExportXCellWriter(HSSFSheet sheet) {
        this.sheet = sheet;
    }

    @Override
    public void write(Object value) {
        if(row==null){
            row=sheet.createRow(rowIndex++);
        }
        HSSFCell cell=row.createCell(cellIndex++);
        if(value==null){
            cell.setCellType(HSSFCell.CELL_TYPE_BLANK);
        }        
        else if(value instanceof Date){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue((Date)value);
        }
        else if(value instanceof Integer){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue((Integer)value);
        }
        else if(value instanceof Long){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue((Long)value);
        }        
        else if(value instanceof Double){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue((Double)value);
        }
        else if(value instanceof BigDecimal){
            cell.setCellType(HSSFCell.CELL_TYPE_NUMERIC);
            cell.setCellValue(((BigDecimal)value).doubleValue());
        }        
        else if(value instanceof Boolean){
            cell.setCellType(HSSFCell.CELL_TYPE_BOOLEAN);
            cell.setCellValue((Boolean)value);
        }
        else{//default to String
            cell.setCellType(HSSFCell.CELL_TYPE_STRING);
            cell.setCellValue(new HSSFRichTextString(value.toString()));
        }
    }

    @Override
    public void writeNextRow() {
        row=sheet.createRow(rowIndex++);
        cellIndex=0;
    }
}
