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
public class DuplicateTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long DUId;
    @ManyToOne
    @JoinColumn (name = "DDIdId")
    private DataDefinitionTable DataDefinition;

    public DataDefinitionTable getDataDefinition() {
        return DataDefinition;
    }

    public void setDataDefinition(DataDefinitionTable DataDefinition) {
        this.DataDefinition = DataDefinition;
    }
    
    public Long getDUId() {
        return DUId;
    }
//Add identification for duplicate records
    public void setDUId(Long DUId) {
        this.DUId = DUId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DUId != null ? DUId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DuplicateTable)) {
            return false;
        }
        DuplicateTable other = (DuplicateTable) object;
        if ((this.DUId == null && other.DUId != null) || (this.DUId != null && !this.DUId.equals(other.DUId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DuplicateTable[ id=" + DUId + " ]";
    }
    
}
