package com.adipa;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 28, 2011
 * Time: 9:54:15 PM
 */
public class DbTransactionException extends BusinessException
{
    public DbTransactionException()
    {
    }

    public DbTransactionException(String s)
    {
        super(s);
    }

    public DbTransactionException(String s, Throwable throwable)
    {
        super(s, throwable);
    }

    public DbTransactionException(Throwable throwable)
    {
        super(throwable);
    }
}