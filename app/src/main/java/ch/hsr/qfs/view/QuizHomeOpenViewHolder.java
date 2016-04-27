package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizHomeOpenViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public ImageView imageView;
    public TextView id;
    public TextView challengerId;
    public TextView opponentId;
    public TextView status;
    public RelativeLayout listItem;

    public QuizHomeOpenViewHolder(View parent, ImageView imageView, TextView id, TextView challengerId, TextView opponentId, TextView status, RelativeLayout listItem) {
        super(parent);
        this.parent = parent;
        this.imageView = imageView;
        this.id = id;
        this.challengerId = challengerId;
        this.opponentId = opponentId;
        this.status = status;
        this.listItem = listItem;
    }
}
