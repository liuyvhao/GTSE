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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;

/**
 * <pre>
 *     author: Zhongnan.Zhang
 *     e-mail: 2315136814@qq.com
 *     time  : 2018/7/3
 *     desc  : HttpManager
 * </pre>
 */
public class HttpManager {

    private ApiService mApiService;

    public HttpManager() {
        this.mApiService = HttpHelper.getInstance().getServer();
    }

    public Observable<HttpResult<Object>> getCode(String phone) {
        return mApiService.getCode(
                getRequestBody(phone)
        );
    }

    public Observable<HttpResult<Object>> register(String phone, String code, String password, String recode) {
        return mApiService.register(getRequestBody(phone),
                getRequestBody(code),
                getRequestBody(password),
                getRequestBody(recode));
    }

    public Observable<HttpResult<User>> login(String phone, String password, String code) {
        return mApiService.login(getRequestBody(phone),
                getRequestBody(password),
                getRequestBody(code));
    }

    public Observable<HttpResult<Object>> retrievePwd(String phone, String code, String password) {
        return mApiService.modifyPassword(getRequestBody(phone),
                getRequestBody(code),
                getRequestBody(password));
    }

    public Observable<HttpResult<Version>> versionUpdate() {
        return mApiService.versionUpdate();
    }

    public Observable<HttpResult<PageData<Product>>> getMallData(int page) {
        return mApiService.getMallData(getRequestBody(String.valueOf(page)));
    }

    public Observable<HttpResult<InformationData>> getInformationData() {
        return mApiService.getInformationData();
    }

    public Observable<HttpResult<PageData<Information>>> getInformationList(String keyword, int page) {
        return mApiService.getInformationList(getRequestBody(keyword),
                getRequestBody(String.valueOf(page)));
    }

    public Observable<HttpResult<WalletData>> getWalletData(String id) {
        return mApiService.getWalletData(getRequestBody(id));
    }

    public Observable<HttpResult<MyIntegral>> getMyIntegral(String id) {
        return mApiService.getMyIntegral(getRequestBody(id));
    }

    public Observable<HttpResult<PageData<Message>>> getMessageList(String id, int page) {
        return mApiService.getMessageList(getRequestBody(id),
                getRequestBody(String.valueOf(page)));
    }

    public Observable<HttpResult<PageData<UsualAddress>>> getUsualAddressList(String id, int page) {
        return mApiService.getUsualAddressList(getRequestBody(id),
                getRequestBody(String.valueOf(page)));
    }

    public Observable<HttpResult<Object>> addUsualAddress(String id, String recipient, String phone, String area, String address, String code) {
        return mApiService.addUsualAddress(getRequestBody(id),
                getRequestBody(recipient),
                getRequestBody(phone),
                getRequestBody(area),
                getRequestBody(address),
                getRequestBody(code));
    }

    public Observable<HttpResult<Object>> modifyUsualAddress(String id, String recipient, String phone, String area, String address, String code) {
        return mApiService.modifyUsualAddress(getRequestBody(id),
                getRequestBody(recipient),
                getRequestBody(phone),
                getRequestBody(area),
                getRequestBody(address),
                getRequestBody(code));
    }

    public Observable<HttpResult<PageData<UsualContacts>>> getUsualContactsList(String id, int page) {
        return mApiService.getUsualContactsList(getRequestBody(id),
                getRequestBody(String.valueOf(page)));
    }

    public Observable<HttpResult<Object>> addUsualContacts(String id, String contacts, String certificateType, String idNumber, String phone, String email, String crowd) {
        return mApiService.addUsualContacts(getRequestBody(id),
                getRequestBody(contacts),
                getRequestBody(certificateType),
                getRequestBody(idNumber),
                getRequestBody(phone),
                getRequestBody(email),
                getRequestBody(crowd));
    }

    public Observable<HttpResult<Object>> modifyUsualContacts(String id, String contacts, String certificateType, String idNumber, String phone, String email, String crowd) {
        return mApiService.modifyUsualContacts(getRequestBody(id),
                getRequestBody(contacts),
                getRequestBody(certificateType),
                getRequestBody(idNumber),
                getRequestBody(phone),
                getRequestBody(email),
                getRequestBody(crowd));
    }

    public Observable<HttpResult<PageData<Order>>> getOrderList(String id, String status, int page) {
        return mApiService.getOrderList(getRequestBody(id),
                getRequestBody(status),
                getRequestBody(String.valueOf(page)));
    }

    public Observable<HttpResult<PageData<Address>>> getAddressList(String id, String keyword, int page) {
        return mApiService.getAddressList(getRequestBody(id),
                getRequestBody(keyword),
                getRequestBody(String.valueOf(page)));
    }

    public Observable<HttpResult<ArrayList<String>>> uploadFlies(MultipartBody multipartBody) {
        return mApiService.uploadFiles(multipartBody);
    }

    private RequestBody getRequestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }
}
