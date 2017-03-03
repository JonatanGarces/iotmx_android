package com.example.oso.nuevaiotmx.models;

import com.example.oso.nuevaiotmx.core.SuperModel;

import java.util.List;
import java.util.Map;

/**
 * Created by JONATAN on 26/08/2016.
 */
public class ActivityDiscoveryModel extends SuperModel {


    public List<Map<String, String>> funcion1(String ip){
        String url ="http://"+ip+"/contact.lc?door="+"3";
            System.out.println(url);
        List<Map<String, String>>  rows = http.getRows(url);
        return rows;
    }

}
