package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.DBColumnType;
import dbgate.ermanagement.*;

import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Mar 28, 2010
 * Time: 8:10:16 PM
 */

@DBTableInfo(tableName = DBConstants.Gen.EntityEntry.TABLE_NAME)
public class GSEntityEntry extends DefaultServerDBClass
{
    @DBColumnInfo(columnType = DBColumnType.INTEGER, key = true,columnName = DBConstants.Gen.EntityEntry.ID_COL_ENTITY
            ,readFromSequence = true,sequenceGeneratorClassName = DBConstants.Gen.EntityEntry.SEQ_GEN_CLASS_NAME)
    protected int entityId;

    @DBColumnInfo(columnType = DBColumnType.INTEGER,columnName = DBConstants.Gen.EntityEntry.COL_ENTITY_TYPE)
    private int entityTypeId;

    @ForeignKeyInfo(name = "fk_entity_entry2entity_type"
                    ,relatedObjectType = GSEntityType.class
                    ,reverseRelation = true
                    ,columnMappings =  {@ForeignKeyColumnMapping(fromField = "entityTypeId",toField = "entityTypeId")})
    private GSEntityType entityType;

    @ForeignKeyInfo(name = "fk_entity2entity_property"
            ,relatedObjectType = GSEntityProperty.class
            ,columnMappings = {@ForeignKeyColumnMapping(fromField = "entityId",toField = "entityId")}
            ,updateRule = ReferentialRuleType.RESTRICT
            ,deleteRule = ReferentialRuleType.CASCADE)
    private ArrayList<GSEntityProperty> properties;

    public GSEntityEntry()
    {
        properties = new ArrayList<GSEntityProperty>();
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

    public GSEntityType getEntityType()
    {
        return entityType;
    }

    public void setEntityType(GSEntityType entityType)
    {
        this.entityType = entityType;
    }

    public ArrayList<GSEntityProperty> getProperties()
    {
        return properties;
    }

    public void setProperties(ArrayList<GSEntityProperty> properties)
    {
        this.properties = properties;
    }
}
