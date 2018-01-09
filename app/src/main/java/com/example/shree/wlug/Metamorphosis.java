package com.example.shree.wlug;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 */
public class Metamorphosis extends Fragment {

    Button register;

    public Metamorphosis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_metamorphosis,container,false);
        register= (Button) view.findViewById(R.id.registerEvent);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getActivity(),MetaForm.class));
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

}
