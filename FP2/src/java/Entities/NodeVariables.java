/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author t_sedgman
 */

@NamedQuery (name = "findAllVariablesbyProject", query= "SELECT e FROM NodeVariables e WHERE e.nodeType = :project")

@Entity
public class NodeVariables implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nodeVariableId;

    @ManyToOne
    @JoinColumn(name = "nodeTypeId")
    private NodeTypes nodeType;
    
    private String nVName;
    @Temporal(TemporalType.DATE)    
    private Date nVDate;
    private String nVValue;

    public Long getNodeVariableId() {
        return nodeVariableId;
    }

    public void setNodeVariableId(Long nodeVariableId) {
        this.nodeVariableId = nodeVariableId;
    }

    public NodeTypes getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeTypes nodeType) {
        this.nodeType = nodeType;
    }

    public String getnVName() {
        return nVName;
    }

    public void setnVName(String nVName) {
        this.nVName = nVName;
    }

    public Date getnVDate() {
        return nVDate;
    }

    public void setnVDate(Date nVDate) {
        this.nVDate = nVDate;
    }

    public String getnVValue() {
        return nVValue;
    }

    public void setnVValue(String nVValue) {
        this.nVValue = nVValue;
    }
    
    
    
    public Long getId() {
        return nodeVariableId;
    }

    public void setId(Long id) {
        this.nodeVariableId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeVariableId != null ? nodeVariableId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeVariables)) {
            return false;
        }
        NodeVariables other = (NodeVariables) object;
        if ((this.nodeVariableId == null && other.nodeVariableId != null) || (this.nodeVariableId != null && !this.nodeVariableId.equals(other.nodeVariableId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NodeVariables[ id=" + nodeVariableId + " ]";
    }
    
}
