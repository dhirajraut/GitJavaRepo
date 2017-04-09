/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.esb;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.intertek.tool.oxm.OxmManager;

/**
 * ESB Related services
 * 
 * @author Eric.Nguyen
 */
public class WebServiceSender {
    protected WebServiceTemplate wsTemplate;
    protected OxmManager oxmManager;
    protected WebServiceMessageCallback callBack;
    
    public WebServiceTemplate getWsTemplate() {
        return wsTemplate;
    }

    public void setWsTemplate(WebServiceTemplate wsTemplate) {
        this.wsTemplate = wsTemplate;
    }

    public OxmManager getOxmManager() {
        return oxmManager;
    }

    public void setOxmManager(OxmManager oxmManager) {
        this.oxmManager = oxmManager;
    }

    public WebServiceMessageCallback getCallBack() {
        return callBack;
    }

    public void setCallBack(WebServiceMessageCallback callBack) {
        this.callBack = callBack;
    }
    
    public boolean sendSourceAndReceiveToResult(String uri, Source source, Result result){
        return getWsTemplate().sendSourceAndReceiveToResult(uri, source, getCallBack(), result);
    }
}
