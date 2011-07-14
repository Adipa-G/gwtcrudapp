package com.adipa.loader.workspace.server;

import com.adipa.entities.IEntity;
import com.adipa.entities.IEntityKey;
import com.adipa.loader.server.AbstractService;
import com.adipa.loader.workspace.client.*;
import com.adipa.repositories.IRepository;
import dbgate.DBClassStatus;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Date: Jul 13, 2011
 * Time: 1:42:57 PM
 */
public abstract class CRUDServiceImpl<T extends IEntity,U extends IEntityKey,V extends ICRUDObjectDto,W extends ICRUDIndexDto> extends AbstractService implements ICRUDService
{
    public abstract U getKey(W indexDto);

    public abstract W getIndexNo(T entity);

    public abstract IRepository<T,U> getRepository();

    public abstract T createNewInstance();

    public abstract V mapDto(T entity);

    public abstract void updateEntity(T entity,V objectDto);

    public abstract void validate(V objectDto) throws IllegalArgumentException;

    @Override
    public CRUDCreateResponseDto create(CRUDCreateRequestDto requestDto) throws IllegalArgumentException
    {
        validate((V)requestDto.getObjectDto());

        try
        {
            T entity = createNewInstance();
            IRepository<T,U> repository = getRepository();

            updateEntity(entity,(V)requestDto.getObjectDto());
            T createdEntity = repository.persist(entity);

            CRUDCreateResponseDto responseDto = new CRUDCreateResponseDto(true,"Success");
            responseDto.setIndexDto(getIndexNo(createdEntity));

            return responseDto;
        }
        catch (Exception ex)
        {
            handleException(ex,ex.getMessage());
            return new CRUDCreateResponseDto(false,"Could not persist");
        }
    }

    @Override
    public CRUDUpdateResponseDto update(CRUDUpdateRequestDto requestDto) throws IllegalArgumentException
    {
        validate((V)requestDto.getObjectDto());

        try
        {
            IRepository<T,U> repository = getRepository();
            U entityKey = getKey((W)requestDto.getIndexDto());

            T entity = repository.getById(entityKey);
            if (entity == null)
            {
                throw new IllegalArgumentException("Incorrect entity id");
            }

            updateEntity(entity,(V)requestDto.getObjectDto());
            T updatedEntity = repository.persist(entity);

            CRUDUpdateResponseDto responseDto = new CRUDUpdateResponseDto(true,"Success");
            return responseDto;
        }
        catch (Exception ex)
        {
            handleException(ex,ex.getMessage());
            return new CRUDUpdateResponseDto(false,"Could not persist");
        }
    }

    @Override
    public CRUDDeleteResponseDto delete(CRUDDeleteRequestDto requestDto) throws IllegalArgumentException
    {
        try
        {
            IRepository<T,U> repository = getRepository();
            U entityKey = getKey((W)requestDto.getIndexDto());

            T entity = repository.getById(entityKey);
            if (entity == null)
            {
                throw new IllegalArgumentException("Incorrect entity id");
            }

            entity.setStatus(DBClassStatus.DELETED);
            repository.persist(entity);

            CRUDDeleteResponseDto responseDto = new CRUDDeleteResponseDto(true,"Success");
            return responseDto;
        }
        catch (Exception ex)
        {
            handleException(ex,ex.getMessage());
            return new CRUDDeleteResponseDto(false,"Could not delete");
        }
    }

    @Override
    public CRUDListResponseDto list(CRUDListRequestDto requestDto) throws IllegalArgumentException
    {
        try
        {
            IRepository<T,U> repository = getRepository();
            Collection<T> entities = repository.getAll();
            List<ICRUDObjectDto> objectList = new ArrayList<ICRUDObjectDto>();

            for (T entity : entities)
            {
                ICRUDObjectDto objectDto = mapDto(entity);
                objectList.add(objectDto);
            }

            CRUDListResponseDto responseDto = new CRUDListResponseDto(true,"Success");
            responseDto.setObjectDtoList(objectList);
            return responseDto;
        }
        catch (Exception ex)
        {
            handleException(ex,ex.getMessage());
            return new CRUDListResponseDto(false,"Could not list");
        }
    }
}
