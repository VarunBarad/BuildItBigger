package com.varunbarad.builditbigger.app;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.varunbarad.builditbigger.app.databinding.ActivityMainBinding;

/**
 * Creator: Varun Barad
 * Date: 29-11-2017
 * Project: BuildItBigger
 */
public final class AdHelper {
  public static void loadBannerAdForMainActivity(Context context, ActivityMainBinding dataBinding) {
    MobileAds.initialize(context, BuildConfig.AdMobKey);
    
    AdRequest adRequest =
        new AdRequest.Builder()
            .build();
    
    dataBinding.adViewMainActivity.loadAd(adRequest);
  }
}
