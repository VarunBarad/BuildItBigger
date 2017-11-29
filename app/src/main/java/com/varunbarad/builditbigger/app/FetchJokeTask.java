package com.varunbarad.builditbigger.app;

import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.varunbarad.builditbigger.javajokeslib.model.Joke;
import com.varunbarad.builditbigger.backend.jokeApi.JokeApi;

import java.io.IOException;

/**
 * Creator: Varun Barad
 * Date: 28-11-2017
 * Project: BuildItBigger
 */
public class FetchJokeTask extends AsyncTask<Void, Void, Joke> {
  private static JokeApi jokeApiService = null;
  private JokeListener jokeListener;
  
  public FetchJokeTask(JokeListener jokeListener) {
    this.jokeListener = jokeListener;
  }
  
  @Override
  protected Joke doInBackground(Void... voids) {
    if (jokeApiService == null) {
      JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
          new AndroidJsonFactory(), null)
          // options for running against local devappserver
          // - 10.0.2.2 is localhost's IP address in Android emulator
          // - turn off compression when running against local devappserver
          .setRootUrl("http://192.168.1.102:8080/_ah/api/")
          .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
            @Override
            public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException {
              abstractGoogleClientRequest.setDisableGZipContent(true);
            }
          });
      // end options for devappserver
  
      jokeApiService = builder.build();
    }
    
    try {
      return Joke.getInstance(jokeApiService.getJoke().execute().getResponseData());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
  
  @Override
  protected void onPostExecute(Joke joke) {
    if (this.jokeListener != null) {
      this.jokeListener.jokeReceived(joke);
    }
  }
}
