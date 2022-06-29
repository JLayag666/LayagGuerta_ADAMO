package com.example.finalmidterm;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.appcompat.app.AppCompatViewInflater;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.io.IOException;
import java.util.List;

public class Maps extends Fragment implements OnMapReadyCallback {

    private GoogleMap map;
    private Button btnSearch;
    private EditText editTextLocation;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager() .findFragmentById(R.id.map);
        mapFragment.getMapAsync(Maps.this);
        btnSearch = (Button) rootView.findViewById(R.id.Search);
        editTextLocation = (EditText) rootView.findViewById(R.id.Location);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String location = editTextLocation.getText().toString();

                if (location.length() != 0) {
                    List<Address> addressList = null;
                    if (location != null || !location.equals("")) {
                        Geocoder geocoder = new Geocoder(getActivity().getApplicationContext());
                        try {
                            addressList = geocoder.getFromLocationName(location, 1);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Address address = addressList.get(0);
                        LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                        map.addMarker(new MarkerOptions().position(latLng).title(location));
                        //map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
                    }

                }

                else {
                    Toast.makeText(getActivity().getApplicationContext(),"Please input a Location",Toast.LENGTH_LONG).show();
                }
            }
        });
        return rootView;


    }


    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng garden1 = new LatLng(14.498156, 121.01749);
        LatLng garden2 = new LatLng(14.63333, 121.1);
        LatLng garden3 = new LatLng(14.654224, 121.066361);
        LatLng garden4 = new LatLng(14.6488, 121.0509);
        LatLng garden5 = new LatLng(14.594788, 121.028204);
        LatLng garden6 = new LatLng(14.58063, 121.065094);
        LatLng garden7 = new LatLng(14.650991, 121.048616);
        LatLng garden8 = new LatLng(14.6042, 120.9822);
        LatLng garden9 = new LatLng(14.58691, 121.0614);
        LatLng garden10 = new LatLng(14.55027, 121.03269);
        map.addMarker(new MarkerOptions().position(garden1).title("BM-JOV Garden Center"));
        map.addMarker(new MarkerOptions().position(garden2).title("Shopleaf"));
        map.addMarker(new MarkerOptions().position(garden3).title("Succulents PH"));
        map.addMarker(new MarkerOptions().position(garden4).title("Plant Express"));
        map.addMarker(new MarkerOptions().position(garden5).title("PGD Botanique"));
        map.addMarker(new MarkerOptions().position(garden6).title("Qach Lifestyle & Garden"));
        map.addMarker(new MarkerOptions().position(garden7).title("Plantita Potito"));
        map.addMarker(new MarkerOptions().position(garden8).title("Sprout Plants Manila"));
        map.addMarker(new MarkerOptions().position(garden9).title("Plant Parenthood PH"));
        map.addMarker(new MarkerOptions().position(garden10).title("Smarty Plants PH"));
    }

}
