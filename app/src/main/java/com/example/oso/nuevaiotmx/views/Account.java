package com.example.oso.nuevaiotmx.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.oso.nuevaiotmx.R;

/**
 * Created by Oso on 07/09/2016.
 */

public class Account extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button login =(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent madre = new Intent(v.getContext(),Maincontrol.class);
                startActivityForResult(madre, 0);
            }
        });
    }
}
