package com.adipa.entities;

import com.adipa.entities.auth.UserTestBase;
import com.adipa.entities.gen.*;
import dbgate.ServerDBClass;
import dbgate.dbutility.DBConnector;
import dbgate.ermanagement.impl.ERLayer;
import dbgate.ermanagement.impl.utils.DBClassAttributeExtractionUtils;
import org.apache.derby.impl.io.VFMemoryStorageFactory;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * User: aga
 * Date: Nov 6, 2010
 * Time: 10:53:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class DBClassesTest
{
    @BeforeClass
    public static void before()
    {
        try
        {
            Logger.getLogger(DBClassesTest.class.getName()).info("Starting in-memory database for unit tests");
            DBConnector dbConnector = new DBConnector("jdbc:derby:memory:unit-testing-dbclassestestbase;create=true","org.apache.derby.jdbc.EmbeddedDriver", DBConnector.DB_DERBY);
            Connection con = dbConnector.getConnection();

            Collection<ServerDBClass> dbClasses = new ArrayList<ServerDBClass>();
            Collection<DbClassTestBase> dbClassTestBases = getTestBaseCollection();
            for (DbClassTestBase dbClassTestBase : dbClassTestBases)
            {
                dbClasses.add((ServerDBClass) dbClassTestBase.getClassToTest().newInstance());
            }
            dbClasses.add(new Config());
            dbClasses.add(new Sequence());
            ERLayer.getSharedInstance().patchDataBase(con,dbClasses,true);
            con.commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Logger.getLogger(DBClassesTest.class.getName()).severe("Exception during database startup.");
        }
    }

    private static Collection<DbClassTestBase> getTestBaseCollection()
    {
        Collection<DbClassTestBase> testBaseList = new ArrayList<DbClassTestBase>();
        testBaseList.add(new GSEntityPropertyTestBase());
        testBaseList.add(new GSEntityEntryTestBase());
        testBaseList.add(new GSEntityPropertyTypeTestBase());
        testBaseList.add(new GSEntityTypeTestBase());
        testBaseList.add(new UserTestBase());

        return testBaseList;
    }

    @Before
    public void beforeEach()
    {
        try
        {
            Collection<DbClassTestBase> dbClassTestBases = getTestBaseCollection();
            Connection con = DBConnector.getSharedInstance().getConnection();
            for (DbClassTestBase dbClassTestBase : dbClassTestBases)
            {
                ServerDBClass serverDBClass = dbClassTestBase.getTestDataObject();
                String tableName = DBClassAttributeExtractionUtils.getTableName(serverDBClass,dbClassTestBase.getClassToTest());

                if (tableName != null)
                {
                    PreparedStatement ps = con.prepareStatement(String.format("delete from %s",tableName));
                    ps.execute();
                }
            }
            con.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    public void DBConnector_insert_shouldInsertAllCalsses()
    {
        try
        {
            Collection<DbClassTestBase> dbClassTestBases = getTestBaseCollection();
            for (DbClassTestBase dbClassTestBase : dbClassTestBases)
            {
                ServerDBClass startServerDBClass = dbClassTestBase.getTestDataObject();
                ServerDBClass toBeInsertedServerDBClass = dbClassTestBase.getTestDataObject();

                Connection connection = DBConnector.getSharedInstance().getConnection();
                dbClassTestBase.insert(toBeInsertedServerDBClass,connection);
                connection.commit();
                connection.close();

                connection = DBConnector.getSharedInstance().getConnection();
                ServerDBClass loadedDBClass = dbClassTestBase.load(connection,toBeInsertedServerDBClass);
                connection.close();

                Assert.assertTrue(String.format("The inserted object is not the same as loaded object for class %s",dbClassTestBase.getClassToTest().getCanonicalName()),dbClassTestBase.verify(startServerDBClass,loadedDBClass));
            }
        }
        catch (SQLException e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void DBConnector_update_shouldUpdateAllCalsses()
    {
        try
        {
            Collection<DbClassTestBase> dbClassTestBases = getTestBaseCollection();
            for (DbClassTestBase dbClassTestBase : dbClassTestBases)
            {
                ServerDBClass startServerDBClass = dbClassTestBase.getTestDataObject();
                dbClassTestBase.updateTestDataObject(startServerDBClass);

                ServerDBClass toBeUpdatedDBClass = dbClassTestBase.getTestDataObject();
                Connection connection = DBConnector.getSharedInstance().getConnection();
                dbClassTestBase.insert(toBeUpdatedDBClass,connection);
                connection.commit();
                connection.close();

                connection = DBConnector.getSharedInstance().getConnection();
                toBeUpdatedDBClass = dbClassTestBase.load(connection,toBeUpdatedDBClass);
                connection.close();

                connection = DBConnector.getSharedInstance().getConnection();
                dbClassTestBase.updateTestDataObject(toBeUpdatedDBClass);
                dbClassTestBase.update(toBeUpdatedDBClass,connection);
                connection.commit();
                connection.close();

                connection = DBConnector.getSharedInstance().getConnection();
                ServerDBClass loadedDBClass = dbClassTestBase.load(connection,toBeUpdatedDBClass);
                connection.close();

                Assert.assertTrue(String.format("The updated object is not the same as loaded object for class %s",dbClassTestBase.getClassToTest().getCanonicalName()),dbClassTestBase.verify(startServerDBClass,loadedDBClass));
            }
        }
        catch (SQLException e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void DBConnector_delete_shouldDeleteAllCalsses()
    {
        try
        {
            Collection<DbClassTestBase> dbClassTestBases = getTestBaseCollection();
            for (DbClassTestBase dbClassTestBase : dbClassTestBases)
            {
                ServerDBClass startServerDBClass = dbClassTestBase.getTestDataObject();
                dbClassTestBase.updateTestDataObject(startServerDBClass);

                ServerDBClass toBeUpdatedDBClass = dbClassTestBase.getTestDataObject();
                Connection connection = DBConnector.getSharedInstance().getConnection();
                dbClassTestBase.insert(toBeUpdatedDBClass,connection);
                connection.commit();
                connection.close();

                connection = DBConnector.getSharedInstance().getConnection();
                dbClassTestBase.delete(toBeUpdatedDBClass,connection);
                connection.commit();
                connection.close();

                connection = DBConnector.getSharedInstance().getConnection();
                ServerDBClass loadedDBClass = dbClassTestBase.load(connection,toBeUpdatedDBClass);
                connection.close();

                Assert.assertNull(String.format("The object has not deleted for class %s",dbClassTestBase.getClassToTest().getCanonicalName()),loadedDBClass);
            }
        }
        catch (SQLException e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @After
    public void afterEach()
    {
    }

    @AfterClass
    public static void after()
    {
        Logger.getLogger(DBClassesTest.class.getName()).info("Stopping in-memory database.");
        try
        {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-dbclassestestbase;shutdown=true").close();
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
