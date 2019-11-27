package com.ibmdevelopersf.serverless_lab1;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.logging.Logger;

public class ServerlessLab1 {

    protected static final Logger logger = Logger.getLogger("ServerlessLab1");

    public static JsonObject main(JsonObject args) {
        JsonObject response = new JsonObject();
        JsonPrimitive nameArg = args.getAsJsonPrimitive("name");
        String result;
        if (nameArg == null) {
            result = "Hello World!";
        } else {
            result = "Hello " + nameArg.getAsString();
        }
        response.addProperty("greeting", result);

        logger.info("exiting ServerlessLab1");
        return response;
    }
}
