package com.varanegar.vaslibrary.model.productMainSubType;

import com.varanegar.framework.database.model.BaseModel;
import com.varanegar.processor.annotations.Column;
import com.varanegar.processor.annotations.Table;

import com.varanegar.framework.validation.annotations.NotNull;

/**
 * Created by s.foroughi on 09/01/2017.
 */
@Table
public class ProductMainSubTypeModel extends BaseModel {

    @Column
    public int GoodsRef;
    @Column
    public int MainTypeRef;
    @Column
    public int SubTypeRef;
    @Column
    public int BackOfficeId;

}