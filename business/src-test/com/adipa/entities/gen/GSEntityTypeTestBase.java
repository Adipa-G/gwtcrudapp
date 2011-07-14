package com.adipa.entities.gen;

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
public class GSEntityTypeTestBase extends DbClassTestBase
{
    @Override
    public Class getClassToTest()
    {
        return GSEntityType.class;
    }

    @Override
    public ServerDBClass getTestDataObject()
    {
        GSEntityType entityType = new GSEntityType();
        entityType.setEntityTypeCode("A");

        return entityType;
    }

    @Override
    public ServerDBClass updateTestDataObject(ServerDBClass object)
    {
        GSEntityType entityType = (GSEntityType) object;
        entityType.setEntityTypeCode("B");
        entityType._modify();

        return object;
    }

    @Override
    public PreparedStatement createLoadStatement(Connection con, ServerDBClass object) throws SQLException
    {
        String tableName = DBClassAttributeExtractionUtils.getTableName(object,object.getClass());
        PreparedStatement ps = con.prepareStatement(String.format("select * from %s where entity_Type_Id = ?",tableName));

        GSEntityType entityType = (GSEntityType) object;
        ps.setInt(1,entityType.getEntityTypeId());
        return ps;
    }

    @Override
    public boolean verify(ServerDBClass original, ServerDBClass loaded)
    {
        boolean value = original instanceof GSEntityType
                && loaded instanceof GSEntityType;
        if (value)
        {
            GSEntityType orgEntityType = (GSEntityType) original;
            GSEntityType loadedEntityType = (GSEntityType) loaded;

            return orgEntityType.getEntityTypeCode().equals(loadedEntityType.getEntityTypeCode());
        }
        return value;
    }


}
