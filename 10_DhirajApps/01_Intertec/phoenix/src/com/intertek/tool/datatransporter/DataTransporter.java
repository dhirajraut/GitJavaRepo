package com.intertek.tool.datatransporter;

import java.util.List;

public interface DataTransporter {
    
    String transport(String input, List<String> sysFileNameList);
    String send(String input, List<String> sysFileNameList);

}