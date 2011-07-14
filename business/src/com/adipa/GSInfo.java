package com.adipa;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Oct 14, 2006
 * Time: 7:12:48 PM
 * ----------------------------------------
 */
public class GSInfo
{
    public static int ERROR = 1;
    public static int SUCESS = 2;
    public static int WARNING = 3;

    private int no;
    private String message;
    private Object returnData;


    public GSInfo(int no, String message)
    {
        this(no,message,null);
    }

    public GSInfo(int no, String message, Object returnData)
    {
        this.no = no;
        this.message = message;
        this.returnData = returnData;
    }

    public int getNo()
    {
        return no;
    }

    public void setNo(int no)
    {
        this.no = no;
    }


    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Object getReturnData()
    {
        return returnData;
    }

    public void setReturnData(Object returnData)
    {
        this.returnData = returnData;
    }
}
