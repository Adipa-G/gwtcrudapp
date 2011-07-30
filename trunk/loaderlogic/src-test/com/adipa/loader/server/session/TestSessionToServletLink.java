package com.adipa.loader.server.session;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 30, 2011
 * Time: 6:26:36 PM
 */
public class TestSessionToServletLink implements ISessionToServeletLink
{
    private SessionRoot sessionRoot;

    @Override
    public SessionRoot getSessionRoot()
    {
        return sessionRoot;
    }

    @Override
    public void setSessionRoot(SessionRoot sessionRoot)
    {
        this.sessionRoot = sessionRoot;
    }
}
