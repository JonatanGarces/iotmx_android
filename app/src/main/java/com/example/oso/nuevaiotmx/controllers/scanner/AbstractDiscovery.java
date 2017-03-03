package com.example.oso.nuevaiotmx.controllers.scanner;

import android.os.AsyncTask;

import com.example.oso.nuevaiotmx.R;
import com.example.oso.nuevaiotmx.controllers.scanner.Network.HostBean;
import com.example.oso.nuevaiotmx.views.ActivityDiscovery;

import java.lang.ref.WeakReference;

public abstract class AbstractDiscovery extends AsyncTask<Void, HostBean, Void> {
    protected int hosts_done = 0;
    final protected WeakReference<ActivityDiscovery> mDiscover;
    protected long ip;
    protected long start = 0;
    protected long end = 0;
    protected long size = 0;
    public AbstractDiscovery(ActivityDiscovery discover) {
        mDiscover = new WeakReference<ActivityDiscovery>(discover);
    }
    public void setNetwork(long ip, long start, long end) {
        this.ip = ip;
        this.start = start;
        this.end = end;
    }
    abstract protected Void doInBackground(Void... params);
    @Override
    protected void onPreExecute() {
        size = (int) (end - start + 1);
        if (mDiscover != null) {
            final ActivityDiscovery discover = mDiscover.get();
        }
    }
    @Override
    protected void onProgressUpdate(HostBean... host) {
        if (mDiscover != null) {
            final ActivityDiscovery discover = mDiscover.get();
            if (discover != null) {
                if (!isCancelled()) {
                    if (host[0] != null) {
                        discover.addHost(host[0]);
                    }
                }
            }
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        if (mDiscover != null) {
            final ActivityDiscovery discover = mDiscover.get();
            if (discover != null) {
                discover.makeToast(R.string.discover_finished);
                discover.stopDiscovering();
            }
        }
    }

}
