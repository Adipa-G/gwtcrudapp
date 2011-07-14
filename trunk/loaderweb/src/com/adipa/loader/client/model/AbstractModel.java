package com.adipa.loader.client.model;

import com.adipa.loader.auth.client.GetSessionInfoDto;
import com.adipa.loader.auth.client.IAuthenticationService;
import com.adipa.loader.auth.client.IAuthenticationServiceAsync;
import com.adipa.loader.auth.client.SetSessionInfoDto;
import com.adipa.loader.client.ViewType;
import com.adipa.loader.client.controller.AbstractController;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Date: Jun 2, 2011
 * Time: 9:18:04 PM
 */
public abstract class AbstractModel<T extends AbstractController> implements IModel<T>
{
    protected T t;
    protected final IAuthenticationServiceAsync authService;

    public AbstractModel()
    {
        authService = GWT.create(IAuthenticationService.class);
    }

    @Override
    public T getController()
    {
        return t;
    }

    public void setController(T t)
    {
        this.t = t;
    }

    @Override
    public void checkAuthStatusAndRedirect(ViewType viewType)
    {
        final SetSessionInfoDto setSessionInfoDto = new SetSessionInfoDto();
        setSessionInfoDto.setUpdateCurrentView(true);
        setSessionInfoDto.setCurrentView(viewType.toString());

        authService.setSessionInfo(setSessionInfoDto,new AsyncCallback()
        {
            @Override
            public void onFailure(Throwable throwable)
            {
                t.onUserSessionCheckFailed();
            }

            @Override
            public void onSuccess(Object o)
            {
                authService.getSessionInfo(new AsyncCallback<GetSessionInfoDto>()
                {
                    @Override
                    public void onFailure(Throwable throwable)
                    {
                        t.onUserSessionCheckFailed();
                    }

                    @Override
                    public void onSuccess(GetSessionInfoDto getSessionInfoDto)
                    {
                        if (getSessionInfoDto.isValidSession())
                        {
                            t.onValidUserSession(ViewType.valueOf(getSessionInfoDto.getCurrentView()));
                        }
                        else
                        {
                            t.onInvalidUserSession(ViewType.valueOf(setSessionInfoDto.getCurrentView()));
                        }
                    }
                });
            }
        });

    }
}
