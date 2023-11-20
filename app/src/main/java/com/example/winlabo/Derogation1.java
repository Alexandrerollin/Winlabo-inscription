package com.example.winlabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Derogation1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derogation1);

        Intent previousIntent = new Intent(Derogation1.this, ChoixTraitementDerogation.class);
        Intent nextIntent = new Intent(Derogation1.this, Derogation2.class);

        Button previousButton = (Button) findViewById(R.id.Precedent11);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant12);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
            }
        });
    }
}