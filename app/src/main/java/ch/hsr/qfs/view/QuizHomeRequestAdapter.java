package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizHomeRequestAdapter extends RecyclerView.Adapter<QuizHomeRequestViewHolder> {

    private ArrayList<Quiz> quizzes;

    public QuizHomeRequestAdapter(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public QuizHomeRequestViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.quiz_home_request_row_layout, parent, false);

        TextView id = (TextView) v.findViewById(R.id.tvId);
        TextView challengerId = (TextView) v.findViewById(R.id.tvChallengerId);
        TextView opponentId = (TextView) v.findViewById(R.id.tvOpponentId);
        TextView status = (TextView) v.findViewById(R.id.tvStatus);
        RelativeLayout listItem = (RelativeLayout) v.findViewById(R.id.rlListItem);

        QuizHomeRequestViewHolder viewHolder = new QuizHomeRequestViewHolder(v, id, challengerId, opponentId, status, listItem);

        return viewHolder;
    }

    public void onBindViewHolder(final QuizHomeRequestViewHolder holder, final int position) {

        final Quiz quiz = quizzes.get(position);
        holder.id.setText(quiz.getId());
        holder.challengerId.setText(quiz.get_challengerId());
        holder.opponentId.setText(quiz.get_opponentId());
        holder.status.setText(quiz.getStatus());
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AuthService as = AuthService.getInstance();
                QuizService qs = QuizService.getInstance();

                qs.create(as.getUser().getId(), quiz.getId(), new ApiHttpCallback<ApiHttpResponse<Quiz>> ()
                {
                    @Override
                    public void onCompletion(ApiHttpResponse<Quiz> response) {
                        if (response.getSuccess()) {
                            removeItem(position);
                        }
                    }

                    @Override
                    public void onError(String message) {
                        Log.d("QFS - Error", message);
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    public void removeItem(int position) {
        quizzes.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, quizzes.size());
    }
}