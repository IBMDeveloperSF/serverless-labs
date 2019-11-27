package com.ibmdevelopersf.serverless_lab1;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;

import com.google.gson.JsonObject;

import org.hamcrest.core.IsEqual;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ServerlessLab1Test {

    @Test
    public void testMainFunctionDoesNotReturnNull() {
        JsonObject args = new JsonObject();
        JsonObject response = ServerlessLab1.main(args);
        assertNotNull(response);
    }


    @Test
    public void testMainFunctionReturnsJsonObject() {
        JsonObject args = new JsonObject();
        JsonObject response = ServerlessLab1.main(args);
        assertThat(response, instanceOf(JsonObject.class));
    }

    @Test
    public void testMainFunctionReturnsHelloWorld() {
        JsonObject args = new JsonObject();
        final String greeting = "Hello World!";
        JsonObject response = ServerlessLab1.main(args);
        assertThat(response.get("greeting"), is(notNullValue()));
        assertThat(response.get("greeting").getAsString(), is(equalTo(greeting)));
    }

    @Test
    public void testMainFunctionReturnsGreetingsWithName() {
        JsonObject args = new JsonObject();
        final String name = "Upkar Lidder";
        args.addProperty("name", name);
        JsonObject response = ServerlessLab1.main(args);
        assertThat(response.get("greeting"), is(notNullValue()));
        assertThat(response.get("greeting").getAsString(), is(equalTo("Hello "
                + name)));
    }
}
