package se.mah.k3.skaneAPI.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import se.mah.k3.skaneAPI.R;
import se.mah.k3.skaneAPI.model.Journey;
import se.mah.k3.skaneAPI.model.Journeys;
import se.mah.k3.skaneAPI.model.Station;

/**
 * Created by Nellie on 2015-04-14.
 */


public class MyAdapter extends BaseExpandableListAdapter {
    private ArrayList<Journey> myJourney;
    private Context c;

    public MyAdapter(Context c, ArrayList<Journey> myJourney){
        this.c=c;
        this.myJourney = myJourney;
    }

    @Override
    public int getGroupCount() {
        return myJourney.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Journey journey = myJourney.get(groupPosition);

        LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.group_layout,null);
        TextView Destinaton = (TextView) convertView.findViewById(R.id.Destination);
        Station endStation = myJourney.get(groupPosition).getStartStation();
        Destinaton.setText(endStation.getStationName());
        TextView tid_avgang =(TextView) convertView.findViewById(R.id.tid_avgang);
        String noOfChanges = myJourney.get(groupPosition).getNoOfChanges();
        tid_avgang.setText(noOfChanges);


        ImageView symbol = (ImageView) convertView.findViewById(R.id.symbol);
        int amountOfChange = Integer.parseInt(journey.getNoOfChanges());

        if(amountOfChange > 1) {
            Log.i("hej", "funkar");
            symbol.setVisibility(View.VISIBLE);
        }else{
            symbol.setVisibility(View.INVISIBLE);
        }


        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.child_layout,null);
        TextView startStation1 = (TextView) convertView.findViewById(R.id.Startstation1);
        Station startStation = myJourney.get(groupPosition).getStartStation();
        startStation1.setText(startStation.getStationName());
        TextView Destination = (TextView) convertView.findViewById(R.id.Destination);
        Station endStation = myJourney.get(groupPosition).getEndStation();
        Destination.setText(endStation.getStationName());
        TextView Rese_tid = (TextView) convertView.findViewById(R.id.Rese_tid);
        String travelMinutes = myJourney.get(groupPosition).getTravelMinutes();
        Rese_tid.setText(travelMinutes + "minutes");
        TextView Forsening = (TextView) convertView.findViewById(R.id.Forsening);
        String depTimeDeviation = myJourney.get(groupPosition).getDepTimeDeviation();
        Forsening.setText(depTimeDeviation);

        return convertView;
    }


    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}