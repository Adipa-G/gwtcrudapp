package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.dbutility.DBMgmtUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 3, 2008
 * Time: 9:12:41 PM
 */
public class SequenceManager
{
    public static int getNextValue(Connection con,String sequence)
    {
        int nextVal = -1;

        PreparedStatement ps = null;
        ResultSet rs = null;
        PreparedStatement psu = null;
        try
        {
            ps = con.prepareStatement(String.format("select %s from %s where %s = ?"
                    ,DBConstants.Gen.Sequence.COL_NEXT_VALUE, DBConstants.Gen.Sequence.TABLE_NAME,DBConstants.Gen.Sequence.COL_SEQUENCE_ID));
            ps.setString(1,sequence);
            rs = ps.executeQuery();
            if (rs.next())
            {
                nextVal = rs.getInt("next_value");
            }

            if (nextVal <= 0)
            {
                nextVal = 1;
                psu = con.prepareStatement(String.format("insert into %s (%s, %s) values (?,?)"
                        , DBConstants.Gen.Sequence.TABLE_NAME,DBConstants.Gen.Sequence.COL_SEQUENCE_ID,DBConstants.Gen.Sequence.COL_NEXT_VALUE));
                psu.setString(1,sequence);
                psu.setInt(2,nextVal + 1);
                psu.execute();
            }
            else
            {
                psu = con.prepareStatement(String.format("update %s set %s = ? where %s = ?"
                        , DBConstants.Gen.Sequence.TABLE_NAME,DBConstants.Gen.Sequence.COL_NEXT_VALUE,DBConstants.Gen.Sequence.COL_SEQUENCE_ID));
                psu.setInt(1,nextVal + 1);
                psu.setString(2,sequence);
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
        }

        return nextVal;
    }
}
