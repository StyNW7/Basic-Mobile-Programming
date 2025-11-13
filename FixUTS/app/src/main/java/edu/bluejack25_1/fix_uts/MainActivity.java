package edu.bluejack25_1.fix_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.bluejack25_1.fix_uts.model.Order;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private OrderAdapter orderAdapter;
    private ArrayList<Order> orderList;
    private RecyclerView orderView;

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

        // Hafal ini

        orderView = findViewById(R.id.rvOrder);
        dbHelper = new DatabaseHelper(this);
        orderList = dbHelper.getAllOrders();

        orderAdapter = new OrderAdapter(orderList);

        orderView.setAdapter(orderAdapter);
        orderView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    // Dan ini

    @Override
    protected void onResume() {
        orderList = dbHelper.getAllOrders();
        orderAdapter = new OrderAdapter(orderList);
        orderView.setAdapter(orderAdapter);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, ProfileActivity.class);
        switch (item.getItemId()){
            case R.id.nav_profile:
                intent = new Intent(this, ProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_pakayan:
                intent = new Intent(this, PakaianActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_sepatu:
                intent = new Intent(this, SepatuActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_pesan:
                intent = new Intent(this, InputActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}