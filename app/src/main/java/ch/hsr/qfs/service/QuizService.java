package ch.hsr.qfs.service;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpController;
import ch.hsr.qfs.service.apiclient.ApiHttpRequest;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizService {

    private static QuizService quizService;
    private String tagJsonObj = "json_obj_req";
    private String quizUrl = "http://10.0.2.2:3000/api/quiz";

    private QuizService() {
    }

    public static QuizService getInstance()
    {
        if (quizService == null)
        {
            quizService = new QuizService();
        }
        return quizService;
    }

    public void create(final String challengerId, final String opponentId, final ApiHttpCallback<ApiHttpResponse<Quiz>> callback) {
        String url = quizUrl + "/";

        Type type = new TypeToken<ApiHttpResponse<Quiz>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<Quiz>> request = new ApiHttpRequest<ApiHttpResponse<Quiz>>(Request.Method.POST, url, type, null,
                new Response.Listener<ApiHttpResponse<Quiz>>()
                {
                    public void onResponse(ApiHttpResponse<Quiz> response) {
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
                params.put("challengerId", challengerId);
                params.put("opponentId", opponentId);

                return params;
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }

    public void getOpen(final String userId, final ApiHttpCallback<ApiHttpResponse<ArrayList<Quiz>>> callback) {
        String url = quizUrl + "/open/" + userId;

        Type type = new TypeToken<ApiHttpResponse<ArrayList<Quiz>>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<ArrayList<Quiz>>> request = new ApiHttpRequest<ApiHttpResponse<ArrayList<Quiz>>>(Request.Method.GET, url, type, null,
                new Response.Listener<ApiHttpResponse<ArrayList<Quiz>>>()
                {
                    public void onResponse(ApiHttpResponse<ArrayList<Quiz>> response) {
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

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }
}
