package com.example.ahmed.notification;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by AHMED on 20/05/2018.
 */

public class ReadWriteFile {
    Context context;

    public ReadWriteFile(Context context) {
        this.context = context;
    }

    public void read() {
        File root = Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/ahmedFile");
        File file = new File(dir, MainActivity.edit_fileName.getText().toString().trim() + ".txt");
        String msg;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(isr);
            StringBuffer stringBuffer = new StringBuffer();

            while ((msg = bufferedReader.readLine()) != null) {
                stringBuffer.append(msg + "\n");
            }

            MainActivity.txt_text.setText(stringBuffer.toString());
        } catch (FileNotFoundException e1) {
            Toast.makeText(context, "file not found", Toast.LENGTH_SHORT).show();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void write() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File root = Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/ahmedFile");
            if (!dir.exists()) {
                dir.mkdir();
            }
            File file = new File(dir, MainActivity.edit_fileName.getText().toString().trim() + ".txt");
            String msg = MainActivity.edit_textToSave.getText().toString();
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(msg.getBytes());
                fileOutputStream.close();
                Toast.makeText(context, "message saved", Toast.LENGTH_SHORT).show();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            Toast.makeText(context, "sd not allowed", Toast.LENGTH_SHORT).show();
        }
    }
}
