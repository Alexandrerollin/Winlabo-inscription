package com.example.winlabo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Enregistrement1 extends AppCompatActivity {


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.profile){
            Toast.makeText(this, "Profile sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.deconnexion) {
            Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enregistrement_1);

        Intent previousIntent = new Intent(Enregistrement1.this, Traitement3.class);
        Intent PreviousIntent2 = new Intent(Enregistrement1.this, Derogation2.class);
        Intent NextIntent = new Intent(Enregistrement1.this, Enregistrement2.class);

        String previousActivity = getIntent().getStringExtra("previousActivity");

        Button previousButton = (Button) findViewById(R.id.Precedent32);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("Traitement3".equals(previousActivity)) {
                    startActivity(previousIntent);
                } else if ("Derogation2".equals(previousActivity)) {
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

