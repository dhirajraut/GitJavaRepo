package com.intertek.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;

public class KeyGenerator
{
  private DataSource dataSource;

  public KeyGenerator()
  {
  }

  public void setDataSource(DataSource dataSource)
  {
    this.dataSource = dataSource;
  }

  public long getSequenceValue(String seqName)
  {
    Connection con = null;
    Statement st = null;
    ResultSet rs = null;
    long seq = 0;

    try
    {
      con = dataSource.getConnection();

      String sql = "select " + seqName + ".nextval from dual";

      st = con.createStatement();
      rs = st.executeQuery(sql);
      if(rs.next())
      {
        seq = rs.getLong(1);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      if(rs != null)
      {
        try { rs.close(); } catch(Exception e) {}
      }

      if(st != null)
      {
        try { st.close(); } catch(Exception e) {}
      }

      if(con != null)
      {
        try { con.close(); } catch (Exception e){}
      }
    }

    return seq;
  }
}

