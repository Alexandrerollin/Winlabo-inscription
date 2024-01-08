package com.example.winlabo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;

import android.os.AsyncTask;

public class ConnectToServer {

    public interface OnTaskCompleted {
        void onTaskCompleted(String result);
    }

    public static class CheckCredentialsTask extends AsyncTask<String, Void, String> {
        private OnTaskCompleted listener;

        public CheckCredentialsTask(OnTaskCompleted listener) {
            this.listener = listener;
        }

        @Override
        protected String doInBackground(String... params) {
//            String pseudo = params[0];
//            String motdepasse = params[1];
//            String url = "http://mobile.winqual.com/Connexion.php?pseudo=" + pseudo + "&motdepasse=" + motdepasse;
//            String url = "https://google.com";

            String url = params[0];

            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setConnectTimeout(5000);
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    return response.toString();
                } else {
                    return "Erreur de réponse du serveur : " + responseCode;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return "Erreur de connexion au serveur" + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            listener.onTaskCompleted(result);
        }
    }
}