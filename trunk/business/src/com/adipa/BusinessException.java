package com.adipa;

/**
 * Date: May 18, 2011
 * Time: 11:41:01 PM
 */
public class BusinessException extends Exception
{
    public BusinessException()
    {
    }

    public BusinessException(String message)
    {
        super(message);
    }

    public BusinessException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public BusinessException(Throwable cause)
    {
        super(cause);
    }
}
