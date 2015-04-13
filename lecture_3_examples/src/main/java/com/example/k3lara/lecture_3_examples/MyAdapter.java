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
        TextView tv = (TextView) convertView.findViewById(R.id.tv_group);
        String course = myItems.get(groupPosition).getCourse();
        tv.setText(course);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        LayoutInflater li = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = li.inflate(R.layout.group_child_layout,null);
        TextView tv = (TextView) convertView.findViewById(R.id.tv_child);
        String course = myItems.get(groupPosition).getMoment();
        tv.setText(course);
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
