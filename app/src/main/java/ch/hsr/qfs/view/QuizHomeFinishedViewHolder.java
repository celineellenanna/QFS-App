package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizHomeFinishedViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public ImageView ivIcon;
    public TextView tvUsername;
    public TextView tvStatus;
    public TextView tvScore;
    public RelativeLayout rlListItem;

    public QuizHomeFinishedViewHolder(View parent, ImageView ivIcon, TextView tvUsername, TextView tvStatus, TextView tvScore, RelativeLayout rlListItem) {
        super(parent);
        this.parent = parent;
        this.ivIcon = ivIcon;
        this.tvUsername = tvUsername;
        this.tvStatus = tvStatus;
        this.tvScore = tvScore;
        this.rlListItem = rlListItem;
    }
}
