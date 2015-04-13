package com.example.k3lara.lecture_3_examples;


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

import com.example.k3lara.lecture_3_examples.xmlparser.Parser;
import com.example.k3lara.lecture_3_examples.xmlparser.ScheduleItem;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpFragment extends Fragment implements AdapterView.OnItemSelectedListener{

    private ArrayList<ScheduleItem> myItems = new ArrayList<ScheduleItem>();
private MyAdapter myAdapter;
    public ExpFragment() {
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
        View v = inflater.inflate(R.layout.fragment_schedule_expandable, container, false);
        ///Do whatever
        ExpandableListView ev = (ExpandableListView) v.findViewById(R.id.expandableListView);
        myAdapter = new MyAdapter(getActivity(),myItems);
        ev.setAdapter(myAdapter);
        //MyAsyncTask myAsyncTask = new MyAsyncTask();
        //myAsyncTask.execute("KD323B-20151-K3522");
        Spinner sp = (Spinner)v.findViewById(R.id.spinner);
        sp.setOnItemSelectedListener(this);
        return v;
    }

    ///Listens to meny selection.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Log.i("ExpFragment", "MenuSelection. " + id);
        if (id == R.id.refresh) {
            Spinner sp = (Spinner)getView().findViewById(R.id.spinner);
            int i = sp.getSelectedItemPosition();
            String[] sa = getActivity().getResources().getStringArray(R.array.courses);
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
            String[] courses = getActivity().getResources().getStringArray(R.array.courses);
            String course = courses[position];
            Log.i("ExpFragment", "Course. " + course);
            MyAsyncTask myAsyncTask = new MyAsyncTask();
            myAsyncTask.execute(course);
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //And the thread
    private class MyAsyncTask extends AsyncTask<String,Void,Void>{

        @Override
        protected Void doInBackground(String... params) {
            String param1 =  params[0];
            myItems.clear();
            myItems.addAll(Parser.getSchedule(params[0]));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myAdapter.notifyDataSetChanged();
            for (ScheduleItem si :myItems){
                Log.i("ExpFragment", "moment" + si.getMoment());
            }
        }
    }
}
