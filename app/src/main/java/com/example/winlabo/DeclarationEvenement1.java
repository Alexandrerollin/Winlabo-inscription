package com.example.winlabo;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class DeclarationEvenement1 extends AppCompatActivity {

    // Déclaration de la liste qui va contenir les noms des laboratoires
    private List<String> laboratoireNames = new ArrayList<>();

    // Méthode appelée pour créer le menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Méthode pour initialiser les sites/laboratoires
    protected void initSites() {
        // URL pour obtenir la liste des sites depuis un serveur PHP
        String url = "http://mobile.winqual.com/getsite.php";

        // Affichage d'un message et journalisation du clic sur ImageView
        Toast.makeText(DeclarationEvenement1.this, "ImageView cliqué!", Toast.LENGTH_SHORT).show();
        Log.d("TAG", "ImageView cliqué");

        // Tâche asynchrone pour récupérer les données du serveur
        ConnectToServer.CheckCredentialsTask task = new ConnectToServer.CheckCredentialsTask(new ConnectToServer.OnTaskCompleted() {
            @Override
            public void onTaskCompleted(String result) {
                try {
                    // Utilisation de Gson pour convertir la réponse JSON en liste d'objets Site
                    Gson gson = new Gson();
                    List<Site> sites = gson.fromJson(result, new TypeToken<List<Site>>() {
                    }.getType());

                    // Créer une liste de noms de laboratoires
                    // Nettoyage de la liste avant d'y ajouter à nouveau des éléments
                    laboratoireNames.clear();
                    // Remplissage de la liste des noms de laboratoires à partir des objets Site
                    for (Site site : sites) {
                        laboratoireNames.add(site.getNOM_DU_LABORATOIRE());
                        Log.d("TAG", "Nom du laboratoire : " + site.getNOM_DU_LABORATOIRE());
                    }

                    // Initialisation du Spinner avec la liste des noms de laboratoires
                    // On construit la liste déroulante à partir de notre tableau de sites laboratoireNames
                    Spinner spinnerLaboratoires = findViewById(R.id.spinnerLaboratoires);
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(DeclarationEvenement1.this, android.R.layout.simple_spinner_item, laboratoireNames);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerLaboratoires.setAdapter(adapter);

                    // Récupérez le nom du laboratoire sélectionné depuis les préférences partagées
                    SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                    String selectedSite = sharedPreferences.getString("nomLaboratoire", "");

                    // Si un site a été précédemment sélectionné, le définir dans le Spinner
                    if (!selectedSite.isEmpty()) {
                        int position = laboratoireNames.indexOf(selectedSite);
                        if (position != -1) {
                            spinnerLaboratoires.setSelection(position);
                        }
                    }

                    // Ajout d'un écouteur sur le Spinner pour gérer la sélection
                    spinnerLaboratoires.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                            // Récupération de l'élément sélectionné dans le Spinner
                            String selectedSite = parentView.getItemAtPosition(position).toString();

                            // Sauvegardez le nom du laboratoire sélectionné dans les préférences partagées
                            SharedPreferences sharedPreferences = getSharedPreferences("session", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("nomLaboratoire", selectedSite);
                            editor.apply();

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parentView) {
                            // Ne rien faire ici
                        }
                    });

                } catch (Exception e) {
                    Log.d("TAG", "Erreur : " + e.getMessage());
                }

            }
        });
        // Exécution de la tâche asynchrone pour obtenir les données du serveur
        task.execute(url);
    }
    // Méthode appelée lors de la sélection d'un élément dans le menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Gestion des actions du menu
        if (item.getItemId() == R.id.profil) {
            Toast.makeText(this, "Profile sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        } else if (item.getItemId() == R.id.deconnexion) {
            // Redirection vers l'activité MainActivity lors de la déconnexion
            Intent intent = new Intent(DeclarationEvenement1.this, MainActivity.class);
            startActivity(intent);
            finish();
//                Toast.makeText(this, "Deconnexion sélectionné", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            // Appel de la méthode de la classe parente si l'action n'est pas reconnue
            return super.onOptionsItemSelected(item);
        }
    }

    // Méthode appelée lors de la création de l'activité
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration_evenement1);

        // Personnalisation de l'ActionBar avec une couleur de fond
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.bleu4)));

        // Configuration de l'ActionBar pour afficher un layout personnalisé
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.action_bar_left);

        // Modification de la couleur de fond du layout principal
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.dec1);
        layout.setBackgroundColor(Color.WHITE);

        // Intent pour l'activité précédente et suivante
        Intent previousIntent = new Intent(DeclarationEvenement1.this, Accueil.class);
        Intent nextIntent = new Intent(DeclarationEvenement1.this, DeclarationEvenement2.class);

        // Bouton pour passer à l'activité précédente
        Button previousButton = (Button) findViewById(R.id.Precedent);

        initSites(); // Appel de la méthode pour initialiser les sites

        // Gestionnaire de clic sur le bouton précédent
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(previousIntent);
            }
        });

        // Bouton pour passer à l'activité suivante
        Button nextButton = (Button) findViewById(R.id.Suivant);

        // Gestionnaire de clic sur le bouton suivant
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(nextIntent);
            }
        });

    }
}