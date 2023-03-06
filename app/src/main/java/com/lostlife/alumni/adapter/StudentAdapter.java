package com.lostlife.alumni.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lostlife.alumni.R;
import com.lostlife.alumni.ViewStudentsActivity;
import com.lostlife.alumni.models.Student;
import com.lostlife.alumni.retrofit.StudentApi;
import com.lostlife.alumni.retrofit.retrofitService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentAdapter extends RecyclerView.Adapter<StudentHolder> {

    private List<Student> studentList;
    retrofitService retrofitservice=new retrofitService();
    StudentApi studentApi1 = retrofitservice.getRetrofit().create(StudentApi.class);

    public StudentAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_student_item,parent,false);
        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentHolder holder, int position) {
        Student student=studentList.get(position);
        holder.name.setText(student.getName());
        holder.branch.setText(student.getBranch());
        holder.email.setText(student.getEmail());
        holder.mobile.setText(student.getMobNo());
        holder.year.setText(Integer.toString(student.getGraduationyear()));

        
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mahadev", "onClick: edit ");

                //update Student

                Dialog dialog=new Dialog(view.getContext());
                dialog.setContentView(R.layout.activity_add_student);

                EditText nameText=dialog.findViewById(R.id.editTextTextPersonName);
                EditText branchText=dialog.findViewById(R.id.editTextTextPersonName2);
                EditText graduationText=dialog.findViewById(R.id.editTextDate);
                EditText mobNoText=dialog.findViewById(R.id.editTextPhone);
                EditText emailText=dialog.findViewById(R.id.editTextTextEmailAddress);
                Button submitButton=dialog.findViewById(R.id.submitButton);


                nameText.setText(student.getName());
                branchText.setText(student.getBranch());
                graduationText.setText(Integer.toString(student.getGraduationyear()));
                mobNoText.setText(student.getMobNo());
                emailText.setText(student.getEmail());
                submitButton.setText("Update");

                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean error=false;
                        Student student1=new Student();
                        student1.setId(student.getId());
                        try {
                            student1.setName(nameText.getText().toString());
                            student1.setBranch(branchText.getText().toString());
                            student1.setGraduationyear(Integer.parseInt(graduationText.getText().toString()));
                            student1.setMobNo(mobNoText.getText().toString());
                            student1.setEmail(emailText.getText().toString());
                        }catch (Exception e){
                            error=true;
                            Toast.makeText(view.getContext(), "Please enter all details!...", Toast.LENGTH_SHORT).show();
                        }
                        if(!error) {
                            studentApi1.updateStudent(student1).enqueue(new Callback<Student>() {
                                @Override
                                public void onResponse(Call<Student> call, Response<Student> response) {
                                    Toast.makeText(view.getContext(), "Details updated successfully!", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    notifyItemChanged(holder.getAdapterPosition());
                                }

                                @Override
                                public void onFailure(Call<Student> call, Throwable t) {
                                    Toast.makeText(view.getContext(), "failed to update details", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });


                dialog.show();

            }
        });
        
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mahadev", "onClick: delete");

                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext()).setTitle("Delete student!").setMessage("Are you Sure?")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        studentApi1.deleteStudent(student.getId()).enqueue(new Callback<Void>() {
                                            @Override
                                            public void onResponse(Call<Void> call, Response<Void> response) {
                                                Log.d("mahadev", "onResponse: Successfullly deleted");
                                                Toast.makeText(view.getContext(), "Student deleted Successfully!", Toast.LENGTH_SHORT).show();
                                                notifyItemRemoved(holder.getAdapterPosition());
                                            }

                                            @Override
                                            public void onFailure(Call<Void> call, Throwable t) {
                                                Log.d("mahadev", "onFailure: delete failed");
                                                Toast.makeText(view.getContext(), "Delete failed...", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                })
                                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialogInterface, int i) {
                                                Toast.makeText(view.getContext(), "Delete Cancelled", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                builder.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}
