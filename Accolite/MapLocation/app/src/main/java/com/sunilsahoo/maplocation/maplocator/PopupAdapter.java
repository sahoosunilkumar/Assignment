package com.sunilsahoo.maplocation.maplocator;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.sunilsahoo.maplocation.R;

class PopupAdapter implements InfoWindowAdapter {
    private View popup = null;
    private LayoutInflater inflater = null;
    private Marker lastMarker = null;

    PopupAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return (null);
    }

    @SuppressLint("InflateParams")
    @Override
    public View getInfoContents(Marker marker) {
        if (popup == null) {
            popup = inflater.inflate(R.layout.popup, null);
        }

        if (lastMarker == null
                || !lastMarker.getId().equals(marker.getId())) {
            lastMarker = marker;

            TextView tv = (TextView) popup.findViewById(R.id.title);

            tv.setText(marker.getTitle());
            tv = (TextView) popup.findViewById(R.id.description);
            tv.setText(marker.getSnippet());
        }

        return (popup);
    }
}