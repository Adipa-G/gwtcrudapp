package com.adipa.loader.workspace.server.user;

import com.adipa.entities.LongEntityKey;
import com.adipa.entities.auth.User;
import com.adipa.ioc.IocRegister;
import com.adipa.loader.auth.client.AuthResponseDto;
import com.adipa.loader.server.AbstractService;
import com.adipa.loader.workspace.client.CRUDSingleLongIndexDto;
import com.adipa.loader.workspace.client.ICRUDIndexDto;
import com.adipa.loader.workspace.client.ICRUDObjectDto;
import com.adipa.loader.workspace.client.user.*;
import com.adipa.loader.workspace.server.CRUDServiceImpl;
import com.adipa.loader.workspace.shared.user.UserVerifier;
import com.adipa.repositories.DataPersistException;
import com.adipa.repositories.DataRetrievalException;
import com.adipa.repositories.IRepository;
import com.adipa.repositories.VersionMisMatchException;
import com.adipa.repositories.auth.IUserRepository;

/**
 * Date: Jul 13, 2011
 * Time: 12:57:30 PM
 */
public class UserServiceImpl extends CRUDServiceImpl<User, LongEntityKey,UserDto, CRUDSingleLongIndexDto> implements IUserService
{
    @Override
    public LongEntityKey getKey(CRUDSingleLongIndexDto indexDto)
    {
        return new LongEntityKey(indexDto.getIndex());
    }

    @Override
    public CRUDSingleLongIndexDto getIndexNo(User entity)
    {
        return new CRUDSingleLongIndexDto(entity.getUserId());
    }

    @Override
    public IRepository<User, LongEntityKey> getRepository()
    {
        return IocRegister.getInstance().getComponent(IUserRepository.class);
    }

    @Override
    public User createNewInstance()
    {
        return new User();
    }

    @Override
    public UserDto mapDto(User entity)
    {
        UserDto dto = new UserDto();
        dto.setUserName(entity.getUserName());
        dto.setPassword(entity.getPassword());
        dto.setUserId(entity.getUserId());
        return dto;
    }

    @Override
    public void updateEntity(User entity, UserDto objectDto)
    {
        entity.setUserName(objectDto.getUserName());
        entity.setPassword(objectDto.getPassword());
    }

    @Override
    public void validate(UserDto objectDto) throws IllegalArgumentException
    {
        if (!UserVerifier.isValidUserName(objectDto.getUserName()))
        {
            throw new IllegalArgumentException("Invalid user name");
        }
        if (!UserVerifier.isValidPassword(objectDto.getPassword()))
        {
            throw new IllegalArgumentException("Invalid password");
        }
    }
}
