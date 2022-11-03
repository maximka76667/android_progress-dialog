package com.example.android_progress_dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = this.findViewById(R.id.textView);

        Task task = new Task(MainActivity.this, new Task.ITask() {
            @Override
            public void onFinish(Long suma) {
                tv.setText(suma + "");
            }
        });

        task.execute(5, 7, 9);
    }

}