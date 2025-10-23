package id.example.pert4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import id.example.pert4.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        prepare();
    }

    private void prepare() {
        //ganti findviewbyid jadi setup binding.
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //langsung btnLogin bisa dipanggil lewat binding.
        binding.btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == binding.btnLogin.getId()) {

            String username = binding.txtUsername.getText().toString();

            //TODO: masukkin username ke SharedPreference
            SharedPreferences sp = getSharedPreferences("configuser", MODE_PRIVATE);
            sp.edit().putString("username", username).apply();

            //pindah ke MainActivity. gausah bawa username karena nnti di sana kita mau ambil pakai SharedPreference
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        }

    }
}