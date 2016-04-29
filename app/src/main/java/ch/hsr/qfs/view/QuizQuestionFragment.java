package ch.hsr.qfs.view;


import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.hsr.qfs.R;

public class QuizQuestionFragment extends Fragment {

    private ProgressBar pgProgressBar;
    private int progressBarStatus = 0;
    private int progressBarSleep = 100;
    private final int progressBarStatusMax = 5000;
    private final int progressBarStatusIncrement = 100;

    private Handler mHandler = new Handler();

    private String quizId;

    public QuizQuestionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_quiz_question, container, false);

        /*Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");*/

        pgProgressBar = (ProgressBar) viewRoot.findViewById(R.id.pgProgressBar);
        pgProgressBar.setMax(progressBarStatusMax);

        runProgressBar();

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
            }
        }).start();
    }
}
