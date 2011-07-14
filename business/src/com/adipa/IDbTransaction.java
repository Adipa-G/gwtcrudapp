package com.adipa;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Feb 5, 2011
 * Time: 10:23:11 AM
 */
public interface IDbTransaction
{
    void begin() throws DbTransactionException;

    void commit() throws DbTransactionException;

    void rollback() throws DbTransactionException;

    boolean isActive();

    Connection getConnection();
}
