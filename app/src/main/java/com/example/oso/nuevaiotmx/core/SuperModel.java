package com.example.oso.nuevaiotmx.core;

/**
 * Created by JONATAN on 18/07/2016.
 */
public class SuperModel {

    public Http_uas http = new Http_uas();
    public SuperModel() {
        // TODO Auto-generated constructor stub
    }
    public void close() {

        System.out.println(this.toString());
    }

}
