package com.example.olenka.fitforyou;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CalculateCalories extends AppCompatActivity {

    private EditText growth, weight, age;
    int _growth, _weight, _age;

    TextView calories;
    private double calc_calories = 0;

    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_calories);

        calories = (TextView) findViewById(R.id.calories);
        btnCalculate = (Button)findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                growth = (EditText)findViewById(R.id.etGrowth);
                _growth = new Integer(growth.getText().toString()).intValue();
                weight = (EditText)findViewById(R.id.etWeight);
                _weight = new Integer(weight.getText().toString()).intValue();
                age = (EditText)findViewById(R.id.etAge);
                _age = new Integer(age.getText().toString()).intValue();

                //Формула Миффлина — Сан Жеора для жінок
                calc_calories = ((10*_weight) + (6.25*_growth) - (5*_age) - 161)*1.375;
                String result = new Double(calc_calories).toString();
                calories.setText(""+result);
            }
        });
    }
}
