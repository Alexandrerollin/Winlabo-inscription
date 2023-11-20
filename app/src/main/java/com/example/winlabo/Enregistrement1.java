package com.example.winlabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Enregistrement1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_1);

        Intent previousIntent = new Intent(Enregistrement1.this, Traitement3.class);
        Intent PreviousIntent2 = new Intent(Enregistrement1.this, Derogation2.class);
        Intent NextIntent = new Intent(Enregistrement1.this, Enregistrement2.class);

        String previousActivity = getIntent().getStringExtra("previousActivity");

        Button previousButton = (Button) findViewById(R.id.Precedent32);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Traitement3".equals(previousActivity)) {
                    startActivity(previousIntent);
                } else if ("Derogation2".equals(previousActivity)) {
                    startActivity(PreviousIntent2);
                }
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant32);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NextIntent);
            }
        });
    }
}

