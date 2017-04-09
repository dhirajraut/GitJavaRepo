<?xml version="1.0" encoding="utf-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
           xmlns:phoenix="http://www.intertek.com/phoenix/schemas"
           elementFormDefault="qualified"
           targetNamespace="http://www.intertek.com/phoenix/schemas">
  <#assign classbody>
  <xs:element name="updateEntity">
    <xs:complexType>
      <xs:all>
        <xs:element maxOccurs="unbounded" name="paymentTerm" type="PaymentTerm" />
        <xs:element maxOccurs="unbounded" name="bank" type="Bank" />
        <xs:element maxOccurs="unbounded" name="bankAccount" type="BankAccount" />
        <xs:element maxOccurs="unbounded" name="bankAccountCurr" type="BankAccountCurr" />
        <xs:element maxOccurs="unbounded" name="businessUnit" type="BusinessUnit" />
        <xs:element maxOccurs="unbounded" name="busUnitVatLoc" type="BusUnitVatLoc" />
        <xs:element maxOccurs="unbounded" name="country" type="Country" />
        <xs:element maxOccurs="unbounded" name="countryVAT" type="CountryVAT" />
        <xs:element maxOccurs="unbounded" name="taxRate" type="TaxRate" />
        <xs:element maxOccurs="unbounded" name="taxCode" type="TaxCode" />

        <xs:element maxOccurs="unbounded" name="openPeriod" type="OpenPeriod" />
        <xs:element maxOccurs="unbounded" name="glAccount" type="GLAccount" />
        <xs:element maxOccurs="unbounded" name="glDepartment" type="GLDepartment" />
        <xs:element maxOccurs="unbounded" name="glProductGroup" type="GLProductGroup" />
      </xs:all>
    </xs:complexType>
  </xs:element>

  <xs:element name="result" type="Result" />

  <xs:complexType name="Result">
    <xs:all>
      <xs:element minOccurs="1" maxOccurs="1" name="status" type="xs:string" />
      <xs:element maxOccurs="unbounded" name="message" type="Message" />
    </xs:all>
  </xs:complexType>

  <xs:complexType name="Message">
    <xs:all>
      <xs:element minOccurs="1" maxOccurs="1" name="value" type="xs:string" />
      <xs:element maxOccurs="unbounded" name="param" type="xs:string" />
    </xs:all>
  </xs:complexType>

  <#foreach entity in c2j.getPOJOIterator(cfg.getClassMappings())>
  
  <xs:complexType name="${entity.declarationName}">
    <xs:all>
    <#foreach field in entity.allPropertiesIterator>
    
    <#if c2j.isCollection(field)>
      <xs:element maxOccurs="unbounded" name="${c2j.lowerFirstLetter(c2j.getUnqualifiedName(c2j.getJavaTypeNameInCollection(field, true)))}" type="${c2j.getUnqualifiedName(c2j.getJavaTypeNameInCollection(field, true))}" />
    <#else>
      <xs:element name="${field.name}" type="${c2j.getXsdType(c2j.getUnqualifiedName(c2j.getJavaTypeName(field, true)))}"/>
    </#if>
      
    </#foreach>
    </xs:all>
  </xs:complexType>
    
  </#foreach>

  <#foreach entity in c2j.getPOJOIterator(cfg.getClassMappings())>
  
    <#foreach field in entity.allPropertiesIterator>
    
      <#if field.composite>
        <xs:complexType name="${field.name}">
          <xs:all>
          <#foreach field1 in c2j.getPOJOClass(field.value).allPropertiesIterator>

          <#if c2j.isCollection(field1)>
            <xs:element maxOccurs="unbounded" name="${c2j.lowerFirstLetter(c2j.getUnqualifiedName(c2j.getJavaTypeNameInCollection(field1, true)))}" type="${c2j.getUnqualifiedName(c2j.getJavaTypeNameInCollection(field1, true))}" />
          <#else>
            <xs:element name="${field1.name}" type="${c2j.getXsdType(c2j.getUnqualifiedName(c2j.getJavaTypeName(field1, true)))}"/>
          </#if>

          </#foreach>

          </xs:all>
        </xs:complexType>
      </#if>
      
    </#foreach>
    
  </#foreach>

</#assign>

${classbody}

</xs:schema>
