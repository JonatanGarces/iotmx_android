package com.example.oso.nuevaiotmx.core;

import android.net.wifi.WifiConfiguration;

/**
 * Created by JONATAN on 07/09/2016.
 */
public class Fwifi {

    public WifiConfiguration WifiNetworkTypes(WifiConfiguration wfc, String networkType, String password) {
        switch (networkType) {
            case "WEP":
                wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                wfc.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.SHARED);
                wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                wfc.wepKeys[0] = password;
                wfc.wepTxKeyIndex = 0;
                break;
            case "WPA":
                wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                wfc.preSharedKey = "\"".concat(password).concat("\"");
                break;
            case "OPEN":
                wfc.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                wfc.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wfc.allowedProtocols.set(WifiConfiguration.Protocol.WPA);
                wfc.allowedAuthAlgorithms.clear();
                wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wfc.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP104);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wfc.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                break;
            default:
                break;
        }
        return wfc;
    }
}
