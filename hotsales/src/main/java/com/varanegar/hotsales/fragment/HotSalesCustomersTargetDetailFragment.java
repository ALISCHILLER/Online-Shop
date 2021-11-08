package com.varanegar.hotsales.fragment;

import com.varanegar.framework.base.VaranegarFragment;
import com.varanegar.vaslibrary.ui.fragment.CustomerTargetReportDetailContentFragment;

/**
 * Created by g.aliakbar on 18/04/2018.
 */

public class HotSalesCustomersTargetDetailFragment extends CustomerTargetReportDetailContentFragment {
    @Override
    protected VaranegarFragment getContentFragment() {
        return new HotSalesCustomersContentFragment();
    }
}