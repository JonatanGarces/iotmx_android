
package com.example.oso.nuevaiotmx.controllers.scanner.Utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.provider.Settings;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.example.oso.nuevaiotmx.R;
import com.example.oso.nuevaiotmx.controllers.scanner.Network.NetInfo;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Prefs extends PreferenceActivity implements OnSharedPreferenceChangeListener {
    // TODO: Show values in summary
    private final String TAG = "Prefs";
    public final static String KEY_RESOLVE_NAME = "resolve_name";
    public final static boolean DEFAULT_RESOLVE_NAME = true;
    public final static String KEY_PORT_START = "port_start";
    public final static String DEFAULT_PORT_START = "1";
    public final static String KEY_PORT_END = "port_end";
    public final static String DEFAULT_PORT_END = "1024";
    public static final String KEY_RATECTRL_ENABLE = "ratecontrol_enable";
    public static final boolean DEFAULT_RATECTRL_ENABLE = true;
    public final static String KEY_TIMEOUT_DISCOVER = "timeout_discover";
    public final static String DEFAULT_TIMEOUT_DISCOVER = "500";
    public static final String KEY_MOBILE = "allow_mobile";
    public static final boolean DEFAULT_MOBILE = false;
    public static final String KEY_INTF = "interface";
    public static final String DEFAULT_INTF = null;
    public static final String KEY_IP_START = "ip_start";
    public static final String DEFAULT_IP_START = "0.0.0.0";
    public static final String KEY_IP_END = "ip_end";
    public static final String DEFAULT_IP_END = "0.0.0.0";
    public static final String KEY_IP_CUSTOM = "ip_custom";
    public static final boolean DEFAULT_IP_CUSTOM = false;
    
    public static final String KEY_CIDR_CUSTOM = "cidr_custom";
    public static final boolean DEFAULT_CIDR_CUSTOM = false;

    public static final String KEY_CIDR = "cidr";
    public static final String DEFAULT_CIDR = "24";

    public static final String KEY_WIFI = "wifi";

    private Context ctxt;
    private PreferenceScreen ps = null;
    private String before_ip_start;
    private String before_ip_end;
    private String before_port_start;
    private String before_port_end;

    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        ctxt = getApplicationContext();

        ps = getPreferenceScreen();
        ps.getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
        // Default state of checkboxes
        checkTimeout(KEY_TIMEOUT_DISCOVER, KEY_RATECTRL_ENABLE, false);
        // Before change values
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(ctxt);
        before_ip_start = prefs.getString(KEY_IP_START, DEFAULT_IP_START);
        before_ip_end = prefs.getString(KEY_IP_END, DEFAULT_IP_END);
        before_port_start = prefs.getString(KEY_PORT_START, DEFAULT_PORT_START);
        before_port_end = prefs.getString(KEY_PORT_END, DEFAULT_PORT_END);

        // Interfaces list
        ListPreference intf = (ListPreference) ps.findPreference(KEY_INTF);
        try {
            ArrayList<NetworkInterface> nis = Collections.list(NetworkInterface
                    .getNetworkInterfaces());
            final int len = nis.size();
            // If there's more than just 2 interfaces (local + network)
            if (len > 2) {
                String[] intf_entries = new String[len - 1];
                String[] intf_values = new String[len - 1];
                int i = 0;
                for (int j = 0; j < len; j++) {
                    NetworkInterface ni = nis.get(j);
                    if (!ni.getName().equals("lo")) {
                        intf_entries[i] = ni.getDisplayName();
                        intf_values[i] = ni.getName();
                        i++;
                    }
                }
                intf.setEntries(intf_entries);
                intf.setEntryValues(intf_values);
            } else {
                intf.setEnabled(false);
            }
        } catch (SocketException e) {
            Log.e(TAG, e.getMessage());
            intf.setEnabled(false);
        }

        // Wifi settings listener
        ((Preference) ps.findPreference(KEY_WIFI))
                .setOnPreferenceClickListener(new OnPreferenceClickListener() {
                    public boolean onPreferenceClick(Preference preference) {
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                        return true;
                    }
                });




        // Contact


    }

    public void onSharedPreferenceChanged(SharedPreferences prefs, String key) {
        if (key.equals(KEY_PORT_START) || key.equals(KEY_PORT_END)) {
            checkPortRange();
        } else if (key.equals(KEY_IP_START) || key.equals(KEY_IP_END)) {
            checkIpRange();
        //} else if (key.equals(KEY_NTHREADS)) {
        //    checkMaxThreads();
        } else if (key.equals(KEY_RATECTRL_ENABLE)) {
            checkTimeout(KEY_TIMEOUT_DISCOVER, KEY_RATECTRL_ENABLE, false);
        } else if (key.equals(KEY_CIDR_CUSTOM)) {
            CheckBoxPreference cb = (CheckBoxPreference) ps.findPreference(KEY_CIDR_CUSTOM);
            if (cb.isChecked()) {
                ((CheckBoxPreference)ps.findPreference(KEY_IP_CUSTOM)).setChecked(false);
            }
            sendBroadcast(new Intent(WifiManager.WIFI_STATE_CHANGED_ACTION));
        } else if (key.equals(KEY_IP_CUSTOM)) {
            CheckBoxPreference cb = (CheckBoxPreference) ps.findPreference(KEY_IP_CUSTOM);
            if (cb.isChecked()) {
                ((CheckBoxPreference)ps.findPreference(KEY_CIDR_CUSTOM)).setChecked(false);
            }
            sendBroadcast(new Intent(WifiManager.WIFI_STATE_CHANGED_ACTION));
        }
    }

    private void checkTimeout(String key_pref, String key_cb, boolean value) {
        EditTextPreference timeout = (EditTextPreference) ps.findPreference(key_pref);
        CheckBoxPreference cb = (CheckBoxPreference) ps.findPreference(key_cb);
        if (cb.isChecked()) {
            timeout.setEnabled(value);
        } else {
            timeout.setEnabled(!value);
        }
    }

    private void checkIpRange() {
        EditTextPreference ipStartEdit = (EditTextPreference) ps.findPreference(KEY_IP_START);
        EditTextPreference ipEndEdit = (EditTextPreference) ps.findPreference(KEY_IP_END);
        // Check if these are valid IP's
        Pattern pattern = Pattern
                .compile("((25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9])\\.(25[0-5]|2[0-4]"
                        + "[0-9]|[0-1][0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1]"
                        + "[0-9]{2}|[1-9][0-9]|[1-9]|0)\\.(25[0-5]|2[0-4][0-9]|[0-1][0-9]{2}"
                        + "|[1-9][0-9]|[0-9]))");
        Matcher matcher1 = pattern.matcher(ipStartEdit.getText());
        Matcher matcher2 = pattern.matcher(ipEndEdit.getText());
        if (!matcher1.matches() || !matcher2.matches()) {
            ipStartEdit.setText(before_ip_start);
            ipEndEdit.setText(before_ip_end);
            Toast.makeText(ctxt, R.string.preferences_error4, Toast.LENGTH_LONG).show();
            return;
        }
        try {
            long ipStart = NetInfo.getUnsignedLongFromIp(ipStartEdit.getText());
            long ipEnd = NetInfo.getUnsignedLongFromIp(ipEndEdit.getText());
            if (ipStart > ipEnd) {
                ipStartEdit.setText(before_ip_start);
                ipEndEdit.setText(before_ip_end);
                Toast.makeText(ctxt, R.string.preferences_error1, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            ipStartEdit.setText(before_ip_start);
            ipEndEdit.setText(before_ip_end);
            Toast.makeText(ctxt, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void checkPortRange() {
        // Check if port start is bigger or equal than port end
        EditTextPreference portStartEdit = (EditTextPreference) ps.findPreference(KEY_PORT_START);
        EditTextPreference portEndEdit = (EditTextPreference) ps.findPreference(KEY_PORT_END);
        try {
            int portStart = Integer.parseInt(portStartEdit.getText());
            int portEnd = Integer.parseInt(portEndEdit.getText());
            if (portStart >= portEnd) {
                portStartEdit.setText(before_port_start);
                portEndEdit.setText(before_port_end);
                Toast.makeText(ctxt, R.string.preferences_error1, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            portStartEdit.setText(before_port_start);
            portEndEdit.setText(before_port_end);
            Toast.makeText(ctxt, e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
        }
    }


}
