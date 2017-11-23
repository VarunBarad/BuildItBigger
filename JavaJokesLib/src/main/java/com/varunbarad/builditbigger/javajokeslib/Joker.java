package com.varunbarad.builditbigger.javajokeslib;

import com.google.gson.Gson;
import com.varunbarad.builditbigger.javajokeslib.model.Joke;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public final class Joker {
  /**
   * Path to jokes file stored in the resources
   */
  private static final String PATH_JOKES = "/jokes.json";
  
  /**
   * List of jokes retrieved from the file
   */
  private ArrayList<Joke> jokes;
  
  /**
   * Get a new instance of {@link Joker} containing jokes stored in resources
   */
  private Joker() {
    this.jokes = Joker.extractJokesFromFile(Joker.class.getResourceAsStream(PATH_JOKES));
  }
  
  /**
   * Get a new instance of {@link Joker} containing jokes stored in resources
   *
   * @return A new instance of {@link Joker}
   */
  public static Joker getInstance() {
    return new Joker();
  }
  
  /**
   * Read jokesJson from provided stream and convert the json to list of {@link Joke}.
   *
   * @param jokesFileStream The input-stream from which json of jokes will be read.
   * @return List of jokes deserialized from json read from provided stream.
   */
  private static ArrayList<Joke> extractJokesFromFile(InputStream jokesFileStream) {
    ArrayList<Joke> jokes;
    
    String jokesJson;
    
    if (jokesFileStream != null) {
      Scanner jokesScanner = new Scanner(jokesFileStream, "UTF-8").useDelimiter("\\A");
      
      if (jokesScanner.hasNext()) {
        jokesJson = jokesScanner.next();
      } else {
        jokesJson = "";
      }
      
      jokesScanner.close();
    } else {
      jokesJson = "";
    }
    
    Gson gson = new Gson();
    
    jokes = new ArrayList<>(Arrays.asList(gson.fromJson(jokesJson, Joke[].class)));
    
    return jokes;
  }
  
  /**
   * Get whether this Joker has any more jokes with him or not.
   *
   * @return A boolean stating whether this Joker has any more jokes or not.
   */
  public boolean hasMoreJokes() {
    if (this.jokes != null) {
      return !this.jokes.isEmpty();
    } else {
      return false;
    }
  }
  
  /**
   * Get a {@link Joke} from this Joker.
   *
   * @return A random {@link Joke} from the jokes this Joker has.
   */
  public Joke getRandomJoke() {
    if (this.hasMoreJokes()) {
      Random random = new Random();
      int jokePosition = random.nextInt(this.jokes.size());
      
      return this.jokes.remove(jokePosition);
    } else {
      return null;
    }
  }
}
