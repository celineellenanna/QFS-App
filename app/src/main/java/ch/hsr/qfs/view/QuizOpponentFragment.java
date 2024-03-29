package ch.hsr.qfs.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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

    private UserService userService = UserService.getInstance();
    private AuthService authService = AuthService.getInstance();

    public QuizOpponentFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_quiz_opponent, container, false);

        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        userService.findOpponent(authService.getUser().getId(), new ApiHttpCallback<ApiHttpResponse<ArrayList<User>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<User>> response) {
                if (response.getSuccess()) {
                    QuizOpponentAdapter adapter = new QuizOpponentAdapter(response.getData());
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
