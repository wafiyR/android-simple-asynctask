package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

// first void because no input required; second void because progress is not published; string for result type
public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    // The weak reference prevents the memory leak by allowing the object held by that reference to be garbage collected if necessary.
    private WeakReference<TextView> mTextView;
    private WeakReference<TextView> mResultTextView;

    SimpleAsyncTask(TextView tv, TextView result) {
        mTextView = new WeakReference<>(tv);
        mResultTextView = new WeakReference<>(result);
    }

    @Override
    protected String doInBackground(Void... voids) {


        Random r = new Random();

        // Generate a random number between 0 and 10
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n * 200;

        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Awake after sleeping for" + s + "milliseconds!";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        //super.onProgressUpdate(values);
        mResultTextView.get().setText("Current sleep time: " + values[0] + " ms");
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}
