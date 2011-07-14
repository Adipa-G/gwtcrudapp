package com.adipa.loader.client.view.workspace;

import com.adipa.loader.client.controller.AbstractController;
import com.adipa.loader.client.view.AbstractView;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Date: Jul 11, 2011
 * Time: 12:27:23 PM
 */
public abstract class MasterChildView<T extends AbstractController> extends AbstractView<T>
{
    private MasterView masterView;

    protected MasterChildView(MasterView masterView)
    {
        this.masterView = masterView;
    }

    public MasterView getMasterView()
    {
        return masterView;
    }

    @Override
    public Panel getRootPanel()
    {
        return masterView.getContentPanel();
    }
}
