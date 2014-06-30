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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author t_sedgman
 */
@Entity
public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long DataId;

    @ManyToOne
    @JoinColumn (name = "NodeId")
    private Nodes Node;
    
    private String DVariable;
    private Date DTimeStamp;
    private int VariablePositionId;

    public Nodes getNode() {
        return Node;
    }

    public void setNode(Nodes Node) {
        this.Node = Node;
    }

    public String getDVariable() {
        return DVariable;
    }

    public void setDVariable(String DVariable) {
        this.DVariable = DVariable;
    }

    public Date getDTimeStamp() {
        return DTimeStamp;
    }

    public void setDTimeStamp(Date DTimeStamp) {
        this.DTimeStamp = DTimeStamp;
    }

    public int getVariablePositionId() {
        return VariablePositionId;
    }

    public void setVariablePositionId(int VariablePositionId) {
        this.VariablePositionId = VariablePositionId;
    }
    
    
    
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
        if (!(object instanceof Data)) {
            return false;
        }
        Data other = (Data) object;
        if ((this.DataId == null && other.DataId != null) || (this.DataId != null && !this.DataId.equals(other.DataId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Data[ id=" + DataId + " ]";
    }
    
}
