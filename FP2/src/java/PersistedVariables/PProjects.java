/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistedVariables;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author t_sedgman
 */
@Named (value="projects")
@Stateless
public class PProjects {

    /**
     * Creates a new instance of PProjects
     */
    public PProjects() {
    }
    List<PProjects> projectList;

    public List<PProjects> getProjects() {
        return projectList;
    }

    public void setProjects(List<PProjects> projects) {
        this.projectList = projects;
    }
    
}
