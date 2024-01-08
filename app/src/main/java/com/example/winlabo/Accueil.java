package com.example.winlabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Accueil extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.profil) {
            // Gérer le clic sur "Profil"
            return true;
        } else if (itemId == R.id.deconnexion) {
            // Gérer le clic sur "Déconnexion"
            deconnecterUtilisateur();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void deconnecterUtilisateur() {
        // Effacer les données de session (SharedPreferences, etc.)
        SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Rediriger vers MainActivity ou une autre activité d'accueil
        Intent intent = new Intent(Accueil.this, MainActivity.class);
        startActivity(intent);
        finish(); // Fermer l'activité actuelle
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.accueil);
        layout.setBackgroundColor(Color.WHITE);

        Intent intent = new Intent(Accueil.this, DeclarationEvenement1.class);

        ImageView imageView = (ImageView) findViewById(R.id.Declarerunevenement);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }
}