package me.syncify.syncifyme.network;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by adarshpandey on 9/14/15.
 */
public class GsonRequest<T> extends Request<T> {
    private final Class<T>              clazz;
    private final Map<String, String> headers;
    private final Response.Listener<T>  listener;
    private final Map<String, String>   paramMap;

    /**
     * Make a GET request and return a parsed object from JSON.
     *
     * @param url     URL of the request to make
     * @param clazz   Relevant class object, for Gson's reflection
     * @param headers Map of request headers
     */
    public GsonRequest(int method, String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener, Map<String, String> paramMap) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.paramMap = paramMap;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        if (paramMap != null) {
            return paramMap;
        }
        return super.getParams();
    }


    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(
                    GsonUtils.getInstance().getGson().fromJson(json, clazz),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            Log.e(GsonRequest.class.getName(), e.getMessage(), e);
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T t) {
        if (listener !=null) {
            listener.onResponse(t);
        }
    }
}
