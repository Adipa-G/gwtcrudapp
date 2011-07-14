package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.ermanagement.ISequenceGenerator;

import java.sql.Connection;

/**
 * Created by IntelliJ IDEA.
 * User: aga
 * Date: Oct 30, 2010
 * Time: 4:43:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class EntityTypeSequenceGenerator implements ISequenceGenerator
{
    public Object getNextSequenceValue(Connection con)
    {
        return SequenceManager.getNextValue(con, DBConstants.Gen.EntityType.TABLE_NAME);
    }
}
