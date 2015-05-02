package be.howest.nmct;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;


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




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
