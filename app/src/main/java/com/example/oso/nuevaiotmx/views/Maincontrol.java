package com.example.oso.nuevaiotmx.views;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.oso.nuevaiotmx.R;
import com.example.oso.nuevaiotmx.controllers.Menu_principal;
import com.example.oso.nuevaiotmx.controllers.SlidingMenuAdapter;


/**
 * Created by Oso on 08/09/2016.
 */
public class Maincontrol extends ActionBarActivity {

    private Menu_principal adapter;
    private Menu miMenu = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincontrol);
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
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        adapter.actionBarDrawerToggle.syncState();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(adapter.actionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        aplicarMenu(item);
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        miMenu = menu;
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    public void aplicarMenu(MenuItem item){
        switch (item.getItemId()){
            case R.id.ajustes:
                Intent productIntent = new Intent(this,Settings.class);
                startActivity(productIntent);
                break;
            case R.id.iotmx:
                Intent iotmx = new Intent(this,Iotmx.class);
                startActivity(iotmx);
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
    public boolean onContextItemSelected(MenuItem item){
        aplicarMenu(item);
        return true;
    }
}
