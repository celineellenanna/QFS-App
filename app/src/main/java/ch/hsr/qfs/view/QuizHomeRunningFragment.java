package ch.hsr.qfs.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizHomeRunningFragment extends Fragment {

    private QuizService quizService = QuizService.getInstance();
    private AuthService authService = AuthService.getInstance();
    private SwipeRefreshLayout swipeRefreshLayout;

    private View viewRoot;

    public QuizHomeRunningFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewRoot = inflater.inflate(R.layout.fragment_quiz_home_running, container, false);

        swipeRefreshLayout = (SwipeRefreshLayout) viewRoot.findViewById(R.id.swipeRefreshLayout);

        loadData();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });

        return viewRoot;
    }

    public void loadData() {
        quizService.getRunning(authService.getUser().getId(), new ApiHttpCallback<ApiHttpResponse<ArrayList<Quiz>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<Quiz>> response) {
                if (response.getSuccess()) {
                    QuizHomeRunningAdapter adapter = new QuizHomeRunningAdapter(response.getData());
                    RecyclerView recyclerView = (RecyclerView) viewRoot.findViewById(R.id.recyclerView);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                    swipeRefreshLayout.setRefreshing(false);
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });
    }
}
