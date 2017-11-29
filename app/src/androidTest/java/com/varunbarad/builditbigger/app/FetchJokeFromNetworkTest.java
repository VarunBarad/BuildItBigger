package com.varunbarad.builditbigger.app;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.matcher.IntentMatchers;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.core.IsNot;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.Intents.intending;
import static android.support.test.espresso.intent.matcher.IntentMatchers.isInternal;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Creator: Varun Barad
 * Date: 29-11-2017
 * Project: BuildItBigger
 */
@RunWith(AndroidJUnit4.class)
public class FetchJokeFromNetworkTest {
  @Rule
  public IntentsTestRule<MainActivity> activityTestRule = new IntentsTestRule<>(MainActivity.class);
  
  private IdlingResource idlingResource;
  
  @Before
  public void registerIdlingResource() {
    intending(IsNot.not(isInternal())).respondWith(new Instrumentation.ActivityResult(Activity.RESULT_OK, null));
    
    this.idlingResource = this.activityTestRule.getActivity().getIdlingResource();
    IdlingRegistry.getInstance().register(this.idlingResource);
  }
  
  @Test
  public void fetchJokeFromNetworkTest() {
    onView(withId(R.id.button_tellJoke))
        .perform(click());
    
    intended(IntentMatchers.hasExtra(equalTo("key_joke"), not(isEmptyOrNullString())));
  }
  
  @After
  public void unregisterIdlingResource() {
    if (this.idlingResource != null) {
      IdlingRegistry.getInstance().unregister(this.idlingResource);
    }
  }
}