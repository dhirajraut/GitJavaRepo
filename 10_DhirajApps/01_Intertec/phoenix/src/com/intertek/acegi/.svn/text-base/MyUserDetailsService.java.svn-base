package com.intertek.acegi;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.springframework.dao.DataAccessException;

/**
 * The interface used to load the UserDetails.
 *
 **/

public interface MyUserDetailsService extends UserDetailsService
{
  /**
   * Locates the user based on the username.
   *
   * @param username - the username
   * @param password - the password
   * @throws UsernameNotFoundException - if the user could not be found or the user has no GrantedAuthority
   * @throws org.springframework.dao.DataAccessException - if user could not be found for a repository-specific reason
   **/
  UserDetails loadUserByUsername(
    String username,
    String password
  ) throws UsernameNotFoundException, DataAccessException;
}
