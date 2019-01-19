package com.witfrog.gtse.ui.address;

import com.witfrog.gtse.base.IView;
import com.witfrog.gtse.model.Address;
import com.witfrog.gtse.model.PageData;

public interface AddressView extends IView {
    void getAddressList(PageData<Address> pageData);
}
