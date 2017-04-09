package com.intertek.tool.datatransporter.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.ContentType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.locator.ServiceLocator;
//import com.intertek.mail.AribaMimeMessagePreparator;
import com.intertek.tool.datatransporter.DataTransporter;
import com.intertek.util.PropertyConfig;
import com.sun.mail.util.LineOutputStream;

/**
 * Purpose: Transporting XML document to remote URLs
 */
public class DataTransporterImpl implements DataTransporter{
    private static Log log = LogFactory.getLog(DataTransporterImpl.class);
    private String url;
    static final int BUFF_SIZE = 1024;
    static final byte[] buffer = new byte[BUFF_SIZE];
    private String  boundary = "kdflkajfdksadjfk";
    private StringBuffer bf = new StringBuffer();
    private HttpURLConnection connection = null;
    
    public String send(String input, List<String> sysFileNameList){
    	
    	 System.out.println("here in send");
     	StringBuffer resultsb = new StringBuffer();
         URL url = null;
         try {
             url = new URL(getUrl());
         } catch(MalformedURLException e) {log.error(e.getMessage()); return null;}
         InputStream istr = null;
         OutputStream ostr = null;
         try {
             connection = (HttpURLConnection)url.openConnection();
             connection.setDoOutput(true);
             connection.setDoInput(true);
             connection.setUseCaches(false);
             connection.setDefaultUseCaches(false);
             connection.setRequestProperty("Content-type","multipart/mixed; boundary=" + boundary + "; type=\"text/xml\"; start=\"<part1@intertek.com>\"");
             connection.setRequestProperty("Cache-Control", "no-cache");
             connection.addRequestProperty("Accept", "application/pdf, image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*");

             connection.connect();
             ostr = connection.getOutputStream();
             
         } catch(IOException e) {log.error(e.getMessage()); return null;}
         //Write Output
         DataOutputStream writer = new DataOutputStream(ostr);
         MimeMultipart ma = new MimeMultipart("mixed");
         MimeBodyPart messageBodyPart = new MimeBodyPart();
         
                 
         try {
        	 
        	LineOutputStream los = new LineOutputStream(writer);
        	los.writeln("--" + boundary);
			messageBodyPart.setContentID("<part1@intertek.com>");
			messageBodyPart.setHeader("Content-Type", "text/xml; boundary=" + boundary);
			messageBodyPart.setContent(input, "text/xml");
            ma.addBodyPart(messageBodyPart);
            messageBodyPart.writeTo(writer);
            los.writeln(); 
         int partNumber=2;
         if (sysFileNameList != null && !sysFileNameList.isEmpty()) {
        	 String filePath = null; 
         	PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
     		if(pRB != null)
     			filePath = pRB.getString(com.intertek.util.Constants.jobcontractfilepath).concat("/");
             for (String sysFileName : sysFileNameList) {
            	 System.out.println("sysFileName"+sysFileName);
                 if (sysFileName != null) {
                	 los.writeln("--" + boundary);
                     messageBodyPart = new MimeBodyPart();
                     FileDataSource ifds = new FileDataSource(filePath+sysFileName);
                     //FileInputStream fis = new FileInputStream(filePath+name);
                     messageBodyPart.setDataHandler(new DataHandler(ifds));
                    // messageBodyPart.setDataHandler(new DataHandler(new URL(sysFileName)));
                     String bpct = messageBodyPart.getContentType();
                     messageBodyPart.setHeader("Content-Type", bpct + ";boundary=" + boundary);
                     messageBodyPart.setHeader("Content-ID", "<part" + partNumber + "@intertek.com>");
                     messageBodyPart.setDisposition("attachment");
                     partNumber++;
                     messageBodyPart.setFileName(sysFileName);
                     messageBodyPart.writeTo(writer);
                     los.writeln();
                     ma.addBodyPart(messageBodyPart);
                 }
             }
             los.writeln(boundary + "--");
         }
         //ma.writeTo(writer);
         

	     writer.flush();
         writer.close();
         istr = connection.getInputStream();
         BufferedReader reader = new BufferedReader(new InputStreamReader(istr));
         String line;
         while ((line = reader.readLine()) != null) 
         	resultsb.append(line);
         } catch (Exception e) {
 			e.printStackTrace();
 		}
		 return resultsb.toString();
    }

