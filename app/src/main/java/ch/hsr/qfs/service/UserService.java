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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserService {

    private static UserService userService;
    private String tagJsonObj = "json_obj_req";
    private String userUrl = "http://10.0.2.2:3000/api/users";

    public UserService() {
    }

    public static UserService getInstance()
    {
        if (userService == null)
        {
            userService = new UserService();
        }
        return userService;
    }

    public void index(final ApiHttpCallback<ApiHttpResponse<ArrayList<User>>> callback) {
        String url = userUrl + "/";

        Type type = new TypeToken<ApiHttpResponse<ArrayList<User>>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<ArrayList<User>>> request = new ApiHttpRequest<ApiHttpResponse<ArrayList<User>>>(Request.Method.GET, url, type, null,
                new Response.Listener<ApiHttpResponse<ArrayList<User>>>()
                {
                    public void onResponse(ApiHttpResponse<ArrayList<User>> response) {
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

                return new HashMap<String, String>();
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }

    public void get(final String userId, final ApiHttpCallback<ApiHttpResponse<User>> callback) {
        String url = userUrl + "/findOpponent/" + userId;

        Type type = new TypeToken<ApiHttpResponse<ArrayList<User>>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<User>> request = new ApiHttpRequest<ApiHttpResponse<User>>(Request.Method.GET, url, type, null,
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
                return new HashMap<String, String>();
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }

    public void findOpponent(final String userId, final ApiHttpCallback<ApiHttpResponse<ArrayList<User>>> callback) {
        String url = userUrl + "/findOpponent/" + userId;

        Type type = new TypeToken<ApiHttpResponse<ArrayList<User>>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<ArrayList<User>>> request = new ApiHttpRequest<ApiHttpResponse<ArrayList<User>>>(Request.Method.GET, url, type, null,
                new Response.Listener<ApiHttpResponse<ArrayList<User>>>()
                {
                    public void onResponse(ApiHttpResponse<ArrayList<User>> response) {
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
                return new HashMap<String, String>();
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }

}
