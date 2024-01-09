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
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Traitement1 extends AppCompatActivity {


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
            Intent intent = new Intent(Traitement1.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void saveCauses(){
        String descriptifCauses = ((EditText) findViewById(R.id.descriptifCauses)).getText().toString();
        Log.d(TAG, "Descriptif causes enregistré. " + descriptifCauses);

        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Causes", descriptifCauses);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traitement1);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.tr1);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(Traitement1.this, ChoixTraitementDerogation.class);
        Intent nextIntent = new Intent(Traitement1.this, Traitement2.class);

        Button previousButton = (Button) findViewById(R.id.Precedent8);

        // Récupérer le descriptif stocké sur le telephone avec editor
        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        String descriptifCauses = sharedPreferences.getString("Causes", "");

        // réafficher dans la zone de texte
        ((EditText) findViewById(R.id.descriptifCauses)).setText(descriptifCauses);

        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
                saveCauses();
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant9);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
                saveCauses();
            }
        });
    }
}