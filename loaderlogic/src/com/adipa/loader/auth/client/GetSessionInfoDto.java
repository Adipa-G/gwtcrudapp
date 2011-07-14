package com.adipa.loader.auth.client;

import java.io.Serializable;


/**
 * Date: Jul 11, 2011
 * Time: 11:05:48 PM
 */
public class GetSessionInfoDto  implements Serializable
{
    private boolean validSession;
    private String userName;
    private String currentView;

    public boolean isValidSession()
    {
        return validSession;
    }

    public void setValidSession(boolean validSession)
    {
        this.validSession = validSession;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getCurrentView()
    {
        return currentView;
    }

    public void setCurrentView(String currentView)
    {
        this.currentView = currentView;
    }
}
