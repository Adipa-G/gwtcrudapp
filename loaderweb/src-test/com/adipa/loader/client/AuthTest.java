package com.adipa.loader.client;

import com.adipa.loader.auth.client.AuthRequestDto;
import com.adipa.loader.auth.client.AuthResponseDto;
import com.adipa.loader.auth.client.IAuthenticationService;
import com.adipa.loader.auth.client.IAuthenticationServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 30, 2011
 * Time: 11:09:01 AM
 */
public class AuthTest extends GWTTestCase
{
    @Override
    public String getModuleName()
    {
        return "com.adipa.loader.Index";
    }

    public void testSimple()
    {
        assertTrue(true);
    }
}
