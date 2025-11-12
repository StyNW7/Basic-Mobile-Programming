package edu.bluejack25_1.pakaian_sepatu;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView tvName, tvQuantity;
    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        tvName = findViewById(R.id.tvName);
        tvQuantity = findViewById(R.id.tvQuantity);
        submitBtn = findViewById(R.id.btnSubmit);
        dbHelper = new DatabaseHelper(this);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = tvName.getText().toString().trim();
                String quantity = tvQuantity.getText().toString().trim();
                boolean insert = dbHelper.addOrder(name, quantity);
                if (insert){
                    Toast.makeText(InputActivity.this, "Add Order Succedd", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(InputActivity.this, "Add Order Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}