package com.example.android_progress_dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class Task extends AsyncTask<Integer, Integer, Long> {
    interface ITask {
        public default void onFinish(Long suma) {
        }
    }

    ProgressDialog dialog;
    Context context;
    ITask iTask;

    public Task(Context context, ITask iTask) {
        this.context = context;
        this.iTask = iTask;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("Procesando...");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.show();
    }

    @Override
    protected Long doInBackground(Integer... numeros) {
        long total = 0;
        dialog.setMax(numeros.length);
        for(int i = 0; i < numeros.length; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            total += numeros[i];
            publishProgress(i + 1);
        }
        return total;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        dialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Long suma) {
        super.onPostExecute(suma);
        dialog.dismiss();
        iTask.onFinish(suma);
    }
}
