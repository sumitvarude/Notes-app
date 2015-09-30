package com.example.sumitvar.snotes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.sumitvar.snotes.helper.NotesGetter;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView noteTitle;
    private TextView noteContent;
    private Context context;
    public void setNotes(Map notes) {
        this.notes = notes;
    }

    private Map notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.noteTitle = (TextView) findViewById(R.id.titlePanel);
        this.noteContent = (TextView) findViewById(R.id.contentPanel);
        this.context = getApplicationContext();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.v("in ", "RESUME ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        NotesGetter notesGetter = new NotesGetter("http://10.0.2.2:8800/getNotes");
        try {
            this.notes = notesGetter.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Map note = (Map) this.notes.get("1");
//        Log.d("title >>>>>>>", String.valueOf(object.get("title")));
        Intent noteIntent = this.getIntent();
        String title = String.valueOf(note.get("title"));
        String content = String.valueOf(note.get("content"));
        this.noteTitle.setText(title);
        this.noteContent.setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createNote(MenuItem item) {
        Intent createNoteIntent = new Intent(this, CreateNoteActivity.class);
        startActivityForResult(createNoteIntent,RESULT_OK);
        finish();
    }
}
