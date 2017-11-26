package com.varunbarad.builditbigger.jokesdisplaylibrary;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.varunbarad.builditbigger.javajokeslib.model.Joke;
import com.varunbarad.builditbigger.jokesdisplaylibrary.databinding.ActivityDisplayJokeBinding;

public class DisplayJokeActivity extends AppCompatActivity {
  private static final String KEY_JOKE = "key_joke";
  
  private Joke joke;
  
  private ActivityDisplayJokeBinding dataBinding;
  
  public static void start(Context context, Joke joke) {
    Intent displayJokeIntent = new Intent(context, DisplayJokeActivity.class);
    displayJokeIntent.putExtra(KEY_JOKE, joke.toJson());
    context.startActivity(displayJokeIntent);
  }
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_display_joke);
    
    if (this.getIntent().hasExtra(KEY_JOKE)) {
      this.joke = Joke.getInstance(this.getIntent().getStringExtra(KEY_JOKE));
    }
    
    if (this.joke != null) {
      this.dataBinding.textViewDisplayJokeSetup.setText(this.joke.getSetup());
      this.dataBinding.textViewDisplayJokePunchline.setText(this.joke.getPunchline());
    }
    
    if (this.getSupportActionBar() != null) {
      this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
  }
  
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      this.onBackPressed();
      return true;
    } else {
      return super.onOptionsItemSelected(item);
    }
  }
}
