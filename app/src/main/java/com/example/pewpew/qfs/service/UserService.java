package com.example.pewpew.qfs.service;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.pewpew.qfs.R;
import com.example.pewpew.qfs.UserAdapter;
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

    public void index() {
        String url = authUrl + "/";

        Type type = new TypeToken<ArrayList<User>>() {}.getType();

        GsonRequest<ArrayList<User>> loginRequest = new GsonRequest<ArrayList<User>>(Request.Method.GET, url, type,
                new Response.Listener<ArrayList<User>>()
                {
                    public void onResponse(ArrayList<User> users) {
                        UserAdapter userAdapter = new UserAdapter(users);
                        RecyclerView userRecycleView = (RecyclerView) view.findViewById(R.id.userRecyclerView);
                        LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(context);
                        userRecycleView.setLayoutManager(linearLayoutManagerUser);
                        userRecycleView.setAdapter(userAdapter);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("QFS - Error", "User Index Error");
                    }
                }
        );

        AppController.getInstance().addToRequestQueue(loginRequest, tagJsonObj);
    }
}
