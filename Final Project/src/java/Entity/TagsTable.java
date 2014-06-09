/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author t_sedgman
 */
@Entity
public class TagsTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TId;
    @ManyToOne
    @JoinColumn (name = "PId")
    private ProjectTable Project;
    private String Tags;

    public ProjectTable getProject() {
        return Project;
    }

    public void setProject(ProjectTable Project) {
        this.Project = Project;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }
    
    public Long getTId() {
        return TId;
    }

    public void setTId(Long TId) {
        this.TId = TId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (TId != null ? TId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TagsTable)) {
            return false;
        }
        TagsTable other = (TagsTable) object;
        if ((this.TId == null && other.TId != null) || (this.TId != null && !this.TId.equals(other.TId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.TagsTable[ id=" + TId + " ]";
    }
    
}
