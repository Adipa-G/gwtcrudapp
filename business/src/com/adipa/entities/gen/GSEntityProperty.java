package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.DBColumnType;
import dbgate.ermanagement.*;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Mar 28, 2010
 * Time: 9:18:55 PM
 */

@DBTableInfo(tableName = DBConstants.Gen.EntityProperty.TABLE_NAME)
public class GSEntityProperty extends DefaultServerDBClass
{
    @DBColumnInfo(columnType = DBColumnType.INTEGER,key = true,columnName = DBConstants.Gen.EntityProperty.ID_COL_ENTITY)
    private int entityId;

    @ForeignKeyInfo(name = "fk_entity_property2entity_entry"
                    ,relatedObjectType = GSEntityEntry.class
                    ,reverseRelation = true
                    ,columnMappings =  {@ForeignKeyColumnMapping(fromField = "entityId",toField = "entityId")})
    private GSEntityEntry entity;

    @DBColumnInfo(columnType = DBColumnType.INTEGER,key = true,columnName = DBConstants.Gen.EntityProperty.ID_COL_ENTITY_TYPE)
    private int entityTypeId;

    @ForeignKeyInfo(name = "fk_entity_property2entity_type"
                    ,relatedObjectType = GSEntityType.class
                    ,reverseRelation = true
                    ,columnMappings =  {@ForeignKeyColumnMapping(fromField = "entityTypeId",toField = "entityTypeId")})
    private GSEntityType entityType;

    @DBColumnInfo(columnType = DBColumnType.INTEGER,key = true,columnName = DBConstants.Gen.EntityProperty.ID_COL_PROPERTY_TYPE)
    private int propertyTypeId;

    @ForeignKeyInfo(name = "fk_entity_property2entity_property_type"
                    ,relatedObjectType = GSEntityPropertyType.class
                    ,reverseRelation = true
                    ,columnMappings =  {@ForeignKeyColumnMapping(fromField = "entityTypeId",toField = "entityTypeId")
                    ,@ForeignKeyColumnMapping(fromField = "propertyTypeId",toField = "propertyTypeId")})
    private GSEntityPropertyType entityPropertyType;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR,nullable = true)
    private String propertyValue;

    public GSEntityProperty()
    {
    }

    public GSEntityProperty(GSEntityEntry entityEntry,GSEntityPropertyType propertyType)
    {
        this.entityId = entityEntry.getEntityId();
        this.propertyTypeId = propertyType.getPropertyTypeId();
    }

    public int getEntityId()
    {
        return entityId;
    }

    public void setEntityId(int entityId)
    {
        this.entityId = entityId;
    }

    public int getEntityTypeId()
    {
        return entityTypeId;
    }

    public void setEntityTypeId(int entityTypeId)
    {
        this.entityTypeId = entityTypeId;
    }

    public int getPropertyTypeId()
    {
        return propertyTypeId;
    }

    public void setPropertyTypeId(int propertyTypeId)
    {
        this.propertyTypeId = propertyTypeId;
    }

    public String getPropertyValue()
    {
        return propertyValue;
    }

    public void setPropertyValue(String propertyValue)
    {
        this.propertyValue = propertyValue;
    }

    public GSEntityEntry getEntity()
    {
        return entity;
    }

    public void setEntity(GSEntityEntry entity)
    {
        this.entity = entity;
    }

    public GSEntityType getEntityType()
    {
        return entityType;
    }

    public void setEntityType(GSEntityType entityType)
    {
        this.entityType = entityType;
    }

    public GSEntityPropertyType getEntityPropertyType()
    {
        return entityPropertyType;
    }

    public void setEntityPropertyType(GSEntityPropertyType entityPropertyType)
    {
        this.entityPropertyType = entityPropertyType;
    }
}
