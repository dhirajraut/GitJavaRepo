package com.lnt.msg;

import java.net.*;
import java.util.Hashtable;
import java.io.*;


public class ConnectToURL
{

public void ConnectToURL()
{
}

public static void main(String args[]) {
try{
	Emailer emailObj = new Emailer();
        URL yahoo = new URL("http://localhost:8080/docs/mq.html");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                                new InputStreamReader(
                                yc.getInputStream()));
        String inputLine;
        StringBuffer str = new StringBuffer();
        String db2;
        String db2n;
        String dw;
        String broker;
        //MailSenderUtil objMailSenderUtil = null;
        Hashtable objHashtable = new Hashtable();
        //StringBuffer str1 = new StringBuffer();

        while ((inputLine = in.readLine()) != null )         	
        	{System.out.println(inputLine);
        str.append(inputLine);
       // str1.append(inputLine);
        	}
        System.out.println("*******"+str);
       db2=str.substring((str.indexOf("CDS.ENT.AGENT.DB2	</td>")+54),(str.indexOf("CDS.ENT.AGENT.DB2	</td>")+55));
       db2n=str.substring((str.indexOf("CDS.ENT.AGENT.DB2N	</td>")+55),(str.indexOf("CDS.ENT.AGENT.DB2N	</td>")+56));
       dw=str.substring((str.indexOf("CDS.ENT.AGENT.DW	</td>")+53),(str.indexOf("CDS.ENT.AGENT.DW	</td>")+54));
      broker=str.substring((str.indexOf("CDS.ENT.BROKER	</td>")+51),(str.indexOf("CDS.ENT.BROKER	</td>")+52));
      System.out.println(db2+db2n+dw+broker);
      if(Integer.parseInt(db2)>0){
    	 
    	 emailObj.sendMail();	
	  
      }
     //  System.out.println("------- "+str.substring((str.indexOf("CDS.ENT.AGENT.DW	</td>")+35),(str.indexOf("CDS.ENT.AGENT.DW </td>")+37)));
        in.close();
}catch(Exception e)
{
System.out.println(e.getMessage());
}

    }
}