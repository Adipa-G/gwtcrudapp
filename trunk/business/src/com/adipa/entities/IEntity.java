package com.adipa.entities;

import dbgate.ServerDBClass;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 28, 2011
 * Time: 9:31:57 PM
 */
public interface IEntity<T extends IEntityKey> extends ServerDBClass
{
    T getId();

    int getVersion();

    void updateVersion();

    void modify();

    void delete();
}
