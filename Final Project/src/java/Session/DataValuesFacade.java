/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.DataValues;
import Entity.Nodes;
import Entity.Projects;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class DataValuesFacade extends AbstractFacade<DataValues> {
    @PersistenceContext(unitName = "MetMonitoring")
    private EntityManager em;
    @EJB
    Session.ProjectsFacade projectFacade;
    
        public void RecordData(List<String> Record)
    {
        Projects currentProject = new Projects();
        Nodes currentNode = new Nodes();
            Date timeStamp = Calendar.getInstance().getTime();
            List projects = projectFacade.findProjectsByUser("ths28");
            for (int i = 0; i < projects.size(); i++)
            {
                Projects tempProject = (Projects) projects.get(i);
                if ("Monitoring 1".equals(tempProject.getProjectName()))
                {
                    currentProject = tempProject;
                    i = projects.size();
                }
            }
            List nodes = findProjectNodes(currentProject);
            int i = 0;
            do
            {
                Nodes tempNode = (Nodes) nodes.get(i);
                if (Record.get(5).equalsIgnoreCase(tempNode.getnTIdentifier()))
                {
                    currentNode = tempNode;
                    i = nodes.size();
                }
                else 
                {
                    i++;
                    //TODO add error return, else null node returned
                }
            }
            while (i < nodes.size());
            

            for (i = 0; i<Record.size(); i++)
            {
                DataValues data = new DataValues();
                data.setNode(currentNode);
                data.setdVariable(Record.get(i));
                data.setdTimeStamp(timeStamp);
                data.setVariablePositionId(i);
                em.persist(data);
            }
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DataValuesFacade() {
        super(DataValues.class);
    }
    
     public List<Nodes> findProjectNodes(Projects project)
    {
         List nodes = em.createNamedQuery("findNodesByProject", Nodes.class).setParameter("project", project).getResultList();
         return nodes;
    }
}
