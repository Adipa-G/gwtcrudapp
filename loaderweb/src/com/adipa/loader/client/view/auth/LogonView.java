package com.adipa.loader.client.view.auth;

import com.adipa.loader.auth.shared.LogonVerifier;
import com.adipa.loader.client.controller.auth.LogonController;
import com.adipa.loader.client.view.AbstractView;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;

/**
 * Date: Jun 2, 2011
 * Time: 4:25:16 PM
 */
public class LogonView extends AbstractView<LogonController>
{
    Label lblError;
    TextBox userNameTextBox;
    PasswordTextBox passwordTextBox;

    public LogonView()
    {
    }

    @Override
    public void initUI()
    {
        Panel rootPanel = getRootPanel();
        rootPanel.clear();
        rootPanel.setHeight(Window.getClientHeight() + "px");

        FlowPanel flowPanel = new FlowPanel();
		flowPanel.setSize("265px", "146px");

        lblError = new Label("");
        lblError.setSize("256px", "18px");
        lblError.setStyleName("errorLabel");
		flowPanel.add(lblError);

		HorizontalPanel hLayout0 = new HorizontalPanel();
		hLayout0.setStyleName("formRow");

		Label lblUserName = new Label("User Name");
        lblUserName.setStyleName("formComponentLabel");
		hLayout0.add(lblUserName);

        userNameTextBox = new TextBox();
        userNameTextBox.setStyleName("formComponent");
        hLayout0.add(userNameTextBox);
		flowPanel.add(hLayout0);

		HorizontalPanel hLayout1 = new HorizontalPanel();
		hLayout1.setStyleName("formRow");

		Label lblPassword = new Label("Password");
        lblPassword.setStyleName("formComponentLabel");
        hLayout1.add(lblPassword);

        passwordTextBox = new PasswordTextBox();
        hLayout1.add(passwordTextBox);
		passwordTextBox.setStyleName("formComponent");
		flowPanel.add(hLayout1);

		HorizontalPanel hLayout2 = new HorizontalPanel();
		hLayout2.setStyleName("formRow");

		Label label = new Label("");
		hLayout2.add(label);
		label.setSize("61px", "2px");

		Button btnLogon = new Button("Logon");
        btnLogon.setStyleName("primaryButton");
		hLayout2.add(btnLogon);

		Button btnReset = new Button("Reset");
        btnReset.setStyleName("secondaryButton");
		hLayout2.add(btnReset);
		flowPanel.add(hLayout2);
        rootPanel.add(flowPanel);

        userNameTextBox.setFocus(true);
        userNameTextBox.selectAll();
        
        LogonHandler logonHandler = new LogonHandler();
        btnLogon.addClickHandler(logonHandler);
        passwordTextBox.addKeyUpHandler(logonHandler);

        ResetHandler resetHandler = new ResetHandler();
        btnReset.addClickHandler(resetHandler);
    }

    @Override
    public Panel getRootPanel()
    {
        return RootPanel.get("rootFormContainer");
    }

    class LogonHandler implements ClickHandler, KeyUpHandler
    {
        public void onClick(ClickEvent event)
        {
            logon();
        }

        public void onKeyUp(KeyUpEvent event)
        {
            if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER)
            {
                logon();
            }
        }

        private void logon()
        {
            // First, we validate the input.
            lblError.setText("");
            String userName = userNameTextBox.getText();
            if (!LogonVerifier.isValid(userName))
            {
                lblError.setText("Please enter a user name");
                return;
            }

            String password = passwordTextBox.getText();
            if (!LogonVerifier.isValid(password))
            {
                lblError.setText("Please enter a password");
                return;
            }

            controller.logon(userName,password);
        }
    }

    class ResetHandler implements ClickHandler
    {
        public void onClick(ClickEvent event)
        {
            clear();
        }
        
        private void clear()
        {
            userNameTextBox.setText("");
            passwordTextBox.setText("");
        }
    }
}
