package com.example.oso.nuevaiotmx.views;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import com.example.oso.nuevaiotmx.R;

/**
 * Created by Oso on 08/09/2016.
 */
public class Settings extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}