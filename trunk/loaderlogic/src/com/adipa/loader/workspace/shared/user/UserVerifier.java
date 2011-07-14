package com.adipa.loader.workspace.shared.user;

public class UserVerifier
{
    public static boolean isValidUserName(String name)
    {
        if (name == null)
        {
            return false;
        }
        return name.length() > 2;
    }

    public static boolean isValidPassword(String password)
    {
        if (password == null)
        {
            return false;
        }
        return password.length() > 2;
    }
}
