/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.Projects;
import Entities.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class ProjectsFacade extends AbstractFacade<Projects> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    public void createNewProject(Users current) {
        Projects newProject = new Projects();
        newProject.setUser(current);
        newProject.setProjectName("PlaceHolder");
        newProject.setPrivacy(false);
        em.persist(newProject);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProjectsFacade() {
        super(Projects.class);
    }
    public List findProjectsByUser(String username)
    {
         List projects =  em.createNamedQuery("findProjectsbyUser", Projects.class).setParameter("username", username).getResultList();
         return projects;
    }
    public Projects findProjectsByName(String projectName, String username)
    {
         Projects projects =  em.createNamedQuery("findProjectsbyName", Projects.class).setParameter("projectName", projectName).setParameter("username", username).getSingleResult();
         return projects;
    }
    
}
