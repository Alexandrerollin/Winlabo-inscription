package com.example.winlabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeclarationEvenement3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_evenement3);

        Intent previousIntent = new Intent(DeclarationEvenement3.this, DeclarationEvenement2.class);
        Intent nextIntent = new Intent(DeclarationEvenement3.this, DeclarationEvenement4.class);

        Button previousButton = (Button) findViewById(R.id.Precedent3);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant3);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
            }
        });
    }
}