package com.example.pewpew.qfs.service;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    String tagJsonObj = "json_obj_req";
    String authUrl = "http://10.0.2.2:3000/api/auth";

    public void login(final String username, final String password) {
        String url = authUrl + "/login";
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (com.android.volley.Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                    }

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("QFS", error.toString());
                    }

                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(jsObjRequest, tagJsonObj);
    }
}
