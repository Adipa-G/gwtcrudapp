package com.adipa.loader.workspace.client;

import java.io.Serializable;

/**
 * Date: Jul 13, 2011
 * Time: 1:16:57 PM
 */
public class CRUDCreateRequestDto implements Serializable
{
    private ICRUDObjectDto objectDto;

    public ICRUDObjectDto getObjectDto()
    {
        return objectDto;
    }

    public void setObjectDto(ICRUDObjectDto objectDto)
    {
        this.objectDto = objectDto;
    }
}
