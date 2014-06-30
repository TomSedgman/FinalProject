/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entity.Duplicates;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class DuplicatesFacade extends AbstractFacade<Duplicates> {
    @PersistenceContext(unitName = "MetMonitoring")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DuplicatesFacade() {
        super(Duplicates.class);
    }
    
}
