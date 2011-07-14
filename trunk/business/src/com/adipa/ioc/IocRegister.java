package com.adipa.ioc;

import com.adipa.repositories.auth.impl.UserRepository;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

/**
 * Date: May 27, 2011
 * Time: 10:55:42 PM
 */
public class IocRegister
{
    private static IocRegister instance;
    private MutablePicoContainer picoContainer;

    private IocRegister()
    {
        picoContainer = new DefaultPicoContainer();

        picoContainer.addComponent(UserRepository.class);
    }

    public <T> T getComponent(java.lang.Class<T> tClass)
    {
        return picoContainer.getComponent(tClass);
    }

    public static IocRegister getInstance()
    {
        if (instance == null)
        {
            instance = new IocRegister();
        }
        return instance;
    }
}
