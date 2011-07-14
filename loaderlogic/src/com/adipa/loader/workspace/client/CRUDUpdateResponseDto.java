package com.adipa.loader.workspace.client;

import java.io.Serializable;

/**
 * Date: Jul 13, 2011
 * Time: 1:16:57 PM
 */
public class CRUDUpdateResponseDto extends CRUDResponse implements Serializable
{
    public CRUDUpdateResponseDto()
    {
    }

    public CRUDUpdateResponseDto(boolean success, String message)
    {
        super(success, message);
    }
}
