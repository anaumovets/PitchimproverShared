package com.pitchimprover.pitchimprover;  

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;



public class Interval
{
  class OnFirstSoundComplete implements Runnable
  {
    private Context m_context;
    private int m_tone1;

    public OnFirstSoundComplete(Context context, int tone1)
    {
      m_context = context;
      m_tone1 = tone1;
    }

    public void run() 
    {
      SoundManager.playSound(m_context, m_tone1);
    }
  };

  public int tone0;
  public int diff;
  static private int prev_tone0;
  MediaPlayer player;

  //private Timer timer;

  public Interval()
  {
  }

  public boolean equal(Interval i_other)
  {
    return (tone0 == i_other.tone0 && diff == i_other.diff);
  }

  public void play(Context context) 
  {
    SoundManager.playSound(context, tone0);

    OnFirstSoundComplete sc = new OnFirstSoundComplete(context, tone0 + diff);
    Handler h = new Handler();
    h.removeCallbacks(sc);
    h.postDelayed(sc, 1000);
  }

  public void randomOfTwo(int diff0, int diff1)
  {
    int tone_ind = RandomUtil.getMod(2);

    if (tone_ind == 0)
      diff = diff0; 
    else 
      diff = diff1;

    if (RandomUtil.getNormalised() > 0.5)
      diff = -diff;


    tone0 = RandomUtil.getMod(Constants.num_tones_in_octave);
    int octave_ind = RandomUtil.getMod(Constants.num_octaves);
    tone0 += octave_ind*Constants.num_tones_in_octave;

    while (tone0 + diff < 0 || tone0 + diff >= Constants.num_tones_in_octave*Constants.num_octaves || tone0 == prev_tone0)
    {
      tone0 = RandomUtil.getMod(Constants.num_tones_in_octave);
      octave_ind = RandomUtil.getMod(Constants.num_octaves);
      tone0 += octave_ind*Constants.num_tones_in_octave;
    }

    prev_tone0 = tone0;
  }

  public void randomInterval(int level)
  {
    diff = 0;

    int interval_ind = RandomUtil.getMod(level + 2);
    diff = Constants.intervals[interval_ind];
    if (RandomUtil.getNormalised() > 0.5)
      diff = -diff;

    tone0 = RandomUtil.getMod(Constants.num_tones_in_octave);
    int octave_ind = RandomUtil.getMod(Constants.num_octaves);
    tone0 += octave_ind*Constants.num_tones_in_octave;

    while (tone0 + diff < 0 || tone0 + diff >= Constants.num_tones_in_octave*Constants.num_octaves || tone0 == prev_tone0)
    {
      tone0 = RandomUtil.getMod(Constants.num_tones_in_octave);
      octave_ind = RandomUtil.getMod(Constants.num_octaves);
      tone0 += octave_ind*Constants.num_tones_in_octave;
    }

    prev_tone0 = tone0;
  }


  public String toString()
  {
    String s0 = Constants.tone_names[tone0 % Constants.num_tones_in_octave];
    String s1 = Constants.tone_names[(tone0 + diff) % Constants.num_tones_in_octave];

    int abdsdiff = Math.abs(diff);

    String s2 = Constants.interval_names[abdsdiff];

    return (s0 + "-" + s1 + " ("+s2 + ")");
  }




}

