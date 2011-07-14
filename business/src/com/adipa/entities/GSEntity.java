package com.adipa.entities;



import com.adipa.entities.gen.GSEntityEntry;
import com.adipa.entities.gen.GSEntityProperty;
import dbgate.DBColumnType;
import dbgate.dbutility.DBMgmtUtility;
import dbgate.ermanagement.exceptions.PersistException;
import dbgate.ermanagement.exceptions.RetrievalException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Mar 28, 2010
 * Time: 9:32:37 PM
 */
public abstract class GSEntity<T extends IEntityKey> extends AbstractEntity<T>
{
    private GSEntityEntry entityEntry;

    protected GSEntity()
    {
        entityEntry = new GSEntityEntry();
    }

    public abstract int getEntityId();

    public abstract void setEntityId(int entityId);

    public GSEntityEntry getEntityEntry()
    {
        return entityEntry;
    }

    public void setEntityEntry(GSEntityEntry entityEntry)
    {
        this.entityEntry = entityEntry;
    }

    @Override
    public void persist(Connection con) throws PersistException
    {
        if (entityEntry != null)
        {
            entityEntry.setStatus(getStatus());
            entityEntry.persist(con);

            setEntityId(entityEntry.getEntityId());
        }
        super.persist(con);
    }

    public void retrieve(ResultSet rs, Connection con) throws RetrievalException
    {
        super.retrieve(rs,con);
        {
            int count = 1;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;
            try
            {
                preparedStatement = con.prepareStatement(String.format("select * from %s where %s = ?"
                        , DBConstants.Gen.EntityEntry.TABLE_NAME,DBConstants.Gen.EntityEntry.ID_COL_ENTITY));
                preparedStatement.setInt(count++, this.getEntityId());
                resultSet = preparedStatement.executeQuery();
                if (resultSet.next())
                {
                    entityEntry = new GSEntityEntry();
                    entityEntry.retrieve(resultSet, con);
                }
            }
            catch (Exception ex)
            {
                throw new RetrievalException(ex.getMessage(),ex);
            }
            finally
            {
                DBMgmtUtility.close(resultSet);
                DBMgmtUtility.close(preparedStatement);
            }
        }
    }

    public Object readProperty(String propertyCode)
    {
        if (entityEntry != null)
        {
            for (GSEntityProperty property : entityEntry.getProperties())
            {
                if (property.getEntityPropertyType().getPropertyCode().equals(propertyCode))
                {
                    DBColumnType columnType = DBColumnType.valueOf(property.getEntityPropertyType().getPropertyType());
                    return convertToType(columnType,property.getPropertyValue());
                }
            }
        }
        return null;
    }

    public void writeProperty(String propertyCode,Object propertyValue)
    {
        if (entityEntry != null)
        {
            for (GSEntityProperty property : entityEntry.getProperties())
            {
                if (property.getEntityPropertyType().getPropertyCode().equals(propertyCode))
                {
                    DBColumnType columnType = DBColumnType.valueOf(property.getEntityPropertyType().getPropertyType());
                    String valStr = convertToString(columnType,propertyValue);
                    property._modify();
                    property.setPropertyValue(valStr);
                }
            }
        }
    }

    private static Object convertToType(DBColumnType typeId,String value)
    {
        if (value == null || value.length() == 0)
        {
            return null;
        }
        switch (typeId)
        {
            case BOOLEAN:
                return Boolean.parseBoolean(value);
            case CHAR:
                return value.toCharArray()[0];
            case DATE:
                //todo use simple date formatter to parse
                return null;
            case DOUBLE:
                return Double.parseDouble(value);
            case FLOAT:
                return Float.parseFloat(value);
            case INTEGER:
                return Integer.parseInt(value);
            case LONG:
                return Long.parseLong(value);
            case TIMESTAMP:
                //todo use simple date formatter to parse
                return null;
            case VARCHAR:
            default:
                return value;
        }
    }

    private static String convertToString(DBColumnType typeId,Object value)
    {
        if (value == null)
        {
            return null;
        }
        switch (typeId)
        {
            case BOOLEAN:
                return Boolean.toString((Boolean) value);
            case CHAR:
                return Character.toString((Character)value);
            case DATE:
                //todo use simple date formatter to string
                return "";
            case DOUBLE:
                return Double.toString((Double)value);
            case FLOAT:
                return Float.toString((Float)value);
            case INTEGER:
                return Integer.toString((Integer)value);
            case LONG:
                return Long.toString((Long)value);
            case TIMESTAMP:
                //todo use simple date formatter to string
                return "";
            case VARCHAR:
            default:
                return value.toString();
        }
    }
}
