package com.example.k3lara.lecture_3_examples;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.k3lara.lecture_3_examples.xmlparser.ScheduleItem;

import java.util.ArrayList;

/**
 * Created by K3LARA on 13/04/2015.
 */
public class MyAdapter extends BaseExpandableListAdapter {
    private ArrayList<ScheduleItem> myItems;
        private Context c;

    public MyAdapter(Context c,ArrayList<ScheduleItem> myItems){
        this.c=c;
        this.myItems = myItems;
    }

    @Override
    public int getGroupCount() {
        return myItems.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.groupexpl_layout,null);
        TextView tvCourse = (TextView) convertView.findViewById(R.id.tv_course);
        String course = myItems.get(groupPosition).getCourse();
        tvCourse.setText(course);
        TextView tvDate =(TextView) convertView.findViewById(R.id.tv_date);
        String date = myItems.get(groupPosition).getDate();
        tvDate.setText(date);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.group_child_layout,null);
        TextView tvMoment = (TextView) convertView.findViewById(R.id.tv_moment);
        String moment = myItems.get(groupPosition).getMoment();
        tvMoment.setText(moment);
        TextView tvRoom = (TextView) convertView.findViewById(R.id.tv_room);
        String room = myItems.get(groupPosition).getRoom();
        tvRoom.setText(room);
        TextView tvStart = (TextView) convertView.findViewById(R.id.tv_start);
        String start = myItems.get(groupPosition).getStartTime();
        tvStart.setText(start);
        TextView tvEnd = (TextView) convertView.findViewById(R.id.tv_end);
        String end = myItems.get(groupPosition).getEndTime();
        tvEnd.setText(end);
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
