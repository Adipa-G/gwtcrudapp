package com.adipa.loader.client.controller.workspace;

import com.adipa.loader.client.controller.AbstractController;
import com.adipa.loader.client.model.workspace.CRUDModel;
import com.adipa.loader.client.view.workspace.CRUDView;
import com.adipa.loader.workspace.client.ICRUDObjectDto;

import java.util.List;

/**
 * Date: Jul 13, 2011
 * Time: 4:37:30 PM
 */
public class CRUDController <T extends CRUDView,U extends CRUDModel,V extends ICRUDObjectDto> extends AbstractController<T,U>
{
    public CRUDController(T t, U u)
    {
        super(t, u);
    }

    public void onDataRetrieved(List<V> data)
    {
        view.populate(data);
    }

    public void onUpdateFailure(String message)
    {
        view.showMessage("Update Failed",message);
    }

    public void onError(Throwable throwable)
    {
        view.onError(throwable);
    }

    public void update(V v)
    {
        model.update(v);
    }
    
    public void delete(V v)
    {
        model.delete(v);
    }

    public void create(V v)
    {
        model.create(v);
    }

    public void list()
    {
        model.list();
    }
}
