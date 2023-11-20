package com.example.winlabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeclarationEvenement6 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_evenement6);

        Intent previousIntent = new Intent(DeclarationEvenement6.this, DeclarationEvenement5.class);
        Intent nextIntent = new Intent(DeclarationEvenement6.this, ChoixTraitementDerogation.class);

        Button previousButton = (Button) findViewById(R.id.Precedent6);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant6);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
            }
        });
    }
}