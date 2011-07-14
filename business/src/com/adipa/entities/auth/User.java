package com.adipa.entities.auth;

import com.adipa.entities.DBConstants;
import com.adipa.entities.GSEntity;
import com.adipa.entities.LongEntityKey;
import dbgate.DBColumnType;
import dbgate.ermanagement.*;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Mar 29, 2010
 * Time: 12:46:23 PM
 */


@DBTableInfo(tableName = DBConstants.Auth.User.TABLE_NAME)
public class User extends GSEntity<LongEntityKey>
{
    @DBColumnInfo(columnType = DBColumnType.INTEGER, key = true,columnName = DBConstants.Auth.User.ID_COL_USER,readFromSequence = true,sequenceGeneratorClassName = DBConstants.Auth.User.SEQ_GEN_CLASS_NAME)
    private int userId;

    @DBColumnInfo(columnType = DBColumnType.INTEGER)
    private int entityId;

    @DBColumnInfo(columnType = DBColumnType.INTEGER)
    private int version;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR,size = 100)
    private String userName;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR,size = 255)
    private String password;

    public User()
    {
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }

    public int getEntityId()
    {
        return entityId;
    }

    public void setEntityId(int entityId)
    {
        this.entityId = entityId;
    }

    @Override
    public void setVersion(int version)
    {
        this.version = version;
    }

    @Override
    public int getVersion()
    {
        return version;
    }

    @Override
    public LongEntityKey getId()
    {
        return new LongEntityKey(userId);
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
