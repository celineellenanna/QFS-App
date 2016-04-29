package ch.hsr.qfs.view;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.Quiz;
import ch.hsr.qfs.domain.User;
import ch.hsr.qfs.service.AuthService;
import ch.hsr.qfs.service.QuizService;
import ch.hsr.qfs.service.apiclient.ApiHttpCallback;
import ch.hsr.qfs.service.apiclient.ApiHttpResponse;

public class QuizOpponentAdapter extends RecyclerView.Adapter<QuizOpponentViewHolder> {

    private ArrayList<User> users;

    public QuizOpponentAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public QuizOpponentViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.quiz_opponent_row_layout, parent, false);

        TextView tvUsername = (TextView) v.findViewById(R.id.tvUsername);
        RelativeLayout rlListItem = (RelativeLayout) v.findViewById(R.id.rlListItem);

        QuizOpponentViewHolder viewHolder = new QuizOpponentViewHolder(v, tvUsername, rlListItem);

        return viewHolder;
    }

    public void onBindViewHolder(final QuizOpponentViewHolder holder, final int position) {

        final User user = users.get(position);
        holder.tvUsername.setText(user.getUsername());
        holder.rlListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            AuthService as = AuthService.getInstance();
            QuizService qs = QuizService.getInstance();

            qs.create(as.getUser().getId(), user.getId(), new ApiHttpCallback<ApiHttpResponse<Quiz>> ()
            {
                @Override
                public void onCompletion(ApiHttpResponse<Quiz> response) {
                    if (response.getSuccess()) {
                        removeItem(position);
                    }
                    Toast.makeText(holder.parent.getContext(), response.getMessage(), Toast.LENGTH_LONG).show();
                    ((MainActivity) holder.parent.getContext()).changeFragment(new QuizHomeFragment());
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
        return users.size();
    }

    public void removeItem(int position) {
        users.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, users.size());
    }
}
