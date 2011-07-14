package com.adipa.loader.workspace.client;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Date: Jul 13, 2011
 * Time: 1:36:56 PM
 */
public interface ICRUDService extends RemoteService
{
    CRUDCreateResponseDto create(CRUDCreateRequestDto requestDto) throws IllegalArgumentException;

    CRUDUpdateResponseDto update(CRUDUpdateRequestDto requestDto) throws IllegalArgumentException;

    CRUDDeleteResponseDto delete(CRUDDeleteRequestDto requestDto) throws IllegalArgumentException;

    CRUDListResponseDto list(CRUDListRequestDto requestDto) throws IllegalArgumentException;
}
