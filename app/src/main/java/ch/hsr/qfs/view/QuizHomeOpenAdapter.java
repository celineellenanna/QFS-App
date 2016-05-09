package ch.hsr.qfs.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizHomeOpenAdapter extends RecyclerView.Adapter<QuizHomeOpenViewHolder> {

    private ArrayList<Quiz> quizzes;

    private AuthService authService = AuthService.getInstance();
    private QuizService quizService = QuizService.getInstance();

    public QuizHomeOpenAdapter(ArrayList<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Override
    public QuizHomeOpenViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.quiz_home_open_row_layout, parent, false);

        ImageView ivIcon = (ImageView) v.findViewById(R.id.ivIcon);
        TextView tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        TextView tvStatus = (TextView) v.findViewById(R.id.tvStatus);
        TextView tvTime = (TextView) v.findViewById(R.id.tvTime);
        RelativeLayout rlListItem = (RelativeLayout) v.findViewById(R.id.rlListItem);
        ImageView ivAccept = (ImageView) v.findViewById(R.id.ivAccept);
        ImageView ivReject = (ImageView) v.findViewById(R.id.ivReject);

        QuizHomeOpenViewHolder viewHolder = new QuizHomeOpenViewHolder(v, ivIcon, tvUsername, tvStatus, tvTime, rlListItem, ivAccept, ivReject);

        return viewHolder;
    }

    public void onBindViewHolder(final QuizHomeOpenViewHolder holder, final int position) {

        final Quiz quiz = quizzes.get(position);

        if(quiz.get_challenger().getId().equals(authService.getUser().getId())) {
            holder.ivIcon.setImageResource(R.drawable.ic_history);
            holder.tvUsername.setText(quiz.get_opponent().getUsername());
            holder.tvStatus.setText("Gesendete Anfrage");
            holder.ivAccept.setVisibility(View.INVISIBLE);
        } else {
            holder.ivIcon.setImageResource(R.drawable.ic_history);
            holder.tvUsername.setText(quiz.get_challenger().getUsername());
            holder.tvStatus.setText("Offene Anfrage");
            holder.ivAccept.setVisibility(View.VISIBLE);
        }

        holder.tvTime.setText("" + quiz.getTimeElapsed() + "");

        holder.ivAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizService.accept(quiz.getId(), new ApiHttpCallback<ApiHttpResponse>() {
                    @Override
                    public void onCompletion(ApiHttpResponse response) {
                        if(response.getSuccess()) {
                            removeItem(position);
                        }
                        Toast.makeText(holder.parent.getContext(), response.getMessage(), Toast.LENGTH_LONG);
                    }

                    @Override
                    public void onError(String message) {
                        Log.d("QFS - Error", message);
                    }
                });
            }
        });

        holder.ivReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizService.reject(quiz.getId(), new ApiHttpCallback<ApiHttpResponse>() {
                    @Override
                    public void onCompletion(ApiHttpResponse response) {
                        if(response.getSuccess()) {
                            removeItem(position);
                        }
                        Toast.makeText(holder.parent.getContext(), response.getMessage(), Toast.LENGTH_LONG);
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
