package ch.hsr.qfs.view;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsr.qfs.R;


public class QuizQuestionFragment extends Fragment {


    public QuizQuestionFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View viewRoot = inflater.inflate(R.layout.fragment_quiz_question, container, false);



        return viewRoot;
    }

}
