package com.example.oso.nuevaiotmx.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.oso.nuevaiotmx.R;
import com.example.oso.nuevaiotmx.views.ActivityDiscovery;
import com.example.oso.nuevaiotmx.views.Devices;
import com.example.oso.nuevaiotmx.views.Houses;
import com.example.oso.nuevaiotmx.views.Maincontrol;
import com.example.oso.nuevaiotmx.views.Profile;
import com.example.oso.nuevaiotmx.views.Programming;
import com.example.oso.nuevaiotmx.views.Rooms;
import com.example.oso.nuevaiotmx.views.Users;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oso on 08/09/2016.
 */
public class Menu_principal {
    public ActionBarDrawerToggle actionBarDrawerToggle;

    public DrawerLayout drawerLayout;
    private Context context;
    public ListView ListViewSliding;
    private List<ItemSlideMenu> listItem;
    private List<ItemSlideMenu> listSliding;
    public ListMenu adapter;

    public Menu_principal(Context context) {
        this.context = context;
        adapter = new ListMenu();
        add_list();
        listviewsliding();

    }



    public void add_list(){
        drawerLayout = (DrawerLayout) ((Activity)(context)).findViewById(R.id.drawer_layout);
        //LayoutInflater inflater = (LayoutInflater) ((Activity)(context)).getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sliding_menu, drawerLayout, true);
    }
    public void listviewsliding(){
        ListViewSliding = (ListView)((Activity)(context)).findViewById(R.id.sliding_menu);
        ListViewSliding.setAdapter(adapter);
        ListViewSliding.setItemChecked(0,true);
        drawerLayout.closeDrawer(ListViewSliding);
        ListViewSliding.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                //setTitle(adapter.getTitle(position));
                ListViewSliding.setItemChecked(position,true);
                replaceFragment(context,position);
                drawerLayout.closeDrawer(ListViewSliding);
            }
        });


    }




    public void replaceFragment(Context ctx,int pos){

        switch (pos) {
            case 0:
                Intent main = new Intent(ctx,Maincontrol.class);
                ctx.startActivity(main);
                break;
            case 1:
                Intent profile = new Intent(ctx,Profile.class);
                ctx.startActivity(profile);
                break;
            case 2:
                Intent houses = new Intent(ctx,Houses.class);
                ctx.startActivity(houses);
                break;
            case 3:
                Intent rooms = new Intent(ctx,Rooms.class);
                ctx. startActivity(rooms);
                break;
            case 4:
                Intent device = new Intent(ctx,Devices.class);
                ctx.startActivity(device);
                break;
            case 5:
                Intent users = new Intent(ctx,Users.class);
                ctx.startActivity(users);
                break;
            case 6:
                Intent program = new Intent(ctx,Programming.class);
                ctx.startActivity(program);
                break;
            case 7:
                Intent devices2 = new Intent(ctx,ActivityDiscovery.class);
                ctx.startActivity(devices2);
                break;

        }
    }

    public class ItemSlideMenu {
        private int imgid;
        private String title;
        public ItemSlideMenu(int imgid, String title) {
            this.imgid = imgid;
            this.title = title;
        }
        public int getImgid() {
            return imgid;
        }
        public void setImgid(int imgid) {
            this.imgid = imgid;
        }
        public String getTitle() {
            return title;
        }
        public void setTitle(String title) {
            this.title = title;
        }
    }

    public class ListMenu extends BaseAdapter{

        public  ListMenu(){
            listSliding = new ArrayList<>();
            listSliding. add(new ItemSlideMenu(R.drawable.houses,"Inicio"));
            listSliding. add(new ItemSlideMenu(R.drawable.profile,"Profile"));
            listSliding. add(new ItemSlideMenu(R.drawable.housess,"Houses"));
            listSliding. add(new ItemSlideMenu(R.drawable.rooms,"Rooms"));
            listSliding. add(new ItemSlideMenu(R.drawable.devices,"Devices"));

            listSliding. add(new ItemSlideMenu(R.drawable.users,"Users"));
            listSliding. add(new ItemSlideMenu(R.drawable.programming,"Programming"));
            listSliding. add(new ItemSlideMenu(R.drawable.devices,"Devices2"));
            listItem = listSliding;
        }

        @Override
        public int getCount() {return listItem.size();}
        @Override
        public Object getItem(int position) {
            return listItem.get(position);
        }
        @Override
        public long getItemId(int position) {
            return position;
        }

        public String getTitle(int position) {
            return listItem.get(position).getTitle();
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = View.inflate(context, R.layout.item_sliding_menu,null);
            ImageView img =(ImageView)v.findViewById(R.id.item_img);
            TextView tv = (TextView)v.findViewById(R.id.item_title);
            ItemSlideMenu item = listItem.get(position);
            img.setImageResource(item.getImgid());
            tv.setText(item.getTitle());
            return v;
        }

    }

}
