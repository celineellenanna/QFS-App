package ch.hsr.qfs.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class QuizHomeRunningViewHolder extends RecyclerView.ViewHolder {

    public View parent;
    public ImageView ivIcon;
    public TextView tvUsername;
    public TextView tvStatus;
    public RelativeLayout rlListItem;
    public ImageView ivAccept;
    public ImageView ivReject;

    public QuizHomeRunningViewHolder(View parent, ImageView ivIcon, TextView tvUsername, TextView tvStatus,
                                     RelativeLayout rlListItem, ImageView ivAccept, ImageView ivReject) {
        super(parent);
        this.parent = parent;
        this.ivIcon = ivIcon;
        this.tvUsername = tvUsername;
        this.tvStatus = tvStatus;
        this.rlListItem = rlListItem;
        this.ivAccept = ivAccept;
        this.ivReject = ivReject;
    }
}
