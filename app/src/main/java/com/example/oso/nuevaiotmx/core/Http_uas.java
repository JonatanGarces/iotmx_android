package com.example.oso.nuevaiotmx.core;

import android.os.StrictMode;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by JONATAN on 18/07/2016.
 */
public class Http_uas {

    public List<Map<String, String>> getRows(String url) {
        List<Map<String, String>> data = new ArrayList<>();
        String json = getJSON(url);

        if(json != null && json != "" && !json.isEmpty() && json.trim() != "{}"){
        try {
            json = json.trim();
            System.out.println(json);
            JSONArray jsonArray = new JSONArray(json);
            for(int a = 0 ; a < jsonArray.length() ; a++) {
                JSONObject row = jsonArray.getJSONObject(a);

                Map<String, String> map = new HashMap<String, String>();
                for(int i = 0; i<row.names().length(); i++){
                    map.put(row.names().getString(i),row.get(row.names().getString(i)).toString());
                }
                data.add(a,map);
            }
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        }else{
            data=null;
        }
        return data;
    }

    public String http_response(String url1){
        int timeout=150000;
        HttpURLConnection connection = null;
        try {
            URL url = new URL(url1);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-length", "0");
            connection.setUseCaches(false);
            connection.setAllowUserInteraction(false);
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
           connection.connect();
            int status = connection.getResponseCode();
            switch (status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line+"\n");
                    }
                    br.close();
                    return sb.toString();
                default:

                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

    public String getJSON(String url) {
        int timeout =15000;
        System.setProperty("java.net.preferIPv4Stack" , "true");
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL u = new URL(url);
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            if(c !=null){
                c.setConnectTimeout(timeout);
                c.setReadTimeout(timeout);
                c.setRequestMethod("GET");
                //c.setDoInput(true);
                c.setRequestProperty("Content-length", "0");
                c.setUseCaches(false);
                //c.setAllowUserInteraction(false);
                c.connect();
                int status = c.getResponseCode();

                switch (status) {
                    case 200:
                    case 201:
                        BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
                        StringBuilder sb = new StringBuilder();
                        String line;
                        while ((line = br.readLine()) != null) {
                            sb.append(line+"\n");
                        }
                        br.close();
                        return sb.toString();
                }
                c.disconnect();
            }

        }catch(SocketTimeoutException st){

            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, st);
            return null;
        } catch (MalformedURLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException io) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, io);
            return null;
        } finally {
           // if (c != null) {
                //try {
                //    c.disconnect();
                //} catch (Exception ex) {
                  //  Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                //}
            //}
        }
        return null;
    }


}
