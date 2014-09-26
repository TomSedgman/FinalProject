/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.Projects;
import Entities.Tags;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class TagsFacade extends AbstractFacade<Tags> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TagsFacade() {
        super(Tags.class);
    }
    public List allTags(Projects project)
    {
        List<Tags> tags= em.createNamedQuery("findAllTagsbyProject", Tags.class).setParameter("project",project).getResultList();
        
        return tags;
    }
}
