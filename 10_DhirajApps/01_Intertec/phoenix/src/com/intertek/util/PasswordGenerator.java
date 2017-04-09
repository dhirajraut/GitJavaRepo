package com.intertek.util;

import java.util.Random;

public class PasswordGenerator
{
  private static String letters[] = new String[]
  {
    "a","b","c","d","e","f","g","h","i","j","k","l","m",
    "n","o","p","q","r","s","t","u","v","w","x","y","z",
    "A","B","C","D","E","F","G","H","I","J","K","L","M",
    "N","O","P","Q","R","S","T","U","V","X","Z","W","Y",
    "@","!","#","$","%","&",
    "1","2","3","4","5","6","7","8","9"
  };

  public static String generate(int numOfLetters)
  {
    if(numOfLetters < 6) numOfLetters = 6;
    if(numOfLetters > 10) numOfLetters = 10;

    Random random = new Random();

    String results = "";
    for(int i=0; i<numOfLetters; i++)
    {
      results += letters[random.nextInt(letters.length)];
    }

    return results;
  }
}
