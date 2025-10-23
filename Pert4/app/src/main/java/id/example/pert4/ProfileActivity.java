package id.example.pert4;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import id.example.pert4.adapter.ItemAdapter;
import id.example.pert4.databinding.ActivityProfileBinding;
import id.example.pert4.helper.WishlistDatabaseHelper;
import id.example.pert4.model.Product;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding binding;
    private ArrayList<Product> arrProduct = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        prepare();
        getUsername();
        getWishlist();
    }

    private void prepare() {
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.rvWishlist.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true));
    }

    private void getUsername() {
        //TODO: get username dari sharedpref masukkin ke txtUsername

    }

    private void getWishlist() {
        //TODO: get wishlist dari database, masukkin ke rvWishlist


    }

}