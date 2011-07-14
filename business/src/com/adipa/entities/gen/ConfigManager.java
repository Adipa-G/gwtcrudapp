package com.adipa.entities.gen;


import com.adipa.entities.DBConstants;
import dbgate.dbutility.DBConnector;
import dbgate.dbutility.DBMgmtUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 6, 2008
 * Time: 1:12:41 PM
 */
public class ConfigManager
{
    public static String getConfigValue(String key)
    {
        String  configValue = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            con = DBConnector.getSharedInstance().getConnection();
            ps = con.prepareStatement(String.format("select %s from %s where %s = ?"
                    ,DBConstants.Gen.Config.COL_CONFIG_VALUE, DBConstants.Gen.Config.TABLE_NAME,DBConstants.Gen.Config.COL_CONFIG_NAME));
            ps.setString(1,key);
            rs = ps.executeQuery();
            if (rs.next())
            {
                configValue = rs.getString(DBConstants.Gen.Config.COL_CONFIG_VALUE);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBMgmtUtility.close(rs);
            DBMgmtUtility.close(ps);
            DBMgmtUtility.close(con);
        }
        return configValue;
    }

    public static void setConfigValue(String key,String value)
    {
        String  configValue = null;

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement psu = null;
        try
        {
            con = DBConnector.getSharedInstance().getConnection();
            ps = con.prepareStatement(String.format("select %s from %s where %s = ?"
                    ,DBConstants.Gen.Config.COL_CONFIG_VALUE,DBConstants.Gen.Config.TABLE_NAME,DBConstants.Gen.Config.COL_CONFIG_NAME));
            ps.setString(1,key);
            rs = ps.executeQuery();
            if (rs.next())
            {
                configValue = rs.getString(DBConstants.Gen.Config.COL_CONFIG_VALUE);
            }

            if (configValue == null)
            {
                psu = con.prepareStatement(String.format("insert into %s (%s, %s) values (?,?)"
                        , DBConstants.Gen.Config.TABLE_NAME,DBConstants.Gen.Config.COL_CONFIG_NAME,DBConstants.Gen.Config.COL_CONFIG_VALUE));
                psu.setString(1,key);
                psu.setString(2,value);
                psu.execute();
            }
            else
            {
                psu = con.prepareStatement(String.format("update %s set %s = ? where %s = ?"
                        , DBConstants.Gen.Config.TABLE_NAME,DBConstants.Gen.Config.COL_CONFIG_VALUE,DBConstants.Gen.Config.COL_CONFIG_NAME));
                psu.setString(1,value);
                psu.setString(2,key);
                psu.execute();
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            DBMgmtUtility.close(rs);
            DBMgmtUtility.close(ps);
            DBMgmtUtility.close(psu);
            DBMgmtUtility.close(con);
        }
    }
}
