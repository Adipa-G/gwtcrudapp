package com.adipa.loader.workspace.client;

/**
 * Date: Jul 13, 2011
 * Time: 1:26:10 PM
 */
public class CRUDSingleLongIndexDto implements ICRUDIndexDto
{
    private long index;

    public CRUDSingleLongIndexDto()
    {
    }

    public CRUDSingleLongIndexDto(long index)
    {
        this.index = index;
    }

    public long getIndex()
    {
        return index;
    }

    public void setIndex(long index)
    {
        this.index = index;
    }
}
