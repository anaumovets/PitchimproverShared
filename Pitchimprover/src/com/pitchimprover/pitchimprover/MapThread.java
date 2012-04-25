package com.pitchimprover.pitchimprover;

import android.graphics.Canvas;

/**
 * Thread class to perform the loop.
 * 
 * @author garypoison
 */
public class MapThread extends Thread {
  private PitchimproverView _PitchimproverView;
  public boolean _run = false;

  /**
   * Constructor.
   * 
   * @param panel View class on which we trigger the drawing.
   */
  public MapThread(PitchimproverView map) {
    _PitchimproverView = map;
  }

  /**
   * Perform the game loop.
   */
  @Override
  public void run() {
    Canvas c;
    while (_run) {
      c = null;
      try { 
        c = _PitchimproverView.getHolder().lockCanvas(null);
        synchronized (_PitchimproverView.getHolder()) {
          _PitchimproverView.onDraw(c);
        }
      } finally {
        // do this in a finally so that if an exception is thrown
        // during the above, we don't leave the Surface in an
        // inconsistent state
        if (c != null) {
          _PitchimproverView.getHolder().unlockCanvasAndPost(c);
        }
      }
    }
  }
}