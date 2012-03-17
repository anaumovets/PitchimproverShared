package com.pitchimprover.pitchimprover;

import android.graphics.Canvas;

/**
 * Thread class to perform the so called "game loop".
 * 
 * @author martin
 */
public class MapThread extends Thread {
  private CellMap _cellMap;
  public boolean _run = false;

  /**
   * Constructor.
   * 
   * @param panel View class on which we trigger the drawing.
   */
  public MapThread(CellMap map) {
    _cellMap = map;
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
        c = _cellMap.getHolder().lockCanvas(null);
        synchronized (_cellMap.getHolder()) {
          _cellMap.onDraw(c);
        }
      } finally {
        // do this in a finally so that if an exception is thrown
        // during the above, we don't leave the Surface in an
        // inconsistent state
        if (c != null) {
          _cellMap.getHolder().unlockCanvasAndPost(c);
        }
      }
    }
  }
}