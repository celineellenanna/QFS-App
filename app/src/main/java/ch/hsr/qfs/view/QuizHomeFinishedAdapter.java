package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.QuizService;

public class QuizHomeFinishedAdapter extends RecyclerView.Adapter<QuizHomeFinishedViewHolder> {

    private ArrayList<Quiz> quizzes;

    private AuthService as = AuthService.getInstance();
    private QuizService qs = QuizService.getInstance();

    public QuizHomeFinishedAdapter(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public QuizHomeFinishedViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.quiz_home_finished_row_layout, parent, false);

        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
        TextView tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        TextView tvStatus = (TextView) v.findViewById(R.id.tvStatus);
        RelativeLayout rlListItem = (RelativeLayout) v.findViewById(R.id.rlListItem);

        QuizHomeFinishedViewHolder viewHolder = new QuizHomeFinishedViewHolder(v, ivIcon, tvUsername, tvStatus, rlListItem);

        return viewHolder;
    }

    public void onBindViewHolder(final QuizHomeFinishedViewHolder holder, final int position) {

        final Quiz quiz = quizzes.get(position);

        if(quiz.get_challenger().getId().equals(as.getUser().getId())) {
            holder.tvUsername.setText(quiz.get_opponent().getUsername());
        } else {
            holder.tvUsername.setText(quiz.get_challenger().getUsername());
        }

        holder.ivIcon.setImageResource(R.drawable.ic_reject);
        holder.tvStatus.setText(quiz.getStatus());
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }
}
