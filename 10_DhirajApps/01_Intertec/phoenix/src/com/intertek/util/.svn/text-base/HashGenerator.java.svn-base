package com.intertek.util;

import java.security.MessageDigest;

public class HashGenerator
{
  private String algorithm;

  public HashGenerator()
  {
    algorithm = "SHA1";
  }

  public void setAlgorithm(String algorithm)
  {
    this.algorithm = algorithm;
  }

  public String getAlgorithm()
  {
    return algorithm;
  }

  public String generateHash(String input, String salt)
  {
    if(input == null) return null;

    MessageDigest md = null;
    try
    {
      md = MessageDigest.getInstance(algorithm);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(md != null)
    {
      if(salt != null) input = input + salt;
      md.update(input.getBytes());

      byte[] mdbytes = md.digest();
      return StringUtil.toHexString(mdbytes);
    }
    else
    {
      return input;
    }
  }

  public String generateSalt()
  {
    //java.util.Random random = new java.util.Random();
    //return random.nextLong() + "";

    return null;
  }
}
