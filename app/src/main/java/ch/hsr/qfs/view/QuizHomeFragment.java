package ch.hsr.qfs.view;


import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsr.pewpew.qfs.R;

public class QuizHomeFragment extends Fragment {


    public QuizHomeFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewRoot = inflater.inflate(R.layout.fragment_quiz_home, container, false);

        FloatingActionButton fab = (FloatingActionButton) viewRoot.findViewById(R.id.fab);
        fab.show();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return viewRoot;
    }

}
