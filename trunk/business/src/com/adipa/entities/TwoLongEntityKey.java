package com.adipa.entities;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 29, 2011
 * Time: 7:58:20 AM
 */
public class TwoLongEntityKey extends AbstractLongEntityKey implements IEntityKey
{
    private long keyA;
    private long keyB;

    public TwoLongEntityKey(long keyA,long keyB)
    {
        this.keyA = keyA;
        this.keyB = keyB;
    }

    public int getKeyCount()
    {
        return 2;
    }

    public long getKey(int index)
    {
        switch (index)
        {
            case 0:
                return keyA;
            case 1:
                return keyB;
        }
        return -1;
    }
}
