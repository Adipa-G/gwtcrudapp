package com.adipa.entities.gen;

import com.adipa.entities.DBConstants;
import dbgate.DBColumnType;
import dbgate.ermanagement.DBColumnInfo;
import dbgate.ermanagement.DBTableInfo;
import dbgate.ermanagement.DefaultServerDBClass;

/**
 * Created by IntelliJ IDEA.
 * User: aga
 * Date: Oct 30, 2010
 * Time: 4:34:35 PM
 * To change this template use File | Settings | File Templates.
 */
@DBTableInfo(tableName = DBConstants.Gen.Sequence.TABLE_NAME)
public class Sequence extends DefaultServerDBClass
{
    @DBColumnInfo(columnType = DBColumnType.VARCHAR,columnName = DBConstants.Gen.Sequence.COL_SEQUENCE_ID, size = 255)
    private String sequenceId;
    @DBColumnInfo(columnType = DBColumnType.VARCHAR,columnName = DBConstants.Gen.Sequence.COL_NEXT_VALUE, size = 255)
    private String nextValue;

    public String getSequenceId()
    {
        return sequenceId;
    }

    public void setSequenceId(String sequenceId)
    {
        this.sequenceId = sequenceId;
    }

    public String getNextValue()
    {
        return nextValue;
    }

    public void setNextValue(String nextValue)
    {
        this.nextValue = nextValue;
    }
}
