package id.example.mynotes;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import id.example.mynotes.helper.DatabaseHelper;
import id.example.mynotes.model.Note;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText etNoteTitle, etNoteBody;
    private Button btnSaveNote;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        dbHelper = new DatabaseHelper(this);
        etNoteTitle = findViewById(R.id.etNoteTitle);
        etNoteBody = findViewById(R.id.etNoteBody);
        btnSaveNote = findViewById(R.id.btnSaveNote);

        btnSaveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNoteToDB();
            }
        });
    }

    private void saveNoteToDB() {

        String title = etNoteTitle.getText().toString().trim();
        String body = etNoteBody.getText().toString().trim();
        long currentTime = System.currentTimeMillis();

        if (title.isEmpty()) {
            Toast.makeText(this, "Title tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return;
        }

        Note newNote = new Note(0, title, body, currentTime);
        dbHelper.addNote(newNote);

        Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show();
        finish();

    }
}