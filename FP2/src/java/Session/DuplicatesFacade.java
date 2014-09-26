/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.Duplicates;
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
public class DuplicatesFacade extends AbstractFacade<Duplicates> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DuplicatesFacade() {
        super(Duplicates.class);
    }
    public List allDuplicates(Projects project)
    {
        List<Duplicates> Duplicates= em.createNamedQuery("findAllDuplicatesbyProject", Duplicates.class).setParameter("project",project).getResultList();
        
        return Duplicates;
    }
}
