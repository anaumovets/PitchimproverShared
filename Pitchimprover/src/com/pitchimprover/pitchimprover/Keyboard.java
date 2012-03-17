
package com.pitchimprover.pitchimprover;



import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.app.Activity;
import android.content.Context;


public class Keyboard
{
  public int play_x = 0;

  private MButton playBtn;


  private Animated keysdisabled_img;
  private Animated keysactive_img;
  private Animated keyspressed_img;
  private Animated keyspressedactive_img;

  private Context m_context;


  public Keyboard(Context context, Activity i_activity, boolean colored)
  {
    keysactive_img = colored ? AnimationManager.CreateKeysActiveColoredAnim(context) : AnimationManager.CreateKeysActiveAnim(context);
    keysdisabled_img = AnimationManager.CreateKeysDisabledAnim(context);
    keyspressed_img = AnimationManager.CreateKeysPressedAnim(context);
    keyspressedactive_img = colored ? AnimationManager.CreateKeysPressedActiveColoredAnim(context) : AnimationManager.CreateKeysPressedActiveAnim(context);

    m_context = context;
    Display display = ((WindowManager)m_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int full_w = display.getWidth();
    int full_h = display.getHeight();

    Rect rect = new Rect();
    Window window = i_activity.getWindow();
    window.getDecorView().getWindowVisibleDisplayFrame(rect);
    int content_top = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
    int toolbar_delta = content_top;

    playBtn = new MButton(0, full_h - full_h/6 - toolbar_delta, full_w/5, full_h/6, "Play", m_context);
  }

  public boolean playHitTest(int x,int y)
  {
    return (playBtn.isEnabled() && playBtn.hitTestPoint(x, y));
  }

  public int getKeysH()
  {
    Display display = ((WindowManager)m_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    return (int)(Constants.phiOrder(2)*(double)display.getHeight());
  }

  public int keyHitTest(int kbdx, int x, int y)
  {
    x += kbdx;
    int ret = -1;

    Display display = ((WindowManager)m_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int sz = getKeySize(display.getHeight());

    for(int t = 0; t < Constants.num_tones_in_octave*Constants.num_octaves; ++t)
    {
      int keyx = getKeyX(t);
      int keyy = getKeysH();

      if(isBlack(t))
        keyy -= sz;

      if((keyx < x) && (keyx+sz > x) && (y > keyy) && (y < keyy + sz))
        return t;
    }

    return ret;
  }

  public void drawPlay(Canvas canvas)
  {
    playBtn.setComplexText("Play");
    playBtn.draw(canvas);
  }

  public void drawRepeat(Canvas canvas)
  {
    playBtn.setTextSize(playBtn.getHeight()/3);
    playBtn.setComplexText("Repeat");
    playBtn.draw(canvas);
  } 

  /*
-----------------------------------------------------------------------------------------------------------------------------
Drawing routines
-----------------------------------------------------------------------------------------------------------------------------
   */

  //////////////////////////////////////////////////////////////////////////////////////
  public int getKeySize(int screen_h)
  {
    Display display = ((WindowManager)m_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    return display.getWidth()/8;//(int)((Constants.phiOrder(2)/2.)*(double)display.getHeight())+5;
  }


  private Animated keysAnimated(int key_state)
  {
    if (key_state == Constants.key_state_active)
      return keysactive_img;

    if (key_state == Constants.key_state_disabled)
      return keysdisabled_img;

    if (key_state == Constants.key_state_active_pressed)
      return keyspressedactive_img;

    return keyspressed_img;

  }

  
  public int getKeyX(int key)
  {
    Display display = ((WindowManager)m_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int tone_index = 0;

    for(int t = 0; t < key; ++t)
      if(!isBlack(t))
        tone_index++;

    int keyx = (int)getKeySize(display.getHeight())*tone_index + 1; 

    if(isBlack(key))
      keyx -= getKeySize(display.getHeight())/2;

    return keyx; 
  }

  public boolean isBlack(int key)
  {
    if (key % Constants.num_tones_in_octave == Constants.tone_c_sharp || key % Constants.num_tones_in_octave == Constants.tone_e_flat || 
        key % Constants.num_tones_in_octave == Constants.tone_f_sharp || key % Constants.num_tones_in_octave == Constants.tone_a_flat || 
        key % Constants.num_tones_in_octave == Constants.tone_b_flat)
      return true;

    return false;
  }


  public void drawKey(int x_keyboard,int y,int key,Canvas canvas, int tone_state)
  {
    Display display = ((WindowManager)m_context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int sz = getKeySize(canvas.getHeight())-2;
    Paint paint = new Paint();
    paint.setColor(Color.WHITE);

    Animated keys_img = keysAnimated(tone_state);


    int tone_in_oct = key % Constants.num_tones_in_octave;

    int btn_w = keys_img.width()/7;//keys_img.width()/4;
    int btn_h = isBlack(key) ? (keys_img.height()/3) : 2*(keys_img.height()/3);//(keys_img.height()/3);


    int ind = 0;
    for(int i = 0; i < tone_in_oct; ++i)
      if(isBlack(key) == isBlack(i))
        ++ind;

    int xx = btn_w*ind;
    int yy = isBlack(key) ? ((keys_img.height()*2)/3) : 0;// - getKeySize(display.getHeight());

    //if(isBlack(key))
    y -= getKeySize(display.getHeight());

    int keyx = getKeyX(key) - x_keyboard;
    int ww = sz;
    int hh = isBlack(key) ? sz : 2*sz;

    keys_img.Draw(canvas, keyx, y, xx, yy, btn_w, btn_h, ww, hh);
  }

}




















