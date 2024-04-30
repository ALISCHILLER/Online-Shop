package com.varanegar.vaslibrary.ui.report.report_new.customerNoSaleReport;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.varanegar.framework.base.MainVaranegarActivity;
import com.varanegar.framework.base.VaranegarFragment;
import com.varanegar.framework.network.listeners.ApiError;
import com.varanegar.framework.network.listeners.WebCallBack;
import com.varanegar.framework.util.component.PairedItems;
import com.varanegar.framework.util.component.SimpleToolbar;
import com.varanegar.framework.util.component.cutemessagedialog.CuteMessageDialog;
import com.varanegar.framework.util.component.cutemessagedialog.Icon;
import com.varanegar.framework.util.datetime.DateFormat;
import com.varanegar.framework.util.datetime.DateHelper;
import com.varanegar.framework.util.report.ReportView;
import com.varanegar.framework.util.report.SimpleReportAdapter;
import com.varanegar.vaslibrary.R;
import com.varanegar.vaslibrary.base.VasHelperMethods;
import com.varanegar.vaslibrary.manager.ProductGroupManager;
import com.varanegar.vaslibrary.manager.ProductType;
import com.varanegar.vaslibrary.manager.UserManager;
import com.varanegar.vaslibrary.model.productGroup.ProductGroupModel;
import com.varanegar.vaslibrary.ui.report.report_new.customerNoSaleReport.model.CustomerNoSaleModel;
import com.varanegar.vaslibrary.ui.report.report_new.webApi.ReportApi;
import com.varanegar.vaslibrary.ui.report.review.BaseReviewReportFragment;
import com.varanegar.vaslibrary.util.multispinnerfilter.KeyPairBoolData;
import com.varanegar.vaslibrary.util.multispinnerfilter.MultiSpinnerListener;
import com.varanegar.vaslibrary.util.multispinnerfilter.MultiSpinnerSearch;
import com.varanegar.vaslibrary.webapi.WebApiErrorBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import okhttp3.Request;
import retrofit2.Call;

public abstract class BaseCustomerNoSaleReportFragment<T extends CustomerNoSaleModel> extends VaranegarFragment {

    private ReportView reportView;
    private ProgressDialog progressDialog;
    private Button buttonReport;
    private SimpleToolbar toolbar;
    protected BaseReviewReportFragment.OnAdapterCallback onAdapterCallback;
    private MultiSpinnerSearch multiSelectSpinnerWithSearch;
    private MainVaranegarActivity activity;
    private final List<String> product_group = new ArrayList<>();
    private SimpleReportAdapter<T> adapter;
    private List<T> resultReport;
    private Date startDate;
    private Date endDate;
    private PairedItems startDatePairedItems;
    private PairedItems endDatePairedItems;
    private ImageView imageViewStartCalender;
    private ImageView imageViewEndCalender;

    protected abstract SimpleReportAdapter<T> createAdapter();

    protected abstract String getTitle();

    protected abstract String isEnabled();

    protected abstract Call<List<T>> reportApi();


