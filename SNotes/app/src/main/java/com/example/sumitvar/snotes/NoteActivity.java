package com.example.sumitvar.snotes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import java.util.Map;

/**
 * Created by sumitvar on 05/10/15.
 */
public class NoteActivity extends AppCompatActivity{
    Map note = null;
    EditText titleTextField;
    EditText contentTextField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity_layout);
        Intent intent = getIntent();
        this.note = (Map) intent.getSerializableExtra("note");
        Log.d("onCreate note = ", String.valueOf(this.note));
        this.titleTextField = (EditText) findViewById(R.id.title_edittext);
        this.contentTextField = (EditText) findViewById(R.id.content_edittext);
        Log.d("note title is = ", (String) this.note.get("title"));
        Log.d("note content is = ", (String) this.note.get("content"));
        this.titleTextField.setText((CharSequence) this.note.get("title"));
        this.contentTextField.setText((CharSequence) this.note.get("content"));
    }
}
