package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizHomeRunningViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public ImageView ivIcon;
    public TextView tvChallengerId;
    public TextView tvOpponentId;
    public TextView tvStatus;
    public RelativeLayout rlListItem;
    public ImageView ivAccept;
    public ImageView ivReject;

    public QuizHomeRunningViewHolder(View parent, ImageView ivIcon, TextView tvChallengerId, TextView tvOpponentId, TextView tvStatus, RelativeLayout rlListItem, ImageView ivAccept, ImageView ivReject) {
        super(parent);
        this.parent = parent;
        this.ivIcon = ivIcon;
        this.tvChallengerId = tvChallengerId;
        this.tvOpponentId = tvOpponentId;
        this.tvStatus = tvStatus;
        this.rlListItem = rlListItem;
        this.ivAccept = ivAccept;
        this.ivReject = ivReject;
    }
}
