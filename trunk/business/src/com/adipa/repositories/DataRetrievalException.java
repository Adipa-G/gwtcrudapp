package com.adipa.repositories;

import com.adipa.BusinessException;

/**
 * Date: May 18, 2011
 * Time: 11:44:52 PM
 */
public class DataRetrievalException extends BusinessException
{
    public DataRetrievalException()
    {
    }

    public DataRetrievalException(String message)
    {
        super(message);
    }

    public DataRetrievalException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public DataRetrievalException(Throwable cause)
    {
        super(cause);
    }
}
