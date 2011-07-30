package com.adipa.loader.workspace;

import com.adipa.entities.IEntity;
import com.adipa.entities.IEntityKey;
import com.adipa.loader.server.ServiceTestBase;
import com.adipa.loader.workspace.client.*;
import com.adipa.loader.workspace.server.CRUDServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Adipa
 * Date: Jul 30, 2011
 * Time: 6:39:42 PM
 */
public abstract class CRUDServiceTestBase<T extends IEntity,U extends IEntityKey,V extends ICRUDObjectDto,W extends ICRUDIndexDto> extends ServiceTestBase
{
    protected CRUDServiceImpl<T,U,V,W> crudService;

    public abstract CRUDServiceImpl<T,U,V,W> getService();

    public abstract CRUDCreateRequestDto getCreateRequestDto() throws Exception;

    public abstract CRUDUpdateRequestDto getUpdateRequestDto() throws Exception;

    public abstract CRUDDeleteRequestDto getDeleteRequestDto() throws Exception;

    public abstract CRUDListRequestDto getListRequestDto() throws Exception;

    private boolean validIndexNo(ICRUDIndexDto indexDto) throws Exception
    {
        if (indexDto instanceof CRUDSingleLongIndexDto)
        {
            return ((CRUDSingleLongIndexDto)indexDto).getIndex() > 0;
        }
        throw new Exception("Incorrect index no type");
    }

    @Before
    public void beforeEach()
    {
        crudService = getService();
    }

    @After
    public void afterEach(){}

    @Test
    public void create_withValidEntities_shouldPersistEntities()
    {
        try
        {
            CRUDCreateResponseDto responseDto = crudService.create(getCreateRequestDto());
            boolean validated = validIndexNo(responseDto.getIndexDto());
            Assert.assertTrue("Creation success",validated);
        }
        catch (Exception ex)
        {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void update_withValidEntities_shouldUpdateEntities()
    {
        try
        {
            CRUDUpdateResponseDto responseDto = crudService.update(getUpdateRequestDto());
            Assert.assertTrue("Update success",responseDto.isSuccess());
        }
        catch (Exception ex)
        {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void delete_withValidEntities_shouldDeleteEntities()
    {
        try
        {
            CRUDDeleteResponseDto responseDto = crudService.delete(getDeleteRequestDto());
            Assert.assertTrue("Update success",responseDto.isSuccess());
        }
        catch (Exception ex)
        {
            Assert.fail(ex.getMessage());
        }
    }

    @Test
    public void list_withValidEntities_shouldListEntities()
    {
        try
        {
            CRUDListResponseDto responseDto = crudService.list(getListRequestDto());
            Assert.assertTrue("Update success",responseDto.isSuccess());
            Assert.assertTrue("No objects returned",responseDto.getObjectDtoList().size() > 0);
        }
        catch (Exception ex)
        {
            Assert.fail(ex.getMessage());
        }
    }
}
