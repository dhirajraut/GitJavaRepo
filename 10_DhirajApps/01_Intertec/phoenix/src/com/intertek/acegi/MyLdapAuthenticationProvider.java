package com.intertek.acegi;

import org.acegisecurity.providers.ldap.LdapAuthenticationProvider;
import org.acegisecurity.providers.ldap.LdapAuthenticator;
import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.ldap.LdapUserDetails;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataRetrievalFailureException;

/**
 * An AuthenticationProvider implementation that provides integration with an LDAP server.
 */

public class MyLdapAuthenticationProvider extends LdapAuthenticationProvider
{
  private static final Log logger = LogFactory.getLog(MyLdapAuthenticationProvider.class);

  private MyUserDetailsService userDetailsService;

  /**
   * .ctor
   *
   * Creates an instance with the supplied authenticator and a null authorities populator. In this case, the authorities must be mapped from the user context.
   *
   * @param authenticator - the authenticator strategy.
   */
  public MyLdapAuthenticationProvider(LdapAuthenticator authenticator)
  {
    super(authenticator);
  }

  /**
   * Set the userDetailsService
   *
   * @param userDetailsService - the MyUserDetailsService used to crate the UserDetails.
   **/
  public void setUserDetailsService(MyUserDetailsService userDetailsService)
  {
    this.userDetailsService = userDetailsService;
  }

  /**
   * Creates the final UserDetails object that will be returned by the provider once the user has been authenticated.
   *
   * @param ldapUser - The intermediate LdapUserDetails instance returned by the authenticator.
   * @param username - the username submitted to the provider
   * @param password - the password submitted to the provider
   * @return The UserDetails for the successfully authenticated user.
   **/
  protected UserDetails createUserDetails(
    LdapUserDetails ldapUser,
    String username,
    String password
  )
  {
    try
    {
      return userDetailsService.loadUserByUsername(
        ldapUser.getUsername(),
        ldapUser.getPassword()
      );
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      throw new DataRetrievalFailureException(t.getMessage(), t);
    }
  }
}

