package com.adipa.loader.workspace.client.user;

import com.adipa.loader.workspace.client.ICRUDObjectDto;

import java.io.Serializable;

/**
 * Date: Jul 13, 2011
 * Time: 12:46:53 PM
 */
public class UserDto implements ICRUDObjectDto
{
    private long userId;
    private String userName;
    private String password;

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
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
