package com.adipa.loader.client.model;

import com.adipa.loader.client.controller.IController;
import com.adipa.loader.client.ViewType;

/**
 * Date: Jun 2, 2011
 * Time: 4:36:30 PM
 */
public interface IModel<T extends IController>
{
    T getController();

    void checkAuthStatusAndRedirect(ViewType viewType);
}