    public String transport(String input, List<String> sysFileNameList) {
    	System.out.println("here in transport");
    	StringBuffer resultsb = new StringBuffer();
        URL url = null;
        try {
            url = new URL(getUrl());
        } catch(MalformedURLException e) {log.error(e.getMessage()); return null;}
        
        
        InputStream istr = null;
        OutputStream ostr = null;
        
        
        try {
            //sh    	connection = url.openConnection();
        	connection = (HttpURLConnection)url.openConnection();
        	connection.setRequestMethod("POST");
        	connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setDefaultUseCaches(false);
            //connection.setRequestProperty("Content-type","multipart/mixed; boundary=" + boundary + "; type=\"text/xml\"; start=\"<part1@intertek.com>\"");
            
            if(sysFileNameList!=null && sysFileNameList.size()>0){
            	 connection.setRequestProperty("Content-type","multipart/mixed; boundary=" + boundary + "; type=\"text/xml\"; start=\"<part1@intertek.com>\"");
                 connection.setRequestProperty("Cache-Control", "no-cache");
                 connection.addRequestProperty("Accept", "application/pdf, image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, */*");
            }
            
           
            connection.connect();
            ostr = connection.getOutputStream();
            
        } catch(IOException e) {log.error(e.getMessage()); return null;}
        
        //Write Output
        DataOutputStream writer = new DataOutputStream(ostr);
        try {
        	 if(sysFileNameList!=null && sysFileNameList.size()>0){
        	        //multipart/form-data
        		    //multipart/mixed
        		    //multipart/related
	        		//shwriter.writeBytes("Content-type: multipart/mixed; boundary=" + boundary + "; type=\"text/xml\"; start=\"<part1@intertek.com>\"" + "\r\n\r\n");
	            	//shbf.append("Content-type: multipart/mixed; boundary=" + boundary + "; type=\"text/xml\"; start=\"<part1@intertek.com>\"" + "\r\n\r\n");
	            	
	            	//shwriter.writeBytes("--" + boundary + "\r\n");
	            	//shbf.append("--" + boundary + "\r\n");
	            	//writer.writeBytes("Content-type: text/xml; charset=UTF-8" + "\r\n");
	            	//shwriter.writeBytes("Content-type: application/xml;" + "\r\n");
	            	//shbf.append("content-type: text/xml; charset=UTF-8" + "\r\n");
        		 connection.addRequestProperty("Content-Type","text/xml");
        		 connection.addRequestProperty("Content-ID","<part1@intertek.com>");
        		 
        		//sh writer.writeBytes("Content-ID: <part1@intertek.com>" + "\r\n\r\n");
	            	//shbf.append("Content-ID: <part1@intertek.com>" + "\r\n\r\n");
        	 }
        	writer.writeBytes(input);
        	bf.append(input);
        	System.out.println("after input");
        	
        	
            if(sysFileNameList!=null && sysFileNameList.size()>0){
            	//shwriter.writeBytes("\r\n");
            	//shbf.append("\r\n");
            	//shwriter.writeBytes("--" + boundary + "\r\n");
            	//shbf.append("--" + boundary + "\r\n");
            	
            	String filePath = null; 
            	PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
        		if(pRB != null)
        			filePath = pRB.getString(com.intertek.util.Constants.jobcontractfilepath).concat("/");
        		 
            	
            	System.out.println("filepath"+filePath);
                int partNumber=2;
            	for(String sysFileName : sysFileNameList){
            		this.writeFile(sysFileName, filePath, writer, partNumber);
            		partNumber++;
            	}
        	}
  /*sh      
        	
        	
        	writer.writeBytes("content-disposition: form-data; name=\"Nomination\"; filename=\"Nomination.pdf\"" + "\r\n");
        	bf.append("content-disposition: form-data; name=\"Nomination\"; filename=\"Nomination.pdf\"" + "\r\n");
        	//writer.writeBytes("content-type: application/pdf; charset=binary" + "\r\n");
        	writer.writeBytes("content-type: application/octet-stream" + "\r\n\r\n");

        	bf.append("content-type: application/octet-stream" + "\r\n\r\n");
        	writer.writeBytes("Content-ID: <part2@intertek.com>" + "\r\n\r\n");
        	bf.append("Content-ID: <part2@intertek.com>" + "\r\n\r\n");
        	
        	FileInputStream fs = new FileInputStream("C:\\docs\\ExxonMobil\\ARIBAATTACHMENTS\\Nomination.pdf");
        	
        	FileReader fr= new FileReader("C:\\docs\\ExxonMobil\\ARIBAATTACHMENTS\\Nomination.pdf");
        	BufferedReader reader = new BufferedReader(fr);
        	String line = "";
        	try {
        	    while( (line = reader.readLine()) != null ) {
        	    	writer.writeBytes(line);
        	    }
        	    reader.close();
        	    fr.close();
        	} catch (IOException e) {
        	    e.printStackTrace();
        	}
 
       	while (true) {
                 synchronized (buffer) {
                     int amountRead = fs.read(buffer);
                         if (amountRead == -1) {
                             break;
                         }
                         writer.write(buffer, 0, amountRead); 
                     }
             }
*/
/*sh        	byte[] bytes = new byte[1024]; 
            int read=0;
	        while ((read = fs.read(bytes)) > 0) { 
	    	   writer.write(bytes,0,read); 
	    	   bf.append(bytes);
	        }
	        fs.close();
            writer.writeBytes("\r\n" + "--" + boundary + "\r\n");
            bf.append("\r\n" + "--" + boundary + "\r\n");
	        
    */        
	       System.out.println("bbbbbbbbbbbb:"+ bf.toString());
	        writer.flush();
            writer.close();
            
       
            istr = connection.getInputStream();
        
        
        //Fetch Result
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(istr));
        String line;
        
        while ((line = reader.readLine()) != null) 
        	resultsb.append(line);
        } catch (Exception e) {
        	log.error(e.getMessage()); 
        }
        System.out.println("string buffer :" + bf.toString());
        
