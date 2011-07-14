package com.adipa.loader.client;

import com.adipa.loader.client.view.IView;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Index implements EntryPoint
{
    /**
     * The message displayed to the user when the server cannot be reached or
     * returns an error.
     */
    private static final String SERVER_ERROR = "An error occurred while "
            + "attempting to contact the server. Please check your network "
            + "connection and try again.";

    /**
     * This is the entry point method.
     */
    public void onModuleLoad()
    {
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler()
        {
            @Override
            public void onUncaughtException(Throwable throwable)
            {
                Logger.getLogger("UNCAUGHT").log(Level.SEVERE,throwable.getMessage(),throwable);
            }
        });

        IView view = ViewFactory.createView(ViewType.LOGON,null);
        view.initUI();
    }
}
