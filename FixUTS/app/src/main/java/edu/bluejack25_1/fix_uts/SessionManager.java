package edu.bluejack25_1.fix_uts;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {

    private SharedPreferences prefs;
    private Context context;
    private SharedPreferences.Editor editor;

    public SessionManager(Context context){
        this.context = context;
        prefs = context.getSharedPreferences("AppSession", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void saveLogin(){
        editor.putBoolean("isLoggedIn", true);
        editor.apply();
    }

    public boolean isLoggedIn(){
        return prefs.getBoolean("isLoggedIn", false);
    }

    public void logout(){
        editor.clear();
        editor.apply();
    }

}
