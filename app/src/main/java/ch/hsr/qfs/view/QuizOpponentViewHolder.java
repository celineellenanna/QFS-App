package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizOpponentViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public TextView tvUsername;
    public RelativeLayout rlListItem;

    public QuizOpponentViewHolder(View parent, TextView tvUsername, RelativeLayout rlListItem) {
        super(parent);
        this.parent = parent;
        this.tvUsername = tvUsername;
        this.rlListItem = rlListItem;
    }
}
