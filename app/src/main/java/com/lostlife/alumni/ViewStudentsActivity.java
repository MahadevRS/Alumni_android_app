package com.lostlife.alumni;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import com.lostlife.alumni.adapter.StudentAdapter;
import com.lostlife.alumni.adapter.StudentHolder;
import com.lostlife.alumni.models.Student;
import com.lostlife.alumni.retrofit.StudentApi;
import com.lostlife.alumni.retrofit.retrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewStudentsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    StudentAdapter studentAdapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_students);

        recyclerView=findViewById(R.id.studentList_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadStudents();
    }

    private void LoadStudents() {
        retrofitService retrofitservice=new retrofitService();
        StudentApi studentApi = retrofitservice.getRetrofit().create(StudentApi.class);

        studentApi.getAllStudent().enqueue(new Callback<List<Student>>() {
            @Override
            public void onResponse(Call<List<Student>> call, Response<List<Student>> response) {
                popolateListView(response.body());

            }

            @Override
            public void onFailure(Call<List<Student>> call, Throwable t) {
                Toast.makeText(ViewStudentsActivity.this, "failed to load students from server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void popolateListView(List<Student> studentList) {
        studentAdapter=new StudentAdapter(studentList);
        recyclerView.setAdapter(studentAdapter);
    }

}