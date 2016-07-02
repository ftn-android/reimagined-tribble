package com.ftn.android.reimagined_tribble.adapters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ftn.android.reimagined_tribble.R;
import com.ftn.android.reimagined_tribble.model.Entity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

/**
 * Created by ftn/tim
 */
public class ViewInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

    private final View mWindow;
    private Entity entity;

    public ViewInfoWindowAdapter(LayoutInflater layoutInflater, Entity entity) {
        mWindow = layoutInflater.inflate(R.layout.map_view_info_window, null);
        this.entity = entity;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker) {
        ImageView badgeImageView = (ImageView) mWindow.findViewById(R.id.view_info_window_badge);
        Bitmap bitmap = BitmapFactory.decodeByteArray(entity.getImage(), 0, entity.getImage().length);
        badgeImageView.setImageBitmap(bitmap);

        TextView titleTextView = (TextView) mWindow.findViewById(R.id.view_info_window_title);
        titleTextView.setText(entity.getName());

        TextView snippetTextView = (TextView) mWindow.findViewById(R.id.view_info_window_snippet);
        snippetTextView.setText(entity.getDescription());

        return mWindow;
    }

}
