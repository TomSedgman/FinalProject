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
import javax.persistence.OneToMany;

/**
 *
 * @author t_sedgman
 */
@Entity
public class ProjectTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long PId;
    @ManyToOne
    @JoinColumn (name = "UId")
    private UserTable User;
    @OneToMany (targetEntity = TagsTable.class, mappedBy = "Project")
    private Collection Tags;
    @OneToMany (targetEntity = NodeTypeTable.class, mappedBy = "Project")
    private Collection NodeType;
    private boolean Privacy;
    private String ProjectName;
    private String ProjectNotes;

    public UserTable getUser() {
        return User;
    }

    public void setUser(UserTable User) {
        this.User = User;
    }

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
    
    public Long getPId() {
        return PId;
    }

    public void setPId(Long PId) {
        this.PId = PId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (PId != null ? PId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProjectTable)) {
            return false;
        }
        ProjectTable other = (ProjectTable) object;
        if ((this.PId == null && other.PId != null) || (this.PId != null && !this.PId.equals(other.PId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.ProjectTable[ id=" + PId + " ]";
    }
    
}
