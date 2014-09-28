/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistedVariables;

import Entity.Projects;
import javax.ejb.Stateless;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author t_sedgman
 */
@Named (value = "project")
@Stateless
public class PProject {

    /**
     * Creates a new instance of PProject
     */
    public PProject() 
    {
    }
    Projects currentProject;

    public Projects getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Projects currentProject) {
        this.currentProject = currentProject;
    }
    

}
