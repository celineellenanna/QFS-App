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

import ch.hsr.qfs.domain.Category;
import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.domain.Round;
import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizStatisticFragment extends Fragment {

    private QuizService quizService = QuizService.getInstance();
    private AuthService authService = AuthService.getInstance();

    private View viewRoot;

    private Quiz quiz;
    private Round round;
    private Category category;
    private User challenger;
    private User opponent;
    private String quizId;

    private Button btnPlay;
    private TextView tvUsername1;
    private TextView tvUsername2;

    private Button btn_statisticQ1;
    private Button btn_statisticQ2;
    private Button btn_statisticQ3;
    private Button btn_statisticQ4;
    private Button btn_statisticQ5;
    private Button btn_statisticQ6;
    private Button btn_statisticQ7;
    private Button btn_statisticQ8;
    private Button btn_statisticQ9;
    private Button btn_statisticQ10;
    private Button btn_statisticQ11;
    private Button btn_statisticQ12;

    public QuizStatisticFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewRoot = inflater.inflate(R.layout.fragment_quiz_statistic, container, false);
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

        loadData();

        return viewRoot;
    }

    public void loadData() {
        quizService.get(quizId, new ApiHttpCallback<ApiHttpResponse<Quiz>>() {
            @Override
            public void onCompletion(ApiHttpResponse<Quiz> response) {
                if (response.getSuccess()) {
                    quiz = response.getData();
                    challenger = quiz.get_challenger();
                    opponent = quiz.get_opponent();

                    updateUsernames();
                    updatePlayButton();
                    updateStatisticButtons();

                } else {
                    Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });
    }

    public void updateUsernames() {
        tvUsername1.setText(challenger.getUsername());
        tvUsername2.setText(opponent.getUsername());
    }

    public void updatePlayButton() {
        btnPlay = (Button) viewRoot.findViewById(R.id.btn_Play);

        quizService.getFinishedAnswerCount(quizId, new ApiHttpCallback<ApiHttpResponse<Integer>>() {
            @Override
            public void onCompletion(ApiHttpResponse<Integer> response) {
                if((quiz.get_challenger().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForOpponent")) ||
                        (quiz.get_opponent().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForChallenger"))) {
                    btnPlay.setVisibility(View.INVISIBLE);
                } else if((quiz.get_challenger().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForChallenger") ||
                        quiz.get_opponent().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForOpponent"))) {
                    btnPlay.setVisibility(View.VISIBLE);
                }

                if(response.getSuccess() && response.getData() != 0 && response.getData() < 36 && response.getData() % 3 == 0 && response.getData() % 6 != 0) {
                    round = quiz.get_rounds().get(quiz.get_rounds().size() - 1);
                    category = quiz.get_rounds().get(quiz.get_rounds().size() - 1).get_category();
                    btnPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Bundle bundle = new Bundle();
                            bundle.putString("quizId", quizId);
                            bundle.putString("roundId", round.get_id());
                            bundle.putString("categoryId", category.get_id());
                            QuizQuestionFragment f = new QuizQuestionFragment();
                            f.setArguments(bundle);
                            ((MainActivity) getActivity()).changeFragment(f);
                        }
                    });
                } else if(response.getSuccess() && (response.getData() == 0 || response.getData() < 36) && response.getData() % 6 == 0) {
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
                } else if(response.getData() == 36) {
                    ((MainActivity) getActivity()).changeFragment(new QuizHomeFragment());
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    public void updateStatisticButtons() {

    }


}
