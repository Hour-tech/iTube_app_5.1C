package com.henry.itubeapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<String> url;

    public MyAdapter(Context context, ArrayList url){
        this.context = context;
        this.url = url;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.url_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, final int position) {
        String currentUrl = url.get(position);
        holder.url_txt.setText(String.valueOf(currentUrl));
        holder.play_button.setOnClickListener(v -> {
            Intent intent = new Intent(context, PlayVideo.class);
            intent.putExtra("video_url", currentUrl); // pass the URL
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return url.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView url_txt;
        Button play_button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            url_txt = itemView.findViewById(R.id.url);
            play_button = itemView.findViewById(R.id.ButtonPlay);

        }
    }
}
