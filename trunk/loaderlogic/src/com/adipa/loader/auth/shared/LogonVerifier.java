package com.adipa.loader.auth.shared;

public class LogonVerifier
{
    public static boolean isValid(String name)
    {
        if (name == null || name.length() == 0)
        {
            return false;
        }
        return true;
    }
}
