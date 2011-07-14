package com.adipa.repositories;

import com.adipa.entities.IEntity;
import com.adipa.entities.gen.*;
import com.adipa.repositories.auth.UserRepositoryTestBase;
import dbgate.ServerDBClass;
import dbgate.ermanagement.impl.ERLayer;
import dbgate.ermanagement.impl.utils.DBClassAttributeExtractionUtils;
import dbgate.dbutility.DBConnector;
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
public class RepositoryTests
{
    static DBConnector dbConnector;
    
    @BeforeClass
    public static void before()
    {
        try
        {
            Logger.getLogger(RepositoryTests.class.getName()).info("Starting in-memory database for unit tests");
            dbConnector = new DBConnector("jdbc:derby:memory:unit-testing-repositoriestestbase;create=true","org.apache.derby.jdbc.EmbeddedDriver",DBConnector.DB_DERBY);

            Connection con = dbConnector.getConnection();
            Collection<ServerDBClass> dbClasses = new ArrayList<ServerDBClass>();
            Collection<RepositoryTestBase> repositoryTestBases = getTestBaseCollection();
            dbClasses.add(new GSEntityType());
            dbClasses.add(new GSEntityPropertyType());
            dbClasses.add(new GSEntityEntry());
            dbClasses.add(new GSEntityProperty());
            for (RepositoryTestBase repositoryTestBase : repositoryTestBases)
            {
                dbClasses.add(repositoryTestBase.getTestDataObject());
            }
            dbClasses.add(new Config());
            dbClasses.add(new Sequence());
            ERLayer.getSharedInstance().patchDataBase(con,dbClasses,true);
            con.commit();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            Logger.getLogger(RepositoryTests.class.getName()).severe("Exception during database startup.");
        }
    }

    private static Collection<RepositoryTestBase> getTestBaseCollection()
    {
        Collection<RepositoryTestBase> testBaseList = new ArrayList<RepositoryTestBase>();
        testBaseList.add(new UserRepositoryTestBase());
        return testBaseList;
    }

    @Before
    public void beforeEach()
    {
        try
        {
            Collection<RepositoryTestBase> repositoryTestsCollection = getTestBaseCollection();
            Connection con = dbConnector.getConnection();
            for (RepositoryTestBase repositoryTestBase : repositoryTestsCollection)
            {
                IEntity entity = repositoryTestBase.getTestDataObject();
                String tableName = DBClassAttributeExtractionUtils.getTableName(entity,entity.getClass());

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
    public void RepositoryTests_persistAndGetById_shouldPersistAndGetById()
    {
        try
        {
            Collection<RepositoryTestBase> repositoryTestBases = getTestBaseCollection();
            for (RepositoryTestBase repositoryTestBase : repositoryTestBases)
            {
                Logger.getLogger(RepositoryTests.class.getName()).info(String.format("persistAndGetById for %s", repositoryTestBase.getClass().getCanonicalName()));
                repositoryTestBase.persistAndGetById();
            }
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void RepositoryTests_persistAndGetAll_shouldPersistAndGetAll()
    {
        try
        {
            Collection<RepositoryTestBase> repositoryTestBases = getTestBaseCollection();
            for (RepositoryTestBase repositoryTestBase : repositoryTestBases)
            {
                Logger.getLogger(RepositoryTests.class.getName()).info(String.format("persistAndGetAll for %s", repositoryTestBase.getClass().getCanonicalName()));
                repositoryTestBase.persistAndGetAll();
            }
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void RepositoryTests_persistUpdateVersion_shouldUpdateVersionWhenPersist()
    {
        try
        {
            Collection<RepositoryTestBase> repositoryTestBases = getTestBaseCollection();
            for (RepositoryTestBase repositoryTestBase : repositoryTestBases)
            {
                Logger.getLogger(RepositoryTests.class.getName()).info(String.format("persistUpdateVersion for %s", repositoryTestBase.getClass().getCanonicalName()));
                repositoryTestBase.persistUpdateVersion();
            }
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void RepositoryTests_persistLessVersionUpdateFails_shouldThrowException()
    {
        try
        {
            Collection<RepositoryTestBase> repositoryTestBases = getTestBaseCollection();
            for (RepositoryTestBase repositoryTestBase : repositoryTestBases)
            {
                Logger.getLogger(RepositoryTests.class.getName()).info(String.format("persistLessVersionUpdateFails for %s", repositoryTestBase.getClass().getCanonicalName()));
                repositoryTestBase.persistLessVersionUpdateFails();
            }
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }

    @Test
    public void RepositoryTests_repoSpecificTests_shouldPass()
    {
        try
        {
            Collection<RepositoryTestBase> repositoryTestBases = getTestBaseCollection();
            for (RepositoryTestBase repositoryTestBase : repositoryTestBases)
            {
                Logger.getLogger(RepositoryTests.class.getName()).info(String.format("RepositoryTests for %s", repositoryTestBase.getClass().getCanonicalName()));
                repositoryTestBase.executeRepositorySpecificTests();
            }
        }
        catch (Exception e)
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
        Logger.getLogger(RepositoryTests.class.getName()).info("Stopping in-memory database.");
        try
        {
            DriverManager.getConnection("jdbc:derby:memory:unit-testing-repositoriestestbase;shutdown=true").close();
        }
        catch (SQLException ex)
        {
            if (ex.getErrorCode() != 45000)
            {
                ex.printStackTrace();
            }
        }
        try
        {
            VFMemoryStorageFactory.purgeDatabase(new File("unit-testing-repositoriestestbase").getCanonicalPath());
        }
        catch (IOException iox)
        {
            iox.printStackTrace();
        }
    }
}