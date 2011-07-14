package com.adipa.entities;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Feb 4, 2011
 * Time: 7:29:11 PM
 */
public abstract class AbstractLongEntityKey implements IEntityKey
{
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof LongEntityKey)
        {
            LongEntityKey oLEK = (LongEntityKey) o;
            boolean value = oLEK.getKeyCount() == getKeyCount();
            if (value)
            {
                for (int i = 0; i < getKeyCount(); i++)
                {
                    long key = getKey(i);
                    long oKey = oLEK.getKey(i);
                    value = value && key == oKey;
                }
                return value;
            }
        }
        return false;
    }
}
