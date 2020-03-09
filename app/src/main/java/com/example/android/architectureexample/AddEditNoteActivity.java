package com.example.android.architectureexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddEditNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.example.android.architectureexample.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.example.android.architectureexample.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY = "com.example.android.architectureexample.EXTRA_PRIORITY";
    public static final String EXTRA_ID = "com.example.android.architectureexample.EXTRA_ID";

    private EditText titleEt;
    private EditText descriptionEt;
    private NumberPicker priorityNp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        titleEt = (EditText) findViewById(R.id.title_et);
        descriptionEt = (EditText) findViewById(R.id.description_et);
        priorityNp = (NumberPicker) findViewById(R.id.priority_np);

        priorityNp.setMinValue(1);
        priorityNp.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Note");
            titleEt.setText(intent.getStringExtra(EXTRA_TITLE));
            descriptionEt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            priorityNp.setValue(intent.getIntExtra(EXTRA_PRIORITY, 1));
        } else
            setTitle("Add Note");
    }

    private void saveNote() {
        String title = titleEt.getText().toString();
        String description = descriptionEt.getText().toString();
        int priority = priorityNp.getValue();

        if (title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Title and description cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (-1 != id) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
