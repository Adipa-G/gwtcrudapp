package com.adipa.loader.client.controller.auth;

import com.adipa.loader.client.ViewType;
import com.adipa.loader.client.controller.AbstractController;
import com.adipa.loader.client.model.auth.LogonModel;
import com.adipa.loader.client.view.auth.LogonView;

/**
 * Date: Jun 2, 2011
 * Time: 4:39:26 PM
 */
public class LogonController extends AbstractController<LogonView,LogonModel>
{
    public LogonController(LogonView t, LogonModel u)
    {
        super(t, u);
    }

    public void logon(String userName,String password)
    {
        model.logon(userName,password);
    }

    public void onSuccess()
    {
        view.switchToView(ViewType.MASTER);
    }

    public void onFailure(String message)
    {
        view.showMessage("Logon Failed",message);
    }

    public void onError(Throwable throwable)
    {
        view.onError(throwable);
    }
}
