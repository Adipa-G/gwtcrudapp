package com.adipa;

import dbgate.dbutility.DBConnector;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Feb 5, 2011
 * Time: 11:00:56 AM
 */
public class DbTransaction implements IDbTransaction
{
    private Connection connection;
    private boolean active = false;

    @Override
    public void begin() throws DbTransactionException
    {
        if (!active)
        {
            active = true;
            connection = DBConnector.getSharedInstance().getConnection();
            if (connection == null)
            {
                throw new DbTransactionException("could not begin the transaction");
            }
            else
            {
                try
                {
                    connection.setAutoCommit(false);
                }
                catch (SQLException ex)
                {
                    throw new DbTransactionException("could not set auto commit off for the transaction");
                }
            }
        }
        else
        {
            throw new DbTransactionException("transaction is already active, illegal to call begin on an active transaction");
        }
    }

    @Override
    public void commit()  throws DbTransactionException
    {
        if (active)
        {
            if (connection != null)
            {
                try
                {
                    connection.commit();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                    throw new DbTransactionException("error occured while commit " + e.getMessage(),e);
                }
            }
            else
            {
                throw new DbTransactionException("transaction is in faulted state");
            }
        }
        else
        {
            throw new DbTransactionException("cannot commit an inactive transaction");
        }
    }

    @Override
    public void rollback()  throws DbTransactionException
    {
        if (active)
        {
            if (connection != null)
            {
                try
                {
                    connection.rollback();
                }
                catch (SQLException e)
                {
                    e.printStackTrace();
                    throw new DbTransactionException("error occured while rollback " + e.getMessage(),e);
                }
            }
            else
            {
                throw new DbTransactionException("transaction is in faulted state");
            }
        }
        else
        {
            throw new DbTransactionException("cannot rollback an inactive transaction");
        }
    }

    @Override
    public boolean isActive()
    {
        return active;
    }

    public void setActive(boolean active)
    {
        this.active = active;
    }

    @Override
    public Connection getConnection()
    {
        return connection;
    }
}
