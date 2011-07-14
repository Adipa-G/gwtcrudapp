package com.adipa.loader.client.view;

import com.adipa.loader.client.ViewFactory;
import com.adipa.loader.client.ViewType;
import com.adipa.loader.client.controller.AbstractController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Date: Jun 2, 2011
 * Time: 9:18:04 PM
 */
public abstract class AbstractView<T extends AbstractController> implements IView<T>
{
    protected T controller;

    protected AbstractView()
    {
    }

    @Override
    public T getController()
    {
        return controller;
    }

    public void setController(T t)
    {
        this.controller = t;
    }

    @Override
    public void checkAuth(ViewType viewType)
    {
        controller.checkAuthStatusAndRedirect(viewType);
    }


    @Override
    public void showMessage(String message, String title)
    {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Error Occurred");
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        closeButton.getElement().setId("closeButton");

        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML(message + "<br>"));
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);
        dialogBox.show();
        dialogBox.center();
        closeButton.setFocus(true);

        closeButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                dialogBox.hide();
            }
        });
    }

    @Override
    public void onError(Throwable throwable)
    {
        final DialogBox dialogBox = new DialogBox();
        dialogBox.setText("Error Occurred");
        dialogBox.setAnimationEnabled(true);
        final Button closeButton = new Button("Close");
        closeButton.getElement().setId("closeButton");

        VerticalPanel dialogVPanel = new VerticalPanel();
        dialogVPanel.addStyleName("dialogVPanel");
        dialogVPanel.add(new HTML("<b>Exception Message</b><br>"));
        dialogVPanel.add(new HTML(throwable.getMessage() + "<br>"));
        dialogVPanel.add(new HTML("<b>Stack Trace</b>"));
        for (StackTraceElement element : throwable.getStackTrace())
        {
            dialogVPanel.add(new HTML(element.toString()));
        }
        dialogVPanel.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
        dialogVPanel.add(closeButton);
        dialogBox.setWidget(dialogVPanel);
        dialogBox.show();
        dialogBox.center();
        closeButton.setFocus(true);

        closeButton.addClickHandler(new ClickHandler()
        {
            public void onClick(ClickEvent event)
            {
                dialogBox.hide();
            }
        });
    }

    @Override
    public void switchToView(ViewType viewType)
    {
        IView view = ViewFactory.createView(viewType,this);
        view.initUI();
    }

    @Override
    public int getAppWindowHeight()
    {
        return Window.getClientHeight();
    }

    @Override
    public int getAppWindowWidth()
    {
        return Window.getClientWidth();
    }

    @Override
    public int getContentHeight()
    {
        return Window.getClientHeight() - getFooterHeight() - getHeaderHeight();
    }

    @Override
    public int getContentWidth()
    {
        return getAppWindowWidth() - getNavigationWidth();
    }

    @Override
    public int getHeaderHeight()
    {
        return 40;
    }

    @Override
    public int getFooterHeight()
    {
        return 40;
    }

    @Override
    public int getNavigationWidth()
    {
        return 150;
    }

    @Override
    public int getNavigationButtonWidth()
    {
        return getNavigationWidth() - 10;
    }
}
