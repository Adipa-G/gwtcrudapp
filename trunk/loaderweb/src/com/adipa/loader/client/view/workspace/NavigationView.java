package com.adipa.loader.client.view.workspace;

import com.adipa.loader.client.ViewType;
import com.adipa.loader.client.controller.workspace.NavigationController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.*;

/**
 * Date: Jul 11, 2011
 * Time: 2:42:18 PM
 */
public class NavigationView extends MasterChildView<NavigationController>
{
    public NavigationView(MasterView masterView)
    {
        super(masterView);
    }

    public void initUI(Panel rootPanel)
    {
        StackPanel stackPanel = new StackPanel();
        stackPanel.setSize(getNavigationWidth() + "px", getContentHeight() + "px");

        {
            VerticalPanel adminPanel = new VerticalPanel();

            Button userButton = createButton("/images/admin/user_nav.png","Users");
            userButton.addClickHandler(new ClickHandler()
            {
                @Override
                public void onClick(ClickEvent clickEvent)
                {
                    switchToView(ViewType.ADMIN_USER);
                }
            });
            adminPanel.add(userButton);

            SafeHtmlBuilder builder = new SafeHtmlBuilder();
            builder.appendHtmlConstant("<img src=\"/images/admin/admin_header.png\" align=\"middle\"> Admin");
            stackPanel.add(adminPanel,builder.toSafeHtml());
        }

        {
            String text1 = "Lorem ipsum dolor sit amet...";

            Label label;
            label = new Label(text1);
            stackPanel.add(label, "One", false);
        }

        rootPanel.add(stackPanel);
    }

    private Button createButton(String image,String text)
    {
        SafeHtmlBuilder builder = new SafeHtmlBuilder();
        builder.appendHtmlConstant("<img src=\"" + image +"\" align=\"middle\">" + text);
        Button button = new Button(builder.toSafeHtml());
        button.setWidth(getNavigationButtonWidth() + "px");
        return button;
    }

    @Override
    public void initUI()
    {
    }

    @Override
    public Panel getRootPanel()
    {
        return null;
    }
}
