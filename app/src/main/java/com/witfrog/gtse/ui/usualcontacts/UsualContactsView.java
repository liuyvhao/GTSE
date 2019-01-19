package com.witfrog.gtse.ui.usualcontacts;

import com.witfrog.gtse.base.IView;
import com.witfrog.gtse.model.PageData;
import com.witfrog.gtse.model.UsualContacts;

public interface UsualContactsView extends IView {
    void getUsualContactsList(PageData<UsualContacts> pageData);
}
