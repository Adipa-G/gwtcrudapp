package com.adipa.repositories.auth.impl;

import com.adipa.entities.DBConstants;
import com.adipa.entities.LongEntityKey;
import com.adipa.entities.auth.User;
import com.adipa.repositories.AbstractRepository;
import com.adipa.repositories.DataRetrievalException;
import com.adipa.repositories.auth.IUserRepository;
import dbgate.dbutility.DBMgmtUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 29, 2011
 * Time: 11:47:40 AM
 */
public class UserRepository extends AbstractRepository<User, LongEntityKey> implements IUserRepository
{
    @Override
    protected ResultSet createRetrieveAllResultSet(Connection con) throws Exception
    {
        PreparedStatement ps = null;
        try
        {
            ps = con.prepareStatement(String.format("select * from %s", DBConstants.Auth.User.TABLE_NAME));

            ResultSet rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException e)
        {
            String logMsg = String.format("Unable to create retrieval result set failed for object %s",createNewInstance().getClass().getName());
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,logMsg,e);
        }
        return null;
    }

    @Override
    protected ResultSet createRetrievalResultSet(Connection con, LongEntityKey key) throws Exception
    {
        PreparedStatement ps = null;
        try
        {
            ps = con.prepareStatement(String.format("select * from %s where %s = ?", DBConstants.Auth.User.TABLE_NAME, DBConstants.Auth.User.ID_COL_USER));
            ps.setLong(1,key.getKey(0));

            ResultSet rs = ps.executeQuery();
            return rs;
        }
        catch (SQLException e)
        {
            String logMsg = String.format("Unable to create retrieval result set failed for object %s",createNewInstance().getClass().getName());
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,logMsg,e);
        }
        return null;
    }

    @Override 
    public User createNewInstance()
    {
        return new User();
    }

    @Override
    public User getByUserName(String userName) throws DataRetrievalException
    {
        User user = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = String.format("select * from %s where %s = ? "
                    ,DBConstants.Auth.User.TABLE_NAME,DBConstants.Auth.User.COL_USER_NAME);
        try
        {
            con = getConnection();
            ps = con.prepareStatement(sql);

            int count = 0;
            ps.setString(++count,userName);

            rs = ps.executeQuery();
            if (rs.next())
            {
                user = createNewInstance();
                user.retrieve(rs,con);
            }
            return user;
        }
        catch (Exception e)
        {
            String logMsg = "Failed to execute the query " + createLogQuery(sql,userName);
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,logMsg,e);
            throw new DataRetrievalException(logMsg);
        }
        finally
        {
            DBMgmtUtility.close(rs);
            DBMgmtUtility.close(ps);
            closeConnection(con);
        }
    }
}