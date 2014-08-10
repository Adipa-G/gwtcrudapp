package com.adipa.loader.server;

import com.adipa.entities.gen.*;
import com.adipa.install.InstallUtil;
import com.adipa.loader.auth.server.AuthenticationServiceImpl;
import com.adipa.repositories.RepositoryTestBase;
import dbgate.ServerDBClass;
import dbgate.dbutility.DBConnector;
import dbgate.ermanagement.impl.ERLayer;
import org.apache.derby.impl.io.VFMemoryStorageFactory;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 30, 2011
 * Time: 6:18:55 PM
 */
public class ServiceTestBase
{
    protected static DBConnector dbConnector;

    @BeforeClass
    public static void before()
    {
        try
        {
            Logger.getLogger(ServiceTestBase.class.getName()).info("Starting in-memory database for unit tests");
            dbConnector = new DBConnector("jdbc:derby:memory:unit-testing-rpc-services;create=true","org.apache.derby.jdbc.EmbeddedDriver",DBConnector.DB_DERBY);

            Connection con = dbConnector.getConnection();
            InstallUtil.patchDB(con);
            con.commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Logger.getLogger(ServiceTestBase.class.getName()).severe("Exception during database startup.");
        }
    }

    @AfterClass
    public static void after()
    {
        Logger.getLogger(ServiceTestBase.class.getName()).info("Stopping in-memory database.");
        try
        {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-rpc-services;shutdown=true").close();
        }
        catch (SQLException ex)
        {
            if (ex.getErrorCode() != 45000)
            {
                ex.printStackTrace();
            }
        }
    }
}
