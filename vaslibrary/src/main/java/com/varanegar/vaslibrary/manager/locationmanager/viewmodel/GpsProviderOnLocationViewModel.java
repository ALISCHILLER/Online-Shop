package com.varanegar.vaslibrary.manager.locationmanager.viewmodel;

import com.varanegar.vaslibrary.webapi.tracking.TrackingRequestModel;

/**
 * Created by A.Torabi on 12/17/2017.
 */

public class GpsProviderOnLocationViewModel extends BaseEventLocationViewModel {

    public DeviceEventViewModel eventData;

    @Override
    public void prepareForTracking(TrackingRequestModel trackingRequestModel) {
        trackingRequestModel.pointEvent.add(this);
        PersonnelDailyActivityEventTypeId = EventTypeId.GpsOn;
    }

    @Override
    public boolean IsImportant() {
        return true;
    }
}