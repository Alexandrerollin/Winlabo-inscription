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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class ChoixTraitementDerogation extends AppCompatActivity {


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
            Intent intent = new Intent(ChoixTraitementDerogation.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

//        if(item.getItemId() == R.id.profile){
//            Toast.makeText(this, "Profile sélectionné", Toast.LENGTH_SHORT).show();
//            return true;
//        } else if (item.getItemId() == R.id.deconnexion) {
//            Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
//            return true;
//        }else {
//            return super.onOptionsItemSelected(item);
//        }
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_traitement_derogation);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.choix);
        layout.setBackgroundColor(Color.WHITE);

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