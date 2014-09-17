/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.AcceptableDataTypes;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class AcceptableDataTypesFacade extends AbstractFacade<AcceptableDataTypes> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AcceptableDataTypesFacade() {
        super(AcceptableDataTypes.class);
    }
    
}
