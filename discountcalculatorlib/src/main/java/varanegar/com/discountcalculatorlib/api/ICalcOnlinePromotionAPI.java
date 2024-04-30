package varanegar.com.discountcalculatorlib.api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import varanegar.com.discountcalculatorlib.viewmodel.ChoicePrizeDiscountOrderPrizeViewModel;
import varanegar.com.discountcalculatorlib.viewmodel.DiscountCallOrderDataOnline;
import varanegar.com.discountcalculatorlib.viewmodel.DiscountOrderPrizeViewModel;
import varanegar.com.discountcalculatorlib.viewmodel.DiscountOutputOnline;
import varanegar.com.discountcalculatorlib.viewmodel.PreSaleEvcHeaderViewModel;

/**
 * Created by A.Razavi on 3/12/2018.
 */

public interface ICalcOnlinePromotionAPI {


    /** محاسبه تخفیف جوایز
     * پیش نمایش ویزیت
     *
     * @param order
     * @param calcDiscount
     * @param calcSaleRestriction
     * @param calcPaymentType
     * @return
     */
    @POST("api/v2/ngt/evc/presale")
    Call<DiscountOutputOnline> getPromotion(@Body DiscountCallOrderDataOnline order,
                                                       @Query("calcDiscount") boolean calcDiscount,
                                                       @Query("calcSaleRestriction") boolean calcSaleRestriction ,

                                                       @Query("calcPaymentType")  boolean calcPaymentType);

    /** پیش نمایش موزع
     * محاسبه تخفیف جوایز
     * @param order
     * @param calcDiscount
     * @param calcSaleRestriction
     * @param calcPaymentType
     * @return
     */
    @POST("api/v2/ngt/evc/distribution")
    Call<DiscountOutputOnline> getDistOnlinePromotion(@Body PreSaleEvcHeaderViewModel order,
                                                      @Query("calcDiscount") boolean calcDiscount,
                                                      @Query("calcSaleRestriction") boolean calcSaleRestriction ,
                                                      @Query("calcPaymentType")  boolean calcPaymentType);


}