package com.pitchimprover.pitchimprover;


import android.graphics.Canvas;

/**
 * ...
 * @author gary poison
 */

public interface IExcercise 
{ 
  void resetState(); 
  void newTask(); 
  void setLevel(int lvl); 
  void playTask(); 
  boolean isActive(int tone); 
  void keyPressed(int key); 
  void mouseDown(int x,int y); 
  void mouseMove(int x,int y); 
  void mouseUp(int x,int y); 
  void clickedAnywhere(); 
  void subscribeRoot(Canvas canvas); 
  void subscribeLevel(Canvas canvas);
  void drawPrompt(Canvas canvas); 
  void draw(Canvas canvas); 
  void drawAnswer(Canvas canvas); 
  void drawLevelComplete(Canvas canvas); 
  void drawGameOver(Canvas canvas);
  boolean playOrRepeat();
  int getCorrectInRow();
  int correctNeeded();
  int getLevel();
  int getNumLevels();
}
