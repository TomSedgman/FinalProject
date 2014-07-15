/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author t_sedgman
 */
@Entity
public class NodeTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nodeTypeId;
    
    @ManyToOne
    @JoinColumn(name = "projectId")
    private Projects project;
    
    @OneToMany(targetEntity = NodeVariables.class, mappedBy = "nodeType")
    private Collection nodeVariables;
    
    @OneToMany(targetEntity = DataDefinitions.class, mappedBy = "nodeType")
    private Collection dataDefinitions;
    
    @OneToMany(targetEntity = Nodes.class, mappedBy = "nodeType")
    private Collection nodes;
    
    private String nTName;
    @Temporal(TemporalType.DATE)
    private Date nTCreationDate;

    public Long getNodeTypeId() {
        return nodeTypeId;
    }

    public void setNodeTypeId(Long nodeTypeId) {
        this.nodeTypeId = nodeTypeId;
    }

    public Projects getProject() {
        return project;
    }

    public void setProject(Projects project) {
        this.project = project;
    }

    public Collection getNodeVariables() {
        return nodeVariables;
    }

    public void setNodeVariables(Collection nodeVariables) {
        this.nodeVariables = nodeVariables;
    }

    public Collection getDataDefinitions() {
        return dataDefinitions;
    }

    public void setDataDefinitions(Collection dataDefinitions) {
        this.dataDefinitions = dataDefinitions;
    }

    public Collection getNodes() {
        return nodes;
    }

    public void setNodes(Collection nodes) {
        this.nodes = nodes;
    }

    public String getnTName() {
        return nTName;
    }

    public void setnTName(String nTName) {
        this.nTName = nTName;
    }

    public Date getnTCreationDate() {
        return nTCreationDate;
    }

    public void setnTCreationDate(Date nTCreationDate) {
        this.nTCreationDate = nTCreationDate;
    }
    
    
    
    public Long getId() {
        return nodeTypeId;
    }

    public void setId(Long id) {
        this.nodeTypeId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nodeTypeId != null ? nodeTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeTypes)) {
            return false;
        }
        NodeTypes other = (NodeTypes) object;
        if ((this.nodeTypeId == null && other.nodeTypeId != null) || (this.nodeTypeId != null && !this.nodeTypeId.equals(other.nodeTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NodeTypes[ id=" + nodeTypeId + " ]";
    }
    
}
