package com.adipa.loader.client.view.workspace.admin;

import com.adipa.loader.client.controller.workspace.admin.UserMgmtController;
import com.adipa.loader.client.view.workspace.CRUDView;
import com.adipa.loader.client.view.workspace.MasterView;
import com.adipa.loader.workspace.client.user.UserDto;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.ProvidesKey;
import com.google.gwt.view.client.SelectionModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Date: Jul 13, 2011
 * Time: 12:27:15 PM
 */
public class UserMgmtView extends CRUDView<UserMgmtController,UserDto>
{
    private List<UserDto> data;

    CellTable<UserDto> table;
    SimplePager pager;
    CustomButton addButton;
    CustomButton deleteButton;

    public UserMgmtView(MasterView masterView)
    {
        super(masterView);
    }

    @Override
    public void populate(List<UserDto> data)
    {
        this.data = data;
        addButton = new PushButton(new Image("/images/operations/edit_add.png"),new AddClickHandler());
        deleteButton = new PushButton(new Image("/images/operations/edit_delete.png"),new DeleteClickHandler());

        HorizontalPanel buttonPanel = new HorizontalPanel();
        buttonPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        table = new CellTable<UserDto>(new UserDtoKeyProvider());
        
        ColumnSortEvent.ListHandler<UserDto> sortHandler = new ColumnSortEvent.ListHandler<UserDto>(data);
        table.addColumnSortHandler(sortHandler);

        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(table);

        final SelectionModel<UserDto> selectionModel = new MultiSelectionModel<UserDto>(new UserDtoKeyProvider());
        table.setSelectionModel(selectionModel,DefaultSelectionEventManager.<UserDto>createCheckboxManager());

        initTableColumns(table,selectionModel,sortHandler);
        table.setRowData(data);

        getRootPanel().clear();
        VerticalPanel verticalPanel = new VerticalPanel();
        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

        verticalPanel.add(buttonPanel);
        verticalPanel.add(table);
        verticalPanel.add(pager);
        getRootPanel().add(verticalPanel);
    }

    @Override
    public void initUI()
    {
        controller.list();   
    }

    /**
     * Add the columns to the table.
     */
    private void initTableColumns(final CellTable cellTable
            ,final SelectionModel<UserDto> selectionModel
            ,ColumnSortEvent.ListHandler<UserDto> sortHandler)
    {
        Column<UserDto, Boolean> checkColumn = new Column<UserDto, Boolean>(
                new CheckboxCell(true, false))
        {
            @Override
            public Boolean getValue(UserDto object)
            {
                // Get the value from the selection model.
                return selectionModel.isSelected(object);
            }
        };
        cellTable.addColumn(checkColumn, SafeHtmlUtils.fromSafeConstant("<br/>"));
        cellTable.setColumnWidth(checkColumn, 40, Style.Unit.PX);

        // First name.
        Column<UserDto, String> userNameColumn = new Column<UserDto, String>(
                new EditTextCell())
        {
            @Override
            public String getValue(UserDto object)
            {
                return object.getUserName();
            }
        };
        userNameColumn.setSortable(true);
        sortHandler.setComparator(userNameColumn, new Comparator<UserDto>()
        {
            public int compare(UserDto o1, UserDto o2)
            {
                return o1.getUserName().compareTo(o2.getUserName());
            }
        });
        cellTable.addColumn(userNameColumn, "User Name");
        userNameColumn.setFieldUpdater(new FieldUpdater<UserDto, String>()
        {
            public void update(int index, UserDto object, String value)
            {
                // Called when the user changes the value.
                object.setUserName(value);
                controller.update(object);
            }
        });
        cellTable.setColumnWidth(userNameColumn, 200, Style.Unit.PX);
    }

    class AddClickHandler implements ClickHandler
    {
        @Override
        public void onClick(ClickEvent clickEvent)
        {
            UserDto dto = new UserDto();
            dto.setUserName("New User");
            dto.setPassword("asd");
            data.add(dto);
            controller.create(dto);

            table.setRowData(data);
        }
    }

    class DeleteClickHandler implements ClickHandler
    {
        @Override
        public void onClick(ClickEvent clickEvent)
        {
            List<UserDto> deletedList = new ArrayList<UserDto>();
            for (UserDto dto : data)
            {
                if (table.getSelectionModel().isSelected(dto))
                {
                    deletedList.add(dto);
                }
            }
            if (deletedList.size() > 0)
            {
                for (UserDto userDto : deletedList)
                {
                    data.remove(userDto);
                    controller.delete(userDto);
                }
                table.setRowData(data);
            }
        }
    }

    class UserDtoKeyProvider implements ProvidesKey<UserDto>
    {
        @Override
        public Object getKey(UserDto dto)
        {
            return dto.getUserId();
        }
    }
}
