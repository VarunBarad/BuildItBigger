package com.varunbarad.builditbigger.app;

import com.varunbarad.builditbigger.javajokeslib.model.Joke;

/**
 * Creator: Varun Barad
 * Date: 28-11-2017
 * Project: BuildItBigger
 */
public interface JokeListener {
  void jokeReceived(Joke joke);
}
