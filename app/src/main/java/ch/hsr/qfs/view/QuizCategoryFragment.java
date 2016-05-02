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
import ch.hsr.qfs.domain.Round;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

/**
 * A simple {@link Fragment} subclass.
 */
public class QuizCategoryFragment extends Fragment {

    private QuizService qs = QuizService.getInstance();
    private String quizId;
    private Category category1;
    private Category category2;
    private Category category3;

    public QuizCategoryFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot =  inflater.inflate(R.layout.fragment_quiz_category, container, false);

        ((MainActivity) getActivity()).changeToolbarTitle("Quiz - Kategorie");

        Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");

        final Button btn_category1 = (Button) viewRoot.findViewById(R.id.btn_category1);
        final Button btn_category2 = (Button) viewRoot.findViewById(R.id.btn_category2);
        final Button btn_category3 = (Button) viewRoot.findViewById(R.id.btn_category3);

        qs.getCategories(new ApiHttpCallback<ApiHttpResponse<ArrayList<Category>>>() {
            @Override
            public void onCompletion(ApiHttpResponse<ArrayList<Category>> response) {
                if(response.getSuccess())
                {
                    ArrayList<Category> categories = response.getData();

                    category1 = categories.get(0);
                    category2 = categories.get(1);
                    category3 = categories.get(2);

                    btn_category1.setText(category1.getName());
                    btn_category2.setText(category2.getName());
                    btn_category3.setText(category3.getName());

                } else {
                    Toast.makeText(getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onError(String message) {
                Log.d("QFS - Error", message);
            }
        });

        btn_category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qs.createRound(quizId, category1.get_id(), new ApiHttpCallback<ApiHttpResponse<Round>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<Round> response) {
                        if(response.getSuccess()){
                            changetoQuestionFragment(quizId, category1.get_id(), response.getData().get_id());
                        }else {
                            Toast.makeText(getContext(), "Kategorie nicht übermittelt (onComplete)", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getContext(), "Kategorie nicht übermittelt (onError)", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btn_category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qs.createRound(quizId, category2.get_id(), new ApiHttpCallback<ApiHttpResponse<Round>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<Round> response) {
                        if(response.getSuccess()){
                            changetoQuestionFragment(quizId, category2.get_id(), response.getData().get_id());
                        }else {
                            Toast.makeText(getContext(), "Kategorie nicht übermittelt (onComplete)", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getContext(), "Kategorie nicht übermittelt (onError)", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
        btn_category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qs.createRound(quizId, category3.get_id(), new ApiHttpCallback<ApiHttpResponse<Round>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<Round> response) {
                        if(response.getSuccess()){
                            changetoQuestionFragment(quizId, category3.get_id(), response.getData().get_id());
                        }else {
                            Toast.makeText(getContext(), "Kategorie nicht übermittelt (onComplete)", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onError(String message) {
                        Toast.makeText(getContext(), "Kategorie nicht übermittelt (onError)", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        return viewRoot;
    }

    private void changetoQuestionFragment(String quizId2, String id, String round) {
        Bundle bundle = new Bundle();
        bundle.putString("quizId", quizId2);
        bundle.putString("categoryId", id);
        bundle.putString("roundId", round);
        QuizQuestionFragment f = new QuizQuestionFragment();
        f.setArguments(bundle);
        ((MainActivity) getActivity()).changeFragment(f);
    }

}
