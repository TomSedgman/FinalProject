/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author t_sedgman
 */
@NamedQueries(
{
@NamedQuery (name = "findFieldsByName", query =   "SELECT e.nodeType.dataDefinitions FROM Nodes e WHERE e.nTIdentifier = :nTIdentifier"),
@NamedQuery (name = "findTypebyName", query =   "SELECT e FROM DataDefinitions e WHERE e.nodeType.project.projectId = :projectId and e.variablePositionId = :variable"),
@NamedQuery (name = "findAllDefinitionsbyProject", query= "SELECT e FROM DataDefinitions e WHERE e.nodeType.project = :project")

})
@Entity
public class DataDefinitions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long dataDefinitionId;

    @ManyToOne
    @JoinColumn(name = "nodeTypeId")
    private NodeTypes nodeType;

    @OneToMany(targetEntity = Duplicates.class, mappedBy = "dataDefinition")
    private Collection duplicates;
    
    private String dDName;
    private String dDUnit;
    private String dDMaxValue;
    private String dDMinValue;
    private boolean dDNullsAllowed;
    private boolean dDZeroStringsAllowed;
    private String dDNotes;
    private String dDType; // replace with restrict later [DONE]
    private int variablePositionId;

    public Long getDataDefinitionId() {
        return dataDefinitionId;
    }

    public void setDataDefinitionId(Long dataDefinitionId) {
        this.dataDefinitionId = dataDefinitionId;
    }

    public NodeTypes getNodeType() {
        return nodeType;
    }

    public void setNodeType(NodeTypes nodeType) {
        this.nodeType = nodeType;
    }

    public Collection getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(Collection duplicates) {
        this.duplicates = duplicates;
    }

    public String getdDName() {
        return dDName;
    }

    public void setdDName(String dDName) {
        this.dDName = dDName;
    }

    public String getdDUnit() {
        return dDUnit;
    }

    public void setdDUnit(String dDUnit) {
        this.dDUnit = dDUnit;
    }

    public String getdDMaxValue() {
        return dDMaxValue;
    }

    public void setdDMaxValue(String dDMaxValue) {
        this.dDMaxValue = dDMaxValue;
    }

    public String getdDMinValue() {
        return dDMinValue;
    }

    public void setdDMinValue(String dDMinValue) {
        this.dDMinValue = dDMinValue;
    }

    public boolean isdDNullsAllowed() {
        return dDNullsAllowed;
    }

    public void setdDNullsAllowed(boolean dDNullsAllowed) {
        this.dDNullsAllowed = dDNullsAllowed;
    }

    public boolean isdDZeroStringsAllowed() {
        return dDZeroStringsAllowed;
    }

    public void setdDZeroStringsAllowed(boolean dDZeroStringsAllowed) {
        this.dDZeroStringsAllowed = dDZeroStringsAllowed;
    }

    public String getdDNotes() {
        return dDNotes;
    }

    public void setdDNotes(String dDNotes) {
        this.dDNotes = dDNotes;
    }

    public String getdDType() {
        return dDType;
    }

    public void setdDType(String dDType) {
        this.dDType = dDType;
    }

    public int getVariablePositionId() {
        return variablePositionId;
    }

    public void setVariablePositionId(int variablePositionId) {
        this.variablePositionId = variablePositionId;
    }
    
    
    
    public Long getId() {
        return dataDefinitionId;
    }

    public void setId(Long id) {
        this.dataDefinitionId = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dataDefinitionId != null ? dataDefinitionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataDefinitions)) {
            return false;
        }
        DataDefinitions other = (DataDefinitions) object;
        if ((this.dataDefinitionId == null && other.dataDefinitionId != null) || (this.dataDefinitionId != null && !this.dataDefinitionId.equals(other.dataDefinitionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DataDefinitions[ id=" + dataDefinitionId + " ]";
    }
    
}
