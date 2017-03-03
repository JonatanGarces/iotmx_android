package com.example.oso.nuevaiotmx.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.oso.nuevaiotmx.R;

/**
 * Created by Oso on 07/09/2016.
 */
public class NewAccount extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
