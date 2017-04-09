package com.intertek.acegi;

import java.util.List;
import java.util.Set;

import org.acegisecurity.GrantedAuthority;

import com.intertek.util.StringEncrypter;

/**
 * It is used by Acegi Security Framework to identify a user.
 * It contains a phoenix user entity, the menu tree of this user, and the permission set of this user.
 **/

public class AcegiUser extends org.acegisecurity.userdetails.User
{
  private com.intertek.entity.User user;
  private List menuTree;
  private Set permNameSet;

  /**
   * .ctor
   *
   * @param username - the username.
   * @param password - the password of the user.
   * @param enabled - set to true if the user is enabled.
   * @param authorities - the authorities that should be granted to the caller if they presented the correct username and password and the user is enabled.
   */
  public AcegiUser(
    String username,
    String password,
    boolean enabled,
    GrantedAuthority[] authorities
  ) throws IllegalArgumentException
  {
    super(username, password, enabled, authorities);
  }

  /**
   * .ctor
   *
   * @param username - the username.
   * @param password - the password of the user.
   * @param enabled - set to true if the user is enabled.
   * @param accountNonExpired - set to true if the account has not expired.
   * @param credentialsNonExpired - set to true if the credentials have not expired.
   * @param authorities - the authorities that should be granted to the caller if they presented the correct username and password and the user is enabled.
   */
  public AcegiUser(
    String username,
    String password,
    boolean enabled,
    boolean accountNonExpired,
    boolean credentialsNonExpired,
    GrantedAuthority[] authorities
  ) throws IllegalArgumentException
  {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, authorities);
  }

  /**
   * .ctor
   *
   * @param username - the username.
   * @param password - the password of the user.
   * @param enabled - set to true if the user is enabled.
   * @param accountNonExpired - set to true if the account has not expired.
   * @param credentialsNonExpired - set to true if the credentials have not expired.
   * @param accountNonLocked - set to true if the account is not locked.
   * @param authorities - the authorities that should be granted to the caller if they presented the correct username and password and the user is enabled.
   */
  public AcegiUser(
    String username,
    String password,
    boolean enabled,
    boolean accountNonExpired,
    boolean credentialsNonExpired,
    boolean accountNonLocked,
    GrantedAuthority[] authorities
  ) throws IllegalArgumentException
  {
    super(
      username,
      password,
      enabled,
      accountNonExpired,
      credentialsNonExpired,
      accountNonLocked,
      authorities
    );
  }

  /**
   * .ctor
   *
   * @param username - the username.
   * @param password - the password of the user.
   * @param enabled - set to true if the user is enabled.
   * @param accountNonExpired - set to true if the account has not expired.
   * @param credentialsNonExpired - set to true if the credentials have not expired.
   * @param accountNonLocked - set to true if the account is not locked.
   * @param authorities - the authorities that should be granted to the caller if they presented the correct username and password and the user is enabled.
   * @param user - the phoenix user entity.
   * @param menuTree - the list of menu trees for this user.
   * @param permNameSet - the permssion set of this user.
   */
  public AcegiUser(
    String username,
    String password,
    boolean enabled,
    boolean accountNonExpired,
    boolean credentialsNonExpired,
    boolean accountNonLocked,
    GrantedAuthority[] authorities,
    com.intertek.entity.User user,
    List menuTree,
    Set permNameSet
  ) throws IllegalArgumentException
  {
    super(
      username,
      password,
      enabled,
      accountNonExpired,
      credentialsNonExpired,
      accountNonLocked,
      authorities
    );

    this.user = user;
    this.menuTree = menuTree;
    this.permNameSet = permNameSet;
  }

  /**
   * Get the phoenix user.
   *
   * @return - the phoenix user.
   */
  public com.intertek.entity.User getUser()
  {
    return user;
  }

  /**
   * Get the list of menu trees of this user.
   *
   * @return - the list of menu trees of this user.
   */
  public List getMenuTree()
  {
    return menuTree;
  }

  /**
   * Get the permission name set of this user.
   *
   * @return - the permission name set of this user.
   */
  public Set getPermNameSet()
  {
    return permNameSet;
  }

    public String getLoginToken() {
        String token=StringEncrypter.doEncrypt(this.getUsername()+";@;"+this.getPassword());
        return token;
    }
}
