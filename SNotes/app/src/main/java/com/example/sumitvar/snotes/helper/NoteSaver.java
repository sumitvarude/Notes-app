package com.example.sumitvar.snotes.helper;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sumitvar on 28/09/15.
 */
public class NoteSaver extends AsyncTask<Void, Void, Boolean>{

    private final Note note;
    private URL saveNoteUrl;
    private Context context;

    public NoteSaver(String url, Note noteObject, Context context){

        try {
            this.saveNoteUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.d("In constructor", "saveNote ");
        this.note = noteObject;
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        Boolean flag = null;
        Log.d("IN the notesaver", "doInBackground ");
        try {
            HttpURLConnection connection = (HttpURLConnection) saveNoteUrl.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            String jsonParamsString = "{\"note\":{\"title\":\""+this.note.getNoteTitle()+"\",\"content\":\""+this.note.getNoteContent()+"\"}}";
            connection.setRequestProperty("Content-Type", "application/json");
            DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(jsonParamsString);
            outputStream.flush();
            outputStream.close();

            InputStream responseStream = new BufferedInputStream(connection.getInputStream());
            BufferedReader responseStreamReader = new BufferedReader(new InputStreamReader(responseStream));
            String line = "";
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = responseStreamReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            responseStreamReader.close();

            String response = stringBuilder.toString();
            flag = Boolean.valueOf(response);
            Log.d("flag is", String.valueOf(flag));

        } catch (IOException e) {
            Log.d("exception notesaver ", "doInBackground ");
            e.printStackTrace();
        }
        return flag;
    }

    protected void onPostExecute(Boolean isNoteSaved) {
        if(isNoteSaved){
            Toast toast = Toast.makeText(this.context, "Note saved to Server!", Toast.LENGTH_LONG);
            toast.show();
        }
        else{
            Toast toast = Toast.makeText(this.context, "Unable to save note to Server", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
