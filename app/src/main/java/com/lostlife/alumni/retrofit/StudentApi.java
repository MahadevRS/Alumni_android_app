package com.lostlife.alumni.retrofit;

import com.lostlife.alumni.models.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface StudentApi {

    @GET("student/get-all")
    Call<List<Student>> getAllStudent();

    @POST("student/add")
    Call<Student> save(@Body Student student);

    @PUT("student/update")
    Call<Student> updateStudent(@Body Student student);

    @DELETE("student/delete/{id}")
    Call<Void> deleteStudent(@Path("id") int id);
}
