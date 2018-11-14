package com.example.harshit.notesadvance;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddEditNoteActivity extends AppCompatActivity {
    //It is not compulsory to name these variable value like this but it is a good practice
    public static final String EXTRA_ID="com.example.harshit.notesadvance.Extra_ID;";
    public static final String EXTRA_TITLE="com.example.harshit.notesadvance.Extra_Title;";
    public static final String EXTRA_DISCRIPTION="com.example.harshit.notesadvance.Extra_DESCRIPTION;";
    public static final String EXTRA_PRIORITY="com.example.harshit.notesadvance.Extra_PRIORITY;";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerPriority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerPriority = findViewById(R.id.number_picker);
        numberPickerPriority.setMinValue(1);
        numberPickerPriority.setMaxValue(10);
        numberPickerPriority.setValue(5);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        Intent intent=getIntent();
        if (intent.hasExtra(EXTRA_ID))
        {
            setTitle("Edit Note");
            editTextTitle.setText(intent.getStringExtra(EXTRA_TITLE));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DISCRIPTION));
            numberPickerPriority.setValue(intent.getIntExtra(EXTRA_PRIORITY,1));
        }
        else
        {
            setTitle("Add Note");
        }


    }
    private void saveNote()
    {
        String Title=editTextTitle.getText().toString();
        String Description=editTextDescription.getText().toString();
        int priority=numberPickerPriority.getValue();
        if(Title.trim().isEmpty()||Description.trim().isEmpty())
        {
            Toast.makeText(this,"Fields can't be empty",Toast.LENGTH_LONG).show();
            return;
        }
        Intent data=new Intent();
        data.putExtra(EXTRA_TITLE,Title);
        data.putExtra(EXTRA_DISCRIPTION,Description);
        data.putExtra(EXTRA_PRIORITY,priority);

        setResult(RESULT_OK,data);
        finish();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;

    }
    //for maintain clicking on menu item

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_btn:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
