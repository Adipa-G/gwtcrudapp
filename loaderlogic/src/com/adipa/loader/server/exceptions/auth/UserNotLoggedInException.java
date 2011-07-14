package com.adipa.loader.server.exceptions.auth;

import com.adipa.loader.server.exceptions.LoaderException;

/**
 * Date: May 31, 2011
 * Time: 10:08:38 PM
 */
public class UserNotLoggedInException extends LoaderException
{
    public UserNotLoggedInException()
    {
    }

    public UserNotLoggedInException(String message)
    {
        super(message);
    }

    public UserNotLoggedInException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public UserNotLoggedInException(Throwable cause)
    {
        super(cause);
    }
}
