package com.varanegar.hotsales;

import android.os.Bundle;

import com.varanegar.framework.util.jobscheduler.JobSchedulerService;
import com.varanegar.hotsales.fragment.HotSalesCustomersFragment;
import com.varanegar.hotsales.fragment.HotSalesLoginFragment;
import com.varanegar.hotsales.fragment.HotSalesSendTourFragment;
import com.varanegar.hotsales.jobscheduler.HotSalestJobScheduler;
import com.varanegar.vaslibrary.base.VasActivity;
import com.varanegar.vaslibrary.manager.UserManager;
import com.varanegar.vaslibrary.manager.tourmanager.TourManager;
import com.varanegar.vaslibrary.model.user.UserModel;

public class MainActivity extends VasActivity {

    @Override
    protected void onStart() {
        super.onStart();
        JobSchedulerService.start(this, R.mipmap.ic_launcher, HotSalestJobScheduler.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean firstCreation = false;
        if (savedInstanceState != null)
            firstCreation = savedInstanceState.getBoolean("firstCreation", false);
        if (!firstCreation) {
            TourManager tourManager = new TourManager(this);
            UserModel userModel = UserManager.readFromFile(this);
            if (userModel == null) {
                HotSalesLoginFragment loginFragment = new HotSalesLoginFragment();
                pushFragment(loginFragment);
            } else if (tourManager.isTourAvailable()) {
                HotSalesCustomersFragment customerFragment = new HotSalesCustomersFragment();
                pushFragment(customerFragment);
            } else if (tourManager.isTourSending()) {
                pushFragment(new HotSalesSendTourFragment());
            } else {
                HotSalesTourReportFragment profileFragment = new HotSalesTourReportFragment();
                pushFragment(profileFragment);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("firstCreation", true);
    }
}