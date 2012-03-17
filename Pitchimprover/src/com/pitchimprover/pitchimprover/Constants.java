package com.pitchimprover.pitchimprover;

/**
 * ...
 * @author gary poison
 */
public class Constants
{
  public static int scr_w = 540;
  public static int scr_h = 330;

  public static double key_w0 = 3;
  public static double key_h0 = 12;
  public static double key_w1 = 2;
  public static double key_dw = 1;
  public static double key_h1 = 9;
  public static double key_scale = 5;

  public static double phi = 1.61803398874989;

  public static int keys_h = 150;

  public static int key_state_disabled = 0;
  public static int key_state_active = 1;
  public static int key_state_pressed = 2;
  public static int key_state_active_pressed = 3;

  public static int num_octaves = 3;
  public static int num_tones_in_octave = 12;

  public static int tone_c = 0; 
  public static int tone_c_sharp = 1;
  public static int tone_d = 2;
  public static int tone_e_flat = 3;
  public static int tone_e = 4;
  public static int tone_f = 5;
  public static int tone_f_sharp = 6;
  public static int tone_g = 7;
  public static int tone_a_flat = 8;
  public static int tone_a = 9;
  public static int tone_b_flat = 10;
  public static int tone_b = 11;

  public static int num_levels = 15;

  public static int uni = 0;
  public static int min2 = 1;
  public static int maj2  = 2;
  public static int min3  = 3;
  public static int maj3  = 4;
  public static int per4  = 5;
  public static int aug4  = 6;
  public static int per5  = 7;
  public static int min6 = 8;
  public static int maj6  = 9;
  public static int min7  = 10;
  public static int maj7  = 11;
  public static int per8  = 12;

  public static int[] tones = {tone_f_sharp, tone_e_flat, tone_a, tone_c, tone_c_sharp, tone_e, tone_g, tone_b_flat, tone_d, tone_f, tone_b, tone_a_flat};
  public static int[] intervals = {per8, per4, per5, maj3, min3, min2, maj2, maj6, min6, min7, maj7, aug4};

  public static int[] steps = {uni, maj2, min3, per4, per5, min6, min7, maj3, maj7, maj6, aug4};

  public static int[][] steps_by_levels = {
    {uni, maj3, per5},

    {uni, min3, per5},
    {uni, maj3, per4, per5},

    {uni, min3, per4, per5},

    {uni, maj2, maj3, per4, per5},

    {uni, maj2, min3, per4, per5},

    {uni, maj2, maj3, per5, maj6},

    {uni, min3, per5, min6, min7},

    {uni, maj2, maj3, per4, per5, maj6},

    {uni, maj2, min3, per4, per5, min6},

    {uni, min3, per4, aug4, per5, min7},

    {uni, maj2, maj3, per4, per5, maj6, maj7},

    {uni, maj2, min3, per4, per5, min6, min7},

    {uni, maj2, min3, per4, per5, min6, maj7},

    {uni, maj2, min3, per4, per5, maj6, maj7},

    {uni, maj2, min3, per4, per5, maj6, min7, maj7},

    {uni, maj2, min3, per4, per5, min6, maj6, min7, maj7},

    {uni, maj2, min3, per4, aug4, per5, min6, maj6, min7, maj7},

    {uni, maj2, min3, maj3, per4, aug4, per5, min6, maj6, min7, maj7},

    {uni, min2, maj2, min3, maj3, per4, aug4, per5, min6, maj6, min7, maj7},

    {uni, min2, maj2, min3, maj3, per4, aug4, per5, min6, maj6, min7, maj7}
  };


  public static String[]  level_names =       
  {
    "Major I,III,V", 
    "Minor I,III,V",
    "Major I,III,IV,V",
    "Minor I,III,IV,V",
    "Major I-V",
    "Minor I-V",
    "Major Pentatonic",
    "Minor Pentatonic",
    "Major I-VI",
    "Minor I-VI",
    "Blues",
    "Major",
    "Minor",
    "Harmonic Minor",
    "Melodic Minor",
    "Minor, 1 accidental",
    "Minor, 2 accidentals",
    "Minor, 3 accidentals",
    "Minor, 4 accidentals",
    "Chromatic",
    "Chromatic"
  };

  public static String[] interval_names = {"unison", "minor 2", "major 2", "minor 3", "major 3", "perfect 4", "tritone", "perfect 5", "minor 6", "major 6", "minor 7", "major 7", "octave", "minor 9", "major 9", "minor 10", "major 10"};

  public static String[] tone_names = {"C", "C#", "D", "Eb", "E", "F", "F#", "G", "G#", "A", "Bb", "B"};


  public static double[] tone_widths = {key_w0 + key_w1, key_w0 + key_w1, key_w0 + key_w1, key_w0 + key_w1, key_w0 + key_w1 + 0.5, key_w0 + key_w1 + 0.5, key_w0 + key_w1};
  public static int[] white_tones = {Constants.tone_c, Constants.tone_d, Constants.tone_e, Constants.tone_f, Constants.tone_g, Constants.tone_a, Constants.tone_b}; 


  public Constants() 
  {
  }

  public static double phiOrder(int order)
  {
    double inv_phi = 1./phi;
    if(order == 1)
      return inv_phi;

    return inv_phi*phiOrder(order-1);
  }

  public static int getNumMelodyLevels()
  {
    return steps_by_levels.length-1;
  }

  public static boolean isMajor(int level)
  {
    return (level == 0 || level == 2 || level == 4 || level == 6 || level == 8 || level == 11);
  }

  public static void init()
  {
  }

  public static String toneName(int tone)
  {
    return tone_names[tone % num_tones_in_octave];
  }

  public static boolean isWhiteTone(int tone)
  {
    for(int i = 0; i < white_tones.length; ++i)
      if(white_tones[i] == tone)
        return true;

    return false;
  }

  public static boolean isSharpKey(int key,boolean major)
  {
    if (!major)
      key = (key + 3)%12;

    return (key == tone_c || key == tone_g || key == tone_d || key == tone_a || key == tone_e || key == tone_b || key == tone_f_sharp || key == tone_c_sharp);
  }

  public static String toneNameInKey(int tone,int key,boolean major)
  {
    int corrected = tone % 12;

    if (isWhiteTone(corrected))
      return toneName(corrected);

    if (isSharpKey(key, major))
      return toneName(corrected - 1 + 12) + "#";

    //flat key
    return toneName(corrected + 1) + "b";
  }





}


