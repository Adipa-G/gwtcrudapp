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
public class GSEntityEntryTestBase extends DbClassTestBase
{
    @Override
    public Class getClassToTest()
    {
        return GSEntityEntry.class;
    }

    @Override
    public ServerDBClass getTestDataObject()
    {
        GSEntityEntry entityEntry = new GSEntityEntry();
        return entityEntry;
    }

    @Override
    public ServerDBClass updateTestDataObject(ServerDBClass object)
    {
        GSEntityEntry entityEntry = (GSEntityEntry) object;
        entityEntry._modify();

        return object;
    }

    @Override
    public PreparedStatement createLoadStatement(Connection con, ServerDBClass object) throws SQLException
    {
        String tableName = DBClassAttributeExtractionUtils.getTableName(object,object.getClass());
        PreparedStatement ps = con.prepareStatement(String.format("select * from %s where entity_id = ?",tableName));

        GSEntityEntry entityEntry = (GSEntityEntry) object;
        ps.setInt(1,entityEntry.getEntityId());
        return ps;
    }

    @Override
    public boolean verify(ServerDBClass original, ServerDBClass loaded)
    {
        boolean value = original instanceof GSEntityEntry
                && loaded instanceof GSEntityEntry;
        if (value)
        {
            GSEntityEntry orgEntityEntry = (GSEntityEntry) original;
            GSEntityEntry loadedEntityEntry = (GSEntityEntry) loaded;

            for (GSEntityProperty orgProperty : orgEntityEntry.getProperties())
            {
                boolean found = false;
                for (GSEntityProperty loadedProperty : loadedEntityEntry.getProperties())
                {
                    found = found ||(orgProperty.getEntityId() == loadedProperty.getEntityId())
                            && orgProperty.getPropertyTypeId() ==  loadedProperty.getPropertyTypeId()
                            && orgProperty.getPropertyValue().equals(loadedProperty.getPropertyValue());
                }
                if (!found)
                {
                    return false;
                }
            }
        }
        return value;
    }
}
