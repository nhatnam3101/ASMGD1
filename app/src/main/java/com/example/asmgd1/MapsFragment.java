package com.example.asmgd1;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsFragment extends Fragment {

    private SupportMapFragment mapFragment;
        Toolbar toolbar;
    public MapsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maps, container, false);
//        mapView=view.findViewById(R.id.map);
//        toolbar=view.findViewById(R.id.tobar);
//        toolbar.setNavigationIcon(R.drawable.ic_maps);
//        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Maps");
        if (mapFragment == null) {
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    LatLng fpt = new LatLng(10.8529444, 106.6273561);
                    googleMap.addMarker(new MarkerOptions().position(fpt).title("FPT Polytechnic HCM CS3"));
//                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(fpt));
//                    googleMap.animateCamera(CameraUpdateFactory.zoomTo(19.0f));
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(fpt).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    UiSettings uisetting = googleMap.getUiSettings();
                    uisetting.setCompassEnabled(true);
                    uisetting.setZoomControlsEnabled(true);
                    uisetting.setScrollGesturesEnabled(true);
                    uisetting.setTiltGesturesEnabled(true);

                    uisetting.setMyLocationButtonEnabled(true);
                    googleMap.setMyLocationEnabled(true);
                }
            });
        }

        // R.id.map is a FrameLayout, not a Fragment
        getChildFragmentManager().beginTransaction().replace(R.id.map, mapFragment).commit();
        return view;

    }
}