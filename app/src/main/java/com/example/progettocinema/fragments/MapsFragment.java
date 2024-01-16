package com.example.progettocinema.fragments;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.progettocinema.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {


    private Boolean received = false;

    private boolean trackUser = false;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng sydney = new LatLng(-34, 151);
            googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
            GoogleMap mMap = googleMap;
            //45.52116096269925, 9.145668272712244
            // Add a marker in Sydney and move the camera
            LatLng milano = new LatLng(45.50, 9.18549);
            LatLng cinema1 = new LatLng(45.50, 9.18569);
            LatLng cinema2 = new LatLng(45.45, 9.185);
            LatLng cinema3 = new LatLng(45.38, 9.19);
            //mMap.addMarker(new MarkerOptions().position(milano).title("Siamo qui"));
            mMap.addMarker(new MarkerOptions().position(cinema1).title("Cinema Bello"));
            mMap.addMarker(new MarkerOptions().position(cinema2).title("Cinema QuanteStorie"));
            mMap.addMarker(new MarkerOptions().position(cinema3).title("Multisala Rossi"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(milano, 10));


            if(ActivityCompat.checkSelfPermission(MapsFragment.this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                System.out.println();
            } else {
                int requestCode = 1;
                ActivityCompat.requestPermissions(MapsFragment.this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);
            }
            mMap.setMyLocationEnabled(true);

        }

    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }



}