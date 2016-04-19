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

        TextView _id = (TextView) v.findViewById(R.id._id);
        TextView firstname = (TextView) v.findViewById(R.id.tvFirstname);
        TextView lastname = (TextView) v.findViewById(R.id.tvLastname);
        TextView email = (TextView) v.findViewById(R.id.tvEmail);
        TextView username = (TextView) v.findViewById(R.id.tvUsername);
        TextView password = (TextView) v.findViewById(R.id.tvPassword);
        TextView role = (TextView) v.findViewById(R.id.tvRole);
        TextView status = (TextView) v.findViewById(R.id.tvStatus);

        QuizOpponentViewHolder userViewHolder = new QuizOpponentViewHolder(v, _id, firstname, lastname, email, username, password, role, status);

        return userViewHolder;
    }

    public void onBindViewHolder(QuizOpponentViewHolder holder, int position) {

        final User user = users.get(position);
        holder._id.setText(user.getId());
        holder.firstname.setText(user.getFirstname());
        holder.lastname.setText(user.getLastname());
        holder.email.setText(user.getEmail());
        holder.username.setText(user.getUsername());
        holder.password.setText(user.getPassword());
        holder.role.setText(user.getRole());
        holder.status.setText(user.getStatus());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
