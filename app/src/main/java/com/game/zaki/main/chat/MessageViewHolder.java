package com.game.zaki.main.chat;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public View mView;
    Context mContext;

    public MessageViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

}