        return resultsb.toString();
    }

    private void writeFile(String name, String filePath, DataOutputStream out, int partNumber) {
        System.out.println("in write file name is"+ name );
        
        if(out==null)
        	System.out.println("out null" );
        if(name !=null && filePath!=null && out!=null && partNumber>1){
	    	try {
	    		//shout.writeBytes("content-disposition: form-data; name=\"" + name + "\"; filename=\"" + name + "\"\r\n");
	    		//shbf.append("content-disposition: form-data; name=\"" + name + "\"; filename=\"" + name + "\"\r\n");
	    		//shout.writeBytes("content-type: application/octet-stream" + "\r\n\r\n");
	    		//shbf.append("content-type: application/octet-stream" + "\r\n");
	    		//shout.writeBytes("Content-ID: <part" + partNumber + "@intertek.com>" + "\r\n");
	    		//shbf.append("Content-ID: <part" + partNumber + "@intertek.com>" + "\r\n\r\n");
	        	//bf.append("Content-ID: <part2@intertek.com>" + "\r\n\r\n");
	    		connection.addRequestProperty("Content-Disposition","attachment; filename=\"" + name);
       		    connection.addRequestProperty("Content-Type","application/octet-stream");
       		    connection.addRequestProperty("Content-ID","<part" + partNumber + "@intertek.com>");
	    		
       		    ByteArrayOutputStream byteStream = new ByteArrayOutputStream(100000000);

	    		FileInputStream fis = new FileInputStream(filePath+name);
	            while (true) {
	                synchronized (buffer) {
	                    int amountRead = fis.read(buffer);
	                        if (amountRead == -1) {
	                            break;
	                        }
	                        byteStream.write(buffer, 0, amountRead);
	                   // out.write(buffer, 0, amountRead); 
	                    bf.append(buffer);
	                    }
	            }
	            byteStream.writeTo(out);
	            byteStream.flush();
	            byteStream.close();

	            fis.close();
	            //shout.writeBytes("\r\n" + "--" + boundary + "\r\n");
	            //shbf.append("\r\n" + "--" + boundary + "\r\n");
	            
	             } catch (Exception e) {  System.out.println(e.toString());      }
        }
    }

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}