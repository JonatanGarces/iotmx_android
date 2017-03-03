package com.example.oso.nuevaiotmx.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.oso.nuevaiotmx.R;

/**
 * Created by Oso on 12/09/2016.
 */
public class Iotmx extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
