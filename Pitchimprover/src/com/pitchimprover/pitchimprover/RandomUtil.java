package com.pitchimprover.pitchimprover;  

import java.util.Random;
/**
 * ...
 * @author gary poison
 */
public class RandomUtil
{
  public static Random rand;


  public RandomUtil() 
  {}


  public static void init() 
  {
    rand = new Random(); 
  }

  public static int getValue()
  {
    int ret = rand.nextInt();
    if (ret < 0)
      ret = -ret;
    return ret;
  }


  public static double getNormalised()
  {
    return rand.nextDouble();
  }

  public static int getMod(int mod)
  {
    return getValue() % mod;  
  }
}

