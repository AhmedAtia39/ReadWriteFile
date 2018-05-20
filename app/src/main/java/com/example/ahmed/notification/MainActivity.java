package com.example.ahmed.notification;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView txt_text;
    static EditText edit_fileName, edit_textToSave;

    ReadWriteFile readWriteFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_text = (TextView) findViewById(R.id.txt_text);
        edit_fileName = (EditText) findViewById(R.id.edit_fileName);
        edit_textToSave = (EditText) findViewById(R.id.edit_textToSave);

        readWriteFile = new ReadWriteFile(this);
    }

    public void click_read(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 10);
            } else {
                read();
            }
        } else {
            read();
        }
    }

    public void click_write(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 20);
            } else {
                write();
            }
        } else {
            write();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 10:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    read();
                break;

            case 20:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    write();
                break;
        }
    }

    private void read() {
        readWriteFile.read();
    }

    private void write() {
        readWriteFile.write();
    }

}
