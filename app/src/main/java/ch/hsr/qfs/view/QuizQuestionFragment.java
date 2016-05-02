package ch.hsr.qfs.view;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.hsr.qfs.R;

import ch.hsr.qfs.domain.Round;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizQuestionFragment extends Fragment {

    private ProgressBar pgProgressBar;
    private int progressBarStatus = 0;
    private int progressBarSleep = 100;
    private final int progressBarStatusMax = 10000;
    private final int progressBarStatusIncrement = 100;

    private Handler mHandler = new Handler();

    private String quizId;
    private String categoryId;
    private String roundId;

    private QuizService quizService = QuizService.getInstance();

    public QuizQuestionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_quiz_question, container, false);

        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");
        categoryId = bundle.getString("categoryId");
        roundId = bundle.getString("roundId");

        quizService.getRound(roundId, new ApiHttpCallback<ApiHttpResponse<Round>>() {
            @Override
            public void onCompletion(ApiHttpResponse<Round> response) {
                if (response.getSuccess()) {

                    Round round = response.getData();


                    ((MainActivity) getActivity()).changeToolbarTitle("Quiz - " + round.get_category().getName());

                    Button btnQuestion1 = (Button) viewRoot.findViewById(R.id.btnQuestion1);
                    Button btnQuestion2 = (Button) viewRoot.findViewById(R.id.btnQuestion2);
                    Button btnQuestion3 = (Button) viewRoot.findViewById(R.id.btnQuestion3);
                    Button btnQuestion4 = (Button) viewRoot.findViewById(R.id.btnQuestion4);

                    TextView tvQuestion = (TextView) viewRoot.findViewById(R.id.tvQuestion);

                    tvQuestion.setText(round.get_roundQuestions().get(0).get_question().getName());

                    btnQuestion1.setText(round.get_roundQuestions().get(0).get_question().getAnswers().get(0).getText());
                    btnQuestion2.setText(round.get_roundQuestions().get(0).get_question().getAnswers().get(1).getText());
                    btnQuestion3.setText(round.get_roundQuestions().get(0).get_question().getAnswers().get(2).getText());
                    btnQuestion4.setText(round.get_roundQuestions().get(0).get_question().getAnswers().get(3).getText());

                    pgProgressBar = (ProgressBar) viewRoot.findViewById(R.id.pgProgressBar);
                    pgProgressBar.setMax(progressBarStatusMax);

                    runProgressBar();
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });

        return viewRoot;
    }

    private void runProgressBar() {
        new Thread(new Runnable() {
            public void run() {
                while (progressBarStatus < progressBarStatusMax) {
                    try {
                        Thread.sleep(progressBarSleep);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBarStatus = progressBarStatus + progressBarStatusIncrement;

                    mHandler.post(new Runnable() {
                        public void run() {
                            pgProgressBar.setProgress(progressBarStatus);
                        }
                    });
                }

                if(progressBarStatus == progressBarStatusMax) {

                }
            }
        }).start();
    }
}
