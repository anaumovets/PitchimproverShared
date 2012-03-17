﻿package com.pitchimprover.pitchimprover;

import android.content.Context;
import android.graphics.Canvas;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * ...
 * @author gary poison
 */
public class MButton 
{
  public MButton(int x,int y,int width,int height, String text, Context context)
  {
    m_x = x;
    m_y = y;
    m_width = width;
    m_height = height;

    btn = new Button(context);
    btn.setText(text);
    btn.setMaxWidth(width);
    btn.setWidth(width);
    btn.setMaxHeight(height);
    btn.setHeight(height);
    btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, height/2);
    layout = new LinearLayout(context);

    layout.setClickable(true);
    //We set the layout parameters
    params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    //SET THE MARGIN HERE
    params.setMargins(x, y, 0, 0);
    params.width = width;
    params.height = height;

    btn.setCompoundDrawablePadding(2);
    layout.addView(btn, params);
  }
  
  public void setTextSize(int size)
  {
    btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
  }


  public void setEnabled(boolean enable) 
  {
    btn.setEnabled(enable);
    layout.removeView(btn);
    layout.addView(btn, params);
    btn.setLayoutParams(params);
  }

  public boolean isEnabled()
  {
    return btn.isEnabled();
  }

  public boolean hitTestPoint(int x, int y)
  {
    if(x < m_x) return false;
    if(x > m_x + m_width) return false;
    if(y < m_y) return false;
    if(y > m_y + m_height) return false;

    btn.performClick();
    return true;
  }

  public void setComplexText(String text)
  {
    btn.setText(text);
  }

  public void setSize(int s)
  {
    params.width = s;
    params.height = s;

    m_width = s;
    m_height = s;

    layout.removeView(btn);
    layout.addView(btn, params);
    btn.setLayoutParams(params);
  }

  public void setDims(int w, int h)
  {
    params.width = w;
    params.height = h;

    m_width = w;
    m_height = h;

    layout.removeView(btn);
    layout.addView(btn, params);
    btn.setLayoutParams(params);
  }

  
  public void moveTo(int x,int y)
  {
    params.leftMargin = x;
    params.topMargin = y;

    m_x = x;
    m_y = y;
    btn.setLayoutParams(params);
  }

  public void draw(Canvas canvas)
  {
    //Measure and layout the linear layout before drawing it
    layout.measure(MeasureSpec.getSize(layout.getMeasuredWidth()), MeasureSpec.getSize(layout.getMeasuredHeight()));
    layout.layout(0, 0, MeasureSpec.getSize(btn.getMeasuredWidth()), MeasureSpec.getSize(btn.getMeasuredHeight()));
    //Finally draw the linear layout on the canvas
    layout.draw(canvas);
  }

  public int getHeight()
  {
    return btn.getHeight();
  }
  
  private LinearLayout layout;
  private Button btn;
  private LinearLayout.LayoutParams params;
  private int m_x;
  private int m_y;
  private int m_width;
  private int m_height;

}

