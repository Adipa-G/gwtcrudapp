package com.adipa.entities;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 29, 2011
 * Time: 7:58:20 AM
 */
public class LongEntityKey extends AbstractLongEntityKey implements IEntityKey
{
    private long key;

    public LongEntityKey(long key)
    {
        this.key = key;
    }

    public int getKeyCount()
    {
        return 1;
    }

    public long getKey(int index)
    {
        return key;
    }


}
