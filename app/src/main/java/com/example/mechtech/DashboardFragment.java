package com.example.mechtech;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DashboardFragment extends Fragment {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Button btnLocation, btnGet_Service;
    TextView textView1, textView2, textView3, textView4, textView5;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        btnLocation = view.findViewById(R.id.button_location);
        btnGet_Service = view.findViewById(R.id.button_get_service);
        textView1 = view.findViewById(R.id.text_view1);
        textView2 = view.findViewById(R.id.text_view2);
        textView3 = view.findViewById(R.id.text_view3);
        textView4 = view.findViewById(R.id.text_view4);
        textView5 = view.findViewById(R.id.text_view5);

        //initialise fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check permission
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                    //if permission granted
                    getLocation();
                } else {
                    //when permission denied
                    ActivityCompat.requestPermissions(getActivity(), new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }
            }
        });

        btnGet_Service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container, new StationCallFragment()).commit();
                // String filterCounty=textView2.getText().toString();
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frament_container,new ViewStationsFragment(filterCounty)).commit();
            }
        });

        return view;
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialise location
                Location location = task.getResult();
                if (location != null) {
                    try {
                        //Initialise geocoder
                        Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
                        //Initialise address list
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 5);

                        //Set Latitutes on textViews
                        textView1.setText(Html.fromHtml("<font color='#6200EE'><b>Country :</br></font>"
                                + addresses.get(1).getCountryName()));
                        //Set Longitude
                        textView2.setText(Html.fromHtml("<font color='#6200EE'><b>County :</br></font>"
                                + addresses.get(1).getAdminArea()));
                        //Set Country name
                        textView3.setText(Html.fromHtml("<font color='#6200EE'><b>Sub Locality :</br></font>"
                                + addresses.get(1).getSubLocality()));
                        //Set Locality
                        textView4.setText(Html.fromHtml("<font color='#6200EE'><b>Featured Name :</br></font>"
                                + addresses.get(1).getFeatureName()));
                        //Set Address
                        textView5.setText(Html.fromHtml("<font color='#6200EE'><b>Address :</br></font>"
                                + addresses.get(2).getAddressLine(0)));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

}