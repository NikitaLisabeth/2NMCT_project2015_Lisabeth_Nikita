package be.howest.nmct;


import android.location.Geocoder;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TheatreMapFragment extends Fragment {
    public static final String ARG_THEATRE_NAME = "theatre_name";

    MapView mMapView;
    private GoogleMap googleMap;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_theatre_map, container,
                false);

        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();// needed to get the map to display immediately

        googleMap = mMapView.getMap();
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        return v;
    }


}
