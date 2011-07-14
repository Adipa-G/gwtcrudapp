package com.adipa.loader.client;

import com.adipa.loader.client.controller.auth.LogonController;
import com.adipa.loader.client.controller.workspace.MasterController;
import com.adipa.loader.client.controller.workspace.NavigationController;
import com.adipa.loader.client.controller.workspace.admin.UserMgmtController;
import com.adipa.loader.client.model.auth.LogonModel;
import com.adipa.loader.client.model.workspace.MasterModel;
import com.adipa.loader.client.model.workspace.NavigationModel;
import com.adipa.loader.client.model.workspace.admin.UserMgmtModel;
import com.adipa.loader.client.view.AbstractView;
import com.adipa.loader.client.view.IView;
import com.adipa.loader.client.view.auth.LogonView;
import com.adipa.loader.client.view.workspace.MasterChildView;
import com.adipa.loader.client.view.workspace.MasterView;
import com.adipa.loader.client.view.workspace.NavigationView;
import com.adipa.loader.client.view.workspace.admin.UserMgmtView;

/**
 * Date: Jun 2, 2011
 * Time: 9:08:06 PM
 */
public class ViewFactory
{
    public static IView createView(ViewType viewType, AbstractView currentView)
    {
        MasterView masterView = null;
        if (currentView instanceof MasterView)
        {
            masterView = (MasterView) currentView;
        }
        else if (currentView instanceof MasterChildView)
        {
            MasterChildView masterChildView = (MasterChildView) currentView;
            masterView = masterChildView.getMasterView();
        }

        AbstractView abstractView = null;
        switch (viewType)
        {
            case LOGON:
            {
                LogonView view = new LogonView();
                LogonModel model = new LogonModel();
                LogonController controller = new LogonController(view,model);
                view.setController(controller);
                model.setController(controller);
                abstractView = view;
                break;
            }
            case MASTER:
            {
                MasterView view = new MasterView();
                MasterModel model = new MasterModel();
                MasterController controller = new MasterController(view,model);
                view.setController(controller);
                model.setController(controller);
                abstractView = view;
                break;
            }
            case NAVIGATION:
            {
                NavigationView view = new NavigationView(masterView);
                NavigationModel model = new NavigationModel();
                NavigationController controller = new NavigationController(view,model);
                view.setController(controller);
                model.setController(controller);
                abstractView = view;
                break;
            }
            case ADMIN_USER:
            {
                UserMgmtView view = new UserMgmtView(masterView);
                UserMgmtModel model = new UserMgmtModel();
                UserMgmtController controller = new UserMgmtController(view,model);
                view.setController(controller);
                model.setController(controller);
                abstractView = view;
                break;
            }
            default:
                break;
        }
        abstractView.checkAuth(viewType);
        
        return abstractView;
    }
}
