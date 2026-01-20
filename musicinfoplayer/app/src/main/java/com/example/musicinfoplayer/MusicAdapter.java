package com.example.musicinfoplayer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {

    private List<Track> list;
    private Context context;

    public MusicAdapter(Context c, List<Track> l) {
        context = c;
        list = l;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup p, int v) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_music, p, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder h, int i) {
        Track t = list.get(i);

        h.title.setText(t.getTitle());
        h.artist.setText(t.getArtist().getName());

        h.btnPlay.setOnClickListener(v-> {
            Intent intent = new Intent(context, MusicService.class);
            intent.putExtra("preview", t.getPreview());
            context.startService(intent);
        });

        h.btnStop.setOnClickListener(v -> {
            context.stopService(new Intent(context, MusicService.class));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, artist;
        Button btnPlay, btnStop;

        ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.txtTitle);
            artist = v.findViewById(R.id.txtArtist);
            btnPlay = v.findViewById(R.id.btnPlay);
            btnStop = v.findViewById(R.id.btnStop);
        }
    }
}
