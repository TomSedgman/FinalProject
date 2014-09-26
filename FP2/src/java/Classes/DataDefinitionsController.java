package Classes;

import Entities.DataDefinitions;
import Classes.util.JsfUtil;
import Classes.util.PaginationHelper;
import Session.DataDefinitionsFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("dataDefinitionsController")
@SessionScoped
public class DataDefinitionsController implements Serializable {

    private DataDefinitions current;
    private DataModel items = null;
    @EJB
    private Session.DataDefinitionsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private PersistedVariables.PProject currProject;
    
    public DataDefinitionsController() {
    }

    public DataDefinitions getSelected() {
        if (current == null) {
            current = new DataDefinitions();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DataDefinitionsFacade getFacade() {
        return ejbFacade;
    }
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    int data = getFacade().allDefinitions(currProject.getCurrentProject()).size(); // prevent overreporting of numbers when handling multiple projects
                    return data; 
                }

                @Override
                public DataModel createPageDataModel() // not stock for security, restircting list on table to those for currProject
                {
//                    if ((getPageFirstItem() + getPageSize()) > getPageLastItem())
//                    {
//                        return new ListDataModel(getFacade().allDefinitions(currProject.getCurrentProject()).subList(getPageFirstItem(), getPageLastItem()));
//   
//                    }
//                    else
//                    {
//                        return new ListDataModel(getFacade().allDefinitions(currProject.getCurrentProject()).subList(getPageFirstItem(), getPageFirstItem() + getPageSize()));
//
//                    }
                    return new ListDataModel(getFacade().allDefinitions(currProject.getCurrentProject()));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "DataDefinitionsList";
    }

    public String prepareView() {
        current = (DataDefinitions) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "DataDefinitionsView";
    }

    public String prepareCreate() {
        current = new DataDefinitions();
        selectedItemIndex = -1;
        return "DataDefinitionsCreate";
    }

    public String create() {
        try {
            getFacade().newDefinition(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DataDefinitionsCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (DataDefinitions) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "DataDefinitionsEdit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DataDefinitionsUpdated"));
            return "DataDefinitionsView";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (DataDefinitions) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "DataDefinitionsList";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "DataDefinitionsView";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "DataDefinitionsList";
        }
    }

//    public String projectList() // depricated, previous attempt to restict list of variables
//    {
//        List temp =  (List) ejbFacade.allDefinitions(currProject.getCurrentProject()); 
//        items.setWrappedData(temp);
//        return "";
//    }
    
    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("DataDefinitionsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().allDefinitions(currProject.getCurrentProject()).size(); // not stock for security
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) 
        {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "DataDefinitionsList";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "DataDefinitionsList";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public DataDefinitions getDataDefinitions(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = DataDefinitions.class)
    public static class DataDefinitionsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DataDefinitionsController controller = (DataDefinitionsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "dataDefinitionsController");
            return controller.getDataDefinitions(getKey(value));
        }

        java.lang.Long getKey(String value) {
            java.lang.Long key;
            key = Long.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Long value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof DataDefinitions) {
                DataDefinitions o = (DataDefinitions) object;
                return getStringKey(o.getDataDefinitionId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DataDefinitions.class.getName());
            }
        }
    }
}
