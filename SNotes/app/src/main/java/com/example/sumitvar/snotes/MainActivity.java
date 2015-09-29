package com.example.sumitvar.snotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView noteTitle;
    private TextView noteContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.noteTitle = (TextView) findViewById(R.id.titlePanel);
        this.noteContent = (TextView) findViewById(R.id.contentPanel);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent noteIntent = this.getIntent();
        String title = noteIntent.getStringExtra("title");
        String content = noteIntent.getStringExtra("content");
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
