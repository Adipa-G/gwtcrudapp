package com.adipa.loader.workspace.client;

import java.io.Serializable;

/**
 * Date: Jul 13, 2011
 * Time: 1:21:59 PM
 */
public abstract class CRUDResponse implements Serializable
{
    private boolean success;
    private String message;

    protected CRUDResponse()
    {
    }

    protected CRUDResponse(boolean success, String message)
    {
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public void setSuccess(boolean success)
    {
        this.success = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }
}
