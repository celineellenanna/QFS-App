package ch.hsr.qfs.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Category;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCategoryFragment extends Fragment {

    private QuizService qs = QuizService.getInstance();

    public QuizCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot =  inflater.inflate(R.layout.fragment_quiz_category, container, false);

        ((MainActivity) getActivity()).changeToolbarTitle("Quiz - Kategorie");

        final Button btn_category1 = (Button) viewRoot.findViewById(R.id.btn_category1);
        final Button btn_category2 = (Button) viewRoot.findViewById(R.id.btn_category2);
        final Button btn_category3 = (Button) viewRoot.findViewById(R.id.btn_category3);

        qs.getCategories(new ApiHttpCallback<ApiHttpResponse<ArrayList<Category>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<Category>> response) {
                if(response.getSuccess())
                {
                    ArrayList<Category> categories = response.getData();

                    btn_category1.setText(categories.get(0).getName());
                    btn_category2.setText(categories.get(1).getName());
                    btn_category3.setText(categories.get(2).getName());
                } else {
                    Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });

        return viewRoot;
    }

}
