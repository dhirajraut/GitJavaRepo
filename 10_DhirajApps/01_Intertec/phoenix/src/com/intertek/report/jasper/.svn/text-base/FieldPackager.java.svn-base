package com.intertek.report.jasper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.StringTokenizer;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.intertek.util.CommonUtil;

public class FieldPackager {

  private static String C_DEFAULT_PREFIX = "default";
  private static String C_PROPERTY_FILENAME="fp.properties";
  private static int C_LINES_TO_CHECK = 10;
  //private static String C_REGEX = "[A-Za-z0-9._%+-]*"; //not used at the moment.

  /*
   * Remove null elements from a collection, return a JRDataSource.
   */
  public static JRDataSource createDataSet(String... elements){
    ArrayList<StringShell> returnedFields = new ArrayList<StringShell>();
    for (String curField: elements){
      if ((curField!=null)&&(curField.replace(" ", "").length()>0)){
          returnedFields.add(new StringShell(curField));
      }
    }
    return new JRBeanCollectionDataSource(returnedFields);
  }

  public static JRDataSource createPairDataSet(StringPair... elements){
    ArrayList<StringPair> returnedFields = new ArrayList<StringPair>();
    for (StringPair curPair: elements){

      if ((curPair.getLeftVal()!=null)&&(curPair.getLeftVal().replace(" ", "").length()>0)&&
          (curPair.getRightVal()!=null)&&(curPair.getRightVal().replace(" ", "").length()>0)){
          returnedFields.add(curPair);
      }
    }
    return new JRBeanCollectionDataSource(returnedFields);
  }

  public static JRDataSource createFormattedDataSet(String locale, String... keyValuePairs){
    ArrayList<StringShell> returnedFields = new ArrayList<StringShell>();
    HashMap <String,String> keyVals = new HashMap<String,String>();

    //get locale.
    String prefix=locale;

    //read in the properties file each time this method is called.
    Properties formatProps = new Properties();
    try{
      formatProps.load(CommonUtil.getClassPathResource(C_PROPERTY_FILENAME));
    } catch (FileNotFoundException fnfe){
      fnfe.printStackTrace();
    } catch (IOException ioe){
      ioe.printStackTrace();
    }

    //parse tokens
    for (String pair : keyValuePairs){
      int eVal = pair.indexOf('=');
      String key = pair.substring(0,eVal);
      String value = pair.substring(eVal+1, pair.length());
      //System.out.println(key + "---" + value); (test)

      //add key value pair to hashmap for lookup.
      keyVals.put(key,value);
    }


    //find entries in properties file matching locale, use default if none found.
    if (formatProps.containsKey(prefix + ".1")){ //found entry for the first line.
      populatePrefix(prefix, formatProps, keyVals, returnedFields);
    } else { //use default.
      populatePrefix(C_DEFAULT_PREFIX, formatProps, keyVals, returnedFields);
    }

    return new JRBeanCollectionDataSource(returnedFields);//return fields.
  }

  private static void populatePrefix(String prefix, Properties formatProps, HashMap<String,String> keyVals, ArrayList<StringShell> returnedFields){
    //run down entries in the properties file for lines 1-10 and evaluate into string,
    //then add to returned fields list.
    for (int t=1; t<=C_LINES_TO_CHECK; t++){
      String prefixCheck = prefix + "." + String.valueOf(t);
      if (formatProps.containsKey(prefixCheck)){ //if prefix doesn't exist for a certain line, skip it.
        if(t==2 && keyVals!=null && !keyVals.isEmpty() && keyVals.containsKey("address4")  && keyVals.get("address4") != null && !keyVals.get("address4").equals("null")  && !keyVals.get("address4").isEmpty() &&   keyVals.get("address4").trim().length()>0){

        	String address4 = "ATTN TO: ".concat(keyVals.get("address4"));
        	keyVals.put("address4", address4);
        }
    	String textPattern = formatProps.getProperty(prefixCheck);
        String resolvedText = resolveTextLiteral(textPattern, keyVals);
        if (resolvedText.trim().length()>0){ //only add if something useful came back from the resolved text.
          returnedFields.add(new StringShell(resolvedText));
        }
      }
    }
  }

  private static String resolveTextLiteral(String textPattern, HashMap<String,String> tokenValues){
    String textLiteral = "";
    StringTokenizer st = new StringTokenizer(textPattern, "+");
    while (st.hasMoreTokens()){
      String nextToken = st.nextToken();
      boolean foundHashMatch = false;
      //check all available tokens for replacement.
      Iterator tokIt = tokenValues.keySet().iterator();
      while (tokIt.hasNext()){
        String nextKey = tokIt.next().toString();
        if (nextToken.trim().equalsIgnoreCase(nextKey)){ //token match found.
          foundHashMatch = true;
          String tokValue = tokenValues.get(nextKey);
          if (!(tokValue.equalsIgnoreCase("null"))){
            textLiteral += tokenValues.get(nextKey);
          }
        }
      }
      if (foundHashMatch == false){//nothing was found in the token hash, use literal.
        textLiteral += nextToken;
      }
    }
    return textLiteral;
  }


  /*
  //test method.
  public static JRDataSource getTestDataSet(){
    ArrayList testData = new ArrayList();
    testData.add(new StringShell((String)"first"));
    testData.add(new StringShell((String)"second"));
    testData.add(new StringShell((String)"third"));
    return new JRBeanCollectionDataSource(testData);
  }
  */


  //test method.
  public static void main (String [] args){
    /*
    Object jrData = FieldPackager.createFormattedDataSet("jpn", "name=__name__",
        "contact=__contact__","address1=__address1__",
        "address2=__address2__","address3=__address3__","country=__country__");

    */
    Object jrData = FieldPackager.createDataSet(" ");
    System.out.println(" ".trim().length());
    System.out.println(" ".length());
    //System.out.println("abdf".replace(" ", "").matches(C_REGEX));
    System.out.println(jrData.toString());
  }


}
