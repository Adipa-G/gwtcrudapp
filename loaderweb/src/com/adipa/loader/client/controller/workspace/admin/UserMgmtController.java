package com.adipa.loader.client.controller.workspace.admin;

import com.adipa.loader.client.controller.AbstractController;
import com.adipa.loader.client.controller.workspace.CRUDController;
import com.adipa.loader.client.model.workspace.admin.UserMgmtModel;
import com.adipa.loader.client.view.workspace.admin.UserMgmtView;
import com.adipa.loader.workspace.client.user.UserDto;

/**
 * Date: Jul 13, 2011
 * Time: 12:27:58 PM
 */
public class UserMgmtController extends CRUDController<UserMgmtView, UserMgmtModel, UserDto>
{
    public UserMgmtController(UserMgmtView userMgmtView, UserMgmtModel userMgmtModel)
    {
        super(userMgmtView, userMgmtModel);
    }
}
