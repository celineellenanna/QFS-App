package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizHomeRequestViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public TextView id;
    public TextView challengerId;
    public TextView opponentId;
    public TextView status;
    public RelativeLayout listItem;

    public QuizHomeRequestViewHolder(View parent, TextView id, TextView challengerId, TextView opponentId, TextView status, RelativeLayout listItem) {
        super(parent);
        this.parent = parent;
        this.id = id;
        this.challengerId = challengerId;
        this.opponentId = opponentId;
        this.status = status;
        this.listItem = listItem;
    }
}
