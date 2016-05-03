package ch.hsr.qfs.service;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.domain.Category;
import ch.hsr.qfs.domain.Question;
import ch.hsr.qfs.domain.Round;
import ch.hsr.qfs.domain.RoundQuestion;
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

public void get(final String quizId, final ApiHttpCallback<ApiHttpResponse<Quiz>> callback){
        String url = quizUrl + "/" + quizId;

        Type type = new TypeToken<ApiHttpResponse<Quiz>>() {}.getType();


        ApiHttpRequest<ApiHttpResponse<Quiz>> request = new ApiHttpRequest<ApiHttpResponse<Quiz>>(Request.Method.GET, url, type, null,
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

                return params;
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);



    }

    public void accept(final String quizId, final ApiHttpCallback<ApiHttpResponse> callback) {
        String url = quizUrl + "/accept";

        Type type = new TypeToken<ApiHttpResponse<Quiz>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse> request = new ApiHttpRequest<ApiHttpResponse>(Request.Method.POST, url, type, null,
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
                params.put("id", quizId);

                return params;
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }

    public void reject(final String quizId, final ApiHttpCallback<ApiHttpResponse> callback) {
        String url = quizUrl + "/reject";

        Type type = new TypeToken<ApiHttpResponse<Quiz>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse> request = new ApiHttpRequest<ApiHttpResponse>(Request.Method.POST, url, type, null,
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
                params.put("id", quizId);

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

    public void getRunning(final String userId, final ApiHttpCallback<ApiHttpResponse<ArrayList<Quiz>>> callback) {
        String url = quizUrl + "/running/" + userId;

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

    public void getFinished(final String userId, final ApiHttpCallback<ApiHttpResponse<ArrayList<Quiz>>> callback) {
        String url = quizUrl + "/finished/" + userId;

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

    public void getCategories(final ApiHttpCallback<ApiHttpResponse<ArrayList<Category>>> callback){
        String url = quizUrl + "/category";

        Type type = new TypeToken<ApiHttpResponse<ArrayList<Category>>>() {}.getType();


        ApiHttpRequest<ApiHttpResponse<ArrayList<Category>>> request = new ApiHttpRequest<ApiHttpResponse<ArrayList<Category>>>(Request.Method.GET, url, type, null,
                new Response.Listener<ApiHttpResponse<ArrayList<Category>>>()
                {
                    public void onResponse(ApiHttpResponse<ArrayList<Category>> response) {
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

    public void getRound(final String roundId, final ApiHttpCallback<ApiHttpResponse<Round>> callback){
        String url = quizUrl + "/round/" + roundId;

        Type type = new TypeToken<ApiHttpResponse<Round>>() {}.getType();


        ApiHttpRequest<ApiHttpResponse<Round>> request = new ApiHttpRequest<ApiHttpResponse<Round>>(Request.Method.GET, url, type, null,
                new Response.Listener<ApiHttpResponse<Round>>()
                {
                    public void onResponse(ApiHttpResponse<Round> response) {
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

    public void createRound(final String quizId, final String categoryId, final ApiHttpCallback<ApiHttpResponse<Round>> callback) {
        String url = quizUrl + "/round/";

        Type type = new TypeToken<ApiHttpResponse<Round>>() {}.getType();

        ApiHttpRequest<ApiHttpResponse<Round>> request = new ApiHttpRequest<ApiHttpResponse<Round>>(Request.Method.POST, url, type, null,
                new Response.Listener<ApiHttpResponse<Round>>()
                {
                    public void onResponse(ApiHttpResponse<Round> response) {
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
                params.put("quizId", quizId);
                params.put("categoryId", categoryId);

                return params;
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }

    public void createUserAnswer(final String roundQuestionId, final String answerId, final String userId, final int timeToAnswer, final ApiHttpCallback<ApiHttpResponse> callback) {
        String url = quizUrl + "/round/answer";

        Type type = new TypeToken<ApiHttpResponse>() {}.getType();

        ApiHttpRequest<ApiHttpResponse> request = new ApiHttpRequest<ApiHttpResponse>(Request.Method.POST, url, type, null,
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
                params.put("roundQuestionId", roundQuestionId);
                params.put("answerId", answerId);
                params.put("userId", userId);
                params.put("timeToAnswer", "" + timeToAnswer + "");

                return params;
            }
        };

        ApiHttpController.getInstance().addToRequestQueue(request, tagJsonObj);
    }

}
