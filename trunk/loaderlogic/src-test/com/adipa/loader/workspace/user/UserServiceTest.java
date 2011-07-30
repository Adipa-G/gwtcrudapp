package com.adipa.loader.workspace.user;

import com.adipa.RandomData;
import com.adipa.entities.IEntity;
import com.adipa.entities.LongEntityKey;
import com.adipa.entities.auth.User;
import com.adipa.loader.workspace.CRUDServiceTestBase;
import com.adipa.loader.workspace.client.*;
import com.adipa.loader.workspace.client.user.UserDto;
import com.adipa.loader.workspace.server.CRUDServiceImpl;
import com.adipa.loader.workspace.server.user.UserServiceImpl;
import dbgate.ermanagement.impl.utils.DBClassAttributeExtractionUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 30, 2011
 * Time: 7:34:20 PM
 */
public class UserServiceTest extends CRUDServiceTestBase<User, LongEntityKey, UserDto, CRUDSingleLongIndexDto>
{
    @Override
    public CRUDServiceImpl<User, LongEntityKey, UserDto, CRUDSingleLongIndexDto> getService()
    {
        return new UserServiceImpl();
    }

    @Override
    public CRUDCreateRequestDto getCreateRequestDto() throws Exception
    {
        UserDto userDto = new UserDto();
        userDto.setUserName(RandomData.randomCode());
        userDto.setPassword(RandomData.randomCode());
        CRUDCreateRequestDto createRequestDto = new CRUDCreateRequestDto();
        createRequestDto.setObjectDto(userDto);
        return createRequestDto;
    }

    @Override
    public CRUDUpdateRequestDto getUpdateRequestDto() throws Exception
    {
        CRUDCreateRequestDto requestDto = getCreateRequestDto();
        CRUDCreateResponseDto responseDto = crudService.create(requestDto);
        if (responseDto.isSuccess())
        {
            CRUDUpdateRequestDto updateRequestDto = new CRUDUpdateRequestDto();

            UserDto updatedUserDto = new UserDto();
            updatedUserDto.setUserName(RandomData.randomCode());
            updatedUserDto.setPassword(RandomData.randomCode());
            updateRequestDto.setIndexDto(responseDto.getIndexDto());
            updateRequestDto.setObjectDto(updatedUserDto);

            return updateRequestDto;
        }
        throw new Exception("Cannot create update request");
    }

    @Override
    public CRUDDeleteRequestDto getDeleteRequestDto() throws Exception
    {
        CRUDCreateRequestDto requestDto = getCreateRequestDto();
        CRUDCreateResponseDto responseDto = crudService.create(requestDto);
        if (responseDto.isSuccess())
        {
            CRUDDeleteRequestDto deleteRequestDto = new CRUDDeleteRequestDto();

            deleteRequestDto.setIndexDto(responseDto.getIndexDto());
            return deleteRequestDto;
        }
        throw new Exception("Cannot create update request");
    }

    @Override
    public CRUDListRequestDto getListRequestDto() throws Exception
    {
        CRUDCreateRequestDto requestDtoA = getCreateRequestDto();
        CRUDCreateResponseDto responseDtoA = crudService.create(requestDtoA);

        CRUDCreateRequestDto requestDtoB = getCreateRequestDto();
        CRUDCreateResponseDto responseDtoB = crudService.create(requestDtoB);

        if (responseDtoA.isSuccess() && responseDtoB.isSuccess())
        {
            CRUDListRequestDto listRequestDto = new CRUDListRequestDto();
            return listRequestDto;
        }
        throw new Exception("Cannot create update request");
    }

    @Override
    public void afterEach()
    {
        try
        {
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
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
