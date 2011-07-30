package com.adipa.loader.auth;

import com.adipa.RandomData;
import com.adipa.entities.IEntity;
import com.adipa.entities.auth.User;
import com.adipa.entities.gen.*;
import com.adipa.loader.auth.client.AuthRequestDto;
import com.adipa.loader.auth.client.AuthResponseDto;
import com.adipa.loader.auth.server.AuthenticationServiceImpl;
import com.adipa.loader.server.ServiceTestBase;
import com.adipa.loader.server.session.TestSessionToServletLink;
import com.adipa.repositories.DataPersistException;
import com.adipa.repositories.RepositoryTestBase;
import com.adipa.repositories.VersionMisMatchException;
import com.adipa.repositories.auth.impl.UserRepository;
import com.google.web.bindery.requestfactory.server.RequestFactoryServlet;
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
 * User: Adipa
 * Date: Jul 30, 2011
 * Time: 5:08:32 PM
 */
public class AuthenticationServiceTest extends ServiceTestBase
{
    static AuthenticationServiceImpl authenticationService;

    @Before
    public void beforeEach()
    {
        try
        {
            if (authenticationService == null)
            {
                authenticationService = new AuthenticationServiceImpl();
                authenticationService.initialize();
                authenticationService.setServletLink(new TestSessionToServletLink());
            }

            Connection con = dbConnector.getConnection();
            IEntity entity = new User();
            String tableName = DBClassAttributeExtractionUtils.getTableName(entity,entity.getClass());
            if (tableName != null)
            {
                PreparedStatement ps = con.prepareStatement(String.format("delete from %s",tableName));
                ps.execute();
            }
            con.close();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    public void logon_withNonExistingUser_shouldNotLogon()
    {
        AuthResponseDto responseDto = authenticationService.logon(new AuthRequestDto(RandomData.randomCode(),RandomData.randomCode()));
        Assert.assertFalse("Login possible with arbitrary credentials",responseDto.isLogonSuccess());
    }
    
    @Test
    public void logon_withExistingUser_shouldLogon()
    {
        User user = new User();
        user.setUserName(RandomData.randomCode());
        user.setPassword(RandomData.randomCode());

        UserRepository repository = new UserRepository();
        try
        {
            repository.persist(user);
        }
        catch (Exception e)
        {
            Assert.fail("Exception trying to persist the user");
        }

        AuthResponseDto responseDto = authenticationService.logon(new AuthRequestDto(user.getUserName(),user.getPassword()));
        Assert.assertTrue("Login impossible with proper credentials",responseDto.isLogonSuccess());
    }


}
