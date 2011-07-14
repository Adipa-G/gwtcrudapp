package com.adipa.loader.server.install;

import com.adipa.DbTransaction;
import com.adipa.DbTransactionException;
import com.adipa.entities.auth.User;
import com.adipa.entities.gen.*;
import com.adipa.repositories.DataPersistException;
import com.adipa.repositories.DataRetrievalException;
import com.adipa.repositories.VersionMisMatchException;
import com.adipa.repositories.auth.impl.UserRepository;
import dbgate.ServerDBClass;
import dbgate.ermanagement.exceptions.DBPatchingException;
import dbgate.ermanagement.impl.ERLayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Date: May 31, 2011
 * Time: 11:00:24 PM
 */
public class InstallUtil
{
    public static void patchDB(Connection con) throws DBPatchingException, SQLException
    {
        Collection<ServerDBClass> testBaseList = new ArrayList<ServerDBClass>();

        testBaseList.add(new Config());
        testBaseList.add(new Sequence());
        testBaseList.add(new GSEntityType());
        testBaseList.add(new GSEntityPropertyType());
        testBaseList.add(new GSEntityEntry());
        testBaseList.add(new GSEntityProperty());

        testBaseList.add(new User());
        ERLayer.getSharedInstance().patchDataBase(con,testBaseList,false);

        try
        {
            setupDefaultUserIfNotExists(con);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void setupDefaultUserIfNotExists(Connection con) throws DataRetrievalException
            , DbTransactionException, DataPersistException, VersionMisMatchException
    {
        UserRepository userRepository = new UserRepository();
        if (userRepository.getAll().size() > 0)
        {
            return;
        }

        DbTransaction dbTransaction = new DbTransaction();
        dbTransaction.begin();
        userRepository.attachTransaction(dbTransaction);

        User user = new User();
        user.setUserName("root");
        user.setPassword("asd");
        userRepository.persist(user);

        dbTransaction.commit();
    }
}
