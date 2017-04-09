/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.util;

import java.util.List;

import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.web.controller.job.CEJobOrderForm;

/**
 * 
 * @author eric.nguyen
 */
public class JSPUtil {
    
    public static List<Field> getProductFromProductGroup(Object obj, String groupId){
        CEJobOrderForm command=(CEJobOrderForm)obj;
        return command.getProductFields(groupId);
    }

}
