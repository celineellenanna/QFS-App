package com.example.pewpew.qfs.service;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.example.pewpew.qfs.domain.User;

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

    public void login(final String username, final String password, final ApiHttpCallback<User> callback) {
        String url = authUrl + "/login";

        ApiHttpRequest<User> loginRequest = new ApiHttpRequest<User>(Request.Method.POST, url, User.class, null,
                new Response.Listener<User>()
                {
                    public void onResponse(User user) {

                        if(user.getStatus() != "activated") {
                            currUser = null;
                        } else {
                            currUser = user;
                        }

                        callback.onCompletion(currUser);
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

        ApiHttpController.getInstance().addToRequestQueue(loginRequest, tagJsonObj);
    }

    public void register(final String firstname, final String lastname, final String email, final String username, final String password, final ApiHttpCallback<ApiHttpResponse> callback) {
        String url = authUrl + "/register";

        ApiHttpRequest<ApiHttpResponse> registerRequest = new ApiHttpRequest<ApiHttpResponse>(Request.Method.POST, url, ApiHttpResponse.class, null,
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

        ApiHttpController.getInstance().addToRequestQueue(registerRequest, tagJsonObj);
    }

    public Boolean isLoggedIn() {
        return this.currUser != null;
    }

    public void logout(final ApiHttpCallback<ApiHttpResponse> callback) {

        String url = authUrl + "/logout";

        ApiHttpRequest<ApiHttpResponse> logoutRequest = new ApiHttpRequest<ApiHttpResponse>(Request.Method.GET, url, ApiHttpResponse.class, null,
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

        ApiHttpController.getInstance().addToRequestQueue(logoutRequest, tagJsonObj);
    }
}
