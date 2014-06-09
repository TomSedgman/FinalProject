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
public class DataTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Did;
    @ManyToOne
    @JoinColumn (name = "NId")
    private NodeTable Node;
    private String DVariable;
    private Date DTimeStamp;
    private int VariablePositionId;

    public NodeTable getNode() {
        return Node;
    }

    public void setNode(NodeTable Node) {
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
    
    public Long getDId() {
        return Did;
    }

    public void setDId(Long DId) {
        this.Did = DId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (Did != null ? Did.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataTable)) {
            return false;
        }
        DataTable other = (DataTable) object;
        if ((this.Did == null && other.Did != null) || (this.Did != null && !this.Did.equals(other.Did))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DataTable[ id=" + Did + " ]";
    }
    
}
