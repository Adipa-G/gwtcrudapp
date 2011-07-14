package com.adipa.loader.client.view.workspace;

import com.adipa.loader.client.controller.workspace.CRUDController;
import com.adipa.loader.workspace.client.ICRUDObjectDto;

import java.util.List;

/**
 * Date: Jul 13, 2011
 * Time: 4:39:11 PM
 */
public abstract class CRUDView<T extends CRUDController,U extends ICRUDObjectDto> extends MasterChildView<T>
{
    public CRUDView(MasterView masterView)
    {
        super(masterView);
    }

    public abstract void populate(List<U> data);
}
