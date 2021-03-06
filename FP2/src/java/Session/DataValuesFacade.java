 /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.DataValues;
import Entities.NodeTypes;
import Entities.Nodes;
import Entities.Projects;
import java.util.ArrayList;
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
    @EJB
    private PersistedVariables.PProject currProject;
    
    public void RecordData(String[] Record)
    {
//        Projects currentProject = new Projects();
        int position = currProject.getCurrentProject().getSourceVariable();
        Date timeStamp = Calendar.getInstance().getTime();
        String id = Record[position];
        Nodes currentNode = findProjectNode(currProject.getCurrentProject(), id);
        for (int i = 0; i<Record.length; i++)
        {
            DataValues data = new DataValues();
            data.setNode(currentNode);
            data.setdVariable(Record[i]);
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
    
     public Nodes findProjectNode(Projects project, String nTIdentifier)
    {
         Nodes node = em.createNamedQuery("findNodeByProject", Nodes.class).setParameter("project", project).setParameter("nTIdentifier", nTIdentifier).getSingleResult();
         return node;
    }
      public List<Nodes> findProjectNodes(Projects project)
    {
         List<Nodes> node = em.createNamedQuery("findNodesByProject", Nodes.class).setParameter("project", project).getResultList();
         return node;
    }
      public List findVariables(Nodes node, int variable1, int variable2)
    {
         List<DataValues> returnData = em.createNamedQuery("findVariablesByTypeAndNode", DataValues.class).setParameter("node", node).setParameter("variable1", variable1).setParameter("variable2", variable2).getResultList();
         return returnData;
    }
        public List findVariable(Nodes node, int variable1)
    {
         List<DataValues> returnData = em.createNamedQuery("findVariableByTypeAndNode", DataValues.class).setParameter("node", node).setParameter("variable1", variable1).getResultList();
         return returnData;
    }
    public List findRecord(Date time)
    {
        List<DataValues> returnData = em.createNamedQuery("findRecords",DataValues.class).setParameter("time", time).getResultList();
        return returnData;
    }
    public List findOrderedData(Nodes node, int var1, int var2)
    {
        List returnData = em.createNamedQuery("findVariablesByTypeAndNodeOrdered",DataValues.class).setParameter("node", node).setParameter("variable1", var1).setParameter("variable2", var2).getResultList();
        return returnData;
    }
    public String bulkUpload(List<String> Record)
    {
        
        return "success";
    }
}
