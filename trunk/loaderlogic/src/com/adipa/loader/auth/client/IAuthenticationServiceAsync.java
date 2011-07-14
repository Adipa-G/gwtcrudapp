package com.adipa.loader.auth.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Date: May 27, 2011
 * Time: 10:38:54 PM
 */
@RemoteServiceRelativePath("auth")
public interface IAuthenticationServiceAsync
{
    void logon(AuthRequestDto requestDto, AsyncCallback<AuthResponseDto> callback) throws IllegalArgumentException;

    void setSessionInfo(SetSessionInfoDto sessionInfoDto,AsyncCallback callback) throws IllegalArgumentException;

    void getSessionInfo(AsyncCallback<GetSessionInfoDto> callback) throws IllegalArgumentException;
}
