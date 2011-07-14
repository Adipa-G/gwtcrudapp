package com.adipa.loader.client.controller;

import com.adipa.loader.client.model.IModel;
import com.adipa.loader.client.view.IView;
import com.adipa.loader.client.ViewType;

/**
 * Date: Jun 2, 2011
 * Time: 4:36:41 PM
 */
public interface IController<U extends IView,T extends IModel>
{
    T getModel();

    U getView();

    void onInvalidUserSession(ViewType currentView);

    void onValidUserSession(ViewType currentView);

    void onUserSessionCheckFailed();

    void checkAuthStatusAndRedirect(ViewType viewType);
}
