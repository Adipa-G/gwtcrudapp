package com.adipa.repositories;

import com.adipa.BusinessException;

/**
 * Date: May 18, 2011
 * Time: 11:44:35 PM
 */
public class DataPersistException extends BusinessException
{
    public DataPersistException()
    {
    }

    public DataPersistException(String message)
    {
        super(message);
    }

    public DataPersistException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DataPersistException(Throwable cause)
    {
        super(cause);
    }
}
