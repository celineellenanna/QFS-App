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

import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.UserService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizOpponentFragment extends Fragment {


    public QuizOpponentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_quiz_opponent, container, false);

        UserService us = new UserService().getInstance();
        AuthService as = new AuthService().getInstance();

        us.findOpponent(as.getUser().getId(), new ApiHttpCallback<ApiHttpResponse<ArrayList<User>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<User>> response) {
                if (response.getSuccess()) {
                    QuizOpponentAdapter userAdapter = new QuizOpponentAdapter(response.getData());
                    RecyclerView userRecycleView = (RecyclerView) viewRoot.findViewById(R.id.recyclerView);
                    LinearLayoutManager linearLayoutManagerUser = new LinearLayoutManager(getContext());
                    userRecycleView.setLayoutManager(linearLayoutManagerUser);
                    userRecycleView.setAdapter(userAdapter);
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
