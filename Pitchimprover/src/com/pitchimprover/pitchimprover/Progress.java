package com.pitchimprover.pitchimprover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Display;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * display progress on each level
 * probably should be replaced with a progress bar
 * @author gary poison
 */
public class Progress 
{
  //public int level_pos = 90;
  //public int level_width = 30;

  public int num_levels;

  public int level;

  private MButton incLevelBtn;
  private MButton decLevelBtn;

  
  private TextHelper text_level;
  private TextHelper text_level_num;
  private TextHelper text_correct;

  //private Canvas canvas;
  private IExcercise excercise;

  private Context context;

  private boolean extended;

  public Progress(int i_num_levels, Context i_context, IExcercise i_excercise, boolean i_extended)
  {
    //canvas = i_canvas;
    extended = i_extended;
    excercise = i_excercise;
    context = i_context;
    level = 0;

    num_levels = i_num_levels;
    int btn_y = 0;
    int btn_sz = 40;

    decLevelBtn = new MButton(0, btn_y, btn_sz, btn_sz, "<", i_context);
    incLevelBtn = new MButton(0, btn_y, btn_sz, btn_sz, ">", i_context);
    text_level = new TextHelper(0,0,200,30, "Level", 15, i_context, Gravity.CENTER);//_VERTICAL | Gravity.LEFT);
    text_level_num = new TextHelper(0, 0, 10, 30, Integer.toString(level + 1), 15, context, Gravity.CENTER);
    
    
    
    Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int full_w = display.getWidth();
    int full_h = display.getHeight();

    if(!extended)
      text_correct = new TextHelper(0, 0, Constants.scr_w, 30, "sfhgsfhg", 15, context, Gravity.LEFT | Gravity.CENTER_VERTICAL);
    else
      text_correct = new TextHelper(0, 0, Constants.scr_w, 30, "sfhgsfhg", 15, context, Gravity.RIGHT | Gravity.CENTER_VERTICAL);

    updateBtnEnabling();
  }

  public void hideLevel()
  {
    text_level = null;
  }

  public int getFontSize(int screen_h)
  {
    return getButtonSize(screen_h)/2;  
  }

  private int getButtonSize(int screen_h)
  {
    return (int)(Constants.phiOrder(4)*(double)screen_h);
  }

  private int getLevelWidth(int screen_h)
  {
    return (int)(Constants.phiOrder(4)*(double)screen_h);
  }

  private int getLevelX(int screen_h)
  {
    if(text_level != null)
      return (int)(Constants.phiOrder(4)*(double)screen_h);
    else
      return getButtonSize(screen_h);
  }

  public int getProgressBarY(int screen_h)
  {
    return (int)(Constants.phiOrder(4)*(double)screen_h);
  }

  public int getProgressBarH(int screen_h)
  {
    return (int)(Constants.phiOrder(7)*(double)screen_h);
  }

  public void updateBtnEnabling()
  {
    incLevelBtn.setEnabled(level < num_levels - 1);
    decLevelBtn.setEnabled(level > 0);
  }


  private void incLevel()
  {
    if (level < num_levels-1)
      ++level;

    updateBtnEnabling();
    excercise.resetState();
  }

  private void decLevel()
  {
    if (level > 0)
      --level;

    updateBtnEnabling();
    excercise.resetState();
  }

  public boolean checkLevelChange(int x,int y)
  {
    if (decLevelBtn.isEnabled() && decLevelBtn.hitTestPoint(x, y))
    {
      decLevel();
      return true;
    }

    if (incLevelBtn.isEnabled() && incLevelBtn.hitTestPoint(x, y))
    {
      if(level < num_levels - 1)
        incLevel();
      return true;
    }

    return false;
  }


  public void drawProgress(int correct,int correct_needed, Canvas canvas)//, BitmapData bitmapData)
  {
    Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int full_w = display.getWidth();
    int full_h = display.getHeight();

    Paint paint = new Paint();

    float w = (correct_needed != 0) ? (int)(((double)correct / (double)correct_needed) * (double)full_w) : full_w;
    paint.setColor(Color.WHITE);
    canvas.drawRoundRect(new RectF(0,getProgressBarY(full_h),
        full_w, getProgressBarY(full_h)+getProgressBarH(full_h)), 0, 0, paint);
    paint.setColor(0xff009900);
    canvas.drawRoundRect(new RectF(0,getProgressBarY(full_h),w,
        getProgressBarY(full_h)+getProgressBarH(full_h)), 0, 0, paint);

    String progress_text = "";
    if(extended)
      progress_text = "Correct:";

    if(correct_needed != 0)
      progress_text += " " + Integer.toString(correct) + "/" + Integer.toString(correct_needed);
    else
      progress_text += " " + Integer.toString(correct);  

    text_correct.setText(progress_text);
    text_correct.setSize(full_w, getButtonSize(full_h));
    //text_level.setText(progress_text);

    if(text_level != null)
    {
      text_level.setTextSize(getFontSize(full_h));
      text_level.setSize(getLevelX(full_h) - getButtonSize(full_h), getButtonSize(full_h));
      text_level.draw(canvas);
    }

    text_level_num.setText(Integer.toString(level + 1));
    text_level_num.setTextSize(getFontSize(full_h));

    text_level_num.draw(canvas);
    text_level_num.moveTo(getLevelX(full_h), 0);
    text_level_num.setSize(getButtonSize(full_h), getButtonSize(full_h));

    if(!extended)
      text_correct.moveTo(getLevelX(full_h) + 2*getButtonSize(full_h), 0);

    text_correct.setTextSize(getFontSize(full_h));
    text_correct.draw(canvas);

    incLevelBtn.setSize(getButtonSize(full_h));
    decLevelBtn.setSize(getButtonSize(full_h));
    incLevelBtn.moveTo(getLevelX(full_h) + getLevelWidth(full_h), 0);
    decLevelBtn.moveTo(getLevelX(full_h) - getButtonSize(full_h), 0);
    incLevelBtn.draw(canvas);
    decLevelBtn.draw(canvas);
  }


}


