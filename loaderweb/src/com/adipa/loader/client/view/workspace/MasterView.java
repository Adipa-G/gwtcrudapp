package com.adipa.loader.client.view.workspace;

import com.adipa.loader.client.ViewFactory;
import com.adipa.loader.client.ViewType;
import com.adipa.loader.client.controller.workspace.MasterController;
import com.adipa.loader.client.view.AbstractView;
import com.google.gwt.user.client.ui.*;

/**
 * Date: Jul 11, 2011
 * Time: 11:15:00 AM
 */
public class MasterView extends AbstractView<MasterController>
{
    private DockPanel dockPanel;
    private FlowPanel contentPanel;

    @Override
    public void initUI()
    {
        RootPanel rootPanel = getRootPanel();
        rootPanel.clear();

        FlowPanel basePanel = new FlowPanel();

        contentPanel = new FlowPanel();
        contentPanel.setSize(getContentWidth() + "px",getContentHeight() + "px");
        contentPanel.add(new HTML("test"));

        FlowPanel navigationPanel = new FlowPanel();
        navigationPanel.setSize(getNavigationWidth() + "px",getContentHeight() + "px");

        NavigationView navigationView = (NavigationView) ViewFactory.createView(ViewType.NAVIGATION,this);
        navigationView.initUI(navigationPanel);

        Image headerImage = new Image("images/app_header.jpg");
        headerImage.setHeight(getHeaderHeight() + "px");
        headerImage.setWidth(getAppWindowWidth() + "px");

        Image footerImage = new Image("images/app_footer.jpg");
        footerImage.setHeight(getFooterHeight() + "px");
        footerImage.setWidth(getAppWindowWidth() + "px");

        dockPanel = new DockPanel();
        dockPanel.add(contentPanel,DockPanel.CENTER);
        dockPanel.add(headerImage, DockPanel.NORTH);
        dockPanel.add(footerImage, DockPanel.SOUTH);
        dockPanel.add(navigationPanel,DockPanel.WEST);

        basePanel.add(dockPanel);
        rootPanel.add(basePanel);
    }

    public Panel getContentPanel()
    {
        return contentPanel;
    }

    @Override
    public RootPanel getRootPanel()
    {
        return RootPanel.get("rootFormContainer");
    }
}
