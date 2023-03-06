package com.lostlife.alumni.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lostlife.alumni.R;

public class StudentHolder extends RecyclerView.ViewHolder {
    TextView name,branch,mobile,email,year;
    Button edit,delete;

    public StudentHolder(@NonNull View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.studentitem_name);
        branch=itemView.findViewById(R.id.studentitem_branch);
        mobile=itemView.findViewById(R.id.studentitem_mobile);
        email=itemView.findViewById(R.id.studentitem_email);
        year=itemView.findViewById(R.id.studentitem_graduationyear);

        edit=itemView.findViewById(R.id.student_itemeditbutton);
        delete=itemView.findViewById(R.id.student_itemdeletebutton);
    }
}

