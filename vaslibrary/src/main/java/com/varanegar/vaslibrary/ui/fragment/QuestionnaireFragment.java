package com.varanegar.vaslibrary.ui.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.varanegar.framework.base.MainVaranegarActivity;
import com.varanegar.framework.base.VaranegarApplication;
import com.varanegar.framework.database.DbException;
import com.varanegar.framework.util.Linq;
import com.varanegar.framework.util.component.SimpleToolbar;
import com.varanegar.framework.util.component.cutemessagedialog.CuteMessageDialog;
import com.varanegar.framework.util.component.cutemessagedialog.Icon;
import com.varanegar.framework.util.recycler.BaseRecyclerAdapter;
import com.varanegar.framework.util.recycler.BaseRecyclerView;
import com.varanegar.framework.util.recycler.BaseViewHolder;
import com.varanegar.vaslibrary.R;
import com.varanegar.vaslibrary.manager.customercallmanager.CustomerCallManager;
import com.varanegar.vaslibrary.manager.questionnaire.QuestionnaireAnswerManager;
import com.varanegar.vaslibrary.manager.questionnaire.QuestionnaireCustomerManager;
import com.varanegar.vaslibrary.manager.questionnaire.QuestionnaireCustomerViewManager;
import com.varanegar.vaslibrary.manager.questionnaire.QuestionnaireLineManager;
import com.varanegar.vaslibrary.model.customercall.CustomerCallType;
import com.varanegar.vaslibrary.model.questionnaire.QuestionnaireCustomerModel;
import com.varanegar.vaslibrary.model.questionnaire.QuestionnaireCustomerViewModel;
import com.varanegar.vaslibrary.model.questionnaire.QuestionnaireDemandType;
import com.varanegar.vaslibrary.model.questionnaire.QuestionnaireLineModel;

import java.util.List;
import java.util.UUID;

import timber.log.Timber;

/**
 * Created by A.Torabi on 7/3/2017.
 */

public class QuestionnaireFragment extends VisitFragment {

    private UUID customerId;

