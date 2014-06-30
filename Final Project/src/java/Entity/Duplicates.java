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
public class Duplicates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long DuplicateId;

    @ManyToOne
    @JoinColumn (name = "DataDefinitionId")
    private DataDefinitions DataDefinition;

    public DataDefinitions getDataDefinition() {
        return DataDefinition;
    }

    public void setDataDefinition(DataDefinitions DataDefinition) {
        this.DataDefinition = DataDefinition;
    }
    
    
    
    public Long getDuplicateId() {
        return DuplicateId;
    }

    public void setDuplicateId(Long DuplicateId) {
        this.DuplicateId = DuplicateId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DuplicateId != null ? DuplicateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Duplicates)) {
            return false;
        }
        Duplicates other = (Duplicates) object;
        if ((this.DuplicateId == null && other.DuplicateId != null) || (this.DuplicateId != null && !this.DuplicateId.equals(other.DuplicateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Duplicates[ id=" + DuplicateId + " ]";
    }
    
}
