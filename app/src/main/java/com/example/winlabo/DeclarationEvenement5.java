package com.example.winlabo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DeclarationEvenement5 extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.profil){
            Toast.makeText(this, "Profile sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.deconnexion) {
            Intent intent = new Intent(DeclarationEvenement5.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void saveRadio(){

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioCritique);
        int selectedId = radioGroup.getCheckedRadioButtonId();
        int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();

        // Vérifiez si un bouton radio est sélectionné
        if (selectedId == -1) {
            // Aucun bouton radio n'est sélectionné, affichez un message à l'utilisateur
            Toast.makeText(DeclarationEvenement5.this, "Veuillez sélectionner une option", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Aucun bouton radio sélectionné");
        } else {

            RadioButton selectedRadioButton = findViewById(selectedRadioButtonId);

            // Obtenez la valeur du RadioButton sélectionné
            String selectedValue = selectedRadioButton.getText().toString();

            // Faites quelque chose avec la valeur sélectionnée
            Log.d(TAG, "Valeur radio sélectionnée : " + selectedValue);

            SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("RadioCritique", selectedValue);
            editor.apply();
            // Un bouton radio est sélectionné, vous pouvez démarrer la prochaine activité
            //  Log.d(TAG, "Valeur radio : " + selectedId);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_evenement5);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.dec5);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(DeclarationEvenement5.this, DeclarationEvenement4.class);
        Intent nextIntent = new Intent(DeclarationEvenement5.this, DeclarationEvenement6.class);

        Button previousButton = (Button) findViewById(R.id.Precedent5);

        // Récupérer le radioButton stocké sur le téléphone avec editor
        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        String radioCheck = sharedPreferences.getString("RadioCritique", "Non");

        RadioGroup radioGroup = findViewById(R.id.radioCritique);

// Parcourir tous les RadioButton dans le RadioGroup
        for (int i = 0; i < radioGroup.getChildCount(); i++) {
            View view = radioGroup.getChildAt(i);
            if (view instanceof RadioButton) {
                RadioButton radioButton = (RadioButton) view;

                // Comparer la valeur du RadioButton avec la valeur stockée
                if (radioButton.getText().toString().equals(radioCheck)) {
                    // Sélectionner le RadioButton correspondant
                    radioButton.setChecked(true);
                    break; // Vous pouvez arrêter la boucle une fois que le RadioButton est sélectionné
                }
            }
        }

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRadio();
                startActivity(previousIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant5);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRadio();
                startActivity(nextIntent);

            }
        });
    }
}
