package com.adipa.repositories.auth;

import com.adipa.RandomData;
import com.adipa.entities.LongEntityKey;
import com.adipa.entities.auth.User;
import com.adipa.repositories.IRepository;
import com.adipa.repositories.RepositoryTestBase;
import com.adipa.repositories.auth.impl.UserRepository;
import junit.framework.Assert;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 29, 2011
 * Time: 5:20:33 PM
 */
public class UserRepositoryTestBase extends RepositoryTestBase<User, LongEntityKey>
{
    @Override
    public User getTestDataObject()
    {
        User entity = new User();
        entity.setUserName(RandomData.randomCode());
        entity.setPassword(RandomData.randomDescription());
        return entity;
    }

    @Override
    public IRepository<User, LongEntityKey> getTestRepository()
    {
        return new UserRepository();
    }

    @Override
    public void prepareToPersist(User iUser) throws Exception
    {
    }

    @Override
    public void executeRepositorySpecificTests()
    {
        getByUserName_byUserName_HavingMatchingUser_ShouldReturnOneResult();
    }

    private void getByUserName_byUserName_HavingMatchingUser_ShouldReturnOneResult()
    {
        String userName = RandomData.randomCode();
        String nonExistenceUserName = RandomData.randomCode();
        String password = RandomData.randomCode();
        int userId = RandomData.randomInt();

        IUserRepository repository = (IUserRepository) getTestRepository();
        User user = getTestDataObject();

        try
        {
            user.setUserId(userId);
            user.setUserName(userName);
            user.setPassword(password);
            repository.persist(user);

            User retrievedUser = repository.getByUserName(userName);
            Assert.assertNotNull(retrievedUser);
            User nonExistenceUser = repository.getByUserName(nonExistenceUserName);
            Assert.assertNull(nonExistenceUser);
        }
        catch (Exception e)
        {
            Assert.fail(e.getMessage());
            e.printStackTrace();
        }
    }
}