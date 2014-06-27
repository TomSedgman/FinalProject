/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author t_sedgman
 */
@Entity
public class NodeTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NId;
    @OneToMany (targetEntity = DataTable.class, mappedBy = "NId")
    private Collection Data;
    @ManyToOne
    @JoinColumn (name = "NTId")
    private NodeTypeTable NodeType;
    private String NTIdentifier;

    public Collection getData() {
        return Data;
    }

    public void setData(Collection Data) {
        this.Data = Data;
    }

    public NodeTypeTable getNodeType() {
        return NodeType;
    }

    public void setNodeType(NodeTypeTable NodeType) {
        this.NodeType = NodeType;
    }

    public String getNTIdentifier() {
        return NTIdentifier;
    }

    public void setNTIdentifier(String NTIdentifier) {
        this.NTIdentifier = NTIdentifier;
    }
    
    public Long getNId() {
        return NId;
    }

    public void setNId(Long NId) {
        this.NId = NId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (NId != null ? NId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeTable)) {
            return false;
        }
        NodeTable other = (NodeTable) object;
        if ((this.NId == null && other.NId != null) || (this.NId != null && !this.NId.equals(other.NId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NodeTable[ id=" + NId + " ]";
    }
    
}
