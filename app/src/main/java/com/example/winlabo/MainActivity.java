package com.example.winlabo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private Button Sinscrire, Seconnecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Sinscrire = (Button) findViewById(R.id.Sinscrire);
//        Seconnecter = (Button) findViewById(R.id.SeConnecter);
//
//        Seconnecter.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), )
//            }
//        });

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_center);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.main);
        layout.setBackgroundColor(Color.WHITE);

        Intent previousIntent = new Intent(MainActivity.this, Inscription.class);
        Intent nextIntent = new Intent(MainActivity.this, SeConnecter.class);

        Button previousButton = (Button) findViewById(R.id.Sinscrire);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        Button nextButton = (Button) findViewById(R.id.SeConnecter);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
            }
        });
    }
}
