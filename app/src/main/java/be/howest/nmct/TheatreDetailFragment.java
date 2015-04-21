package be.howest.nmct;


import android.app.Activity;
import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import be.howest.nmct.Loader.Contract;
import be.howest.nmct.Model.Theatre;
import be.howest.nmct.provider.TheatreProvider;


/**
 * A simple {@link Fragment} subclass.
 */
public class TheatreDetailFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private TextView tvCurrentMusical;
    private TextView tvTheatreName;
    private TextView tvStagedoor;
    private TextView tvAddress;
    private ImageView imgTheatre;
    private Button btnNavigate;
    private CursorAdapter mAdapter;
    private OnTheatreDetailsFragmentListener onTheatreDetailsFragmentListener;
    public static final String ARG_THEATRE_NAME = "theatre_name";

    public TheatreDetailFragment() {
        // Required empty public constructor
    }
    public static TheatreDetailFragment newInstance(String sTheatreName) {
        TheatreDetailFragment fragment = new TheatreDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_THEATRE_NAME, sTheatreName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onTheatreDetailsFragmentListener = (OnTheatreDetailsFragmentListener) activity;
        } catch (ClassCastException ex){
            throw new ClassCastException(activity.toString() + " implement interface OnClubListFragmentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_theatre_detail, container, false);
        tvCurrentMusical = (TextView) v.findViewById(R.id.tvDetailCurrentMusical);
        tvTheatreName = (TextView) v.findViewById(R.id.tvDetailTheatreName);
        tvStagedoor = (TextView) v.findViewById(R.id.tvDetailStageDoor);
        tvAddress = (TextView) v.findViewById(R.id.tvDetailAddress);
        imgTheatre = (ImageView) v.findViewById(R.id.imgDetailTheatre);
        btnNavigate = (Button) v.findViewById(R.id.btnDetailNavigate);
        tvTheatreName.setText(getArguments().getString(ARG_THEATRE_NAME));
        showImage(tvTheatreName.getText().toString());
       /* btnNavigate.setBackgroundColor(Color.parseColor("#BD0000"));*/
        btnNavigate.setTextColor(Color.parseColor("#ffffff"));

        btnNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onTheatreDetailsFragmentListener != null){
                    onTheatreDetailsFragmentListener.onSelectTheatreDetail(tvTheatreName.getText().toString());
                }
            }
        });

        return v;
    }
    public void showImage(String theatreName){
       // int colnr1 = cursor.getColumnIndex(Contract.TheatreColumns.COLUMN_THEATRE_NAME);
        //String theatreName = cursor.getString(colnr1);
        Log.d(theatreName, theatreName);
        switch (theatreName){
            case "Aldwych Theatre":
                imgTheatre.setImageResource(R.drawable.beautifulbanner);
                break;
            case "Phoenix Theatre":
                imgTheatre.setImageResource(R.drawable.beckhambanner);
                break;
            case "Victoria Palace Theatre":
                imgTheatre.setImageResource(R.drawable.billyelliotbanner);
                break;
            case "Prince of Wales Theatre":
                imgTheatre.setImageResource(R.drawable.bookofmormonbanner);
                break;
            case "London Palladium":
                imgTheatre.setImageResource(R.drawable.catsbanner);
                break;
            case "Queens Theatre":
                imgTheatre.setImageResource(R.drawable.lesmisbanner);
                break;
            case "Majesty's Theatre":
                imgTheatre.setImageResource(R.drawable.phantomoftheoperabanner);
                break;
            case "Apollo Victoria Theatre":
                imgTheatre.setImageResource(R.drawable.wickedbanner);
                break;
            case "Prince Edward Theatre":
                imgTheatre.setImageResource(R.drawable.misssaigonbanner);
                break;
            case "Savoy Theatre":
                imgTheatre.setImageResource(R.drawable.gypsybanner);
                break;
            case "Piccadilly Theatre":
                imgTheatre.setImageResource(R.drawable.jerseyboysbanner);
                break;
            case "Shaftesbury Theatre":
                imgTheatre.setImageResource(R.drawable.memphisbanner);
                break;
            case "Cambridge Theatre":
                imgTheatre.setImageResource(R.drawable.mathildabanner);
                break;
            default:
                imgTheatre.setImageResource(R.drawable.comedytragedy);
                break;
        }
    }



    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Theatre selectedTheatre = TheatreProvider.getTheatresWestEnd(getArguments().getString(ARG_THEATRE_NAME));

        tvCurrentMusical.setText(selectedTheatre.getCurrentMusical());
        tvStagedoor.setText(selectedTheatre.getStageDoor());
        tvAddress.setText(selectedTheatre.getAddress());
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //return new TheatreDetailLoader(getActivity(), getArguments().getString(ARG_THEATRE_NAME));
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mAdapter.swapCursor(null);
    }
    public interface OnTheatreDetailsFragmentListener {
        public void onSelectTheatreDetail(String name);
    }
}
