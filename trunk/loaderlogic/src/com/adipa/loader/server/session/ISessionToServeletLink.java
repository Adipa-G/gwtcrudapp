package com.adipa.loader.server.session;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 30, 2011
 * Time: 6:11:18 PM
 */
public interface ISessionToServeletLink
{
    SessionRoot getSessionRoot();

    void setSessionRoot(SessionRoot sessionRoot);
}
