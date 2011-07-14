package com.adipa.repositories;

import com.adipa.BusinessException;

/**
 * Date: May 18, 2011
 * Time: 11:42:59 PM
 */
public class VersionMisMatchException extends BusinessException
{
    public VersionMisMatchException()
    {
    }

    public VersionMisMatchException(String message)
    {
        super(message);
    }

    public VersionMisMatchException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public VersionMisMatchException(Throwable cause)
    {
        super(cause);
    }
}
