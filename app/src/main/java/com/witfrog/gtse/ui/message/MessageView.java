package com.witfrog.gtse.ui.message;

import com.witfrog.gtse.base.IView;
import com.witfrog.gtse.model.Message;
import com.witfrog.gtse.model.PageData;

public interface MessageView extends IView {
    void getMessageList(PageData<Message> pageData);
}
