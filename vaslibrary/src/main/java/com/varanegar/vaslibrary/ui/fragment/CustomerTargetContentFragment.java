package com.varanegar.vaslibrary.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.varanegar.framework.base.MainVaranegarActivity;
import com.varanegar.framework.base.VaranegarFragment;
import com.varanegar.framework.util.recycler.ContextMenuItem;
import com.varanegar.framework.util.report.CustomViewHolder;
import com.varanegar.framework.util.report.ReportAdapter;
import com.varanegar.framework.util.report.ReportColumns;
import com.varanegar.framework.util.report.ReportView;
import com.varanegar.framework.util.report.SimpleReportAdapter;
import com.varanegar.vaslibrary.R;
import com.varanegar.vaslibrary.base.VasHelperMethods;
import com.varanegar.vaslibrary.manager.Target.TargetMasterManager;
import com.varanegar.vaslibrary.manager.UserManager;
import com.varanegar.vaslibrary.model.target.TargetMaster;
import com.varanegar.vaslibrary.model.target.TargetMasterModel;
import com.varanegar.vaslibrary.model.target.TargetMasterModelRepository;
import com.varanegar.vaslibrary.model.targetbase.TargetBase;
import com.varanegar.vaslibrary.model.targettype.TargetType;
import com.varanegar.vaslibrary.ui.report.target.TargetReportDetailFragment;

import java.util.UUID;

/**
 * Created by A.Torabi on 4/11/2018.
 */

public abstract class CustomerTargetContentFragment extends VaranegarFragment {
    private ReportAdapter<TargetMasterModel> adapter;

    protected abstract VaranegarFragment getContentFragment();

    public UUID getSelectedId() {
        try {
            return UUID.fromString(getArguments().getString("a129ef75-77ce-432a-8982-6bcab0bf7b51"));
        } catch (Exception ex) {
            return null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_target_header_report_customer, viewGroup, false);

        final ReportView targetHeaderReport = (ReportView) view.findViewById(R.id.target_header_report);
        view.findViewById(R.id.content_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                changeContent(getContentFragment());
            }
        });

        adapter = new SimpleReportAdapter<TargetMasterModel>((MainVaranegarActivity) getActivity(), TargetMasterModel.class) {
            @Override
            public void bind(ReportColumns columns, final TargetMasterModel entity) {
                bindRowNumber(columns);
                columns.add(bind(entity, TargetMaster.Title, getString(R.string.tareget_title)).setSortable());
                columns.add(bind(entity, TargetMaster.FromPDate, getString(R.string.from_date)).setSortable());
                columns.add(bind(entity, TargetMaster.ToPDate, getString(R.string.to_date)).setSortable());
                columns.add(bind(entity, TargetMaster.TargetBaseUniqueId, getString(R.string.target_with_base)).setSortable().setCustomViewHolder(new CustomViewHolder<TargetMasterModel>() {
                    @Override
                    public void onBind(View view, TargetMasterModel entity) {
                        TextView textView = (TextView) view.findViewById(R.id.target_base);
                        textView.setText(TargetBase.getName(getContext(), entity.TargetBaseUniqueId));
                    }

                    @Override
                    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
                        return inflater.inflate(R.layout.fragment_base_target, parent, false);
                    }
                }));
                columns.add(bind(entity, TargetMaster.TargetTypeUniqueId, getString(R.string.target_type)).setSortable().setCustomViewHolder(new CustomViewHolder<TargetMasterModel>() {
                    @Override
                    public void onBind(View view, TargetMasterModel entity) {
                        TextView textView = (TextView) view.findViewById(R.id.target_base);
                        textView.setText(TargetType.getName(getContext(), entity.TargetTypeUniqueId));
                    }

                    @Override
                    public View onCreateView(LayoutInflater inflater, ViewGroup parent) {
                        return inflater.inflate(R.layout.fragment_base_target, parent, false);
                    }
                }));
            }
        };
        adapter.create(new TargetMasterModelRepository(), TargetMasterManager.getFilterTargets(UserManager.readFromFile(getContext()).UniqueId, getSelectedId()), savedInstanceState);
        adapter.setLocale(VasHelperMethods.getSysConfigLocale(getContext()));
        targetHeaderReport.setAdapter(adapter);
        adapter.addOnItemClickListener(new ContextMenuItem() {
            @Override
            public boolean isAvailable(int position) {
                return true;
            }

            @Override
            public String getName(int posiotn) {
                return getContext().getString(R.string.saleman_general);
            }

            @Override
            public int getIcon(int position) {
                return R.drawable.ic_report_24dp;
            }

            @Override
            public void run(int position) {
                TargetMasterModel targetMasterModel = adapter.get(position);
                String targetReportText = targetMasterModel.Title + "  " + targetMasterModel.FromPDate + " - " + targetMasterModel.ToPDate + "  " + TargetBase.getName(getContext(), targetMasterModel.TargetBaseUniqueId) + " - " + TargetType.getName(getContext(), targetMasterModel.TargetTypeUniqueId);
                TargetReportDetailFragment targetReportDetail = new TargetReportDetailFragment();
                targetReportDetail.setTarget(targetMasterModel.UniqueId, targetReportText, targetMasterModel.TargetBaseUniqueId, targetMasterModel.TargetTypeUniqueId, getSelectedId().toString());
                getVaranegarActvity().pushFragment(targetReportDetail);
            }
        });
        return view;
    }
}