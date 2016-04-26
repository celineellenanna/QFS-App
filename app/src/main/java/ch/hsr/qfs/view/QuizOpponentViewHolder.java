package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

public class QuizOpponentViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public TextView username;
    public TableLayout listItem;

    public QuizOpponentViewHolder(View parent,TextView username, TableLayout listItem) {
        super(parent);
        this.parent = parent;
        this.username = username;
        this.listItem = listItem;
    }
}
