package com.adipa.entities;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jan 28, 2011
 * Time: 9:36:37 PM
 */
public interface IEntityKey
{
    int getKeyCount();
    
    long getKey(int index);
}
