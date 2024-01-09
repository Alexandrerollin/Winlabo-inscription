package com.example.winlabo;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Enregistrement1 extends AppCompatActivity {


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
            Intent intent = new Intent(Enregistrement1.this, MainActivity.class);
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
        setContentView(R.layout.activity_enregistrement_1);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.enr1);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(Enregistrement1.this, Traitement3.class);
        Intent PreviousIntent2 = new Intent(Enregistrement1.this, Derogation1.class);
        Intent NextIntent = new Intent(Enregistrement1.this, Enregistrement2.class);

        String previousActivity = getIntent().getStringExtra("previousActivity");

        Button previousButton = (Button) findViewById(R.id.Precedent32);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "valeur previous activity: " + previousActivity);
                if ("Traitement3".equals(previousActivity)) {
                    startActivity(previousIntent);
                } else if ("Derogation1".equals(previousActivity)) {
                    startActivity(PreviousIntent2);
                }
            }
        });

        Button nextButton = (Button) findViewById(R.id.Suivant32);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(NextIntent);
            }
        });
    }
}

