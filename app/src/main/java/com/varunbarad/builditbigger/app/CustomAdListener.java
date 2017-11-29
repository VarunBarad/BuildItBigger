package com.varunbarad.builditbigger.app;

/**
 * Creator: Varun Barad
 * Date: 29-11-2017
 * Project: BuildItBigger
 */
public interface CustomAdListener {
  void onAdLoaded();
  
  void onAdFailedToLoad(int errorCode);
  
  void onAdOpened();
  
  void onAdLeftApplication();
  
  void onAdClosed();
}
