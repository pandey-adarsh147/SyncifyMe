package me.syncify.syncifyme.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by adarshpandey on 9/14/15.
 */
public class GsonUtils {

    private static GsonUtils gsonUtils;

    private Gson gson = null;

    private GsonUtils() {
        gson = new GsonBuilder().create();
    }

    public static synchronized GsonUtils getInstance() {
        if (gsonUtils == null) {
            gsonUtils = new GsonUtils();
        }

        return gsonUtils;
    }

    public Gson getGson() {
        return gson;
    }
}
