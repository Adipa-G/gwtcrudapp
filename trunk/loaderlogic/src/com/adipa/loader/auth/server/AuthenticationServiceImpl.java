package com.adipa.loader.auth.server;

import com.adipa.entities.auth.User;
import com.adipa.loader.auth.client.*;
import com.adipa.loader.auth.shared.LogonVerifier;
import com.adipa.loader.server.AbstractService;
import com.adipa.loader.server.exceptions.auth.UserNotLoggedInException;
import com.adipa.loader.server.session.SessionKeys;
import com.adipa.loader.server.session.SessionRoot;
import com.adipa.repositories.DataRetrievalException;
import com.adipa.repositories.auth.IUserRepository;
import com.adipa.ioc.IocRegister;

/**
 * Date: May 27, 2011
 * Time: 10:48:41 PM
 */
public class AuthenticationServiceImpl extends AbstractService implements IAuthenticationService
{
    @Override
    public void checkIfAuthenticated() throws UserNotLoggedInException
    {
        //nothing to authentication as this is authentication service
    }

    @Override
    public AuthResponseDto logon(AuthRequestDto requestDto) throws IllegalArgumentException
    {
        if (!(LogonVerifier.isValid(requestDto.getUserName())
              && LogonVerifier.isValid(requestDto.getPassword())))
        {
            throw new IllegalArgumentException("User name/Password cannot be empty");
        }

        IUserRepository userRepository = IocRegister.getInstance().getComponent(IUserRepository.class);
        try
        {
            User user =  userRepository.getByUserName(requestDto.getUserName());
            if (user != null && user.getPassword().equals(requestDto.getPassword()))
            {
                javax.servlet.http.HttpServletRequest request = this.getThreadLocalRequest();
                SessionRoot sessionRoot = new SessionRoot();
                sessionRoot.setUserName(user.getUserName());
                request.getSession().setAttribute(SessionKeys.SESSION_ROOT,sessionRoot);

                log.info(String.format("logon successful for user %s",requestDto.getUserName()));
                return new AuthResponseDto(true,"Logon success");
            }
            log.info(String.format("logon failed for user %s",requestDto.getUserName()));
            return new AuthResponseDto(false,"Logon failed");
        }
        catch (DataRetrievalException e)
        {
            handleException(e,e.getMessage());
            return new AuthResponseDto(false,"Logon failed");
        }
    }

    @Override
    public void setSessionInfo(SetSessionInfoDto sessionInfoDto) throws IllegalArgumentException
    {
        javax.servlet.http.HttpServletRequest request = this.getThreadLocalRequest();
        SessionRoot sessionRoot = (SessionRoot)request.getSession().getAttribute(SessionKeys.SESSION_ROOT);
        if (sessionRoot == null)
        {
            return;
        }
        if (sessionInfoDto.isUpdateCurrentView())
        {
            sessionRoot.setCurrentViewType(sessionInfoDto.getCurrentView());
        }
    }

    @Override
    public GetSessionInfoDto getSessionInfo() throws IllegalArgumentException
    {
        javax.servlet.http.HttpServletRequest request = this.getThreadLocalRequest();
        SessionRoot sessionRoot = (SessionRoot)request.getSession().getAttribute(SessionKeys.SESSION_ROOT);

        GetSessionInfoDto getSessionInfoDto = new GetSessionInfoDto();
        if (sessionRoot == null
            || sessionRoot.getUserName() == null
                || sessionRoot.getUserName().length() == 0)
        {
            log.info("invalid session");
            getSessionInfoDto.setValidSession(false);
            return getSessionInfoDto;
        }

        getSessionInfoDto.setValidSession(true);
        getSessionInfoDto.setCurrentView(sessionRoot.getCurrentViewType().toString());
        getSessionInfoDto.setUserName(sessionRoot.getUserName());

        return getSessionInfoDto;
    }
}
