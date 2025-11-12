package edu.bluejack25_1.latihan_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.bluejack25_1.latihan_uts.adapter.NoteAdapter;
import edu.bluejack25_1.latihan_uts.helper.DatabaseHelper;
import edu.bluejack25_1.latihan_uts.helper.SessionManager;
import edu.bluejack25_1.latihan_uts.model.Note;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private DatabaseHelper dbHelper;
    private TextView tvUsername;
    private RecyclerView rvNotes;
    private NoteAdapter noteAdapter;
    private ArrayList<Note> noteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sessionManager = new SessionManager(this);
        dbHelper = new DatabaseHelper(this);

        tvUsername = findViewById(R.id.tvUsername);
        rvNotes = findViewById(R.id.rvNotes);

        if (sessionManager.isLoggedIn()) {
            tvUsername.setText("Welcome, " + sessionManager.getUsername());
        }

        else {
            goToLogin();
            return;
        }

        rvNotes.setLayoutManager(new LinearLayoutManager(this));
    }

    private void goToLogin() {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        // TODO: Set flags agar user tidak bisa kembali ke MainActivity dari LoginActivity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }

    private void loadNotes() {
        noteList = dbHelper.getAllNotes();
        noteAdapter = new NoteAdapter(noteList, new NoteAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(Note note) {
                Intent intent = new Intent(MainActivity.this, NoteDetailActivity.class);
                intent.putExtra("NOTE_ID", note.getId());
                startActivity(intent);
            }
        });
        rvNotes.setAdapter(noteAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO: Lakukan inflate menu dari main_menu.xml
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_create_note) {
            // TODO: Pindah ke CreateNoteActivity
            Intent intent = new Intent(MainActivity.this, CreateNoteActivity.class);
            startActivity(intent);

            return true;
        } else if (id == R.id.action_logout) {
            sessionManager.logout();
            // TODO: Pindah ke LoginActivity
            goToLogin();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}