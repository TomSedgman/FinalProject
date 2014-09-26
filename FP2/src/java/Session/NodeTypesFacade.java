/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.NodeTypes;
import Entities.Projects;
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
public class NodeTypesFacade extends AbstractFacade<NodeTypes> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;
    @EJB
    private PersistedVariables.PProject currProject;
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    public void newNodeType(NodeTypes selected)
    {
        NodeTypes newNodeType = new NodeTypes();
        newNodeType.setProject(currProject.getCurrentProject());
        newNodeType.setnTCreationDate(Calendar.getInstance().getTime());
        newNodeType.setnTName(selected.getnTName());
        em.persist(newNodeType);
    }
    public NodeTypesFacade() {
        super(NodeTypes.class);
    }
    public List allNodeTypes (Projects project)
    {
        List <NodeTypes> nodeTypes=  em.createNamedQuery("findAllNodeTypesbyProject", NodeTypes.class).setParameter("project",project).getResultList();
        return nodeTypes;
    }
}
