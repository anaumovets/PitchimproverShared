package com.pitchimprover.pitchimprover;


import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Display;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/*
 * ExcerciseHelper contains util functions and is inherited by all excercise classes
 */
public abstract class ExcerciseHelper implements IExcercise
{
  private TextHelper msg_text;
  private TextHelper sub_text;
  private Activity activity;
  private Context context;


  public void init(Context i_context, Activity i_activity)
  {
    activity = i_activity;
    context = i_context;
    msg_text = new TextHelper(0, 0, 100, 100, "",  18, context, Gravity.CENTER);
    sub_text = new TextHelper(0, 0, 100, 100, "",  18, context, Gravity.CENTER);

  }
  
  public int getFontSize(Context context)
  {
    Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    return (int)((Constants.phiOrder(6))*(double)display.getHeight());
  }

  public void drawSubText(String text, Context context, Canvas canvas)
  {
    Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
    int full_w = display.getWidth();

    Rect rect = new Rect();
    Window window = activity.getWindow();
    window.getDecorView().getWindowVisibleDisplayFrame(rect);
    int content_top = window.findViewById(Window.ID_ANDROID_CONTENT).getTop();
    int toolbar_delta = content_top;

    sub_text.setTextSize(getFontSize(context));
    sub_text.moveTo(full_w/5, (int) (display.getHeight() - getFontSize(context)*4 - toolbar_delta));
    sub_text.setSize(display.getWidth() - full_w/5, getFontSize(context)*4);
    sub_text.setText(text);
    sub_text.draw(canvas);
  }

  public void mouseDownHelper(int x,int y)
  {
  }
  
  public void drawMessageBox(String text, Context context, Canvas canvas)
  {
    Display display = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
  
    int xbox = display.getWidth()/5;
    int ybox = display.getHeight()/5;
    int wbox = (display.getWidth()*3)/5;
    int hbox = (display.getHeight()*3)/5;
  
    drawMessageBoxBkg(xbox, ybox, wbox, hbox, canvas);
    
    msg_text.setTextSize(getFontSize(context));
    msg_text.moveTo(xbox+5, ybox+5);
    msg_text.setSize(wbox, hbox);
    msg_text.setText(text);
    msg_text.draw(canvas);
  }

  public void drawHelper(Canvas canvas)
  {
  }
  
  private void drawMessageBoxBkg(int x,int y, int w, int h, Canvas canvas)
  {
    Paint paint = new Paint();
    paint.setColor(Color.BLACK);
    paint.setAlpha(200);
    canvas.drawRoundRect(new RectF(x, y, x + w, y + h), 5, 5, paint);
  }

}
