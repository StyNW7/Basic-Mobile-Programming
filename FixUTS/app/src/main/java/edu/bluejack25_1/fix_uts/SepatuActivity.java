package edu.bluejack25_1.fix_uts;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

import edu.bluejack25_1.fix_uts.model.Product;

public class SepatuActivity extends AppCompatActivity {

    ListView listSepatu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sepatu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listSepatu = (ListView) findViewById(R.id.listSepatu);
        ArrayList <Product> products = new ArrayList<>();

        Product prod1 = new Product(R.drawable.sepatu1, "Sepatu 1", "50000");
        products.add(prod1);

        Product prod2 = new Product(R.drawable.sepatu2, "Sepatu 2", "50000");
        products.add(prod2);

        Product prod3 = new Product(R.drawable.sepatu3, "Sepatu 3", "50000");
        products.add(prod3);

        // Create Adapter
        InputAdapter sepatuAdapter = new InputAdapter(this, R.layout.item, products);
        // setAdapter
        listSepatu.setAdapter(sepatuAdapter);

    }

}