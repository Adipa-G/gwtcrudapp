package com.adipa.loader.client.model.workspace;

import com.adipa.loader.client.controller.workspace.CRUDController;
import com.adipa.loader.client.model.AbstractModel;
import com.adipa.loader.workspace.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Date: Jul 13, 2011
 * Time: 4:34:30 PM
 */
public abstract class CRUDModel<T extends CRUDController,U extends ICRUDObjectDto,V extends ICRUDIndexDto> extends AbstractModel<T>
{
    public abstract ICRUDServiceAsync getService();

    public abstract V getIndex(U dto);

    public abstract void setIndex(U dto, V index);

    public void update(U dto)
    {
        CRUDUpdateRequestDto updateRequestDto = new CRUDUpdateRequestDto();
        updateRequestDto.setObjectDto(dto);
        updateRequestDto.setIndexDto(getIndex(dto));

        getService().update(updateRequestDto,new AsyncCallback<CRUDUpdateResponseDto>()
        {
            @Override
            public void onFailure(Throwable throwable)
            {
                t.onError(throwable);
            }

            @Override
            public void onSuccess(CRUDUpdateResponseDto crudUpdateResponseDto)
            {
                if (!crudUpdateResponseDto.isSuccess())
                {
                    t.onUpdateFailure(crudUpdateResponseDto.getMessage());
                }
            }
        });
    }

    public void delete(U dto)
    {
        CRUDDeleteRequestDto deleteRequestDto = new CRUDDeleteRequestDto();
        deleteRequestDto.setIndexDto(getIndex(dto));

        getService().delete(deleteRequestDto,new AsyncCallback<CRUDDeleteResponseDto>()
        {
            @Override
            public void onFailure(Throwable throwable)
            {
                t.onError(throwable);
            }

            @Override
            public void onSuccess(CRUDDeleteResponseDto crudDeleteResponseDto)
            {
                if (!crudDeleteResponseDto.isSuccess())
                {
                    t.onUpdateFailure(crudDeleteResponseDto.getMessage());
                }
            }
        });
    }

    public void create(final U dto)
    {
        CRUDCreateRequestDto createRequestDto = new CRUDCreateRequestDto();
        createRequestDto.setObjectDto(dto);

        getService().create(createRequestDto,new AsyncCallback<CRUDCreateResponseDto>()
        {
            @Override
            public void onFailure(Throwable throwable)
            {
                t.onError(throwable);
            }

            @Override
            public void onSuccess(CRUDCreateResponseDto createResponseDto)
            {
                if (!createResponseDto.isSuccess())
                {
                    t.onUpdateFailure(createResponseDto.getMessage());
                }
                else
                {
                    setIndex(dto,(V)createResponseDto.getIndexDto());
                }
            }
        });
    }

    public void list()
    {
        CRUDListRequestDto listRequestDto = new CRUDListRequestDto();

        getService().list(listRequestDto,new AsyncCallback<CRUDListResponseDto>()
        {
            @Override
            public void onFailure(Throwable throwable)
            {
                t.onError(throwable);
            }

            @Override
            public void onSuccess(CRUDListResponseDto createResponseDto)
            {
                if (!createResponseDto.isSuccess())
                {
                    t.onUpdateFailure(createResponseDto.getMessage());
                }
                else
                {
                    t.onDataRetrieved(createResponseDto.getObjectDtoList());
                }
            }
        });
    }
}
