package com.example.latihanuas.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihanuas.JokeDetailActivity;
import com.example.latihanuas.R;
import com.example.latihanuas.models.Joke;

import java.util.ArrayList;
import java.util.List;

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokeHolder> {
    Context mCtx;
    ArrayList<Joke> jokeList;

    public JokesAdapter(ArrayList<Joke> jokeList) {
        this.jokeList = jokeList;
    }

    @NonNull
    @Override
    public JokeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mCtx = parent.getContext();
        View v = LayoutInflater.from(mCtx).inflate(R.layout.item_joke, parent, false);

        return new JokeHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull JokeHolder holder, int position) {

        Joke data = jokeList.get(position);
        holder.txtSetup.setText(data.setup);
        holder.txtType.setText(data.type);

        holder.itemView.setOnClickListener(view -> {

            Intent i = new Intent(mCtx, JokeDetailActivity.class);
            i.putExtra("setup", data.setup);
            i.putExtra("punchline", data.punchline);
            i.putExtra("type", data.type);
            i.putExtra("id", data.id);
            i.putExtra("views", data.stats.views);
            i.putExtra("likes", data.stats.likes);
            i.putExtra("shares", data.stats.shares);

            mCtx.startActivity(i);

        });

    }

    @Override
    public void onBindViewHolder(@NonNull JokeHolder holder, int position, @NonNull List<Object> payloads) {

        Joke data = jokeList.get(position);
        holder.txtType.setText(data.type);
        holder.txtSetup.setText(data.setup);

        holder.itemView.setOnClickListener(view -> {

            Intent i = new Intent(mCtx, JokeDetailActivity.class);
            i.putExtra("setup", data.setup);
            i.putExtra("punchline", data.punchline);
            i.putExtra("type", data.type);
            i.putExtra("likes", data.stats.likes);
            i.putExtra("shares", data.stats.shares);
            i.putExtra("views", data.stats.views);
            mCtx.startActivity(i);

        });

    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }

    public class JokeHolder extends RecyclerView.ViewHolder {
        TextView txtType, txtSetup;
        public JokeHolder(@NonNull View itemView) {
            super(itemView);
            txtType = itemView.findViewById(R.id.txtType);
            txtSetup = itemView.findViewById(R.id.txtSetup);
        }
    }
}
