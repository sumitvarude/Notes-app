package com.example.sumitvar.snotes.helper;

import android.os.AsyncTask;
import android.util.Log;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by sumitvar on 29/09/15.
 */
public class NotesGetter extends AsyncTask<Void, Void, Map>{

    private URL url;


    public NotesGetter(String url) {
        super();
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Map doInBackground(Void... params) {
        HttpURLConnection connection = null;
        Log.d("aala re", "doInBackground ");
        String response = null;
        try {
            connection = (HttpURLConnection) this.url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            InputStream responseStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            responseStreamReader.close();

            response = stringBuilder.toString();
            Log.d("response is  >>>>>>>>" , response);
        } catch (IOException e) {
            Log.d("exception >>>>>" , response);
            e.printStackTrace();
        }

        Map notes = null;
        Log.d("all re \n", response);
        try {
            notes = (Map) new JSONParser().parse(response);
            Log.d("dada >>>>>>>>>>>>>>>>>", notes.toString());
            Map object = (Map) notes.get("1");
            Log.d("title >>>>>>>", String.valueOf(object.get("title")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return notes;
    }

    @Override
    protected void onPostExecute(Map notes) {
        super.onPostExecute(notes);
        return;
    }

}
