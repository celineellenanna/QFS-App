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

    Button btn_statisticQ1;
    Button btn_statisticQ2;
    Button btn_statisticQ3;
    Button btn_statisticQ4;
    Button btn_statisticQ5;
    Button btn_statisticQ6;
    Button btn_statisticQ7;
    Button btn_statisticQ8;
    Button btn_statisticQ9;
    Button btn_statisticQ10;
    Button btn_statisticQ11;
    Button btn_statisticQ12;

    public QuizStatisticFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_quiz_statistic, container, false);
        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");
        tvUsername1 = (TextView) viewRoot.findViewById(R.id.tvUsername1);
        tvUsername2 = (TextView) viewRoot.findViewById(R.id.tvUsername2);

        btn_statisticQ1 = (Button) viewRoot.findViewById(R.id.btn_statisticQ1);
        btn_statisticQ2 = (Button) viewRoot.findViewById(R.id.btn_statisticQ2);
        btn_statisticQ3 = (Button) viewRoot.findViewById(R.id.btn_statisticQ3);
        btn_statisticQ4 = (Button) viewRoot.findViewById(R.id.btn_statisticQ4);
        btn_statisticQ5 = (Button) viewRoot.findViewById(R.id.btn_statisticQ5);
        btn_statisticQ6 = (Button) viewRoot.findViewById(R.id.btn_statisticQ6);
        btn_statisticQ7 = (Button) viewRoot.findViewById(R.id.btn_statisticQ7);
        btn_statisticQ8 = (Button) viewRoot.findViewById(R.id.btn_statisticQ8);
        btn_statisticQ9 = (Button) viewRoot.findViewById(R.id.btn_statisticQ9);
        btn_statisticQ10 = (Button) viewRoot.findViewById(R.id.btn_statisticQ10);
        btn_statisticQ11 = (Button) viewRoot.findViewById(R.id.btn_statisticQ11);
        btn_statisticQ12 = (Button) viewRoot.findViewById(R.id.btn_statisticQ12);

        qs.get(quizId, new ApiHttpCallback<ApiHttpResponse<Quiz>>() {
            @Override
            public void onCompletion(ApiHttpResponse<Quiz> response) {
                if (response.getSuccess()) {
                    Quiz quiz = response.getData();
                    User challenger = quiz.get_challenger();
                    User opponent = quiz.get_opponent();
                    tvUsername1.setText(challenger.getUsername());
                    tvUsername2.setText(opponent.getUsername());
                    Log.d("QFS", "QuizID: " + quizId);

                   // if (quiz.get)


                } else {
                    Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });

        btnPlay = (Button) viewRoot.findViewById(R.id.btn_Play);
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
