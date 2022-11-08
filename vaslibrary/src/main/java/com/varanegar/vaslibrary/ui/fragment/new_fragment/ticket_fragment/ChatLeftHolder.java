package com.varanegar.vaslibrary.ui.fragment.new_fragment.ticket_fragment;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.varanegar.vaslibrary.R;

public class ChatLeftHolder extends RecyclerView.ViewHolder {

    private TextView tvContent;
    private TextView tvName;

    public ChatLeftHolder(View itemView) {
        super(itemView);
        tvContent = (TextView) itemView.findViewById(R.id.text_gchat_message_other);
    }

    public TextView getTvContent() {
        return tvContent;
    }

    public void setTvContent(TextView tvContent) {
        this.tvContent = tvContent;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }
}
