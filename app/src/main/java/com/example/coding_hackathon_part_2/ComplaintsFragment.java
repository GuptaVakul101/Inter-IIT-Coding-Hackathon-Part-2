package com.example.coding_hackathon_part_2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


//public class ComplaintsFragment extends Fragment {
//     @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_complaints, container, false);
//    }
//}

public class ComplaintsFragment extends Fragment
{

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    List<Region_Groups> region_list = new ArrayList<>();

    private RecyclerView.Adapter<ViewHolder> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view =  inflater.inflate(R.layout.fragment_complaints, container, false);
        recyclerView = view.findViewById(R.id.region_groups_list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        Log.d("Lavish", getActivity().toString());

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference ref = db.collection("groups_complaint");

        ref.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task)
            {
                if(task.isSuccessful())
                {
                    for(QueryDocumentSnapshot document: task.getResult())
                    {
                        Map<String,Object> data = document.getData();
                        Region_Groups temp = new Region_Groups(document.getId(),
                                data.get("centre_latitude"), data.get("centre_longitude"),data.get("num_complaints"));
                        region_list.add(temp);
                        //mAdapter.notifyDataSetChanged();
                    }

                    adapter = new RecyclerView.Adapter<ViewHolder>()
                    {
                        @NonNull
                        @Override
                        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
                        {
                            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.regions_layout, viewGroup, false);
                            ViewHolder holder = new ViewHolder(view);
                            return holder;
                        }

                        @Override
                        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
                        {
                            final Region_Groups region_details = region_list.get(position);

                            holder.txtProjectName.setText("Latitude: " + region_details.getLatitude());
                            holder.txtProjectDesciption.setText("Longitude: " + region_details.getLongitude());
                            holder.txtProjectSurveyCount.setText("Number of Complaints: " + region_details.getNum_complaints());
                            holder.parentlayout.setOnClickListener(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Intent i = new Intent(getActivity().getApplicationContext(), Complaint_Details.class);
                                    i.putExtra("groupid", region_details.getId());
                                    startActivity(i);
                                }
                            });
                        }

                        @Override
                        public int getItemCount()
                        {
                            return region_list.size();
                        }
                    };

                    recyclerView.setAdapter(adapter);
                }
            }
        });

        return view;
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtProjectName;
        public TextView txtProjectDesciption;
        public TextView txtProjectSurveyCount;
        public CardView parentlayout;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtProjectName = itemView.findViewById(R.id.region_latitude);
            txtProjectDesciption = itemView.findViewById(R.id.region_longitude);
            txtProjectSurveyCount = itemView.findViewById(R.id.region_count);
            parentlayout = itemView.findViewById(R.id.cardview_region);
        }
    }

}
