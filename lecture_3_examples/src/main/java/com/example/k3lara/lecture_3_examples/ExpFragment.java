package com.example.k3lara.lecture_3_examples;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.k3lara.lecture_3_examples.xmlparser.Parser;
import com.example.k3lara.lecture_3_examples.xmlparser.ScheduleItem;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExpFragment extends Fragment {

    private ArrayList<ScheduleItem> myItems = new ArrayList<ScheduleItem>();
private MyAdapter myAdapter;
    public ExpFragment() {
        // Required empty public constructor
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

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("KD323B-20151-K3522");

        return v;
    }

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
