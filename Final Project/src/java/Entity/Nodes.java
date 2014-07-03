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
    private Long nodeId;

    @OneToMany(targetEntity = DataValues.class, mappedBy = "node")
    private Collection dataValues;
    
    @ManyToOne
    @JoinColumn(name = "nodeTypeId")
    private NodeTypes nodeType;
    
    private String nTIdentifier;

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public Collection getDataValues() {
        return dataValues;
    }

    public void setDataValues(Collection dataValues) {
        this.dataValues = dataValues;
    }

    public NodeTypes getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeTypes nodeType) {
        this.nodeType = nodeType;
    }

    public String getnTIdentifier() {
        return nTIdentifier;
    }

    public void setnTIdentifier(String nTIdentifier) {
        this.nTIdentifier = nTIdentifier;
    }
    
    
    
    public Long getId() {
        return nodeId;
    }

    public void setId(Long id) {
        this.nodeId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeId != null ? nodeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nodes)) {
            return false;
        }
        Nodes other = (Nodes) object;
        if ((this.nodeId == null && other.nodeId != null) || (this.nodeId != null && !this.nodeId.equals(other.nodeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.Nodes[ id=" + nodeId + " ]";
    }
    
}
