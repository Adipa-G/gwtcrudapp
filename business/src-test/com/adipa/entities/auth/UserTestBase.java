package com.adipa.entities.auth;

import com.adipa.entities.DbClassTestBase;
import dbgate.ServerDBClass;
import dbgate.ermanagement.impl.utils.DBClassAttributeExtractionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: aga
 * Date: Nov 7, 2010
 * Time: 10:07:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class UserTestBase extends DbClassTestBase
{
    @Override
    public Class getClassToTest()
    {
        return User.class;
    }

    @Override
    public ServerDBClass getTestDataObject()
    {
        User user = new User();
        user.setUserName("NameA");
        user.setPassword("PasswordA");

        return user;
    }

    @Override
    public ServerDBClass updateTestDataObject(ServerDBClass object)
    {
        User user = (User) object;
        user.setUserName("NameAB");
        user.setPassword("PasswordAB");
        user._modify();

        return user;
    }

    @Override
    public PreparedStatement createLoadStatement(Connection con, ServerDBClass object) throws SQLException
    {
        String tableName = DBClassAttributeExtractionUtils.getTableName(object,object.getClass());
        PreparedStatement ps = con.prepareStatement(String.format("select * from %s where user_id = ?",tableName));

        User user = (User) object;
        ps.setInt(1,user.getUserId());
        return ps;
    }

    @Override
    public boolean verify(ServerDBClass original, ServerDBClass loaded)
    {
        boolean value = original instanceof User
                && loaded instanceof User;
        if (value)
        {
            User orgUser = (User) original;
            User loadedUser = (User) loaded;

            return orgUser.getUserName().equals(loadedUser.getUserName())
                    && orgUser.getPassword().equals(loadedUser.getPassword());
        }
        return value;
    }
}
