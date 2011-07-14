package com.adipa.entities.gen;

import com.adipa.entities.DbClassTestBase;
import dbgate.ServerDBClass;

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
public class GSEntityPropertyTypeTestBase extends DbClassTestBase
{
    @Override
    public Class getClassToTest()
    {
        return GSEntityPropertyType.class;
    }

    @Override
    public ServerDBClass getTestDataObject()
    {
        GSEntityTypeTestBase entityTypeTestBase = new GSEntityTypeTestBase();
        GSEntityType entityType = (GSEntityType) entityTypeTestBase.getTestDataObject();

        GSEntityPropertyType entityPropertyType = new GSEntityPropertyType();
        entityPropertyType.setPropertyTypeId(1);
        entityType.getPropertyTypes().add(entityPropertyType);

        entityPropertyType.setDefaultValue("A");
        entityPropertyType.setPropertyCode("PropA");
        entityPropertyType.setPropertyDescription("PropDescA");
        entityPropertyType.setPropertyType("PropTypeA");

        entityPropertyType = new GSEntityPropertyType();
        entityPropertyType.setPropertyTypeId(2);
        entityType.getPropertyTypes().add(entityPropertyType);

        entityPropertyType.setDefaultValue("B");
        entityPropertyType.setPropertyCode("PropB");
        entityPropertyType.setPropertyDescription("PropDescB");
        entityPropertyType.setPropertyType("PropTypeB");

        return entityType;
    }

    @Override
    public ServerDBClass updateTestDataObject(ServerDBClass object)
    {
        GSEntityType entityType = (GSEntityType) object;

        GSEntityPropertyType entityPropertyType = entityType.getPropertyTypes().get(0);
        entityPropertyType.setDefaultValue("AB");
        entityPropertyType.setPropertyCode("PropAB");
        entityPropertyType.setPropertyDescription("PropDescAB");
        entityPropertyType.setPropertyType("PropTypeAB");
        entityPropertyType._modify();

        entityPropertyType = entityType.getPropertyTypes().get(1);
        entityPropertyType.setDefaultValue("BB");
        entityPropertyType.setPropertyCode("PropBB");
        entityPropertyType.setPropertyDescription("PropDescBB");
        entityPropertyType.setPropertyType("PropTypeBB");
        entityPropertyType._modify();

        return entityType;
    }

    @Override
    public PreparedStatement createLoadStatement(Connection con, ServerDBClass object) throws SQLException
    {
        return null;
    }

    @Override
    public ServerDBClass load(Connection con, ServerDBClass object)
    {
        GSEntityTypeTestBase entityTypeTestBase = new GSEntityTypeTestBase();
        return entityTypeTestBase.load(con,object);
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

            for (GSEntityPropertyType orgPropertyType : orgEntityType.getPropertyTypes())
            {
                boolean found = false;
                for (GSEntityPropertyType loadedPropertyType : loadedEntityType.getPropertyTypes())
                {
                    found = found ||(orgPropertyType.getDefaultValue().equals(loadedPropertyType.getDefaultValue())
                            && orgPropertyType.getPropertyCode().equals(loadedPropertyType.getPropertyCode())
                            && orgPropertyType.getPropertyDescription().equals(loadedPropertyType.getPropertyDescription())
                            && orgPropertyType.getPropertyType().equals(loadedPropertyType.getPropertyType())
                            && orgPropertyType.getPropertyTypeId() ==  loadedPropertyType.getPropertyTypeId());

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
