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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.oso.nuevaiotmx.Fragment.Fragmento1;
import com.example.oso.nuevaiotmx.Fragment.Fragmento2;
import com.example.oso.nuevaiotmx.Fragment.Fragmento3;
import com.example.oso.nuevaiotmx.R;
import com.example.oso.nuevaiotmx.controllers.DevicesC;
import com.example.oso.nuevaiotmx.controllers.Menu_principal;
import com.example.oso.nuevaiotmx.controllers.SlidingMenuAdapter;
import com.example.oso.nuevaiotmx.models.ItemSlideMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Oso on 10/09/2016.
 */
public class Devices extends ActionBarActivity {

    Button button1;
    Button button4;
    ListView lv;
    String ITEM_KEY = "key";
    String ITEM_VALUE = "value";
    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter2;
    ListView lv2;
    ArrayList<HashMap<String, String>> arraylist2 = new ArrayList<HashMap<String, String>>();
    DevicesC controller = new DevicesC();




    private Menu miMenu = null;
    private Menu_principal adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.devices);
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

        lv = (ListView) this.findViewById(R.id.list);
        adapter2 = new SimpleAdapter(this, arraylist, R.layout.configuration_row,
                new String[]{ITEM_KEY, ITEM_VALUE},
                new int[]{R.id.list_value, R.id.email});
        lv.setAdapter(adapter2);



        button1 = (Button) findViewById(R.id.buttonScan);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String pass="";
                String ssid="";
                list1_clear();
                DevicesC.Multiprocesamiento2 mp2 = controller.new Multiprocesamiento2(Devices.this,ssid,pass);
                mp2.start();
            }
        });

        TextView txt15=(TextView) findViewById(R.id.textView15);
        String ssid = controller.getCurrentSsid(this);
        txt15.setText(ssid);

        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String ssid=((TextView) findViewById(R.id.textView15)).getText().toString();
                String pass=((EditText) findViewById(R.id.editText6)).getText().toString();
                Boolean PASS= controller.ConnectCurrentNetwork(Devices.this,ssid,pass);
                if(PASS){
                    System.out.println("si es contraseña");
                    System.out.println("si es contraseña");
                    System.out.println("si es contraseña");
                    System.out.println("si es contraseña");
                    System.out.println("si es contraseña");
                    controller.GetDevices(Devices.this,ssid,pass);
                }
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view,
                                    int position, long id) {
                String ssid=((TextView) findViewById(R.id.textView15)).getText().toString();
                String pass=((EditText) findViewById(R.id.editText6)).getText().toString();
                Boolean PASS= controller.ConnectCurrentNetwork(Devices.this,ssid,pass);

                System.out.println(ssid + "|"+pass);
                if(PASS){
                    String  ssid_list = ((TextView)view.findViewById(R.id.list_value)).getText().toString();
                    System.out.println(ssid + "|"+pass+"|"+ssid_list);
                    controller.connect_one_by_one(Devices.this,ssid,pass,ssid_list);
                }

            }
        });

    }
    public void list1_add_item(HashMap<String, String> item) {
        arraylist.add(item);
        System.out.println(arraylist);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter2.notifyDataSetChanged();

            }
        });
    }
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

    public void list1_clear() {
        arraylist.clear();
        adapter2.notifyDataSetChanged();
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

