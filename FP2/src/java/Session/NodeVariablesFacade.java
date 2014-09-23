/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.NodeVariables;
import Entities.Projects;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class NodeVariablesFacade extends AbstractFacade<NodeVariables> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NodeVariablesFacade() {
        super(NodeVariables.class);
    }
    public List allVariables(Projects project)
    {
        List<NodeVariables> dataDefinitions= em.createNamedQuery("findAllVariablesbyProject", NodeVariables.class).setParameter("project",project).getResultList();
        return dataDefinitions;
    }
    
}
