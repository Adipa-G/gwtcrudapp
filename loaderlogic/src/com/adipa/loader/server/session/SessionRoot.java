package com.adipa.loader.server.session;


/**
 * Date: May 31, 2011
 * Time: 9:59:29 PM
 */
public class SessionRoot
{
    String userName;
    String currentViewType;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getCurrentViewType()
    {
        return currentViewType;
    }

    public void setCurrentViewType(String currentViewType)
    {
        this.currentViewType = currentViewType;
    }
}
