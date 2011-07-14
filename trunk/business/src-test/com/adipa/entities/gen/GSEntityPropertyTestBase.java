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
public class GSEntityPropertyTestBase extends DbClassTestBase
{
    @Override
    public Class getClassToTest()
    {
        return GSEntityProperty.class;
    }

    @Override
    public ServerDBClass getTestDataObject()
    {
        GSEntityEntryTestBase entityEntryTestBase = new GSEntityEntryTestBase();
        GSEntityEntry entityEntry = (GSEntityEntry) entityEntryTestBase.getTestDataObject();

        GSEntityProperty entityProperty = new GSEntityProperty();
        entityProperty.setPropertyTypeId(1);
        entityProperty.setPropertyValue("A");
        entityEntry.getProperties().add(entityProperty);

        entityProperty = new GSEntityProperty();
        entityProperty.setPropertyTypeId(2);
        entityProperty.setPropertyValue("B");
        entityEntry.getProperties().add(entityProperty);

        return entityEntry;
    }

    @Override
    public ServerDBClass updateTestDataObject(ServerDBClass object)
    {
        GSEntityEntry entityEntry = (GSEntityEntry) object;

        GSEntityProperty entityProperty = entityEntry.getProperties().get(0);
        entityProperty._modify();
        entityProperty.setPropertyValue("BA");

        entityProperty = entityEntry.getProperties().get(1);
        entityProperty._modify();
        entityProperty.setPropertyValue("BB");

        return entityEntry;
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
                    found = found ||(orgProperty.getPropertyValue().equals(loadedProperty.getPropertyValue()));
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
