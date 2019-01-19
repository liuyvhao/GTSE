package com.witfrog.gtse.ui.home;

import com.witfrog.gtse.base.IView;
import com.witfrog.gtse.model.Product;
import com.witfrog.gtse.model.Information;
import com.witfrog.gtse.model.InformationData;
import com.witfrog.gtse.model.MyIntegral;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.model.WalletData;

public interface HomeView extends IView {
    void getMallData(PageData<Product> pageData);

    void getInformationData(InformationData informationData);

    void getInformationList(PageData<Information> pageData);

    void getWalletData(WalletData walletData);

    void getMyIntegral(MyIntegral myIntegral);
}