    //---------------------------------------------------------------------------------------------- onCreateView
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_customer_no_sale_report, container, false);
    }
    //---------------------------------------------------------------------------------------------- onCreateView


    //---------------------------------------------------------------------------------------------- onViewCreated
    @Override
    public void onViewCreated(
            @NonNull View view,
            @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
            long start = savedInstanceState.getLong("startDate", 0);
            long end = savedInstanceState.getLong("endDate", 0);
            /*
             * مجبور شدم این کار رو بکنم چون مدل اصلی کل پروژه از Parcelable ارث بری نکرده بود
             * */
            ArrayList<Parcelable> objects = savedInstanceState.getParcelableArrayList("report");
            try {
                resultReport = new ArrayList<>();
                for (int i = 0; i < objects.size(); i++) {
                    T item = (T) objects.get(i);
                    resultReport.add(item);
                }
            } catch (Exception ignored) {

            }

            if (start != 0 && end != 0) {
                startDate = new Date(start);
                endDate = new Date(end);
            }

        }

        activity = getVaranegarActvity();
        if (activity == null)
            return;
        initView(view);
        setOnListener();
    }
    //---------------------------------------------------------------------------------------------- onViewCreated


    //---------------------------------------------------------------------------------------------- initView
    private void initView(@NonNull View view) {
        buttonReport = view.findViewById(R.id.buttonReport);
        reportView = view.findViewById(R.id.review_report_view);
        toolbar = view.findViewById(R.id.toolbar);
        startDatePairedItems = view.findViewById(R.id.start_date_item);
        endDatePairedItems = view.findViewById(R.id.end_date_item);
        imageViewStartCalender = view.findViewById(R.id.start_calendar_image_view);
        imageViewEndCalender = view.findViewById(R.id.end_calendar_image_view);
        multiSelectSpinnerWithSearch = view.findViewById(R.id.multipleItemSelectionSpinnerr);
        setUpMultiSelectSpinnerWithSearch(getKeyPairBoolData());
        toolbar.setTitle(getTitle());

        if (startDate != null)
            setDateToDatePairedItems(startDatePairedItems, startDate);

        if (endDate != null)
            setDateToDatePairedItems(endDatePairedItems, endDate);

        if (resultReport != null)
            setReportAdapter();
    }
    //---------------------------------------------------------------------------------------------- initView


    //---------------------------------------------------------------------------------------------- setOnListener
    private void setOnListener() {
        buttonReport.setOnClickListener(view15 -> requestCustomerNoSaleReport());
        toolbar.setOnBackClickListener(view12 -> activity.popFragment());
        toolbar.setOnMenuClickListener(view1 -> activity.openDrawer());
        imageViewStartCalender.setOnClickListener(v -> clickOnStartCalender());
        imageViewEndCalender.setOnClickListener(v -> clickOnEndCalender());
    }
    //---------------------------------------------------------------------------------------------- setOnListener


    //---------------------------------------------------------------------------------------------- clickOnStartCalender
    private void clickOnStartCalender() {
        DateHelper.showDatePicker(activity,
                VasHelperMethods.getSysConfigLocale(getContext()), calendar -> {
                    if (calendar.getTime().after(new Date())) {
                        showErrorDialog(getString(R.string.date_could_not_be_after_now));
                        return;
                    }
                    if (endDate != null && endDate.before(calendar.getTime())) {
                        showErrorDialog(getString(R.string.end_date_could_not_be_before_start_date));
                        return;
                    }
                    startDate = calendar.getTime();
                    setDateToDatePairedItems(startDatePairedItems, startDate);
                });
    }
    //---------------------------------------------------------------------------------------------- clickOnStartCalender


    //---------------------------------------------------------------------------------------------- clickOnEndCalender
    private void clickOnEndCalender() {
        DateHelper.showDatePicker(activity,
                VasHelperMethods.getSysConfigLocale(getContext()), calendar -> {
                    if (calendar.getTime().after(new Date())) {
                        showErrorDialog(getString(R.string.date_could_not_be_after_now));
                        return;
                    }
                    if (startDate != null && startDate.after(calendar.getTime())) {
                        showErrorDialog(getString(R.string.start_date_could_not_be_after_end_date));
                        return;
                    }
                    endDate = calendar.getTime();
                    setDateToDatePairedItems(endDatePairedItems, endDate);
                });
    }
    //---------------------------------------------------------------------------------------------- clickOnEndCalender


    //---------------------------------------------------------------------------------------------- setDateToDatePairedItems
    private void setDateToDatePairedItems(PairedItems pairedItems, Date date) {
        pairedItems.setValue(DateHelper.toString(
                date,
                DateFormat.Date,
                VasHelperMethods.getSysConfigLocale(getContext())));
    }
    //---------------------------------------------------------------------------------------------- setDateToDatePairedItems


    //---------------------------------------------------------------------------------------------- setUpMultiSelectSpinnerWithSearch
    private void setUpMultiSelectSpinnerWithSearch(List<KeyPairBoolData> items) {
        multiSelectSpinnerWithSearch.setSearchEnabled(true);
        multiSelectSpinnerWithSearch.setHintText("لیست گروه کالاها");
        multiSelectSpinnerWithSearch.setClearText("پاک کردن لیست");
        multiSelectSpinnerWithSearch.setSelectAllText("همه کالا ها");
        multiSelectSpinnerWithSearch.setSearchHint("جستجو");
        multiSelectSpinnerWithSearch.setEmptyTitle("Not Data Found!");
        multiSelectSpinnerWithSearch.setShowSelectAllButton(true);
        multiSelectSpinnerWithSearch.setItems(items, new MultiSpinnerListener() {
            @Override
            public void onItemsSelected(List<KeyPairBoolData> selectedItems) {
                for (int i = 0; i < selectedItems.size(); i++) {
                    product_group.add(String.valueOf(selectedItems.get(i).getId()));
                }
            }

            @Override
            public void onClear() {
                product_group.clear();
            }
        });
    }
    //---------------------------------------------------------------------------------------------- setUpMultiSelectSpinnerWithSearch


    //---------------------------------------------------------------------------------------------- getKeyPairBoolData
    private List<KeyPairBoolData> getKeyPairBoolData() {
        ProductGroupManager ProductGroupManager = new ProductGroupManager(requireContext());
        List<ProductGroupModel> catalogModels = ProductGroupManager.getParentItems(ProductType.isForReturn);
        List<KeyPairBoolData> items = new ArrayList<>();
        for (int i = 0; i < catalogModels.size(); i++) {
            //list.add(catalogModels.get(i).ProductGroupName);
            product_group.clear();
            KeyPairBoolData h = new KeyPairBoolData();
            h.setId(catalogModels.get(i).UniqueId);
            h.setName(catalogModels.get(i).ProductGroupName);
            h.setSelected(false);
            items.add(h);
        }
        return items;
    }
    //---------------------------------------------------------------------------------------------- getKeyPairBoolData


    //---------------------------------------------------------------------------------------------- requestCustomerNoSaleReport
    private void requestCustomerNoSaleReport() {
        String error = isEnabled();
        if (error != null) {
            showErrorDialog(error);
            return;
        }

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle(R.string.please_wait);
        progressDialog.setMessage(getString(R.string.downloading_data));
        progressDialog.show();
        ReportApi invoiceReportApi = new ReportApi(getContext());
        invoiceReportApi.runWebRequest(reportApi(), new WebCallBack<List<T>>() {
            @Override
            protected void onFinish() {
                dismissProgressDialog();
            }

            @Override
            protected void onSuccess(List<T> result, Request request) {
                MainVaranegarActivity activity = getVaranegarActvity();
                if (activity != null && !activity.isFinishing() && isResumed()) {
                    resultReport = result;
                    setReportAdapter();
                }
            }

            @Override
            protected void onApiFailure(ApiError error, Request request) {
                String err = WebApiErrorBody.log(error, getContext());
                MainVaranegarActivity activity = getVaranegarActvity();
                if (activity != null && !activity.isFinishing() && isResumed()) {
                    showErrorDialog(err);
                    if (onAdapterCallback != null)
                        onAdapterCallback.onFailure();
                }
            }

            @Override
            protected void onNetworkFailure(Throwable t, Request request) {
                MainVaranegarActivity activity = getVaranegarActvity();
                if (activity != null && !activity.isFinishing() && isResumed()) {
                    showErrorDialog(getString(R.string.connection_to_server_failed));
                    if (onAdapterCallback != null)
                        onAdapterCallback.onFailure();
                }
            }
        });
    }
    //---------------------------------------------------------------------------------------------- requestCustomerNoSaleReport


    //---------------------------------------------------------------------------------------------- setReportAdapter
    private void setReportAdapter() {
        adapter = createAdapter();
        adapter.setLocale(VasHelperMethods.getSysConfigLocale(getContext()));
        adapter.create(resultReport, null);
        reportView.setAdapter(adapter);
        if (onAdapterCallback != null)
            onAdapterCallback.onSuccess();
    }
    //---------------------------------------------------------------------------------------------- setReportAdapter


    //---------------------------------------------------------------------------------------------- dismissProgressDialog
    private void dismissProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            try {
                progressDialog.dismiss();
            } catch (Exception ignored) {

            }
        }
    }
    //---------------------------------------------------------------------------------------------- dismissProgressDialog


    //---------------------------------------------------------------------------------------------- getDealerId
    protected UUID getDealerId() {
        return Objects.requireNonNull(UserManager.readFromFile(getContext())).UniqueId;
    }
    //---------------------------------------------------------------------------------------------- getDealerId


    //---------------------------------------------------------------------------------------------- getStartDateString
    protected String getStartDateString() {
        return DateHelper.toString(getStartDate(), DateFormat.Date,
                VasHelperMethods.getSysConfigLocale(getContext()));
    }
    //---------------------------------------------------------------------------------------------- getStartDateString


    //---------------------------------------------------------------------------------------------- getEndDateString
    protected String getEndDateString() {
        return DateHelper.toString(getEndDate(), DateFormat.Date,
                VasHelperMethods.getSysConfigLocale(getContext()));
    }
    //---------------------------------------------------------------------------------------------- getEndDateString


    //---------------------------------------------------------------------------------------------- getStartDate
    protected Date getStartDate() {
        return startDate == null ? DateHelper.Min : startDate;
    }
    //---------------------------------------------------------------------------------------------- getStartDate


    //---------------------------------------------------------------------------------------------- getEndDate
    protected Date getEndDate() {
        return endDate == null ? new Date() : endDate;
    }
    //---------------------------------------------------------------------------------------------- getEndDate


    //---------------------------------------------------------------------------------------------- getProduct_group
    protected List<String> getProduct_group() {
        return product_group;
    }
    //---------------------------------------------------------------------------------------------- getProduct_group



    //---------------------------------------------------------------------------------------------- getAdapter
    public SimpleReportAdapter<T> getAdapter() {
        return adapter;
    }
    //---------------------------------------------------------------------------------------------- getAdapter


    //---------------------------------------------------------------------------------------------- showErrorDialog
    private void showErrorDialog(String error) {
        if (isResumed()) {
            CuteMessageDialog dialog = new CuteMessageDialog(requireContext());
            dialog.setTitle(R.string.error);
            dialog.setMessage(error);
            dialog.setIcon(Icon.Error);
            dialog.setPositiveButton(R.string.ok, null);
            dialog.show();
        }
    }
    //---------------------------------------------------------------------------------------------- showErrorDialog


    //---------------------------------------------------------------------------------------------- onSaveInstanceState
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if (startDate != null)
            outState.putLong("startDate", startDate.getTime());
        if (endDate != null)
            outState.putLong("endDate", endDate.getTime());
        outState.putParcelableArrayList("report", (ArrayList<? extends Parcelable>) resultReport);
    }
    //---------------------------------------------------------------------------------------------- onSaveInstanceState


}