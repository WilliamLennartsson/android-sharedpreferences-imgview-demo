package com.example.sharedpreferencesimageviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView stateTextView;
    private EditText stateEditText;
    private ImageView imageView;

    private static final String SH_KEY = "MY_PREFERENCES_FILE";
    private static final String STATE_KEY = "state_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateTextView = findViewById(R.id.textViewState);
        stateEditText = findViewById(R.id.editTextState);
        imageView = findViewById(R.id.imageView1);

        // getString(R.string.test_with_vars, "will"); // Get string resource with dynamic var
        // stateTextView.setText();
        readState();
    }

    // Write state
    public void saveState() {
        // Get editText string - our state
        String state = stateEditText.getText().toString();
        // SharedPreferences
        SharedPreferences sh = getSharedPreferences(SH_KEY, MODE_PRIVATE);
        // Editor - Need this to write
        SharedPreferences.Editor editor = sh.edit();
        // Add change to sharedPreferences
        editor.putString(STATE_KEY, state);
        // editor.putFloat("distance_key", 1.5f);
        // Commit changes. Write changes to file
        // apply works on a background thread
        editor.apply();
        Log.d("Will", "State was saved");
        // Use editor.commit if you want a boolean result
        // boolean wasSuccess = editor.commit();
    }

    // Read state
    public void readState() {
        // SharedPreferences
        SharedPreferences sh = getSharedPreferences(SH_KEY, MODE_PRIVATE);
        // Get string from sharedPreferences
        String state = sh.getString(STATE_KEY, "");

        // Do something with loaded state
        if (state.isEmpty()) {
            Log.d("Will", "State is empty");
        } else {
            stateTextView.setText(state);
            Log.d("Will", "State loaded");
        }
    }

    // Clear state
    public void clearState() {
        SharedPreferences sh = getSharedPreferences(SH_KEY, MODE_PRIVATE);
        SharedPreferences.Editor editor = sh.edit();
        editor.clear();
        editor.apply();
    }

    public void changeImage() {
        // Change image with id
        imageView.setImageResource(R.drawable.space_circle);

        // Get image id with name
        // int id = getResources().getIdentifier("space_circle", "drawable", getPackageName());
        // Change image
        // imageView.setImageResource(id);
    }

    // SaveStateButton onClick
    public void saveButtonPressed(View view) {
        saveState();
        changeImage();
    }

}
