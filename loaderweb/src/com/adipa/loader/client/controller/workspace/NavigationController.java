package com.adipa.loader.client.controller.workspace;

import com.adipa.loader.client.controller.AbstractController;
import com.adipa.loader.client.model.workspace.NavigationModel;
import com.adipa.loader.client.view.workspace.NavigationView;

/**
 * Date: Jul 11, 2011
 * Time: 2:42:49 PM
 */
public class NavigationController extends AbstractController<NavigationView, NavigationModel>
{
    public NavigationController(NavigationView navigationView, NavigationModel navigationModel)
    {
        super(navigationView, navigationModel);
    }
}
