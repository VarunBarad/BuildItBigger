package com.varunbarad.builditbigger.app;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.varunbarad.builditbigger.app.databinding.ActivityMainBinding;
import com.varunbarad.builditbigger.javajokeslib.model.Joke;
import com.varunbarad.builditbigger.jokesdisplaylibrary.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity {
  private FetchJokeTask fetchJokeTask;
  
  private ActivityMainBinding dataBinding;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    
    this.loadAd();
  }
  
  public void tellJoke(View view) {
    this.fetchJokeTask = new FetchJokeTask(new JokeListener() {
      @Override
      public void jokeReceived(Joke joke) {
        DisplayJokeActivity.start(MainActivity.this, joke);
      }
    });
    
    this.fetchJokeTask.execute();
  }
  
  private void loadAd() {
    MobileAds.initialize(this, BuildConfig.AdMobKey);
    
    AdRequest adRequest =
        new AdRequest.Builder()
            .build();
    
    this.dataBinding.adViewMainActivity.loadAd(adRequest);
  }
}
