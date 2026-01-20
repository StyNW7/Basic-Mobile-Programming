package com.example.latihanuas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.latihanuas.R;
import com.example.latihanuas.model.Joke;

import java.util.ArrayList;

public class JokeAdapter extends RecyclerView.Adapter<JokeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Joke> jokeList;

    public JokeAdapter(Context context, ArrayList<Joke> jokeList) {
        this.context = context;
        this.jokeList = jokeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_joke_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Joke joke = jokeList.get(position);

        holder.tvType.setText(joke.getType());
        holder.tvSetup.setText(joke.getSetup());
        holder.tvPunchline.setText(joke.getPunchline());
    }

    @Override
    public int getItemCount() {
        return jokeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvType, tvSetup, tvPunchline;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvType = itemView.findViewById(R.id.tvType);
            tvSetup = itemView.findViewById(R.id.tvSetup);
            tvPunchline = itemView.findViewById(R.id.tvPunchline);
        }
    }
}