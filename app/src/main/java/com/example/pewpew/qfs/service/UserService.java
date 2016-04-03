package com.example.pewpew.qfs.service;

import android.content.Context;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.pewpew.qfs.domain.User;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class UserService {

    private String tagJsonObj = "json_obj_req";
    private String authUrl = "http://10.0.2.2:3000/api/users";
    private Context context;
    private View view;

    public UserService(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public void index(final ApiCallback<ArrayList<User>> callback) {
        String url = authUrl + "/";

        Type type = new TypeToken<ArrayList<User>>() {}.getType();

        GsonRequest<ArrayList<User>> loginRequest = new GsonRequest<ArrayList<User>>(Request.Method.GET, url, type,
                new Response.Listener<ArrayList<User>>()
                {
                    public void onResponse(ArrayList<User> users) {
                        callback.onCompletion(users);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.toString());
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(loginRequest, tagJsonObj);
    }
}
