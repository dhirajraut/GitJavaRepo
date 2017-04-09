package com.intertek.test;

import java.util.Enumeration;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.BasicAttributes;
import javax.naming.directory.SearchControls;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DistinguishedName;
import org.springframework.ldap.core.LdapTemplate;

import com.intertek.locator.ServiceLocator;

public class LdapTest
{
  public static void main(String[] args) throws Exception
  {
    ApplicationContext ctx = new FileSystemXmlApplicationContext(
      new String[]
      {
        "../web/WEB-INF/applicationContext-ldap.xml"
      }
    );

    ServiceLocator.getInstance().setApplicationContext(ctx);

    LdapTemplate ldapTemplate = (LdapTemplate)ServiceLocator.getInstance().getBean("ldapTemplate");
    List results = ldapTemplate.search(
      "DC=cb,DC=winadroot,DC=com", //"OU=ADAM users,O=Oreads,C=US",//"",
      "sAMAccountName=li.miao", //"(objectclass=user)",
      SearchControls.SUBTREE_SCOPE,
      new AttributesMapper()
      {
        public Object mapFromAttributes(Attributes attrs) throws NamingException
        {
            System.out.println("========== attrs = " + attrs);
          Enumeration e = attrs.getAll();
          while(e.hasMoreElements())
          {
            Attribute a = (Attribute)e.nextElement();

            System.out.println("========== a.getID() = " + a.getID());
          }

          return attrs.get("sAMAccountName").get();
        }
      }
    );

    System.out.println("=== results = " + results);

    System.out.println("Done");
    System.exit(0);
  }

  public static void insert(LdapTemplate ldapTemplate)
  {
    Attributes personAttributes = new BasicAttributes();
    BasicAttribute personBasicAttribute = new BasicAttribute("objectclass");
    personBasicAttribute.add("user");
    personAttributes.put(personBasicAttribute);
    personAttributes.put("cn", "dennisshen");
    personAttributes.put("sn", "Shen");
    personAttributes.put("description", "Dennis Shen");

    DistinguishedName newContactDN = new DistinguishedName("OU=ADAM users");
    newContactDN.add("cn", "dennisshen");

    ldapTemplate.bind(newContactDN, null, personAttributes);
  }

  public static void delete(LdapTemplate ldapTemplate)
  {
    DistinguishedName newContactDN = new DistinguishedName("OU=ADAM users");
    newContactDN.add("cn", "dennisshen");

    ldapTemplate.unbind(newContactDN);
  }
/*
  public boolean checkPassword(String login, String password)
  {
    log.debug("LdapServiceDao::checkPassword()");

    // Construction du DN
    DistinguishedName dn = new DistinguishedName("ou=People,dc=univ,dc=fr");
    dn.append(new DistinguishedName(getUserDn(login)));

    // Connexion manuelle
    LdapContextSource ctxSource = new LdapContextSource();
    ctxSource.setUrl(url);
    ctxSource.setUserName(dn.encode());
    ctxSource.setPassword(password);
    ctxSource.setPooled(false);
    try {
      ctxSource.afterPropertiesSet();
      ctxSource.getReadWriteContext();
      return true;
    }
    catch(Exception e) {
      return false;
    }
  }
*/
}
