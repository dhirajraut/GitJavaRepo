package com.intertek.export.template;

import java.util.List;

public interface ITemplate {
    List<ExportColumn> getHeader(); // list of column objects -- headers
    boolean hasMoreRow();
    Object getNextRow(); // list of column objects -- values
    String getName();
}
