package ch.hsr.qfs.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsr.qfs.R;

public class QuizStatisticFragment extends Fragment {


    public QuizStatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        return inflater.inflate(R.layout.fragment_quiz_statistic, container, false);
    }

}
