package com.adipa.loader.server;

import com.adipa.loader.server.exceptions.auth.UserNotLoggedInException;

/**
 * Date: May 31, 2011
 * Time: 9:43:48 PM
 */
public interface IService
{
    void initialize();

    void handleException(Exception ex,String... messages);

    void checkIfAuthenticated() throws UserNotLoggedInException;
}
