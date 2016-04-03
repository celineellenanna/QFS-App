package com.example.pewpew.qfs.service;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.pewpew.qfs.HomeFragment;
import com.example.pewpew.qfs.MainActivity;
import com.example.pewpew.qfs.UserFragment;
import com.example.pewpew.qfs.domain.DefaultResponse;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private String tagJsonObj = "json_obj_req";
    private String authUrl = "http://10.0.2.2:3000/api/auth";
    private Context context;
    private View view;

    public AuthService(Context context, View view) {
        this.context = context;
        this.view = view;
    }

    public void login(final String username, final String password) {
        String url = authUrl + "/login";

        GsonRequest<DefaultResponse> loginRequest = new GsonRequest<DefaultResponse>( Request.Method.POST, url, DefaultResponse.class,
                new Response.Listener<DefaultResponse>()
                {
                    public void onResponse(DefaultResponse defaultResponse) {
                        if(!defaultResponse.getStatus()) {
                            Toast.makeText(context, defaultResponse.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            ((MainActivity) context).changeFragment(new UserFragment());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("QFS - Error", "Auth Login Error");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(loginRequest, tagJsonObj);
    }

    public void register(final String firstname, final String lastname, final String email, final String username, final String password) {
        String url = authUrl + "/register";

        GsonRequest<DefaultResponse> loginRequest = new GsonRequest<DefaultResponse>( Request.Method.POST, url, DefaultResponse.class,
                new Response.Listener<DefaultResponse>()
                {
                    public void onResponse(DefaultResponse response) {
                        if(!response.getStatus()) {
                            Toast.makeText(context, response.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            ((MainActivity) context).changeFragment(new HomeFragment());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("QFS - Error", "Auth Register Error");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("fistname", firstname);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(loginRequest, tagJsonObj);
    }

    public void isLoggedIn() {
        String url = authUrl + "/isLoggedIn";

        GsonRequest<DefaultResponse> loginRequest = new GsonRequest<DefaultResponse>( Request.Method.GET, url, DefaultResponse.class,
                new Response.Listener<DefaultResponse>()
                {
                    public void onResponse(DefaultResponse response) {
                        if(!response.getStatus()) {
                            Toast.makeText(context, response.getMessage(), Toast.LENGTH_LONG).show();
                        } else {
                            ((MainActivity) context).changeFragment(new HomeFragment());
                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("QFS - Error", "Auth Register Error");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                return params;
            }
        };

        AppController.getInstance().addToRequestQueue(loginRequest, tagJsonObj);
    }
}
