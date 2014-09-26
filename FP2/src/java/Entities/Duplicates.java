/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

/**
 *
 * @author t_sedgman
 */
@NamedQuery (name = "findAllDuplicatesbyProject", query= "SELECT e FROM Duplicates e WHERE e.dataDefinition.nodeType.project = :project")

@Entity
public class Duplicates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long duplicateId;

    @ManyToOne
    @JoinColumn(name = "dataDefinitionId")
    private DataDefinitions dataDefinition;

    public Long getDuplicateId() {
        return duplicateId;
    }

    public void setDuplicateId(Long duplicateId) {
        this.duplicateId = duplicateId;
    }

    public DataDefinitions getDataDefinition() {
        return dataDefinition;
    }

    public void setDataDefinition(DataDefinitions dataDefinition) {
        this.dataDefinition = dataDefinition;
    }
    
    
    
    public Long getId() {
        return duplicateId;
    }

    public void setId(Long id) {
        this.duplicateId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (duplicateId != null ? duplicateId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Duplicates)) {
            return false;
        }
        Duplicates other = (Duplicates) object;
        if ((this.duplicateId == null && other.duplicateId != null) || (this.duplicateId != null && !this.duplicateId.equals(other.duplicateId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Duplicates[ id=" + duplicateId + " ]";
    }
    
}