    public void setCustomerId(UUID customerId) {
        addArgument("f8c2abc4-c401-4f16-8f7d-1019e80574af", customerId.toString());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_quetionaire, container, false);
        SimpleToolbar toolbar = (SimpleToolbar) view.findViewById(R.id.toolbar);
        toolbar.setOnBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVaranegarActvity().popFragment();
            }
        });
        BaseRecyclerView formsRecyclerView = (BaseRecyclerView) view.findViewById(R.id.forms_recycler_view);
        customerId = UUID.fromString(getArguments().getString("f8c2abc4-c401-4f16-8f7d-1019e80574af"));
        final CustomerCallManager callManager = new CustomerCallManager(getContext());
        Button sendButton = view.findViewById(R.id.send_questionnaire_btn);
        try {
            if (VaranegarApplication.is(VaranegarApplication.AppId.Supervisor)) {
                sendButton.setVisibility(View.VISIBLE);
                sendButton.setOnClickListener(view1 -> {

                });
            } else {
                sendButton.setVisibility(View.GONE);
                callManager.unConfirmAllCalls(customerId);
                callManager.removeCall(CustomerCallType.LackOfVisit, customerId);
            }
            QuestionnaireCustomerViewManager manager = new QuestionnaireCustomerViewManager(getContext());
            List<QuestionnaireCustomerViewModel> questionnaires = manager.getQuestionnaires(customerId);
            final BaseRecyclerAdapter<QuestionnaireCustomerViewModel> adapter = new BaseRecyclerAdapter<QuestionnaireCustomerViewModel>(getVaranegarActvity(), questionnaires) {
                @Override
                public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_questionaire_summary, parent, false);
                    return new QuestionnaireViewHolder(view, this, getContext());
                }
            };
            formsRecyclerView.setAdapter(adapter);
            return view;
        } catch (Exception ex) {
            Timber.e(ex);
            return null;
        }

    }

    @NonNull
    @Override
    protected UUID getCustomerId() {
        return customerId;
    }

    private class QuestionnaireViewHolder extends BaseViewHolder<QuestionnaireCustomerViewModel> {

        private final TextView title;
        private final ImageView doneImageView;
        private final ImageView cancelImageView;
        private final ImageView forceImageView;

        public QuestionnaireViewHolder(View itemView, BaseRecyclerAdapter<QuestionnaireCustomerViewModel> recyclerAdapter, Context context) {
            super(itemView, recyclerAdapter, context);
            title = (TextView) itemView.findViewById(R.id.questionnaire_title_text_view);
            doneImageView = (ImageView) itemView.findViewById(R.id.done_image_view);
            cancelImageView = (ImageView) itemView.findViewById(R.id.cancel_image_view);
            forceImageView = (ImageView) itemView.findViewById(R.id.force_image_view);

        }

        @Override
        public void bindView(final int position) {
            final QuestionnaireCustomerViewModel q = recyclerAdapter.get(position);
            title.setText(q.Title);
            if (q.HasAnswer)
                doneImageView.setImageResource(R.drawable.ic_done_green_900_24dp);
            else
                doneImageView.setImageResource(R.drawable.ic_done_blue_grey_300_24dp);
            if (q.DemandType == QuestionnaireDemandType.Mandatory)
                forceImageView.setVisibility(View.VISIBLE);
            else
                forceImageView.setVisibility(View.INVISIBLE);
            if (q.NoAnswerReason == null)
                cancelImageView.setImageResource(R.drawable.ic_clear_blue_grey_300_24dp);
            else
                cancelImageView.setImageResource(R.drawable.ic_clear_red_900_24dp);
            doneImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QuestionnaireCustomerManager questionnaireCustomerManager = new QuestionnaireCustomerManager(getContext());
                    QuestionnaireCustomerModel item = questionnaireCustomerManager.getCustomerQuestionnaire(q.CustomerId, q.QuestionnaireId);
                    item.NoAnswerReason = null;
                    try {
                        questionnaireCustomerManager.insertOrUpdate(item);

                        List<QuestionnaireLineModel> lines = new QuestionnaireLineManager(getContext()).getLines(item.QuestionnaireId);
                        QuestionnaireLineModel epoll = Linq.findFirst(lines, new Linq.Criteria<QuestionnaireLineModel>() {
                            @Override
                            public boolean run(QuestionnaireLineModel item) {
                                return item.QuestionnaireLineTypeUniqueId.equals(QuestionnaireLineManager.QuestionnaireLineTypeUniqueId.EPoll);
                            }
                        });
                        if (epoll == null) {
                            QuestionnaireFormFragment fragment = new QuestionnaireFormFragment();
                            fragment.setArguments(q.CustomerId, q.QuestionnaireId);
                            getVaranegarActvity().pushFragment(fragment);
                        } else {
                            if (VaranegarApplication.is(VaranegarApplication.AppId.Supervisor)) {
                                CuteMessageDialog dialog = new CuteMessageDialog(getContext());
                                dialog.setIcon(Icon.Info);
                                dialog.setMessage(R.string.questionnaire_not_supported_in_supervisor);
                                dialog.setPositiveButton(R.string.ok, null);
                                dialog.show();
                            } else {
                                EPollFragment fragment = new EPollFragment();
                                fragment.setArguments(q.CustomerId, q.QuestionnaireId, epoll.UniqueId);
                                getVaranegarActvity().pushFragment(fragment);
                            }
                        }
                    } catch (Exception ex) {
                        Timber.e(ex);
                        getVaranegarActvity().showSnackBar(R.string.error_saving_request, MainVaranegarActivity.Duration.Short);
                    }

                }
            });
            cancelImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    QuestionnaireNoAnswerDialog dialog = new QuestionnaireNoAnswerDialog();
                    dialog.onReasonSelected = new QuestionnaireNoAnswerDialog.OnReasonSelected() {
                        @Override
                        public void onDone() {
                            QuestionnaireAnswerManager questionnaireAnswerManager = new QuestionnaireAnswerManager(getContext());
                            try {
                                questionnaireAnswerManager.deleteAnswers(q.CustomerId, q.QuestionnaireId);
                                QuestionnaireCustomerViewManager manager = new QuestionnaireCustomerViewManager(getContext());
                                QuestionnaireCustomerViewModel updatedModel = manager.getCustomerQuestionnaire(q.CustomerId, q.QuestionnaireId);
                                q.NoAnswerReason = updatedModel.NoAnswerReason;
                                q.HasAnswer = updatedModel.HasAnswer;
                                recyclerAdapter.notifyDataSetChanged();
                                if (!VaranegarApplication.is(VaranegarApplication.AppId.Supervisor)) {
                                    List<QuestionnaireCustomerViewModel> models = manager.getQuestionnaires(q.CustomerId, false);
                                    if (models.size() == 0) {
                                        saveCall(q.CustomerId);
                                    } else {
                                        boolean exist = Linq.exists(models, new Linq.Criteria<QuestionnaireCustomerViewModel>() {
                                            @Override
                                            public boolean run(QuestionnaireCustomerViewModel item) {
                                                return item.DemandType == QuestionnaireDemandType.Mandatory && item.NoAnswerReason == null;
                                            }
                                        });
                                        if (exist) {
                                            removeCall(q.CustomerId);
                                        } else {
                                            saveCall(q.CustomerId);
                                        }
                                    }
                                }
                            } catch (Exception ex) {
                                Timber.e(ex);
                            }
                        }
                    };
                    dialog.setArguments(q.CustomerId, q.QuestionnaireId);
                    dialog.show(getChildFragmentManager(), "4de9d98d-09c5-4a1a-b8d3-963e67ae9ccc");
                }
            });
        }
    }

    private void removeCall(UUID customerId) {
        CustomerCallManager callManager = new CustomerCallManager(getContext());
        try {
            callManager.removeQuestionnaireCall(customerId);
        } catch (DbException e) {
            Timber.e(e);
        }
    }

    private void saveCall(UUID customerId) {
        CustomerCallManager callManager = new CustomerCallManager(getContext());
        try {
            callManager.saveQuestionnaireCall(customerId);
        } catch (Exception e) {
            Timber.e(e);
        }
    }
}