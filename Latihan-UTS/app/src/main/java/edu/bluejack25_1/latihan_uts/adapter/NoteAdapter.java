package edu.bluejack25_1.latihan_uts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.bluejack25_1.latihan_uts.helper.DatabaseHelper;
import edu.bluejack25_1.latihan_uts.model.Note;

import edu.bluejack25_1.latihan_uts.R;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    private List<Note> noteList;
    private OnNoteClickListener listener;

    public interface OnNoteClickListener {
        void onNoteClick(Note note);
    }

    public NoteAdapter(List<Note> noteList, OnNoteClickListener listener) {
        this.noteList = noteList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        // TODO: Ambil objek Note berdasarkan position dan panggil holder.bind()
        Note note = noteList.get(position);
        holder.bind(note, listener);
    }

    @Override
    public int getItemCount() {
        // TODO: Update size recyclerviewnya di getItemCount()
        return noteList.size();
    }

    static class NoteViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNoteTitle, tvNoteLastEdited;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNoteTitle = itemView.findViewById(R.id.tvNoteTitle);
            tvNoteTitle = itemView.findViewById(R.id.tvNoteTitle);
            tvNoteLastEdited = itemView.findViewById(R.id.tvNoteLastEdited);
        }

        public void bind(final Note note, final OnNoteClickListener listener) {
            // TODO: Pastikan setiap item masuk ke method bind() dan mengisi data
            tvNoteTitle.setText(note.getTitle());
            String formattedDate = DatabaseHelper.formatTimestamp(note.getLastEditedAt());
            tvNoteLastEdited.setText("Last Edited: " + formattedDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onNoteClick(note);
                }
            });
        }
    }
}