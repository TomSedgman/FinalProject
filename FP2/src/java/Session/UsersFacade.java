/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Session;

import Entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author t_sedgman
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "FP2PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    public Users findUser(String username)
    {
         Users user =  em.createNamedQuery("findUserbyUsername", Users.class).setParameter("username", username).getSingleResult();
         return user;
    }
    
}
