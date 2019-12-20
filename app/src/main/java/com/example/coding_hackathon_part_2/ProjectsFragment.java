package com.example.coding_hackathon_part_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static android.content.Intent.EXTRA_TITLE;


public class ProjectsFragment extends Fragment {

    private EditText titleText, descriptionText, latitudeText, longitudeText;
    private Button continueBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_projects, container, false);

        titleText = view.findViewById(R.id.input_name);
        descriptionText = view.findViewById(R.id.input_description);
        latitudeText = view.findViewById(R.id.input_latitude);
        longitudeText = view.findViewById(R.id.input_longitude);
        continueBtn = view.findViewById(R.id.btn_continue);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                continueToAssignContractor();
            }
        });

        return view;

    }

    private void continueToAssignContractor(){
        if(!validate()){
            return;
        }

        Intent intent = new Intent(getActivity(), AssignContractorActivity.class);
        String title = titleText.getText().toString();
        String description = descriptionText.getText().toString();
        String latitude = latitudeText.getText().toString();
        String longitude = longitudeText.getText().toString();
        intent.putExtra("title", title);
        intent.putExtra("description", description);
        intent.putExtra("latitude", latitude);
        intent.putExtra("longitude", longitude);
        startActivity(intent);
    }

    private boolean validate() {
        boolean valid = true;

        String title = titleText.getText().toString();
        String latitude = latitudeText.getText().toString();
        String longitude = longitudeText.getText().toString();

        if (title.isEmpty() || title.length() < 3) {
            titleText.setError("should contain at least 3 alphanumeric characters");
            valid = false;
        } else {
            titleText.setError(null);
        }

        try {
            Double.parseDouble(latitude);
            latitudeText.setError(null);
        } catch (NumberFormatException e) {
            valid = false;
            latitudeText.setError("should be numeric");
        }

        try {
            Double.parseDouble(longitude);
            longitudeText.setError(null);
        } catch (NumberFormatException e) {
            valid = false;
            longitudeText.setError("should be numeric");
        }

        if(!valid){
            Toast.makeText(getActivity(), "Try again", Toast.LENGTH_LONG).show();
        }

        return valid;
    }

}
