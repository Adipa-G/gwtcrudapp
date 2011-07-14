package com.adipa.entities;

import dbgate.DBClassStatus;
import dbgate.ServerDBClass;
import dbgate.ermanagement.exceptions.PersistException;
import dbgate.ermanagement.impl.ERLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: aga
 * Date: Nov 7, 2010
 * Time: 9:25:11 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class DbClassTestBase
{
    public abstract Class getClassToTest();

    public abstract ServerDBClass getTestDataObject();

    public abstract ServerDBClass updateTestDataObject(ServerDBClass object);

    public abstract boolean verify(ServerDBClass original,ServerDBClass loaded);

    public abstract PreparedStatement createLoadStatement(Connection con,ServerDBClass object) throws SQLException;

    public ServerDBClass load(Connection con,ServerDBClass object)
    {
        ServerDBClass serverDBClass = null;
        try
        {
            serverDBClass = object.getClass().newInstance();

            PreparedStatement ps = createLoadStatement(con,object);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                ERLayer.getSharedInstance().load(serverDBClass,rs,con);
            }
            else
            {
                serverDBClass = null;
            }
            rs.close();
            ps.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return serverDBClass;
    }

    public ServerDBClass insert(ServerDBClass orgDBClass,Connection con)
    {
        orgDBClass.setStatus(DBClassStatus.NEW);
        try
        {
            ERLayer.getSharedInstance().save(orgDBClass,con);
        }
        catch (PersistException e)
        {
            e.printStackTrace();
        }
        return orgDBClass;
    }

    public ServerDBClass update(ServerDBClass orgDBClass, Connection con)
    {
        try
        {
            ERLayer.getSharedInstance().save(orgDBClass,con);
        }
        catch (PersistException e)
        {
            e.printStackTrace();
        }
        return orgDBClass;
    }

    public ServerDBClass delete(ServerDBClass orgDBClass,Connection con)
    {
        try
        {
            orgDBClass.setStatus(DBClassStatus.DELETED);
            ERLayer.getSharedInstance().save(orgDBClass,con);
        }
        catch (PersistException e)
        {
            e.printStackTrace();
        }
        return orgDBClass;
    }
}
