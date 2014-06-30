/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.DataDefinitions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class DataDefinitionsFacade extends AbstractFacade<DataDefinitions> {
    @PersistenceContext(unitName = "MetMonitoring")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DataDefinitionsFacade() {
        super(DataDefinitions.class);
    }
    
}
