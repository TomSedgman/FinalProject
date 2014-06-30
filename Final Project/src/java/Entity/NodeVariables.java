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
public class NodeVariables implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NodeVariableId;

    @ManyToOne
    @JoinColumn (name = "NodeTypeId")
    private NodeTypes NodeType;
    
    private String NVName;
    private Date NVDate;
    private String NVValue;

    public NodeTypes getNodeType() {
        return NodeType;
    }

    public void setNodeType(NodeTypes NodeType) {
        this.NodeType = NodeType;
    }

    public String getNVName() {
        return NVName;
    }

    public void setNVName(String NVName) {
        this.NVName = NVName;
    }

    public Date getNVDate() {
        return NVDate;
    }

    public void setNVDate(Date NVDate) {
        this.NVDate = NVDate;
    }

    public String getNVValue() {
        return NVValue;
    }

    public void setNVValue(String NVValue) {
        this.NVValue = NVValue;
    }
    
    
    
    public Long getNodeVariableId() {
        return NodeVariableId;
    }

    public void setNodeVariableId(Long NodeVariableId) {
        this.NodeVariableId = NodeVariableId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (NodeVariableId != null ? NodeVariableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeVariables)) {
            return false;
        }
        NodeVariables other = (NodeVariables) object;
        if ((this.NodeVariableId == null && other.NodeVariableId != null) || (this.NodeVariableId != null && !this.NodeVariableId.equals(other.NodeVariableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NodeVariables[ id=" + NodeVariableId + " ]";
    }
    
}
