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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author t_sedgman
 */
@NamedQuery (name = "findProjectsbyUser", query =   "SELECT e FROM Projects e WHERE e.user.username = :username")
@Entity
public class Projects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long projectId;
    
    @ManyToOne
    @JoinColumn(name = "userId")
    private Users user;

    @OneToMany(targetEntity = Tags.class, mappedBy = "project")
    private Collection tags;
    
    @OneToMany(targetEntity = NodeTypes.class, mappedBy = "project")
    private Collection nodeType;
    
    private boolean privacy;
    private String projectName;
    private String projectNotes;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Collection getTags() {
        return tags;
    }

    public void setTags(Collection tags) {
        this.tags = tags;
    }

    public Collection getNodeType() {
        return nodeType;
    }

    public void setNodeType(Collection nodeType) {
        this.nodeType = nodeType;
    }

    public boolean isPrivacy() {
        return privacy;
    }

    public void setPrivacy(boolean privacy) {
        this.privacy = privacy;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectNotes() {
        return projectNotes;
    }

    public void setProjectNotes(String projectNotes) {
        this.projectNotes = projectNotes;
    }

    
    
    public Long getId() {
        return projectId;
    }

    public void setId(Long id) {
        this.projectId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (projectId != null ? projectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.projectId == null && other.projectId != null) || (this.projectId != null && !this.projectId.equals(other.projectId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Projects[ id=" + projectId + " ]";
    }
    
}
