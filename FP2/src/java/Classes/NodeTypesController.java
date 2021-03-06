package Classes;

import Entities.NodeTypes;
import Classes.util.JsfUtil;
import Classes.util.PaginationHelper;
import Session.NodeTypesFacade;

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

@Named("nodeTypesController")
@SessionScoped
public class NodeTypesController implements Serializable {

    private NodeTypes current;
    private DataModel items = null;
    @EJB
    private Session.NodeTypesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private PersistedVariables.PProject currProject;

    public NodeTypesController() {
    }

    public NodeTypes getSelected() {
        if (current == null) {
            current = new NodeTypes();
            selectedItemIndex = -1;
        }
        return current;
    }

    private NodeTypesFacade getFacade() {
        return ejbFacade;
    }
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    int data = getFacade().allNodeTypes(currProject.getCurrentProject()).size(); // prevent overreporting of numbers when handling multiple projects
                    return data; 
                }

                @Override
                public DataModel createPageDataModel() // not stock for security, restircting list on table to those for currProject
                {
//                    if ((getPageFirstItem() + getPageSize()) > getPageLastItem())
//                    {
//                        return new ListDataModel(getFacade().allNodeTypes(currProject.getCurrentProject()).subList(getPageFirstItem(), getPageLastItem()));
//   
//                    }
//                    else
//                    {
//                        return new ListDataModel(getFacade().allNodeTypes(currProject.getCurrentProject()).subList(getPageFirstItem(), getPageFirstItem() + getPageSize()));
//
//                    }
                    return new ListDataModel(getFacade().allNodeTypes(currProject.getCurrentProject()));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "NodeTypesList";
    }

    public String prepareView() {
        current = (NodeTypes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "NodeTypesView";
    }

    public String prepareCreate() {
        current = new NodeTypes();
        selectedItemIndex = -1;
        return "NodeTypesCreate";
    }

    public String create() {
        try {
            getFacade().newNodeType(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NodeTypesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (NodeTypes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "NodeTypesEdit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NodeTypesUpdated"));
            return "NodeTypesView";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (NodeTypes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "Destroy";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "NodeTypesView";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "NodeTypesList";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NodeTypesDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
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
            items = getPagination().createPageDataModel();
        
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
        return "NodeTypesList";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "NodeTypesList";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.allNodeTypes(currProject.getCurrentProject()), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.allNodeTypes(currProject.getCurrentProject()), true);
    }

    public NodeTypes getNodeTypes(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = NodeTypes.class)
    public static class NodeTypesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NodeTypesController controller = (NodeTypesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nodeTypesController");
            return controller.getNodeTypes(getKey(value));
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
            if (object instanceof NodeTypes) {
                NodeTypes o = (NodeTypes) object;
                return getStringKey(o.getNodeTypeId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + NodeTypes.class.getName());
            }
        }
    }
}
