package com.example.sumitvar.snotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.sumitvar.snotes.helper.Note;
import com.example.sumitvar.snotes.helper.NoteSaver;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText titleText;
    private EditText contentText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        this.titleText = (EditText) findViewById(R.id.note_title);
        this.contentText = (EditText) findViewById(R.id.note_content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_note, menu);
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

    public void saveNote(View view) {
        String title = String.valueOf(titleText.getText());
        String content = String.valueOf(contentText.getText());
        Note note = new Note(title,content);
        NoteSaver noteSaver = new NoteSaver("http://10.0.2.2:8800/saveNote", note, this);
        noteSaver.execute();

        Intent mainIntent = new Intent(CreateNoteActivity.this, MainActivity.class);
        mainIntent.putExtra("title",title);
        mainIntent.putExtra("content",content);
        startActivity(mainIntent);
        finish();
    }
}
