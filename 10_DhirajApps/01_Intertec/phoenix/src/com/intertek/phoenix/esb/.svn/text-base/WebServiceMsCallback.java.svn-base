/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.esb;

import java.io.IOException;

import javax.xml.soap.SOAPMessage;
import javax.xml.transform.TransformerException;

import org.springframework.core.io.Resource;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.soap.security.xwss.XwsSecuritySecurementException;

import com.sun.xml.wss.ProcessingContext;
import com.sun.xml.wss.XWSSProcessor;
import com.sun.xml.wss.XWSSProcessorFactory;
import com.sun.xml.wss.XWSSecurityException;

/**
 * ESB Related services
 * 
 * @author Eric.Nguyen
 */
public class WebServiceMsCallback implements WebServiceMessageCallback {
    private Resource xwssConfig;
    private String soapAction;

    public WebServiceMsCallback() {
    }

    @Override
    public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
        try {
            SaajSoapMessage saajSoapMessage = (SaajSoapMessage) message;
            
            if (xwssConfig != null) {
                ProcessingContext context = new ProcessingContext();
                XWSSProcessorFactory factory = XWSSProcessorFactory.newInstance();
                XWSSProcessor cprocessor = factory.createProcessorForSecurityConfiguration(xwssConfig.getInputStream(), null);
                SOAPMessage saajMessage = saajSoapMessage.getSaajMessage();
                context.setSOAPMessage(saajMessage);
                SOAPMessage securedMessage = cprocessor.secureOutboundMessage(context);
                saajSoapMessage.setSaajMessage(securedMessage);
            }
            
            if (soapAction != null) {
                saajSoapMessage.setSoapAction(soapAction);
            }
        }
        catch (XWSSecurityException e) {
            e.printStackTrace();
            throw new XwsSecuritySecurementException(e.getMessage());
        }
    }

    public Resource getXwssConfig() {
        return xwssConfig;
    }

    public void setXwssConfig(Resource xwssConfig) {
        this.xwssConfig = xwssConfig;
    }

    public String getSoapAction() {
        return soapAction;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }
}
