package com.example.oso.nuevaiotmx.views;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.oso.nuevaiotmx.Fragment.Fragmento1;
import com.example.oso.nuevaiotmx.Fragment.Fragmento2;
import com.example.oso.nuevaiotmx.Fragment.Fragmento3;
import com.example.oso.nuevaiotmx.R;
import com.example.oso.nuevaiotmx.controllers.Menu_principal;
import com.example.oso.nuevaiotmx.controllers.SlidingMenuAdapter;
import com.example.oso.nuevaiotmx.models.ItemSlideMenu;

import java.util.List;

/**
 * Created by Oso on 10/09/2016.
 */
public class Profile extends ActionBarActivity {

    private Menu miMenu = null;
    private Menu_principal adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        adapter = new Menu_principal(this);
        adapter.actionBarDrawerToggle = new ActionBarDrawerToggle(this,adapter.drawerLayout, R.string.drawer_opened,R.string.drawer_closed){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        adapter.drawerLayout.setDrawerListener(adapter.actionBarDrawerToggle);

    }
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        miMenu = menu;
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }*/

    public boolean onOptionsItemSelected(MenuItem item){

        if(adapter.actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        aplicarMenu(item);
        return super.onOptionsItemSelected(item);
    }

    public void aplicarMenu(MenuItem item){

        switch (item.getItemId()){
            case R.id.ajustes:
                Intent productIntent = new Intent(this,Settings.class);
                startActivity(productIntent);
                break;
            case R.id.iotmx:
                /*Intent productIntent = new Intent(this,MainActivity.class);
                startActivity(productIntent);
                return true;*/
                //startActivity(new Intent("informacion"));

                //Toast.makeText(Maincontrol.this, "Oprimi el Tercero", Toast.LENGTH_SHORT).show();
                break;
            case R.id.salir:
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                finish();
                break;
        }
    }
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        adapter.actionBarDrawerToggle.syncState();
    }
    public boolean onContextItemSelected(MenuItem item){
        aplicarMenu(item);
        return true;
    }
}



