package com.example.coding_hackathon_part_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Complaint_Details_Adapter extends RecyclerView.Adapter<Complaint_Details_Adapter.ViewHolder>
{
    Context context;
    List<complaintDetails> mainUploadlist;

    public Complaint_Details_Adapter(Context context, List<complaintDetails> Templist)
    {
        this.mainUploadlist = Templist;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.complaint_details_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        complaintDetails itemdetails = mainUploadlist.get(position);
        holder.txtProductName.setText(itemdetails.getRemarks());

        DocumentReference ref2 = itemdetails.getUserid();
        String path = ref2.getPath();

        final String user_id_temp=path.substring(path.lastIndexOf("/")+1);

        holder.txtProductCategory.setText("User ID: " + user_id_temp);

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final FirebaseFirestore db = FirebaseFirestore.getInstance();
                String des_path = "/users/" + user_id_temp;
                DocumentReference ref3 = db.document(des_path);

                ref3.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        Integer credits = documentSnapshot.getDouble("credits").intValue();

                        String des_path2 = "/users/" + user_id_temp;
                        DocumentReference ref4 = db.document(des_path2);

                        ref4.update("credits", credits+2)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Toast.makeText(context,"Credits given successfully", Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }
                });
            }
        });

        holder.btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context,"Click to give User credits", Toast.LENGTH_SHORT).show();
                return true;

            }
        });
    }

    @Override
    public int getItemCount() {

        return mainUploadlist.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView txtProductName, txtProductCategory, locationText, latitudeText, longitudeText;
        public Button btn;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            txtProductName = itemView.findViewById(R.id.complaint_remarks);
            txtProductCategory = itemView.findViewById(R.id.complaint_user_id);
            btn = itemView.findViewById(R.id.btn_credits);

            locationText = itemView.findViewById(R.id.display_location);
            latitudeText = itemView.findViewById(R.id.display_latitude);
            longitudeText = itemView.findViewById(R.id.display_longitude);
        }
    }
}