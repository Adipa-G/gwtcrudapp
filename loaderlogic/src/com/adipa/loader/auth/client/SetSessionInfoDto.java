package com.adipa.loader.auth.client;

import java.io.Serializable;


/**
 * Date: Jul 11, 2011
 * Time: 11:21:50 PM
 */
public class SetSessionInfoDto  implements Serializable
{
    private String currentView;
    private boolean updateCurrentView;

    public SetSessionInfoDto()
    {
    }

    public String getCurrentView()
    {
        return currentView;
    }

    public void setCurrentView(String currentView)
    {
        this.currentView = currentView;
    }

    public boolean isUpdateCurrentView()
    {
        return updateCurrentView;
    }

    public void setUpdateCurrentView(boolean updateCurrentView)
    {
        this.updateCurrentView = updateCurrentView;
    }
}
