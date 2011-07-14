package com.adipa.loader.workspace.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Date: Jul 13, 2011
 * Time: 1:36:56 PM
 */
public interface ICRUDServiceAsync extends RemoteService
{
    void create(CRUDCreateRequestDto requestDto, AsyncCallback<CRUDCreateResponseDto> callback) throws IllegalArgumentException;

    void update(CRUDUpdateRequestDto requestDto, AsyncCallback<CRUDUpdateResponseDto> callback) throws IllegalArgumentException;

    void delete(CRUDDeleteRequestDto requestDto, AsyncCallback<CRUDDeleteResponseDto> callback) throws IllegalArgumentException;
    
    void list(CRUDListRequestDto requestDto, AsyncCallback<CRUDListResponseDto> callback) throws IllegalArgumentException;
}
