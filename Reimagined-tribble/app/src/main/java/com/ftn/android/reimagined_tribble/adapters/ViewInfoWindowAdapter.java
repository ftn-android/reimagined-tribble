package com.ftn.android.reimagined_tribble.adapters;

import android.view.LayoutInflater;
import android.view.View;

import com.ftn.android.reimagined_tribble.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by ftn/tim
 */
public class ViewInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;

    public ViewInfoWindowAdapter(LayoutInflater layoutInflater) {
        mWindow = layoutInflater.inflate(R.layout.map_view_info_window, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return mWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

}
