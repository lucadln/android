package com.ardeleanlucian.systemapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ardelean on 12/28/17.
 */

public class HttpClient {

    Context context;
    String url;

    /**
     * Constructor method
     * @param context
     */
    public HttpClient(Context context) {
        this.context = context;
    }

    public void sendPostRequest() {

        RequestQueue queue = Volley.newRequestQueue(context);
        url = "http://192.168.1.5/test_post/post_action.php";
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {
                        // response
                        Log.d("Response", response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("Response", "There has been a problem in receiving the response!");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("id", "1");
                params.put("name", "test");

                return params;
            }
        };
        queue.add(postRequest);
    }
}
