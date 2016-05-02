package ch.hsr.qfs.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Category;
import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizStatisticFragment extends Fragment {

    private QuizService qs = QuizService.getInstance();
    private String quizId;
    Button btnPlay;
    TextView tvUsername1;
    TextView tvUsername2;

    public QuizStatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_quiz_statistic, container, false);
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");
        tvUsername1 = (TextView) viewRoot.findViewById(R.id.tvUsername1);
        tvUsername2 = (TextView) viewRoot.findViewById(R.id.tvUsername2);

        qs.get(quizId, new ApiHttpCallback<ApiHttpResponse<Quiz>>() {
            @Override
            public void onCompletion(ApiHttpResponse<Quiz> response) {
                if (response.getSuccess()) {
                    Quiz quiz = response.getData();
                    User challenger = quiz.get_challengerId();
                    User opponent = quiz.get_opponentId();
                    tvUsername1.setText(challenger.getUsername());
                    tvUsername2.setText(opponent.getUsername());
                    Log.d("QFS", "QuizID: " + quizId);



                } else {
                    Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });

        btnPlay = (Button) viewRoot.findViewById(R.id.btn_statisticPlay);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("quizId", quizId);
                QuizCategoryFragment f = new QuizCategoryFragment();
                f.setArguments(bundle);
                ((MainActivity) getActivity()).changeFragment(f);
            }
        });

        return viewRoot;
    }


}
