package com.example.winlabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Enregistrement2 extends AppCompatActivity {


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
            Intent intent = new Intent(Enregistrement2.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_2);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.enr2);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(Enregistrement2.this, Accueil.class);

        Button previousButton = (Button) findViewById(R.id.Precedent33);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        // Obtenir l'intent actuel
        Intent intent = getIntent();

        // Obtenir les données supplémentaires de l'intent
        String ref = intent.getStringExtra("ref");
//        String error = intent.getStringExtra("erreur");

        // Utiliser les données récupérées
        TextView refTextView = findViewById(R.id.textviewenregistrement);
        refTextView.setText(ref);

//        TextView errorTextView = findViewById(R.id.textviewenregistrement);
//        errorTextView.setText(error);
    }
}