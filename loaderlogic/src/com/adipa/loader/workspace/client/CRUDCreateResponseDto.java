package com.adipa.loader.workspace.client;

import java.io.Serializable;

/**
 * Date: Jul 13, 2011
 * Time: 1:16:57 PM
 */
public class CRUDCreateResponseDto extends CRUDResponse
{
    private ICRUDIndexDto indexDto;

    public CRUDCreateResponseDto()
    {
    }

    public CRUDCreateResponseDto(boolean success, String message)
    {
        super(success, message);
    }

    public ICRUDIndexDto getIndexDto()
    {
        return indexDto;
    }

    public void setIndexDto(ICRUDIndexDto indexDto)
    {
        this.indexDto = indexDto;
    }
}
