package com.example.bmicalculator;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText weightInput, feetInput, inchesInput;
    private Button calculateBtn;
    private TextView bmiResult, motivationQuote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weightInput = findViewById(R.id.weightInput);
        feetInput = findViewById(R.id.feetInput);
        inchesInput = findViewById(R.id.inchesInput);
        calculateBtn = findViewById(R.id.calculateBtn);
        bmiResult = findViewById(R.id.bmiResult);
        motivationQuote = findViewById(R.id.motivationQuote);

        calculateBtn.setOnClickListener(v -> calculateBMI());
    }

    private void calculateBMI() {
        String weightText = weightInput.getText().toString();
        String feetText = feetInput.getText().toString();
        String inchesText = inchesInput.getText().toString();

        if (weightText.isEmpty() || feetText.isEmpty() || inchesText.isEmpty()) {
            Toast.makeText(this, "Please enter valid values", Toast.LENGTH_SHORT).show();
            return;
        }

        float weight = Float.parseFloat(weightText);
        float feet = Float.parseFloat(feetText);
        float inches = Float.parseFloat(inchesText);

        float heightInMeters = ((feet * 12) + inches) * 0.0254f; // Convert feet & inches to meters
        float bmi = weight / (heightInMeters * heightInMeters);
        updateUI(bmi);
    }

    private void updateUI(float bmi) {
        String resultText = "Your BMI: " + String.format("%.2f", bmi);
        int color;
        String quote;
        int quoteColor;

        if (bmi < 18.5) {
            resultText += " (Underweight)";
            color = Color.parseColor("#00FFFF"); // Bright Cyan
            quote = "You're light as a feather! Nourish yourself well 💪🍽️";
            quoteColor = Color.parseColor("#1E90FF"); // Dodger Blue for quote
        } else if (bmi < 24.9) {
            resultText += " (Healthy)";
            color = Color.parseColor("#32CD32"); // Emerald Green
            quote = "You're in great shape! Keep moving forward 🚀🏋️‍♂️";
            quoteColor = Color.parseColor("#228B22"); // Forest Green for quote
        } else if (bmi < 29.9) {
            resultText += " (Overweight)";
            color = Color.parseColor("#FFA500"); // Warm Orange
            quote = "A little effort every day goes a long way! 🌟🏃‍♂️";
            quoteColor = Color.parseColor("#FF6347"); // Tomato for quote
        } else {
            resultText += " (Obese)";
            color = Color.parseColor("#DC143C"); // Deep Crimson
            quote = "Health is the greatest wealth! Start today ❤️💪";
            quoteColor = Color.parseColor("#B22222"); // Firebrick for quote
        }

        bmiResult.setText(resultText);
        bmiResult.setTextColor(color);
        motivationQuote.setText(quote);
    }
}
