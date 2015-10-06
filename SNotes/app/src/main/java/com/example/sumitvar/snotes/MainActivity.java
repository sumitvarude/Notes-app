package com.example.sumitvar.snotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.sumitvar.snotes.helper.NotesGetter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private TextView noteTitle;
    private TextView noteContent;
    private ListView notesList;
    private Map notes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.noteTitle = (TextView) findViewById(R.id.titlePanel);
        this.noteContent = (TextView) findViewById(R.id.contentPanel);
        this.notesList = (ListView) findViewById(R.id.notes_list);
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
        Map note;
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 1; i <= this.notes.size(); i++) {
            note = (Map) this.notes.get(String.valueOf(i));
            list.add(String.valueOf(note.get("title")));
        }

        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        this.notesList.setAdapter(adapter);
        this.notesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent noteActivityIntent = new Intent(MainActivity.this, NoteActivity.class);
                Log.d("onItemClick =", String.valueOf(position));
                Map selectedNote = (Map) notes.get(String.valueOf(position+1));
                Log.d("selected note is =", (String) selectedNote.get("title"));
                noteActivityIntent.putExtra("note", (Serializable) selectedNote);
                startActivity(noteActivityIntent);
            }
        });
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
