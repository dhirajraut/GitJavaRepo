package com.intertek.tool.schema;

import java.io.*;
import java.sql.*;
import javax.sql.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class SchemaCreator
{
  public static void main(String[] args) throws Exception
  {
    if(args.length != 1)
    {
      System.out.println("Usage: java SchemaCreator schemafilename");
      System.exit(1);
    }

    String fileName = args[0];

    ApplicationContext ctx = new FileSystemXmlApplicationContext(
      new String[]
      {
        "../web/WEB-INF/applicationContext.xml",
        "../web/WEB-INF/applicationContext-Loader.xml"
      }
    );

    DataSource dataSource = (DataSource)ctx.getBean("entityDataSource");

    long start = System.currentTimeMillis();
    loadFile(dataSource, fileName);
    long end = System.currentTimeMillis();

    System.out.println("Total time used (minutes): " + (end - start)/60000.0);

    System.out.println("Done");
    System.exit(0);
  }

  private static void loadFile(DataSource dataSource, String fileName) throws Exception
  {
    BufferedReader br = new BufferedReader(new FileReader(fileName));
    Connection con = dataSource.getConnection();

    StringBuffer sql = new StringBuffer();
    String line = br.readLine();
    //System.out.println("line = " + line);
    while(line != null)
    {
      sql.append(line).append(" ");
      if(line.indexOf(";") >= 0)
      {
        System.out.println("Got one Sql = " + sql.toString());
        String myStr = sql.toString();
        String cleanStr = myStr.replace(";", "");

        executeSql(con, cleanStr);

        sql = new StringBuffer();
      }

      line = br.readLine();
    }

    con.close();
  }

  private static  void executeSql(Connection con, String sql) throws Exception
  {
    Statement st = null;
    try
    {
      st = con.createStatement();
      st.executeUpdate(sql);
      st.close();
    }
    catch(Exception e)
    {
      System.out.println(e);
    }
    finally
    {
      if(st != null)
      {
        try { st.close(); } catch(Exception t) {}
      }
    }
  }

}
