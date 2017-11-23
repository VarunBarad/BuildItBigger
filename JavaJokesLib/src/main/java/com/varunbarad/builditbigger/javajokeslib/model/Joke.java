package com.varunbarad.builditbigger.javajokeslib.model;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Locale;

/**
 * Creator: Varun Barad
 * Date: 22-11-2017
 * Project: BuildItBigger
 */
public final class Joke {
  @Expose
  @SerializedName("id")
  private long id;
  @Expose
  @SerializedName("type")
  private String type;
  @Expose
  @SerializedName("setup")
  private String setup;
  @Expose
  @SerializedName("punchline")
  private String punchline;
  
  /**
   * Get a new Joke instance having the specified properties.
   *
   * @param id        The id for the joke
   * @param type      Type under which the joke falls
   * @param setup     The setup line for the joke
   * @param punchline The punchline of the joke
   */
  public Joke(long id, String type, String setup, String punchline) {
    this.id = id;
    this.type = type;
    this.setup = setup;
    this.punchline = punchline;
  }
  
  /**
   * Get an instance of Joke from it's Json representation
   *
   * @param jokeJson Json serialized representation of {@link Joke}
   * @return A Joke instance deserialized from jokeJson
   */
  public static Joke getInstance(String jokeJson) {
    return (new Gson()).fromJson(jokeJson, Joke.class);
  }
  
  /**
   * Get the id of this joke
   *
   * @return The id of this joke
   */
  public long getId() {
    return id;
  }
  
  /**
   * Get the type of this joke
   *
   * @return The type of this joke
   */
  public String getType() {
    return type;
  }
  
  /**
   * Get the setup line of this joke
   *
   * @return The setup line of this joke
   */
  public String getSetup() {
    return setup;
  }
  
  /**
   * Get the punchline of the joke
   *
   * @return The punchline of this joke
   */
  public String getPunchline() {
    return punchline;
  }
  
  /**
   * Get both the setup and punchline as a whole joke
   *
   * @return Whole joke containing both setup and punchline
   */
  @Override
  public String toString() {
    return String.format(Locale.getDefault(), "%s\n%s", this.getSetup(), this.getPunchline());
  }
  
  /**
   * Get json serialized version of this joke
   *
   * @return Json serialized version of this joke
   */
  public String toJson() {
    return (new Gson()).toJson(this);
  }
}
