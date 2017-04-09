package com.intertek.report.jasper;

import java.util.ArrayList;

public class Tally {

  private int currentResponse = 0;
  private boolean firstEntry = true;
  private boolean breakFlag = true;
  private boolean delayedBreakFlag = true;
  private boolean nextRupture = false;
  ArrayList<Integer> contributeStack = new ArrayList<Integer>();

  public Tally(){
  }


  public String contribute(int currentTally){
    delayedBreakFlag = breakFlag;
    if (nextRupture){
      nextRupture=false;
      currentResponse++;
    }
    breakFlag = true;
    if (currentTally<100){
      contributeStack.add(new Integer(currentTally));
      if (getStackSum()>=200){ //start new group;
        nextRupture=true;
        contributeStack.clear();
        breakFlag = true;
      } else {
        breakFlag = false;
      }
    } else {
      nextRupture=true;
      contributeStack.clear();
      breakFlag = false;
    }
    return String.valueOf(currentResponse);
  }

  public int getStackSum(){
    int total = 0;
    for (Integer entry: contributeStack){
      total += entry.intValue();
    }
    return total;
  }


  public Boolean wasBreakOut(){
    if (breakFlag==true) delayedBreakFlag = breakFlag;
    return delayedBreakFlag;
  }

}
