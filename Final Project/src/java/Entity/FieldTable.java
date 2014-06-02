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

/**
 *
 * @author t_sedgman
 */
@Entity
public class FieldTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long FieldId;
    private Long RecordId;
    private int FieldNumber;
    private String DataType;
    private String DataUnit;
    private String Notes;
    
    public Long getFieldId() {
        return FieldId;
    }

    public void setFieldId(Long FieldId) {
        this.FieldId = FieldId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (FieldId != null ? FieldId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FieldTable)) {
            return false;
        }
        FieldTable other = (FieldTable) object;
        if ((this.FieldId == null && other.FieldId != null) || (this.FieldId != null && !this.FieldId.equals(other.FieldId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.FieldTable[ id=" + FieldId + " ]";
    }
    
}
