/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author t_sedgman
 */
@Entity
public class RecordTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long RecordId;
    private Long UserId;
    private boolean Privacy;
    private String SiteName;
    private Date StartDate;
    private Float GPSLat;
    private Float GPSLong;
    private String Notes;
    
    public Long getRecordId() {
        return RecordId;
    }

    public void setRecordId(Long RecordId) {
        this.RecordId = RecordId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (RecordId != null ? RecordId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RecordTable)) {
            return false;
        }
        RecordTable other = (RecordTable) object;
        if ((this.RecordId == null && other.RecordId != null) || (this.RecordId != null && !this.RecordId.equals(other.RecordId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.RecordTable[ id=" + RecordId + " ]";
    }
    
}
