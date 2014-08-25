/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author t_sedgman
 */
@NamedQuery (name = "findUserbyUsername", query =   "SELECT e FROM Users e WHERE e.username = :username")
@Entity
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    
    @OneToMany(targetEntity = Projects.class, mappedBy = "user")
    private Collection project;
    
    private String username;
    private String password;
    private String passwordHint;
    private String email;
    private String forename;
    private String surname;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection getProject() {
        return project;
    }

    public void setProject(Collection project) {
        this.project = project;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHint() {
        return passwordHint;
    }

    public void setPasswordHint(String passwordHint) {
        this.passwordHint = passwordHint;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getForename() {
        return forename;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    
    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Users[ id=" + userId + " ]";
    }
    
}
