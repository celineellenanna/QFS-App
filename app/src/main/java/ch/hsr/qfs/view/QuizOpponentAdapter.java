package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hsr.qfs.R;

import java.util.ArrayList;

import ch.hsr.qfs.domain.User;

public class QuizOpponentAdapter extends RecyclerView.Adapter<QuizOpponentViewHolder> {

    private ArrayList<User> users;

    public QuizOpponentAdapter(ArrayList<User> users) {
        this.users = users;
    }

    @Override
    public QuizOpponentViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.quiz_opponent_row_layout, parent, false);

        TextView username = (TextView) v.findViewById(R.id.tvUsername);

        QuizOpponentViewHolder userViewHolder = new QuizOpponentViewHolder(v, username);

        return userViewHolder;
    }

    public void onBindViewHolder(QuizOpponentViewHolder holder, int position) {

        final User user = users.get(position);
        holder.username.setText(user.getUsername());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
