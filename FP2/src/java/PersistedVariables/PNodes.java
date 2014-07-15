/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistedVariables;

import Entity.Projects;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author t_sedgman
 */
@Named (value = "nodes")
@Stateless
public class PNodes {

    /**
     * Creates a new instance of Project
     */
    public PNodes() 
    {
    }
    List<Entity.Nodes> currentNodes;

    public List<Entity.Nodes> getCurrentNodes() {
        return currentNodes;
    }

    public void setCurrentNodes(List<Entity.Nodes> currentNodes) {
        this.currentNodes = currentNodes;
    }

    

    
    

}
