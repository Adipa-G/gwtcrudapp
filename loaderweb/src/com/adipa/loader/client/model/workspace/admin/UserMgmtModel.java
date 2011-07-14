package com.adipa.loader.client.model.workspace.admin;

import com.adipa.loader.auth.client.IAuthenticationService;
import com.adipa.loader.auth.client.IAuthenticationServiceAsync;
import com.adipa.loader.client.controller.workspace.admin.UserMgmtController;
import com.adipa.loader.client.model.AbstractModel;
import com.adipa.loader.client.model.workspace.CRUDModel;
import com.adipa.loader.workspace.client.*;
import com.adipa.loader.workspace.client.user.IUserService;
import com.adipa.loader.workspace.client.user.IUserServiceAsync;
import com.adipa.loader.workspace.client.user.UserDto;
import com.adipa.loader.workspace.server.user.UserServiceImpl;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Date: Jul 13, 2011
 * Time: 12:27:47 PM
 */
public class UserMgmtModel extends CRUDModel<UserMgmtController,UserDto,CRUDSingleLongIndexDto>
{
    protected final IUserServiceAsync userService;

    public UserMgmtModel()
    {
       super();
       userService = GWT.create(IUserService.class);
    }

    @Override
    public ICRUDServiceAsync getService()
    {
        return userService;
    }

    @Override
    public CRUDSingleLongIndexDto getIndex(UserDto dto)
    {
        return new CRUDSingleLongIndexDto(dto.getUserId());
    }

    @Override
    public void setIndex(UserDto dto, CRUDSingleLongIndexDto index)
    {
        dto.setUserId(index.getIndex());
    }
}
