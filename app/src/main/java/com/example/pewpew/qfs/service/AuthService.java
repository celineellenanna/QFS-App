package com.example.pewpew.qfs.service;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.example.pewpew.qfs.domain.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthService {

    private static AuthService authService;
    private String tagJsonObj = "json_obj_req";
    private String authUrl = "http://10.0.2.2:3000/api/auth";
    private static User currUser;

    private AuthService() {
    }

    public static AuthService getInstance()
    {
        if (authService == null)
        {
            authService = new AuthService();
        }
        return authService;
    }

    public void login(final String username, final String password, final ApiCallback<ApiHttpResponse> callback) {
        String url = authUrl + "/login";

        GsonRequest<ApiHttpResponse> loginRequest = new GsonRequest<ApiHttpResponse>(Request.Method.POST, url, ApiHttpResponse.class,
                new Response.Listener<ApiHttpResponse>()
                {
                    public void onResponse(ApiHttpResponse response) {
                        if(response.getStatus()) {
                            currUser = response.getUser();
                        }

                        callback.onCompletion(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.toString());
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

    public void register(final String firstname, final String lastname, final String email, final String username, final String password, final ApiCallback<ApiHttpResponse> callback) {
        String url = authUrl + "/register";

        GsonRequest<ApiHttpResponse> registerRequest = new GsonRequest<ApiHttpResponse>(Request.Method.POST, url, ApiHttpResponse.class,
                new Response.Listener<ApiHttpResponse>()
                {
                    public void onResponse(ApiHttpResponse response) {
                        callback.onCompletion(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.toString());
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

        AppController.getInstance().addToRequestQueue(registerRequest, tagJsonObj);
    }

    public Boolean isLoggedIn() {
        return this.currUser != null;
    }

    public void logout(final ApiCallback<ApiHttpResponse> callback) {

        String url = authUrl + "/logout";

        GsonRequest<ApiHttpResponse> logoutRequest = new GsonRequest<ApiHttpResponse>(Request.Method.GET, url, ApiHttpResponse.class,
                new Response.Listener<ApiHttpResponse>()
                {
                    public void onResponse(ApiHttpResponse response) {
                        currUser = null;
                        callback.onCompletion(response);
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        callback.onError(error.toString());
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

        AppController.getInstance().addToRequestQueue(logoutRequest, tagJsonObj);
    }

    public void getUser() {

        String url = authUrl + "/logout";

        GsonRequest<ApiHttpGenericResponse<String>> logoutRequest = new GsonRequest<ApiHttpGenericResponse<String>>(Request.Method.GET, url, ApiHttpGenericResponse.class,
                new Response.Listener<ApiHttpGenericResponse<String>>()
                {
                    public void onResponse(ApiHttpGenericResponse<String> response) {
                        Log.d("QFS - Status ", response.getStatus().toString());
                        Log.d("QFS - Message ", response.getMessage());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("QFS - Error", error.toString());
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

        AppController.getInstance().addToRequestQueue(logoutRequest, tagJsonObj);
    }
}
