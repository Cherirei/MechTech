package com.example.mechtech;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StationsFragment extends Fragment {

    EditText stationCode, stationName, stationCounty, stationAddress, stationworkingHrs;
    Button btnAdd;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stations, container, false);

        stationCode = view.findViewById(R.id.station_code);
        stationCounty = view.findViewById(R.id.station_county);
        stationName = view.findViewById(R.id.station_name);
        stationAddress = view.findViewById(R.id.station_postal_code);
        stationworkingHrs = view.findViewById(R.id.working_hours);
        btnAdd = view.findViewById(R.id.btn_add_station);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("ServiceStations");

                String code = stationCode.getText().toString();
                String name = stationName.getText().toString();
                String county = stationCounty.getText().toString();
                String address = stationAddress.getText().toString();
                String workingHrs = stationworkingHrs.getText().toString();

                String userId=FirebaseDatabase.getInstance().toString();
                ServiceStations serviceStations = new ServiceStations(code, name, county, address, workingHrs);
                reference.child(address).setValue(serviceStations);
                Toast.makeText(getActivity(), "Successfully Added", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}