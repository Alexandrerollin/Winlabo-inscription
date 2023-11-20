package com.example.winlabo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DeclarationEvenement5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_evenement5);

        Intent previousIntent = new Intent(DeclarationEvenement5.this, DeclarationEvenement4.class);
        Intent nextIntent = new Intent(DeclarationEvenement5.this, DeclarationEvenement6.class);

        Button previousButton = (Button) findViewById(R.id.Precedent5);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant5);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioCritique);
                int selectedId = radioGroup.getCheckedRadioButtonId();

                // Vérifiez si un bouton radio est sélectionné
                if (selectedId == -1) {
                    // Aucun bouton radio n'est sélectionné, affichez un message à l'utilisateur
                    Toast.makeText(DeclarationEvenement5.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
                } else {
                    // Un bouton radio est sélectionné, vous pouvez démarrer la prochaine activité
                    startActivity(nextIntent);
                }
            }
        });
    }
}
