package edu.bluejack25_1.fix_uts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private EditText edName, edOrderName, edQuantity;
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
        edName = findViewById(R.id.editName);
        edOrderName = findViewById(R.id.editOrder);
        edQuantity = findViewById(R.id.editQuantity);

        submitBtn = findViewById(R.id.submitBtn);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean insert = dbHelper.insertOrder(
                        edName.getText().toString().trim(),
                        edOrderName.getText().toString().trim(),
                        edQuantity.getText().toString().trim()
                );
                if (insert){
                    Toast.makeText(InputActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(InputActivity.this, MainActivity.class);
                    startActivity(intent);
                    AlertDialog.Builder alert = new AlertDialog.Builder(InputActivity.this);
                    alert.setTitle("Success");
                    alert.setMessage("Berhasil");
                    alert.setCancelable(true);
                    alert.show();
                }
                else {
                    Toast.makeText(InputActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
//                StringBuffer buff = new StringBuffer();
//                while(cursor.moveToNext())
//                buff.append("Nama" + cursor.getString(0) + "\n);
//                AlertDialog.Builder alert = new AlertDialog.Builder(this);
//                alert.setTitle("Orang");
//                alert.setCancelable(true);
//                alert.setMessage(buff);
//                alert.show();
            }
        });

    }
}