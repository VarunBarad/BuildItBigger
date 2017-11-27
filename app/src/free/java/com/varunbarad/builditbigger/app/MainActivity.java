package com.varunbarad.builditbigger.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.varunbarad.builditbigger.app.databinding.ActivityMainBinding;
import com.varunbarad.builditbigger.javajokeslib.Joker;
import com.varunbarad.builditbigger.javajokeslib.model.Joke;
import com.varunbarad.builditbigger.jokesdisplaylibrary.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity {
  private Joker joker;
  
  private ActivityMainBinding dataBinding;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    
    this.joker = Joker.getInstance();
    
    if (!this.joker.hasMoreJokes()) {
      this.dataBinding.buttonTellJoke.setVisibility(View.GONE);
    }
  
    this.loadAd();
  }
  
  public void tellJoke(View view) {
    if (joker.hasMoreJokes()) {
      Joke joke = joker.getRandomJoke();
      DisplayJokeActivity.start(this, joke);
      
      if (!joker.hasMoreJokes()) {
        this.dataBinding.buttonTellJoke.setVisibility(View.GONE);
      }
    } else {
      Toast.makeText(this, "Sorry, no jokes available", Toast.LENGTH_SHORT).show();
    }
  }
  
  private void loadAd() {
    MobileAds.initialize(this, BuildConfig.AdMobKey);
    
    AdRequest adRequest =
        new AdRequest.Builder()
            .build();
    
    this.dataBinding.adViewMainActivity.loadAd(adRequest);
  }
}
