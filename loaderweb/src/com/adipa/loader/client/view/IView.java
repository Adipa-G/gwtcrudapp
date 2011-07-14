package com.adipa.loader.client.view;

import com.adipa.loader.client.ViewType;
import com.adipa.loader.client.controller.IController;
import com.google.gwt.user.client.ui.Panel;

/**
 * Date: Jun 2, 2011
 * Time: 4:26:56 PM
 */
public interface IView<T extends IController>
{
    void initUI();

    void checkAuth(ViewType viewType);
    
    void onError(Throwable throwable);

    void showMessage(String message,String title);

    void switchToView(ViewType viewType);

    int getAppWindowHeight();

    int getAppWindowWidth();

    int getContentHeight();

    int getContentWidth();

    int getHeaderHeight();

    int getFooterHeight();

    int getNavigationWidth();

    int getNavigationButtonWidth();

    T getController();

    Panel getRootPanel();
}
