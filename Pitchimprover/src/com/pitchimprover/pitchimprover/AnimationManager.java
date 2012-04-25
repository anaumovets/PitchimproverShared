package com.pitchimprover.pitchimprover;

import android.graphics.BitmapFactory;
import android.content.Context;

/*
 * AnimationManager is a factory class for bitmaps and animations
 */
class AnimationManager
{
  public static Animated CreateKeysActiveAnim(Context context)
  {
    Animated ani = new Animated(BitmapFactory.decodeResource(context.getResources(), R.drawable.keysgreen));
    return ani;
  }
  /////////////////////////////////////////////////////////////////////
  public static Animated CreateKeysActiveColoredAnim(Context context)
  {
    Animated ani = new Animated(BitmapFactory.decodeResource(context.getResources(), R.drawable.keysactive));
    return ani;
  }
  /////////////////////////////////////////////////////////////////////
  public static Animated CreateKeysDisabledAnim(Context context)
  {
    Animated ani = new Animated(BitmapFactory.decodeResource(context.getResources(), R.drawable.keysdisabled));
    return ani;
  }

  /////////////////////////////////////////////////////////////////////    
  public static Animated CreateKeysPressedAnim(Context context)
  {
    Animated ani = new Animated(BitmapFactory.decodeResource(context.getResources(), R.drawable.keyspressed));
    return ani;
  }

  /////////////////////////////////////////////////////////////////////    
  public static Animated CreateKeysPressedActiveAnim(Context context)
  {
    Animated ani = new Animated(BitmapFactory.decodeResource(context.getResources(), R.drawable.keysgreenpressed));
    return ani;
  }

  /////////////////////////////////////////////////////////////////////    
  public static Animated CreateKeysPressedActiveColoredAnim(Context context)
  {
    Animated ani = new Animated(BitmapFactory.decodeResource(context.getResources(), R.drawable.keyspressedactive));
    return ani;
  }

}

