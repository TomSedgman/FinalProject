/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Nodes;
import Entity.Projects;
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
public class NodesFacade extends AbstractFacade<Nodes> 
{
        
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NodesFacade() {
        super(Nodes.class);
    }
    public List nodesList(Projects project)
    {
        List<Nodes> nodeList= em.createNamedQuery("findNodesByProject", Nodes.class).setParameter("project",project).getResultList();
        
        return nodeList;
    }
}
