package com.adipa.loader.server.exceptions;

/**
 * Date: May 31, 2011
 * Time: 10:06:30 PM
 */
public class LoaderException extends Exception
{
    public LoaderException()
    {
    }

    public LoaderException(String message)
    {
        super(message);
    }

    public LoaderException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public LoaderException(Throwable cause)
    {
        super(cause);
    }
}
