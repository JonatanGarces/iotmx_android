package com.example.oso.nuevaiotmx.models;

import com.example.oso.nuevaiotmx.core.SuperModel;

import java.util.List;
import java.util.Map;

/**
 * Created by JONATAN on 14/09/2016.
 */
public class DevicesM extends SuperModel {

    public List<Map<String, String>> funcion1(String url){
        List<Map<String, String>>  rows = http.getRows(url);
        return rows;
    }

    public List<Map<String, String>> funcion2(String url){
        List<Map<String, String>>  rows = http.getRows(url);
        return rows;
    }

}
