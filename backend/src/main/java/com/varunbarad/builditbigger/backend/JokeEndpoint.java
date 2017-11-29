/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.varunbarad.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.varunbarad.builditbigger.javajokeslib.Joker;
import com.varunbarad.builditbigger.javajokeslib.model.Joke;

/** An endpoint class we are exposing */
@Api(
        name = "jokeApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.varunbarad.com",
                ownerName = "backend.builditbigger.varunbarad.com",
                packagePath = ""
        )
)
public class JokeEndpoint {
    @ApiMethod(name = "getJoke")
    public ResponseWrapper getJoke() {
        Joke joke = Joker.getInstance().getRandomJoke();
        String jokeJson;
        if (joke != null) {
            jokeJson = joke.toJson();
        } else {
            jokeJson = "";
        }
        ResponseWrapper response = new ResponseWrapper();
        response.setResponseData(jokeJson);
        return response;
    }
}
