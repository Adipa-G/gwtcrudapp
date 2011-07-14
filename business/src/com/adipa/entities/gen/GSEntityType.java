package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.DBColumnType;
import dbgate.ermanagement.*;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Mar 28, 2010
 * Time: 8:37:18 PM
 */
@DBTableInfo(tableName = DBConstants.Gen.EntityType.TABLE_NAME)
public class GSEntityType extends DefaultServerDBClass
{
    @DBColumnInfo(columnType = DBColumnType.INTEGER, key = true,columnName = DBConstants.Gen.EntityType.ID_COL_ENTITY_TYPE
            ,readFromSequence = true,sequenceGeneratorClassName = DBConstants.Gen.EntityType.SEQ_GEN_CLASS_NAME)
    private int entityTypeId;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR)
    private String entityTypeCode;

    @ForeignKeyInfo(name = "fk_entity_type2entity_property"
            ,relatedObjectType = GSEntityPropertyType.class
            ,columnMappings = {@ForeignKeyColumnMapping(fromField = "entityTypeId",toField = "entityTypeId")}
            ,updateRule = ReferentialRuleType.RESTRICT
            ,deleteRule = ReferentialRuleType.CASCADE)
    private ArrayList<GSEntityPropertyType> propertyTypes;

    public GSEntityType()
    {
        propertyTypes = new ArrayList<GSEntityPropertyType>();
    }

    public int getEntityTypeId()
    {
        return entityTypeId;
    }

    public void setEntityTypeId(int entityTypeId)
    {
        this.entityTypeId = entityTypeId;
    }

    public String getEntityTypeCode()
    {
        return entityTypeCode;
    }

    public void setEntityTypeCode(String entityTypeCode)
    {
        this.entityTypeCode = entityTypeCode;
    }

    public ArrayList<GSEntityPropertyType> getPropertyTypes()
    {
        return propertyTypes;
    }

    public void setPropertyTypes(ArrayList<GSEntityPropertyType> propertyTypes)
    {
        this.propertyTypes = propertyTypes;
    }
}
