package com.example.latihanuas;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.example.latihanuas.fragments.DanceFragment;
import com.example.latihanuas.fragments.DdolFragment;
import com.example.latihanuas.fragments.JokesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bottomNavigationView = findViewById(R.id.bottomNav);

        //TODO 1A: by default tampilin DdolFragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, new DdolFragment())
                .commit();

        //TODO 1B: atur kalau tiap menu di klik, tampilkan fragment yang sesuai
//        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                Fragment fragmentToShow = null;
//
//                if(item.getItemId() == R.id.menu_ddol){
//                    fragmentToShow = new DdolFragment();
//                } else if (item.getItemId() == R.id.menu_dance) {
//                    fragmentToShow = new DanceFragment();
//                }
//
//                if(fragmentToShow != null){
//                    getSupportFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.frameLayout, fragmentToShow)
//                            .commit();
//                    return true; //kalau ini gak direturn true, ttp jalan, tapi menu yg dipilih ga berubah warna
//                }
//
//                return false;
//            }
//        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_ddol){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new DdolFragment())
                        .commit();
            }
            else if(item.getItemId() == R.id.menu_dance){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, new DanceFragment())
                        .commit();
            }
            else if(item.getItemId() == R.id.menu_jokes){
                changeFragment(new JokesFragment());
            }

            return true;
        });

    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}