/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author t_sedgman
 */
@Entity
public class Nodes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NodeId;

    @OneToMany (targetEntity = Data.class, mappedBy = "Node")
    private Collection Data;
    
    @ManyToOne
    @JoinColumn (name = "NodeTypeId")
    private NodeTypes NodeType;
    
    private String NTIdentifier;

    public Collection getData() {
        return Data;
    }

    public void setData(Collection Data) {
        this.Data = Data;
    }

    public NodeTypes getNodeType() {
        return NodeType;
    }

    public void setNodeType(NodeTypes NodeType) {
        this.NodeType = NodeType;
    }

    public String getNTIdentifier() {
        return NTIdentifier;
    }

    public void setNTIdentifier(String NTIdentifier) {
        this.NTIdentifier = NTIdentifier;
    }
    
    
    
    public Long getNodeId() {
        return NodeId;
    }

    public void setNodeId(Long NodeId) {
        this.NodeId = NodeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (NodeId != null ? NodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodes)) {
            return false;
        }
        Nodes other = (Nodes) object;
        if ((this.NodeId == null && other.NodeId != null) || (this.NodeId != null && !this.NodeId.equals(other.NodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Nodes[ id=" + NodeId + " ]";
    }
    
}
