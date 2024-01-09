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

public class Derogation1 extends AppCompatActivity {


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
            Intent intent = new Intent(Derogation1.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected void saveDerogation(){
        String descriptifDerogation = ((EditText) findViewById(R.id.descriptifDerogation)).getText().toString();
        Log.d(TAG, "Descriptif dérogation enregistré. " + descriptifDerogation);

        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("LaDerogation", descriptifDerogation);
        editor.apply();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derogation1);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.dero1);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(Derogation1.this, ChoixTraitementDerogation.class);
        Intent nextIntent = new Intent(Derogation1.this, Enregistrement1.class);
        nextIntent.putExtra("previousActivity", "Derogation1");


        Button previousButton = (Button) findViewById(R.id.Precedent11);

        // Récupérer le descriptif stocké sur le telephone avec editor
        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        String descriptifDero = sharedPreferences.getString("LaDerogation", "");

        // réafficher dans la zone de texte
        ((EditText) findViewById(R.id.descriptifDerogation)).setText(descriptifDero);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
                saveDerogation();
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant12);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
                saveDerogation();
            }
        });
    }
}