package be.howest.nmct;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class TheatreMapFragment extends Fragment {
    public static final String ARG_THEATRE_NAME = "theatre_name";


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
        return inflater.inflate(R.layout.fragment_theatre_map, container, false);
    }


}
