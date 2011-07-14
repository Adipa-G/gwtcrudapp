package com.adipa.entities;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 29, 2011
 * Time: 7:58:20 AM
 */
public class ThreeLongEntityKey extends AbstractLongEntityKey  implements IEntityKey
{
    private long keyA;
    private long keyB;
    private long keyC;

    public ThreeLongEntityKey(long keyA,long keyB,long keyC)
    {
        this.keyA = keyA;
        this.keyB = keyB;
        this.keyC = keyC;
    }

    public int getKeyCount()
    {
        return 3;
    }

    public long getKey(int index)
    {
        switch (index)
        {
            case 0:
                return keyA;
            case 1:
                return keyB;
            case 2:
                return keyC;
        }
        return -1;
    }
}
