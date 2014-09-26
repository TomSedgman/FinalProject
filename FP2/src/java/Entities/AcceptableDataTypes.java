/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

/**
 *
 * @author t_sedgman
 */
@NamedQuery (name="findAll", query= "SELECT e.dDTypeValue FROM AcceptableDataTypes e")
@Entity
public class AcceptableDataTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dDTypeValue;

    public String getdDTypeValue() {
        return dDTypeValue;
    }

    public void setdDTypeValue(String dDTypeValue) {
        this.dDTypeValue = dDTypeValue;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcceptableDataTypes)) {
            return false;
        }
        AcceptableDataTypes other = (AcceptableDataTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Entities.AcceptableDataTypes[ id=" + id + " ]";
    }
    
}
