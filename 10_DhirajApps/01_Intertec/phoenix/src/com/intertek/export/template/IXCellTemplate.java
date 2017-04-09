package com.intertek.export.template;

import java.util.List;

public interface IXCellTemplate{
    List<ITemplate> getSheets();    //return a list of Sheets in the xcell file
    String getFileName();
}
