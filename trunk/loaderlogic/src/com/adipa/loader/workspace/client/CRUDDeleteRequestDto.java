package com.adipa.loader.workspace.client;

import java.io.Serializable;

/**
 * Date: Jul 13, 2011
 * Time: 1:16:57 PM
 */
public class CRUDDeleteRequestDto implements Serializable
{
    private ICRUDIndexDto indexDto;

    public ICRUDIndexDto getIndexDto()
    {
        return indexDto;
    }

    public void setIndexDto(ICRUDIndexDto indexDto)
    {
        this.indexDto = indexDto;
    }
}
