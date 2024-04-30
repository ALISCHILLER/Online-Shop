package com.varanegar.vaslibrary.ui.report.report_new.invoice_balance;

import com.varanegar.framework.util.report.SimpleReportAdapter;
import com.varanegar.vaslibrary.ui.report.report_new.invoice_balance.model.ProductInvoiveBalanceReportModel;
import com.varanegar.vaslibrary.ui.report.report_new.webApi.ReportApi;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;

public class InvoiceBalanceReportFragment extends BaseInvoiceBalanceReportFragment<ProductInvoiveBalanceReportModel> {

    @Override
    protected Call<List<ProductInvoiveBalanceReportModel>> reportApi() {
        return new ReportApi(getContext()).product(Collections.singletonList
                (getDealerId().toString()), getStartDateString(), getEndDateString());
    }

    @Override
    protected SimpleReportAdapter<ProductInvoiveBalanceReportModel> createAdapter() {
        return new ProductInvoiceReportAdapter(getVaranegarActvity());
    }

    @Override
    protected String getTitle() {
        return "گزارش مانده فاکتور";
    }

    @Override
    protected String isEnabled() {
        return null;
    }
}