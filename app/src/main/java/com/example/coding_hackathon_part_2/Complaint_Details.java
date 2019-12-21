package com.example.coding_hackathon_part_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Complaint_Details extends AppCompatActivity
{
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<complaintDetails> list = new ArrayList<>();
    private String group_id;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint__details);

        recyclerView = findViewById(R.id.complaint_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();

        if(intent.getStringExtra("groupid") != null && !intent.getStringExtra("groupid").isEmpty()){
            group_id = intent.getStringExtra("groupid");
        }
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection("user_complaints");
        ref.whereEqualTo("group_ref", group_id).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> data = document.getData();
                        complaintDetails temp = new complaintDetails(data.get("user_ref"), data.get("remarks"));
                        list.add(temp);
                    }
                    RecyclerView.Adapter adapter = new Complaint_Details_Adapter(getApplicationContext(),list);
                    recyclerView.setAdapter(adapter);
                }
            }
        });
    }
}
