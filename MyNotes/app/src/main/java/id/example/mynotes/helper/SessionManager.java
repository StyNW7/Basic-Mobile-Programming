package id.example.mynotes.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private Context context;

    private static final String PREF_NAME = "AppSession";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";

    public SessionManager(Context context) {
        this.context = context;
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveLogin(String username) {
        // TODO: Simpan flag isLoggedIn jadi true dan simpan username
        editor.putBoolean(KEY_IS_LOGGED_IN, true);
        editor.putString(KEY_USERNAME, username);
        editor.apply();
    }

    public boolean isLoggedIn() {
        // TODO: Cek apakah flag isLoggedIn bernilai true (default false)
        return prefs.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public String getUsername() {
        // TODO: Ambil username yang disimpan (default string kosong)
        return prefs.getString(KEY_USERNAME, "");
    }

    public void logout() {
        // TODO: Hapus semua data di SharedPreferences
        editor.clear();
        editor.apply();
    }

}