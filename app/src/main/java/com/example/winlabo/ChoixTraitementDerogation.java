package com.example.winlabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ChoixTraitementDerogation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_traitement_derogation);

        Intent previousIntent = new Intent(ChoixTraitementDerogation.this, DeclarationEvenement6.class);
        Intent nextIntent = new Intent(ChoixTraitementDerogation.this, Traitement1.class);
        Intent nextIntent2 = new Intent(ChoixTraitementDerogation.this, Derogation1.class);

        Button previousButton = (Button) findViewById(R.id.Precedent7);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        Button suivantButton = (Button) findViewById(R.id.Suivant7);
        suivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton radioTraitement = (RadioButton) findViewById(R.id.radioTraitement);
                RadioButton radioDerogation = (RadioButton) findViewById(R.id.radioDerogation);
                if (radioTraitement.isChecked()) {
                    // Redirige vers la page Traitement
                    startActivity(nextIntent);
                } else if (radioDerogation.isChecked()) {
                    // Redirige vers la page Dérogation
                    startActivity(nextIntent2);
                }
            }
        });
    }
}