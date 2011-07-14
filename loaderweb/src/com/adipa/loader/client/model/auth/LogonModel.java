package com.adipa.loader.client.model.auth;

import com.adipa.loader.auth.client.AuthRequestDto;
import com.adipa.loader.auth.client.AuthResponseDto;
import com.adipa.loader.client.controller.auth.LogonController;
import com.adipa.loader.client.model.AbstractModel;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Date: Jun 2, 2011
 * Time: 4:39:12 PM
 */
public class LogonModel extends AbstractModel<LogonController>
{
    public LogonModel()
    {
    }

    public void logon(String userName,String password)
    {
        authService.logon(new AuthRequestDto(userName,password),new AsyncCallback<AuthResponseDto>()
        {
            @Override
            public void onFailure(Throwable throwable)
            {
                t.onError(throwable);
            }

            @Override
            public void onSuccess(AuthResponseDto authResponseDto)
            {
                if (authResponseDto.isLogonSuccess())
                {
                    t.onSuccess();
                }
                else
                {
                    t.onFailure(authResponseDto.getMessage());
                }
            }
        } );
    }
}
