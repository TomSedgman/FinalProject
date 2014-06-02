/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author t_sedgman
 */
@Entity
public class DataTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long DataId;
    private Long RecordId;
    private int FieldValue;
    private Time DateStamp;
    private Float DataValue;
    
    public Long getDataId() {
        return DataId;
    }

    public void setDataId(Long DataId) {
        this.DataId = DataId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DataId != null ? DataId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataTable)) {
            return false;
        }
        DataTable other = (DataTable) object;
        if ((this.DataId == null && other.DataId != null) || (this.DataId != null && !this.DataId.equals(other.DataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DataTable[ id=" + DataId + " ]";
    }
    
}
