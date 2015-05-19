package be.howest.nmct;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.SearchView;
import android.widget.TextView;

import be.howest.nmct.Loader.Contract;
import be.howest.nmct.Loader.TheatreLoader;


public class MainActivity extends Activity implements TheatreListFragment.OnTheatresFragmentListener, TheatreDetailFragment.OnTheatreDetailsFragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new TheatreListFragment())
                    .commit();
            ActionBar ab = getActionBar();
        }
    }


    TheatreListFragment tlfragment;
    public Cursor CurrentCursor;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
       /* SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default


        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        tlfragment = (TheatreListFragment) getFragmentManager().findFragmentByTag("FilterFragment");
        CurrentCursor = tlfragment.mAdapter.getCursor();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String s = searchView.getQuery().toString();
                filterCursorOnTheatre(newText);
                of.mAdapter.changeCursor(fcw);
                return true;
            }
        });*/
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSelectTheatre(String name ) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TheatreDetailFragment fragment2 = TheatreDetailFragment.newInstance(name);
        fragmentTransaction.replace(R.id.container, fragment2);

        fragmentTransaction.addToBackStack("showDetailTheatre");
        fragmentTransaction.commit();
    }

    @Override
    public void onSelectTheatreDetail(String name) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        TheatreMapFragment fragment3 = TheatreMapFragment.newInstance(name);
        fragmentTransaction.replace(R.id.container, fragment3);

        fragmentTransaction.addToBackStack("showMapTheatre");
        fragmentTransaction.commit();
    }

    private Cursor filterCursorOnTheatre(String TheatreName) {
        String[] mColumnNames = new String[]{
                BaseColumns._ID,
                Contract.TheatreColumns.COLUMN_THEATRE_NAME,
                Contract.TheatreColumns.COLUMN_CURRENT_MUSICAL,
                Contract.TheatreColumns.COLUMN_ADDRESS,
                Contract.TheatreColumns.COLUMN_STAGEDOOR,
                Contract.TheatreColumns.COLUMN_LOCATION};

        MatrixCursor newCursor = new MatrixCursor(mColumnNames);
        int colnr1 = TheatreLoader.mCursor.getColumnIndex(Contract.TheatreColumns.COLUMN_THEATRE_NAME);
        int colnr2 = TheatreLoader.mCursor.getColumnIndex(Contract.TheatreColumns.COLUMN_CURRENT_MUSICAL);
        int colnr3 = TheatreLoader.mCursor.getColumnIndex(Contract.TheatreColumns.COLUMN_ADDRESS);
        int colnr4 = TheatreLoader.mCursor.getColumnIndex(Contract.TheatreColumns.COLUMN_STAGEDOOR);
        int colnr5 = TheatreLoader.mCursor.getColumnIndex(Contract.TheatreColumns.COLUMN_LOCATION);

        int id = 1;
        if(TheatreLoader.mCursor.moveToFirst()){
            do{
                if(TheatreLoader.mCursor.getString(colnr1).toLowerCase().contains(TheatreName.toLowerCase().trim())){
                    MatrixCursor.RowBuilder row = newCursor.newRow();
                    row.add(id++);
                    row.add(TheatreLoader.mCursor.getString(colnr1));
                    row.add(TheatreLoader.mCursor.getString(colnr2));
                    row.add(TheatreLoader.mCursor.getString(colnr3));
                    row.add(TheatreLoader.mCursor.getString(colnr4));
                    row.add(TheatreLoader.mCursor.getString(colnr5));
                }
            }while (TheatreLoader.mCursor.moveToNext());
        }
        return newCursor;
    }
}
