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
public class NodeTypeTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long NTId;
    @ManyToOne
    @JoinColumn (name = "PId")
    private ProjectTable Project;
    @OneToMany (targetEntity = NodeVariableTable.class, mappedBy = "NodeType")
    private Collection NodeVariables;
    @OneToMany (targetEntity = DataDefinitionTable.class, mappedBy = "NodeType")
    private Collection DataDefinitions;
    @OneToMany (targetEntity = NodeTable.class, mappedBy = "NodeType")
    private Collection NodeType;
    private String NTName;
    private Date NTCreationDate;

    public ProjectTable getProject() {
        return Project;
    }

    public void setProject(ProjectTable Project) {
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

    public Collection getNodeType() {
        return NodeType;
    }

    public void setNodeType(Collection NodeType) {
        this.NodeType = NodeType;
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
    
    public Long getNTId() {
        return NTId;
    }

    public void setNTId(Long NTId) {
        this.NTId = NTId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (NTId != null ? NTId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NodeTypeTable)) {
            return false;
        }
        NodeTypeTable other = (NodeTypeTable) object;
        if ((this.NTId == null && other.NTId != null) || (this.NTId != null && !this.NTId.equals(other.NTId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.NodeTypeTable[ id=" + NTId + " ]";
    }
    
}
