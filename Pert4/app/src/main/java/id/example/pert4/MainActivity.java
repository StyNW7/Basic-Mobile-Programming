package id.example.pert4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import id.example.pert4.adapter.ItemAdapter;
import id.example.pert4.model.Product;

public class MainActivity extends AppCompatActivity {
//    recyclerview, adapter, list datanya
    private RecyclerView rvItems;
    private ItemAdapter itemAdapter;
    private ArrayList<Product> arrData;

    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private TextView txtGreetings;
    private ImageButton btnMenu;

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

        rvItems = findViewById(R.id.rvItems);
        arrData = new ArrayList<Product>();
        for (int i = 1; i <= 100; i++) {
            arrData.add(new Product(i,"Product "+i, 10000+i, "Description product "+i));
        }
        itemAdapter = new ItemAdapter(arrData);

//        rvItems.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
//                                                            LinearLayoutManager.VERTICAL,
//                                                            false));
        rvItems.setLayoutManager(new GridLayoutManager(getApplicationContext(),
                                                        2,
                                                        GridLayoutManager.VERTICAL,
                                                        false));
        rvItems.setAdapter(itemAdapter);

        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navView);
        txtGreetings = findViewById(R.id.txtGreetings);
        btnMenu = findViewById(R.id.btnMenu);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_profile){
                    goToProfile();
                }
                else if(item.getItemId() == R.id.menu_signout){
                    // lakukan intent ke activity login. terus activity yg ini difinish.
                    signout();
                }

                return true;
            }
        });

        btnMenu.setOnClickListener(v->{
            drawerLayout.open();
        });
        checkUser();
    }

    private void goToProfile() {
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);
    }

    private void signout(){
        //tendang ke login
        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);
        finish();

        //TODO: hapus username dari shared pref

    }

    private void checkUser() {
        //TODO: ambil username dari shared pref

    }
}