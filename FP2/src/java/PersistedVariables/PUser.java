/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PersistedVariables;

import Entities.Users;
import javax.ejb.Stateless;
import javax.inject.Named;

/**
 *
 * @author t_sedgman
 */
@Named (value = "user")
@Stateless
public class PUser {

    /**
     * Creates a new instance of PUser
     */
    public PUser() 
    {
    }
    private Users currentUser;

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }
    
}
