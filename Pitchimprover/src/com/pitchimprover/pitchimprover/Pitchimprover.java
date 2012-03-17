package com.pitchimprover.pitchimprover;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;


//Activity for the application

public class Pitchimprover extends Activity {
  private PitchimproverView _map;
  private Button btn_intervals;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    RelativeLayout panel = new RelativeLayout(this);
    panel.setLayoutParams(new RelativeLayout.LayoutParams(0, 0));


    setContentView(R.layout.main);

    _map = new PitchimproverView(this, panel, this);
    initOnClick();
  }
  
  @Override
  public void onBackPressed()
  {
    if(_map.state == PitchimproverView.state_excercise)
      _map.backToMenu();
    else
      super.onBackPressed();
  }
  
  public void initOnClick()
  {
    btn_intervals = (Button)findViewById(R.id.button_intervals);
    btn_intervals.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {_map.startExcerciseIntervals(); setContentView(_map);}
    });
  }


}