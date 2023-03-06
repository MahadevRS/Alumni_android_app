package com.lostlife.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initilizeAll();
    }

    private void initilizeAll() {

        FloatingActionButton fab=findViewById(R.id.fabbutton);
        fab.setOnClickListener(View ->{
            Intent intent=new Intent(this,AddStudentActivity.class);
            startActivity(intent);
        });

        Button viewStudentbtn=findViewById(R.id.viewStudentbtn);
        viewStudentbtn.setOnClickListener(View ->{
            Intent intent=new Intent(this,ViewStudentsActivity.class);
            startActivity(intent);
        });
    }

}