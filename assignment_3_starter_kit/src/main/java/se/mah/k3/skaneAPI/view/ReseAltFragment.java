package se.mah.k3.skaneAPI.view;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;

import se.mah.k3.skaneAPI.R;
import se.mah.k3.skaneAPI.control.Constants;
import se.mah.k3.skaneAPI.model.Journey;
import se.mah.k3.skaneAPI.model.Journeys;
import se.mah.k3.skaneAPI.xmlparser.Parser;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReseAltFragment extends Fragment implements AdapterView.OnItemSelectedListener {



    private ArrayList<Journey> myJourney = new ArrayList<Journey>();
    private MyAdapter adapter;
    private Spinner sp;
    private Spinner sp2;

    public ReseAltFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_rese_alt, container, false);
        ///Do whatever
        ExpandableListView ev = (ExpandableListView) v.findViewById(R.id.expandableListView);
        adapter = new MyAdapter(getActivity(),myJourney);
        ev.setAdapter(adapter);
        //MyAsyncTask myAsyncTask = new MyAsyncTask();
        //myAsyncTask.execute("KD323B-20151-K3522");
        sp = (Spinner)v.findViewById(R.id.avgangAvgang);
        sp.setOnItemSelectedListener(this);
        sp2 = (Spinner)v.findViewById(R.id.destination);
        sp2.setOnItemSelectedListener(this);
        return v;
    }

    //Listens to meny selection.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i("Fragment", "MenuSelection. " + id);
        if (id == R.id.refresh) {
            int i = sp.getSelectedItemPosition();
            String[] sa = getActivity().getResources().getStringArray(R.array.avgangAvgang);
            String course = sa[i];
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(course);
            return true;
        }else {
            return super.onOptionsItemSelected(item);
        }
    }

    //Listens to the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int fromNumber = sp.getSelectedItemPosition();
        int toNumber = sp2.getSelectedItemPosition();
        String[] courses = getActivity().getResources().getStringArray(R.array.destination);
        String searchURL = Constants.getURL(courses[fromNumber],courses[toNumber],10);
        //Log.i("ExpFragment", "Course. " + course);

        new MyAsyncTask().execute(searchURL);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //And the thread
    private class MyAsyncTask extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            Journeys journeys = Parser.getJourneys(params[0]);

            myJourney.clear();
            myJourney.addAll(journeys.getJourneys());
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
            for (Journey si :myJourney){
                Log.i("ExpFragment", "moment" + si.getStartStation());
            }
        }
    }
}