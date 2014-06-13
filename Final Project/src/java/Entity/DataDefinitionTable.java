/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
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
public class DataDefinitionTable implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long DDId;
    @ManyToOne
    @JoinColumn (name = "NTId")
    private NodeTypeTable NodeType;
    @OneToMany (targetEntity = DuplicateTable.class, mappedBy = "DataDefinition")
    private Collection Duplicates;
    private String DDName;
    private String DDUnit;
    private String DDMaxValue;
    private String DDMinValue;
    private boolean DDNullsAllowed;
    private boolean DDZeroStringsAllowed;
    private String DDNotes;
    private String DDType; // replace with enum later
    private int VariablePositionId;

    public NodeTypeTable getNodeType() {
        return NodeType;
    }

    public void setNodeType(NodeTypeTable NodeType) {
        this.NodeType = NodeType;
    }

    public Collection getDuplicates() {
        return Duplicates;
    }

    public void setDuplicates(Collection Duplicates) {
        this.Duplicates = Duplicates;
    }

    public String getDDName() {
        return DDName;
    }

    public void setDDName(String DDName) {
        this.DDName = DDName;
    }

    public String getDDUnit() {
        return DDUnit;
    }

    public void setDDUnit(String DDUnit) {
        this.DDUnit = DDUnit;
    }

    public String getDDMaxValue() {
        return DDMaxValue;
    }

    public void setDDMaxValue(String DDMaxValue) {
        this.DDMaxValue = DDMaxValue;
    }

    public String getDDMinValue() {
        return DDMinValue;
    }

    public void setDDMinValue(String DDMinValue) {
        this.DDMinValue = DDMinValue;
    }

    public boolean isDDNullsAllowed() {
        return DDNullsAllowed;
    }

    public void setDDNullsAllowed(boolean DDNullsAllowed) {
        this.DDNullsAllowed = DDNullsAllowed;
    }

    public boolean isDDZeroStringsAllowed() {
        return DDZeroStringsAllowed;
    }

    public void setDDZeroStringsAllowed(boolean DDZeroStringsAllowed) {
        this.DDZeroStringsAllowed = DDZeroStringsAllowed;
    }

    public String getDDNotes() {
        return DDNotes;
    }

    public void setDDNotes(String DDNotes) {
        this.DDNotes = DDNotes;
    }

    public String getDDType() {
        return DDType;
    }

    public void setDDType(String DDType) {
        this.DDType = DDType;
    }

    public int getVariablePositionId() {
        return VariablePositionId;
    }

    public void setVariablePositionId(int VariablePositionId) {
        this.VariablePositionId = VariablePositionId;
    }
    
    public Long getDDId() {
        return DDId;
    }

    public void setDDId(Long DDId) {
        this.DDId = DDId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (DDId != null ? DDId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DataDefinitionTable)) {
            return false;
        }
        DataDefinitionTable other = (DataDefinitionTable) object;
        if ((this.DDId == null && other.DDId != null) || (this.DDId != null && !this.DDId.equals(other.DDId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entity.DataDefinitionTable[ id=" + DDId + " ]";
    }
    
}
