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

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.RoundQuestion;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizQuestionFragment extends Fragment {

    private ProgressBar pgProgressBar;
    private int progressBarStatus = 0;
    private int progressBarSleep = 100;
    private final int progressBarStatusMax = 5000;
    private final int progressBarStatusIncrement = 100;

    private Handler mHandler = new Handler();

    private String quizId;
    private String categoryId;

    private QuizService quizService;

    public QuizQuestionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_quiz_question, container, false);

        Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");
        categoryId = bundle.getString("categoryId");

        quizService.getRoundQuestions(quizId, new ApiHttpCallback<ApiHttpResponse<ArrayList<RoundQuestion>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<RoundQuestion>> response) {
                if (response.getSuccess()) {

                    Button btnQuestion1 = (Button) viewRoot.findViewById(R.id.btnQuestion1);
                    Button btnQuestion2 = (Button) viewRoot.findViewById(R.id.btnQuestion2);
                    Button btnQuestion3 = (Button) viewRoot.findViewById(R.id.btnQuestion3);
                    Button btnQuestion4 = (Button) viewRoot.findViewById(R.id.btnQuestion4);

                    btnQuestion1.setText(response.getData().get(0).getQuestion().getName());
                    btnQuestion2.setText(response.getData().get(1).getQuestion().getName());
                    btnQuestion3.setText(response.getData().get(2).getQuestion().getName());
                    btnQuestion4.setText(response.getData().get(3).getQuestion().getName());

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
                    Log.d("QFS", "FINISHED");
                }
            }
        }).start();
    }
}
