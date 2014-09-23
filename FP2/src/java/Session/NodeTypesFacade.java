/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.NodeTypes;
import Entities.Projects;
import java.util.Date;
import java.util.List;
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

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NodeTypesFacade() {
        super(NodeTypes.class);
    }
    public List allByProject (Projects project)
    {
        List dataDefinitions=  em.createNamedQuery("findAllNodeTypesbyProject", NodeTypes.class).setParameter("project",project).getResultList();
        return dataDefinitions;
    }
}
