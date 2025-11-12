package edu.bluejack25_1.fix_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText editName, editOrder, editQuantity;

    private Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);
        editName = findViewById(R.id.editName);
        editOrder = findViewById(R.id.editOrder);
        editQuantity = findViewById(R.id.editQuantity);
        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = dbHelper.insertOrder(editName.getText().toString().trim(),
                        editOrder.getText().toString().trim(),
                        editQuantity.getText().toString().trim());
                if (result){
                    Toast.makeText(InputActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InputActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(InputActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}