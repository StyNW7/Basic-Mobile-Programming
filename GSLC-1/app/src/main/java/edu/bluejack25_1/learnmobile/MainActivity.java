package edu.bluejack25_1.learnmobile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView greetingText;
    private TextView nameText;
    private EditText inputDisplay;
    private Button btnClear;
    private Button btnDelete;

    // White or Grey card buttons
    private CardView card1, card2, card3, card4;

    // Purple pill buttons
    private CardView pill1, pill2, pill3, pill4, pill5, pill6, pill7, pill8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        initializeViews();

        // Set greeting based on time of day
        setGreeting();

        // Set user name
        nameText.setText("NW");

        // Setup all click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        // Header views
        greetingText = findViewById(R.id.greetingText);
        nameText = findViewById(R.id.nameText);

        // Input and action buttons
        inputDisplay = findViewById(R.id.inputDisplay);
        btnClear = findViewById(R.id.btnClear);
        btnDelete = findViewById(R.id.btnDelete);

        // White card buttons
        card1 = findViewById(R.id.card1);
        card2 = findViewById(R.id.card2);
        card3 = findViewById(R.id.card3);
        card4 = findViewById(R.id.card4);

        // Purple pill buttons
        pill1 = findViewById(R.id.pill1);
        pill2 = findViewById(R.id.pill2);
        pill3 = findViewById(R.id.pill3);
        pill4 = findViewById(R.id.pill4);
        pill5 = findViewById(R.id.pill5);
        pill6 = findViewById(R.id.pill6);
        pill7 = findViewById(R.id.pill7);
        pill8 = findViewById(R.id.pill8);
    }

    private void setGreeting() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        String greeting;
        if (hourOfDay >= 0 && hourOfDay < 12) {
            greeting = "Good Morning,";
        } else if (hourOfDay >= 12 && hourOfDay < 17) {
            greeting = "Good Afternoon,";
        } else {
            greeting = "Good Evening,";
        }

        greetingText.setText(greeting);
    }

    private void setupClickListeners() {
        // White card click listeners
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("1");
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("2");
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("3");
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("4");
            }
        });

        // Purple pill click listeners
        pill1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("1");
            }
        });

        pill2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("2");
            }
        });

        pill3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("3");
            }
        });

        pill4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("4");
            }
        });

        pill5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("5");
            }
        });

        pill6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("6");
            }
        });

        pill7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("7");
            }
        });

        pill8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                appendToInput("8");
            }
        });

        // Clear button listener
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearInput();
            }
        });

        // Delete button listener
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLastCharacter();
            }
        });
    }

    // Method to append number to input display
    private void appendToInput(String number) {
        String currentText = inputDisplay.getText().toString();
        inputDisplay.setText(currentText + number);
    }

    // Method to clear all input
    private void clearInput() {
        inputDisplay.setText("");
        Toast.makeText(this, "Input cleared", Toast.LENGTH_SHORT).show();
    }

    // Method to delete last character
    private void deleteLastCharacter() {
        String currentText = inputDisplay.getText().toString();
        if (currentText.length() > 0) {
            inputDisplay.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            Toast.makeText(this, "Nothing to delete", Toast.LENGTH_SHORT).show();
        }
    }

}