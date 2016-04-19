package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

public class QuizOpponentViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public TextView _id;
    public TextView firstname;
    public TextView lastname;
    public TextView email;
    public TextView username;
    public TextView password;
    public TextView role;
    public TextView status;

    public QuizOpponentViewHolder(View parent, TextView _id, TextView firstname, TextView lastname, TextView email
            ,TextView username, TextView password, TextView role, TextView status) {
        super(parent);
        this.parent = parent;
        this._id = _id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }
}
