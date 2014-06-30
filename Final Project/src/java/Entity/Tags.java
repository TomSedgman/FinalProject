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
public class Tags implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long TagId;

    @ManyToOne
    @JoinColumn (name = "ProjectId")
    private Projects Project;
    
    private String Tags;

    public Projects getProject() {
        return Project;
    }

    public void setProject(Projects Project) {
        this.Project = Project;
    }

    public String getTags() {
        return Tags;
    }

    public void setTags(String Tags) {
        this.Tags = Tags;
    }
    
    
    
    
    public Long getTagId() {
        return TagId;
    }

    public void setTagId(Long TagId) {
        this.TagId = TagId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (TagId != null ? TagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tags)) {
            return false;
        }
        Tags other = (Tags) object;
        if ((this.TagId == null && other.TagId != null) || (this.TagId != null && !this.TagId.equals(other.TagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Tags[ id=" + TagId + " ]";
    }
    
}
