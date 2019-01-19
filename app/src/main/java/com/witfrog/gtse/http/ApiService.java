package com.witfrog.gtse.http;

import com.witfrog.gtse.model.Address;
import com.witfrog.gtse.model.HttpResult;
import com.witfrog.gtse.model.Information;
import com.witfrog.gtse.model.InformationData;
import com.witfrog.gtse.model.Message;
import com.witfrog.gtse.model.MyIntegral;
import com.witfrog.gtse.model.Order;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.model.Product;
import com.witfrog.gtse.model.User;
import com.witfrog.gtse.model.UsualAddress;
import com.witfrog.gtse.model.UsualContacts;
import com.witfrog.gtse.model.Version;
import com.witfrog.gtse.model.WalletData;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : ApiService
 * </pre>
 */
public interface ApiService {

    @Multipart
    @POST("index.php")
    Observable<HttpResult<Object>> getCode(
            @Part("phone") RequestBody phone);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<Object>> register(
            @Part("phone") RequestBody phone,
            @Part("code") RequestBody code,
            @Part("password") RequestBody password,
            @Part("recode") RequestBody recode);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<User>> login(
            @Part("phone") RequestBody phone,
            @Part("password") RequestBody password,
            @Part("code") RequestBody code);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<Object>> modifyPassword(
            @Part("phone") RequestBody phone,
            @Part("code") RequestBody code,
            @Part("password") RequestBody password);

    @POST("index.php")
    Observable<HttpResult<Version>> versionUpdate();

    @Multipart
    @POST("index.php")
    Observable<HttpResult<PageData<Product>>> getMallData(
            @Part("page") RequestBody page);

    @POST("index.php")
    Observable<HttpResult<InformationData>> getInformationData();

    @Multipart
    @POST("index.php")
    Observable<HttpResult<PageData<Information>>> getInformationList(
            @Part("keyword") RequestBody keyword,
            @Part("page") RequestBody page);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<WalletData>> getWalletData(
            @Part("id") RequestBody id);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<MyIntegral>> getMyIntegral(
            @Part("id") RequestBody id);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<PageData<Message>>> getMessageList(
            @Part("id") RequestBody id,
            @Part("page") RequestBody page);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<PageData<UsualAddress>>> getUsualAddressList(
            @Part("id") RequestBody id,
            @Part("page") RequestBody page);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<Object>> addUsualAddress(
            @Part("id") RequestBody id,
            @Part("recipient") RequestBody recipient,
            @Part("phone") RequestBody phone,
            @Part("area") RequestBody area,
            @Part("address") RequestBody address,
            @Part("code") RequestBody code);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<Object>> modifyUsualAddress(
            @Part("id") RequestBody id,
            @Part("recipient") RequestBody recipient,
            @Part("phone") RequestBody phone,
            @Part("area") RequestBody area,
            @Part("address") RequestBody address,
            @Part("code") RequestBody code);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<PageData<UsualContacts>>> getUsualContactsList(
            @Part("id") RequestBody id,
            @Part("page") RequestBody page);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<Object>> addUsualContacts(
            @Part("id") RequestBody id,
            @Part("contacts") RequestBody contacts,
            @Part("certificateType") RequestBody certificateType,
            @Part("idNumber") RequestBody idNumber,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("crowd") RequestBody crowd);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<Object>> modifyUsualContacts(
            @Part("id") RequestBody id,
            @Part("contacts") RequestBody contacts,
            @Part("certificateType") RequestBody certificateType,
            @Part("idNumber") RequestBody idNumber,
            @Part("phone") RequestBody phone,
            @Part("email") RequestBody email,
            @Part("crowd") RequestBody crowd);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<PageData<Order>>> getOrderList(
            @Part("id") RequestBody id,
            @Part("status") RequestBody status,
            @Part("page") RequestBody page);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<PageData<Address>>> getAddressList(
            @Part("id") RequestBody id,
            @Part("keyword") RequestBody keyword,
            @Part("page") RequestBody page);

    @Multipart
    @POST("index.php")
    Observable<HttpResult<ArrayList<String>>> uploadFiles(
            @Body MultipartBody multipartBody);

    @Streaming
    @GET
    Call<ResponseBody> downloadFile(@Url String fileUrl);

}
