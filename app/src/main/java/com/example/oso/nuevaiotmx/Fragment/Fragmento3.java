package com.example.oso.nuevaiotmx.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.oso.nuevaiotmx.R;

/**
 * Created by Oso on 08/09/2016.
 */
public class Fragmento3 extends Fragment{

    public Fragmento3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmento3sliding, container, false);
        return rootView;
    }
}
