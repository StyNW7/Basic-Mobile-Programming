package id.example.pert4;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import id.example.pert4.helper.WishlistDatabaseHelper;
import id.example.pert4.model.Product;

public class ProductDetailActivity extends AppCompatActivity {
    private TextView txtTitle, txtPrice, txtDescription;
    private Button btnAddToWL;

    private Product theProduct = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtTitle = findViewById(R.id.txtTitle);
        txtPrice = findViewById(R.id.txtPrice);
        txtDescription = findViewById(R.id.txtDescription);
        btnAddToWL = findViewById(R.id.btnAddToWishlist);

        Product data = (Product) getIntent().getSerializableExtra("data");
        theProduct = data;
        txtTitle.setText(data.getName());
        txtPrice.setText("IDR "+data.getPrice());
        txtDescription.setText(data.getDescription());

        //handle button
        WishlistDatabaseHelper helper = new WishlistDatabaseHelper(getApplicationContext());
        boolean isInWL = helper.isInWishlist(theProduct.getId());

        if(isInWL){
            btnAddToWL.setText("Remove From Wishlist");
        }
        else{
            btnAddToWL.setText("Add To Wishlist");
        }

        btnAddToWL.setOnClickListener(v -> {
            if(isInWL){
                helper.removeFromWishlist(theProduct.getId());
                btnAddToWL.setText("Add To Wishlist");
            }
            else{
                helper.addToWishlist(theProduct);
                btnAddToWL.setText("Remove From Wishlist");
            }
        });
    }
}