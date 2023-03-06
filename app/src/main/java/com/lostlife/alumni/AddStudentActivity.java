package com.lostlife.alumni;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.lostlife.alumni.models.Student;
import com.lostlife.alumni.retrofit.StudentApi;
import com.lostlife.alumni.retrofit.retrofitService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddStudentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        initilize();
    }

    private void initilize() {

        EditText nameText=(EditText) findViewById(R.id.editTextTextPersonName);
        EditText branchText=(EditText) findViewById(R.id.editTextTextPersonName2);
        EditText graduationText=(EditText) findViewById(R.id.editTextDate);
        EditText mobNoText=(EditText) findViewById(R.id.editTextPhone);
        EditText emailText=(EditText) findViewById(R.id.editTextTextEmailAddress);
        Button submitButton=(Button) findViewById(R.id.submitButton);

        retrofitService retrofitservice=new retrofitService();
        StudentApi studentApi = retrofitservice.getRetrofit().create(StudentApi.class);

        submitButton.setOnClickListener(View ->{
            String name="",branch="",mobNo="",email="";
            int graduationYear=0;
            try {
                name = nameText.getText().toString();
                branch = branchText.getText().toString();
                graduationYear = Integer.parseInt(graduationText.getText().toString());
                mobNo = mobNoText.getText().toString();
                email = emailText.getText().toString();
            } catch (Exception e){
                Toast.makeText(this,"Please enter all details!...", Toast.LENGTH_SHORT).show();
            }
            
            if(graduationYear==0 || name.equals("") || branch.equals("") || mobNo.equals("") || email.equals("")){

            }
            else {
                Student student = new Student(name, branch, graduationYear, mobNo, email);

                studentApi.save(student).enqueue(new Callback<Student>() {
                    @Override
                    public void onResponse(Call<Student> call, Response<Student> response) {
                        Toast.makeText(AddStudentActivity.this, "Student Saved Successfully", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Student> call, Throwable t) {
                        Toast.makeText(AddStudentActivity.this, "Save failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }


        });

    }
}