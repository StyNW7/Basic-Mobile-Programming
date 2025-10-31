package id.example.mynotes;


import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import id.example.mynotes.helper.DatabaseHelper;
import id.example.mynotes.model.Note;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView tvDetailTitle, tvDetailLastEdited, tvDetailBody;
    private DatabaseHelper dbHelper;
    private long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailLastEdited = findViewById(R.id.tvDetailLastEdited);
        tvDetailBody = findViewById(R.id.tvDetailBody);
        dbHelper = new DatabaseHelper(this);

        // Ambil id dari sqlite.
        noteId = getIntent().getLongExtra("NOTE_ID", -1);

        if (noteId != -1) {
            loadNoteDetails();
        }
    }

    private void loadNoteDetails() {
        Note note = dbHelper.getNote(noteId);

        tvDetailTitle.setText(note.getTitle());
        tvDetailBody.setText(note.getBody());

        String formattedDate = DatabaseHelper.formatTimestamp(note.getLastEditedAt());
        tvDetailLastEdited.setText("Last Edited: " + formattedDate);
    }
}