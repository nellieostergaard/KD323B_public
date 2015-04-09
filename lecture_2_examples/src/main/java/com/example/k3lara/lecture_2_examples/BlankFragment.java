package com.example.k3lara.lecture_2_examples;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements View.OnClickListener {



    public BlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_blank, container, false);
        Button b = (Button) v.findViewById(R.id.button);
        b.setOnClickListener(this);
        b.setText("Hallo");
        return v;
    }


    @Override
    public void onClick(View v) {
        //Button b = (Button) getActivity().findViewById( R.id.button);
        Button b = (Button) getView().findViewById(R.id.button);
        b.setText("Hej");
        MainActivity a = (MainActivity)getActivity();
        a.test = "retro";
        Toast toast = Toast.makeText(getActivity(),a.test,Toast.LENGTH_LONG);
        toast.show();
       /* FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.main,new BlankFragment2());
        ft.commit();*/

    }
}
