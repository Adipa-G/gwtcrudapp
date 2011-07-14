package com.adipa.loader.auth.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * Date: May 27, 2011
 * Time: 10:38:54 PM
 */
@RemoteServiceRelativePath("auth")
public interface IAuthenticationService extends RemoteService
{
    AuthResponseDto logon(AuthRequestDto requestDto) throws IllegalArgumentException;

    void setSessionInfo(SetSessionInfoDto sessionInfoDto) throws IllegalArgumentException;

    GetSessionInfoDto getSessionInfo() throws IllegalArgumentException;
}
