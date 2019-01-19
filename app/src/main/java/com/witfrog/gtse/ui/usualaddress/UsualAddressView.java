package com.witfrog.gtse.ui.usualaddress;

import com.witfrog.gtse.base.IView;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.model.UsualAddress;

public interface UsualAddressView extends IView {
    void getUsualAddressList(PageData<UsualAddress> pageData);
}
