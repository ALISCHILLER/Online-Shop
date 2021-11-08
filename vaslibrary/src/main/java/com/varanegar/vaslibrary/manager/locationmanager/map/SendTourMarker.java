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
import com.varanegar.vaslibrary.manager.locationmanager.viewmodel.SendTourLocationViewModel;

import java.util.Locale;

/**
 * Created by A.Torabi on 6/9/2018.
 */

public class SendTourMarker extends TrackingMarker<SendTourLocationViewModel> {
    public SendTourMarker(@NonNull Activity activity, SendTourLocationViewModel locationModel) {
        super(activity, locationModel, true);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.marker_base_event, null);
        ImageView iconImageView = view.findViewById(R.id.icon_image_view);
        iconImageView.setImageResource(R.drawable.closetour);
        SendTourLocationViewModel sendTourLocationViewModel = getLocationViewModel();
        TextView timeTextView = view.findViewById(R.id.time_text_view);
        timeTextView.setText(DateHelper.toString(sendTourLocationViewModel.ActivityDate, DateFormat.Complete, Locale.getDefault()));
        return view;
    }

    @Override
    public float zIndex() {
        return 3;
    }
}