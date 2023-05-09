package com.varanegar.vaslibrary.webapi.apiNew;

import com.varanegar.vaslibrary.manager.customergrouplastsalesreportmanager.ReviewReportRequestViewModel;
import com.varanegar.vaslibrary.manager.newmanager.CustomerSumMoneyAndWeightReport.CustomerSumMoneyAndWeightReportModel;
import com.varanegar.vaslibrary.manager.newmanager.customerGroupSimilarProductsalesReport.CustomerGroupSimilarProductsalesReportModel;
import com.varanegar.vaslibrary.manager.newmanager.customerLastBill.CustomerLastBillModel;
import com.varanegar.vaslibrary.manager.newmanager.customerXmounthsalereport.CustomerXMounthSaleReportModel;
import com.varanegar.vaslibrary.manager.newmanager.dealercommission.DealerCommissionDataModel;
import com.varanegar.vaslibrary.model.CheckCustomerCreditsModel;
import com.varanegar.vaslibrary.model.newmodel.checkCustomerCredits.CheckCustomerCreditModel;
import com.varanegar.vaslibrary.model.newmodel.commodity_rationing.CommodityRationingModel;
import com.varanegar.vaslibrary.model.newmodel.customergrouplastsalesreport.CustomerGroupLastSalesReportModel;
import com.varanegar.vaslibrary.model.product.ProductModel;
import com.varanegar.vaslibrary.ui.fragment.news_fragment.model.NewsData_Model;
import com.varanegar.vaslibrary.webapi.apiNew.modelNew.PinRequestViewModel;
import com.varanegar.vaslibrary.webapi.apiNew.modelNew.RoleCodeCustomerRequestViewModel;
import com.varanegar.vaslibrary.webapi.apiNew.modelNew.RoleCodeViewModel;
import com.varanegar.vaslibrary.webapi.apiNew.modelNew.customer_not_allowed_product.CustomerNotAllowProductModel;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InApiNew {

    @POST("api/v2/ngt/customer/pinrequest")
    Call<String> sendPinCode(
            @Body PinRequestViewModel pinRequestViewModel
            );

    @GET("api/v2/ngt/newsletter/sync/loaddata")
    Call<List<NewsData_Model>>getNewsData();


    @POST("api/v2/ngt/customer/getrolecode")
    Call<List<RoleCodeViewModel>>getCodeNaghsh(
            @Body RoleCodeCustomerRequestViewModel roleCodeCustomerViewModel
    );

    @POST("api/v2/ngt/customer/GetCustomerProductBlackList")
    Call<List<CustomerNotAllowProductModel>>getProductNotAllowed(
            @Body List<String> customerId
    );

    @POST("api/v2/ngt/customer/CheckCustomerCredits")
    Call<List<CheckCustomerCreditModel>>CheckCustomerCredits (
            @Body List<String> customerCode);

    @POST("api/v2/ngt/ReviewReport/CustomerGroupLastsalesReport")
    Call<List<CustomerGroupLastSalesReportModel>>CustomerLastSaleReport (
            @Body List<String> customerCode
    );


    @GET("api/v2/ngt/Quotas/sync/loaddata")
    Call<List<CommodityRationingModel>>getCommodityRationin(
            @Query("id") String dealersId
    );

    @GET("api/v2/ngt/commissionData/GetDealerCommissionData")
    Call<DealerCommissionDataModel>getDealerCommissionData(
            @Query("dealersId") List<String> dealersId
    );
    @GET("api/v2/ngt/commissionData/EditDealerCommissionData")
    Call<Void>getEditCommissionData(
            @Query("dealersId") List<String> dealersId
    );


    @POST("api/v2/ngt/ReviewReport/CustomerXMounthSaleReport")
    Call<List<CustomerXMounthSaleReportModel>>CustomerXMounthSaleReport (
            @Body  List<String> customersCode);

    @POST("api/v2/ngt/ReviewReport/CustomerLastBill")
    Call<List<CustomerLastBillModel>>CustomerLastBill (
            @Body  List<String> customersCode);

    @POST("api/v2/ngt/ReviewReport/CustomerGroupSimilarProductsalesReport")
    Call<List<CustomerGroupSimilarProductsalesReportModel>>CustomerGroupSimilarProductsalesReport (
            @Body  List<String> customersCode);


    @POST("api/v2/ngt/ReviewReport/CustomerSumMoneyAndWeightReport")
    Call<List<CustomerSumMoneyAndWeightReportModel>>CustomerSumMoneyAndWeightReport (
            @Body List<String> customersCode);

}
