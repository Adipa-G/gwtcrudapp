package com.adipa.repositories;

import com.adipa.GSInfo;
import com.adipa.IDbTransaction;
import com.adipa.entities.IEntity;
import com.adipa.entities.IEntityKey;
import dbgate.DBClassStatus;
import dbgate.dbutility.DBConnector;
import dbgate.dbutility.DBMgmtUtility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 28, 2011
 * Time: 9:45:03 PM
 */
public abstract class AbstractRepository<T extends IEntity,U extends IEntityKey> implements IRepository<T,U>
{
    private IDbTransaction dbTransaction;

    protected String createLogQuery(String sql,Object... parameters)
    {
        String query = sql;
        for (Object parameter : parameters)
        {
            query = query.replaceFirst("\\?",parameter.toString());
        }
        return query;
    }

    @Override
    public Connection getConnection()
    {
        if (dbTransaction != null && !dbTransaction.isActive())
        {
            dbTransaction = null;
        }
        if (dbTransaction != null)
        {
            return dbTransaction.getConnection();
        }
        return DBConnector.getSharedInstance().getConnection();
    }

    @Override
    public void closeConnection(Connection con)
    {
        if (dbTransaction != null && !dbTransaction.isActive())
        {
            dbTransaction = null;
        }
        if (dbTransaction == null)
        {
            DBMgmtUtility.close(con);
        }
    }

    @Override
    public void attachTransaction(IDbTransaction dbTransaction)
    {
        this.dbTransaction = dbTransaction;
    }

    @Override
    public void clearTransaction()
    {
        this.dbTransaction = null;
    }

    @Override
    public Collection<T> getAll() throws DataRetrievalException
    {
        Connection con = null;
        ResultSet rs = null;
        Collection<T> collection = new ArrayList<T>();

        try
        {
            con = getConnection();
            rs = createRetrieveAllResultSet(con);
            while (rs.next())
            {
                T t = createNewInstance();
                t.retrieve(rs,con);
                collection.add(t);
            }
            return collection;
        }
        catch (Exception ex)
        {
            String logMsg = String.format("retrieval all failed for object %s",createNewInstance().getClass().getName());
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,logMsg,ex);
            throw new DataRetrievalException(logMsg,ex);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    DBMgmtUtility.close(rs.getStatement());
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();

            }
            DBMgmtUtility.close(rs);
            closeConnection(con);
        }
    }

    @Override
    public T getById(U key) throws DataRetrievalException
    {
        Connection con = null;
        ResultSet rs = null;
        try
        {
            con = getConnection();
            rs = createRetrievalResultSet(con,key);
            if (rs.next())
            {
                T t = createNewInstance();
                t.retrieve(rs,con);
                return t;
            }
            return null;
        }
        catch (Exception ex)
        {
            String logMsg = String.format("retrieval failed for object %s",createNewInstance().getClass().getName());
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,logMsg,ex);
            throw new DataRetrievalException(logMsg,ex);
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    DBMgmtUtility.close(rs.getStatement());
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            DBMgmtUtility.close(rs);
            closeConnection(con);
        }
    }

    @Override
    public T persist(T t) throws DataPersistException, VersionMisMatchException
    {
        Connection con = null;
        try
        {
            GSInfo versionMatchInfo = isVersionMatch(t);
            if (versionMatchInfo.getNo() == GSInfo.ERROR)
            {
                throw new VersionMisMatchException(versionMatchInfo.getMessage());
            }
            if (t.getStatus().equals(DBClassStatus.MODIFIED))
            {
                t.updateVersion();
            }
            con = getConnection();
            t.persist(con);

            return getById((U) t.getId());
        }
        catch (Exception ex)
        {
            try
            {
                if (con != null)
                {
                    con.rollback();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
            String logMsg = String.format("Persisting failed for object %s",t);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,logMsg,ex);
            if (ex instanceof VersionMisMatchException)
            {
                throw (VersionMisMatchException)ex;
            }
            throw new DataPersistException(logMsg,ex);
        }
        finally
        {
            closeConnection(con);
        }
    }

    protected GSInfo isVersionMatch(T t) throws DataRetrievalException,VersionMisMatchException
    {
        T oldT = getById((U) t.getId());
        if (oldT != null)
        {
            if (oldT.getVersion() > t.getVersion())
            {
                String logMsg = String.format("Database version[%s] is newer than the object version[%s]. cannot persist %s",oldT.getVersion(),t.getVersion(),t);
                Logger.getLogger(getClass().getName()).severe(logMsg);
                return new GSInfo(GSInfo.ERROR,logMsg);
            }
        }
        return new GSInfo(GSInfo.SUCESS,"Success");
    }

    protected abstract ResultSet createRetrievalResultSet(Connection con,U key) throws Exception;

    protected abstract ResultSet createRetrieveAllResultSet(Connection con) throws Exception;
}
