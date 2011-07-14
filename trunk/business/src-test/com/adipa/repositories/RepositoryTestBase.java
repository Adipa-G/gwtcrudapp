package com.adipa.repositories;

import com.adipa.entities.IEntity;
import com.adipa.entities.IEntityKey;
import dbgate.DBClassStatus;
import org.junit.Assert;

import java.util.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: aga
 * Date: Nov 7, 2010
 * Time: 9:25:11 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class RepositoryTestBase<T extends IEntity,U extends IEntityKey>
{
    public abstract T getTestDataObject();

    public abstract IRepository<T,U> getTestRepository();

    public abstract void executeRepositorySpecificTests();

    public abstract void prepareToPersist(T t) throws Exception;

    public void persistAndGetById()  throws Exception
    {
        T t = getTestDataObject();
        try
        {
            prepareToPersist(t);
            getTestRepository().persist(t);
            T loadedT = getTestRepository().getById((U) t.getId());
            Assert.assertNotNull(String.format("Type %s persisting failed",t.getClass().getName()),loadedT);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public void persistAndGetAll()   throws Exception
    {
        T t = getTestDataObject();
        try
        {
            prepareToPersist(t);
            getTestRepository().persist(t);
            Collection<T> loadedTs = getTestRepository().getAll();
            Assert.assertTrue(String.format("Type %s persisting failed",t.getClass().getName()),loadedTs.size() > 0);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }

    public void persistUpdateVersion()   throws Exception
    {
        T t = getTestDataObject();
        try
        {
            prepareToPersist(t);
            getTestRepository().persist(t);
            T loadedT = getTestRepository().getById((U) t.getId());
            T loadedTClone = getTestRepository().getById((U) t.getId());

            loadedT.setStatus(DBClassStatus.MODIFIED);
            getTestRepository().persist(loadedT);
            T loaded2ndTimeT = getTestRepository().getById((U) t.getId());

            Assert.assertTrue(String.format("Version update failed %s",t.getClass().getName()),loaded2ndTimeT.getVersion() > loadedTClone.getVersion());
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }  
    }

    public void persistLessVersionUpdateFails()   throws Exception
    {
        T t = getTestDataObject();
        try
        {
            prepareToPersist(t);
            getTestRepository().persist(t);
            T loadedT = getTestRepository().getById((U) t.getId());
            T loadedTClone = getTestRepository().getById((U) t.getId());
            loadedT.setStatus(DBClassStatus.MODIFIED);
            loadedTClone.setStatus(DBClassStatus.MODIFIED);
            getTestRepository().persist(loadedT);

            getTestRepository().persist(loadedTClone);
            Assert.assertTrue(String.format("Version restriction does not works %s",t.getClass().getName()),false);
        }
        catch (VersionMisMatchException e)
        {
            Assert.assertTrue(String.format("Version restriction does works %s",t.getClass().getName()),true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw e;
        }
    }
}