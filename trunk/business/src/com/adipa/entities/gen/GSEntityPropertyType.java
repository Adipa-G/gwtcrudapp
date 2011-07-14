package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.DBColumnType;
import dbgate.ermanagement.*;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Mar 28, 2010
 * Time: 9:03:27 PM
 */
@DBTableInfo(tableName = DBConstants.Gen.EntityPropertyType.TABLE_NAME)
public class GSEntityPropertyType extends DefaultServerDBClass
{
    @DBColumnInfo(columnType = DBColumnType.INTEGER,key = true,columnName = DBConstants.Gen.EntityPropertyType.ID_COL_ENTITY_TYPE)
    private int entityTypeId;

    @ForeignKeyInfo(name = "fk_entity_property_type2entity_type"
                    ,relatedObjectType = GSEntityType.class
                    ,reverseRelation = true
                    ,columnMappings =  {@ForeignKeyColumnMapping(fromField = "entityTypeId",toField = "entityTypeId")})
    private GSEntityType entityType;

    @DBColumnInfo(columnType = DBColumnType.INTEGER,key = true,columnName = DBConstants.Gen.EntityPropertyType.ID_COL_PROPERTY_TYPE)
    private int propertyTypeId;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR)
    private String propertyCode;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR)
    private String propertyDescription;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR)
    private String propertyType;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR)
    private String defaultValue;

    public GSEntityPropertyType()
    {
    }

    public int getPropertyTypeId()
    {
        return propertyTypeId;
    }

    public void setPropertyTypeId(int propertyTypeId)
    {
        this.propertyTypeId = propertyTypeId;
    }

    public int getEntityTypeId()
    {
        return entityTypeId;
    }

    public void setEntityTypeId(int entityTypeId)
    {
        this.entityTypeId = entityTypeId;
    }

    public String getPropertyCode()
    {
        return propertyCode;
    }

    public void setPropertyCode(String propertyCode)
    {
        this.propertyCode = propertyCode;
    }

    public String getPropertyDescription()
    {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription)
    {
        this.propertyDescription = propertyDescription;
    }

    public String getPropertyType()
    {
        return propertyType;
    }

    public void setPropertyType(String propertyType)
    {
        this.propertyType = propertyType;
    }

    public String getDefaultValue()
    {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue)
    {
        this.defaultValue = defaultValue;
    }

    public GSEntityType getEntityType()
    {
        return entityType;
    }

    public void setEntityType(GSEntityType entityType)
    {
        this.entityType = entityType;
    }
}
