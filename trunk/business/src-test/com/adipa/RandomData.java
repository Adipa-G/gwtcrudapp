package com.adipa;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Feb 5, 2011
 * Time: 6:17:13 PM
 */
public class RandomData
{
    public static String randomCode()
    {
        return UUID.randomUUID().toString().substring(20);
    }

    public static String randomDescription()
    {
        return UUID.randomUUID().toString();
    }

    public static long randomLong()
    {
        return Math.abs(UUID.randomUUID().getLeastSignificantBits());
    }

    public static int randomInt()
    {
        return (int) (Math.abs(UUID.randomUUID().getLeastSignificantBits()) % Integer.MAX_VALUE);
    }

    public static double randomDouble()
    {
        return Math.abs(UUID.randomUUID().getLeastSignificantBits()) / Integer.MAX_VALUE;
    }
}
