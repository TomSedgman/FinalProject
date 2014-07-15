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
    @PersistenceContext(unitName = "FP2PU")
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
            currentNode = findProjectNodes(currentProject, Record.get(5));
           
            

            for (int i = 0; i<Record.size(); i++)
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
    
     public Nodes findProjectNodes(Projects project, String nTIdentifier)
    {
         Nodes node = em.createNamedQuery("findNodesByProject", Nodes.class).setParameter("project", project).setParameter("nTIdentifier", nTIdentifier).getSingleResult();
         return node;
    }
      public List findVariables(Nodes node, int variable1, int variable2)
    {
         List<DataValues> returnData = em.createNamedQuery("findVariablesByTypeAndNode", DataValues.class).setParameter("node", node).setParameter("variable1", variable1).setParameter("variable2", variable2).getResultList();
         return returnData;
    }
}
