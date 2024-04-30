package com.varanegar.vaslibrary.ui.dialog;

import android.os.Bundle;

import androidx.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.varanegar.framework.util.component.CuteAlertDialog;
import com.varanegar.framework.util.component.cutemessagedialog.CuteMessageDialog;
import com.varanegar.framework.util.component.cutemessagedialog.Icon;
import com.varanegar.framework.util.recycler.BaseRecyclerView;
import com.varanegar.framework.util.recycler.selectionlistadapter.SelectionRecyclerAdapter;
import com.varanegar.vaslibrary.R;
import com.varanegar.vaslibrary.manager.NoSaleReasonManager;
import com.varanegar.vaslibrary.manager.customercallmanager.CustomerCallManager;
import com.varanegar.vaslibrary.model.noSaleReason.NoSaleReasonModel;

import java.util.List;
import java.util.UUID;

import timber.log.Timber;

/**
 * Created by s.foroughi on 11/03/2017.
 * این دیالوگ لیست دلایل عدم ویزیت را به کاربر نمایش می دهد
 */
public class NonVisitActionDialog extends CuteAlertDialog {
    UUID customerUinqueId;
    SelectionRecyclerAdapter<NoSaleReasonModel> selectionAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            customerUinqueId = UUID.fromString(bundle.getString("CustomerUniqueId"));
        }
    }

    @Override
    protected void onCreateContentView(LayoutInflater inflater, ViewGroup viewGroup, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_non_visit, viewGroup, true);
        setTitle(R.string.please_select_a_reason_of_no_visit);
        BaseRecyclerView nonVisitList = (BaseRecyclerView) view.findViewById(R.id.non_visit_list_view);
        NoSaleReasonManager noSaleReasonManager = new NoSaleReasonManager(getActivity());
        List<NoSaleReasonModel> noSalesReasons = noSaleReasonManager.getNoneVisitReasons();
        selectionAdapter = new SelectionRecyclerAdapter<>(getVaranegarActvity(), noSalesReasons, false);
        nonVisitList.setAdapter(selectionAdapter);
    }

    @Override
    public void ok() {
        NoSaleReasonModel noSaleReasonModel = selectionAdapter.getSelectedItem();
        if (noSaleReasonModel == null || noSaleReasonModel.UniqueId == null) {
            Toast.makeText(getContext(), R.string.please_select_a_reason_of_no_visit, Toast.LENGTH_SHORT).show();
        } else {
            if (getDialog() != null) {
                getDialog().dismiss();
            }

            if (getContext() != null) {
                CustomerCallManager callManager = new CustomerCallManager(getContext());
                try {
                    callManager.saveLackOfVisit(customerUinqueId, noSaleReasonModel.UniqueId);
                    Timber.i("none visit saved: ");
                    if (onVisitStatusChanged != null)
                        onVisitStatusChanged.onChanged();
                } catch (Exception e) {
                    CuteMessageDialog dialog = new CuteMessageDialog(getContext());
                    dialog.setTitle(R.string.error);
                    dialog.setMessage(R.string.error_saving_request);
                    dialog.setIcon(Icon.Error);
                    dialog.setPositiveButton(R.string.ok, null);
                    dialog.show();
                }
            }
        }
    }

    @Override
    public void cancel() {

    }

    public OnVisitStatusChanged onVisitStatusChanged;

    public interface OnVisitStatusChanged {
        void onChanged();
    }
}