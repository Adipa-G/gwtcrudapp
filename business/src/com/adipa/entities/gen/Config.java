package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.DBColumnType;
import dbgate.ermanagement.DBColumnInfo;
import dbgate.ermanagement.DBTableInfo;
import dbgate.ermanagement.DefaultServerDBClass;

/**
 * Created by IntelliJ IDEA.
 * User: aga
 * Date: Oct 30, 2010
 * Time: 4:34:35 PM
 * To change this template use File | Settings | File Templates.
 */
@DBTableInfo(tableName = DBConstants.Gen.Config.TABLE_NAME)
public class Config extends DefaultServerDBClass
{
    @DBColumnInfo(columnType = DBColumnType.VARCHAR,columnName = DBConstants.Gen.Config.COL_CONFIG_NAME, size = 255)
    private String configName;

    @DBColumnInfo(columnType = DBColumnType.VARCHAR,columnName = DBConstants.Gen.Config.COL_CONFIG_VALUE, size = 255)
    private String configValue;

    public String getConfigName()
    {
        return configName;
    }

    public void setConfigName(String configName)
    {
        this.configName = configName;
    }

    public String getConfigValue()
    {
        return configValue;
    }

    public void setConfigValue(String configValue)
    {
        this.configValue = configValue;
    }
}
