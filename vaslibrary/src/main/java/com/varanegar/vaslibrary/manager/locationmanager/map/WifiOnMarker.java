package com.varanegar.vaslibrary.manager.locationmanager.map;

import android.app.Activity;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.varanegar.framework.util.datetime.DateFormat;
import com.varanegar.framework.util.datetime.DateHelper;
import com.varanegar.vaslibrary.R;
import com.varanegar.vaslibrary.manager.locationmanager.viewmodel.WifiOnLocationViewModel;

import java.util.Locale;

/**
 * Created by A.Torabi on 6/9/2018.
 */

public class WifiOnMarker extends TrackingMarker<WifiOnLocationViewModel> {
    public WifiOnMarker(@NonNull Activity activity, WifiOnLocationViewModel locationModel) {
        super(activity, locationModel, true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.marker_base_event, null);
        ImageView iconImageView = view.findViewById(R.id.icon_image_view);
        iconImageView.setImageResource(R.drawable.wifion);
        WifiOnLocationViewModel wifiOnLocationViewModel = getLocationViewModel();
        TextView timeTextView = view.findViewById(R.id.time_text_view);
        timeTextView.setText(DateHelper.toString(wifiOnLocationViewModel.ActivityDate, DateFormat.Complete, Locale.getDefault()));
        return view;
    }

    @Override
    public float zIndex() {
        return 3;
    }
}