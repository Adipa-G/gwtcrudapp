package com.adipa.loader.workspace.client;

import java.io.Serializable;

/**
 * Date: Jul 13, 2011
 * Time: 1:16:57 PM
 */
public class CRUDUpdateRequestDto implements Serializable
{
    private ICRUDIndexDto indexDto;
    private ICRUDObjectDto objectDto;

    public CRUDUpdateRequestDto()
    {
    }

    public ICRUDIndexDto getIndexDto()
    {
        return indexDto;
    }

    public void setIndexDto(ICRUDIndexDto indexDto)
    {
        this.indexDto = indexDto;
    }

    public ICRUDObjectDto getObjectDto()
    {
        return objectDto;
    }

    public void setObjectDto(ICRUDObjectDto objectDto)
    {
        this.objectDto = objectDto;
    }
}
