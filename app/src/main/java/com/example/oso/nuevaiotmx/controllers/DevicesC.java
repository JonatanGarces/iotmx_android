package com.example.oso.nuevaiotmx.controllers;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

import com.example.oso.nuevaiotmx.core.Fwifi;
import com.example.oso.nuevaiotmx.models.DevicesM;
import com.example.oso.nuevaiotmx.views.Devices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by JONATAN on 14/09/2016.
 */
public class DevicesC {

    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION = 1001;
    Fwifi wifihelper = new Fwifi();
    String ITEM_KEY = "key";
    String ITEM_VALUE = "value";
    DevicesM model = new DevicesM();
    public class Multiprocesamiento2 extends Thread {
        protected boolean isRunning;
        protected Devices view;
        protected String pass;
        protected String ssid;
        public Multiprocesamiento2(Devices view,String ssid,String pass) {
            this.isRunning = true;
            this.view = view;
            this.ssid = ssid;
            this.pass=pass;
        }
        @Override
        public void run() {
            plotData2(view,ssid,pass);
        }
        public void setRunning(boolean isRunning) {
            this.isRunning = isRunning;
        }
    }

    public void plotData2(Devices view, String networkSSID,String password){
        String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && view.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            view.requestPermissions(permissions, PERMISSIONS_REQUEST_CODE_ACCESS_COARSE_LOCATION);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            WifiManager wfMgr = (WifiManager) view.getSystemService(Context.WIFI_SERVICE);
            List<ScanResult> results = wfMgr.getScanResults();
            for (ScanResult result : results) {
                HashMap<String, String> item = new HashMap<String, String>();
                if(result.SSID.toLowerCase().contains("iotmx")) {
                item.put(ITEM_KEY, result.SSID);
                item.put(ITEM_VALUE, result.capabilities);
                view.list1_add_item(item);
                }
            }
            //do something, permission was previously granted; or legacy device
        }
    }
    //getcurrentssid
    //ConnectCurrentNetwork


    //Multiprocesamiento mp = new Multiprocesamiento(v);
    //if(mp.isAlive()){
    //System.out.println("Vivo");
    //}else {
    //    makeToast(R.string.discover_connected_reading);
    //    mp.start();
    //}
    //mp.interrupt();


    public void connect_one_by_one(Devices view,String ssid,String pass,String ssid_list){
        boolean connected = ConnectCurrentNetwork(view,ssid_list,ssid_list);
        if(connected){
            System.out.println("CONECTADO");
            Multiprocesamiento mp = new Multiprocesamiento(view,ssid,pass);
            if(mp.isAlive()){
                //System.out.println("Vivo");
            }else {
                //    makeToast(R.string.discover_connected_reading);
                mp.start();
            }
            //mp.interrupt();
        }else{
            System.out.println("NO CONECTADO");
        }

    }
    public void GetDevices(Devices view,String pass,String ssid){
        WifiManager wfMgr = (WifiManager) view.getSystemService(Context.WIFI_SERVICE);
        List<ScanResult> results = wfMgr.getScanResults();
        int i=0;
        for (ScanResult result : results) {
            HashMap<String, String> item = new HashMap<String, String>();
            if(result.SSID.toLowerCase().contains("iotmx") ) {

               // if(i==1){

                    item.put(ITEM_KEY, result.SSID);
                    boolean connected = ConnectCurrentNetwork(view,result.SSID,result.SSID);
                    System.out.println("CONECTANDO  A DISPOSITIVO"+result.SSID);
                    System.out.println("CONECTANDO  A DISPOSITIVO"+result.SSID);
                    System.out.println("CONECTANDO  A DISPOSITIVO"+result.SSID);
                    if(connected){
                        System.out.println("CONECTADO  A DISPOSITIVO"+result.SSID);
                        System.out.println("CONECTADO  A DISPOSITIVO"+result.SSID);
                        System.out.println("CONECTADO  A DISPOSITIVO"+result.SSID);

                        Multiprocesamiento mp = new Multiprocesamiento(view,ssid,pass);
                        if(mp.isAlive()){
                            //System.out.println("Vivo");
                        }else {
                            //    makeToast(R.string.discover_connected_reading);
                            mp.start();
                        }
                        //mp.interrupt();


                    }
                    item.put(ITEM_VALUE, result.capabilities);

                //}
                i++;

            }
        }
    }

    public void sendhttp(Devices view,String ssid,String pass){

         String str = "\"";
         String str1 = "";
        ssid =ssid.replace( str, str1 );
        pass =pass.replace( str, str1 );
        String url ="http://192.168.111.1/register.lc?ssid="+ssid;

        url = url + "&password="+pass;
        System.out.println(url);
        List<Map<String, String>> data = model.funcion2(url);
    }
    public  boolean readyforhttp(Devices view) {
        boolean connected = false;
        ConnectivityManager connManager = (ConnectivityManager) view.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connManager != null ) {
            NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
            if(activeNetwork != null){
                if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                    if(activeNetwork.isConnected() && activeNetwork.getState() == NetworkInfo.State.CONNECTED &&  activeNetwork.isAvailable()){
                        connected=true;
                    }
                }
            }
        }
        return connected;
    }
    public String getCurrentSsid(Devices view) {
        String ssid = null;
        WifiManager wifiManager = (WifiManager) view.getSystemService(Context.WIFI_SERVICE);
        if (!wifiManager.isWifiEnabled()) {
            wifiManager.setWifiEnabled(true);
        }
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        if(wifiInfo.getSSID() != null && wifiInfo.getSSID() != "") {
            ssid = wifiInfo.getSSID();
           // String str = "\"";
           // String str1 = "";
           // ssid =ssid.replace( str, str1 );

        }
        return ssid;
    }

    public boolean ConnectCurrentNetwork(Devices view, String networkSSID,String password) {
        System.out.println("attempt to connect to "+networkSSID+"|"+password);
        String networkType = "WPA";
        WifiConfiguration wfc = new WifiConfiguration();
        WifiManager wfMgr = (WifiManager) view.getSystemService(Context.WIFI_SERVICE);
        wfMgr.disconnect();
        boolean conectado = false;
        boolean configurado = false;
        boolean agregado = false;
        wfc.SSID = networkSSID;
        wfc = wifihelper.WifiNetworkTypes(wfc, networkType, password);
        List<WifiConfiguration> list = wfMgr.getConfiguredNetworks();
        for (WifiConfiguration i : list) {
            if (i.SSID != null && (i.SSID.toLowerCase().equals("\"" + networkSSID.toLowerCase() + "\""))) {
                agregado = true;
                wfc.networkId = i.networkId;
                break;
            }
        }
        int networkId = -1;
        if (agregado) {
            networkId = wfMgr.updateNetwork(wfc);
            if (networkId != -1) {
                configurado = true;
            }
        } else {
            networkId = wfMgr.addNetwork(wfc);
            if (networkId != -1) {
                configurado = true;
                agregado = true;
            }
        }
        if (agregado && configurado) {
            if (wfMgr.enableNetwork(networkId, true)) {
                conectado = true;
            }
        }
        return conectado;
    }


     public class Multiprocesamiento extends Thread{
        protected boolean isRunning;
        protected Devices view;
        protected String ssid ;
        protected String pass;
        public Multiprocesamiento(Devices view,String ssid,String pass) {
            this.isRunning = true;
            this.view=view;
            this.ssid =ssid;
            this.pass=pass;
        }
        @Override
        public void run() {
                while (isRunning ==true) {
                    System.out.println("WHILE");
                    System.out.println("WHILE");
                    System.out.println("WHILE");

                    if (readyforhttp(view)) {
                        setRunning(false);
                    }
                }


            System.out.println("YA SE CONECTO A IOTMX DISPOISITO");
            System.out.println("YA SE CONECTO A IOTMX DISPOISITO");
            System.out.println("YA SE CONECTO A IOTMX DISPOISITO");
            //try {
            //Thread.sleep(1000);
            sendhttp(view,ssid,pass);
            //} catch (InterruptedException e) {
            //  e.printStackTrace();
            //}

           Thread.currentThread().interrupt();
        }
        public void setRunning(boolean isRunning){
            this.isRunning = isRunning;
        }
    }

}
