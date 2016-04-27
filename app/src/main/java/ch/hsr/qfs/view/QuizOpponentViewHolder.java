package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizOpponentViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public TextView username;
    public RelativeLayout listItem;

    public QuizOpponentViewHolder(View parent, TextView username, RelativeLayout listItem) {
        super(parent);
        this.parent = parent;
        this.username = username;
        this.listItem = listItem;
    }
}
