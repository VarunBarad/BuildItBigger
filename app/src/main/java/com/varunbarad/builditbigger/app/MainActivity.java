package com.varunbarad.builditbigger.app;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.idling.CountingIdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.varunbarad.builditbigger.app.databinding.ActivityMainBinding;
import com.varunbarad.builditbigger.javajokeslib.model.Joke;
import com.varunbarad.builditbigger.jokesdisplaylibrary.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity {
  @Nullable
  private CountingIdlingResource idlingResource;
  
  private ProgressDialog progressDialog;
  
  private ActivityMainBinding dataBinding;
  
  private InterstitialAdHelper interstitialAdHelper;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
  
    AdHelper.loadBannerAdForMainActivity(this, this.dataBinding);
    this.interstitialAdHelper = new InterstitialAdHelper(this, new CustomAdListener() {
      @Override
      public void onAdLoaded() {
      
      }
    
      @Override
      public void onAdFailedToLoad(int errorCode) {
      
      }
    
      @Override
      public void onAdOpened() {
      
      }
    
      @Override
      public void onAdLeftApplication() {
      
      }
    
      @Override
      public void onAdClosed() {
        MainActivity.this.fetchJoke();
      }
    });
  
    this.getIdlingResource();
  }
  
  public void showInterstitialAd() {
    this.interstitialAdHelper.showAd();
  }
  
  public void fetchJoke() {
    this.interstitialAdHelper.reset();
    
    FetchJokeTask fetchJokeTask = new FetchJokeTask(new JokeListener() {
      @Override
      public void jokeReceived(Joke joke) {
        MainActivity.this.launchDetailsActivity(joke);
      }
    });
    
    fetchJokeTask.execute();
    this.showProgressDialog();
  }
  
  public void tellJoke(View view) {
    if (this.idlingResource != null) {
      this.idlingResource.increment();
    }
  
    if (this.interstitialAdHelper.isAdLoaded()) {
      this.showInterstitialAd();
    } else {
      this.fetchJoke();
    }
  }
  
  private void showProgressDialog() {
    this.dismissProgressDialog();
    
    this.progressDialog = new ProgressDialog(this);
    this.progressDialog.setCancelable(false);
    this.progressDialog.setIndeterminate(true);
    this.progressDialog.setTitle("Fetching joke");
    this.progressDialog.setMessage("Please wait a while...");
    this.progressDialog.show();
  }
  
  private void dismissProgressDialog() {
    if (this.progressDialog != null && this.progressDialog.isShowing()) {
      this.progressDialog.dismiss();
      this.progressDialog = null;
    }
  }
  
  private void launchDetailsActivity(Joke joke) {
    this.dismissProgressDialog();
    DisplayJokeActivity.start(this, joke);
    
    if (this.idlingResource != null) {
      this.idlingResource.decrement();
    }
  }
  
  @VisibleForTesting
  @Nullable
  public IdlingResource getIdlingResource() {
    if (this.idlingResource == null) {
      this.idlingResource = new CountingIdlingResource("Network-Idling-Resource");
    }
    return this.idlingResource;
  }
}
