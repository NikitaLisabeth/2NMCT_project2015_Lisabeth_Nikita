package be.howest.nmct;


import android.content.Context;
import android.graphics.Color;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.InputStream;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import be.howest.nmct.Model.Theatre;
import be.howest.nmct.provider.TheatreProvider;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheatreMapFragment extends Fragment {
    public static final String ARG_THEATRE_NAME = "theatre_name";
    public static Theatre selectedTheatre;
    private MapView mMapView;
    private GoogleMap googleMap;
    private TextView tvTheatre;
    private TextView tvAddress;
    public TheatreMapFragment() {
        // Required empty public constructor
    }

    public static TheatreMapFragment newInstance(String sTheatreName) {
        TheatreMapFragment fragment = new TheatreMapFragment();
        Bundle args = new Bundle();
        args.putString(ARG_THEATRE_NAME, sTheatreName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
        public void onActivityCreated(Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            selectedTheatre = TheatreProvider.getTheatresWestEnd(getArguments().getString(ARG_THEATRE_NAME));

            tvTheatre.setText(selectedTheatre.getName());
            tvAddress.setText(selectedTheatre.getAddress());

            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());

            MarkerOptions marker = new MarkerOptions().position(
                    new LatLng(selectedTheatre.getLocation().latitude,selectedTheatre.getLocation().longitude))
                    .title(selectedTheatre.getName())
                    .snippet(selectedTheatre.getAddress());
            marker.icon(BitmapDescriptorFactory
                    .fromResource(R.drawable.pin));
            googleMap.addMarker(marker);


        /*Polyline line = googleMap.addPolyline(new PolylineOptions()
                .add(selectedTheatre.getLocation(), new LatLng(40.7, -74.0))
                .width(5)
                .color(Color.RED));*/


            CameraPosition cameraPosition = new CameraPosition.Builder().target(selectedTheatre.getLocation()).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMyLocationEnabled(true);
        googleMap.setIndoorEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_theatre_map, container,
                false);
        tvAddress = (TextView) v.findViewById(R.id.tvMapAddress);
        tvTheatre = (TextView) v.findViewById(R.id.tvMapTheatre);
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        googleMap = mMapView.getMap();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
