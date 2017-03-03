package com.example.oso.nuevaiotmx.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.oso.nuevaiotmx.R;

/**
 * Created by Oso on 07/09/2016.
 */
public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button crearcuenta = (Button)findViewById(R.id.crearcuenta);
        crearcuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nuevacuenta = new Intent(v.getContext(),NewAccount.class);
                startActivityForResult(nuevacuenta, 0);
            }
        });
        Button cuentaexistente = (Button)findViewById(R.id.cuentaexistente);
        cuentaexistente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cuentaexistente = new Intent(v.getContext(),Account.class);
                startActivityForResult(cuentaexistente, 0);
            }
        });
        Button salir =(Button)findViewById(R.id.salir);
        salir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
