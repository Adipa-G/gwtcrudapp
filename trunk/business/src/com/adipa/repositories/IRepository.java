package com.adipa.repositories;

import com.adipa.IDbTransaction;
import com.adipa.entities.IEntity;
import com.adipa.entities.IEntityKey;

import java.sql.Connection;
import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 28, 2011
 * Time: 9:31:36 PM
 */
public interface IRepository<T extends IEntity,U extends IEntityKey>
{
    Connection getConnection();

    void closeConnection(Connection con);

    void attachTransaction(IDbTransaction dbTransaction);

    void clearTransaction();

    T createNewInstance();

    Collection<T> getAll() throws DataRetrievalException;

    T getById(U key) throws DataRetrievalException;

    T persist(T t) throws DataPersistException, VersionMisMatchException;
}
