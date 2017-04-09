package com.intertek.report;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.util.Iterator;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.intertek.entity.User;
import com.intertek.exception.JasperFillException;
import com.intertek.util.Constants;
import com.intertek.report.JasperFillReport;
import com.intertek.util.MailUtil;
import com.intertek.util.SecurityUtil;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

public class JasperFillReport implements Runnable{
	protected JasperPrint jasperPrint;
	// only one of con and datasource should be used. con is used with old reports, while new reports
	// use datasource
    protected Connection con;
	protected JRDataSource datasource;
	@SuppressWarnings("unchecked")
    protected Map map;
	protected InputStream is;
	protected boolean done=false;
	protected Exception ex=null; 
	
	protected static int maxTry;
	protected static int numOfSleep;
	protected static long sleepAmount;
	protected static String mailFrom;
	protected static String mailTo;
	protected static String mailSubject;

	
	@SuppressWarnings("unchecked")
    public JasperFillReport(InputStream is, Map map, JRDataSource datasource){
        this.is=is;
        this.map=map;
        this.datasource = datasource;
    }
    
    @SuppressWarnings("unchecked")
    public JasperFillReport(InputStream is, Map map, Connection con){
        this.is=is;
        this.map=map;
        this.con = con;
    }
    
	static{
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("jasperFillReport");
        maxTry=Integer.parseInt(pRB.getString("MAX_TRY"));
        numOfSleep=Integer.parseInt(pRB.getString("NUM_OF_SLEEP"));
        sleepAmount=Long.parseLong(pRB.getString("SLEEP_AMOUNT"));
        
        mailFrom=pRB.getString("MAIL_FROM");
        mailTo=pRB.getString("MAIL_TO");
        mailSubject=pRB.getString("MAIL_SUBJECT");
	}
	
	@Override
	public void run() {
		ex=null;
		jasperPrint=null;
		done=false;
		try{
		    if(datasource != null){
		        jasperPrint=JasperFillManager.fillReport(is, map, datasource);
		    }
		    else if(con != null){
                jasperPrint=JasperFillManager.fillReport(is, map, con);
		    }
		}
		catch(JRException e){
			ex=e;
			e.printStackTrace();
			System.out.println("Error in filling report...");
			jasperPrint=null;
		}
		done=true;
	}
	
    @SuppressWarnings("unchecked")
    public static JasperPrint fillReport(InputStream is, Map map, Connection con)throws JasperFillException{
        for(int numTry=0; numTry<maxTry; numTry++){
            //System.out.println("Trying to fill "+(numTry+1)+"/"+MAX_TRY);
            JasperPrint jasperPrint=JasperFillReport.fillReport(numOfSleep, sleepAmount, is, map, con);
            if(jasperPrint!=null){
                return jasperPrint;
            }
        }
        return null;
    }
    
	@SuppressWarnings("unchecked")
    public static JasperPrint fillReport(String reportName, InputStream is, Map map, Connection conn){
	    // get the data source for the given report
	    JRDataSource ds = ReportDataSourceManager.getManager().getReportDataSource(reportName, map, conn);
		// add the connection to the parameters map
	    if(conn != null){
	        map.put("JdbcConnection", conn);
	    }
	    
        for(int numTry=0; numTry<maxTry; numTry++){
        	//System.out.println("Trying to fill "+(numTry+1)+"/"+MAX_TRY);
        	JasperPrint jasperPrint=JasperFillReport.fillReport(numOfSleep, sleepAmount, is, map, ds);
	        if(jasperPrint!=null){
	        	return jasperPrint;
	        }
        }
        return null;
	}
	
	// fillReport() has been refactored to support different constructors, so the main logic
	// has been move into executeFillReport()
    @SuppressWarnings("unchecked")
    protected static JasperPrint fillReport(int numOfSleep, long sleepAmount, InputStream is, Map map, Connection con)throws JasperFillException{
        JasperFillReport jasper=new JasperFillReport(is, map, con);
        return executeFillReport(jasper, numOfSleep, sleepAmount);
    }
    
	@SuppressWarnings("unchecked")
    protected static JasperPrint fillReport(int numOfSleep, long sleepAmount, InputStream is, Map map, JRDataSource rs){
		JasperFillReport jasper=new JasperFillReport(is, map, rs);
		return executeFillReport(jasper, numOfSleep, sleepAmount);
	}
	
	private static JasperPrint executeFillReport(JasperFillReport jasper, int numOfSleep, long sleepAmount){
	    Thread runner=new Thread(jasper);
	    runner.start();
	    
	    int sleepCount=0;
	    while(!jasper.done && sleepCount<numOfSleep){
	    	try{
                //System.out.println("Sleeping ... "+sleepCount);
	    		Thread.sleep(sleepAmount);
	    	}
	    	catch(Exception e){
	    	}
	    	sleepCount++;
	    }
	    
	    if(jasper.jasperPrint==null){
            String reason="Failed to fill Jasper";
            if(!jasper.done){
                reason="Timed Out";
            }
            runner.interrupt();
            jasper.emailError(reason);
            throw new JasperFillException(reason);
	    }
	    return jasper.jasperPrint;	    
	}
	
	@SuppressWarnings("unchecked")
    protected void emailError(String reason){
        StringBuffer buf=new StringBuffer("");
        buf.append("<html><body>");
        buf.append("Reason:"+reason+"<BR>");
        User user=SecurityUtil.getUser();
        if(user!=null){
            buf.append("User:"+user.getLoginName()+"<BR>");
        }
        
        Iterator itr=map.keySet().iterator();
        while(itr.hasNext()){
            String name=itr.next().toString();
            Object value=map.get(name);
            buf.append(name+"="+(value==null?"null":value.toString()));
            buf.append("<BR>");
        }
        
        if(ex!=null){
            ByteArrayOutputStream bf=new ByteArrayOutputStream();
            ex.printStackTrace(new PrintStream(bf));
            buf.append(bf.toString());
        }
        
        buf.append("</body></html>");
        
        try {
            MailUtil.sendMail(mailFrom, mailTo, mailSubject+"--"+Constants.machineName, buf.toString());
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
