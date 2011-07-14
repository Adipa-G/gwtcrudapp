package com.adipa.entities;

import dbgate.DBClassStatus;
import dbgate.ermanagement.DefaultServerDBClass;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 29, 2011
 * Time: 8:37:39 AM
 */
public abstract class AbstractEntity<T extends IEntityKey> extends DefaultServerDBClass implements IEntity<T>
{
    public abstract void setVersion(int version);

    public abstract int getVersion();

    public abstract T getId();

    @Override
    public void modify()
    {
        if (getStatus() == DBClassStatus.UNMODIFIED)
        {
            setStatus(DBClassStatus.MODIFIED);
        }
    }

    @Override
    public void delete()
    {
        if (getStatus() == DBClassStatus.UNMODIFIED
                || getStatus() ==  DBClassStatus.MODIFIED)
        {
            setStatus(DBClassStatus.DELETED);
        }
    }

    @Override
    public void updateVersion()
    {
        int version = getVersion();
        setVersion(++version);
    }
}
