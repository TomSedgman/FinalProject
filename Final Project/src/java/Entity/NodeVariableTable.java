/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author t_sedgman
 */
@Entity
public class NodeVariableTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NVId;
    @ManyToOne
    @JoinColumn (name = "NTId")
    private NodeTypeTable NodeType;
    private String NVName;
    private Date NVDate;
    private String NVValue;
    
    public NodeTypeTable getNodeType() {
        return NodeType;
    }

    public void setNodeType(NodeTypeTable NodeType) {
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
    
    public Long getNVId() {
        return NVId;
    }

    public void setNVId(Long NVId) {
        this.NVId = NVId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (NVId != null ? NVId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeVariableTable)) {
            return false;
        }
        NodeVariableTable other = (NodeVariableTable) object;
        if ((this.NVId == null && other.NVId != null) || (this.NVId != null && !this.NVId.equals(other.NVId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NodeVariableTable[ id=" + NVId + " ]";
    }
    
}
