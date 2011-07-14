package com.adipa.loader.workspace.client;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: Jul 13, 2011
 * Time: 1:16:57 PM
 */
public class CRUDListResponseDto extends CRUDResponse
{
    private List<ICRUDObjectDto> objectDtoList;

    public CRUDListResponseDto()
    {
    }

    public CRUDListResponseDto(boolean success, String message)
    {
        super(success, message);
        objectDtoList = new ArrayList<ICRUDObjectDto>();
    }

    public List<ICRUDObjectDto> getObjectDtoList()
    {
        return objectDtoList;
    }

    public void setObjectDtoList(List<ICRUDObjectDto> objectDtoList)
    {
        this.objectDtoList = objectDtoList;
    }
}
