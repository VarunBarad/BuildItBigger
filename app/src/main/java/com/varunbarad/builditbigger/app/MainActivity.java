package com.varunbarad.builditbigger.app;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.varunbarad.builditbigger.app.databinding.ActivityMainBinding;
import com.varunbarad.builditbigger.javajokeslib.model.Joke;
import com.varunbarad.builditbigger.jokesdisplaylibrary.DisplayJokeActivity;


public class MainActivity extends AppCompatActivity {
  private ProgressDialog progressDialog;
  
  private ActivityMainBinding dataBinding;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
  
    AdHelper.loadBannerAdForMainActivity(this, this.dataBinding);
  }
  
  public void tellJoke(View view) {
    FetchJokeTask fetchJokeTask = new FetchJokeTask(new JokeListener() {
      @Override
      public void jokeReceived(Joke joke) {
        MainActivity.this.dismissProgressDialog();
        DisplayJokeActivity.start(MainActivity.this, joke);
      }
    });
  
    fetchJokeTask.execute();
    this.showProgressDialog();
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
}
