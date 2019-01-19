package com.witfrog.gtse.ui.order;

import com.witfrog.gtse.base.IView;
import com.witfrog.gtse.model.Order;
import com.witfrog.gtse.model.PageData;

public interface OrderView extends IView {
    void getOrderList(PageData<Order> pageData);
}
