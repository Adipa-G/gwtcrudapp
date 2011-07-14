package com.adipa.loader.server;

import com.adipa.loader.server.exceptions.auth.UserNotLoggedInException;
import com.adipa.loader.server.session.SessionKeys;
import com.adipa.loader.server.session.SessionRoot;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import dbgate.dbutility.DBConnector;
import dbgate.dbutility.DBMgmtUtility;

import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Date: May 31, 2011
 * Time: 9:43:56 PM
 */
public abstract class AbstractService extends RemoteServiceServlet implements IService
{
    protected Logger log;

    @Override
    public String processCall(String payload) throws SerializationException
    {
        initialize();
        return super.processCall(payload);
    }

    @Override
    public void initialize()
    {
        try
        {
            log = Logger.getLogger(getClass().getName());
            checkIfAuthenticated();
        }
        catch (UserNotLoggedInException e)
        {
            handleException(e);
            return;
        }
        connectToDB();
    }

    private void connectToDB()
    {
        try
        {
            if (DBConnector.getSharedInstance() != null)
            {
                return;
            }
            File config = new File(ConfigConstants.CONFIG_FILE_PATH);
            System.out.println("=================" + config.getAbsolutePath());

            Properties properties = new Properties();
            properties.load(new FileReader(config));

            String dbIp = properties.getProperty(ConfigConstants.DATABASE_IP);
            String dbPort = properties.getProperty(ConfigConstants.DATABASE_PORT);
            String dbName = properties.getProperty(ConfigConstants.DATABASE_NAME);
            String dbUser = properties.getProperty(ConfigConstants.DATABASE_USER);
            String dbPass = properties.getProperty(ConfigConstants.DATABASE_PASS);

            String connectionString = String.format("jdbc:mysql://%s:%s/%s?user=%s&password=%s", dbIp, dbPort, dbName, dbUser, dbPass);
            new DBConnector(connectionString,"com.mysql.jdbc.Driver",DBConnector.DB_MYSQL);

            Connection con = DBConnector.getSharedInstance().getConnection();
            //todo InstallUtil.patchDB(con);
            DBMgmtUtility.close(con);
        }
        catch (Exception ex)
        {
            handleException(ex);
        }
    }

    @Override
    public void handleException(Exception ex,String... messages)
    {
        StringBuilder sb = new StringBuilder();
        if (messages != null && messages.length > 0)
        {
            for (String message : messages)
            {
                sb.append(message);
                sb.append("\n\r");
            }
        }
        else
        {
            sb.append(ex.getMessage());
        }
        Logger.getLogger(getClass().getName()).log(Level.SEVERE,sb.toString(),ex);
    }

    @Override
    public void checkIfAuthenticated() throws UserNotLoggedInException
    {
        javax.servlet.http.HttpServletRequest request = this.getThreadLocalRequest();
        SessionRoot sessionRoot = (SessionRoot)request.getSession().getAttribute(SessionKeys.SESSION_ROOT);
        if (sessionRoot == null
            || sessionRoot.getUserName() == null
                || sessionRoot.getUserName().length() == 0)
        {
            throw new UserNotLoggedInException("No user logged in for current session");
        }
    }
}
