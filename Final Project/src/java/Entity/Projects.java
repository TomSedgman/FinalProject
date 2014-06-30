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
import javax.persistence.OneToOne;

/**
 *
 * @author t_sedgman
 */
@Entity
public class Projects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ProjectId;

    @ManyToOne
    @JoinColumn(name = "usersId")
    private Users user;

    @OneToMany (targetEntity = Tags.class, mappedBy = "Project")
    private Collection Tags;
    
    @OneToMany (targetEntity = NodeTypes.class, mappedBy = "Project")
    private Collection NodeType;
    
    private boolean Privacy;
    private String ProjectName;
    private String ProjectNotes;

    public Collection getTags() {
        return Tags;
    }

    public void setTags(Collection Tags) {
        this.Tags = Tags;
    }

    public Collection getNodeType() {
        return NodeType;
    }

    public void setNodeType(Collection NodeType) {
        this.NodeType = NodeType;
    }

    public boolean isPrivacy() {
        return Privacy;
    }

    public void setPrivacy(boolean Privacy) {
        this.Privacy = Privacy;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getProjectNotes() {
        return ProjectNotes;
    }

    public void setProjectNotes(String ProjectNotes) {
        this.ProjectNotes = ProjectNotes;
    }
    
    
    
    
    public Long getProjectId() {
        return ProjectId;
    }

    public void setProjectId(Long ProjectId) {
        this.ProjectId = ProjectId;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

   
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ProjectId != null ? ProjectId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.ProjectId == null && other.ProjectId != null) || (this.ProjectId != null && !this.ProjectId.equals(other.ProjectId))) {
            return false;
        }
        return true;
    }

   
    @Override
    public String toString() {
        return "Entity.Projects[ id=" + ProjectId + " ]";
    }
    
}
