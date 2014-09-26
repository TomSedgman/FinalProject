package Classes;

import Entities.Nodes;
import Classes.util.JsfUtil;
import Classes.util.PaginationHelper;
import Session.NodesFacade;

import java.io.Serializable;
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

@Named("nodesController")
@SessionScoped
public class NodesController implements Serializable {

    private Nodes current;
    private DataModel items = null;
    @EJB
    private Session.NodesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @EJB
    private PersistedVariables.PProject currProject;

    public NodesController() {
    }

    public Nodes getSelected() {
        if (current == null) {
            current = new Nodes();
            selectedItemIndex = -1;
        }
        return current;
    }

    private NodesFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().allNodes(currProject.getCurrentProject()).size(); // prevent overreporting of numbers when handling multiple projects
                }

                @Override
                public DataModel createPageDataModel() // not stock for security, restircting list on table to those for currProject
                {
//                    if ((getPageFirstItem() + getPageSize()) > getPageLastItem())
//                    {
//                        return new ListDataModel(getFacade().allNodes(currProject.getCurrentProject()).subList(getPageFirstItem(), getPageLastItem()));
//   
//                    }
//                    else
//                    {
//                        return new ListDataModel(getFacade().allNodes(currProject.getCurrentProject()).subList(getPageFirstItem(), getPageFirstItem() + getPageSize()));
//
//                    }
                      return new ListDataModel(getFacade().allNodes(currProject.getCurrentProject()));

                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "NodesList";
    }

    public String prepareView() {
        current = (Nodes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "NodesView";
    }

    public String prepareCreate() {
        current = new Nodes();
        selectedItemIndex = -1;
        return "NodesCreate";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NodesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Nodes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "NodesEdit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NodesUpdated"));
            return "NodesView";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Nodes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "NodesList";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "NodesView";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "NodesList";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NodesDeleted"));
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
        if (selectedItemIndex >= 0) {
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
        return "NodesList";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "NodesList";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Nodes getNodes(java.lang.Long id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Nodes.class)
    public static class NodesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NodesController controller = (NodesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nodesController");
            return controller.getNodes(getKey(value));
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
            if (object instanceof Nodes) {
                Nodes o = (Nodes) object;
                return getStringKey(o.getNodeId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Nodes.class.getName());
            }
        }
    }
}
