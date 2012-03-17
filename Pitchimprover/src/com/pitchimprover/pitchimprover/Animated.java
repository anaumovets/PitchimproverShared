package com.pitchimprover.pitchimprover;


import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Rect;



/**
 * ...
 * @author DefaultUser (Tools -> Custom Arguments...)
 */
class Animated 
{
  private Bitmap image;


  public Animated(Bitmap i_image) 
  {
    image = i_image; 
  }

  public void Draw(Canvas i_canvas, int x, int y) 
  {
    i_canvas.drawBitmap(image, x, y, null);
  }

  public void Draw(Canvas i_canvas, int wherex, int wherey, int srcx, int srcy, int w, int h)
  {
    Bitmap sub = Bitmap.createBitmap(image, srcx, srcy, w, h);
    i_canvas.drawBitmap(sub, wherex, wherey, null);
  }

  public void Draw(Canvas i_canvas, int wherex, int wherey, int srcx, int srcy, int sw, int sh, int dw, int dh)
  {
    //Bitmap sub = Bitmap.createBitmap(image, srcx, srcy, sw, sh);
    i_canvas.drawBitmap(image, new Rect(srcx, srcy, srcx+sw, srcy+sh), 
        new Rect(wherex, wherey, wherex+dw, wherey+dh), null);
  }

  public int width()
  {
    return image.getWidth();
  }

  public int height()
  {
    return image.getHeight();
  }    
}


