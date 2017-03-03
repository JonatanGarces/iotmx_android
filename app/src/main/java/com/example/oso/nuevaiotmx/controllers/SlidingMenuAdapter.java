package com.example.oso.nuevaiotmx.controllers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.oso.nuevaiotmx.R;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Oso on 08/09/2016.
 */
public class SlidingMenuAdapter extends BaseAdapter{

    private Context context;
    private List<ItemSlideMenu> listItem;
    private List<SlidingMenuAdapter.ItemSlideMenu> listSliding;
    public SlidingMenuAdapter(Context context) {
        this.context = context;
        listSliding = new ArrayList<>();
        listSliding. add(new ItemSlideMenu(R.drawable.houses,"Inicio"));
        listSliding. add(new ItemSlideMenu(R.drawable.profile,"Profile"));
        listSliding. add(new ItemSlideMenu(R.drawable.housess,"Houses"));
        listSliding. add(new ItemSlideMenu(R.drawable.rooms,"Rooms"));
        listSliding. add(new ItemSlideMenu(R.drawable.devices,"Devices"));
        listSliding. add(new ItemSlideMenu(R.drawable.devices,"Devices2"));

        listSliding. add(new ItemSlideMenu(R.drawable.users,"Users"));
        listSliding. add(new ItemSlideMenu(R.drawable.programming,"Programming"));
        this.listItem = listSliding;
    }

    @Override
    public int getCount() {
        return listItem.size();
    }

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
}