package ch.hsr.qfs.service;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpController;
import ch.hsr.qfs.service.apiclient.ApiHttpRequest;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    public void login(final String username, final String password, final ApiHttpCallback<ApiHttpResponse<User>> callback) {
        String url = authUrl + "/login";

        Type type = new TypeToken<ApiHttpResponse<User>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<User>> loginRequest = new ApiHttpRequest<ApiHttpResponse<User>>(Request.Method.POST, url, type, null,
                new Response.Listener<ApiHttpResponse<User>>()
                {
                    public void onResponse(ApiHttpResponse<User> response) {
                        if(response.getSuccess()) {
                            currUser = response.getData();
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

        ApiHttpController.getInstance().addToRequestQueue(loginRequest, tagJsonObj);
    }

    public void register(final String firstname, final String lastname, final String email, final String username, final String password, final ApiHttpCallback<ApiHttpResponse<User>> callback) {
        String url = authUrl + "/register";

        Type type = new TypeToken<ApiHttpResponse<User>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<User>> registerRequest = new ApiHttpRequest<ApiHttpResponse<User>>(Request.Method.POST, url, type, null,
                new Response.Listener<ApiHttpResponse<User>>()
                {
                    public void onResponse(ApiHttpResponse<User> response) {
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
                params.put("firstname", firstname);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("username", username);
                params.put("password", password);

                return params;
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(registerRequest, tagJsonObj);
    }

    public Boolean isAuthenticated() {
        return this.currUser != null;
    }

    public void logout(final ApiHttpCallback<ApiHttpResponse<User>> callback) {
        String url = authUrl + "/logout";

        Type type = new TypeToken<ApiHttpResponse<User>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<User>> logoutRequest = new ApiHttpRequest<ApiHttpResponse<User>>(Request.Method.GET, url, type, null,
                new Response.Listener<ApiHttpResponse<User>>()
                {
                    public void onResponse(ApiHttpResponse<User> response) {
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
