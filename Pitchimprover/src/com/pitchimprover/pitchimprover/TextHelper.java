package com.pitchimprover.pitchimprover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View.MeasureSpec;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * ...
 * @author gary poison
 */
public class TextHelper 
{
  private static TextView text_view;
  private static LinearLayout l_layout;
  private static LinearLayout.LayoutParams l_layoutParams;
  private static Context context;


  public static void init(Context i_context)
  {
    text_view = new TextView(i_context);
    context = i_context;
    l_layout = new LinearLayout(context);
    //We set the layout parameters
    l_layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    //SET THE MARGIN HERE
    l_layout.addView(text_view, l_layoutParams);
  }

  public static void drawText(int x,int y,int width,int height, String i_text, float textsize, int gravity, Canvas canvas)
  {
    text_view.setText(i_text);
    text_view.setTextSize(textsize);
    text_view.setWidth(width);
    text_view.setHeight(height);

    LinearLayout l_layout = new LinearLayout(context);
    //We set the layout parameters

    //SET THE MARGIN HERE
    l_layoutParams.setMargins(x, y, 0, 0);
    l_layoutParams.width = width;
    l_layoutParams.height = height;
    //Add it to our linear layout
    text_view.setLayoutParams(l_layoutParams);
    text_view.setGravity(gravity);


    //Measure and layout the linear layout before drawing it
    l_layout.measure(MeasureSpec.getSize(l_layout.getMeasuredWidth()), MeasureSpec.getSize(l_layout.getMeasuredHeight()));
    l_layout.layout(0, 0, MeasureSpec.getSize(text_view.getMeasuredWidth()), MeasureSpec.getSize(text_view.getMeasuredHeight()));
    //Finally draw the linear layout on the canvas
    l_layout.draw(canvas);
  }

  public TextHelper(int x,int y,int width,int height, String i_text, float textsize, Context context, int gravity)
  {
    text = new TextView(context);
    text.setText(i_text);
    text.setTextSize(textsize);
    text.setWidth(width);
    text.setHeight(height);
    text.setTextColor(Color.WHITE);

    layout = new LinearLayout(context);
    //We set the layout parameters
    params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    //SET THE MARGIN HERE
    params.setMargins(x, y, 0, 0);
    params.width = width;
    params.height = height;
    //Add it to our linear layout


    text.setLayoutParams(params);
    text.setGravity(gravity);
    layout.addView(text, params);
  }

  public void setText(String i_text)
  {
    text.setText(i_text);
  }

  public void setTextSize(int text_size)
  {
    text.setTextSize(TypedValue.COMPLEX_UNIT_PX, text_size);
  }
  
  public void setTextColor(int color)
  {
    text.setTextColor(color);
  }

  public void moveTo(int x, int y)
  {
    params.leftMargin = x;
    params.topMargin = y;
    layout.removeView(text);
    layout.addView(text, params);
    text.setLayoutParams(params);
  }

  public void setSize(int w, int h)
  {
    params.width = w;
    params.height = h;
    params.gravity = Gravity.CENTER;
    layout.removeView(text);
    layout.addView(text, params);
    text.setLayoutParams(params);
  }


  public void draw(Canvas canvas)
  {
    //Measure and layout the linear layout before drawing it
    layout.measure(MeasureSpec.getSize(layout.getMeasuredWidth()), MeasureSpec.getSize(layout.getMeasuredHeight()));
    layout.layout(0, 0, MeasureSpec.getSize(text.getMeasuredWidth()), MeasureSpec.getSize(text.getMeasuredHeight()));
    //Finally draw the linear layout on the canvas
    layout.draw(canvas);
  }

  public TextView text;
  LinearLayout layout;
  LinearLayout.LayoutParams params;
}
