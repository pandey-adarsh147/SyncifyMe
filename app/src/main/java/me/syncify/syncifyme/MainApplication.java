package me.syncify.syncifyme;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by adarshpandey on 9/14/15.
 */
public class MainApplication extends Application {

    private RequestQueue requestQueue;

    private static MainApplication mainApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        requestQueue = Volley.newRequestQueue(this);

        mainApplication = this;
    }

    public static MainApplication getInstance() {
        return mainApplication;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }
}
