package com.varanegar.vaslibrary.model.sendAnswersQustion;

import java.util.ArrayList;
import java.util.UUID;

public class SyncCustomerCallQuestionnaire {

    public UUID questionnaireUniqueId;

    public ArrayList<SyncGetCustomerQuestionnaireAnswerModel> answers= new ArrayList<>();
}
