package com.example.oso.nuevaiotmx.models;

/**
 * Created by Oso on 08/09/2016.
 */
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
