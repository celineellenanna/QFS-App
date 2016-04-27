package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizHomeOpenAdapter extends RecyclerView.Adapter<QuizHomeOpenViewHolder> {

    private ArrayList<Quiz> quizzes;

    private AuthService as = AuthService.getInstance();
    private QuizService qs = QuizService.getInstance();

    public QuizHomeOpenAdapter(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public QuizHomeOpenViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.quiz_home_open_row_layout, parent, false);

        ImageView imageView = (ImageView) v.findViewById(R.id.imageView);
        TextView id = (TextView) v.findViewById(R.id.tvId);
        TextView challengerId = (TextView) v.findViewById(R.id.tvChallengerId);
        TextView opponentId = (TextView) v.findViewById(R.id.tvOpponentId);
        TextView status = (TextView) v.findViewById(R.id.tvStatus);
        RelativeLayout listItem = (RelativeLayout) v.findViewById(R.id.rlListItem);

        QuizHomeOpenViewHolder viewHolder = new QuizHomeOpenViewHolder(v, imageView, id, challengerId, opponentId, status, listItem);

        return viewHolder;
    }

    public void onBindViewHolder(final QuizHomeOpenViewHolder holder, final int position) {

        final Quiz quiz = quizzes.get(position);

        if(quiz.get_challengerId().getId().equals(as.getUser().getId())) {
            holder.imageView.setImageResource(R.drawable.ic_history);
        } else {
            holder.imageView.setImageResource(R.drawable.ic_history);
        }

        holder.id.setText(quiz.getId());
        holder.challengerId.setText(quiz.get_challengerId().getUsername());
        holder.opponentId.setText(quiz.get_opponentId().getUsername());
        holder.status.setText(quiz.getStatus());
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
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
