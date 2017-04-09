package com.intertek.test;

import java.io.*;

public class LogTest
{
  public static void main(String[] args) throws Exception
  {
    try
    {
      String filename = "C:\\back\\stg\\072408\\phoenixlog4j0724.log";

      String outFileName = filename + ".csv";
      PrintWriter pw = new PrintWriter(new FileWriter(outFileName));

      pw.println("Time, Title, MaxMemory, TotalMemory, FreeMemory, MemoryUsed, TimeUsed");

      LogPair logPair = null;

      BufferedReader br = new BufferedReader(new FileReader(filename));
      String line = br.readLine();
      while(line != null)
      {
        int index = line.lastIndexOf(" -- ");
        if(index > 0)
        {
          String subLine = line.substring(index + 4);
          if(subLine.indexOf("max memory") > 0)
          {
            logPair = new LogPair();
            logPair.maxMemoryLine = subLine;
          }
          else if(subLine.indexOf("total memory") > 0)
          {
            if(logPair != null)
            {
              logPair.totalMemoryLine = subLine;
            }
          }
          else if(subLine.indexOf("timeUsed") > 0)
          {
            if(logPair != null)
            {
              logPair.timeUsedLine = subLine;
            }
          }
          else if(subLine.indexOf("memoryUsed") > 0)
          {
            if(logPair != null)
            {
              logPair.memoryUsedLine = subLine;
            }
          }
          else if(subLine.indexOf("freeMemory") > 0)
          {
            if(logPair != null)
            {
              logPair.freeMemoryLine = subLine;

              logPair.analyze();
              logPair.output(pw);
            }
          }

          index = line.indexOf(",");
          if(index > 0)
          {
            subLine = line.substring(1, index);

            if(logPair != null)
            {
              logPair.timeStr = subLine;
            }
          }
        }

        line = br.readLine();
      }

      pw.close();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }
}

class LogPair
{
  String maxMemoryLine;
  String totalMemoryLine;
  String timeUsedLine;
  String memoryUsedLine;
  String freeMemoryLine;

  String timeStr;
  String title;

  String maxMemory;
  String totalMemory;
  String timeUsed;
  String memoryUsed;
  String freeMemory;

  void analyze()
  {
    if(maxMemoryLine != null)
    {
      String[] subs = maxMemoryLine.split(" ");
      if(subs != null)
      {
        for(int i=0; i<subs.length; i++)
        {
          if(i == 0) title = subs[0];
          else if(i == 4)
          {
            maxMemory = subs[4];
          }
        }
      }
    }

    if(totalMemoryLine != null)
    {
      String[] subs = totalMemoryLine.split(" ");
      if(subs != null)
      {
        for(int i=0; i<subs.length; i++)
        {
          if(i == 0) title = subs[0];
          else if(i == 4)
          {
            totalMemory = subs[4];
          }
        }
      }
    }

    if(timeUsedLine != null)
    {
      String[] subs = timeUsedLine.split(" ");
      if(subs != null)
      {
        for(int i=0; i<subs.length; i++)
        {
          if(i == 0) title = subs[0];
          else if(i == 3)
          {
            timeUsed = subs[3];
          }
        }
      }
    }

    if(memoryUsedLine != null)
    {
      String[] subs = memoryUsedLine.split(" ");
      if(subs != null)
      {
        for(int i=0; i<subs.length; i++)
        {
          if(i == 0) title = subs[0];
          else if(i == 3)
          {
            memoryUsed = subs[3];
          }
        }
      }
    }

    if(freeMemoryLine != null)
    {
      String[] subs = freeMemoryLine.split(" ");
      if(subs != null)
      {
        for(int i=0; i<subs.length; i++)
        {
          if(i == 0) title = subs[0];
          else if(i == 3)
          {
            freeMemory = subs[3];
          }
        }
      }
    }
  }

  void output(PrintWriter pw) throws Exception
  {
    pw.println(timeStr + ", "
      + title + ", "
      + maxMemory + ", "
      + totalMemory + ", "
      + freeMemory + ", "
      + memoryUsed + ", "
      + timeUsed
    );
  }
}


