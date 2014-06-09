/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Sesion;

import Entity.NodeTypeTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class NodeTypeTableFacade extends AbstractFacade<NodeTypeTable> {
    @PersistenceContext(unitName = "MetMonitoring")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NodeTypeTableFacade() {
        super(NodeTypeTable.class);
    }
    
}
