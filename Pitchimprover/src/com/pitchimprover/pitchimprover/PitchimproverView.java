package com.pitchimprover.pitchimprover;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.RelativeLayout;


/**
 * The SurfaceView, on which we draw.
 * The drawing loop is here.
 */
public class PitchimproverView extends SurfaceView implements SurfaceHolder.Callback {
  private MapThread _mapThread;
  private Context context;

  private int _xTouch = 0;
  private int _yTouch = 0;

  public static int state_menu = 0;
  public static int state_excercise = 1;


  public int state;
  RelativeLayout panel;

  IExcercise excercise;
  Pitchimprover activity;
  


  /**
   * Constructor, fills the map on creation.
   * 
   * @param context
   */
  public PitchimproverView(Context i_context, RelativeLayout i_panel, Pitchimprover i_activity) {

    super(i_context);
    panel = i_panel;
    context = i_context;
    RandomUtil.init();
    TextHelper.init(i_context);
    SoundManager.init(i_context);
    activity = i_activity;
    


    
    state = state_menu;
    // register the view to the surfaceholder
    getHolder().addCallback(this);

    // set the thread - not yet started
    _mapThread = new MapThread(this);

    // secure the view is focusable
    setFocusable(true);
  }

  /**
   * Draw what we want to see.
   */
  @Override
  public void onDraw(Canvas canvas) {
    if(state == state_excercise)
      excercise.draw(canvas);
  }

  public void backToMenu()
  {
    state = state_menu;
    activity.setContentView(R.layout.main);
    activity.initOnClick();
  }

  /**
   * Handle touch event on the map.
   */
  @Override
  public boolean onTouchEvent(MotionEvent event) {

    synchronized (getHolder())
    {
      // touch down
      if (event.getAction() == MotionEvent.ACTION_DOWN) {
        // start of a new event, reset the flag
        // store the current touch coordinates for scroll calculation
        _xTouch = (int) event.getX();
        _yTouch = (int) event.getY();

          if(state == state_excercise)
          {
            excercise.mouseDown(_xTouch, _yTouch);
          }
      } 

      if (event.getAction() == MotionEvent.ACTION_UP) {
        _xTouch = (int) event.getX();
        _yTouch = (int) event.getY();
        if(state == state_excercise)
          excercise.mouseUp(_xTouch, _yTouch);
      }
    }
    return true;
  }


  public void startExcerciseIntervals()
  {
    excercise = new ExcerciseIntervals(context, activity);
    state = state_excercise; 
  }


  @Override
  public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {}

  /**
   * Called when surface created.
   * Starts the thread if not already running.
   */
  @Override
  public void surfaceCreated(SurfaceHolder holder) {
    if (!_mapThread.isAlive()) {
      _mapThread = new MapThread(this);
    }
    _mapThread._run = true;
    _mapThread.start();
  }

  /**
   * Called when surface destroyed
   * Stops the thread.
   */
  @Override
  public void surfaceDestroyed(SurfaceHolder holder) {
    boolean retry = true;
    
    _mapThread._run = false;
    while (retry) {
      try {
        _mapThread.join();
        retry = false;
      } catch (InterruptedException e) {
        // we will try it again and again...
      }
    }
  }
}