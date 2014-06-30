/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.sql.Date;
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
public class NodeTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NodeTypeId;
    
    @ManyToOne
    @JoinColumn (name = "ProjectsId")
    private Projects Project;
    
    @OneToMany (targetEntity = NodeVariables.class, mappedBy = "NodeType")
    private Collection NodeVariables;
    
    @OneToMany (targetEntity = DataDefinitions.class, mappedBy = "NodeType")
    private Collection DataDefinitions;
    
    @OneToMany (targetEntity = Nodes.class, mappedBy = "NodeType")
    private Collection NodeTypes;
    
    private String NTName;
    private Date NTCreationDate;

    public Projects getProject() {
        return Project;
    }

    public void setProject(Projects Project) {
        this.Project = Project;
    }

    public Collection getNodeVariables() {
        return NodeVariables;
    }

    public void setNodeVariables(Collection NodeVariables) {
        this.NodeVariables = NodeVariables;
    }

    public Collection getDataDefinitions() {
        return DataDefinitions;
    }

    public void setDataDefinitions(Collection DataDefinitions) {
        this.DataDefinitions = DataDefinitions;
    }

    public Collection getNodeTypes() {
        return NodeTypes;
    }

    public void setNodeTypes(Collection NodeTypes) {
        this.NodeTypes = NodeTypes;
    }

    public String getNTName() {
        return NTName;
    }

    public void setNTName(String NTName) {
        this.NTName = NTName;
    }

    public Date getNTCreationDate() {
        return NTCreationDate;
    }

    public void setNTCreationDate(Date NTCreationDate) {
        this.NTCreationDate = NTCreationDate;
    }

    
    
    public Long getNodeTypeId() {
        return NodeTypeId;
    }

    public void setNodeTypeId(Long NodeTypeId) {
        this.NodeTypeId = NodeTypeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (NodeTypeId != null ? NodeTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeTypes)) {
            return false;
        }
        NodeTypes other = (NodeTypes) object;
        if ((this.NodeTypeId == null && other.NodeTypeId != null) || (this.NodeTypeId != null && !this.NodeTypeId.equals(other.NodeTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NodeTypes[ id=" + NodeTypeId + " ]";
    }
    
}
