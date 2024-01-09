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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DeclarationEvenement4 extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

//        if(item.getItemId() == R.id.profil){
//            Toast.makeText(this, "Profile sélectionné", Toast.LENGTH_SHORT).show();
//            return true;
//        } else
            if (item.getItemId() == R.id.deconnexion) {
            Intent intent = new Intent(DeclarationEvenement4.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void saveDescriptif(){
        String descriptifEvenement = ((EditText) findViewById(R.id.Descriptifevenement)).getText().toString();
        Log.d(TAG, "Descriptif évènement enregistré. " + descriptifEvenement);

        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /**
         Ce code utilise un éditeur de préférences partagées (editor) pour enregistrer l'ID d'une catégorie
         sélectionné dans les préférences partagées. L'ID de la catégorie (selectedSite.getID_LINUX_SMQ_PROCESSUS_CATEGORIE())
         est stocké avec la clé "idVategorie". La méthode apply() est ensuite appelée pour appliquer ces modifications
         aux préférences partagées de manière asynchrone. En résumé, cela permet de mémoriser l'ID de la catégorie sélectionné
         pour une utilisation ultérieure, par exemple, afin de pré-sélectionner la même catégorie lorsqu'on revient sur cette activité.
         */
        editor.putString("LeDescriptif", descriptifEvenement);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_evenement4);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.dec4);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(DeclarationEvenement4.this, DeclarationEvenement3.class);
        Intent nextIntent = new Intent(DeclarationEvenement4.this, DeclarationEvenement5.class);

        Button previousButton = (Button) findViewById(R.id.Precedent4);

        // Récupérer le descriptif stocké sur le telephone avec editor
        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        String descriptifStocké = sharedPreferences.getString("LeDescriptif", "");

        // réafficher dans la zone de texte
        ((EditText) findViewById(R.id.Descriptifevenement)).setText(descriptifStocké);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
                saveDescriptif();
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant4);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
                saveDescriptif();
            }
        });
    }
}