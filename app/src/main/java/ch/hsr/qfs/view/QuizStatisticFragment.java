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

import ch.hsr.qfs.domain.Answer;
import ch.hsr.qfs.domain.Category;
import ch.hsr.qfs.domain.CountAnswers;
import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.domain.Round;
import ch.hsr.qfs.domain.RoundQuestion;
import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.domain.UserAnswer;
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
    private TextView tvScore;

    private Button[] btn_array;

    public QuizStatisticFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewRoot = inflater.inflate(R.layout.fragment_quiz_statistic, container, false);
        ((MainActivity) getActivity()).hideFloatingActionButton(true);
        ((MainActivity) getActivity()).changeToolbarTitle("Quiz - Statistik");

        Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");

        tvUsername1 = (TextView) viewRoot.findViewById(R.id.tvUsername1);
        tvUsername2 = (TextView) viewRoot.findViewById(R.id.tvUsername2);

        tvScore = (TextView) viewRoot.findViewById(R.id.tvScore);

        Button btn_statisticQ1 = (Button) viewRoot.findViewById(R.id.btn_statisticQ1);
        Button btn_statisticQ2 = (Button) viewRoot.findViewById(R.id.btn_statisticQ2);
        Button btn_statisticQ3 = (Button) viewRoot.findViewById(R.id.btn_statisticQ3);
        Button btn_statisticQ4 = (Button) viewRoot.findViewById(R.id.btn_statisticQ4);
        Button btn_statisticQ5 = (Button) viewRoot.findViewById(R.id.btn_statisticQ5);
        Button btn_statisticQ6 = (Button) viewRoot.findViewById(R.id.btn_statisticQ6);
        Button btn_statisticQ7 = (Button) viewRoot.findViewById(R.id.btn_statisticQ7);
        Button btn_statisticQ8 = (Button) viewRoot.findViewById(R.id.btn_statisticQ8);
        Button btn_statisticQ9 = (Button) viewRoot.findViewById(R.id.btn_statisticQ9);
        Button btn_statisticQ10 = (Button) viewRoot.findViewById(R.id.btn_statisticQ10);
        Button btn_statisticQ11 = (Button) viewRoot.findViewById(R.id.btn_statisticQ11);
        Button btn_statisticQ12 = (Button) viewRoot.findViewById(R.id.btn_statisticQ12);
        Button btn_statisticQ13 = (Button) viewRoot.findViewById(R.id.btn_statisticQ13);
        Button btn_statisticQ14 = (Button) viewRoot.findViewById(R.id.btn_statisticQ14);
        Button btn_statisticQ15 = (Button) viewRoot.findViewById(R.id.btn_statisticQ15);
        Button btn_statisticQ16 = (Button) viewRoot.findViewById(R.id.btn_statisticQ16);
        Button btn_statisticQ17 = (Button) viewRoot.findViewById(R.id.btn_statisticQ17);
        Button btn_statisticQ18 = (Button) viewRoot.findViewById(R.id.btn_statisticQ18);
        Button btn_statisticQ19 = (Button) viewRoot.findViewById(R.id.btn_statisticQ19);
        Button btn_statisticQ20 = (Button) viewRoot.findViewById(R.id.btn_statisticQ20);
        Button btn_statisticQ21 = (Button) viewRoot.findViewById(R.id.btn_statisticQ21);
        Button btn_statisticQ22 = (Button) viewRoot.findViewById(R.id.btn_statisticQ22);
        Button btn_statisticQ23 = (Button) viewRoot.findViewById(R.id.btn_statisticQ23);
        Button btn_statisticQ24 = (Button) viewRoot.findViewById(R.id.btn_statisticQ24);
        Button btn_statisticQ25 = (Button) viewRoot.findViewById(R.id.btn_statisticQ25);
        Button btn_statisticQ26 = (Button) viewRoot.findViewById(R.id.btn_statisticQ26);
        Button btn_statisticQ27 = (Button) viewRoot.findViewById(R.id.btn_statisticQ27);
        Button btn_statisticQ28 = (Button) viewRoot.findViewById(R.id.btn_statisticQ28);
        Button btn_statisticQ29 = (Button) viewRoot.findViewById(R.id.btn_statisticQ29);
        Button btn_statisticQ30 = (Button) viewRoot.findViewById(R.id.btn_statisticQ30);
        Button btn_statisticQ31 = (Button) viewRoot.findViewById(R.id.btn_statisticQ31);
        Button btn_statisticQ32 = (Button) viewRoot.findViewById(R.id.btn_statisticQ32);
        Button btn_statisticQ33 = (Button) viewRoot.findViewById(R.id.btn_statisticQ33);
        Button btn_statisticQ34 = (Button) viewRoot.findViewById(R.id.btn_statisticQ34);
        Button btn_statisticQ35 = (Button) viewRoot.findViewById(R.id.btn_statisticQ35);
        Button btn_statisticQ36 = (Button) viewRoot.findViewById(R.id.btn_statisticQ36);

        Button[] btn_array_temp = {btn_statisticQ1, btn_statisticQ4, btn_statisticQ2, btn_statisticQ5, btn_statisticQ3, btn_statisticQ6,
                btn_statisticQ10, btn_statisticQ7, btn_statisticQ11, btn_statisticQ8, btn_statisticQ12, btn_statisticQ9, btn_statisticQ13,
                btn_statisticQ16, btn_statisticQ14, btn_statisticQ17, btn_statisticQ15, btn_statisticQ18, btn_statisticQ22, btn_statisticQ19,
                btn_statisticQ23, btn_statisticQ20, btn_statisticQ24, btn_statisticQ21, btn_statisticQ25, btn_statisticQ28, btn_statisticQ26,
                btn_statisticQ29, btn_statisticQ27, btn_statisticQ30, btn_statisticQ34, btn_statisticQ31, btn_statisticQ35, btn_statisticQ32,
                btn_statisticQ36, btn_statisticQ33};

        btn_array = btn_array_temp;

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
                    updateScore();
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
        tvUsername1.setText(opponent.getUsername());
        tvUsername2.setText(challenger.getUsername());
    }

    public void updatePlayButton() {
        btnPlay = (Button) viewRoot.findViewById(R.id.btn_Play);

        quizService.getFinishedAnswerCount(quizId, new ApiHttpCallback<ApiHttpResponse<CountAnswers>>() {
            @Override
            public void onCompletion(ApiHttpResponse<CountAnswers> response) {
                if ((quiz.get_challenger().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForOpponent")) ||
                        (quiz.get_opponent().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForChallenger"))) {
                    btnPlay.setVisibility(View.INVISIBLE);
                } else if ((quiz.get_challenger().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForChallenger") ||
                        quiz.get_opponent().getId().equals(authService.getUser().getId()) && quiz.getStatus().equals("WaitingForOpponent"))) {
                    btnPlay.setVisibility(View.VISIBLE);
                }

                if (response.getSuccess() && response.getData().getCountUserAnswer() > 0 && response.getData().getCountUserAnswer() < 36 &&
                        response.getData().getCountActualRoundAnswers() < 6) {

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
                } else if (response.getSuccess() && response.getData().getCountUserAnswer() < 36 &&
                        (response.getData().getCountUserAnswer() == 0 || response.getData().getCountActualRoundAnswers() == 6)) {

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
                } else if (response.getData().getCountUserAnswer() == 36) {
                    ((MainActivity) getActivity()).changeFragment(new QuizHomeFragment());
                }
            }

            @Override
            public void onError(String message) {

            }
        });
    }

    public void updateStatisticButtons() {

        int buttonIndex = 0;
        for (Round round:quiz.get_rounds()) {
            for(RoundQuestion roundQuestion: round.get_roundQuestions())
            {
                if(roundQuestion.get_userAnswers().size() == 2) {
                    for(UserAnswer userAnswer:roundQuestion.get_userAnswers())
                    {
                        if(userAnswer.getStatus()) {
                            btn_array[buttonIndex].setBackgroundResource(R.drawable.button_bg_round_green);
                        } else {
                            btn_array[buttonIndex].setBackgroundResource(R.drawable.button_bg_round_red);
                        }
                        buttonIndex++;
                    }
                } else {
                    for(UserAnswer userAnswer:roundQuestion.get_userAnswers())
                    {
                        if(userAnswer.getStatus()) {
                            btn_array[buttonIndex].setBackgroundResource(R.drawable.button_bg_round_green);
                        } else {
                            btn_array[buttonIndex].setBackgroundResource(R.drawable.button_bg_round_red);
                        }
                        buttonIndex = buttonIndex + 2;
                    }
                }
            }
        }
    }

    public void updateScore() {
        tvScore.setText(String.format("%d : %d", quiz.getOpponentPoints(), quiz.getChallengerPoints()));
    }
}
