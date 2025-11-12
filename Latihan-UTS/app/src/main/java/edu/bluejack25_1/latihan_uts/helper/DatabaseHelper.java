package edu.bluejack25_1.latihan_uts.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import edu.bluejack25_1.latihan_uts.model.Note;

public class DatabaseHelper extends SQLiteOpenHelper {

    // TO-DO
    /*
        1. Variable
        2. onCreateTable
        3. onUpgradeTable
        4. createNote
        5. getNote
        6. getAllNotes
        7. formatTimeStamp
     */

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "NotesDB";
    private static final String TABLE_NOTES = "notes";

    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_BODY = "body";
    private static final String KEY_LAST_EDITED_AT = "lastEditedAt";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NOTES_TABLE = "CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TITLE + " TEXT,"
                + KEY_BODY + " TEXT,"
                + KEY_LAST_EDITED_AT + " INTEGER"
                + ")";
        db.execSQL(CREATE_NOTES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public void addNote(Note note){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, note.getTitle());
        values.put(KEY_BODY, note.getBody());
        values.put(KEY_LAST_EDITED_AT, note.getLastEditedAt());

        db.insert(TABLE_NOTES, null, values);
        db.close();

    }

    public Note getNote(long id){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTES, new String[]{KEY_ID, KEY_TITLE, KEY_BODY, KEY_LAST_EDITED_AT},
                KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Note note = new Note (
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getLong(3)
        );

        cursor.close();
        db.close();
        return note;

    }

    public ArrayList<Note> getAllNotes(){
        ArrayList<Note> noteList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NOTES + " ORDER BY " +KEY_LAST_EDITED_AT +" DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                Note note = new Note();
                note.setId(cursor.getLong(cursor.getColumnIndexOrThrow(KEY_ID)));
                note.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(KEY_TITLE)));
                note.setBody(cursor.getString(cursor.getColumnIndexOrThrow(KEY_BODY)));
                note.setLastEditedAt(cursor.getLong(cursor.getColumnIndexOrThrow(KEY_LAST_EDITED_AT)));
                noteList.add(note);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return noteList;
    }

    public static String formatTimestamp(long timestamp){
        try {
            SimpleDateFormat sdf =new SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault());
            Date date = new Date();
            return sdf.format(date);
        }
        catch (Exception e){
            return String.valueOf(timestamp);
        }
    }

}
