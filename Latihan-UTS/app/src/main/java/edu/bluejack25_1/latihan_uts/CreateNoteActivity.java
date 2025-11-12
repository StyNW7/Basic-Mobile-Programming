package edu.bluejack25_1.latihan_uts;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.bluejack25_1.latihan_uts.helper.DatabaseHelper;
import edu.bluejack25_1.latihan_uts.model.Note;

public class CreateNoteActivity extends AppCompatActivity {

    private EditText etNoteTitle,etNoteBody;
    private Button saveBtn;
private DatabaseHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        dbhelper = new DatabaseHelper(this);
        etNoteBody = findViewById(R.id.etNoteBody);
        etNoteTitle = findViewById(R.id.etNoteTitle);

        saveBtn = findViewById(R.id.btnSaveNote);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savetoDB();
            }
        });

    }

    private void savetoDB(){
        String title = etNoteTitle.getText().toString().trim();
        String body = etNoteBody.getText().toString().trim();
        long time = System.currentTimeMillis();
        Note note = new Note(0, title, body, time);
        dbhelper.addNote(note);
        Toast.makeText(this, "Note added successfully", Toast.LENGTH_SHORT).show();
        finish();
    }

}