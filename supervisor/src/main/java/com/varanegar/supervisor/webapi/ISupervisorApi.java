package com.varanegar.supervisor.webapi;

import androidx.annotation.Nullable;

import com.varanegar.supervisor.model.ProductModel;
import com.varanegar.vaslibrary.model.customer.SupervisorCustomerModel;
import com.varanegar.supervisor.model.VisitorModel;
import com.varanegar.supervisor.status.OrderSummaryRequestViewModel;
import com.varanegar.supervisor.status.OrderSummaryResultViewModel;
import com.varanegar.vaslibrary.webapi.reviewreport.OrderReviewReportViewModel;
import com.varanegar.vaslibrary.webapi.reviewreport.ProductGroupReviewReportViewModel;
import com.varanegar.vaslibrary.webapi.reviewreport.ProductReviewReportViewModel;
import com.varanegar.vaslibrary.webapi.reviewreport.SellReturnReviewReportViewModel;
import com.varanegar.vaslibrary.webapi.reviewreport.SellReviewReportViewModel;

import java.util.List;
import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by A.Torabi on 6/7/2018.
 */

public interface ISupervisorApi {
    @GET("api/v2/ngt/tour")
    Call<List<TourStatusSummaryViewModel>> tour(@Query("TourViewType") int tourViewType,
                                                @Query("Show_ReadyToSend") boolean Show_ReadyToSend,
                                                @Query("Show_Sent") boolean Show_Sent,
                                                @Query("Show_InProgress") boolean Show_InProgress,
                                                @Query("Show_Received") boolean Show_Received,
                                                @Query("Show_Finished") boolean Show_Finished,
                                                @Query("Show_Canceled") boolean Show_Canceled,
                                                @Query("Show_Deactivated") boolean Show_Deactivated,
                                                @Query("From_Date") String from_date,
                                                @Query("To_Date") String to_date,
                                                @Query("AgentUniqueId") UUID agentUniqueId);

    @GET("api/v2/ngt/Customercall/summary/{TourId}")
    Call<List<TourCustomerSummaryViewModel>> tourCustomers(@Path("TourId") String tourId);


    @GET("api/v2/ngt/Tour/PreSale/DeactivateTour")
    Call<ResponseBody> deactivateTour(@Query("Id") String tourId);

    @GET("api/v2/ngt/Tour/PreSale/Replicate/{Id}")
    Call<ResponseBody> replicateTour(@Path("Id") String tourId);

    @GET("api/v2/ngt/customercall/cancelcall")
    Call<ResponseBody> cancelCustomerCall(@Query("id") String customerCallId);

    @GET("api/v2/ngt/customercall/replicate/{id}")
    Call<ResponseBody> replicateCustomerCall(@Path("id") String customerCallId);

    @PUT("api/v2/ngt/customercall/supervisor/{id}")
    Call<ResponseBody> putCustomerCall(@Path("id") UUID customerCallId, @Query("dealerId") UUID dealerId, @Body CustomerCallViewModel customerCallViewModel);

    @GET("api/v2/ngt/Customercall/{Id}")
    Call<CustomerCallViewModel> customerCalls(@Path("Id") String customerCallUniqueId, @Query("SubsystemTypeId") UUID subsystemTypeId);

    @GET("api/v2/ngt/Customercall")
    Call<List<CustomerCallViewModel>> customerCalls(@Query("Show_Confirmed") boolean showConfirmed, @Query("Show_Unconfirmed") boolean showUnconfirmed, @Query("Show_Canceled") boolean showCanceled, @Query("From_Date") String startDate, @Query("TO_Date") String endDate , @Query("AgentUniqueId") UUID agentUniqueId);

    @POST("api/v2/ngt/tracking/personellastlocationv2")
    Call<List<EventViewModel>> loadLastPoints(@Body LastPointsParam parameter);

    @POST("api/v2/ngt/tracking/personelmarkerv2")
    Call<List<EventViewModel>> loadPersonnelEvents(@Body PersonnelPointsParam parameter);

    @POST("api/v2/ngt/tracking/personelpathv2")
    Call<List<MasterEventViewModel>> loadPersonnelPath(@Body PersonnelPointsParam parameter);


    @GET("api/v2/ngt/Supervisor/{supervisorId}/personels")
    Call<List<VisitorModel>> getVisitors(@Path("supervisorId") String supervisorId);

    @GET("api/v2/ngt/reviewreport/Order")
    Call<List<OrderReviewReportViewModel>> order(@Query("DealerId") String dealerId, @Query("StartDate") String startDate, @Query("EndDate") String endDate);

    @GET("api/v2/ngt/reviewreport/Sell")
    Call<List<SellReviewReportViewModel>> sell(@Query("DealerId") String dealerId, @Query("StartDate") String startDate, @Query("EndDate") String endDate);

    @GET("api/v2/ngt/reviewreport/SellReturn")
    Call<List<SellReturnReviewReportViewModel>> sellReturn(@Query("DealerId") String dealerId, @Query("StartDate") String startDate, @Query("EndDate") String endDate);

    @GET("api/v2/ngt/reviewreport/Product")
    Call<List<ProductReviewReportViewModel>> product(@Query("DealerId") String dealerId, @Query("StartDate") String startDate, @Query("EndDate") String endDate);

    @GET("api/v2/ngt/product/supervisor/batchonhandqty/sync/loaddata")
    Call<OnHandQtyReportViewModel> onHandQty(@Query("supervisorId") UUID supervisorId, @Query("DeviceSettingNo") int deviceSettingNo);

    @GET("api/v2/ngt/reviewreport/ProductGroup")
    Call<List<ProductGroupReviewReportViewModel>> productGroup(@Query("DealerId") String dealerId, @Query("StartDate") String startDate, @Query("EndDate") String endDate);

    @GET("api/v2/ngt/product/SearchProductList")
    Call<List<ProductModel>> getProducts(@Query("searchText") @Nullable String searchText);

    @GET("api/v2/ngt/supervisor/SimpleCustomer")
    Call<List<SupervisorCustomerModel>> getCustomers(@Query("SupervisorId") UUID supervisorId);

    @GET("api/v2/ngt/customer/FinanceData")
    Call<List<CustomerSummaryViewModel>> getCustomerFinanceData(@Query("CustomerId") UUID customerId, @Query("DealerId") UUID dealerId, @Query("SubSystemTypeUniqueId") UUID subSystemTypeUniqueId, @Query("DeviceSettingNo") int deviceSettingNo);

    @POST("api/v2/ngt/evc/supervisor")
    Call<OrderSummaryResultViewModel> getOrderPreview(@Body OrderSummaryRequestViewModel requestViewModel);

    @GET("api/v2/ngt/supervisor/operation")
    Call<List<VisitorVisitInfoViewModel>> getVisitorsVisitInfo(@Query("SupervisorId") UUID supervisorId, @Query("DealerId") UUID dealerId);
}