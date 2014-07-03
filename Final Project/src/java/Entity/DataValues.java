/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author t_sedgman
 */
@Entity
public class DataValues implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dataValueId;
    
    @ManyToOne
    @JoinColumn(name = "nodeId")
    private Nodes node;
    
    private String dVariable;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dTimeStamp;
    private int variablePositionId;

    public Long getDataValueId() {
        return dataValueId;
    }

    public void setDataValueId(Long dataValueId) {
        this.dataValueId = dataValueId;
    }

    public Nodes getNode() {
        return node;
    }

    public void setNode(Nodes node) {
        this.node = node;
    }

    public String getdVariable() {
        return dVariable;
    }

    public void setdVariable(String dVariable) {
        this.dVariable = dVariable;
    }

    public Date getdTimeStamp() {
        return dTimeStamp;
    }

    public void setdTimeStamp(Date dTimeStamp) {
        this.dTimeStamp = dTimeStamp;
    }

    public int getVariablePositionId() {
        return variablePositionId;
    }

    public void setVariablePositionId(int variablePositionId) {
        this.variablePositionId = variablePositionId;
    }

    
    
    public Long getId() {
        return dataValueId;
    }

    public void setId(Long id) {
        this.dataValueId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataValueId != null ? dataValueId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataValues)) {
            return false;
        }
        DataValues other = (DataValues) object;
        if ((this.dataValueId == null && other.dataValueId != null) || (this.dataValueId != null && !this.dataValueId.equals(other.dataValueId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Data[ id=" + dataValueId + " ]";
    }
    
}
