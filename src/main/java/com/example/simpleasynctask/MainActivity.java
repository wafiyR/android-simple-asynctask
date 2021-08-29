package com.example.simpleasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private TextView mProgressTextView;
    private static final String TEXT_STATE = "currentText";
    private static final String TEXT_PROGRESS = "CurrentSleepDuration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);
        mProgressTextView = findViewById(R.id.textViewResult);

        // Restore TextView if there is a savedInstanceState
        if(savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
            mProgressTextView.setText(savedInstanceState.getString(TEXT_PROGRESS));
        }

    }

    public void startTask(View view) {

        // add string with name: napping, and text "Napping..." in strings.xml of values folder
        mTextView.setText(R.string.napping);

        new SimpleAsyncTask(mTextView, mProgressTextView).execute();


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the state of the TextView
        outState.putString(TEXT_STATE,mTextView.getText().toString());
        outState.putString(TEXT_PROGRESS, mProgressTextView.getText().toString());
    }
}
