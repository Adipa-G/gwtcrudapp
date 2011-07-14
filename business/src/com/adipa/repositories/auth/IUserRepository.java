package com.adipa.repositories.auth;

import com.adipa.entities.LongEntityKey;
import com.adipa.entities.auth.User;
import com.adipa.repositories.DataRetrievalException;
import com.adipa.repositories.IRepository;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 29, 2011
 * Time: 11:43:32 AM
 */
public interface IUserRepository extends IRepository<User, LongEntityKey>
{
    User getByUserName(String userName) throws DataRetrievalException;
}