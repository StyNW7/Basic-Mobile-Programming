package edu.bluejack25_1.latihan_uts;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.bluejack25_1.latihan_uts.helper.DatabaseHelper;
import edu.bluejack25_1.latihan_uts.model.Note;

public class NoteDetailActivity extends AppCompatActivity {

    private TextView tvDetailBody, tvDetailTitle, tvDetailLastEdited;
    private DatabaseHelper dbhelper;
    private long noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);

        dbhelper = new DatabaseHelper(this);
        tvDetailBody = findViewById(R.id.tvDetailBody);
        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailLastEdited = findViewById(R.id.tvDetailLastEdited);

        noteId = getIntent().getLongExtra("NOTE_ID", -1);

        if (noteId != -1){
            loadNoteDetails();
        }

    }

    public void loadNoteDetails(){
        Note note = dbhelper.getNote(noteId);
        tvDetailTitle.setText(note.getTitle());
        tvDetailBody.setText(note.getBody());
        String formattedDate =DatabaseHelper.formatTimestamp(note.getLastEditedAt());
        tvDetailLastEdited.setText("Last Edited: " + formattedDate);
    }

}