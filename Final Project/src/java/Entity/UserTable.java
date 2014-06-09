/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author t_sedgman
 */
@Entity
public class UserTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long UId;
    @OneToMany (targetEntity = ProjectTable.class, mappedBy = "User")
    private Collection Projects;
    private String Username;
    private String Password;
    private String PasswordHint;
    private String Email;
    private String Forename;
    private String Surname;

    public Collection getProjects() {
        return Projects;
    }

    public void setProjects(Collection Projects) {
        this.Projects = Projects;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getPasswordHint() {
        return PasswordHint;
    }

    public void setPasswordHint(String PasswordHint) {
        this.PasswordHint = PasswordHint;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getForename() {
        return Forename;
    }

    public void setForename(String Forename) {
        this.Forename = Forename;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String Surname) {
        this.Surname = Surname;
    }
    
    public Long getUId() {
        return UId;
    }

    public void setUId(Long UId) {
        this.UId = UId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (UId != null ? UId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTable)) {
            return false;
        }
        UserTable other = (UserTable) object;
        if ((this.UId == null && other.UId != null) || (this.UId != null && !this.UId.equals(other.UId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.User[ id=" + UId + " ]";
    }
    
}
