package com.varunbarad.builditbigger.app;

import android.content.Context;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

/**
 * Creator: Varun Barad
 * Date: 29-11-2017
 * Project: BuildItBigger
 */
public final class InterstitialAdHelper {
  private InterstitialAd interstitialAd;
  private CustomAdListener adListener;
  
  public InterstitialAdHelper(Context context, CustomAdListener adListener) {
    this.adListener = adListener;
    
    this.interstitialAd = new InterstitialAd(context);
    this.interstitialAd.setAdUnitId(BuildConfig.AdMobInterstitialUnitId);
    this.interstitialAd.loadAd((new AdRequest.Builder()).build());
    this.interstitialAd.setAdListener(new AdListener() {
      @Override
      public void onAdLoaded() {
        InterstitialAdHelper.this.adListener.onAdLoaded();
      }
      
      @Override
      public void onAdFailedToLoad(int errorCode) {
        InterstitialAdHelper.this.adListener.onAdFailedToLoad(errorCode);
      }
      
      @Override
      public void onAdOpened() {
        InterstitialAdHelper.this.adListener.onAdOpened();
      }
      
      @Override
      public void onAdLeftApplication() {
        InterstitialAdHelper.this.adListener.onAdLeftApplication();
      }
      
      @Override
      public void onAdClosed() {
        InterstitialAdHelper.this.adListener.onAdClosed();
      }
    });
  }
  
  public boolean isAdLoaded() {
    if (this.interstitialAd != null) {
      return this.interstitialAd.isLoaded();
    } else {
      return false;
    }
  }
  
  public void showAd() {
    if (this.isAdLoaded()) {
      this.interstitialAd.show();
    }
  }
  
  public void reset() {
    this.interstitialAd.loadAd((new AdRequest.Builder()).build());
  }
}
