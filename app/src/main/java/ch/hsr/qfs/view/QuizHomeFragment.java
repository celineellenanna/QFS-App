package ch.hsr.qfs.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.hsr.pewpew.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.UserService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizHomeFragment extends Fragment {


    public QuizHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_quiz_home, container, false);

        Button btnExecute = (Button) viewRoot.findViewById(R.id.btnExecute);

        btnExecute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthService authService = AuthService.getInstance();
                QuizService quizService = QuizService.getInstance();
                UserService userService = UserService.getInstance();

                userService.findOpponent(authService.getUser().getId(), new ApiHttpCallback<ApiHttpResponse<ArrayList<User>>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<ArrayList<User>> response) {
                        Log.d("QFS-User", "findOpponent successfully");
                    }

                    @Override
                    public void onError(String message) {
                        Log.d("QFS-User", message);
                    }
                });

                quizService.create(authService.getUser().getId(), authService.getUser().getId(), new ApiHttpCallback<ApiHttpResponse<Quiz>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<Quiz> response) {
                        Log.d("QFS-QUIZ", "create successfully");
                    }

                    @Override
                    public void onError(String message) {
                        Log.d("QFS-QUIZ", message);
                    }
                });
            }
        });

        FloatingActionButton fab = (FloatingActionButton) viewRoot.findViewById(R.id.fab);
        fab.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return viewRoot;
    }

}
