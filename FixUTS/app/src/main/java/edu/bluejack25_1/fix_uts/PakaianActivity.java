package edu.bluejack25_1.fix_uts;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import edu.bluejack25_1.fix_uts.model.Product;

public class PakaianActivity extends AppCompatActivity {

    ListView pakaianList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pakaian);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pakaianList = findViewById(R.id.listPakaian);
        ArrayList<Product> products = new ArrayList<>();

        Product prod1 = new Product(R.drawable.baju1, "Baju 1", "100000");
        products.add(prod1);

        InputAdapter pakaianAdapter = new InputAdapter(this, R.layout.item, products);
        pakaianList.setAdapter(pakaianAdapter);

    }
}