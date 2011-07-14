package com.adipa.loader.auth.client;

import java.io.Serializable;

/**
 * Date: May 27, 2011
 * Time: 10:40:14 PM
 */
public class AuthRequestDto implements Serializable
{
    private String userName;
    private String password;

    public AuthRequestDto()
    {
    }

    public AuthRequestDto(String userName, String password)
    {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
