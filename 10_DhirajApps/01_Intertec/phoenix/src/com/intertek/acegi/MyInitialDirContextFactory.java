package com.intertek.acegi;

import java.util.Hashtable;
import java.util.List;

import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

import org.acegisecurity.BadCredentialsException;
import org.acegisecurity.ldap.DefaultInitialDirContextFactory;
import org.acegisecurity.ldap.LdapDataAccessException;

public class MyInitialDirContextFactory extends DefaultInitialDirContextFactory {
    protected List urls;

    public void setUrls(List urls) {
        this.urls = urls;
    }

    public List getUrls() {
        return urls;
    }

    public MyInitialDirContextFactory(String providerUrl) {
        super(providerUrl);
    }

    public DirContext newInitialDirContext(String username, String password) {
        Exception ce = null;
        Exception nx = null;
        for (int i = 0; i < urls.size(); i++) {
            Hashtable env = getEnvironment();
            env.put(Context.PROVIDER_URL, urls.get(i));

            // env.remove("com.sun.jndi.ldap.connect.pool");

            // env.put("com.sun.jndi.ldap.connect.pool.maxsize", "1");
            // env.put("com.sun.jndi.ldap.connect.pool.prefsize", "0");
            // env.put("com.sun.jndi.ldap.connect.pool.timeout", "100");

            // Don't pool connections for individual users
            // if (!username.equals(managerDn)) {
            //env.remove(org.acegisecurity.ldap.DefaultInitialDirContextFactory.
            // CONNECTION_POOL_KEY);
            // }

            env.put(Context.SECURITY_PRINCIPAL, username);
            env.put(Context.SECURITY_CREDENTIALS, password);
            try {
                DirContext dr = new InitialDirContext(env);
                System.out.println("Successfully logged in using " + urls.get(i));
                return dr;
            }
            catch (CommunicationException ce1) {
                ce = ce1;
            }
            catch (javax.naming.AuthenticationException ae) {
                throw new BadCredentialsException(messages.getMessage("DefaultIntitalDirContextFactory.badCredentials", "Bad credentials"), ae);
            }
            catch (NamingException nx1) {
                nx = nx1;
            }
        }

        if (ce != null) {
            throw new LdapDataAccessException(messages.getMessage("DefaultIntitalDirContextFactory.communicationFailure", "Unable to connect to LDAP server"),
                                              ce);
        }
        throw new LdapDataAccessException(messages.getMessage("DefaultIntitalDirContextFactory.unexpectedException",
                                                              "Failed to obtain InitialDirContext due to unexpected exception"), nx);
    }
}
