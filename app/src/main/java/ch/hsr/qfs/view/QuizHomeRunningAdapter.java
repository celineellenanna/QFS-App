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

public class QuizHomeRunningAdapter extends RecyclerView.Adapter<QuizHomeRunningViewHolder> {

    private ArrayList<Quiz> quizzes;

    private AuthService as = AuthService.getInstance();
    private QuizService qs = QuizService.getInstance();

    public QuizHomeRunningAdapter(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public QuizHomeRunningViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.quiz_home_running_row_layout, parent, false);

        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
        TextView tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        TextView tvStatus = (TextView) v.findViewById(R.id.tvStatus);
        RelativeLayout rlListItem = (RelativeLayout) v.findViewById(R.id.rlListItem);
        ImageView ivAccept = (ImageView) v.findViewById(R.id.ivAccept);
        ImageView ivReject = (ImageView) v.findViewById(R.id.ivReject);

        QuizHomeRunningViewHolder viewHolder = new QuizHomeRunningViewHolder(v, ivIcon, tvUsername, tvStatus, rlListItem, ivAccept, ivReject);

        return viewHolder;
    }

    public void onBindViewHolder(final QuizHomeRunningViewHolder holder, final int position) {

        final Quiz quiz = quizzes.get(position);

        if(quiz.get_challengerId().getId().equals(as.getUser().getId())) {
            holder.ivIcon.setImageResource(R.drawable.ic_history);
            holder.tvUsername.setText(quiz.get_opponentId().getUsername());
            holder.tvStatus.setText("Warten");
        } else {
            holder.ivIcon.setImageResource(R.drawable.ic_start);
            holder.tvUsername.setText(quiz.get_challengerId().getUsername());
            holder.tvStatus.setText("Spielbereit");
            holder.rlListItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity) holder.parent.getContext()).changeFragment(new ClosedFragment());
                }
            });
        }
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
