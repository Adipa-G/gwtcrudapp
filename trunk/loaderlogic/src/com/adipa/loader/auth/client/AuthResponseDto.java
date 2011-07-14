package com.adipa.loader.auth.client;

import java.io.Serializable;

/**
 * Date: May 27, 2011
 * Time: 10:41:15 PM
 */
public class AuthResponseDto  implements Serializable
{
    private boolean logonSuccess;
    private String message;

    public AuthResponseDto()
    {
    }

    public AuthResponseDto(boolean logonSuccess, String message)
    {
        this.logonSuccess = logonSuccess;
        this.message = message;
    }

    public boolean isLogonSuccess()
    {
        return logonSuccess;
    }

    public void setLogonSuccess(boolean logonSuccess)
    {
        this.logonSuccess = logonSuccess;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
