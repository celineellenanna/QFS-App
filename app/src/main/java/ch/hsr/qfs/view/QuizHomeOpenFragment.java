package ch.hsr.qfs.view;


import android.os.Bundle;
import android.app.Fragment;
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

public class QuizHomeOpenFragment extends Fragment {


    public QuizHomeOpenFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_quiz_home_open, container, false);

        QuizService qs = QuizService.getInstance();
        AuthService as = AuthService.getInstance();

        qs.getRequests(as.getUser().getId(), new ApiHttpCallback<ApiHttpResponse<ArrayList<Quiz>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<Quiz>> response) {
                if (response.getSuccess()) {
                    QuizHomeOpenAdapter adapter = new QuizHomeOpenAdapter(response.getData());
                    RecyclerView recyclerView = (RecyclerView) viewRoot.findViewById(R.id.recyclerView);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });

        return viewRoot;
    }

}
