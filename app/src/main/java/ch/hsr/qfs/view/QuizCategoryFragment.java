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


public class QuizCategoryFragment extends Fragment {

    private QuizService quizService = QuizService.getInstance();
    private String quizId;
    private Category category1;
    private Category category2;
    private Category category3;
    private Button btn_category1;
    private Button btn_category2;
    private Button btn_category3;

    public QuizCategoryFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewRoot =  inflater.inflate(R.layout.fragment_quiz_category, container, false);

        ((MainActivity) getActivity()).changeToolbarTitle("Quiz - Kategorie");
        ((MainActivity) getActivity()).hideFloatingActionButton(true);

        Bundle bundle = getArguments();
        quizId = bundle.getString("quizId");

        btn_category1 = (Button) viewRoot.findViewById(R.id.btn_category1);
        btn_category2 = (Button) viewRoot.findViewById(R.id.btn_category2);
        btn_category3 = (Button) viewRoot.findViewById(R.id.btn_category3);

        quizService.getCategories(new ApiHttpCallback<ApiHttpResponse<ArrayList<Category>>>() {
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
                disableButtons();
                quizService.createRound(quizId, category1.get_id(), new ApiHttpCallback<ApiHttpResponse<Round>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<Round> response) {
                        if(response.getSuccess()){
                            changeToQuestionFragment(quizId, category1.get_id(), response.getData().get_id());
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
                disableButtons();
                quizService.createRound(quizId, category2.get_id(), new ApiHttpCallback<ApiHttpResponse<Round>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<Round> response) {
                        if(response.getSuccess()){
                            changeToQuestionFragment(quizId, category2.get_id(), response.getData().get_id());
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
                disableButtons();
                quizService.createRound(quizId, category3.get_id(), new ApiHttpCallback<ApiHttpResponse<Round>>() {
                    @Override
                    public void onCompletion(ApiHttpResponse<Round> response) {
                        if (response.getSuccess()) {
                            changeToQuestionFragment(quizId, category3.get_id(), response.getData().get_id());
                        } else {
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

    private void changeToQuestionFragment(String quizId2, String categoryId2, String roundId2) {
        Bundle bundle = new Bundle();
        bundle.putString("quizId", quizId2);
        bundle.putString("categoryId", categoryId2);
        bundle.putString("roundId", roundId2);
        QuizQuestionFragment f = new QuizQuestionFragment();
        f.setArguments(bundle);
        ((MainActivity) getActivity()).changeFragment(f);
    }

    private void disableButtons(){
        btn_category1.setOnClickListener(null);
        btn_category2.setOnClickListener(null);
        btn_category3.setOnClickListener(null);
    }

}
