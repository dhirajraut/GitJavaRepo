package com.intertek.test;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

public class WebServiceClientTest extends WebServiceGatewaySupport
{
  private static final String UPDATE_BUSINESS_UNIT =
    "<updateEntity xmlns=\"http://www.intertek.com/phoenix/schemas\">"
    + "    <bank>"
    + "      <bankCode>B3333</bankCode>"
    + "      <bankDesc>Bank 3333 description</bankDesc>"
    + "    </bank>"
    + "    <bankAccount>"
    + "      <bankAccountId>"
    + "        <businessUnitName>USA01</businessUnitName>"
    + "        <bankCode>B3333</bankCode>"
    + "        <bankAcctCode>BC33</bankAcctCode>"
    + "      </bankAccountId>"
    + "      <bankAcctDesc>Bank Account 3333 description</bankAcctDesc>"
    + "    </bankAccount>"
    + "    <bankAccountCurr>"
    + "      <bankAccountCurrId>"
    + "        <businessUnitName>USA01</businessUnitName>"
    + "        <bankCode>B3333</bankCode>"
    + "        <bankAcctCode>BC33</bankAcctCode>"
    + "        <currencyCode>USD</currencyCode>"
    + "      </bankAccountCurrId>"
    + "    </bankAccountCurr>"
    + "    <paymentTerm>"
    + "      <paymentTermsCode>PTC01</paymentTermsCode>"
    + "      <paymentTermsDesc>PTC01 description</paymentTermsDesc>"
    + "      <paymentTermsShortDesc>PTC01 S</paymentTermsShortDesc>"
    + "    </paymentTerm>"
    + "    <taxRate>"
    + "      <taxCode>TR001</taxCode>"
    + "      <taxType>F</taxType>"
    + "      <effDate>2007-12-23T10:10:10</effDate>"
    + "      <taxPct>10</taxPct>"
    + "    </taxRate>"
    + "    <country>"
    + "      <countryCode>ABW</countryCode>"
    + "      <name>Aruba</name>"
    + "      <shortName></shortName>"
    + "      <country2></country2>"
    + "      <euMemberId>N</euMemberId>"
    + "      <addr1Lbl></addr1Lbl>"
    + "      <addr1Avail>N</addr1Avail>"
    + "      <addr2Lbl></addr2Lbl>"
    + "      <addr2Avail>N</addr2Avail>"
    + "      <addr3Lbl></addr3Lbl>"
    + "      <addr3Avail>N</addr3Avail>"
    + "      <addr4Lbl></addr4Lbl>"
    + "      <addr4Avail>N</addr4Avail>"
    + "      <otherLabel></otherLabel>"
    + "      <otherAvail>N</otherAvail>"
    + "      <city></city>"
    + "      <cityAvail>N</cityAvail>"
    + "      <county></county>"
    + "      <countyAvail>N</countyAvail>"
    + "      <state></state>"
    + "      <stateAvail>N</stateAvail>"
    + "      <postal></postal>"
    + "      <postalAvail>N</postalAvail>"
    + "      <vatCountry>N</vatCountry>"
    + "      <vatByProvince>N</vatByProvince>"
    + "      <foreignBuyer>FB</foreignBuyer>"
    + "    </country>"
    + "    <countryVAT>"
    + "      <countryVATId>"
    + "        <vatCode>VT1</vatCode>"
    + "        <countryCode>ABW</countryCode>"
    + "        <stateCode>Dummy</stateCode>"
    + "        <effDate>2007-12-23T10:10:10</effDate>"
    + "      </countryVATId>"
    + "      <zeroRatedVATCode>T</zeroRatedVATCode>"
    + "    </countryVAT>"
    + "    <busUnitVatLoc>"
    + "      <busUnitVatLocId>"
    + "        <countryCode>ABW</countryCode>"
    + "        <buName>USA01</buName>"
    + "      </busUnitVatLocId>"
    + "      <defaultLocInd>Y</defaultLocInd>"
    + "    </busUnitVatLoc>"
    + "    <businessUnit>"
    + "      <name>USA01</name>"
    + "      <orgName>Division1</orgName>"
    + "      <address1>222 Black Oak Cir, #123</address1>"
    + "    </businessUnit>"
    + "    <businessUnit>"
    + "      <name>USA09</name>"
    + "      <orgName>Division2</orgName>"
    + "    </businessUnit>"
    + "</updateEntity>";

  public void sendService() throws IOException
  {
    StreamSource source = new StreamSource(new StringReader(UPDATE_BUSINESS_UNIT));
    StreamResult result = new StreamResult(System.out);
    getWebServiceTemplate().sendSourceAndReceiveToResult(
      "http://localhost:9999/phoenix/updateEntityService.ws",
      //"https://phxstg.intertek-cb.com/phoenix/updateEntityService.ws",
      source,
      result
    );
  }

  public static void main(String[] args) throws IOException
  {
    WebServiceClientTest client = new WebServiceClientTest();
    client.sendService();
  }
}
