package com.pitchimprover.pitchimprover;
  //this file is generated automatically
  import java.util.HashMap;
  import android.content.Context;
  import android.media.AudioManager;
  import android.media.SoundPool;
  public class SoundManager
  {
  private static Context context;
  private static HashMap<Integer, Integer> sound_map;
  private static SoundPool sound_pool;
  private static AudioManager audio_manager;
      public static void init(Context i_context)
  {
  context = i_context;
  sound_map  = new HashMap<Integer, Integer>();
  sound_pool = new SoundPool(8, AudioManager.STREAM_MUSIC, 0);
  sound_map.put(0, sound_pool.load(context, R.raw.piano_silence_01, 1));
  sound_map.put(1, sound_pool.load(context, R.raw.piano_silence_02, 1));
  sound_map.put(2, sound_pool.load(context, R.raw.piano_silence_03, 1));
  sound_map.put(3, sound_pool.load(context, R.raw.piano_silence_04, 1));
  sound_map.put(4, sound_pool.load(context, R.raw.piano_silence_05, 1));
  sound_map.put(5, sound_pool.load(context, R.raw.piano_silence_06, 1));
  sound_map.put(6, sound_pool.load(context, R.raw.piano_silence_07, 1));
  sound_map.put(7, sound_pool.load(context, R.raw.piano_silence_08, 1));
  sound_map.put(8, sound_pool.load(context, R.raw.piano_silence_09, 1));
  sound_map.put(9, sound_pool.load(context, R.raw.piano_silence_10, 1));
  sound_map.put(10, sound_pool.load(context, R.raw.piano_silence_11, 1));
  sound_map.put(11, sound_pool.load(context, R.raw.piano_silence_12, 1));
  sound_map.put(12, sound_pool.load(context, R.raw.piano_silence_13, 1));
  sound_map.put(13, sound_pool.load(context, R.raw.piano_silence_14, 1));
  sound_map.put(14, sound_pool.load(context, R.raw.piano_silence_15, 1));
  sound_map.put(15, sound_pool.load(context, R.raw.piano_silence_16, 1));
  sound_map.put(16, sound_pool.load(context, R.raw.piano_silence_17, 1));
  sound_map.put(17, sound_pool.load(context, R.raw.piano_silence_18, 1));
  sound_map.put(18, sound_pool.load(context, R.raw.piano_silence_19, 1));
  sound_map.put(19, sound_pool.load(context, R.raw.piano_silence_20, 1));
  sound_map.put(20, sound_pool.load(context, R.raw.piano_silence_21, 1));
  sound_map.put(21, sound_pool.load(context, R.raw.piano_silence_22, 1));
  sound_map.put(22, sound_pool.load(context, R.raw.piano_silence_23, 1));
  sound_map.put(23, sound_pool.load(context, R.raw.piano_silence_24, 1));
  sound_map.put(24, sound_pool.load(context, R.raw.piano_silence_25, 1));
  sound_map.put(25, sound_pool.load(context, R.raw.piano_silence_26, 1));
  sound_map.put(26, sound_pool.load(context, R.raw.piano_silence_27, 1));
  sound_map.put(27, sound_pool.load(context, R.raw.piano_silence_28, 1));
  sound_map.put(28, sound_pool.load(context, R.raw.piano_silence_29, 1));
  sound_map.put(29, sound_pool.load(context, R.raw.piano_silence_30, 1));
  sound_map.put(30, sound_pool.load(context, R.raw.piano_silence_31, 1));
  sound_map.put(31, sound_pool.load(context, R.raw.piano_silence_32, 1));
  sound_map.put(32, sound_pool.load(context, R.raw.piano_silence_33, 1));
  sound_map.put(33, sound_pool.load(context, R.raw.piano_silence_34, 1));
  sound_map.put(34, sound_pool.load(context, R.raw.piano_silence_35, 1));
  sound_map.put(35, sound_pool.load(context, R.raw.piano_silence_36, 1));
  audio_manager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
  }
  public static void playSound(Context context, int tone)
  {
  float streamVolume = audio_manager.getStreamVolume(AudioManager.STREAM_MUSIC);
  streamVolume = streamVolume / audio_manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
  sound_pool.play(sound_map.get(tone), streamVolume, streamVolume, 1, 0, 1f);
  }
  };
