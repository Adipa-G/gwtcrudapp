package com.adipa.loader.client.controller;

import com.adipa.loader.client.ViewType;
import com.adipa.loader.client.model.AbstractModel;
import com.adipa.loader.client.view.AbstractView;

/**
 * Date: Jun 2, 2011
 * Time: 9:19:21 PM
 */
public abstract class AbstractController<T extends AbstractView,U extends AbstractModel> implements IController
{
    protected T view;
    protected U model;

    protected AbstractController(T view, U model)
    {
        this.view = view;
        this.model = model;
    }

    @Override
    public U getModel()
    {
        return model;
    }

    @Override
    public T getView()
    {
        return view;
    }

    @Override
    public void checkAuthStatusAndRedirect(ViewType viewType)
    {
        model.checkAuthStatusAndRedirect(viewType);
    }

    @Override
    public void onInvalidUserSession(ViewType viewType)
    {
        if (viewType != ViewType.LOGON)
            view.switchToView(ViewType.LOGON);
    }

    @Override
    public void onValidUserSession(ViewType viewType)
    {
        if (viewType == ViewType.LOGON)
            view.switchToView(ViewType.MASTER);
    }

    @Override
    public void onUserSessionCheckFailed()
    {
        view.showMessage("Session seems to be unavailable. Please logon again","Invalid Session");
    }
}
