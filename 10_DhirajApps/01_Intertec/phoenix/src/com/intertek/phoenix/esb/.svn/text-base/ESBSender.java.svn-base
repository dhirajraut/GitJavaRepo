/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.esb;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;

import com.intertek.entity.WebServiceEntity;
import com.intertek.exception.ServiceException;
import com.intertek.tool.transformer.DataTransformer;

/**
 * 
 * @author Eric.Nguyen
 */
public class ESBSender {
    protected String type;
    protected DataTransformer dataTransformer;
    protected String uri;
    protected WebServiceSender sender;

    public ESBSender() {
    }

    public WebServiceSender getSender() {
        return sender;
    }

    public void setSender(WebServiceSender sender) {
        this.sender = sender;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public DataTransformer getDataTransformer() {
        return dataTransformer;
    }

    public void setDataTransformer(DataTransformer dataTransformer) {
        this.dataTransformer = dataTransformer;
    }

    /*
     * send the given object write to logEntity if not null return the response
     * object if successful, otherwise throws Exception
     */
    public Object send(Object obj, WebServiceEntity logEntity) throws ServiceException {
        if (obj == null) {
            return null;
        }
        Object toReturn = null;

        StringWriter resultSW = new StringWriter();
        StreamResult result = new StreamResult(resultSW);
        boolean sent = false;
        Exception err = null;
        try {
            // convert to XML
            String content = null;
            if (obj instanceof String) {
                content = (String) obj;
            }
            else {
                Document doc = convertToDoc(obj);
                content = convertToXml(doc);
            }

            // create logEntity
            if (logEntity != null) {
                logEntity.setContent(content);
            }

            // trying to send
            StreamSource source = new StreamSource(new StringReader(content));
            sent = getSender().sendSourceAndReceiveToResult(uri, source, result);
        }
        catch (Exception e) {
            err = e;
        }

        try {
            if (sent) {
                toReturn = convertToObject(resultSW.toString());
            }
        }
        catch (Exception e) {
            err = e;
        }

        if (logEntity != null) {
            logEntity.setStatus(sent);
            if (err != null) {
                String ms=err.getMessage()+"";
                logEntity.setMessage(ms.substring(0, Math.min(1023, ms.length())));
            }
            String rs=resultSW.toString()+"";
            logEntity.setResult(rs.substring(0, Math.min(1023, rs.length())));
        }

        if (toReturn != null) {
            return toReturn;
        }

        if (err == null) {
            err = new RuntimeException("Unkown error");
        }

        throw new ServiceException(err.getMessage(), err);
    }

    /*
     * convert the given object to xml Document using OxmManager
     */
    public Document convertToDoc(Object obj) {
        Document doc = getSender().oxmManager.marshal(obj);
        return doc;
    }

    /*
     * convert xml back to java object
     */
    private Object convertToObject(String xml) throws JDOMException, IOException{
        System.out.println(xml);
        SAXBuilder builder=new SAXBuilder(); 
        Document doc = builder.build(new StringReader(xml));

        if (dataTransformer != null) {
            doc = dataTransformer.transform(doc);
        }
        return getSender().oxmManager.unmarshal(doc.getRootElement());
    }

    /*
     * convert from xml document to xml String
     */
    public String convertToXml(Document doc) throws IOException {
        print(doc);
        Document myDoc = doc;

        DataTransformer dataTransformer = getDataTransformer();
        if (dataTransformer != null) {
            myDoc = dataTransformer.transform(myDoc);
        }

        StringWriter sw = new StringWriter();
        XMLOutputter xmlOut = new XMLOutputter();
        xmlOut.output(myDoc, sw);
        String content = sw.toString();
        return content;
    }

    public void print(Document doc) throws IOException {
        StringWriter sw = new StringWriter();
        XMLOutputter xmlOut = new XMLOutputter();
        xmlOut.output(doc, sw);
        System.out.println("DOC:" + sw.toString());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
